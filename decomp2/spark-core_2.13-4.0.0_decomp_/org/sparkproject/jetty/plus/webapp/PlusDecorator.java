package org.sparkproject.jetty.plus.webapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.plus.annotation.InjectionCollection;
import org.sparkproject.jetty.plus.annotation.LifeCycleCallbackCollection;
import org.sparkproject.jetty.util.Decorator;
import org.sparkproject.jetty.webapp.WebAppContext;

public class PlusDecorator implements Decorator {
   private static final Logger LOG = LoggerFactory.getLogger(PlusDecorator.class);
   protected WebAppContext _context;

   public PlusDecorator(WebAppContext context) {
      this._context = context;
   }

   public Object decorate(Object o) {
      InjectionCollection injections = (InjectionCollection)this._context.getAttribute("org.sparkproject.jetty.injectionCollection");
      if (injections != null) {
         injections.inject(o);
      }

      LifeCycleCallbackCollection callbacks = (LifeCycleCallbackCollection)this._context.getAttribute("org.sparkproject.jetty.lifecyleCallbackCollection");
      if (callbacks != null) {
         try {
            callbacks.callPostConstructCallback(o);
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }

      return o;
   }

   public void destroy(Object o) {
      LifeCycleCallbackCollection callbacks = (LifeCycleCallbackCollection)this._context.getAttribute("org.sparkproject.jetty.lifecyleCallbackCollection");
      if (callbacks != null) {
         try {
            callbacks.callPreDestroyCallback(o);
         } catch (Exception e) {
            LOG.warn("Destroying instance of {}", o.getClass(), e);
         }
      }

   }
}
