package org.apache.zookeeper.cli;

import java.nio.charset.StandardCharsets;

public class PlainOutputFormatter implements OutputFormatter {
   public static final PlainOutputFormatter INSTANCE = new PlainOutputFormatter();

   public String format(byte[] data) {
      return new String(data, StandardCharsets.UTF_8);
   }
}
