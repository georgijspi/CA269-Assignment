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

  // recursive function to generate all character combinations for digits pressed
  private static String[] generateCombos(int digits) {
    // base case
    if (digits == 0) return new String[] {""};

    // split digits into head (last digit) and tail (remaining digits)
    int head = digits % 10;
    int tail = digits / 10;

    // recurse on the tail digits
    String[] combos = generateCombos(tail);
    // get corresponding characters for head digit
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

  // function to generate a word from a sequence of repeated numbers
  public static String generateWord(String nums) {
    // generated output word
    String word = "";
    // split number sequence into numbers
    String[] splitNums = nums.split(" ");

    // for each number
    for (String num : splitNums) {
      // split the number into digits
      String[] splitNum = num.split("");
      // initialize digits array of same length as number
      int[] digits = new int[splitNum.length];
      
      // for each digit in the number
      for(int i = 0; i < splitNum.length; i++) {
        try {
          // parse digit as correct type, then insert into digit array
          digits[i] = Integer.parseInt(splitNum[i]);
          // validate the digit is the same as first digit
          // (if all digits are equal, number is a repeated number)
          if (digits[i] != digits[0]) {
            throw new NumberFormatException();
          }
        }
        // throw error if digit could not be parsed,
        // or if it is different than the other digits
        catch (NumberFormatException ex) {
          throw new InputMismatchException();
        }
      }

      // get corresponding chars for digit from numpad
      char[] chars = numpad[digits[0]];
      // append correct char to output word
      word += chars[(digits.length - 1) % chars.length];
    }

    // return generated output word
    return word;
  }
  
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    // program loop states
    boolean running = true;
    boolean asking = false;
    // selected option
    int option = 1;

    // while the program is running
    while (running) {
      // execute the selected option function
      switch (option) {
        case 1:
          try {
            System.out.print("Enter a sequence of digits: ");
            int digits = in.nextInt();
            String[] combos = generateCombos(digits);
            System.out.println("All possible combinations are as follows:\n" + String.join(" ", combos));
          }
          catch (InputMismatchException ex) {
            System.out.println("Error: Input must be a sequence of digits.");
          }
          in.nextLine();
          break;
        case 2:
          try {
            System.out.println("(2: abc, 3: def, 4: ghi, 5: jkl, 6: mno, 7: pqrs, 8: tuv, 9: wxyz)");
            System.out.print("Enter a sequence of repeated numbers, delimited by spaces: ");
            String repeatNums = in.nextLine();
            String word = generateWord(repeatNums);
            System.out.println("Generated word: " + word);
          }
          catch (InputMismatchException ex) {
            System.out.println("Error: Input must be a sequence of repeated numbers.");
          }
          break;
        // if there is no function associated with the selected option
        default:
          System.out.println("Error: Invalid option.");
      }

      // display options menu after function executes
      asking = true;
      while (asking) {
        // list all options and prompt user to select option
        System.out.print(
          "\n-=== OPTIONS MENU ===-\n"
          + "1) Generate all possible combinations\n"
          + "2) Generate your own word\n"
          + "3) Exit\n"
          + "Select an option (integer): "
          );

        try {
          // set selected option as next provided integer
          option = in.nextInt();
          // once option is selected, stop displaying options menu
          System.out.println();
          asking = false;
          // if user chose to exit, stop the program
          if (option == 3) running = false;
        }
        // if selected option is not an integer
        catch (InputMismatchException ex) {
          System.out.println("Error: Option must be an integer.");
        }
        in.nextLine();
      }
    }

    // stop the program when the user selects exit
    System.out.println("Exiting the program.");
    // close scanner to prevent resource leaks
    in.close();
  }
}