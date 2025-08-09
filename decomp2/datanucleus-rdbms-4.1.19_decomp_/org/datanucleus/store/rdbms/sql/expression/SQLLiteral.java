package org.datanucleus.store.rdbms.sql.expression;

public interface SQLLiteral {
   Object getValue();

   void setNotParameter();
}
