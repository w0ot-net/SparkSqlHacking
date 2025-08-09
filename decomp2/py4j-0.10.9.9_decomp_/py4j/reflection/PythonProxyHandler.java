package py4j.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import py4j.Gateway;
import py4j.Protocol;
import py4j.Py4JException;

public class PythonProxyHandler implements InvocationHandler {
   private final String id;
   private final Gateway gateway;
   private final Logger logger = Logger.getLogger(PythonProxyHandler.class.getName());
   private final String finalizeCommand;
   public static final String CALL_PROXY_COMMAND_NAME = "c\n";
   public static final String GARBAGE_COLLECT_PROXY_COMMAND_NAME = "g\n";

   public PythonProxyHandler(String id, Gateway gateway) {
      this.id = id;
      this.gateway = gateway;
      this.finalizeCommand = "g\n" + id + "\ne\n";
   }

   protected void finalize() throws Throwable {
      try {
         if (this.gateway.getCallbackClient().isMemoryManagementEnabled() && this.id != "t") {
            this.logger.fine("Finalizing python proxy id " + this.id);
            this.gateway.getCallbackClient().sendCommand(this.finalizeCommand, false);
         }
      } catch (Exception var5) {
         this.logger.warning("Python Proxy ID could not send a finalize message: " + this.id);
      } finally {
         super.finalize();
      }

   }

   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      this.logger.fine("Method " + method.getName() + " called on Python object " + this.id);
      StringBuilder sBuilder = new StringBuilder();
      sBuilder.append("c\n");
      sBuilder.append(this.id);
      sBuilder.append("\n");
      sBuilder.append(method.getName());
      sBuilder.append("\n");
      if (args != null) {
         for(Object arg : args) {
            sBuilder.append(this.gateway.getReturnObject(arg).getCommandPart());
            sBuilder.append("\n");
         }
      }

      sBuilder.append("e\n");
      String returnCommand = this.gateway.getCallbackClient().sendCommand(sBuilder.toString());
      Object output = Protocol.getReturnValue(returnCommand, this.gateway);
      Object convertedOutput = this.convertOutput(method, output);
      return convertedOutput;
   }

   private Object convertOutput(Method method, Object output) {
      Class<?> returnType = method.getReturnType();
      if (output != null && !returnType.equals(Void.TYPE)) {
         Class<?> outputType = output.getClass();
         Class<?>[] parameters = new Class[]{returnType};
         Class<?>[] arguments = new Class[]{outputType};
         List<TypeConverter> converters = new ArrayList();
         int cost = MethodInvoker.buildConverters(converters, parameters, arguments);
         if (cost == -1) {
            throw new Py4JException("Incompatible output type. Expected: " + returnType.getName() + " Actual: " + outputType.getName());
         } else {
            return ((TypeConverter)converters.get(0)).convert(output);
         }
      } else {
         return output;
      }
   }
}
