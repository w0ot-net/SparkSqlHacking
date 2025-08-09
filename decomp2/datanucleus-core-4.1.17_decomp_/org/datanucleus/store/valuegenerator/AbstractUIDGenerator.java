package org.datanucleus.store.valuegenerator;

import java.util.Properties;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public abstract class AbstractUIDGenerator extends AbstractGenerator {
   public AbstractUIDGenerator(String name, Properties props) {
      super(name, props);
      this.allocationSize = 1;
   }

   public static Class getStorageClass() {
      return String.class;
   }

   protected ValueGenerationBlock reserveBlock(long size) {
      Object[] ids = new Object[(int)size];

      for(int i = 0; (long)i < size; ++i) {
         ids[i] = this.getIdentifier();
      }

      if (NucleusLogger.VALUEGENERATION.isDebugEnabled()) {
         NucleusLogger.VALUEGENERATION.debug(Localiser.msg("040004", "" + size));
      }

      return new ValueGenerationBlock(ids);
   }

   protected abstract String getIdentifier();
}
