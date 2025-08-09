package org.sparkproject.guava.io;

import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public enum FileWriteMode {
   APPEND;

   // $FF: synthetic method
   private static FileWriteMode[] $values() {
      return new FileWriteMode[]{APPEND};
   }
}
