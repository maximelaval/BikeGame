package ch.epfl.cs107.play.game.actor;

import java.awt.Color;

import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.window.Canvas;

public class Terrain extends GameEntity implements Actor{
	private ShapeGraphics graphicsTerrain ;
	
	 
public Terrain(ActorGame Game, boolean fixed) {
			super(Game, fixed);
			Polyline terrainShape = new Polyline(
					
					-1000.0f, -1000.0f,
					-1000.0f, 0.0f,
					0.0f, 0.0f,
					3.0f, 1.0f,
					8.0f, 1.0f,
					15.0f, 3.0f,
					16.0f, 3.0f,
					25.0f, 0.0f,
					35.0f, -5.0f,
					50.0f, -5.0f,
					55.0f, -4.0f,
					65.0f, 0.0f,
					6500.0f, -1000.0f);
			graphicsTerrain = new ShapeGraphics(terrainShape,Color.LIGHT_GRAY,Color.WHITE,.1f,1,0);
			graphicsTerrain.setParent(this.getEntity()); 
			
	
	PartBuilder partBuilderTerrain = this.getEntity().createPartBuilder() ;
	 partBuilderTerrain.setShape(terrainShape);
     partBuilderTerrain.setFriction(2);
     partBuilderTerrain.build();
}
public void draw(Canvas canvas) {
	graphicsTerrain.draw(canvas);
	
}
public void update() {
	
}


}