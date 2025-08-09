package org.snakeyaml.engine.v2.tokens;

import java.util.Objects;
import java.util.Optional;
import org.snakeyaml.engine.v2.comments.CommentType;
import org.snakeyaml.engine.v2.exceptions.Mark;

public final class CommentToken extends Token {
   private final CommentType type;
   private final String value;

   public CommentToken(CommentType type, String value, Optional startMark, Optional endMark) {
      super(startMark, endMark);
      Objects.requireNonNull(type);
      this.type = type;
      Objects.requireNonNull(value);
      this.value = value;
   }

   public CommentType getCommentType() {
      return this.type;
   }

   public String getValue() {
      return this.value;
   }

   public Token.ID getTokenId() {
      return Token.ID.Comment;
   }
}
