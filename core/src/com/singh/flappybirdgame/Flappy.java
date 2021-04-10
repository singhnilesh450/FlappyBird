package com.singh.flappybirdgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Flappy extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture toptube;
	Texture bottomtube;
	Texture birds[];
	int flapstate=0;
	int pause=0;
	float birdY;
     int gravity=2;
	float velocity=0;
	int gamestate=0;
    float gap=400;

	@Override
	public void create () {
		batch = new SpriteBatch();
        background=new Texture("bg.png");
		birds=new Texture[2];
		birds[0]=new Texture("bird.png");
		birds[1]=new Texture("bird2.png");

		birdY=(Gdx.graphics.getHeight()/2-birds[0].getHeight()/2);

		toptube=new Texture("toptube.png");
		bottomtube=new Texture("bootomtube.png");
	}

	@Override
	public void render () {
		batch.begin();
		batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		if(gamestate!=0) {


			if(Gdx.input.justTouched()){
				velocity=-30;
			}

			batch.draw(toptube,Gdx.graphics.getWidth()/2-toptube.getWidth()/2,Gdx.graphics.getHeight()/2+gap/2);
			batch.draw(bottomtube,Gdx.graphics.getWidth()/2-bottomtube.getWidth()/2,Gdx.graphics.getHeight()/2-gap/2-bottomtube.getHeight());
			if(birdY>0 || velocity<0) {
				velocity = velocity + gravity;
				birdY -= velocity;
			}

		}else {
			if (Gdx.input.justTouched()) {
				gamestate = 1;
			}
		}





		if (pause < 6) {
			pause++;
		} else {
			pause = 0;
			if (flapstate == 0) {
				flapstate = 1;
			} else {
				flapstate = 0;
			}
		}
		/*f(flapstate==0)
			flapstate=1;
		else
			flapstate=0;*/


		batch.draw(birds[flapstate],Gdx.graphics.getWidth()/2-birds[flapstate].getWidth()/2,birdY);
        batch.end();
	}
	
	/*@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}*/
}
