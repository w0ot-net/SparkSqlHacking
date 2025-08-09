package org.datanucleus.query.inmemory;

import java.math.BigDecimal;

public class BigDecimalAggregateExpression extends NumericAggregateExpression {
   public BigDecimalAggregateExpression(BigDecimal value) {
      super(value);
   }

   public Object add(Object obj) {
      return obj instanceof BigDecimal ? new BigDecimal(((BigDecimal)obj).doubleValue() + ((BigDecimal)this.value).doubleValue()) : super.add(obj);
   }

   public Object sub(Object obj) {
      return super.sub(obj);
   }

   public Object div(Object obj) {
      return obj instanceof BigDecimal ? new BigDecimal(((BigDecimal)this.value).doubleValue() / ((BigDecimal)obj).doubleValue()) : super.add(obj);
   }

   public Boolean gt(Object obj) {
      if (obj instanceof BigDecimal) {
         return ((BigDecimal)this.value).doubleValue() > ((BigDecimal)obj).doubleValue() ? Boolean.TRUE : Boolean.FALSE;
      } else {
         return super.gt(obj);
      }
   }

   public Boolean lt(Object obj) {
      if (obj instanceof BigDecimal) {
         return ((BigDecimal)this.value).doubleValue() < ((BigDecimal)obj).doubleValue() ? Boolean.TRUE : Boolean.FALSE;
      } else {
         return super.lt(obj);
      }
   }

   public Boolean eq(Object obj) {
      if (obj instanceof BigDecimal) {
         return ((BigDecimal)this.value).doubleValue() == ((BigDecimal)obj).doubleValue() ? Boolean.TRUE : Boolean.FALSE;
      } else {
         return super.eq(obj);
      }
   }
}
