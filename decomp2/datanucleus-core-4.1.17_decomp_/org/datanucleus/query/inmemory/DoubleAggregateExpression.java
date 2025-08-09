package org.datanucleus.query.inmemory;

public class DoubleAggregateExpression extends NumericAggregateExpression {
   public DoubleAggregateExpression(Double value) {
      super(value);
   }

   public Object add(Object obj) {
      return obj instanceof Double ? new Double((Double)obj + (Double)this.value) : super.add(obj);
   }

   public Object sub(Object obj) {
      return super.sub(obj);
   }

   public Object div(Object obj) {
      return obj instanceof Double ? new Double((Double)this.value / (Double)obj) : super.add(obj);
   }

   public Boolean gt(Object obj) {
      if (obj instanceof Double) {
         return (Double)this.value > (Double)obj ? Boolean.TRUE : Boolean.FALSE;
      } else {
         return super.gt(obj);
      }
   }

   public Boolean lt(Object obj) {
      if (obj instanceof Double) {
         return (Double)this.value < (Double)obj ? Boolean.TRUE : Boolean.FALSE;
      } else {
         return super.lt(obj);
      }
   }

   public Boolean eq(Object obj) {
      if (obj instanceof Double) {
         return (Double)this.value == (Double)obj ? Boolean.TRUE : Boolean.FALSE;
      } else {
         return super.eq(obj);
      }
   }
}
