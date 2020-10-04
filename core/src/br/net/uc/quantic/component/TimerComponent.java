package br.net.uc.quantic.component;

import com.badlogic.ashley.core.Component;

public class TimerComponent implements Component {

    public long time = 0L;

    public TimerComponent (long time) {

        this.time = Math.abs(time);

    }

}
