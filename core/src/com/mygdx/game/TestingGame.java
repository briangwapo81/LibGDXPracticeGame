package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class TestingGame implements Screen {

	final Drop game;
	OrthographicCamera camera;
	Texture droplets,bucket;
	com.badlogic.gdx.math.Rectangle bucketShape;

	Array<com.badlogic.gdx.math.Rectangle> raindrops;

	int score = 0;




	long lastDropTime;

	Sound waterdrop;
	Music bgAudio;

    public TestingGame(final Drop game) {
		this.game = game;
		camera = new OrthographicCamera();
		bucketShape = new com.badlogic.gdx.math.Rectangle();

		droplets = new Texture(Gdx.files.internal("drop.png"));
		bucket = new Texture(Gdx.files.internal("bucket.png"));

		waterdrop = Gdx.audio.newSound(Gdx.files.internal("waterdrop.wav"));
		bgAudio = Gdx.audio.newMusic(Gdx.files.internal("backgroundAudio.mp3"));

		raindrops = new Array<>();
		spawnRaindrop();
		camera.setToOrtho(false,800,480);

		bucketShape.x = 800 / 2 - 64 / 2;
		bucketShape.y = 20;
		bucketShape.width = 64;
		bucketShape.height = 64;

	}


	@Override
	public void show() {
		bgAudio.setLooping(true);
		bgAudio.play();


	}

	@Override
	public void render (float delta) {
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		game.font.draw(game.batch,"Score: " + score,840 / 2 - 100,400 / 2 + 180);
		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(),Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			bucketShape.x = (int) (touchPos.x - 64 / 2);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.A)) {
			bucketShape.x -= (int) (200 * Gdx.graphics.getDeltaTime());
		}

		if(bucketShape.x <= 0){
			bucketShape.x = 800-63;
		}

		if(bucketShape.x >= 800-60){
			bucketShape.x = 0;
		}

		if(Gdx.input.isKeyPressed(Input.Keys.D)){
			bucketShape.x += (int) (200 * Gdx.graphics.getDeltaTime());
		}

		if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();

		ScreenUtils.clear(0,0,2f,1);

		for (Iterator<com.badlogic.gdx.math.Rectangle> iter = raindrops.iterator(); iter.hasNext(); ) {
			com.badlogic.gdx.math.Rectangle raindrop = iter.next();
			raindrop.y -= (int) (200 * Gdx.graphics.getDeltaTime());
			if(raindrop.y + 64 < 0) iter.remove();
			if(raindrop.overlaps(bucketShape)) {
				waterdrop.play();
				score++;
				iter.remove();
			}
		}

		game.batch.draw(bucket,bucketShape.x,bucketShape.y);
		for(com.badlogic.gdx.math.Rectangle raindrop : raindrops){
			game.batch.draw(droplets,raindrop.x,raindrop.y);
		}
		camera.update();
		game.batch.end();
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
	public void dispose () {

		game.batch.dispose();
		bucket.dispose();
		droplets.dispose();
		bgAudio.dispose();
		waterdrop.dispose();
	}


	private void spawnRaindrop() {
		com.badlogic.gdx.math.Rectangle raindrop = new Rectangle();
		raindrop.x = MathUtils.random(0, 800-64);
		raindrop.y = 480;
		raindrop.width = 64;
		raindrop.height = 64;
		raindrops.add(raindrop);
		lastDropTime = TimeUtils.nanoTime();
	}




}
