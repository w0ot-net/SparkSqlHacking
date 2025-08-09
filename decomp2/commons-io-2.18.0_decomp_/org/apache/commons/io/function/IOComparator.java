package org.apache.commons.io.function;

import java.io.IOException;
import java.util.Comparator;

@FunctionalInterface
public interface IOComparator {
   default Comparator asComparator() {
      return (t, u) -> Uncheck.compare(this, t, u);
   }

   int compare(Object var1, Object var2) throws IOException;
}
