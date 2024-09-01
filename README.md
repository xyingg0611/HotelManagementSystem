package shellsort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class shellSort {
	
	public static void main(String[] args) {
		shellSortArrayList();
		//shellSortLinkedList();
		//shellSortCompareNumber();
	}
	
	public static void shellSortArrayList() {
		//create ArrayList
		ArrayList <String> numList = new ArrayList<>();
		
		//read file
		try(BufferedReader br = new BufferedReader (new FileReader("largenumberdataset.txt"))){
			String line;
			
			while ((line = br.readLine())!= null) {
				numList.add(line);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Numbers: ");
		for (String number : numList) {
			System.out.println(number);
		}
		//convert array list to array
		String [] numArrayList = numList.toArray(new String[0]);
		
		//sorting method
		shellSort.shellSort(numArrayList);
		
		ArrayList<String> sortedNumArray = new ArrayList<>();
		for (String number : numArrayList) {
			sortedNumArray.add(number);
		}
		
		System.out.println("Sorted Number: ");
		for (String number : numArrayList) {
			System.out.println(number);
		}
	}

	 /* An utility function to print array of size n*/
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");	 			
        System.out.println();
    }
    
    /* function to sort arr using shellSort */
    public static void shellSort(String arr[])
    {
        int n = arr.length;

        // Start with a big gap, then reduce the gap
        for (int gap = n/2; gap > 0; gap /= 2)
        {
            // Do a gapped insertion sort for this gap size.
            // The first gap elements a[0..gap-1] are already
            // in gapped order keep adding one more element
            // until the entire array is gap sorted
            for (int i = 1; i < n; i ++)
            {
                // add a[i] to the elements that have been gap
                // sorted save a[i] in temp and make a hole at
                // position i
                String temp = arr[i];

                // shift earlier gap-sorted elements up until
                // the correct location for a[i] is found
                int j;
                for (j = i; j >= gap && arr[j - gap].compareTo(temp) > 0; j -= gap)
                    arr[j] = arr[j - gap];

                // put temp (the original a[i]) in its correct
                // location
                arr[j] = temp;
            }
        }
        return; 
    }


}
