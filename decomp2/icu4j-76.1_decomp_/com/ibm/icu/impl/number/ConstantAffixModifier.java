package com.ibm.icu.impl.number;

import com.ibm.icu.impl.FormattedStringBuilder;
import java.text.Format;

public class ConstantAffixModifier implements Modifier {
   public static final ConstantAffixModifier EMPTY = new ConstantAffixModifier();
   private final String prefix;
   private final String suffix;
   private final Format.Field field;
   private final boolean strong;

   public ConstantAffixModifier(String prefix, String suffix, Format.Field field, boolean strong) {
      this.prefix = prefix == null ? "" : prefix;
      this.suffix = suffix == null ? "" : suffix;
      this.field = field;
      this.strong = strong;
   }

   public ConstantAffixModifier() {
      this.prefix = "";
      this.suffix = "";
      this.field = null;
      this.strong = false;
   }

   public int apply(FormattedStringBuilder output, int leftIndex, int rightIndex) {
      int length = output.insert(rightIndex, (CharSequence)this.suffix, (Object)this.field);
      length += output.insert(leftIndex, (CharSequence)this.prefix, (Object)this.field);
      return length;
   }

   public int getPrefixLength() {
      return this.prefix.length();
   }

   public int getCodePointCount() {
      return this.prefix.codePointCount(0, this.prefix.length()) + this.suffix.codePointCount(0, this.suffix.length());
   }

   public boolean isStrong() {
      return this.strong;
   }

   public boolean containsField(Format.Field field) {
      assert false;

      return false;
   }

   public Modifier.Parameters getParameters() {
      return null;
   }

   public boolean strictEquals(Modifier other) {
      if (!(other instanceof ConstantAffixModifier)) {
         return false;
      } else {
         ConstantAffixModifier _other = (ConstantAffixModifier)other;
         return this.prefix.equals(_other.prefix) && this.suffix.equals(_other.suffix) && this.field == _other.field && this.strong == _other.strong;
      }
   }

   public String toString() {
      return String.format("<ConstantAffixModifier prefix:'%s' suffix:'%s'>", this.prefix, this.suffix);
   }
}
