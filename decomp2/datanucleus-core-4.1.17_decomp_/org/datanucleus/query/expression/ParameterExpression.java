package org.datanucleus.query.expression;

import org.datanucleus.query.symbol.PropertySymbol;
import org.datanucleus.query.symbol.Symbol;
import org.datanucleus.query.symbol.SymbolTable;

public class ParameterExpression extends Expression {
   private static final long serialVersionUID = -2170413163550042263L;
   String name;
   int position;
   Class type;

   public ParameterExpression(String name, int position) {
      this.name = name;
      this.position = position;
   }

   public ParameterExpression(String name, Class type) {
      this.name = name;
      this.type = type;
      this.position = -1;
   }

   public String getId() {
      return this.name;
   }

   public int getPosition() {
      return this.position;
   }

   public Class getType() {
      return this.type;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Symbol bind(SymbolTable symtbl) {
      if (symtbl.hasSymbol(this.getId())) {
         this.symbol = symtbl.getSymbol(this.getId());
      } else {
         this.symbol = new PropertySymbol(this.getId());
         this.symbol.setType(1);
         symtbl.addSymbol(this.symbol);
      }

      return this.symbol;
   }

   public String toString() {
      return "ParameterExpression{" + this.name + "}";
   }
}
