package org.apache.curator.shaded.com.google.common.util.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotMock;

@DoNotMock("Use the methods in Futures (like immediateFuture) or SettableFuture")
@ElementTypesAreNonnullByDefault
public interface ListenableFuture extends Future {
   void addListener(Runnable listener, Executor executor);
}
