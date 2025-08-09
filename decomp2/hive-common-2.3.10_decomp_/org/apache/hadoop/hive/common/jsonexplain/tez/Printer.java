package org.apache.hadoop.hive.common.jsonexplain.tez;

public final class Printer {
   public static final String lineSeparator = System.getProperty("line.separator");
   private final StringBuilder builder = new StringBuilder();

   public void print(String string) {
      this.builder.append(string);
   }

   public void println(String string) {
      this.builder.append(string);
      this.builder.append(lineSeparator);
   }

   public void println() {
      this.builder.append(lineSeparator);
   }

   public String toString() {
      return this.builder.toString();
   }
}
