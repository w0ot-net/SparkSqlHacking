package py4j.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.logging.Logger;
import py4j.Gateway;
import py4j.JVMView;
import py4j.NetworkUtil;
import py4j.Protocol;
import py4j.Py4JException;
import py4j.Py4JServerConnection;
import py4j.ReturnObject;
import py4j.StringUtil;
import py4j.reflection.ReflectionEngine;

public class JVMViewCommand extends AbstractCommand {
   private final Logger logger = Logger.getLogger(JVMViewCommand.class.getName());
   public static final char CREATE_VIEW_SUB_COMMAND_NAME = 'c';
   public static final char IMPORT_SUB_COMMAND_NAME = 'i';
   public static final char REMOVE_IMPORT_SUB_COMMAND_NAME = 'r';
   public static final char SEARCH_SUB_COMMAND_NAME = 's';
   public static final String JVMVIEW_COMMAND_NAME = "j";
   protected ReflectionEngine rEngine;

   public JVMViewCommand() {
      this.commandName = "j";
   }

   private String createJVMView(BufferedReader reader) throws IOException {
      String name = StringUtil.unescape(reader.readLine());
      reader.readLine();
      JVMView newView = new JVMView(name, (String)null);
      ReturnObject rObject = this.gateway.getReturnObject(newView);
      newView.setId(rObject.getName());
      return Protocol.getOutputCommand(rObject);
   }

   private String doImport(BufferedReader reader) throws IOException {
      String jvmId = reader.readLine();
      String importString = StringUtil.unescape(reader.readLine());
      reader.readLine();
      JVMView view = (JVMView)Protocol.getObject(jvmId, this.gateway);
      if (importString.endsWith("*")) {
         view.addStarImport(importString);
      } else {
         view.addSingleImport(importString);
      }

      return Protocol.getOutputVoidCommand();
   }

   public void execute(String commandName, BufferedReader reader, BufferedWriter writer) throws Py4JException, IOException {
      char subCommand = NetworkUtil.safeReadLine(reader).charAt(0);
      String returnCommand = null;
      if (subCommand == 'c') {
         returnCommand = this.createJVMView(reader);
      } else if (subCommand == 'i') {
         returnCommand = this.doImport(reader);
      } else if (subCommand == 'r') {
         returnCommand = this.removeImport(reader);
      } else if (subCommand == 's') {
         returnCommand = this.search(reader);
      } else {
         returnCommand = Protocol.getOutputErrorCommand("Unknown JVM View SubCommand Name: " + subCommand);
      }

      this.logger.finest("Returning command: " + returnCommand);
      writer.write(returnCommand);
      writer.flush();
   }

   public void init(Gateway gateway, Py4JServerConnection connection) {
      super.init(gateway, connection);
      this.rEngine = gateway.getReflectionEngine();
   }

   private String removeImport(BufferedReader reader) throws IOException {
      String jvmId = reader.readLine();
      String importString = StringUtil.unescape(reader.readLine());
      reader.readLine();
      JVMView view = (JVMView)Protocol.getObject(jvmId, this.gateway);
      boolean removed = false;
      if (importString.endsWith("*")) {
         removed = view.removeStarImport(importString);
      } else {
         removed = view.removeSingleImport(importString);
      }

      return Protocol.getOutputCommand(ReturnObject.getPrimitiveReturnObject(removed));
   }

   private String search(BufferedReader reader) {
      return null;
   }
}
