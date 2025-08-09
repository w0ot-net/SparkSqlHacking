package org.apache.ivy.util.extendable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ivy.plugins.parser.ParserSettings;
import org.xml.sax.Attributes;

public final class ExtendableItemHelper {
   private static final char separator = '.';

   private ExtendableItemHelper() {
   }

   public static String decodeAttribute(String blob, String prefix) {
      StringBuilder builder = new StringBuilder(blob);
      int cur = prefix.length();
      int sepi = blob.indexOf(46, cur);
      int qlen = Integer.parseInt(blob.substring(cur, sepi));
      cur = sepi + 1;
      if (qlen > 0) {
         builder.setCharAt(cur + qlen, ':');
      }

      return builder.substring(cur);
   }

   public static String encodeAttribute(String attribute, String prefix) {
      StringBuilder builder = new StringBuilder(attribute.length() + prefix.length() + 5);
      int coloni = attribute.indexOf(58);
      int qlen = coloni == -1 ? 0 : coloni;
      builder.append(prefix);
      builder.append(qlen);
      builder.append('.');
      builder.append(attribute);
      if (qlen > 0) {
         int cur = builder.length() - attribute.length();
         builder.setCharAt(cur + qlen, '.');
      }

      return builder.toString();
   }

   public static Map getExtraAttributes(Attributes attributes, String prefix) {
      Map<String, String> ret = new HashMap();

      for(int i = 0; i < attributes.getLength(); ++i) {
         if (attributes.getQName(i).startsWith(prefix)) {
            String name = decodeAttribute(attributes.getQName(i), prefix);
            String value = attributes.getValue(i);
            ret.put(name, value);
         }
      }

      return ret;
   }

   /** @deprecated */
   @Deprecated
   public static Map getExtraAttributes(ParserSettings settings, Attributes attributes, String[] ignoredAttNames) {
      return getExtraAttributes(settings, attributes, Arrays.asList(ignoredAttNames));
   }

   public static Map getExtraAttributes(ParserSettings settings, Attributes attributes, List ignoredAttNames) {
      Map<String, String> ret = new HashMap();

      for(int i = 0; i < attributes.getLength(); ++i) {
         if (!ignoredAttNames.contains(attributes.getQName(i))) {
            ret.put(attributes.getQName(i), settings.substitute(attributes.getValue(i)));
         }
      }

      return ret;
   }

   /** @deprecated */
   @Deprecated
   public static void fillExtraAttributes(ParserSettings settings, DefaultExtendableItem item, Attributes attributes, String[] ignoredAttNames) {
      fillExtraAttributes(settings, item, attributes, Arrays.asList(ignoredAttNames));
   }

   public static void fillExtraAttributes(ParserSettings settings, DefaultExtendableItem item, Attributes attributes, List ignoredAttNames) {
      Map<String, String> att = getExtraAttributes(settings, attributes, ignoredAttNames);

      for(Map.Entry entry : att.entrySet()) {
         item.setExtraAttribute((String)entry.getKey(), (String)entry.getValue());
      }

   }
}
