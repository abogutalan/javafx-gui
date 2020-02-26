#A4 ReadMe

Abdullah Ogutalan	1109732

# javafx_gui

1. src/
* contains the source files, probably in at least two packages
2. javafxlib/
* contains the platform specific javafx library files.  
* Do not commit these files to git.  
* You don't even have to commit the folder to git as long as you remember to make it when you compile
3. lib/
* contains the dnd-A4.jar file as well as the junit.jar files and checkstyle
4. res/
* the root folder for resources that the gui might need
* image files
* css files

The ant file will make build/ dist/ and doc/ directories so you don't have to create them.

#### Ant file commands
## ant
* will compile the program
## ant runme
* will compile, create a jar file, and run the program from the jar file

## ant runmeJava
* will compile and run from the .class files in the build folder

## ant archive
* compile and create executable jar and store in dist folder


### To make this work with your own package names and program
1. Copy src/gui/Launcher.java to your own hierarchy and edit the package name and the name of the class to launch
2. edit line 19 of the build.xml file to create the name of the jar file you want
3. edit line 20 of the build.xml file to be the proper package for the Launcher.java file you copied in


***********************************************
https://gitlab.socs.uoguelph.ca/examples/javafx_gui

http://tutorials.jenkov.com/javafx/textfield.html

https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/ui_controls.htm
