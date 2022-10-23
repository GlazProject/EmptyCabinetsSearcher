package ru.telegramBot.gm.dataContainer.components;


import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.security.KeyException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Контейнер данных, который позволяет хранить ассоциативные связи между названием компонента и самим экземпляром компонента
 */
public class ComponentsContainer {
    protected final Map<String, Component> dataComponents = new HashMap<>();

    public ComponentsContainer(){}

    public ComponentsContainer (@NotNull ComponentsContainer oldData){
        for (String component: oldData.dataComponents.keySet()){
            dataComponents.put(component, oldData.dataComponents.get(component));
        }
    }

    /**
     * Метод, который позволяет получить компонент контейнера
     *
     * @param name Имя компонента
     * @return Компонент нужного типа, либо null, если не найден компонент с заданным именем
     */
    @Nullable
    public <T extends Component> T getComponent(Class<?> componentCls) {
        Component component = dataComponents.get(name);
        if (component == null)
            return null;
        return (T)component;
    }

    /**
     * Метод, который позволяет добавить готовый компонент в контейнер
     *
     * @param name Имя, с которым ассоциируется добавляемый компонент
     * @param component Экземпляр компонента с данными
     */
    public void setComponent(Component component){
        dataComponents.put(name, component);
    }

    /**
     * Метод, который позволяет установить данные в контейнер по имени компонента
     *
     * @param name Имя компонента
     * @param value Значение, которое нужно сохранить
     * @exception KeyException, если в контейнере не существует компонента с заданным именем
     */
    public void setToComponent(String name, Object value) throws KeyException {
        Component component = dataComponents.get(name);
        if (component == null)
            throw new KeyException();
        component.set(value);
    }

    /**
     * Метод, который позволяет получить данные из контейнера по имени компонента
     *
     * @param name Имя компонента в контейнере
     * @return Данные установленного типа, либо null, если компонент с заданным именем не существует
     * @param <T> Тип данных, которые ожидается получить из контейнера
     */
    @Nullable
    public <T> T getFromComponent(String name) {
        Component component = dataComponents.get(name);
        if (component == null)
            return null;
        return (T)(component.get());
    }

    /**
     * Метод, который позволяет получить все названия компонентов в контейнере
     * @return Set из всех названий
     */
    public Set<String> getAllComponentsNames(){
        return dataComponents.keySet();
    }
}
