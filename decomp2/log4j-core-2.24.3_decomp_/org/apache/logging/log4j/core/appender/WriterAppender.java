package org.apache.logging.log4j.core.appender;

import java.io.Serializable;
import java.io.Writer;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.StringLayout;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.core.util.CloseShieldWriter;

@Plugin(
   name = "Writer",
   category = "Core",
   elementType = "appender",
   printObject = true
)
public final class WriterAppender extends AbstractWriterAppender {
   private static WriterManagerFactory factory = new WriterManagerFactory();

   @PluginFactory
   public static WriterAppender createAppender(StringLayout layout, final Filter filter, final Writer target, final String name, final boolean follow, final boolean ignore) {
      if (name == null) {
         LOGGER.error("No name provided for WriterAppender");
         return null;
      } else {
         if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
         }

         return new WriterAppender(name, layout, filter, getManager(target, follow, layout), ignore, (Property[])null);
      }
   }

   private static WriterManager getManager(final Writer target, final boolean follow, final StringLayout layout) {
      Writer writer = new CloseShieldWriter(target);
      String managerName = target.getClass().getName() + "@" + Integer.toHexString(target.hashCode()) + '.' + follow;
      return WriterManager.getManager(managerName, new FactoryData(writer, managerName, layout), factory);
   }

   @PluginBuilderFactory
   public static Builder newBuilder() {
      return (Builder)(new Builder()).asBuilder();
   }

   private WriterAppender(final String name, final StringLayout layout, final Filter filter, final WriterManager manager, final boolean ignoreExceptions, final Property[] properties) {
      super(name, layout, filter, ignoreExceptions, true, properties, manager);
   }

   public static class Builder extends AbstractAppender.Builder implements org.apache.logging.log4j.core.util.Builder {
      private boolean follow = false;
      private Writer target;

      public WriterAppender build() {
         Layout<? extends Serializable> layout = this.getOrCreateLayout();
         if (!(layout instanceof StringLayout)) {
            WriterAppender.LOGGER.error("Layout must be a StringLayout to log to ServletContext");
            return null;
         } else {
            StringLayout stringLayout = (StringLayout)layout;
            return new WriterAppender(this.getName(), stringLayout, this.getFilter(), WriterAppender.getManager(this.target, this.follow, stringLayout), this.isIgnoreExceptions(), this.getPropertyArray());
         }
      }

      public Builder setFollow(final boolean shouldFollow) {
         this.follow = shouldFollow;
         return (Builder)this.asBuilder();
      }

      public Builder setTarget(final Writer aTarget) {
         this.target = aTarget;
         return (Builder)this.asBuilder();
      }
   }

   private static class FactoryData {
      private final StringLayout layout;
      private final String name;
      private final Writer writer;

      public FactoryData(final Writer writer, final String type, final StringLayout layout) {
         this.writer = writer;
         this.name = type;
         this.layout = layout;
      }
   }

   private static class WriterManagerFactory implements ManagerFactory {
      private WriterManagerFactory() {
      }

      public WriterManager createManager(final String name, final FactoryData data) {
         return new WriterManager(data.writer, data.name, data.layout, true);
      }
   }
}
