package scala.collection.immutable;

import java.lang.invoke.SerializedLambda;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]3q!\u0003\u0006\u0011\u0002\u0007\u0005\u0011\u0003C\u00032\u0001\u0011\u0005!\u0007C\u00037\u0001\u0019\u0005q\u0007C\u0003;\u0001\u0011\u00153\bC\u0003>\u0001\u0019\u0005a\bC\u0003A\u0001\u0011\u0015\u0013\tC\u0003H\u0001\u0011\u0005\u0001\nC\u0003O\u0001\u0011\u0005q\nC\u0003U\u0001\u0011\u0015SK\u0001\u0004TKR|\u0005o\u001d\u0006\u0003\u00171\t\u0011\"[7nkR\f'\r\\3\u000b\u00055q\u0011AC2pY2,7\r^5p]*\tq\"A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\tIab\u0005L\n\u0004\u0001M9\u0002C\u0001\u000b\u0016\u001b\u0005q\u0011B\u0001\f\u000f\u0005\u0019\te.\u001f*fMB)\u0001$\u0007\u000e&W5\tA\"\u0003\u0002\n\u0019A\u00111\u0004\b\u0007\u0001\t\u0015i\u0002A1\u0001\u001f\u0005\u0005\t\u0015CA\u0010#!\t!\u0002%\u0003\u0002\"\u001d\t9aj\u001c;iS:<\u0007C\u0001\u000b$\u0013\t!cBA\u0002B]f\u0004\"a\u0007\u0014\u0005\r\u001d\u0002AQ1\u0001)\u0005\t\u00195)\u0006\u0002\u001fS\u0011)!F\nb\u0001=\t\t\u0001\f\u0005\u0002\u001cY\u00111Q\u0006\u0001CC\u00029\u0012\u0011aQ\t\u0003?=\u0002R\u0001\r\u0001\u001bK-j\u0011AC\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003M\u0002\"\u0001\u0006\u001b\n\u0005Ur!\u0001B+oSR\fA!\u001b8dYR\u00111\u0006\u000f\u0005\u0006s\t\u0001\rAG\u0001\u0005K2,W.A\u0003%a2,8\u000f\u0006\u0002,y!)\u0011h\u0001a\u00015\u0005!Q\r_2m)\tYs\bC\u0003:\t\u0001\u0007!$\u0001\u0004%[&tWo\u001d\u000b\u0003W\tCQ!O\u0003A\u0002iA#!\u0002#\u0011\u0005Q)\u0015B\u0001$\u000f\u0005\u0019Ig\u000e\\5oK\u0006!A-\u001b4g)\tY\u0013\nC\u0003K\r\u0001\u00071*\u0001\u0003uQ\u0006$\bc\u0001\rM5%\u0011Q\n\u0004\u0002\u0004'\u0016$\u0018A\u0003:f[>4X\rZ!mYR\u00111\u0006\u0015\u0005\u0006\u0015\u001e\u0001\r!\u0015\t\u00041IS\u0012BA*\r\u00051IE/\u001a:bE2,wJ\\2f\u00031!S.\u001b8vg\u0012j\u0017N\\;t)\tYc\u000bC\u0003K\u0011\u0001\u0007\u0011\u000b"
)
public interface SetOps extends scala.collection.SetOps {
   SetOps incl(final Object elem);

   // $FF: synthetic method
   static SetOps $plus$(final SetOps $this, final Object elem) {
      return $this.$plus(elem);
   }

   default SetOps $plus(final Object elem) {
      return this.incl(elem);
   }

   SetOps excl(final Object elem);

   // $FF: synthetic method
   static SetOps $minus$(final SetOps $this, final Object elem) {
      return $this.$minus(elem);
   }

   default SetOps $minus(final Object elem) {
      return this.excl(elem);
   }

   // $FF: synthetic method
   static SetOps diff$(final SetOps $this, final scala.collection.Set that) {
      return $this.diff(that);
   }

   default SetOps diff(final scala.collection.Set that) {
      return (SetOps)this.foldLeft(this.empty(), (result, elem) -> {
         if (that.contains(elem)) {
            return result;
         } else if (result == null) {
            throw null;
         } else {
            return result.incl(elem);
         }
      });
   }

   // $FF: synthetic method
   static SetOps removedAll$(final SetOps $this, final IterableOnce that) {
      return $this.removedAll(that);
   }

   default SetOps removedAll(final IterableOnce that) {
      return (SetOps)that.iterator().foldLeft(this.coll(), (x$1, x$2) -> x$1.$minus(x$2));
   }

   // $FF: synthetic method
   static SetOps $minus$minus$(final SetOps $this, final IterableOnce that) {
      return $this.$minus$minus(that);
   }

   default SetOps $minus$minus(final IterableOnce that) {
      return this.removedAll(that);
   }

   static void $init$(final SetOps $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
