package engine;

public class Maths {
	
	public static float noise(long x) {		
		x = (x<<13) ^ x;
	    return (float) ( 1.0 - ( (x * (x * x * 15731 + 789221) + 1376312589) & 2147483647) / 1073741824.0);
	}
	
	public static float lerp(float a, float b, float x) {
		return a * (1 - x) + b * x;
	}
	
	public static float costerp(float a, float b, float x) {
		double ft = x * Math.PI;
		double f = (1 - Math.cos(ft)) * 0.5;

		return  (float) (a*(1-f) + b*f);
	}
	
	
	public static float smoothedNoise(int x) {
		return noise(x)/2 + noise(x-1)/4 + noise(x+1)/4;
	}
	
	public static float interpolatedNoise(float x) {
		int x_int = (int) x;
		float fractional = x - x_int;
		
		return costerp(smoothedNoise(x_int), smoothedNoise(x_int + 1), fractional);
	}
	
	public static float perlinNoise(float x) {
		float total = 0;
		float persistence = 0.25f;
		int octaves = 6;
		for (int k = 0; k < octaves; k++) {
			float frequency = (float) Math.pow(2, k);
			float amplitude = (float) Math.pow(persistence, k);
			total += interpolatedNoise(x * frequency) * amplitude;
		}
		return total;
	}
	
	
}
