import java.util.Scanner;

public class Main
{
  //recursive function to get the all the letter combinations from the digits provided
  public void r_WordPad(String[] chars, String result, String num, int n, int m, int i, int j)
  {
	if (i == m) {
	  //if base case, add the letter combinations and a space for the other letter combinations and print all the combinations at once
	  System.out.print(result + " ");
	  return;
	}

	int x = num.charAt(i) - '0'; 

	if (x < n && chars[x].length() > 0) {  
	  //if x is less than 9 and its not 0 or 1, for each letter in that number keypad, call the function again to get all the letters
	  for (int y = 0; y < chars[x].length(); ++y) {
		r_WordPad(chars, result + chars[x].charAt(y), num, n, m, i + 1, j + 1);
	  }
	}
	else {  
	  //else add 1 to i for the base case if the number is 1 or 0 as it's an empty string
	  r_WordPad(chars, result, num, n, m, i + 1, j);
	}
  }

  //function to find the numbers to press on a phone pad to get the word provided
  public void NumPad(String chars[], String letters)
  {
	//if the string does not contain alphabets print an error message
    for (int k=0; k < letters.length(); k++) {
      if (letters.charAt(k) < 'a' || letters.charAt(k) > 'z') {
        System.out.print("Sorry, the string must be lowercase and in alphabets");
        break;
      }

      int i = 2;
      while (i < 10) {
        if (letters.charAt(k) == chars[i].charAt(0)) {
          System.out.print(i + " ");
          i = 10;
        }
        else if (letters.charAt(k) < chars[i+1].charAt(0)) {
          int j = 0;
		  while (letters.charAt(k) != chars[i].charAt(j)) {
            System.out.print(i);
			j++;
          }
		  System.out.print(i + " ");
          i = 10;
        }
        else {
          i++;
        }
	  }
    }
  }

  public static void main(String[] args)
  {
	boolean run = true;

	//keep running unless the user answers no
	while(run) {
	  Main task = new Main();
	  String[] chars = {  
		"" , "" , "abc" , "def" , "ghi" , "jkl" , "mno" , "pqrs" , "tuv" , "wxyz"};
	  int n = chars.length; 

	  Scanner in = new Scanner(System.in);
	  System.out.println("Enter\n'Digit'\nthen a sequence of Digits to find all the word combinations possible\nor\n'String'\nthen a string of Letters (lowercase )to find the numbers you must press to get the word)");
	  String option = in.next();

	  if (option.equalsIgnoreCase("Digit")) {
        String num = in.next(); 
		int m = num.length();
	    if (m > 0) {         
	      String result = "";
	      task.r_WordPad(chars, result, num, n, m, 0, 0);
		  System.out.println();
		}
	  }
	  else if (option.equalsIgnoreCase("String")) {
        String letters = in.next();
		task.NumPad(chars, letters);
		System.out.println();
	  }
	  else {
		System.out.println("Sorry, the option must be 'Digit' or 'String'");
		break;
	  }

      System.out.print("Try Again? 'yes' or 'no' ");
      String ans = in.next();
	  
      if (ans.equalsIgnoreCase("no")) {
        run = false;
      }
      else if (ans.equalsIgnoreCase("yes")) {
        continue;
      }
      else {
        System.out.println("Sorry, the option must be 'yes' or 'no'");
      }
	}
  }
}