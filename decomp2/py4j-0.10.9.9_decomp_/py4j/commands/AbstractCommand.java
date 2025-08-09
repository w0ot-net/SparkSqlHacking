package py4j.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import py4j.Gateway;
import py4j.Protocol;
import py4j.Py4JException;
import py4j.Py4JServerConnection;
import py4j.ReturnObject;

public abstract class AbstractCommand implements Command {
   protected Gateway gateway;
   protected String commandName;
   private final Logger logger = Logger.getLogger(AbstractCommand.class.getName());
   protected Py4JServerConnection connection;

   public abstract void execute(String var1, BufferedReader var2, BufferedWriter var3) throws Py4JException, IOException;

   protected List getArguments(BufferedReader reader) throws IOException {
      List<Object> arguments = new ArrayList();

      for(String stringArgument : this.getStringArguments(reader)) {
         arguments.add(Protocol.getObject(stringArgument, this.gateway));
      }

      return arguments;
   }

   public String getCommandName() {
      return this.commandName;
   }

   protected List getStringArguments(BufferedReader reader) throws IOException {
      List<String> arguments = new ArrayList();

      for(String line = reader.readLine(); !Protocol.isEmpty(line) && !Protocol.isEnd(line); line = reader.readLine()) {
         this.logger.finest("Raw String Argument: " + line);
         arguments.add(line);
      }

      return arguments;
   }

   public void init(Gateway gateway, Py4JServerConnection connection) {
      this.gateway = gateway;
      this.connection = connection;
   }

   protected ReturnObject invokeMethod(String methodName, String targetObjectId, List arguments) {
      ReturnObject returnObject = null;

      try {
         returnObject = this.gateway.invoke(methodName, targetObjectId, arguments);
      } catch (Exception e) {
         this.logger.log(Level.FINE, "Received exception while executing this command: " + methodName, e);
         returnObject = ReturnObject.getErrorReturnObject(e);
      }

      return returnObject;
   }
}
