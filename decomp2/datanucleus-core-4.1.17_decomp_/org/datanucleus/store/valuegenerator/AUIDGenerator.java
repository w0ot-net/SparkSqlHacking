package org.datanucleus.store.valuegenerator;

import java.util.Properties;

public class AUIDGenerator extends AbstractUIDGenerator {
   public AUIDGenerator(String name, Properties props) {
      super(name, props);
      this.allocationSize = 1;
   }

   protected String getIdentifier() {
      return (new AUID()).toString();
   }
}
