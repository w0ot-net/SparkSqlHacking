package org.apache.curator.shaded.com.google.common.util.concurrent;

import java.util.concurrent.ScheduledFuture;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface ListenableScheduledFuture extends ScheduledFuture, ListenableFuture {
}
