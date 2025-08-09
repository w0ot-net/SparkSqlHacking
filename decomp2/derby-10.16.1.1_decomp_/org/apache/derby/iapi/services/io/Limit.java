package org.apache.derby.iapi.services.io;

import java.io.IOException;

public interface Limit {
   void setLimit(int var1) throws IOException;

   int clearLimit();
}
