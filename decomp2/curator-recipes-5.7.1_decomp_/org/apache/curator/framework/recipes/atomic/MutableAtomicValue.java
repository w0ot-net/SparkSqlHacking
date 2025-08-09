package org.apache.curator.framework.recipes.atomic;

class MutableAtomicValue implements AtomicValue {
   Object preValue;
   Object postValue;
   boolean succeeded;
   AtomicStats stats;

   MutableAtomicValue(Object preValue, Object postValue) {
      this(preValue, postValue, false);
   }

   MutableAtomicValue(Object preValue, Object postValue, boolean succeeded) {
      this.succeeded = false;
      this.stats = new AtomicStats();
      this.preValue = preValue;
      this.postValue = postValue;
      this.succeeded = succeeded;
   }

   public Object preValue() {
      return this.preValue;
   }

   public Object postValue() {
      return this.postValue;
   }

   public boolean succeeded() {
      return this.succeeded;
   }

   public AtomicStats getStats() {
      return this.stats;
   }
}
