package org.apache.logging.log4j.core.appender;

import java.io.OutputStream;
import java.io.Serializable;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.core.util.CloseShieldOutputStream;
import org.apache.logging.log4j.core.util.NullOutputStream;

@Plugin(
   name = "OutputStream",
   category = "Core",
   elementType = "appender",
   printObject = true
)
public final class OutputStreamAppender extends AbstractOutputStreamAppender {
   private static OutputStreamManagerFactory factory = new OutputStreamManagerFactory();

   @PluginFactory
   public static OutputStreamAppender createAppender(Layout layout, final Filter filter, final OutputStream target, final String name, final boolean follow, final boolean ignore) {
      if (name == null) {
         LOGGER.error("No name provided for OutputStreamAppender");
         return null;
      } else {
         if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
         }

         return new OutputStreamAppender(name, layout, filter, getManager(target, follow, layout), ignore, (Property[])null);
      }
   }

   private static OutputStreamManager getManager(final OutputStream target, final boolean follow, final Layout layout) {
      OutputStream os = (OutputStream)(target == null ? NullOutputStream.getInstance() : new CloseShieldOutputStream(target));
      OutputStream targetRef = target == null ? os : target;
      String managerName = targetRef.getClass().getName() + "@" + Integer.toHexString(targetRef.hashCode()) + '.' + follow;
      return OutputStreamManager.getManager(managerName, new FactoryData(os, managerName, layout), factory);
   }

   @PluginBuilderFactory
   public static Builder newBuilder() {
      return (Builder)(new Builder()).asBuilder();
   }

   private OutputStreamAppender(final String name, final Layout layout, final Filter filter, final OutputStreamManager manager, final boolean ignoreExceptions, final Property[] properties) {
      super(name, layout, filter, ignoreExceptions, true, properties, manager);
   }

   public static class Builder extends AbstractOutputStreamAppender.Builder implements org.apache.logging.log4j.core.util.Builder {
      private boolean follow = false;
      private final boolean ignoreExceptions = true;
      private OutputStream target;

      public OutputStreamAppender build() {
         Layout<? extends Serializable> layout = this.getOrCreateLayout();
         return new OutputStreamAppender(this.getName(), layout, this.getFilter(), OutputStreamAppender.getManager(this.target, this.follow, layout), true, this.getPropertyArray());
      }

      public Builder setFollow(final boolean shouldFollow) {
         this.follow = shouldFollow;
         return (Builder)this.asBuilder();
      }

      public Builder setTarget(final OutputStream aTarget) {
         this.target = aTarget;
         return (Builder)this.asBuilder();
      }
   }

   private static class FactoryData {
      private final Layout layout;
      private final String name;
      private final OutputStream os;

      public FactoryData(final OutputStream os, final String type, final Layout layout) {
         this.os = os;
         this.name = type;
         this.layout = layout;
      }
   }

   private static class OutputStreamManagerFactory implements ManagerFactory {
      private OutputStreamManagerFactory() {
      }

      public OutputStreamManager createManager(final String name, final FactoryData data) {
         return new OutputStreamManager(data.os, data.name, data.layout, true);
      }
   }
}
