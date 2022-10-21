package ru.telegramBot.gm.dataContainer.components;

import javax.annotation.Nullable;
import java.security.KeyException;
import java.util.HashMap;
import java.util.Map;

public class ComponentsContainer {
    protected final Map<String, Component> dataComponents;

    public ComponentsContainer (ComponentsContainer oldData){
        dataComponents = new HashMap<>();
        for (String component: oldData.dataComponents.keySet()){
            dataComponents.put(component, oldData.dataComponents.get(component));
        }
    }

    public ComponentsContainer (){
        dataComponents = new HashMap<>() {{
            put("text", new TextComponent());
        }};
    }

    /**
     * Метод, который позволяет получить компонент контейнера
     *
     * @param name Имя компонента. Доступны: text
     * @return Контейнер нужного типа
     */
    @Nullable
    public <T extends Component> T getComponent(String name) {
        Component component = dataComponents.get(name);
        if (component == null)
            return null;
        return (T)component;
    }

    public void setComponent(String name, Component component){
        dataComponents.put(name, component);
    }

    /**
     * Устанавливает значение в компонент контейнера данных
     *
     * @param name Имя компонента. Доступны: text
     * @param value Значение, которое нужно сохранить
     */
    public void setToComponent(String name, Object value) throws KeyException {
        Component component = dataComponents.get(name);
        if (component == null)
            throw new KeyException();
        component.set(value);
    }

    @Nullable
    public <T> T getFromComponent(String name) throws KeyException {
        Component component = dataComponents.get(name);
        if (component == null)
            throw new KeyException();
        return (T)(component.get());
    }
}
