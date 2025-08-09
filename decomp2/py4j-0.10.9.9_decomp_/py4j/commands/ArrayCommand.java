package py4j.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import py4j.NetworkUtil;
import py4j.Protocol;
import py4j.Py4JException;
import py4j.ReturnObject;
import py4j.reflection.MethodInvoker;
import py4j.reflection.TypeConverter;

public class ArrayCommand extends AbstractCommand {
   private final Logger logger = Logger.getLogger(ArrayCommand.class.getName());
   public static final String ARRAY_COMMAND_NAME = "a";
   public static final char ARRAY_GET_SUB_COMMAND_NAME = 'g';
   public static final char ARRAY_SET_SUB_COMMAND_NAME = 's';
   public static final char ARRAY_SLICE_SUB_COMMAND_NAME = 'l';
   public static final char ARRAY_LEN_SUB_COMMAND_NAME = 'e';
   public static final char ARRAY_CREATE_SUB_COMMAND_NAME = 'c';
   public static final String RETURN_VOID = "!yv\n";

   public ArrayCommand() {
      this.commandName = "a";
   }

   private String createArray(BufferedReader reader) throws IOException {
      String fqn = (String)Protocol.getObject(reader.readLine(), this.gateway);
      List<Object> dimensions = this.getArguments(reader);
      int size = dimensions.size();
      int[] dimensionsInt = new int[size];

      for(int i = 0; i < size; ++i) {
         dimensionsInt[i] = (Integer)dimensions.get(i);
      }

      Object newArray = this.gateway.getReflectionEngine().createArray(fqn, dimensionsInt);
      ReturnObject returnObject = this.gateway.getReturnObject(newArray);
      return Protocol.getOutputCommand(returnObject);
   }

   public void execute(String commandName, BufferedReader reader, BufferedWriter writer) throws Py4JException, IOException {
      char subCommand = NetworkUtil.safeReadLine(reader).charAt(0);
      String returnCommand = null;
      if (subCommand == 'g') {
         returnCommand = this.getArray(reader);
      } else if (subCommand == 's') {
         returnCommand = this.setArray(reader);
      } else if (subCommand == 'l') {
         returnCommand = this.sliceArray(reader);
      } else if (subCommand == 'e') {
         returnCommand = this.lenArray(reader);
      } else if (subCommand == 'c') {
         returnCommand = this.createArray(reader);
      } else {
         returnCommand = Protocol.getOutputErrorCommand("Unknown Array SubCommand Name: " + subCommand);
      }

      this.logger.finest("Returning command: " + returnCommand);
      writer.write(returnCommand);
      writer.flush();
   }

   private String getArray(BufferedReader reader) throws IOException {
      Object arrayObject = this.gateway.getObject(reader.readLine());
      int index = (Integer)Protocol.getObject(reader.readLine(), this.gateway);
      reader.readLine();
      Object getObject = Array.get(arrayObject, index);
      ReturnObject returnObject = this.gateway.getReturnObject(getObject);
      return Protocol.getOutputCommand(returnObject);
   }

   private String lenArray(BufferedReader reader) throws IOException {
      Object arrayObject = this.gateway.getObject(reader.readLine());
      reader.readLine();
      int length = Array.getLength(arrayObject);
      ReturnObject returnObject = this.gateway.getReturnObject(length);
      return Protocol.getOutputCommand(returnObject);
   }

   private String setArray(BufferedReader reader) throws IOException {
      Object arrayObject = this.gateway.getObject(reader.readLine());
      int index = (Integer)Protocol.getObject(reader.readLine(), this.gateway);
      Object objectToSet = Protocol.getObject(reader.readLine(), this.gateway);
      reader.readLine();
      Object convertedObject = this.convertArgument(arrayObject.getClass().getComponentType(), objectToSet);
      Array.set(arrayObject, index, convertedObject);
      return "!yv\n";
   }

   private Object convertArgument(Class arrayClass, Object objectToSet) {
      Object newObject = null;
      List<TypeConverter> converters = new ArrayList();
      Class<?>[] parameterClasses = new Class[]{arrayClass};
      Class<?>[] argumentClasses = this.gateway.getReflectionEngine().getClassParameters(new Object[]{objectToSet});
      int cost = MethodInvoker.buildConverters(converters, parameterClasses, argumentClasses);
      if (cost >= 0) {
         newObject = ((TypeConverter)converters.get(0)).convert(objectToSet);
         return newObject;
      } else {
         String errorMessage;
         if (argumentClasses[0] != null) {
            errorMessage = "Cannot convert " + argumentClasses[0].getName() + " to " + arrayClass.getName();
         } else {
            errorMessage = "Cannot convert null to " + arrayClass.getName();
         }

         throw new Py4JException(errorMessage);
      }
   }

   private String sliceArray(BufferedReader reader) throws IOException {
      Object arrayObject = this.gateway.getObject(reader.readLine());
      List<Object> indices = this.getArguments(reader);
      int size = indices.size();
      Object newArray = this.gateway.getReflectionEngine().createArray(arrayObject.getClass().getComponentType().getName(), new int[]{size});

      for(int i = 0; i < size; ++i) {
         int index = (Integer)indices.get(i);
         Array.set(newArray, i, Array.get(arrayObject, index));
      }

      ReturnObject returnObject = this.gateway.getReturnObject(newArray);
      return Protocol.getOutputCommand(returnObject);
   }
}
