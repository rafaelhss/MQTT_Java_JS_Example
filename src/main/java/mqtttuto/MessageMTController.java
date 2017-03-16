package mqtttuto;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageMTController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    private MqttClient client;

    @RequestMapping("/messagemt")
    public MessageMT greeting(@RequestParam(value="name", defaultValue="World") String name) {
        MessageMT mensagem = new MessageMT(counter.incrementAndGet(),
                            String.format(template, name));
        try {
            client = new MqttClient("tcp://localhost:1883", "pahomqttpublish1");
            client.connect();
            MqttMessage message = new MqttMessage();
            message.setPayload(Util.serialize("mensagem"));
            client.publish("pahodemo/test", message);
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mensagem;
    }
}
