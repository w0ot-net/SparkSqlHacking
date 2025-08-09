package org.apache.spark.sql.connector.catalog;

import org.apache.spark.annotation.Evolving;

@Evolving
public interface Identifier {
   static Identifier of(String[] namespace, String name) {
      return new IdentifierImpl(namespace, name);
   }

   String[] namespace();

   String name();
}
