package org.snakeyaml.engine.v2.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.snakeyaml.engine.v2.common.FlowStyle;
import org.snakeyaml.engine.v2.common.NonPrintableStyle;
import org.snakeyaml.engine.v2.common.ScalarStyle;
import org.snakeyaml.engine.v2.common.SpecVersion;
import org.snakeyaml.engine.v2.exceptions.EmitterException;
import org.snakeyaml.engine.v2.exceptions.YamlEngineException;
import org.snakeyaml.engine.v2.nodes.Tag;
import org.snakeyaml.engine.v2.schema.JsonSchema;
import org.snakeyaml.engine.v2.schema.Schema;
import org.snakeyaml.engine.v2.serializer.AnchorGenerator;
import org.snakeyaml.engine.v2.serializer.NumberAnchorGenerator;

public final class DumpSettingsBuilder {
   Map customProperties = new HashMap();
   private boolean explicitStart = false;
   private boolean explicitEnd = false;
   private NonPrintableStyle nonPrintableStyle;
   private Optional explicitRootTag = Optional.empty();
   private AnchorGenerator anchorGenerator = new NumberAnchorGenerator(0);
   private Optional yamlDirective = Optional.empty();
   private Map tagDirective = new HashMap();
   private FlowStyle defaultFlowStyle;
   private ScalarStyle defaultScalarStyle;
   private boolean dereferenceAliases;
   private boolean canonical = false;
   private boolean multiLineFlow;
   private boolean useUnicodeEncoding = true;
   private int indent = 2;
   private int indicatorIndent = 0;
   private int width = 80;
   private String bestLineBreak = "\n";
   private boolean splitLines = true;
   private int maxSimpleKeyLength;
   private boolean indentWithIndicator;
   private boolean dumpComments;
   private Schema schema;

   DumpSettingsBuilder() {
      this.defaultFlowStyle = FlowStyle.AUTO;
      this.defaultScalarStyle = ScalarStyle.PLAIN;
      this.nonPrintableStyle = NonPrintableStyle.ESCAPE;
      this.maxSimpleKeyLength = 128;
      this.indentWithIndicator = false;
      this.dumpComments = false;
      this.schema = new JsonSchema();
      this.dereferenceAliases = false;
   }

   public DumpSettingsBuilder setDefaultFlowStyle(FlowStyle defaultFlowStyle) {
      this.defaultFlowStyle = defaultFlowStyle;
      return this;
   }

   public DumpSettingsBuilder setDefaultScalarStyle(ScalarStyle defaultScalarStyle) {
      this.defaultScalarStyle = defaultScalarStyle;
      return this;
   }

   public DumpSettingsBuilder setExplicitStart(boolean explicitStart) {
      this.explicitStart = explicitStart;
      return this;
   }

   public DumpSettingsBuilder setAnchorGenerator(AnchorGenerator anchorGenerator) {
      Objects.requireNonNull(anchorGenerator, "anchorGenerator cannot be null");
      this.anchorGenerator = anchorGenerator;
      return this;
   }

   public DumpSettingsBuilder setExplicitRootTag(Optional explicitRootTag) {
      Objects.requireNonNull(explicitRootTag, "explicitRootTag cannot be null");
      this.explicitRootTag = explicitRootTag;
      return this;
   }

   public DumpSettingsBuilder setExplicitEnd(boolean explicitEnd) {
      this.explicitEnd = explicitEnd;
      return this;
   }

   public DumpSettingsBuilder setYamlDirective(Optional yamlDirective) {
      Objects.requireNonNull(yamlDirective, "yamlDirective cannot be null");
      this.yamlDirective = yamlDirective;
      return this;
   }

   public DumpSettingsBuilder setTagDirective(Map tagDirective) {
      Objects.requireNonNull(tagDirective, "tagDirective cannot be null");
      this.tagDirective = tagDirective;
      return this;
   }

   public DumpSettingsBuilder setCanonical(boolean canonical) {
      this.canonical = canonical;
      return this;
   }

   public DumpSettingsBuilder setMultiLineFlow(boolean multiLineFlow) {
      this.multiLineFlow = multiLineFlow;
      return this;
   }

   public DumpSettingsBuilder setUseUnicodeEncoding(boolean useUnicodeEncoding) {
      this.useUnicodeEncoding = useUnicodeEncoding;
      return this;
   }

   public DumpSettingsBuilder setIndent(int indent) {
      if (indent < 1) {
         throw new EmitterException("Indent must be at least 1");
      } else if (indent > 10) {
         throw new EmitterException("Indent must be at most 10");
      } else {
         this.indent = indent;
         return this;
      }
   }

   public DumpSettingsBuilder setIndicatorIndent(int indicatorIndent) {
      if (indicatorIndent < 0) {
         throw new EmitterException("Indicator indent must be non-negative");
      } else if (indicatorIndent > 9) {
         throw new EmitterException("Indicator indent must be at most Emitter.MAX_INDENT-1: 9");
      } else {
         this.indicatorIndent = indicatorIndent;
         return this;
      }
   }

   public DumpSettingsBuilder setWidth(int width) {
      this.width = width;
      return this;
   }

   public DumpSettingsBuilder setBestLineBreak(String bestLineBreak) {
      Objects.requireNonNull(bestLineBreak, "bestLineBreak cannot be null");
      this.bestLineBreak = bestLineBreak;
      return this;
   }

   public DumpSettingsBuilder setSplitLines(boolean splitLines) {
      this.splitLines = splitLines;
      return this;
   }

   public DumpSettingsBuilder setMaxSimpleKeyLength(int maxSimpleKeyLength) {
      if (maxSimpleKeyLength > 1024) {
         throw new YamlEngineException("The simple key must not span more than 1024 stream characters. See https://yaml.org/spec/1.2/spec.html#id2798057");
      } else {
         this.maxSimpleKeyLength = maxSimpleKeyLength;
         return this;
      }
   }

   public DumpSettingsBuilder setNonPrintableStyle(NonPrintableStyle nonPrintableStyle) {
      this.nonPrintableStyle = nonPrintableStyle;
      return this;
   }

   public DumpSettingsBuilder setCustomProperty(SettingKey key, Object value) {
      this.customProperties.put(key, value);
      return this;
   }

   public DumpSettingsBuilder setIndentWithIndicator(boolean indentWithIndicator) {
      this.indentWithIndicator = indentWithIndicator;
      return this;
   }

   public DumpSettingsBuilder setDumpComments(boolean dumpComments) {
      this.dumpComments = dumpComments;
      return this;
   }

   public DumpSettingsBuilder setSchema(Schema schema) {
      this.schema = schema;
      return this;
   }

   public DumpSettingsBuilder setDereferenceAliases(Boolean dereferenceAliases) {
      this.dereferenceAliases = dereferenceAliases;
      return this;
   }

   public DumpSettings build() {
      return new DumpSettings(this.explicitStart, this.explicitEnd, this.explicitRootTag, this.anchorGenerator, this.yamlDirective, this.tagDirective, this.defaultFlowStyle, this.defaultScalarStyle, this.nonPrintableStyle, this.schema, this.dereferenceAliases, this.canonical, this.multiLineFlow, this.useUnicodeEncoding, this.indent, this.indicatorIndent, this.width, this.bestLineBreak, this.splitLines, this.maxSimpleKeyLength, this.customProperties, this.indentWithIndicator, this.dumpComments);
   }
}
