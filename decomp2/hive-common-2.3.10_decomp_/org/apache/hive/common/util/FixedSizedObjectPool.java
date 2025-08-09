package org.apache.hive.common.util;

import com.google.common.annotations.VisibleForTesting;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.hadoop.hive.common.Pool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FixedSizedObjectPool implements Pool {
   public static final Logger LOG = LoggerFactory.getLogger(FixedSizedObjectPool.class);
   private static final long NO_MARKER = 65535L;
   private static final long NO_DELTA = 255L;
   private static final long MAX_DELTA = 254L;
   private static final long MAX_SIZE = 65534L;
   private static final long NO_INDEX = 0L;
   private static final Marker OBJECTS = new Marker(48, 40, 32);
   private static final Marker EMPTY = new Marker(16, 8, 0);
   private final AtomicLong state;
   private final Pool.PoolObjectHelper helper;
   private final Object[] pool;
   private final CasLog casLog;

   public FixedSizedObjectPool(int size, Pool.PoolObjectHelper helper) {
      this(size, helper, LOG.isTraceEnabled());
   }

   @VisibleForTesting
   public FixedSizedObjectPool(int size, Pool.PoolObjectHelper helper, boolean doTraceLog) {
      if ((long)size > 65534L) {
         throw new AssertionError("Size must be <= 65534");
      } else {
         this.helper = helper;
         T[] poolTmp = (T[])((Object[])(new Object[size]));
         this.pool = poolTmp;
         this.state = new AtomicLong(OBJECTS.setMarker(0L, 65535L));
         this.casLog = doTraceLog ? new CasLog() : null;
      }
   }

   public Object take() {
      T result = (T)(this.pool.length > 0 ? this.takeImpl() : null);
      return result == null ? this.helper.create() : result;
   }

   public void offer(Object t) {
      this.tryOffer(t);
   }

   public int size() {
      return this.pool.length;
   }

   @VisibleForTesting
   public boolean tryOffer(Object t) {
      if (t != null && this.pool.length != 0) {
         this.helper.resetBeforeOffer(t);
         return this.offerImpl(t);
      } else {
         return false;
      }
   }

   private Object takeImpl() {
      long oldState = this.reserveArrayIndex(OBJECTS, EMPTY);
      if (oldState == 0L) {
         return null;
      } else {
         long originalMarker = OBJECTS.getMarker(oldState);
         long delta = OBJECTS.getDelta(oldState);
         int arrayIndex = (int)this.getArrayIndex(originalMarker, delta);
         T result = (T)this.pool[arrayIndex];
         if (result == null) {
            this.throwError(oldState, arrayIndex, "null");
         }

         this.pool[arrayIndex] = null;
         this.commitArrayIndex(OBJECTS, EMPTY, originalMarker);
         return result;
      }
   }

   private boolean offerImpl(Object t) {
      long oldState = this.reserveArrayIndex(EMPTY, OBJECTS);
      if (oldState == 0L) {
         return false;
      } else {
         long originalMarker = EMPTY.getMarker(oldState);
         long delta = EMPTY.getDelta(oldState);
         int arrayIndex = (int)this.getArrayIndex(originalMarker, delta);
         if (this.pool[arrayIndex] != null) {
            this.throwError(oldState, arrayIndex, "non-null");
         }

         this.pool[arrayIndex] = t;
         this.commitArrayIndex(EMPTY, OBJECTS, originalMarker);
         return true;
      }
   }

   private void throwError(long oldState, int arrayIndex, String type) {
      long newState = this.state.get();
      if (this.casLog != null) {
         this.casLog.dumpLog(true);
      }

      String msg = "Unexpected " + type + " at " + arrayIndex + "; state was " + toString(oldState) + ", now " + toString(newState);
      LOG.info(msg);
      throw new AssertionError(msg);
   }

   private long reserveArrayIndex(Marker from, Marker to) {
      long oldVal;
      long newVal;
      do {
         oldVal = this.state.get();
         long marker = from.getMarker(oldVal);
         long delta = from.getDelta(oldVal);
         long rc = from.getRc(oldVal);
         long toMarker = to.getMarker(oldVal);
         long toDelta = to.getDelta(oldVal);
         if (marker == 65535L) {
            return 0L;
         }

         if (delta == 254L) {
            return 0L;
         }

         if (delta == 255L) {
            return 0L;
         }

         if (toDelta == 255L) {
            return 0L;
         }

         assert rc <= delta;

         long newDelta = this.incDeltaValue(marker, toMarker, delta);
         if (newDelta == 255L) {
            return 0L;
         }

         newVal = from.setRc(from.setDelta(oldVal, newDelta), rc + 1L);
      } while(!this.setState(oldVal, newVal));

      return oldVal;
   }

   private void commitArrayIndex(Marker from, Marker to, long originalMarker) {
      long oldVal;
      long newVal;
      do {
         oldVal = this.state.get();
         long rc = from.getRc(oldVal);
         newVal = from.setRc(oldVal, rc - 1L);

         assert rc > 0L;

         if (rc == 1L) {
            long marker = from.getMarker(oldVal);
            long delta = from.getDelta(oldVal);
            long otherMarker = to.getMarker(oldVal);
            long otherDelta = to.getDelta(oldVal);

            assert rc <= delta;

            long newMarker = this.applyDeltaToMarker(marker, otherMarker, delta);
            newVal = from.setDelta(from.setMarker(newVal, newMarker), 0L);
            if (otherMarker == 65535L) {
               assert otherDelta == 0L;

               newVal = to.setMarker(newVal, originalMarker);
            } else if (otherDelta > 0L && otherDelta != 255L && this.applyDeltaToMarker(otherMarker, marker, otherDelta) == 65535L) {
               newVal = to.setDelta(to.setMarker(newVal, originalMarker), 255L);
            }
         }
      } while(!this.setState(oldVal, newVal));

   }

   private boolean setState(long oldVal, long newVal) {
      boolean result = this.state.compareAndSet(oldVal, newVal);
      if (result && this.casLog != null) {
         this.casLog.log(oldVal, newVal);
      }

      return result;
   }

   private long incDeltaValue(long markerFrom, long otherMarker, long delta) {
      if (delta == (long)this.pool.length) {
         return 255L;
      } else {
         long result = delta + 1L;
         return this.getArrayIndex(markerFrom, result) == this.getArrayIndex(otherMarker, 1L) ? 255L : result;
      }
   }

   private long applyDeltaToMarker(long marker, long markerLimit, long delta) {
      if (delta == 255L) {
         return marker;
      } else if (delta == (long)this.pool.length) {
         assert markerLimit == 65535L;

         return 65535L;
      } else {
         marker = this.getArrayIndex(marker, delta);
         return marker == markerLimit ? 65535L : marker;
      }
   }

   private long getArrayIndex(long marker, long delta) {
      marker += delta;
      if (marker >= (long)this.pool.length) {
         marker -= (long)this.pool.length;
      }

      return marker;
   }

   static String toString(long markers) {
      return OBJECTS.toString(markers) + ", " + EMPTY.toString(markers);
   }

   private static final class Marker {
      private static final long MARKER_MASK = 65535L;
      private static final long DELTA_MASK = 255L;
      private static final long RC_MASK = 255L;
      int markerShift;
      int deltaShift;
      int rcShift;

      public Marker(int markerShift, int deltaShift, int rcShift) {
         this.markerShift = markerShift;
         this.deltaShift = deltaShift;
         this.rcShift = rcShift;
      }

      public final long setMarker(long dest, long val) {
         return this.setValue(dest, val, this.markerShift, 65535L);
      }

      public final long setDelta(long dest, long val) {
         return this.setValue(dest, val, this.deltaShift, 255L);
      }

      public final long setRc(long dest, long val) {
         return this.setValue(dest, val, this.rcShift, 255L);
      }

      public final long getMarker(long src) {
         return this.getValue(src, this.markerShift, 65535L);
      }

      public final long getDelta(long src) {
         return this.getValue(src, this.deltaShift, 255L);
      }

      public final long getRc(long src) {
         return this.getValue(src, this.rcShift, 255L);
      }

      private final long setValue(long dest, long val, int offset, long mask) {
         return (dest & ~(mask << offset)) + (val << offset);
      }

      private final long getValue(long src, int offset, long mask) {
         return src >>> offset & mask;
      }

      public String toString(long markers) {
         return "{" + this.getMarker(markers) + ", " + this.getDelta(markers) + ", " + this.getRc(markers) + "}";
      }
   }

   private static final class CasLog {
      private final int size = 16384;
      private final long[] log;
      private final AtomicLong offset = new AtomicLong(-1L);

      public CasLog() {
         this.log = new long[this.size];
      }

      public void log(long oldVal, long newVal) {
         int ix = (int)(this.offset.incrementAndGet() << 1 & (long)(this.size - 1));
         this.log[ix] = oldVal;
         this.log[ix + 1] = newVal;
      }

      public synchronized void dumpLog(boolean doSleep) {
         if (doSleep) {
            try {
               Thread.sleep(100L);
            } catch (InterruptedException var4) {
            }
         }

         int logSize = (int)this.offset.get();

         for(int i = 0; i < logSize; ++i) {
            FixedSizedObjectPool.LOG.info("CAS history dump: " + FixedSizedObjectPool.toString(this.log[i << 1]) + " => " + FixedSizedObjectPool.toString(this.log[(i << 1) + 1]));
         }

         this.offset.set(0L);
      }
   }
}
