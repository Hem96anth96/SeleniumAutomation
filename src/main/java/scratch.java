import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


	
	public class scratch {

		 public static void main(String[] args) {
			  Scanner scan = new Scanner(System.in);
			  System.out.print("Enter the number to calculate its count Numbers : ");
			  int num = scan.nextInt();
			  CountOfNumber(num);

			 }

			 private static void CountOfNumber(int num) {
			  int count =0;
			  while(num !=0) {
			   num = num/10;
			   count++;
			  }
			  System.out.println(count);
			 }
}

		  

