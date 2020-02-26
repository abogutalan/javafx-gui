package aogutala;

import dnd.die.D20;
import dnd.models.Monster;
import dnd.models.Treasure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
/*
A passage begins at a door and ends at a door.  It may have many other doors along 
the way

You will need to keep track of which door is the "beginning" of the passage 
so that you know how to 
*/

public class Passage extends Space implements Serializable {
	//these instance variables are suggestions only
	//you can change them if you wish.

	private ArrayList<PassageSection> thePassageSections;
	private HashMap<PassageSection,Door> doorMap;

	private String description;
	private String descriptionToBeAdded;
	private HashMap<PassageSection ,Monster> monsterMap;
	private boolean doors;
	private static int current;

	/******************************
	 Required Methods for that we will test during grading
	*******************************/
	/* note:  Some of these methods would normally be protected or private, but because we 
	don't want to dictate how you set up your packages we need them to be public 
	for the purposes of running an automated test suite (junit) on your code.  */

public Passage() {
	this.thePassageSections = new ArrayList<PassageSection>();
	this.doorMap = new HashMap<PassageSection,Door>();
	this.monsterMap = new HashMap<PassageSection,Monster>();
	this.doors = false;
	this.description = "";
	this.descriptionToBeAdded = "";
	this.current = 0;
}

public ArrayList getDoors() {
//gets all of the doors in the entire passage			ABO: return an ArrayList<Door>

	ArrayList<Door> dArrayList = new ArrayList<>();
	

	if(doorMap.get(thePassageSections.get(0)) == null) return null;

	for(int i = 0; i < doorMap.size(); i++) {
		dArrayList.add(doorMap.get(thePassageSections.get(i)));
	}

	return dArrayList;
}

public Door getDoor(int i) {
	//returns the door in section 'i'. If there is no door, returns null
	if(thePassageSections.get(i).getDoor() != null) {
		return doorMap.get(thePassageSections.get(i));
	}
	return null;
}

public void addMonster(Monster theMonster, int i) {
	// adds a monster to section 'i' of the passage
	monsterMap.put(thePassageSections.get(i), theMonster);
	thePassageSections.get(i).addMonsterPS(theMonster);
	this.description += "Monster added to Passage: " + theMonster.getDescription() + "\n";

}
public void removeMonster(Monster theMonster, int i){
	this.description += "Monster removed from Passage: " + theMonster.getDescription() + "\n";
	monsterMap.remove(thePassageSections.get(i));
	thePassageSections.get(i).removeMonsterPS(theMonster);
}

public Monster getMonster(int i) {
	//returns Monster door in section 'i'. If there is no Monster, returns null
	return monsterMap.get(thePassageSections.get(i));
}



public void addPassageSection(PassageSection toAdd) {
	//adds the passage section to the passageway

	thePassageSections.add(toAdd);
	doorMap.put(toAdd, new Door());
}
public ArrayList<PassageSection> getPassageSectionList() {
	return this.thePassageSections;
}


@Override
public void setDoor(Door newDoor) {
	//should add a door connection to the current Passage Section
	if(!thePassageSections.isEmpty()) {
		this.doorMap.put
		(thePassageSections.get(thePassageSections.size()-1), newDoor);
	}



}

@Override
public String getDescription() {
 descriptionToBeAdded = setDescription();											//TO DOOOOOOOOOOOOOOOOOO
 return this.description + descriptionToBeAdded;
}
/***********
You can write your own methods too, you aren't limited to the required ones
*************/
public String setDescription(){
	D20 d20 = new D20();
	int width = d20.roll();
	this.descriptionToBeAdded = "";
	if(width  > 18){
		this.descriptionToBeAdded += "Special Passage: " + d20.roll() ;
	}else{
	this.descriptionToBeAdded += "The passage width: " + width + "\n";
	}
	return this.descriptionToBeAdded;
}

}