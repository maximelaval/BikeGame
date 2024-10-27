package ch.epfl.cs107.play.game.actor.bike;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.WheelConstraint;
import ch.epfl.cs107.play.math.WheelConstraintBuilder;
import ch.epfl.cs107.play.window.Canvas;



public class Wheel extends GameEntity implements Actor{
	private ImageGraphics wheelGraphics;
	private WheelConstraint constraint;
	
	public Wheel(ActorGame Game, boolean fixed,Vector position) {
		super(Game,fixed,position);
		wheelGraphics = new ImageGraphics("explosive.11.png",2.0f*0.5f , 2.0f *
				0.5f , new Vector(0.5f, 0.5f));
		wheelGraphics.setParent(this);
		Circle circle = new Circle(0.5f) ;
		PartBuilder partBuilderWheel = getEntity().createPartBuilder() ;
		partBuilderWheel.setShape(circle);
		partBuilderWheel.setFriction(2);
		partBuilderWheel.build();
		
		
	}

	public  void update() {
	

	}




	@Override
	public void draw(Canvas canvas) {
		wheelGraphics.draw(canvas);

	}
	public void destroy () {
getEntity().destroy();
wheelGraphics.setAlpha(0f);

	}
	public void attach(Entity vehicle, Vector anchor, Vector axis) {
		WheelConstraintBuilder constraintBuilder =getOwner().createWheelConstraintBuilder() ;
		constraintBuilder.setFirstEntity(vehicle) ;
		// point d'ancrage du véhicule :
		constraintBuilder.setFirstAnchor(anchor) ;
		// Entity associée à la roue :
		constraintBuilder.setSecondEntity(getEntity()) ;
		// point d'ancrage de la roue (son centre) :
		constraintBuilder.setSecondAnchor(Vector.ZERO) ;
		// axe le long duquel la roue peut se déplacer :
		constraintBuilder.setAxis(axis) ;
		// fréquence du ressort associé
		constraintBuilder.setFrequency(3.0f) ;
		constraintBuilder.setDamping(0.5f) ;
		// force angulaire maximale pouvant être appliquée
		//à la roue pour la faire tourner :
		constraintBuilder.setMotorMaxTorque(10.0f) ;
		constraint = constraintBuilder.build() ;
		
	}
	
	public void detach() {
constraint.destroy();
	}
	public float getSpeed() {
Vector velocityVector=this.getVelocity();
float speed = velocityVector.getLength();
float angularSpeed=this.getEntity().getAngularVelocity();
return Math.abs(speed-angularSpeed);
	}
	public void power(float speed) {
 constraint.setMotorEnabled(true);
 constraint.setMotorSpeed(speed);
	}
	public void relax() {
 constraint.setMotorEnabled(false);
	}
}

