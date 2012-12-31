package commandchain;

import java.io.File;
import java.util.Arrays;

import logger.Logger;

import command.Command;

public class TorrentHandler extends CommandChain {

	public TorrentHandler(CommandChain successor, String toHandle) {
		super(successor, toHandle, null);
	}
	
	public Command handle(String...param){
		Command ret=null;
		Class<?> c;
		if (param[0].equalsIgnoreCase(toHandle)) {
			if (param.length < 2)
				throw new IllegalArgumentException("Too few param!");
			Logger.log(toHandle + " request found!");
			try {
				String name = "command."
						+ Character.toUpperCase(param[1].charAt(0))
						+ param[1].substring(1).replaceAll("\n", "")
						+ "TorrentCommand";
				c = Class.forName(name);
				ret = (Command) c.newInstance();
			} catch (Exception e) {
				Logger.log(e.getMessage());
				System.out.println(e.getMessage());
			}
			ret.initialize((Object[])Arrays.copyOfRange(param, 2, param.length));
		}else
			ret=successor.handle(param);
		return ret;
	}

	@Override
	public Command handle(File request) {
		Command ret = null;
		Class<?> c;
		if (request.getName().contains(toHandle)) {
			Logger.log(toHandle + " request found!");
			String[] data = this.getData(request).split(" ");
			try {
				String name = "command."
						+ Character.toUpperCase(data[0].charAt(0))
						+ data[0].substring(1).replaceAll("\n", "")
						+ "TorrentCommand";
				System.out.println(name);
				c = Class.forName(name);
				ret = (Command) c.newInstance();
				ret.initialize((Object[]) data);
				rmFile(request);
			} catch (Exception e) {
				Logger.log(e.getMessage());
			}
		} else {
			ret = successor.handle(request);
		}

		return ret;
	}
}
