package org.apache.log4j.builders.rolling;

import java.util.Properties;
import org.apache.log4j.builders.AbstractBuilder;
import org.apache.log4j.config.PropertiesConfiguration;
import org.apache.log4j.xml.XmlConfiguration;
import org.apache.logging.log4j.core.appender.rolling.TimeBasedTriggeringPolicy;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.w3c.dom.Element;

@Plugin(
   name = "org.apache.log4j.rolling.TimeBasedRollingPolicy",
   category = "Log4j Builder"
)
public class TimeBasedRollingPolicyBuilder extends AbstractBuilder implements TriggeringPolicyBuilder {
   public TimeBasedRollingPolicyBuilder(final String prefix, final Properties props) {
      super(prefix, props);
   }

   public TimeBasedRollingPolicyBuilder() {
   }

   public TimeBasedTriggeringPolicy parse(final Element element, final XmlConfiguration configuration) {
      return this.createTriggeringPolicy();
   }

   public TimeBasedTriggeringPolicy parse(final PropertiesConfiguration configuration) {
      return this.createTriggeringPolicy();
   }

   private TimeBasedTriggeringPolicy createTriggeringPolicy() {
      return TimeBasedTriggeringPolicy.newBuilder().build();
   }
}
