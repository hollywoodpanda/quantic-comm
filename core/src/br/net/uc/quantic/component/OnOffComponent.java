package br.net.uc.quantic.component;

import com.badlogic.ashley.core.Component;

public class OnOffComponent implements Component {

    public boolean isOn;

    public OnOffComponent () {
        isOn = false;
    }

    public OnOffComponent (boolean on) {
        this.isOn = on;
    }

}
