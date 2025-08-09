package org.apache.logging.log4j.core.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(
   name = "CustomLevels",
   category = "Core",
   printObject = true
)
public final class CustomLevels {
   private final List customLevels;

   private CustomLevels(final CustomLevelConfig[] customLevels) {
      this.customLevels = new ArrayList(Arrays.asList(customLevels));
   }

   @PluginFactory
   public static CustomLevels createCustomLevels(@PluginElement("CustomLevels") final CustomLevelConfig[] customLevels) {
      return new CustomLevels(customLevels == null ? CustomLevelConfig.EMPTY_ARRAY : customLevels);
   }

   public List getCustomLevels() {
      return this.customLevels;
   }
}
