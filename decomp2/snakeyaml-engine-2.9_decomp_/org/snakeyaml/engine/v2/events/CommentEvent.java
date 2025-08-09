package org.snakeyaml.engine.v2.events;

import java.util.Objects;
import java.util.Optional;
import org.snakeyaml.engine.v2.comments.CommentType;
import org.snakeyaml.engine.v2.exceptions.Mark;

public final class CommentEvent extends Event {
   private final CommentType type;
   private final String value;

   public CommentEvent(CommentType type, String value, Optional startMark, Optional endMark) {
      super(startMark, endMark);
      Objects.requireNonNull(type);
      this.type = type;
      Objects.requireNonNull(value);
      this.value = value;
   }

   public String getValue() {
      return this.value;
   }

   public CommentType getCommentType() {
      return this.type;
   }

   public Event.ID getEventId() {
      return Event.ID.Comment;
   }

   public String toString() {
      String builder = "=COM " + this.type + " " + this.value;
      return builder;
   }
}
