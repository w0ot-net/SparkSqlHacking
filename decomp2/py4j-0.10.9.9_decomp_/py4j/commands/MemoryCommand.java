package py4j.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.logging.Logger;
import py4j.NetworkUtil;
import py4j.Protocol;
import py4j.Py4JException;

public class MemoryCommand extends AbstractCommand {
   private final Logger logger = Logger.getLogger(MemoryCommand.class.getName());
   public static final String MEMORY_COMMAND_NAME = "m";
   public static final String MEMORY_DEL_SUB_COMMAND_NAME = "d";

   public MemoryCommand() {
      this.commandName = "m";
   }

   private String deleteObject(BufferedReader reader) throws IOException {
      String objectId = reader.readLine();
      reader.readLine();
      if (objectId != "t" && objectId != "j" && objectId != "GATEWAY_SERVER") {
         this.gateway.deleteObject(objectId);
      }

      return Protocol.getOutputVoidCommand();
   }

   public void execute(String commandName, BufferedReader reader, BufferedWriter writer) throws Py4JException, IOException {
      String returnCommand = null;
      String subCommand = NetworkUtil.safeReadLine(reader);
      if (subCommand.equals("d")) {
         returnCommand = this.deleteObject(reader);
      } else {
         returnCommand = Protocol.getOutputErrorCommand("Unknown Memory SubCommand Name: " + subCommand);
      }

      this.logger.finest("Returning command: " + returnCommand);
      writer.write(returnCommand);
      writer.flush();
   }
}
