package org.apache.derby.iapi.sql.compile;

public class IgnoreFilter implements VisitableFilter {
   public boolean accept(Visitable var1) {
      return false;
   }
}
