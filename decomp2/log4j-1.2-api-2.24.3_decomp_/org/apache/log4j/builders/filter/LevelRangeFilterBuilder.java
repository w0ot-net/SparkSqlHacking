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
import org.apache.logging.log4j.core.filter.LevelRangeFilter;
import org.w3c.dom.Element;

@Plugin(
   name = "org.apache.log4j.varia.LevelRangeFilter",
   category = "Log4j Builder"
)
public class LevelRangeFilterBuilder extends AbstractBuilder implements FilterBuilder {
   private static final String LEVEL_MAX = "LevelMax";
   private static final String LEVEL_MIN = "LevelMin";
   private static final String ACCEPT_ON_MATCH = "AcceptOnMatch";

   public LevelRangeFilterBuilder() {
   }

   public LevelRangeFilterBuilder(final String prefix, final Properties props) {
      super(prefix, props);
   }

   public Filter parse(final Element filterElement, final XmlConfiguration config) {
      AtomicReference<String> levelMax = new AtomicReference();
      AtomicReference<String> levelMin = new AtomicReference();
      AtomicBoolean acceptOnMatch = new AtomicBoolean();
      XmlConfiguration.forEachElement(filterElement.getElementsByTagName("param"), (currentElement) -> {
         if (currentElement.getTagName().equals("param")) {
            switch (this.getNameAttributeKey(currentElement)) {
               case "LevelMax":
                  levelMax.set(this.getValueAttribute(currentElement));
                  break;
               case "LevelMin":
                  levelMin.set(this.getValueAttribute(currentElement));
                  break;
               case "AcceptOnMatch":
                  acceptOnMatch.set(this.getBooleanValueAttribute(currentElement));
            }
         }

      });
      return this.createFilter((String)levelMax.get(), (String)levelMin.get(), acceptOnMatch.get());
   }

   public Filter parse(final PropertiesConfiguration config) {
      String levelMax = this.getProperty("LevelMax");
      String levelMin = this.getProperty("LevelMin");
      boolean acceptOnMatch = this.getBooleanProperty("AcceptOnMatch");
      return this.createFilter(levelMax, levelMin, acceptOnMatch);
   }

   private Filter createFilter(final String levelMax, final String levelMin, final boolean acceptOnMatch) {
      Level max = Level.OFF;
      Level min = Level.ALL;
      if (levelMax != null) {
         max = OptionConverter.toLevel(levelMax, org.apache.log4j.Level.OFF).getVersion2Level();
      }

      if (levelMin != null) {
         min = OptionConverter.toLevel(levelMin, org.apache.log4j.Level.ALL).getVersion2Level();
      }

      org.apache.logging.log4j.core.Filter.Result onMatch = acceptOnMatch ? Result.ACCEPT : Result.NEUTRAL;
      return FilterWrapper.adapt(LevelRangeFilter.createFilter(max, min, onMatch, Result.DENY));
   }
}
