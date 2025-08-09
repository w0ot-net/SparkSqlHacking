package org.apache.hadoop.hive.common.type;

import org.apache.commons.lang3.StringUtils;

public class HiveChar extends HiveBaseChar implements Comparable {
   public static final int MAX_CHAR_LENGTH = 255;

   public HiveChar() {
   }

   public HiveChar(String val, int len) {
      this.setValue(val, len);
   }

   public HiveChar(HiveChar hc, int len) {
      this.setValue(hc.value, len);
   }

   public void setValue(String val, int len) {
      super.setValue((String)HiveBaseChar.getPaddedValue(val, len), -1);
   }

   public void setValue(String val) {
      this.setValue(val, -1);
   }

   public String getStrippedValue() {
      return StringUtils.stripEnd(this.value, " ");
   }

   public String getPaddedValue() {
      return this.value;
   }

   public int getCharacterLength() {
      String strippedValue = this.getStrippedValue();
      return strippedValue.codePointCount(0, strippedValue.length());
   }

   public String toString() {
      return this.getPaddedValue();
   }

   public int compareTo(HiveChar rhs) {
      return rhs == this ? 0 : this.getStrippedValue().compareTo(rhs.getStrippedValue());
   }

   public boolean equals(Object rhs) {
      if (rhs == this) {
         return true;
      } else {
         return rhs != null && rhs.getClass() == this.getClass() ? this.getStrippedValue().equals(((HiveChar)rhs).getStrippedValue()) : false;
      }
   }

   public int hashCode() {
      return this.getStrippedValue().hashCode();
   }
}
