//isto4nik: https://github.com/KiraKari/proga/tree/master/src/main/java

import java.util.ArrayList;
import java.util.Collections;

public class Algorithm {
    // Структуры данных для работы алгоритма
    private ArrayList<ArrayList<Integer>> g = new ArrayList<> (); // Матрица смежности графа
    private ArrayList<Boolean> visited = new ArrayList<> ();      // Посещенные вершины
    private ArrayList<Integer> path = new ArrayList<> ();         // Текущий путь
    private ArrayList<Integer> optimal_path = new ArrayList<> (); // Оптимальный путь

    private int n = 0;              // Количество вершин
    private int min_weight = 99999999; // Минимальный вес пути (инициализируем большим числом)
    private int start = 0;          // Стартовая вершина
    private int finish = 0;         // Конечная вершина

    private Visualization visual;   // Объект для визуализации

    // Основной метод запуска алгоритма
    public void start()
    {
        getInput();     // Шаг 1: Получаем входные данные
        init();         // Шаг 2: Инициализируем структуры данных
        updateViz();    // Шаг 3: Создаем визуализацию графа
        DFS(0, -1, n - 1, 0); // Шаг 4: Запускаем поиск в глубину
        showAnswer();   // Шаг 5: Показываем результат
    }

    // Инициализация массивов visited и path
    private void init(){
        for(int i = 0; i < n; i++){
            path.add(-1);       // -1 означает, что вершина еще не посещена в текущем пути
            visited.add(false); // Изначально все вершины не посещены
        }
    }

    // Получение входных данных от пользователя
    private void getInput(){
        n = Input.getN(); // Получаем количество вершин
        g = Input.getG(); // Получаем матрицу смежности графа
    }

    // Создание и обновление визуализации
    private void updateViz(){
        visual = new Visualization();
        ArrayList<Point> points = getCoordinates(); // Получаем координаты вершин для отрисовки
        
        // Добавляем вершины на визуализацию
        for(int i = 0; i < n; i++){
            visual.addVertex(i, (int)(points.get(i).x * 250) + 250, (int)(points.get(i).y * 250) + 250);
        }

        // Добавляем ребра между всеми вершинами (полный граф)
        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){ // Идем по правой от главной диагонали части матрицы
                if(i == j) continue;    // Пропускаем петли (ребра из вершины в себя)
                visual.addEdge(i, j);   // Добавляем ребро между вершинами i и j
            }
        }
    }

    // Рекурсивный поиск в глубину для нахождения гамильтонова цикла
    private void DFS(int v, int from, int steps, int weight){
        // Базовый случай: если вершина уже посещена, выходим
        if (visited.get(v)) return;

        // Визуализация: подсвечиваем текущее ребро
        visual.turnOn(from, v);
        visual.changeSumOfWeights(weight, min_weight);

        // Отсечение: если текущий вес уже больше минимального, прекращаем поиск
        if(weight > min_weight){
            visual.turnOff(from, v);
            return;
        }

        // Помечаем вершину как посещенную и добавляем в путь
        visited.set(v, true);
        path.set(v, from);
        
        // Если прошли все вершины и можем вернуться в начало
        if(steps == 0 && g.get(v).get(start)!= -1){
            weight += g.get(v).get(start); // Добавляем вес возврата в стартовую вершину
            if(weight < min_weight){
                min_weight = weight; // Обновляем минимальный вес
                optimal_path = (ArrayList<Integer>) path.clone(); // Сохраняем найденный путь
                finish = v; // Запоминаем последнюю вершину перед возвратом
                visual.changeSumOfWeights(weight, min_weight);
            }
        }

        // Рекурсивно посещаем всех соседей текущей вершины
        for(int i = 0; i < n; i++){ 
            if(g.get(v).get(i) == -1) continue; // Пропускаем если нет ребра
            DFS(i, v, steps - 1, weight + g.get(v).get(i)); // Рекурсивный вызов
        }
        
        // Backtracking: откатываем изменения для поиска других путей
        visited.set(v, false);
        visual.turnOff(from, v); // Выключаем подсветку ребра
    }

    // Вывод результата работы алгоритма
    private void showAnswer(){
        ArrayList<Integer> a = get_path(); // Получаем оптимальный путь
        // Выводим путь в лог
        for(int i = 0; i < a.size() - 1; i++){
            visual.log(i+" - ");
        }
        visual.log(finish+"\nМинимальный вес "+min_weight+"\n");
    }

    // Восстановление оптимального пути из массива предков
    private ArrayList<Integer> get_path()
    {
        ArrayList<Integer> ans = new ArrayList<> ();

        int prev = -1;
        // Восстанавливаем путь от конечной вершины к начальной
        for (int v = finish; v != start; v = optimal_path.get(v)){
            visual.turnOn(prev, v); // Подсвечиваем ребра пути
            ans.add(v);
            prev = v;
        }

        ans.add(start);
        Collections.reverse(ans); // Переворачиваем для получения пути в правильном порядке
        
        // Визуализация: подсвечиваем все ребра оптимального пути
        visual.turnOnEdge(ans.get(0), ans.get(1));
        for(int i = 0; i < n; i++){
            if(i != 0 && i != n - 1){
                visual.turnOnEdge(ans.get(i - 1), ans.get(i));
            }
            visual.turnOnVertex(ans.get(i)); // Подсвечиваем вершины пути
        }
        visual.turnOnEdge(start, finish); // Подсвечиваем последнее ребро (возврат в начало)
        return ans;
    }

    // Расчет координат вершин для кругового расположения на визуализации
    private ArrayList<Point> getCoordinates(){
        ArrayList<Point> coord = new ArrayList<> ();
        double step = (2*Math.PI)/n; // Шаг между вершинами на окружности
        // Располагаем вершины равномерно по кругу
        for(double i = 0; i < 2*Math.PI; i += step){
            double y = Math.sin(i); // Y-координата
            double x = Math.cos(i); // X-координата
            coord.add(new Point(x, y));
        }
        return coord;
    }
}
