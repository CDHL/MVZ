package test.basics;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DisplayAssemble{
	private final Color color = new Color(1, 1, 1, 1);
	private float x, y;
	float width, height;
	private float originX, originY;
	private float rotation;

	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	
	public DisplayAssemble () {
		sprites.add(new Sprite());
		setColor(1, 1, 1, 1);
	}

	public DisplayAssemble (Texture texture) {
		sprites.add(new Sprite(texture, 0, 0, texture.getWidth(), texture.getHeight()));
	}

	public DisplayAssemble (Texture texture, int srcWidth, int srcHeight) {
		sprites.add(new Sprite(texture, 0, 0, srcWidth, srcHeight));
	}

	public DisplayAssemble (Texture texture, int srcX, int srcY, int srcWidth, int srcHeight) {
		sprites.add(new Sprite(texture, srcX, srcY, srcWidth, srcHeight));
	}

	public DisplayAssemble (TextureRegion region) {
		sprites.add(new Sprite(region));
	}

	public DisplayAssemble (TextureRegion region, int srcX, int srcY, int srcWidth, int srcHeight) {
		sprites.add(new Sprite(region, srcX, srcY, srcWidth, srcHeight));
	}

	public DisplayAssemble (Sprite sprite) {
		sprites.add(sprite);
	}
	
	public Sprite add(Sprite sprite) {
		sprites.add(sprite);
		return sprite;
	}

	public void setBounds (Sprite sprite, float x, float y, float width, float height) {
		sprite.setBounds(x, y, width, height);
	}

	public void setBounds (int index, float x, float y, float width, float height) {
		sprites.get(index).setBounds(x, y, width, height);
	}

	public void setSize (Sprite sprite, float width, float height) {
		sprite.setSize(width, height);
	}

	public void setSize (int index, float width, float height) {
		sprites.get(index).setSize(width, height);
	}

	public void setRelativePosition (Sprite sprite, float x, float y) {
		sprite.translate(x - sprite.getX(), y - sprite.getY());
	}

	public void setRelativePosition (int index, float x, float y) {
		sprites.get(index).translate(x - sprites.get(index).getX(), y - sprites.get(index).getY());
	}

	public void setRelativeX (Sprite sprite, float x) {
		sprite.translateX(x - sprite.getX());
	}

	public void setRelativeX (int index, float x) {
		sprites.get(index).translateX(x - sprites.get(index).getX());
	}

	public void setRelativeY (Sprite sprite, float y) {
		sprite.translateY(y - sprite.getY());
	}

	public void setRelativeY (int index, float y) {
		sprites.get(index).translateY(y - sprites.get(index).getY());
	}

	public void setRelativeCenter (Sprite sprite, float x, float y) {
		sprite.setCenterX(x);
		sprite.setCenterY(y);
	}

	public void setRelativeCenter (int index, float x, float y) {
		sprites.get(index).setCenterX(x);
		sprites.get(index).setCenterY(y);
	}

	public void translateRelativeX (Sprite sprite, float xAmount) {
		sprite.translateX(xAmount);
	}

	public void translateRelativeX (int index, float xAmount) {
		sprites.get(index).translateX(xAmount);
	}

	public void translateRelativeY (Sprite sprite, float yAmount) {
		sprite.translateY(yAmount);
	}

	public void translateRelativeY (int index, float yAmount) {
		sprites.get(index).translateY(yAmount);
	}

	public void translateRelatively (Sprite sprite, float xAmount, float yAmount) {
		sprite.translate(xAmount, yAmount);
	}

	public void translateRelatively (int index, float xAmount, float yAmount) {
		sprites.get(index).translate(xAmount, yAmount);
	}

	public void setColor (Sprite sprite, Color tint) {
		sprite.setColor(tint);
	}

	public void setColor (int index, Color tint) {
		sprites.get(index).setColor(tint);
	}
	
	public void setColor (Color tint) {
		for(Sprite sprite:sprites)
		{
			sprite.setColor(tint);
		}
		color.set(tint);
	}

	public void setAlpha (Sprite sprite, float a) {
		sprite.setAlpha(a);
	}

	public void setAlpha (int index, float a) {
		sprites.get(index).setAlpha(a);
	}

	public void setAlpha (float a) {
		for(Sprite sprite:sprites)
		{
			sprite.setAlpha(a);
		}
		color.a = a;
	}


	public void setColor (Sprite sprite, float r, float g, float b, float a) {
		sprite.setColor(r, g, b, a);
	}

	public void setColor (int index, float r, float g, float b, float a) {
		sprites.get(index).setColor(r, g, b, a);
	}

	public void setColor (float r, float g, float b, float a) {
		for(Sprite sprite:sprites)
		{
			sprite.setColor(r, g, b, a);
		}
		color.set(r, g, b, a);
	}

	public void setPackedColor (float packedColor) {
		for(Sprite sprite:sprites)
		{
			sprite.setPackedColor(packedColor);
		}
		Color.abgr8888ToColor(color, packedColor);
	}

	public void setOrigin (float originX, float originY) {

		this.originX = originX;
		this.originY = originY;
		for(Sprite sprite:sprites)
		{
			sprite.setOrigin(this.originX - sprite.getX(), this.originY - sprite.getY());
		}
	}

	public void setRotation (float degrees) {
		this.rotation = degrees;
		for(Sprite sprite:sprites)
		{
			sprite.translate(x, y);
			sprite.setRotation(degrees);
			sprite.translate(-x, -y);
		}
	}

	public void rotate (float degrees) {
		if (degrees == 0) return;
		setRotation (rotation + degrees);
	}

	public void rotate90 (boolean clockwise) {

		for(Sprite sprite:sprites)
		{
			sprite.rotate90(clockwise);
		}
	}
	
	public void draw (Batch batch) {

		for(Sprite sprite:sprites)
		{
			sprite.translate(x, y);
			sprite.draw(batch);
			sprite.translate(-x, -y);
		}
	}

	public void draw (Batch batch, float alphaModulation) {
		for(Sprite sprite:sprites)
		{
			sprite.translate(x, y);
			sprite.draw(batch, alphaModulation);
			sprite.translate(-x, -y);
		}
	}

	public float getX () {
		return x;
	}

	public float getY () {
		return y;
	}

	public float getWidth () {
		return width;
	}

	public float getHeight () {
		return height;
	}

	public float getOriginX () {
		return originX;
	}

	public float getOriginY () {
		return originY;
	}
}
