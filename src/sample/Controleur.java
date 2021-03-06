package sample;

import java.util.ArrayList;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Controleur implements Sujet {
    private static Controleur singleton;

    //commentaire
    //commentaire2
    public static Controleur getControleur() {
        if (singleton == null)
            singleton = new Controleur(new FacadeModele());
        return singleton;
    }

    FacadeModele facadeModele;
    ArrayList<Observateur> observateurs = new ArrayList<Observateur>();

    private Controleur(FacadeModele facadeModele) {
        this.facadeModele = facadeModele;
    }

    public void abonne(Observateur observateur) {
        observateurs.add(observateur);
    }

    @Override
    public void notifie() {
        for (Observateur observateur:observateurs)
            observateur.actualise();
    }

    public void move(int x) {
        facadeModele.move(x);
        notifie();
    }

    public void reset() {
        facadeModele.reset();
        notifie();
    }

    public void undo() {
        facadeModele.undo();
        notifie();
    }

    public CommandeInt commandeNbCoup() {
        return new CommandeInt() {
            @Override
            public int exec() {
                return facadeModele.nbCoup();
            }
        };
    }

    public CommandeTabInt commandeGetEtat() {
        return new CommandeTabInt() {
            @Override
            public int[] exec() {
                return facadeModele.getEtat();
            }
        };
    }

    public CommandeInt getLineNumber(){
        return new CommandeInt() {
            @Override
            public int exec() {
                return facadeModele.getLineNumber();
            }
        };    }

    public void winornot() {
        if(facadeModele.winornot()==true){
            System.out.println("WIN");
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("Vous avez gagné !     VOTRE SCORE : "+ facadeModele.nbCoup());
            alert.setContentText("Que voulez vous faire ?");

            ButtonType buttonReset = new ButtonType("Rejouer");
            ButtonType buttonMenu = new ButtonType("Menu principal");

            alert.getButtonTypes().setAll(buttonReset, buttonMenu);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == buttonReset)
            {
                reset();
            }
            else if(result.get() == buttonMenu)
            {
                // ...
            }

        }
    }

    public boolean canUndo() {
        return facadeModele.canUndo();
    }

    public void redo() {
        facadeModele.redo();
        notifie();
    }

    public boolean canRedo() {
        return facadeModele.canRedo();

    }

    public void setTerrain(){
         facadeModele.setTerrain();
    }
}
