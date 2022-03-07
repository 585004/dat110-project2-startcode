package no.hvl.dat110.iotsystem;

import no.hvl.dat110.messages.MessageType;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.common.TODO;
import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messages.PublishMsg;

public class DisplayDevice {

	private static final int COUNT = 10;

	public static void main(String[] args) {

		System.out.println("Display starting ...");

		Client client = new Client("display", Common.BROKERHOST, Common.BROKERPORT);

		client.connect();

		client.createTopic(Common.TEMPTOPIC);

		client.subscribe(Common.TEMPTOPIC);

		for (int i = 0; i <= COUNT; i++) {
			Message message = client.receive();
			if (message.getType() == MessageType.PUBLISH) {
				System.out.println("Display: " + ((PublishMsg) message).getMessage());
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		client.unsubscribe(Common.TEMPTOPIC);

		client.disconnect();

		System.out.println("Display stopping ... ");

		throw new UnsupportedOperationException(TODO.method());

	}
}
