package org.datanucleus.query.expression;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.datanucleus.query.symbol.Symbol;
import org.datanucleus.query.symbol.SymbolTable;

public class Literal extends Expression {
   private static final long serialVersionUID = 1829184296603207264L;
   Object value;

   public Literal(Object value) {
      this.value = value;
   }

   public Object getLiteral() {
      return this.value;
   }

   public void negate() {
      if (this.value != null) {
         if (this.value instanceof BigInteger) {
            this.value = ((BigInteger)this.value).negate();
         } else if (this.value instanceof BigDecimal) {
            this.value = ((BigDecimal)this.value).negate();
         } else if (this.value instanceof Integer) {
            this.value = -1 * (Integer)this.value;
         } else if (this.value instanceof Long) {
            this.value = -1L * (Long)this.value;
         } else if (this.value instanceof Double) {
            this.value = (double)-1.0F * (Double)this.value;
         } else if (this.value instanceof Float) {
            this.value = -1.0F * (Float)this.value;
         } else if (this.value instanceof Short) {
            this.value = (short)(-1 * (Short)this.value);
         }

      }
   }

   public Symbol bind(SymbolTable symtbl) {
      return null;
   }

   public String toString() {
      return "Literal{" + this.value + "}" + (this.alias != null ? " AS " + this.alias : "");
   }
}
