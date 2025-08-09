package org.apache.logging.log4j.core.config.builder.api;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.util.Builder;

public interface ComponentBuilder extends Builder {
   ComponentBuilder addAttribute(String key, String value);

   ComponentBuilder addAttribute(String key, Level level);

   ComponentBuilder addAttribute(String key, Enum value);

   ComponentBuilder addAttribute(String key, int value);

   ComponentBuilder addAttribute(String key, boolean value);

   ComponentBuilder addAttribute(String key, Object value);

   ComponentBuilder addComponent(ComponentBuilder builder);

   String getName();

   ConfigurationBuilder getBuilder();
}
