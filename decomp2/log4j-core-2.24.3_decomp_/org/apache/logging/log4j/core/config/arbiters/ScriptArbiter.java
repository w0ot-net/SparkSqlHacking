package org.apache.logging.log4j.core.config.arbiters;

import javax.script.SimpleBindings;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.AbstractConfiguration;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.PluginConfiguration;
import org.apache.logging.log4j.core.config.plugins.PluginNode;
import org.apache.logging.log4j.core.config.plugins.util.PluginType;
import org.apache.logging.log4j.core.script.AbstractScript;
import org.apache.logging.log4j.core.script.ScriptRef;
import org.apache.logging.log4j.status.StatusLogger;

@Plugin(
   name = "ScriptArbiter",
   category = "Core",
   elementType = "Arbiter",
   deferChildren = true,
   printObject = true
)
public class ScriptArbiter implements Arbiter {
   private final AbstractScript script;
   private final Configuration configuration;

   private ScriptArbiter(final Configuration configuration, final AbstractScript script) {
      this.configuration = configuration;
      this.script = script;
   }

   public boolean isCondition() {
      SimpleBindings bindings = new SimpleBindings();
      bindings.putAll(this.configuration.getProperties());
      bindings.put("substitutor", this.configuration.getStrSubstitutor());
      Object object = this.configuration.getScriptManager().execute(this.script.getName(), bindings);
      return Boolean.parseBoolean(object.toString());
   }

   @PluginBuilderFactory
   public static Builder newBuilder() {
      return new Builder();
   }

   public static class Builder implements org.apache.logging.log4j.core.util.Builder {
      private static final Logger LOGGER = StatusLogger.getLogger();
      @PluginConfiguration
      private AbstractConfiguration configuration;
      @PluginNode
      private Node node;

      public Builder setConfiguration(final AbstractConfiguration configuration) {
         this.configuration = configuration;
         return this.asBuilder();
      }

      public Builder setNode(final Node node) {
         this.node = node;
         return this.asBuilder();
      }

      public Builder asBuilder() {
         return this;
      }

      public ScriptArbiter build() {
         AbstractScript script = null;

         for(Node child : this.node.getChildren()) {
            PluginType<?> type = child.getType();
            if (type == null) {
               LOGGER.error("Node {} is missing a Plugintype", child.getName());
            } else if (AbstractScript.class.isAssignableFrom(type.getPluginClass())) {
               script = (AbstractScript)this.configuration.createPluginObject(type, child);
               this.node.getChildren().remove(child);
               break;
            }
         }

         if (script == null) {
            LOGGER.error("A Script, ScriptFile or ScriptRef element must be provided for this ScriptFilter");
            return null;
         } else if (this.configuration.getScriptManager() == null) {
            LOGGER.error("Script support is not enabled");
            return null;
         } else {
            if (script instanceof ScriptRef) {
               if (this.configuration.getScriptManager().getScript(script.getName()) == null) {
                  LOGGER.error("No script with name {} has been declared.", script.getName());
                  return null;
               }
            } else if (!this.configuration.getScriptManager().addScript(script)) {
               return null;
            }

            return new ScriptArbiter(this.configuration, script);
         }
      }
   }
}
