package ch.epfl.cs107.play.game.actor;

import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;



public class Crate extends GameEntity implements Actor{
	private ImageGraphics graphics;
	
	public Crate(ActorGame Game, boolean fixed,Vector position) {
		super(Game,fixed,position);
		graphics = new ImageGraphics("crate.3.png",1,1);
		graphics.setParent(this);
		Polygon square = new Polygon(
			     new Vector(0.0f, 0.0f),
			     new Vector(1.0f, 0.0f),
			     new Vector(1.0f, 1.0f),
			     new Vector(0.0f, 1.0f)
			     ) ;
		PartBuilder partBuilderCrate = this.getEntity().createPartBuilder() ;
		 partBuilderCrate.setShape(square);
	     partBuilderCrate.setFriction(2);
	     partBuilderCrate.build();
	}

	public  void update() {
	
	}




	@Override
	public void draw(Canvas canvas) {
		graphics.draw(canvas);
		// TODO Auto-generated method stub

	}
	public void destroy () {
		this.destroy();
		}
	
	}
	
