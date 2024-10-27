package ch.epfl.cs107.play.game.tutorial;

import com.sun.glass.events.KeyEvent;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.RevoluteConstraintBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;


public class ScaleGame implements Game {
	private ImageGraphics graphicsblock;
	private ImageGraphics graphicsBall;
	private ImageGraphics graphicsPlank;
	private float ballRadius = 0.5f;
	private float blockWidth = 10.0f;
	private float blockHeight = 1.0f;
	private float plankWidth =5f;
	private float plankHeight=0.2f;


    private Window window;
  
    private World world;

    private Entity block;
    private Entity ball;
    private Entity plank;
    

    
    
    public boolean begin(Window window, FileSystem fileSystem) {
        
        
        this.window = window;
        world = new World();
        world.setGravity(new Vector(0.0f,-9.81f));
        EntityBuilder blockBuilder = world.createEntityBuilder();
        blockBuilder.setFixed(true);
        blockBuilder.setPosition(new Vector(-5.0f,-1.0f));
        block =blockBuilder.build();
        EntityBuilder ballBuilder = world.createEntityBuilder();
        ballBuilder.setFixed(false);
        ballBuilder.setPosition(new Vector(0.6f,4.0f));
        ball=ballBuilder.build();
        EntityBuilder plankBuilder = world.createEntityBuilder();
        plankBuilder.setFixed(false);
        plankBuilder.setPosition(new Vector(-2.5f,0.8f));
        plank =plankBuilder.build();
        
     // At this point , your body is in the world , but it has no geometry
     // You need to use another builder to add shapes
     PartBuilder partBuilderBlock = block.createPartBuilder() ;
     // Create a square polygon , and set the shape of the builder to
     
     Polygon polygonBlock = new Polygon(
     new Vector(0.0f, 0.0f),
     new Vector(10f, 0.0f),
     new Vector(10f, 1.0f),
     new Vector(0.0f, 1.0f)
     ) ;
     
     partBuilderBlock.setShape(polygonBlock) ;
     // Finally , do not forget the following line.
     partBuilderBlock.setFriction(2f);
     partBuilderBlock.build() ;
     // Note : we do not need to keep a reference on partBuilder
     
     Polygon polygonPlank = new Polygon(
    	     new Vector(0.0f, 0.0f),
    	     new Vector(5f, 0.0f),
    	     new Vector(5f, 0.2f),
    	     new Vector(0.0f, 0.2f)
    	     ) ;
     
     
     PartBuilder partBuilderPlank=plank.createPartBuilder();
     partBuilderPlank.setShape(polygonPlank);
     partBuilderPlank.setFriction(2);
     partBuilderPlank.build();
     PartBuilder partBuilderBall = ball.createPartBuilder();
     Circle circle = new Circle(ballRadius);
     partBuilderBall.setShape(circle) ;
     partBuilderBall.setFriction(2);
     partBuilderBall.build() ;
        graphicsblock = new ImageGraphics("stone.broken.4.png",10, 1);
        graphicsblock.setAlpha(1.0f);
        graphicsblock.setDepth(0f);
        graphicsBall = new ImageGraphics("explosive.11.png", 2.0f*ballRadius , 2.0f *
        		ballRadius , new Vector(0.5f, 0.5f));
        graphicsBall.setAlpha(1.0f);
        graphicsBall.setDepth(0f);
        graphicsPlank = new ImageGraphics("wood.3.png",5f, 0.2f);
        graphicsPlank.setAlpha(1.0f);
        graphicsblock.setDepth(0f);
        graphicsblock.setParent(block);
        graphicsBall.setParent(ball);
        graphicsPlank.setParent(plank);
        
        RevoluteConstraintBuilder revoluteConstraintBuilder =
        		world.createRevoluteConstraintBuilder() ;
        		
        		revoluteConstraintBuilder.setFirstEntity(block) ;
        		revoluteConstraintBuilder.setFirstAnchor(new Vector(blockWidth/2,
        		(blockHeight*7)/4)) ;
        		revoluteConstraintBuilder.setSecondEntity(plank) ;
        		revoluteConstraintBuilder.setSecondAnchor(new Vector(plankWidth/2,
        		plankHeight/2)) ;
        		revoluteConstraintBuilder.setInternalCollision(true) ;
        		revoluteConstraintBuilder.build() ;
        return true;
    }

  
    public void update(float deltaTime) {
    	
    	if (window.getKeyboard().get(KeyEvent.VK_LEFT).isDown()) {
    		ball.applyAngularForce(10.0f) ;
    	} else if (window.getKeyboard().get(KeyEvent.VK_RIGHT).isDown()) {
    		ball.applyAngularForce(-10.0f) ;
    		}
   
    	world.update(deltaTime) ;
    	
    	window.setRelativeTransform(Transform.I.scaled(10.0f)) ;

    	
    	graphicsblock.draw(window) ;
    	graphicsBall.draw(window);
    	graphicsPlank.draw(window);
    }

    public void end() {

    }
}