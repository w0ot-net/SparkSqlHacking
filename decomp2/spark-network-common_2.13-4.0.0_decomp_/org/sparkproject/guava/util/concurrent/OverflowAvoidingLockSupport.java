package org.sparkproject.guava.util.concurrent;

import java.util.concurrent.locks.LockSupport;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.J2ktIncompatible;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
final class OverflowAvoidingLockSupport {
   static final long MAX_NANOSECONDS_THRESHOLD = 2147483647999999999L;

   private OverflowAvoidingLockSupport() {
   }

   static void parkNanos(@CheckForNull Object blocker, long nanos) {
      LockSupport.parkNanos(blocker, Math.min(nanos, 2147483647999999999L));
   }
}
