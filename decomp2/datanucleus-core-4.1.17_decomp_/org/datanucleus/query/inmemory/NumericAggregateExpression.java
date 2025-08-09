package org.datanucleus.query.inmemory;

public class NumericAggregateExpression extends AggregateExpression {
   Number value;

   public NumericAggregateExpression(Number value) {
      this.value = value;
   }

   public Object add(Object obj) {
      throw new UnsupportedOperationException();
   }

   public Object sub(Object obj) {
      throw new UnsupportedOperationException();
   }

   public Object div(Object obj) {
      throw new UnsupportedOperationException();
   }
}
