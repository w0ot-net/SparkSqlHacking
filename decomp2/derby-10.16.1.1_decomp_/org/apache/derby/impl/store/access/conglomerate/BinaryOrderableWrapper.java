package org.apache.derby.impl.store.access.conglomerate;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.ArrayInputStream;
import org.apache.derby.iapi.services.io.Storable;
import org.apache.derby.iapi.services.io.TypedFormat;
import org.apache.derby.iapi.store.access.BinaryOrderable;

class BinaryOrderableWrapper implements Storable {
   BinaryOrderable ref_object;
   BinaryOrderable other_object;
   int cmp_result;

   protected void init(BinaryOrderable var1, BinaryOrderable var2) {
      this.ref_object = var1;
      this.other_object = var2;
   }

   public int getCmpResult() {
      return this.cmp_result;
   }

   public int getTypeFormatId() {
      return ((TypedFormat)this.ref_object).getTypeFormatId();
   }

   public boolean isNull() {
      return false;
   }

   public void restoreToNull() {
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.cmp_result = this.ref_object.binarycompare(var1, this.other_object);
   }

   public void readExternalFromArray(ArrayInputStream var1) throws IOException, ClassNotFoundException {
      this.cmp_result = this.ref_object.binarycompare(var1, this.other_object);
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
   }
}
