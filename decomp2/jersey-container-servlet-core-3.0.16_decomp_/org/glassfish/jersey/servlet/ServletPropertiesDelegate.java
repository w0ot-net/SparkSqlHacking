package org.glassfish.jersey.servlet;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;
import org.glassfish.jersey.internal.PropertiesDelegate;

class ServletPropertiesDelegate implements PropertiesDelegate {
   private final HttpServletRequest request;

   public ServletPropertiesDelegate(HttpServletRequest request) {
      this.request = request;
   }

   public Object getProperty(String name) {
      return this.request.getAttribute(name);
   }

   public Collection getPropertyNames() {
      return Collections.list(this.request.getAttributeNames());
   }

   public void setProperty(String name, Object object) {
      this.request.setAttribute(name, object);
   }

   public void removeProperty(String name) {
      this.request.removeAttribute(name);
   }
}
