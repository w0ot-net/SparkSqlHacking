package org.sparkproject.jetty.server;

import java.util.Set;
import org.sparkproject.jetty.util.Attributes;
import org.sparkproject.jetty.util.AttributesMap;

public class ServletAttributes implements Attributes {
   private final Attributes _attributes = new AttributesMap();
   private AsyncAttributes _asyncAttributes;

   public void setAsyncAttributes(String requestURI, String contextPath, String pathInContext, ServletPathMapping servletPathMapping, String queryString) {
      this._asyncAttributes = new AsyncAttributes(this._attributes, requestURI, contextPath, pathInContext, servletPathMapping, queryString);
   }

   private Attributes getAttributes() {
      return (Attributes)(this._asyncAttributes == null ? this._attributes : this._asyncAttributes);
   }

   public void removeAttribute(String name) {
      this.getAttributes().removeAttribute(name);
   }

   public void setAttribute(String name, Object attribute) {
      this.getAttributes().setAttribute(name, attribute);
   }

   public Object getAttribute(String name) {
      return this.getAttributes().getAttribute(name);
   }

   public Set getAttributeNameSet() {
      return this.getAttributes().getAttributeNameSet();
   }

   public void clearAttributes() {
      this.getAttributes().clearAttributes();
      this._asyncAttributes = null;
   }
}
