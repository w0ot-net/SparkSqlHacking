package org.apache.commons.math3.complex;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathParseException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.CompositeFormat;

public class ComplexFormat {
   private static final String DEFAULT_IMAGINARY_CHARACTER = "i";
   private final String imaginaryCharacter;
   private final NumberFormat imaginaryFormat;
   private final NumberFormat realFormat;

   public ComplexFormat() {
      this.imaginaryCharacter = "i";
      this.imaginaryFormat = CompositeFormat.getDefaultNumberFormat();
      this.realFormat = this.imaginaryFormat;
   }

   public ComplexFormat(NumberFormat format) throws NullArgumentException {
      if (format == null) {
         throw new NullArgumentException(LocalizedFormats.IMAGINARY_FORMAT, new Object[0]);
      } else {
         this.imaginaryCharacter = "i";
         this.imaginaryFormat = format;
         this.realFormat = format;
      }
   }

   public ComplexFormat(NumberFormat realFormat, NumberFormat imaginaryFormat) throws NullArgumentException {
      if (imaginaryFormat == null) {
         throw new NullArgumentException(LocalizedFormats.IMAGINARY_FORMAT, new Object[0]);
      } else if (realFormat == null) {
         throw new NullArgumentException(LocalizedFormats.REAL_FORMAT, new Object[0]);
      } else {
         this.imaginaryCharacter = "i";
         this.imaginaryFormat = imaginaryFormat;
         this.realFormat = realFormat;
      }
   }

   public ComplexFormat(String imaginaryCharacter) throws NullArgumentException, NoDataException {
      this(imaginaryCharacter, CompositeFormat.getDefaultNumberFormat());
   }

   public ComplexFormat(String imaginaryCharacter, NumberFormat format) throws NullArgumentException, NoDataException {
      this(imaginaryCharacter, format, format);
   }

   public ComplexFormat(String imaginaryCharacter, NumberFormat realFormat, NumberFormat imaginaryFormat) throws NullArgumentException, NoDataException {
      if (imaginaryCharacter == null) {
         throw new NullArgumentException();
      } else if (imaginaryCharacter.length() == 0) {
         throw new NoDataException();
      } else if (imaginaryFormat == null) {
         throw new NullArgumentException(LocalizedFormats.IMAGINARY_FORMAT, new Object[0]);
      } else if (realFormat == null) {
         throw new NullArgumentException(LocalizedFormats.REAL_FORMAT, new Object[0]);
      } else {
         this.imaginaryCharacter = imaginaryCharacter;
         this.imaginaryFormat = imaginaryFormat;
         this.realFormat = realFormat;
      }
   }

   public static Locale[] getAvailableLocales() {
      return NumberFormat.getAvailableLocales();
   }

   public String format(Complex c) {
      return this.format(c, new StringBuffer(), new FieldPosition(0)).toString();
   }

   public String format(Double c) {
      return this.format(new Complex(c, (double)0.0F), new StringBuffer(), new FieldPosition(0)).toString();
   }

   public StringBuffer format(Complex complex, StringBuffer toAppendTo, FieldPosition pos) {
      pos.setBeginIndex(0);
      pos.setEndIndex(0);
      double re = complex.getReal();
      CompositeFormat.formatDouble(re, this.getRealFormat(), toAppendTo, pos);
      double im = complex.getImaginary();
      if (im < (double)0.0F) {
         toAppendTo.append(" - ");
         StringBuffer imAppendTo = this.formatImaginary(-im, new StringBuffer(), pos);
         toAppendTo.append(imAppendTo);
         toAppendTo.append(this.getImaginaryCharacter());
      } else if (im > (double)0.0F || Double.isNaN(im)) {
         toAppendTo.append(" + ");
         StringBuffer imAppendTo = this.formatImaginary(im, new StringBuffer(), pos);
         toAppendTo.append(imAppendTo);
         toAppendTo.append(this.getImaginaryCharacter());
      }

      return toAppendTo;
   }

   private StringBuffer formatImaginary(double absIm, StringBuffer toAppendTo, FieldPosition pos) {
      pos.setBeginIndex(0);
      pos.setEndIndex(0);
      CompositeFormat.formatDouble(absIm, this.getImaginaryFormat(), toAppendTo, pos);
      if (toAppendTo.toString().equals("1")) {
         toAppendTo.setLength(0);
      }

      return toAppendTo;
   }

   public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) throws MathIllegalArgumentException {
      StringBuffer ret = null;
      if (obj instanceof Complex) {
         ret = this.format((Complex)obj, toAppendTo, pos);
      } else {
         if (!(obj instanceof Number)) {
            throw new MathIllegalArgumentException(LocalizedFormats.CANNOT_FORMAT_INSTANCE_AS_COMPLEX, new Object[]{obj.getClass().getName()});
         }

         ret = this.format(new Complex(((Number)obj).doubleValue(), (double)0.0F), toAppendTo, pos);
      }

      return ret;
   }

   public String getImaginaryCharacter() {
      return this.imaginaryCharacter;
   }

   public NumberFormat getImaginaryFormat() {
      return this.imaginaryFormat;
   }

   public static ComplexFormat getInstance() {
      return getInstance(Locale.getDefault());
   }

   public static ComplexFormat getInstance(Locale locale) {
      NumberFormat f = CompositeFormat.getDefaultNumberFormat(locale);
      return new ComplexFormat(f);
   }

   public static ComplexFormat getInstance(String imaginaryCharacter, Locale locale) throws NullArgumentException, NoDataException {
      NumberFormat f = CompositeFormat.getDefaultNumberFormat(locale);
      return new ComplexFormat(imaginaryCharacter, f);
   }

   public NumberFormat getRealFormat() {
      return this.realFormat;
   }

   public Complex parse(String source) throws MathParseException {
      ParsePosition parsePosition = new ParsePosition(0);
      Complex result = this.parse(source, parsePosition);
      if (parsePosition.getIndex() == 0) {
         throw new MathParseException(source, parsePosition.getErrorIndex(), Complex.class);
      } else {
         return result;
      }
   }

   public Complex parse(String source, ParsePosition pos) {
      int initialIndex = pos.getIndex();
      CompositeFormat.parseAndIgnoreWhitespace(source, pos);
      Number re = CompositeFormat.parseNumber(source, this.getRealFormat(), pos);
      if (re == null) {
         pos.setIndex(initialIndex);
         return null;
      } else {
         int startIndex = pos.getIndex();
         char c = CompositeFormat.parseNextCharacter(source, pos);
         int sign = 0;
         switch (c) {
            case '\u0000':
               return new Complex(re.doubleValue(), (double)0.0F);
            case '+':
               sign = 1;
               break;
            case '-':
               sign = -1;
               break;
            default:
               pos.setIndex(initialIndex);
               pos.setErrorIndex(startIndex);
               return null;
         }

         CompositeFormat.parseAndIgnoreWhitespace(source, pos);
         Number im = CompositeFormat.parseNumber(source, this.getRealFormat(), pos);
         if (im == null) {
            pos.setIndex(initialIndex);
            return null;
         } else {
            return !CompositeFormat.parseFixedstring(source, this.getImaginaryCharacter(), pos) ? null : new Complex(re.doubleValue(), im.doubleValue() * (double)sign);
         }
      }
   }
}
