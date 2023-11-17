package com.lawzoom.companyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

@SpringBootApplication
@EnableFeignClients
public class CompanyServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(CompanyServiceApplication.class, args);

//		try {
//			InetAddress networkIP = findNetworkIP();
//			if (networkIP != null) {
//				System.out.println("Network IP Address: " + networkIP.getHostAddress());
//			} else {
//				System.out.println("Network IP Address not found.");
//			}
//		} catch (SocketException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private static InetAddress findNetworkIP() throws SocketException {
//		try {
//			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
//
//			while (networkInterfaces.hasMoreElements()) {
//				NetworkInterface networkInterface = networkInterfaces.nextElement();
//
//				// Check if the interface is up and not a loopback or virtual interface
//				if (networkInterface.isUp() && !networkInterface.isLoopback() && !networkInterface.isVirtual()) {
//					Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
//
//					while (addresses.hasMoreElements()) {
//						InetAddress address = addresses.nextElement();
//
//						// Assuming you want IPv4 addresses
//						if (address.getHostAddress().matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {
//							return address;
//						}
//					}
//				}
//			}
//		} catch (SocketException e) {
//			throw e;
//		}
//
//		return null;
	}
}
