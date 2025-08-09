package io.fabric8.kubernetes.client.dsl;

import java.io.OutputStream;

public interface TtyExecErrorChannelable extends TtyExecable {
   /** @deprecated */
   @Deprecated
   TtyExecable writingErrorChannel(OutputStream var1);

   /** @deprecated */
   @Deprecated
   TtyExecable redirectingErrorChannel();
}
