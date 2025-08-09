package org.datanucleus.store.valuegenerator;

import java.util.Properties;
import org.datanucleus.store.StoreManager;

public abstract class AbstractDatastoreGenerator extends AbstractGenerator {
   protected StoreManager storeMgr;
   protected ValueGenerationConnectionProvider connectionProvider;

   public AbstractDatastoreGenerator(String name, Properties props) {
      super(name, props);
      this.allocationSize = 1;
   }

   public void setStoreManager(StoreManager storeMgr) {
      this.storeMgr = storeMgr;
   }

   public void setConnectionProvider(ValueGenerationConnectionProvider provider) {
      this.connectionProvider = provider;
   }

   public ConnectionPreference getConnectionPreference() {
      return AbstractDatastoreGenerator.ConnectionPreference.NONE;
   }

   public static enum ConnectionPreference {
      NONE,
      EXISTING,
      NEW;
   }
}
