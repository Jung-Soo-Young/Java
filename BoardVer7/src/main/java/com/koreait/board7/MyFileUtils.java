package com.koreait.board7;

import java.io.File;

public class MyFileUtils {
	// 폴더 삭제
	public static void delFolder(String path) {
		File folder = new File(path);
		while(folder.exists()) {
			File[] fileList = folder.listFiles();
			if(fileList == null) {
				return;
			}
			for (int j=0; j<fileList.length; j++) {
				File f = fileList[j];
				if(f.isDirectory()) {
					delFolder(f.getPath());
				} else {
					f.delete();
				}
			}
			folder.delete();
		}
	}
}
