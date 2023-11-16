import java.util.ArrayList;

import javax.management.RuntimeErrorException;

public class House extends Building {

/* 1. Make the `House` class `extend` the `Building` class, and add the following attributes:
```
private ArrayList<String> residents; // The <String> tells Java what kind of data we plan to store IN the ArrayList
private boolean hasDiningRoom;
```
Modify the `House` **constructor** to initialize `residents` to a `new ArrayList<String>()`, as well as to set 
`hasDiningRoom` to indicate whether or not the house has a dining room. You'll have to pass this value in as a parameter 
to the constructor, and don't forget to `import java.util.ArrayList`!

---
2. Write the following accessors to retrieve the indicated values:
```
public boolean hasDiningRoom();
public int nResidents();
```

---
3. Write methods to update the `ArrayList` of `residents` every time someone moves in or out:
```
public void moveIn(String name);
public String moveOut(String name); // return the name of the person who moved out
```
as well as a boolean method that tells us whether or not a given person is a resident of the `House` 
(for security reasons, we don't want to provide direct access to the entire list of residents):
```
public boolean isResident(String person);
```
_Hint: use the functions provided by the [`ArrayList`](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html) class to make this much easier! Specifically, check out `add(...)`, `remove(...)`, and `contains(...)`._
 */



private ArrayList<String> residents; // The <String> tells Java what kind of data we plan to store IN the ArrayList
private boolean hasDiningRoom;
private boolean hasElevator;

  public House(boolean hasDiningRoom) {
    super();

    this.residents = new ArrayList<>();
    this.hasDiningRoom = hasDiningRoom;
    this.hasElevator=false;
    System.out.println("You have built a house: 🏠");
  }

  public House(boolean hasDiningRoom, boolean hasElevator, String name, String address, int nFloors) {
    super(name, address, nFloors);

    this.residents = new ArrayList<>();
    this.hasDiningRoom = hasDiningRoom;
    this.hasElevator=hasElevator;
    System.out.println("You have built a house: 🏠");
  }

   @Override
   /**
    * Moved user to their requested floor, but requred an elevator for multi-floor movement
    * @param floorNum          int that represents which floor the user wants to go to
    * @throws RuntimeException throws exception if user tries to move up/down multiple floors without an elevator
    */
  public void goToFloor(int floorNum) {
      if(!hasElevator && Math.abs(floorNum-this.activeFloor)>1) {
        throw new RuntimeException("Can only go up multiple floors with an elevator.");
      }
      super.goToFloor(floorNum);
  }

  public boolean hasDiningRoom() {
    return this.hasDiningRoom;
  }

  public int nResidents() {
    return this.residents.size();
  }

  public boolean hasElevator() {
    return hasElevator;
  }

  public boolean isResident(String person) {
    return residents.contains(person);
  }

  /**
   * Checks if an all elements in an arraylist are also in arraylist residents. Makes it simpler to check if groups of roommates or a family are residents of the house.
   * @param family
   * @return
   */
  public boolean isResident(ArrayList<String> family) {
    for(int i=0; i<family.size(); i++) {
        if(!isResident(family.get(i))) {
            return false;
        }
    }
    return true;
  }

  public void moveIn(String name) {
    residents.add(name);
  }

  public String moveOut(String name) {
    residents.remove(name);
    return name;
  }

  /**
   * Shows all possible methods to call. Adds onto building's showOptions() function.
   */

  public void showOptions() {
    super.showOptions();
    System.out.print("\n + hasDiningRoom() \n + nResidents() \n + isResident(n) \n + moveIn(n) \n + moveOut(n)");
}

  public static void main(String[] args) {
    House a = new House(true, false, "A", "A Street", 3);
    House b = new House(false, true, "B", "B Street", 3);
    try {
        a.goToFloor(1);
        System.out.println("Test 1 failed.");
    } catch (Exception e) {
        System.out.println("Test 1 passed.");
    }
    try {
        a.enter();
        System.out.println("Test 2 passed.");
    } catch (Exception e) {
        System.out.println("Test 2 failed.");
    }
    try {
        a.goToFloor(3);
        System.out.println("Test 3 failed.");
    } catch (Exception e) {
        System.out.println("Test 3 passed.");
    }

    try {
        b.goToFloor(1);
        System.out.println("Test 4 failed.");
    } catch (Exception e) {
        System.out.println("Test 4 passed.");
    }
    try {
        b.enter();
        System.out.println("Test 5 passed.");
    } catch (Exception e) {
        System.out.println("Test 5 failed.");
    }
    try {
        b.goToFloor(3);
        System.out.println("Test 6 passed.");
    } catch (Exception e) {
        System.out.println("Test 6 failed.");
    }

    


  }

}
