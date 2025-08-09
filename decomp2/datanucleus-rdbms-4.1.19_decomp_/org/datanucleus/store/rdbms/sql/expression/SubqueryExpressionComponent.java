package org.datanucleus.store.rdbms.sql.expression;

import org.datanucleus.store.rdbms.sql.SQLStatement;

public interface SubqueryExpressionComponent {
   SQLStatement getSubqueryStatement();
}
