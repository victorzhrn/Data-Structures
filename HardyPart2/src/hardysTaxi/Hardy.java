package hardysTaxi;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Provides the static method hardySolutionsLessThan(N) which finds all "taxicab
 * numbers that are less than n. These are represented as TaxicabNumber objects,
 * which include the sum and two different ways of writing that as a sum of two
 * cubes.
 * 
 * @author Claude Anderson.
 * 
 */
public class Hardy {
	

	// TODO: Declare any variables or helper methods that you need.

	/**
	 * Find the nth Hardy number (start counting with 1, not 0) and the numbers
	 * whose cubes demonstrate that it is a Hardy number.
	 * 
	 * @param n
	 * @return the nth Hardy number
	 */
	public static long nthHardyNumber(int n) {
		// TODO: If you have any fields that are arrays or collections,
		// You must clear them at the beginning of each call to this method.
		// This is so that values calculated by previous calls do not speed up
		// subsequent calls.

		// TODO: replace the following dummy statement by real code that
		// calculates
		// and returns the nth Hardy number.
		int count = 0;
		
		long i = 0;
		cubesum prev = new cubesum(0,0);
		PriorityQueue pd = new PriorityQueue<cubesum>();
		PriorityQueue tempd = new PriorityQueue<cubesum>();
		HashSet<Long> set = new HashSet<Long>();
		i = increasePQsize(i, pd, tempd);
		while (count <n){
			cubesum s = (cubesum) pd.remove();
			
			if (prev.getSum() == s.getSum()){
				if(!set.contains(prev.getSum())){
					set.add(prev.getSum());
					count += 1;
				}
//				System.out.println("prev  "+prev.x+","+prev.y);
//				System.out.println("s  "+s.x+","+s.y);
//				System.out.println("sum    "+prev.getSum());
//				count = count +1;
			}else{
				// nothing to do for now
			}
			prev = s;
			if (pd.size()<5){
				i= increasePQsize(i,pd,tempd);
			}
			
		}
		
		return prev.getSum();
		
		

	}

	private static long increasePQsize(long i, PriorityQueue pd,
			PriorityQueue tempd) {
		while (pd.size()<5){
			//System.out.println("size  "+ pd.size());
			cubi tem = findNextCubeSum(tempd,i);
			i = tem.i;
			cubesum c = tem.c;
			pd.add(c);
			
		}
		return i;
	}
	
	public static cubi findNextCubeSum(PriorityQueue<cubesum> pd,long i){
		while (pd.size() == 0 || ((cubesum) pd.peek()).getSum() >= Math.pow(i, 3)){
			pd.add(new cubesum(++i,1));
		}
		cubesum s =  pd.remove();
		//System.out.println(s.x+"  "+s.y);
		if (s.x > s.y+1){
			pd.add(new cubesum(s.x,s.y+1));
		}
		return new cubi(s,i);
	}
	
	public static class cubi {
		private cubesum c;
		private long i;
		
		public cubi(cubesum c, long i){
			this.i = i;
			this.c = c;
		}
	}
	
	public static class cubesum implements Comparable<cubesum>{
		public long x,y,sum;
		
		public cubesum(long x2, long l){
			this.x = x2;
			this.y = l;
			this.sum = x*x*x+y*y*y;
		}

		@Override
		public int compareTo(cubesum that) {
			// TODO Auto-generated method stub
			if (this.sum < that.sum){
				return -1;
			}else if (this.sum > that.sum){
				return 1;				
			}
			return 0;
		}
		
		public long getSum(){
			return this.sum;
		}
	}
	
	

}
