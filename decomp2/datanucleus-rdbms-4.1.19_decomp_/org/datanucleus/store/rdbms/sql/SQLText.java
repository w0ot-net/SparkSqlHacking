package org.datanucleus.store.rdbms.sql;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.MappingHelper;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;

public class SQLText {
   private String sql;
   private List parameters;
   private boolean encloseInParentheses;
   private String postpend;
   private List appended;

   public SQLText() {
      this.parameters = null;
      this.encloseInParentheses = false;
      this.appended = new ArrayList();
   }

   public SQLText(String initialSQLText) {
      this();
      this.append(initialSQLText);
   }

   public void clearStatement() {
      this.sql = null;
      this.appended.clear();
   }

   public void encloseInParentheses() {
      this.sql = null;
      this.encloseInParentheses = true;
   }

   public SQLText postpend(String s) {
      this.sql = null;
      this.postpend = s;
      return this;
   }

   public SQLText prepend(String s) {
      this.sql = null;
      this.appended.add(0, s);
      return this;
   }

   public SQLText append(char c) {
      this.sql = null;
      this.appended.add(c);
      return this;
   }

   public SQLText append(String s) {
      this.sql = null;
      this.appended.add(s);
      return this;
   }

   public SQLText append(SQLStatement stmt) {
      this.sql = null;
      this.appended.add(stmt);
      return this;
   }

   public SQLText append(SQLExpression.ColumnExpressionList exprList) {
      this.sql = null;
      this.appended.add(exprList);
      return this;
   }

   public SQLText append(SQLText st) {
      this.sql = null;
      this.appended.add(st.toSQL());
      if (st.parameters != null) {
         if (this.parameters == null) {
            this.parameters = new ArrayList();
         }

         this.parameters.addAll(st.parameters);
      }

      return this;
   }

   public SQLText append(SQLExpression expr) {
      this.sql = null;
      this.appended.add(expr);
      return this;
   }

   public SQLText appendParameter(String name, JavaTypeMapping mapping, Object value) {
      return this.appendParameter(name, mapping, value, -1);
   }

   public SQLText appendParameter(String name, JavaTypeMapping mapping, Object value, int columnNumber) {
      this.sql = null;
      this.appended.add(new SQLStatementParameter(name, mapping, value, columnNumber));
      return this;
   }

   public void changeMappingForParameter(String parameterName, JavaTypeMapping mapping) {
      for(Object obj : this.appended) {
         if (obj instanceof SQLStatementParameter) {
            SQLStatementParameter param = (SQLStatementParameter)obj;
            if (param.getName().equalsIgnoreCase(parameterName)) {
               param.setMapping(mapping);
            }
         }
      }

   }

   public void applyParametersToStatement(ExecutionContext ec, PreparedStatement ps) {
      if (this.parameters != null) {
         int num = 1;

         for(SQLStatementParameter param : this.parameters) {
            JavaTypeMapping mapping = param.getMapping();
            if (mapping != null) {
               Object value = param.getValue();
               if (param.getColumnNumber() >= 0) {
                  Object colValue = null;
                  if (value != null) {
                     ClassLoaderResolver clr = ec.getClassLoaderResolver();
                     AbstractClassMetaData cmd = ec.getMetaDataManager().getMetaDataForClass(mapping.getType(), clr);
                     RDBMSStoreManager storeMgr = mapping.getStoreManager();
                     if (cmd.getIdentityType() == IdentityType.DATASTORE) {
                        colValue = mapping.getValueForDatastoreMapping(ec.getNucleusContext(), param.getColumnNumber(), value);
                     } else if (cmd.getIdentityType() == IdentityType.APPLICATION) {
                        colValue = SQLStatementHelper.getValueForPrimaryKeyIndexOfObjectUsingReflection(value, param.getColumnNumber(), cmd, storeMgr, clr);
                     }
                  }

                  mapping.getDatastoreMapping(param.getColumnNumber()).setObject(ps, num, colValue);
                  ++num;
               } else {
                  mapping.setObject(ec, ps, MappingHelper.getMappingIndices(num, mapping), value);
                  if (mapping.getNumberOfDatastoreMappings() > 0) {
                     num += mapping.getNumberOfDatastoreMappings();
                  } else {
                     ++num;
                  }
               }
            }
         }
      }

   }

   public List getParametersForStatement() {
      return this.parameters;
   }

   public String toSQL() {
      if (this.sql != null) {
         return this.sql;
      } else {
         StringBuilder sql = new StringBuilder();
         if (this.encloseInParentheses) {
            sql.append("(");
         }

         for(int i = 0; i < this.appended.size(); ++i) {
            Object item = this.appended.get(i);
            if (item instanceof SQLExpression) {
               SQLExpression expr = (SQLExpression)item;
               SQLText st = expr.toSQLText();
               sql.append(st.toSQL());
               if (st.parameters != null) {
                  if (this.parameters == null) {
                     this.parameters = new ArrayList();
                  }

                  this.parameters.addAll(st.parameters);
               }
            } else if (item instanceof SQLStatementParameter) {
               SQLStatementParameter param = (SQLStatementParameter)item;
               sql.append('?');
               if (this.parameters == null) {
                  this.parameters = new ArrayList();
               }

               this.parameters.add(param);
            } else if (item instanceof SQLStatement) {
               SQLStatement stmt = (SQLStatement)item;
               SQLText st = stmt.getSelectStatement();
               sql.append(st.toSQL());
               if (st.parameters != null) {
                  if (this.parameters == null) {
                     this.parameters = new ArrayList();
                  }

                  this.parameters.addAll(st.parameters);
               }
            } else if (item instanceof SQLText) {
               SQLText st = (SQLText)item;
               sql.append(st.toSQL());
               if (st.parameters != null) {
                  if (this.parameters == null) {
                     this.parameters = new ArrayList();
                  }

                  this.parameters.addAll(st.parameters);
               }
            } else {
               sql.append(item);
            }
         }

         if (this.encloseInParentheses) {
            sql.append(")");
         }

         sql.append(this.postpend == null ? "" : this.postpend);
         this.sql = sql.toString();
         return this.sql;
      }
   }

   public String toString() {
      return this.toSQL();
   }
}
