package org.jline.jansi;

public enum AnsiColors {
   Colors16("16 colors"),
   Colors256("256 colors"),
   TrueColor("24-bit colors");

   private final String description;

   private AnsiColors(String description) {
      this.description = description;
   }

   String getDescription() {
      return this.description;
   }

   // $FF: synthetic method
   private static AnsiColors[] $values() {
      return new AnsiColors[]{Colors16, Colors256, TrueColor};
   }
}
