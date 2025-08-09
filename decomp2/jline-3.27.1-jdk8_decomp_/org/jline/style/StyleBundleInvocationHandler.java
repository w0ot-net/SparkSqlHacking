package org.jline.style;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;

class StyleBundleInvocationHandler implements InvocationHandler {
   private static final Logger log = Logger.getLogger(StyleBundleInvocationHandler.class.getName());
   private final Class type;
   private final StyleResolver resolver;

   public StyleBundleInvocationHandler(Class type, StyleResolver resolver) {
      this.type = (Class)Objects.requireNonNull(type);
      this.resolver = (StyleResolver)Objects.requireNonNull(resolver);
   }

   private static void validate(Method method) {
      if (method.getParameterCount() != 1) {
         throw new InvalidStyleBundleMethodException(method, "Invalid parameters");
      } else if (method.getReturnType() != AttributedString.class) {
         throw new InvalidStyleBundleMethodException(method, "Invalid return-type");
      }
   }

   @Nullable
   private static String emptyToNull(@Nullable String value) {
      return value != null && !value.isEmpty() ? value : null;
   }

   @Nullable
   private static String getStyleGroup(Class type) {
      StyleBundle.StyleGroup styleGroup = (StyleBundle.StyleGroup)type.getAnnotation(StyleBundle.StyleGroup.class);
      return styleGroup != null ? emptyToNull(styleGroup.value().trim()) : null;
   }

   private static String getStyleName(Method method) {
      StyleBundle.StyleName styleName = (StyleBundle.StyleName)method.getAnnotation(StyleBundle.StyleName.class);
      return styleName != null ? emptyToNull(styleName.value().trim()) : method.getName();
   }

   @Nullable
   private static String getDefaultStyle(Method method) {
      StyleBundle.DefaultStyle defaultStyle = (StyleBundle.DefaultStyle)method.getAnnotation(StyleBundle.DefaultStyle.class);
      return defaultStyle != null ? emptyToNull(defaultStyle.value()) : null;
   }

   static StyleBundle create(StyleResolver resolver, Class type) {
      Objects.requireNonNull(resolver);
      Objects.requireNonNull(type);
      if (log.isLoggable(Level.FINEST)) {
         log.finest(String.format("Using style-group: %s for type: %s", resolver.getGroup(), type.getName()));
      }

      StyleBundleInvocationHandler handler = new StyleBundleInvocationHandler(type, resolver);
      return (StyleBundle)Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, handler);
   }

   static StyleBundle create(StyleSource source, Class type) {
      Objects.requireNonNull(type);
      String group = getStyleGroup(type);
      if (group == null) {
         throw new InvalidStyleGroupException(type);
      } else {
         return create(new StyleResolver(source, group), type);
      }
   }

   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      if (method.getDeclaringClass() == Object.class) {
         return method.invoke(this, args);
      } else {
         validate(method);
         String styleName = getStyleName(method);
         String style = this.resolver.getSource().get(this.resolver.getGroup(), styleName);
         if (log.isLoggable(Level.FINEST)) {
            log.finest(String.format("Sourced-style: %s -> %s", styleName, style));
         }

         if (style == null) {
            style = getDefaultStyle(method);
            if (style == null) {
               throw new StyleBundleMethodMissingDefaultStyleException(method);
            }
         }

         String value = String.valueOf(args[0]);
         if (log.isLoggable(Level.FINEST)) {
            log.finest(String.format("Applying style: %s -> %s to: %s", styleName, style, value));
         }

         AttributedStyle astyle = this.resolver.resolve(style);
         return new AttributedString(value, astyle);
      }
   }

   public String toString() {
      return this.type.getName();
   }

   static class StyleBundleMethodMissingDefaultStyleException extends RuntimeException {
      private static final long serialVersionUID = 1L;

      public StyleBundleMethodMissingDefaultStyleException(Method method) {
         super(String.format("%s method missing @%s: %s", StyleBundle.class.getSimpleName(), StyleBundle.DefaultStyle.class.getSimpleName(), method));
      }
   }

   static class InvalidStyleBundleMethodException extends RuntimeException {
      private static final long serialVersionUID = 1L;

      public InvalidStyleBundleMethodException(Method method, String message) {
         super(message + ": " + method);
      }
   }

   static class InvalidStyleGroupException extends RuntimeException {
      private static final long serialVersionUID = 1L;

      public InvalidStyleGroupException(Class type) {
         super(String.format("%s missing or invalid @%s: %s", StyleBundle.class.getSimpleName(), StyleBundle.StyleGroup.class.getSimpleName(), type.getName()));
      }
   }
}
