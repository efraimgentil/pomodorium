package me.efraimgentil.pomodorium.service;

import java.util.*;

import me.efraimgentil.pomodorium.model.Pomodoro;

/**
 * Created by efraimgentil on 04/06/16.
 */
public interface ServiceNotifier {

    void add(PomodoroObserver observer);
    void notifyEvent(Pomodoro pomodoro, String newTime, PomodoroEvent event);

}
