package org.datanucleus.metadata;

public enum SequenceStrategy {
   NONTRANSACTIONAL("nontransactional"),
   CONTIGUOUS("contiguous"),
   NONCONTIGUOUS("noncontiguous");

   String name;

   private SequenceStrategy(String name) {
      this.name = name;
   }

   public String toString() {
      return this.name;
   }

   public static SequenceStrategy getStrategy(String value) {
      if (value == null) {
         return null;
      } else if (NONTRANSACTIONAL.toString().equalsIgnoreCase(value)) {
         return NONTRANSACTIONAL;
      } else if (CONTIGUOUS.toString().equalsIgnoreCase(value)) {
         return CONTIGUOUS;
      } else {
         return NONCONTIGUOUS.toString().equalsIgnoreCase(value) ? NONCONTIGUOUS : null;
      }
   }
}
