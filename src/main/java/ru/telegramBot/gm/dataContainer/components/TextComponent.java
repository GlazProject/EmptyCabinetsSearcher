package ru.telegramBot.gm.dataContainer.components;

public class TextComponent extends Component {
    private String text;

    @Override
    public String get() {
        return text;
    }

    @Override
    public void set(Object value){
        if (!(value instanceof String)){
            throw new ClassCastException();
        }
        text = (String) value;
    }
}
