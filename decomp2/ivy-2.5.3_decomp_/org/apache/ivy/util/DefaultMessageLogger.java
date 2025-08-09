package org.apache.ivy.util;

public class DefaultMessageLogger extends AbstractMessageLogger {
   private int level = 2;

   public DefaultMessageLogger(int level) {
      this.level = level;
   }

   public void log(String msg, int level) {
      if (level <= this.level) {
         System.out.println(msg);
      }

   }

   public void rawlog(String msg, int level) {
      this.log(msg, level);
   }

   public void doProgress() {
      System.out.print(".");
   }

   public void doEndProgress(String msg) {
      System.out.println(msg);
   }

   public int getLevel() {
      return this.level;
   }
}
