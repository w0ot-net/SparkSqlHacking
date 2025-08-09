package org.apache.commons.codec.language.bm;

public enum RuleType {
   APPROX("approx"),
   EXACT("exact"),
   RULES("rules");

   private final String name;

   private RuleType(final String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }

   // $FF: synthetic method
   private static RuleType[] $values() {
      return new RuleType[]{APPROX, EXACT, RULES};
   }
}
