// package com.example.demo;

// import com.hazelcast.client.HazelcastClient;
// import com.hazelcast.client.config.ClientConfig;
// import com.hazelcast.core.HazelcastInstance;

// public class CustomersHZWebClient {
//     ClientConfig clientConfig = new ClientConfig();

//     public CustomersHZWebClient(){
//         this.clientConfig.setClusterName("hello-world");
//         this.clientConfig.getNetworkConfig().addAddress("192.168.1.213:5701");
//     }
//     public HazelcastInstance getClient(){
//         HazelcastInstance client = HazelcastClient.newHazelcastClient(this.clientConfig);
//         return client;
//     }
    
//     // IMap<String, String> mapCustomers = client.getMap("my-distributed-map"); // creates the map proxy

//     // for(IMap.Entry<String, String> entry:mapCustomers.entrySet())
//     // {
//     //     System.out.println(entry.getKey() + " " + entry.getValue());
//     // }

// }
