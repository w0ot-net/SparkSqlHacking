package org.snakeyaml.engine.v2.events;

import java.util.Optional;
import org.snakeyaml.engine.v2.exceptions.Mark;

public abstract class CollectionEndEvent extends Event {
   public CollectionEndEvent(Optional startMark, Optional endMark) {
      super(startMark, endMark);
   }

   public CollectionEndEvent() {
   }
}
