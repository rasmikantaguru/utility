package com.test.reader;

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class FileRead {
	
	private static Map<String, String> map = new HashMap<String, String>();
	private static long size;
	private static AtomicInteger count = new AtomicInteger(0);


	public static void read(int skip) throws IOException, FileNotFoundException {
		FileInputStream fis = new FileInputStream("src/main/java/abc.text");
		fis.skip(skip);
		StringBuilder sb = new StringBuilder();
		int ch, i =0;
		while((ch=fis.read()) != -1) {
			sb.append((char)ch);
			i++;
			if (i == 50) {
				break;
			}
		}
		map.put(Thread.currentThread().getName(), sb.toString());
		synchronized (FileRead.class) {
			size += sb.toString().length();
			System.out.println(sb.toString()+" ::length="+sb.length()+"--"+Thread.currentThread().getName()+"->size="+size);
		}
		count.incrementAndGet();
		fis.close();
	}
	
	public static void showFileDetails() {
		StringBuilder sb = new StringBuilder();
		List<String> list = new ArrayList<String>(map.keySet());
		Collections.sort(list);
		System.out.println(list);
		for (String line : list) {
			sb.append(map.get(line));
		}
		System.out.println(sb.toString() + ":: legnth="+ sb.length());
	}
	
	public static Map<String,String> getFileDetails() {
		return map;
	}
	
	public static int getSize() {
		try {
			File file = new File("src/main/java/abc.text");
			size = file.length();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return (int)(size/50);
	}
	
	public static int getCount() {
		return count.get();
	}
}
