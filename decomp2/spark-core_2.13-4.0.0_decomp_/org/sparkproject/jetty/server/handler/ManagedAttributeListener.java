package org.sparkproject.jetty.server.handler;

import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ManagedAttributeListener implements ServletContextListener, ServletContextAttributeListener {
   private static final Logger LOG = LoggerFactory.getLogger(ManagedAttributeListener.class);
   final Set _managedAttributes = new HashSet();
   final ContextHandler _context;

   public ManagedAttributeListener(ContextHandler context, String... managedAttributes) {
      this._context = context;

      for(String attr : managedAttributes) {
         this._managedAttributes.add(attr);
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("managedAttributes {}", this._managedAttributes);
      }

   }

   public void attributeReplaced(ServletContextAttributeEvent event) {
      if (this._managedAttributes.contains(event.getName())) {
         this.updateBean(event.getName(), event.getValue(), event.getServletContext().getAttribute(event.getName()));
      }

   }

   public void attributeRemoved(ServletContextAttributeEvent event) {
      if (this._managedAttributes.contains(event.getName())) {
         this.updateBean(event.getName(), event.getValue(), (Object)null);
      }

   }

   public void attributeAdded(ServletContextAttributeEvent event) {
      if (this._managedAttributes.contains(event.getName())) {
         this.updateBean(event.getName(), (Object)null, event.getValue());
      }

   }

   public void contextInitialized(ServletContextEvent event) {
      for(String name : this._context.getServletContext().getAttributeNameSet()) {
         if (this._managedAttributes.contains(name)) {
            this.updateBean(name, (Object)null, event.getServletContext().getAttribute(name));
         }
      }

   }

   public void contextDestroyed(ServletContextEvent event) {
      for(String name : this._context.getServletContext().getAttributeNameSet()) {
         if (this._managedAttributes.contains(name)) {
            this.updateBean(name, event.getServletContext().getAttribute(name), (Object)null);
         }
      }

   }

   protected void updateBean(String name, Object oldBean, Object newBean) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("update {} {}->{} on {}", new Object[]{name, oldBean, newBean, this._context});
      }

      this._context.updateBean(oldBean, newBean, false);
   }
}
