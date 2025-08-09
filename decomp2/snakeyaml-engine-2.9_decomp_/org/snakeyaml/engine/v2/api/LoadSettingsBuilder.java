package org.snakeyaml.engine.v2.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.IntFunction;
import java.util.function.UnaryOperator;
import org.snakeyaml.engine.v2.common.SpecVersion;
import org.snakeyaml.engine.v2.env.EnvConfig;
import org.snakeyaml.engine.v2.exceptions.YamlVersionException;
import org.snakeyaml.engine.v2.nodes.Tag;
import org.snakeyaml.engine.v2.schema.JsonSchema;
import org.snakeyaml.engine.v2.schema.Schema;

public final class LoadSettingsBuilder {
   private final Map customProperties = new HashMap();
   private String label = "reader";
   private Map tagConstructors = new HashMap();
   private IntFunction defaultList = ArrayList::new;
   private IntFunction defaultSet = LinkedHashSet::new;
   private IntFunction defaultMap = LinkedHashMap::new;
   private UnaryOperator versionFunction = (version) -> {
      if (version.getMajor() != 1) {
         throw new YamlVersionException(version);
      } else {
         return version;
      }
   };
   private Integer bufferSize = 1024;
   private boolean allowDuplicateKeys = false;
   private boolean allowRecursiveKeys = false;
   private boolean parseComments = false;
   private int maxAliasesForCollections = 50;
   private boolean useMarks = true;
   private Optional envConfig = Optional.empty();
   private int codePointLimit = 3145728;
   private Schema schema = new JsonSchema();

   LoadSettingsBuilder() {
   }

   public LoadSettingsBuilder setLabel(String label) {
      Objects.requireNonNull(label, "label cannot be null");
      this.label = label;
      return this;
   }

   public LoadSettingsBuilder setTagConstructors(Map tagConstructors) {
      this.tagConstructors = tagConstructors;
      return this;
   }

   public LoadSettingsBuilder setDefaultList(IntFunction defaultList) {
      Objects.requireNonNull(defaultList, "defaultList cannot be null");
      this.defaultList = defaultList;
      return this;
   }

   public LoadSettingsBuilder setDefaultSet(IntFunction defaultSet) {
      Objects.requireNonNull(defaultSet, "defaultSet cannot be null");
      this.defaultSet = defaultSet;
      return this;
   }

   public LoadSettingsBuilder setDefaultMap(IntFunction defaultMap) {
      Objects.requireNonNull(defaultMap, "defaultMap cannot be null");
      this.defaultMap = defaultMap;
      return this;
   }

   public LoadSettingsBuilder setBufferSize(Integer bufferSize) {
      this.bufferSize = bufferSize;
      return this;
   }

   public LoadSettingsBuilder setAllowDuplicateKeys(boolean allowDuplicateKeys) {
      this.allowDuplicateKeys = allowDuplicateKeys;
      return this;
   }

   public LoadSettingsBuilder setAllowRecursiveKeys(boolean allowRecursiveKeys) {
      this.allowRecursiveKeys = allowRecursiveKeys;
      return this;
   }

   public LoadSettingsBuilder setMaxAliasesForCollections(int maxAliasesForCollections) {
      this.maxAliasesForCollections = maxAliasesForCollections;
      return this;
   }

   public LoadSettingsBuilder setUseMarks(boolean useMarks) {
      this.useMarks = useMarks;
      return this;
   }

   public LoadSettingsBuilder setVersionFunction(UnaryOperator versionFunction) {
      Objects.requireNonNull(versionFunction, "versionFunction cannot be null");
      this.versionFunction = versionFunction;
      return this;
   }

   public LoadSettingsBuilder setEnvConfig(Optional envConfig) {
      this.envConfig = envConfig;
      return this;
   }

   public LoadSettingsBuilder setCustomProperty(SettingKey key, Object value) {
      this.customProperties.put(key, value);
      return this;
   }

   public LoadSettingsBuilder setParseComments(boolean parseComments) {
      this.parseComments = parseComments;
      return this;
   }

   public LoadSettingsBuilder setCodePointLimit(int codePointLimit) {
      this.codePointLimit = codePointLimit;
      return this;
   }

   public LoadSettingsBuilder setSchema(Schema schema) {
      this.schema = schema;
      return this;
   }

   public LoadSettings build() {
      return new LoadSettings(this.label, this.tagConstructors, this.defaultList, this.defaultSet, this.defaultMap, this.versionFunction, this.bufferSize, this.allowDuplicateKeys, this.allowRecursiveKeys, this.maxAliasesForCollections, this.useMarks, this.customProperties, this.envConfig, this.parseComments, this.codePointLimit, this.schema);
   }
}
