package org.apache.derby.iapi.types;

import java.io.IOException;
import org.apache.derby.shared.common.error.StandardException;

public interface Resetable {
   void resetStream() throws IOException, StandardException;

   void initStream() throws StandardException;

   void closeStream();
}
