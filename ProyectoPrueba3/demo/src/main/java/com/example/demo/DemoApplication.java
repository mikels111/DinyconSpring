package com.example.demo;

import java.util.Map;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		ClientConfig clientConfig = new ClientConfig();
		clientConfig.setClusterName("hello-world");
		clientConfig.getNetworkConfig().addAddress("192.168.1.213:5701");

		HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);

		IMap<String, String> mapCustomers = client.getMap("my-distributed-map"); // creates the map proxy

		for (Map.Entry<String, String> entry : mapCustomers.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}

		// mapCustomers.put("4", "Joe");
		// mapCustomers.put("5", "Ali");
		// mapCustomers.put("6", "Avi");

		// System.out.println(mapCustomers.get('1'));
		// System.out.println(mapCustomers.get('2'));
		// System.out.println(mapCustomers.get('3'));

	}

}
