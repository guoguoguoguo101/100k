package mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Server {

    public static final String HOST = "tcp://127.0.0.1:1883";

    public static final String TOPIC = "tokudu/yzq124";
    private static final String clientid ="server";

    private MqttClient client;
    private MqttTopic topic;
    private String userName = "adminsadsa";
    private String passWord = "ssdsad";

    private MqttMessage message;

    public Server() throws MqttException {
        //MemoryPersistence设置clientid的保存形式，默认为以内存保存
        client = new MqttClient(HOST, clientid, new MemoryPersistence());
        connect();
    }

    private void connect() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);
        try {
            client.setCallback(new PushCallback());
            client.connect(options);
            topic = client.getTopic(TOPIC);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void publish(MqttMessage message) throws MqttPersistenceException, MqttException{
        MqttDeliveryToken token = topic.publish(message);
        token.waitForCompletion();
        System.out.println(token.isComplete()+"========");
    }

    public static void main(String[] args) throws MqttException, InterruptedException {
        Server server =  new Server();
        server.message = new MqttMessage();
        server.message.setQos(1);
        server.message.setRetained(true);
        int i = 0;
        while (true){
            Thread.sleep(5000);
            i++;
            String bb = i+""+"zhuzhu";
            server.message.setPayload(bb.getBytes());
            server.publish(server.message);
            System.out.println(server.message.isRetained()+"------ratained状态");
        }



    }
}
