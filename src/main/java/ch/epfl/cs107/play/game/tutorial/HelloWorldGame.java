package ch.epfl.cs107.play.game.tutorial;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;

/**
 * Simple game, to show basic the basic architecture
 */
public class HelloWorldGame implements Game {
	private ImageGraphics graphics;
	private ImageGraphics graphics2;

    // Store context
    private Window window;
    
    // We need our physics engine
    private World world;
    
    // And we need to keep references on our game objects
    private Entity body;
    

    // This event is raised when game has just started
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        
        // Store context
        this.window = window;
        world = new World();
        world.setGravity(new Vector(0.0f,-9.81f));
        EntityBuilder entityBuilder = world.createEntityBuilder();
        entityBuilder.setFixed(true);
        entityBuilder.setPosition(new Vector(1.f,1.5f));
        body =entityBuilder.build();
        graphics = new ImageGraphics("stone.broken.4.png",1, 1);
        graphics.setAlpha(1.0f);
        graphics.setDepth(0f);
        graphics2 = new ImageGraphics("bow.png",1,1);
        graphics2.setAlpha(1.0f);
        graphics2.setDepth(1f);
        graphics.setParent(body);
        graphics2.setParent(body);
        
        
       // TO BE COMPLETED
        // Successfully initiated
        return true;
    }

    // This event is called at each frame
    @Override
    public void update(float deltaTime) {
        
    	// Game logic comes here
    	// Nothing to do, yet
    	// Simulate physics
    	// Our body is fixed , though , nothing will move
    	world.update(deltaTime) ;
    	// We can render our scene now,
    	
    	// we must place the camera where we want
    	// We will look at the origin (identity) and increase the view size a bit
    	window.setRelativeTransform(Transform.I.scaled(10.0f)) ;

    	
    	graphics.draw(window) ;
    	graphics2.draw(window);
        // The actual rendering will be done now, by the program loop
    }

    // This event is raised after game ends, to release additional resources
    @Override
    public void end() {
        // Empty on purpose, no cleanup required yet
    }
    
}
