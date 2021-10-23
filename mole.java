import java.util.*;
import java.io.*;

/*
The main observation in this problem is that when two points collide,
we swap the lines representing the points.
*/

public class mole{

	static double epsilon = 1e-6;

	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		for(int q = 1; q <= t; q++){
			int n = scan.nextInt();

			// maintain the index of each point
			int[] index = new int[n];
			for(int i = 0; i < n; i++) index[i] = i;

			Seg[] pts = new Seg[n];

			// Create points
			for(int i = 0; i < n; i++){
				Vec p = new Vec(scan.nextInt(), scan.nextInt());
				Vec d = new Vec(scan.nextInt(), scan.nextInt()).unit();

				pts[i] = new Seg(p, p.add(d));
			}

			// Compute collisions between points
			ArrayList<Collision> cols = new ArrayList<Collision>();
			for(int i = 0; i < n; i++){
				for(int j = i+1; j < n; j++){
					Vec isct = pts[i].lineIntersect(pts[j]);
					// parallel lines case
					if(isct == null){
						// same line
						if(pts[i].sameLine(pts[j].from)){
							// opposite direction
							if(!pts[i].dir.unit().equals(pts[j].dir.unit())){
								Seg extendo = new Seg(
									pts[i].from, 
									pts[i].from.add(pts[i].dir.scale(10_000_000))
									);
								// facing each other
								if(extendo.containsPoint(pts[j].from)){
									// in this case, the collision happens at the midpoint
									Vec midpoint = pts[i].from.add(pts[j].from).scale(0.5);
									double time = dist(pts[i].from, midpoint);
									cols.add(new Collision(i, j, time));
								}
							}
						}
						continue;
					}

					// to determine if the points collide, measure distance to intersection
					double distA = dist(pts[i].from, isct);
					double distB = dist(pts[j].from, isct);

					if(Vec.eq(distA, distB)){
						// they have collided
						// the time is equal to the distance

						// let's also make sure that the lines didn't intersect backwards in time
						Seg extendoI = new Seg(
									pts[i].from, 
									pts[i].from.add(pts[i].dir.scale(10_000_000))
									);
						Seg extendoJ = new Seg(
									pts[j].from, 
									pts[j].from.add(pts[j].dir.scale(10_000_000))
									);

						if(extendoI.containsPoint(isct) && extendoJ.containsPoint(isct)){
							cols.add(new Collision(i, j, distA));
						}

					}
				}
			}

			int[] ans = new int[n];

			// sort the collisions by time
			Collections.sort(cols);
			for(Collision c : cols){
				// take the two points which collided and swap their lines
				ans[index[c.i]]++;
				ans[index[c.j]]++;
				int temp = index[c.i];
				index[c.i] = index[c.j];
				index[c.j] = temp;
			}

			// print the collisions!
			for(int i = 0; i < n; i++){
				System.out.println(ans[i]);
			}
		}
	}

	// distance between two vectors
	static double dist(Vec a, Vec b){
		return a.sub(b).mag();
	}

	// Collision class stores indeces of two molecules involved in collision and time of collision
	static class Collision implements Comparable<Collision>{

		int i;
		int j;
		double time;

		public Collision(int ii, int jj, double tt){
			i = ii;
			j = jj;
			time = tt;
		}

		public int compareTo(Collision o){
			return Double.compare(time, o.time);
		}
	}

	// Segment class which we use to represent a moving point
	static class Seg {

		Vec from, to, dir;
	
		public Seg(Vec from, Vec to) {
			this.from=from;
			this.to=to;
			dir=to.sub(from);
		}
	
		//line-line intersection
		public Vec lineIntersect(Seg o) {
			double det=o.dir.x*dir.y-dir.x*o.dir.y;
			if (Vec.eq(det, 0)) return null;
			double dist=(o.dir.x*(o.from.y-from.y)-
					o.dir.y*(o.from.x-from.x))/det;
			return from.add(dir.scale(dist));
		}

		public boolean containsPoint(Vec o) {
			double distFromLine=dir.unit().cross(o.sub(from));
			if (!Vec.eq(distFromLine, 0)) return false;
			return Vec.eq(dir.mag(), from.sub(o).mag()+to.sub(o).mag());
		}

		public boolean sameLine(Vec o) {
			return projectToLine(o).equals(o);
		}

		public Vec projectToLine(Vec o) {
			return dir.scale(o.sub(from).dot(dir)/dir.mag2()).add(from);
		}
	}

	// Vector class supporting operations on vectors
	static class Vec{

		double x;
		double y;

		public Vec(double xx, double yy){
			x = xx;
			y = yy;
		}

		public Vec add(Vec o){
			return new Vec(x+o.x, y+o.y);
		}

		public Vec sub(Vec o){
			return new Vec(x-o.x, y-o.y);
		}

		public Vec scale(double o){
			return new Vec(x*o, y*o);
		}

		public static boolean eq(double a, double o){
			return Math.abs(a-o) < epsilon;
		}

		public Vec unit(){
			return this.scale(1/this.mag());
		}

		public double mag2(){
			return this.dot(this);
		}

		public double mag(){
			return Math.sqrt(x*x+y*y);
		}

		public double dot(Vec v){
			return x*v.x+y*v.y;
		}

		public double cross(Vec v){
			return x*v.y-y*v.x;
		}

		public boolean equals(Vec o){
			return eq(x, o.x) && eq(y, o.y);
		}
	}
}