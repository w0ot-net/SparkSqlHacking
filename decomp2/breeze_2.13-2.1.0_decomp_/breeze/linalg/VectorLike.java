package breeze.linalg;

import breeze.linalg.support.CanMapValues;
import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00014q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u00033\u0001\u0011\u00051\u0007C\u00038\u0001\u0011\u0005\u0001\bC\u0003Y\u0001\u0011\u0005\u0011L\u0001\u0006WK\u000e$xN\u001d'jW\u0016T!AB\u0004\u0002\r1Lg.\u00197h\u0015\u0005A\u0011A\u00022sK\u0016TXm\u0001\u0001\u0016\u0007-YBf\u0005\u0003\u0001\u0019IA\u0003CA\u0007\u0011\u001b\u0005q!\"A\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Eq!AB!osJ+g\r\u0005\u0003\u0014)YIR\"A\u0003\n\u0005U)!A\u0002+f]N|'\u000f\u0005\u0002\u000e/%\u0011\u0001D\u0004\u0002\u0004\u0013:$\bC\u0001\u000e\u001c\u0019\u0001!\u0011\u0002\b\u0001!\u0002\u0003\u0005)\u0019A\u000f\u0003\u0003Y\u000b\"AH\u0011\u0011\u00055y\u0012B\u0001\u0011\u000f\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\u0004\u0012\n\u0005\rr!aA!os\"\u00121$\n\t\u0003\u001b\u0019J!a\n\b\u0003\u0017M\u0004XmY5bY&TX\r\u001a\t\u0006'%2\u0012dK\u0005\u0003U\u0015\u0011!\u0002V3og>\u0014H*[6f!\tQB\u0006\u0002\u0004.\u0001\u0011\u0015\rA\f\u0002\u0005'\u0016dg-\u0005\u0002\u001f_A\u00191\u0003M\r\n\u0005E*!A\u0002,fGR|'/\u0001\u0004%S:LG\u000f\n\u000b\u0002iA\u0011Q\"N\u0005\u0003m9\u0011A!\u00168ji\u0006\u0019Q.\u00199\u0016\u0007e\nF\b\u0006\u0002;'R\u00111H\u0010\t\u00035q\"Q!\u0010\u0002C\u0002u\u0011A\u0001\u00165bi\")qH\u0001a\u0002\u0001\u0006a1-\u00198NCB4\u0016\r\\;fgB1\u0011\t\u0012$\u001a!nj\u0011A\u0011\u0006\u0003\u0007\u0016\tqa];qa>\u0014H/\u0003\u0002F\u0005\na1)\u00198NCB4\u0016\r\\;fg*\u00121fR\u0016\u0002\u0011B\u0011\u0011JT\u0007\u0002\u0015*\u00111\nT\u0001\nk:\u001c\u0007.Z2lK\u0012T!!\u0014\b\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002P\u0015\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0011\u0005i\tF!\u0002*\u0003\u0005\u0004i\"A\u0001,3\u0011\u0015!&\u00011\u0001V\u0003\t1g\u000e\u0005\u0003\u000e-f\u0001\u0016BA,\u000f\u0005%1UO\\2uS>t\u0017'A\u0004g_J,\u0017m\u00195\u0016\u0005isFC\u0001\u001b\\\u0011\u0015!6\u00011\u0001]!\u0011ia+G/\u0011\u0005iqF!B0\u0004\u0005\u0004i\"!A+"
)
public interface VectorLike extends Tensor {
   // $FF: synthetic method
   static Object map$(final VectorLike $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map(fn, canMapValues);
   }

   default Object map(final Function1 fn, final CanMapValues canMapValues) {
      return this.values().map(fn, canMapValues);
   }

   // $FF: synthetic method
   static void foreach$(final VectorLike $this, final Function1 fn) {
      $this.foreach(fn);
   }

   default void foreach(final Function1 fn) {
      this.values().foreach(fn);
   }

   // $FF: synthetic method
   static Object map$mcZ$sp$(final VectorLike $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map$mcZ$sp(fn, canMapValues);
   }

   default Object map$mcZ$sp(final Function1 fn, final CanMapValues canMapValues) {
      return this.map(fn, canMapValues);
   }

   // $FF: synthetic method
   static Object map$mcB$sp$(final VectorLike $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map$mcB$sp(fn, canMapValues);
   }

   default Object map$mcB$sp(final Function1 fn, final CanMapValues canMapValues) {
      return this.map(fn, canMapValues);
   }

   // $FF: synthetic method
   static Object map$mcC$sp$(final VectorLike $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map$mcC$sp(fn, canMapValues);
   }

   default Object map$mcC$sp(final Function1 fn, final CanMapValues canMapValues) {
      return this.map(fn, canMapValues);
   }

   // $FF: synthetic method
   static Object map$mcD$sp$(final VectorLike $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map$mcD$sp(fn, canMapValues);
   }

   default Object map$mcD$sp(final Function1 fn, final CanMapValues canMapValues) {
      return this.map(fn, canMapValues);
   }

   // $FF: synthetic method
   static Object map$mcF$sp$(final VectorLike $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map$mcF$sp(fn, canMapValues);
   }

   default Object map$mcF$sp(final Function1 fn, final CanMapValues canMapValues) {
      return this.map(fn, canMapValues);
   }

   // $FF: synthetic method
   static Object map$mcI$sp$(final VectorLike $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map$mcI$sp(fn, canMapValues);
   }

   default Object map$mcI$sp(final Function1 fn, final CanMapValues canMapValues) {
      return this.map(fn, canMapValues);
   }

   // $FF: synthetic method
   static Object map$mcJ$sp$(final VectorLike $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map$mcJ$sp(fn, canMapValues);
   }

   default Object map$mcJ$sp(final Function1 fn, final CanMapValues canMapValues) {
      return this.map(fn, canMapValues);
   }

   // $FF: synthetic method
   static Object map$mcS$sp$(final VectorLike $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map$mcS$sp(fn, canMapValues);
   }

   default Object map$mcS$sp(final Function1 fn, final CanMapValues canMapValues) {
      return this.map(fn, canMapValues);
   }

   // $FF: synthetic method
   static Object map$mcV$sp$(final VectorLike $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map$mcV$sp(fn, canMapValues);
   }

   default Object map$mcV$sp(final Function1 fn, final CanMapValues canMapValues) {
      return this.map(fn, canMapValues);
   }

   // $FF: synthetic method
   static void foreach$mcZ$sp$(final VectorLike $this, final Function1 fn) {
      $this.foreach$mcZ$sp(fn);
   }

   default void foreach$mcZ$sp(final Function1 fn) {
      this.foreach(fn);
   }

   // $FF: synthetic method
   static void foreach$mcB$sp$(final VectorLike $this, final Function1 fn) {
      $this.foreach$mcB$sp(fn);
   }

   default void foreach$mcB$sp(final Function1 fn) {
      this.foreach(fn);
   }

   // $FF: synthetic method
   static void foreach$mcC$sp$(final VectorLike $this, final Function1 fn) {
      $this.foreach$mcC$sp(fn);
   }

   default void foreach$mcC$sp(final Function1 fn) {
      this.foreach(fn);
   }

   // $FF: synthetic method
   static void foreach$mcD$sp$(final VectorLike $this, final Function1 fn) {
      $this.foreach$mcD$sp(fn);
   }

   default void foreach$mcD$sp(final Function1 fn) {
      this.foreach(fn);
   }

   // $FF: synthetic method
   static void foreach$mcF$sp$(final VectorLike $this, final Function1 fn) {
      $this.foreach$mcF$sp(fn);
   }

   default void foreach$mcF$sp(final Function1 fn) {
      this.foreach(fn);
   }

   // $FF: synthetic method
   static void foreach$mcI$sp$(final VectorLike $this, final Function1 fn) {
      $this.foreach$mcI$sp(fn);
   }

   default void foreach$mcI$sp(final Function1 fn) {
      this.foreach(fn);
   }

   // $FF: synthetic method
   static void foreach$mcJ$sp$(final VectorLike $this, final Function1 fn) {
      $this.foreach$mcJ$sp(fn);
   }

   default void foreach$mcJ$sp(final Function1 fn) {
      this.foreach(fn);
   }

   // $FF: synthetic method
   static void foreach$mcS$sp$(final VectorLike $this, final Function1 fn) {
      $this.foreach$mcS$sp(fn);
   }

   default void foreach$mcS$sp(final Function1 fn) {
      this.foreach(fn);
   }

   // $FF: synthetic method
   static void foreach$mcV$sp$(final VectorLike $this, final Function1 fn) {
      $this.foreach$mcV$sp(fn);
   }

   default void foreach$mcV$sp(final Function1 fn) {
      this.foreach(fn);
   }

   static void $init$(final VectorLike $this) {
   }
}
