package io.kerim.mqttexample;

import io.kerim.mqttexample.entity.MessageDto;
import io.kerim.mqttexample.service.MessagingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "io.kerim.mqttexample")
public class MqttExampleApplication implements CommandLineRunner {

	private final MessagingService messagingService;
	private final ConfigurableApplicationContext context;

	public MqttExampleApplication(MessagingService messagingService, ConfigurableApplicationContext context) {
		this.messagingService = messagingService;
		this.context = context;
	}

	public static void main(String[] args) {
		SpringApplication.run(MqttExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		final String topic = "topic-01";

		messagingService.subscribe(topic);

		MessageDto messageDto = new MessageDto(1, "test", "152");
		//messagingService.publish(topic, "Hi This is a sample message published to topic topic-01", 0, true);
		messagingService.publish(topic, messageDto.toString(), 0, true);

		context.close();
	}

}
