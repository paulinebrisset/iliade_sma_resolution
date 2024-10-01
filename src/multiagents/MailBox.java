package multiagents;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MailBox {
	// Initialisation de la BlockingQueue
	private BlockingQueue<String> messages = new LinkedBlockingQueue<>();
	private int agentid;

	MailBox(int agentid) {
		this.agentid = agentid;
	}

	public void add(String message) {
		System.out.println("MailBox de l'agent " + agentid + " : je mets un msg pour mon agent");
		messages.add(message);
	}

	public String getLastMessage() {
		String message = "";
		try {
			message = messages.take(); // Recupère le message de manière bloquante
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return message;
	}
}
