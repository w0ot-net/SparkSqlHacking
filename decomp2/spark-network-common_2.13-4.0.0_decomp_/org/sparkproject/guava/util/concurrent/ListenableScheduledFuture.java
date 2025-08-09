package org.sparkproject.guava.util.concurrent;

import java.util.concurrent.ScheduledFuture;
import org.sparkproject.guava.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface ListenableScheduledFuture extends ScheduledFuture, ListenableFuture {
}
