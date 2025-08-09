package org.jline.style;

import java.util.Objects;

public class StyleResolver extends org.jline.utils.StyleResolver {
   private final StyleSource source;
   private final String group;

   public StyleResolver(StyleSource source, String group) {
      super((s) -> source.get(group, s));
      this.source = (StyleSource)Objects.requireNonNull(source);
      this.group = (String)Objects.requireNonNull(group);
   }

   public StyleSource getSource() {
      return this.source;
   }

   public String getGroup() {
      return this.group;
   }
}
