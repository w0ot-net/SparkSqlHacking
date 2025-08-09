package py4j.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.logging.Logger;
import py4j.Gateway;
import py4j.NetworkUtil;
import py4j.Protocol;
import py4j.Py4JException;
import py4j.Py4JServerConnection;
import py4j.ReturnObject;
import py4j.reflection.ReflectionEngine;

public class FieldCommand extends AbstractCommand {
   private final Logger logger = Logger.getLogger(FieldCommand.class.getName());
   public static final String FIELD_COMMAND_NAME = "f";
   public static final String FIELD_GET_SUB_COMMAND_NAME = "g";
   public static final String FIELD_SET_SUB_COMMAND_NAME = "s";
   private ReflectionEngine reflectionEngine;

   public FieldCommand() {
      this.commandName = "f";
   }

   public void execute(String commandName, BufferedReader reader, BufferedWriter writer) throws Py4JException, IOException {
      String returnCommand = null;
      String subCommand = NetworkUtil.safeReadLine(reader, false);
      if (subCommand.equals("g")) {
         returnCommand = this.getField(reader);
      } else if (subCommand.equals("s")) {
         returnCommand = this.setField(reader);
      } else {
         returnCommand = Protocol.getOutputErrorCommand("Unknown Field SubCommand Name: " + subCommand);
      }

      this.logger.finest("Returning command: " + returnCommand);
      writer.write(returnCommand);
      writer.flush();
   }

   private String getField(BufferedReader reader) throws IOException {
      String targetObjectId = reader.readLine();
      String fieldName = reader.readLine();
      reader.readLine();
      Object object = this.gateway.getObject(targetObjectId);
      Field field = this.reflectionEngine.getField(object, fieldName);
      this.logger.finer("Getting field " + fieldName);
      String returnCommand = null;
      if (field == null) {
         returnCommand = Protocol.getNoSuchFieldOutputCommand();
      } else {
         Object fieldObject = this.reflectionEngine.getFieldValue(object, field);
         ReturnObject rObject = this.gateway.getReturnObject(fieldObject);
         returnCommand = Protocol.getOutputCommand(rObject);
      }

      return returnCommand;
   }

   public void init(Gateway gateway, Py4JServerConnection connection) {
      super.init(gateway, connection);
      this.reflectionEngine = gateway.getReflectionEngine();
   }

   private String setField(BufferedReader reader) throws IOException {
      String targetObjectId = reader.readLine();
      String fieldName = reader.readLine();
      String value = reader.readLine();
      reader.readLine();
      Object valueObject = Protocol.getObject(value, this.gateway);
      Object object = this.gateway.getObject(targetObjectId);
      Field field = this.reflectionEngine.getField(object, fieldName);
      this.logger.finer("Setting field " + fieldName);
      String returnCommand = null;
      if (field == null) {
         returnCommand = Protocol.getNoSuchFieldOutputCommand();
      } else {
         this.reflectionEngine.setFieldValue(object, field, valueObject);
         returnCommand = Protocol.getOutputVoidCommand();
      }

      return returnCommand;
   }
}
