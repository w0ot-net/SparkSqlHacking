package org.apache.ivy.core.cache;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.ivy.core.RelativeUrlResolver;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.module.status.StatusManager;
import org.apache.ivy.core.settings.TimeoutConstraint;
import org.apache.ivy.plugins.conflict.ConflictManager;
import org.apache.ivy.plugins.matcher.PatternMatcher;
import org.apache.ivy.plugins.namespace.Namespace;
import org.apache.ivy.plugins.parser.ParserSettings;
import org.apache.ivy.plugins.resolver.DependencyResolver;
import org.apache.ivy.util.Message;

class ParserSettingsMonitor {
   private ParserSettings delegatedSettings;
   private final Map substitutes;
   private ParserSettings monitoredSettings = new ParserSettings() {
      public ConflictManager getConflictManager(String name) {
         return ParserSettingsMonitor.this.delegatedSettings.getConflictManager(name);
      }

      public PatternMatcher getMatcher(String matcherName) {
         return ParserSettingsMonitor.this.delegatedSettings.getMatcher(matcherName);
      }

      public Namespace getNamespace(String namespace) {
         return ParserSettingsMonitor.this.delegatedSettings.getNamespace(namespace);
      }

      public RelativeUrlResolver getRelativeUrlResolver() {
         return ParserSettingsMonitor.this.delegatedSettings.getRelativeUrlResolver();
      }

      public ResolutionCacheManager getResolutionCacheManager() {
         return ParserSettingsMonitor.this.delegatedSettings.getResolutionCacheManager();
      }

      public DependencyResolver getResolver(ModuleRevisionId mRevId) {
         return ParserSettingsMonitor.this.delegatedSettings.getResolver(mRevId);
      }

      public StatusManager getStatusManager() {
         return ParserSettingsMonitor.this.delegatedSettings.getStatusManager();
      }

      public File resolveFile(String filename) {
         return ParserSettingsMonitor.this.delegatedSettings.resolveFile(filename);
      }

      public String getDefaultBranch(ModuleId moduleId) {
         return ParserSettingsMonitor.this.delegatedSettings.getDefaultBranch(moduleId);
      }

      public Namespace getContextNamespace() {
         return ParserSettingsMonitor.this.delegatedSettings.getContextNamespace();
      }

      public Map substitute(Map strings) {
         Map<String, String> substituted = new LinkedHashMap();

         for(Map.Entry entry : strings.entrySet()) {
            substituted.put(entry.getKey(), this.substitute((String)entry.getValue()));
         }

         return substituted;
      }

      public String substitute(String value) {
         String r = ParserSettingsMonitor.this.delegatedSettings.substitute(value);
         if (value != null && !value.equals(r)) {
            ParserSettingsMonitor.this.substitutes.put(value, r);
         }

         return r;
      }

      public String getVariable(String value) {
         return ParserSettingsMonitor.this.delegatedSettings.getVariable(value);
      }

      public TimeoutConstraint getTimeoutConstraint(String name) {
         return ParserSettingsMonitor.this.delegatedSettings.getTimeoutConstraint(name);
      }
   };

   public ParserSettingsMonitor(ParserSettings settings) {
      this.delegatedSettings = settings;
      this.substitutes = new HashMap();
   }

   public ParserSettings getMonitoredSettings() {
      return this.monitoredSettings;
   }

   public void endMonitoring() {
      this.monitoredSettings = null;
      this.delegatedSettings = null;
   }

   public boolean hasChanged(ParserSettings newSettings) {
      for(Map.Entry entry : this.substitutes.entrySet()) {
         String key = (String)entry.getKey();
         if (!((String)entry.getValue()).equals(newSettings.substitute(key))) {
            Message.debug("settings variable has changed for : " + key);
            return true;
         }
      }

      return false;
   }
}
