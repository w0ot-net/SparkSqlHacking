package org.apache.commons.text.lookup;

@FunctionalInterface
public interface BiStringLookup extends StringLookup {
   default String lookup(String key, Object object) {
      return this.lookup(key);
   }
}
