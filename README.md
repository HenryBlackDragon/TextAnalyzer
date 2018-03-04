# TextAnalyzer

Анализатор текста:
    - подсчет количество повторяющихся слов;
    - проверка правильности расстановки скобок.
    
        Входные данные: txt-файл
        Результат:
            - топ 10 наиболее встречаемых слов;
            - correct / incorrect после проверки файла.
            
   http://localhost:8080/analyzer/list?path=*.txt - возваращает топ 10 встечаемых слов;
   http://localhost:8080/analyzer/check?path=*.txt - проверка на правильность расстановки скобок.
   
   где ?path=*.txt указывает на путь к файлу, если данного файла нету,
   то загружается файл из ресурсов.