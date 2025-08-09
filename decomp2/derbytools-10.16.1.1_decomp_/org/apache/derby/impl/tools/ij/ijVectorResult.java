package org.apache.derby.impl.tools.ij;

import java.sql.SQLWarning;
import java.util.Vector;

class ijVectorResult extends ijResultImpl {
   Vector vec;
   SQLWarning warns;

   ijVectorResult(Vector var1, SQLWarning var2) {
      this.vec = var1;
      this.warns = var2;
   }

   ijVectorResult(Object var1, SQLWarning var2) {
      this(new Vector(1), var2);
      this.vec.add(var1);
   }

   public boolean isVector() {
      return true;
   }

   public Vector getVector() {
      return this.vec;
   }

   public SQLWarning getSQLWarnings() {
      return this.warns;
   }

   public void clearSQLWarnings() {
      this.warns = null;
   }
}
