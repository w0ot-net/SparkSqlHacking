package org.datanucleus.query.inmemory;

public class FloatAggregateExpression extends NumericAggregateExpression {
   public FloatAggregateExpression(Float value) {
      super(value);
   }

   public Object add(Object obj) {
      return obj instanceof Float ? new Float((Float)obj + (Float)this.value) : super.add(obj);
   }

   public Object sub(Object obj) {
      return super.sub(obj);
   }

   public Object div(Object obj) {
      return obj instanceof Float ? new Float((Float)this.value / (Float)obj) : super.add(obj);
   }

   public Boolean gt(Object obj) {
      if (obj instanceof Float) {
         return (Float)this.value > (Float)obj ? Boolean.TRUE : Boolean.FALSE;
      } else {
         return super.gt(obj);
      }
   }

   public Boolean lt(Object obj) {
      if (obj instanceof Float) {
         return (Float)this.value < (Float)obj ? Boolean.TRUE : Boolean.FALSE;
      } else {
         return super.lt(obj);
      }
   }

   public Boolean eq(Object obj) {
      if (obj instanceof Float) {
         return (Float)this.value == (Float)obj ? Boolean.TRUE : Boolean.FALSE;
      } else {
         return super.eq(obj);
      }
   }
}
