import java.util.*;

/**
 * Created by alexeypavlenko on 10/04/2017.
 */
public class MyArrayList<E> {

//  Начальное значение элементов в массиве
    private static final int CAPACITY = 10;

//  Начальные значения, когда еще ничего не создано
    private static final Object[] CAPACITY_ELEMENTDATA = {};

//  Размер нашего массива будет хранится в этой переменной
    private int size;


//  Тот самый массив
    Object[] elementData;

//  Конструктор по-умолчанию при создании экземляра класса создатся массив объектов из 10 элементов
    public MyArrayList() {

        this.elementData = (E[]) new Object[CAPACITY];

    }

//  Вызываем конструктор со своим размером
    public MyArrayList(int capacity) {

        if (capacity >= 0) {
            this.elementData = new Object[capacity];
        }
        else {
            throw new IllegalArgumentException("MyArrayList(int capacity)");
        }

    }


//                  .add(value)
//    -----------------------------------------------

//  Операция добавления элемента
    public boolean add (E a) {

        if (size == elementData.length) {

            increase();

        }

//      Добавляем элемент в конец массива
        elementData[size++] = a;

//      Операция прошла успешно
        return true;

    }


//  Если места в массиве не достаточно, новая емкость рассчитывается по формуле (oldCapacity * 3) / 2 + 1, точнее увеличение массива исходного будет примерно в 1,5 раза
    private void increase() {

        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);


        E[] array = (E[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, array, 0, size);
        elementData = array;


    }

//  проверка выхода за границу массива
    private void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }


//        .add(index, Element) - если у нас в массиве не будет хватать места, то System.arraycopy вызовется 2 раза
//    ------------------------------------------------------------------------------------------------------------------

    public void add(int index, E element) {

        checkIndex(index);

//      Подготавливаем место для нового элемента
        System.arraycopy(elementData, index, elementData, index + 1, size - index);

//      Переписываем значеие у элемента по указанному индексу
        elementData[index] = element;
        size++;

    }


//           .remove(index) - при удалении текущая длина коллекции не изменяется, следовательно возможна утечка памяти => пригодится trimToSize
//    ---------------------------------------------------------------------------------------------------------------------------------------------


//  функция удаления по индексу
    private void delete(int index) {

//      Определяем какое количество элементов надо скопировать
        int numMoved = size - index - 1;

//      Копируем элементы
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }

//      Уменьшаем размер массива
        elementData[--size] = null;

    }


//  Зачем нам возвращать жлемент, который мы удаляем ?!
    public E remove(int index) {

        checkIndex(index);

        E value = (E) elementData[index];

        delete(index);

        return value;

    }

//          .remove(Object o) - удаление объекта коллекции
//    ---------------------------------------------------------


    public boolean remove(Object o) {

        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    delete(i);
                    return true;
                }
            }
        }
        else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    delete(i);
                    return true;
                }
            }
        }

        return false;
    }

//               .size() - размер коллекции
//    -----------------------------------------------

    public int size(){
        return size;
    }


//               .get() - получение элемента коллекции
//    ---------------------------------------------------------

    public E get(int index) {

        checkIndex(index);

        return (E) elementData[index];
    }


//               .set() - замена элемента коллекции
//    ---------------------------------------------------------

//  Зачем нам возвращать жлемент, который мы удаляем ?!
    public E set(int index, E element) {

        checkIndex(index);

        E value = (E) elementData[index];

        elementData[index] = element;

        return value;
    }

//                   .indexOf() - нахождение индекса элемента коллекции
//    ------------------------------------------------------------------------

    public int indexOf(E value) {
        if (value == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        }
        else {
            for (int i = 0; i < size; i++) {
                if (value.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

//                       .clear()
//    ------------------------------------------------------------------------

    public void clear() {

        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }

        size = 0;

    }

}
