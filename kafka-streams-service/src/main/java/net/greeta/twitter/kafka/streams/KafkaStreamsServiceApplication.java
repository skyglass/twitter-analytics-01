package net.greeta.twitter.kafka.streams;

import net.greeta.twitter.kafka.streams.runner.StreamsRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"net.greeta.twitter"})
public class KafkaStreamsServiceApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaStreamsServiceApplication.class);

	private final StreamsRunner<String, Long> streamsRunner;

	public KafkaStreamsServiceApplication(StreamsRunner<String, Long> runner) {
		this.streamsRunner = runner;
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaStreamsServiceApplication.class, args);
	}

	@Override
	public void run(String... args) {
		LOG.info("App starts...");
		streamsRunner.start();
	}

}