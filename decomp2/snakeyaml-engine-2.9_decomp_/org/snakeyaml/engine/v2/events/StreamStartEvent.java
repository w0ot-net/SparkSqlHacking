package org.snakeyaml.engine.v2.events;

import java.util.Optional;
import org.snakeyaml.engine.v2.exceptions.Mark;

public final class StreamStartEvent extends Event {
   public StreamStartEvent(Optional startMark, Optional endMark) {
      super(startMark, endMark);
   }

   public StreamStartEvent() {
   }

   public Event.ID getEventId() {
      return Event.ID.StreamStart;
   }

   public String toString() {
      return "+STR";
   }
}
