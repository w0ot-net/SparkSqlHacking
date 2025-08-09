package org.apache.hadoop.hive.common;

public class ObjectPair {
   private Object first;
   private Object second;

   public ObjectPair() {
   }

   public static ObjectPair create(Object f, Object s) {
      return new ObjectPair(f, s);
   }

   public ObjectPair(Object first, Object second) {
      this.first = first;
      this.second = second;
   }

   public Object getFirst() {
      return this.first;
   }

   public void setFirst(Object first) {
      this.first = first;
   }

   public Object getSecond() {
      return this.second;
   }

   public void setSecond(Object second) {
      this.second = second;
   }

   public boolean equals(Object that) {
      if (that == null) {
         return false;
      } else {
         return that instanceof ObjectPair ? this.equals((ObjectPair)that) : false;
      }
   }

   public boolean equals(ObjectPair that) {
      if (that == null) {
         return false;
      } else {
         return this.getFirst().equals(that.getFirst()) && this.getSecond().equals(that.getSecond());
      }
   }

   public int hashCode() {
      return this.first.hashCode() * 31 + this.second.hashCode();
   }

   public String toString() {
      return this.first + ":" + this.second;
   }
}
