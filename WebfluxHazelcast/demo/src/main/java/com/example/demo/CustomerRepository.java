package com.example.demo;

import java.util.Map;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.SerializationConfig;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.HazelcastInstance;

import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class CustomerRepository {


    static Map<String,Customer> customersMap;
    static{

        SerializationConfig serializationConfig = new SerializationConfig();
        serializationConfig.setAllowOverrideDefaultSerializers(true);

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setClusterName("hello-world");
        clientConfig.getNetworkConfig().addAddress("192.168.1.213:5701");
        clientConfig.setSerializationConfig(serializationConfig);
        
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        customersMap=client.getMap("my-distributed-map");
    }

    public Mono<Customer> findCustomerById(String id){
        return Mono.just(customersMap.get(id));
    }

    public Flux<Customer> findAllCustomers(){
        return Flux.fromIterable(customersMap.values());
    }

    public Flux<Customer> putCustomer(String key,String customer){
        Customer myCustomer = new Customer(key,customer);
        if(!customersMap.containsValue(myCustomer)){
            Mono.just(customersMap.put(key, myCustomer));
        }
        return Flux.fromIterable(customersMap.values());
    }


    
}
