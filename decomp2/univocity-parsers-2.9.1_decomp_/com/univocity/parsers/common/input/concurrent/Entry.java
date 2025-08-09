package com.univocity.parsers.common.input.concurrent;

class Entry {
   final Object entry;
   final int index;

   Entry(Object entry, int index) {
      this.entry = entry;
      this.index = index;
   }

   public Object get() {
      return this.entry;
   }
}
