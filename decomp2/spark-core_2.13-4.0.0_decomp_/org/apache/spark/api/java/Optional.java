package org.apache.spark.api.java;

import java.io.Serializable;
import java.util.Objects;
import org.sparkproject.guava.base.Preconditions;

public final class Optional implements Serializable {
   private static final Optional EMPTY = new Optional();
   private final Object value;

   private Optional() {
      this.value = null;
   }

   private Optional(Object value) {
      Preconditions.checkNotNull(value);
      this.value = value;
   }

   public static Optional empty() {
      Optional<T> t = EMPTY;
      return t;
   }

   public static Optional of(Object value) {
      return new Optional(value);
   }

   public static Optional ofNullable(Object value) {
      return value == null ? empty() : of(value);
   }

   public Object get() {
      Preconditions.checkNotNull(this.value);
      return this.value;
   }

   public Object orElse(Object other) {
      return this.value != null ? this.value : other;
   }

   public boolean isPresent() {
      return this.value != null;
   }

   public static Optional absent() {
      return empty();
   }

   public static Optional fromNullable(Object value) {
      return ofNullable(value);
   }

   public Object or(Object other) {
      return this.value != null ? this.value : other;
   }

   public Object orNull() {
      return this.value;
   }

   public boolean equals(Object obj) {
      if (obj instanceof Optional other) {
         return Objects.equals(this.value, other.value);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.value == null ? 0 : this.value.hashCode();
   }

   public String toString() {
      return this.value == null ? "Optional.empty" : String.format("Optional[%s]", this.value);
   }
}
