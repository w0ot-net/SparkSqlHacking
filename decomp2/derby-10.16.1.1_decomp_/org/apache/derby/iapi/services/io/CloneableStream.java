package org.apache.derby.iapi.services.io;

import java.io.InputStream;

public interface CloneableStream {
   InputStream cloneStream();
}
