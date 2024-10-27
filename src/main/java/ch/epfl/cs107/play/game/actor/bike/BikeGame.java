package ch.epfl.cs107.play.game.actor.bike;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.Bike;
import ch.epfl.cs107.play.game.actor.Crate;
import ch.epfl.cs107.play.game.actor.Terrain;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class BikeGame extends ActorGame {
	private Bike bike;
	private TextGraphics message;
	private Finish finish;
	private TextGraphics gameOver;
	public boolean begin(Window window,FileSystem fileSystem) {
		super.begin(window, fileSystem);

		Crate crate1 = new Crate(this,false,new Vector(0.0f,5.0f));
		Crate crate2 = new Crate(this,false,new Vector(0.2f,7.0f));
		Crate  crate3 = new Crate(this,false,new Vector(2.0f,6.0f));
		this.addActor(crate1);
		this.addActor(crate2);
		this.addActor(crate3);

		Terrain terrain = new Terrain(this,true);
		this.addActor(terrain);

		bike = new Bike(this,false,new Vector(4.0f,5.0f));
		this.addActor(bike);

		this.setViewCandidate(bike);
		finish = new Finish (this,true,new Vector(130.0f,-10.1f));
		this.addActor(finish);

		message = new TextGraphics("FINISH", 0.3f, Color.RED, Color.WHITE , 0.02f,
				true , false , new Vector(0.5f, 0.5f), 1.0f, 100.0f) ;
		message.setParent(getCanvas()) ;
		message.setRelativeTransform(Transform.I.translated(0.0f, -1.0f)) ;
		gameOver= new TextGraphics("GAME OVER", 0.3f, Color.RED, Color.WHITE , 0.02f,
				true , false , new Vector(0.5f, 0.5f), 1.0f, 100.0f) ;

		gameOver.setRelativeTransform(Transform.I.translated(0.0f, -1.0f)) ;
		gameOver.setParent(getCanvas()) ;
		return true;}


	public void update (float deltaTime) {
		bike.update();
		super.update(deltaTime);
		if(finish.getContactListener().hasContactWith(bike.getBikeEntity())) {        //getContactListener et getBikeEntity sont des m�thodes que 
			                                                                            // j'ai d�fini respectivement dans Finish.java et Bike.java
			message.draw(getCanvas());                                                               //mais il y a surement un moyen plus simple de s'y prendre.

		

		
		}
		if(bike.getHit()) {

			gameOver.draw(getCanvas());
			bike.destroy();
		}


	}

}



