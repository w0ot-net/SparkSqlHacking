package org.apache.derby.iapi.types;

import java.io.IOException;
import java.io.InputStream;
import org.apache.derby.shared.common.error.StandardException;

public interface PositionedStream {
   InputStream asInputStream();

   long getPosition();

   void reposition(long var1) throws IOException, StandardException;
}
