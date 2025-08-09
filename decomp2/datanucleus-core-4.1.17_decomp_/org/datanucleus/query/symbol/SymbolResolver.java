package org.datanucleus.query.symbol;

import java.util.List;

public interface SymbolResolver {
   Class getType(List var1);

   Class getPrimaryClass();

   Class resolveClass(String var1);

   boolean supportsImplicitVariables();

   boolean caseSensitiveSymbolNames();
}
