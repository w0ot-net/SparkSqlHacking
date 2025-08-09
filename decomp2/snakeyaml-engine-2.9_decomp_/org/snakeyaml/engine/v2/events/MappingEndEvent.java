package org.snakeyaml.engine.v2.events;

import java.util.Optional;
import org.snakeyaml.engine.v2.exceptions.Mark;

public final class MappingEndEvent extends CollectionEndEvent {
   public MappingEndEvent(Optional startMark, Optional endMark) {
      super(startMark, endMark);
   }

   public MappingEndEvent() {
   }

   public Event.ID getEventId() {
      return Event.ID.MappingEnd;
   }

   public String toString() {
      return "-MAP";
   }
}
