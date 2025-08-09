package org.datanucleus.query.inmemory;

public class IntegerAggregateExpression extends NumericAggregateExpression {
   public IntegerAggregateExpression(Integer value) {
      super(value);
   }

   public Object add(Object obj) {
      return obj instanceof Integer ? (Integer)obj + (Integer)this.value : super.add(obj);
   }

   public Object sub(Object obj) {
      return super.sub(obj);
   }

   public Object div(Object obj) {
      return obj instanceof Integer ? (Integer)this.value / (Integer)obj : super.add(obj);
   }

   public Boolean gt(Object obj) {
      if (obj instanceof Integer) {
         return (Integer)this.value > (Integer)obj ? Boolean.TRUE : Boolean.FALSE;
      } else {
         return super.gt(obj);
      }
   }

   public Boolean lt(Object obj) {
      if (obj instanceof Integer) {
         return (Integer)this.value < (Integer)obj ? Boolean.TRUE : Boolean.FALSE;
      } else {
         return super.lt(obj);
      }
   }

   public Boolean eq(Object obj) {
      if (obj instanceof Integer) {
         return (Integer)this.value == (Integer)obj ? Boolean.TRUE : Boolean.FALSE;
      } else {
         return super.eq(obj);
      }
   }
}
