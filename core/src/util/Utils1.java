package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import basics.Assets;
import basics.Trail;
import beings.MoveableBeing;
import beings.TrailBeing;
import beings.MoveableBeing.MoveType;
import test.basics.Being;
import test.basics.Being.Drawbase_name;
import test.basics.Being.Status;
import test.basics.Being.Type1;
import test.basics.Being.Type2;
import test.basics.Bulletimplementer;
import test.basics.Enemyimplementer;
import test.basics.Hero;
import test.basics.Heroimplementer;
import test.basics.Implementer;
import test.basics.Towerimplementer;
import test.framework.Level;

public class Utils1 {

	final Assets assets;
	final Map<Drawbase_name, Animation<TextureRegion>> animations;
	final Map<Drawbase_name, Rectangle> relativeRectangles;

	Level level;
	
	final Map<Type1, Implementer> implementers = new HashMap<Type1, Implementer> ();
	
	public Utils1(final Assets assets, final Map<Drawbase_name, Animation<TextureRegion>> animations, final Map<Drawbase_name, Rectangle> relativeRectangles, final Level level)
	{
		this.assets = assets;
		this.animations = animations;
		this.relativeRectangles = relativeRectangles;
		
		this.level = level;
		
		implementers.put(Type1.ZOMBIE,
		new Enemyimplementer()
		{
			@Override
			public void render(Being r, SpriteBatch batch, float deltaX, float deltaY)
			{
				int LV = (int)r.type2Args[3];
    			renderAnimation(r, batch, deltaX, deltaY);
    			renderHPBar(r, batch, deltaX, deltaY, Data.zombieOriginalHP[LV - 1], 0);
			}
			@Override
			public void moveBeings(MoveableBeing b, float x, float y) {

				b.setStatus(Status.MOVING);
				if(Math.abs(x) < Math.abs(y))
				{
					if(y > 0)
						b.setDrawbaseName(Being.Drawbase_name.ZOMBIE_WALKING_UP);
					else
						b.setDrawbaseName(Being.Drawbase_name.ZOMBIE_WALKING_DOWN);
				}
				else
				{
					if(x < 0)
					{
						b.setDrawbaseName(Being.Drawbase_name.ZOMBIE_WALKING_LEFT);
						b.fliped = false;
					}
					else
					{
						b.setDrawbaseName(Being.Drawbase_name.ZOMBIE_WALKING_RIGHT);
						b.fliped = true;
					}
				}
			}
			@Override
			public void attack(MoveableBeing b1, Being b2) {
				normalZombieAttack(b1, b2);
			}
			@Override
			public void zombieAnalysis(Being z, Iterator<Being> it) {
				normalTrailZombieAnalysis((TrailBeing)z, it);
				buffJudge(z);
				
			}
		});
		
		implementers.put(Type1.CONE_ZOMBIE,
		new Enemyimplementer()
		{
			@Override
			public void render(Being r, SpriteBatch batch, float deltaX, float deltaY)
			{
    			renderAnimation(r, batch, deltaX, deltaY);
    			Being cone = r.attachments.get(0);
    			
    			switch(cone.getDrawbaseName())
    			{
    				case ZOMBIE_CONE_WALKING_UP:
    				{
    	            	batch.draw(assets.zombie_cone_back_Texture, r.getX() + deltaX + cone.getX(), r.getY() + deltaY + cone.getY(), cone.getWidth(), cone.getHeight());
    					break;
    				}
    				case ZOMBIE_CONE_WALKING_DOWN:
					{
    	            	batch.draw(assets.zombie_cone_Texture, r.getX() + deltaX + cone.getX(), r.getY() + deltaY + cone.getY(), cone.getWidth(), cone.getHeight());
						break;
					}
    				case ZOMBIE_CONE_WALKING_LEFT:
					{
		    			renderAnimation(cone, batch, deltaX + r.getX(), deltaY + r.getY());
						break;
					}
    				case ZOMBIE_CONE_WALKING_RIGHT:
					{
		    			renderAnimation(cone, batch, deltaX + r.getX(), deltaY + r.getY());
						break;
					} 
					default:{}
    			}

				int LV = (int)r.type2Args[3];
    			renderHPBar(r, batch, deltaX, deltaY, Data.zombieOriginalHP[LV - 1], Data.coneOriginalHP[LV - 1]);
			}
			@Override
			public void moveBeings(MoveableBeing b, float x, float y) {

				b.setStatus(Status.MOVING);
				b.attachments.get(0).setStatus(Status.MOVING);
				if(Math.abs(x) < Math.abs(y))
				{
					if(y > 0)
					{
						b.setDrawbaseName(Drawbase_name.ZOMBIE_WALKING_UP);
						b.attachments.get(0).setDrawbaseName(Drawbase_name.ZOMBIE_CONE_WALKING_UP);
					}
					else
					{
						b.setDrawbaseName(Drawbase_name.ZOMBIE_WALKING_DOWN);
						b.attachments.get(0).setDrawbaseName(Drawbase_name.ZOMBIE_CONE_WALKING_DOWN);
					}
				}
				else
				{
					if(x < 0)
					{
						b.fliped = false;
						b.setDrawbaseName(Drawbase_name.ZOMBIE_WALKING_LEFT);
						b.attachments.get(0).fliped = false;
						b.attachments.get(0).setDrawbaseName(Drawbase_name.ZOMBIE_CONE_WALKING_LEFT);
					}
					else
					{
						b.fliped = true;
						b.setDrawbaseName(Drawbase_name.ZOMBIE_WALKING_RIGHT);
						b.attachments.get(0).fliped = true;
						b.attachments.get(0).setDrawbaseName(Drawbase_name.ZOMBIE_CONE_WALKING_RIGHT);
					}
				}
			}
			@Override
			public void attack(MoveableBeing b1, Being b2) {
				normalZombieAttack(b1, b2);
			}
			@Override
			public void zombieAnalysis(Being z, Iterator<Being> it) {
				normalTrailZombieAnalysis((TrailBeing)z, it);
				buffJudge(z);
				
			}
		});

		implementers.put(Type1.BUCKET_ZOMBIE,
		new Enemyimplementer()
		{
			@Override
			public void render(Being r, SpriteBatch batch, float deltaX, float deltaY)
			{
    			Being bucket = r.attachments.get(0);
    			renderAnimation(r, batch, deltaX, deltaY);
    			
    			switch(bucket.getDrawbaseName())
    			{
    				case ZOMBIE_BUCKET_WALKING_UP:
    				{
    	            	batch.draw(assets.zombie_bucket_back_Texture, r.getX() + deltaX + bucket.getX(), r.getY() + deltaY + bucket.getY(), bucket.getWidth(), bucket.getHeight());
    					break;
    				}
    				case ZOMBIE_BUCKET_WALKING_DOWN:
					{
    	            	batch.draw(assets.zombie_bucket_Texture, r.getX() + deltaX + bucket.getX(), r.getY() + deltaY + bucket.getY(), bucket.getWidth(), bucket.getHeight());
						break;
					}
    				case ZOMBIE_BUCKET_WALKING_LEFT:
					{
		    			renderAnimation(bucket, batch, deltaX + r.getX(), deltaY + r.getY());
						break;
					}
    				case ZOMBIE_BUCKET_WALKING_RIGHT:
					{
		    			renderAnimation(bucket, batch, deltaX + r.getX(), deltaY + r.getY());
						break;
					} 
					default:{}
    			}

				int LV = (int)r.type2Args[3];
    			renderHPBar(r, batch, deltaX, deltaY, Data.zombieOriginalHP[LV - 1], Data.bucketOriginalHP[LV - 1]);
			}
			@Override
			public void moveBeings(MoveableBeing b, float x, float y) {

				b.setStatus(Status.MOVING);
				b.attachments.get(0).setStatus(Status.MOVING);
				if(Math.abs(x) < Math.abs(y))
				{
					if(y > 0)
					{
						b.setDrawbaseName(Drawbase_name.ZOMBIE_WALKING_UP);
						b.attachments.get(0).setDrawbaseName(Drawbase_name.ZOMBIE_BUCKET_WALKING_UP);
					}
					else
					{
						b.setDrawbaseName(Drawbase_name.ZOMBIE_WALKING_DOWN);
						b.attachments.get(0).setDrawbaseName(Drawbase_name.ZOMBIE_BUCKET_WALKING_DOWN);
					}
				}
				else
				{
					if(x < 0)
					{
						b.fliped = false;
						b.setDrawbaseName(Drawbase_name.ZOMBIE_WALKING_LEFT);
						b.attachments.get(0).fliped = false;
						b.attachments.get(0).setDrawbaseName(Drawbase_name.ZOMBIE_BUCKET_WALKING_LEFT);
					}
					else
					{
						b.fliped = true;
						b.setDrawbaseName(Drawbase_name.ZOMBIE_WALKING_RIGHT);
						b.attachments.get(0).fliped = true;
						b.attachments.get(0).setDrawbaseName(Drawbase_name.ZOMBIE_BUCKET_WALKING_RIGHT);
					}
				}
			}
			@Override
			public void attack(MoveableBeing b1, Being b2) {
				normalZombieAttack(b1, b2);
			}
			@Override
			public void zombieAnalysis(Being z, Iterator<Being> it) {
				normalTrailZombieAnalysis((TrailBeing)z, it);
				buffJudge(z);
			}
		});
		
		
		implementers.put(Type1.FROGGIT,
		new Towerimplementer()
		{
			@Override
			public void render(Being r, SpriteBatch batch, float deltaX, float deltaY) {
				mixColorAndDraw(r, batch, assets.froggit_Texture, r.getX() + deltaX, r.getY() + deltaY, r.getWidth(), r.getHeight());
                //batch.draw(assets.froggit_Texture, r.getX() + deltaX, r.getY() + deltaY, r.getWidth(), r.getHeight());
				
			}
			@Override
			public void towerAnalysis(Being t, ArrayList<Being> enemies, ArrayList<Being> towers,
					ArrayList<MoveableBeing> bullets, ArrayList<Being> renderList, Trail trail) {

				buffJudge(t);
            	t.skills.get(0).updateCDTime();
            	if(t.skills.get(0).cdReady(Data.froggitSkill0CDArgs[Utils2.getLV(t) - 1]))
            	{
                	Iterator<Being> it = enemies.iterator();
                	while(it.hasNext())
                	{
                		Being z = it.next();
                    	if(t.skills.get(0).rangeTest(t, z, new int[] {Utils2.getRG(t)}))
                    	{
                    		t.skills.get(0).resetCDTime();
                    		Vector2 center = t.getCenter(new Vector2());
                    		Vector2 bulletDestination = Utils2.getNormalBulletDestination(z, center.x, center.y, Data.froggitBullet1Speed, trail);
                    		MoveableBeing bullet = Utils2.getBullet(Type1.FROGGIT_BULLET, new double[] {center.x, center.y, bulletDestination.x, bulletDestination.y, Data.froggitBullet1Speed}, relativeRectangles.get(Drawbase_name.FROGGIT_BULLET), Utils2.getLV(t), t.buffArgs);
                	        bullets.add(bullet);
                	        renderList.add(bullet);
                	        break;
                    	}
                	}
            	}
            	transientlyRemoveBuff(t);
			}
		});
		
		implementers.put(Type1.MOLDSMAL,
		new Towerimplementer()
		{
			@Override
			public void render(Being r, SpriteBatch batch, float deltaX, float deltaY) {
    			if((int)r.stateTime %2 == 0)
    			{
    				mixColorAndDraw(r, batch, assets.moldsmal_Texture, r.getX() + deltaX, r.getY() + deltaY, r.getWidth(), r.getHeight() - 2);
    			}
    			else
    			{
    				mixColorAndDraw(r, batch, assets.moldsmal_Texture, r.getX() + deltaX, r.getY() + deltaY, r.getWidth(), r.getHeight());
    			}
			}
			@Override
			public void towerAnalysis(Being t, ArrayList<Being> enemies, ArrayList<Being> towers,
					ArrayList<MoveableBeing> bullets, ArrayList<Being> renderList, Trail trail) {

				buffJudge(t);
            	t.skills.get(0).updateCDTime();
            	if(t.skills.get(0).cdReady(Data.moldsmalSkill0CDArgs[Utils2.getLV(t) - 1]))
            	{
            		Iterator<Being> it = enemies.iterator();
                	while(it.hasNext())
                	{
                		Being z = it.next();
                    	if(t.skills.get(0).rangeTest(t, z, new int[] {Utils2.getRG(t)}))
                    	{
                    		t.skills.get(0).resetCDTime();
                    		Vector2 center = t.getCenter(new Vector2());
                    		Vector2 bulletDestination = Utils2.getNormalBulletDestination(z, center.x, center.y, Data.moldsmalBullet1Speed, trail);
                    		MoveableBeing bullet = Utils2.getBullet(Type1.MOLDSMAL_BULLET1, new double[] {center.x, center.y, bulletDestination.x, bulletDestination.y, Data.moldsmalBullet1Speed}, relativeRectangles.get(Drawbase_name.FROGGIT_BULLET), Utils2.getLV(t), t.buffArgs);
                	        bullets.add(bullet);
                	        renderList.add(bullet);
                	        break;
                    	}
                	}
            	}
            	transientlyRemoveBuff(t);
			}
		});

		implementers.put(Type1.WHIMSUN,
		new Towerimplementer()
		{
			@Override
			public void render(Being r, SpriteBatch batch, float deltaX, float deltaY) {
				
    			if((int)r.stateTime %2 == 0)
    			{
    				mixColorAndDraw(r, batch, assets.whimsun_Texture, r.getX() + deltaX, r.getY() + deltaY + 5, r.getWidth(), r.getHeight());
    			}
    			else
    			{
    				mixColorAndDraw(r, batch, assets.whimsun_Texture, r.getX() + deltaX, r.getY() + deltaY, r.getWidth(), r.getHeight());
    			}
    			
			}
			@Override
			public void towerAnalysis(Being t, ArrayList<Being> enemies, ArrayList<Being> towers,
					ArrayList<MoveableBeing> bullets, ArrayList<Being> renderList, Trail trail) {

				buffJudge(t);
            	t.skills.get(0).updateCDTime();
            	if(t.skills.get(0).cdReady(Data.whimsunSkill0CDArgs[Utils2.getLV(t) - 1]))
            	{
                	Iterator<Being> it = enemies.iterator();
                	while(it.hasNext())
                	{
                		Being z = it.next();
                    	if(t.skills.get(0).rangeTest(t, z, new int[] {Utils2.getRG(t)}))
                    	{
                    		t.skills.get(0).resetCDTime();
                    		for(int i = 0; i < 360; i += 45)
                    		{
                        		MoveableBeing bullet = Utils2.getBullet(Type1.WHIMSUN_BULLET, new double[]{z.getX(), z.getY(), i}, relativeRectangles.get(Drawbase_name.WHIMSUN), Utils2.getLV(t), t.buffArgs);
                    	        bullets.add(bullet);
                    	        renderList.add(bullet);
                    		}
                	        break;
                    	}
                	}
            	}
            	transientlyRemoveBuff(t);
			}
		});
	
		implementers.put(Type1.FROGGIT_BULLET,
		new Bulletimplementer(){

			@Override
			public void render(Being r, SpriteBatch batch, float deltaX, float deltaY) {
            	batch.draw(assets.froggit_bullet_Texture, r.getX() + deltaX, r.getY() + deltaY);
			}

			@Override
			public boolean MoveBullet(MoveableBeing b) {
				return moveSimpleBullet(b);
			}

			@Override
			public void bulletAnalysis(MoveableBeing bullet, ArrayList<Being> renderList, Iterator<MoveableBeing> it,
					ArrayList<Being> enemies) {

		    	//让子弹飞，并且移除逃逸的子弹
		    	if(!MoveBullet(bullet))
		    	{
		    		goodRemove(bullet, renderList);
		    		it.remove();
		    		return;
		    	}
		    	//如果子弹打中了僵尸，进行分析
		    	Iterator<Being> _it = enemies.iterator();
		    	while(_it.hasNext())
		    	{
		    		Being z = _it.next();

		    		if(normalBulletAttack(bullet, z, renderList, _it))
		    		{
	        			goodRemove(bullet, renderList);
	        			it.remove();
		    			return;
		    		}
		    	}
			}

		});

		implementers.put(Type1.WHIMSUN_BULLET,
		new Bulletimplementer(){

			@Override
			public void render(Being r, SpriteBatch batch, float deltaX, float deltaY) {
				
    			MoveableBeing _r = (MoveableBeing)r;
    			if(_r.alpha < 1)
    			{
        			_r.alpha += 0.05;
    			}
    			if(_r.stateTime > 8 - (float)20 * Gdx.graphics.getDeltaTime())
    			{
            		_r.alpha -= 0.1;
    			}
    			
    			batch.draw(assets.whimsun_bullet_Texture, 
				(float)_r.moveArgs[0] - _r.getWidth()/2 + deltaX,
				(float)_r.moveArgs[1] - Data.whimsunBullet1Radius - _r.getHeight()/2 + deltaY,
				_r.getWidth()/2,
				Data.whimsunBullet1Radius + _r.getHeight()/2, 
				_r.getWidth(), _r.getHeight(),
				1f, 1f, 
				(int)_r.moveArgs[2],
				0, 0, (int)r.getWidth(), (int)r.getHeight(),
				false, true);
			}

			@Override
			public boolean MoveBullet(MoveableBeing b) {
				return moveRotatingBulletAboutSinglePoint(b, Data.whimsunBullet1AngleSpeed, Data.whimsunBullet1Radius);
			}

			@Override
			public void bulletAnalysis(MoveableBeing bullet, ArrayList<Being> renderList, Iterator<MoveableBeing> it,
					ArrayList<Being> enemies) {

		    	MoveBullet(bullet);
				bullet.type2Args[2] -= Gdx.graphics.getDeltaTime();
				if(bullet.type2Args[2] < 0)
				{
					goodRemove(bullet, renderList);
					it.remove();
					return;
				}
		    	//如果子弹打中了僵尸，进行分析
		    	Iterator<Being> _it = enemies.iterator();
		    	while(_it.hasNext())
		    	{
		    		Being z = _it.next();
		    		normalBulletAttack(bullet, z, renderList, _it);
		    	}
			}
			
		});

		implementers.put(Type1.MOLDSMAL_BULLET1,
		new Bulletimplementer(){

			@Override
			public void render(Being r, SpriteBatch batch, float deltaX, float deltaY) {
            	batch.draw(assets.moldsmal_bullet1_Texture, r.getX() + deltaX, r.getY() + deltaY);
			}

			@Override
			public boolean MoveBullet(MoveableBeing b) {
				return moveSimpleBullet(b);
			}

			@Override
			public void bulletAnalysis(MoveableBeing bullet, ArrayList<Being> renderList, Iterator<MoveableBeing> it, ArrayList<Being> enemies) {

		    	//让子弹飞，并且移除逃逸的子弹
		    	if(!MoveBullet(bullet))
		    	{
		    		goodRemove(bullet, renderList);
		    		it.remove();
		    		return;
		    	}
		    	//如果子弹打中了僵尸，进行分析
		    	Iterator<Being> _it = enemies.iterator();
		    	while(_it.hasNext())
		    	{
		    		Being z = _it.next();
		    		if(normalBulletAttack(bullet, z, renderList, _it))
		    		{
                		Vector2 center = bullet.getCenter(new Vector2());
	        			goodRemove(bullet, renderList);
	        			it.remove();
	        			//爆出新的子弹
                		for(int r = 0; r < 360; r += 40)
                		{
                			double rad = Math.toRadians(r);
                			float deltaX = (float)Math.sin(rad);
                			float deltaY = (float)Math.cos(rad);
                			Vector2 presumedDestination = new Vector2(center).add(deltaX, deltaY);
                    		MoveableBeing daughtetbullet = Utils2.getBullet(Type1.MOLDSMAL_BULLET2, new double[]{center.x, center.y, presumedDestination.x, presumedDestination.y, Data.moldsmalBullet2Speed}, relativeRectangles.get(Drawbase_name.FROGGIT_BULLET), Utils2.getLV(bullet), bullet.buffArgs);
                	        level._bullets.add(daughtetbullet);
                	        renderList.add(daughtetbullet);
                		}
		    			return;
		    		}
		    	}
			}

		});
		
		implementers.put(Type1.MOLDSMAL_BULLET2,
		new Bulletimplementer(){

			@Override
			public void render(Being r, SpriteBatch batch, float deltaX, float deltaY) {
				batch.setColor(1, 1, 1, r.alpha);
            	batch.draw(assets.moldsmal_bullet2_Texture, r.getX() + deltaX, r.getY() + deltaY);
				batch.setColor(Color.WHITE);
			}

			@Override
			public boolean MoveBullet(MoveableBeing b) {

				b.alpha -= 1/(Data.moldsmalBullet2LastTime*Gdx.graphics.getFramesPerSecond());
				return moveSimpleBullet(b);
			}

			@Override
			public void bulletAnalysis(MoveableBeing bullet, ArrayList<Being> renderList, Iterator<MoveableBeing> it,
					ArrayList<Being> enemies) {

		    	MoveBullet(bullet);
				bullet.type2Args[2] -= Gdx.graphics.getDeltaTime();
				if(bullet.type2Args[2] < 0)
				{
					goodRemove(bullet, renderList);
					it.remove();
					return;
				}
		    	//如果子弹打中了僵尸，进行分析
		    	Iterator<Being> _it = enemies.iterator();
		    	while(_it.hasNext())
		    	{
		    		Being z = _it.next();
		    		normalBulletAttack(bullet, z, renderList, _it);
		    	}
			}
		});
		
		implementers.put(Type1.LOOX_BULLET1,
		new Bulletimplementer(){

			@Override
			public void render(Being r, SpriteBatch batch, float deltaX, float deltaY) {
            	batch.draw(assets.loox_bullet1_Texture, r.getX() + deltaX, r.getY() + deltaY);
			}

			@Override
			public boolean MoveBullet(MoveableBeing b) {
				return moveSimpleBullet(b);
			}

			@Override
			public void bulletAnalysis(MoveableBeing bullet, ArrayList<Being> renderList, Iterator<MoveableBeing> it,
					ArrayList<Being> enemies) {

		    	//让子弹飞，并且移除逃逸的子弹
		    	if(!MoveBullet(bullet))
		    	{
		    		goodRemove(bullet, renderList);
		    		it.remove();
		    		return;
		    	}
		    	//如果子弹打中了僵尸，进行分析
		    	Iterator<Being> _it = enemies.iterator();
		    	while(_it.hasNext())
		    	{
		    		Being z = _it.next();

		        	if(bullet.overlaps(z) && z.buffArgs[4] <= 0)
		        	{
		        		transientlyAddBuff(z, bullet);
	        			goodRemove(bullet, renderList);
	        			it.remove();
	        			return;
		        	}
		    	}
			}
		});

		implementers.put(Type1.LOOX_BULLET2,
		new Bulletimplementer(){

			@Override
			public void render(Being r, SpriteBatch batch, float deltaX, float deltaY) {
            	batch.draw(assets.loox_bullet2_Texture, r.getX() + deltaX, r.getY() + deltaY);
			}

			@Override
			public boolean MoveBullet(MoveableBeing b) {
				return moveSimpleBullet(b);
			}

			@Override
			public void bulletAnalysis(MoveableBeing bullet, ArrayList<Being> renderList, Iterator<MoveableBeing> it,
					ArrayList<Being> enemies) {

		    	//让子弹飞，并且移除逃逸的子弹
		    	if(!MoveBullet(bullet))
		    	{
		    		goodRemove(bullet, renderList);
		    		it.remove();
		    		return;
		    	}
		    	//如果子弹打中了僵尸，进行分析
		    	Iterator<Being> _it = enemies.iterator();
		    	while(_it.hasNext())
		    	{
		    		Being z = _it.next();

		        	if(bullet.overlaps(z) && z.buffArgs[4] <= 0)
		        	{
		        		transientlyAddBuff(z, bullet);
	        			goodRemove(bullet, renderList);
	        			it.remove();
	        			return;
		        	}
		    	}
			}
		});

		implementers.put(Type1.LOOX_BULLET3,
		new Bulletimplementer(){

			@Override
			public void render(Being r, SpriteBatch batch, float deltaX, float deltaY) {
            	batch.draw(assets.loox_bullet3_Texture, r.getX() + deltaX, r.getY() + deltaY);
			}

			@Override
			public boolean MoveBullet(MoveableBeing b) {
				return moveSimpleBullet(b);
			}

			@Override
			public void bulletAnalysis(MoveableBeing bullet, ArrayList<Being> renderList, Iterator<MoveableBeing> it,
					ArrayList<Being> enemies) {

		    	//让子弹飞，并且移除逃逸的子弹
		    	if(!MoveBullet(bullet))
		    	{
		    		goodRemove(bullet, renderList);
		    		it.remove();
		    		return;
		    	}
		    	//如果子弹打中了僵尸，进行分析
		    	Iterator<Being> _it = enemies.iterator();
		    	while(_it.hasNext())
		    	{
		    		Being z = _it.next();

		        	if(bullet.overlaps(z) && z.buffArgs[4] <= 0)
		        	{
		        		transientlyAddBuff(z, bullet);
	        			goodRemove(bullet, renderList);
	        			it.remove();
	        			return;
		        	}
		    	}
			}
		});
		
		implementers.put(Type1.ROCK,
		new Implementer()
		{
			@Override
			public void render(Being r, SpriteBatch batch, float deltaX, float deltaY) {
                batch.draw(assets.rock_Texture, r.getX() + deltaX, r.getY() + deltaY, r.getWidth(), r.getHeight());
                renderHPBar(r, batch, deltaX, deltaY, Data.rockOriginalHP, 0);
				
			}
		});
	
		implementers.put(Type1.FRISK, 
		new Heroimplementer()
		{
			@Override
			public void render(Being r, SpriteBatch batch, float deltaX, float deltaY) {

				if(renderDiedHero(r, batch, deltaX, deltaY))
				{
					return;
				}
				if(r.getStatus() == Status.DEFAULTED && r.type2Args[2] != -1)
				{
					int position = (int)r.type2Args[2];
					Vector2 positionNow = level.trail.get(position);
					Vector2 positionPref = level.trail.get(position - 1);
					float dx = positionPref.x - positionNow.x, dy = positionPref.y - positionNow.y;
					if(Math.abs(dx) < Math.abs(dy))
					{
						if(dy > 0)
						{
							r.setDrawbaseName(Drawbase_name.FRISK_WALKING_UP);
						}
						else
						{
							r.setDrawbaseName(Drawbase_name.FRISK_WALKING_DOWN);
						}
					}
					else
					{
						if(dx < 0)
						{
							r.setDrawbaseName(Drawbase_name.FRISK_WALKING_LEFT);
							r.fliped = false;
						}
						else
						{
							r.setDrawbaseName(Drawbase_name.FRISK_WALKING_RIGHT);
							r.fliped = true;
						}
					}
					r.setRelativeRectangle(level.relativeRectangles.get(r.getDrawbaseName()));
				}
    			renderAnimation(r, batch, deltaX, deltaY);

				int LV = (int)r.type2Args[1];
    			renderHPBar(r, batch, deltaX, deltaY, Data.friskOriginalHP[LV - 1], 0);
			}

			@Override
			public void heroAnalysis(Hero h, ArrayList<Being> enemies, ArrayList<MoveableBeing> bullets,
					ArrayList<Being> renderList, Trail trail) {

				if(revivingDiedHero(h))
				{
					return;
				}
				
				if(h.getStatus() == Status.MOVING)
				{
					h.translate((float)h.moveArgs[0], (float)h.moveArgs[1]);
					if(h.getPosition(new Vector2()).dst(h.destination) < Data.friskSpeed[0])
					{
						h.setStatus(Status.DEFAULTED);
					}
				}
            	h.skills.get(0).updateCDTime();
            	if(h.skills.get(0).cdReady(Data.friskSkill0CDArgs[Utils2.getLV(h) - 1]))
            	{
                	Iterator<Being> it = enemies.iterator();
                	while(it.hasNext())
                	{
                		Being z = it.next();
                    	if(h.skills.get(0).rangeTest(h, z, new int[] {Utils2.getRG(h)}))
                    	{
                    		h.skills.get(0).resetCDTime();
                    		Vector2 center = h.getCenter(new Vector2());
                    		Vector2 bulletDestination = Utils2.getNormalBulletDestination(z, center.x, center.y, Data.friskBullet1Speed, trail);
                    		MoveableBeing bullet = Utils2.getBullet(Type1.FROGGIT_BULLET, new double[] {center.x, center.y, bulletDestination.x, bulletDestination.y, Data.friskBullet1Speed}, relativeRectangles.get(Drawbase_name.FROGGIT_BULLET), Utils2.getLV(h), h.buffArgs);
                	        bullets.add(bullet);
                	        renderList.add(bullet);
                	        break;
                    	}
                	}
            	}
			}

			@Override
			public void beginGOTO(Hero h, Vector2 destination, int position) {

				if(h.died)return;

				h.type2Args[2] = position;
				
				h.setStatus(Status.MOVING);
				h.destination = destination;
				Vector2 startPosition = h.getPosition(new Vector2());
				int LV = (int)h.type2Args[1]-1;
				double dst = startPosition.dst(destination);
				double vx = (destination.x - startPosition.x)/dst * (double)Data.friskSpeed[LV];
				double vy = (destination.y - startPosition.y)/dst * (double)Data.friskSpeed[LV];
				h.moveArgs[0] = vx;
				h.moveArgs[1] = vy;
				if(Math.abs(vx) < Math.abs(vy))
				{
					if(vy > 0)
					{
						h.setDrawbaseName(Drawbase_name.FRISK_WALKING_UP);
						h.fliped = false;
					}
					else
					{
						h.setDrawbaseName(Drawbase_name.FRISK_WALKING_DOWN);
						h.fliped = false;
					}
				}
				else
				{
					if(vx < 0)
					{
						h.setDrawbaseName(Drawbase_name.FRISK_WALKING_LEFT);
						h.fliped = false;
					}
					else
					{
						h.setDrawbaseName(Drawbase_name.FRISK_WALKING_RIGHT);
						h.fliped = true;
					}
				}
			}
		});
	
		implementers.put(Type1.MIGOSP,
		new Towerimplementer()
		{
			@Override
			public void render(Being r, SpriteBatch batch, float deltaX, float deltaY) {
    			if((int)r.stateTime %2 == 0)
    			{
                    batch.draw(assets.migosp_Texture, r.getX() + deltaX, r.getY() + deltaY, r.getWidth(), r.getHeight() - 2);
    			}
    			else
    			{
                    batch.draw(assets.migosp_Texture, r.getX() + deltaX, r.getY() + deltaY, r.getWidth(), r.getHeight());
    			}
			}
			@Override
			public void towerAnalysis(Being t, ArrayList<Being> enemies, ArrayList<Being> towers,
					ArrayList<MoveableBeing> bullets, ArrayList<Being> renderList, Trail trail) {
				
				Iterator<Being> it = towers.iterator();
            	while(it.hasNext())
            	{
            		Being _t = it.next();
            		if(t.skills.get(0).rangeTest(t, _t, new int[] {Utils2.getRG(t)}) && _t != t && _t.getType1() != Type1.MIGOSP)
            		{
            			transientlyAddBuff(_t, t);
            		}
            	}
			}
		});
	
		implementers.put(Type1.LOOX,
		new Towerimplementer()
		{
			@Override
			public void render(Being r, SpriteBatch batch, float deltaX, float deltaY) {
				mixColorAndDraw(r, batch, assets.loox_Texture, r.getX() + deltaX, r.getY() + deltaY, r.getWidth(), r.getHeight());
                //batch.draw(assets.froggit_Texture, r.getX() + deltaX, r.getY() + deltaY, r.getWidth(), r.getHeight());
				
			}
			@Override
			public void towerAnalysis(Being t, ArrayList<Being> enemies, ArrayList<Being> towers,
					ArrayList<MoveableBeing> bullets, ArrayList<Being> renderList, Trail trail) {

				buffJudge(t);
            	t.skills.get(0).updateCDTime();
            	if(t.skills.get(0).cdReady(Data.looxSkill0CDArgs[Utils2.getLV(t) - 1]))
            	{
                	Iterator<Being> it = enemies.iterator();
                	while(it.hasNext())
                	{
                		Being z = it.next();
                    	if(t.skills.get(0).rangeTest(t, z, new int[] {Utils2.getRG(t)}) && z.buffArgs[4] <= 0)
                    	{
                    		t.skills.get(0).resetCDTime();
                        	t.skills.get(1).updateCDTime();
                    		Vector2 center = t.getCenter(new Vector2());
                    		Vector2 bulletDestination = Utils2.getNormalBulletDestination(z, center.x, center.y, Data.looxBulletSpeed, trail);
                    		MoveableBeing bullet = Utils2.getBullet(Type1.LOOX_BULLET1, new double[] {center.x, center.y, bulletDestination.x, bulletDestination.y, Data.looxBulletSpeed}, relativeRectangles.get(Drawbase_name.LOOX_BULLET1), Utils2.getLV(t), t.buffArgs);
                    		bullets.add(bullet);
                	        renderList.add(bullet);
                	        t.assigned = z;
                	        break;
                    	}
                	}
                	transientlyRemoveBuff(t);
                	return;
            	}
            	if(t.skills.get(1).cdTime > 0 && t.assigned != null)
            	{
                	t.skills.get(1).updateCDTime();
                	if(t.skills.get(1).cdReady(Data.looxSkill1and2CD))
                	{
                		t.skills.get(1).resetCDTime();
                    	t.skills.get(2).updateCDTime();
                		Vector2 center = t.getCenter(new Vector2());
                		Vector2 bulletDestination = Utils2.getNormalBulletDestination(t.assigned, center.x, center.y, Data.looxBulletSpeed, trail);
                		MoveableBeing bullet = Utils2.getBullet(Type1.LOOX_BULLET2, new double[] {center.x, center.y, bulletDestination.x, bulletDestination.y, Data.looxBulletSpeed}, relativeRectangles.get(Drawbase_name.LOOX_BULLET2), Utils2.getLV(t), t.buffArgs);
                		bullets.add(bullet);
            	        renderList.add(bullet);
                	}
                	transientlyRemoveBuff(t);
                	return;
            	}
            	if(t.skills.get(2).cdTime > 0 && t.assigned != null)
            	{
                	t.skills.get(2).updateCDTime();
                	if(t.skills.get(2).cdReady(Data.looxSkill1and2CD))
                	{
                		t.skills.get(2).resetCDTime();
                		Vector2 center = t.getCenter(new Vector2());
                		Vector2 bulletDestination = Utils2.getNormalBulletDestination(t.assigned, center.x, center.y, Data.looxBulletSpeed, trail);
                		MoveableBeing bullet = Utils2.getBullet(Type1.LOOX_BULLET3, new double[] {center.x, center.y, bulletDestination.x, bulletDestination.y, Data.looxBulletSpeed}, relativeRectangles.get(Drawbase_name.LOOX_BULLET3), Utils2.getLV(t), t.buffArgs);
                		bullets.add(bullet);
            	        renderList.add(bullet);
            	        t.assigned = null;
                	}
            	}
            	transientlyRemoveBuff(t);
			}
		});
	}
	
	void buffJudge(Being r)
	{
		if(r.buffArgs[2] == 0)
		{
			r.buffArgs[0] = 0;
			r.buffArgs[1] = 0;
			r.removeMixedColor(0.86f, 0.68f, 0.86f, this);
		}
		if(r.buffArgs[4] > 0)
		{
			r.buffArgs[4] -= Gdx.graphics.getDeltaTime();
			if(r.buffArgs[4] < 0)
			{
				r.buffArgs[3] = 0;
				r.buffArgs[4] = 0;
				r.removeMixedColor(0.9f, 0.42f, 0.3f, this);
			}
		}
	}
	
	void transientlyRemoveBuff(Being r)
	{
		r.buffArgs[2] = 0;
	}
	
	void transientlyAddBuff(Being r, Being buffer)
	{
		int LV = Utils2.getLV(buffer);
		switch(buffer.getType1())
		{
			case MIGOSP:
			{
				if(r.getType2() == Type2.HERO)
					return;
    			r.buffArgs[0] = Data.migospATBuffArgs[LV - 1];
    			r.buffArgs[1] = Data.migospRGBuffArgs[LV - 1];
				r.buffArgs[2] = 1;
    			r.mixInColor(0.86f, 0.68f, 0.86f);
			}
			case LOOX_BULLET1:
			{
    			r.buffArgs[3] = Data.looxDMGDebuffArgs[LV - 1];
    			r.buffArgs[4] = Data.looxSkill0LastTime[LV - 1];

    			r.mixInColor(0.9f, 0.42f, 0.3f);
			}
			default:break;
		}
	}
	//如果子弹对僵尸造成伤害就返回true 不然就false
	boolean normalBulletAttack(MoveableBeing b, Being z, ArrayList<Being> renderList, Iterator<Being> _it)
	{
    	if(b.overlaps(z))
    	{
    		float damage = b.type2Args[0];
    		if(z.buffArgs[4] > 0)
    		{
    			damage *= (1+z.buffArgs[3]);
    		}
    		//如果有防具优先攻击防具，然后再减本体的血量
    		if(z.type2Args[1] > 0)
        		z.type2Args[1] -= damage;
    		else
    			z.type2Args[0] -= damage;
    		if(deathJudge(z))
    		{
    			level.addEXP((int)z.type2Args[2]);
    			if(z.assigned != null)
    			{
        			z.assigned.assigned = null;
        			z.assigned = null;
    			}
    			//只能用iterator移出，不然会报错
            	goodRemove(z, renderList);
            	if(level.counteringFinalWave)
        		level.finalWaveZombies --;
            	_it.remove();
    		}
    		return true;
    	}
    	return false;
	}
	
	void normalZombieAttack(MoveableBeing b1, Being b2)
	{
		//int LV = (int)b1.type2Args[3];
		if(!b1.attachments.isEmpty())
		{
			for(Being at:b1.attachments)
			{
				at.setStatus(Status.DEFAULTED);
			}
		}
		b1.setStatus(Status.ATTACKING);
		switch(b1.getDrawbaseName())
		{
			case ZOMBIE_WALKING_UP:
			{
				b1.setDrawbaseName(Drawbase_name.ZOMBIE_EATING_UP);
				break;
			}
			case ZOMBIE_WALKING_DOWN:
			{
				b1.setDrawbaseName(Drawbase_name.ZOMBIE_EATING_DOWN);
				break;
			}
			case ZOMBIE_WALKING_LEFT:
			{
				b1.setDrawbaseName(Drawbase_name.ZOMBIE_EATING_LEFT);
				break;
			}
			case ZOMBIE_WALKING_RIGHT:
			{
				b1.fliped = true;
				b1.setDrawbaseName(Drawbase_name.ZOMBIE_EATING_RIGHT);
				break;
			}
			default:{}
		}
		switch(b2.getType2())
		{
			case BLOCKER:
			{
				b2.type2Args[1] -= Utils2.getAT(b1)/Gdx.graphics.getFramesPerSecond();
				break;
			}
			case HERO:
			{
				b2.type2Args[0] -= Utils2.getAT(b1)/Gdx.graphics.getFramesPerSecond();
				break;
			}
			default:{break;}
		}
	}
	
	boolean renderDiedHero(Being h, SpriteBatch batch, float deltaX, float deltaY)
	{
		Hero _h = (Hero)h;
		if(_h.died)
		{
			float reviveProgress = _h.reviveTime/_h.reviveGoalTime;
			h.alpha = reviveProgress;
			h.r = 0.2f;
			h.g = 0.2f;
			h.b = 0.2f;
			renderAnimation(h, batch, deltaX, deltaY);
			return true;
		}
		return false;
	}

	boolean revivingDiedHero(Hero h)
	{
		if(h.died)
		{
			h.reviveTime += Gdx.graphics.getDeltaTime();
			if(h.reviveTime >= h.reviveGoalTime)
			{
				h.died = false;
				h.alpha = 1;
				h.r = 1;
				h.g = 1;
				h.b = 1;
				h.reviveTime = 0;
				h.type2Args[0] = h.originalHP;
			}
			return true;
		}
		return false;
	}
	
	public boolean deathJudge(Being b)
	{
		float HP = 0;
		switch(b.getType2())
		{
			case BLOCKER:
			{
				HP = b.type2Args[1];
				break;
			}
			case ENEMY:
			{
				HP = b.type2Args[0];
				break;
			}
			case HERO:
			{
				HP = b.type2Args[0];
				break;
			}
			default:{break;}
		}
		return HP <= 0;
	}

	void drawHPBar(SpriteBatch batch, float x, float y, float width, float height, float ratio, boolean ishero)
	{
		float leftHPWidth = width * ratio;
		if(leftHPWidth < 0)leftHPWidth = 0;
		if(!ishero)
		batch.setColor(Color.GREEN);
		else
		batch.setColor(Color.YELLOW);
		batch.draw(assets.unit_Texture, x, y, leftHPWidth, height);
		if(!ishero)
		batch.setColor(0.64f, 0.64f, 0.64f, 1);
		else
		batch.setColor(Color.RED);
		batch.draw(assets.unit_Texture, x + leftHPWidth, y, width - leftHPWidth, height);
		batch.setColor(Color.WHITE);
	}
	
	void renderAnimation(Being r, SpriteBatch batch, float deltaX, float deltaY)
	{
		Color c = r.getMixedColor();
		batch.setColor(c.r, c.g, c.b, c.a);
		//绘动画
        Animation<TextureRegion> animation = animations.get(r.getDrawbaseName());
        float stateTime = r.stateTime;
        if(r.getStatus() == Status.DEFAULTED)
        {
        	stateTime = 0;
        }
		TextureRegion currentFrame = animation.getKeyFrame(stateTime);
		Rectangle relativeRectangle = relativeRectangles.get(r.getDrawbaseName());
		if(!r.fliped)
        batch.draw(currentFrame, r.getX() + deltaX, r.getY() + deltaY, relativeRectangle.width, relativeRectangle.height);
		else
	    batch.draw(currentFrame, r.getX() + deltaX + relativeRectangle.width, r.getY() + deltaY, -relativeRectangle.width, relativeRectangle.height);
        //batch.draw(currentFrame, r.getX() + relativeRectangle.x + deltaX, r.getY() + deltaY + relativeRectangle.y, relativeRectangle.width, relativeRectangle.height);
		batch.setColor(Color.WHITE);
	}
	
	void renderHPBar(Being r, SpriteBatch batch, float deltaX, float deltaY, int originalHP, int armorOriginalHP)
	{
		//绘画血条
        float HPRatio = 0;
        if(r.getType2() == Type2.ENEMY)
    	HPRatio = (r.type2Args[0]+r.type2Args[1])/((float)originalHP+(float)armorOriginalHP);
        if(r.getType2() == Type2.BLOCKER)
        HPRatio = r.type2Args[1]/((float)originalHP);
        if(r.getType2() == Type2.HERO)
        HPRatio = r.type2Args[0]/((float)originalHP);
		drawHPBar(batch, r.getX() + deltaX, r.getY() + deltaY + r.getHeight()*1.02625f, r.getWidth(), r.getWidth()*0.13f, HPRatio, r.getType2() == Type2.HERO);
	}
	
	public void render(Being r, SpriteBatch batch, float deltaX, float deltaY)
	{
		Rectangle bl_r = relativeRectangles.get(r.getDrawbaseName());
    	if(bl_r != null)
    	batch.draw(assets.unit_Texture, r.getX() + deltaX, r.getY() + deltaY, bl_r.width, 2);

		implementers.get(r.getType1()).render(r, batch, deltaX, deltaY);
	}
	
	public void mixColorAndDraw(Being r, SpriteBatch batch, Texture texture, float x, float y, float width, float height)
	{
		Color c = r.getMixedColor();
		batch.setColor(c.r, c.g, c.b, c.a);
        batch.draw(texture, x, y, width, height);
	}
	
	public void moveBeings(MoveableBeing b, float x, float y)
	{
		if(b.getMoveType() == MoveType.UNMOVEABLE)return;
		b.translate(x, y);
		switch(b.getType2())
		{
			case ENEMY:
			{
    			Enemyimplementer i = (Enemyimplementer)implementers.get(b.getType1());
    			i.moveBeings(b, x, y);
				break;
			}
			default:{}
		}
	}
	
	public void attack(MoveableBeing attacker, Being attackee)
	{
		switch(attacker.getType2())
		{
			case ENEMY:
			{
    			Enemyimplementer i = (Enemyimplementer)implementers.get(attacker.getType1());
    			i.attack(attacker, attackee);
				break;
			}
			default:{}
		}
	}

	public void onroadmonsterAnalysis(Being m, ArrayList<Being> enemies, ArrayList<Being> towers, ArrayList<MoveableBeing> bullets, ArrayList<Being> renderList, Trail trail)
	{
		if(m.getType2() == Type2.HERO)
		{
			Heroimplementer i = (Heroimplementer)implementers.get(m.getType1());
			i.heroAnalysis((Hero)m, enemies, bullets, renderList, trail);
			return;
		}
	}
	
	public void towerAnalysis(Being t, ArrayList<Being> enemies, ArrayList<Being> towers, ArrayList<MoveableBeing> bullets, ArrayList<Being> renderList, Trail trail)
	{
		if(t.getType2() == Type2.HERO)
		{
			Heroimplementer i = (Heroimplementer)implementers.get(t.getType1());
			i.heroAnalysis((Hero)t, enemies, bullets, renderList, trail);
			return;
		}
		Towerimplementer i = (Towerimplementer)implementers.get(t.getType1());
		i.towerAnalysis(t, enemies, towers, bullets, renderList, trail);
	}
	
	public void heroAnalysis(Hero h, ArrayList<Being> enemies, ArrayList<MoveableBeing> bullets,
			ArrayList<Being> renderList, Trail trail) {

		Heroimplementer i = (Heroimplementer)implementers.get(h.getType1());
		i.heroAnalysis(h, enemies, bullets, renderList, trail);
	}
	
	public void zombieAnalysis(Being z, Iterator<Being> it)
	{
		Enemyimplementer i = (Enemyimplementer)implementers.get(z.getType1());
		i.zombieAnalysis(z, it);
	}
	
	public void normalTrailZombieAnalysis(TrailBeing z, Iterator<Being> it)
	{
		boolean enemyBlocked = false;
		
		if(z.assigned != null)
		{
			Being m = z.assigned;
			if(!m.overlaps(z) || level.renderList.indexOf(m) == -1)
			{
    			z.assigned = null;
    			m.assigned = null;
				enemyBlocked = false;
			}
			attack(z, m);
        	if(deathJudge(m))
        	{
    			if(m.getType2() == Type2.HERO)
    			{
    				level.hero.died = true;
	    			z.assigned = null;
	    			level.hero.assigned = null;
    				enemyBlocked = false;
    			}
    			else
    			{
        			level.fightertile.remove(m.entityRectangle);
                	goodRemove(m, level.renderList);
                	goodRemove(m, level.onroadmonsters);
	    			z.assigned = null;
	    			m.assigned = null;
    				enemyBlocked = false;
    			}
    		}
        	return;
		}
		
    	Iterator<Being> _it = level.onroadmonsters.iterator();
    	while(_it.hasNext())
    	{
    		Being m = _it.next();
    		//assign
    		if(m.overlaps(z))
    		{
    			if(m.getType2() == Type2.HERO && m.assigned == null && !level.hero.died)
    			{
    				//assign
    				m.assigned = z;
    				z.assigned = m;
    				enemyBlocked = true;
    				break;
    			}
    			else if(m.getType2() == Type2.BLOCKER)
    			{
    				z.assigned = m;
    				enemyBlocked = true;
    				break;
    			}
    		}
    	}
    	
    	if(enemyBlocked)
    	{
    		return;
    	}
		//最后再移动僵尸，万一僵尸被挡住了呢？如果僵尸逃逸，就把僵尸移除
    	if(!trailMoveZombie(z, level.trail))
    	{
    		goodRemove(z, level.renderList);
    		if(level.selectedBeing == z)
    		{
    			level.selectedBeing = null;
    		}
    		it.remove();
    		
    		level.HP --;

        	if(level.counteringFinalWave)
    		level.finalWaveZombies --;
    	}
    	//更新僵尸的相对方块
		z.setRelativeRectangle(relativeRectangles.get(z.getDrawbaseName()));
	}
	
	public void beginGOTO(Hero h, Vector2 destination, int position)
	{
		Heroimplementer i = (Heroimplementer)implementers.get(h.getType1());
		i.beginGOTO(h, destination, position);
	}

	public void bulletAnalysis(MoveableBeing bullet, ArrayList<Being> renderList, Iterator<MoveableBeing> it, ArrayList<Being> enemies)
	{
		Bulletimplementer i = (Bulletimplementer)implementers.get(bullet.getType1());
		i.bulletAnalysis(bullet, renderList, it, enemies);
	}
	
	public void goodRemove(Being b, ArrayList<Being> al)
	{
		//只能用iterator移除，不然会报错
    	Iterator<Being> _it = al.iterator();
    	while(_it.hasNext())
    	{
    		Being _b = _it.next();
    		if(_b == b)
    		{
    			_it.remove();
    			break;
    		}
    	}
	}
	
	boolean moveSimpleBullet(MoveableBeing bullet)
	{
		Vector2 instantVelocity = new Vector2((float)bullet.moveArgs[0], (float)bullet.moveArgs[1]);
		bullet.translate(instantVelocity.x, instantVelocity.y);

		Vector2 bulletCenter = bullet.getCenter(new Vector2());
		if(bulletCenter.x >= Level.BATTLEFIELD_WIDTH || bulletCenter.x <= 0 || bulletCenter.y >= Level.BATTLEFIELD_HEIGHT || bulletCenter.y <= 0)
		{
			return false;
		}
		return true;
	}
	
	boolean moveRotatingBulletAboutSinglePoint(MoveableBeing bullet, float angleSpeed, int radius)
	{
		bullet.moveArgs[2] = (int)(bullet.moveArgs[2] + angleSpeed)%360;
		double rad = Math.toRadians(90 - bullet.moveArgs[2]);
		
		float x = (float)bullet.moveArgs[0];
		float y = (float)bullet.moveArgs[1];
		Vector2 position = new Vector2((float)Math.cos(rad)*radius + x, (float)Math.sin(rad)*radius + y);
		bullet.setCenter(position);

		return true;
	}
	//移动子弹 如果子弹在移动过程中逃逸，就返回false
	public boolean MoveBullet(MoveableBeing b)
	{
		Bulletimplementer i = (Bulletimplementer)implementers.get(b.getType1());
		return i.MoveBullet(b);
	}

	//移动僵尸 如果僵尸在移动过程中逃逸出去，就返回false
	public boolean trailMoveZombie(TrailBeing e, Trail trail)
	{
		//充满怪物的能量
		int steps = Utils2.getDeltaTrailPosition(e, 1);
		if(e.moveArgs[2] + steps >= trail.size - 1)
		{
			//如果enemy被移除视为移动失败
			return false;
		}
		if(steps != 0)
		{
			moveBeings(e, trail.get((int)e.moveArgs[2]+steps).x-trail.get((int)e.moveArgs[2]).x, trail.get((int)e.moveArgs[2]+steps).y-trail.get((int)e.moveArgs[2]).y);
			e.moveArgs[2] += steps;
		}
		e.moveArgs[0] = (float)e.moveArgs[0] + (float)e.moveArgs[1]-steps*trail.dstmin;
		//移动成功
		return true;
	}
}
