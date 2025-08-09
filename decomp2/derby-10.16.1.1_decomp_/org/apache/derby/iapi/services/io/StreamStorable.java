package org.apache.derby.iapi.services.io;

import java.io.InputStream;
import org.apache.derby.shared.common.error.StandardException;

public interface StreamStorable {
   InputStream returnStream();

   void setStream(InputStream var1);

   void loadStream() throws StandardException;
}
