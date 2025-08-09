package org.datanucleus.store.valuegenerator;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.datanucleus.store.StoreManager;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class ValueGenerationManager {
   protected Map generatorsByName = new HashMap();

   public void clear() {
      this.generatorsByName.clear();
   }

   public synchronized ValueGenerator getValueGenerator(String name) {
      return name == null ? null : (ValueGenerator)this.generatorsByName.get(name);
   }

   public synchronized ValueGenerator createValueGenerator(String name, Class generatorClass, Properties props, StoreManager storeMgr, ValueGenerationConnectionProvider connectionProvider) {
      ValueGenerator generator;
      try {
         if (NucleusLogger.VALUEGENERATION.isDebugEnabled()) {
            NucleusLogger.VALUEGENERATION.debug(Localiser.msg("040001", generatorClass.getName(), name));
         }

         Class[] argTypes = new Class[]{String.class, Properties.class};
         Object[] args = new Object[]{name, props};
         Constructor ctor = generatorClass.getConstructor(argTypes);
         generator = (ValueGenerator)ctor.newInstance(args);
      } catch (Exception e) {
         NucleusLogger.VALUEGENERATION.error(e);
         throw new ValueGenerationException(Localiser.msg("040000", generatorClass.getName(), e), e);
      }

      if (generator instanceof AbstractDatastoreGenerator && storeMgr != null) {
         ((AbstractDatastoreGenerator)generator).setStoreManager(storeMgr);
         ((AbstractDatastoreGenerator)generator).setConnectionProvider(connectionProvider);
      }

      this.generatorsByName.put(name, generator);
      return generator;
   }
}
