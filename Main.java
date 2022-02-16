import java.util.*;

public class Main {
  private static Character[][] numpad = {
    {'\0'},
    {'\0'},
    {'a', 'b', 'c'},
    {'d', 'e', 'f'},
    {'g', 'h', 'i'},
    {'j', 'k', 'l'},
    {'m', 'n', 'o'},
    {'p', 'q', 'r', 's'},
    {'t', 'u', 'v'},
    {'w', 'x', 'y', 'z'}
  };

  private static String[] calculateCombos(int digits) {
    if (digits == 0) return new String[] {""};

    int lastDigit = digits % 10;
    int lessDigits = digits / 10;

    String[] combos = calculateCombos(lessDigits);
    Character[] chars = numpad[lastDigit];

    ArrayList<String> newCombos = new ArrayList<String>();

    for (String combo : combos) {
      for (int i = 0, len = chars.length; i < len; i++) {
        newCombos.add(combo + chars[i]);
      }
    }

    return newCombos.toArray(new String[0]);
  }
  
  public static void main(String[] args) {
    boolean running = true;
    boolean asking = false;
    Scanner in = new Scanner(System.in);

    while (running) {
      System.out.print("Enter a sequence of digits: ");
      
      try {
        int digits = in.nextInt();
        // Integer[] digits = handleInput(input);
        String[] combos = calculateCombos(digits);
        System.out.println("All possible combinations are as follows:\n" + String.join(" ", combos));
      }
      catch (InputMismatchException ex) {
        in.next();
        System.out.println("Error: " + ex.toString() + " (Input must be a sequence of digits)");
      }
      finally {
        asking = true;
        while (asking) {
          System.out.print("Do you want to enter another digit sequence? (yes|no) ");
          String ans = in.next();
          if (ans.equalsIgnoreCase("no")) {
            asking = running = false;
          }
          else if (ans.equalsIgnoreCase("yes")) {
            asking = false;
          }
          else {
            System.out.println("Error: Invalid response. (yes|no)");
          }
        }
      }
    }

    System.out.println("Exiting the program.");
    in.close();
  }
}