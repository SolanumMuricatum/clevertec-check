Здравствуйте. В данной ветке находится тестовое задание №1.
Инструкция по запуску: запустить приложение можно по команде, которая описана в тестовом задании. Для этого в пакетах с файлами .java содержатся файлы .class. Программу по желанию можно запустить и через ide (для этого в самом начале класса CheckRunner я оставила тестовую строку с аргументами, которую нужно раскомментировать и поменять в аргументах конструктора класса Transfer args на str).
Также проект можно запустить из папки out. Для этого необходимо выполнить следующую команду в консоли: Set-Location ./out/product/check2 main.java.ru.clevertec.check.CheckRunner + аргументы (папка создастся только после запуска проекта)

В данном задании я использовала следующие паттерны:
1. Стратегия. Данный паттерн использовался для создания различных стратегий по конвертации данных из командной строки в необходимые поля объектов.
2. Декоратор. Данный паттерн использовался для создания операций-обёрток над листом продуктов (начисление скидки из-за wholesale product, discount card и т.д.)
3. Синглтон. Данный паттерн был необходим для реализации логики создания чека в виде класса CSVWorker.
В приложении также используется собственный класс исключения, который при выбросе исключения завершает выполнение программы для того, чтобы пользователь мог проверить свои данные и избежания null-ошибок.
Сущности реализуют интерфейсы.
Класс CheckRunner запускает main-метод.
В приложении предусмотрены проверки на некорректный ввод аргументов командной строки.
