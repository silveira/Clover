package net.silveiraneto;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
* A clover is like this:
* +---+---+
* | a | b |
* +---+---+
* | c | d |
* +---+---+
* where a, b, c or d can be a clover or nothing.
* 
* We represent this structure as bunch of circles thats why store the radius.
* We also store x and y so we can paint them in the screen. The width is used
* to determine the radius.
* 
* @author Silveira Neto <silveiraneto@gmail.com>
* 
**/
class Clover {
	Clover a = null;
	Clover b = null;
	Clover c = null;
	Clover d = null;
	
	int color = Color.WHITE;
	
	float x, y, radius, width;
	
	/**
	 * Constructor without any parameters to create a clover to fill it later.
	 */
	public Clover() {
		super();
	}
	
	/**
	 * Constructor without other clovers.
	 * 
	 * @param x upper-left corner x position
	 * @param y upper-left corner y position
	 * @param width of two circles.
	 */
	public Clover(float x, float y, float width) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.radius = (int) (width/4.0);
	}
	
	/**
	 * Constructor without coordinates and size but with the 4 other clovers.
	 * 
	 * @param a a clover or null
	 * @param b a clover or null
	 * @param c a clover or null
	 * @param d a clover or null
	 */
	public Clover(Clover a, Clover b, Clover c, Clover d) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}
	
	/**
	 * Update coordinates and size of clover and recursively update all its children.
	 * 
	 * @param x upper-left corner x position
	 * @param y upper-left corner y position
	 * @param width of two circles
	 */
	public void update(float x, float y, float width) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.radius =  (float)(width/4.0);
		float half_width = (float)(width/2.0);
		
		if(this.a!=null)
			this.a.update(x, y, half_width);
		if(this.b!=null)
			this.b.update(x+2*radius, y, half_width);
		if(this.c!=null)
			this.c.update(x, y+2*radius, half_width);
		if(this.d!=null)
			this.d.update(x+2*radius, y+2*radius, half_width);
	}
	

	public void draw(Canvas canvas, Paint paint){
		
		if(color==Color.WHITE){
			Random r = new Random();
			color = Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		}
		
		/* 1st quadrant */
		if(a==null){
			paint.setColor(color);
			canvas.drawCircle(x+radius, y+radius, radius, paint);
		} else {
			a.draw(canvas, paint);
		}
		
		/* 2nd quadrant */
		if(b==null){
			paint.setColor(color);
			canvas.drawCircle(x+3*radius, y+radius, radius, paint);
		} else {
			b.draw(canvas, paint);
		}
		
		/* 3rd quadrant */
		if(c==null){
			paint.setColor(color);
			canvas.drawCircle(x+radius, y+3*radius, radius, paint);
		} else {
			c.draw(canvas, paint);
		}
		
		/* 4rd quadrant */
		if(d==null){
			paint.setColor(color);
			canvas.drawCircle(x+3*radius, y+3*radius, radius, paint);
		} else {
			d.draw(canvas, paint);
		}
	}

	public boolean is_point_in_circle(float pointX, float pointY, float centerX, float centerY, float radius){
		return ((pointX-centerX)*(pointX-centerX) + (pointY-centerY)*(pointY-centerY) <= radius*radius);
	}
	
/*	public boolean is_point_in_square(float pointX, float pointY, float squareX, float squareY, float width, float height){
		return false;
	}
	
	public float center(int quadrant) {
		return 0.0f;
	}*/

	public boolean split(float x, float y){
		/* 1st quadrant */
		if (is_point_in_circle(x, y, this.x + this.radius, this.y + this.radius, this.radius)){
			if(this.a==null){
				this.a = new Clover(this.x, this.y, this.width/2);
			} else {
				this.a.split(x, y);
			}
			return true;
		}
		
		/* 2st quadrant */
		if (is_point_in_circle(x, y, this.x + 3*this.radius, this.y + this.radius, this.radius)){
			if(this.b==null){
				this.b = new Clover(this.x + 2*this.radius, this.y, this.width/2);
			} else {
				this.b.split(x, y);
			}
			return true;
		}
		
		/* 3rd quadrant */
		if (is_point_in_circle(x, y, this.x + this.radius, this.y + 3*this.radius, this.radius)){
			if(this.c==null){
				this.c = new Clover(this.x, this.y+ 2*this.radius, this.width/2);
			} else {
				this.c.split(x, y);
			}
			return true;
		}
		
		/* 4rd quadrant */
		if (is_point_in_circle(x, y,this.x + 3*this.radius, this.y + 3*this.radius, this.radius)){
			if(this.d==null){
				this.d = new Clover(this.x+2*this.radius, this.y+ 2*this.radius, this.width/2);
			} else {
				this.d.split(x, y);
			}
			return true;
		}
		return false;
	}
	
	/* 
	 * Represent the object like Clover(x:15, y:20, width:100, radius:25).
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Clover(x:%d, y:%d, width:%d, radius:%d)", this.x, this.y, this.width, this.radius);
	}
}