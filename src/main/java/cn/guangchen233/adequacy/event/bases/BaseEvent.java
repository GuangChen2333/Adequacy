package cn.guangchen233.adequacy.event.bases;

public class BaseEvent {
    private final String eventName;

    public BaseEvent() {
        this.eventName = this.getClass().getSimpleName();
    }

    public BaseEvent(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }
}