package org.snakeyaml.engine.v2.events;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.snakeyaml.engine.v2.common.SpecVersion;
import org.snakeyaml.engine.v2.exceptions.Mark;

public final class DocumentStartEvent extends Event {
   private final boolean explicit;
   private final Optional specVersion;
   private final Map tags;

   public DocumentStartEvent(boolean explicit, Optional specVersion, Map tags, Optional startMark, Optional endMark) {
      super(startMark, endMark);
      this.explicit = explicit;
      Objects.requireNonNull(specVersion);
      this.specVersion = specVersion;
      Objects.requireNonNull(tags);
      this.tags = tags;
   }

   public DocumentStartEvent(boolean explicit, Optional specVersion, Map tags) {
      this(explicit, specVersion, tags, Optional.empty(), Optional.empty());
   }

   public boolean isExplicit() {
      return this.explicit;
   }

   public Optional getSpecVersion() {
      return this.specVersion;
   }

   public Map getTags() {
      return this.tags;
   }

   public Event.ID getEventId() {
      return Event.ID.DocumentStart;
   }

   public String toString() {
      StringBuilder builder = new StringBuilder("+DOC");
      if (this.isExplicit()) {
         builder.append(" ---");
      }

      return builder.toString();
   }
}
