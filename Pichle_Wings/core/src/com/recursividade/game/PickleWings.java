package com.recursividade.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils; //importa
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

//criar obj
public class PickleWings extends ApplicationAdapter { //nossa class erdando da class da lgdx
	SpriteBatch batch; //tipo para reiderisar objetos
	Texture img, tPepino; //cria o obj
	private Sprite pepino;
	private float posX, posY;
	private final float velocity = 10;
	
	@Override //instanciar obj
	public void create () { //construtor, define e atribui os objs
		batch = new SpriteBatch();
		img = new Texture("fundo_preto.png");
		tPepino = new Texture("pepino.png"); //instancia obj
		pepino = new Sprite(tPepino); //é mais fácil movimentar um sprite do que uma textura
		posX = 0; posY = 0;
	}

	@Override
	public void render () { //
		this.movePepino();
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0); //desenhar os objs
		batch.draw(pepino,posX,posY);
		batch.end();

	}
	
	@Override
	public void dispose () { //
		batch.dispose();
		img.dispose();
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
}
// Gdx.graphics.getWidth()  - '1280' Pega de maneira dinamica o tamanho da janela
// pepino.getWidth() - '187' Pega de maneir automatizada o tamanho da instancia/imagem