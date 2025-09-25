#include <iostream>
#include <string>

std::string sws(const std::string& str, size_t start, size_t step) {
    std::string result;
    for (size_t i = start; i < str.length(); i += step) {
        result += str[i];
    }
    return result;
}

int main() {
    // Сохраним строку в переменной
    std::string str_1 = "AaBbCcDd";
    
    // Выводим на экран строки
    std::cout << "str_1[::2] -> " << sws(str_1, 0, 2) << "\n\n";
    
    // Смещаем вывод
    std::cout << "str_1[1::2]-> " << sws(str_1, 1, 2) << std::endl;
    
    return 0;
}