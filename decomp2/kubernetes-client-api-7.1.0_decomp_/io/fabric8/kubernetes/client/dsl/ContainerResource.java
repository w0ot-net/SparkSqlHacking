package io.fabric8.kubernetes.client.dsl;

import java.io.InputStream;

public interface ContainerResource extends TtyExecOutputErrorable, TimestampBytesLimitTerminateTimeTailPrettyLoggable {
   /** @deprecated */
   @Deprecated
   TtyExecOutputErrorable readingInput(InputStream var1);

   TtyExecOutputErrorable redirectingInput();

   TtyExecOutputErrorable redirectingInput(Integer var1);

   CopyOrReadable file(String var1);

   CopyOrReadable dir(String var1);

   ContainerResource withReadyWaitTimeout(Integer var1);
}
