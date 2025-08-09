package org.apache.derby.iapi.store.raw;

import java.io.IOException;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.services.io.LimitObjectInput;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.shared.common.error.StandardException;

public interface Loggable extends Formatable {
   int FIRST = 1;
   int LAST = 2;
   int COMPENSATION = 4;
   int BI_LOG = 8;
   int COMMIT = 16;
   int ABORT = 32;
   int PREPARE = 64;
   int XA_NEEDLOCK = 128;
   int RAWSTORE = 256;
   int FILE_RESOURCE = 1024;
   int CHECKSUM = 2048;

   void doMe(Transaction var1, LogInstant var2, LimitObjectInput var3) throws StandardException, IOException;

   ByteArray getPreparedLog() throws StandardException;

   boolean needsRedo(Transaction var1) throws StandardException;

   void releaseResource(Transaction var1);

   int group();
}
