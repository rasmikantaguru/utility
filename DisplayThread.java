package com.test.reader;

public class DisplayThread implements Runnable {
	
	public void run() {
		while (true) {
			//System.out.println(FileRead.getCount());
			if (FileRead.getCount() == 3) {
				FileRead.showFileDetails();
				break;
			} 
		}
	}

	
}
