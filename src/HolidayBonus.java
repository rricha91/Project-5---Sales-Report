import java.util.ArrayList;

public class HolidayBonus {
	
		public static double[] calculateHolidayBonus (double[][] sales, double highBonus, double lowBonus, double defBonus) {
			System.out.println("Progress Check");
			int depth=sales.length, length=0;
			ArrayList<ArrayList<Double>> bonusArLi = new ArrayList<ArrayList<Double>>(); 
			for (int d=0,len,l; d<depth;d++) {
				len=sales[d].length;
				System.out.print(d);
				bonusArLi.add(new ArrayList<Double>());
				System.out.println (" " + len);
				for (l=0; l<len;l++) {
					bonusArLi.get(l).add(defBonus);
					System.out.println(" " + l);
				}
			}
			
			for (int len=0, 
					h = twoDimRaggedArrayUtility.getHighestInColumnIndex(sales, len), 
					l = twoDimRaggedArrayUtility.getHighestInColumnIndex(sales, len); 
					(len<length)||((h!=-1) && (l!=-1)); len++) {
						
						bonusArLi.get(h).set(len, highBonus);
						if (h!=l) bonusArLi.get(l).set(len, lowBonus);
						}
			
			double[] bonus= new double[depth];
			
			double[][] bonusRaw =  bonusArLi.stream().map(u -> u.parallelStream().mapToDouble(d->d).toArray()).toArray(double[][]::new);
			bonusArLi=null;
			for (int d=0; d<depth; d++) bonus[d]=twoDimRaggedArrayUtility.getRowTotal(bonusRaw,d);
			bonusRaw=null;
			
			return bonus;
		}
		
		public static double calculateTotalHolidayBonus (double[][] sales, double highBonus, double lowBonus, double defBonus) {

			int depth=sales.length, length=1;
			ArrayList<ArrayList<Double>> bonusArLi = new ArrayList<ArrayList<Double>>();
//			double[][] Ar=new double[depth][];
			
			for (int d=0,len,l; d<depth;d++) {
				len=sales[d].length;
				if (len>length) length=len;
				
				bonusArLi.add(new ArrayList<Double>());
//				Ar[d] = new double[len];
				
				for (l=0; l<len;l++) {
					bonusArLi.get(l).add(defBonus);
//					Ar[d][l]=defBonus;
				}
			}
			
			for (int len=0, 
					h = twoDimRaggedArrayUtility.getHighestInColumnIndex(sales, len), 
					l = twoDimRaggedArrayUtility.getHighestInColumnIndex(sales, len); 
					((len<length)&&((h!=-1) && (l!=-1))); len++) {
						bonusArLi.get(h).set(len, highBonus);
//					Ar[h][len] = highBonus;					
						if (h!=l) {
							bonusArLi.get(l).set(len, lowBonus);

//						Ar[l][len] = lowBonus;
						
						}
				}
			
			double bonusTotal=0;
			
			double[][] bonusRaw =  bonusArLi.stream().map(u -> u.parallelStream().mapToDouble(d->d).toArray()).toArray(double[][]::new);
			bonusArLi=null;
			
			for (int d=0; d<depth; d++) bonusTotal+=twoDimRaggedArrayUtility.getTotal(bonusRaw);
			bonusRaw=null;
			
//			double bonusTotal+=twoDimRaggedArrayUtility.getTotal(Ar);
			
			return bonusTotal;
		}


//public static double[] calculateHolidayBonus (double[][] sales, double highBonus, double lowBonus, double defBonus) {
//
//		int depth=sales.length;
//			
//		int[][] LowestHighest= new int[depth][2];
//		for (int len=0, 
//				l = twoDimRaggedArrayUtility.getHighestInColumnIndex(sales, len), 
//				h = twoDimRaggedArrayUtility.getHighestInColumnIndex(sales, len);
//				(h!=-1) && (l!=-1); len++) {
//				LowestHighest[h][1]++;
//				if (h!=l) LowestHighest[l][0]++;
//			}
//		
//		double[] bonus= new double[depth];
//		for (int d=0; d<depth;d++) {
//			
//			int low = LowestHighest[d][0], high = LowestHighest[d][1],
//					o = sales[d].length - low - high;
//			
//			bonus[d] = o*defBonus + low*lowBonus + high*highBonus;
//		}
//		
//		LowestHighest=null;
//		
//		return bonus;
//	}
//	
//	public static double calculateTotalHolidayBonus (double[][] sales, double highBonus, double lowBonus, double defBonus) {
//		
// 		int depth=sales.length;
//			
//		int[][] LowestHighest= new int[depth][2];
//		for (int len=0, 
//				l = twoDimRaggedArrayUtility.getHighestInColumnIndex(sales, len),
//				h = twoDimRaggedArrayUtility.getHighestInColumnIndex(sales, len);
//				(h!=-1) && (l!=-1); len++) {
//				
//				LowestHighest[h][1]++;
//				if (h!=l) LowestHighest[l][0]++;
//			}
//		
//		double bonusTotal=0;
//		for (int d=0; d<depth;d++) {
//			
//			int low = LowestHighest[d][0], high = LowestHighest[d][1],
//					o = sales[d].length - low - high;
//						bonusTotal += o*defBonus + low*lowBonus + high*highBonus;
//		}
//
//		LowestHighest=null;
//		
//		
//		return bonusTotal;
}