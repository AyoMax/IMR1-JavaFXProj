package app.models;

import java.util.Observable;
import java.util.Observer;

public class Model extends Observable{
    public void addView(Observer vue) {
        addObserver(vue);
    }

    public void removeView(Observer vue) {
        deleteObserver(vue);
    }

    public void notifyViews() {
        setChanged();
        notifyObservers();
    }
}
