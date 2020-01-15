package mqtt;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class IoTDemo {

    public static String productKey = "a19VizvYVg5";
    public static String deviceName = "light001";
    public static String deviceSecret = "MMQwnS5lJA0VYtBpTccOSkJGmj6xUlmR";
    public static String regionId = "cn-shanghai";

    //物模型-属性上报topic
    private static String pubTopic = "/sys/" + productKey + "/" + deviceName + "/thing/event/property/post";
    //高级版 物模型-属性上报payload
    private static final String payloadJson =
            "{" +
                    "    \"id\": %s," +
                    "    \"params\": {" +
                    "        \"CurrentTemperature\": %s," +
                    "        \"CurrentColor\": %s" +
                    "    }," +
                    "    \"method\": \"thing.event.property.post\"" +
                    "}";

    private static MqttClient mqttClient;
    private static Random random = new Random();
    private static DecimalFormat df = new DecimalFormat("0.#");

    public static void main(String [] args) {

        initAliyunIoTClient();

        ScheduledExecutorService scheduledThreadPool = new ScheduledThreadPoolExecutor(1,
                new ThreadFactoryBuilder().setNameFormat("thread-runner-%d").build());

        scheduledThreadPool.scheduleAtFixedRate(()->postDeviceProperties(), 10,10, TimeUnit.SECONDS);

    }

    private static void initAliyunIoTClient() {

        try {
            String clientId = "java" + System.currentTimeMillis();

            Map<String, String> params = new HashMap<>(16);
            params.put("productKey", productKey);
            params.put("deviceName", deviceName);
            params.put("clientId", clientId);
            String timestamp = String.valueOf(System.currentTimeMillis());
            params.put("timestamp", timestamp);

            // cn-shanghai
            String targetServer = "tcp://" + productKey + ".iot-as-mqtt."+regionId+".aliyuncs.com:1883";

            String mqttclientId = clientId + "|securemode=3,signmethod=hmacsha1,timestamp=" + timestamp + "|";
            String mqttUsername = deviceName + "&" + productKey;
            String mqttPassword = AliyunIoTSignUtil.sign(params, deviceSecret, "hmacsha1");

            System.out.println(params);
            System.out.println("targetServer="+targetServer+";mqttclientId="+mqttclientId+";mqttUsername="+mqttUsername+";mqttPassword="+mqttPassword);
            connectMqtt(targetServer, mqttclientId, mqttUsername, mqttPassword);

        } catch (Exception e) {
            System.out.println("initAliyunIoTClient error " + e.getMessage());
        }
    }

    public static void connectMqtt(String url, String clientId, String mqttUsername, String mqttPassword) throws Exception {

        MemoryPersistence persistence = new MemoryPersistence();
        mqttClient = new MqttClient(url, clientId, persistence);
        System.out.println("url="+url);
        MqttConnectOptions connOpts = new MqttConnectOptions();
        // MQTT 3.1.1
        connOpts.setMqttVersion(4);
        connOpts.setAutomaticReconnect(false);
        connOpts.setCleanSession(true);

        connOpts.setUserName(mqttUsername);
        connOpts.setPassword(mqttPassword.toCharArray());
        connOpts.setKeepAliveInterval(60);
        System.out.println("connOpts = "+connOpts.toString());
        mqttClient.connect(connOpts);

    }


    private static void postDeviceProperties() {

        try {

            //上报数据
            String payload = String.format(payloadJson, System.currentTimeMillis(), df.format(25+random.nextDouble()*10), df.format((int) (100*Math.random())));

            System.out.println("post :"+payload);

            MqttMessage message = new MqttMessage(payload.getBytes("utf-8"));
            message.setQos(1);

            System.out.println(pubTopic+"******");
            mqttClient.publish(pubTopic, message);

        } catch (Exception e) {

        }

    }


}
