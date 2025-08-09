package org.apache.commons.compress.archivers.zip;

public enum Zip64Mode {
   Always,
   Never,
   AsNeeded,
   AlwaysWithCompatibility;

   // $FF: synthetic method
   private static Zip64Mode[] $values() {
      return new Zip64Mode[]{Always, Never, AsNeeded, AlwaysWithCompatibility};
   }
}
