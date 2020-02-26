package aogutala;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import gui.ResourceManager;
import gui.SaveData;

public class Floor implements Serializable {


        //Hydra = Chambers
//Head = Door
//Battle = level	
private ArrayList<Chamber> myChamberList;
//Door to Chamber
private HashMap<Door,Chamber> myDoorToChamber;
//Door to Door
private HashMap<Door, Door> myDoorToDoor;
//final lineup
private HashMap<Door, ArrayList<Chamber>> myDoorToChamberList;
private boolean debug;
private ArrayList<Chamber> myPossibleTargets;
private ArrayList<Passage> myPassageList;



    public Floor() {
        this.myChamberList = new ArrayList<>();
        this.myDoorToChamber = new HashMap<>();
        this.myDoorToDoor = new HashMap<>();
        this.myDoorToChamberList = new HashMap<>();        
        this.debug = false;
        this.myPossibleTargets = new ArrayList<>();
        this.myPassageList = new ArrayList<>();


    }

    public void setDebug(boolean flag) {
        this.debug = flag;
    }

    public ArrayList<Chamber> getChamberOfFloor(){
        return this.myChamberList;
    }
    public ArrayList<Passage> getPassageOfFloor(){
        return this.myPassageList;
    }

    public ArrayList<Passage> createPassages(int howManyPassages)
    {
        
            for(int i = 0; i < howManyPassages; i++)
            {

                myPassageList.add(new Passage());
                myPassageList.get(i).addPassageSection(new PassageSection());
            } 
        
        
        return myPassageList;
    }

    public ArrayList<Chamber> createChambers(int howManyChambers)
    {

            for(int i = 0; i < howManyChambers; i++)
            {

                myChamberList.add(new Chamber());
                myChamberList.get(i).addDoors();
            } 
        
        
        
        return myChamberList;     
    }

    public void addDoorToChamber()
    {
        //private HashMap<Door,Chamber> myDoorToChamber;
        for(int i = 0; i < 5; i++)
        {
            myChamberList.get(i).addDoors();

        }
    }   

    public ArrayList<Chamber> getPossibleTargets(int current)
    // get the possible targets according to given source Chamber
    {   
        for(int i = 0; i < 5; i++)
        {
            if(i != current)
            this.myPossibleTargets.add(myChamberList.get(i));
        }

        return this.myPossibleTargets;
    }

    public void removePossibleTargets() {
        myPossibleTargets.removeAll(myPossibleTargets);
    }

    public void setChamberList(ArrayList<Chamber> chambers){
        this.myChamberList = chambers;
    }
    

    public HashMap<Door,Chamber> getMyDoorToChamber(){
        return this.myDoorToChamber;
    }

    public void connectDoorsToChambers()
    // make a connection map that ensures that each door has at least one target chamber
    {   
        //System.out.println("CONNECT DOORS TO CHAMBER");
        for(int i = 0; i < 5; i++)
        {
            myPossibleTargets = getPossibleTargets(i);
            int doorsNumber = myChamberList.get(i).getDoors().size();
            //System.out.print("The source chamber is chamber-"+(i+1)+" and has -"+doorsNumber+"- doors.\n");

            int j = 0;
            while( j < doorsNumber )
            {
              int targetsIndex = j;
              targetsIndex = targetsIndex % 4; // if the doors numbers are more than 4
              while((myPossibleTargets.get(targetsIndex).isConnected() == true) && (targetsIndex < 3))
                  // Connect door to chamber whether the chamber isn't connected to any door OR all the target chambers are connected to a door
                  // I don't also want to targetsIndex more than 4
              {
                  targetsIndex++;
              }
                  myDoorToChamber.put(myChamberList.get(i).getDoors().get(j), myPossibleTargets.get(targetsIndex));
                  myPossibleTargets.get(targetsIndex).setConnected(true);
                  myChamberList.get(i).getDoors().get(j).setDecription("The "+ (j + 1) + ". door of the source chamber is connected to target Chamber -"+(targetsIndex + 1) + "-\n");
                  
              j++;
            }
            removePossibleTargets();
        }
    }

    public void connectDoorsToChamberDoors() {
        // Connecting every unconnected door to a door.
        for(int i = 0; i < 5; i++)
        {
            int j = 0, index_j = 0;
            int k = 0 , index_k = 0;
            int doorsNumber1 = 0;   // doors number of source chamber
            int doorsNumber2 = 0;   // doors number of target chamber
             doorsNumber1 = myChamberList.get(i).getDoors().size();

            int doorIndexOfSourceChamber = 0;   // in order to loop all doors of source chamber
            while(doorIndexOfSourceChamber < doorsNumber1) { //loop all doors in source chamber
            if(myChamberList.get(i).getDoors().get(doorIndexOfSourceChamber) != null)
           doorsNumber2 = myDoorToChamber.get(myChamberList.get(i).getDoors().get(doorIndexOfSourceChamber) ).getDoors().size(); // doors amount of a target chamber that is connected by the source chamber's door



             while( (j < doorsNumber1) || (k < doorsNumber2) )   // As long as there's an unconnected door either for Source chamber or Target Chamber
             {
                index_k = 0;  // in order to find unconnected door in a chamber
                if(doorsNumber1 <= doorsNumber2)
                {
                    while((myDoorToChamber.get(myChamberList.get(i).getDoors().get(doorIndexOfSourceChamber) ).getDoors().get(index_k).isConnected() == true) &&  (index_k < doorsNumber2-1))
                    {
                        index_k++;
                    }
                     myDoorToDoor.put(myChamberList.get(i).getDoors().get(doorIndexOfSourceChamber), myDoorToChamber.get(myChamberList.get(i).getDoors().get(doorIndexOfSourceChamber) ).getDoors().get(index_k));


                } 
                else if(doorsNumber1 > doorsNumber2) {

                        index_j = 0;  // in order to find unconnected door in a chamber

                        while((myChamberList.get(i).getDoors().get(j).isConnected() == true) && (index_j < doorsNumber1-1))
                        {
                            index_j++;
                        }
                     myDoorToDoor.put(myChamberList.get(i).getDoors().get(index_j), myDoorToChamber.get(myChamberList.get(i).getDoors().get(index_j) ).getDoors().get(index_j));
                    }
                  k++;  
                 j++;
             }
             doorIndexOfSourceChamber++;
            }


        }
    }









}