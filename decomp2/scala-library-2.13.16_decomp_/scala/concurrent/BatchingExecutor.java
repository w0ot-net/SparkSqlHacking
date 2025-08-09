package scala.concurrent;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import scala.Function0;
import scala.Function1;
import scala.reflect.ScalaSignature;
import scala.util.control.NonFatal$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Uf\u0001C\u0013'!\u0003\r\tA\n\u0016\t\u000bi\u0002A\u0011\u0001\u001f\t\u000f\u0005\u0003!\u0019)C\u0007\u0005\u001a1\u0011\n\u0001Q\u0002*)C\u0001bS\u0002\u0003\u0002\u0004%)\u0002\u0014\u0005\t!\u000e\u0011\t\u0019!C\u000b#\"AAk\u0001B\u0001B\u00036Q\n\u0003\u0005V\u0007\t\u0005\r\u0011\"\u0006W\u0011!Q6A!a\u0001\n+Y\u0006\u0002C/\u0004\u0005\u0003\u0005\u000bUB,\t\u0011y\u001b!\u00111A\u0005\u0016}C\u0001bY\u0002\u0003\u0002\u0004%)\u0002\u001a\u0005\tM\u000e\u0011\t\u0011)Q\u0007A\")qm\u0001C\tQ\"1an\u0001Q\u0005\u000e=DQA]\u0002\u0005\u0006MDQA^\u0002\u0005\u0016]4\u0001\"!\u0002\u0001A\u00035\u0011q\u0001\u0005\n\u0003_\t\"\u0011!Q\u0001\n5C\u0011\"!\r\u0012\u0005\u0003\u0005\u000b\u0011B,\t\u0013\u0005M\u0012C!A!\u0002\u0013\u0001\u0007BB4\u0012\t\u0013\t)\u0004\u0003\u0005\u0002@E\u0001\u000bUBA\u0005\u0011\u00199\u0017\u0003\"\u0002\u0002B!1\u0011qI\t\u0005FqBq!!\u0013\u0012\t\u000b\nY\u0005\u0003\u0005\u0002RE\u0001KQBA*\u0011!\tI&\u0005Q\u0005\u000e\u0005m\u0003bBA/#\u0011\u0015\u0013q\f\u0004\t\u0003\u001f\u0003\u0001\u0015!\u0004\u0002\u0012\"I\u0011QI\u000f\u0003\u0002\u0003\u0006I!\u0014\u0005\u0007Ov!\t!a%\t\r\u0005\u001dS\u0004\"\u0012=\u0011\u001d\tY\n\u0001D\t\u0003;Cq!!)\u0001\r#\t\u0019\u000bC\u0004\u0002*\u0002!)\"a+\t\u000f\u0005=\u0006\u0001\"\u0006\u00022\n\u0001\")\u0019;dQ&tw-\u0012=fGV$xN\u001d\u0006\u0003O!\n!bY8oGV\u0014(/\u001a8u\u0015\u0005I\u0013!B:dC2\f7c\u0001\u0001,gA\u0011A&M\u0007\u0002[)\u0011afL\u0001\u0005Y\u0006twMC\u00011\u0003\u0011Q\u0017M^1\n\u0005Ij#AB(cU\u0016\u001cG\u000f\u0005\u00025q5\tQG\u0003\u0002(m)\u0011qgL\u0001\u0005kRLG.\u0003\u0002:k\tAQ\t_3dkR|'/\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u0005i\u0004C\u0001 @\u001b\u0005A\u0013B\u0001!)\u0005\u0011)f.\u001b;\u0002\u0017}#\u0018m]6t\u0019>\u001c\u0017\r\\\u000b\u0002\u0007B\u0019A\u0006\u0012$\n\u0005\u0015k#a\u0003+ie\u0016\fG\rT8dC2\u0004\"AP$\n\u0005!C#AB!osJ+gMA\u0007BEN$(/Y2u\u0005\u0006$8\r[\n\u0003\u0007\u0019\u000bQAZ5sgR,\u0012!\u0014\t\u0003Y9K!aT\u0017\u0003\u0011I+hN\\1cY\u0016\f\u0011BZ5sgR|F%Z9\u0015\u0005u\u0012\u0006bB*\u0006\u0003\u0003\u0005\r!T\u0001\u0004q\u0012\n\u0014A\u00024jeN$\b%A\u0003pi\",'/F\u0001X!\rq\u0004,T\u0005\u00033\"\u0012Q!\u0011:sCf\f\u0011b\u001c;iKJ|F%Z9\u0015\u0005ub\u0006bB*\t\u0003\u0003\u0005\raV\u0001\u0007_RDWM\u001d\u0011\u0002\tML'0Z\u000b\u0002AB\u0011a(Y\u0005\u0003E\"\u00121!\u00138u\u0003!\u0019\u0018N_3`I\u0015\fHCA\u001ff\u0011\u001d\u00196\"!AA\u0002\u0001\fQa]5{K\u0002\na\u0001P5oSRtD\u0003B5lY6\u0004\"A[\u0002\u000e\u0003\u0001AQaS\u0007A\u00025CQ!V\u0007A\u0002]CQAX\u0007A\u0002\u0001\fa\"\u001a8tkJ,7)\u00199bG&$\u0018\u0010\u0006\u0002Xa\")\u0011O\u0004a\u0001A\u000691-\u001e:TSj,\u0017\u0001\u00029vg\"$\"!\u0010;\t\u000bU|\u0001\u0019A'\u0002\u0003I\fAA];o\u001dR\u0011Q\b\u001f\u0005\u0006sB\u0001\r\u0001Y\u0001\u0002]\"\u0012\u0001c\u001f\t\u0003y~l\u0011! \u0006\u0003}\"\n!\"\u00198o_R\fG/[8o\u0013\r\t\t! \u0002\bi\u0006LGN]3dS\r\u0019\u0011#\b\u0002\u000b\u0003NLhn\u0019\"bi\u000eD7cB\tj\u001b\u0006%\u0011\u0011\u0003\t\u0005\u0003\u0017\ti!D\u0001'\u0013\r\tyA\n\u0002\r\u00052|7m[\"p]R,\u0007\u0010\u001e\t\b}\u0005M\u0011\u0011BA\f\u0013\r\t)\u0002\u000b\u0002\n\rVt7\r^5p]F\u0002B!!\u0007\u0002*9!\u00111DA\u0013\u001d\u0011\ti\"a\t\u000e\u0005\u0005}!bAA\u0011w\u00051AH]8pizJ\u0011!K\u0005\u0004\u0003OA\u0013a\u00029bG.\fw-Z\u0005\u0005\u0003W\tiCA\u0005UQJ|w/\u00192mK*\u0019\u0011q\u0005\u0015\u0002\r}3\u0017N]:u\u0003\u0019yv\u000e\u001e5fe\u0006)ql]5{KRA\u0011qGA\u001d\u0003w\ti\u0004\u0005\u0002k#!1\u0011qF\u000bA\u00025Ca!!\r\u0016\u0001\u00049\u0006BBA\u001a+\u0001\u0007\u0001-\u0001\nqCJ,g\u000e\u001e\"m_\u000e\\7i\u001c8uKb$H\u0003BA\u001c\u0003\u0007Ba!!\u0012\u0018\u0001\u0004i\u0015\u0001\u0003:v]:\f'\r\\3\u0002\u0007I,h.A\u0003baBd\u0017\u0010\u0006\u0003\u0002\u0018\u00055\u0003bBA(3\u0001\u0007\u0011\u0011B\u0001\u0011aJ,gO\u00117pG.\u001cuN\u001c;fqR\f\u0001B]3tk\nl\u0017\u000e\u001e\u000b\u0005\u0003/\t)\u0006C\u0004\u0002Xi\u0001\r!a\u0006\u0002\u000b\r\fWo]3\u0002\u001b\rdwN\\3B]\u0012\u001cE.Z1s)\t\t9$A\u0004cY>\u001c7n\u00148\u0016\t\u0005\u0005\u0014\u0011\u000e\u000b\u0005\u0003G\n)\t\u0006\u0003\u0002f\u0005m\u0004\u0003BA4\u0003Sb\u0001\u0001B\u0004\u0002lq\u0011\r!!\u001c\u0003\u0003Q\u000bB!a\u001c\u0002vA\u0019a(!\u001d\n\u0007\u0005M\u0004FA\u0004O_RD\u0017N\\4\u0011\u0007y\n9(C\u0002\u0002z!\u00121!\u00118z\u0011\u001d\ti\b\ba\u0002\u0003\u007f\n!\u0002]3s[&\u001c8/[8o!\u0011\tY!!!\n\u0007\u0005\reE\u0001\u0005DC:\fu/Y5u\u0011!\t9\t\bCA\u0002\u0005%\u0015!\u0002;ik:\\\u0007#\u0002 \u0002\f\u0006\u0015\u0014bAAGQ\tAAHY=oC6,gHA\u0005Ts:\u001c')\u0019;dQN\u0019Q$['\u0015\t\u0005U\u0015q\u0013\t\u0003UvAa!!\u0012 \u0001\u0004i\u0005F\u0001\u0011|\u0003I\u0019XOY7ji\u001a{'/\u0012=fGV$\u0018n\u001c8\u0015\u0007u\ny\n\u0003\u0004\u0002F\u0005\u0002\r!T\u0001\u000ee\u0016\u0004xN\u001d;GC&dWO]3\u0015\u0007u\n)\u000bC\u0004\u0002(\n\u0002\r!a\u0006\u0002\u0013QD'o\\<bE2,\u0017AE:vE6LG/Q:z]\u000e\u0014\u0015\r^2iK\u0012$2!PAW\u0011\u0019\t)e\ta\u0001\u001b\u0006\t2/\u001e2nSR\u001c\u0016P\\2CCR\u001c\u0007.\u001a3\u0015\u0007u\n\u0019\f\u0003\u0004\u0002F\u0011\u0002\r!\u0014"
)
public interface BatchingExecutor extends Executor {
   void scala$concurrent$BatchingExecutor$_setter_$scala$concurrent$BatchingExecutor$$_tasksLocal_$eq(final ThreadLocal x$1);

   ThreadLocal scala$concurrent$BatchingExecutor$$_tasksLocal();

   void submitForExecution(final Runnable runnable);

   void reportFailure(final Throwable throwable);

   // $FF: synthetic method
   static void submitAsyncBatched$(final BatchingExecutor $this, final Runnable runnable) {
      $this.submitAsyncBatched(runnable);
   }

   default void submitAsyncBatched(final Runnable runnable) {
      Object b = this.scala$concurrent$BatchingExecutor$$_tasksLocal().get();
      if (b instanceof AsyncBatch) {
         ((AsyncBatch)b).push(runnable);
      } else {
         this.submitForExecution(new AsyncBatch(runnable));
      }
   }

   // $FF: synthetic method
   static void submitSyncBatched$(final BatchingExecutor $this, final Runnable runnable) {
      $this.submitSyncBatched(runnable);
   }

   default void submitSyncBatched(final Runnable runnable) {
      Objects.requireNonNull(runnable, "runnable is null");
      ThreadLocal tl = this.scala$concurrent$BatchingExecutor$$_tasksLocal();
      Object b = tl.get();
      if (b instanceof SyncBatch) {
         ((SyncBatch)b).push(runnable);
      } else {
         int i = b != null ? (Integer)b : 0;
         if (i < 16) {
            tl.set(i + 1);

            try {
               this.submitForExecution(runnable);
            } catch (InterruptedException var12) {
               this.reportFailure(var12);
            } catch (Throwable var13) {
               if (!NonFatal$.MODULE$.apply(var13)) {
                  throw var13;
               }

               this.reportFailure(var13);
            } finally {
               tl.set(b);
            }

         } else {
            SyncBatch batch = new SyncBatch(runnable);
            tl.set(batch);
            this.submitForExecution(batch);
            tl.set(b);
         }
      }
   }

   static void $init$(final BatchingExecutor $this) {
      $this.scala$concurrent$BatchingExecutor$_setter_$scala$concurrent$BatchingExecutor$$_tasksLocal_$eq(new ThreadLocal());
   }

   private abstract class AbstractBatch {
      private Runnable first;
      private Runnable[] other;
      private int size;
      // $FF: synthetic field
      public final BatchingExecutor $outer;

      public final Runnable first() {
         return this.first;
      }

      public final void first_$eq(final Runnable x$1) {
         this.first = x$1;
      }

      public final Runnable[] other() {
         return this.other;
      }

      public final void other_$eq(final Runnable[] x$1) {
         this.other = x$1;
      }

      public final int size() {
         return this.size;
      }

      public final void size_$eq(final int x$1) {
         this.size = x$1;
      }

      private final Runnable[] ensureCapacity(final int curSize) {
         Runnable[] curOther = this.other();
         int curLen = curOther.length;
         if (curSize <= curLen) {
            return curOther;
         } else {
            int newLen = curLen == 0 ? 4 : curLen << 1;
            if (newLen <= curLen) {
               throw new StackOverflowError((new StringBuilder(43)).append("Space limit of asynchronous stack reached: ").append(curLen).toString());
            } else {
               Runnable[] newOther = new Runnable[newLen];
               System.arraycopy(curOther, 0, newOther, 0, curLen);
               this.other_$eq(newOther);
               return newOther;
            }
         }
      }

      public final void push(final Runnable r) {
         int sz = this.size();
         if (sz == 0) {
            this.first = r;
         } else {
            this.ensureCapacity(sz)[sz - 1] = r;
         }

         this.size_$eq(sz + 1);
      }

      public final void runN(final int n) {
         while(n > 0) {
            int var2 = this.size();
            switch (var2) {
               case 0:
                  return;
               case 1:
                  Runnable next = this.first();
                  this.first_$eq((Runnable)null);
                  this.size_$eq(0);
                  next.run();
                  --n;
                  break;
               default:
                  Runnable[] o = this.other();
                  Runnable next = o[var2 - 2];
                  o[var2 - 2] = null;
                  this.size_$eq(var2 - 1);
                  next.run();
                  --n;
            }
         }

      }

      // $FF: synthetic method
      public BatchingExecutor scala$concurrent$BatchingExecutor$AbstractBatch$$$outer() {
         return this.$outer;
      }

      public AbstractBatch(final Runnable first, final Runnable[] other, final int size) {
         this.first = first;
         this.other = other;
         this.size = size;
         if (BatchingExecutor.this == null) {
            throw null;
         } else {
            this.$outer = BatchingExecutor.this;
            super();
         }
      }
   }

   private final class AsyncBatch extends AbstractBatch implements Runnable, BlockContext, Function1 {
      private BlockContext parentBlockContext;

      public boolean apply$mcZD$sp(final double v1) {
         return Function1.apply$mcZD$sp$(this, v1);
      }

      public double apply$mcDD$sp(final double v1) {
         return Function1.apply$mcDD$sp$(this, v1);
      }

      public float apply$mcFD$sp(final double v1) {
         return Function1.apply$mcFD$sp$(this, v1);
      }

      public int apply$mcID$sp(final double v1) {
         return Function1.apply$mcID$sp$(this, v1);
      }

      public long apply$mcJD$sp(final double v1) {
         return Function1.apply$mcJD$sp$(this, v1);
      }

      public void apply$mcVD$sp(final double v1) {
         Function1.apply$mcVD$sp$(this, v1);
      }

      public boolean apply$mcZF$sp(final float v1) {
         return Function1.apply$mcZF$sp$(this, v1);
      }

      public double apply$mcDF$sp(final float v1) {
         return Function1.apply$mcDF$sp$(this, v1);
      }

      public float apply$mcFF$sp(final float v1) {
         return Function1.apply$mcFF$sp$(this, v1);
      }

      public int apply$mcIF$sp(final float v1) {
         return Function1.apply$mcIF$sp$(this, v1);
      }

      public long apply$mcJF$sp(final float v1) {
         return Function1.apply$mcJF$sp$(this, v1);
      }

      public void apply$mcVF$sp(final float v1) {
         Function1.apply$mcVF$sp$(this, v1);
      }

      public boolean apply$mcZI$sp(final int v1) {
         return Function1.apply$mcZI$sp$(this, v1);
      }

      public double apply$mcDI$sp(final int v1) {
         return Function1.apply$mcDI$sp$(this, v1);
      }

      public float apply$mcFI$sp(final int v1) {
         return Function1.apply$mcFI$sp$(this, v1);
      }

      public int apply$mcII$sp(final int v1) {
         return Function1.apply$mcII$sp$(this, v1);
      }

      public long apply$mcJI$sp(final int v1) {
         return Function1.apply$mcJI$sp$(this, v1);
      }

      public void apply$mcVI$sp(final int v1) {
         Function1.apply$mcVI$sp$(this, v1);
      }

      public boolean apply$mcZJ$sp(final long v1) {
         return Function1.apply$mcZJ$sp$(this, v1);
      }

      public double apply$mcDJ$sp(final long v1) {
         return Function1.apply$mcDJ$sp$(this, v1);
      }

      public float apply$mcFJ$sp(final long v1) {
         return Function1.apply$mcFJ$sp$(this, v1);
      }

      public int apply$mcIJ$sp(final long v1) {
         return Function1.apply$mcIJ$sp$(this, v1);
      }

      public long apply$mcJJ$sp(final long v1) {
         return Function1.apply$mcJJ$sp$(this, v1);
      }

      public void apply$mcVJ$sp(final long v1) {
         Function1.apply$mcVJ$sp$(this, v1);
      }

      public Function1 compose(final Function1 g) {
         return Function1.compose$(this, g);
      }

      public Function1 andThen(final Function1 g) {
         return Function1.andThen$(this, g);
      }

      public String toString() {
         return Function1.toString$(this);
      }

      public final void run() {
         this.scala$concurrent$BatchingExecutor$AsyncBatch$$$outer().scala$concurrent$BatchingExecutor$$_tasksLocal().set(this);
         Throwable f = this.resubmit((Throwable)BlockContext$.MODULE$.usingBlockContext(this, this));
         if (f != null) {
            throw f;
         }
      }

      public final Throwable apply(final BlockContext prevBlockContext) {
         Throwable var10000;
         try {
            this.parentBlockContext = prevBlockContext;
            this.runN(1024);
            var10000 = null;
         } catch (Throwable var5) {
            var10000 = var5;
         } finally {
            this.parentBlockContext = BatchingExecutorStatics.MissingParentBlockContext$.MODULE$;
            this.scala$concurrent$BatchingExecutor$AsyncBatch$$$outer().scala$concurrent$BatchingExecutor$$_tasksLocal().remove();
         }

         return var10000;
      }

      private final Throwable resubmit(final Throwable cause) {
         if (this.size() > 0) {
            try {
               this.scala$concurrent$BatchingExecutor$AsyncBatch$$$outer().submitForExecution(this);
               return cause;
            } catch (Throwable var4) {
               if (NonFatal$.MODULE$.apply(var4)) {
                  ExecutionException e = new ExecutionException("Non-fatal error occurred and resubmission failed, see suppressed exception.", cause);
                  e.addSuppressed(var4);
                  return e;
               } else {
                  return var4;
               }
            }
         } else {
            return cause;
         }
      }

      private final AsyncBatch cloneAndClear() {
         AsyncBatch newBatch = this.scala$concurrent$BatchingExecutor$AsyncBatch$$$outer().new AsyncBatch(this.first(), this.other(), this.size());
         this.first_$eq((Runnable)null);
         this.other_$eq(BatchingExecutorStatics$.MODULE$.emptyBatchArray());
         this.size_$eq(0);
         return newBatch;
      }

      public final Object blockOn(final Function0 thunk, final CanAwait permission) {
         if (this.size() > 0) {
            this.scala$concurrent$BatchingExecutor$AsyncBatch$$$outer().submitForExecution(this.cloneAndClear());
         }

         return this.parentBlockContext.blockOn(thunk, permission);
      }

      // $FF: synthetic method
      public BatchingExecutor scala$concurrent$BatchingExecutor$AsyncBatch$$$outer() {
         return this.$outer;
      }

      private AsyncBatch(final Runnable _first, final Runnable[] _other, final int _size) {
         super(_first, _other, _size);
         this.parentBlockContext = BatchingExecutorStatics.MissingParentBlockContext$.MODULE$;
      }

      public AsyncBatch(final Runnable runnable) {
         this(runnable, BatchingExecutorStatics$.MODULE$.emptyBatchArray(), 1);
      }
   }

   private final class SyncBatch extends AbstractBatch implements Runnable {
      public final void run() {
         do {
            try {
               this.runN(1024);
            } catch (InterruptedException var3) {
               this.scala$concurrent$BatchingExecutor$SyncBatch$$$outer().reportFailure(var3);
            } catch (Throwable var4) {
               if (!NonFatal$.MODULE$.apply(var4)) {
                  throw var4;
               }

               this.scala$concurrent$BatchingExecutor$SyncBatch$$$outer().reportFailure(var4);
            }
         } while(this.size() > 0);

      }

      // $FF: synthetic method
      public BatchingExecutor scala$concurrent$BatchingExecutor$SyncBatch$$$outer() {
         return this.$outer;
      }

      public SyncBatch(final Runnable runnable) {
         super(runnable, BatchingExecutorStatics$.MODULE$.emptyBatchArray(), 1);
      }
   }
}
