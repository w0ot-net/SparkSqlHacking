package org.sparkproject.guava.util.concurrent;

import com.google.errorprone.annotations.DoNotMock;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

@DoNotMock("Use the methods in Futures (like immediateFuture) or SettableFuture")
@ElementTypesAreNonnullByDefault
public interface ListenableFuture extends Future {
   void addListener(Runnable listener, Executor executor);
}
