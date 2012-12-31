package response;

import logger.Logger;
import settings.Settings;

import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;

import command.Command;
import commandchain.CommandChain;

public class XMPPResponder implements Responder, MessageListener {

	private ConnectionConfiguration connConfig;
	private static XMPPConnection connection;
	private ChatManager chatmanager;
	private Chat chat;
	private CommandChain cc;
	private Command command;

	public static Responder singleton = new XMPPResponder();

	private XMPPResponder() {

	}

	@Override
	public void initialize() {

		connConfig = new ConnectionConfiguration("talk.google.com", 5222,
				"gmail.com");
		connection = new XMPPConnection(connConfig);
		try {
			connection.connect();
			System.out.println("Connected to " + connection.getHost());
		} catch (XMPPException ex) {
			System.out.println("Failed to connect to " + connection.getHost());
			Logger.log(ex.getMessage());
		}
		try {
			connection.login(Settings.getUsername(), Settings.getPasswd());
			System.out.println("Logged in as " + connection.getUser());
			Presence presence = new Presence(Presence.Type.available);
			connection.sendPacket(presence);
		} catch (XMPPException ex) {
			System.out.println("Failed to log in as " + connection.getUser());
			Logger.log(ex.getMessage());
		}
		chatmanager = connection.getChatManager();
		chat = chatmanager.createChat(Settings.getJID(), this);
	}

	@Override
	public void initialize(Object... objects) {
		cc = (CommandChain) objects[0];
		initialize();
	}

	@Override
	public void send(String message) {
		try {
			Message mex = new Message(Settings.getJID(), Message.Type.chat);
			mex.setBody(message);
			chat.sendMessage(mex);
		} catch (XMPPException e) {
			System.out.println("Failed to send message");
			Logger.log(e.getMessage());
		}
	}

	@Override
	public void processMessage(Chat chat, Message message) {
		System.out.println(message.getBody());
		String[] param = message.getBody().toLowerCase().split(" ");
		command = cc.handle(param);
		command.setResponder(this);
		command.execute();
	}

	public static void close() {
		connection.disconnect();
	}

}
