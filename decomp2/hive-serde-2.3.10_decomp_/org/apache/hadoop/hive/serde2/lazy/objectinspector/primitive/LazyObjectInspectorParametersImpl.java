package org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.hadoop.io.Text;

public class LazyObjectInspectorParametersImpl implements LazyObjectInspectorParameters {
   protected boolean escaped;
   protected byte escapeChar;
   protected boolean extendedBooleanLiteral;
   protected List timestampFormats;
   protected byte[] separators;
   protected Text nullSequence;
   protected boolean lastColumnTakesRest;

   public LazyObjectInspectorParametersImpl() {
      this.escaped = false;
      this.extendedBooleanLiteral = false;
      this.timestampFormats = null;
   }

   public LazyObjectInspectorParametersImpl(boolean escaped, byte escapeChar, boolean extendedBooleanLiteral, List timestampFormats, byte[] separators, Text nullSequence) {
      this.escaped = escaped;
      this.escapeChar = escapeChar;
      this.extendedBooleanLiteral = extendedBooleanLiteral;
      this.timestampFormats = timestampFormats;
      this.separators = separators;
      this.nullSequence = nullSequence;
      this.lastColumnTakesRest = false;
   }

   public LazyObjectInspectorParametersImpl(boolean escaped, byte escapeChar, boolean extendedBooleanLiteral, List timestampFormats, byte[] separators, Text nullSequence, boolean lastColumnTakesRest) {
      this.escaped = escaped;
      this.escapeChar = escapeChar;
      this.extendedBooleanLiteral = extendedBooleanLiteral;
      this.timestampFormats = timestampFormats;
      this.separators = separators;
      this.nullSequence = nullSequence;
      this.lastColumnTakesRest = lastColumnTakesRest;
   }

   public LazyObjectInspectorParametersImpl(LazyObjectInspectorParameters lazyParams) {
      this.escaped = lazyParams.isEscaped();
      this.escapeChar = lazyParams.getEscapeChar();
      this.extendedBooleanLiteral = lazyParams.isExtendedBooleanLiteral();
      this.timestampFormats = lazyParams.getTimestampFormats();
      this.separators = lazyParams.getSeparators();
      this.nullSequence = lazyParams.getNullSequence();
      this.lastColumnTakesRest = lazyParams.isLastColumnTakesRest();
   }

   public boolean isEscaped() {
      return this.escaped;
   }

   public byte getEscapeChar() {
      return this.escapeChar;
   }

   public boolean isExtendedBooleanLiteral() {
      return this.extendedBooleanLiteral;
   }

   public List getTimestampFormats() {
      return this.timestampFormats;
   }

   public byte[] getSeparators() {
      return this.separators;
   }

   public Text getNullSequence() {
      return this.nullSequence;
   }

   public boolean isLastColumnTakesRest() {
      return this.lastColumnTakesRest;
   }

   protected boolean equals(LazyObjectInspectorParametersImpl other) {
      return this.escaped == other.escaped && this.escapeChar == other.escapeChar && this.extendedBooleanLiteral == other.extendedBooleanLiteral && this.lastColumnTakesRest == other.lastColumnTakesRest && ObjectUtils.equals(this.nullSequence, other.nullSequence) && Arrays.equals(this.separators, other.separators) && ObjectUtils.equals(this.timestampFormats, other.timestampFormats);
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else {
         return !(obj instanceof LazyObjectInspectorParametersImpl) ? false : this.equals((LazyObjectInspectorParametersImpl)obj);
      }
   }

   public int hashCode() {
      return (new HashCodeBuilder()).append(this.escaped).append(this.escapeChar).append(this.extendedBooleanLiteral).append(this.timestampFormats).append(this.lastColumnTakesRest).append(this.nullSequence).append(this.separators).toHashCode();
   }
}
