/**
 * $Revision: 1.0 $
 * $Author: Eric Yang $
 * $Date: Feb 2, 2010 5:05:08 PM $
 *
 * Author: Eric Yang
 * Date  : Feb 2, 2010 5:05:08 PM
 *
 */
package c4j.haa.ems.sample;

import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

/**
 * @author Eric Yang
 * @version 1.0
 */
public class TestTopicSubscriber extends BaseEMSTestUnit {

	public void test() {
		try {
			setTopicName("helloboy");
			System.out.println("start");
			TopicConnection topicConnect = getTopicConnect();
			TopicSession session = getTopicSession(topicConnect);
			Topic topic = session.createTopic(topicName);

			TopicPublisher publisher = session.createPublisher(topic);

			TextMessage message = session.createTextMessage();
			message.setText("hello");
			publisher.publish(message);

			TopicSubscriber subscriber = session.createSubscriber(topic);

			topicConnect.start();

			/* read topic messages */
			while (true) {
				Message m = subscriber.receive();
				if (m == null)
					break;

				System.err.println("Received message: " + m);

			}

			topicConnect.close();

			System.out.println("over");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new TestTopicSubscriber().test();
	}
}
