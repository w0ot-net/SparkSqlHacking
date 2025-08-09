package org.apache.parquet.filter2.predicate;

public abstract class UserDefinedPredicate {
   public boolean acceptsNullValue() {
      try {
         return this.keep((Comparable)null);
      } catch (NullPointerException var2) {
         return false;
      }
   }

   public abstract boolean keep(Comparable var1);

   public abstract boolean canDrop(Statistics var1);

   public abstract boolean inverseCanDrop(Statistics var1);
}
