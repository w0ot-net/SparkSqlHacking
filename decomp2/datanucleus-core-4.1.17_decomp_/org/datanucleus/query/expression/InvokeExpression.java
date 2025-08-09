package org.datanucleus.query.expression;

import java.util.Iterator;
import java.util.List;
import org.datanucleus.query.symbol.Symbol;
import org.datanucleus.query.symbol.SymbolTable;
import org.datanucleus.util.StringUtils;

public class InvokeExpression extends Expression {
   private static final long serialVersionUID = -4907486904172153963L;
   String methodName;
   List arguments;

   public InvokeExpression(Expression invoked, String methodName, List args) {
      this.left = invoked;
      this.methodName = methodName;
      this.arguments = args;
      if (invoked != null) {
         invoked.parent = this;
      }

      if (args != null && !args.isEmpty()) {
         for(Iterator<Expression> argIter = args.iterator(); argIter.hasNext(); ((Expression)argIter.next()).parent = this) {
         }
      }

   }

   public String getOperation() {
      return this.methodName;
   }

   public List getArguments() {
      return this.arguments;
   }

   public Symbol bind(SymbolTable symtbl) {
      if (this.left != null) {
         try {
            this.left.bind(symtbl);
         } catch (PrimaryExpressionIsVariableException pive) {
            this.left = pive.getVariableExpression();
            this.left.bind(symtbl);
         } catch (PrimaryExpressionIsInvokeException piie) {
            this.left = piie.getInvokeExpression();
            this.left.bind(symtbl);
         } catch (PrimaryExpressionIsClassLiteralException var11) {
            this.methodName = ((PrimaryExpression)this.left).getId() + "." + this.methodName;
            this.left = null;
         }
      }

      if (this.arguments != null && this.arguments.size() > 0) {
         for(int i = 0; i < this.arguments.size(); ++i) {
            Expression expr = (Expression)this.arguments.get(i);

            try {
               expr.bind(symtbl);
            } catch (PrimaryExpressionIsVariableException pive) {
               VariableExpression ve = pive.getVariableExpression();
               ve.bind(symtbl);
               this.arguments.remove(i);
               this.arguments.add(i, ve);
            } catch (PrimaryExpressionIsInvokeException piie) {
               InvokeExpression ve = piie.getInvokeExpression();
               ve.bind(symtbl);
               this.arguments.remove(i);
               this.arguments.add(i, ve);
            } catch (PrimaryExpressionIsClassLiteralException picle) {
               Literal l = picle.getLiteral();
               l.bind(symtbl);
               this.arguments.remove(i);
               this.arguments.add(i, l);
            }
         }
      }

      return this.symbol;
   }

   public String toStringWithoutAlias() {
      return this.left == null ? "InvokeExpression{STATIC." + this.methodName + "(" + StringUtils.collectionToString(this.arguments) + ")}" : "InvokeExpression{[" + this.left + "]." + this.methodName + "(" + StringUtils.collectionToString(this.arguments) + ")}";
   }

   public String toString() {
      return this.left == null ? "InvokeExpression{STATIC." + this.methodName + "(" + StringUtils.collectionToString(this.arguments) + ")}" + (this.alias != null ? " AS " + this.alias : "") : "InvokeExpression{[" + this.left + "]." + this.methodName + "(" + StringUtils.collectionToString(this.arguments) + ")}" + (this.alias != null ? " AS " + this.alias : "");
   }
}
