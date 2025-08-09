package org.sparkproject.dmg.pmml;

public interface HasRequiredId extends HasId, Indexable {
   String requireId();

   default String getKey() {
      return this.requireId();
   }
}
