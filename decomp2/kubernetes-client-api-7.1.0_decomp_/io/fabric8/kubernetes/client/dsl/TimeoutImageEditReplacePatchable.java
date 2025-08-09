package io.fabric8.kubernetes.client.dsl;

import java.util.concurrent.TimeUnit;

public interface TimeoutImageEditReplacePatchable extends Timeoutable, ImageEditReplacePatchable {
   /** @deprecated */
   @Deprecated
   ImageEditReplacePatchable withTimeout(long var1, TimeUnit var3);

   /** @deprecated */
   @Deprecated
   ImageEditReplacePatchable withTimeoutInMillis(long var1);

   Object pause();

   Object resume();

   Object restart();

   Object undo();
}
