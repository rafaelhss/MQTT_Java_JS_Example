package mqtttuto;

import org.eclipse.paho.client.mqttv3.*;

/**
 * Created by root on 15/03/17.
 */
public class Callback implements MqttCallback {

    public static void main(String[] args){
        new Callback().doit();
    }

    public void doit(){
        try {
            MqttClient client = new MqttClient("tcp://localhost:1883", "pahomqttpublish2");
            client.setCallback(this);

            client.connect();


            client.subscribe("pahodemo/test");

            Thread.sleep(10000);

            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("################################33 Connection lost");
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println("################################33 message arrived: " + s);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("################################33 delivery complete");
    }
}
