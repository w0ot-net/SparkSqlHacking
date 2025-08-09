package org.apache.logging.log4j.layout.template.json.util;

import java.util.function.Consumer;
import java.util.function.Supplier;

@FunctionalInterface
public interface RecyclerFactory {
   default Recycler create(Supplier supplier) {
      return this.create(supplier, (ignored) -> {
      });
   }

   Recycler create(Supplier supplier, Consumer cleaner);
}
