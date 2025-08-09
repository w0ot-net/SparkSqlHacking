package org.snakeyaml.engine.v2.tokens;

import java.util.Objects;
import java.util.Optional;

public final class TagTuple {
   private final Optional handle;
   private final String suffix;

   public TagTuple(Optional handle, String suffix) {
      Objects.requireNonNull(handle);
      this.handle = handle;
      Objects.requireNonNull(suffix);
      this.suffix = suffix;
   }

   public Optional getHandle() {
      return this.handle;
   }

   public String getSuffix() {
      return this.suffix;
   }
}
