package org.sparkproject.dmg.pmml;

public interface HasRequiredName extends HasName, Indexable {
   String requireName();

   default String getKey() {
      return this.requireName();
   }
}
