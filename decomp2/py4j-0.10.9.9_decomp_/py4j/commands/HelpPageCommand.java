package py4j.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.logging.Logger;
import py4j.NetworkUtil;
import py4j.Protocol;
import py4j.Py4JException;
import py4j.ReturnObject;
import py4j.model.HelpPageGenerator;
import py4j.model.Py4JClass;
import py4j.reflection.ReflectionUtil;

public class HelpPageCommand extends AbstractCommand {
   private final Logger logger = Logger.getLogger(HelpPageCommand.class.getName());
   public static final String HELP_COMMAND_NAME = "h";
   public static final String HELP_OBJECT_SUB_COMMAND_NAME = "o";
   public static final String HELP_CLASS_SUB_COMMAND_NAME = "c";

   public HelpPageCommand() {
      this.commandName = "h";
   }

   public void execute(String commandName, BufferedReader reader, BufferedWriter writer) throws Py4JException, IOException {
      String returnCommand = null;
      String subCommand = NetworkUtil.safeReadLine(reader, false);
      if (subCommand.equals("o")) {
         returnCommand = this.getHelpObject(reader);
      } else if (subCommand.equals("c")) {
         returnCommand = this.getHelpClass(reader);
      } else {
         returnCommand = Protocol.getOutputErrorCommand("Unknown Help SubCommand Name: " + subCommand);
      }

      this.logger.finest("Returning command: " + returnCommand);
      writer.write(returnCommand);
      writer.flush();
   }

   private String getHelpClass(BufferedReader reader) throws IOException {
      String className = reader.readLine();
      String pattern = (String)Protocol.getObject(reader.readLine(), this.gateway);
      String shortName = NetworkUtil.safeReadLine(reader, false);
      reader.readLine();

      String returnCommand;
      try {
         Py4JClass clazz = Py4JClass.buildClass(ReflectionUtil.classForName(className), true);
         boolean isShortName = Protocol.getBoolean(shortName);
         String helpPage = HelpPageGenerator.getHelpPage(clazz, pattern, isShortName);
         ReturnObject rObject = this.gateway.getReturnObject(helpPage);
         returnCommand = Protocol.getOutputCommand(rObject);
      } catch (Exception e) {
         returnCommand = Protocol.getOutputErrorCommand((Throwable)e);
      }

      return returnCommand;
   }

   private String getHelpObject(BufferedReader reader) throws IOException {
      String objectId = reader.readLine();
      String pattern = (String)Protocol.getObject(reader.readLine(), this.gateway);
      String shortName = NetworkUtil.safeReadLine(reader, false);
      reader.readLine();

      String returnCommand;
      try {
         Object obj = this.gateway.getObject(objectId);
         Py4JClass clazz = Py4JClass.buildClass(obj.getClass(), true);
         boolean isShortName = Protocol.getBoolean(shortName);
         String helpPage = HelpPageGenerator.getHelpPage(clazz, pattern, isShortName);
         ReturnObject rObject = this.gateway.getReturnObject(helpPage);
         returnCommand = Protocol.getOutputCommand(rObject);
      } catch (Exception e) {
         returnCommand = Protocol.getOutputErrorCommand((Throwable)e);
      }

      return returnCommand;
   }
}
