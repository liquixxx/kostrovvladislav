public class Main {
    public static void main(String[] args) {
        // Сохраним строку в переменной
        String str_1 = "AaBbCcDd";
        
        // Выводим на экран строки
        System.out.print("str_1[::2] -> ");
        for (int i = 0; i < str_1.length(); i += 2) {
            System.out.print(str_1.charAt(i));
        }
        System.out.println("\n");
        
        // Смещаем вывод
        System.out.print("str_1[1::2]-> ");
        for (int i = 1; i < str_1.length(); i += 2) {
            System.out.print(str_1.charAt(i));
        }
        System.out.println();
    }
}
