package org.apache.log4j.spi;

public abstract class Filter {
   public static final int DENY = -1;
   public static final int NEUTRAL = 0;
   public static final int ACCEPT = 1;
   /** @deprecated */
   @Deprecated
   public Filter next;
   private static final boolean isCorePresent;

   public void activateOptions() {
   }

   public abstract int decide(LoggingEvent event);

   public void setNext(final Filter next) {
      this.next = next;
   }

   public Filter getNext() {
      return this.next;
   }

   static {
      boolean temp;
      try {
         temp = Class.forName("org.apache.logging.log4j.core.Filter") != null;
      } catch (LinkageError | Exception var2) {
         temp = false;
      }

      isCorePresent = temp;
   }
}
