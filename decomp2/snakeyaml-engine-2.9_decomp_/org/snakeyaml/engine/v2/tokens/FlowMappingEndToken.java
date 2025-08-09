package org.snakeyaml.engine.v2.tokens;

import java.util.Optional;
import org.snakeyaml.engine.v2.exceptions.Mark;

public final class FlowMappingEndToken extends Token {
   public FlowMappingEndToken(Optional startMark, Optional endMark) {
      super(startMark, endMark);
   }

   public Token.ID getTokenId() {
      return Token.ID.FlowMappingEnd;
   }
}
