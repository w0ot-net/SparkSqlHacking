package org.datanucleus.query.expression;

import java.util.Iterator;
import java.util.List;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.query.symbol.PropertySymbol;
import org.datanucleus.query.symbol.Symbol;
import org.datanucleus.query.symbol.SymbolTable;
import org.datanucleus.util.StringUtils;

public class CreatorExpression extends Expression {
   private static final long serialVersionUID = -6455308731943969503L;
   List tuples;
   List arguments;

   public CreatorExpression(List tuples, List args) {
      this.tuples = tuples;
      this.arguments = args;
      if (args != null && !args.isEmpty()) {
         for(Iterator<Expression> argIter = args.iterator(); argIter.hasNext(); ((Expression)argIter.next()).parent = this) {
         }
      }

   }

   public String getId() {
      StringBuilder id = new StringBuilder();

      for(int i = 0; i < this.tuples.size(); ++i) {
         if (id.length() > 0) {
            id.append('.');
         }

         id.append((String)this.tuples.get(i));
      }

      return id.toString();
   }

   public List getArguments() {
      return this.arguments;
   }

   public List getTuples() {
      return this.tuples;
   }

   public Symbol bind(SymbolTable symtbl) {
      if (symtbl.hasSymbol(this.getId())) {
         this.symbol = symtbl.getSymbol(this.getId());
      } else {
         try {
            String className = this.getId();
            Class cls = symtbl.getSymbolResolver().resolveClass(className);
            this.symbol = new PropertySymbol(this.getId(), cls);
         } catch (ClassNotResolvedException var4) {
            throw new NucleusUserException("CreatorExpression defined with class of " + this.getId() + " yet this class is not found");
         }
      }

      return this.symbol;
   }

   public String toString() {
      return "CreatorExpression{" + this.getId() + "(" + StringUtils.collectionToString(this.arguments) + ")}" + (this.alias != null ? " AS " + this.alias : "");
   }
}
