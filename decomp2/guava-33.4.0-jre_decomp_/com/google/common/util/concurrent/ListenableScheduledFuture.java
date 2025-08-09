package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import java.util.concurrent.ScheduledFuture;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface ListenableScheduledFuture extends ScheduledFuture, ListenableFuture {
}
