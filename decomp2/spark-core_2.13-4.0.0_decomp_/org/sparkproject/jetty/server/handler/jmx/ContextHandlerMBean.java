package org.sparkproject.jetty.server.handler.jmx;

import java.util.HashMap;
import java.util.Map;
import org.sparkproject.jetty.server.handler.ContextHandler;
import org.sparkproject.jetty.util.Attributes;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.annotation.ManagedOperation;
import org.sparkproject.jetty.util.annotation.Name;

@ManagedObject("ContextHandler mbean wrapper")
public class ContextHandlerMBean extends AbstractHandlerMBean {
   public ContextHandlerMBean(Object managedObject) {
      super(managedObject);
   }

   @ManagedAttribute("Map of context attributes")
   public Map getContextAttributes() {
      Map<String, Object> map = new HashMap();
      Attributes attrs = ((ContextHandler)this._managed).getAttributes();

      for(String name : attrs.getAttributeNameSet()) {
         Object value = attrs.getAttribute(name);
         map.put(name, value);
      }

      return map;
   }

   @ManagedOperation(
      value = "Set context attribute",
      impact = "ACTION"
   )
   public void setContextAttribute(@Name(value = "name",description = "attribute name") String name, @Name(value = "value",description = "attribute value") Object value) {
      Attributes attrs = ((ContextHandler)this._managed).getAttributes();
      attrs.setAttribute(name, value);
   }

   @ManagedOperation(
      value = "Set context attribute",
      impact = "ACTION"
   )
   public void setContextAttribute(@Name(value = "name",description = "attribute name") String name, @Name(value = "value",description = "attribute value") String value) {
      Attributes attrs = ((ContextHandler)this._managed).getAttributes();
      attrs.setAttribute(name, value);
   }

   @ManagedOperation(
      value = "Remove context attribute",
      impact = "ACTION"
   )
   public void removeContextAttribute(@Name(value = "name",description = "attribute name") String name) {
      Attributes attrs = ((ContextHandler)this._managed).getAttributes();
      attrs.removeAttribute(name);
   }
}
