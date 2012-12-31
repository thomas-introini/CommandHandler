package main;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import response.Responder;
import response.XMPPResponder;
import settings.Settings;

import commandchain.*;
import command.*;
import logger.Logger;

public class CommandHandler implements Runnable{
	private Responder r;
	private static boolean isOn=true;
	public CommandHandler() {
		CommandChain chain = new HighLevelOpHandler(new TorrentHandler(new ConsoleHandler(new QuitHandler(new EndOfChain(),"quit",QuitCommand.class),
				"console", ConsoleCommand.class), "torrent"),"hlo");
		Logger.setLogFile(Settings.getLogFile());
		r=XMPPResponder.singleton;
		r.initialize(new Object[]{chain});
	}
	
	public static void shutdown(){
		isOn=false;
	}

	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		System.setErr(new PrintStream(Settings.getErrFile()));
		System.setOut(new PrintStream(Settings.getOutFile()));
		CommandHandler ch = new CommandHandler();
		ch.run();
		XMPPResponder.close();
	}

	@Override
	public void run() {
		while (isOn) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}

}
