package org.apache.derby.impl.sql.execute;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Properties;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.ResultDescription;
import org.apache.derby.iapi.sql.dictionary.IndexRowGenerator;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.shared.common.util.ArrayUtil;

public class DeleteConstantAction extends WriteCursorConstantAction {
   int numColumns;
   ConstantAction[] dependentCActions;
   ResultDescription resultDescription;

   public DeleteConstantAction() {
   }

   DeleteConstantAction(long var1, StaticCompiledOpenConglomInfo var3, IndexRowGenerator[] var4, long[] var5, StaticCompiledOpenConglomInfo[] var6, boolean var7, UUID var8, int var9, FKInfo[] var10, TriggerInfo var11, FormatableBitSet var12, int[] var13, int[] var14, int var15, boolean var16, ResultDescription var17, ConstantAction[] var18, boolean var19) {
      super(var1, var3, var4, var5, var6, (String[])null, var7, (Properties)null, var8, var9, var10, var11, var12, var13, var14, var16, var19);
      this.numColumns = var15;
      this.resultDescription = var17;
      this.dependentCActions = var18;
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      super.readExternal(var1);
      this.numColumns = var1.readInt();
      this.dependentCActions = new ConstantAction[ArrayUtil.readArrayLength(var1)];
      ArrayUtil.readArrayItems(var1, this.dependentCActions);
      this.resultDescription = (ResultDescription)var1.readObject();
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      super.writeExternal(var1);
      var1.writeInt(this.numColumns);
      ArrayUtil.writeArray(var1, this.dependentCActions);
      var1.writeObject(this.resultDescription);
   }

   public int getTypeFormatId() {
      return 37;
   }
}
