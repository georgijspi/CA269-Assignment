import java.util.Scanner;

public class KeyPad
{
	//recursive function to get the all the letter combinations from the digits provided
	public void r_WordPad(String[] chars, String result, String num, int n, int m, int i, int j)
	{
		if (i == m) {
			//if i is the position of the last digit, the function has reached its base case so it prints out all recursive results,
			//add the letter combinations and a space for the other letter combinations and print all the combinations at once
			System.out.print(result + " ");
			return;
		}

		int x = num.charAt(i) - '0';                           //get the integer of the i'th position in the string of digits
		if (x < n && chars[x].length() > 0) {                  //if x is less than 9 and its not 0 or 1, for each letter in that number keypad, call the function again to get all the letters
			for (int y = 0; y < chars[x].length(); ++y) {      //call the recursive function for the amount of letters in the keypad for that number
				r_WordPad(chars, result + chars[x].charAt(y), num, n, m, i + 1, j + 1);
			}
		}

		else {                                                 //else add 1 to i for the base case if the number is 1 or 0 as it's an empty string
			r_WordPad(chars, result, num, n, m, i + 1, j);
		}
	}

	public void WordPad(String[] chars, String num, int n)     //function to call the recursive WordPad function and create a few variables
	{
		int m = num.length();                                  //m is the number of digits in that string, ie 3 for 156 and 4 for 8364
		
		if (m > 0) {                                           //if there are 1 or more digits in the string, create a result string to add to and call the recursive wordpad function to find all the letter combinations
			String result = "";                                //this is the result that will be printed out, it's set now and will be added to later on by the recursive WordPad function
			r_WordPad(chars, result, num, n, m, 0, 0);         //call the recursive WordPad function
		}
	}

	public static void main(String[] args)
	{
		KeyPad task = new KeyPad();                            //Assign the class
		String[] chars = {                                     //set the letters for the corresponding digits in accordance to their position in the list
			"" , "" , "abc" , "def" , "ghi" , "jkl" , "mno" , "pqrs" , "tuv" , "wxyz"};
		int n = chars.length;                                  //n is equal to 9, the max number that is on the keypad

        //read in digits as a string
		Scanner in = new Scanner(System.in);
        String num = in.next(); 

		task.WordPad(chars, num, n);                           //call the WordPad function
		System.out.println();                                  //print a new line at the end for aesthetic purposes
	}
}