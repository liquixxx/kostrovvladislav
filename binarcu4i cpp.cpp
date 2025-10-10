// ISTO4NIK: https://translated.turbopages.org/proxy_u/en-ru.ru.7471a50a-68e8cd98-4b38fd02-74722d776562/https/runestone.academy/ns/books/published/cppds/Trees/BinaryHeapImplementation.html

#include <iostream>
#include <vector>
using namespace std;

// Использует вектор для создания бинарной кучи
class BinHeap{

private:
    vector<int> heapvector;  // вектор для хранения элементов кучи
    int currentSize;         // текущий размер кучи

public:
    // Инициализирует вектор и устанавливает currentSize
    // в 0 для корректного целочисленного деления
    BinHeap(vector<int> heapvector){
        this->heapvector = heapvector;
        this->currentSize = 0;
    }

    // Просеивание элемента вверх по дереву настолько, 
    // насколько это необходимо для сохранения свойства кучи
    void percUp(int i){
        while ((i / 2) > 0){
            if (this->heapvector[i] < this->heapvector[i/2]){
                int tmp = this->heapvector[i/2];
                this->heapvector[i/2] = this->heapvector[i];
                this->heapvector[i] = tmp;
            }
            i = i/2;
        }
    }

    // Добавляет элемент в конец вектора
    void insert(int k){
        this->heapvector.push_back(k);
        this->currentSize = this->currentSize + 1;
        this->percUp(this->currentSize);
    }

    // Просеивание элемента вниз по дереву настолько,
    // насколько это необходимо для сохранения свойства кучи
    void percDown(int i){
        while ((i*2) <= this->currentSize){
            int mc = this->minChild(i);
            if (this->heapvector[i] > this->heapvector[mc]){
                int tmp = this->heapvector[i];
                this->heapvector[i] = this->heapvector[mc];
                this->heapvector[mc] = tmp;
            }
            i = mc;
        }
    }

    // Находит индекс минимального дочернего элемента
    int minChild(int i){
        if (((i*2)+1) > this->currentSize){
            return i * 2;
        }
        else{
            if (this->heapvector[i*2] < this->heapvector[(i*2)+1]){
                return i * 2;
            }
            else{
                return (i * 2) + 1;
            }
        }
    }

    // Восстанавливает структуру кучи и порядок элементов 
    // после удаления корня путем перемещения последнего элемента 
    // в позицию корня и просеивания нового корневого элемента 
    // вниз по дереву в его правильную позицию
    int delMin(){
        int retval = this->heapvector[1];
        this->heapvector[1] = this->heapvector[this->currentSize];
        this->currentSize = this->currentSize - 1;
        this->heapvector.pop_back();
        this->percDown(1);
        return retval;
    }

    // Строит кучу из заданного вектора
    void buildheap(vector<int> avector){
        int i = avector.size() / 2;
        this->currentSize = avector.size();
        this->heapvector.insert(this->heapvector.end(), avector.begin(), avector.end());
        while (i > 0){
            this->percDown(i);
            i = i - 1;
        }
    }

    // Проверяет, пуста ли куча
    bool isEmpty(){
        if (this->heapvector.size()>0){
            return false;
        }
        return true;
    }

    // Находит минимальный элемент (корень кучи)
    int findMin(){
        return this->heapvector[1];
    }
};


int main(){
    int arr[] = {9, 5, 6, 2, 3};
    vector<int> a(arr,arr+(sizeof(arr)/ sizeof(arr[0])));

    vector<int> vec;
    vec.push_back(0);  // добавляем нулевой элемент для корректной индексации

    BinHeap *bh = new BinHeap(vec);
    bh->buildheap(a);

    // Последовательно извлекаем минимальные элементы
    cout << bh->delMin() << endl;
    cout << bh->delMin() << endl;
    cout << bh->delMin() << endl;
    cout << bh->delMin() << endl;
    cout << bh->delMin() << endl;

    return 0;
}
