package org.snakeyaml.engine.v2.api;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.UnaryOperator;
import org.snakeyaml.engine.v2.common.SpecVersion;
import org.snakeyaml.engine.v2.env.EnvConfig;
import org.snakeyaml.engine.v2.nodes.Tag;
import org.snakeyaml.engine.v2.schema.Schema;

public final class LoadSettings {
   private final String label;
   private final Map tagConstructors;
   private final IntFunction defaultList;
   private final IntFunction defaultSet;
   private final IntFunction defaultMap;
   private final UnaryOperator versionFunction;
   private final Integer bufferSize;
   private final boolean allowDuplicateKeys;
   private final boolean allowRecursiveKeys;
   private final boolean parseComments;
   private final int maxAliasesForCollections;
   private final boolean useMarks;
   private final Optional envConfig;
   private final int codePointLimit;
   private final Schema schema;
   private final Map customProperties;

   LoadSettings(String label, Map tagConstructors, IntFunction defaultList, IntFunction defaultSet, IntFunction defaultMap, UnaryOperator versionFunction, Integer bufferSize, boolean allowDuplicateKeys, boolean allowRecursiveKeys, int maxAliasesForCollections, boolean useMarks, Map customProperties, Optional envConfig, boolean parseComments, int codePointLimit, Schema schema) {
      this.label = label;
      this.tagConstructors = tagConstructors;
      this.defaultList = defaultList;
      this.defaultSet = defaultSet;
      this.defaultMap = defaultMap;
      this.versionFunction = versionFunction;
      this.bufferSize = bufferSize;
      this.allowDuplicateKeys = allowDuplicateKeys;
      this.allowRecursiveKeys = allowRecursiveKeys;
      this.parseComments = parseComments;
      this.maxAliasesForCollections = maxAliasesForCollections;
      this.useMarks = useMarks;
      this.customProperties = customProperties;
      this.envConfig = envConfig;
      this.codePointLimit = codePointLimit;
      this.schema = schema;
   }

   public static LoadSettingsBuilder builder() {
      return new LoadSettingsBuilder();
   }

   public String getLabel() {
      return this.label;
   }

   public Map getTagConstructors() {
      return this.tagConstructors;
   }

   public IntFunction getDefaultList() {
      return this.defaultList;
   }

   public IntFunction getDefaultSet() {
      return this.defaultSet;
   }

   public IntFunction getDefaultMap() {
      return this.defaultMap;
   }

   public Integer getBufferSize() {
      return this.bufferSize;
   }

   public boolean getAllowDuplicateKeys() {
      return this.allowDuplicateKeys;
   }

   public boolean getAllowRecursiveKeys() {
      return this.allowRecursiveKeys;
   }

   public boolean getUseMarks() {
      return this.useMarks;
   }

   public Function getVersionFunction() {
      return this.versionFunction;
   }

   public Object getCustomProperty(SettingKey key) {
      return this.customProperties.get(key);
   }

   public int getMaxAliasesForCollections() {
      return this.maxAliasesForCollections;
   }

   public Optional getEnvConfig() {
      return this.envConfig;
   }

   public boolean getParseComments() {
      return this.parseComments;
   }

   public int getCodePointLimit() {
      return this.codePointLimit;
   }

   public Schema getSchema() {
      return this.schema;
   }
}
