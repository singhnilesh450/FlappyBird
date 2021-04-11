package com.singh.flappybirdgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Flappy extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture toptube;
	Texture bottomtube;
	Texture birds[];
	ShapeRenderer shapeRenderer;
	Circle birdCircle;
	Rectangle[] toppipeReactangle;
	Rectangle[] bottompipeReactangle;
	int flapstate=0;
	int pause=0;
	float birdY;
     int gravity=2;
	float velocity=0;
	int gamestate=0;
    float gap=400;

    float maxtubeofset;
    Random randomGenerator;
	int  maxnooftube=4;
    float[] tubeX=new float[maxnooftube];
	float tubeofset[]=new float[maxnooftube];
    float tubevelocity=4;

    float distbetweentubes;
	@Override
	public void create () {
		batch = new SpriteBatch();
        background=new Texture("bg.png");
		birds=new Texture[2];
		birds[0]=new Texture("bird.png");
		birds[1]=new Texture("bird2.png");

		birdY=(Gdx.graphics.getHeight()/2-birds[0].getHeight()/2);
        shapeRenderer=new ShapeRenderer();
        birdCircle=new Circle();
        toppipeReactangle=new Rectangle[maxnooftube];
		bottompipeReactangle=new Rectangle[maxnooftube];
		toptube=new Texture("toptube.png");
		bottomtube=new Texture("bootomtube.png");
		maxtubeofset=Gdx.graphics.getHeight()/2-gap/2-100;
		randomGenerator=new Random();
		distbetweentubes=Gdx.graphics.getWidth()/2;

		for(int i=0;i<maxnooftube;i++){
			tubeofset[i]=(float) ((randomGenerator.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-(2.4)*gap-200));
			tubeX[i]=Gdx.graphics.getWidth()/2-toptube.getWidth()/2+Gdx.graphics.getWidth()+i*distbetweentubes;

			toppipeReactangle[i]=new Rectangle();
			bottompipeReactangle[i]=new Rectangle();

		}
	}
/*oppipeReactangle.set(tubeX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeofset[i]);
			bottompipeReactangle.set(tubeX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - bottomtube.getHeight() + tubeofset[i]);*/
	@Override
	public void render () {
		batch.begin();
		batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		if(gamestate!=0) {


			if(Gdx.input.justTouched()){
				velocity=-30;


			}
			for(int i=0;i<maxnooftube;i++) {
				if(tubeX[i]<-Gdx.graphics.getWidth()/2){
					tubeofset[i]=(float) ((randomGenerator.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-(2.4)*gap-200));
					tubeX[i]+=maxnooftube*distbetweentubes;
				}else{
					tubeX[i] -= 4;
				}

				batch.draw(toptube, tubeX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeofset[i]);
				batch.draw(bottomtube, tubeX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - bottomtube.getHeight() + tubeofset[i]);

				toppipeReactangle[i]=new Rectangle(tubeX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeofset[i],toptube.getWidth(),toptube.getHeight());
				bottompipeReactangle[i]=new Rectangle( tubeX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - bottomtube.getHeight() + tubeofset[i],bottomtube.getWidth(),bottomtube.getHeight());
			}
			if(birdY>0 || velocity<0) {
				velocity = velocity + gravity;
				birdY -= velocity;//ssdd

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

        birdCircle.set(Gdx.graphics.getWidth()/2,birdY+birds[flapstate].getHeight()/2,birds[flapstate].getWidth()/2);


        /*shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(birdCircle.x,birdCircle.y,birdCircle.radius);*/
        for(int i=0;i<maxnooftube;i++){
			/*shapeRenderer.rect(tubeX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeofset[i],toptube.getWidth(),toptube.getHeight());
			shapeRenderer.rect( tubeX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - bottomtube.getHeight() + tubeofset[i],bottomtube.getWidth(),bottomtube.getHeight());
*/
			if(Intersector.overlaps(birdCircle,toppipeReactangle[i])  || Intersector.overlaps(birdCircle,bottompipeReactangle[i])){
              Gdx.app.log("Collision","Yes");
			}
		}
        shapeRenderer.end();
	}
	
	/*@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}*/
}
