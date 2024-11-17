import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

    public static void main(String[] args) throws FileNotFoundException {
        String hiddenWord = null;
        // Цикл для выбора подходящего слова из файла
        CHOOSINESS:
        while (true) {

            Random random = new Random();
            Scanner scanner = new Scanner(new File("dictionary.txt"));

            int wordNumber = random.nextInt(67774);


            for (int i = 0; i < wordNumber; i++)
                if (scanner.hasNext())
                    hiddenWord = scanner.nextLine();
                else continue CHOOSINESS;

            System.out.println(wordNumber);
            System.out.println(hiddenWord);

            char[] check = hiddenWord.toCharArray();

            boolean forbiddenChars = false;
            for (char c : check)
                if (c == ' ' || c == '-')
                    forbiddenChars = true;

            if (check.length < 10 || forbiddenChars)
                continue CHOOSINESS;
            else
                System.out.println(check.length);
                System.out.println(Arrays.toString(check));
                scanner.close();
                break;
        }






        System.out.println(hiddenWord);
        char[] wordAsChars = hiddenWord.toCharArray();
    }

}



