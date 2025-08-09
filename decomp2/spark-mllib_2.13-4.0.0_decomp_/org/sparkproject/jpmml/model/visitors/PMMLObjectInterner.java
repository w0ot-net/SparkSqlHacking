package org.sparkproject.jpmml.model.visitors;

import java.util.Objects;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.jpmml.model.PMMLObjectCache;

public abstract class PMMLObjectInterner extends ElementInterner {
   private PMMLObjectCache cache = null;

   protected PMMLObjectInterner(Class type, PMMLObjectCache cache) {
      super(type);
      this.setCache(cache);
   }

   public PMMLObject intern(PMMLObject object) {
      PMMLObjectCache<E> cache = this.getCache();
      return cache.intern(object);
   }

   public PMMLObjectCache getCache() {
      return this.cache;
   }

   private void setCache(PMMLObjectCache cache) {
      this.cache = (PMMLObjectCache)Objects.requireNonNull(cache);
   }
}
