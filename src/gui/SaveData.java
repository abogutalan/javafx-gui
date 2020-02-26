package gui;

import java.io.Serializable;
import aogutala.*;


public class SaveData implements Serializable {
    private static final long serialVersionUID = 1L;
    private Floor dataFloor;

    public SaveData(){
        dataFloor = new Floor();
        
    }

    public void setFloor(Floor floor){
        this.dataFloor = floor;
    }
    public Floor getFloor(){
        return this.dataFloor;
    }

}