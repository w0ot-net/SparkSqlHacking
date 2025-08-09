package org.datanucleus.query.expression;

import java.util.ArrayList;
import java.util.List;
import org.datanucleus.query.symbol.Symbol;
import org.datanucleus.query.symbol.SymbolTable;
import org.datanucleus.util.StringUtils;

public class ArrayExpression extends Expression {
   private static final long serialVersionUID = -1145090859293765860L;
   List elements = new ArrayList();

   public ArrayExpression(Expression[] elements) {
      if (elements != null) {
         for(int i = 0; i < elements.length; ++i) {
            this.elements.add(elements[i]);
            elements[i].parent = this;
         }
      }

   }

   public Expression getElement(int index) {
      if (index >= 0 && index < this.elements.size()) {
         return (Expression)this.elements.get(index);
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public int getArraySize() {
      return this.elements.size();
   }

   public Symbol bind(SymbolTable symtbl) {
      for(int i = 0; i < this.elements.size(); ++i) {
         Expression expr = (Expression)this.elements.get(i);
         expr.bind(symtbl);
      }

      return this.symbol;
   }

   public String toString() {
      return "ArrayExpression{" + StringUtils.collectionToString(this.elements) + "}";
   }
}
