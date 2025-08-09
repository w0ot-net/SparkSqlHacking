package org.snakeyaml.engine.v2.events;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.snakeyaml.engine.v2.common.Anchor;
import org.snakeyaml.engine.v2.common.CharConstants;
import org.snakeyaml.engine.v2.common.ScalarStyle;
import org.snakeyaml.engine.v2.exceptions.Mark;

public final class ScalarEvent extends NodeEvent {
   private final Optional tag;
   private final ScalarStyle style;
   private final String value;
   private final ImplicitTuple implicit;

   public ScalarEvent(Optional anchor, Optional tag, ImplicitTuple implicit, String value, ScalarStyle style, Optional startMark, Optional endMark) {
      super(anchor, startMark, endMark);
      Objects.requireNonNull(tag);
      this.tag = tag;
      this.implicit = implicit;
      Objects.requireNonNull(value);
      this.value = value;
      Objects.requireNonNull(style);
      this.style = style;
   }

   public ScalarEvent(Optional anchor, Optional tag, ImplicitTuple implicit, String value, ScalarStyle style) {
      this(anchor, tag, implicit, value, style, Optional.empty(), Optional.empty());
   }

   public Optional getTag() {
      return this.tag;
   }

   public ScalarStyle getScalarStyle() {
      return this.style;
   }

   public String getValue() {
      return this.value;
   }

   public ImplicitTuple getImplicit() {
      return this.implicit;
   }

   public Event.ID getEventId() {
      return Event.ID.Scalar;
   }

   public boolean isPlain() {
      return this.style == ScalarStyle.PLAIN;
   }

   public boolean isLiteral() {
      return this.style == ScalarStyle.LITERAL;
   }

   public boolean isSQuoted() {
      return this.style == ScalarStyle.SINGLE_QUOTED;
   }

   public boolean isDQuoted() {
      return this.style == ScalarStyle.DOUBLE_QUOTED;
   }

   public boolean isFolded() {
      return this.style == ScalarStyle.FOLDED;
   }

   public boolean isJson() {
      return this.style == ScalarStyle.JSON_SCALAR_STYLE;
   }

   public String toString() {
      StringBuilder builder = new StringBuilder("=VAL");
      this.getAnchor().ifPresent((a) -> builder.append(" &" + a));
      if (this.implicit.bothFalse()) {
         this.getTag().ifPresent((theTag) -> builder.append(" <" + theTag + ">"));
      }

      builder.append(" ");
      builder.append(this.getScalarStyle().toString());
      builder.append(this.escapedValue());
      return builder.toString();
   }

   public String escapedValue() {
      return (String)this.value.codePoints().filter((i) -> i < 65535).mapToObj((ch) -> CharConstants.escapeChar(String.valueOf(Character.toChars(ch)))).collect(Collectors.joining(""));
   }
}
