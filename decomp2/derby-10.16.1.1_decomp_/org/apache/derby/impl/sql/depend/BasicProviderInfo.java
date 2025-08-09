package org.apache.derby.impl.sql.depend;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.catalog.DependableFinder;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.io.FormatableHashtable;
import org.apache.derby.iapi.sql.depend.ProviderInfo;

public class BasicProviderInfo implements ProviderInfo {
   private UUID uuid;
   private DependableFinder dFinder;
   private String providerName;

   public BasicProviderInfo() {
   }

   BasicProviderInfo(UUID var1, DependableFinder var2, String var3) {
      this.uuid = var1;
      this.dFinder = var2;
      this.providerName = var3;
   }

   public DependableFinder getDependableFinder() {
      return this.dFinder;
   }

   public UUID getObjectId() {
      return this.uuid;
   }

   public String getProviderName() {
      return this.providerName;
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      FormatableHashtable var2 = (FormatableHashtable)var1.readObject();
      this.uuid = (UUID)var2.get("uuid");
      this.dFinder = (DependableFinder)var2.get("dFinder");
      this.providerName = (String)var2.get("providerName");
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      FormatableHashtable var2 = new FormatableHashtable();
      var2.put("uuid", this.uuid);
      var2.put("dFinder", this.dFinder);
      var2.put("providerName", this.providerName);
      var1.writeObject(var2);
   }

   public int getTypeFormatId() {
      return 359;
   }

   public String toString() {
      return "";
   }
}
