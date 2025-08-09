package org.yaml.snakeyaml.parser;

import java.util.Map;
import org.yaml.snakeyaml.DumperOptions;

class VersionTagsTuple {
   private final DumperOptions.Version version;
   private final Map tags;

   public VersionTagsTuple(DumperOptions.Version version, Map tags) {
      this.version = version;
      this.tags = tags;
   }

   public DumperOptions.Version getVersion() {
      return this.version;
   }

   public Map getTags() {
      return this.tags;
   }

   public String toString() {
      return String.format("VersionTagsTuple<%s, %s>", this.version, this.tags);
   }
}
