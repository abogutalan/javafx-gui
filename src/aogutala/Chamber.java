package aogutala;

import dnd.die.D10;
import dnd.die.D20;
import dnd.exceptions.UnusualShapeException;
import dnd.models.ChamberContents;
import dnd.models.ChamberShape;
import aogutala.Chamber;
import dnd.models.DnDElement;
import dnd.models.Exit;
import dnd.models.Monster;
import dnd.models.Stairs;
import dnd.models.Treasure;
import dnd.models.Trap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class Chamber extends Space implements Serializable {

	private ChamberContents myContents;
	private ChamberShape myShape;

	private String descriptionForMyMain;
	private String description_added;
	private ArrayList<Door> doorLists;
	private ArrayList<DnDElement> dnDElementsList;
	private HashMap<Trap,Treasure> treasureTrapMap;

	private ArrayList<Monster> monstersList;
	private ArrayList<Treasure> treasureList;
	private ArrayList<Exit> exitLists;
	private Monster monster;
	private Treasure treasure;
	private	int exitNumOfMyShape;
	private boolean connectedVar;
	private String chamberShapeString;


	/******************************
	 Required Methods for that we will test during grading
	*******************************/
	/* note:  Some of these methods would normally be protected or private, but because we
	don't want to dictate how you set up your packages we need them to be public
	for the purposes of running an automated test suite (junit) on your code.  */


public Chamber()  {
	init();
	this.myShape = ChamberShape.selectChamberShape(new D20().roll());
	this.myContents = new ChamberContents();


}

public Chamber(ChamberShape theShape, ChamberContents theContents) {
	init();
	this.myShape = theShape;
	this.myContents = theContents;


}

private void init(){

	this.monstersList = new ArrayList<Monster>();
	this.treasureList = new ArrayList<Treasure>();
	this.doorLists = new ArrayList<Door>();
	this.exitLists = new ArrayList<Exit>();

	this.descriptionForMyMain = "";
	this.description_added = "";
	this.exitNumOfMyShape = 0;
	this.chamberShapeString = "";
	this.connectedVar = false;

}



public void setShape(ChamberShape theShape){

	// this.exitNumOfMyShape = theShape.getNumExits();	
	// if(exitNumOfMyShape == 0) exitNumOfMyShape++;
	// myShape.setShape(exitNumOfMyShape);
	this.chamberShapeString = theShape.getShape();

}


public ArrayList<Door> getDoors(){
	return this.doorLists;
}
public void setDoors(ArrayList<Door> newDoors){
	this.doorLists = newDoors;
}

public void addDoors(){
	// adding doors to do
	//descriptionForMyMain+= "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n";
	int numberOfExits = myShape.getNumExits();
	if(numberOfExits == 0){
		numberOfExits++;
	}
	int index = exitLists.size();
	for(int i=0 ; i < numberOfExits ; i++){	
		exitLists.add(new Exit());
		doorLists.add(new Door(exitLists.get(i)));
		 descriptionForMyMain += ("The exit location of door " + (index+1) + " : " + doorLists.get(i).getExit().getLocation() + "\n"
		 				+ "The exit direction of door " + (index+1) + " : " + doorLists.get(i).getExit().getDirection() + "\n");
		index++;

	}
	 descriptionForMyMain += ("Exit nums of ExiltList : " +exitLists.size() + "\n");
	 descriptionForMyMain += ("Door nums: "+doorLists.size() +"\n");
	 descriptionForMyMain += "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n";


}



public void addMonster(Monster theMonster){
	Monster m = new Monster();
	m = theMonster;
	this.monstersList.add(m);
	descriptionForMyMain += "Added Monster to Chamber: " + theMonster.getDescription()+ "\n";
}
public void removeMonster(Monster theMonster){
	if(monstersList.contains(theMonster)){
		monstersList.remove(theMonster);
		descriptionForMyMain += "Removed Monster from Chamber: " + theMonster.getDescription()+ "\n";
	}
	else System.out.println("The Monster you tried to remove couldn't be removed!");
	

}

public ArrayList<Monster> getMonsters(){

	return this.monstersList;
}


public void addTreasure(Treasure theTreasure){
	this.treasureList.add(theTreasure);
}

public ArrayList<Treasure> getTreasureList(){

	return this.treasureList;
}

public void setConnected(boolean connectedVar){
	this.connectedVar = true;
}

public boolean isConnected(){
	return this.connectedVar;
}


@Override
public String getDescription(){						// to do
	description_added = descriptionAdded();
	return this.descriptionForMyMain + description_added;
}

@Override
public void setDoor(Door newDoor){										

    newDoor.setSpaces(this, null);

}




/***********
You can write your own methods too, you aren't limited to the required ones
*************/


public String descriptionAdded(){
	this.description_added= "";

	D10 d10 = new D10();
	D20 d20 = new D20();
	this.myContents.chooseContents(d20.roll());

	description_added += "Content of the Chamber: "+this.myContents.getDescription() + "\n";
	if(this.myContents.getDescription().equals("monster and treasure")){

		Monster m = new Monster();
		m.setType(d10.roll() * 10);
		this.addMonster(m);
		Treasure t = new Treasure();
		t.chooseTreasure(d10.roll() * 10);
        this.addTreasure(t);

        description_added += "Monster  : " + monstersList.get(0).getDescription() + "\n" + "Treasure : " + this.getTreasureList().get(0).getDescription() + "\n";

	}
	else if(this.myContents.getDescription().equals("monster only")){
		Monster m = new Monster();
		m.setType(d10.roll() * 10);
		this.addMonster(m);
        description_added += "Monster : " + monstersList.get(0).getDescription() + "\n";

	}
	else if(this.myContents.getDescription().equals("treasure")){
		Treasure t = new Treasure();
		t.chooseTreasure(d10.roll() * 10);
        this.addTreasure(t);
        description_added += "Treasure : " + this.getTreasureList().get(0).getDescription() + "\n";

	}
	else if(this.myContents.getDescription().equals("stairs")){
		Stairs stairs = new Stairs();
		stairs.setType(d20.roll());
		description_added += "Stairs inside the chamber: "+ stairs.getDescription() + "\n";
	}
	else if(this.myContents.getDescription().equals("trap")){
		Trap trap = new Trap();
		trap.chooseTrap(d20.roll());
		description_added += "Trap description of chamber: " + trap.getDescription() + "\n";
	}
	else{
		description_added += "No monster, treasure, stairs or trap \n";
	}

	description_added += "How many doors? " + doorLists.size() + "\n";
	description_added += "How many monsters? " + monstersList.size() + "\n";
	description_added += "How many treasures? " + treasureList.size() + "\n";
	myGetShape();
	myGetArea();

	return this.description_added;
}
private void myGetShape(){
	this.setShape(myShape);
	description_added += ("The shape of the Chamber: " + chamberShapeString +"\n");
}
private void myGetArea(){
	try{
		int length = myShape.getLength();
		int width = myShape.getWidth();
		description_added += ("The length: "+length + " and The width: "+width+ "\n");
	} catch (UnusualShapeException e) {
		int area= myShape.getArea();
		description_added += ("The area of the Chamber: "+ area+ "\n");
	}

}


}
