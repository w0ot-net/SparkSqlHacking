package io.fabric8.kubernetes.client.dsl;

import java.io.OutputStream;

public interface TtyExecErrorable extends TtyExecErrorChannelable {
   TtyExecErrorChannelable writingError(OutputStream var1);

   TtyExecErrorChannelable terminateOnError();

   TtyExecErrorChannelable redirectingError();
}
