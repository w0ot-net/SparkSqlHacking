package py4j.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import py4j.Gateway;
import py4j.JVMView;
import py4j.NetworkUtil;
import py4j.Protocol;
import py4j.Py4JException;
import py4j.Py4JServerConnection;
import py4j.ReturnObject;
import py4j.reflection.ReflectionEngine;
import py4j.reflection.TypeUtil;

public class DirCommand extends AbstractCommand {
   private final Logger logger = Logger.getLogger(DirCommand.class.getName());
   private ReflectionEngine reflectionEngine;
   public static final String DIR_COMMAND_NAME = "d";
   public static final String DIR_FIELDS_SUBCOMMAND_NAME = "f";
   public static final String DIR_METHODS_SUBCOMMAND_NAME = "m";
   public static final String DIR_STATIC_SUBCOMMAND_NAME = "s";
   public static final String DIR_JVMVIEW_SUBCOMMAND_NAME = "v";

   public DirCommand() {
      this.commandName = "d";
   }

   public void execute(String commandName, BufferedReader reader, BufferedWriter writer) throws Py4JException, IOException {
      String subCommand = NetworkUtil.safeReadLine(reader);
      boolean unknownSubCommand = false;
      String param = reader.readLine();
      String returnCommand = null;

      try {
         String[] names;
         if (subCommand.equals("f")) {
            Object targetObject = this.gateway.getObject(param);
            names = this.reflectionEngine.getPublicFieldNames(targetObject);
         } else if (subCommand.equals("m")) {
            Object targetObject = this.gateway.getObject(param);
            names = this.reflectionEngine.getPublicMethodNames(targetObject);
         } else if (subCommand.equals("s")) {
            Class<?> clazz = TypeUtil.forName(param);
            names = this.reflectionEngine.getPublicStaticNames(clazz);
         } else if (subCommand.equals("v")) {
            names = this.getJvmViewNames(param, reader);
         } else {
            names = null;
            unknownSubCommand = true;
         }

         reader.readLine();
         if (unknownSubCommand) {
            returnCommand = Protocol.getOutputErrorCommand("Unknown Array SubCommand Name: " + subCommand);
         } else if (names == null) {
            ReturnObject returnObject = this.gateway.getReturnObject((Object)null);
            returnCommand = Protocol.getOutputCommand(returnObject);
         } else {
            StringBuilder namesJoinedBuilder = new StringBuilder();

            for(String name : names) {
               namesJoinedBuilder.append(name);
               namesJoinedBuilder.append("\n");
            }

            String namesJoined;
            if (namesJoinedBuilder.length() > 0) {
               namesJoined = namesJoinedBuilder.substring(0, namesJoinedBuilder.length() - 1);
            } else {
               namesJoined = "";
            }

            ReturnObject returnObject = this.gateway.getReturnObject(namesJoined);
            returnCommand = Protocol.getOutputCommand(returnObject);
         }
      } catch (Exception e) {
         this.logger.log(Level.FINEST, "Error in a dir subcommand", e);
         returnCommand = Protocol.getOutputErrorCommand();
      }

      this.logger.finest("Returning command: " + returnCommand);
      writer.write(returnCommand);
      writer.flush();
   }

   private String[] getJvmViewNames(String jvmId, BufferedReader reader) throws IOException {
      String lastSequenceIdString = (String)Protocol.getObject(reader.readLine(), this.gateway);
      int lastSequenceId;
      if (lastSequenceIdString == null) {
         lastSequenceId = 0;
      } else {
         lastSequenceId = Integer.parseInt(lastSequenceIdString);
      }

      JVMView view = (JVMView)Protocol.getObject(jvmId, this.gateway);
      int sequenceId = view.getSequenceId();
      if (lastSequenceId == sequenceId) {
         return null;
      } else {
         String[] importedNames = view.getImportedNames();
         String[] returnValue = new String[importedNames.length + 1];
         returnValue[0] = Integer.toString(sequenceId);
         System.arraycopy(importedNames, 0, returnValue, 1, importedNames.length);
         return returnValue;
      }
   }

   public void init(Gateway gateway, Py4JServerConnection connection) {
      super.init(gateway, connection);
      this.reflectionEngine = gateway.getReflectionEngine();
   }
}
