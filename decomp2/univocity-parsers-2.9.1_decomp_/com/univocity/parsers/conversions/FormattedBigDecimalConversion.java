package com.univocity.parsers.conversions;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class FormattedBigDecimalConversion extends NumericConversion {
   public FormattedBigDecimalConversion(BigDecimal valueIfStringIsNull, String valueIfObjectIsNull, String... numericFormats) {
      super(valueIfStringIsNull, valueIfObjectIsNull, (String[])numericFormats);
   }

   public FormattedBigDecimalConversion(BigDecimal valueIfStringIsNull, String valueIfObjectIsNull) {
      super(valueIfStringIsNull, valueIfObjectIsNull);
   }

   public FormattedBigDecimalConversion(String... numericFormats) {
      super((Number)null, (String)null, (String[])numericFormats);
   }

   public FormattedBigDecimalConversion(DecimalFormat... numericFormatters) {
      super(numericFormatters);
   }

   public FormattedBigDecimalConversion() {
   }

   protected void configureFormatter(DecimalFormat formatter) {
      formatter.setParseBigDecimal(true);
   }
}
