package org.sparkproject.jetty.client.util;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.sparkproject.jetty.util.Fields;

public class FormRequestContent extends StringRequestContent {
   public FormRequestContent(Fields fields) {
      this(fields, StandardCharsets.UTF_8);
   }

   public FormRequestContent(Fields fields, Charset charset) {
      super("application/x-www-form-urlencoded", convert(fields, charset), charset);
   }

   public static String convert(Fields fields) {
      return convert(fields, StandardCharsets.UTF_8);
   }

   public static String convert(Fields fields, Charset charset) {
      StringBuilder builder = new StringBuilder(fields.getSize() * 32);

      for(Fields.Field field : fields) {
         for(String value : field.getValues()) {
            if (builder.length() > 0) {
               builder.append("&");
            }

            builder.append(encode(field.getName(), charset)).append("=").append(encode(value, charset));
         }
      }

      return builder.toString();
   }

   private static String encode(String value, Charset charset) {
      return URLEncoder.encode(value, charset);
   }
}
