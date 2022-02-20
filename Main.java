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

  
  // simple function to turn an integer into an array of the integer's digits
  public static int[] numToDigitArray(int num) {
    String[] temp = Integer.toString(num).split("");
    int[] numArray = new int[temp.length];
    for(int i = 0; i < temp.length; i++) {
      numArray[i] = Integer.parseInt(temp[i]);
    }
    return numArray;
  }

  public static boolean sameDigits(int[] digits) {
    for(int i=0; i < digits.length; i++) {
      if(digits[i] == digits[0]) {
        continue;
      }
      else {
        return false;
      }
    }
    return true;
  }

  // function to convert an integer to the corresponding letters in the map object like when typing on a nokia-style keyboard where if you press 4+4+3+3+5+5+5+7 it would become the word 'help'
  public static String generateQuery2(char[][] map) {
    Scanner in = new Scanner(System.in);
    in.useDelimiter(System.getProperty("line.separator")); // set scanner delimiter in order to stop scanning for new inputs when we hit a newline character/enter key
    
    int[][] inputNums = new int[160][];
    int[] tempInput = new int[4];
    String output = "";
    for(int i=0; in.hasNextInt(); i++)
    {
      tempInput = numToDigitArray(in.nextInt());
      inputNums[i] = tempInput;
      if(inputNums[i] != null && inputNums[i].length <= map[inputNums[i][0]].length && sameDigits(inputNums[i])) {
        output = output + map[inputNums[i][0]][inputNums[i].length-1];
      }
    }
    in.close();
    return output;
  }
  
  
  
  public static void main(String[] args) {
    boolean running = true;
    boolean asking = false;
    int option = 1;
    Scanner in = new Scanner(System.in);

    while (running) {
      switch (option) {
        case 1:
          try {
            System.out.print("Enter a sequence of digits: ");
            int digits = in.nextInt();
            // Integer[] digits = handleInput(input);
            String[] combos = generateCombos(digits);
            System.out.println("All possible combinations are as follows:\n" + String.join(" ", combos));
          }
          catch (InputMismatchException ex) {
            in.next();
            System.out.println("Error: Input must be a sequence of digits");
          }
          break;
        default:
          System.out.println("Error: Invalid option.");
      }

      asking = true;
      while (asking) {
        System.out.print(
          "\n-=== OPTION MENU ===-\n"
          + "1) Generate all possible combinations\n"
          + "2) Generate your own word\n"
          + "3) Exit\n"
          + "Select an option (integer): "
          );

        try {
          option = in.nextInt();
          System.out.println();
          asking = false;
          if (option == 3) running = false;
        }
        catch (InputMismatchException ex) {
          in.next();
          System.out.println("Error: Option must be an integer)");
        }
      }
    }

    System.out.println("Exiting the program.");
    in.close();
  }
}