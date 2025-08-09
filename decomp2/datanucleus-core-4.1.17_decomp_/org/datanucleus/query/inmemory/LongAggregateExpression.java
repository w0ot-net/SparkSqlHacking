package org.datanucleus.query.inmemory;

public class LongAggregateExpression extends NumericAggregateExpression {
   public LongAggregateExpression(Long value) {
      super(value);
   }

   public Object add(Object obj) {
      return obj instanceof Long ? (Long)obj + (Long)this.value : super.add(obj);
   }

   public Object sub(Object obj) {
      return super.sub(obj);
   }

   public Object div(Object obj) {
      return obj instanceof Long ? (Long)this.value / (Long)obj : super.add(obj);
   }

   public Boolean gt(Object obj) {
      if (obj instanceof Long) {
         return (Long)this.value > (Long)obj ? Boolean.TRUE : Boolean.FALSE;
      } else {
         return super.gt(obj);
      }
   }

   public Boolean lt(Object obj) {
      if (obj instanceof Long) {
         return (Long)this.value < (Long)obj ? Boolean.TRUE : Boolean.FALSE;
      } else {
         return super.lt(obj);
      }
   }

   public Boolean eq(Object obj) {
      if (obj instanceof Long) {
         return (Long)this.value == (Long)obj ? Boolean.TRUE : Boolean.FALSE;
      } else {
         return super.eq(obj);
      }
   }
}
