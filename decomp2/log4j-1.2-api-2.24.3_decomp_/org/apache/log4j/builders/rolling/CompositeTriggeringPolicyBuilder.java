package org.apache.log4j.builders.rolling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.builders.AbstractBuilder;
import org.apache.log4j.config.PropertiesConfiguration;
import org.apache.log4j.xml.XmlConfiguration;
import org.apache.logging.log4j.core.appender.rolling.CompositeTriggeringPolicy;
import org.apache.logging.log4j.core.appender.rolling.TriggeringPolicy;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.w3c.dom.Element;

@Plugin(
   name = "org.apache.log4j.rolling.CompositeTriggeringPolicy",
   category = "Log4j Builder"
)
public class CompositeTriggeringPolicyBuilder extends AbstractBuilder implements TriggeringPolicyBuilder {
   private static final TriggeringPolicy[] EMPTY_TRIGGERING_POLICIES = new TriggeringPolicy[0];
   private static final String POLICY_TAG = "triggeringPolicy";

   public CompositeTriggeringPolicyBuilder() {
   }

   public CompositeTriggeringPolicyBuilder(final String prefix, final Properties props) {
      super(prefix, props);
   }

   public CompositeTriggeringPolicy parse(final Element element, final XmlConfiguration configuration) {
      List<TriggeringPolicy> policies = new ArrayList();
      XmlConfiguration.forEachElement(element.getChildNodes(), (currentElement) -> {
         switch (currentElement.getTagName()) {
            case "triggeringPolicy":
               TriggeringPolicy policy = configuration.parseTriggeringPolicy(currentElement);
               if (policy != null) {
                  policies.add(policy);
               }
            default:
         }
      });
      return this.createTriggeringPolicy(policies);
   }

   public CompositeTriggeringPolicy parse(final PropertiesConfiguration configuration) {
      return this.createTriggeringPolicy(Collections.emptyList());
   }

   private CompositeTriggeringPolicy createTriggeringPolicy(final List policies) {
      return CompositeTriggeringPolicy.createPolicy((TriggeringPolicy[])policies.toArray(EMPTY_TRIGGERING_POLICIES));
   }
}
