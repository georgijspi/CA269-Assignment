import java.util.*;

public class Main
{
    // generate a mapping of all digits to corresponding character arrays
    public static char[][] generateMap() {
        char[][] map = {
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
        return map;
    }

    // recursive solution to generate all combinations of keys pressed
    public static String generateCombos(char[][] map, int num, String currentOutput) {
        // base case
        if(num == 0) {
            System.out.print(currentOutput + ' '); // print completed combo
            return currentOutput;
        }

        // split the current number into a reversed head|tail format like in prolog loops so 234 becomes 23|4, where the tail is 23 and the head is 4
        int tail = Math.floorDiv(num, 10);
        int head = num % 10;
        // headLetters is an array that holds the current options for the head through which we are looping
        char[] headLetters = map[head];
        // recursive case - loop through headLetters and add the character to the current combo string
        for(char ch : headLetters)
        {
            String newOutput =  ch + currentOutput;
            generateCombos(map, tail, newOutput);
        }
        return currentOutput;
    }

    // simple function to turn an integer into an array of the integer's digits
    public static int[] numToDigitArray(int num) {
        String[] temp = Integer.toString(num).split("");
        int[] numArray = new int[temp.length];
        for(int i = 0; i < temp.length; i++)
        {
            numArray[i] = Integer.parseInt(temp[i]);
        }
        return numArray;
    }

    // function to convert an integer to the corresponding letters in the map object like when typing on a nokia-style keyboard where if you press 4+4+3+3+5+5+5+7 it would become the word 'help'
    public static void generateQuery(char[][] map, int input) {
        int len = String.valueOf(input).length();
        int[] num = numToDigitArray(input); // convert input nums to integer array of digits
        
        int sum=0; // sum of repreating digits for use when indexing
        int i;
        for(i=0; i < len;)
        {
            // base case for when either current digit is non repreating or last digit in array
            if(i == len-1 || num[i] != num[i+1]) {
                System.out.printf("%c",map[num[i]][0]); // print corresponding map character value
                i++;
            }
            
            else
            {
                while(i+sum+1<len && num[i] == num[i+sum+1])
                {
                    sum += 1; // increase sum of repeating digits for each time that the next digit is repeated
                }
                System.out.printf("%c",map[num[i]][sum]); // print corresponding map character value
                i = i+sum+1; // increase i by one + sum of repeating digits already looked at
                sum = 0; // reset sum to zero for next digit/character
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        
        char[][] map = generateMap();

        // retrieve an input from the user in the form of an integer
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your numbers: ");
        int inputNums = in.nextInt();

        // void print the combos for the input int
        generateCombos(map, inputNums, "");
        
        System.out.println();

        System.out.println("Enter your numbers to generate corresponding string: ");
        inputNums = in.nextInt();

        // void print the corresponding letters that accompany the input digits
        generateQuery(map, inputNums);
    }
}
