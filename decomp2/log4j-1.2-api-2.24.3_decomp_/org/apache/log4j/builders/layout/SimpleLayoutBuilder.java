package org.apache.log4j.builders.layout;

import org.apache.log4j.Layout;
import org.apache.log4j.bridge.LayoutWrapper;
import org.apache.log4j.config.PropertiesConfiguration;
import org.apache.log4j.xml.XmlConfiguration;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.w3c.dom.Element;

@Plugin(
   name = "org.apache.log4j.SimpleLayout",
   category = "Log4j Builder"
)
public class SimpleLayoutBuilder implements LayoutBuilder {
   public Layout parse(final Element layoutElement, final XmlConfiguration config) {
      return new LayoutWrapper(PatternLayout.newBuilder().withPattern("%v1Level - %m%n").withConfiguration(config).build());
   }

   public Layout parse(final PropertiesConfiguration config) {
      return new LayoutWrapper(PatternLayout.newBuilder().withPattern("%v1Level - %m%n").withConfiguration(config).build());
   }
}
