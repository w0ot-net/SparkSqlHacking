package org.apache.logging.log4j.core.filter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationException;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationScheduler;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAliases;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.PluginConfiguration;
import org.apache.logging.log4j.core.filter.mutable.KeyValuePairConfig;
import org.apache.logging.log4j.core.util.AuthorizationProvider;
import org.apache.logging.log4j.core.util.KeyValuePair;
import org.apache.logging.log4j.core.util.internal.HttpInputStreamUtil;
import org.apache.logging.log4j.core.util.internal.LastModifiedSource;
import org.apache.logging.log4j.core.util.internal.Status;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.apache.logging.log4j.util.PropertiesUtil;

@Plugin(
   name = "MutableThreadContextMapFilter",
   category = "Core",
   elementType = "filter",
   printObject = true
)
@PluginAliases({"MutableContextMapFilter"})
@PerformanceSensitive({"allocation"})
public class MutableThreadContextMapFilter extends AbstractFilter {
   private static final String HTTP = "http";
   private static final String HTTPS = "https";
   private static final ObjectMapper MAPPER;
   private static final KeyValuePair[] EMPTY_ARRAY;
   private volatile Filter filter;
   private final long pollInterval;
   private final ConfigurationScheduler scheduler;
   private final LastModifiedSource source;
   private final AuthorizationProvider authorizationProvider;
   private final List listeners;
   private ScheduledFuture future;

   private MutableThreadContextMapFilter(final Filter filter, final LastModifiedSource source, final long pollInterval, final AuthorizationProvider authorizationProvider, final Filter.Result onMatch, final Filter.Result onMismatch, final Configuration configuration) {
      super(onMatch, onMismatch);
      this.listeners = new ArrayList();
      this.future = null;
      this.filter = filter;
      this.pollInterval = pollInterval;
      this.source = source;
      this.scheduler = configuration.getScheduler();
      this.authorizationProvider = authorizationProvider;
   }

   public void start() {
      if (this.pollInterval > 0L) {
         this.future = this.scheduler.scheduleWithFixedDelay(new FileMonitor(), 0L, this.pollInterval, TimeUnit.SECONDS);
         LOGGER.debug("Watching {} with poll interval {}", this.source.toString(), this.pollInterval);
      }

      super.start();
   }

   public boolean stop(final long timeout, final TimeUnit timeUnit) {
      this.future.cancel(true);
      return super.stop(timeout, timeUnit);
   }

   public void registerListener(final FilterConfigUpdateListener listener) {
      this.listeners.add(listener);
   }

   @PluginBuilderFactory
   public static Builder newBuilder() {
      return new Builder();
   }

   public Filter.Result filter(final LogEvent event) {
      return this.filter.filter(event);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final Message msg, final Throwable t) {
      return this.filter.filter(logger, level, marker, msg, t);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final Object msg, final Throwable t) {
      return this.filter.filter(logger, level, marker, msg, t);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object... params) {
      return this.filter.filter(logger, level, marker, msg, params);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0) {
      return this.filter.filter(logger, level, marker, msg, p0);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1) {
      return this.filter.filter(logger, level, marker, msg, p0, p1);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2) {
      return this.filter.filter(logger, level, marker, msg, p0, p1, p2);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3) {
      return this.filter.filter(logger, level, marker, msg, p0, p1, p2, p3);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
      return this.filter.filter(logger, level, marker, msg, p0, p1, p2, p3, p4);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
      return this.filter.filter(logger, level, marker, msg, p0, p1, p2, p3, p4, p5);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
      return this.filter.filter(logger, level, marker, msg, p0, p1, p2, p3, p4, p5, p6);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
      return this.filter.filter(logger, level, marker, msg, p0, p1, p2, p3, p4, p5, p6, p7);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
      return this.filter.filter(logger, level, marker, msg, p0, p1, p2, p3, p4, p5, p6, p7, p8);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
      return this.filter.filter(logger, level, marker, msg, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
   }

   @SuppressFBWarnings(
      value = {"PATH_TRAVERSAL_IN"},
      justification = "The location of the file comes from a configuration value."
   )
   private static LastModifiedSource getSource(final String configLocation) {
      LastModifiedSource source;
      try {
         URI uri = new URI(configLocation);
         if (uri.getScheme() != null) {
            source = new LastModifiedSource(new URI(configLocation));
         } else {
            source = new LastModifiedSource(new File(configLocation));
         }
      } catch (Exception var3) {
         source = new LastModifiedSource(new File(configLocation));
      }

      return source;
   }

   private static ConfigResult getConfig(final LastModifiedSource source, final AuthorizationProvider authorizationProvider) {
      File inputFile = source.getFile();
      InputStream inputStream = null;
      long lastModified = source.getLastModified();
      URI uri = source.getURI();
      HttpInputStreamUtil.Result result;
      if (inputFile != null && inputFile.exists()) {
         try {
            long modified = inputFile.lastModified();
            if (modified > lastModified) {
               source.setLastModified(modified);
               inputStream = Files.newInputStream(inputFile.toPath());
               result = new HttpInputStreamUtil.Result(Status.SUCCESS);
            } else {
               result = new HttpInputStreamUtil.Result(Status.NOT_MODIFIED);
            }
         } catch (Exception var20) {
            result = new HttpInputStreamUtil.Result(Status.ERROR);
         }
      } else if (uri != null && ("http".equalsIgnoreCase(uri.getScheme()) || "https".equalsIgnoreCase(uri.getScheme()))) {
         try {
            result = HttpInputStreamUtil.getInputStream(source, authorizationProvider);
            inputStream = result.getInputStream();
         } catch (ConfigurationException var19) {
            result = new HttpInputStreamUtil.Result(Status.ERROR);
         }
      } else {
         result = new HttpInputStreamUtil.Result(Status.NOT_FOUND);
      }

      ConfigResult configResult = new ConfigResult();
      if (result.getStatus() == Status.SUCCESS) {
         LOGGER.debug("Processing Debug key/value pairs from: {}", source.toString());

         try {
            KeyValuePairConfig keyValuePairConfig = (KeyValuePairConfig)MAPPER.readValue(inputStream, KeyValuePairConfig.class);
            if (keyValuePairConfig != null) {
               Map<String, String[]> configs = keyValuePairConfig.getConfigs();
               if (configs != null && !configs.isEmpty()) {
                  List<KeyValuePair> pairs = new ArrayList();

                  for(Map.Entry entry : configs.entrySet()) {
                     String key = (String)entry.getKey();

                     for(String value : (String[])entry.getValue()) {
                        if (value != null) {
                           pairs.add(new KeyValuePair(key, value));
                        } else {
                           LOGGER.warn("Ignoring null value for {}", key);
                        }
                     }
                  }

                  if (!pairs.isEmpty()) {
                     configResult.pairs = (KeyValuePair[])pairs.toArray(EMPTY_ARRAY);
                     configResult.status = Status.SUCCESS;
                  } else {
                     configResult.status = Status.EMPTY;
                  }
               } else {
                  LOGGER.debug("No configuration data in {}", source.toString());
                  configResult.status = Status.EMPTY;
               }
            } else {
               LOGGER.warn("No configs element in MutableThreadContextMapFilter configuration");
               configResult.status = Status.ERROR;
            }
         } catch (Exception ex) {
            LOGGER.warn("Invalid key/value pair configuration, input ignored: {}", ex.getMessage());
            configResult.status = Status.ERROR;
         }
      } else {
         configResult.status = result.getStatus();
      }

      return configResult;
   }

   static {
      MAPPER = (new ObjectMapper()).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      EMPTY_ARRAY = new KeyValuePair[0];
   }

   public static class Builder extends AbstractFilter.AbstractFilterBuilder implements org.apache.logging.log4j.core.util.Builder {
      @PluginBuilderAttribute
      private String configLocation;
      @PluginBuilderAttribute
      private long pollInterval;
      @PluginConfiguration
      private Configuration configuration;

      public Builder setConfiguration(final Configuration configuration) {
         this.configuration = configuration;
         return this;
      }

      public Builder setPollInterval(final long pollInterval) {
         this.pollInterval = pollInterval;
         return this;
      }

      public Builder setConfigLocation(final String configLocation) {
         this.configLocation = configLocation;
         return this;
      }

      public MutableThreadContextMapFilter build() {
         LastModifiedSource source = MutableThreadContextMapFilter.getSource(this.configLocation);
         if (source == null) {
            return new MutableThreadContextMapFilter(new NoOpFilter(), (LastModifiedSource)null, 0L, (AuthorizationProvider)null, this.getOnMatch(), this.getOnMismatch(), this.configuration);
         } else {
            AuthorizationProvider authorizationProvider = ConfigurationFactory.authorizationProvider(PropertiesUtil.getProperties());
            Filter filter;
            if (this.pollInterval <= 0L) {
               ConfigResult result = MutableThreadContextMapFilter.getConfig(source, authorizationProvider);
               if (result.status == Status.SUCCESS) {
                  if (result.pairs.length > 0) {
                     filter = ThreadContextMapFilter.createFilter(result.pairs, "or", this.getOnMatch(), this.getOnMismatch());
                  } else {
                     filter = new NoOpFilter();
                  }
               } else if (result.status != Status.NOT_FOUND && result.status != Status.EMPTY) {
                  MutableThreadContextMapFilter.LOGGER.warn("Unexpected response returned on initial call: {}", result.status);
                  filter = new NoOpFilter();
               } else {
                  filter = new NoOpFilter();
               }
            } else {
               filter = new NoOpFilter();
            }

            if (this.pollInterval > 0L) {
               this.configuration.getScheduler().incrementScheduledItems();
            }

            return new MutableThreadContextMapFilter(filter, source, this.pollInterval, authorizationProvider, this.getOnMatch(), this.getOnMismatch(), this.configuration);
         }
      }
   }

   private class FileMonitor implements Runnable {
      private FileMonitor() {
      }

      public void run() {
         ConfigResult result = MutableThreadContextMapFilter.getConfig(MutableThreadContextMapFilter.this.source, MutableThreadContextMapFilter.this.authorizationProvider);
         switch (result.status) {
            case SUCCESS:
               MutableThreadContextMapFilter.this.filter = ThreadContextMapFilter.createFilter(result.pairs, "or", MutableThreadContextMapFilter.this.getOnMatch(), MutableThreadContextMapFilter.this.getOnMismatch());
               MutableThreadContextMapFilter.LOGGER.info("MutableThreadContextMapFilter configuration was updated: {}", MutableThreadContextMapFilter.this.filter.toString());
               break;
            case NOT_FOUND:
               if (!(MutableThreadContextMapFilter.this.filter instanceof NoOpFilter)) {
                  MutableThreadContextMapFilter.LOGGER.info("MutableThreadContextMapFilter configuration was removed");
                  MutableThreadContextMapFilter.this.filter = new NoOpFilter();
               }
               break;
            case EMPTY:
               MutableThreadContextMapFilter.LOGGER.debug("MutableThreadContextMapFilter configuration is empty");
               MutableThreadContextMapFilter.this.filter = new NoOpFilter();
         }

         switch (result.status) {
            case SUCCESS:
            case NOT_FOUND:
            case EMPTY:
               for(FilterConfigUpdateListener listener : MutableThreadContextMapFilter.this.listeners) {
                  listener.onEvent();
               }
            case ERROR:
            case NOT_MODIFIED:
            default:
         }
      }
   }

   private static class NoOpFilter extends AbstractFilter {
      public NoOpFilter() {
         super(Filter.Result.NEUTRAL, Filter.Result.NEUTRAL);
      }
   }

   private static class ConfigResult extends HttpInputStreamUtil.Result {
      public KeyValuePair[] pairs;
      public Status status;

      private ConfigResult() {
      }
   }

   public interface FilterConfigUpdateListener {
      void onEvent();
   }
}
