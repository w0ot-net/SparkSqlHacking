package org.apache.commons.lang3.tuple;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.function.FailableBiConsumer;
import org.apache.commons.lang3.function.FailableBiFunction;

public abstract class Pair implements Map.Entry, Comparable, Serializable {
   private static final long serialVersionUID = 4954918890077093841L;
   public static final Pair[] EMPTY_ARRAY = new Pair[0];

   public static Pair[] emptyArray() {
      return EMPTY_ARRAY;
   }

   public static Pair of(Object left, Object right) {
      return ImmutablePair.of(left, right);
   }

   public static Pair of(Map.Entry pair) {
      return ImmutablePair.of(pair);
   }

   public static Pair ofNonNull(Object left, Object right) {
      return ImmutablePair.ofNonNull(left, right);
   }

   public void accept(FailableBiConsumer consumer) throws Throwable {
      consumer.accept(this.getKey(), this.getValue());
   }

   public Object apply(FailableBiFunction function) throws Throwable {
      return function.apply(this.getKey(), this.getValue());
   }

   public int compareTo(Pair other) {
      return (new CompareToBuilder()).append(this.getLeft(), other.getLeft()).append(this.getRight(), other.getRight()).toComparison();
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof Map.Entry)) {
         return false;
      } else {
         Map.Entry<?, ?> other = (Map.Entry)obj;
         return Objects.equals(this.getKey(), other.getKey()) && Objects.equals(this.getValue(), other.getValue());
      }
   }

   public final Object getKey() {
      return this.getLeft();
   }

   public abstract Object getLeft();

   public abstract Object getRight();

   public Object getValue() {
      return this.getRight();
   }

   public int hashCode() {
      return Objects.hashCode(this.getKey()) ^ Objects.hashCode(this.getValue());
   }

   public String toString() {
      return "(" + this.getLeft() + ',' + this.getRight() + ')';
   }

   public String toString(String format) {
      return String.format(format, this.getLeft(), this.getRight());
   }
}
