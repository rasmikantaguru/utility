package com.test.reader;

public class ReaderThread implements Runnable {
	
	private int skip;
	
	public ReaderThread(int skip) {
		this.skip = skip;
	}

	public void run() {
		try {
			FileRead.read(skip);
			//FileRead.class.notify();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new ReaderThread(0), "1");
		Thread t2 = new Thread(new ReaderThread(50), "2");
		Thread t3 = new Thread(new ReaderThread(100), "3");
		Thread t4 = new Thread(new DisplayThread(), "Show");

		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}
