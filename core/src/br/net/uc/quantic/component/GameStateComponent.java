package br.net.uc.quantic.component;

import com.badlogic.ashley.core.Component;

public class GameStateComponent implements Component {

    public enum GameState {

        PLAYING,
        LOOSE,
        WIN

    }

    public GameState state;

    public GameStateComponent () {

        this.state = GameState.PLAYING;

    }

    public GameStateComponent (GameState state) {

        this.state = state;

    }

}
