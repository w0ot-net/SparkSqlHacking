package org.apache.logging.log4j.core.layout.internal;

import java.util.List;

public class ExcludeChecker implements ListChecker {
   private final List list;

   public ExcludeChecker(final List list) {
      this.list = list;
   }

   public boolean check(final String key) {
      return !this.list.contains(key);
   }

   public String toString() {
      return "ThreadContextExcludes=" + this.list.toString();
   }
}
