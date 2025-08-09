package org.snakeyaml.engine.v2.api.lowlevel;

import java.util.Iterator;
import java.util.Objects;
import org.snakeyaml.engine.v2.api.DumpSettings;
import org.snakeyaml.engine.v2.emitter.Emitter;
import org.snakeyaml.engine.v2.events.Event;

public class Present {
   private final DumpSettings settings;

   public Present(DumpSettings settings) {
      Objects.requireNonNull(settings, "DumpSettings cannot be null");
      this.settings = settings;
   }

   public String emitToString(Iterator events) {
      Objects.requireNonNull(events, "events cannot be null");
      StreamToStringWriter writer = new StreamToStringWriter();
      Emitter emitter = new Emitter(this.settings, writer);
      Objects.requireNonNull(emitter);
      events.forEachRemaining(emitter::emit);
      return writer.toString();
   }
}
