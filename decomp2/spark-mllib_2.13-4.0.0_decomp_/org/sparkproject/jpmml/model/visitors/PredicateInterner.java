package org.sparkproject.jpmml.model.visitors;

import org.sparkproject.dmg.pmml.False;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Predicate;
import org.sparkproject.dmg.pmml.True;
import org.sparkproject.jpmml.model.PMMLObjectCache;

public class PredicateInterner extends PMMLObjectInterner {
   public static final ThreadLocal CACHE_PROVIDER = new ThreadLocal() {
      public PMMLObjectCache initialValue() {
         return new PMMLObjectCache();
      }
   };

   public PredicateInterner() {
      super(Predicate.class, (PMMLObjectCache)CACHE_PROVIDER.get());
   }

   public Predicate intern(Predicate predicate) {
      if (predicate instanceof True) {
         True truePredicate = (True)predicate;
         if (!truePredicate.hasExtensions()) {
            return True.INSTANCE;
         }
      } else if (predicate instanceof False) {
         False falsePredicate = (False)predicate;
         if (!falsePredicate.hasExtensions()) {
            return False.INSTANCE;
         }
      }

      return (Predicate)super.intern((PMMLObject)predicate);
   }
}
