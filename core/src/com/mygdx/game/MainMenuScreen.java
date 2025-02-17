package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.TestingGame;

public class MainMenuScreen implements Screen {

    final Drop game;
    OrthographicCamera camera;

    public MainMenuScreen(final Drop game){
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false,800,480);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

        ScreenUtils.clear(0,0,0.2f,1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.font.draw(game.batch,"Welcome to Drop!!!",840 / 2 - 100,400 / 2 + 180);
        game.font.draw(game.batch,"Tap anywhere to begin!",840 / 2 - 120,400 / 2 - 150);
        game.batch.end();
        if(Gdx.input.isTouched()){
            game.setScreen(new TestingGame(game));
            dispose();
        }
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
