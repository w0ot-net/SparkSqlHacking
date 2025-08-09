package org.snakeyaml.engine.v2.tokens;

import java.util.Optional;
import org.snakeyaml.engine.v2.exceptions.Mark;

public final class FlowSequenceStartToken extends Token {
   public FlowSequenceStartToken(Optional startMark, Optional endMark) {
      super(startMark, endMark);
   }

   public Token.ID getTokenId() {
      return Token.ID.FlowSequenceStart;
   }
}
