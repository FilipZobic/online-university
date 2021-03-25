package rs.ac.singidunum.org.apiusers.configuration;

import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

@Configuration
public class ActiveMQ {
    @Bean
    @Qualifier("sendUser")
    public Queue sendUser() {
        return new ActiveMQQueue("from-users-dto");
    }

    @Bean
    @Qualifier("newUser")
    public Queue logNewUser() {
        return new ActiveMQQueue("from-log-new-user"); //mongo db
    }

    @Bean()
    @Qualifier("user-exists")
    public Queue userExists() { return new ActiveMQQueue("api-user-(user-exists-response)-to-(some-api)");}
}
