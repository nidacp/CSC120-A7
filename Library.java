import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class Library extends Building {

    /*
     * 1. Make the `Library` class `extend` the `Building` class, add a `private Hashtable<String, boolean> collection` attribute, 
     * and initialize this to an empty `Hashtable<String, boolean>` inside the `Library` constructor. Don't forget to `import java.util.Hashtable`!

---
2. Write methods to update the `Hashtable` containing the `collection` every time we add/remove a title:
```
public void addTitle(String title);
public String removeTitle(String title); // return the title that we removed
```
as well as to check a book out or return it (rather than adding or removing a book from the collection, these methods will simply modify the `value` associated with the given `key` - the `title`):
```
public void checkOut(String title);
public void returnBook(String title);
```
_Hint: use the functions provided by the [`Hashtable`](https://docs.oracle.com/javase/8/docs/api/java/util/Hashtable.html) class to make this much easier! Specifically, check out `put(...)`, `remove(...)`, and `replace(...)`._

---
3. For good measure, we'll also write a couple of methods to support browsing the collection:
```
public boolean containsTitle(String title); // returns true if the title appears as a key in the Libary's collection, false otherwise
public boolean isAvailable(String title); // returns true if the title is currently available, false otherwise
public void printCollection(); // prints out the entire collection in an easy-to-read way (including checkout status)
```
_Hint: again, let `Hashtable`'s methods do some of the heavy lifting for you!_
     */

    private Hashtable<String, Boolean> collection;

    public Library() {
      super();
      this.collection = new Hashtable<>() {};
      System.out.println("You have built a library: 📖");
    }

    public Library(String name, String address, int nFloors) {
        super(name, address, nFloors);
      this.collection = new Hashtable<>() {};
      System.out.println("You have built a library: 📖");
    }

    public void addTitle(String title) {
      collection.put(title, true);
    }

    public String removeTitle(String title) {
      collection.remove(title);
      return title;
    }

    public void checkOut(String title) {
        if(!isAvailable(title)) {
            throw new RuntimeException("This book is already checked out.");
        }
        collection.replace(title, true, false);
    }

    public void returnBook(String title) {
      collection.replace(title, false, true);
    }

    public boolean containsTitle(String title) {
      return collection.containsKey(title);
    }

    public boolean isAvailable(String title) {
      return collection.get(title);
    }

    /**
     * Checks if a series of books are available at once, useful if a user only wants to start a series if all books are available.
     * @param titles an ArrayList of String titles of each book in a series.
     * @return       boolean true is returned if every book in titles is available, false if not.
     */
    public boolean isAvailable(ArrayList<String> titles) {
      for(int i=0; i<titles.size(); i++) {
        if(!collection.get(titles.get(i))) {
            return false;
        }
      }
      return true;
    }

    public void printCollection() {
      for(Map.Entry<String, Boolean> entry : collection.entrySet()) {
        System.out.println("Title: " + entry.getKey() + ". Available: " + entry.getValue());
      }
    }
  
   @Override
   /**
    * Moved user to their requested floor
    * @param floorNum int that represents which floor the user wants to go to
    */
   public void goToFloor(int floorNum) {
       super.goToFloor(floorNum);
   }


    /**
   * Shows all possible methods to call. Adds onto building's showOptions() function.
   */
    public void showOptions() {
        super.showOptions();
        System.out.print("\n + addTitle(t) \n + removeTitle(t) \n + checkOut(t) \n + returnBook(t) \n + containsTitle(t) \n + isAvailable(t)  \n + printCollection()");
    }
  
    public static void main(String[] args) {
      Library a = new Library();
      Library b = new Library("Library", "Main Street", 2);
      String book = "B";
      try {
        b.checkOut(book);
        System.out.println("Test 1 failed.");
      } catch (Exception e) {
        System.out.println("Test 1 passed.");
      }
      b.addTitle(book);
      try {
        b.checkOut(book);
        System.out.println("Test 2 passed.");
      } catch (Exception e) {
        System.out.println("Test 2 failed.");
      }
      ArrayList<String> books = new ArrayList<>();
      books.add(book);
      books.add("A");
      b.addTitle("A");
      if(b.isAvailable(book)) {
        System.out.println("Test 3 failed.");
      }
      else {
        System.out.println("Test 3 passed.");
      }
      if(!b.isAvailable("A")) {
        System.out.println("Test 4 failed.");
      }
      else {
        System.out.println("Test 4 passed.");
      }
      if(b.isAvailable(books)) {
        System.out.println("Test 5 failed.");
      }
      else {
        System.out.println("Test 5 passed.");
      }

    }
  
  }
