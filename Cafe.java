public class Cafe extends Building{

    private int nCoffeeOunces; // The number of ounces of coffee remaining in inventory
    private int nSugarPackets; // The number of sugar packets remaining in inventory
    private int nCreams; // The number of "splashes" of cream remaining in inventory
    private int nCups; // The number of cups remaining in inventory
/*
```

---
1. Make the `Cafe` class `extend` the `Building` class, add the attributes listed above, and modify the `Cafe` constructor 
to set the starting values of each of the stocked items (coffee, sugar, cream, and cups).

---
2. Write a method to decrease the remaining inventory when the `Cafe` sells a cup of coffee:
```
public void sellCoffee(int size, int nSugarPackets, int nCreams);
```
Each time this method is called, the inventory should decrease in each category according to the given parameters, e.g. calling `myCafe.sellCoffee(12, 2, 3);`
should decrease the `myCafe` object's `nCoffeeOunces` by 12, `nSugarPackets` by 2, and `nCreams` by 3 (and of course, `nCups` by 1).

---
3. And of course, a `Cafe` can't sell what it doesn't have in stock, so let's also write a method to restock when necessary:
```
private void restock(int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups); 
```
This method will be `private` (since we don't want some random person forcefully restocking the shelves!) - we'll call it from **inside** the `sellCoffee(...)` method, in the event that we don't have enough ingredients in stock to make the requested drink.
     */

    public Cafe() {
        super();
        this.nCoffeeOunces=10;
        this.nSugarPackets=10;
        this.nCreams=10;
        this.nCups=10;
        System.out.println("You have built a cafe: ☕");
    }
    
    public Cafe(int nFloors, String address, String name) {
        super(name,address, nFloors);
        this.nCoffeeOunces=10;
        this.nSugarPackets=10;
        this.nCreams=10;
        this.nCups=10;
        System.out.println("You have built a cafe: ☕");
    }

    public Cafe(int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups) {
        super();
        this.nCoffeeOunces=nCoffeeOunces;
        this.nSugarPackets=nSugarPackets;
        this.nCreams=nCreams;
        this.nCups=nCups;
        System.out.println("You have built a cafe: ☕");
    }

    public void sellCoffee(int size, int nSugarPackets, int nCreams) {
        if(this.nCoffeeOunces<size || this.nSugarPackets<nSugarPackets || this.nCreams<nCreams || nCups<1) {
            restock(size, nSugarPackets, nCreams, 5);
        }
        this.nCoffeeOunces=-size;
        this.nSugarPackets=-nSugarPackets;
        this.nCreams=-nCreams;
        nCups--;
    }



    private void restock(int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups) {
        this.nCoffeeOunces+=nCoffeeOunces;
        this.nSugarPackets+=nSugarPackets;
        this.nCreams+=nCreams;
        this.nCups+=nCups;
    }

    /**
   * Shows all possible methods to call. Adds onto building's showOptions() function.
   */

    public void showOptions() {
        super.showOptions();
        System.out.print("\n + sellCoffee(size, nSug, nCream)\n + restock(nCoff, nSug, nCream, nCups)");
    }


    @Override
    /**
     * @param floorNum          int that represents which floor the user wants to go to
     * @throws runTimeException Error if the user isn't in the Cafe, is already at the floor they're requesting, or trying to get off the 1st floor
     */
    public void goToFloor(int floorNum) {
        if(this.activeFloor==-1) {
            throw new RuntimeException("You haven't entered this Cafe yet.");
        }
        if(floorNum==1) {
            throw new RuntimeException("You are already on the first floor.");
        }
        throw new RuntimeException("You can only access one floor of this Cafe.");
    }
    
    public static void main(String[] args) {
        Cafe a = new Cafe(3, "A Street", "A");
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

    }
    
}
