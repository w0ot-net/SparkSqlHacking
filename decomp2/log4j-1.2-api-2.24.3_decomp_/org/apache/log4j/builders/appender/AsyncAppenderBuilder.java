package org.apache.log4j.builders.appender;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.log4j.Appender;
import org.apache.log4j.bridge.AppenderWrapper;
import org.apache.log4j.bridge.FilterAdapter;
import org.apache.log4j.builders.AbstractBuilder;
import org.apache.log4j.config.Log4j1Configuration;
import org.apache.log4j.config.PropertiesConfiguration;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.xml.XmlConfiguration;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.AsyncAppender;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.Strings;
import org.w3c.dom.Element;

@Plugin(
   name = "org.apache.log4j.AsyncAppender",
   category = "Log4j Builder"
)
public class AsyncAppenderBuilder extends AbstractBuilder implements AppenderBuilder {
   private static final Logger LOGGER = StatusLogger.getLogger();
   private static final String BLOCKING_PARAM = "Blocking";
   private static final String INCLUDE_LOCATION_PARAM = "IncludeLocation";

   public AsyncAppenderBuilder() {
   }

   public AsyncAppenderBuilder(final String prefix, final Properties props) {
      super(prefix, props);
   }

   public Appender parseAppender(final Element appenderElement, final XmlConfiguration config) {
      String name = this.getNameAttribute(appenderElement);
      AtomicReference<List<String>> appenderRefs = new AtomicReference(new ArrayList());
      AtomicBoolean blocking = new AtomicBoolean();
      AtomicBoolean includeLocation = new AtomicBoolean();
      AtomicReference<String> level = new AtomicReference("trace");
      AtomicInteger bufferSize = new AtomicInteger(1024);
      AtomicReference<Filter> filter = new AtomicReference();
      XmlConfiguration.forEachElement(appenderElement.getChildNodes(), (currentElement) -> {
         switch (currentElement.getTagName()) {
            case "appender-ref":
               Appender appender = config.findAppenderByReference(currentElement);
               if (appender != null) {
                  ((List)appenderRefs.get()).add(appender.getName());
               }
               break;
            case "filter":
               config.addFilter(filter, currentElement);
               break;
            case "param":
               switch (this.getNameAttributeKey(currentElement)) {
                  case "BufferSize":
                     this.set("BufferSize", currentElement, bufferSize);
                     break;
                  case "Blocking":
                     this.set("Blocking", currentElement, blocking);
                     break;
                  case "IncludeLocation":
                     this.set("IncludeLocation", currentElement, includeLocation);
                     break;
                  case "Threshold":
                     this.set("Threshold", currentElement, level);
               }
         }

      });
      return this.createAppender(name, (String)level.get(), (String[])((List)appenderRefs.get()).toArray(Strings.EMPTY_ARRAY), blocking.get(), bufferSize.get(), includeLocation.get(), (Filter)filter.get(), config);
   }

   public Appender parseAppender(final String name, final String appenderPrefix, final String layoutPrefix, final String filterPrefix, final Properties props, final PropertiesConfiguration configuration) {
      String appenderRef = this.getProperty("appender-ref");
      Filter filter = configuration.parseAppenderFilters(props, filterPrefix, name);
      boolean blocking = this.getBooleanProperty("Blocking");
      boolean includeLocation = this.getBooleanProperty("IncludeLocation");
      String level = this.getProperty("Threshold");
      int bufferSize = this.getIntegerProperty("BufferSize", 1024);
      if (appenderRef == null) {
         LOGGER.error("No appender references configured for AsyncAppender {}", name);
         return null;
      } else {
         Appender appender = configuration.parseAppender(props, appenderRef);
         if (appender == null) {
            LOGGER.error("Cannot locate Appender {}", appenderRef);
            return null;
         } else {
            return this.createAppender(name, level, new String[]{appenderRef}, blocking, bufferSize, includeLocation, filter, configuration);
         }
      }
   }

   private Appender createAppender(final String name, final String level, final String[] appenderRefs, final boolean blocking, final int bufferSize, final boolean includeLocation, final Filter filter, final Log4j1Configuration configuration) {
      if (appenderRefs.length == 0) {
         LOGGER.error("No appender references configured for AsyncAppender {}", name);
         return null;
      } else {
         Level logLevel = OptionConverter.convertLevel(level, Level.TRACE);
         AppenderRef[] refs = new AppenderRef[appenderRefs.length];
         int index = 0;

         for(String appenderRef : appenderRefs) {
            refs[index++] = AppenderRef.createAppenderRef(appenderRef, logLevel, (org.apache.logging.log4j.core.Filter)null);
         }

         AsyncAppender.Builder builder = AsyncAppender.newBuilder();
         builder.setFilter(FilterAdapter.adapt(filter));
         return AppenderWrapper.adapt(builder.setName(name).setAppenderRefs(refs).setBlocking(blocking).setBufferSize(bufferSize).setIncludeLocation(includeLocation).setConfiguration(configuration).build());
      }
   }
}
