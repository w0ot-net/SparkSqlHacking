package py4j;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import py4j.reflection.ReflectionUtil;

public class Protocol {
   public static final char BYTES_TYPE = 'j';
   public static final char INTEGER_TYPE = 'i';
   public static final char LONG_TYPE = 'L';
   public static final char BOOLEAN_TYPE = 'b';
   public static final char DOUBLE_TYPE = 'd';
   public static final char DECIMAL_TYPE = 'D';
   public static final char STRING_TYPE = 's';
   public static final char REFERENCE_TYPE = 'r';
   public static final char LIST_TYPE = 'l';
   public static final char SET_TYPE = 'h';
   public static final char ARRAY_TYPE = 't';
   public static final char MAP_TYPE = 'a';
   public static final char ITERATOR_TYPE = 'g';
   public static final char NULL_TYPE = 'n';
   public static final char PYTHON_PROXY_TYPE = 'f';
   public static final char PACKAGE_TYPE = 'p';
   public static final char CLASS_TYPE = 'c';
   public static final char METHOD_TYPE = 'm';
   public static final char NO_MEMBER = 'o';
   public static final char VOID = 'v';
   public static final char RETURN_MESSAGE = '!';
   public static final char END = 'e';
   public static final char END_OUTPUT = '\n';
   public static final char ERROR = 'x';
   public static final char FATAL_ERROR = 'z';
   public static final char SUCCESS = 'y';
   public static final String AUTH_COMMAND_NAME = "A";
   public static final String ERROR_COMMAND = "!x\n";
   public static final String VOID_COMMAND = "!yv\n";
   public static final String NO_SUCH_FIELD = "!yo\n";
   public static final String ENTRY_POINT_OBJECT_ID = "t";
   public static final String DEFAULT_JVM_OBJECT_ID = "j";
   public static final String GATEWAY_SERVER_ID = "GATEWAY_SERVER";
   public static final String STATIC_PREFIX = "z:";
   public static final String PYTHON_NAN = "nan";
   public static final String PYTHON_INFINITY = "inf";
   public static final String PYTHON_NEGATIVE_INFINITY = "-inf";

   public static String encodeBytes(byte[] bytes) {
      return Base64.encodeToString(bytes, false);
   }

   public static final boolean getBoolean(String commandPart) {
      return Boolean.parseBoolean(commandPart.substring(1, commandPart.length()));
   }

   public static final byte[] getBytes(String commandPart) {
      return Base64.decode(commandPart.substring(1));
   }

   public static final BigDecimal getDecimal(String commandPart) {
      return new BigDecimal(commandPart.substring(1, commandPart.length()));
   }

   public static final double getDouble(String commandPart) {
      String doubleValue = commandPart.substring(1, commandPart.length());

      try {
         return Double.parseDouble(doubleValue);
      } catch (NumberFormatException e) {
         if (doubleValue.equals("inf")) {
            return Double.POSITIVE_INFINITY;
         } else if (doubleValue.equals("-inf")) {
            return Double.NEGATIVE_INFINITY;
         } else if (doubleValue.equals("nan")) {
            return Double.NaN;
         } else {
            throw e;
         }
      }
   }

   public static final int getInteger(String commandPart) {
      return Integer.parseInt(commandPart.substring(1, commandPart.length()));
   }

   public static final long getLong(String commandPart) {
      return Long.parseLong(commandPart.substring(1, commandPart.length()));
   }

   public static final String getMemberOutputCommand(char memberType) {
      StringBuilder builder = new StringBuilder();
      builder.append('!');
      builder.append('y');
      builder.append(memberType);
      builder.append('\n');
      return builder.toString();
   }

   public static final String getMemberOutputCommand(char memberType, String fqn) {
      StringBuilder builder = new StringBuilder();
      builder.append('!');
      builder.append('y');
      builder.append(memberType);
      builder.append(fqn);
      builder.append('\n');
      return builder.toString();
   }

   public static String getNoSuchFieldOutputCommand() {
      return "!yo\n";
   }

   public static final Object getNull(String commandPart) {
      return null;
   }

   public static final Object getObject(String commandPart, Gateway gateway) {
      if (!isEmpty(commandPart) && !isEnd(commandPart)) {
         switch (commandPart.charAt(0)) {
            case 'D':
               return getDecimal(commandPart);
            case 'L':
               return getLong(commandPart);
            case 'b':
               return getBoolean(commandPart);
            case 'd':
               return getDouble(commandPart);
            case 'f':
               return getPythonProxy(commandPart, gateway);
            case 'i':
               try {
                  return getInteger(commandPart);
               } catch (NumberFormatException var3) {
                  return getLong(commandPart);
               }
            case 'j':
               return getBytes(commandPart);
            case 'n':
               return getNull(commandPart);
            case 'r':
               return getReference(commandPart, gateway);
            case 's':
               return getString(commandPart);
            case 'v':
               return getNull(commandPart);
            default:
               throw new Py4JException("Command Part is unknown: " + commandPart);
         }
      } else {
         throw new Py4JException("Command Part is Empty or is the End of Command Part");
      }
   }

   public static final String getOutputCommand(ReturnObject rObject) {
      StringBuilder builder = new StringBuilder();
      builder.append('!');
      if (rObject.isError()) {
         builder.append(rObject.getCommandPart());
      } else {
         builder.append('y');
         builder.append(rObject.getCommandPart());
      }

      builder.append('\n');
      return builder.toString();
   }

   public static final String getOutputErrorCommand() {
      return "!x\n";
   }

   public static final String getOutputErrorCommand(String errorMessage) {
      StringBuilder builder = new StringBuilder();
      builder.append('!');
      builder.append('x');
      builder.append('s');
      builder.append(StringUtil.escape(errorMessage));
      builder.append('\n');
      return builder.toString();
   }

   public static final String getOutputErrorCommand(Throwable throwable) {
      StringBuilder builder = new StringBuilder();
      builder.append('!');
      builder.append('x');
      builder.append('s');
      builder.append(StringUtil.escape(getThrowableAsString(throwable)));
      builder.append('\n');
      return builder.toString();
   }

   public static final String getOutputFatalErrorCommand(Throwable throwable) {
      StringBuilder builder = new StringBuilder();
      builder.append('!');
      builder.append('z');
      builder.append('s');
      builder.append(StringUtil.escape(getThrowableAsString(throwable)));
      builder.append('\n');
      return builder.toString();
   }

   public static final String getOutputVoidCommand() {
      return "!yv\n";
   }

   public static final String getAuthCommand(String authToken) {
      StringBuilder builder = new StringBuilder();
      builder.append("A");
      builder.append("\n");
      builder.append(StringUtil.escape(authToken));
      builder.append("\n");
      builder.append('e');
      builder.append("\n");
      return builder.toString();
   }

   public static char getPrimitiveType(Object primitiveObject) {
      char c = 'i';
      if (!(primitiveObject instanceof String) && !(primitiveObject instanceof Character)) {
         if (primitiveObject instanceof Long) {
            c = 'L';
         } else if (!(primitiveObject instanceof Double) && !(primitiveObject instanceof Float)) {
            if (primitiveObject instanceof Boolean) {
               c = 'b';
            } else if (primitiveObject instanceof byte[]) {
               c = 'j';
            }
         } else {
            c = 'd';
         }
      } else {
         c = 's';
      }

      return c;
   }

   public static Object getPythonProxy(String commandPart, Gateway gateway) {
      String proxyString = commandPart.substring(1, commandPart.length());
      String[] parts = proxyString.split(";");
      int length = parts.length;
      Class<?>[] interfaces = new Class[length - 1];
      if (length < 2) {
         throw new Py4JException("Invalid Python Proxy.");
      } else {
         for(int i = 1; i < length; ++i) {
            try {
               interfaces[i - 1] = ReflectionUtil.classForName(parts[i]);
               if (!interfaces[i - 1].isInterface()) {
                  throw new Py4JException("This class " + parts[i] + " is not an interface and cannot be used as a Python Proxy.");
               }
            } catch (ClassNotFoundException var8) {
               throw new Py4JException("Invalid interface name: " + parts[i]);
            }
         }

         return gateway.createProxy(ReflectionUtil.getClassLoader(), interfaces, parts[0]);
      }
   }

   /** @deprecated */
   public static Object getPythonProxyHandler(ClassLoader classLoader, Class[] interfacesToImplement, String objectId, Gateway gateway) {
      return gateway.createProxy(classLoader, interfacesToImplement, objectId);
   }

   public static final Object getReference(String commandPart, Gateway gateway) {
      String reference = commandPart.substring(1, commandPart.length());
      if (reference.trim().length() == 0) {
         throw new Py4JException("Reference is empty.");
      } else {
         return gateway.getObject(reference);
      }
   }

   public static final Object getReturnValue(String returnMessage, Gateway gateway) throws Throwable {
      Object result = getObject(returnMessage.substring(1), gateway);
      if (isError(returnMessage)) {
         if (result instanceof Throwable) {
            throw (Throwable)result;
         } else {
            throw new Py4JException("An exception was raised by the Python Proxy. Return Message: " + result);
         }
      } else {
         return result;
      }
   }

   public static final Throwable getRootThrowable(Throwable throwable, boolean skipInvocation) {
      Throwable child;
      if (!skipInvocation && throwable instanceof InvocationTargetException) {
         child = throwable.getCause();
         skipInvocation = true;
      } else {
         if (!(throwable instanceof Py4JException) && !(throwable instanceof Py4JNetworkException)) {
            return throwable;
         }

         child = throwable.getCause();
      }

      return child == null ? throwable : getRootThrowable(child, skipInvocation);
   }

   public static final String getString(String commandPart) {
      String toReturn = "";
      if (commandPart.length() >= 2) {
         toReturn = StringUtil.unescape(commandPart.substring(1, commandPart.length()));
      }

      return toReturn;
   }

   public static final String getThrowableAsString(Throwable throwable) {
      Throwable root = getRootThrowable(throwable, false);
      StringWriter stringWriter = new StringWriter();
      PrintWriter printWriter = new PrintWriter(stringWriter);
      root.printStackTrace(printWriter);
      return stringWriter.toString();
   }

   public static final boolean isBoolean(String commandPart) {
      return commandPart.charAt(0) == 'b';
   }

   public static final boolean isReturnMessage(String commandPart) {
      return commandPart != null && commandPart.length() > 1 && commandPart.charAt(0) == '!';
   }

   public static final boolean isBytes(String commandPart) {
      return commandPart.charAt(0) == 'j';
   }

   public static final boolean isDecimal(String commandPart) {
      return commandPart.charAt(0) == 'D';
   }

   public static final boolean isDouble(String commandPart) {
      return commandPart.charAt(0) == 'd';
   }

   public static final boolean isEmpty(String commandPart) {
      return commandPart == null || commandPart.trim().length() == 0;
   }

   public static final boolean isEnd(String commandPart) {
      return commandPart.length() == 1 && commandPart.charAt(0) == 'e';
   }

   public static final boolean isError(String returnMessage) {
      return returnMessage == null || returnMessage.length() == 0 || returnMessage.charAt(0) == 'x';
   }

   public static final boolean isInteger(String commandPart) {
      return commandPart.charAt(0) == 'i';
   }

   public static final boolean isLong(String commandPart) {
      return commandPart.charAt(0) == 'L';
   }

   public static final boolean isNull(String commandPart) {
      return commandPart.charAt(0) == 'n';
   }

   public static final boolean isPythonProxy(String commandPart) {
      return commandPart.charAt(0) == 'f';
   }

   public static final boolean isReference(String commandPart) {
      return commandPart.charAt(0) == 'r';
   }

   public static final boolean isString(String commandPart) {
      return commandPart.charAt(0) == 's';
   }
}
