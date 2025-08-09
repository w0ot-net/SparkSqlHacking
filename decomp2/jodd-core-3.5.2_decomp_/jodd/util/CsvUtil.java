package jodd.util;

import java.util.ArrayList;
import java.util.List;

public class CsvUtil {
   protected static final char FIELD_SEPARATOR = ',';
   protected static final char FIELD_QUOTE = '"';
   protected static final String DOUBLE_QUOTE = "\"\"";
   protected static final String SPECIAL_CHARS = "\r\n";

   public static String toCsvString(Object... elements) {
      StringBuilder line = new StringBuilder();
      int last = elements.length - 1;

      for(int i = 0; i < elements.length; ++i) {
         if (elements[i] == null) {
            if (i != last) {
               line.append(',');
            }
         } else {
            String field = elements[i].toString();
            int ndx = field.indexOf(44);
            if (ndx == -1) {
               ndx = field.indexOf(34);
            }

            if (ndx == -1 && (field.startsWith(" ") || field.endsWith(" "))) {
               ndx = 1;
            }

            if (ndx == -1) {
               ndx = StringUtil.indexOfChars(field, "\r\n");
            }

            if (ndx != -1) {
               line.append('"');
            }

            field = StringUtil.replace(field, "\"", "\"\"");
            line.append(field);
            if (ndx != -1) {
               line.append('"');
            }

            if (i != last) {
               line.append(',');
            }
         }
      }

      return line.toString();
   }

   public static String[] toStringArray(String line) {
      List<String> row = new ArrayList();
      boolean inQuotedField = false;
      int fieldStart = 0;
      int len = line.length();

      for(int i = 0; i < len; ++i) {
         char c = line.charAt(i);
         if (c == ',') {
            if (!inQuotedField) {
               addField(row, line, fieldStart, i, inQuotedField);
               fieldStart = i + 1;
            }
         } else if (c == '"') {
            if (inQuotedField) {
               if (i + 1 == len || line.charAt(i + 1) == ',') {
                  addField(row, line, fieldStart, i, inQuotedField);
                  fieldStart = i + 2;
                  ++i;
                  inQuotedField = false;
               }
            } else if (fieldStart == i) {
               inQuotedField = true;
               ++fieldStart;
            }
         }
      }

      if (len > 0 && fieldStart <= len) {
         addField(row, line, fieldStart, len, inQuotedField);
      }

      return (String[])row.toArray(new String[row.size()]);
   }

   private static void addField(List row, String line, int startIndex, int endIndex, boolean inQuoted) {
      String field = line.substring(startIndex, endIndex);
      if (inQuoted) {
         field = StringUtil.replace(field, "\"\"", "\"");
      }

      row.add(field);
   }
}
