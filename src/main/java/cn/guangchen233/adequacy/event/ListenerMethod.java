package cn.guangchen233.adequacy.event;

import cn.guangchen233.adequacy.Adequacy;
import cn.guangchen233.adequacy.event.annotations.EventHandler;
import cn.guangchen233.adequacy.event.bases.BaseEvent;
import cn.guangchen233.adequacy.event.interfaces.Listenable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ListenerMethod {
    private final Method method;
    private final EventHandler eventHandler;
    private final Listenable eventListener;
    private final Class<BaseEvent> eventClass;

    public ListenerMethod(Method method, EventHandler eventHandler, Listenable eventListener, Class<BaseEvent> eventClass) {
        this.method = method;
        this.eventHandler = eventHandler;
        this.eventListener = eventListener;
        this.eventClass = eventClass;
    }

    public Method getMethod() {
        return method;
    }

    public EventHandler getEventHandler() {
        return eventHandler;
    }

    public Listenable getEventListener() {
        return eventListener;
    }

    public Class<BaseEvent> getEventClass() {
        return eventClass;
    }

    public void invoke(BaseEvent event) {
        try {
            this.getMethod().setAccessible(true);
            this.getMethod().invoke(this.eventListener, event);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
