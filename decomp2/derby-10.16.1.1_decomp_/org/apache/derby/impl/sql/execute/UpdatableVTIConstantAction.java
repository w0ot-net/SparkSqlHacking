package org.apache.derby.impl.sql.execute;

import java.util.Properties;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.dictionary.IndexRowGenerator;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;

public class UpdatableVTIConstantAction extends WriteCursorConstantAction {
   public int[] changedColumnIds;
   public int statementType;

   public UpdatableVTIConstantAction() {
   }

   UpdatableVTIConstantAction(int var1, boolean var2, int[] var3) {
      super(0L, (StaticCompiledOpenConglomInfo)null, (IndexRowGenerator[])null, (long[])null, (StaticCompiledOpenConglomInfo[])null, (String[])null, var2, (Properties)null, (UUID)null, 0, (FKInfo[])null, (TriggerInfo)null, (FormatableBitSet)null, (int[])null, (int[])null, false, false);
      this.statementType = var1;
      this.changedColumnIds = var3;
   }

   public int getTypeFormatId() {
      return 375;
   }
}
