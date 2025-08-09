package scala.collection.immutable;

import scala.Function1;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.generic.CommonErrors$;
import scala.collection.mutable.Builder;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i4qa\u0002\u0005\u0011\u0002\u0007\u0005q\u0002C\u00031\u0001\u0011\u0005\u0011\u0007C\u00036\u0001\u0011\u0005c\u0007C\u0003A\u0001\u0011\u0005\u0013\tC\u0003O\u0001\u0011\u0005s\nC\u0003^\u0001\u0011\u0005c\fC\u0006l\u0001A\u0005\u0019\u0011!A\u0005\n1D(!F*ue&\u001cGo\u00149uS6L'0\u001a3TKF|\u0005o\u001d\u0006\u0003\u0013)\t\u0011\"[7nkR\f'\r\\3\u000b\u0005-a\u0011AC2pY2,7\r^5p]*\tQ\"A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\tAY\"\u0005K\n\u0006\u0001E)\"&\f\t\u0003%Mi\u0011\u0001D\u0005\u0003)1\u00111!\u00118z!\u00151r#G\u0011(\u001b\u0005A\u0011B\u0001\r\t\u0005\u0019\u0019V-](qgB\u0011!d\u0007\u0007\u0001\t\u0019a\u0002\u0001\"b\u0001;\t\t\u0011)\u0005\u0002\u001f#A\u0011!cH\u0005\u0003A1\u0011qAT8uQ&tw\r\u0005\u0002\u001bE\u001111\u0005\u0001CC\u0002\u0011\u0012!aQ\"\u0016\u0005u)C!\u0002\u0014#\u0005\u0004i\"\u0001B0%IE\u0002\"A\u0007\u0015\u0005\r%\u0002AQ1\u0001\u001e\u0005\u0005\u0019\u0005#B\u0016-3\u0005:S\"\u0001\u0006\n\u0005\u001dQ\u0001#B\u0016/3\u0005:\u0013BA\u0018\u000b\u0005i\u0019FO]5di>\u0003H/[7ju\u0016$\u0017\n^3sC\ndWm\u00149t\u0003\u0019!\u0013N\\5uIQ\t!\u0007\u0005\u0002\u0013g%\u0011A\u0007\u0004\u0002\u0005+:LG/\u0001\u0006eSN$\u0018N\\2u\u0005f,\"a\u000e \u0015\u0005\u001dB\u0004\"B\u001d\u0003\u0001\u0004Q\u0014!\u00014\u0011\tIY\u0014$P\u0005\u0003y1\u0011\u0011BR;oGRLwN\\\u0019\u0011\u0005iqD!B \u0003\u0005\u0004i\"!\u0001\"\u0002\u000fU\u0004H-\u0019;fIV\u0011!)\u0012\u000b\u0004\u0007\u001ec\u0005c\u0001\u000e#\tB\u0011!$\u0012\u0003\u0006\u007f\r\u0011\rAR\t\u00033EAQ\u0001S\u0002A\u0002%\u000bQ!\u001b8eKb\u0004\"A\u0005&\n\u0005-c!aA%oi\")Qj\u0001a\u0001\t\u0006!Q\r\\3n\u0003\u0015\u0001\u0018\r^2i+\t\u00016\u000b\u0006\u0003R)Z[\u0006c\u0001\u000e#%B\u0011!d\u0015\u0003\u0006\u007f\u0011\u0011\rA\u0012\u0005\u0006+\u0012\u0001\r!S\u0001\u0005MJ|W\u000eC\u0003X\t\u0001\u0007\u0001,A\u0003pi\",'\u000fE\u0002,3JK!A\u0017\u0006\u0003\u0019%#XM]1cY\u0016|enY3\t\u000bq#\u0001\u0019A%\u0002\u0011I,\u0007\u000f\\1dK\u0012\faa]8si\u0016$WCA0k)\t9\u0003\rC\u0003b\u000b\u0001\u000f!-A\u0002pe\u0012\u00042a\u00194j\u001d\t\u0011B-\u0003\u0002f\u0019\u00059\u0001/Y2lC\u001e,\u0017BA4i\u0005!y%\u000fZ3sS:<'BA3\r!\tQ\"\u000eB\u0003@\u000b\t\u0007a)\u0001\u0007tkB,'\u000fJ:peR,G-\u0006\u0002noR\u0011qE\u001c\u0005\u0006C\u001a\u0001\u001da\u001c\t\u0004a\u001a4hBA9e\u001d\t\u0011X/D\u0001t\u0015\t!h\"\u0001\u0004=e>|GOP\u0005\u0002\u001bA\u0011!d\u001e\u0003\u0006\u007f\u0019\u0011\rAR\u0005\u0003;fL!\u0001\u0007\u0006"
)
public interface StrictOptimizedSeqOps extends SeqOps, scala.collection.StrictOptimizedSeqOps {
   // $FF: synthetic method
   Object scala$collection$immutable$StrictOptimizedSeqOps$$super$sorted(final Ordering ord);

   // $FF: synthetic method
   static Object distinctBy$(final StrictOptimizedSeqOps $this, final Function1 f) {
      return $this.distinctBy(f);
   }

   default Object distinctBy(final Function1 f) {
      if (this.lengthCompare(1) <= 0) {
         return this.coll();
      } else {
         Builder builder = this.newSpecificBuilder();
         scala.collection.mutable.HashSet$ var10000 = scala.collection.mutable.HashSet$.MODULE$;
         scala.collection.mutable.HashSet seen = new scala.collection.mutable.HashSet();
         Iterator it = this.iterator();
         boolean different = false;

         while(it.hasNext()) {
            Object next = it.next();
            if (seen.add(f.apply(next))) {
               if (builder == null) {
                  throw null;
               }

               builder.addOne(next);
            } else {
               different = true;
            }
         }

         if (different) {
            return builder.result();
         } else {
            return this.coll();
         }
      }
   }

   // $FF: synthetic method
   static Object updated$(final StrictOptimizedSeqOps $this, final int index, final Object elem) {
      return $this.updated(index, elem);
   }

   default Object updated(final int index, final Object elem) {
      if (index < 0) {
         if (this.knownSize() >= 0) {
            throw CommonErrors$.MODULE$.indexOutOfBounds(index, this.knownSize());
         } else {
            throw CommonErrors$.MODULE$.indexOutOfBounds(index);
         }
      } else {
         Builder b = this.iterableFactory().newBuilder();
         if (b == null) {
            throw null;
         } else {
            b.sizeHint(this, 0);
            int i = 0;

            Iterator it;
            for(it = this.iterator(); i < index && it.hasNext(); ++i) {
               Object $plus$eq_elem = it.next();
               b.addOne($plus$eq_elem);
               $plus$eq_elem = null;
            }

            if (!it.hasNext()) {
               throw CommonErrors$.MODULE$.indexOutOfBounds(index, i - 1);
            } else {
               b.addOne(elem);
               it.next();

               while(it.hasNext()) {
                  Object $plus$eq_elem = it.next();
                  b.addOne($plus$eq_elem);
                  $plus$eq_elem = null;
               }

               return b.result();
            }
         }
      }
   }

   // $FF: synthetic method
   static Object patch$(final StrictOptimizedSeqOps $this, final int from, final IterableOnce other, final int replaced) {
      return $this.patch(from, other, replaced);
   }

   default Object patch(final int from, final IterableOnce other, final int replaced) {
      Builder b = this.iterableFactory().newBuilder();
      int i = 0;

      Iterator it;
      for(it = this.iterator(); i < from && it.hasNext(); ++i) {
         Object $plus$eq_elem = it.next();
         if (b == null) {
            throw null;
         }

         b.addOne($plus$eq_elem);
         $plus$eq_elem = null;
      }

      if (b == null) {
         throw null;
      } else {
         b.addAll(other);

         for(int var9 = replaced; var9 > 0 && it.hasNext(); --var9) {
            it.next();
         }

         while(it.hasNext()) {
            Object $plus$eq_elem = it.next();
            b.addOne($plus$eq_elem);
            $plus$eq_elem = null;
         }

         return b.result();
      }
   }

   // $FF: synthetic method
   static Object sorted$(final StrictOptimizedSeqOps $this, final Ordering ord) {
      return $this.sorted(ord);
   }

   default Object sorted(final Ordering ord) {
      return this.scala$collection$immutable$StrictOptimizedSeqOps$$super$sorted(ord);
   }

   static void $init$(final StrictOptimizedSeqOps $this) {
   }
}
