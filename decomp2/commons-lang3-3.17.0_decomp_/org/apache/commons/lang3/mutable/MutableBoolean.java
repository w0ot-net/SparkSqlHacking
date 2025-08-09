package org.apache.commons.lang3.mutable;

import java.io.Serializable;
import org.apache.commons.lang3.BooleanUtils;

public class MutableBoolean implements Mutable, Serializable, Comparable {
   private static final long serialVersionUID = -4830728138360036487L;
   private boolean value;

   public MutableBoolean() {
   }

   public MutableBoolean(boolean value) {
      this.value = value;
   }

   public MutableBoolean(Boolean value) {
      this.value = value;
   }

   public boolean booleanValue() {
      return this.value;
   }

   public int compareTo(MutableBoolean other) {
      return BooleanUtils.compare(this.value, other.value);
   }

   public boolean equals(Object obj) {
      if (obj instanceof MutableBoolean) {
         return this.value == ((MutableBoolean)obj).booleanValue();
      } else {
         return false;
      }
   }

   public Boolean getValue() {
      return this.value;
   }

   public int hashCode() {
      return this.value ? Boolean.TRUE.hashCode() : Boolean.FALSE.hashCode();
   }

   public boolean isFalse() {
      return !this.value;
   }

   public boolean isTrue() {
      return this.value;
   }

   public void setFalse() {
      this.value = false;
   }

   public void setTrue() {
      this.value = true;
   }

   public void setValue(boolean value) {
      this.value = value;
   }

   public void setValue(Boolean value) {
      this.value = value;
   }

   public Boolean toBoolean() {
      return this.booleanValue();
   }

   public String toString() {
      return String.valueOf(this.value);
   }
}
