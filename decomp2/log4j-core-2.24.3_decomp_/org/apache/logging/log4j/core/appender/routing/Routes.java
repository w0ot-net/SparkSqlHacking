package org.apache.logging.log4j.core.appender.routing;

import java.util.Objects;
import java.util.concurrent.ConcurrentMap;
import javax.script.Bindings;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.PluginConfiguration;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.apache.logging.log4j.core.script.AbstractScript;
import org.apache.logging.log4j.core.script.ScriptManager;
import org.apache.logging.log4j.core.script.ScriptRef;
import org.apache.logging.log4j.status.StatusLogger;

@Plugin(
   name = "Routes",
   category = "Core",
   printObject = true
)
public final class Routes {
   private static final String LOG_EVENT_KEY = "logEvent";
   private static final Logger LOGGER = StatusLogger.getLogger();
   private final Configuration configuration;
   private final String pattern;
   private final AbstractScript patternScript;
   private final Route[] routes;

   /** @deprecated */
   @Deprecated
   public static Routes createRoutes(final String pattern, final Route... routes) {
      if (routes != null && routes.length != 0) {
         return new Routes((Configuration)null, (AbstractScript)null, pattern, routes);
      } else {
         LOGGER.error("No routes configured");
         return null;
      }
   }

   @PluginBuilderFactory
   public static Builder newBuilder() {
      return new Builder();
   }

   private Routes(final Configuration configuration, final AbstractScript patternScript, final String pattern, final Route... routes) {
      this.configuration = configuration;
      this.patternScript = patternScript;
      this.pattern = pattern;
      this.routes = routes;
   }

   public String getPattern(final LogEvent event, final ConcurrentMap scriptStaticVariables) {
      if (this.patternScript != null) {
         ScriptManager scriptManager = this.configuration.getScriptManager();
         Bindings bindings = scriptManager.createBindings(this.patternScript);
         bindings.put("staticVariables", scriptStaticVariables);
         bindings.put("logEvent", event);
         Object object = scriptManager.execute(this.patternScript.getName(), bindings);
         bindings.remove("logEvent");
         return Objects.toString(object, (String)null);
      } else {
         return this.pattern;
      }
   }

   public AbstractScript getPatternScript() {
      return this.patternScript;
   }

   public Route getRoute(final String key) {
      for(Route route : this.routes) {
         if (Objects.equals(route.getKey(), key)) {
            return route;
         }
      }

      return null;
   }

   public Route[] getRoutes() {
      return this.routes;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("{");
      boolean first = true;

      for(Route route : this.routes) {
         if (!first) {
            sb.append(',');
         }

         first = false;
         sb.append(route.toString());
      }

      sb.append('}');
      return sb.toString();
   }

   public static class Builder implements org.apache.logging.log4j.core.util.Builder {
      @PluginConfiguration
      private Configuration configuration;
      @PluginAttribute("pattern")
      private String pattern;
      @PluginElement("Script")
      private AbstractScript patternScript;
      @PluginElement("Routes")
      @Required
      private Route[] routes;

      public Routes build() {
         if (this.routes != null && this.routes.length != 0) {
            if (this.patternScript != null && this.pattern != null || this.patternScript == null && this.pattern == null) {
               Routes.LOGGER.warn("In a Routes element, you must configure either a Script element or a pattern attribute.");
            }

            if (this.patternScript != null) {
               if (this.configuration == null) {
                  Routes.LOGGER.error("No Configuration defined for Routes; required for Script");
               } else {
                  if (this.configuration.getScriptManager() == null) {
                     Routes.LOGGER.error("Script support is not enabled");
                     return null;
                  }

                  if (!this.configuration.getScriptManager().addScript(this.patternScript) && !(this.patternScript instanceof ScriptRef) && !this.getConfiguration().getScriptManager().addScript(this.patternScript)) {
                     return null;
                  }
               }
            }

            return new Routes(this.configuration, this.patternScript, this.pattern, this.routes);
         } else {
            Routes.LOGGER.error("No Routes configured.");
            return null;
         }
      }

      public Configuration getConfiguration() {
         return this.configuration;
      }

      public String getPattern() {
         return this.pattern;
      }

      public AbstractScript getPatternScript() {
         return this.patternScript;
      }

      public Route[] getRoutes() {
         return this.routes;
      }

      public Builder withConfiguration(final Configuration configuration) {
         this.configuration = configuration;
         return this;
      }

      public Builder withPattern(final String pattern) {
         this.pattern = pattern;
         return this;
      }

      public Builder withPatternScript(final AbstractScript patternScript) {
         this.patternScript = patternScript;
         return this;
      }

      public Builder withRoutes(final Route[] routes) {
         this.routes = routes;
         return this;
      }
   }
}
