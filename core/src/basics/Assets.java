package basics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Rectangle;

import util.Utils2;

public class Assets {

	public Texture zombie_layer_walking_Texture = new Texture("sprites/zombie_layer_walking.png");
	public Texture zombie_back_walking_Texture = new Texture("sprites/zombie_back_walking.png");
	public Texture zombie_walking_Texture = new Texture("sprites/zombie_walking.png");
	
	public Texture frisk_layer_walking_Texture = new Texture("sprites/frisk_layer_walking.png");
	public Texture frisk_back_walking_Texture = new Texture("sprites/frisk_back_walking.png");
	public Texture frisk_walking_Texture = new Texture("sprites/frisk_walking.png");
	
	public Texture zombie_layer_eating_Texture = new Texture("sprites/zombie_layer_eating.png");
	public Texture zombie_back_eating_Texture = new Texture("sprites/zombie_back_eating.png");
	public Texture zombie_eating_Texture = new Texture("sprites/zombie_eating.png");
	
	public Texture zombie_cone_back_Texture = new Texture("sprites/zombie_cone_back.png");
	public Texture zombie_cone_layer_Texture = new Texture("sprites/zombie_cone_layer.png");
	public Texture zombie_cone_Texture = new Texture("sprites/zombie_cone.png");
	
	public Texture zombie_bucket_back_Texture = new Texture("sprites/zombie_bucket_back.png");
	public Texture zombie_bucket_layer_Texture = new Texture("sprites/zombie_bucket_layer.png");
	public Texture zombie_bucket_Texture = new Texture("sprites/zombie_bucket.png");
	
	public Texture Twoswitchpuzzle_Texture = new Texture("sprites/Twoswitchpuzzle.png");
	public Texture ruins1_Texture = new Texture("sprites/ruins1.png");
	public Texture ruins2_Texture = new Texture("sprites/ruins2.png");
	public Texture ruins3_Texture = new Texture("sprites/ruins3.png");
	
	public Texture froggit_Texture = new Texture("sprites/froggit.png");
	public Texture froggit_bullet_Texture = new Texture("sprites/froggit_bullet.png");

	public Texture moldsmal_Texture = new Texture("sprites/moldsmal.png");
	public Texture moldsmal_bullet2_Texture = new Texture("sprites/moldsmal_bullet1.png");
	public Texture moldsmal_bullet1_Texture = new Texture("sprites/moldsmal_bullet2.png");

	public Texture rock_Texture = new Texture("sprites/rock.png");
	
	public Texture unit_Texture = new Texture("sprites/unit.png");
	
	public Texture white_circle_100X100_Texture = new Texture("sprites/white_circle_100X100.png");
	
	public Texture unit_alpha2_Texture = new Texture("sprites/unit_alpha2.png");
	
	public Texture whimsun_Texture = new Texture("sprites/whimsun.png");
	public Texture whimsun_bullet_Texture = new Texture("sprites/whimsun_bullet.png");
	
	public Texture loox_Texture = new Texture("sprites/loox.png");
	public Texture loox_bullet1_Texture = new Texture("sprites/loox_bullet3.png");
	public Texture loox_bullet2_Texture = new Texture("sprites/loox_bullet2.png");
	public Texture loox_bullet3_Texture = new Texture("sprites/loox_bullet1.png");

	public Texture migosp_Texture = new Texture("sprites/migosp.png");
	
	public BitmapFont font_DTM_Mono;
	
	public final Rectangle zombie_layer_left_walking_RRectangle;
	public final Rectangle zombie_layer_right_walking_RRectangle;
	public final Rectangle zombie_back_walking_RRectangle;
	public final Rectangle zombie_walking_RRectangle;

	public final Rectangle frisk_layer_left_walking_RRectangle;
	public final Rectangle frisk_layer_right_walking_RRectangle;
	public final Rectangle frisk_back_walking_RRectangle;
	public final Rectangle frisk_walking_RRectangle;
	
	public final Rectangle zombie_layer_left_eating_RRectangle;
	public final Rectangle zombie_layer_right_eating_RRectangle;
	public final Rectangle zombie_back_eating_RRectangle;
	public final Rectangle zombie_eating_RRectangle;
	
	public final Rectangle zombie_cone_layer_left_walking_RRectangle;
	public final Rectangle zombie_cone_layer_right_walking_RRectangle;
	public final Rectangle zombie_cone_back_walking_RRectangle;
	public final Rectangle zombie_cone_walking_RRectangle;
	
	public final Rectangle zombie_bucket_layer_left_walking_RRectangle;
	public final Rectangle zombie_bucket_layer_right_walking_RRectangle;
	public final Rectangle zombie_bucket_back_walking_RRectangle;
	public final Rectangle zombie_bucket_walking_RRectangle;
	
	public final Rectangle froggit_RRectangle;
	public final Rectangle froggit_bullet_RRectangle;
	
	public final Rectangle moldsmal_RRectangle;
	public final Rectangle moldsmal_bullet_RRectangle;

	public final Rectangle rock_RRectangle;
	
	public final Rectangle whimsun_RRectangle;
	public final Rectangle whimsun_bullet_RRectangle;

	public final Rectangle loox_RRectangle;
	public final Rectangle loox_bullet1_RRectangle;
	public final Rectangle loox_bullet2_RRectangle;
	public final Rectangle loox_bullet3_RRectangle;
	
	public final Rectangle migosp_RRectangle;
	
	public final Animation<TextureRegion> zombie_layer_left_walking_Animation = new Animation<TextureRegion>(.15F, Utils2.getFrames(zombie_layer_walking_Texture, 1, 4), Animation.PlayMode.LOOP);
	public final Animation<TextureRegion> zombie_back_walking_Animation = new Animation<TextureRegion>(.15F, Utils2.getFrames(zombie_back_walking_Texture, 1, 4), Animation.PlayMode.LOOP);
	public final Animation<TextureRegion> zombie_walking_Animation = new Animation<TextureRegion>(.15F, Utils2.getFrames(zombie_walking_Texture, 1, 4), Animation.PlayMode.LOOP);
	public final Animation<TextureRegion> zombie_layer_right_walking_Animation = new Animation<TextureRegion>(.15F, Utils2.getFrames(zombie_layer_walking_Texture, 1, 4), Animation.PlayMode.LOOP);
	
	public final Animation<TextureRegion> frisk_layer_left_walking_Animation = new Animation<TextureRegion>(.15F, Utils2.getFrames(frisk_layer_walking_Texture, 1, 4), Animation.PlayMode.LOOP);
	public final Animation<TextureRegion> frisk_back_walking_Animation = new Animation<TextureRegion>(.15F, Utils2.getFrames(frisk_back_walking_Texture, 1, 4), Animation.PlayMode.LOOP);
	public final Animation<TextureRegion> frisk_walking_Animation = new Animation<TextureRegion>(.15F, Utils2.getFrames(frisk_walking_Texture, 1, 4), Animation.PlayMode.LOOP);
	public final Animation<TextureRegion> frisk_layer_right_walking_Animation = new Animation<TextureRegion>(.15F, Utils2.getFrames(frisk_layer_walking_Texture, 1, 4), Animation.PlayMode.LOOP);
	
	public final Animation<TextureRegion> zombie_layer_left_eating_Animation = new Animation<TextureRegion>(.15F, Utils2.getFrames(zombie_layer_eating_Texture, 1, 4), Animation.PlayMode.LOOP);
	public final Animation<TextureRegion> zombie_back_eating_Animation = new Animation<TextureRegion>(.15F, Utils2.getFrames(zombie_back_eating_Texture, 1, 4), Animation.PlayMode.LOOP);
	public final Animation<TextureRegion> zombie_eating_Animation = new Animation<TextureRegion>(.15F, Utils2.getFrames(zombie_eating_Texture, 1, 4), Animation.PlayMode.LOOP);
	public final Animation<TextureRegion> zombie_layer_right_eating_Animation = new Animation<TextureRegion>(.15F, Utils2.getFrames(zombie_layer_eating_Texture, 1, 4), Animation.PlayMode.LOOP);
	
	public final Animation<TextureRegion> zombie_cone_layer_left_walking_Animation = new Animation<TextureRegion>(.15F, Utils2.getFrames(zombie_cone_layer_Texture, 1, 4), Animation.PlayMode.LOOP);
	public final Animation<TextureRegion> zombie_cone_layer_right_walking_Animation = new Animation<TextureRegion>(.15F, Utils2.getFrames(zombie_cone_layer_Texture, 1, 4), Animation.PlayMode.LOOP);
	public final Animation<TextureRegion> zombie_bucket_layer_left_walking_Animation = new Animation<TextureRegion>(.15F, Utils2.getFrames(zombie_bucket_layer_Texture, 1, 4), Animation.PlayMode.LOOP);
	public final Animation<TextureRegion> zombie_bucket_layer_right_walking_Animation = new Animation<TextureRegion>(.15F, Utils2.getFrames(zombie_bucket_layer_Texture, 1, 4), Animation.PlayMode.LOOP);
	
	public Assets()
	{
		//create the font DTM_Mono
		FileHandle fontFile_DTM_Mono = Gdx.files.internal("fonts/DTM-Mono.otf");
	    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile_DTM_Mono);
	    FreeTypeFontParameter parameter = new FreeTypeFontParameter();
	    parameter.size = 26;
	    parameter.flip = false;
	    parameter.genMipMaps = true;
	    font_DTM_Mono = generator.generateFont(parameter);
	    generator.dispose();

	    //create texture regions to prepare for RRectangles
		TextureRegion zombie_layer_left_walking_Firstframe = zombie_layer_left_walking_Animation.getKeyFrame(0);
		TextureRegion zombie_back_walking_Texture_Firstframe = zombie_back_walking_Animation.getKeyFrame(0);
		TextureRegion zombie_walking_Texture_Firstframe = zombie_walking_Animation.getKeyFrame(0);
		TextureRegion zombie_layer_right_walking_Firstframe = zombie_layer_right_walking_Animation.getKeyFrame(0);

		TextureRegion zombie_layer_left_eating_Firstframe = zombie_layer_left_eating_Animation.getKeyFrame(0);
		TextureRegion zombie_back_eating_Texture_Firstframe = zombie_back_eating_Animation.getKeyFrame(0);
		TextureRegion zombie_eating_Texture_Firstframe = zombie_eating_Animation.getKeyFrame(0);
		TextureRegion zombie_layer_right_eating_Firstframe = zombie_layer_right_eating_Animation.getKeyFrame(0);
		
		TextureRegion zombie_cone_layer_left_walking_Firstframe = zombie_cone_layer_left_walking_Animation.getKeyFrame(0);
		TextureRegion zombie_cone_layer_right_walking_Firstframe = zombie_cone_layer_right_walking_Animation.getKeyFrame(0);
		
		TextureRegion zombie_bucket_layer_left_walking_Firstframe = zombie_bucket_layer_left_walking_Animation.getKeyFrame(0);
		TextureRegion zombie_bucket_layer_right_walking_Firstframe = zombie_bucket_layer_right_walking_Animation.getKeyFrame(0);
		
		TextureRegion frisk_layer_left_walking_Firstframe = frisk_layer_left_walking_Animation.getKeyFrame(0);
		TextureRegion frisk_back_walking_Texture_Firstframe = frisk_back_walking_Animation.getKeyFrame(0);
		TextureRegion frisk_walking_Texture_Firstframe = frisk_walking_Animation.getKeyFrame(0);
		TextureRegion frisk_layer_right_walking_Firstframe = frisk_layer_right_walking_Animation.getKeyFrame(0);
	    
		//create RRectangles
		Rectangle zombie_layer_left_walking_RRectangle = new Rectangle();
				  zombie_layer_left_walking_RRectangle.setX(0);
				  zombie_layer_left_walking_RRectangle.setSize(zombie_layer_left_walking_Firstframe.getRegionWidth()*2, zombie_layer_left_walking_Firstframe.getRegionHeight()*2);
				  this.zombie_layer_left_walking_RRectangle = zombie_layer_left_walking_RRectangle;
		Rectangle zombie_back_walking_Texture_RRectangle = new Rectangle();
				  zombie_back_walking_Texture_RRectangle.setX(-2.5f);
				  zombie_back_walking_Texture_RRectangle.setSize(zombie_back_walking_Texture_Firstframe.getRegionWidth()*2, zombie_back_walking_Texture_Firstframe.getRegionHeight()*2);
				  this.zombie_back_walking_RRectangle = zombie_back_walking_Texture_RRectangle;
		Rectangle zombie_walking_Texture_RRectangle = new Rectangle();
				  zombie_walking_Texture_RRectangle.setSize(zombie_walking_Texture_Firstframe.getRegionWidth()*2, zombie_walking_Texture_Firstframe.getRegionHeight()*2);
				  zombie_walking_Texture_RRectangle.setX(-2.5f);
				  this.zombie_walking_RRectangle = zombie_walking_Texture_RRectangle;
		Rectangle zombie_layer_right_walking_RRectangle = new Rectangle();
				  zombie_layer_right_walking_RRectangle.setX(0);
				  zombie_layer_right_walking_RRectangle.setSize(zombie_layer_right_walking_Firstframe.getRegionWidth()*2, zombie_layer_right_walking_Firstframe.getRegionHeight()*2);
				  this.zombie_layer_right_walking_RRectangle = zombie_layer_right_walking_RRectangle;
				  
		Rectangle zombie_layer_left_eating_RRectangle = new Rectangle();
				  zombie_layer_left_eating_RRectangle.setX(0);
				  zombie_layer_left_eating_RRectangle.setSize(zombie_layer_left_eating_Firstframe.getRegionWidth()*2, zombie_layer_left_eating_Firstframe.getRegionHeight()*2);
				  this.zombie_layer_left_eating_RRectangle = zombie_layer_left_eating_RRectangle;
		Rectangle zombie_back_eating_Texture_RRectangle = new Rectangle();
				  zombie_back_eating_Texture_RRectangle.setX(-2.5f);
				  zombie_back_eating_Texture_RRectangle.setSize(zombie_back_eating_Texture_Firstframe.getRegionWidth()*2, zombie_back_eating_Texture_Firstframe.getRegionHeight()*2);
				  this.zombie_back_eating_RRectangle = zombie_back_eating_Texture_RRectangle;
		Rectangle zombie_eating_Texture_RRectangle = new Rectangle();
				  zombie_eating_Texture_RRectangle.setSize(zombie_eating_Texture_Firstframe.getRegionWidth()*2, zombie_eating_Texture_Firstframe.getRegionHeight()*2);
				  zombie_eating_Texture_RRectangle.setX(-2.5f);
				  this.zombie_eating_RRectangle = zombie_eating_Texture_RRectangle;
		Rectangle zombie_layer_right_eating_RRectangle = new Rectangle();
				  zombie_layer_right_eating_RRectangle.setSize(zombie_layer_right_eating_Firstframe.getRegionWidth()*2, zombie_layer_right_eating_Firstframe.getRegionHeight()*2);
				  zombie_layer_right_eating_RRectangle.setX(0);
				  this.zombie_layer_right_eating_RRectangle = zombie_layer_right_eating_RRectangle;
				  
		Rectangle zombie_cone_layer_left_walking_RRectangle = new Rectangle();
				  zombie_cone_layer_left_walking_RRectangle.setX(-4);
				  zombie_cone_layer_left_walking_RRectangle.setSize(zombie_cone_layer_left_walking_Firstframe.getRegionWidth()*2, zombie_cone_layer_left_walking_Firstframe.getRegionHeight()*2);
				  this.zombie_cone_layer_left_walking_RRectangle = zombie_cone_layer_left_walking_RRectangle;
		Rectangle zombie_cone_layer_right_walking_RRectangle = new Rectangle();
				  zombie_cone_layer_right_walking_RRectangle.setSize(zombie_cone_layer_right_walking_Firstframe.getRegionWidth()*2, zombie_cone_layer_right_walking_Firstframe.getRegionHeight()*2);
				  zombie_cone_layer_right_walking_RRectangle.setX(-4);
				  this.zombie_cone_layer_right_walking_RRectangle = zombie_cone_layer_right_walking_RRectangle;
		Rectangle zombie_cone_back_walking_RRectangle = new Rectangle();
				  zombie_cone_back_walking_RRectangle.setSize(30, 32);
				  zombie_cone_back_walking_RRectangle.setX(6f);
		  		  this.zombie_cone_back_walking_RRectangle = zombie_cone_back_walking_RRectangle;
		Rectangle zombie_cone_walking_RRectangle = new Rectangle();
				  zombie_cone_walking_RRectangle.setSize(30, 32);
				  zombie_cone_walking_RRectangle.setX(6f);
		  		  this.zombie_cone_walking_RRectangle = zombie_cone_walking_RRectangle;
		  		  

  		Rectangle zombie_bucket_layer_left_walking_RRectangle = new Rectangle();
  				  zombie_bucket_layer_left_walking_RRectangle.setX(-4);
  				  zombie_bucket_layer_left_walking_RRectangle.setSize(zombie_bucket_layer_left_walking_Firstframe.getRegionWidth()*2, zombie_bucket_layer_left_walking_Firstframe.getRegionHeight()*2);
  				  this.zombie_bucket_layer_left_walking_RRectangle = zombie_bucket_layer_left_walking_RRectangle;
  		Rectangle zombie_bucket_layer_right_walking_RRectangle = new Rectangle();
  				  zombie_bucket_layer_right_walking_RRectangle.setSize(zombie_bucket_layer_right_walking_Firstframe.getRegionWidth()*2, zombie_bucket_layer_right_walking_Firstframe.getRegionHeight()*2);
  				  zombie_bucket_layer_right_walking_RRectangle.setX(- 4);
  				  this.zombie_bucket_layer_right_walking_RRectangle = zombie_bucket_layer_right_walking_RRectangle;
  		Rectangle zombie_bucket_back_walking_RRectangle = new Rectangle();
  				  zombie_bucket_back_walking_RRectangle.setSize(32, 30);
  				  zombie_bucket_back_walking_RRectangle.setX(2f);
  		  		  this.zombie_bucket_back_walking_RRectangle = zombie_bucket_back_walking_RRectangle;
  		Rectangle zombie_bucket_walking_RRectangle = new Rectangle();
  				  zombie_bucket_walking_RRectangle.setSize(38, 40);
  				  zombie_bucket_walking_RRectangle.setX(2f);
  		  		  this.zombie_bucket_walking_RRectangle = zombie_bucket_walking_RRectangle;
				  
		Rectangle froggit_RRectangle = new Rectangle();
				  froggit_RRectangle.setSize(40, 40);
				  this.froggit_RRectangle = froggit_RRectangle;
		Rectangle froggit_bullet_RRectangle = new Rectangle();
				  froggit_bullet_RRectangle.set(0, 0, froggit_bullet_Texture.getWidth(), froggit_bullet_Texture.getHeight());
				  this.froggit_bullet_RRectangle = froggit_bullet_RRectangle;


		Rectangle frisk_layer_left_walking_RRectangle = new Rectangle();
				  frisk_layer_left_walking_RRectangle.setX(0);
				  frisk_layer_left_walking_RRectangle.setSize(frisk_layer_left_walking_Firstframe.getRegionWidth()*2, frisk_layer_left_walking_Firstframe.getRegionHeight()*2);
				  this.frisk_layer_left_walking_RRectangle = frisk_layer_left_walking_RRectangle;
		Rectangle frisk_back_walking_Texture_RRectangle = new Rectangle();
				  frisk_back_walking_Texture_RRectangle.setX(-1);
				  frisk_back_walking_Texture_RRectangle.setSize(frisk_back_walking_Texture_Firstframe.getRegionWidth()*2, frisk_back_walking_Texture_Firstframe.getRegionHeight()*2);
				  this.frisk_back_walking_RRectangle = frisk_back_walking_Texture_RRectangle;
		Rectangle frisk_walking_Texture_RRectangle = new Rectangle();
				  frisk_walking_Texture_RRectangle.setSize(frisk_walking_Texture_Firstframe.getRegionWidth()*2, frisk_walking_Texture_Firstframe.getRegionHeight()*2);
				  frisk_walking_Texture_RRectangle.setX(-1);
				  this.frisk_walking_RRectangle = frisk_walking_Texture_RRectangle;
		Rectangle frisk_layer_right_walking_RRectangle = new Rectangle();
				  frisk_layer_right_walking_RRectangle.setSize(frisk_layer_right_walking_Firstframe.getRegionWidth()*2, frisk_layer_right_walking_Firstframe.getRegionHeight()*2);
				  frisk_layer_right_walking_RRectangle.setX(0);
				  this.frisk_layer_right_walking_RRectangle = frisk_layer_right_walking_RRectangle;
				  
		Rectangle moldsmal_RRectangle = new Rectangle();
				  moldsmal_RRectangle.setSize(42, 30);
				  this.moldsmal_RRectangle = moldsmal_RRectangle;
		Rectangle moldsmal_bullet_RRectangle = new Rectangle();
				  moldsmal_bullet_RRectangle.set(0, 0, froggit_bullet_Texture.getWidth(), froggit_bullet_Texture.getHeight());
				  this.moldsmal_bullet_RRectangle = moldsmal_bullet_RRectangle;
				  
		Rectangle rock_RRectangle = new Rectangle();
				  rock_RRectangle.setSize(40, 36);
				  this.rock_RRectangle = rock_RRectangle;
				  
		Rectangle whimsun_RRectangle = new Rectangle();
				  whimsun_RRectangle.setSize(38, 40);
				  this.whimsun_RRectangle = whimsun_RRectangle;
		Rectangle whimsun_bullet_RRectangle = new Rectangle();
				  whimsun_bullet_RRectangle.setPosition(0, 0);
				  whimsun_bullet_RRectangle.set(0, 0, whimsun_bullet_Texture.getWidth(), whimsun_bullet_Texture.getHeight());
				  this.whimsun_bullet_RRectangle = whimsun_bullet_RRectangle;

		Rectangle loox_RRectangle = new Rectangle();
				  loox_RRectangle.setSize(loox_Texture.getWidth(), loox_Texture.getHeight());
				  this.loox_RRectangle = loox_RRectangle;
		Rectangle loox_bullet1_RRectangle = new Rectangle();
			  	  loox_bullet1_RRectangle.setPosition(0, 0);
				  loox_bullet1_RRectangle.set(0, 0, loox_bullet1_Texture.getWidth(), loox_bullet1_Texture.getHeight());
				  this.loox_bullet1_RRectangle = loox_bullet1_RRectangle;
		Rectangle loox_bullet2_RRectangle = new Rectangle();
			  	  loox_bullet2_RRectangle.setPosition(0, 0);
				  loox_bullet2_RRectangle.set(0, 0, loox_bullet2_Texture.getWidth(), loox_bullet2_Texture.getHeight());
				  this.loox_bullet2_RRectangle = loox_bullet2_RRectangle;
		Rectangle loox_bullet3_RRectangle = new Rectangle();
			  	  loox_bullet3_RRectangle.setPosition(0, 0);
				  loox_bullet3_RRectangle.set(0, 0, loox_bullet3_Texture.getWidth(), loox_bullet3_Texture.getHeight());
				  this.loox_bullet3_RRectangle = loox_bullet3_RRectangle;

		Rectangle migosp_RRectangle = new Rectangle();
				  migosp_RRectangle.setSize(36, 46);
				  this.migosp_RRectangle = migosp_RRectangle;
				  
	}
	public void dispose()
	{
		//dispose textures
		zombie_layer_walking_Texture.dispose();
		zombie_back_walking_Texture.dispose();
		zombie_walking_Texture.dispose();
		frisk_layer_walking_Texture.dispose();
		frisk_back_walking_Texture.dispose();
		frisk_walking_Texture.dispose();
		zombie_layer_eating_Texture.dispose();
		zombie_back_eating_Texture.dispose();
		zombie_eating_Texture.dispose();
		Twoswitchpuzzle_Texture.dispose();
		froggit_Texture.dispose();
		froggit_bullet_Texture.dispose();
		whimsun_Texture.dispose();
		whimsun_bullet_Texture.dispose();
		unit_Texture.dispose();
		zombie_cone_back_Texture.dispose();
		zombie_cone_layer_Texture.dispose();
		zombie_cone_Texture.dispose();
		zombie_bucket_back_Texture.dispose();
		zombie_bucket_layer_Texture.dispose();
		zombie_bucket_Texture.dispose();
		rock_Texture.dispose();
		moldsmal_Texture.dispose();
		moldsmal_bullet1_Texture.dispose();
		moldsmal_bullet2_Texture.dispose();
		loox_Texture.dispose();
		loox_bullet1_Texture.dispose();
		loox_bullet2_Texture.dispose();
		loox_bullet3_Texture.dispose();
		ruins1_Texture.dispose();
		ruins2_Texture.dispose();
		ruins3_Texture.dispose();
		//dispose fonts
		font_DTM_Mono.dispose();
	}
}
