import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
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

        GAME:
        while (true) {

            Scanner scannerOfChoice = new Scanner(System.in);

            CHOICE:
            while (true) {
                System.out.println("    Хотите начать новую игру? \n (Д) - Новая игра   (Н) - Выход");
                String decision = scannerOfChoice.nextLine().toUpperCase();
                System.out.println(decision);

                if (decision.equals("Д"))
                    break CHOICE;
                else if (decision.equals("Н"))
                    break GAME;
                else
                    System.out.println("Уважаемый пользователь, пожалуйста, введите только Д либо Н");
                continue GAME;
            }




//
//            System.out.println("Выберите уровень сложности \n (1) - легкий   (2) - средний   (3) - тяжелый");
//            Scanner scannerOfDifficulty = new Scanner(System.in);
//            String difficulty = scannerOfDifficulty.nextLine().toUpperCase();
//            System.out.println(difficulty);
//            scannerOfDifficulty.close();
//
//            DIFFICULTY:
//            while (true) {
//                if (difficulty.equals("1"))
//                    break CHOICE;
//                else if (decision.equals("Н"))
//                    break GAME;
//                else
//                    System.out.println("Уважаемый пользователь, пожалуйста, введите только Д либо Н");
//                continue GAME;
//            }
            System.out.println(hiddenWord);
            char[] wordAsChars = hiddenWord.toCharArray();



            char letter = '*';



            while (true) {

                System.out.println("Введите букву русского алфавита");
                String letterInput = scannerOfChoice.nextLine().toUpperCase();


                if (!letterInput.isEmpty() )
                    letter = letterInput.charAt(0);
                else
                    System.out.println("Вы ничего не ввели");

                if (letter >= 'А' && letter <= 'Я' || letter == 'Ё')
                break;
                else
                    System.out.println("Вы ввели не букву, попробуйте еще раз");
                continue;
            }


            System.out.println(letter);
            System.out.println(letter);



            break GAME;
        }

    }

}



