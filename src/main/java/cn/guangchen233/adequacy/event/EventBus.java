package cn.guangchen233.adequacy.event;

import cn.guangchen233.adequacy.event.annotations.EventHandler;
import cn.guangchen233.adequacy.event.bases.BaseEvent;
import cn.guangchen233.adequacy.event.interfaces.Cancellable;
import cn.guangchen233.adequacy.event.interfaces.Listenable;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {
    private final HashMap<Listenable, CopyOnWriteArrayList<ListenerMethod>> listenerClasses = new HashMap<>();

    public void registerListener(Listenable listener) {
        if (this.isRegistered(listener)) return;

        CopyOnWriteArrayList<ListenerMethod> listenerMethods = new CopyOnWriteArrayList<>();

        Method[] declaredMethods = listener.getClass().getDeclaredMethods();

        for (Method method : declaredMethods) {
            if (!method.isAccessible()) method.setAccessible(true);
            if (!method.isAnnotationPresent(EventHandler.class)) continue;

            Class<BaseEvent> eventClass = BaseEvent.class;
            Class<?> parameterType = method.getParameterTypes()[0];

            if (eventClass.isAssignableFrom(parameterType)) {
                @SuppressWarnings("unchecked")
                Class<BaseEvent> eventTarget = (Class<BaseEvent>) parameterType;

                listenerMethods.add(new ListenerMethod(
                        method,
                        method.getAnnotation(EventHandler.class),
                        listener,
                        eventTarget
                ));
            }

            listenerClasses.put(listener, listenerMethods);
        }
    }

    public boolean isRegistered(Listenable listener) {
        return listenerClasses.containsKey(listener);
    }

    public void postEvent(BaseEvent event) {
        for (Map.Entry<Listenable, CopyOnWriteArrayList<ListenerMethod>> entry: listenerClasses.entrySet()) {
            for (ListenerMethod method : entry.getValue()) {
                EventHandler eventHandler = method.getEventHandler();
                if (method.getEventClass() == event.getClass()) {
                    if (event instanceof Cancellable) {
                        Cancellable cancellableEvent = (Cancellable) event;
                        if (cancellableEvent.isCanceled() && eventHandler.ignoreCancel()) continue;
                    }
                    method.invoke(event);
                }
            }
        }
    }

    public void unregisterListener(Listenable listener) {
        if (this.isRegistered(listener)) {
            listenerClasses.remove(listener);
        }
    }
}
