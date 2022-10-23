package ru.telegramBot.gm.dataContainer.components;

/**
 * Класс компонента контейнера данных
 */
public abstract class Component {
    /**
     * Метод, который позволяет получить данные из компонента
     * @return Данные, которые содержатся в компоненте
     */
    abstract public Object get();

    /**
     * Метод, который позволяет добавить (обновить) данные в компонент
     * @param value Значение установленного типа, которое необходимо записать в компонент
     * @exception ClassCastException, если переданное значение не является установленным типом
     */
    abstract public void set(Object value);
}
