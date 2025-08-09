package org.apache.derby.impl.jdbc;

import java.sql.SQLException;
import java.sql.Savepoint;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.shared.common.error.StandardException;

final class EmbedSavepoint extends ConnectionChild implements Savepoint {
   private final String savepointName;
   private final int savepointID;

   EmbedSavepoint(EmbedConnection var1, String var2) throws StandardException {
      super(var1);
      if (var2 == null) {
         LanguageConnectionContext var10001 = this.getLanguageConnectionContext(var1);
         this.savepointName = "i." + var10001.getUniqueSavepointName();
         this.savepointID = this.getLanguageConnectionContext(var1).getUniqueSavepointID();
      } else {
         this.savepointName = "e." + var2;
         this.savepointID = -1;
      }

      this.getLanguageConnectionContext(var1).languageSetSavePoint(this.savepointName, this);
   }

   public int getSavepointId() throws SQLException {
      if (this.savepointID == -1) {
         throw newSQLException("XJ013.S", new Object[0]);
      } else {
         return this.savepointID;
      }
   }

   public String getSavepointName() throws SQLException {
      if (this.savepointID != -1) {
         throw newSQLException("XJ014.S", new Object[0]);
      } else {
         return this.savepointName.substring(2);
      }
   }

   String getInternalName() {
      return this.savepointName;
   }

   boolean sameConnection(EmbedConnection var1) {
      return getLCC(this.getEmbedConnection()) == getLCC(var1);
   }
}
