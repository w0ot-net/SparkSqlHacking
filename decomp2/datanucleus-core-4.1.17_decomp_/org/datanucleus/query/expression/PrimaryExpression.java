package org.datanucleus.query.expression;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.List;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.query.symbol.PropertySymbol;
import org.datanucleus.query.symbol.Symbol;
import org.datanucleus.query.symbol.SymbolTable;
import org.datanucleus.util.ClassUtils;

public class PrimaryExpression extends Expression {
   private static final long serialVersionUID = 6725075523258882792L;
   List tuples;

   public PrimaryExpression(List tuples) {
      this.tuples = tuples;
   }

   public PrimaryExpression(Expression left, List tuples) {
      this.left = left;
      if (left != null) {
         left.parent = this;
      }

      this.tuples = tuples;
   }

   public String getId() {
      StringBuilder str = new StringBuilder();

      for(String tuple : this.tuples) {
         if (str.length() > 0) {
            str.append('.');
         }

         str.append(tuple);
      }

      return str.toString();
   }

   public List getTuples() {
      return this.tuples;
   }

   public Symbol bind(SymbolTable symtbl) {
      if (this.left != null) {
         this.left.bind(symtbl);
      }

      if (this.left == null && symtbl.hasSymbol(this.getId())) {
         this.symbol = symtbl.getSymbol(this.getId());
         if (this.symbol.getType() == 2) {
            throw new PrimaryExpressionIsVariableException(this.symbol.getQualifiedName());
         } else {
            return this.symbol;
         }
      } else if (this.left != null) {
         return null;
      } else {
         if (this.symbol == null) {
            try {
               Class symbolType = symtbl.getSymbolResolver().getType(this.tuples);
               this.symbol = new PropertySymbol(this.getId(), symbolType);
            } catch (NucleusUserException var12) {
            }
         }

         if (this.symbol == null && symtbl.getParentSymbolTable() != null) {
            try {
               Class symbolType = symtbl.getParentSymbolTable().getSymbolResolver().getType(this.tuples);
               this.symbol = new PropertySymbol(this.getId(), symbolType);
            } catch (NucleusUserException var11) {
            }
         }

         if (this.symbol == null) {
            String className = this.getId();

            try {
               Class cls = symtbl.getSymbolResolver().resolveClass(className);
               throw new PrimaryExpressionIsClassLiteralException(cls);
            } catch (ClassNotResolvedException var15) {
               if (className.indexOf(46) < 0) {
                  Class primaryCls = symtbl.getSymbolResolver().getPrimaryClass();
                  if (primaryCls == null) {
                     throw new NucleusUserException("Class name " + className + " could not be resolved");
                  }

                  try {
                     Field fld = primaryCls.getDeclaredField(className);
                     if (!Modifier.isStatic(fld.getModifiers())) {
                        throw new NucleusUserException("Identifier " + className + " is unresolved (not a static field)");
                     }

                     throw new PrimaryExpressionIsClassStaticFieldException(fld);
                  } catch (NoSuchFieldException var13) {
                     if (symtbl.getSymbolResolver().supportsImplicitVariables() && this.left == null) {
                        throw new PrimaryExpressionIsVariableException(className);
                     }

                     throw new NucleusUserException("Class name " + className + " could not be resolved");
                  }
               }

               try {
                  String staticFieldName = className.substring(className.lastIndexOf(46) + 1);
                  className = className.substring(0, className.lastIndexOf(46));
                  Class cls = symtbl.getSymbolResolver().resolveClass(className);

                  try {
                     Field fld = cls.getDeclaredField(staticFieldName);
                     if (!Modifier.isStatic(fld.getModifiers())) {
                        throw new NucleusUserException("Identifier " + className + "." + staticFieldName + " is unresolved (not a static field)");
                     }

                     throw new PrimaryExpressionIsClassStaticFieldException(fld);
                  } catch (NoSuchFieldException var10) {
                     throw new NucleusUserException("Identifier " + className + "." + staticFieldName + " is unresolved (not a static field)");
                  }
               } catch (ClassNotResolvedException var14) {
                  if (this.getId().indexOf(".") > 0) {
                     Iterator<String> tupleIter = this.tuples.iterator();
                     Class cls = null;

                     while(tupleIter.hasNext()) {
                        String tuple = (String)tupleIter.next();
                        if (cls == null) {
                           Symbol sym = symtbl.getSymbol(tuple);
                           if (sym == null) {
                              sym = symtbl.getSymbol("this");
                              if (sym == null) {
                                 break;
                              }
                           }

                           cls = sym.getValueType();
                        } else {
                           if (cls.isArray() && tuple.equals("length") && !tupleIter.hasNext()) {
                              PrimaryExpression primExpr = new PrimaryExpression(this.left, this.tuples.subList(0, this.tuples.size() - 1));
                              InvokeExpression invokeExpr = new InvokeExpression(primExpr, "size", (List)null);
                              throw new PrimaryExpressionIsInvokeException(invokeExpr);
                           }

                           cls = ClassUtils.getClassForMemberOfClass(cls, tuple);
                        }
                     }

                     if (cls != null) {
                     }
                  }
               }
            }

            if (!symtbl.getSymbolResolver().supportsImplicitVariables() || this.left != null) {
               throw new NucleusUserException("Cannot find type of (part of) " + this.getId() + " since symbol has no type; implicit variable?");
            }

            String varName = (String)this.tuples.remove(0);
            VariableExpression varExpr = new VariableExpression(varName);
            varExpr.bind(symtbl);
            this.left = varExpr;
         }

         return this.symbol;
      }
   }

   public String toString() {
      return this.left != null ? "PrimaryExpression{" + this.left + "." + this.getId() + "}" + (this.alias != null ? " AS " + this.alias : "") : "PrimaryExpression{" + this.getId() + "}" + (this.alias != null ? " AS " + this.alias : "");
   }
}
