package cn.guangchen233.adequacy.event.interfaces;

public interface Cancellable {
    boolean isCanceled();

    void cancel();
}
