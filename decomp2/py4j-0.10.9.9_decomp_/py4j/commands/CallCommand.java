package py4j.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import py4j.Protocol;
import py4j.Py4JException;
import py4j.ReturnObject;

public class CallCommand extends AbstractCommand {
   private final Logger logger = Logger.getLogger(CallCommand.class.getName());
   public static final String CALL_COMMAND_NAME = "c";

   public CallCommand() {
      this.commandName = "c";
   }

   public void execute(String commandName, BufferedReader reader, BufferedWriter writer) throws Py4JException, IOException {
      String targetObjectId = reader.readLine();
      String methodName = reader.readLine();
      List<Object> arguments = this.getArguments(reader);
      ReturnObject returnObject = this.invokeMethod(methodName, targetObjectId, arguments);
      String returnCommand = Protocol.getOutputCommand(returnObject);
      this.logger.finest("Returning command: " + returnCommand);
      writer.write(returnCommand);
      writer.flush();
   }
}
