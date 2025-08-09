package org.apache.commons.lang3.mutable;

public class MutableDouble extends Number implements Comparable, Mutable {
   private static final long serialVersionUID = 1587163916L;
   private double value;

   public MutableDouble() {
   }

   public MutableDouble(double value) {
      this.value = value;
   }

   public MutableDouble(Number value) {
      this.value = value.doubleValue();
   }

   public MutableDouble(String value) {
      this.value = Double.parseDouble(value);
   }

   public void add(double operand) {
      this.value += operand;
   }

   public void add(Number operand) {
      this.value += operand.doubleValue();
   }

   public double addAndGet(double operand) {
      this.value += operand;
      return this.value;
   }

   public double addAndGet(Number operand) {
      this.value += operand.doubleValue();
      return this.value;
   }

   public int compareTo(MutableDouble other) {
      return Double.compare(this.value, other.value);
   }

   public void decrement() {
      --this.value;
   }

   public double decrementAndGet() {
      --this.value;
      return this.value;
   }

   public double doubleValue() {
      return this.value;
   }

   public boolean equals(Object obj) {
      return obj instanceof MutableDouble && Double.doubleToLongBits(((MutableDouble)obj).value) == Double.doubleToLongBits(this.value);
   }

   public float floatValue() {
      return (float)this.value;
   }

   public double getAndAdd(double operand) {
      double last = this.value;
      this.value += operand;
      return last;
   }

   public double getAndAdd(Number operand) {
      double last = this.value;
      this.value += operand.doubleValue();
      return last;
   }

   public double getAndDecrement() {
      double last = (double)(this.value--);
      return last;
   }

   public double getAndIncrement() {
      double last = (double)(this.value++);
      return last;
   }

   public Double getValue() {
      return this.value;
   }

   public int hashCode() {
      long bits = Double.doubleToLongBits(this.value);
      return (int)(bits ^ bits >>> 32);
   }

   public void increment() {
      ++this.value;
   }

   public double incrementAndGet() {
      ++this.value;
      return this.value;
   }

   public int intValue() {
      return (int)this.value;
   }

   public boolean isInfinite() {
      return Double.isInfinite(this.value);
   }

   public boolean isNaN() {
      return Double.isNaN(this.value);
   }

   public long longValue() {
      return (long)this.value;
   }

   public void setValue(double value) {
      this.value = value;
   }

   public void setValue(Number value) {
      this.value = value.doubleValue();
   }

   public void subtract(double operand) {
      this.value -= operand;
   }

   public void subtract(Number operand) {
      this.value -= operand.doubleValue();
   }

   public Double toDouble() {
      return this.doubleValue();
   }

   public String toString() {
      return String.valueOf(this.value);
   }
}
