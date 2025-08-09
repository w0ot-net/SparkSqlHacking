package org.snakeyaml.engine.v2.events;

import java.util.Optional;
import org.snakeyaml.engine.v2.exceptions.Mark;

public final class SequenceEndEvent extends CollectionEndEvent {
   public SequenceEndEvent(Optional startMark, Optional endMark) {
      super(startMark, endMark);
   }

   public SequenceEndEvent() {
   }

   public Event.ID getEventId() {
      return Event.ID.SequenceEnd;
   }

   public String toString() {
      return "-SEQ";
   }
}
