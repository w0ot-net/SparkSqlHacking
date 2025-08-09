package org.yaml.snakeyaml;

import java.util.Map;
import java.util.TimeZone;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.serializer.AnchorGenerator;
import org.yaml.snakeyaml.serializer.NumberAnchorGenerator;

public class DumperOptions {
   private ScalarStyle defaultStyle;
   private FlowStyle defaultFlowStyle;
   private boolean canonical;
   private boolean allowUnicode;
   private boolean allowReadOnlyProperties;
   private int indent;
   private int indicatorIndent;
   private boolean indentWithIndicator;
   private int bestWidth;
   private boolean splitLines;
   private LineBreak lineBreak;
   private boolean explicitStart;
   private boolean explicitEnd;
   private TimeZone timeZone;
   private int maxSimpleKeyLength;
   private boolean processComments;
   private NonPrintableStyle nonPrintableStyle;
   private Version version;
   private Map tags;
   private Boolean prettyFlow;
   private AnchorGenerator anchorGenerator;
   private boolean dereferenceAliases;

   public DumperOptions() {
      this.defaultStyle = DumperOptions.ScalarStyle.PLAIN;
      this.defaultFlowStyle = DumperOptions.FlowStyle.AUTO;
      this.canonical = false;
      this.allowUnicode = true;
      this.allowReadOnlyProperties = false;
      this.indent = 2;
      this.indicatorIndent = 0;
      this.indentWithIndicator = false;
      this.bestWidth = 80;
      this.splitLines = true;
      this.lineBreak = DumperOptions.LineBreak.UNIX;
      this.explicitStart = false;
      this.explicitEnd = false;
      this.timeZone = null;
      this.maxSimpleKeyLength = 128;
      this.processComments = false;
      this.nonPrintableStyle = DumperOptions.NonPrintableStyle.BINARY;
      this.version = null;
      this.tags = null;
      this.prettyFlow = false;
      this.anchorGenerator = new NumberAnchorGenerator(0);
      this.dereferenceAliases = false;
   }

   public boolean isAllowUnicode() {
      return this.allowUnicode;
   }

   public void setAllowUnicode(boolean allowUnicode) {
      this.allowUnicode = allowUnicode;
   }

   public ScalarStyle getDefaultScalarStyle() {
      return this.defaultStyle;
   }

   public void setDefaultScalarStyle(ScalarStyle defaultStyle) {
      if (defaultStyle == null) {
         throw new NullPointerException("Use ScalarStyle enum.");
      } else {
         this.defaultStyle = defaultStyle;
      }
   }

   public void setIndent(int indent) {
      if (indent < 1) {
         throw new YAMLException("Indent must be at least 1");
      } else if (indent > 10) {
         throw new YAMLException("Indent must be at most 10");
      } else {
         this.indent = indent;
      }
   }

   public int getIndent() {
      return this.indent;
   }

   public void setIndicatorIndent(int indicatorIndent) {
      if (indicatorIndent < 0) {
         throw new YAMLException("Indicator indent must be non-negative.");
      } else if (indicatorIndent > 9) {
         throw new YAMLException("Indicator indent must be at most Emitter.MAX_INDENT-1: 9");
      } else {
         this.indicatorIndent = indicatorIndent;
      }
   }

   public int getIndicatorIndent() {
      return this.indicatorIndent;
   }

   public boolean getIndentWithIndicator() {
      return this.indentWithIndicator;
   }

   public void setIndentWithIndicator(boolean indentWithIndicator) {
      this.indentWithIndicator = indentWithIndicator;
   }

   public void setVersion(Version version) {
      this.version = version;
   }

   public Version getVersion() {
      return this.version;
   }

   public void setCanonical(boolean canonical) {
      this.canonical = canonical;
   }

   public boolean isCanonical() {
      return this.canonical;
   }

   public void setPrettyFlow(boolean prettyFlow) {
      this.prettyFlow = prettyFlow;
   }

   public boolean isPrettyFlow() {
      return this.prettyFlow;
   }

   public void setWidth(int bestWidth) {
      this.bestWidth = bestWidth;
   }

   public int getWidth() {
      return this.bestWidth;
   }

   public void setSplitLines(boolean splitLines) {
      this.splitLines = splitLines;
   }

   public boolean getSplitLines() {
      return this.splitLines;
   }

   public LineBreak getLineBreak() {
      return this.lineBreak;
   }

   public void setDefaultFlowStyle(FlowStyle defaultFlowStyle) {
      if (defaultFlowStyle == null) {
         throw new NullPointerException("Use FlowStyle enum.");
      } else {
         this.defaultFlowStyle = defaultFlowStyle;
      }
   }

   public FlowStyle getDefaultFlowStyle() {
      return this.defaultFlowStyle;
   }

   public void setLineBreak(LineBreak lineBreak) {
      if (lineBreak == null) {
         throw new NullPointerException("Specify line break.");
      } else {
         this.lineBreak = lineBreak;
      }
   }

   public boolean isExplicitStart() {
      return this.explicitStart;
   }

   public void setExplicitStart(boolean explicitStart) {
      this.explicitStart = explicitStart;
   }

   public boolean isExplicitEnd() {
      return this.explicitEnd;
   }

   public void setExplicitEnd(boolean explicitEnd) {
      this.explicitEnd = explicitEnd;
   }

   public Map getTags() {
      return this.tags;
   }

   public void setTags(Map tags) {
      this.tags = tags;
   }

   public boolean isAllowReadOnlyProperties() {
      return this.allowReadOnlyProperties;
   }

   public void setAllowReadOnlyProperties(boolean allowReadOnlyProperties) {
      this.allowReadOnlyProperties = allowReadOnlyProperties;
   }

   public TimeZone getTimeZone() {
      return this.timeZone;
   }

   public void setTimeZone(TimeZone timeZone) {
      this.timeZone = timeZone;
   }

   public AnchorGenerator getAnchorGenerator() {
      return this.anchorGenerator;
   }

   public void setAnchorGenerator(AnchorGenerator anchorGenerator) {
      this.anchorGenerator = anchorGenerator;
   }

   public int getMaxSimpleKeyLength() {
      return this.maxSimpleKeyLength;
   }

   public void setMaxSimpleKeyLength(int maxSimpleKeyLength) {
      if (maxSimpleKeyLength > 1024) {
         throw new YAMLException("The simple key must not span more than 1024 stream characters. See https://yaml.org/spec/1.1/#id934537");
      } else {
         this.maxSimpleKeyLength = maxSimpleKeyLength;
      }
   }

   public void setProcessComments(boolean processComments) {
      this.processComments = processComments;
   }

   public boolean isProcessComments() {
      return this.processComments;
   }

   public NonPrintableStyle getNonPrintableStyle() {
      return this.nonPrintableStyle;
   }

   public void setNonPrintableStyle(NonPrintableStyle style) {
      this.nonPrintableStyle = style;
   }

   public boolean isDereferenceAliases() {
      return this.dereferenceAliases;
   }

   public void setDereferenceAliases(boolean dereferenceAliases) {
      this.dereferenceAliases = dereferenceAliases;
   }

   public static enum ScalarStyle {
      DOUBLE_QUOTED('"'),
      SINGLE_QUOTED('\''),
      LITERAL('|'),
      FOLDED('>'),
      JSON_SCALAR_STYLE('J'),
      PLAIN((Character)null);

      private final Character styleChar;

      private ScalarStyle(Character style) {
         this.styleChar = style;
      }

      public Character getChar() {
         return this.styleChar;
      }

      public String toString() {
         return "Scalar style: '" + this.styleChar + "'";
      }

      public static ScalarStyle createStyle(Character style) {
         if (style == null) {
            return PLAIN;
         } else {
            switch (style) {
               case '"':
                  return DOUBLE_QUOTED;
               case '\'':
                  return SINGLE_QUOTED;
               case '>':
                  return FOLDED;
               case '|':
                  return LITERAL;
               default:
                  throw new YAMLException("Unknown scalar style character: " + style);
            }
         }
      }
   }

   public static enum FlowStyle {
      FLOW(Boolean.TRUE),
      BLOCK(Boolean.FALSE),
      AUTO((Boolean)null);

      private final Boolean styleBoolean;

      private FlowStyle(Boolean flowStyle) {
         this.styleBoolean = flowStyle;
      }

      public String toString() {
         return "Flow style: '" + this.styleBoolean + "'";
      }
   }

   public static enum LineBreak {
      WIN("\r\n"),
      MAC("\r"),
      UNIX("\n");

      private final String lineBreak;

      private LineBreak(String lineBreak) {
         this.lineBreak = lineBreak;
      }

      public String getString() {
         return this.lineBreak;
      }

      public String toString() {
         return "Line break: " + this.name();
      }

      public static LineBreak getPlatformLineBreak() {
         String platformLineBreak = System.getProperty("line.separator");

         for(LineBreak lb : values()) {
            if (lb.lineBreak.equals(platformLineBreak)) {
               return lb;
            }
         }

         return UNIX;
      }
   }

   public static enum Version {
      V1_0(new Integer[]{1, 0}),
      V1_1(new Integer[]{1, 1});

      private final Integer[] version;

      private Version(Integer[] version) {
         this.version = version;
      }

      public int major() {
         return this.version[0];
      }

      public int minor() {
         return this.version[1];
      }

      public String getRepresentation() {
         return this.version[0] + "." + this.version[1];
      }

      public String toString() {
         return "Version: " + this.getRepresentation();
      }
   }

   public static enum NonPrintableStyle {
      BINARY,
      ESCAPE;
   }
}
