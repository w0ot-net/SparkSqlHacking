package org.apache.logging.log4j.core.appender;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.core.util.Booleans;
import org.apache.logging.log4j.core.util.CloseShieldOutputStream;
import org.apache.logging.log4j.core.util.Loader;
import org.apache.logging.log4j.core.util.Throwables;
import org.apache.logging.log4j.util.PropertiesUtil;

@Plugin(
   name = "Console",
   category = "Core",
   elementType = "appender",
   printObject = true
)
public final class ConsoleAppender extends AbstractOutputStreamAppender {
   public static final String PLUGIN_NAME = "Console";
   private static final String JANSI_CLASS = "org.fusesource.jansi.WindowsAnsiOutputStream";
   private static ConsoleManagerFactory factory = new ConsoleManagerFactory();
   private static final Target DEFAULT_TARGET;
   private static final AtomicInteger COUNT;
   private final Target target;

   private ConsoleAppender(final String name, final Layout layout, final Filter filter, final OutputStreamManager manager, final boolean ignoreExceptions, final Target target, final Property[] properties) {
      super(name, layout, filter, ignoreExceptions, true, properties, manager);
      this.target = target;
   }

   /** @deprecated */
   @Deprecated
   public static ConsoleAppender createAppender(Layout layout, final Filter filter, final String targetStr, final String name, final String follow, final String ignore) {
      if (name == null) {
         LOGGER.error("No name provided for ConsoleAppender");
         return null;
      } else {
         if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
         }

         boolean isFollow = Boolean.parseBoolean(follow);
         boolean ignoreExceptions = Booleans.parseBoolean(ignore, true);
         Target target = targetStr == null ? DEFAULT_TARGET : ConsoleAppender.Target.valueOf(targetStr);
         return new ConsoleAppender(name, layout, filter, getManager(target, isFollow, false, layout), ignoreExceptions, target, (Property[])null);
      }
   }

   /** @deprecated */
   @Deprecated
   public static ConsoleAppender createAppender(Layout layout, final Filter filter, Target target, final String name, final boolean follow, final boolean direct, final boolean ignoreExceptions) {
      if (name == null) {
         LOGGER.error("No name provided for ConsoleAppender");
         return null;
      } else {
         if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
         }

         target = target == null ? ConsoleAppender.Target.SYSTEM_OUT : target;
         if (follow && direct) {
            LOGGER.error("Cannot use both follow and direct on ConsoleAppender");
            return null;
         } else {
            return new ConsoleAppender(name, layout, filter, getManager(target, follow, direct, layout), ignoreExceptions, target, (Property[])null);
         }
      }
   }

   public static ConsoleAppender createDefaultAppenderForLayout(final Layout layout) {
      return new ConsoleAppender("DefaultConsole-" + COUNT.incrementAndGet(), layout, (Filter)null, getDefaultManager(DEFAULT_TARGET, false, false, layout), true, DEFAULT_TARGET, (Property[])null);
   }

   @PluginBuilderFactory
   public static Builder newBuilder() {
      return (Builder)(new Builder()).asBuilder();
   }

   private static OutputStreamManager getDefaultManager(final Target target, final boolean follow, final boolean direct, final Layout layout) {
      OutputStream os = getOutputStream(follow, direct, target);
      String managerName = target.name() + '.' + follow + '.' + direct + "-" + COUNT.get();
      return OutputStreamManager.getManager(managerName, new FactoryData(os, managerName, layout), factory);
   }

   private static OutputStreamManager getManager(final Target target, final boolean follow, final boolean direct, final Layout layout) {
      OutputStream os = getOutputStream(follow, direct, target);
      String managerName = target.name() + '.' + follow + '.' + direct;
      return OutputStreamManager.getManager(managerName, new FactoryData(os, managerName, layout), factory);
   }

   private static OutputStream getOutputStream(final boolean follow, final boolean direct, final Target target) {
      String enc = Charset.defaultCharset().name();

      CloseShieldOutputStream outputStream;
      try {
         OutputStream outputStream = (OutputStream)(target == ConsoleAppender.Target.SYSTEM_OUT ? (direct ? new FileOutputStream(FileDescriptor.out) : (follow ? new PrintStream(new SystemOutStream(), true, enc) : System.out)) : (direct ? new FileOutputStream(FileDescriptor.err) : (follow ? new PrintStream(new SystemErrStream(), true, enc) : System.err)));
         outputStream = new CloseShieldOutputStream(outputStream);
      } catch (UnsupportedEncodingException ex) {
         throw new IllegalStateException("Unsupported default encoding " + enc, ex);
      }

      PropertiesUtil propsUtil = PropertiesUtil.getProperties();
      if (propsUtil.isOsWindows() && !propsUtil.getBooleanProperty("log4j.skipJansi", true) && !direct) {
         try {
            Class<?> clazz = Loader.loadClass("org.fusesource.jansi.WindowsAnsiOutputStream");
            Constructor<?> constructor = clazz.getConstructor(OutputStream.class);
            return new CloseShieldOutputStream((OutputStream)constructor.newInstance(outputStream));
         } catch (ClassNotFoundException var8) {
            LOGGER.debug("Jansi is not installed, cannot find {}", "org.fusesource.jansi.WindowsAnsiOutputStream");
         } catch (NoSuchMethodException var9) {
            LOGGER.warn("{} is missing the proper constructor", "org.fusesource.jansi.WindowsAnsiOutputStream");
         } catch (Exception ex) {
            LOGGER.warn("Unable to instantiate {} due to {}", "org.fusesource.jansi.WindowsAnsiOutputStream", clean(Throwables.getRootCause(ex).toString()).trim());
         }

         return outputStream;
      } else {
         return outputStream;
      }
   }

   private static String clean(final String string) {
      return string.replace('\u0000', ' ');
   }

   public Target getTarget() {
      return this.target;
   }

   static {
      DEFAULT_TARGET = ConsoleAppender.Target.SYSTEM_OUT;
      COUNT = new AtomicInteger();
   }

   public static enum Target {
      SYSTEM_OUT {
         public Charset getDefaultCharset() {
            return this.getCharset("sun.stdout.encoding", Charset.defaultCharset());
         }
      },
      SYSTEM_ERR {
         public Charset getDefaultCharset() {
            return this.getCharset("sun.stderr.encoding", Charset.defaultCharset());
         }
      };

      private Target() {
      }

      public abstract Charset getDefaultCharset();

      protected Charset getCharset(final String property, final Charset defaultCharset) {
         return (new PropertiesUtil(PropertiesUtil.getSystemProperties())).getCharsetProperty(property, defaultCharset);
      }

      // $FF: synthetic method
      private static Target[] $values() {
         return new Target[]{SYSTEM_OUT, SYSTEM_ERR};
      }
   }

   public static class Builder extends AbstractOutputStreamAppender.Builder implements org.apache.logging.log4j.core.util.Builder {
      @PluginBuilderAttribute
      @Required
      private Target target;
      @PluginBuilderAttribute
      private boolean follow;
      @PluginBuilderAttribute
      private boolean direct;

      public Builder() {
         this.target = ConsoleAppender.DEFAULT_TARGET;
      }

      public Builder setTarget(final Target aTarget) {
         this.target = aTarget;
         return (Builder)this.asBuilder();
      }

      public Builder setFollow(final boolean shouldFollow) {
         this.follow = shouldFollow;
         return (Builder)this.asBuilder();
      }

      public Builder setDirect(final boolean shouldDirect) {
         this.direct = shouldDirect;
         return (Builder)this.asBuilder();
      }

      public ConsoleAppender build() {
         if (!this.isValid()) {
            return null;
         } else if (this.follow && this.direct) {
            throw new IllegalArgumentException("Cannot use both follow and direct on ConsoleAppender '" + this.getName() + "'");
         } else {
            Layout<? extends Serializable> layout = this.getOrCreateLayout(this.target.getDefaultCharset());
            return new ConsoleAppender(this.getName(), layout, this.getFilter(), ConsoleAppender.getManager(this.target, this.follow, this.direct, layout), this.isIgnoreExceptions(), this.target, this.getPropertyArray());
         }
      }
   }

   private static class SystemErrStream extends OutputStream {
      public SystemErrStream() {
      }

      public void close() {
      }

      public void flush() {
         System.err.flush();
      }

      public void write(final byte[] b) throws IOException {
         System.err.write(b);
      }

      public void write(final byte[] b, final int off, final int len) throws IOException {
         System.err.write(b, off, len);
      }

      public void write(final int b) {
         System.err.write(b);
      }
   }

   private static class SystemOutStream extends OutputStream {
      public SystemOutStream() {
      }

      public void close() {
      }

      public void flush() {
         System.out.flush();
      }

      public void write(final byte[] b) throws IOException {
         System.out.write(b);
      }

      public void write(final byte[] b, final int off, final int len) throws IOException {
         System.out.write(b, off, len);
      }

      public void write(final int b) throws IOException {
         System.out.write(b);
      }
   }

   private static class FactoryData {
      private final OutputStream os;
      private final String name;
      private final Layout layout;

      public FactoryData(final OutputStream os, final String type, final Layout layout) {
         this.os = os;
         this.name = type;
         this.layout = layout;
      }
   }

   private static class ConsoleManagerFactory implements ManagerFactory {
      private ConsoleManagerFactory() {
      }

      public OutputStreamManager createManager(final String name, final FactoryData data) {
         return new OutputStreamManager(data.os, data.name, data.layout, true);
      }
   }
}
