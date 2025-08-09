package py4j.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import py4j.NetworkUtil;
import py4j.Protocol;
import py4j.Py4JException;
import py4j.ReturnObject;

public class ListCommand extends AbstractCommand {
   private final Logger logger = Logger.getLogger(ListCommand.class.getName());
   public static final String LIST_COMMAND_NAME = "l";
   public static final char LIST_SORT_SUB_COMMAND_NAME = 's';
   public static final char LIST_REVERSE_SUB_COMMAND_NAME = 'r';
   public static final char LIST_MAX_SUB_COMMAND_NAME = 'x';
   public static final char LIST_MIN_SUB_COMMAND_NAME = 'n';
   public static final char LIST_SLICE_SUB_COMMAND_NAME = 'l';
   public static final char LIST_CONCAT_SUB_COMMAND_NAME = 'a';
   public static final char LIST_MULT_SUB_COMMAND_NAME = 'm';
   public static final char LIST_IMULT_SUB_COMMAND_NAME = 'i';
   public static final char LIST_COUNT_SUB_COMMAND_NAME = 'f';
   public static final String RETURN_VOID = "!yv\n";

   public ListCommand() {
      this.commandName = "l";
   }

   private String call_collections_method(BufferedReader reader, char listCommand) throws IOException {
      String list_id = reader.readLine();
      reader.readLine();
      List list = (List)this.gateway.getObject(list_id);

      String returnCommand;
      try {
         if (listCommand == 's') {
            returnCommand = this.sort_list(list);
         } else if (listCommand == 'r') {
            returnCommand = this.reverse_list(list);
         } else if (listCommand == 'x') {
            returnCommand = this.max_list(list);
         } else if (listCommand == 'n') {
            returnCommand = this.min_list(list);
         } else {
            returnCommand = Protocol.getOutputErrorCommand();
         }
      } catch (Exception var7) {
         returnCommand = Protocol.getOutputErrorCommand();
      }

      return returnCommand;
   }

   private String concat_list(BufferedReader reader) throws IOException {
      List list1 = (List)this.gateway.getObject(reader.readLine());
      List list2 = (List)this.gateway.getObject(reader.readLine());
      reader.readLine();
      List list3 = new ArrayList(list1);
      list3.addAll(list2);
      ReturnObject returnObject = this.gateway.getReturnObject(list3);
      return Protocol.getOutputCommand(returnObject);
   }

   private String count_list(BufferedReader reader) throws IOException {
      List list1 = (List)this.gateway.getObject(reader.readLine());
      Object objectToCount = Protocol.getObject(reader.readLine(), this.gateway);
      reader.readLine();
      int count = Collections.frequency(list1, objectToCount);
      ReturnObject returnObject = this.gateway.getReturnObject(count);
      return Protocol.getOutputCommand(returnObject);
   }

   public void execute(String commandName, BufferedReader reader, BufferedWriter writer) throws Py4JException, IOException {
      char subCommand = NetworkUtil.safeReadLine(reader).charAt(0);
      String returnCommand = null;
      if (subCommand == 'l') {
         returnCommand = this.slice_list(reader);
      } else if (subCommand == 'a') {
         returnCommand = this.concat_list(reader);
      } else if (subCommand == 'm') {
         returnCommand = this.mult_list(reader);
      } else if (subCommand == 'i') {
         returnCommand = this.imult_list(reader);
      } else if (subCommand == 'f') {
         returnCommand = this.count_list(reader);
      } else {
         returnCommand = this.call_collections_method(reader, subCommand);
      }

      this.logger.finest("Returning command: " + returnCommand);
      writer.write(returnCommand);
      writer.flush();
   }

   private String imult_list(BufferedReader reader) throws IOException {
      List list1 = (List)this.gateway.getObject(reader.readLine());
      List tempList = new ArrayList(list1.subList(0, list1.size()));
      int n = Protocol.getInteger(reader.readLine());
      reader.readLine();
      if (n <= 0) {
         list1.clear();
      } else {
         for(int i = 1; i < n; ++i) {
            list1.addAll(tempList);
         }
      }

      return "!yv\n";
   }

   private String max_list(List list) {
      Object object = Collections.max(list);
      ReturnObject returnObject = this.gateway.getReturnObject(object);
      return Protocol.getOutputCommand(returnObject);
   }

   private String min_list(List list) {
      Object object = Collections.min(list);
      ReturnObject returnObject = this.gateway.getReturnObject(object);
      return Protocol.getOutputCommand(returnObject);
   }

   private String mult_list(BufferedReader reader) throws IOException {
      List list1 = (List)this.gateway.getObject(reader.readLine());
      int n = Protocol.getInteger(reader.readLine());
      reader.readLine();
      List list2 = new ArrayList();

      for(int i = 0; i < n; ++i) {
         list2.addAll(list1);
      }

      ReturnObject returnObject = this.gateway.getReturnObject(list2);
      return Protocol.getOutputCommand(returnObject);
   }

   private String reverse_list(List list) {
      Collections.reverse(list);
      return "!yv\n";
   }

   private String slice_list(BufferedReader reader) throws IOException {
      List list1 = (List)this.gateway.getObject(reader.readLine());
      List<Object> arguments = this.getArguments(reader);
      List slice = new ArrayList();

      for(Object argument : arguments) {
         slice.add(list1.get((Integer)argument));
      }

      ReturnObject returnObject = this.gateway.getReturnObject(slice);
      return Protocol.getOutputCommand(returnObject);
   }

   private String sort_list(List list) {
      Collections.sort(list);
      return "!yv\n";
   }
}
