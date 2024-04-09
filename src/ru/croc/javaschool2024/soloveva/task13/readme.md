# Задача 13. Меню
### Задачу необходимо решить без использования рекордов


Вы служите при дворе короля Главным распорядителем пиров.
И ваша задача, чтобы все на этих пирах были довольны. Особенно король.

Основная ваша задача — это составление меню.

На дворцовой кухне работают повара. У каждого из них есть:
- идентификатор (у каждого повара уникальный)
- имя
- грейд (шеф, су-шеф, старший повар, младший повар, поварёнок).

Каждое блюдо имеет:
- название (у каждого блюда уникальное)
- текст рецепта
- набор необходимых ингредиентов (тут для простоты будем использовать строки)
- категорию (закуска, горячее или десерт)
- оценку от 0 до 100 по мнению короля
- оценку от 0 до 100 по мнению придворных

Для пиров вы составляете меню только из проверенных блюд, никаких экспериментов, так что оценки есть всегда.

Вы решаете написать класс, который позволил бы немного автоматизировать вашу работу.
Основная информация о сотрудниках кухни хранится в ```Map<Повар, Набор блюд>```, где пары хранят информацию
```Повар - блюда, которые он умеет готовить```
Иногда на кухне появляются новые повара, иногда повара увольняются, так что вам важно предусмотреть методы в вашем классе, которые бы позволили внести изменения такого рода.

Для составления меню вам требуется три параметра:
1. Список поваров, которые работают на кухне в этот день
2. Список продуктов, которых _нет_ на дворцовой кухне в данный момент
3. Максимальный размер меню

С помощью двух первых параметров вы отбираете из вышеуказанной HashMap блюда, которые кухня способна приготовить в текущих условиях.
После чего этот список сортируете по оценкам (сортировка блюд происходит следующим образом: сравниваем два блюда по оценке короля, если оценки одинаковые, то сравниваем оценки придворных. Это считается _естественным порядком_).
В качестве итогового меню возвращайте отсортированный по приоритету _набор_ блюд _не более указанного размера_.


Кроме того, иногда король капризничает и просит вас организовать меню с учётом особых требований (все блюда должны содержать клубнику в составе, все блюда должны иметь названия на “А”, только десерты и тд).
Требования каждый раз разные, так что вы решаете добавить метод, который учитывал бы предыдущие условия и дополнительно мог бы отфильтровать список с учётом требований, определённых в качестве предиката.

В main продемонстрируйте работу вашего класса. Сгенерируйте меню под разные запросы и выведите в консоль в читабельном виде.

### Доп. задание:
Дворцовые повара не сидят сложа руки и активно обучаются.
Часть поваров съездила в командировку на обучение и вам необходимо зафиксировать это в своей 
Добавьте метод, который бы позволил дополнить вашу мапу следующим образом: на вход метода получаем набор блюд и набор поваров, которые научились их готовить, после чего добавляем эти блюда к тем, которые указанные повара уже умеют готовить.

### Подсказки:

Валидируйте данные.


Набор блюд для меню должен содержать только уникальные записи. Так как итоговое меню вида:
```
Жареная картошка
Жареная картошка
Жареная картошка
Пирог с голубями
```
не понравится ни королю, ни гостям. Меню должно быть разнообразным по мере возможностей.

Для каждого повара, аналогично, имеет смысл хранить только уникальные блюда. То есть хранение пары K,V вида:
```Повар Аркадий - Пирог с голубями, Пирог с голубями, Пирог с голубями, Пирог с голубями, Булочка с корицей```
не слишком оптимально.

Поразмышляйте над тем, какие инструменты помогут вам выполнить указанные условия.
Учтите требования, которые необходимо выполнить, чтобы эти инструменты работали корректно.
Просмотрите, какие методы предоставляют классы, которые вы используете, вероятно, там найдутся те, которые позволяют красиво и лаконично выполнить некоторые задачи, с которыми вы столкнётесь.

Старайтесь избегать дублирования логики.