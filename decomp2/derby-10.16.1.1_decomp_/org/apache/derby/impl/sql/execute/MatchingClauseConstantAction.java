package org.apache.derby.impl.sql.execute;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ResultDescription;
import org.apache.derby.iapi.sql.ResultSet;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.types.SQLBoolean;
import org.apache.derby.shared.common.error.StandardException;

public class MatchingClauseConstantAction implements ConstantAction, Formatable {
   private static final long serialVersionUID = -6725483265211088817L;
   private static final int FIRST_VERSION = 0;
   private int _clauseType;
   private String _matchRefinementName;
   private ResultDescription _thenColumnSignature;
   private String _rowMakingMethodName;
   private String _resultSetFieldName;
   private String _actionMethodName;
   private ConstantAction _thenAction;
   private transient GeneratedMethod _matchRefinementMethod;
   private transient GeneratedMethod _rowMakingMethod;
   private transient ResultSet _actionRS;

   public MatchingClauseConstantAction() {
   }

   public MatchingClauseConstantAction(int var1, String var2, ResultDescription var3, String var4, String var5, String var6, ConstantAction var7) {
      this._clauseType = var1;
      this._matchRefinementName = var2;
      this._thenColumnSignature = var3;
      this._rowMakingMethodName = var4;
      this._resultSetFieldName = var5;
      this._actionMethodName = var6;
      this._thenAction = var7;
   }

   public int clauseType() {
      return this._clauseType;
   }

   public void executeConstantAction(Activation var1) throws StandardException {
   }

   public void executeConstantAction(Activation var1, TemporaryRowHolderImpl var2) throws StandardException {
      if (var2 != null) {
         CursorResultSet var3 = var2.getResultSet();
         GeneratedMethod var4 = ((BaseActivation)var1).getMethod(this._actionMethodName);

         try {
            var1.pushConstantAction(this._thenAction);

            try {
               Field var5 = var1.getClass().getField(this._resultSetFieldName);
               var5.set(var1, var3);
               Activation var6 = var3.getActivation();
               Method var7 = var1.getClass().getMethod(this._actionMethodName);
               this._actionRS = (ResultSet)var7.invoke(var1, (Object[])null);
            } catch (Exception var11) {
               throw StandardException.plainWrapException(var11);
            }

            this._actionRS.open();
         } finally {
            var1.popConstantAction();
         }

      }
   }

   void init() throws StandardException {
      this._actionRS = null;
   }

   boolean evaluateRefinementClause(Activation var1) throws StandardException {
      if (this._matchRefinementName == null) {
         return true;
      } else {
         if (this._matchRefinementMethod == null) {
            this._matchRefinementMethod = ((BaseActivation)var1).getMethod(this._matchRefinementName);
         }

         SQLBoolean var2 = (SQLBoolean)this._matchRefinementMethod.invoke(var1);
         return var2.isNull() ? false : var2.getBoolean();
      }
   }

   TemporaryRowHolderImpl bufferThenRow(Activation var1, TemporaryRowHolderImpl var2, ExecRow var3) throws StandardException {
      if (var2 == null) {
         var2 = this.createThenRows(var1);
      }

      ExecRow var4 = this.bufferThenRow(var1);
      var2.insert(var4);
      return var2;
   }

   void cleanUp() throws StandardException {
      if (this._actionRS != null) {
         this._actionRS.close();
         this._actionRS = null;
      }

      this._matchRefinementMethod = null;
      this._rowMakingMethod = null;
   }

   private ExecRow bufferThenRow(Activation var1) throws StandardException {
      if (this._rowMakingMethod == null) {
         this._rowMakingMethod = ((BaseActivation)var1).getMethod(this._rowMakingMethodName);
      }

      return (ExecRow)this._rowMakingMethod.invoke(var1);
   }

   private TemporaryRowHolderImpl createThenRows(Activation var1) throws StandardException {
      return new TemporaryRowHolderImpl(var1, new Properties(), this._thenColumnSignature);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      int var2 = var1.readInt();
      this._clauseType = var1.readInt();
      this._matchRefinementName = (String)var1.readObject();
      this._thenColumnSignature = (ResultDescription)var1.readObject();
      this._rowMakingMethodName = (String)var1.readObject();
      this._resultSetFieldName = (String)var1.readObject();
      this._actionMethodName = (String)var1.readObject();
      this._thenAction = (ConstantAction)var1.readObject();
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeInt(0);
      var1.writeInt(this._clauseType);
      var1.writeObject(this._matchRefinementName);
      var1.writeObject(this._thenColumnSignature);
      var1.writeObject(this._rowMakingMethodName);
      var1.writeObject(this._resultSetFieldName);
      var1.writeObject(this._actionMethodName);
      var1.writeObject(this._thenAction);
   }

   public int getTypeFormatId() {
      return 476;
   }
}
