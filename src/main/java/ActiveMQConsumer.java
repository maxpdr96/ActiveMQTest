import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ActiveMQConsumer {

    private static final String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    private static final String queueName = "MESSAGE_QUEUE";

    public static void main(String[] args) throws JMSException {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();


        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(queueName);

        MessageConsumer consumer = session.createConsumer(destination);

        Message message = consumer.receive();

        if (message instanceof TextMessage){
            TextMessage textMessage = (TextMessage) message;
            System.out.println("Message is: " + textMessage.getText());
        }
        connection.close();

    }
}
