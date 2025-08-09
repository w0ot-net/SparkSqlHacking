package org.sparkproject.jetty.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Objects;
import org.sparkproject.jetty.util.Decorator;

public class DecoratingListener implements ServletContextAttributeListener {
   private static final MethodType DECORATE_TYPE;
   private static final MethodType DESTROY_TYPE;
   private final ServletContextHandler _context;
   private final String _attributeName;
   private Decorator _decorator;

   public DecoratingListener(ServletContextHandler context, String attributeName) {
      Objects.requireNonNull(context);
      Objects.requireNonNull(attributeName);
      this._context = context;
      this._attributeName = attributeName;
      Object decorator = this._context.getAttribute(this._attributeName);
      if (decorator != null) {
         this._context.getObjectFactory().addDecorator(this.asDecorator(decorator));
      }

   }

   public String getAttributeName() {
      return this._attributeName;
   }

   public ServletContext getServletContext() {
      return this._context.getServletContext();
   }

   private Decorator asDecorator(Object object) {
      if (object == null) {
         return null;
      } else if (object instanceof Decorator) {
         return (Decorator)object;
      } else {
         try {
            Class<?> clazz = object.getClass();
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            MethodHandle decorate = lookup.findVirtual(clazz, "decorate", DECORATE_TYPE);
            MethodHandle destroy = lookup.findVirtual(clazz, "destroy", DESTROY_TYPE);
            return new DynamicDecorator(object, decorate, destroy);
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }
   }

   public void attributeAdded(ServletContextAttributeEvent event) {
      if (this._attributeName.equals(event.getName())) {
         this._decorator = this.asDecorator(event.getValue());
         this._context.getObjectFactory().addDecorator(this._decorator);
      }

   }

   public void attributeRemoved(ServletContextAttributeEvent event) {
      if (this._attributeName.equals(event.getName()) && this._decorator != null) {
         this._context.getObjectFactory().removeDecorator(this._decorator);
         this._decorator = null;
      }

   }

   public void attributeReplaced(ServletContextAttributeEvent event) {
      this.attributeRemoved(event);
      this.attributeAdded(event);
   }

   static {
      try {
         DECORATE_TYPE = MethodType.methodType(Object.class, Object.class);
         DESTROY_TYPE = MethodType.methodType(Void.TYPE, Object.class);
         MethodHandles.Lookup lookup = MethodHandles.lookup();
         lookup.findVirtual(Decorator.class, "decorate", DECORATE_TYPE);
         lookup.findVirtual(Decorator.class, "destroy", DESTROY_TYPE);
      } catch (Exception e) {
         throw new IllegalStateException(e);
      }
   }

   private static class DynamicDecorator implements Decorator {
      private final Object _object;
      private final MethodHandle _decorate;
      private final MethodHandle _destroy;

      private DynamicDecorator(Object object, MethodHandle decorate, MethodHandle destroy) {
         this._object = object;
         this._decorate = decorate;
         this._destroy = destroy;
      }

      public Object decorate(Object o) {
         try {
            return this._decorate.invoke(this._object, o);
         } catch (Throwable t) {
            throw new RuntimeException(t);
         }
      }

      public void destroy(Object o) {
         try {
            this._destroy.invoke(this._object, o);
         } catch (Throwable t) {
            throw new RuntimeException(t);
         }
      }
   }
}
