package io.fabric8.kubernetes.client.dsl;

import java.io.OutputStream;

public interface TtyExecOutputErrorable extends TtyExecErrorable {
   TtyExecErrorable writingOutput(OutputStream var1);

   TtyExecErrorable redirectingOutput();
}
