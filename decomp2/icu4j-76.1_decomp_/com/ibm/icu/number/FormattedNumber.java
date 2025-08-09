package com.ibm.icu.number;

import com.ibm.icu.impl.FormattedStringBuilder;
import com.ibm.icu.impl.FormattedValueStringBuilderImpl;
import com.ibm.icu.impl.Utility;
import com.ibm.icu.impl.number.DecimalQuantity;
import com.ibm.icu.text.ConstrainedFieldPosition;
import com.ibm.icu.text.DisplayOptions;
import com.ibm.icu.text.FormattedValue;
import com.ibm.icu.text.PluralRules;
import com.ibm.icu.util.MeasureUnit;
import java.math.BigDecimal;
import java.text.AttributedCharacterIterator;
import java.text.Format;

public class FormattedNumber implements FormattedValue {
   final FormattedStringBuilder string;
   final DecimalQuantity fq;
   final MeasureUnit outputUnit;
   final String gender;

   FormattedNumber(FormattedStringBuilder nsb, DecimalQuantity fq, MeasureUnit outputUnit, String gender) {
      this.string = nsb;
      this.fq = fq;
      this.outputUnit = outputUnit;
      this.gender = gender;
   }

   public String toString() {
      return this.string.toString();
   }

   public int length() {
      return this.string.length();
   }

   public char charAt(int index) {
      return this.string.charAt(index);
   }

   public CharSequence subSequence(int start, int end) {
      return this.string.subString(start, end);
   }

   public Appendable appendTo(Appendable appendable) {
      return Utility.appendTo(this.string, appendable);
   }

   public boolean nextPosition(ConstrainedFieldPosition cfpos) {
      return FormattedValueStringBuilderImpl.nextPosition(this.string, cfpos, (Format.Field)null);
   }

   public AttributedCharacterIterator toCharacterIterator() {
      return FormattedValueStringBuilderImpl.toCharacterIterator(this.string, (Format.Field)null);
   }

   public BigDecimal toBigDecimal() {
      return this.fq.toBigDecimal();
   }

   public MeasureUnit getOutputUnit() {
      return this.outputUnit;
   }

   public DisplayOptions.NounClass getNounClass() {
      return DisplayOptions.NounClass.fromIdentifier(this.gender);
   }

   /** @deprecated */
   @Deprecated
   public String getGender() {
      return this.gender == null ? "" : this.gender;
   }

   /** @deprecated */
   @Deprecated
   public PluralRules.IFixedDecimal getFixedDecimal() {
      return this.fq;
   }
}
