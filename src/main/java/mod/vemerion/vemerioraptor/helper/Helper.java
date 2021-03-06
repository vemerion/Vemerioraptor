package mod.vemerion.vemerioraptor.helper;

public class Helper {
	
	public static final float PI = (float) Math.PI;

	public static int color(int r, int g, int b, int a) {
		a = (a << 24) & 0xFF000000;
		r = (r << 16) & 0x00FF0000;
		g = (g << 8) & 0x0000FF00;
		b &= 0x000000FF;

		return a | r | g | b;
	}
	
	public static float toRad(double deg) {
		return (float) Math.toRadians(deg);
	}
}