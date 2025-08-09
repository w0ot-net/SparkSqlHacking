package org.snakeyaml.engine.v2.tokens;

import java.util.Objects;
import java.util.Optional;
import org.snakeyaml.engine.v2.exceptions.Mark;

public final class TagToken extends Token {
   private final TagTuple value;

   public TagToken(TagTuple value, Optional startMark, Optional endMark) {
      super(startMark, endMark);
      Objects.requireNonNull(value);
      this.value = value;
   }

   public TagTuple getValue() {
      return this.value;
   }

   public Token.ID getTokenId() {
      return Token.ID.Tag;
   }
}
