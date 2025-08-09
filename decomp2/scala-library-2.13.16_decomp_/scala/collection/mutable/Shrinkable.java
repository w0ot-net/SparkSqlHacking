package scala.collection.mutable;

import java.lang.invoke.SerializedLambda;
import scala.collection.IterableOnce;
import scala.collection.LinearSeq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E3qa\u0002\u0005\u0011\u0002\u0007\u0005q\u0002C\u0003\u0016\u0001\u0011\u0005a\u0003C\u0003\u001b\u0001\u0019\u00051\u0004C\u0003+\u0001\u0011\u00151\u0006C\u0003+\u0001\u0011\u0005\u0011\u0007C\u0003F\u0001\u0011\u0005a\tC\u0003N\u0001\u0011\u0015aJ\u0001\u0006TQJLgn[1cY\u0016T!!\u0003\u0006\u0002\u000f5,H/\u00192mK*\u00111\u0002D\u0001\u000bG>dG.Z2uS>t'\"A\u0007\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u0011\u0001#I\n\u0003\u0001E\u0001\"AE\n\u000e\u00031I!\u0001\u0006\u0007\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\tq\u0003\u0005\u0002\u00131%\u0011\u0011\u0004\u0004\u0002\u0005+:LG/A\u0006tk\n$(/Y2u\u001f:,GC\u0001\u000f\u001e\u001b\u0005\u0001\u0001\"\u0002\u0010\u0003\u0001\u0004y\u0012\u0001B3mK6\u0004\"\u0001I\u0011\r\u0001\u00111!\u0005\u0001EC\u0002\r\u0012\u0011!Q\t\u0003I\u001d\u0002\"AE\u0013\n\u0005\u0019b!a\u0002(pi\"Lgn\u001a\t\u0003%!J!!\u000b\u0007\u0003\u0007\u0005s\u00170A\u0005%[&tWo\u001d\u0013fcR\u0011A\u0004\f\u0005\u0006=\r\u0001\ra\b\u0015\u0003\u00079\u0002\"AE\u0018\n\u0005Ab!AB5oY&tW\r\u0006\u0003\u001deQ2\u0004\"B\u001a\u0005\u0001\u0004y\u0012!B3mK6\f\u0004\"B\u001b\u0005\u0001\u0004y\u0012!B3mK6\u0014\u0004\"B\u001c\u0005\u0001\u0004A\u0014!B3mK6\u001c\bc\u0001\n:?%\u0011!\b\u0004\u0002\u000byI,\u0007/Z1uK\u0012t\u0004F\u0002\u0003=\u007f\u0001\u00135\t\u0005\u0002\u0013{%\u0011a\b\u0004\u0002\u000bI\u0016\u0004(/Z2bi\u0016$\u0017aB7fgN\fw-Z\u0011\u0002\u0003\u0006IXk]3!A6jS\b\u0019\u0011bW\u0006\u0004\u0003m];ciJ\f7\r^!mY\u0002\u0004\u0013N\\:uK\u0006$\u0007e\u001c4!m\u0006\u0014\u0018M]4tA\u0001lS\bY\u001e!S:4\u0017\u000e\u001f\u0011pa\u0016\u0014\u0018\r^5p]N\u0004s/\u001b;iA\u0005t\u0007e\u001c9fe\u0006tG\rI8gA5,H\u000e^5qY\u0016\u0004\u0013M]4tA]LG\u000e\u001c\u0011cK\u0002\"W\r\u001d:fG\u0006$X\rZ\u0001\u0006g&t7-Z\u0011\u0002\t\u00061!GL\u00194]M\n1b];ciJ\f7\r^!mYR\u0011Ad\u0012\u0005\u0006\u0011\u0016\u0001\r!S\u0001\u0003qN\u00042AS& \u001b\u0005Q\u0011B\u0001'\u000b\u00051IE/\u001a:bE2,wJ\\2f\u0003=!S.\u001b8vg\u0012j\u0017N\\;tI\u0015\fHC\u0001\u000fP\u0011\u0015Ae\u00011\u0001JQ\t1a\u0006"
)
public interface Shrinkable {
   Shrinkable subtractOne(final Object elem);

   // $FF: synthetic method
   static Shrinkable $minus$eq$(final Shrinkable $this, final Object elem) {
      return $this.$minus$eq(elem);
   }

   default Shrinkable $minus$eq(final Object elem) {
      return this.subtractOne(elem);
   }

   // $FF: synthetic method
   static Shrinkable $minus$eq$(final Shrinkable $this, final Object elem1, final Object elem2, final scala.collection.immutable.Seq elems) {
      return $this.$minus$eq(elem1, elem2, elems);
   }

   /** @deprecated */
   default Shrinkable $minus$eq(final Object elem1, final Object elem2, final scala.collection.immutable.Seq elems) {
      this.subtractOne(elem1);
      this.subtractOne(elem2);
      return this.subtractAll(elems);
   }

   // $FF: synthetic method
   static Shrinkable subtractAll$(final Shrinkable $this, final IterableOnce xs) {
      return $this.subtractAll(xs);
   }

   default Shrinkable subtractAll(final IterableOnce xs) {
      if (xs == this) {
         if (xs instanceof Clearable) {
            ((Clearable)xs).clear();
         } else {
            this.subtractAll(Buffer$.MODULE$.from(xs));
         }
      } else if (xs instanceof LinearSeq) {
         LinearSeq var2 = (LinearSeq)xs;
         this.loop$1(var2);
      } else {
         xs.iterator().foreach((elem) -> this.subtractOne(elem));
      }

      return this;
   }

   // $FF: synthetic method
   static Shrinkable $minus$minus$eq$(final Shrinkable $this, final IterableOnce xs) {
      return $this.$minus$minus$eq(xs);
   }

   default Shrinkable $minus$minus$eq(final IterableOnce xs) {
      return this.subtractAll(xs);
   }

   private void loop$1(final LinearSeq xs) {
      while(xs.nonEmpty()) {
         this.subtractOne(xs.head());
         xs = (LinearSeq)xs.tail();
      }

   }

   static void $init$(final Shrinkable $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
