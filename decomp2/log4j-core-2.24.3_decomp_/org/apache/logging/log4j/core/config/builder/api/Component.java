package org.apache.logging.log4j.core.config.builder.api;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Component {
   private final Map attributes;
   private final List components;
   private final String pluginType;
   private final String value;

   public Component(final String pluginType) {
      this(pluginType, (String)null, (String)null);
   }

   public Component(final String pluginType, final String name) {
      this(pluginType, name, (String)null);
   }

   public Component(final String pluginType, final String name, final String value) {
      this.attributes = new LinkedHashMap();
      this.components = new ArrayList();
      this.pluginType = pluginType;
      this.value = value;
      if (name != null && name.length() > 0) {
         this.attributes.put("name", name);
      }

   }

   public Component() {
      this.attributes = new LinkedHashMap();
      this.components = new ArrayList();
      this.pluginType = null;
      this.value = null;
   }

   public String addAttribute(final String key, final String newValue) {
      return (String)this.attributes.put(key, newValue);
   }

   public void addComponent(final Component component) {
      this.components.add(component);
   }

   public Map getAttributes() {
      return this.attributes;
   }

   public List getComponents() {
      return this.components;
   }

   public String getPluginType() {
      return this.pluginType;
   }

   public String getValue() {
      return this.value;
   }
}
