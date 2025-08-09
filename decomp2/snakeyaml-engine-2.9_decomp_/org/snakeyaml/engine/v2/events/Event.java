package org.snakeyaml.engine.v2.events;

import java.util.Optional;
import org.snakeyaml.engine.v2.exceptions.Mark;

public abstract class Event {
   private final Optional startMark;
   private final Optional endMark;

   public Event(Optional startMark, Optional endMark) {
      if ((!startMark.isPresent() || endMark.isPresent()) && (startMark.isPresent() || !endMark.isPresent())) {
         this.startMark = startMark;
         this.endMark = endMark;
      } else {
         throw new NullPointerException("Both marks must be either present or absent.");
      }
   }

   public Event() {
      this(Optional.empty(), Optional.empty());
   }

   public Optional getStartMark() {
      return this.startMark;
   }

   public Optional getEndMark() {
      return this.endMark;
   }

   public abstract ID getEventId();

   public static enum ID {
      Alias,
      Comment,
      DocumentEnd,
      DocumentStart,
      MappingEnd,
      MappingStart,
      Scalar,
      SequenceEnd,
      SequenceStart,
      StreamEnd,
      StreamStart;
   }
}
