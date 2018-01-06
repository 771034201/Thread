package com.ws.Thread;

public class Sort {
	
	public static void main(String[] args) {
		int arr[] = {2,1,3,5,4};
		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < arr.length-1; j++) {
				if(arr[j]>arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		for (int i : arr) {
			System.out.println(i);
		}
	}
}
