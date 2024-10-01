package multiagents;

public class Agent extends Thread {

	private boolean hasToWork = true;
	private int recievedValue;
	private int turnsNumber;
	private int id;
	public MailBox mailbox;

	// Constructeur : initialise avec un ID et une valeur
	Agent(int agentID, int newValue) {
		recievedValue = newValue;
		id = agentID;
		turnsNumber = 0;
		mailbox = new MailBox(id);
	}

	@Override
	public void run() {
		System.out.println("Agent no " + id + " : J'attends un message");
		String witnessStart = "";
		while (!witnessStart.equals("GO")) {
			// Bloquant
			witnessStart = mailbox.getLastMessage();
		}
		System.out.println("Agent no " + id + " : J'ai reçu GO, je demarres");

		while (hasToWork) {
			turnsNumber = turnsNumber + 1;
			recievedValue = recievedValue * 2;
			System.out.println(
					"Agent no " + id + " tour numero " + turnsNumber + " ma nouvelle valeur est " + recievedValue);
			if (recievedValue >= 100) {
				stopAgent();
				break;
			} else {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void sendMessage(String message) {
		System.out.println("Agent numero " + id + " : Je m'envoie un message sur ma propre boite");
		this.mailbox.add(message);
	}

	public void stopAgent() {
		// stop Neighbour
		int idNeighbour = id + 1;
		System.out.println("Agent numero " + id + " : J'appelle le launcher pour l'agent " + idNeighbour);
		Launcher.sendMessage(idNeighbour, "GO");
		this.hasToWork = false;
	}

}
