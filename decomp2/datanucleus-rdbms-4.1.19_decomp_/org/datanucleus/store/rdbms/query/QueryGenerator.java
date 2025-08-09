package org.datanucleus.store.rdbms.query;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.query.compiler.CompilationComponent;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLLiteral;
import org.datanucleus.store.rdbms.sql.expression.UnboundExpression;

public interface QueryGenerator {
   String getQueryLanguage();

   ClassLoaderResolver getClassLoaderResolver();

   ExecutionContext getExecutionContext();

   CompilationComponent getCompilationComponent();

   Object getProperty(String var1);

   void useParameterExpressionAsLiteral(SQLLiteral var1);

   Class getTypeOfVariable(String var1);

   void bindVariable(String var1, AbstractClassMetaData var2, SQLTable var3, JavaTypeMapping var4);

   SQLExpression bindVariable(UnboundExpression var1, Class var2);

   boolean hasExplicitJoins();

   boolean processingOnClause();

   void bindParameter(String var1, Class var2);

   Class resolveClass(String var1);

   boolean hasExtension(String var1);

   Object getValueForExtension(String var1);
}
