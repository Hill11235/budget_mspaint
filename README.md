# **CS5001 Assignment 4**

### **Features Implemented**
All basic features outlined in the specification have been implemented. For additional features the following have been implemented:
* When the GUI is open the aspect ratio can be locked using the "L" key (not case sensitive). Pressing this toggles the aspect ratio lock.
* I have included additional shapes that can be drawn. These are triangles and a triforce shape.

### **Compiling and running programme**
In order to run the program:
* Navigate to the src directory and use `javac shapes/*.java controller/*.java view/*.java` to compile.
* Then use `java view.ModelMain` to run the programme and interact with the GUI.
* Draw directly on the panel and use the buttons, switch and "L" key to interact with the panel. The buttons outline what actions can be performed.

### **Running and compiling unit tests**
Unit tests are included to test the controller level (controller package) and model level (shapes package). There is 100% coverage on these packages. The view package classes were user tested. The unit tests are in the tests directory. These can be run by:
* If using an IDE add JUnit to the build path and run through IDE.
* Otherwise, navigate to the src directory and use `javac -cp ./tests/junit.jar:./tests/hamcrest.jar:. tests/*.java` to compile the tests.
* Use `java -cp ./tests/junit.jar:./tests/hamcrest.jar:.:. org.junit.runner.JUnitCore tests.ControlTest tests.BasicShapeTest tests.CrossTest tests.RectTest tests.TriangleTest tests.EllipseTest tests.LineTest tests.ShapeDeepCopierTest tests.TriforceTest` to run all tests that have been written.

Have fun drawing!
