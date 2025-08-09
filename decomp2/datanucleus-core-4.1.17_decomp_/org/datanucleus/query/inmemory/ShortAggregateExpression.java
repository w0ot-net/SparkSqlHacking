package org.datanucleus.query.inmemory;

public class ShortAggregateExpression extends NumericAggregateExpression {
   public ShortAggregateExpression(Short value) {
      super(value);
   }

   public Object add(Object obj) {
      return obj instanceof Short ? (short)((Short)obj + (Short)this.value) : super.add(obj);
   }

   public Object sub(Object obj) {
      return super.sub(obj);
   }

   public Object div(Object obj) {
      return obj instanceof Short ? (short)((Short)this.value / (Short)obj) : super.add(obj);
   }

   public Boolean gt(Object obj) {
      if (obj instanceof Short) {
         return (Short)this.value > (Short)obj ? Boolean.TRUE : Boolean.FALSE;
      } else {
         return super.gt(obj);
      }
   }

   public Boolean lt(Object obj) {
      if (obj instanceof Short) {
         return (Short)this.value < (Short)obj ? Boolean.TRUE : Boolean.FALSE;
      } else {
         return super.lt(obj);
      }
   }

   public Boolean eq(Object obj) {
      if (obj instanceof Short) {
         return (Short)this.value == (Short)obj ? Boolean.TRUE : Boolean.FALSE;
      } else {
         return super.eq(obj);
      }
   }
}
