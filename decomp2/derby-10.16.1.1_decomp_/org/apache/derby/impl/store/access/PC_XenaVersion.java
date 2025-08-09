package org.apache.derby.impl.store.access;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Properties;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

public class PC_XenaVersion implements Formatable {
   private static final int XENA_MAJOR_VERSION = 1;
   private static final int XENA_MINOR_VERSION_0 = 0;
   private int minorVersion = 0;

   private boolean isUpgradeNeeded(PC_XenaVersion var1) {
      return var1 == null || this.getMajorVersionNumber() != var1.getMajorVersionNumber();
   }

   public void upgradeIfNeeded(TransactionController var1, PropertyConglomerate var2, Properties var3) throws StandardException {
      PC_XenaVersion var4 = (PC_XenaVersion)var2.getProperty(var1, "PropertyConglomerateVersion");
      if (this.isUpgradeNeeded(var4)) {
         throw StandardException.newException("XCW00.D", new Object[]{var4, this});
      }
   }

   public int getMajorVersionNumber() {
      return 1;
   }

   public int getMinorVersionNumber() {
      return this.minorVersion;
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeInt(this.getMajorVersionNumber());
      var1.writeInt(this.getMinorVersionNumber());
   }

   public void readExternal(ObjectInput var1) throws IOException {
      int var2 = var1.readInt();
      this.minorVersion = var1.readInt();
   }

   public int getTypeFormatId() {
      return 15;
   }

   public String toString() {
      int var10000 = this.getMajorVersionNumber();
      return var10000 + "." + this.getMinorVersionNumber();
   }
}
