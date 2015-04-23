
package com.makerstudios.aud;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Findhost {

	public static boolean isWindows() {
		String os = System.getProperty("os.name").toLowerCase();
		return (os.indexOf("win") >= 0);
	}

	public static boolean isMac() {
		String os = System.getProperty("os.name").toLowerCase();
		return (os.indexOf("mac") >= 0);
	}

	public static boolean isUnix() {
		String os = System.getProperty("os.name").toLowerCase();
		return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0);
	}

	public static boolean isSolaris() {
		String os = System.getProperty("os.name").toLowerCase();
		return (os.indexOf("sunos") >= 0);
	}

	public static String hostname() {
		try {
			InetAddress localMachine = InetAddress.getLocalHost();
			return localMachine.getHostName();
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}
}