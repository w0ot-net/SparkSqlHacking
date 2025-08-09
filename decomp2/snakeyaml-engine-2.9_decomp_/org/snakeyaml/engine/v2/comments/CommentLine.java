package org.snakeyaml.engine.v2.comments;

import java.util.Objects;
import java.util.Optional;
import org.snakeyaml.engine.v2.events.CommentEvent;
import org.snakeyaml.engine.v2.exceptions.Mark;

public class CommentLine {
   private final Optional startMark;
   private final Optional endMark;
   private final String value;
   private final CommentType commentType;

   public CommentLine(CommentEvent event) {
      this(event.getStartMark(), event.getEndMark(), event.getValue(), event.getCommentType());
   }

   public CommentLine(Optional startMark, Optional endMark, String value, CommentType commentType) {
      Objects.requireNonNull(startMark);
      this.startMark = startMark;
      Objects.requireNonNull(endMark);
      this.endMark = endMark;
      Objects.requireNonNull(value);
      this.value = value;
      Objects.requireNonNull(commentType);
      this.commentType = commentType;
   }

   public Optional getEndMark() {
      return this.endMark;
   }

   public Optional getStartMark() {
      return this.startMark;
   }

   public CommentType getCommentType() {
      return this.commentType;
   }

   public String getValue() {
      return this.value;
   }

   public String toString() {
      return "<" + this.getClass().getName() + " (type=" + this.getCommentType() + ", value=" + this.getValue() + ")>";
   }
}
