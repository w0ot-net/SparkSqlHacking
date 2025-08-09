package org.apache.logging.log4j.core.layout.internal;

public interface ListChecker {
   NoopChecker NOOP_CHECKER = new NoopChecker();

   boolean check(final String key);

   public static class NoopChecker implements ListChecker {
      public boolean check(final String key) {
         return true;
      }

      public String toString() {
         return "";
      }
   }
}
