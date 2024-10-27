package ch.epfl.cs107.play.game.tutorial;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;


public class SimpleCrateGames implements Game {
	private ImageGraphics graphicsblock;
	private ImageGraphics graphicscrate;


    private Window window;
  
    private World world;

    private Entity block;
    private Entity crate;
    

    
    
    public boolean begin(Window window, FileSystem fileSystem) {
        
        
        this.window = window;
        world = new World();
        world.setGravity(new Vector(0.0f,-9.81f));
        EntityBuilder blockBuilder = world.createEntityBuilder();
        blockBuilder.setFixed(true);
        blockBuilder.setPosition(new Vector(1.0f,0.5f));
        block =blockBuilder.build();
        EntityBuilder crateBuilder = world.createEntityBuilder();
        crateBuilder.setFixed(false);
        crateBuilder.setPosition(new Vector(0.2f,4.0f));
        crate=crateBuilder.build();
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
     partBuilder.setFriction(0f);
     partBuilder.build() ;
     // Note : we do not need to keep a reference on partBuilder
     PartBuilder partBuilder2 = crate.createPartBuilder();
     partBuilder2.setShape(polygon) ;
     partBuilder2.setFriction(0.5f);
     partBuilder2.build() ;
        graphicsblock = new ImageGraphics("stone.broken.4.png",1, 1);
        graphicsblock.setAlpha(1.0f);
        graphicsblock.setDepth(0f);
        graphicscrate = new ImageGraphics("box.4.png",1,1);
        graphicscrate.setAlpha(1.0f);
        graphicscrate.setDepth(0f);
        graphicsblock.setParent(block);
        graphicscrate.setParent(crate);
        
        
    
        return true;
    }

  
    public void update(float deltaTime) {
        
   
    	world.update(deltaTime) ;
    	
    	window.setRelativeTransform(Transform.I.scaled(10.0f)) ;

    	
    	graphicsblock.draw(window) ;
    	graphicscrate.draw(window);
    }

    public void end() {

    }
}