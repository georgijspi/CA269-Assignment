import java.util.*;

public class Main
{
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        
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
        /*
        new String[9][];
        
        map[0] = new String[] {'\0'};
        map[1] = new String[] {'\0'};
        map[2] = new String[] {'a', 'b', 'c'};
        map[3] = new String[] {'d', 'e', 'f'};
        map[4] = new String[] {'g', 'h', 'i'};
        map[5] = new String[] {'j', 'k', 'l'};
        map[6] = new String[] {'m', 'n', 'o'};
        map[7] = new String[] {'p', 'q', 'r', 's'};
        map[8] = new String[] {'t', 'u', 'v'};
        map[9] = new String[] {'w', 'x', 'y', 'z'};

    */
        for(char[] elem : map) {
            System.out.println(Arrays.toString(elem));
        }

    }
}
