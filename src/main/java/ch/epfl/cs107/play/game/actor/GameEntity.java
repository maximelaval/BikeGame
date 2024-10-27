package ch.epfl.cs107.play.game.actor;


import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;


public abstract class GameEntity {
private Entity entity;
private ActorGame Game;

protected Entity getEntity(){
return entity  ;
}
protected ActorGame getOwner(){
return Game;
}
public GameEntity(ActorGame Game, boolean fixed,Vector position){
	EntityBuilder entityBuilder = Game.getWorld().createEntityBuilder();
	this.Game =Game;
	 entityBuilder.setFixed(fixed);
	 entityBuilder.setPosition(position);
	 entity = entityBuilder.build();
	
	
}
public GameEntity(ActorGame Game, boolean fixed){
	this(Game, fixed, Vector.ZERO);
}
public void destroy(){
entity.destroy();
}
public Vector getVelocity () {
	return entity.getVelocity() ;
	
}
public Transform getTransform () { 
	return entity.getTransform() ;
}

}
