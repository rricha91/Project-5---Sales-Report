

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TwoDimRaggedArrayUtilitySTUDENT_Test {
	//STUDENT fill in dataSetSTUDENT with values, it must be a ragged array
	private double[][] dataSetSTUDENT = {{1.1,2.2,-1.1},{2.2,-3.3},{4.4}};
	
	private File inputFile,outputFile;

	@Before
	public void setUp() throws Exception {
		outputFile = new File("TestOut.txt");
	}

	@After
	public void tearDown() throws Exception {
		dataSetSTUDENT = null;
		inputFile = outputFile = null;
	}

	/**
	 * Student Test getTotal method
	 * Return the total of all the elements in the two dimensional array
	 */
	@Test
	public void testGetTotal() {
		assertEquals(5.5,twoDimRaggedArrayUtility.getTotal(dataSetSTUDENT),.001);
	}

	/**
	 * Student Test getAverage method
	 * Return the average of all the elements in the two dimensional array
	 */
	@Test
	public void testGetAverage() {
		assertEquals(5.5/6,twoDimRaggedArrayUtility.getAverage(dataSetSTUDENT),.001);
	}

	/**
	 * Student Test getRowTotal method
	 * Return the total of all the elements of the row.
	 * Row 0 refers to the first row in the two dimensional array
	 */
	@Test
	public void testGetRowTotal() {
		assertEquals(2.2,twoDimRaggedArrayUtility.getRowTotal(dataSetSTUDENT,0),.001);	
		assertEquals(-1.1,twoDimRaggedArrayUtility.getRowTotal(dataSetSTUDENT,1),.001);	
		assertEquals(4.4,twoDimRaggedArrayUtility.getRowTotal(dataSetSTUDENT,2),.001);	
		
	}


	/**
	 * Student Test getColumnTotal method
	 * Return the total of all the elements in the column. If a row in the two dimensional array
	 * doesn't have this column index, it is not an error, it doesn't participate in this method.
	 * Column 0 refers to the first column in the two dimensional array
	 */
	@Test
	public void testGetColumnTotal() {
		assertEquals(7.7,twoDimRaggedArrayUtility.getColumnTotal(dataSetSTUDENT,0),.001);	
		assertEquals(-1.1,twoDimRaggedArrayUtility.getColumnTotal(dataSetSTUDENT,1),.001);	
		assertEquals(-1.1,twoDimRaggedArrayUtility.getColumnTotal(dataSetSTUDENT,2),.001);	
	}


	/**
	 * Student Test getHighestInArray method
	 * Return the largest of all the elements in the two dimensional array.
	 */
	@Test
	public void testGetHighestInArray() {
		assertEquals(4.4,twoDimRaggedArrayUtility.getHighestInArray(dataSetSTUDENT),.001);	
	}
	

	/**
	 * Test the writeToFile method
	 * write the array to the outputFile File
	 * then read it back to make sure formatted correctly to read
	 * @throws IOException 
	 * 
	 */
	@Test
	public void testWriteToFile() throws IOException {
		double[][] array=null;
		twoDimRaggedArrayUtility.writeToFile(dataSetSTUDENT, outputFile);
		array = twoDimRaggedArrayUtility.readFile(outputFile);
		assertEquals(array[0][0],dataSetSTUDENT[0][0],.001);	
		assertEquals(array[0][1],dataSetSTUDENT[0][1],.001);
		assertEquals(array[0][2],dataSetSTUDENT[0][2],.001);
		
		assertEquals(array[1][0],dataSetSTUDENT[1][0],.001);	
		assertEquals(array[1][1],dataSetSTUDENT[1][1],.001);
		
		assertEquals(array[2][0],dataSetSTUDENT[2][0],.001);
	}

}
