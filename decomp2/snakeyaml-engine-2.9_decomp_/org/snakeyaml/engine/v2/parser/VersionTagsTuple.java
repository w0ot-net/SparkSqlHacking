package org.snakeyaml.engine.v2.parser;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.snakeyaml.engine.v2.common.SpecVersion;

class VersionTagsTuple {
   private final Optional specVersion;
   private final Map tags;

   public VersionTagsTuple(Optional specVersion, Map tags) {
      Objects.requireNonNull(specVersion);
      this.specVersion = specVersion;
      this.tags = tags;
   }

   public Optional getSpecVersion() {
      return this.specVersion;
   }

   public Map getTags() {
      return this.tags;
   }

   public String toString() {
      return String.format("VersionTagsTuple<%s, %s>", this.specVersion, this.tags);
   }
}
