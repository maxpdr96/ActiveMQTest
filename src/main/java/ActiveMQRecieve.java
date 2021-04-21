import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ActiveMQRecieve {

    /*
     * default broker URL is: tcp://localhost:61616
     *
     * */
    private static final String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    private static final String queueName = "MESSAGE_QUEUE";

    public static void main(String[] args) throws JMSException {

        System.out.println("url is: " + url);
        System.out.println("Name queue is: " + queueName);


        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();


        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(queueName);

        MessageProducer producer = session.createProducer(destination);
        TextMessage message = session.createTextMessage("Teste");

        producer.send(message);


        System.out.println("Message: " + message.getText() + "Sent Successfully to the Queue");
        connection.close();
    }
}
