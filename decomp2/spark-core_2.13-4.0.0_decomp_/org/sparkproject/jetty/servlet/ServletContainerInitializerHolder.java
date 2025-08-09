package org.sparkproject.jetty.servlet;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.server.handler.ContextHandler;
import org.sparkproject.jetty.util.Loader;
import org.sparkproject.jetty.util.NanoTime;
import org.sparkproject.jetty.util.StringUtil;

public class ServletContainerInitializerHolder extends BaseHolder {
   private static final Logger LOG = LoggerFactory.getLogger(ServletContainerInitializerHolder.class);
   protected Set _startupClassNames;
   protected Set _startupClasses;
   public static final Pattern __pattern = Pattern.compile("ContainerInitializer\\{([^,]*),interested=(\\[[^\\]]*\\])(,applicable=(\\[[^\\]]*\\]))?(,annotated=(\\[[^\\]]*\\]))?\\}");

   protected ServletContainerInitializerHolder(Source source) {
      super(source);
      this._startupClassNames = new HashSet();
      this._startupClasses = new HashSet();
   }

   public ServletContainerInitializerHolder() {
      this(Source.EMBEDDED);
   }

   public ServletContainerInitializerHolder(Class sciClass) {
      super(Source.EMBEDDED);
      this._startupClassNames = new HashSet();
      this._startupClasses = new HashSet();
      this.setHeldClass(sciClass);
   }

   public ServletContainerInitializerHolder(Class sciClass, Class... startupClasses) {
      super(Source.EMBEDDED);
      this._startupClassNames = new HashSet();
      this._startupClasses = new HashSet();
      this.setHeldClass(sciClass);
      this._startupClasses.addAll(Arrays.asList(startupClasses));
   }

   public ServletContainerInitializerHolder(ServletContainerInitializer sci, Class... startupClasses) {
      this(Source.EMBEDDED, sci, startupClasses);
   }

   public ServletContainerInitializerHolder(Source source, ServletContainerInitializer sci, Class... startupClasses) {
      super(source);
      this._startupClassNames = new HashSet();
      this._startupClasses = new HashSet();
      this.setInstance(sci);
      if (startupClasses != null) {
         this._startupClasses.addAll(Arrays.asList(startupClasses));
      }

   }

   public void addStartupClasses(String... names) {
      Collections.addAll(this._startupClassNames, names);
   }

   public void addStartupClasses(Class... clazzes) {
      Collections.addAll(this._startupClasses, clazzes);
   }

   protected Set resolveStartupClasses() throws Exception {
      Set<Class<?>> classes = new HashSet();

      for(String name : this._startupClassNames) {
         classes.add(Loader.loadClass(name));
      }

      return classes;
   }

   public void doStart() throws Exception {
      Set<Class<?>> classes = new HashSet(this._startupClasses);
      super.doStart();
      classes.addAll(this.resolveStartupClasses());
      ContextHandler.Context ctx = null;
      if (this.getServletHandler() != null) {
         ctx = this.getServletHandler().getServletContextHandler().getServletContext();
      }

      if (ctx == null && ContextHandler.getCurrentContext() != null) {
         ctx = ContextHandler.getCurrentContext();
      }

      if (ctx == null) {
         throw new IllegalStateException("No Context");
      } else {
         ServletContainerInitializer initializer = (ServletContainerInitializer)this.getInstance();
         if (initializer == null) {
            initializer = (ServletContainerInitializer)this.createInstance();
            initializer = (ServletContainerInitializer)this.wrap(initializer, WrapFunction.class, WrapFunction::wrapServletContainerInitializer);
         }

         try {
            ctx.setExtendedListenerTypes(true);
            if (LOG.isDebugEnabled()) {
               long start = NanoTime.now();
               initializer.onStartup(classes, ctx);
               LOG.debug("ServletContainerInitializer {} called in {}ms", this.getClassName(), NanoTime.millisSince(start));
            } else {
               initializer.onStartup(classes, ctx);
            }
         } finally {
            ctx.setExtendedListenerTypes(false);
         }

      }
   }

   public static ServletContainerInitializerHolder fromString(ClassLoader loader, String string) {
      Matcher m = __pattern.matcher(string);
      if (!m.matches()) {
         throw new IllegalArgumentException(string);
      } else {
         try {
            String sciClassname = m.group(1);
            ServletContainerInitializer sci = (ServletContainerInitializer)loader.loadClass(sciClassname).getDeclaredConstructor().newInstance();
            ServletContainerInitializerHolder holder = new ServletContainerInitializerHolder(new Source(Source.Origin.ANNOTATION, sciClassname));
            holder.setInstance(sci);
            Set<Class<?>> classes = new HashSet();
            String[] classnames = StringUtil.arrayFromString(m.group(2));

            for(String name : classnames) {
               classes.add(loader.loadClass(name));
            }

            classnames = StringUtil.arrayFromString(m.group(4));

            for(String name : classnames) {
               classes.add(loader.loadClass(name));
            }

            classnames = StringUtil.arrayFromString(m.group(6));

            for(String name : classnames) {
               classes.add(loader.loadClass(name));
            }

            holder.addStartupClasses((Class[])classes.toArray(new Class[0]));
            return holder;
         } catch (Exception e) {
            throw new IllegalArgumentException(string, e);
         }
      }
   }

   public String toString() {
      Set<String> interested = new HashSet(this._startupClassNames);
      this._startupClasses.forEach((c) -> interested.add(c.getName()));
      return String.format("ContainerInitializer{%s,interested=%s,applicable=%s,annotated=%s}", this.getClassName(), interested, Collections.emptySet(), Collections.emptySet());
   }

   public static class Wrapper implements ServletContainerInitializer, BaseHolder.Wrapped {
      private final ServletContainerInitializer _wrappedSCI;

      public Wrapper(ServletContainerInitializer sci) {
         this._wrappedSCI = (ServletContainerInitializer)Objects.requireNonNull(sci, "ServletContainerInitializer cannot be null");
      }

      public ServletContainerInitializer getWrapped() {
         return this._wrappedSCI;
      }

      public void onStartup(Set c, ServletContext ctx) throws ServletException {
         this._wrappedSCI.onStartup(c, ctx);
      }

      public String toString() {
         return String.format("%s:%s", this.getClass().getSimpleName(), this._wrappedSCI.toString());
      }
   }

   public interface WrapFunction {
      ServletContainerInitializer wrapServletContainerInitializer(ServletContainerInitializer var1);
   }
}
