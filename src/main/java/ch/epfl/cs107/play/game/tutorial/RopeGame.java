package ch.epfl.cs107.play.game.tutorial;

import java.awt.Color;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.RopeConstraintBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;


public class RopeGame implements Game {
	private ImageGraphics graphicsblock;
	private ShapeGraphics graphicsBall;
	private float ballRadius = 0.6f;
	private float blockWidth = 1.0f;
	private float blockHeight = 1.0f;


    private Window window;
  
    private World world;

    private Entity block;
    private Entity ball;
    

    
    
    public boolean begin(Window window, FileSystem fileSystem) {
        
        
        this.window = window;
        world = new World();
        world.setGravity(new Vector(0.0f,-9.81f));
        EntityBuilder blockBuilder = world.createEntityBuilder();
        blockBuilder.setFixed(true);
        blockBuilder.setPosition(new Vector(1.0f,0.5f));
        block =blockBuilder.build();
        EntityBuilder ballBuilder = world.createEntityBuilder();
        ballBuilder.setFixed(false);
        ballBuilder.setPosition(new Vector(0.6f,4.0f));
        ball=ballBuilder.build();
     // At this point , your body is in the world , but it has no geometry
     // You need to use another builder to add shapes
     PartBuilder partBuilder = block.createPartBuilder() ;
     // Create a square polygon , and set the shape of the builder to
     
     Polygon polygon = new Polygon(
     new Vector(0.0f, 0.0f),
     new Vector(1.0f, 0.0f),
     new Vector(1.0f, 1.0f),
     new Vector(0.0f, 1.0f)
     ) ;
     
     partBuilder.setShape(polygon) ;
     // Finally , do not forget the following line.
     partBuilder.setFriction(0.5f);
     partBuilder.build() ;
     // Note : we do not need to keep a reference on partBuilder
     PartBuilder partBuilder2 = ball.createPartBuilder();
     Circle circle = new Circle(ballRadius);
     partBuilder2.setShape(circle) ;
     partBuilder2.build() ;
        graphicsblock = new ImageGraphics("stone.broken.4.png",1, 1);
        graphicsblock.setAlpha(1.0f);
        graphicsblock.setDepth(0f);
        graphicsBall = new ShapeGraphics(circle, Color.BLUE,Color.RED,.1f,1.f,0);
        graphicsBall.setAlpha(1.0f);
        graphicsBall.setDepth(0f);
        graphicsblock.setParent(block);
        graphicsBall.setParent(ball);
        
        
        RopeConstraintBuilder ropeConstraintBuilder =
        		world.createRopeConstraintBuilder() ;
        		ropeConstraintBuilder.setFirstEntity(block) ;
        		ropeConstraintBuilder.setFirstAnchor(new Vector(blockWidth/2,
        		blockHeight/2)) ;
        		ropeConstraintBuilder.setSecondEntity(ball) ;
        		ropeConstraintBuilder.setSecondAnchor(Vector.ZERO) ;
        		ropeConstraintBuilder.setMaxLength(6.0f) ;
        		ropeConstraintBuilder.setInternalCollision(true) ;
        		ropeConstraintBuilder.build() ;
        return true;
    }

  
    public void update(float deltaTime) {
        
   
    	world.update(deltaTime) ;
    	
    	window.setRelativeTransform(Transform.I.scaled(10.0f)) ;

    	
    	graphicsblock.draw(window) ;
    	graphicsBall.draw(window);
    }

    public void end() {

    }
}