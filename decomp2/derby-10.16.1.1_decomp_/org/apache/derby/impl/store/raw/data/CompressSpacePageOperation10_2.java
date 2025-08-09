package org.apache.derby.impl.store.raw.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.CompressedNumber;
import org.apache.derby.shared.common.error.StandardException;

public final class CompressSpacePageOperation10_2 extends CompressSpacePageOperation {
   CompressSpacePageOperation10_2(AllocPage var1, int var2, int var3) throws StandardException {
      super(var1, var2, var3);
   }

   public CompressSpacePageOperation10_2() {
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      super.writeExternal(var1);
      CompressedNumber.writeInt((DataOutput)var1, this.newHighestPage);
      CompressedNumber.writeInt((DataOutput)var1, this.num_pages_truncated);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      super.readExternal(var1);
      this.newHighestPage = CompressedNumber.readInt((DataInput)var1);
      this.num_pages_truncated = CompressedNumber.readInt((DataInput)var1);
   }

   public int getTypeFormatId() {
      return 454;
   }
}
