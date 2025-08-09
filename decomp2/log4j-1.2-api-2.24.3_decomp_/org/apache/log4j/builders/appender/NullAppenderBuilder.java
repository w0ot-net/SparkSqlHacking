package org.apache.log4j.builders.appender;

import java.util.Properties;
import org.apache.log4j.Appender;
import org.apache.log4j.bridge.AppenderWrapper;
import org.apache.log4j.config.PropertiesConfiguration;
import org.apache.log4j.xml.XmlConfiguration;
import org.apache.logging.log4j.core.appender.NullAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.w3c.dom.Element;

@Plugin(
   name = "org.apache.log4j.varia.NullAppender",
   category = "Log4j Builder"
)
public class NullAppenderBuilder implements AppenderBuilder {
   public Appender parseAppender(final Element appenderElement, final XmlConfiguration config) {
      String name = appenderElement.getAttribute("name");
      return AppenderWrapper.adapt(NullAppender.createAppender(name));
   }

   public Appender parseAppender(final String name, final String appenderPrefix, final String layoutPrefix, final String filterPrefix, final Properties props, final PropertiesConfiguration configuration) {
      return AppenderWrapper.adapt(NullAppender.createAppender(name));
   }
}
