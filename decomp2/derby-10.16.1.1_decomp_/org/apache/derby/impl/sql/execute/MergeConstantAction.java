package org.apache.derby.impl.sql.execute;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.shared.common.error.StandardException;

public class MergeConstantAction implements ConstantAction, Formatable {
   private static final int FIRST_VERSION = 0;
   private MatchingClauseConstantAction[] _matchingClauses;

   public MergeConstantAction() {
   }

   public MergeConstantAction(ConstantAction[] var1) {
      int var2 = var1.length;
      this._matchingClauses = new MatchingClauseConstantAction[var2];

      for(int var3 = 0; var3 < var2; ++var3) {
         this._matchingClauses[var3] = (MatchingClauseConstantAction)var1[var3];
      }

   }

   public int matchingClauseCount() {
      return this._matchingClauses.length;
   }

   public MatchingClauseConstantAction getMatchingClause(int var1) {
      return this._matchingClauses[var1];
   }

   public void executeConstantAction(Activation var1) throws StandardException {
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      int var2 = var1.readInt();
      this._matchingClauses = new MatchingClauseConstantAction[var1.readInt()];

      for(int var3 = 0; var3 < this._matchingClauses.length; ++var3) {
         this._matchingClauses[var3] = (MatchingClauseConstantAction)var1.readObject();
      }

   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeInt(0);
      var1.writeInt(this._matchingClauses.length);

      for(int var2 = 0; var2 < this._matchingClauses.length; ++var2) {
         var1.writeObject(this._matchingClauses[var2]);
      }

   }

   public int getTypeFormatId() {
      return 477;
   }
}
