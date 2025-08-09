package org.apache.hadoop.hive.common.type;

import org.apache.commons.lang3.StringUtils;

public abstract class HiveBaseChar {
   protected String value;

   protected HiveBaseChar() {
   }

   public void setValue(String val, int maxLength) {
      this.value = enforceMaxLength(val, maxLength);
   }

   public void setValue(HiveBaseChar val, int maxLength) {
      this.setValue(val.value, maxLength);
   }

   public static String enforceMaxLength(String val, int maxLength) {
      if (val == null) {
         return null;
      } else {
         String value = val;
         if (maxLength > 0) {
            int valLength = val.codePointCount(0, val.length());
            if (valLength > maxLength) {
               value = val.substring(0, val.offsetByCodePoints(0, maxLength));
            }
         }

         return value;
      }
   }

   public static String getPaddedValue(String val, int maxLength) {
      if (val == null) {
         return null;
      } else if (maxLength < 0) {
         return val;
      } else {
         int valLength = val.codePointCount(0, val.length());
         if (valLength > maxLength) {
            return enforceMaxLength(val, maxLength);
         } else {
            if (maxLength > valLength) {
               int padLength = val.length() + (maxLength - valLength);
               val = StringUtils.rightPad(val, padLength);
            }

            return val;
         }
      }
   }

   public String getValue() {
      return this.value;
   }

   public int getCharacterLength() {
      return this.value.codePointCount(0, this.value.length());
   }

   public int hashCode() {
      return this.getValue().hashCode();
   }

   public String toString() {
      return this.getValue();
   }
}
