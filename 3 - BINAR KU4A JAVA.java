// ISTO4NIK: https://ru.stackoverflow.com/questions/731067/Реализация-двоичной-кучи

public class BinaryHeap {

    private int[] elements;  // массив для хранения элементов кучи

    // Конструктор: строит кучу из неупорядоченного массива
    public BinaryHeap(int[] elements) {
        this.elements = elements;
        // Начинаем с последнего нелистового узла и просеиваем вниз до корня
        for (int i = elements.length / 2 - 1; i >= 0; --i) {
            siftDown(i);
        }
    }

    // Добавление нового элемента в кучу
    public void add(int element) {
        // Создаем новый массив на 1 элемент больше
        int[] newElements = new int[elements.length + 1];
        // Копируем старые элементы
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        // Добавляем новый элемент в конец
        newElements[newElements.length - 1] = element;
        elements = newElements;

        // Просеиваем новый элемент вверх для восстановления свойства кучи
        siftUp(elements.length - 1);
    }

    // Извлечение максимального элемента (корня кучи)
    public int extractMax() {
        assert !isEmpty();  // проверка, что куча не пуста

        int result = elements[0];  // сохраняем максимальный элемент
        // Перемещаем последний элемент в корень
        elements[0] = elements[elements.length - 1];
        deleteLast();  // удаляем последний элемент

        // Если куча не пуста, просеиваем новый корень вниз
        if (!isEmpty()) {
            siftDown(0);
        }

        return result;  // возвращаем извлеченный максимальный элемент
    }

    // Проверка пустоты кучи
    public boolean isEmpty() {
        return elements.length == 0;
    }

    // Удаление последнего элемента из массива
    private void deleteLast() {
        if (elements.length > 1) {
            // Создаем новый массив на 1 элемент меньше
            int[] newElements = new int[elements.length - 1];
            // Копируем все элементы кроме последнего
            System.arraycopy(elements, 0, newElements, 0, elements.length - 1);
            elements = newElements;
        } else {
            elements = new int[0];  // создаем пустой массив
        }
    }

    // Просеивание элемента вниз для восстановления свойства кучи
    private void siftDown(int i) {
        int left = 2 * i + 1;   // индекс левого потомка
        int right = 2 * i + 2;  // индекс правого потомка

        // Ищем наибольший элемент среди текущего и его потомков
        int largest = i;
        if (left < elements.length && elements[left] > elements[i]) {
            largest = left;
        }
        if (right < elements.length && elements[right] > elements[largest]) {
            largest = right;
        }
        
        // Если наибольший элемент не текущий, меняем местами и продолжаем просеивание
        if (largest != i) {
            swap(i, largest);
            siftDown(largest);  // рекурсивно просеиваем дальше
        }
    }

    /**
     * Просеивание элемента наверх
     * Используется после добавления нового элемента
     * @param i - индекс элемента для просеивания вверх
     */
    private void siftUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;  // индекс родительского элемента
            // Если текущий элемент меньше родителя, свойство кучи восстановлено
            if (elements[i] <= elements[parent])
                return;
            // Меняем местами с родителем
            swap(i, parent);
            i = parent;  // переходим к родительской позиции
        }
    }

    // Обмен двух элементов местами
    private void swap(int index1, int index2) {
        int temp = elements[index1];
        elements[index1] = elements[index2];
        elements[index2] = temp;
    }

    // Получение копии массива элементов (для тестирования)
    public int[] getElements() {
        int[] elementsCopy = new int[elements.length];
        System.arraycopy(elements, 0, elementsCopy, 0, elements.length);
        return elementsCopy;
    }
}
