package org.snakeyaml.engine.v2.common;

import java.util.Optional;

public enum ScalarStyle {
   DOUBLE_QUOTED(Optional.of('"')),
   SINGLE_QUOTED(Optional.of('\'')),
   LITERAL(Optional.of('|')),
   FOLDED(Optional.of('>')),
   JSON_SCALAR_STYLE(Optional.of('J')),
   PLAIN(Optional.empty());

   private final Optional styleOpt;

   private ScalarStyle(Optional style) {
      this.styleOpt = style;
   }

   public String toString() {
      return String.valueOf(this.styleOpt.orElse(':'));
   }
}
