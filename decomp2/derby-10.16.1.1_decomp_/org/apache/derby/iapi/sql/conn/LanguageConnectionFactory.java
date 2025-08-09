package org.apache.derby.iapi.sql.conn;

import org.apache.derby.iapi.db.Database;
import org.apache.derby.iapi.services.cache.CacheManager;
import org.apache.derby.iapi.services.compiler.JavaFactory;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.services.property.PropertyFactory;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.LanguageFactory;
import org.apache.derby.iapi.sql.Statement;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.OptimizerFactory;
import org.apache.derby.iapi.sql.compile.Parser;
import org.apache.derby.iapi.sql.compile.TypeCompilerFactory;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.shared.common.error.StandardException;

public interface LanguageConnectionFactory {
   String MODULE = "org.apache.derby.iapi.sql.conn.LanguageConnectionFactory";

   Statement getStatement(SchemaDescriptor var1, String var2, boolean var3);

   LanguageConnectionContext newLanguageConnectionContext(ContextManager var1, TransactionController var2, LanguageFactory var3, Database var4, String var5, String var6, String var7) throws StandardException;

   UUIDFactory getUUIDFactory();

   ClassFactory getClassFactory();

   JavaFactory getJavaFactory();

   ExecutionFactory getExecutionFactory();

   PropertyFactory getPropertyFactory();

   OptimizerFactory getOptimizerFactory();

   TypeCompilerFactory getTypeCompilerFactory();

   DataValueFactory getDataValueFactory();

   CacheManager getStatementCache();

   Parser newParser(CompilerContext var1);
}
