package org.datanucleus.plugin;

import java.util.HashMap;
import java.util.Map;

public class ConfigurationElement {
   private final ConfigurationElement parent;
   private ConfigurationElement[] children;
   private final Map attributes = new HashMap();
   private String[] attributeNames;
   private String name;
   private String text;
   private Extension extension;

   public ConfigurationElement(Extension extension, String name, ConfigurationElement parent) {
      this.extension = extension;
      this.name = name;
      this.parent = parent;
      this.attributeNames = new String[0];
      this.children = new ConfigurationElement[0];
   }

   public String getName() {
      return this.name;
   }

   public ConfigurationElement getParent() {
      return this.parent;
   }

   public ConfigurationElement[] getChildren() {
      return this.children;
   }

   public String getAttribute(String name) {
      return (String)this.attributes.get(name);
   }

   public void putAttribute(String name, String value) {
      String[] names = new String[this.attributeNames.length + 1];
      System.arraycopy(this.attributeNames, 0, names, 0, this.attributeNames.length);
      names[this.attributeNames.length] = name;
      this.attributeNames = names;
      this.attributes.put(name, value);
   }

   public void addConfigurationElement(ConfigurationElement confElm) {
      ConfigurationElement[] elm = new ConfigurationElement[this.children.length + 1];
      System.arraycopy(this.children, 0, elm, 0, this.children.length);
      elm[this.children.length] = confElm;
      this.children = elm;
   }

   public String[] getAttributeNames() {
      return this.attributeNames;
   }

   public void setText(String text) {
      this.text = text;
   }

   public String getText() {
      return this.text;
   }

   public Extension getExtension() {
      return this.extension;
   }

   public String toString() {
      return this.name + " " + this.attributes;
   }
}
