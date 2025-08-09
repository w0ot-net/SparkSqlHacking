package org.apache.log4j.builders.appender;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.log4j.Appender;
import org.apache.log4j.Layout;
import org.apache.log4j.bridge.AppenderWrapper;
import org.apache.log4j.bridge.LayoutAdapter;
import org.apache.log4j.builders.AbstractBuilder;
import org.apache.log4j.config.PropertiesConfiguration;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.xml.XmlConfiguration;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import org.apache.logging.log4j.core.appender.rolling.DefaultRolloverStrategy;
import org.apache.logging.log4j.core.appender.rolling.TimeBasedTriggeringPolicy;
import org.apache.logging.log4j.core.appender.rolling.TriggeringPolicy;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.status.StatusLogger;
import org.w3c.dom.Element;

@Plugin(
   name = "org.apache.log4j.rolling.RollingFileAppender",
   category = "Log4j Builder"
)
public class EnhancedRollingFileAppenderBuilder extends AbstractBuilder implements AppenderBuilder {
   private static final String TIME_BASED_ROLLING_POLICY = "org.apache.log4j.rolling.TimeBasedRollingPolicy";
   private static final String FIXED_WINDOW_ROLLING_POLICY = "org.apache.log4j.rolling.FixedWindowRollingPolicy";
   private static final Logger LOGGER = StatusLogger.getLogger();
   private static final String TRIGGERING_TAG = "triggeringPolicy";
   private static final String ROLLING_TAG = "rollingPolicy";
   private static final int DEFAULT_MIN_INDEX = 1;
   private static final int DEFAULT_MAX_INDEX = 7;
   private static final String ACTIVE_FILE_PARAM = "ActiveFileName";
   private static final String FILE_PATTERN_PARAM = "FileNamePattern";
   private static final String MIN_INDEX_PARAM = "MinIndex";
   private static final String MAX_INDEX_PARAM = "MaxIndex";

   public EnhancedRollingFileAppenderBuilder() {
   }

   public EnhancedRollingFileAppenderBuilder(final String prefix, final Properties properties) {
      super(prefix, properties);
   }

   private void parseRollingPolicy(final Element element, final XmlConfiguration configuration, final AtomicReference rollingPolicyClassName, final AtomicReference activeFileName, final AtomicReference fileNamePattern, final AtomicInteger minIndex, final AtomicInteger maxIndex) {
      rollingPolicyClassName.set(configuration.subst(element.getAttribute("class"), this.getProperties()));
      XmlConfiguration.forEachElement(element.getChildNodes(), (currentElement) -> {
         switch (currentElement.getTagName()) {
            case "param":
               switch (this.getNameAttributeKey(currentElement)) {
                  case "ActiveFileName":
                     this.set("ActiveFileName", currentElement, activeFileName);
                     break;
                  case "FileNamePattern":
                     this.set("FileNamePattern", currentElement, fileNamePattern);
                     break;
                  case "MinIndex":
                     this.set("MinIndex", currentElement, minIndex);
                     break;
                  case "MaxIndex":
                     this.set("MaxIndex", currentElement, maxIndex);
               }
            default:
         }
      });
   }

   public Appender parseAppender(final Element element, final XmlConfiguration configuration) {
      String name = this.getNameAttribute(element);
      AtomicReference<Layout> layout = new AtomicReference();
      AtomicReference<Filter> filter = new AtomicReference();
      AtomicReference<String> fileName = new AtomicReference();
      AtomicReference<String> level = new AtomicReference();
      AtomicBoolean immediateFlush = new AtomicBoolean(true);
      AtomicBoolean append = new AtomicBoolean(true);
      AtomicBoolean bufferedIo = new AtomicBoolean();
      AtomicInteger bufferSize = new AtomicInteger(8192);
      AtomicReference<String> rollingPolicyClassName = new AtomicReference();
      AtomicReference<String> activeFileName = new AtomicReference();
      AtomicReference<String> fileNamePattern = new AtomicReference();
      AtomicInteger minIndex = new AtomicInteger(1);
      AtomicInteger maxIndex = new AtomicInteger(7);
      AtomicReference<TriggeringPolicy> triggeringPolicy = new AtomicReference();
      XmlConfiguration.forEachElement(element.getChildNodes(), (currentElement) -> {
         switch (currentElement.getTagName()) {
            case "rollingPolicy":
               this.parseRollingPolicy(currentElement, configuration, rollingPolicyClassName, activeFileName, fileNamePattern, minIndex, maxIndex);
               break;
            case "triggeringPolicy":
               triggeringPolicy.set(configuration.parseTriggeringPolicy(currentElement));
               break;
            case "layout":
               layout.set(configuration.parseLayout(currentElement));
               break;
            case "filter":
               configuration.addFilter(filter, currentElement);
               break;
            case "param":
               switch (this.getNameAttributeKey(currentElement)) {
                  case "File":
                     this.set("File", currentElement, fileName);
                     break;
                  case "Append":
                     this.set("Append", currentElement, append);
                     break;
                  case "BufferedIO":
                     this.set("BufferedIO", currentElement, bufferedIo);
                     break;
                  case "BufferSize":
                     this.set("BufferSize", currentElement, bufferSize);
                     break;
                  case "Threshold":
                     this.set("Threshold", currentElement, level);
                     break;
                  case "ImmediateFlush":
                     this.set("ImmediateFlush", currentElement, immediateFlush);
               }
         }

      });
      return this.createAppender(name, (Layout)layout.get(), (Filter)filter.get(), (String)fileName.get(), (String)level.get(), immediateFlush.get(), append.get(), bufferedIo.get(), bufferSize.get(), (String)rollingPolicyClassName.get(), (String)activeFileName.get(), (String)fileNamePattern.get(), minIndex.get(), maxIndex.get(), (TriggeringPolicy)triggeringPolicy.get(), configuration);
   }

   public Appender parseAppender(final String name, final String appenderPrefix, final String layoutPrefix, final String filterPrefix, final Properties props, final PropertiesConfiguration configuration) {
      Layout layout = configuration.parseLayout(layoutPrefix, name, props);
      Filter filter = configuration.parseAppenderFilters(props, filterPrefix, name);
      String level = this.getProperty("Threshold");
      String fileName = this.getProperty("File");
      boolean append = this.getBooleanProperty("Append", true);
      boolean immediateFlush = this.getBooleanProperty("ImmediateFlush", true);
      boolean bufferedIo = this.getBooleanProperty("BufferedIO", false);
      int bufferSize = Integer.parseInt(this.getProperty("BufferSize", "8192"));
      String rollingPolicyClassName = this.getProperty("rollingPolicy");
      int minIndex = this.getIntegerProperty("rollingPolicy.MinIndex", 1);
      int maxIndex = this.getIntegerProperty("rollingPolicy.MaxIndex", 7);
      String activeFileName = this.getProperty("rollingPolicy.ActiveFileName");
      String fileNamePattern = this.getProperty("rollingPolicy.FileNamePattern");
      TriggeringPolicy triggeringPolicy = configuration.parseTriggeringPolicy(props, appenderPrefix + "." + "triggeringPolicy");
      return this.createAppender(name, layout, filter, fileName, level, immediateFlush, append, bufferedIo, bufferSize, rollingPolicyClassName, activeFileName, fileNamePattern, minIndex, maxIndex, triggeringPolicy, configuration);
   }

   private Appender createAppender(final String name, final Layout layout, final Filter filter, final String fileName, final String level, final boolean immediateFlush, final boolean append, final boolean bufferedIo, final int bufferSize, final String rollingPolicyClassName, final String activeFileName, final String fileNamePattern, final int minIndex, final int maxIndex, final TriggeringPolicy triggeringPolicy, final Configuration configuration) {
      org.apache.logging.log4j.core.Layout<?> fileLayout = LayoutAdapter.adapt(layout);
      boolean actualImmediateFlush = bufferedIo ? false : immediateFlush;
      org.apache.logging.log4j.core.Filter fileFilter = buildFilters(level, filter);
      if (rollingPolicyClassName == null) {
         LOGGER.error("Unable to create RollingFileAppender, no rolling policy provided.");
         return null;
      } else {
         String actualFileName = activeFileName != null ? activeFileName : fileName;
         if (actualFileName == null) {
            LOGGER.error("Unable to create RollingFileAppender, no file name provided.");
            return null;
         } else if (fileNamePattern == null) {
            LOGGER.error("Unable to create RollingFileAppender, no file name pattern provided.");
            return null;
         } else {
            DefaultRolloverStrategy.Builder rolloverStrategyBuilder = DefaultRolloverStrategy.newBuilder();
            switch (rollingPolicyClassName) {
               case "org.apache.log4j.rolling.FixedWindowRollingPolicy":
                  rolloverStrategyBuilder.withMin(Integer.toString(minIndex)).withMax(Integer.toString(maxIndex));
               case "org.apache.log4j.rolling.TimeBasedRollingPolicy":
                  break;
               default:
                  LOGGER.warn("Unsupported rolling policy: {}", rollingPolicyClassName);
            }

            TriggeringPolicy actualTriggeringPolicy;
            if (triggeringPolicy != null) {
               actualTriggeringPolicy = triggeringPolicy;
            } else {
               if (!rollingPolicyClassName.equals("org.apache.log4j.rolling.TimeBasedRollingPolicy")) {
                  LOGGER.error("Unable to create RollingFileAppender, no triggering policy provided.");
                  return null;
               }

               actualTriggeringPolicy = TimeBasedTriggeringPolicy.newBuilder().build();
            }

            return AppenderWrapper.adapt(((RollingFileAppender.Builder)((RollingFileAppender.Builder)((RollingFileAppender.Builder)((RollingFileAppender.Builder)((RollingFileAppender.Builder)((RollingFileAppender.Builder)((RollingFileAppender.Builder)RollingFileAppender.newBuilder().withAppend(append).setBufferedIo(bufferedIo)).setBufferSize(bufferedIo ? bufferSize : 0)).setConfiguration(configuration)).withFileName(actualFileName).withFilePattern(fileNamePattern).setFilter(fileFilter)).setImmediateFlush(actualImmediateFlush)).setLayout(fileLayout)).setName(name)).withPolicy(actualTriggeringPolicy).withStrategy(rolloverStrategyBuilder.build()).build());
         }
      }
   }
}
