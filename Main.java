/* -=== Group Details ===-
  Jed Hazaymeh - 20449642
  Rishab Sidhu - 20309616
  Georgijs Pitkevics - 19355266
*/

import java.util.*;

public class Main {
  private static char[][] numpad = {
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

  // recursive solution to generate all character combinations for digits pressed
  private static String[] generateCombos(int digits) {
    // base case
    if (digits == 0) return new String[] {""};

    // split digits into head (last digit) and tail (remaining digits)
    int head = digits % 10;
    int tail = digits / 10;

    // recurse on the tail digits
    String[] combos = generateCombos(tail);
    // get all possible characters for head digit
    char[] chars = numpad[head];

    // declare new combos (unfinished combos with new characters appended)
    ArrayList<String> newCombos = new ArrayList<String>();

    // for each unfinished combo
    for (String combo : combos) {
      // for each possible new character
      for (char ch : chars) {
        // append new character to unfinished combo
        newCombos.add(combo + ch);
      }
    }

    // return new combos array
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
        String[] combos = generateCombos(digits);
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