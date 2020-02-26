package gui;


import aogutala.*;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

import java.io.Serializable;
import java.util.ArrayList;

public class Controller implements Serializable {
    private GuiDemo myGui;
    private Floor myFloor; //private Battle hydraBattle;
    private ArrayList<Chamber> chambers;
    private ArrayList<Passage> passages;


    public Controller(GuiDemo theGui){
        myGui = theGui;
        myFloor = new Floor();//hydraBattle = new Battle();
        chambers = myFloor.createChambers(5);
        passages = myFloor.createPassages(4);
        myFloor.connectDoorsToChambers();

    }


    private String getNameList(){
        String nameList = new String();
        for(Chamber c: chambers){
            nameList = nameList  + c.toString()+ "\n";
        }
        return nameList;
    }

    public void updateData(String save_or_load){
        if(save_or_load.equals("save")){
            SaveData data = new SaveData();
            data.setFloor(this.myFloor);
            try{
                ResourceManager.save(data,"myStage.save");
            }
            catch (Exception ex) {
                System.out.println("Couldn't save: " + ex.getMessage());
            }
        }
        else if(save_or_load.equals("load")){
            try{
                SaveData data = (SaveData) ResourceManager.load("myStage.save");

                if(data.getFloor() != null){
                    //this.myFloor = data.getFloor(); 
                    this.chambers = myFloor.getChamberOfFloor();
                    for(int i = 0; i < 5; i++){ // setting doorList of 5 Chambers of Floor to Chambers of gui 
                        this.chambers.get(i).setDoors(myFloor.getChamberOfFloor().get(i).getDoors());    
                    }                    
                    this.passages = myFloor.getPassageOfFloor(); 
                    System.out.println("Data loaded!");
                }                       
            }          
            catch (Exception exp){
                System.out.println("Couldn't load save data: " + exp.getMessage());

            }

        }
    }

    public String getDoorDescription(String selectedVal){
        String doorDescription = new String();
        //ComboBox myChoiceBox = myGui.getChoiceBox();
        ArrayList<Door> doors = myGui.getDoorListOfGUI();
        if( (selectedVal != null) && (!selectedVal.equals("List of Doors"))){
                int index = Integer.parseInt(selectedVal.substring(selectedVal.length()-1, selectedVal.length()));
                doorDescription = doorDescription + "Door "+ (index)+ " selected\n" + doors.get(index-1).getDescription()+ "\n";
          }
            
        
        
        
        return doorDescription;
    }

    public void reactToButton(){
        System.out.println("Thanks for clicking!");
    }

    public String getNewDescription(){
        //return "this would normally be a description pulled from the model of the Dungeon level.";
        return getNameList();
    }

    public ArrayList<Chamber> getListOfChambers(){
        return this.chambers;
    }
    
    public ArrayList<Passage> getListOfPassages(){
        return this.passages;
    }

}