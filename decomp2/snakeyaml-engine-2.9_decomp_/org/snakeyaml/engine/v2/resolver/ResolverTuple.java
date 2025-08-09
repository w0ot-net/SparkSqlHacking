package org.snakeyaml.engine.v2.resolver;

import java.util.Objects;
import java.util.regex.Pattern;
import org.snakeyaml.engine.v2.nodes.Tag;

final class ResolverTuple {
   private final Tag tag;
   private final Pattern regexp;

   public ResolverTuple(Tag tag, Pattern regexp) {
      Objects.requireNonNull(tag);
      Objects.requireNonNull(regexp);
      this.tag = tag;
      this.regexp = regexp;
   }

   public Tag getTag() {
      return this.tag;
   }

   public Pattern getRegexp() {
      return this.regexp;
   }

   public String toString() {
      return "Tuple tag=" + this.tag + " regexp=" + this.regexp;
   }
}
