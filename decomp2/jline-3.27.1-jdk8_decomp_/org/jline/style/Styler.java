package org.jline.style;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Styler {
   private static final Logger log = Logger.getLogger(Styler.class.getName());
   private static volatile StyleSource source = new NopStyleSource();

   private Styler() {
   }

   public static StyleSource getSource() {
      return source;
   }

   public static void setSource(StyleSource source) {
      Styler.source = (StyleSource)Objects.requireNonNull(source);
      if (log.isLoggable(Level.FINE)) {
         log.fine("Source: " + source);
      }

   }

   public static StyleResolver resolver(String group) {
      return new StyleResolver(source, group);
   }

   public static StyleFactory factory(String group) {
      return new StyleFactory(resolver(group));
   }

   public static StyleBundle bundle(Class type) {
      return StyleBundleInvocationHandler.create(source, type);
   }

   public static StyleBundle bundle(String group, Class type) {
      return StyleBundleInvocationHandler.create(resolver(group), type);
   }
}
