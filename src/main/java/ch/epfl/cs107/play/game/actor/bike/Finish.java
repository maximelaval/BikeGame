package ch.epfl.cs107.play.game.actor.bike;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;



public class Finish extends GameEntity implements Actor{
	private ImageGraphics graphicsFinish;
	
	private BasicContactListener contactListener;
	public Finish(ActorGame Game, boolean fixed,Vector position) {
		super(Game,fixed,position);
		graphicsFinish = new ImageGraphics("flag.red.png",4,4);
		graphicsFinish.setParent(this);
		 Polygon polygon = new Polygon(
			     new Vector(0.0f, 0.0f),
			     new Vector(-1f, 0.0f),
			     new Vector(-1f, 10.0f),
			     new Vector(0.0f, 10.0f)
			     ) ;
		PartBuilder partBuilderFinish = this.getEntity().createPartBuilder() ;
		 partBuilderFinish.setShape(polygon);
	     partBuilderFinish.setFriction(2);
	     partBuilderFinish.setGhost(true);
	     partBuilderFinish.build();
	     
	     contactListener = new BasicContactListener () ;
	   getEntity().addContactListener(contactListener) ;
	     
	    
		}
	
	
		
	
	
	public BasicContactListener getContactListener () {
		return contactListener;
	}
	     
	     
	

	public  void update() {
		 
	}




	@Override
	public void draw(Canvas canvas) {
		graphicsFinish.draw(canvas);
		// TODO Auto-generated method stub

	}
	public void destroy () {
		this.destroy();
		}
	
	}
	
