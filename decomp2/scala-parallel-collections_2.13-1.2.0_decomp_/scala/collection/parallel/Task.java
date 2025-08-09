package scala.collection.parallel;

import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction0;
import scala.util.control.Breaks.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005aaB\t\u0013!\u0003\r\t!\u0007\u0005\u0006?\u0001!\t\u0001I\u0003\u0005I\u0001\u0001Q\u0005C\u00031\u0001\u0011\u0005\u0011\u0007C\u00036\u0001\u0019\u0005a\u0007C\u00049\u0001\u0001\u0007i\u0011\u0001\u001f\t\u000fu\u0002\u0001\u0019!D\u0001}!)\u0011\t\u0001D\u0001\u0005\"1a\t\u0001D\u0001%\u001dCa!\u0015\u0001\u0005\u0002I\u0011\u0006bB0\u0001\u0001\u0004%\t\u0001\u0019\u0005\bQ\u0002\u0001\r\u0011\"\u0001j\u0011\u0015Y\u0007\u0001\"\u0001!\u0011\u0019a\u0007\u0001\"\u0001\u0013[\"1\u0001\u000f\u0001C\u0001%EDa\u0001\u001e\u0001\u0005\u0002I)\bBB@\u0001\t\u0003\u0011\u0002E\u0001\u0003UCN\\'BA\n\u0015\u0003!\u0001\u0018M]1mY\u0016d'BA\u000b\u0017\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002/\u0005)1oY1mC\u000e\u0001Qc\u0001\u000e(gM\u0011\u0001a\u0007\t\u00039ui\u0011AF\u0005\u0003=Y\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001\"!\ta\"%\u0003\u0002$-\t!QK\\5u\u0005\u0019\u0011Vm];miB\u0011ae\n\u0007\u0001\t\u0015A\u0003A1\u0001*\u0005\u0005\u0011\u0016C\u0001\u0016.!\ta2&\u0003\u0002--\t9aj\u001c;iS:<\u0007C\u0001\u000f/\u0013\tycCA\u0002B]f\fAA]3qeV\t!\u0007\u0005\u0002'g\u00111A\u0007\u0001CC\u0002%\u0012!\u0001\u00169\u0002\t1,\u0017M\u001a\u000b\u0003C]BQ\u0001\u000f\u0003A\u0002e\naA]3tk2$\bc\u0001\u000f;K%\u00111H\u0006\u0002\u0007\u001fB$\u0018n\u001c8\u0016\u0003\u0015\n!B]3tk2$x\fJ3r)\t\ts\bC\u0004A\r\u0005\u0005\t\u0019A\u0013\u0002\u0007a$\u0013'\u0001\ntQ>,H\u000eZ*qY&$h)\u001e:uQ\u0016\u0014X#A\"\u0011\u0005q!\u0015BA#\u0017\u0005\u001d\u0011un\u001c7fC:\fQa\u001d9mSR,\u0012\u0001\u0013\t\u0004\u00132{eB\u0001\u000fK\u0013\tYe#A\u0004qC\u000e\\\u0017mZ3\n\u00055s%aA*fc*\u00111J\u0006\t\u0005!\u0002)#'D\u0001\u0013\u0003\u0015iWM]4f)\t\t3\u000bC\u0003U\u0013\u0001\u0007Q+\u0001\u0003uQ\u0006$(F\u0001\u001aWW\u00059\u0006C\u0001-^\u001b\u0005I&B\u0001.\\\u0003%)hn\u00195fG.,GM\u0003\u0002]-\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005yK&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006IA\u000f\u001b:po\u0006\u0014G.Z\u000b\u0002CB\u0011\u0011JY\u0005\u0003G:\u0013\u0011\u0002\u00165s_^\f'\r\\3)\u0005))\u0007C\u0001\u000fg\u0013\t9gC\u0001\u0005w_2\fG/\u001b7f\u00035!\bN]8xC\ndWm\u0018\u0013fcR\u0011\u0011E\u001b\u0005\b\u0001.\t\t\u00111\u0001b\u0003A1wN]<be\u0012$\u0006N]8xC\ndW-A\u0004uefdU-\u00194\u0015\u0005\u0005r\u0007\"B8\u000e\u0001\u0004I\u0014a\u00027bgR\u0014Xm]\u0001\tiJLX*\u001a:hKR\u0011\u0011E\u001d\u0005\u0006g:\u0001\r!V\u0001\u0002i\u0006yQ.\u001a:hKRC'o\\<bE2,7\u000f\u0006\u0002\"m\")Ak\u0004a\u0001oB\u001a\u0001P_?\u0011\tA\u0003\u0011\u0010 \t\u0003Mi$\u0011b\u001f<\u0002\u0002\u0003\u0005)\u0011A\u0015\u0003\u0011\u0011\nX.\u0019:lIE\u0002\"AJ?\u0005\u0013y4\u0018\u0011!A\u0001\u0006\u0003I#\u0001\u0003\u0013r[\u0006\u00148\u000e\n\u001a\u0002\u0017MLwM\\1m\u0003\n|'\u000f\u001e"
)
public interface Task {
   // $FF: synthetic method
   static Object repr$(final Task $this) {
      return $this.repr();
   }

   default Object repr() {
      return this;
   }

   void leaf(final Option result);

   Object result();

   void result_$eq(final Object x$1);

   boolean shouldSplitFurther();

   Seq split();

   // $FF: synthetic method
   static void merge$(final Task $this, final Object that) {
      $this.merge(that);
   }

   default void merge(final Object that) {
   }

   Throwable throwable();

   void throwable_$eq(final Throwable x$1);

   // $FF: synthetic method
   static void forwardThrowable$(final Task $this) {
      $this.forwardThrowable();
   }

   default void forwardThrowable() {
      if (this.throwable() != null) {
         throw this.throwable();
      }
   }

   // $FF: synthetic method
   static void tryLeaf$(final Task $this, final Option lastres) {
      $this.tryLeaf(lastres);
   }

   default void tryLeaf(final Option lastres) {
      try {
         .MODULE$.tryBreakable((JFunction0.mcV.sp)() -> {
            this.leaf(lastres);
            this.result_$eq(this.result());
         }).catchBreak((JFunction0.mcV.sp)() -> this.signalAbort());
      } catch (Throwable var3) {
         this.result_$eq(this.result());
         this.throwable_$eq(var3);
         this.signalAbort();
      }

   }

   // $FF: synthetic method
   static void tryMerge$(final Task $this, final Object t) {
      $this.tryMerge(t);
   }

   default void tryMerge(final Object t) {
      Task that = (Task)t;
      if (this.throwable() == null && that.throwable() == null) {
         this.merge(t);
      }

      this.mergeThrowables(that);
   }

   // $FF: synthetic method
   static void mergeThrowables$(final Task $this, final Task that) {
      $this.mergeThrowables(that);
   }

   default void mergeThrowables(final Task that) {
      if (this.throwable() != null) {
         if (that.throwable() != null && this.throwable() != that.throwable()) {
            this.throwable().addSuppressed(that.throwable());
         }
      } else if (that.throwable() != null) {
         this.throwable_$eq(that.throwable());
      }
   }

   // $FF: synthetic method
   static void signalAbort$(final Task $this) {
      $this.signalAbort();
   }

   default void signalAbort() {
   }

   static void $init$(final Task $this) {
      $this.throwable_$eq((Throwable)null);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
