package ch.epfl.cs107.play.game.tutorial;
import java.awt.Color;
import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;
/**
 * Simple game, to show  the basic architecture
 */
public class ContactGame implements Game {
 // Store context
 private Window window;
 private BasicContactListener contactListener ;
 // We need our physics engine
 private World world;
 // And we need to keep references on our game objects
 private Entity block;
 private Entity ball;
 private ImageGraphics blockGraphics;
 private ShapeGraphics ballGraphics;
 // This event is raised when game has just started
 @Override
 public boolean begin(Window window, FileSystem fileSystem) {
  // Create physics engine
  world = new World();
  // Note that you should use meters as unit
  world.setGravity(new Vector(0.0f,-9.81f));
  // Store context
  this.window = window;
  //To create an object,you need to use a builder
  EntityBuilder entityBuilder = world.createEntityBuilder();
  //Make sure this does not move
  entityBuilder.setFixed(true);
  //This helps you define properties,like its initial location
  entityBuilder.setPosition(new Vector(-5.0f,-1.0f));
  //Once ready,the body can be built
  block = entityBuilder.build();
  // At this point , your body is in the world , but it has no geometry
  // You need to use another builder to add shapes
  PartBuilder partBuilder = block.createPartBuilder () ;
  // Create a square polygon , and set the shape of the builder to
  //this polygon
  Polygon polygon = new Polygon(
    new Vector (0.0f, 0.0f),
    new Vector (10.0f, 0.0f),
    new Vector (10.0f, 1.0f),
    new Vector (0.0f, 1.0f)
    ) ;
  partBuilder.setShape(polygon) ;
  // Finally , do not forget the following line.
  partBuilder.build () ;
  // Note : we do not need to keep a reference on partBuilder
  entityBuilder.setFixed(false);
  entityBuilder.setPosition(new Vector(0.0f,2.0f));
  ball =entityBuilder.build();
  PartBuilder partBuilderBall = ball.createPartBuilder () ;
  Circle circle = new Circle(0.5f);
  partBuilderBall.setShape(circle) ;
  // Finally , do not forget the following line.
  partBuilderBall.build () ;
  // Note : we do not need to keep a reference on partBuilder
  blockGraphics = new ImageGraphics("stone.broken.4.png",10,1);
  // Transparency can be chosen for each drawing(0.0-transparent,1.0-opaque)
  blockGraphics.setAlpha(1.0f);
  //Additionally, you can choose a depth when drawing
  //Therefore,you could draw behind what you have already done
  blockGraphics.setDepth(1.0f);
  blockGraphics.setParent(block);
  ballGraphics = new ShapeGraphics(circle,Color.BLUE,Color.BLUE,.1f,1,0);
  ballGraphics.setAlpha(1.0f);
  ballGraphics.setDepth(1.0f);
  ballGraphics.setParent(ball);
  contactListener = new BasicContactListener () ;
  ball.addContactListener(contactListener) ;
  // TO BE COMPLETED
  // Successfully initiated
  return true;
 }
 // This event is called at each frame
 @Override
 public void update(float deltaTime) {
  // Game logic comes here
  // Nothing to do,yet
  //Simulate physics
  world.update(deltaTime);
  //We must place the camera where we want
  //We will look at the origin(identity) and increase the view size a bit
  window.setRelativeTransform(Transform.I.scaled(10.0f));
  //We can render our scene now
  blockGraphics.draw(window);
  ballGraphics.draw(window);
  // The actual rendering will be done now, by the program loop
  // contactListener is associated to ball
  // contactListener.getEntities () returns the list of entities in
  //  collision with ball
  int numberOfCollisions = contactListener.getEntities ().size() ;
  if (numberOfCollisions > 0){
   ballGraphics.setFillColor(Color.RED) ;
  }
  ballGraphics.draw(window) ;
 }
 // This event is raised after game ends, to release additional resources
 @Override
 public void end() {
  // Empty on purpose, no cleanup required yet
 }
}