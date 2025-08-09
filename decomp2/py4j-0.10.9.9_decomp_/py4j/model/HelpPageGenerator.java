package py4j.model;

import java.util.regex.Pattern;
import py4j.reflection.TypeUtil;

public class HelpPageGenerator {
   public static final String PREFIX = "|";
   public static final String INDENT = "  ";
   public static final String PREFIX_INDENT = "|  ";
   public static final String DOUBLE_LINES = "\n|  \n";
   public static final String SEPARATOR = "------------------------------------------------------------";
   public static final String PREFIX_SEPARATOR = "|  ------------------------------------------------------------\n";

   public static final String getHelpPage(Py4JClass clazz, String pattern, boolean shortName) {
      Pattern regex = getRegex(pattern);
      StringBuilder builder = new StringBuilder();
      builder.append("Help on ");
      builder.append("class ");
      builder.append(TypeUtil.getName(clazz.getName(), true));
      builder.append(" in package ");
      builder.append(TypeUtil.getPackage(clazz.getName()));
      builder.append(":\n\n");
      builder.append(clazz.getSignature(shortName));
      builder.append(" {");
      builder.append("\n|  \n");
      builder.append("|  ");
      builder.append("Methods defined here:");
      builder.append("\n|  \n");

      for(Py4JMethod method : clazz.getMethods()) {
         String signature = method.getSignature(shortName);
         if (regex.matcher(signature).matches()) {
            builder.append("|  ");
            builder.append(signature);
            builder.append("\n|  \n");
         }
      }

      builder.append("|  ------------------------------------------------------------\n");
      builder.append("|  ");
      builder.append("Fields defined here:");
      builder.append("\n|  \n");

      for(Py4JField field : clazz.getFields()) {
         String signature = field.getSignature(shortName);
         if (regex.matcher(signature).matches()) {
            builder.append("|  ");
            builder.append(signature);
            builder.append("\n|  \n");
         }
      }

      builder.append("|  ------------------------------------------------------------\n");
      builder.append("|  ");
      builder.append("Internal classes defined here:");
      builder.append("\n|  \n");

      for(Py4JClass internalClass : clazz.getClasses()) {
         builder.append("|  ");
         builder.append(internalClass.getSignature(shortName));
         builder.append("\n|  \n");
      }

      builder.append("}");
      builder.append("\n");
      return builder.toString();
   }

   public static final String getHelpPage(Py4JMethod method, boolean shortName) {
      StringBuilder builder = new StringBuilder();
      builder.append("Method \"");
      builder.append(method.getName());
      builder.append("\" of class ");
      builder.append(method.getContainer());
      builder.append("\n{\n");
      builder.append("|  ");
      builder.append(method.getSignature(shortName));
      builder.append("\n}");
      builder.append("\n");
      return builder.toString();
   }

   public static final Pattern getRegex(String pattern) {
      if (pattern == null) {
         return Pattern.compile(".*");
      } else {
         String newPattern = "^" + pattern.trim().replace(".", "\\.").replace("*", ".*").replace("?", ".?").replace("(", "\\(").replace(")", "\\)");
         return Pattern.compile(newPattern);
      }
   }
}
