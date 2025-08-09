package io.fabric8.kubernetes.client.dsl;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;

public interface Loggable {
   String getLog();

   String getLog(boolean var1);

   Reader getLogReader();

   InputStream getLogInputStream();

   LogWatch watchLog();

   LogWatch watchLog(OutputStream var1);

   /** @deprecated */
   @Deprecated
   Loggable withLogWaitTimeout(Integer var1);

   Loggable withReadyWaitTimeout(Integer var1);
}
