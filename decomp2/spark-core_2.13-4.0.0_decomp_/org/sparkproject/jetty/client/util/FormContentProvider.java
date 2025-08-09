package org.sparkproject.jetty.client.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.sparkproject.jetty.util.Fields;

/** @deprecated */
@Deprecated
public class FormContentProvider extends StringContentProvider {
   public FormContentProvider(Fields fields) {
      this(fields, StandardCharsets.UTF_8);
   }

   public FormContentProvider(Fields fields, Charset charset) {
      super("application/x-www-form-urlencoded", convert(fields, charset), charset);
   }

   public static String convert(Fields fields) {
      return convert(fields, StandardCharsets.UTF_8);
   }

   public static String convert(Fields fields, Charset charset) {
      return FormRequestContent.convert(fields, charset);
   }
}
