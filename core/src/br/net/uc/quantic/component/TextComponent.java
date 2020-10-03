package br.net.uc.quantic.component;

import com.badlogic.ashley.core.Component;

public class TextComponent implements Component {

    public String text;

    public TextComponent () {
        this.text = "";
    }

    public TextComponent (String text) {
        this.text = text;
    }

}
