package scala.collection.parallel;

import java.lang.invoke.SerializedLambda;
import scala.Some;
import scala.None.;
import scala.collection.IterableOnceOps;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.ObjectRef;

@ScalaSignature(
   bytes = "\u0006\u0005!4qAD\b\u0011\u0002\u0007\u0005a\u0003C\u0003 \u0001\u0011\u0005\u0001EB\u0004%\u0001A\u0005\u0019\u0011A\u0013\t\u000b}\u0011A\u0011\u0001\u0011\t\u000fe\u0012\u0001\u0019!C\u0001u!9\u0001I\u0001a\u0001\n\u0003\t\u0005b\u0002#\u0003\u0001\u0004%\t!\u0012\u0005\b\u0015\n\u0001\r\u0011\"\u0001L\u0011\u0015i%A\"\u0001O\u0011\u00151&\u0001\"\u0001!\u0011\u00159&\u0001\"\u0001!\u0011\u0015A&\u0001\"\u0001Z\u0011\u0015Q&\u0001\"\u0001!\u0011\u0015Y\u0006A\"\u0005]\u0005e\tE-\u00199uSZ,wk\u001c:l'R,\u0017\r\\5oOR\u000b7o[:\u000b\u0005A\t\u0012\u0001\u00039be\u0006dG.\u001a7\u000b\u0005I\u0019\u0012AC2pY2,7\r^5p]*\tA#A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0007\u000192\u0004\u0005\u0002\u001935\t1#\u0003\u0002\u001b'\t1\u0011I\\=SK\u001a\u0004\"\u0001H\u000f\u000e\u0003=I!AH\b\u0003\u000bQ\u000b7o[:\u0002\r\u0011Jg.\u001b;%)\u0005\t\u0003C\u0001\r#\u0013\t\u00193C\u0001\u0003V]&$(aD!X'R;&/\u00199qK\u0012$\u0016m]6\u0016\u0007\u0019jsgE\u0002\u0003/\u001d\u0002B\u0001K\u0015,m5\t\u0001!\u0003\u0002+;\tYqK]1qa\u0016$G+Y:l!\taS\u0006\u0004\u0001\u0005\u000b9\u0012!\u0019A\u0018\u0003\u0003I\u000b\"\u0001M\u001a\u0011\u0005a\t\u0014B\u0001\u001a\u0014\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u0007\u001b\n\u0005U\u001a\"aA!osB\u0011Af\u000e\u0003\u0006q\t\u0011\ra\f\u0002\u0003)B\fAA\\3yiV\t1\b\u0005\u0003)\u0005-2\u0004F\u0001\u0003>!\tAb(\u0003\u0002@'\tAao\u001c7bi&dW-\u0001\u0005oKb$x\fJ3r)\t\t#\tC\u0004D\u000b\u0005\u0005\t\u0019A\u001e\u0002\u0007a$\u0013'A\u0007tQ>,H\u000eZ,bSR4uN]\u000b\u0002\rB\u0011\u0001dR\u0005\u0003\u0011N\u0011qAQ8pY\u0016\fg\u000e\u000b\u0002\u0007{\u0005\t2\u000f[8vY\u0012<\u0016-\u001b;G_J|F%Z9\u0015\u0005\u0005b\u0005bB\"\b\u0003\u0003\u0005\rAR\u0001\u0006gBd\u0017\u000e^\u000b\u0002\u001fB\u0019\u0001kU\u001e\u000f\u0005a\t\u0016B\u0001*\u0014\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001V+\u0003\u0007M+\u0017O\u0003\u0002S'\u000591m\\7qkR,\u0017\u0001C5oi\u0016\u0014h.\u00197\u0002\u001bM\u0004\u0018m\u001e8Tk\n$\u0018m]6t)\u0005Y\u0014A\u00039sS:$8\t[1j]\u0006qa.Z<Xe\u0006\u0004\b/\u001a3UCN\\WcA/aER\u0011al\u0019\t\u0005Q%z\u0016\r\u0005\u0002-A\u0012)a&\u0004b\u0001_A\u0011AF\u0019\u0003\u0006q5\u0011\ra\f\u0005\u0006I6\u0001\r!Z\u0001\u0002EB!ADZ0b\u0013\t9wB\u0001\u0003UCN\\\u0007"
)
public interface AdaptiveWorkStealingTasks extends Tasks {
   Tasks.WrappedTask newWrappedTask(final Task b);

   static void $init$(final AdaptiveWorkStealingTasks $this) {
   }

   public interface AWSTWrappedTask extends Tasks.WrappedTask {
      AWSTWrappedTask next();

      void next_$eq(final AWSTWrappedTask x$1);

      boolean shouldWaitFor();

      void shouldWaitFor_$eq(final boolean x$1);

      Seq split();

      // $FF: synthetic method
      static void compute$(final AWSTWrappedTask $this) {
         $this.compute();
      }

      default void compute() {
         if (this.body().shouldSplitFurther()) {
            this.internal();
            this.release();
         } else {
            this.body().tryLeaf(.MODULE$);
            this.release();
         }
      }

      // $FF: synthetic method
      static void internal$(final AWSTWrappedTask $this) {
         $this.internal();
      }

      default void internal() {
         AWSTWrappedTask last = this.spawnSubtasks();
         last.body().tryLeaf(.MODULE$);
         last.release();
         this.body().result_$eq(last.body().result());
         this.body().throwable_$eq(last.body().throwable());

         for(; last.next() != null; this.body().tryMerge(last.body().repr())) {
            last = last.next();
            if (last.tryCancel()) {
               last.body().tryLeaf(new Some(this.body().result()));
               last.release();
            } else {
               last.sync();
            }
         }

      }

      // $FF: synthetic method
      static AWSTWrappedTask spawnSubtasks$(final AWSTWrappedTask $this) {
         return $this.spawnSubtasks();
      }

      default AWSTWrappedTask spawnSubtasks() {
         ObjectRef last = ObjectRef.create((Object)null);
         AWSTWrappedTask head = this;

         do {
            Seq subtasks = head.split();
            head = (AWSTWrappedTask)subtasks.head();
            ((IterableOnceOps)((SeqOps)subtasks.tail()).reverse()).foreach((t) -> {
               $anonfun$spawnSubtasks$1(last, t);
               return BoxedUnit.UNIT;
            });
         } while(head.body().shouldSplitFurther());

         head.next_$eq((AWSTWrappedTask)last.elem);
         return head;
      }

      // $FF: synthetic method
      static void printChain$(final AWSTWrappedTask $this) {
         $this.printChain();
      }

      default void printChain() {
         AWSTWrappedTask curr = this;

         String chain;
         for(chain = "chain: "; curr != null; curr = curr.next()) {
            chain = (new StringBuilder(6)).append(chain).append(curr.toString()).append(" ---> ").toString();
         }

         scala.Predef..MODULE$.println(chain);
      }

      // $FF: synthetic method
      AdaptiveWorkStealingTasks scala$collection$parallel$AdaptiveWorkStealingTasks$AWSTWrappedTask$$$outer();

      // $FF: synthetic method
      static void $anonfun$spawnSubtasks$1(final ObjectRef last$1, final AWSTWrappedTask t) {
         t.next_$eq((AWSTWrappedTask)last$1.elem);
         last$1.elem = t;
         t.start();
      }

      static void $init$(final AWSTWrappedTask $this) {
         $this.next_$eq((AWSTWrappedTask)null);
         $this.shouldWaitFor_$eq(true);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
