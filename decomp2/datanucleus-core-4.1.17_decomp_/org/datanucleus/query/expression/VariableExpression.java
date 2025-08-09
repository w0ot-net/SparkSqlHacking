package org.datanucleus.query.expression;

import org.datanucleus.query.symbol.PropertySymbol;
import org.datanucleus.query.symbol.Symbol;
import org.datanucleus.query.symbol.SymbolTable;

public class VariableExpression extends Expression {
   private static final long serialVersionUID = 5353841370656870816L;
   String name;
   Class type;

   public VariableExpression(String name) {
      this.name = name;
   }

   public VariableExpression(String name, Class type) {
      this.name = name;
      this.type = type;
   }

   public String getId() {
      return this.name;
   }

   public Symbol bind(SymbolTable symtbl) {
      if (symtbl.hasSymbol(this.getId())) {
         this.symbol = symtbl.getSymbol(this.getId());
      } else {
         if (this.type != null) {
            this.symbol = new PropertySymbol(this.getId(), this.type);
         } else {
            this.symbol = new PropertySymbol(this.getId());
         }

         this.symbol.setType(2);
         symtbl.addSymbol(this.symbol);
      }

      return this.symbol;
   }

   public String toString() {
      return "VariableExpression{" + this.name + "}" + (this.alias != null ? " AS " + this.alias : "");
   }
}
