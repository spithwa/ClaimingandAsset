package com.maker.helpers.sql;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;

public class S3Helper {
	//public static final String BUCKET = "maker_analytics/aud_report";
	public static final String BUCKET = "maker_analytics/shadow_youtube";
	
	AmazonS3 s3;
	TransferManager tx;
	private long execTime;
	public S3Helper() throws IOException {
		FileInputStream propFile = new FileInputStream("src/AwsCredentials.properties");
		Properties p = new Properties(System.getProperties());
		p.load(propFile);
		System.setProperties(p);
		s3 = new AmazonS3Client(new DefaultAWSCredentialsProviderChain());
		tx = new TransferManager(s3);
	}

	public long putFile(String filePath, String fileName) {
		execTime = -1;
		try {
			long t1 = System.currentTimeMillis();
			File newFile = new File(filePath+fileName);
			Upload upload = tx.upload(BUCKET, newFile.getName(), newFile);
			System.out.println("Starting : Upload to S3 - File Name :"+fileName);
			upload.waitForCompletion();
			System.out.println("Finished : Uploading to S3");
			tx.shutdownNow();
			execTime = (System.currentTimeMillis()-t1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return execTime;
	}

	public static void main(String[] args) throws IOException {
		S3Helper s3Helper = new S3Helper();
		
	}
}
