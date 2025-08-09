package org.apache.spark.memory;

import javax.annotation.concurrent.GuardedBy;
import scala.Predef.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d3a!\u0003\u0006\u0002\u0002)\u0011\u0002\u0002C\r\u0001\u0005\u0003\u0005\u000b\u0011B\u000e\t\u000b\r\u0002A\u0011\u0001\u0013\t\r!\u0002\u0001\u0015)\u0003*\u0011\u0015I\u0004\u0001\"\u0002;\u0011\u0015Y\u0004\u0001\"\u0002;\u0011\u0015a\u0004\u0001\"\u0002>\u0011\u0015\u0019\u0005\u0001\"\u0002E\u0011\u00151\u0005A\"\u0001;\u0005)iU-\\8ssB{w\u000e\u001c\u0006\u0003\u00171\ta!\\3n_JL(BA\u0007\u000f\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0001#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002#\u0005\u0019qN]4\u0014\u0005\u0001\u0019\u0002C\u0001\u000b\u0018\u001b\u0005)\"\"\u0001\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005a)\"AB!osJ+g-\u0001\u0003m_\u000e\\7\u0001\u0001\t\u00039\u0005j\u0011!\b\u0006\u0003=}\tA\u0001\\1oO*\t\u0001%\u0001\u0003kCZ\f\u0017B\u0001\u0012\u001e\u0005\u0019y%M[3di\u00061A(\u001b8jiz\"\"!J\u0014\u0011\u0005\u0019\u0002Q\"\u0001\u0006\t\u000be\u0011\u0001\u0019A\u000e\u0002\u0013}\u0003xn\u001c7TSj,\u0007C\u0001\u000b+\u0013\tYSC\u0001\u0003M_:<\u0007\u0006B\u0002.oa\u0002\"AL\u001b\u000e\u0003=R!\u0001M\u0019\u0002\u0015\r|gnY;se\u0016tGO\u0003\u00023g\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\u000b\u0003Q\nQA[1wCbL!AN\u0018\u0003\u0013\u001d+\u0018M\u001d3fI\nK\u0018!\u0002<bYV,\u0017%A\r\u0002\u0011A|w\u000e\\*ju\u0016,\u0012!K\u0001\u000b[\u0016lwN]=Ge\u0016,\u0017!E5oGJ,W.\u001a8u!>|GnU5{KR\u0011a(\u0011\t\u0003)}J!\u0001Q\u000b\u0003\tUs\u0017\u000e\u001e\u0005\u0006\u0005\u001a\u0001\r!K\u0001\u0006I\u0016dG/Y\u0001\u0012I\u0016\u001c'/Z7f]R\u0004vn\u001c7TSj,GC\u0001 F\u0011\u0015\u0011u\u00011\u0001*\u0003)iW-\\8ssV\u001bX\r\u001a"
)
public abstract class MemoryPool {
   private final Object lock;
   @GuardedBy("lock")
   private long _poolSize;

   public final long poolSize() {
      synchronized(this.lock){}

      long var2;
      try {
         var2 = this._poolSize;
      } catch (Throwable var5) {
         throw var5;
      }

      return var2;
   }

   public final long memoryFree() {
      synchronized(this.lock){}

      long var2;
      try {
         var2 = this._poolSize - this.memoryUsed();
      } catch (Throwable var5) {
         throw var5;
      }

      return var2;
   }

   public final void incrementPoolSize(final long delta) {
      synchronized(this.lock){}

      try {
         .MODULE$.require(delta >= 0L);
         this._poolSize += delta;
      } catch (Throwable var5) {
         throw var5;
      }

   }

   public final void decrementPoolSize(final long delta) {
      synchronized(this.lock){}

      try {
         .MODULE$.require(delta >= 0L);
         .MODULE$.require(delta <= this._poolSize);
         .MODULE$.require(this._poolSize - delta >= this.memoryUsed());
         this._poolSize -= delta;
      } catch (Throwable var5) {
         throw var5;
      }

   }

   public abstract long memoryUsed();

   public MemoryPool(final Object lock) {
      this.lock = lock;
      this._poolSize = 0L;
   }
}
