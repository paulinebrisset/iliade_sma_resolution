package multiagents;

import java.util.HashMap;

public class Launcher {
	// dictionnaire pour un id, un agent
	private static HashMap<Integer, Agent> agents;

	public Launcher() {
		agents = new HashMap<Integer, Agent>();
		for (int i = 0; i < 6; i++) {
			int agentID = i + 1;
			int agentValue = i + 2;
			// Instancier directement l'Agent car il etend Thread
			Agent agent = new Agent(agentID, agentValue);
			agents.put(agentID, agent);

			agent.start(); // Demarre le thread directement
		}
		// lancer le premier agent
		sendMessage(1, "GO");
	}

	public static void main(String[] args) {
		new Launcher();
	}

	public Agent getAgent(int idAgent) {
		Agent agent;
		if (idAgent > 0 && idAgent <= agents.size()) {
			agent = agents.get(idAgent - 1); // Renvoie l'agent de id+1 stocke dans la case id
		} else {
			agent = agents.get(0); // Si l'agent n'existe pas, renvoie l'agent à l'indice 0
		}
		return agent;
	}

	public static void sendMessage(int idAgent, String message) {
		// Verifier si l'agent avec l'ID demande existe
		if (agents.containsKey(idAgent)) {
			System.out.println("Launcher : J'ai trouve l'agent " + idAgent);
			agents.get(idAgent).sendMessage(message); // Si l'agent existe, envoyer le message
		} else {
			// Si l'agent n'existe pas, envoyer le message à l'agent avec l'ID 1
			if (agents.containsKey(1)) {
				agents.get(1).sendMessage(message);
			} else {
				System.out.println("Launcher : Agent 1 n'existe pas non plus.");
			}
		}
	}
}
