// источник: https://www.techiedelight.com/graph-implementation-using-stl/

import java.util.*;

//класс для хранения ребер взвешенного графа
class Edge {
    int src, dest, weight;  // исходная вершина, вершина назначения и вес ребра
    Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
}

// Класс графа
class Graph {
    // узел списка смежности 
    static class Node {
        int value, weight;  // номер вершины и вес ребра
        Node(int value, int weight)  {
            this.value = value;
            this.weight = weight;
        }
    };

// определяем список смежности

List<List<Node>> adj_list = new ArrayList<>();

    //Конструктор графа
    public Graph(List<Edge> edges)
    {
        // выделение памяти для списка смежности
        for (int i = 0; i < edges.size(); i++)
            adj_list.add(i, new ArrayList<>());

        // добавляем ребра в граф
        for (Edge e : edges)
        {
            // создаем новый узел в списке смежности от src к dest
            adj_list.get(e.src).add(new Node(e.dest, e.weight));
        }
    }

// печать списка смежности для графа
    public static void printGraph(Graph graph)  {
        int src_vertex = 0;  // начальная вершина
        int list_size = graph.adj_list.size();  // размер списка смежности

        System.out.println("Содержимое графа:");
        while (src_vertex < list_size) {
            // проходим по списку смежности и печатаем ребра
            for (Node edge : graph.adj_list.get(src_vertex)) {
                System.out.print("Вершина:" + src_vertex + " ==> " + edge.value + 
                                " (" + edge.weight + ")\t");
            }

            System.out.println();  // переход на новую строку для следующей вершины
            src_vertex++;  // переходим к следующей вершине
        }
    }
}

class Main{
    public static void main (String[] args)    {
        // определяем ребра графа 
        List<Edge> edges = Arrays.asList(new Edge(0, 1, 2),new Edge(0, 2, 4),
                   new Edge(1, 2, 4),new Edge(2, 0, 5), new Edge(2, 1, 4),
                   new Edge(3, 2, 3), new Edge(4, 5, 1),new Edge(5, 4, 3));

        // вызываем конструктор класса Graph для построения графа
        Graph graph = new Graph(edges);

        // печатаем граф в виде списка смежности
        Graph.printGraph(graph);
    }
}
