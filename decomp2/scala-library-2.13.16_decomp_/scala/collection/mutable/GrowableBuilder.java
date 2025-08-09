package scala.collection.mutable;

import scala.Function1;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A3A!\u0003\u0006\u0001#!AQ\u0006\u0001BC\u0002\u0013Ea\u0006\u0003\u00050\u0001\t\u0005\t\u0015!\u0003'\u0011\u0015\u0001\u0004\u0001\"\u00012\u0011\u0015!\u0004\u0001\"\u00016\u0011\u0015I\u0004\u0001\"\u0001;\u0011\u0015Y\u0004\u0001\"\u0001=\u0011\u0015\u0001\u0005\u0001\"\u0011B\u0011\u0015Y\u0005\u0001\"\u0011M\u0005=9%o\\<bE2,')^5mI\u0016\u0014(BA\u0006\r\u0003\u001diW\u000f^1cY\u0016T!!\u0004\b\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\u0010\u0003\u0015\u00198-\u00197b\u0007\u0001)2AE\u000f('\r\u00011c\u0006\t\u0003)Ui\u0011AD\u0005\u0003-9\u0011a!\u00118z%\u00164\u0007\u0003\u0002\r\u001a7\u0019j\u0011AC\u0005\u00035)\u0011qAQ;jY\u0012,'\u000f\u0005\u0002\u001d;1\u0001A!\u0002\u0010\u0001\u0005\u0004y\"\u0001B#mK6\f\"\u0001I\u0012\u0011\u0005Q\t\u0013B\u0001\u0012\u000f\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u0006\u0013\n\u0005\u0015r!aA!osB\u0011Ad\n\u0003\u0006Q\u0001\u0011\r!\u000b\u0002\u0003)>\f\"\u0001\t\u0016\u0011\u0007aY3$\u0003\u0002-\u0015\tAqI]8xC\ndW-A\u0003fY\u0016l7/F\u0001'\u0003\u0019)G.Z7tA\u00051A(\u001b8jiz\"\"AM\u001a\u0011\ta\u00011D\n\u0005\u0006[\r\u0001\rAJ\u0001\u0006G2,\u0017M\u001d\u000b\u0002mA\u0011AcN\u0005\u0003q9\u0011A!\u00168ji\u00061!/Z:vYR$\u0012AJ\u0001\u0007C\u0012$wJ\\3\u0015\u0005urT\"\u0001\u0001\t\u000b}2\u0001\u0019A\u000e\u0002\t\u0015dW-\\\u0001\u0007C\u0012$\u0017\t\u001c7\u0015\u0005u\u0012\u0005\"B\"\b\u0001\u0004!\u0015A\u0001=t!\r)\u0005j\u0007\b\u0003)\u0019K!a\u0012\b\u0002\u000fA\f7m[1hK&\u0011\u0011J\u0013\u0002\r\u0013R,'/\u00192mK>s7-\u001a\u0006\u0003\u000f:\t\u0011b\u001b8po:\u001c\u0016N_3\u0016\u00035\u0003\"\u0001\u0006(\n\u0005=s!aA%oi\u0002"
)
public class GrowableBuilder implements Builder {
   private final Growable elems;

   public void sizeHint(final int size) {
      Builder.sizeHint$(this, size);
   }

   public final void sizeHint(final IterableOnce coll, final int delta) {
      Builder.sizeHint$(this, coll, delta);
   }

   public final int sizeHint$default$2() {
      return Builder.sizeHint$default$2$(this);
   }

   public final void sizeHintBounded(final int size, final scala.collection.Iterable boundingColl) {
      Builder.sizeHintBounded$(this, size, boundingColl);
   }

   public Builder mapResult(final Function1 f) {
      return Builder.mapResult$(this, f);
   }

   public final Growable $plus$eq(final Object elem) {
      return Growable.$plus$eq$(this, elem);
   }

   /** @deprecated */
   public final Growable $plus$eq(final Object elem1, final Object elem2, final scala.collection.immutable.Seq elems) {
      return Growable.$plus$eq$(this, elem1, elem2, elems);
   }

   public final Growable $plus$plus$eq(final IterableOnce elems) {
      return Growable.$plus$plus$eq$(this, elems);
   }

   public Growable elems() {
      return this.elems;
   }

   public void clear() {
      this.elems().clear();
   }

   public Growable result() {
      return this.elems();
   }

   public GrowableBuilder addOne(final Object elem) {
      Growable var10000 = this.elems();
      if (var10000 == null) {
         throw null;
      } else {
         var10000.addOne(elem);
         return this;
      }
   }

   public GrowableBuilder addAll(final IterableOnce xs) {
      this.elems().addAll(xs);
      return this;
   }

   public int knownSize() {
      return this.elems().knownSize();
   }

   public GrowableBuilder(final Growable elems) {
      this.elems = elems;
   }
}
