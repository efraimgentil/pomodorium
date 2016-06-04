package me.efraimgentil.pomodorium.service;

import me.efraimgentil.pomodorium.model.Pomodoro;

/**
 * Created by efraimgentil on 04/06/16.
 */
public interface PomodoroObserver {

    void receiveNewValue(Pomodoro pomodoro , String newTime, PomodoroEvent event);
}
