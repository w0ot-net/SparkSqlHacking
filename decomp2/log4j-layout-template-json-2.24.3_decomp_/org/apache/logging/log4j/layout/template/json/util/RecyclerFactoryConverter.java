package org.apache.logging.log4j.layout.template.json.util;

import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.convert.TypeConverter;

@Plugin(
   name = "RecyclerFactoryConverter",
   category = "TypeConverter"
)
public final class RecyclerFactoryConverter implements TypeConverter {
   public RecyclerFactory convert(final String recyclerFactorySpec) {
      return RecyclerFactories.ofSpec(recyclerFactorySpec);
   }
}
