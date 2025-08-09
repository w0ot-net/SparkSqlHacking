package com.ibm.icu.impl;

import com.ibm.icu.text.ConstrainedFieldPosition;
import com.ibm.icu.text.ListFormatter;
import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.text.UFormat;
import com.ibm.icu.text.UnicodeSet;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.FieldPosition;
import java.text.Format;

public class FormattedValueStringBuilderImpl {
   public static int findSpan(FormattedStringBuilder self, Object value) {
      for(int i = self.zero; i < self.zero + self.length; ++i) {
         if (self.fields[i] instanceof SpanFieldPlaceholder && ((SpanFieldPlaceholder)self.fields[i]).value.equals(value)) {
            return i - self.zero;
         }
      }

      return -1;
   }

   public static void applySpanRange(FormattedStringBuilder self, UFormat.SpanField spanField, Object value, int start, int end) {
      for(int i = start + self.zero; i < end + self.zero; ++i) {
         Object oldField = self.fields[i];
         SpanFieldPlaceholder newField = new SpanFieldPlaceholder();
         newField.spanField = spanField;
         newField.normalField = (Format.Field)oldField;
         newField.value = value;
         newField.start = start;
         newField.length = end - start;
         self.fields[i] = newField;
      }

   }

   public static boolean nextFieldPosition(FormattedStringBuilder self, FieldPosition fp) {
      Format.Field rawField = fp.getFieldAttribute();
      if (rawField == null) {
         if (fp.getField() == 0) {
            rawField = NumberFormat.Field.INTEGER;
         } else {
            if (fp.getField() != 1) {
               return false;
            }

            rawField = NumberFormat.Field.FRACTION;
         }
      }

      if (!(rawField instanceof NumberFormat.Field)) {
         throw new IllegalArgumentException("You must pass an instance of com.ibm.icu.text.NumberFormat.Field as your FieldPosition attribute.  You passed: " + rawField.getClass().toString());
      } else {
         ConstrainedFieldPosition cfpos = new ConstrainedFieldPosition();
         cfpos.constrainField(rawField);
         cfpos.setState(rawField, (Object)null, fp.getBeginIndex(), fp.getEndIndex());
         if (nextPosition(self, cfpos, (Format.Field)null)) {
            fp.setBeginIndex(cfpos.getStart());
            fp.setEndIndex(cfpos.getLimit());
            return true;
         } else {
            if (rawField == NumberFormat.Field.FRACTION && fp.getEndIndex() == 0) {
               boolean inside = false;

               int i;
               for(i = self.zero; i < self.zero + self.length; ++i) {
                  if (!isIntOrGroup(self.fields[i]) && self.fields[i] != NumberFormat.Field.DECIMAL_SEPARATOR) {
                     if (inside) {
                        break;
                     }
                  } else {
                     inside = true;
                  }
               }

               fp.setBeginIndex(i - self.zero);
               fp.setEndIndex(i - self.zero);
            }

            return false;
         }
      }
   }

   public static AttributedCharacterIterator toCharacterIterator(FormattedStringBuilder self, Format.Field numericField) {
      ConstrainedFieldPosition cfpos = new ConstrainedFieldPosition();

      AttributedString as;
      Object value;
      for(as = new AttributedString(self.toString()); nextPosition(self, cfpos, numericField); as.addAttribute(cfpos.getField(), value, cfpos.getStart(), cfpos.getLimit())) {
         value = cfpos.getFieldValue();
         if (value == null) {
            value = cfpos.getField();
         }
      }

      return as.getIterator();
   }

   public static boolean nextPosition(FormattedStringBuilder self, ConstrainedFieldPosition cfpos, Format.Field numericField) {
      int fieldStart = -1;
      Object currField = null;
      boolean prevIsSpan = false;
      if (cfpos.getLimit() > 0) {
         prevIsSpan = cfpos.getField() instanceof UFormat.SpanField && cfpos.getStart() < cfpos.getLimit();
      }

      boolean prevIsNumeric = false;
      if (numericField != null) {
         prevIsNumeric = cfpos.getField() == numericField;
      }

      boolean prevIsInteger = cfpos.getField() == NumberFormat.Field.INTEGER;

      for(int i = self.zero + cfpos.getLimit(); i <= self.zero + self.length; ++i) {
         Object _field = i < self.zero + self.length ? self.fields[i] : FormattedValueStringBuilderImpl.NullField.END;
         if (currField != null) {
            if (currField != _field) {
               int end = i - self.zero;
               if (isTrimmable(currField)) {
                  end = trimBack(self, end);
               }

               if (end > fieldStart) {
                  int start = fieldStart;
                  if (isTrimmable(currField)) {
                     start = trimFront(self, fieldStart);
                  }

                  cfpos.setState((Format.Field)currField, (Object)null, start, end);
                  return true;
               }

               fieldStart = -1;
               currField = null;
               --i;
            }
         } else {
            if (i > self.zero && prevIsSpan) {
               assert self.fields[i - 1] instanceof SpanFieldPlaceholder;

               SpanFieldPlaceholder ph = (SpanFieldPlaceholder)self.fields[i - 1];
               if (ph.normalField == ListFormatter.Field.ELEMENT) {
                  if (cfpos.matchesField(ListFormatter.Field.ELEMENT, (Object)null)) {
                     fieldStart = i - self.zero - ph.length;
                     int end = fieldStart + ph.length;
                     cfpos.setState(ListFormatter.Field.ELEMENT, (Object)null, fieldStart, end);
                     return true;
                  }
               } else {
                  i -= ph.length;

                  assert i >= self.zero;

                  _field = ((SpanFieldPlaceholder)self.fields[i]).normalField;
               }
            }

            if (cfpos.matchesField(NumberFormat.Field.INTEGER, (Object)null) && i > self.zero && !prevIsInteger && !prevIsNumeric && isIntOrGroup(self.fields[i - 1]) && !isIntOrGroup(_field)) {
               int j;
               for(j = i - 1; j >= self.zero && isIntOrGroup(self.fields[j]); --j) {
               }

               cfpos.setState(NumberFormat.Field.INTEGER, (Object)null, j - self.zero + 1, i - self.zero);
               return true;
            }

            if (numericField != null && cfpos.matchesField(numericField, (Object)null) && i > self.zero && !prevIsNumeric && isNumericField(self.fields[i - 1]) && !isNumericField(_field)) {
               int j;
               for(j = i - 1; j >= self.zero && isNumericField(self.fields[j]); --j) {
               }

               cfpos.setState(numericField, (Object)null, j - self.zero + 1, i - self.zero);
               return true;
            }

            SpanFieldPlaceholder ph = null;
            if (_field instanceof SpanFieldPlaceholder) {
               ph = (SpanFieldPlaceholder)_field;
               _field = ph.normalField;
            }

            if (ph != null && (ph.start == -1 || ph.start == i - self.zero)) {
               if (cfpos.matchesField(ph.spanField, ph.value)) {
                  fieldStart = i - self.zero;
                  int end = fieldStart + ph.length;
                  cfpos.setState(ph.spanField, ph.value, fieldStart, end);
                  return true;
               }

               if (ph.normalField == ListFormatter.Field.ELEMENT) {
                  if (cfpos.matchesField(ListFormatter.Field.ELEMENT, (Object)null)) {
                     fieldStart = i - self.zero;
                     int end = fieldStart + ph.length;
                     cfpos.setState(ListFormatter.Field.ELEMENT, (Object)null, fieldStart, end);
                     return true;
                  }

                  i += ph.length - 1;
               }
            } else if (_field == NumberFormat.Field.INTEGER) {
               _field = null;
            } else if (_field != null && _field != FormattedValueStringBuilderImpl.NullField.END && cfpos.matchesField((Format.Field)_field, (Object)null)) {
               fieldStart = i - self.zero;
               currField = _field;
            }

            prevIsSpan = false;
            prevIsNumeric = false;
            prevIsInteger = false;
         }
      }

      assert currField == null;

      cfpos.setState(cfpos.getField(), cfpos.getFieldValue(), self.length, self.length);
      return false;
   }

   private static boolean isIntOrGroup(Object field) {
      Object var1 = FormattedStringBuilder.unwrapField(field);
      return var1 == NumberFormat.Field.INTEGER || var1 == NumberFormat.Field.GROUPING_SEPARATOR;
   }

   private static boolean isNumericField(Object field) {
      Object var1 = FormattedStringBuilder.unwrapField(field);
      return var1 == null || NumberFormat.Field.class.isAssignableFrom(var1.getClass());
   }

   private static boolean isTrimmable(Object field) {
      return field != NumberFormat.Field.GROUPING_SEPARATOR && !(field instanceof ListFormatter.Field);
   }

   private static int trimBack(FormattedStringBuilder self, int limit) {
      return StaticUnicodeSets.get(StaticUnicodeSets.Key.DEFAULT_IGNORABLES).spanBack(self, limit, UnicodeSet.SpanCondition.CONTAINED);
   }

   private static int trimFront(FormattedStringBuilder self, int start) {
      return StaticUnicodeSets.get(StaticUnicodeSets.Key.DEFAULT_IGNORABLES).span(self, start, UnicodeSet.SpanCondition.CONTAINED);
   }

   public static class SpanFieldPlaceholder implements FormattedStringBuilder.FieldWrapper {
      public UFormat.SpanField spanField;
      public Format.Field normalField;
      public Object value;
      public int start;
      public int length;

      public Format.Field unwrap() {
         return this.normalField;
      }
   }

   static class NullField extends Format.Field {
      private static final long serialVersionUID = 1L;
      static final NullField END = new NullField("end");

      private NullField(String name) {
         super(name);
      }
   }
}
