package commandchain;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import logger.Logger;

import command.Command;

public abstract class CommandChain {
	protected CommandChain successor;
	protected String toHandle;
	protected Class<?> command;

	public CommandChain(CommandChain successor, String toHandle,
			Class<?> command) {
		this.successor = successor;
		this.toHandle = toHandle;
		this.command = command;
	}

	public Command handle(String... param) {
		Command ret = null;
		if (param[0].equalsIgnoreCase(toHandle)) {
			if (param.length < 2)
				throw new IllegalArgumentException("Too few param!");
			Logger.log(toHandle + " request found!");
			try {
				ret = (Command) command.newInstance();
				ret.initialize((Object[]) Arrays.copyOfRange(param, 1,
						param.length));
			} catch (Exception e) {
				Logger.log(e.getMessage());
				System.out.println(e.getMessage());
			}
		} else
			ret = successor.handle(param);
		return ret;
	}

	public Command handle(File request) {
		Command ret = null;
		if (request.getName().equalsIgnoreCase(toHandle)) {
			Logger.log(toHandle + " request found!");
			String read = getData(request);
			if (read.equals("")) {
				Logger.log("Errore while reading " + request.getName());
			} else {
				try {
					ret = (Command) command.newInstance();
				} catch (Exception e) {
					Logger.log(e.getMessage());
					System.out.println(e.getMessage());
				}
				ret.initialize(new Object[] { read });
				rmFile(request);
			}
		} else
			ret = successor.handle(request);
		return ret;
	}

	protected void rmFile(File file) {
		if (!file.exists())
			return;
		Logger.log("Deleting " + file.getName());
		file.deleteOnExit();
	}

	protected String getData(File file) {
		String read = "";
		int content;
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			while ((content = fis.read()) != -1)
				read += (char) content;
		} catch (IOException e) {
			Logger.log(e.getMessage());
		}
		return read;
	}
}
