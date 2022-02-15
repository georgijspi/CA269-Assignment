import java.util.Scanner;

public class KeyPad
{
	//recursive function to get the all the letter combinations from the digits provided
	public void word(String[] chars, String result, String num, int n, int m, int i, int j)
	{
		if (i == m) {
			System.out.print(" " + result);
            System.out.println();
			return;
			//if base case, add the letter combinations and a space for the other letter combinations and print all the combinations at once
		}
		int x = num.charAt(i) - '0';  //get the integer of the i'th position in the string of digits
		if (x < n && chars[x].length() > 0) {  //if x is less than 9 and its not 0 or 1, for each letter in that number keypad, call the function again to get all the letters
			for (int y = 0; y < chars[x].length(); ++y) {
				word(chars, result + chars[x].charAt(y), num, n, m, i + 1, j + 1);
			}
		}
		else {  //else add 1 to i for the base case if the number is 1 or 0 as it's an empty string
			word(chars, result, num, n, m, i + 1, j);
		}
	}

	public void findWord(String[] chars, String num, int n)  //function to call the word function and create a few variables
	{
		int m = num.length(); //m is the number of digits in that string, ie 3 for 156 and 4 for 8364
		if (m > 0) {          //if there are 1 or more digits in the string, create a result string to add to and call the word function to find all the letter combinations
			String result = "";
			word(chars, result, num, n, m, 0, 0);
		}
	}
	public static void main(String[] args)
	{
		KeyPad task = new KeyPad();  //I don't know what this does I just saw it online
		String[] chars = {  //set the letters for the corresponding digits in accordance to their position in the list
			"" , "" , "abc" , "def" , "ghi" , "jkl" , "mno" , "pqrs" , "tuv" , "wxyz"};
		int n = chars.length;    //n is equal to 9, the max number that is on the keypad

		Scanner in = new Scanner(System.in);
        String num = in.next();    //read in digits as a string

		task.findWord(chars, num, n);
	}
}