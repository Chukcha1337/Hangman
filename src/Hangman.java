import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

    public static void main(String[] args) throws FileNotFoundException {

        String chosenWord = null;


        // Цикл для выбора подходящего слова из файла


        GAME:
        while (true) {

            Scanner scannerOfChoice = new Scanner(System.in);

            CHOICE:
            while (true) {
                System.out.println("    Хотите начать новую игру? \n (Д) - Новая игра   (Н) - Выход");
                String decision = scannerOfChoice.nextLine().toUpperCase();


                if (decision.equals("Д"))
                    break CHOICE;
                else if (decision.equals("Н"))
                    break GAME;
                else
                    System.out.println("Уважаемый пользователь, пожалуйста, введите только Д либо Н");
                continue GAME;
            }
            CHOOSINESS:
            while (true) {

                Random random = new Random();
                Scanner scanner = new Scanner(new File("dictionary.txt"));

                int wordNumber = random.nextInt(67774);


                for (int i = 0; i < wordNumber; i++)
                    if (scanner.hasNext())
                        chosenWord = scanner.nextLine();
                    else continue CHOOSINESS;


                char[] check = chosenWord.toCharArray();

                boolean forbiddenChars = false;
                for (char c : check)
                    if (c == ' ' || c == '-')
                        forbiddenChars = true;

                if (check.length < 5 || forbiddenChars)
                    continue;
                else

                scanner.close();
                break;
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

            String chosenWordUpperCase = chosenWord.toUpperCase();

            char[] wordAsChars = chosenWordUpperCase.toCharArray();

            char[] hiddenWord = new char[wordAsChars.length];
            System.out.println("В загаданном слове "+ wordAsChars.length + " букв" );
            System.out.println(chosenWordUpperCase);
            for (int i = 0; i < hiddenWord.length; i++) {
                hiddenWord[i] = '*';
                System.out.print(hiddenWord[i]+ " ");
            }
            System.out.println();
            char letter = '-';
            int counterOfMistakes = 0;
            int maximumMistakes = 8;
            char[] mistakes = new char[maximumMistakes];
            for (int k = 0; k < mistakes.length; k++)
                mistakes[k] = '1';


            CURRENT_GAME:
            while (true) {


// Ввод буквы русского алфавита
                LETTER:
                while (true) {

                    System.out.println("Введите букву русского алфавита");
                    String letterInput = scannerOfChoice.nextLine().toUpperCase();


                    if (!letterInput.isEmpty())
                        letter = letterInput.charAt(0);
                    else
                        System.out.println("Вы ничего не ввели");

                    for (int j = 0; j < mistakes.length; j++) {
                        if (mistakes[j] == letter) {
                            System.out.println("Вы уже вводили эту букву");
                            continue LETTER;

                        }
                    }
                    for (int j = 0; j < hiddenWord.length; j++) {
                        if (hiddenWord[j] == letter) {
                            System.out.println("Вы уже вводили эту букву");
                            continue LETTER;

                        }
                    }
                    if (letter >= 'А' && letter <= 'Я' || letter == 'Ё')
                        break;
                    else
                        System.out.println("Вы ввели не букву, попробуйте еще раз");


                }
                for (int q = 0; q < 30; q++) {
                    System.out.println();
                }


                for (int i = 0; i < wordAsChars.length; i++) {
                    if (wordAsChars[i] == letter)
                        hiddenWord[i] = letter;
                }

                for (int i = 0; i < wordAsChars.length; i++) {
                    if (wordAsChars[i] != letter) ;
                    else {
                        System.out.print("Вы угадали букву! Искомое слово: ");

                        for (char c : hiddenWord) {
                            System.out.print(c + " ");
                        }
                        System.out.println();
                        System.out.print("Неправильные буквы: ");
                        for (int j = 0; j < mistakes.length; j++) {
                            if (mistakes[j] != '1') {
                                System.out.print(mistakes[j] + " ");
                            }
                        }
                        System.out.println();
                        Scanner painting = new Scanner(new File("hang.txt"));
                        for (int t = 0; t <= ((counterOfMistakes - 1) * 10 + 10); t++) {
                            if (painting.hasNext())
                                painting.nextLine();
                        }
                        for (int w = 0; w < 10; w++) {
                            if (painting.hasNext())
                                System.out.println(painting.nextLine());
                        }


                        WINCONDITION:
                        while (true) {
                            for (char value : hiddenWord) {
                                if (value == '*')
                                    break WINCONDITION;
                            }
                            System.out.println();
                            System.out.print("Поздравляю, Вы выиграли! Искомое слово: ");
                            for (char c : hiddenWord) {
                                System.out.print(c + " ");
                            }
                            System.out.println();
                            continue GAME;

                        }


                        continue CURRENT_GAME;
                    }

                }

                counterOfMistakes++;
                System.out.println("Увы, Вы ошиблись. Количество допущенных ошибок: " + counterOfMistakes);
                mistakes[counterOfMistakes - 1] = letter;
                System.out.print("Неправильные буквы: ");
                for (int j = 0; j < mistakes.length; j++) {
                    if (mistakes[j] != '1') {
                        System.out.print(mistakes[j] + " ");
                    }
                }
                System.out.println();
                for (char c : hiddenWord) {
                    System.out.print(c + " ");
                }

                System.out.println();

                Scanner painting = new Scanner(new File("hang.txt"));
                for (int t = 0; t <= ((counterOfMistakes - 1) * 10 + 10); t++) {
                    if (painting.hasNext())
                        painting.nextLine();
                }
                for (int w = 0; w < 10; w++) {
                    if (painting.hasNext())
                        System.out.println(painting.nextLine());
                }



                if (counterOfMistakes == maximumMistakes) {
                    System.out.println("Вы проиграли");
                    System.out.println("Искомое слово: " + chosenWordUpperCase);
                    continue GAME;
                }


            }
        }

    }

}







