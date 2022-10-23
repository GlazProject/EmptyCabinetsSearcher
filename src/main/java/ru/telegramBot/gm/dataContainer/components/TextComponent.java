package ru.telegramBot.gm.dataContainer.components;

/**
 * Текстовый компонент контейнера данных, который хранит данные типа String
 */
public class TextComponent extends Component {
    private String text;

    /**
     * Метод, который позволяет получить текст из компонента
     * @return Текст, котрый содержится в компоненте
     */
    @Override
    public String get() {
        return text;
    }

    /**
     * Метод, который позволяет добавить (обновить) текст в компонент
     * @param value Значение типа `String`, которое необходимо записать в компонет
     * @exception ClassCastException, если переданное значение не является типом String
     */
    @Override
    public void set(Object value){
        if (!(value instanceof String)){
            throw new ClassCastException();
        }
        text = (String) value;
    }
}
