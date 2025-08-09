package org.datanucleus.store.valuegenerator;

import java.util.Properties;
import java.util.UUID;

public class UUIDGenerator extends AbstractUIDGenerator {
   public UUIDGenerator(String name, Properties props) {
      super(name, props);
   }

   protected String getIdentifier() {
      return UUID.randomUUID().toString();
   }
}
