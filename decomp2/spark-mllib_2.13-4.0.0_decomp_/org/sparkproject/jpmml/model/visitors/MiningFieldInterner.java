package org.sparkproject.jpmml.model.visitors;

import org.sparkproject.dmg.pmml.MiningField;
import org.sparkproject.jpmml.model.PMMLObjectCache;

public class MiningFieldInterner extends PMMLObjectInterner {
   public static final ThreadLocal CACHE_PROVIDER = new ThreadLocal() {
      public PMMLObjectCache initialValue() {
         return new PMMLObjectCache();
      }
   };

   public MiningFieldInterner() {
      super(MiningField.class, (PMMLObjectCache)CACHE_PROVIDER.get());
   }
}
