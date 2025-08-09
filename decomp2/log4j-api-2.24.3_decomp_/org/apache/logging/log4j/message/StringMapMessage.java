package org.apache.logging.log4j.message;

import java.util.Map;
import org.apache.logging.log4j.util.PerformanceSensitive;

@AsynchronouslyFormattable
@PerformanceSensitive({"allocation"})
public class StringMapMessage extends MapMessage {
   private static final long serialVersionUID = 1L;

   public StringMapMessage() {
   }

   public StringMapMessage(final int initialCapacity) {
      super(initialCapacity);
   }

   public StringMapMessage(final Map map) {
      super(map);
   }

   public StringMapMessage newInstance(final Map map) {
      return new StringMapMessage(map);
   }
}
