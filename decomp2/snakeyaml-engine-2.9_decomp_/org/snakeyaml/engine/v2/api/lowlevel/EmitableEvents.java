package org.snakeyaml.engine.v2.api.lowlevel;

import java.util.ArrayList;
import java.util.List;
import org.snakeyaml.engine.v2.emitter.Emitable;
import org.snakeyaml.engine.v2.events.Event;

class EmitableEvents implements Emitable {
   private final List events = new ArrayList();

   public void emit(Event event) {
      this.events.add(event);
   }

   public List getEvents() {
      return this.events;
   }
}
