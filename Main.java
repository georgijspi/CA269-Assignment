import java.util.*;

public class Main
{
    // generate a mapping of all digits to corresponding character arrays
    public static char[][] generateMap() {
        char[][] map = {
            {' '},
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

    public static void drawKeypad(String input) {
        String outputString = input;
        String[] output = {""};
        
        System.out.println("            _.._           ");
        System.out.println("     __.--\"\" __ \"\"--.__    ");
        System.out.println("   .'//   .-\"  \"-.   \\\\`,  ");
        System.out.println("  : :'  .'.  :;  ,`.  `; ; ");
        System.out.println(" /; ;  /  T. $$ ,P  \\  : : ");
        System.out.println("/: :  ;    T.:;,P    :  ; ;");
        System.out.println(")| | :      `  '      ; | |");
        System.out.println("`j | :.--------------.: | |");
        System.out.println(" ; ; |                | : :");
        System.out.println(" ; ; |                | : :");
    
    
        if(outputString.length() > 16) {
            output = outputString.split("(?<=\\G................)"); // regex mojo to split the string every 16 dogots (16 is denoted by no/ of .'s)
            if(output[output.length-1].length() < 16) {
                while(output[output.length-1].length() != 16) {
                    output[output.length-1] = output[output.length-1] + " "; // add sufficient whitespace for nice formatting in the output
                }
            }
        }
        else {
            while(outputString.length() != 16) {
                outputString = outputString + " ";
            }
            output[0] = outputString;
        }

        for(int i = 0; i < output.length; i++) {
            System.out.print(" | | |"); // left
            System.out.print(output[i]); // left
            System.out.println("| | |"); // right
        }
        

        
        System.out.println(" : : |                | ; ;");
        System.out.println(" : : :________________: ; ;");
        System.out.println("  ; ;__    _...._    __: : ");
        System.out.println("  | ;  \"-./ ,--, \\,-\"  : | ");
        System.out.println("  | '._   \\ ;  : /   _.' | ");
        System.out.println("  :  __`-. `.\"\",' .-'__  ; ");
        System.out.println("   ;`.__> `.J__L.' <__.':  ");
        System.out.println("   ;.--._   .--.   _.--,:  ");
        System.out.println("   ||    | 2 abc| 3 def||  ");
        System.out.println("   |`.__.' `.__.' `.__.'|  ");
        System.out.println("   |.--._   .--.   _.--,|  ");
        System.out.println("   |4 ghi| 5 jkl| 6 mno||  ");
        System.out.println("   |`.__.' `.__.' `.__.'|  ");
        System.out.println("   |.--._   .--.   _.--,|  ");
        System.out.println("   |7pqrs| 8 tuv| 9wxyz||  ");
        System.out.println("   ;`.__.' `.__.' `.__.':  ");
        System.out.println("  : .--._   .--.   _.--, ; ");
        System.out.println("  ; `.__.' `.__.' `.__.' : ");
        System.out.println("  ;                      : ");
        System.out.println("  '--..__          __..--' ");
        System.out.println("         \"\"\"\"\"\"\"\"\"\"       ");
    }

    // recursive solution to generate all combinations of keys pressed
    public static String generateCombos(char[][] map, int num, String currentOutput) {
        // base case
        if(num == 0) {
            System.out.print(currentOutput + ' '); // print completed combo
            return "";
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

    public static boolean sameDigits(int[] digits) {
        for(int i=0; i < digits.length; i++)
        {
            if(digits[i] == digits[0]) {
                ;
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
            if(inputNums[i] != null && inputNums[i].length <= map[inputNums[i][0]].length && sameDigits(inputNums[i]))
            {
                output = output + map[inputNums[i][0]][inputNums[i].length-1];
            }
        }
        return output;
    }
    
    
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
        drawKeypad("Enter your numbers all in one go to see all possible combinations.");
        int inputNums = in.nextInt();

        // void print the combos for the input int
        generateCombos(map, inputNums, "");
        
        System.out.println();

        drawKeypad("Enter your numbers one by one separated by the enter key. To end input simply press the enter key again."); // change this later to drawKeypad("Enter your numbers: ")
        String genQuer = generateQuery2(map);
        System.out.printf("Output string: %s%n", genQuer);
    }
}
