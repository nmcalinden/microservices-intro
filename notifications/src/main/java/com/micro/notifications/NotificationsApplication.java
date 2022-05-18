package com.micro.notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(
        scanBasePackages = {
                "com.micro.notifications",
                "com.micro.amqp",
        }
)
@EnableEurekaClient
public class NotificationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationsApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(RabbitMQMessageProducer producer, NotificationsConfig notificationsConfig) {
//        return args -> {
//            producer.publish(new Person("Nathan", "28"), notificationsConfig.getInternalExchange(),
//                    notificationsConfig.getInternalNotificationRoutingKey());
//        };
//    }
//
//    @Data
//    @AllArgsConstructor
//    class Person {
//        private String name;
//        private String age;
//    }
}
