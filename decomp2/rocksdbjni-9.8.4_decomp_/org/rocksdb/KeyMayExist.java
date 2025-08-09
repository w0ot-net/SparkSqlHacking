package org.rocksdb;

import java.util.Objects;

public class KeyMayExist {
   public final KeyMayExistEnum exists;
   public final int valueLength;

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         KeyMayExist var2 = (KeyMayExist)var1;
         return this.valueLength == var2.valueLength && this.exists == var2.exists;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.exists, this.valueLength});
   }

   public KeyMayExist(KeyMayExistEnum var1, int var2) {
      this.exists = var1;
      this.valueLength = var2;
   }

   public static enum KeyMayExistEnum {
      kNotExist,
      kExistsWithoutValue,
      kExistsWithValue;
   }
}
