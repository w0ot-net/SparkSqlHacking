package org.apache.log4j.bridge;

import org.apache.log4j.spi.LoggingEvent;
import org.apache.logging.log4j.core.Layout;

public class LayoutWrapper extends org.apache.log4j.Layout {
   private final Layout layout;

   public static org.apache.log4j.Layout adapt(final Layout layout) {
      if (layout instanceof LayoutAdapter) {
         return ((LayoutAdapter)layout).getLayout();
      } else {
         return layout != null ? new LayoutWrapper(layout) : null;
      }
   }

   public LayoutWrapper(final Layout layout) {
      this.layout = layout;
   }

   public String format(final LoggingEvent event) {
      return this.layout.toSerializable(((LogEventAdapter)event).getEvent()).toString();
   }

   public Layout getLayout() {
      return this.layout;
   }

   public boolean ignoresThrowable() {
      return false;
   }

   public String toString() {
      return String.format("LayoutWrapper [layout=%s]", this.layout);
   }
}
