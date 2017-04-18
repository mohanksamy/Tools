package com.tools.m2c;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import com.sun.jna.platform.FileUtils;

public class DirectoryLocation {
	

	public static void main(String[] args) {
		String key = System.getProperty("user.home");

		//repository path
		String path = key + "\\.m2\\repository\\";
			
		File mainFolder = new File(path);

		DirectoryLocation lf = new DirectoryLocation();
		System.out.println("path ::" + path);
		lf.getFiles(mainFolder);
	}

	public void getFiles(File f) {
		
		File files[];
		
		if ( f.isFile() ) {
			
			System.out.println("absolutePath :: " + f.getAbsolutePath());
			
			if ( f.getAbsolutePath().endsWith(".jar") ) {
				
				//System.out.println(f.getAbsolutePath().endsWith(".jar"));
				System.out.println("file path ::" + f.getAbsolutePath());
				System.out.println("file size in byte :" + f.length());
				SimpleDateFormat sfd = new SimpleDateFormat();
				System.out.println("Last Modified Date ::" + sfd.format(f.lastModified()));
				System.out.println("artifactId :: " + f.getName());
				System.out.println(" path:: " + f.getPath());
				System.out.println("folder:::"+f.getParent());
				System.out.println("parent folder ::"+ f.getParentFile());
				
				
				String[] arg = f.getName().split("-");
				System.out.println("getname ::" + arg[0]);
				System.out.println("version ::" + arg[1]);
				
				
				FileUtils fileUtils = FileUtils.getInstance();
				
				if ( fileUtils.hasTrash() ) {
					
					try {
						fileUtils.moveToTrash( new File[] {new File(f.getAbsolutePath())});
						System.out.println("file successfully moved to recycle bin ");
						
				    } catch (IOException ioe) {
				    	ioe.printStackTrace();
				    }
				
				} else {
					System.out.println("No Trash available");
				}
			
				/*System.out.println("can Read the file :" + f.canRead());
				System.out.println("Parent folder :: " + f.getParent());
				System.out.println("get file Name:: " + f.getName());
	        	System.out.println(" path:: " + f.getPath());*/
	        	
				System.out.println("=====================================================");
				
			}
	    } else {
	        
	    	files = f.listFiles();
	        
	        for ( int i = 0; i < files.length; i++ ) {
	            getFiles(files[i]);
	        }
	    }
	 }
}