package org.apache.derby.iapi.sql.compile;

import org.apache.derby.shared.common.error.StandardException;

public class ScopeFilter implements VisitableFilter {
   private CompilerContext _compilerContext;
   private String _scopeName;
   private int _minDepth;

   public ScopeFilter(CompilerContext var1, String var2, int var3) {
      this._compilerContext = var1;
      this._scopeName = var2;
      this._minDepth = var3;
   }

   public boolean accept(Visitable var1) throws StandardException {
      return this._compilerContext.scopeDepth(this._scopeName) >= this._minDepth;
   }
}
