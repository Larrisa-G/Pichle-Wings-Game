package com.recursividade.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils; //importa
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import java.util.Iterator; //fazer loop com indereços ao invez de com os própios valores.


//criar obj
public class PickleWings extends ApplicationAdapter { //nossa class erdando da class da lgdx
	SpriteBatch batch; //tipo para reiderisar objetos
	Texture img, tPepino, tPicles, tBurguer; //cria o obj
	private Sprite pepino, picles, burguer;
	private float posX, posY, velocity, pos_x_picles, pos_y_picles;
	private Boolean attack;
	private Array<Rectangle> burguers;
	private long lastBurguerTime;
	
	@Override //instanciar obj
	public void create () { //construtor, define e atribui os objs
		batch = new SpriteBatch();
		img = new Texture("fundo_roxo.png");
		tPepino = new Texture("pepino_transparente.png"); //instancia obj
		pepino = new Sprite(tPepino); //é mais fácil movimentar um sprite do que uma textura
		posX = 0; posY = 0; velocity = 10;
		tPicles = new Texture("picles.png");
		picles = new Sprite(tPicles);
		pos_x_picles = posX; pos_y_picles = posY;
		attack = false;
		tBurguer = new Texture("hamburguer.png");
		burguers = new Array<Rectangle>();
		lastBurguerTime = 0;


	}

	@Override
	public void render () { // Desenhar na tela
		this.movePepino();
		this.movePicles();
		this.spawnBurguer();
		this.moveBurguers();
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0); //desenhar os objs
		batch.draw(picles, (pos_x_picles + pepino.getWidth()/2), (pos_y_picles + (pepino.getHeight() / 2) - 15 ));
		batch.draw(pepino,posX,posY);
		for(Rectangle burguer: burguers){
			batch.draw(tBurguer, burguer.x, burguer.y);
		}
		batch.end();

	}
	
	@Override
	public void dispose () { //
		batch.dispose();
		img.dispose();
		tPepino.dispose();
	}

	private void movePepino(){

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			if (posX < Gdx.graphics.getWidth() - pepino.getWidth()) {
				posX += velocity;
			}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			if(posX > 0){
				posX -= velocity;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)){
			if(posY < Gdx.graphics.getHeight() - pepino.getHeight()){
				posY += velocity;
			}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			if(posY > 0){
				posY -= velocity;
			}
		}
	}

	private void movePicles(){
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && !attack){
			attack = true;
			pos_y_picles = posY;
		}
		if (attack){
			if (pos_x_picles < Gdx.graphics.getWidth()){
				pos_x_picles += 20;
			}
			else{
				pos_x_picles = posX;
				attack = false;
			}
		}
		else {
			pos_x_picles = posX;
			pos_y_picles = posY;
		}

	}

	//criar vários hamburguers
	private void spawnBurguer(){										//onde começa e onde termina
		Rectangle burguer = new Rectangle(Gdx.graphics.getWidth(),MathUtils.random(0, Gdx.graphics.getHeight() - tBurguer.getHeight()), tBurguer.getWidth(), tBurguer.getHeight());
		burguers.add(burguer);
		lastBurguerTime = TimeUtils.nanoTime();
	}
	private void moveBurguers(){

		if (TimeUtils.nanoTime() - lastBurguerTime > 999999999) {
			this.spawnBurguer();
		}

		for(Iterator<Rectangle> iter = burguers.iterator(); iter.hasNext();){
			Rectangle burguer = iter.next();
			burguer.x -= (400 * Gdx.graphics.getDeltaTime());
			if(burguer.x + tBurguer.getWidth() < 0){
				iter.remove();
			}
		}
	}


}
// Gdx.graphics.getWidth()  - '1280' Pega de maneira dinamica o tamanho da janela
// pepino.getWidth() - '187' Pega de maneir automatizada o tamanho da instancia/imagem
