package org.datanucleus.query.inmemory;

import java.math.BigInteger;

public class BigIntegerAggregateExpression extends NumericAggregateExpression {
   public BigIntegerAggregateExpression(BigInteger value) {
      super(value);
   }

   public Object add(Object obj) {
      return obj instanceof BigInteger ? BigInteger.valueOf(((BigInteger)obj).longValue() + ((BigInteger)this.value).longValue()) : super.add(obj);
   }

   public Object sub(Object obj) {
      return super.sub(obj);
   }

   public Object div(Object obj) {
      return obj instanceof BigInteger ? BigInteger.valueOf(((BigInteger)this.value).longValue() / ((BigInteger)obj).longValue()) : super.add(obj);
   }

   public Boolean gt(Object obj) {
      return obj instanceof BigInteger ? ((BigInteger)this.value).longValue() > ((BigInteger)obj).longValue() : super.gt(obj);
   }

   public Boolean lt(Object obj) {
      return obj instanceof BigInteger ? ((BigInteger)this.value).longValue() < ((BigInteger)obj).longValue() : super.lt(obj);
   }

   public Boolean eq(Object obj) {
      return obj instanceof BigInteger ? ((BigInteger)this.value).longValue() == ((BigInteger)obj).longValue() : super.eq(obj);
   }
}
