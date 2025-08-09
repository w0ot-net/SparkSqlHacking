package org.apache.logging.log4j.core.config.composite;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.AbstractConfiguration;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.Reconfigurable;
import org.apache.logging.log4j.core.config.status.StatusConfiguration;
import org.apache.logging.log4j.core.util.Loader;
import org.apache.logging.log4j.core.util.Patterns;
import org.apache.logging.log4j.core.util.Source;
import org.apache.logging.log4j.core.util.WatchManager;
import org.apache.logging.log4j.core.util.Watcher;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.Strings;

public class CompositeConfiguration extends AbstractConfiguration implements Reconfigurable {
   public static final String MERGE_STRATEGY_PROPERTY = "log4j.mergeStrategy";
   private final List configurations;
   private MergeStrategy mergeStrategy;

   public CompositeConfiguration(final List configurations) {
      super(((AbstractConfiguration)configurations.get(0)).getLoggerContext(), ConfigurationSource.COMPOSITE_SOURCE);
      this.rootNode = ((AbstractConfiguration)configurations.get(0)).getRootNode();
      this.configurations = configurations;
      String mergeStrategyClassName = PropertiesUtil.getProperties().getStringProperty("log4j.mergeStrategy", DefaultMergeStrategy.class.getName());

      try {
         this.mergeStrategy = (MergeStrategy)Loader.newInstanceOf(mergeStrategyClassName);
      } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException | ClassNotFoundException var8) {
         this.mergeStrategy = new DefaultMergeStrategy();
      }

      for(AbstractConfiguration config : configurations) {
         this.mergeStrategy.mergeRootProperties(this.rootNode, config);
      }

      StatusConfiguration statusConfig = (new StatusConfiguration()).withStatus(this.getDefaultStatus());

      for(Map.Entry entry : this.rootNode.getAttributes().entrySet()) {
         String key = (String)entry.getKey();
         String value = this.getConfigurationStrSubstitutor().replace((String)entry.getValue());
         if ("status".equalsIgnoreCase(key)) {
            statusConfig.withStatus(Strings.toRootUpperCase(value));
         } else if ("dest".equalsIgnoreCase(key)) {
            statusConfig.withDestination(value);
         } else if ("shutdownHook".equalsIgnoreCase(key)) {
            this.isShutdownHookEnabled = !"disable".equalsIgnoreCase(value);
         } else if ("shutdownTimeout".equalsIgnoreCase(key)) {
            this.shutdownTimeoutMillis = Long.parseLong(value);
         } else if ("packages".equalsIgnoreCase(key)) {
            this.pluginPackages.addAll(Arrays.asList(value.split(Patterns.COMMA_SEPARATOR)));
         } else if ("name".equalsIgnoreCase(key)) {
            this.setName(value);
         }
      }

      statusConfig.initialize();
   }

   public void setup() {
      AbstractConfiguration targetConfiguration = (AbstractConfiguration)this.configurations.get(0);
      this.staffChildConfiguration(targetConfiguration);
      WatchManager watchManager = this.getWatchManager();
      WatchManager targetWatchManager = targetConfiguration.getWatchManager();
      if (targetWatchManager.getIntervalSeconds() > 0) {
         watchManager.setIntervalSeconds(targetWatchManager.getIntervalSeconds());
         Map<Source, Watcher> watchers = targetWatchManager.getConfigurationWatchers();

         for(Map.Entry entry : watchers.entrySet()) {
            watchManager.watch((Source)entry.getKey(), ((Watcher)entry.getValue()).newWatcher(this, this.listeners, ((Watcher)entry.getValue()).getLastModified()));
         }
      }

      for(AbstractConfiguration sourceConfiguration : this.configurations.subList(1, this.configurations.size())) {
         this.staffChildConfiguration(sourceConfiguration);
         Node sourceRoot = sourceConfiguration.getRootNode();
         this.mergeStrategy.mergConfigurations(this.rootNode, sourceRoot, this.getPluginManager());
         if (LOGGER.isEnabled(Level.ALL)) {
            StringBuilder sb = new StringBuilder();
            this.printNodes("", this.rootNode, sb);
            System.out.println(sb.toString());
         }

         int monitorInterval = sourceConfiguration.getWatchManager().getIntervalSeconds();
         if (monitorInterval > 0) {
            int currentInterval = watchManager.getIntervalSeconds();
            if (currentInterval <= 0 || monitorInterval < currentInterval) {
               watchManager.setIntervalSeconds(monitorInterval);
            }

            WatchManager sourceWatchManager = sourceConfiguration.getWatchManager();
            Map<Source, Watcher> watchers = sourceWatchManager.getConfigurationWatchers();

            for(Map.Entry entry : watchers.entrySet()) {
               watchManager.watch((Source)entry.getKey(), ((Watcher)entry.getValue()).newWatcher(this, this.listeners, ((Watcher)entry.getValue()).getLastModified()));
            }
         }
      }

   }

   public Configuration reconfigure() {
      LOGGER.debug("Reconfiguring composite configuration");
      List<AbstractConfiguration> configs = new ArrayList();
      ConfigurationFactory factory = ConfigurationFactory.getInstance();

      for(AbstractConfiguration config : this.configurations) {
         ConfigurationSource source = config.getConfigurationSource();
         URI sourceURI = source.getURI();
         Configuration currentConfig = config;
         if (sourceURI == null) {
            LOGGER.warn("Unable to determine URI for configuration {}, changes to it will be ignored", config.getName());
         } else {
            currentConfig = factory.getConfiguration(this.getLoggerContext(), config.getName(), sourceURI);
            if (currentConfig == null) {
               LOGGER.warn("Unable to reload configuration {}, changes to it will be ignored", config.getName());
            }
         }

         configs.add((AbstractConfiguration)currentConfig);
      }

      return new CompositeConfiguration(configs);
   }

   private void staffChildConfiguration(final AbstractConfiguration childConfiguration) {
      childConfiguration.setPluginManager(this.pluginManager);
      childConfiguration.setScriptManager(this.scriptManager);
      childConfiguration.setup();
   }

   private void printNodes(final String indent, final Node node, final StringBuilder sb) {
      sb.append(indent).append(node.getName()).append(" type: ").append(node.getType()).append("\n");
      sb.append(indent).append(node.getAttributes().toString()).append("\n");

      for(Node child : node.getChildren()) {
         this.printNodes(indent + "  ", child, sb);
      }

   }

   public String toString() {
      return this.getClass().getName() + "@" + Integer.toHexString(this.hashCode()) + " [configurations=" + this.configurations + ", mergeStrategy=" + this.mergeStrategy + ", rootNode=" + this.rootNode + ", listeners=" + this.listeners + ", pluginPackages=" + this.pluginPackages + ", pluginManager=" + this.pluginManager + ", isShutdownHookEnabled=" + this.isShutdownHookEnabled + ", shutdownTimeoutMillis=" + this.shutdownTimeoutMillis + ", scriptManager=" + this.scriptManager + "]";
   }
}
