import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

    public static void main(String[] args) throws FileNotFoundException {

        String chosenWord = "Тут будет слово из словаря";

        GAME:
        while (true) {

            Scanner scannerOfChoice = new Scanner(System.in);

            // Цикл выбора начала или завершения игры
            while (true) {
                System.out.println("    Хотите начать новую игру? \n (Д) - Новая игра   (Н) - Выход");
                String decision = scannerOfChoice.nextLine().toUpperCase();

                if (decision.equals("Д"))
                    break;
                else if (decision.equals("Н")) {
                    System.out.println("Благодарю Вас за игру!");
                    break GAME;
                } else
                    System.out.println("Уважаемый пользователь, пожалуйста, введите только Д либо Н");
                continue GAME;
            }
            int difficultyMultiplier;

            // Цикл выбора сложности
            while (true) {
                System.out.println("Выберите уровень сложности \n (1) - легкий   (2) - средний   (3) - тяжелый");
                Scanner scannerOfDifficulty = new Scanner(System.in);
                String difficulty = scannerOfDifficulty.nextLine().toUpperCase();

                System.out.println(difficulty);

                switch (difficulty) {
                    case ("1"):
                        difficultyMultiplier = 1;
                        break;
                    case ("2"):
                        difficultyMultiplier = 2;
                        break;
                    case ("3"):
                        difficultyMultiplier = 4;
                        break;
                    default:
                        System.out.println("Уважаемый пользователь, пожалуйста, введите только 1, 2 или 3");
                        continue;
                }
                break;
            }

            //Цикл выбора слова из словаря с проверкой на длину и наличие запрещенных символов
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

                for (char c : check)
                    if (c == ' ' || c == '-' || check.length < 5)
                        continue CHOOSINESS;
                    else
                        scanner.close();
                break;
            }

            //Объявление необходимых пременных
            char letter = '-';
            int counterOfMistakes = 0;
            int maximumMistakes = 8 / difficultyMultiplier;
            String chosenWordUpperCase = chosenWord.toUpperCase();
            char[] wordAsChars = chosenWordUpperCase.toCharArray();
            char[] hiddenWord = new char[wordAsChars.length];
            char[] mistakes = new char[maximumMistakes];

            Arrays.fill(mistakes, '*');
            System.out.println("В загаданном слове " + wordAsChars.length + " букв");

            for (int i = 0; i < hiddenWord.length; i++) {
                hiddenWord[i] = '*';
                System.out.print(hiddenWord[i] + " ");
            }
            System.out.println();

            //Цикл конкретной игры
            CURRENT_GAME:
            while (true) {

                //Цикл ввода буквы русского алфавита
                LETTER:
                while (true) {

                    System.out.println("Введите букву русского алфавита \nЕсли хотите выйти из текущей игры, введите (1)");
                    String letterInput = scannerOfChoice.nextLine().toUpperCase();

                    if (!letterInput.isEmpty())
                        letter = letterInput.charAt(0);
                    else
                        System.out.println("Вы ничего не ввели");

                    if (letter == '1')
                        continue GAME;

                    for (char mistake : mistakes) {
                        if (mistake == letter) {
                            System.out.println("Вы уже вводили эту букву");
                            continue LETTER;
                        }
                    }

                    for (char hidden : hiddenWord) {
                        if (hidden == letter) {
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
                // Если буква угадана
                for (char checkedLetter : wordAsChars) {
                    if (checkedLetter == letter) {
                        showHangCondition(mistakes, counterOfMistakes, hiddenWord, "угадали", difficultyMultiplier);

                        // Проверка условия победы
                        WINCONDITION:
                        while (true) {
                            for (char value : hiddenWord) {
                                if (value == '*')
                                    break WINCONDITION;
                            }
                            System.out.println("Поздравляю, Вы выиграли! Искомое слово: ");
                            showHiddenWord(hiddenWord);
                            System.out.println();
                            continue GAME;
                        }
                        continue CURRENT_GAME;
                    }
                }
                // Если буква не угадана
                counterOfMistakes++;
                mistakes[counterOfMistakes - 1] = letter;
                showHangCondition(mistakes, counterOfMistakes, hiddenWord, "не угадали", difficultyMultiplier);
                if (counterOfMistakes == maximumMistakes) {
                    System.out.println("Вы проиграли");
                    System.out.println("Искомое слово: " + chosenWordUpperCase);
                    System.out.println();
                    continue GAME;
                }
            }
        }
    }

    public static void showHiddenWord(char[] hiddenWord) {
        for (char c : hiddenWord)
            System.out.print(c + " ");
        System.out.println();
    }

    public static void showHangCondition(char[] mistakes, int counterOfMistakes, char[] hiddenWord, String s, int difficultyMultiplier) throws FileNotFoundException {

        System.out.print("Вы " + s + " букву! \nИскомое слово: ");
        showHiddenWord(hiddenWord);
        System.out.println("Количество допущенных ошибок: " + counterOfMistakes + " / " + mistakes.length);
        System.out.print("Неправильные буквы: ");

        for (char mistake : mistakes) {
            if (mistake != '*') {
                System.out.print(mistake + " ");
            }
        }
        System.out.println();
        Scanner painting = new Scanner(new File("hang.txt"));
        for (int t = 0; t <= (counterOfMistakes * 10 * difficultyMultiplier + 10); t++) {
            if (painting.hasNext())
                painting.nextLine();
        }
        for (int w = 0; w < 10; w++) {
            if (painting.hasNext())
                System.out.println(painting.nextLine());
        }
    }

}







