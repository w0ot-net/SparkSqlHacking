package org.snakeyaml.engine.v2.tokens;

import java.util.Objects;
import java.util.Optional;
import org.snakeyaml.engine.v2.common.Anchor;
import org.snakeyaml.engine.v2.exceptions.Mark;

public final class AliasToken extends Token {
   private final Anchor value;

   public AliasToken(Anchor value, Optional startMark, Optional endMark) {
      super(startMark, endMark);
      Objects.requireNonNull(value);
      this.value = value;
   }

   public Anchor getValue() {
      return this.value;
   }

   public Token.ID getTokenId() {
      return Token.ID.Alias;
   }
}
