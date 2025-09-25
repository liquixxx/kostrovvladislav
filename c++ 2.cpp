#include <iostream>
#include <vector>
#include <string>

int main() {
    // Сохраним список в переменной
    std::vector<std::string> li = {"a", "1", "b", "2", "c", "3"};
    
    // Получаем требуемые срезы
    std::vector<std::string> li_1;
    std::vector<std::string> li_2;
    
    for (size_t i = 0; i < li.size(); i += 2) {
        li_1.push_back(li[i]);
    }
    
    for (size_t i = 1; i < li.size(); i += 2) {
        li_2.push_back(li[i]);
    }
    
    // Удаляем список
    li.clear();
    
    // Выводим полученные списки
    std::cout << "li_1: ";
    for (const auto& item : li_1) {
        std::cout << item << " ";
    }
    std::cout << "\n\n";
    
    std::cout << "li_2: ";
    for (const auto& item : li_2) {
        std::cout << item << " ";
    }
    std::cout << std::endl;
    
    return 0;
}