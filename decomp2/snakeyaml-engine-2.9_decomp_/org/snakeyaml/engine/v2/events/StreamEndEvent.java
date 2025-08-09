package org.snakeyaml.engine.v2.events;

import java.util.Optional;
import org.snakeyaml.engine.v2.exceptions.Mark;

public final class StreamEndEvent extends Event {
   public StreamEndEvent(Optional startMark, Optional endMark) {
      super(startMark, endMark);
   }

   public StreamEndEvent() {
   }

   public Event.ID getEventId() {
      return Event.ID.StreamEnd;
   }

   public String toString() {
      return "-STR";
   }
}
