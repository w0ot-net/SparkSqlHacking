package org.apache.logging.log4j.core.appender;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(
   name = "CountingNoOp",
   category = "Core",
   elementType = "appender",
   printObject = true
)
public class CountingNoOpAppender extends AbstractAppender {
   private final AtomicLong total = new AtomicLong();

   public CountingNoOpAppender(final String name, final Layout layout) {
      super(name, (Filter)null, layout, true, Property.EMPTY_ARRAY);
   }

   private CountingNoOpAppender(final String name, final Layout layout, final Property[] properties) {
      super(name, (Filter)null, layout, true, properties);
   }

   public long getCount() {
      return this.total.get();
   }

   public void append(final LogEvent event) {
      this.total.incrementAndGet();
   }

   @PluginFactory
   public static CountingNoOpAppender createAppender(@PluginAttribute("name") final String name) {
      return new CountingNoOpAppender((String)Objects.requireNonNull(name), (Layout)null, Property.EMPTY_ARRAY);
   }
}
