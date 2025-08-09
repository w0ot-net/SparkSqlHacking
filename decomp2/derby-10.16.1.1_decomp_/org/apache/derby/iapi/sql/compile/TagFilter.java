package org.apache.derby.iapi.sql.compile;

import org.apache.derby.shared.common.error.StandardException;

public class TagFilter implements VisitableFilter {
   public static final String NEED_PRIVS_FOR_UPDATE_STMT = "updatePrivs";
   public static final String ORIG_UPDATE_COL = "origUpdateCol";
   private String _tag;

   public TagFilter(String var1) {
      this._tag = var1;
   }

   public boolean accept(Visitable var1) throws StandardException {
      return var1.taggedWith(this._tag);
   }
}
