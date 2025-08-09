package org.apache.derby.iapi.services.io;

import org.apache.derby.iapi.services.loader.InstanceGetter;

public abstract class FormatableInstanceGetter implements InstanceGetter {
   protected int fmtId;

   public final void setFormatId(int var1) {
      this.fmtId = var1;
   }
}
