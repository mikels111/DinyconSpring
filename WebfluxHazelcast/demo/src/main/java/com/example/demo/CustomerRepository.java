package com.example.demo;

import java.util.Map;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.SerializationConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class CustomerRepository {


    static IMap<String,Customer> customersMap;
    static HazelcastInstance client;
    static{

        SerializationConfig serializationConfig = new SerializationConfig();
        serializationConfig.setAllowOverrideDefaultSerializers(true);

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setClusterName("hello-world");
        clientConfig.getNetworkConfig().addAddress("192.168.1.213");
        clientConfig.setSerializationConfig(serializationConfig);
        
        // HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        client=HazelcastClient.newHazelcastClient(clientConfig);
        // customersMap=client.getMap("Customer-map");
    }

    public Mono<Customer> findCustomerById(String id){
        Map<String,Customer> mapa=client.getMap("Customer-map");
        return Mono.just(mapa.get(id));
    }

    public Flux<Customer> findAllCustomers(){
        // return Flux.fromIterable(customersMap.values());
        Map<String,Customer> mapa=client.getMap("Customer-map");
        System.out.println("CUSTOMERS KEYS------------------------------>"+mapa.keySet());
        return Flux.fromIterable(mapa.values());
    }

    public Flux<Customer> putCustomer(String key,String name){
        Customer mycustomer=new Customer(key,name);
        client.getMap("Customer-map").put(key, mycustomer);
        Map<String,Customer> mapa=client.getMap("Customer-map");
        return Flux.fromIterable(mapa.values());
    }

    public Flux<Customer> deleteCustomer(String key){
        client.getMap("Customer-map").delete(key);
        Map<String,Customer> mapa=client.getMap("Customer-map");
        return Flux.fromIterable(mapa.values());
    }
    // public Flux<Customer> deleteCustomer(String)

}
