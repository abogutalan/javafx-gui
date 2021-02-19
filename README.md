
# javafx_gui

1. src/
* contains the source files, probably in at least two packages
2. javafxlib/
* contains the platform specific javafx library files.  
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

