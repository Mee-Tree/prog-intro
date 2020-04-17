# Тесты к курсу «Введение в программирование»

[Условия домашних заданий](http://www.kgeorgiy.info/courses/prog-intro/homeworks.html)

## Домашнее задание 12. Разбор выражений

Модификации
 * *Базовая*
    * Класс `ExpressionParser` должен реализовывать интерфейс
        [Parser](parser/Parser.java)
    * Результат разбора должен реализовывать интерфейс
        [TripleExpression](expression/TripleExpression.java)
    * [Исходный код тестов](tests/parser/ParserTest.java)
 * *Shifts*
    * Дополнительно реализуйте бинарные операции:
        * `<<` – сдвиг влево, минимальный приоритет (`1 << 5 + 3` равно `1 << (5 + 3)` равно 256);
        * `>>` – сдвиг вправо, минимальный приоритет (`1024 >> 5 + 3` равно `1024 >> (5 + 3)` равно 4);
    * [Исходный код тестов](tests/parser/ParserShiftsTest.java)
 * *ReverseDigits*
    * Реализуйте операции из модификации *Shifts*.
    * Дополнительно реализуйте унарные операции (приоритет как у унарного минуса):
        * `reverse` – число с переставленными цифрами, `reverse -12345` равно -54321;
        * `digits` – сумма цифр числа, `digits -12345` равно 15.
    * [Исходный код тестов](tests/parser/ParserReverseDigitsTest.java)
 * *Abs*
    * Реализуйте операции из модификации *Shifts*.
    * Дополнительно реализуйте унарные операции (приоритет как у унарного минуса):
        * `abs` – модуль числа, `abs -5` равно 5;
        * `square` – возведение в квадрат, `square -5` равно 25.
    * [Исходный код тестов](tests/parser/ParserAbsSquareTest.java)


## Домашнее задание 11. Выражения

Модификации
 * *Базовая*
    * Реализуйте интерфейс [Expression](expression/Expression.java)
    * [Исходный код тестов](tests/expression/ExpressionTest.java)
        * Запускать c аргументом `easy` или `hard`
 * *Double*
    * Дополнительно реализуйте интерфейс [DoubleExpression](expression/DoubleExpression.java)
    * [Исходный код тестов](tests/expression/DoubleExpressionTest.java)
 * *Triple*
    * Дополнительно реализуйте интерфейс [TripleExpression](expression/TripleExpression.java)
    * [Исходный код тестов](tests/expression/TripleExpressionTest.java)


## Домашнее задание 10. Игра n,m,k

Модификации
 * *Турнир*
    * Добавьте поддержку кругового турнира из _c_ кругов
    * Выведите таблицу очков по схеме:
        * 3 очка за победу
        * 1 очко за ничью
        * 0 очков за поражение
 * *Multiplayer*
    * Добавьте поддержку значков `-` и `|`
    * Добавьте возможность игры для 3 и 4 игроков
 * *Ромб*
    * Добавить поддержку доски в форме ромба
 * *Матчи*
    * Добавьте поддержку матчей: последовательность игр указанного числа побед
    * Стороны в матче должны меняться каждую игру

## Домашнее задание 9. Markdown to HTML

Модификации
 * *Underline*
    * Добавьте поддержку `++подчеркивания++`: `<u>подчеркивания</u>`
 * *Link*
    * Добавьте поддержку ```[ссылок с _выделением_](https://kgeorgiy.info)```:
        ```&lt;a href='https://kgeorgiy.info'>ссылок с &lt;em>выделением&lt;/em>&lt;/a>```
    * [Исходный код тестов](tests/md2html/Md2HtmlLinkTest.java)
 * *Mark*
    * Добавьте поддержку `~выделения цветом~`: `<mark>выделения цветом</mark>`
 * *Image*
    * Добавьте поддержку ```![картинок](http://www.ifmo.ru/images/menu/small/p10.jpg)```:
        ```&lt;img alt='картинок' src='http://www.ifmo.ru/images/menu/small/p10.jpg'&gt;```

Исходный код тестов: [Md2HtmlTest.java](tests/md2html/Md2HtmlTest.java)


## Домашнее задание 7. Разметка

Модификации
 * *HTML*
    * Дополнительно реализуйте метод `toHtml`, генерирующий HTML-разметку:
      * выделеный текст окружается тегом `em`;
      * сильно выделеный текст окружается тегом `strong`;
      * зачеркнутый текст окружается тегом `s`.
 * *HTML списки*
    * Добавьте поддержку:
      * Нумерованных списков (класс `OrderedList`, тег `ol`): последовательность элементов
      * Ненумерованных списков (класс `UnorderedList`, тег `ul`): последовательность элементов
      * Элементов списка (класс `ListItem`, тег `li`): последовательность абзацев и списков
    * Для новых классов поддержка Markdown не требуется
 * *TeX*
    * Дополнительно реализуйте метод `toTex`, генерирующий TeX-разметку:
      * выделеный текст заключается в `\emph{` и `}`;
      * сильно выделеный текст заключается в `\textbf{` и `}`;
      * зачеркнутый текст заключается в `\textst{` и `}`.
    * [Исходный код тестов](tests/markup/TexTest.java)
 * *Tex списки*
    * Добавьте поддержку:
      * Нумерованных списков (класс `OrderedList`, окружение `enumerate`): последовательность элементов
      * Ненумерованных списков (класс `UnorderedList`, окружение `itemize`): последовательность элементов
      * Элементов списка (класс `ListItem`, тег `\item`: последовательность абзацев и списков
    * Для новых классов поддержка Markdown не требуется
    * [Исходный код тестов](tests/markup/TexListTest.java)


Исходный код тестов:

 * [MarkdownTest.java](tests/markup/MarkdownTest.java)
 * [AbstractTest.java](tests/markup/AbstractTest.java)


## Домашнее задание 6. Подсчет слов++

Модификации
 * *LineIndex*
    * В выходном файле слова должны быть упорядочены в лексикографическом порядке
    * Вместо номеров вхождений во всем файле надо указывать
      `<номер строки>:<номер в строке>`
    * Класс должен иметь имя `WordStatLineIndex`
 * *FirstIndex*
    * Вместо номеров вхождений во всем файле надо указывать
      только первое вхождение в каждой строке
    * Класс должен иметь имя `WordStatFirstIndex`
 * *LastIndex*
    * Вместо номеров вхождений во всем файле надо указывать
      только последнее вхождение в каждой строке
    * Класс должен иметь имя `WordStatLastIndex`
    * [Исходный код тестов](tests/wordStat/WordStatLastIndexTest.java)
 * *SortedLastIndex*
    * В выходном файле слова должны быть упорядочены в лексикографическом порядке
    * Вместо номеров вхождений во всем файле надо указывать
      только последнее вхождение в каждой строке
    * Класс должен иметь имя `WordStatSortedLastIndex`

Исходный код тестов:

* [WordStatIndexTest.java](tests/wordStat/WordStatIndexTest.java)
* [WordStatIndexChecker.java](tests/wordStat/WordStatIndexChecker.java)


## Домашнее задание 5. Свой сканнер

Модификации
 * *Transpose*
    * Рассмотрим входные данные как (не полностью определенную) матрицу,
      выведите ее в транспонированном виде
    * Класс должен иметь имя `ReverseTranspose`
 * *Sort*
    * Строки должны быть отсортированы по сумме в обратном порядке
      (при равенстве сумм – в порядке обратном следованию во входе).
      Числа в строке так же должны быть отсортированы в обратном порядке.
 * *Min*
    * Рассмотрим входные данные как (не полностью определенную) матрицу,
      вместо каждого числа выведите минимум из чисел в его столбце и строке
    * Класс должен иметь имя `ReverseMin`
    * [Исходный код тестов](tests/reverse/FastReverseMinTest.java)

Исходный код тестов:

* [FastReverseTest.java](tests/reverse/FastReverseTest.java)


## Домашнее задание 4. Подсчет слов

Модификации
 * *Words*
    * В выходном файле слова должны быть упорядочены в лексикографическом порядке
    * Класс должен иметь имя `WordStatWords`
 * *Sort*
    * Пусть _n_ – число слов во входном файле,
      тогда программа должна работать за O(_n_ log _n_).
 * *Count*
    * В выходном файле слова должны быть упорядочены по возрастанию числа
      вхождений, а при равном числе вхождений – по порядку первого вхождения
      во входном файле
    * Класс должен иметь имя `WordStatCount`
    * [Исходный код тестов](tests/wordStat/WordStatCountTest.java)

Исходный код тестов:

* [WordStatInputTest.java](tests/wordStat/WordStatInputTest.java)
* [WordStatChecker.java](tests/wordStat/WordStatChecker.java)


## Домашнее задание 3. Реверс

Модификации
 * *Even*
    * Выведите только четные числа (в реверсивном порядке)
    * Класс должен иметь имя `ReverseEven`
 * *Linear*
    * Пусть _n_ – сумма числа чисел и строк во входе,
      тогда программе разрешается потратить не более 5_n_+O(1) памяти
 * *Sum*
    * Рассмотрим входные данные как (не полностью определенную) матрицу,
      вместо каждого числа выведите сумму чисел в его столбце и строке
    * Класс должен иметь имя `ReverseSum`
    * [Исходный код тестов](tests/reverse/ReverseSumTest.java)

Исходный код тестов:

* [ReverseTest.java](tests/reverse/ReverseTest.java)
* [ReverseChecker.java](tests/reverse/ReverseChecker.java)
* [Базовые классы](base/)
