package ch.epfl.cs107.play.game.actor;
import java.util.ArrayList;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.WheelConstraintBuilder;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public abstract class ActorGame implements Game {
private Window window;
private FileSystem fileSystem ;
private ArrayList<Actor> actors = new ArrayList<Actor>();
private  World world;


// Viewport properties
private Vector viewCenter ;
private Vector viewTarget ;
private ch.epfl.cs107.play.math.Positionable viewCandidate ;
private static final float VIEW_TARGET_VELOCITY_COMPENSATION =
0.2f ;
private static final float VIEW_INTERPOLATION_RATIO_PER_SECOND =
0.1f ;
private static final float VIEW_SCALE = 20.0f ;

//begin method
public boolean begin(Window window, FileSystem fileSystem) {
this.window = window;
this.fileSystem = fileSystem;
world = new World();
world.setGravity(new Vector(0.0f,-9.81f));
this.viewCenter = Vector.ZERO;
this.viewTarget = Vector.ZERO;
return true;
}

//the getters 
public Keyboard getKeyboard (){
return window.getKeyboard () ;
}
public Canvas getCanvas (){
return window ;
}
public World getWorld () {
return world;
}
public void end(){
}


//update method
public void update(float deltaTime){
world.update(deltaTime);

       
        
        
        //updating actors
        for (Actor a :actors) {
        	a.update(deltaTime);
        }
      
     // Update expected viewport center
        if (viewCandidate != null) {
        viewTarget =
        viewCandidate.getPosition ().add(viewCandidate.getVelocity ()
        .mul(VIEW_TARGET_VELOCITY_COMPENSATION)) ;
        }
        // Interpolate with previous location
        float ratio = (float)Math.pow(VIEW_INTERPOLATION_RATIO_PER_SECOND ,
        deltaTime) ;
        viewCenter = viewCenter.mixed(viewTarget , 1.0f - ratio) ;
        // Compute new viewport
        Transform viewTransform =
        Transform.I.scaled(VIEW_SCALE).translated(viewCenter) ;
        window.setRelativeTransform(viewTransform) ; 
   
      //drawing actors
        for (Actor a :actors) {
        	a.draw(window);
        }
}
public void deleteActor(Actor actor){
actors.remove(actor); 
}
public void addActor ( Actor actor) {
	actors.add(actor);
}


public WheelConstraintBuilder createWheelConstraintBuilder() {
	WheelConstraintBuilder wheelConstraintBuilder = world.createWheelConstraintBuilder();
	return wheelConstraintBuilder ;
}
public void setViewCandidate(Positionable pos) {
	viewCandidate = pos;
}
}
