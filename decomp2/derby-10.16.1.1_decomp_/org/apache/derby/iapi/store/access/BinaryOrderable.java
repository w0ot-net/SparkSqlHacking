package org.apache.derby.iapi.store.access;

import java.io.IOException;
import java.io.ObjectInput;
import org.apache.derby.iapi.types.Orderable;

public interface BinaryOrderable extends Orderable {
   int binarycompare(ObjectInput var1, Orderable var2) throws IOException;

   boolean binarycompare(ObjectInput var1, int var2, Orderable var3, boolean var4, boolean var5) throws IOException;
}
