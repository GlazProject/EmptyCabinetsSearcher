package ru.telegramBot.gm.app.dataContainers;


import org.jetbrains.annotations.NotNull;
import ru.telegramBot.gm.app.dataComponents.Component;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

/**
 * Контейнер данных, который позволяет хранить ассоциативные связи между названием компонента и самим экземпляром компонента
 */
public class ComponentsContainer {
    protected final Set<Component> dataComponents = new HashSet<>();

    public ComponentsContainer() {
    }

    public ComponentsContainer(@NotNull ComponentsContainer oldData) {
        dataComponents.addAll(oldData.dataComponents);
    }

    /**
     * Метод, который позволяет получить компонент контейнера
     *
     * @return Компонент нужного типа, либо null, если не найден компонент с заданным именем
     */
    @Nullable
    public <T extends Component> T getComponent(Class<?> componentCls) {
        for (Component component : dataComponents) {
            if (component.getClass().equals(componentCls))
                return (T) component;
        }
        return null;
    }

    /**
     * Метод, который позволяет добавить готовый компонент в контейнер
     *
     * @param component Экземпляр компонента с данными
     */
    public void setComponent(Component component) {
        dataComponents.add(component);
    }
}
