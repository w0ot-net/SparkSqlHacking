package breeze.util;

import java.lang.invoke.SerializedLambda;
import scala.;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.BuildFrom;
import scala.collection.Iterable;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Growable;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;

@ScalaSignature(
   bytes = "\u0006\u0005\tmr!B\u0010!\u0011\u0003)c!B\u0014!\u0011\u0003A\u0003\"B\u001b\u0002\t\u00031d\u0001B\u001c\u0002\u0007aB\u0001\"P\u0002\u0003\u0006\u0004%\tA\u0010\u0005\tC\u000e\u0011\t\u0011)A\u0005\u007f!)Qg\u0001C\u0001E\")am\u0001C\u0001O\"I\u00111E\u0002\u0002\u0002\u0013\u0005\u0013Q\u0005\u0005\n\u0003[\u0019\u0011\u0011!C!\u0003_9\u0011\"a\u000f\u0002\u0003\u0003E\t!!\u0010\u0007\u0011]\n\u0011\u0011!E\u0001\u0003\u007fAa!N\u0006\u0005\u0002\u0005\u0005\u0003bBA\"\u0017\u0011\u0015\u0011Q\t\u0005\n\u0003_Z\u0011\u0011!C\u0003\u0003cB\u0011\"! \f\u0003\u0003%)!a \t\u0013\u0005m\u0012!!A\u0005\u0004\u0005=eABAV\u0003\r\ti\u000bC\u0005>#\t\u0015\r\u0011\"\u0001\u00022\"I\u0011-\u0005B\u0001B\u0003%\u00111\u0017\u0005\u0007kE!\t!a1\t\r\u0019\fB\u0011AAe\u0011%\t\u0019#EA\u0001\n\u0003\n)\u0003C\u0005\u0002.E\t\t\u0011\"\u0011\u0002X\u001eI\u00111\\\u0001\u0002\u0002#\u0005\u0011Q\u001c\u0004\n\u0003W\u000b\u0011\u0011!E\u0001\u0003?Da!N\r\u0005\u0002\u0005\u0005\bbBA\"3\u0011\u0015\u00111\u001d\u0005\n\u0003_J\u0012\u0011!C\u0003\u0005\u0007A\u0011\"! \u001a\u0003\u0003%)Aa\u0005\t\u0013\u0005m\u0017!!A\u0005\u0004\t\u001d\u0012!C%na2L7-\u001b;t\u0015\t\t#%\u0001\u0003vi&d'\"A\u0012\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0001\"AJ\u0001\u000e\u0003\u0001\u0012\u0011\"S7qY&\u001c\u0017\u000e^:\u0014\t\u0005IsF\r\t\u0003U5j\u0011a\u000b\u0006\u0002Y\u0005)1oY1mC&\u0011af\u000b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u0019\u0002\u0014BA\u0019!\u0005=!u.\u001e2mK&k\u0007\u000f\\5dSR\u001c\bC\u0001\u00144\u0013\t!\u0004EA\tJi\u0016\u0014\u0018\r^8s\u00136\u0004H.[2jiN\fa\u0001P5oSRtD#A\u0013\u0003\u0019M\u001cWI\u001c:jG\"\u001cu\u000e\u001c7\u0016\u0005e\n5CA\u0002;!\tQ3(\u0003\u0002=W\t1\u0011I\\=WC2\faaX0uQ&\u001cX#A \u0011\u0005\u0001\u000bE\u0002\u0001\u0003\u0006\u0005\u000e\u0011\ra\u0011\u0002\u0005\u0007>dG.\u0005\u0002E\u000fB\u0011!&R\u0005\u0003\r.\u0012qAT8uQ&tw\rE\u0002I!Ns!!\u0013(\u000f\u0005)kU\"A&\u000b\u00051#\u0013A\u0002\u001fs_>$h(C\u0001-\u0013\ty5&A\u0004qC\u000e\\\u0017mZ3\n\u0005E\u0013&a\u0003+sCZ,'o]1cY\u0016T!aT\u00161\u0007QCv\f\u0005\u0003++^s\u0016B\u0001,,\u0005\u0019!V\u000f\u001d7feA\u0011\u0001\t\u0017\u0003\n3\u0006\u000b\t\u0011!A\u0003\u0002i\u00131a\u0018\u00132#\t!5\f\u0005\u0002+9&\u0011Ql\u000b\u0002\u0004\u0003:L\bC\u0001!`\t%\u0001\u0017)!A\u0001\u0002\u000b\u0005!LA\u0002`II\nqaX0uQ&\u001c\b\u0005\u0006\u0002dKB\u0019AmA \u000e\u0003\u0005AQ!\u0010\u0004A\u0002}\n!\u0002^8Nk2$\u0018.T1q+\u0011AWO]@\u0015\t%<\u00181\u0001\t\u0005U:\fHO\u0004\u0002lYB\u0011!jK\u0005\u0003[.\na\u0001\u0015:fI\u00164\u0017BA8q\u0005\ri\u0015\r\u001d\u0006\u0003[.\u0002\"\u0001\u0011:\u0005\u000bM<!\u0019\u0001.\u0003\u0003\u0005\u0003\"\u0001Q;\u0005\u000bY<!\u0019\u0001.\u0003\rI+7/\u001e7u\u0011\u0015Ax\u0001q\u0001z\u0003\u00111\u0018.Z<\u0011\t)Rx\b`\u0005\u0003w.\u0012\u0001\u0003\n7fgN$3m\u001c7p]\u0012bWm]:\u0011\u0007!\u0003V\u0010\u0005\u0003++Ft\bC\u0001!\u0000\t\u0019\t\ta\u0002b\u00015\n\t!\tC\u0004\u0002\u0006\u001d\u0001\u001d!a\u0002\u0002\u0007\r\u0014g\rE\u0004\u0002\n\u0005uqH ;\u000f\t\u0005-\u0011\u0011\u0004\b\u0005\u0003\u001b\t\u0019BD\u0002J\u0003\u001fI1!!\u0005,\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003+\t9\"A\u0004hK:,'/[2\u000b\u0007\u0005E1&C\u0002P\u00037QA!!\u0006\u0002\u0018%!\u0011qDA\u0011\u00051\u0019\u0015M\u001c\"vS2$gI]8n\u0015\ry\u00151D\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011q\u0005\t\u0004U\u0005%\u0012bAA\u0016W\t\u0019\u0011J\u001c;\u0002\r\u0015\fX/\u00197t)\u0011\t\t$a\u000e\u0011\u0007)\n\u0019$C\u0002\u00026-\u0012qAQ8pY\u0016\fg\u000e\u0003\u0005\u0002:%\t\t\u00111\u0001\\\u0003\rAH%M\u0001\rg\u000e,eN]5dQ\u000e{G\u000e\u001c\t\u0003I.\u0019\"aC\u0015\u0015\u0005\u0005u\u0012\u0001\u0006;p\u001bVdG/['ba\u0012*\u0007\u0010^3og&|g.\u0006\u0006\u0002H\u0005M\u0013qJA2\u00037\"B!!\u0013\u0002jQ1\u00111JA+\u0003K\u0002bA\u001b8\u0002N\u0005E\u0003c\u0001!\u0002P\u0011)1/\u0004b\u00015B\u0019\u0001)a\u0015\u0005\u000bYl!\u0019\u0001.\t\ral\u00019AA,!\u0019Q#0!\u0017\u0002^A\u0019\u0001)a\u0017\u0005\u000b\tk!\u0019A\"\u0011\t!\u0003\u0016q\f\t\u0007UU\u000bi%!\u0019\u0011\u0007\u0001\u000b\u0019\u0007\u0002\u0004\u0002\u00025\u0011\rA\u0017\u0005\b\u0003\u000bi\u00019AA4!)\tI!!\b\u0002Z\u0005\u0005\u0014\u0011\u000b\u0005\b\u0003Wj\u0001\u0019AA7\u0003\u0015!C\u000f[5t!\u0011!7!!\u0017\u0002%!\f7\u000f[\"pI\u0016$S\r\u001f;f]NLwN\\\u000b\u0005\u0003g\nY\b\u0006\u0003\u0002&\u0005U\u0004bBA6\u001d\u0001\u0007\u0011q\u000f\t\u0005I\u000e\tI\bE\u0002A\u0003w\"QA\u0011\bC\u0002\r\u000b\u0001#Z9vC2\u001cH%\u001a=uK:\u001c\u0018n\u001c8\u0016\t\u0005\u0005\u0015Q\u0012\u000b\u0005\u0003\u0007\u000b9\t\u0006\u0003\u00022\u0005\u0015\u0005\u0002CA\u001d\u001f\u0005\u0005\t\u0019A.\t\u000f\u0005-t\u00021\u0001\u0002\nB!AmAAF!\r\u0001\u0015Q\u0012\u0003\u0006\u0005>\u0011\raQ\u000b\u0005\u0003#\u000b9\n\u0006\u0003\u0002\u0014\u0006%\u0006\u0003\u00023\u0004\u0003+\u00032\u0001QAL\t\u0019\u0011\u0005C1\u0001\u0002\u001aF\u0019A)a'\u0011\t!\u0003\u0016Q\u0014\u0019\u0007\u0003?\u000b\u0019+a*\u0011\r)*\u0016\u0011UAS!\r\u0001\u00151\u0015\u0003\u000b3\u0006]\u0015\u0011!A\u0001\u0006\u0003Q\u0006c\u0001!\u0002(\u0012Q\u0001-a&\u0002\u0002\u0003\u0005)\u0011\u0001.\t\ru\u0002\u0002\u0019AAK\u00055\u00198-\u00128sS\u000eD\u0017I\u001d:bsV1\u0011qVA_\u0003\u0003\u001c\"!\u0005\u001e\u0016\u0005\u0005M\u0006#\u0002\u0016\u00026\u0006e\u0016bAA\\W\t)\u0011I\u001d:bsB1!&VA^\u0003\u007f\u00032\u0001QA_\t\u0015\u0019\u0018C1\u0001[!\r\u0001\u0015\u0011\u0019\u0003\u0007\u0003\u0003\t\"\u0019\u0001.\u0015\t\u0005\u0015\u0017q\u0019\t\u0007IF\tY,a0\t\ru\"\u0002\u0019AAZ+\u0011\tY-!5\u0015\t\u00055\u00171\u001b\t\u0007U:\fY,a4\u0011\u0007\u0001\u000b\t\u000eB\u0003w+\t\u0007!\fC\u0004\u0002\u0006U\u0001\u001d!!6\u0011\u0015\u0005%\u0011QDAZ\u0003\u007f\u000by\r\u0006\u0003\u00022\u0005e\u0007\u0002CA\u001d/\u0005\u0005\t\u0019A.\u0002\u001bM\u001cWI\u001c:jG\"\f%O]1z!\t!\u0017d\u0005\u0002\u001aSQ\u0011\u0011Q\\\u000b\t\u0003K\f\t0!<\u0002~R!\u0011q]A\u0000)\u0011\tI/a=\u0011\r)t\u00171^Ax!\r\u0001\u0015Q\u001e\u0003\u0006gn\u0011\rA\u0017\t\u0004\u0001\u0006EH!\u0002<\u001c\u0005\u0004Q\u0006bBA\u00037\u0001\u000f\u0011Q\u001f\t\u000b\u0003\u0013\ti\"a>\u0002|\u0006=\b#\u0002\u0016\u00026\u0006e\bC\u0002\u0016V\u0003W\fY\u0010E\u0002A\u0003{$a!!\u0001\u001c\u0005\u0004Q\u0006bBA67\u0001\u0007!\u0011\u0001\t\u0007IF\tY/a?\u0016\r\t\u0015!Q\u0002B\t)\u0011\t)Ca\u0002\t\u000f\u0005-D\u00041\u0001\u0003\nA1A-\u0005B\u0006\u0005\u001f\u00012\u0001\u0011B\u0007\t\u0015\u0019HD1\u0001[!\r\u0001%\u0011\u0003\u0003\u0007\u0003\u0003a\"\u0019\u0001.\u0016\r\tU!\u0011\u0005B\u0013)\u0011\u00119Ba\u0007\u0015\t\u0005E\"\u0011\u0004\u0005\t\u0003si\u0012\u0011!a\u00017\"9\u00111N\u000fA\u0002\tu\u0001C\u00023\u0012\u0005?\u0011\u0019\u0003E\u0002A\u0005C!Qa]\u000fC\u0002i\u00032\u0001\u0011B\u0013\t\u0019\t\t!\bb\u00015V1!\u0011\u0006B\u0018\u0005g!BAa\u000b\u00036A1A-\u0005B\u0017\u0005c\u00012\u0001\u0011B\u0018\t\u0015\u0019hD1\u0001[!\r\u0001%1\u0007\u0003\u0007\u0003\u0003q\"\u0019\u0001.\t\rur\u0002\u0019\u0001B\u001c!\u0015Q\u0013Q\u0017B\u001d!\u0019QSK!\f\u00032\u0001"
)
public final class Implicits {
   public static Tuple2[] scEnrichArray(final Tuple2[] __this) {
      return Implicits$.MODULE$.scEnrichArray(__this);
   }

   public static Iterable scEnrichColl(final Iterable __this) {
      return Implicits$.MODULE$.scEnrichColl(__this);
   }

   public static IteratorImplicits.RichIterator scEnrichIterator(final Iterator iter) {
      return Implicits$.MODULE$.scEnrichIterator(iter);
   }

   public static DoubleImplicits.RichFloat RichFloat(final float x) {
      return Implicits$.MODULE$.RichFloat(x);
   }

   public static DoubleImplicits.RichDouble RichDouble(final double x) {
      return Implicits$.MODULE$.RichDouble(x);
   }

   public static final class scEnrichColl {
      private final Iterable __this;

      public Iterable __this() {
         return this.__this;
      }

      public Map toMultiMap(final .less.colon.less view, final BuildFrom cbf) {
         return Implicits.scEnrichColl$.MODULE$.toMultiMap$extension(this.__this(), view, cbf);
      }

      public int hashCode() {
         return Implicits.scEnrichColl$.MODULE$.hashCode$extension(this.__this());
      }

      public boolean equals(final Object x$1) {
         return Implicits.scEnrichColl$.MODULE$.equals$extension(this.__this(), x$1);
      }

      public scEnrichColl(final Iterable __this) {
         this.__this = __this;
      }
   }

   public static class scEnrichColl$ {
      public static final scEnrichColl$ MODULE$ = new scEnrichColl$();

      public final Map toMultiMap$extension(final Iterable $this, final .less.colon.less view, final BuildFrom cbf) {
         ObjectRef result = ObjectRef.create((scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
         result.elem = ((scala.collection.mutable.Map)result.elem).withDefault((a) -> {
            Builder r = cbf.apply($this);
            ((scala.collection.mutable.Map)result.elem).update(a, r);
            return r;
         });
         ((IterableOps)view.apply($this)).withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$toMultiMap$2(check$ifrefutable$1))).foreach((x$1) -> {
            if (x$1 != null) {
               Object a = x$1._1();
               Object b = x$1._2();
               Builder var2 = (Builder)((Growable)((scala.collection.mutable.Map)result.elem).apply(a)).$plus$eq(b);
               return var2;
            } else {
               throw new MatchError(x$1);
            }
         });
         return ((scala.collection.mutable.Map)result.elem).mapValues((x$2) -> x$2.result()).toMap(scala..less.colon.less..MODULE$.refl());
      }

      public final int hashCode$extension(final Iterable $this) {
         return $this.hashCode();
      }

      public final boolean equals$extension(final Iterable $this, final Object x$1) {
         boolean var3;
         if (x$1 instanceof scEnrichColl) {
            var3 = true;
         } else {
            var3 = false;
         }

         boolean var7;
         if (var3) {
            label32: {
               label31: {
                  Iterable var5 = x$1 == null ? null : ((scEnrichColl)x$1).__this();
                  if ($this == null) {
                     if (var5 == null) {
                        break label31;
                     }
                  } else if ($this.equals(var5)) {
                     break label31;
                  }

                  var7 = false;
                  break label32;
               }

               var7 = true;
            }

            if (var7) {
               var7 = true;
               return var7;
            }
         }

         var7 = false;
         return var7;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$toMultiMap$2(final Tuple2 check$ifrefutable$1) {
         boolean var1;
         if (check$ifrefutable$1 != null) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static final class scEnrichArray {
      private final Tuple2[] __this;

      public Tuple2[] __this() {
         return this.__this;
      }

      public Map toMultiMap(final BuildFrom cbf) {
         return Implicits.scEnrichArray$.MODULE$.toMultiMap$extension(this.__this(), cbf);
      }

      public int hashCode() {
         return Implicits.scEnrichArray$.MODULE$.hashCode$extension(this.__this());
      }

      public boolean equals(final Object x$1) {
         return Implicits.scEnrichArray$.MODULE$.equals$extension(this.__this(), x$1);
      }

      public scEnrichArray(final Tuple2[] __this) {
         this.__this = __this;
      }
   }

   public static class scEnrichArray$ {
      public static final scEnrichArray$ MODULE$ = new scEnrichArray$();

      public final Map toMultiMap$extension(final Tuple2[] $this, final BuildFrom cbf) {
         ObjectRef result = ObjectRef.create((scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
         result.elem = ((scala.collection.mutable.Map)result.elem).withDefault((a) -> {
            Builder r = cbf.apply($this);
            ((scala.collection.mutable.Map)result.elem).update(a, r);
            return r;
         });
         scala.collection.ArrayOps..MODULE$.withFilter$extension(scala.Predef..MODULE$.refArrayOps((Object[])$this), (check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$toMultiMap$6(check$ifrefutable$2))).foreach((x$3) -> {
            if (x$3 != null) {
               Object a = x$3._1();
               Object b = x$3._2();
               Builder var2 = (Builder)((Growable)((scala.collection.mutable.Map)result.elem).apply(a)).$plus$eq(b);
               return var2;
            } else {
               throw new MatchError(x$3);
            }
         });
         return (Map)scala.Predef..MODULE$.Map().empty().$plus$plus(((scala.collection.mutable.Map)result.elem).mapValues((x$4) -> x$4.result()));
      }

      public final int hashCode$extension(final Tuple2[] $this) {
         return $this.hashCode();
      }

      public final boolean equals$extension(final Tuple2[] $this, final Object x$1) {
         boolean var3;
         if (x$1 instanceof scEnrichArray) {
            var3 = true;
         } else {
            var3 = false;
         }

         boolean var10000;
         if (var3) {
            Tuple2[] var5 = x$1 == null ? null : ((scEnrichArray)x$1).__this();
            if ($this == var5) {
               var10000 = true;
               return var10000;
            }
         }

         var10000 = false;
         return var10000;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$toMultiMap$6(final Tuple2 check$ifrefutable$2) {
         boolean var1;
         if (check$ifrefutable$2 != null) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
