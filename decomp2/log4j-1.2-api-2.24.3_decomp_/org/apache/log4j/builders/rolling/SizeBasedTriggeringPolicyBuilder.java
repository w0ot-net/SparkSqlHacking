package org.apache.log4j.builders.rolling;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.log4j.builders.AbstractBuilder;
import org.apache.log4j.config.PropertiesConfiguration;
import org.apache.log4j.xml.XmlConfiguration;
import org.apache.logging.log4j.core.appender.rolling.SizeBasedTriggeringPolicy;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.w3c.dom.Element;

@Plugin(
   name = "org.apache.log4j.rolling.SizeBasedTriggeringPolicy",
   category = "Log4j Builder"
)
public class SizeBasedTriggeringPolicyBuilder extends AbstractBuilder implements TriggeringPolicyBuilder {
   private static final String MAX_SIZE_PARAM = "MaxFileSize";
   private static final long DEFAULT_MAX_SIZE = 10485760L;

   public SizeBasedTriggeringPolicyBuilder() {
   }

   public SizeBasedTriggeringPolicyBuilder(final String prefix, final Properties props) {
      super(prefix, props);
   }

   public SizeBasedTriggeringPolicy parse(final Element element, final XmlConfiguration configuration) {
      AtomicLong maxSize = new AtomicLong(10485760L);
      XmlConfiguration.forEachElement(element.getChildNodes(), (currentElement) -> {
         switch (currentElement.getTagName()) {
            case "param":
               switch (this.getNameAttributeKey(currentElement)) {
                  case "MaxFileSize":
                     this.set("MaxFileSize", currentElement, maxSize);
               }
            default:
         }
      });
      return this.createTriggeringPolicy(maxSize.get());
   }

   public SizeBasedTriggeringPolicy parse(final PropertiesConfiguration configuration) {
      long maxSize = this.getLongProperty("MaxFileSize", 10485760L);
      return this.createTriggeringPolicy(maxSize);
   }

   private SizeBasedTriggeringPolicy createTriggeringPolicy(final long maxSize) {
      return SizeBasedTriggeringPolicy.createPolicy(Long.toString(maxSize));
   }
}
