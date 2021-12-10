package hazelcast;

import java.util.Map;
import com.hazelcast.client.Client;
import com.hazelcast.client.ClientListener;
import com.hazelcast.client.ClientService;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.example.demo.Customer;

public class HazelcastStart {
    public static void main(String[] args){
        // Cluster
        Config helloWorldConfig = new Config();
        helloWorldConfig.setClusterName("hello-world");

        // Instancias
        HazelcastInstance hz = Hazelcast.newHazelcastInstance(helloWorldConfig);
        // HazelcastInstance hz2 = Hazelcast.newHazelcastInstance(helloWorldConfig);

        final ClientService clientService = hz.getClientService();

        clientService.addClientListener(new ClientListener() {
            @Override
            public void clientConnected(Client client) {
                System.out.println("----------Client Connected-----------");
                IMap<String, Customer> map = hz.getMap("Customer-map");
                for (Map.Entry<String, Customer> entry: map.entrySet()) {
                    System.out.println(entry.getKey()+" "+entry.getValue().getName());
                }
            }

            @Override
            public void clientDisconnected(Client client) {
                System.out.println("----------Client Disconnected----------");
                IMap<String, Customer> map = hz.getMap("Customer-map");
                for (Map.Entry<String, Customer> entry: map.entrySet()) {
                    System.out.println(entry.getKey()+" "+entry.getValue().getName());
                }
            }
        });

        // IMap<String, Customer> map = hz.getMap("Customer-map");
        // map.put("1", new Customer("1","John"));
        // map.put("2", new Customer("2","Mary"));
        // map.put("3", new Customer("3","Jane"));
        // map.put("4", new Customer("4","Mikel"));
        // map.put("5", new Customer("5","Julen"));
        // map.put("6", new Customer("6","Adrian"));
        // map.put("7", new Customer("7","Hodei"));

        // for (Map.Entry<String, Customer> entry: map.entrySet()) {
        //     System.out.println(entry.getKey()+" "+entry.getValue().getName());
        // }
    }
}
