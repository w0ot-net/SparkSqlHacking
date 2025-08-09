package org.codehaus.janino;

public enum Access {
   PRIVATE,
   PROTECTED,
   DEFAULT,
   PUBLIC;

   public static Access fromString(String s) {
      if ("private".equals(s)) {
         return PRIVATE;
      } else if ("protected".equals(s)) {
         return PROTECTED;
      } else if ("public".equals(s)) {
         return PUBLIC;
      } else {
         throw new IllegalArgumentException(s);
      }
   }

   public String toString() {
      return this.name().toLowerCase();
   }

   // $FF: synthetic method
   private static Access[] $values() {
      return new Access[]{PRIVATE, PROTECTED, DEFAULT, PUBLIC};
   }
}
