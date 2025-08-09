package org.apache.logging.log4j.core.config.builder.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.builder.api.Component;
import org.apache.logging.log4j.core.config.builder.api.ComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;

class DefaultComponentBuilder implements ComponentBuilder {
   private final ConfigurationBuilder builder;
   private final String type;
   private final Map attributes;
   private final List components;
   private final String name;
   private final String value;

   public DefaultComponentBuilder(final ConfigurationBuilder builder, final String type) {
      this(builder, (String)null, type, (String)null);
   }

   public DefaultComponentBuilder(final ConfigurationBuilder builder, final String name, final String type) {
      this(builder, name, type, (String)null);
   }

   public DefaultComponentBuilder(final ConfigurationBuilder builder, final String name, final String type, final String value) {
      this.attributes = new LinkedHashMap();
      this.components = new ArrayList();
      this.type = type;
      this.builder = builder;
      this.name = name;
      this.value = value;
   }

   public ComponentBuilder addAttribute(final String key, final boolean value) {
      return this.put(key, Boolean.toString(value));
   }

   public ComponentBuilder addAttribute(final String key, final Enum value) {
      return this.put(key, value.name());
   }

   public ComponentBuilder addAttribute(final String key, final int value) {
      return this.put(key, Integer.toString(value));
   }

   public ComponentBuilder addAttribute(final String key, final Level level) {
      return this.put(key, level.toString());
   }

   public ComponentBuilder addAttribute(final String key, final Object value) {
      return this.put(key, value.toString());
   }

   public ComponentBuilder addAttribute(final String key, final String value) {
      return this.put(key, value);
   }

   public ComponentBuilder addComponent(final ComponentBuilder builder) {
      this.components.add((Component)builder.build());
      return this;
   }

   public Component build() {
      Component component = new Component(this.type, this.name, this.value);
      component.getAttributes().putAll(this.attributes);
      component.getComponents().addAll(this.components);
      return component;
   }

   public ConfigurationBuilder getBuilder() {
      return this.builder;
   }

   public String getName() {
      return this.name;
   }

   protected ComponentBuilder put(final String key, final String value) {
      this.attributes.put(key, value);
      return this;
   }
}
