import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner scan = new Scanner(System.in);                 //Создаем сканер.
        System.out.println("Введите выражение: ");
        String input = scan.nextLine();                      //Для считывания с консоли строки полностью.

        System.out.println(calc(input));                     //Принимаем возвращаемую сумму чисел и выводим результат.
    }

    public static String calc(String input) {

        String[] operator = {"+", "-", "/", "*"};              //Задаем знаки действий и
        String[] symbol = {"\\+", "-", "/", "\\*"};            //символы, для разделения строки по знакам действий.

        int operatorIndex = -1;

        for (int i = 0; i < operator.length; i++) {            //Определяем знак действия и присваиваем ему значение i.
            if (input.contains(operator[i])) {
                operatorIndex = i;
                break;
            }
        }

        Converter converter = new Converter();
        String[] data = input.split(symbol[operatorIndex]);  //Определяем цифры путем разделения строки по символу.


        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) { //Определяем находятся ли цифры в одном формате.
            int a, b;

            boolean isRoman = converter.isRoman(data[0]);      //Определяем римские ли это цифры.
            if (isRoman) {

                a = converter.romanToInt(data[0]);             //Если римские, конвертируем в арабские.
                b = converter.romanToInt(data[1]);

            } else {
                a = Integer.parseInt(data[0]);                 //Если арабские, присваиваем им значение a и b.
                b = Integer.parseInt(data[1]);
            }

            if (a > 10 || b > 10 || a < 1 || b < 1 || data.length != 2) {  //Согласно заданию, отфильтровываем лишнее
                throw new RuntimeException();                              //И на "лишнее" выдаем исключение
            }

            int result = switch (operator[operatorIndex]) {  //выполняем арифметическое действие
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                default -> a / b;
            };

            if(isRoman){
                return converter.intToRoman(result); // Возвращаем сумму Римских чисел
            } else {
                return result + "";                  // Возвращаем сумму Арабских чисел
            }
        } else {
            throw new RuntimeException();            //Выдаем исключение, если используются одновременно разные системы счисления.
                                                    //Если сумма Римских чисел меньше I.
        }

    }
}






