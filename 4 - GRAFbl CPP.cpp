// ИСТОЧНИК: https://www.techiedelight.com/graph-implementation-using-stl/

#include <iostream>
#include <vector>
using namespace std;
 
// Структура данных для представления ребра графа
struct Edge {
    int src, dest;  // исходная вершина и вершина назначения
};
 
// Класс для представления графа
class Graph
{
public:
    // вектор векторов для представления списка смежности
    vector<vector<int>> adjList;
 
    // Конструктор графа
    Graph(vector<Edge> const &edges, int n)
    {
        // изменяем размер вектора для хранения n элементов типа vector<int>
        adjList.resize(n);
 
        // добавляем рёбра в ориентированный граф
        for (auto &edge: edges)
        {
            // вставляем в конец списка смежности для исходной вершины
            adjList[edge.src].push_back(edge.dest);
 
            // раскомментируйте следующий код для неориентированного графа
            // adjList[edge.dest].push_back(edge.src);
        }
    }
};
 
// Функция для печати представления графа в виде списка смежности
void printGraph(Graph const &graph, int n)
{
    for (int i = 0; i < n; i++)
    {
        // печатаем номер текущей вершины
        cout << i << " ——> ";
 
        // печатаем все смежные вершины вершины i
        for (int v: graph.adjList[i]) {
            cout << v << " ";
        }
        cout << endl;
    }
}
 
// Реализация графа с использованием STL
int main()
{
    // вектор рёбер графа согласно приведенной выше диаграмме.
    // Обратите внимание, что инициализация вектора в таком формате будет
    // работать в C++11, C++14, C++17, но не сработает в C++98.
    vector<Edge> edges =
    {
        {0, 1}, {1, 2}, {2, 0}, {2, 1}, {3, 2}, {4, 5}, {5, 4}
    };
 
    // общее количество узлов в графе (пронумерованных от 0 до 5)
    int n = 6;
 
    // строим граф
    Graph graph(edges, n);
 
    // печатаем представление графа в виде списка смежности
    printGraph(graph, n);
 
    return 0;
}
