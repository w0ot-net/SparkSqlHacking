package py4j.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import py4j.Protocol;
import py4j.Py4JException;
import py4j.ReturnObject;

public class ConstructorCommand extends AbstractCommand {
   private final Logger logger = Logger.getLogger(CallCommand.class.getName());
   public static final String CONSTRUCTOR_COMMAND_NAME = "i";

   public ConstructorCommand() {
      this.commandName = "i";
   }

   public void execute(String commandName, BufferedReader reader, BufferedWriter writer) throws Py4JException, IOException {
      String fqn = reader.readLine();
      List<Object> arguments = this.getArguments(reader);
      ReturnObject returnObject = this.invokeConstructor(fqn, arguments);
      String returnCommand = Protocol.getOutputCommand(returnObject);
      this.logger.finest("Returning command: " + returnCommand);
      writer.write(returnCommand);
      writer.flush();
   }

   protected ReturnObject invokeConstructor(String fqn, List arguments) {
      ReturnObject returnObject = null;

      try {
         returnObject = this.gateway.invoke(fqn, arguments);
      } catch (Exception e) {
         this.logger.log(Level.FINE, "Received exception while executing this command: " + fqn, e);
         returnObject = ReturnObject.getErrorReturnObject(e);
      }

      return returnObject;
   }
}
