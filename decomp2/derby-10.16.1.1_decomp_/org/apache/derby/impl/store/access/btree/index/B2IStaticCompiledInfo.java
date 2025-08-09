package org.apache.derby.impl.store.access.btree.index;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.ArrayInputStream;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public class B2IStaticCompiledInfo implements StaticCompiledOpenConglomInfo {
   B2I b2i;
   StaticCompiledOpenConglomInfo base_table_static_info;

   public B2IStaticCompiledInfo() {
   }

   B2IStaticCompiledInfo(TransactionController var1, B2I var2) throws StandardException {
      this.b2i = var2;
      this.base_table_static_info = var1.getStaticCompiledConglomInfo(var2.baseConglomerateId);
   }

   public DataValueDescriptor getConglom() {
      return this.b2i;
   }

   public boolean isNull() {
      return this.b2i == null;
   }

   public void restoreToNull() {
      this.b2i = null;
   }

   public int getTypeFormatId() {
      return 360;
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.b2i = new B2I();
      this.b2i.readExternal(var1);
      this.base_table_static_info = (StaticCompiledOpenConglomInfo)var1.readObject();
   }

   public void readExternalFromArray(ArrayInputStream var1) throws IOException, ClassNotFoundException {
      this.b2i = new B2I();
      this.b2i.readExternal(var1);
      this.base_table_static_info = (StaticCompiledOpenConglomInfo)var1.readObject();
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      this.b2i.writeExternal(var1);
      var1.writeObject(this.base_table_static_info);
   }
}
