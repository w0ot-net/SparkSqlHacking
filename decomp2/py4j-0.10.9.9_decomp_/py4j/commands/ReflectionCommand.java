package py4j.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
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

public class ReflectionCommand extends AbstractCommand {
   private final Logger logger = Logger.getLogger(ReflectionCommand.class.getName());
   public static final char GET_UNKNOWN_SUB_COMMAND_NAME = 'u';
   public static final char GET_MEMBER_SUB_COMMAND_NAME = 'm';
   public static final char GET_JAVA_LANG_CLASS_SUB_COMMAND_NAME = 'c';
   public static final String REFLECTION_COMMAND_NAME = "r";
   protected ReflectionEngine rEngine;

   public ReflectionCommand() {
      this.commandName = "r";
   }

   public void execute(String commandName, BufferedReader reader, BufferedWriter writer) throws Py4JException, IOException {
      char subCommand = NetworkUtil.safeReadLine(reader).charAt(0);
      String returnCommand = null;
      if (subCommand == 'u') {
         returnCommand = this.getUnknownMember(reader);
      } else if (subCommand == 'c') {
         returnCommand = this.getJavaLangClass(reader);
      } else {
         returnCommand = this.getMember(reader);
      }

      this.logger.finest("Returning command: " + returnCommand);
      writer.write(returnCommand);
      writer.flush();
   }

   private String getJavaLangClass(BufferedReader reader) throws IOException {
      String fqn = reader.readLine();
      reader.readLine();
      String returnCommand = null;

      try {
         Class<?> clazz = TypeUtil.forName(fqn);
         ReturnObject rObject = this.gateway.getReturnObject(clazz);
         returnCommand = Protocol.getOutputCommand(rObject);
      } catch (ClassNotFoundException var6) {
         returnCommand = Protocol.getOutputErrorCommand("The class " + fqn + " does not exist.");
      } catch (Exception var7) {
         returnCommand = Protocol.getOutputErrorCommand();
      }

      return returnCommand;
   }

   private String getMember(BufferedReader reader) throws IOException {
      String fqn = reader.readLine();
      String member = reader.readLine();
      reader.readLine();
      String returnCommand = null;

      try {
         Class<?> clazz = TypeUtil.forName(fqn);
         Field f = this.rEngine.getField(clazz, member);
         if (f != null && Modifier.isStatic(f.getModifiers())) {
            Object obj = this.rEngine.getFieldValue((Object)null, f);
            ReturnObject rObject = this.gateway.getReturnObject(obj);
            returnCommand = Protocol.getOutputCommand(rObject);
         }

         if (returnCommand == null) {
            Method m = this.rEngine.getMethod(clazz, member);
            if (m != null) {
               if (Modifier.isStatic(m.getModifiers())) {
                  returnCommand = Protocol.getMemberOutputCommand('m');
               } else {
                  returnCommand = Protocol.getOutputErrorCommand("Trying to access a non-static member from a static context.");
               }
            }
         }

         if (returnCommand == null) {
            Class<?> c = this.rEngine.getClass(clazz, member);
            if (c != null) {
               returnCommand = Protocol.getMemberOutputCommand('c');
            } else {
               returnCommand = Protocol.getOutputErrorCommand();
            }
         }
      } catch (Exception var9) {
         returnCommand = Protocol.getOutputErrorCommand();
      }

      return returnCommand;
   }

   private String getUnknownMember(BufferedReader reader) throws IOException {
      String fqn = reader.readLine();
      String jvmId = reader.readLine();
      JVMView view = (JVMView)Protocol.getObject(jvmId, this.gateway);
      reader.readLine();
      String returnCommand = null;

      try {
         String fullyQualifiedName = TypeUtil.forName(fqn, view).getName();
         returnCommand = Protocol.getMemberOutputCommand('c', fullyQualifiedName);
      } catch (ClassNotFoundException var7) {
         returnCommand = Protocol.getMemberOutputCommand('p');
      } catch (Exception e) {
         returnCommand = Protocol.getOutputErrorCommand((Throwable)e);
      }

      return returnCommand;
   }

   public void init(Gateway gateway, Py4JServerConnection connection) {
      super.init(gateway, connection);
      this.rEngine = gateway.getReflectionEngine();
   }
}
