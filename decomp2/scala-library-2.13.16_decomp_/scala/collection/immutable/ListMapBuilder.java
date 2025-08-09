package scala.collection.immutable;

import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.MapView;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Growable;
import scala.collection.mutable.LinkedHashMap;
import scala.collection.mutable.ReusableBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u00054QAC\u0006\u0003\u0017EAQa\r\u0001\u0005\u0002QBaA\u000e\u0001!B\u00139\u0004B\u0002\u001e\u0001A\u0003&q\u0006C\u0003<\u0001\u0011\u0005C\bC\u0003A\u0001\u0011\u0005\u0013\tC\u0003C\u0001\u0011\u00053\t\u0003\u0004H\u0001\u0001&I\u0001\u0013\u0005\u0006\u0005\u0002!\tA\u0016\u0005\u00063\u0002!\tE\u0017\u0002\u000f\u0019&\u001cH/T1q\u0005VLG\u000eZ3s\u0015\taQ\"A\u0005j[6,H/\u00192mK*\u0011abD\u0001\u000bG>dG.Z2uS>t'\"\u0001\t\u0002\u000bM\u001c\u0017\r\\1\u0016\u0007I\u0011SfE\u0002\u0001']\u0001\"\u0001F\u000b\u000e\u0003=I!AF\b\u0003\r\u0005s\u0017PU3g!\u0011A2$H\u0018\u000e\u0003eQ!AG\u0007\u0002\u000f5,H/\u00192mK&\u0011A$\u0007\u0002\u0010%\u0016,8/\u00192mK\n+\u0018\u000e\u001c3feB!AC\b\u0011-\u0013\tyrB\u0001\u0004UkBdWM\r\t\u0003C\tb\u0001\u0001B\u0003$\u0001\t\u0007QEA\u0001L\u0007\u0001\t\"AJ\u0015\u0011\u0005Q9\u0013B\u0001\u0015\u0010\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u0006\u0016\n\u0005-z!aA!osB\u0011\u0011%\f\u0003\u0006]\u0001\u0011\r!\n\u0002\u0002-B!\u0001'\r\u0011-\u001b\u0005Y\u0011B\u0001\u001a\f\u0005\u001da\u0015n\u001d;NCB\fa\u0001P5oSRtD#A\u001b\u0011\tA\u0002\u0001\u0005L\u0001\nSN\fE.[1tK\u0012\u0004\"\u0001\u0006\u001d\n\u0005ez!a\u0002\"p_2,\u0017M\\\u0001\u000bk:$WM\u001d7zS:<\u0017!B2mK\u0006\u0014H#A\u001f\u0011\u0005Qq\u0014BA \u0010\u0005\u0011)f.\u001b;\u0002\rI,7/\u001e7u)\u0005y\u0013AB1eI>sW\r\u0006\u0002E\u000b6\t\u0001\u0001C\u0003G\r\u0001\u0007Q$\u0001\u0003fY\u0016l\u0017aG5og\u0016\u0014HOV1mk\u0016\fEoS3z%\u0016$XO\u001d8G_VtG\r\u0006\u00038\u0013.k\u0005\"\u0002&\b\u0001\u0004y\u0013!A7\t\u000b1;\u0001\u0019\u0001\u0011\u0002\u0007-,\u0017\u0010C\u0003O\u000f\u0001\u0007A&A\u0003wC2,X\r\u000b\u0002\b!B\u0011\u0011\u000bV\u0007\u0002%*\u00111kD\u0001\u000bC:tw\u000e^1uS>t\u0017BA+S\u0005\u001d!\u0018-\u001b7sK\u000e$2\u0001R,Y\u0011\u0015a\u0005\u00021\u0001!\u0011\u0015q\u0005\u00021\u0001-\u0003\u0019\tG\rZ!mYR\u0011Ai\u0017\u0005\u00069&\u0001\r!X\u0001\u0003qN\u00042AX0\u001e\u001b\u0005i\u0011B\u00011\u000e\u00051IE/\u001a:bE2,wJ\\2f\u0001"
)
public final class ListMapBuilder implements ReusableBuilder {
   private boolean isAliased = false;
   private ListMap underlying;

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
   public final Growable $plus$eq(final Object elem1, final Object elem2, final Seq elems) {
      return Growable.$plus$eq$(this, elem1, elem2, elems);
   }

   public final Growable $plus$plus$eq(final IterableOnce elems) {
      return Growable.$plus$plus$eq$(this, elems);
   }

   public int knownSize() {
      return Growable.knownSize$(this);
   }

   public void clear() {
      ListMap$ var10001 = ListMap$.MODULE$;
      this.underlying = ListMap.EmptyListMap$.MODULE$;
      this.isAliased = false;
   }

   public ListMap result() {
      this.isAliased = true;
      Statics.releaseFence();
      return this.underlying;
   }

   public ListMapBuilder addOne(final Tuple2 elem) {
      return this.addOne(elem._1(), elem._2());
   }

   private boolean insertValueAtKeyReturnFound(final ListMap m, final Object key, final Object value) {
      while(m instanceof ListMap.Node) {
         ListMap.Node var4 = (ListMap.Node)m;
         if (BoxesRunTime.equals(var4.key(), key)) {
            var4._value_$eq(value);
            return true;
         }

         ListMap var10000 = var4._init();
         value = value;
         key = key;
         m = var10000;
      }

      return false;
   }

   public ListMapBuilder addOne(final Object key, final Object value) {
      if (this.isAliased) {
         this.underlying = this.underlying.updated(key, value);
      } else if (!this.insertValueAtKeyReturnFound(this.underlying, key, value)) {
         this.underlying = new ListMap.Node(key, value, this.underlying);
      }

      return this;
   }

   public ListMapBuilder addAll(final IterableOnce xs) {
      if (this.isAliased) {
         return (ListMapBuilder)Growable.addAll$(this, xs);
      } else if (this.underlying.nonEmpty()) {
         if (xs instanceof scala.collection.Map) {
            Iterator iter = ((scala.collection.Map)xs).iterator();
            ListMap newUnderlying = this.underlying;

            while(iter.hasNext()) {
               Tuple2 next = (Tuple2)iter.next();
               if (!this.insertValueAtKeyReturnFound(this.underlying, next._1(), next._2())) {
                  newUnderlying = new ListMap.Node(next._1(), next._2(), newUnderlying);
               }
            }

            this.underlying = newUnderlying;
            return this;
         } else {
            return (ListMapBuilder)Growable.addAll$(this, xs);
         }
      } else if (!(xs instanceof LinkedHashMap)) {
         if (xs instanceof scala.collection.Map ? true : xs instanceof MapView) {
            Object k;
            Object v;
            for(Iterator iter = xs.iterator(); iter.hasNext(); this.underlying = new ListMap.Node(k, v, this.underlying)) {
               Tuple2 var7 = (Tuple2)iter.next();
               if (var7 == null) {
                  throw new MatchError((Object)null);
               }

               k = var7._1();
               v = var7._2();
            }

            return this;
         } else {
            return (ListMapBuilder)Growable.addAll$(this, xs);
         }
      } else {
         for(LinkedHashMap.LinkedEntry firstEntry = ((LinkedHashMap)xs)._firstEntry(); firstEntry != null; firstEntry = firstEntry.later()) {
            this.underlying = new ListMap.Node(firstEntry.key(), firstEntry.value(), this.underlying);
         }

         return this;
      }
   }

   public ListMapBuilder() {
      ListMap$ var10001 = ListMap$.MODULE$;
      this.underlying = ListMap.EmptyListMap$.MODULE$;
   }
}
