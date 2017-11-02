package observerPattern.observer;

public interface Observer {
    void update(Subject subject, Object hint);
}