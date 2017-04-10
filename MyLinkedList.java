/**
 * Created by alexeypavlenko on 10/04/2017.
 */
public class MyLinkedList<E> {

//  Размер списка
    private int size;

//  Указатель списка на начало
    Entry<E> first;

//  Указатель списка на конец
    Entry<E> last;

//
    public MyLinkedList() {

//        first = last;

    }

    //  С помощью класса Entry создаются новые элементы
    private static class Entry<E> {

        E element;
        Entry<E> next;
        Entry<E> prev;

        public Entry(E element, Entry<E> next, Entry<E> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

    }


//  Добавление нового элемента в коллекцию
    public boolean add(E element) {

//      Запоминаем указатель на конец
        Entry<E> Last = last;

//      1й шаг - создаем новый экземпляр класса Entry
        Entry<E> newEntry = new Entry<E>(element, null, Last);

        last = newEntry;

//      2й шаг - переопределяем указатели на предыдущий и следующий

        if (Last == null) {
            first = newEntry;
        }
        else {
            Last.next = newEntry;
        }


        size++;

        return true;
    }


//  функция проверки на выход за границы
    public void checkIndex(int index) {

        if (index < 0 | index >= size) {
            throw new IndexOutOfBoundsException("checkIndex(int index)");
        }

    }


//  Entry(index) - ищет элемент в списк с указанным индексом, чтобы перед ним вставить наш элемент в add(int index, E element)

    private Entry<E> entry(int index) {

        Entry<E> eEntry = first;

        if (index < (size >> 1)) {
            for (int i = 0; i <= index; i++) {
                eEntry = eEntry.next;
            }
        }
        else {
            for (int i = size; i > index; i--) {
                eEntry = eEntry.prev;
            }
        }

        return eEntry;

    }


//  Функция добавления элемента перед заданным
    public void addBefore(E element, Entry<E> entry) {

//      Запоминаем указатель на предыдущий
        Entry<E> prev = entry.prev;

//      1й шаг - создаем новый экземпляр класса Entry
        Entry<E> newEntry = new Entry<E>(element, entry, prev);

        entry.prev = newEntry;

//      2й шаг - переопределяем указатели на предыдущий и следующий

        if (prev == null) {
            first = newEntry;
        }
        else {
            prev.next = newEntry;
        }


        size++;

    }



//  Добавление нового элемента в середину коллекцию
    public void add(int index, E element) {

        checkIndex(index);

        if (index == size) {
            add(element);
        }
        else {
            addBefore(element,entry(index));
        }
    }



//  Размер коллекции
    public int size() {
        return size;
    }


//  Получение элемента коллекции
    public E get(int index) {

        checkIndex(index);
        return entry(index).element;

    }



}
