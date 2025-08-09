package org.datanucleus.query.compiler;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.query.symbol.SymbolTable;

public class JDOQLSymbolResolver extends AbstractSymbolResolver {
   public JDOQLSymbolResolver(MetaDataManager mmgr, ClassLoaderResolver clr, SymbolTable symtbl, Class cls, String alias) {
      super(mmgr, clr, symtbl, cls, alias);
   }

   public Class resolveClass(String className) {
      AbstractClassMetaData acmd = this.metaDataManager.getMetaDataForEntityName(className);
      if (acmd != null) {
         String fullClassName = acmd.getFullClassName();
         if (fullClassName != null) {
            return this.clr.classForName(fullClassName);
         }
      }

      throw new ClassNotResolvedException("Class " + className + " for query has not been resolved. Check the query and any imports specification");
   }

   public boolean caseSensitiveSymbolNames() {
      return true;
   }

   public boolean supportsImplicitVariables() {
      return true;
   }
}
