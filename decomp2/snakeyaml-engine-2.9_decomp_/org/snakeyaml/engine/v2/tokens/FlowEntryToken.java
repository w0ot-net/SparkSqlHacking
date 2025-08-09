package org.snakeyaml.engine.v2.tokens;

import java.util.Optional;
import org.snakeyaml.engine.v2.exceptions.Mark;

public final class FlowEntryToken extends Token {
   public FlowEntryToken(Optional startMark, Optional endMark) {
      super(startMark, endMark);
   }

   public Token.ID getTokenId() {
      return Token.ID.FlowEntry;
   }
}
