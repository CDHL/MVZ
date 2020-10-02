package basics;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Frame extends Sprite {

	public enum Type
	{
		TEXTINFO,
		SPRITEIMG,
	}
	class Args
	{
		Type type;
		float x, y;
	}
	public class Textinfo extends Args
	{
		BitmapFont font;
		public CharSequence text;
		float r = 1, g = 1, b = 1, a = 1;
		Textinfo(CharSequence text, float x, float y, float r, float g, float b, float a)
		{
			this.text = text;
			this.x = x;
			this.y = y;
			this.r = r;
			this.g = g;
			this.b = b;
			this.a = a;
		}
	}
	public class Spriteimg extends Args
	{
		Sprite sprite;
		Color color = new Color(1, 1, 1, 1);
		Spriteimg(Texture texture)
		{
			this.sprite = new Sprite(texture);
		}
		public void setColor (float r, float g, float b, float a) {
			this.color.set(r, g, b, a);
		}
	}
	
	ArrayList<Args> args = new ArrayList<Args> ();
	//Box��width��heightָ���Ǻڿ�
	public Frame(Texture texture, float width, float height, float a)
	{
		super(texture);
		this.setSize(width, height);
		this.a = a;
	}
	public Textinfo add(CharSequence text, BitmapFont font, float x, float y, float r, float g, float b, float a)
	{
		Textinfo textinfo = new Textinfo(text, x, y, r, g, b, a);
		textinfo.type = Type.TEXTINFO;
		textinfo.font = font;
		args.add(textinfo);
		return textinfo;
	}
	public Textinfo update(Textinfo textinfo, CharSequence text)
	{
		textinfo.text = text;
		return textinfo;
	}
	public Textinfo update(Textinfo textinfo, float r, float g, float b, float a)
	{
		textinfo.r = r;
		textinfo.g = g;
		textinfo.b = b;
		textinfo.a = a;
		return textinfo;
	}
	public Spriteimg add(Texture texture, float x, float y, float width, float height)
	{
		Spriteimg spriteimg = new Spriteimg(texture);
		spriteimg.type = Type.SPRITEIMG;
		spriteimg.sprite.setPosition(x + this.getX() + 6, y + this.getY() + 6);
		spriteimg.sprite.setSize(width, height);
		args.add(spriteimg);
		return spriteimg;
	}
	public float a = 1;
	@Override
	public void draw(Batch batch)
	{
		//�滭����
		batch.setColor(1, 1, 1, a);
		batch.draw(this.getTexture(), this.getX(), this.getY(), 6, this.getHeight()+12);
		batch.draw(this.getTexture(), this.getX() + this.getWidth() + 6, this.getY(), 6, this.getHeight()+12);
		batch.draw(this.getTexture(), this.getX() + 6, this.getY(), this.getWidth(), 6);
		batch.draw(this.getTexture(), this.getX() + 6, this.getY() + this.getHeight() + 6, this.getWidth(), 6);
		//batch.draw(this.getTexture(), this.getX(), this.getY(), this.getWidth()+12, this.getHeight()+12);
		batch.setColor(0, 0, 0, a);
		batch.draw(this.getTexture(), this.getX()+6, this.getY()+6, this.getWidth(), this.getHeight());
		
		if(args.isEmpty()) return;
		for(Args arg: args)
		{
			batch.setColor(Color.WHITE);
			if(arg.type == Type.TEXTINFO)
			{
				Textinfo _arg = (Textinfo)arg;
				_arg.font.setColor(_arg.r, _arg.g, _arg.b, _arg.a);
				_arg.font.draw(batch, _arg.text, _arg.x + this.getX() + 6, _arg.y + this.getY() + 6);
			}
			else if(arg.type == Type.SPRITEIMG)
			{
				Spriteimg _arg = (Spriteimg)arg;
				_arg.sprite.setColor(_arg.color);
				_arg.sprite.draw(batch);
				batch.setColor(Color.WHITE);
			}
		}
		batch.setColor(Color.WHITE);
	}
}
