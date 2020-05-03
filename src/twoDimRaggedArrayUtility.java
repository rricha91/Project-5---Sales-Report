import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class twoDimRaggedArrayUtility {

	
	
	
	public static double[][] readFile(java.io.File file) throws FileNotFoundException {
		
		// Creates a new RandomAccessFile object for the given file, set to write only mode
		RandomAccessFile fi = new RandomAccessFile(file, "r");
		
		//Create an arrayList for an arrayList of doubles for easy manipulation.
		ArrayList<ArrayList<Double>> arLi = new ArrayList<ArrayList<Double>>();
		
		//Declare line counter
		int line=0; 
			
			//
			try {
				
				/*
				 * Loop is in effect until a null character is read,
				 * i.e. the end of the file.
				 */
				while (fi.readChar()!=0) {
					/*
					 * Random access file's seek method goes by bytes. Two bytes
					 * is one character. This is so the end of file check doesnt 
					 * offset the pointer.
					 */
					fi.seek(fi.getFilePointer()-2);
					
					/*
					 * Create a new arrayList of doubles in the main arraylist. 
					 * Adds a new row.
					 */
					arLi.add(new ArrayList<Double>());
					
					/*
					 *  Adds the first double in the line to the inner array.
					 *  Done outside the while loop so the loop's first check can work.
					 */
					arLi.get(line).add(fi.readDouble());
					
					/*
					 *  Loop checks if the upcoming character is a nextLine character.
					 *  This, assuming that the only thing separating each double is one character,
					 *  will also move the pointer into position to read the next double, if there is one.
					 *  
					 *  This repeats until a next line character is found.
					 */
					while (fi.readChar()!=('\n')) arLi.get(line).add(fi.readDouble());
					line++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			};
			
			/*
			 * Black box code. Apparently converts the 2d arrayList into a 2d array of doubles without
			 * having to write out a painfully inefficient loop.
			 */
		double[][] arr =  arLi.stream().map(u -> u.parallelStream().mapToDouble(d->d).toArray()).toArray(double[][]::new);
		
		try {
			fi.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return arr;
	}
	
	public static void writeToFile(double[][] array, java.io.File file) throws FileNotFoundException {
		RandomAccessFile fi;
			fi = new RandomAccessFile(file, "rwd");
		
		int depth=array.length;
		int length;
		for (int d=0, l=0; d<depth;d++) {
		length=array[d].length-1;
			
			try {
				for(l=0;l<length;fi.writeChar(' '))fi.writeDouble(array[d][l++]);
				
				fi.writeDouble(array[d][l]);
				fi.writeChar('\n');
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	/**
	 * Returns the average of the elements in the two dimensional array
	 * @param data
	 * @return
	 */
	static double getAverage(double[][] data) {
		int h=data.length, n=0;
		double tot=0;
		
		/*
		 * Painfully inneficent nested-for loop. Checks the length of each row of the array,
		 * which is then used to define a secondary variable that keeps track of the array's pointer.
		 * 
		 * This pointer makes its way through the array, adding each value to the
		 * a double tracking the total. With each iteration, n is incremented by one 
		 * to track the number of variables 
		 * 
		 * This is done for every row of the parameter array.
		 */
		for (int cou=0, dat; cou<h;cou++) {
				dat=data[cou].length;
				for (int c=0;c<dat;c++) {
					tot+=data[cou][c];
					n++;
				}					
			}
		
		// returns the total divided by the number of numbers.
	return tot/n;
	}
	
	public static double getTotal(double[][] data) {
		int h=data.length;
		double tot=0;
		for (int cou=0, dat; cou<h;cou++) {
				dat=data[cou].length;
				for (int c=0;c<dat;c++) tot+=data[cou][c];
		}
		
	return tot;
	}
	
	/**
	 * Returns the total of the selected row in the two dimensional array index 0 refers to the first row.
	 * @param data
	 * @param row
	 * @return
	 */
	public static double getRowTotal(double[][] data, int row) {
		//Gets the length of the given row.
		int h=data[row].length;
		double tot=0;
		
		/*
		 * The for loop counts through the row by incrementing the variable for column its positioned on,
		 * until reaching the end of the given row. The row variable is identical for each iteration.
		 */
		for (int col=0; col<h;col++) {
				tot+=data[row][col];
		}
	return tot;
	}
	
	/**
	 * Returns the total of the selected column in the two dimensional array index 0 refers to the first column.
	 * @param data
	 * @param col
	 * @return
	 */
	public static double getColumnTotal(double[][] data, int col) {
		int h=data.length;
		double tot=0, n;
		
		
		/*
		 * The for loop counts through the column by incrementing the row variable. This is done until the
		 * the full outer array has been cycled through. The column variable is fixed to the parameter
		 * to ensure the same one is accessed each time.
		 */
		for (int r=0;r<h;r++) {
			
			/*
			 * A try-catch method has been implemented to keep array-out-of-bounds errors
			 * from cropping up. If one of the rows does not extend out to the given column,
			 * n will simply be initialized to 0 before being added to the total.
			 * 
			 * A similar process has been implemented for every method that involves column checks.
			 */
			try {
				n=data[r][col];
				} catch (ArrayIndexOutOfBoundsException e) {
				n=0;
			}
			tot+=n;
		}
	return tot;
	}
	
	/**
	 * Returns the largest element in the two dimensional array
	 * @param data
	 * @return
	 */
	public static double getHighestInArray(double[][] data) {
		int h=data.length;
		double high=0, n;
		for (int cou=0, dat; cou<h;cou++) {
				dat=data[cou].length;
				for (int c=0;c<dat;c++) {
					n=data[cou][c];
					if (n>high) high=n;
				}
		}
		
	return high;
	}
	
	/**
	 * Returns the largest element of the selected row in the two dimensional array index 0 refers to the first row.
	 * @param data
	 * @param row
	 * @return
	 */
	public static double getHighestInRow(double[][] data, int row) {
		int h=data[row].length;
		double high=0/*data[row][0]*/, n;
			for (int c=1;c<h;c++) {
				n=data[row][c];
				if (n>high) high=n;
		}
		
	return high;
	}
	
	/**
	 * Returns index of the largest element of the selected row in the two dimensional array index 0 refers to the first row.
	 * @param data
	 * @param row
	 * @return
	 */
	public static int getHighestInRowIndex(double[][] data, int row) {
		int h=data[row].length, in = 0, c=1;
			for (double high=0/*data[row][0]*/, n;c<h;c++) {
				n=data[row][c];
				if (n>high) {
					high=n;
					in=c;
				}
		}
		
	return in;
	}
	
	/**
	 * Returns the largest element of the selected column in the two dimensional array index 0 refers to the first column.
	 * @param data
	 * @param col
	 * @return
	 */
	public static double getHighestInColumn(double[][] data, int col) {
		int h=data.length, r=0;
		double high=0, n;
		
		/*
		 * Implemented an initial check into all High-Low column methods,
		 * which locates the first valid value in the column that we can
		 * initialize the value tracker to. This check repeats until it either
		 * reaches the bottom of the array, or the value high is initialized.
		 * 
		 * We do this becasue we don't know which lines do or don't reach
		 * the specified column. Since negative numbers are allowed, we must
		 * either set the tracker to the lowest possible value a double can contain,
		 * or have a dynamic way to find the first valid variable.
		 */
		for (boolean Defined=false;!Defined&&r<h;r++) {
			try {
				high=0/*data[r][col]*/;
				Defined=true;
			} catch (ArrayIndexOutOfBoundsException e) {}
		}
		
		/*
		 * If the value tracker manages to get initialized before exausting the column, the following for loop will
		 * check the remainder of the column for any higher values. If not, the loop ends automatically.
		 */
		for (;r<h;r++) {
			try {
				n=data[r][col];
				if (n>high) high=n;
			} catch (ArrayIndexOutOfBoundsException e) {}
		}
		
		return high;
	}
	
	
	/**
	 * Returns index of the largest element of the selected column in the two dimensional array index 0 refers to the first column.
	 * @param data
	 * @param col
	 * @return
	 */
	public static int getHighestInColumnIndex(double[][] data, int col) {
		int h=data.length, in = 0, r=0;
		double high=0, n;
		
		/*
		 * Much the same as the Lowest/Highest initializer function,
		 * but now the 'index pointer' is defined outside the for loop. 
		 */
		for (boolean Defined=false;!Defined&&r<h;r++) {
			try {
				high=0/*data[r][col]*/;
				Defined=true;
			} catch (ArrayIndexOutOfBoundsException e) {}
		}
		
		/*
		 *  After the loop, if the pointer (r) is within the 'column limit' (h), the
		 *  newly declared index tracker will be initialized to the value of the pointer.
		 *  If not, the tracker is defined to -1, indicating an empty column fully outside bounds,
		 *  and returned immediately.
		 */
		in = r<h ? r :-1;
		if (in==-1)return in;
		
		
		/*
		 * Same as the secondary function of the Lowest/Highest value method,
		 * but it also updates the tracker whenever a new lowest/highest value is found.
		 */
		for (;r<h;r++) {
			try {
				n=data[r][col];
				if (n>high) {
					high=n;
					in=r;
				}
			} catch (ArrayIndexOutOfBoundsException e) {}
		}	
	return in;
	}
	
	/**
	 * Returns the smallest element in the two dimensional array
	 * @param data
	 * @return
	 */
	public static double getLowestInArray(double[][] data) {
		int h=data.length;
		double low=0/*data[0][0]*/, n;
		for (int cou=0, dat; cou<h;cou++) {
				dat=data[cou].length;
				for (int c=0;c<dat;c++) {
					n=data[cou][c];
					if (n<low) low=n;
				}
		}
		
	return low;
	}
	
	/**
	 * Returns the smallest element of the selected row in the two dimensional array index 0 refers to the first row.
	 * @param data
	 * @param row
	 * @return
	 */
	public static double getLowestInRow(double[][] data, int row) {
		int h=data[row].length;
		double low=0/*data[row][0]*/, n;
			for (int col=1;col<h;col++) {
				n=data[row][col];
				if (n<low) low=n;
		}
		
	return low;
	}
	
	/**
	 * Returns index of the smallest element of the selected row in the two dimensional array index 0 refers to the first row.
	 * @param data
	 * @param row
	 * @return
	 */
	public static int getLowestInRowIndex(double[][] data, int row) {
		int h=data[row].length, in = 0, c=0;
			for (double low=0/*data[row][c++]*/, n;c<h;c++) {
				n=data[row][c];
				if (n<low) {
					low=n;
					in=c;
				}
		}
		
	return in;
	}
	
	/**
	 * Returns the smallest element of the selected column in the two dimensional array index 0 refers to the first column.
	 * @param data
	 * @param col
	 * @return
	 */
	public static double getLowestInColumn(double[][] data, int col) {
		int h=data.length, r=0;
		double low=0, n;
		
		/*
		 * Implemented an initial check into all High-Low column methods,
		 * which locates the first valid value in the column that we can
		 * initialize the value tracker to. This check repeats until it either
		 * reaches the bottom of the array, or the value high is initialized.
		 * 
		 * We do this becasue we don't know which lines do or don't reach
		 * the specified column. Since negative numbers are allowed, we must
		 * either set the tracker to the lowest possible value a double can contain,
		 * or have a dynamic way to find the first valid variable.
		 */
		for (boolean Defined=false;!Defined&&r<h;r++) {
			try {
				low=0/*data[r][col*/;
				Defined=true;
			} catch (ArrayIndexOutOfBoundsException e) {}
		}
		
		/*
		 * If the value tracker manages to get initialized before exausting the column, the following for loop will
		 * check the remainder of the column for any higher values. If not, the loop ends automatically.
		 */
		for (;r<h;r++) {
			try {
				n=data[r][col];
				if (n<low) low=n;
			} catch (ArrayIndexOutOfBoundsException e) {}
		}
		
		return low;
	}
	
	/**
	 * Returns index of the smallest element of the selected column in the two dimensional array index 0 refers to the first column.
	 * @param data
	 * @param col
	 * @return
	 */
	public static int getLowestInColumnIndex(double[][] data, int col) {
		int h=data.length, in = 0, r=0;
		double low=0, n;
		
		/*
		 * The for loop counts through the column by incrementing the row variable. This is done until the
		 * the full outer array has been cycled through. The column variable is fixed to the parameter
		 * to ensure the same one is accessed each time.
		 */
		for (boolean Defined=false;!Defined&&r<h;r++) {
			try {
				low=0/*data[r][col]*/;
				Defined=true;
			} catch (ArrayIndexOutOfBoundsException e) {}
		}
		in = r<h ? r :-1;
		if (in==-1)return in;
		
		/*
		 *  After the loop, if the pointer (r) is within the 'column limit' (h), the
		 *  newly declared index tracker will be initialized to the value of the pointer.
		 *  If not, the tracker is defined to -1, indicating an empty column fully outside bounds,
		 *  and returned immediately.
		 */
		for (;r<h;r++) {
			try {
				n=data[r][col];
				if (n<low) {
					low=n;
					in=r;
				}
			} catch (ArrayIndexOutOfBoundsException e) {}
		}	
	return in;
	}
	
	
}
