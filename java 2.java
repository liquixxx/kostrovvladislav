import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        // Сохраним список в переменной
        List<String> li = List.of("a", "1", "b", "2", "c", "3");
        
        // Получаем требуемые срезы
        List<String> li_1 = IntStream.range(0, li.size())
                .filter(i -> i % 2 == 0)
                .mapToObj(li::get)
                .collect(Collectors.toList());
                
        List<String> li_2 = IntStream.range(0, li.size())
                .filter(i -> i % 2 == 1)
                .mapToObj(li::get)
                .collect(Collectors.toList());
        
        // Удаляем список
        li = null;
        
        // Выводим полученные списки на экран
        System.out.println("li_1: " + li_1);
        System.out.println();
        System.out.println("li_2: " + li_2);
    }
}