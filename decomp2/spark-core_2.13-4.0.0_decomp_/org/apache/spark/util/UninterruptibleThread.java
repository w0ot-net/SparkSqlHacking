package org.apache.spark.util;

import javax.annotation.concurrent.GuardedBy;
import scala.Function0;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mb!B\u000e\u001d\u0001y!\u0003\u0002C\u0017\u0001\u0005\u0003\u0005\u000b\u0011B\u0018\t\u0011I\u0002!\u0011!Q\u0001\nMBQ\u0001\u0011\u0001\u0005\u0002\u0005CQ\u0001\u0011\u0001\u0005\u0002\u00193A\u0001\u0013\u0001\u0005\u0013\")\u0001)\u0002C\u0001\u001d\"9\u0011+\u0002a\u0001\n\u0013\u0011\u0006b\u0002,\u0006\u0001\u0004%Ia\u0016\u0005\u0007;\u0016\u0001\u000b\u0015B*\t\u000f1,\u0001\u0019!C\u0005%\"9Q.\u0002a\u0001\n\u0013q\u0007B\u00029\u0006A\u0003&1\u000bC\u0004s\u000b\u0001\u0007I\u0011\u0002*\t\u000fM,\u0001\u0019!C\u0005i\"1a/\u0002Q!\nMCQ\u0001_\u0003\u0005\u0002eDQa_\u0003\u0005\u0002qDQA`\u0003\u0005\u0002}Da!a\u0001\u0006\t\u0003\u0011\u0006bBA\u0003\u000b\u0011\u0005\u0011q\u0001\u0005\u0007\u0003\u0013)A\u0011\u0001*\t\u0011-\u0004!\u0019!C\u0005\u0003\u0017Aq!!\u0004\u0001A\u0003%q\nC\u0004\u0002\u0010\u0001!\t!!\u0005\t\u000f\u0005U\u0002\u0001\"\u0011\u0002\b!q\u0011q\u0007\u0001\u0011\u0002\u0007\u0005\t\u0011\"\u0003\u0002\b\u0005e\"!F+oS:$XM\u001d:vaRL'\r\\3UQJ,\u0017\r\u001a\u0006\u0003;y\tA!\u001e;jY*\u0011q\u0004I\u0001\u0006gB\f'o\u001b\u0006\u0003C\t\na!\u00199bG\",'\"A\u0012\u0002\u0007=\u0014xm\u0005\u0002\u0001KA\u0011aeK\u0007\u0002O)\u0011\u0001&K\u0001\u0005Y\u0006twMC\u0001+\u0003\u0011Q\u0017M^1\n\u00051:#A\u0002+ie\u0016\fG-\u0001\u0004uCJ<W\r^\u0002\u0001!\t1\u0003'\u0003\u00022O\tA!+\u001e8oC\ndW-\u0001\u0003oC6,\u0007C\u0001\u001b>\u001d\t)4\b\u0005\u00027s5\tqG\u0003\u00029]\u00051AH]8pizR\u0011AO\u0001\u0006g\u000e\fG.Y\u0005\u0003ye\na\u0001\u0015:fI\u00164\u0017B\u0001 @\u0005\u0019\u0019FO]5oO*\u0011A(O\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007\t#U\t\u0005\u0002D\u00015\tA\u0004C\u0003.\u0007\u0001\u0007q\u0006C\u00033\u0007\u0001\u00071\u0007\u0006\u0002C\u000f\")!\u0007\u0002a\u0001g\t\u0019RK\\5oi\u0016\u0014(/\u001e9uS\ndW\rT8dWN\u0011QA\u0013\t\u0003\u00172k\u0011!O\u0005\u0003\u001bf\u0012a!\u00118z%\u00164G#A(\u0011\u0005A+Q\"\u0001\u0001\u0002\u001fUt\u0017N\u001c;feJ,\b\u000f^5cY\u0016,\u0012a\u0015\t\u0003\u0017RK!!V\u001d\u0003\u000f\t{w\u000e\\3b]\u0006\u0019RO\\5oi\u0016\u0014(/\u001e9uS\ndWm\u0018\u0013fcR\u0011\u0001l\u0017\t\u0003\u0017fK!AW\u001d\u0003\tUs\u0017\u000e\u001e\u0005\b9\"\t\t\u00111\u0001T\u0003\rAH%M\u0001\u0011k:Lg\u000e^3seV\u0004H/\u001b2mK\u0002BC!C0jUB\u0011\u0001mZ\u0007\u0002C*\u0011!mY\u0001\u000bG>t7-\u001e:sK:$(B\u00013f\u0003)\tgN\\8uCRLwN\u001c\u0006\u0002M\u0006)!.\u0019<bq&\u0011\u0001.\u0019\u0002\n\u000fV\f'\u000fZ3e\u0005f\fQA^1mk\u0016\f\u0013a[\u0001\u0014k:Lg\u000e^3seV\u0004H/\u001b2mK2{7m[\u0001\u0016g\"|W\u000f\u001c3J]R,'O];qiRC'/Z1e\u0003e\u0019\bn\\;mI&sG/\u001a:skB$H\u000b\u001b:fC\u0012|F%Z9\u0015\u0005a{\u0007b\u0002/\f\u0003\u0003\u0005\raU\u0001\u0017g\"|W\u000f\u001c3J]R,'O];qiRC'/Z1eA!\"AbX5k\u0003Q\tw/Y5u\u0013:$XM\u001d:vaR$\u0006N]3bI\u0006A\u0012m^1ji&sG/\u001a:skB$H\u000b\u001b:fC\u0012|F%Z9\u0015\u0005a+\bb\u0002/\u000f\u0003\u0003\u0005\raU\u0001\u0016C^\f\u0017\u000e^%oi\u0016\u0014(/\u001e9u)\"\u0014X-\u00193!Q\u0011yq,\u001b6\u00021\u001d,G/\u00118e'\u0016$XK\\5oi\u0016\u0014(/\u001e9uS\ndW\r\u0006\u0002Tu\")\u0011\u000e\u0005a\u0001'\u0006A2/\u001a;TQ>,H\u000eZ%oi\u0016\u0014(/\u001e9u)\"\u0014X-\u00193\u0015\u0005ak\b\"B5\u0012\u0001\u0004\u0019\u0016aF:fi\u0006;\u0018-\u001b;J]R,'O];qiRC'/Z1e)\rA\u0016\u0011\u0001\u0005\u0006SJ\u0001\raU\u0001\u0013SNLe\u000e^3seV\u0004H\u000fU3oI&tw-\u0001\tsK\u000e|g/\u001a:J]R,'O];qiR\t\u0001,A\bjg&sG/\u001a:skB$\u0018N\u00197f+\u0005y\u0015\u0001F;oS:$XM\u001d:vaRL'\r\\3M_\u000e\\\u0007%\u0001\nsk:,f.\u001b8uKJ\u0014X\u000f\u001d;jE2LX\u0003BA\n\u00033!B!!\u0006\u0002,A!\u0011qCA\r\u0019\u0001!q!a\u0007\u0019\u0005\u0004\tiBA\u0001U#\u0011\ty\"!\n\u0011\u0007-\u000b\t#C\u0002\u0002$e\u0012qAT8uQ&tw\rE\u0002L\u0003OI1!!\u000b:\u0005\r\te.\u001f\u0005\t\u0003[AB\u00111\u0001\u00020\u0005\ta\rE\u0003L\u0003c\t)\"C\u0002\u00024e\u0012\u0001\u0002\u00102z]\u0006lWMP\u0001\nS:$XM\u001d:vaR\fqb];qKJ$\u0013N\u001c;feJ,\b\u000f^\u0005\u0004\u0003kY\u0003"
)
public class UninterruptibleThread extends Thread {
   private final UninterruptibleLock uninterruptibleLock;

   // $FF: synthetic method
   public void org$apache$spark$util$UninterruptibleThread$$super$interrupt() {
      super.interrupt();
   }

   private UninterruptibleLock uninterruptibleLock() {
      return this.uninterruptibleLock;
   }

   public Object runUninterruptibly(final Function0 f) {
      Thread var10000 = Thread.currentThread();
      if (var10000 == null) {
         if (this != null) {
            throw new IllegalStateException("Call runUninterruptibly in a wrong thread. Expected: " + this + " but was " + Thread.currentThread());
         }
      } else if (!var10000.equals(this)) {
         throw new IllegalStateException("Call runUninterruptibly in a wrong thread. Expected: " + this + " but was " + Thread.currentThread());
      }

      if (this.uninterruptibleLock().getAndSetUninterruptible(true)) {
         return f.apply();
      } else {
         while(this.uninterruptibleLock().isInterruptPending()) {
            try {
               Thread.sleep(100L);
            } catch (InterruptedException var7) {
               this.uninterruptibleLock().setShouldInterruptThread(true);
            }
         }

         try {
            var8 = f.apply();
         } finally {
            this.uninterruptibleLock().recoverInterrupt();
         }

         return var8;
      }
   }

   public void interrupt() {
      if (this.uninterruptibleLock().isInterruptible()) {
         try {
            super.interrupt();
         } finally {
            this.uninterruptibleLock().setAwaitInterruptThread(false);
         }

      }
   }

   public UninterruptibleThread(final Runnable target, final String name) {
      super(target, name);
      this.uninterruptibleLock = new UninterruptibleLock();
   }

   public UninterruptibleThread(final String name) {
      this((Runnable)null, name);
   }

   private class UninterruptibleLock {
      @GuardedBy("uninterruptibleLock")
      private boolean uninterruptible;
      @GuardedBy("uninterruptibleLock")
      private boolean shouldInterruptThread;
      @GuardedBy("uninterruptibleLock")
      private boolean awaitInterruptThread;
      // $FF: synthetic field
      public final UninterruptibleThread $outer;

      private boolean uninterruptible() {
         return this.uninterruptible;
      }

      private void uninterruptible_$eq(final boolean x$1) {
         this.uninterruptible = x$1;
      }

      private boolean shouldInterruptThread() {
         return this.shouldInterruptThread;
      }

      private void shouldInterruptThread_$eq(final boolean x$1) {
         this.shouldInterruptThread = x$1;
      }

      private boolean awaitInterruptThread() {
         return this.awaitInterruptThread;
      }

      private void awaitInterruptThread_$eq(final boolean x$1) {
         this.awaitInterruptThread = x$1;
      }

      public synchronized boolean getAndSetUninterruptible(final boolean value) {
         boolean uninterruptible = this.uninterruptible();
         this.uninterruptible_$eq(value);
         return uninterruptible;
      }

      public synchronized void setShouldInterruptThread(final boolean value) {
         this.shouldInterruptThread_$eq(value);
      }

      public synchronized void setAwaitInterruptThread(final boolean value) {
         this.awaitInterruptThread_$eq(value);
      }

      public synchronized boolean isInterruptPending() {
         this.shouldInterruptThread_$eq(Thread.interrupted() || this.shouldInterruptThread());
         return !this.shouldInterruptThread() && this.awaitInterruptThread();
      }

      public synchronized void recoverInterrupt() {
         this.uninterruptible_$eq(false);
         if (this.shouldInterruptThread()) {
            this.shouldInterruptThread_$eq(false);
            this.org$apache$spark$util$UninterruptibleThread$UninterruptibleLock$$$outer().org$apache$spark$util$UninterruptibleThread$$super$interrupt();
         }
      }

      public synchronized boolean isInterruptible() {
         this.shouldInterruptThread_$eq(this.uninterruptible());
         if (!this.shouldInterruptThread() && !this.awaitInterruptThread()) {
            this.awaitInterruptThread_$eq(true);
            return true;
         } else {
            return false;
         }
      }

      // $FF: synthetic method
      public UninterruptibleThread org$apache$spark$util$UninterruptibleThread$UninterruptibleLock$$$outer() {
         return this.$outer;
      }

      public UninterruptibleLock() {
         if (UninterruptibleThread.this == null) {
            throw null;
         } else {
            this.$outer = UninterruptibleThread.this;
            super();
            this.uninterruptible = false;
            this.shouldInterruptThread = false;
            this.awaitInterruptThread = false;
         }
      }
   }
}
