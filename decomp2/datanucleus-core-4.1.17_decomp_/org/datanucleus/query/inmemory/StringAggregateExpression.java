package org.datanucleus.query.inmemory;

public class StringAggregateExpression extends AggregateExpression {
   String value;

   public StringAggregateExpression(String value) {
      this.value = value;
   }

   public Boolean gt(Object obj) {
      if (obj instanceof String) {
         return this.value.compareTo((String)obj) > 0 ? Boolean.TRUE : Boolean.FALSE;
      } else {
         return super.gt(obj);
      }
   }

   public Boolean lt(Object obj) {
      if (obj instanceof String) {
         return this.value.compareTo((String)obj) < 0 ? Boolean.TRUE : Boolean.FALSE;
      } else {
         return super.lt(obj);
      }
   }

   public Boolean eq(Object obj) {
      if (obj instanceof String) {
         return this.value.equals(obj) ? Boolean.TRUE : Boolean.FALSE;
      } else {
         return super.eq(obj);
      }
   }
}
