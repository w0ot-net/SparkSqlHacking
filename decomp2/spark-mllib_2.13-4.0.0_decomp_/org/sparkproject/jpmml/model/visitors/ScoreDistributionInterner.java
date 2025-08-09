package org.sparkproject.jpmml.model.visitors;

import org.sparkproject.dmg.pmml.ScoreDistribution;
import org.sparkproject.jpmml.model.PMMLObjectCache;

public class ScoreDistributionInterner extends PMMLObjectInterner {
   public static final ThreadLocal CACHE_PROVIDER = new ThreadLocal() {
      public PMMLObjectCache initialValue() {
         return new PMMLObjectCache();
      }
   };

   public ScoreDistributionInterner() {
      super(ScoreDistribution.class, (PMMLObjectCache)CACHE_PROVIDER.get());
   }
}
