package sample;


import java.util.ArrayList;

public class ModeleNbCoup implements Modele {
    Modele modele;
    int nbCoup =0;

    public ModeleNbCoup(Modele modele) {
        this.modele = modele;
    }

    public int nbCoup(){
        return nbCoup;
    }
    public int getLineNumber(){return modele.getLineNumber();}
    public int[] getEtat(){
        return modele.getEtat();
    }
    public int[] getFinish(){return modele.getFinish();}
    public ArrayList<int[]> getCoups(){return modele.getCoups();}
    public void setEtat(int[] etats){modele.setEtat(etats);}

    public void move(int indice) {
        System.out.println("move");
        nbCoup++;
        modele.move(indice);
    }
        public void reset(){
        nbCoup=0;
        modele.reset();
    }

    public void undo(){
        modele.undo();
        nbCoup--;
    }
    public boolean canUndo(){return modele.canUndo();}



    public boolean winornot() {
        for(int i=0;i<getFinish().length;i++){
            if(getEtat()[getFinish()[i]]==4 || getEtat()[getFinish()[i]]==1){
                return false;
            }
        }
        return true;
    }

    public void redo() {
        modele.redo();
        nbCoup++;
    }

    public boolean canRedo() {
        return modele.canRedo();
    }
    public void setTerrain(){modele.setTerrain();}
}
