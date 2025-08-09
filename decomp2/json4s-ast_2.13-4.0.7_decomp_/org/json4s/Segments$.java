package org.json4s;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import scala.Option;
import scala.Some;
import scala.runtime.BoxedUnit;
import scala.sys.package.;

public final class Segments$ {
   public static final Segments$ MODULE$;
   private static final boolean enableSegments;
   private static int segmentSize;
   private static final int maxNumOfSegments;
   private static final AtomicInteger segmentCount;
   private static final ArrayBlockingQueue segments;

   static {
      boolean var1;
      label17: {
         label16: {
            MODULE$ = new Segments$();
            Option var10000 = .MODULE$.props().get("json4s.segments.enable");
            Some var0 = new Some("false");
            if (var10000 == null) {
               if (var0 != null) {
                  break label16;
               }
            } else if (!var10000.equals(var0)) {
               break label16;
            }

            var1 = false;
            break label17;
         }

         var1 = true;
      }

      enableSegments = var1;
      segmentSize = ParserUtil$.MODULE$.defaultSegmentSize();
      maxNumOfSegments = 10000;
      segmentCount = new AtomicInteger(0);
      segments = new ArrayBlockingQueue(maxNumOfSegments);
   }

   public int segmentSize() {
      return segmentSize;
   }

   public void segmentSize_$eq(final int x$1) {
      segmentSize = x$1;
   }

   public void clear() {
      segments.clear();
   }

   public Segment apply() {
      Object var10000;
      if (enableSegments) {
         Segment s = this.acquire();
         var10000 = s != null ? s : new Segments.DisposableSegment(new char[this.segmentSize()]);
      } else {
         var10000 = new Segments.DisposableSegment(new char[this.segmentSize()]);
      }

      return (Segment)var10000;
   }

   private Segment acquire() {
      int curCount = segmentCount.get();
      boolean createNew = segments.size() == 0 && curCount < maxNumOfSegments ? segmentCount.compareAndSet(curCount, curCount + 1) : false;
      return (Segment)(createNew ? new Segments.RecycledSegment(new char[this.segmentSize()]) : (Segment)segments.poll());
   }

   public void release(final Segment s) {
      if (s instanceof Segments.RecycledSegment) {
         segments.offer(s);
         BoxedUnit var2 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var4 = BoxedUnit.UNIT;
      }

   }

   private Segments$() {
   }
}
