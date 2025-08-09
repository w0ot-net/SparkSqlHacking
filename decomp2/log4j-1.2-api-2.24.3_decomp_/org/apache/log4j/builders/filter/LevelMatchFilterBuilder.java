package org.apache.log4j.builders.filter;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.log4j.bridge.FilterWrapper;
import org.apache.log4j.builders.AbstractBuilder;
import org.apache.log4j.config.PropertiesConfiguration;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.xml.XmlConfiguration;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Filter.Result;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.filter.LevelMatchFilter;
import org.w3c.dom.Element;

@Plugin(
   name = "org.apache.log4j.varia.LevelMatchFilter",
   category = "Log4j Builder"
)
public class LevelMatchFilterBuilder extends AbstractBuilder implements FilterBuilder {
   private static final String LEVEL = "LevelToMatch";
   private static final String ACCEPT_ON_MATCH = "AcceptOnMatch";

   public LevelMatchFilterBuilder() {
   }

   public LevelMatchFilterBuilder(final String prefix, final Properties props) {
      super(prefix, props);
   }

   public Filter parse(final Element filterElement, final XmlConfiguration config) {
      AtomicReference<String> level = new AtomicReference();
      AtomicBoolean acceptOnMatch = new AtomicBoolean();
      XmlConfiguration.forEachElement(filterElement.getElementsByTagName("param"), (currentElement) -> {
         if (currentElement.getTagName().equals("param")) {
            switch (this.getNameAttributeKey(currentElement)) {
               case "LevelToMatch":
                  level.set(this.getValueAttribute(currentElement));
                  break;
               case "AcceptOnMatch":
                  acceptOnMatch.set(this.getBooleanValueAttribute(currentElement));
            }
         }

      });
      return this.createFilter((String)level.get(), acceptOnMatch.get());
   }

   public Filter parse(final PropertiesConfiguration config) {
      String level = this.getProperty("LevelToMatch");
      boolean acceptOnMatch = this.getBooleanProperty("AcceptOnMatch");
      return this.createFilter(level, acceptOnMatch);
   }

   private Filter createFilter(final String level, final boolean acceptOnMatch) {
      Level lvl = Level.ERROR;
      if (level != null) {
         lvl = OptionConverter.toLevel(level, org.apache.log4j.Level.ERROR).getVersion2Level();
      }

      org.apache.logging.log4j.core.Filter.Result onMatch = acceptOnMatch ? Result.ACCEPT : Result.DENY;
      return FilterWrapper.adapt(((LevelMatchFilter.Builder)((LevelMatchFilter.Builder)LevelMatchFilter.newBuilder().setLevel(lvl).setOnMatch(onMatch)).setOnMismatch(Result.NEUTRAL)).build());
   }
}
