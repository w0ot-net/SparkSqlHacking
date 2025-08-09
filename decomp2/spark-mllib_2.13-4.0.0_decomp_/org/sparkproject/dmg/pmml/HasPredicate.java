package org.sparkproject.dmg.pmml;

import org.sparkproject.jpmml.model.UnsupportedElementException;

public interface HasPredicate {
   default Predicate requirePredicate(Class clazz) {
      Predicate predicate = this.requirePredicate();
      if (!clazz.isInstance(predicate)) {
         throw new UnsupportedElementException(predicate);
      } else {
         return (Predicate)clazz.cast(predicate);
      }
   }

   Predicate requirePredicate();

   Predicate getPredicate();

   PMMLObject setPredicate(Predicate var1);
}
