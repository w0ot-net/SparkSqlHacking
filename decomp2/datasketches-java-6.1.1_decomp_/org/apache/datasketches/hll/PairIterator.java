package org.apache.datasketches.hll;

abstract class PairIterator {
   String getHeader() {
      return String.format("%10s%10s%10s%6s", "Index", "Key", "Slot", "Value");
   }

   abstract int getIndex();

   abstract int getKey();

   abstract int getPair();

   abstract int getSlot();

   String getString() {
      int index = this.getIndex();
      int key = this.getKey();
      int slot = this.getSlot();
      int value = this.getValue();
      return String.format("%10d%10d%10d%6d", index, key, slot, value);
   }

   abstract int getValue();

   abstract boolean nextAll();

   abstract boolean nextValid();
}
