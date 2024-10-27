package ch.epfl.cs107.play.game.actor;

import java.awt.Color;

import com.sun.glass.events.KeyEvent;

import ch.epfl.cs107.play.game.actor.bike.Wheel;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Contact;
import ch.epfl.cs107.play.math.ContactListener;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Bike  extends GameEntity implements Actor{
    final float MAX_WHEEL_SPEED = 20.0f;
    private boolean hit = false;
    private boolean orientedRight = true ;
    private Wheel leftWheel;
    private Wheel rightWheel;
    private ShapeGraphics stickManHeadGraphics;
    private ShapeGraphics stickManArmGraphics;
    private ShapeGraphics stickManBackGraphics;
    private ShapeGraphics stickManUpLegGraphics;
    private ShapeGraphics stickManLeg1Graphics;
    private ShapeGraphics stickManLeg2Graphics;
    private ShapeGraphics stickManBodyGraphics;


    public Bike(ActorGame Game, boolean fixed, Vector position) {
        super(Game, fixed, position);

        leftWheel = new Wheel(Game,false,position.add(new Vector(-1.0f,0.f)));
        rightWheel = new Wheel(Game,false,position.add(new Vector(1.0f,0.f)));
        //Draw Head
        Circle head = new Circle(0.2f,getHeadLocation());
        //Draw Arm
       // Polyline arm = new Polyline ( getHandLocation(),getShoulderLocation());
       // Polyline back = new Polyline ( getShoulderLocation(),getBackLocation());
      //  Polyline upperLeg = new Polyline(getBackLocation(),getWaistLocation());
      //  Polyline leg1 = new Polyline(getWaistLocation(),getFoot1Location());
       // Polyline leg2 = new Polyline(getWaistLocation(),getFoot2Location());
        Polygon hitboxShape = new Polygon(

                0.0f, 0.5f,
                0.5f, 1.0f,
                0.0f, 2.0f,
                -0.5f, 1.0f
                ) ;



        PartBuilder partBuilderHitbox = getEntity().createPartBuilder() ;
        partBuilderHitbox.setShape(hitboxShape);
        partBuilderHitbox.setFriction(2);
        partBuilderHitbox.setGhost(false);
        partBuilderHitbox.build();


        leftWheel.attach(getEntity() , new Vector(-1.0f, 0.0f), new
                Vector(-0.5f, -1.0f)) ;
        rightWheel.attach(getEntity (), new Vector(1.0f, 0.0f), new
                Vector(0.5f, -1.0f)) ;

       stickManHeadGraphics = new ShapeGraphics(head,Color.YELLOW,Color.YELLOW,.1f,1,0);
        /**stickManArmGraphics = new ShapeGraphics(arm,Color.YELLOW,Color.YELLOW,.1f,1,0);
        stickManBackGraphics = new ShapeGraphics(back,Color.YELLOW,Color.YELLOW,.1f,1,0);
        stickManUpLegGraphics = new ShapeGraphics(upperLeg,Color.YELLOW,Color.YELLOW,.1f,1,0);
        stickManLeg1Graphics = new ShapeGraphics(leg1,Color.YELLOW,Color.YELLOW,.1f,1,0);
        stickManLeg2Graphics = new ShapeGraphics(leg2,Color.YELLOW,Color.YELLOW,.1f,1,0);
        stickManLeg2Graphics.setParent(getEntity());
   
        stickManArmGraphics.setParent(getEntity());
        stickManBackGraphics.setParent(getEntity());
        stickManUpLegGraphics.setParent(getEntity());
        stickManLeg1Graphics.setParent(getEntity());
        **/
        stickManBodyGraphics = new ShapeGraphics(getBody(),null,Color.YELLOW,.1f,1,0);
        stickManBodyGraphics.setParent(getEntity());
        stickManHeadGraphics.setParent(getEntity());
        
        ContactListener listener = new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                if (contact.getOther().isGhost())
                    return ;
                if (contact.getOther().equals(Wheel.class))// si contact avec les roues :
                    return ;
                hit = true ;
            }
            @Override
            public void endContact(Contact contact) {} 
        };

        this.getEntity().addContactListener(listener);

    }

    
    public void destroy() {            //destroy
		getEntity().destroy();
		leftWheel.destroy();
		rightWheel.destroy();
		stickManHeadGraphics.setAlpha(0f);
		/**stickManArmGraphics.setAlpha(0f);
		stickManBackGraphics.setAlpha(0f);
		stickManUpLegGraphics.setAlpha(0f);
		stickManLeg1Graphics.setAlpha(0f);
		stickManLeg2Graphics.setAlpha(0f);**/
		stickManBodyGraphics.setAlpha(0f);
		
	}
	public Entity getBikeEntity () {
		return getEntity();
		
	}

    
    
    public boolean getHit() {
    	return hit;
    }
    public void draw(Canvas canvas) {
        leftWheel.draw(canvas);
        rightWheel.draw(canvas);
        stickManHeadGraphics.draw(canvas);
        /**stickManArmGraphics.draw(canvas);
        stickManBackGraphics.draw(canvas);
        stickManUpLegGraphics.draw(canvas);
        stickManLeg1Graphics.draw(canvas);
        stickManLeg2Graphics.draw(canvas);**/
        stickManBodyGraphics.draw(canvas);
        ;

    }

    public void update() {


        if (this.getOwner().getKeyboard().get(KeyEvent.VK_SPACE).isPressed() && orientedRight) {
            orientedRight = false;

        }
        if(this.getOwner().getKeyboard().get(KeyEvent.VK_SPACE).isPressed() && !orientedRight){
            orientedRight = true;


        }
        if (this.getOwner().getKeyboard().get(KeyEvent.VK_DOWN).isDown() && orientedRight){
        	rightWheel.power(1.0f);leftWheel.power(1.0f);
            

        }

        
        
        
        if (this.getOwner().getKeyboard().get(KeyEvent.VK_UP).isDown() && orientedRight && leftWheel.getSpeed()> -MAX_WHEEL_SPEED){
            leftWheel.power(-MAX_WHEEL_SPEED);

        }
        if (this.getOwner().getKeyboard().get(KeyEvent.VK_UP).isDown() && !orientedRight && rightWheel.getSpeed()< MAX_WHEEL_SPEED){
            rightWheel.power(MAX_WHEEL_SPEED);

        }
        if (this.getOwner().getKeyboard().get(KeyEvent.VK_RIGHT).isDown() ){
            getEntity().applyAngularForce(-10.0f);

        }
        if (this.getOwner().getKeyboard().get(KeyEvent.VK_LEFT).isDown() ){
            getEntity().applyAngularForce(10.0f);
        }
        
        if (!((hit))) {
        stickManBodyGraphics = new ShapeGraphics(getBody(),null,Color.YELLOW,.1f,1,0);
        stickManBodyGraphics.setParent(getEntity());}
//if (hit) {
	//this.destroy();

//}

    }

    //Head location , in local coordinates
    private Vector getHeadLocation() {
        return new Vector(0.0f, 1.75f) ;
    }

    private Vector getShoulderLocation() {
        return new Vector(0.0f,1.5f);}

    private Vector getHandLocation() {
        return new Vector(0.5f,1.0f);
    }
    private Vector getBackLocation() {
        return new Vector (-0.4f,1.0f);
    }
    private Vector getWaistLocation() {
        return new Vector(0.0f,0.7f);
    }
    private Vector getFoot1Location() {
        return new Vector(0.25f,0.0f);
    }
    private Vector getFoot2Location() {
        return new Vector(-0.25f,0.0f);
    }
private Polyline getBody() {
	return  new Polyline(getHandLocation(),getShoulderLocation(),getBackLocation(),getWaistLocation(),getFoot1Location(),getWaistLocation(),getFoot2Location());

}

}

	

