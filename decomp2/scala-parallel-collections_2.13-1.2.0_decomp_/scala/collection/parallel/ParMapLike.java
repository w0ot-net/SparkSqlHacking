package scala.collection.parallel;

import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import scala.Equals;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.BufferedIterator;
import scala.collection.CustomParallelizable;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.MapOps;
import scala.collection.Parallelizable;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.generic.DelegatedSignalling;
import scala.collection.generic.GenericParCompanion;
import scala.collection.generic.GenericParMapCompanion;
import scala.collection.generic.GenericParMapTemplate;
import scala.collection.generic.GenericParTemplate;
import scala.collection.generic.GenericTraversableTemplate;
import scala.collection.generic.Signalling;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing;
import scala.util.hashing.MurmurHash3.;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0005haB\u00181!\u0003\r\ta\u000e\u0005\u0006e\u0002!\ta\u001d\u0005\u0006o\u00021\t\u0001\u001f\u0005\u0006}\u0002!\ta \u0005\b\u0003\u0017\u0001A\u0011IA\u0007\u0011\u001d\t\t\u0002\u0001C!\u0003'Aq!a\u0007\u0001\r\u0003\ti\u0002C\u0004\u0002H\u0001!\t!!\u0013\t\u000f\u0005e\u0003A\"\u0001\u0002\\!9\u0011q\f\u0001\u0007\u0002\u0005\u0005\u0004bBA9\u0001\u0011\u0005\u00111\u000f\u0005\b\u0003o\u0002a\u0011AA=\u0011\u001d\tY\b\u0001C\u0001\u0003{Bq!!!\u0001\t\u0003\t\u0019\tC\u0004\u0002\u0018\u0002!\t!!'\t\u000f\u0005u\u0005\u0001\"\u0001\u0002 \"A\u00111\u0015\u0001!\n\u0013\t)\u000bC\u0004\u0002$\u0002!\t!a2\t\u0011\u0005%\u0007\u0001)C\u0005\u0003\u0017Dq!!3\u0001\t\u0003\t\tN\u0002\u0004\u0002T\u0002A\u0011Q\u001b\u0005\b\u0003;$B\u0011AAp\u0011\u001d\t9\n\u0006C\u0001\u0003KDq!!;\u0015\t\u0003\t9\rC\u0004\u0002\u001cQ!\t!a;\t\u000f\u0005eC\u0003\"\u0001\u0002r\"9\u0011Q\u001f\u000b\u0005\u0002\u0005]\bbBA})\u0011\u0005\u0013q\u001f\u0005\b\u0003w$B\u0011IA\u007f\u0011\u001d\u0011y\u0001\u0006C!\u0005#1aA!\u0007\u0001\u0011\tm\u0001bBAo=\u0011\u0005!q\u0004\u0005\b\u0003StB\u0011AAi\u0011\u001d\t)P\bC\u0001\u0003oDq!!?\u001f\t\u0003\n9\u0010C\u0004\u0002|z!\tEa\t\t\u000f\t=a\u0004\"\u0001\u00030!9!q\u0007\u0001\u0005\u0002\te\u0002b\u0002B\u001e\u0001\u0011\u0005!Q\b\u0005\b\u0005\u0003\u0002A\u0011\u0001B\"\u0011\u001d\u0011)\u0005\u0001C\u0001\u0005\u000fBqAa\u0014\u0001\t\u0003\u0011\t\u0006C\u0004\u0003b\u0001!\tAa\u0019\t\u000f\tm\u0004\u0001\"\u0001\u0003~!9!q\u0013\u0001\u0005\u0002\te\u0005b\u0002BZ\u0001\u0011\u0005!Q\u0017\u0005\b\u0005\u000b\u0004AQ\u0001Bd\u0005)\u0001\u0016M]'ba2K7.\u001a\u0006\u0003cI\n\u0001\u0002]1sC2dW\r\u001c\u0006\u0003gQ\n!bY8mY\u0016\u001cG/[8o\u0015\u0005)\u0014!B:dC2\f7\u0001A\u000b\bq\u0019\u0003\u00161\u0005,b'\u0011\u0001\u0011(P8\u0011\u0005iZT\"\u0001\u001b\n\u0005q\"$AB!osJ+g\r\u0005\u0004?\u007f\u0005\u0013V\u000bY\u0007\u0002a%\u0011\u0001\t\r\u0002\u0010!\u0006\u0014\u0018\n^3sC\ndW\rT5lKB!!H\u0011#P\u0013\t\u0019EG\u0001\u0004UkBdWM\r\t\u0003\u000b\u001ac\u0001\u0001B\u0003H\u0001\t\u0007\u0001JA\u0001L#\tIE\n\u0005\u0002;\u0015&\u00111\n\u000e\u0002\b\u001d>$\b.\u001b8h!\tQT*\u0003\u0002Oi\t\u0019\u0011I\\=\u0011\u0005\u0015\u0003FAB)\u0001\t\u000b\u0007\u0001JA\u0001W!\tq4+\u0003\u0002Ua\tY\u0001+\u0019:Ji\u0016\u0014\u0018M\u00197f!\t)e\u000b\u0002\u0004X\u0001\u0011\u0015\r\u0001\u0017\u0002\u0005%\u0016\u0004(/\u0005\u0002J3J\u0019!\f\u00188\u0007\tm\u0003\u0001!\u0017\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\b}\u0001!u*X+a!\tqd,\u0003\u0002`a\t1\u0001+\u0019:NCB\u0004\"!R1\u0005\r\t\u0004AQ1\u0001d\u0005)\u0019V-];f]RL\u0017\r\\\t\u0003\u0013\u0012\u00142!\u001a4k\r\u0011Y\u0006\u0001\u00013\u0011\t\u001dDGiT\u0007\u0002e%\u0011\u0011N\r\u0002\u0004\u001b\u0006\u0004\bCB4l\t>k\u0007-\u0003\u0002me\t1Q*\u00199PaN\u0004\"a\u001a5\u0011\tyrFi\u0014\t\u0003uAL!!\u001d\u001b\u0003\r\u0015\u000bX/\u00197t\u0003\u0019!\u0013N\\5uIQ\tA\u000f\u0005\u0002;k&\u0011a\u000f\u000e\u0002\u0005+:LG/A\u0002hKR$\"!\u001f?\u0011\u0007iRx*\u0003\u0002|i\t1q\n\u001d;j_:DQ! \u0002A\u0002\u0011\u000b1a[3z\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\u0001\u0003\u000f\u00012AOA\u0002\u0013\r\t)\u0001\u000e\u0002\b\u0005>|G.Z1o\u0011\u0019\tIa\u0001a\u0001\u0019\u0006!A\u000f[1u\u0003\u0019)\u0017/^1mgR!\u0011\u0011AA\b\u0011\u0019\tI\u0001\u0002a\u0001\u0019\u0006A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002\u0016A\u0019!(a\u0006\n\u0007\u0005eAGA\u0002J]R\fQ\u0001\n9mkN,B!a\b\u0002<Q!\u0011\u0011EA!!\u0019)\u00151\u0005#\u0002:\u0011A\u0011Q\u0005\u0001\u0005\u0006\u0004\t9C\u0001\u0002D\u0007V1\u0011\u0011FA\u0018\u0003k\t2!SA\u0016!\u0019qd,!\f\u00024A\u0019Q)a\f\u0005\u000f\u0005E\u00121\u0005b\u0001\u0011\n\t\u0001\fE\u0002F\u0003k!q!a\u000e\u0002$\t\u0007\u0001JA\u0001Z!\r)\u00151\b\u0003\b\u0003{1!\u0019AA \u0005\t1\u0016'\u0005\u0002P\u0019\"9\u00111\t\u0004A\u0002\u0005\u0015\u0013AA6w!\u0015Q$\tRA\u001d\u0003\u001d)\b\u000fZ1uK\u0012,B!a\u0013\u0002RQ1\u0011QJA*\u0003+\u0002b!RA\u0012\t\u0006=\u0003cA#\u0002R\u00119\u0011QH\u0004C\u0002\u0005}\u0002\"B?\b\u0001\u0004!\u0005bBA,\u000f\u0001\u0007\u0011qJ\u0001\u0006m\u0006dW/Z\u0001\u0007I5Lg.^:\u0015\u0007U\u000bi\u0006C\u0003~\u0011\u0001\u0007A)\u0001\u0007nCB\u001cu.\u001c9b]&|g.\u0006\u0002\u0002dA1\u0011QMA6\u0003_j!!a\u001a\u000b\u0007\u0005%$'A\u0004hK:,'/[2\n\t\u00055\u0014q\r\u0002\u0017\u000f\u0016tWM]5d!\u0006\u0014X*\u00199D_6\u0004\u0018M\\5p]B\u0019Q)a\t\u0002\u000f\u0011,g-Y;miR\u0019q*!\u001e\t\u000buT\u0001\u0019\u0001#\u0002\u000b\u0015l\u0007\u000f^=\u0016\u0003U\u000bQ!\u00199qYf$2aTA@\u0011\u0015iH\u00021\u0001E\u0003%9W\r^(s\u000b2\u001cX-\u0006\u0003\u0002\u0006\u0006%ECBAD\u0003\u001b\u000by\tE\u0002F\u0003\u0013#q!a#\u000e\u0005\u0004\tyDA\u0001V\u0011\u0015iX\u00021\u0001E\u0011!\t\t(\u0004CA\u0002\u0005E\u0005#\u0002\u001e\u0002\u0014\u0006\u001d\u0015bAAKi\tAAHY=oC6,g(\u0001\u0005d_:$\u0018-\u001b8t)\u0011\t\t!a'\t\u000but\u0001\u0019\u0001#\u0002\u0017%\u001cH)\u001a4j]\u0016$\u0017\t\u001e\u000b\u0005\u0003\u0003\t\t\u000bC\u0003~\u001f\u0001\u0007A)\u0001\u0007lKf\u001c\u0018\n^3sCR|'\u000f\u0006\u0003\u0002(\u00065\u0006\u0003\u0002 \u0002*\u0012K1!a+1\u0005AIE/\u001a:bE2,7\u000b\u001d7jiR,'\u000fC\u0004\u00020B\u0001\r!!-\u0002\u0003MTC!a-\u00026B!a(!+BW\t\t9\f\u0005\u0003\u0002:\u0006\rWBAA^\u0015\u0011\ti,a0\u0002\u0013Ut7\r[3dW\u0016$'bAAai\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005\u0015\u00171\u0018\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,WCAAT\u000391\u0018\r\\;fg&#XM]1u_J$B!!4\u0002PB!a(!+P\u0011\u001d\tyK\u0005a\u0001\u0003c+\"!!4\u0003\u001b\u0011+g-Y;mi.+\u0017pU3u'\u0011!\u0012(a6\u0011\ty\nI\u000eR\u0005\u0004\u00037\u0004$A\u0002)beN+G/\u0001\u0004=S:LGO\u0010\u000b\u0003\u0003C\u00042!a9\u0015\u001b\u0005\u0001A\u0003BA\u0001\u0003ODQ! \fA\u0002\u0011\u000b\u0001b\u001d9mSR$XM\u001d\u000b\u0005\u0003/\fi\u000f\u0003\u0004\u0002pb\u0001\r\u0001R\u0001\u0005K2,W\u000e\u0006\u0003\u0002X\u0006M\bBBAx3\u0001\u0007A)\u0001\u0003tSj,WCAA\u000b\u0003%Ygn\\<o'&TX-A\u0004g_J,\u0017m\u00195\u0016\t\u0005}(Q\u0002\u000b\u0004i\n\u0005\u0001b\u0002B\u00029\u0001\u0007!QA\u0001\u0002MB1!Ha\u0002E\u0005\u0017I1A!\u00035\u0005%1UO\\2uS>t\u0017\u0007E\u0002F\u0005\u001b!a!a#\u001d\u0005\u0004A\u0015aA:fcV\u0011!1\u0003\t\u0005O\nUA)C\u0002\u0003\u0018I\u00121aU3u\u0005U!UMZ1vYR4\u0016\r\\;fg&#XM]1cY\u0016\u001cBAH\u001d\u0003\u001eA\u0019ahU(\u0015\u0005\t\u0005\u0002cAAr=U!!Q\u0005B\u0017)\r!(q\u0005\u0005\b\u0005\u0007\u0019\u0003\u0019\u0001B\u0015!\u0019Q$qA(\u0003,A\u0019QI!\f\u0005\r\u0005-5E1\u0001I+\t\u0011\t\u0004\u0005\u0003h\u0005gy\u0015b\u0001B\u001be\tA\u0011\n^3sC\ndW-\u0001\u0004lKf\u001cV\r^\u000b\u0003\u0003/\fAa[3zgV\u0011!q\b\t\u0004}M#\u0015A\u0002<bYV,7/\u0006\u0002\u0003\u001e\u0005Qa-\u001b7uKJ\\U-_:\u0015\u00079\u0014I\u0005C\u0004\u0003L!\u0002\rA!\u0014\u0002\u0003A\u0004bA\u000fB\u0004\t\u0006\u0005\u0011!C7baZ\u000bG.^3t+\u0011\u0011\u0019F!\u0017\u0015\t\tU#Q\f\t\u0006}y#%q\u000b\t\u0004\u000b\neCA\u0002B.S\t\u0007\u0001JA\u0001T\u0011\u001d\u0011\u0019!\u000ba\u0001\u0005?\u0002bA\u000fB\u0004\u001f\n]\u0013aA7baV1!Q\rB6\u0005c\"BAa\u001a\u0003vA9Q)a\t\u0003j\t=\u0004cA#\u0003l\u00111!Q\u000e\u0016C\u0002!\u0013!a\u0013\u001a\u0011\u0007\u0015\u0013\t\b\u0002\u0004\u0003t)\u0012\r\u0001\u0013\u0002\u0003-JBqAa\u0001+\u0001\u0004\u00119\b\u0005\u0004;\u0005\u000f\t%\u0011\u0010\t\u0007u\t\u0013IGa\u001c\u0002\u000f\r|G\u000e\\3diV1!q\u0010BC\u0005\u0013#BA!!\u0003\fB9Q)a\t\u0003\u0004\n\u001d\u0005cA#\u0003\u0006\u00121!QN\u0016C\u0002!\u00032!\u0012BE\t\u0019\u0011\u0019h\u000bb\u0001\u0011\"9!QR\u0016A\u0002\t=\u0015A\u00019g!\u0019Q$\u0011S!\u0003\u0016&\u0019!1\u0013\u001b\u0003\u001fA\u000b'\u000f^5bY\u001a+hn\u0019;j_:\u0004bA\u000f\"\u0003\u0004\n\u001d\u0015a\u00024mCRl\u0015\r]\u000b\u0007\u00057\u0013\tK!*\u0015\t\tu%q\u0015\t\b\u000b\u0006\r\"q\u0014BR!\r)%\u0011\u0015\u0003\u0007\u0005[b#\u0019\u0001%\u0011\u0007\u0015\u0013)\u000b\u0002\u0004\u0003t1\u0012\r\u0001\u0013\u0005\b\u0005\u0007a\u0003\u0019\u0001BU!\u0019Q$qA!\u0003,B)qM!,\u00032&\u0019!q\u0016\u001a\u0003\u0019%#XM]1cY\u0016|enY3\u0011\ri\u0012%q\u0014BR\u0003\u0019\u0019wN\\2biV!!q\u0017B_)\u0011\u0011ILa0\u0011\r\u0015\u000b\u0019\u0003\u0012B^!\r)%Q\u0018\u0003\b\u0005gj#\u0019AA \u0011\u001d\tI!\fa\u0001\u0005\u0003\u0004Ra\u001aBW\u0005\u0007\u0004RA\u000f\"E\u0005w\u000b!\u0002\n9mkN$\u0003\u000f\\;t+\u0011\u0011IMa4\u0015\t\t-'\u0011\u001b\t\u0007\u000b\u0006\rBI!4\u0011\u0007\u0015\u0013y\rB\u0004\u0003t9\u0012\r!a\u0010\t\u000f\tMg\u00061\u0001\u0003V\u0006\u0011\u0001p\u001d\t\u0006O\n5&q\u001b\t\u0006u\t#%Q\u001a\u0015\u0004]\tm\u0007c\u0001\u001e\u0003^&\u0019!q\u001c\u001b\u0003\r%tG.\u001b8f\u0001"
)
public interface ParMapLike extends ParIterableLike, Equals {
   Option get(final Object key);

   default boolean canEqual(final Object that) {
      return true;
   }

   default boolean equals(final Object that) {
      if (!(that instanceof ParMap)) {
         return false;
      } else {
         ParMap var4 = (ParMap)that;
         boolean var7;
         if (this != var4) {
            label42: {
               if (var4.canEqual(this) && this.size() == var4.size()) {
                  try {
                     var7 = this.forall((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$equals$1(var4, x0$1)));
                  } catch (ClassCastException var6) {
                     var7 = false;
                  }

                  if (var7) {
                     break label42;
                  }
               }

               var7 = false;
               return var7;
            }
         }

         var7 = true;
         return var7;
      }
   }

   default int hashCode() {
      return .MODULE$.unorderedHash(this, "ParMap".hashCode());
   }

   ParMap $plus(final Tuple2 kv);

   default ParMap updated(final Object key, final Object value) {
      return this.$plus(new Tuple2(key, value));
   }

   ParMap $minus(final Object key);

   GenericParMapCompanion mapCompanion();

   default Object default(final Object key) {
      throw new NoSuchElementException((new StringBuilder(15)).append("key not found: ").append(key).toString());
   }

   ParMap empty();

   default Object apply(final Object key) {
      Option var3 = this.get(key);
      if (var3 instanceof Some) {
         Some var4 = (Some)var3;
         Object v = var4.value();
         return v;
      } else if (scala.None..MODULE$.equals(var3)) {
         return this.default(key);
      } else {
         throw new MatchError(var3);
      }
   }

   default Object getOrElse(final Object key, final Function0 default) {
      Option var4 = this.get(key);
      if (var4 instanceof Some) {
         Some var5 = (Some)var4;
         Object v = var5.value();
         return v;
      } else if (scala.None..MODULE$.equals(var4)) {
         return default.apply();
      } else {
         throw new MatchError(var4);
      }
   }

   // $FF: synthetic method
   static boolean contains$(final ParMapLike $this, final Object key) {
      return $this.contains(key);
   }

   default boolean contains(final Object key) {
      return this.get(key).isDefined();
   }

   default boolean isDefinedAt(final Object key) {
      return this.contains(key);
   }

   // $FF: synthetic method
   static IterableSplitter scala$collection$parallel$ParMapLike$$keysIterator$(final ParMapLike $this, final IterableSplitter s) {
      return $this.scala$collection$parallel$ParMapLike$$keysIterator(s);
   }

   default IterableSplitter scala$collection$parallel$ParMapLike$$keysIterator(final IterableSplitter s) {
      return new IterableSplitter(s) {
         private final IterableSplitter iter;
         private Signalling signalDelegate;
         // $FF: synthetic field
         private final ParMapLike $outer;

         public Seq splitWithSignalling() {
            return IterableSplitter.splitWithSignalling$(this);
         }

         public boolean shouldSplitFurther(final ParIterable coll, final int parallelismLevel) {
            return IterableSplitter.shouldSplitFurther$(this, coll, parallelismLevel);
         }

         public String buildString(final Function1 closure) {
            return IterableSplitter.buildString$(this, closure);
         }

         public String debugInformation() {
            return IterableSplitter.debugInformation$(this);
         }

         public IterableSplitter.Taken newTaken(final int until) {
            return IterableSplitter.newTaken$(this, until);
         }

         public IterableSplitter.Taken newSliceInternal(final IterableSplitter.Taken it, final int from1) {
            return IterableSplitter.newSliceInternal$(this, it, from1);
         }

         public IterableSplitter drop(final int n) {
            return IterableSplitter.drop$(this, n);
         }

         public IterableSplitter take(final int n) {
            return IterableSplitter.take$(this, n);
         }

         public IterableSplitter slice(final int from1, final int until1) {
            return IterableSplitter.slice$(this, from1, until1);
         }

         public IterableSplitter map(final Function1 f) {
            return IterableSplitter.map$(this, f);
         }

         public IterableSplitter.Appended appendParIterable(final IterableSplitter that) {
            return IterableSplitter.appendParIterable$(this, that);
         }

         public IterableSplitter zipParSeq(final SeqSplitter that) {
            return IterableSplitter.zipParSeq$(this, that);
         }

         public IterableSplitter.ZippedAll zipAllParSeq(final SeqSplitter that, final Object thisElem, final Object thatElem) {
            return IterableSplitter.zipAllParSeq$(this, that, thisElem, thatElem);
         }

         public boolean isAborted() {
            return DelegatedSignalling.isAborted$(this);
         }

         public void abort() {
            DelegatedSignalling.abort$(this);
         }

         public int indexFlag() {
            return DelegatedSignalling.indexFlag$(this);
         }

         public void setIndexFlag(final int f) {
            DelegatedSignalling.setIndexFlag$(this, f);
         }

         public void setIndexFlagIfGreater(final int f) {
            DelegatedSignalling.setIndexFlagIfGreater$(this, f);
         }

         public void setIndexFlagIfLesser(final int f) {
            DelegatedSignalling.setIndexFlagIfLesser$(this, f);
         }

         public int tag() {
            return DelegatedSignalling.tag$(this);
         }

         public int count(final Function1 p) {
            return AugmentedIterableIterator.count$(this, p);
         }

         public Object reduce(final Function2 op) {
            return AugmentedIterableIterator.reduce$(this, op);
         }

         public Object fold(final Object z, final Function2 op) {
            return AugmentedIterableIterator.fold$(this, z, op);
         }

         public Object sum(final Numeric num) {
            return AugmentedIterableIterator.sum$(this, num);
         }

         public Object product(final Numeric num) {
            return AugmentedIterableIterator.product$(this, num);
         }

         public Object min(final Ordering ord) {
            return AugmentedIterableIterator.min$(this, ord);
         }

         public Object max(final Ordering ord) {
            return AugmentedIterableIterator.max$(this, ord);
         }

         public Object reduceLeft(final int howmany, final Function2 op) {
            return AugmentedIterableIterator.reduceLeft$(this, howmany, op);
         }

         public Combiner map2combiner(final Function1 f, final Combiner cb) {
            return AugmentedIterableIterator.map2combiner$(this, f, cb);
         }

         public Combiner collect2combiner(final PartialFunction pf, final Combiner cb) {
            return AugmentedIterableIterator.collect2combiner$(this, pf, cb);
         }

         public Combiner flatmap2combiner(final Function1 f, final Combiner cb) {
            return AugmentedIterableIterator.flatmap2combiner$(this, f, cb);
         }

         public Builder copy2builder(final Builder b) {
            return AugmentedIterableIterator.copy2builder$(this, b);
         }

         public Combiner filter2combiner(final Function1 pred, final Combiner cb) {
            return AugmentedIterableIterator.filter2combiner$(this, pred, cb);
         }

         public Combiner filterNot2combiner(final Function1 pred, final Combiner cb) {
            return AugmentedIterableIterator.filterNot2combiner$(this, pred, cb);
         }

         public Tuple2 partition2combiners(final Function1 pred, final Combiner btrue, final Combiner bfalse) {
            return AugmentedIterableIterator.partition2combiners$(this, pred, btrue, bfalse);
         }

         public Combiner take2combiner(final int n, final Combiner cb) {
            return AugmentedIterableIterator.take2combiner$(this, n, cb);
         }

         public Combiner drop2combiner(final int n, final Combiner cb) {
            return AugmentedIterableIterator.drop2combiner$(this, n, cb);
         }

         public Combiner slice2combiner(final int from, final int until, final Combiner cb) {
            return AugmentedIterableIterator.slice2combiner$(this, from, until, cb);
         }

         public Tuple2 splitAt2combiners(final int at, final Combiner before, final Combiner after) {
            return AugmentedIterableIterator.splitAt2combiners$(this, at, before, after);
         }

         public Tuple2 takeWhile2combiner(final Function1 p, final Combiner cb) {
            return AugmentedIterableIterator.takeWhile2combiner$(this, p, cb);
         }

         public Tuple2 span2combiners(final Function1 p, final Combiner before, final Combiner after) {
            return AugmentedIterableIterator.span2combiners$(this, p, before, after);
         }

         public void scanToArray(final Object z, final Function2 op, final Object array, final int from) {
            AugmentedIterableIterator.scanToArray$(this, z, op, array, from);
         }

         public Combiner scanToCombiner(final Object startValue, final Function2 op, final Combiner cb) {
            return AugmentedIterableIterator.scanToCombiner$(this, startValue, op, cb);
         }

         public Combiner scanToCombiner(final int howmany, final Object startValue, final Function2 op, final Combiner cb) {
            return AugmentedIterableIterator.scanToCombiner$(this, howmany, startValue, op, cb);
         }

         public Combiner zip2combiner(final RemainsIterator otherpit, final Combiner cb) {
            return AugmentedIterableIterator.zip2combiner$(this, otherpit, cb);
         }

         public Combiner zipAll2combiner(final RemainsIterator that, final Object thiselem, final Object thatelem, final Combiner cb) {
            return AugmentedIterableIterator.zipAll2combiner$(this, that, thiselem, thatelem, cb);
         }

         public boolean isRemainingCheap() {
            return RemainsIterator.isRemainingCheap$(this);
         }

         /** @deprecated */
         public final boolean hasDefiniteSize() {
            return Iterator.hasDefiniteSize$(this);
         }

         public final Iterator iterator() {
            return Iterator.iterator$(this);
         }

         public Option nextOption() {
            return Iterator.nextOption$(this);
         }

         public boolean contains(final Object elem) {
            return Iterator.contains$(this, elem);
         }

         public BufferedIterator buffered() {
            return Iterator.buffered$(this);
         }

         public Iterator padTo(final int len, final Object elem) {
            return Iterator.padTo$(this, len, elem);
         }

         public Tuple2 partition(final Function1 p) {
            return Iterator.partition$(this, p);
         }

         public Iterator.GroupedIterator grouped(final int size) {
            return Iterator.grouped$(this, size);
         }

         public Iterator.GroupedIterator sliding(final int size, final int step) {
            return Iterator.sliding$(this, size, step);
         }

         public int sliding$default$2() {
            return Iterator.sliding$default$2$(this);
         }

         public Iterator scanLeft(final Object z, final Function2 op) {
            return Iterator.scanLeft$(this, z, op);
         }

         /** @deprecated */
         public Iterator scanRight(final Object z, final Function2 op) {
            return Iterator.scanRight$(this, z, op);
         }

         public int indexWhere(final Function1 p, final int from) {
            return Iterator.indexWhere$(this, p, from);
         }

         public int indexWhere$default$2() {
            return Iterator.indexWhere$default$2$(this);
         }

         public int indexOf(final Object elem) {
            return Iterator.indexOf$(this, elem);
         }

         public int indexOf(final Object elem, final int from) {
            return Iterator.indexOf$(this, elem, from);
         }

         public final int length() {
            return Iterator.length$(this);
         }

         public boolean isEmpty() {
            return Iterator.isEmpty$(this);
         }

         public Iterator filter(final Function1 p) {
            return Iterator.filter$(this, p);
         }

         public Iterator filterNot(final Function1 p) {
            return Iterator.filterNot$(this, p);
         }

         public Iterator filterImpl(final Function1 p, final boolean isFlipped) {
            return Iterator.filterImpl$(this, p, isFlipped);
         }

         public Iterator withFilter(final Function1 p) {
            return Iterator.withFilter$(this, p);
         }

         public Iterator collect(final PartialFunction pf) {
            return Iterator.collect$(this, pf);
         }

         public Iterator distinct() {
            return Iterator.distinct$(this);
         }

         public Iterator distinctBy(final Function1 f) {
            return Iterator.distinctBy$(this, f);
         }

         public Iterator flatMap(final Function1 f) {
            return Iterator.flatMap$(this, f);
         }

         public Iterator flatten(final Function1 ev) {
            return Iterator.flatten$(this, ev);
         }

         public Iterator concat(final Function0 xs) {
            return Iterator.concat$(this, xs);
         }

         public final Iterator $plus$plus(final Function0 xs) {
            return Iterator.$plus$plus$(this, xs);
         }

         public Iterator takeWhile(final Function1 p) {
            return Iterator.takeWhile$(this, p);
         }

         public Iterator dropWhile(final Function1 p) {
            return Iterator.dropWhile$(this, p);
         }

         public Tuple2 span(final Function1 p) {
            return Iterator.span$(this, p);
         }

         public Iterator sliceIterator(final int from, final int until) {
            return Iterator.sliceIterator$(this, from, until);
         }

         public Iterator zip(final IterableOnce that) {
            return Iterator.zip$(this, that);
         }

         public Iterator zipAll(final IterableOnce that, final Object thisElem, final Object thatElem) {
            return Iterator.zipAll$(this, that, thisElem, thatElem);
         }

         public Iterator zipWithIndex() {
            return Iterator.zipWithIndex$(this);
         }

         public boolean sameElements(final IterableOnce that) {
            return Iterator.sameElements$(this, that);
         }

         public Tuple2 duplicate() {
            return Iterator.duplicate$(this);
         }

         public Iterator patch(final int from, final Iterator patchElems, final int replaced) {
            return Iterator.patch$(this, from, patchElems, replaced);
         }

         public Iterator tapEach(final Function1 f) {
            return Iterator.tapEach$(this, f);
         }

         public String toString() {
            return Iterator.toString$(this);
         }

         /** @deprecated */
         public Iterator seq() {
            return Iterator.seq$(this);
         }

         public Tuple2 splitAt(final int n) {
            return IterableOnceOps.splitAt$(this, n);
         }

         public boolean isTraversableAgain() {
            return IterableOnceOps.isTraversableAgain$(this);
         }

         public void foreach(final Function1 f) {
            IterableOnceOps.foreach$(this, f);
         }

         public boolean forall(final Function1 p) {
            return IterableOnceOps.forall$(this, p);
         }

         public boolean exists(final Function1 p) {
            return IterableOnceOps.exists$(this, p);
         }

         public Option find(final Function1 p) {
            return IterableOnceOps.find$(this, p);
         }

         public Object foldLeft(final Object z, final Function2 op) {
            return IterableOnceOps.foldLeft$(this, z, op);
         }

         public Object foldRight(final Object z, final Function2 op) {
            return IterableOnceOps.foldRight$(this, z, op);
         }

         /** @deprecated */
         public final Object $div$colon(final Object z, final Function2 op) {
            return IterableOnceOps.$div$colon$(this, z, op);
         }

         /** @deprecated */
         public final Object $colon$bslash(final Object z, final Function2 op) {
            return IterableOnceOps.$colon$bslash$(this, z, op);
         }

         public Option reduceOption(final Function2 op) {
            return IterableOnceOps.reduceOption$(this, op);
         }

         public Object reduceLeft(final Function2 op) {
            return IterableOnceOps.reduceLeft$(this, op);
         }

         public Object reduceRight(final Function2 op) {
            return IterableOnceOps.reduceRight$(this, op);
         }

         public Option reduceLeftOption(final Function2 op) {
            return IterableOnceOps.reduceLeftOption$(this, op);
         }

         public Option reduceRightOption(final Function2 op) {
            return IterableOnceOps.reduceRightOption$(this, op);
         }

         public boolean nonEmpty() {
            return IterableOnceOps.nonEmpty$(this);
         }

         public int size() {
            return IterableOnceOps.size$(this);
         }

         /** @deprecated */
         public final void copyToBuffer(final Buffer dest) {
            IterableOnceOps.copyToBuffer$(this, dest);
         }

         public int copyToArray(final Object xs) {
            return IterableOnceOps.copyToArray$(this, xs);
         }

         public int copyToArray(final Object xs, final int start) {
            return IterableOnceOps.copyToArray$(this, xs, start);
         }

         public int copyToArray(final Object xs, final int start, final int len) {
            return IterableOnceOps.copyToArray$(this, xs, start, len);
         }

         public Option minOption(final Ordering ord) {
            return IterableOnceOps.minOption$(this, ord);
         }

         public Option maxOption(final Ordering ord) {
            return IterableOnceOps.maxOption$(this, ord);
         }

         public Object maxBy(final Function1 f, final Ordering ord) {
            return IterableOnceOps.maxBy$(this, f, ord);
         }

         public Option maxByOption(final Function1 f, final Ordering ord) {
            return IterableOnceOps.maxByOption$(this, f, ord);
         }

         public Object minBy(final Function1 f, final Ordering ord) {
            return IterableOnceOps.minBy$(this, f, ord);
         }

         public Option minByOption(final Function1 f, final Ordering ord) {
            return IterableOnceOps.minByOption$(this, f, ord);
         }

         public Option collectFirst(final PartialFunction pf) {
            return IterableOnceOps.collectFirst$(this, pf);
         }

         /** @deprecated */
         public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
            return IterableOnceOps.aggregate$(this, z, seqop, combop);
         }

         public boolean corresponds(final IterableOnce that, final Function2 p) {
            return IterableOnceOps.corresponds$(this, that, p);
         }

         public final String mkString(final String start, final String sep, final String end) {
            return IterableOnceOps.mkString$(this, start, sep, end);
         }

         public final String mkString(final String sep) {
            return IterableOnceOps.mkString$(this, sep);
         }

         public final String mkString() {
            return IterableOnceOps.mkString$(this);
         }

         public scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b, final String start, final String sep, final String end) {
            return IterableOnceOps.addString$(this, b, start, sep, end);
         }

         public final scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b, final String sep) {
            return IterableOnceOps.addString$(this, b, sep);
         }

         public final scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b) {
            return IterableOnceOps.addString$(this, b);
         }

         public Object to(final Factory factory) {
            return IterableOnceOps.to$(this, factory);
         }

         /** @deprecated */
         public final Iterator toIterator() {
            return IterableOnceOps.toIterator$(this);
         }

         public List toList() {
            return IterableOnceOps.toList$(this);
         }

         public Vector toVector() {
            return IterableOnceOps.toVector$(this);
         }

         public scala.collection.immutable.Map toMap(final scala..less.colon.less ev) {
            return IterableOnceOps.toMap$(this, ev);
         }

         public Set toSet() {
            return IterableOnceOps.toSet$(this);
         }

         public Seq toSeq() {
            return IterableOnceOps.toSeq$(this);
         }

         public IndexedSeq toIndexedSeq() {
            return IterableOnceOps.toIndexedSeq$(this);
         }

         /** @deprecated */
         public final Stream toStream() {
            return IterableOnceOps.toStream$(this);
         }

         public final Buffer toBuffer() {
            return IterableOnceOps.toBuffer$(this);
         }

         public Object toArray(final ClassTag evidence$2) {
            return IterableOnceOps.toArray$(this, evidence$2);
         }

         public Iterable reversed() {
            return IterableOnceOps.reversed$(this);
         }

         public Stepper stepper(final StepperShape shape) {
            return IterableOnce.stepper$(this, shape);
         }

         public int knownSize() {
            return IterableOnce.knownSize$(this);
         }

         public Signalling signalDelegate() {
            return this.signalDelegate;
         }

         public void signalDelegate_$eq(final Signalling x$1) {
            this.signalDelegate = x$1;
         }

         private IterableSplitter iter() {
            return this.iter;
         }

         public boolean hasNext() {
            return this.iter().hasNext();
         }

         public Object next() {
            return ((Tuple2)this.iter().next())._1();
         }

         public Seq split() {
            Seq ss = (Seq)this.iter().split().map((x$1) -> this.$outer.scala$collection$parallel$ParMapLike$$keysIterator(x$1));
            ss.foreach((x$2) -> {
               $anonfun$split$2(this, x$2);
               return BoxedUnit.UNIT;
            });
            return ss;
         }

         public int remaining() {
            return this.iter().remaining();
         }

         public IterableSplitter dup() {
            return this.$outer.scala$collection$parallel$ParMapLike$$keysIterator(this.iter().dup());
         }

         // $FF: synthetic method
         public static final void $anonfun$split$2(final Object $this, final IterableSplitter x$2) {
            x$2.signalDelegate_$eq($this.signalDelegate());
         }

         public {
            if (ParMapLike.this == null) {
               throw null;
            } else {
               this.$outer = ParMapLike.this;
               IterableOnce.$init$(this);
               IterableOnceOps.$init$(this);
               Iterator.$init$(this);
               RemainsIterator.$init$(this);
               AugmentedIterableIterator.$init$(this);
               DelegatedSignalling.$init$(this);
               IterableSplitter.$init$(this);
               this.iter = s$1;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   default IterableSplitter keysIterator() {
      return this.scala$collection$parallel$ParMapLike$$keysIterator(this.splitter());
   }

   // $FF: synthetic method
   static IterableSplitter scala$collection$parallel$ParMapLike$$valuesIterator$(final ParMapLike $this, final IterableSplitter s) {
      return $this.scala$collection$parallel$ParMapLike$$valuesIterator(s);
   }

   default IterableSplitter scala$collection$parallel$ParMapLike$$valuesIterator(final IterableSplitter s) {
      return new IterableSplitter(s) {
         private final IterableSplitter iter;
         private Signalling signalDelegate;
         // $FF: synthetic field
         private final ParMapLike $outer;

         public Seq splitWithSignalling() {
            return IterableSplitter.splitWithSignalling$(this);
         }

         public boolean shouldSplitFurther(final ParIterable coll, final int parallelismLevel) {
            return IterableSplitter.shouldSplitFurther$(this, coll, parallelismLevel);
         }

         public String buildString(final Function1 closure) {
            return IterableSplitter.buildString$(this, closure);
         }

         public String debugInformation() {
            return IterableSplitter.debugInformation$(this);
         }

         public IterableSplitter.Taken newTaken(final int until) {
            return IterableSplitter.newTaken$(this, until);
         }

         public IterableSplitter.Taken newSliceInternal(final IterableSplitter.Taken it, final int from1) {
            return IterableSplitter.newSliceInternal$(this, it, from1);
         }

         public IterableSplitter drop(final int n) {
            return IterableSplitter.drop$(this, n);
         }

         public IterableSplitter take(final int n) {
            return IterableSplitter.take$(this, n);
         }

         public IterableSplitter slice(final int from1, final int until1) {
            return IterableSplitter.slice$(this, from1, until1);
         }

         public IterableSplitter map(final Function1 f) {
            return IterableSplitter.map$(this, f);
         }

         public IterableSplitter.Appended appendParIterable(final IterableSplitter that) {
            return IterableSplitter.appendParIterable$(this, that);
         }

         public IterableSplitter zipParSeq(final SeqSplitter that) {
            return IterableSplitter.zipParSeq$(this, that);
         }

         public IterableSplitter.ZippedAll zipAllParSeq(final SeqSplitter that, final Object thisElem, final Object thatElem) {
            return IterableSplitter.zipAllParSeq$(this, that, thisElem, thatElem);
         }

         public boolean isAborted() {
            return DelegatedSignalling.isAborted$(this);
         }

         public void abort() {
            DelegatedSignalling.abort$(this);
         }

         public int indexFlag() {
            return DelegatedSignalling.indexFlag$(this);
         }

         public void setIndexFlag(final int f) {
            DelegatedSignalling.setIndexFlag$(this, f);
         }

         public void setIndexFlagIfGreater(final int f) {
            DelegatedSignalling.setIndexFlagIfGreater$(this, f);
         }

         public void setIndexFlagIfLesser(final int f) {
            DelegatedSignalling.setIndexFlagIfLesser$(this, f);
         }

         public int tag() {
            return DelegatedSignalling.tag$(this);
         }

         public int count(final Function1 p) {
            return AugmentedIterableIterator.count$(this, p);
         }

         public Object reduce(final Function2 op) {
            return AugmentedIterableIterator.reduce$(this, op);
         }

         public Object fold(final Object z, final Function2 op) {
            return AugmentedIterableIterator.fold$(this, z, op);
         }

         public Object sum(final Numeric num) {
            return AugmentedIterableIterator.sum$(this, num);
         }

         public Object product(final Numeric num) {
            return AugmentedIterableIterator.product$(this, num);
         }

         public Object min(final Ordering ord) {
            return AugmentedIterableIterator.min$(this, ord);
         }

         public Object max(final Ordering ord) {
            return AugmentedIterableIterator.max$(this, ord);
         }

         public Object reduceLeft(final int howmany, final Function2 op) {
            return AugmentedIterableIterator.reduceLeft$(this, howmany, op);
         }

         public Combiner map2combiner(final Function1 f, final Combiner cb) {
            return AugmentedIterableIterator.map2combiner$(this, f, cb);
         }

         public Combiner collect2combiner(final PartialFunction pf, final Combiner cb) {
            return AugmentedIterableIterator.collect2combiner$(this, pf, cb);
         }

         public Combiner flatmap2combiner(final Function1 f, final Combiner cb) {
            return AugmentedIterableIterator.flatmap2combiner$(this, f, cb);
         }

         public Builder copy2builder(final Builder b) {
            return AugmentedIterableIterator.copy2builder$(this, b);
         }

         public Combiner filter2combiner(final Function1 pred, final Combiner cb) {
            return AugmentedIterableIterator.filter2combiner$(this, pred, cb);
         }

         public Combiner filterNot2combiner(final Function1 pred, final Combiner cb) {
            return AugmentedIterableIterator.filterNot2combiner$(this, pred, cb);
         }

         public Tuple2 partition2combiners(final Function1 pred, final Combiner btrue, final Combiner bfalse) {
            return AugmentedIterableIterator.partition2combiners$(this, pred, btrue, bfalse);
         }

         public Combiner take2combiner(final int n, final Combiner cb) {
            return AugmentedIterableIterator.take2combiner$(this, n, cb);
         }

         public Combiner drop2combiner(final int n, final Combiner cb) {
            return AugmentedIterableIterator.drop2combiner$(this, n, cb);
         }

         public Combiner slice2combiner(final int from, final int until, final Combiner cb) {
            return AugmentedIterableIterator.slice2combiner$(this, from, until, cb);
         }

         public Tuple2 splitAt2combiners(final int at, final Combiner before, final Combiner after) {
            return AugmentedIterableIterator.splitAt2combiners$(this, at, before, after);
         }

         public Tuple2 takeWhile2combiner(final Function1 p, final Combiner cb) {
            return AugmentedIterableIterator.takeWhile2combiner$(this, p, cb);
         }

         public Tuple2 span2combiners(final Function1 p, final Combiner before, final Combiner after) {
            return AugmentedIterableIterator.span2combiners$(this, p, before, after);
         }

         public void scanToArray(final Object z, final Function2 op, final Object array, final int from) {
            AugmentedIterableIterator.scanToArray$(this, z, op, array, from);
         }

         public Combiner scanToCombiner(final Object startValue, final Function2 op, final Combiner cb) {
            return AugmentedIterableIterator.scanToCombiner$(this, startValue, op, cb);
         }

         public Combiner scanToCombiner(final int howmany, final Object startValue, final Function2 op, final Combiner cb) {
            return AugmentedIterableIterator.scanToCombiner$(this, howmany, startValue, op, cb);
         }

         public Combiner zip2combiner(final RemainsIterator otherpit, final Combiner cb) {
            return AugmentedIterableIterator.zip2combiner$(this, otherpit, cb);
         }

         public Combiner zipAll2combiner(final RemainsIterator that, final Object thiselem, final Object thatelem, final Combiner cb) {
            return AugmentedIterableIterator.zipAll2combiner$(this, that, thiselem, thatelem, cb);
         }

         public boolean isRemainingCheap() {
            return RemainsIterator.isRemainingCheap$(this);
         }

         /** @deprecated */
         public final boolean hasDefiniteSize() {
            return Iterator.hasDefiniteSize$(this);
         }

         public final Iterator iterator() {
            return Iterator.iterator$(this);
         }

         public Option nextOption() {
            return Iterator.nextOption$(this);
         }

         public boolean contains(final Object elem) {
            return Iterator.contains$(this, elem);
         }

         public BufferedIterator buffered() {
            return Iterator.buffered$(this);
         }

         public Iterator padTo(final int len, final Object elem) {
            return Iterator.padTo$(this, len, elem);
         }

         public Tuple2 partition(final Function1 p) {
            return Iterator.partition$(this, p);
         }

         public Iterator.GroupedIterator grouped(final int size) {
            return Iterator.grouped$(this, size);
         }

         public Iterator.GroupedIterator sliding(final int size, final int step) {
            return Iterator.sliding$(this, size, step);
         }

         public int sliding$default$2() {
            return Iterator.sliding$default$2$(this);
         }

         public Iterator scanLeft(final Object z, final Function2 op) {
            return Iterator.scanLeft$(this, z, op);
         }

         /** @deprecated */
         public Iterator scanRight(final Object z, final Function2 op) {
            return Iterator.scanRight$(this, z, op);
         }

         public int indexWhere(final Function1 p, final int from) {
            return Iterator.indexWhere$(this, p, from);
         }

         public int indexWhere$default$2() {
            return Iterator.indexWhere$default$2$(this);
         }

         public int indexOf(final Object elem) {
            return Iterator.indexOf$(this, elem);
         }

         public int indexOf(final Object elem, final int from) {
            return Iterator.indexOf$(this, elem, from);
         }

         public final int length() {
            return Iterator.length$(this);
         }

         public boolean isEmpty() {
            return Iterator.isEmpty$(this);
         }

         public Iterator filter(final Function1 p) {
            return Iterator.filter$(this, p);
         }

         public Iterator filterNot(final Function1 p) {
            return Iterator.filterNot$(this, p);
         }

         public Iterator filterImpl(final Function1 p, final boolean isFlipped) {
            return Iterator.filterImpl$(this, p, isFlipped);
         }

         public Iterator withFilter(final Function1 p) {
            return Iterator.withFilter$(this, p);
         }

         public Iterator collect(final PartialFunction pf) {
            return Iterator.collect$(this, pf);
         }

         public Iterator distinct() {
            return Iterator.distinct$(this);
         }

         public Iterator distinctBy(final Function1 f) {
            return Iterator.distinctBy$(this, f);
         }

         public Iterator flatMap(final Function1 f) {
            return Iterator.flatMap$(this, f);
         }

         public Iterator flatten(final Function1 ev) {
            return Iterator.flatten$(this, ev);
         }

         public Iterator concat(final Function0 xs) {
            return Iterator.concat$(this, xs);
         }

         public final Iterator $plus$plus(final Function0 xs) {
            return Iterator.$plus$plus$(this, xs);
         }

         public Iterator takeWhile(final Function1 p) {
            return Iterator.takeWhile$(this, p);
         }

         public Iterator dropWhile(final Function1 p) {
            return Iterator.dropWhile$(this, p);
         }

         public Tuple2 span(final Function1 p) {
            return Iterator.span$(this, p);
         }

         public Iterator sliceIterator(final int from, final int until) {
            return Iterator.sliceIterator$(this, from, until);
         }

         public Iterator zip(final IterableOnce that) {
            return Iterator.zip$(this, that);
         }

         public Iterator zipAll(final IterableOnce that, final Object thisElem, final Object thatElem) {
            return Iterator.zipAll$(this, that, thisElem, thatElem);
         }

         public Iterator zipWithIndex() {
            return Iterator.zipWithIndex$(this);
         }

         public boolean sameElements(final IterableOnce that) {
            return Iterator.sameElements$(this, that);
         }

         public Tuple2 duplicate() {
            return Iterator.duplicate$(this);
         }

         public Iterator patch(final int from, final Iterator patchElems, final int replaced) {
            return Iterator.patch$(this, from, patchElems, replaced);
         }

         public Iterator tapEach(final Function1 f) {
            return Iterator.tapEach$(this, f);
         }

         public String toString() {
            return Iterator.toString$(this);
         }

         /** @deprecated */
         public Iterator seq() {
            return Iterator.seq$(this);
         }

         public Tuple2 splitAt(final int n) {
            return IterableOnceOps.splitAt$(this, n);
         }

         public boolean isTraversableAgain() {
            return IterableOnceOps.isTraversableAgain$(this);
         }

         public void foreach(final Function1 f) {
            IterableOnceOps.foreach$(this, f);
         }

         public boolean forall(final Function1 p) {
            return IterableOnceOps.forall$(this, p);
         }

         public boolean exists(final Function1 p) {
            return IterableOnceOps.exists$(this, p);
         }

         public Option find(final Function1 p) {
            return IterableOnceOps.find$(this, p);
         }

         public Object foldLeft(final Object z, final Function2 op) {
            return IterableOnceOps.foldLeft$(this, z, op);
         }

         public Object foldRight(final Object z, final Function2 op) {
            return IterableOnceOps.foldRight$(this, z, op);
         }

         /** @deprecated */
         public final Object $div$colon(final Object z, final Function2 op) {
            return IterableOnceOps.$div$colon$(this, z, op);
         }

         /** @deprecated */
         public final Object $colon$bslash(final Object z, final Function2 op) {
            return IterableOnceOps.$colon$bslash$(this, z, op);
         }

         public Option reduceOption(final Function2 op) {
            return IterableOnceOps.reduceOption$(this, op);
         }

         public Object reduceLeft(final Function2 op) {
            return IterableOnceOps.reduceLeft$(this, op);
         }

         public Object reduceRight(final Function2 op) {
            return IterableOnceOps.reduceRight$(this, op);
         }

         public Option reduceLeftOption(final Function2 op) {
            return IterableOnceOps.reduceLeftOption$(this, op);
         }

         public Option reduceRightOption(final Function2 op) {
            return IterableOnceOps.reduceRightOption$(this, op);
         }

         public boolean nonEmpty() {
            return IterableOnceOps.nonEmpty$(this);
         }

         public int size() {
            return IterableOnceOps.size$(this);
         }

         /** @deprecated */
         public final void copyToBuffer(final Buffer dest) {
            IterableOnceOps.copyToBuffer$(this, dest);
         }

         public int copyToArray(final Object xs) {
            return IterableOnceOps.copyToArray$(this, xs);
         }

         public int copyToArray(final Object xs, final int start) {
            return IterableOnceOps.copyToArray$(this, xs, start);
         }

         public int copyToArray(final Object xs, final int start, final int len) {
            return IterableOnceOps.copyToArray$(this, xs, start, len);
         }

         public Option minOption(final Ordering ord) {
            return IterableOnceOps.minOption$(this, ord);
         }

         public Option maxOption(final Ordering ord) {
            return IterableOnceOps.maxOption$(this, ord);
         }

         public Object maxBy(final Function1 f, final Ordering ord) {
            return IterableOnceOps.maxBy$(this, f, ord);
         }

         public Option maxByOption(final Function1 f, final Ordering ord) {
            return IterableOnceOps.maxByOption$(this, f, ord);
         }

         public Object minBy(final Function1 f, final Ordering ord) {
            return IterableOnceOps.minBy$(this, f, ord);
         }

         public Option minByOption(final Function1 f, final Ordering ord) {
            return IterableOnceOps.minByOption$(this, f, ord);
         }

         public Option collectFirst(final PartialFunction pf) {
            return IterableOnceOps.collectFirst$(this, pf);
         }

         /** @deprecated */
         public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
            return IterableOnceOps.aggregate$(this, z, seqop, combop);
         }

         public boolean corresponds(final IterableOnce that, final Function2 p) {
            return IterableOnceOps.corresponds$(this, that, p);
         }

         public final String mkString(final String start, final String sep, final String end) {
            return IterableOnceOps.mkString$(this, start, sep, end);
         }

         public final String mkString(final String sep) {
            return IterableOnceOps.mkString$(this, sep);
         }

         public final String mkString() {
            return IterableOnceOps.mkString$(this);
         }

         public scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b, final String start, final String sep, final String end) {
            return IterableOnceOps.addString$(this, b, start, sep, end);
         }

         public final scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b, final String sep) {
            return IterableOnceOps.addString$(this, b, sep);
         }

         public final scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b) {
            return IterableOnceOps.addString$(this, b);
         }

         public Object to(final Factory factory) {
            return IterableOnceOps.to$(this, factory);
         }

         /** @deprecated */
         public final Iterator toIterator() {
            return IterableOnceOps.toIterator$(this);
         }

         public List toList() {
            return IterableOnceOps.toList$(this);
         }

         public Vector toVector() {
            return IterableOnceOps.toVector$(this);
         }

         public scala.collection.immutable.Map toMap(final scala..less.colon.less ev) {
            return IterableOnceOps.toMap$(this, ev);
         }

         public Set toSet() {
            return IterableOnceOps.toSet$(this);
         }

         public Seq toSeq() {
            return IterableOnceOps.toSeq$(this);
         }

         public IndexedSeq toIndexedSeq() {
            return IterableOnceOps.toIndexedSeq$(this);
         }

         /** @deprecated */
         public final Stream toStream() {
            return IterableOnceOps.toStream$(this);
         }

         public final Buffer toBuffer() {
            return IterableOnceOps.toBuffer$(this);
         }

         public Object toArray(final ClassTag evidence$2) {
            return IterableOnceOps.toArray$(this, evidence$2);
         }

         public Iterable reversed() {
            return IterableOnceOps.reversed$(this);
         }

         public Stepper stepper(final StepperShape shape) {
            return IterableOnce.stepper$(this, shape);
         }

         public int knownSize() {
            return IterableOnce.knownSize$(this);
         }

         public Signalling signalDelegate() {
            return this.signalDelegate;
         }

         public void signalDelegate_$eq(final Signalling x$1) {
            this.signalDelegate = x$1;
         }

         private IterableSplitter iter() {
            return this.iter;
         }

         public boolean hasNext() {
            return this.iter().hasNext();
         }

         public Object next() {
            return ((Tuple2)this.iter().next())._2();
         }

         public Seq split() {
            Seq ss = (Seq)this.iter().split().map((x$3) -> this.$outer.scala$collection$parallel$ParMapLike$$valuesIterator(x$3));
            ss.foreach((x$4) -> {
               $anonfun$split$4(this, x$4);
               return BoxedUnit.UNIT;
            });
            return ss;
         }

         public int remaining() {
            return this.iter().remaining();
         }

         public IterableSplitter dup() {
            return this.$outer.scala$collection$parallel$ParMapLike$$valuesIterator(this.iter().dup());
         }

         // $FF: synthetic method
         public static final void $anonfun$split$4(final Object $this, final IterableSplitter x$4) {
            x$4.signalDelegate_$eq($this.signalDelegate());
         }

         public {
            if (ParMapLike.this == null) {
               throw null;
            } else {
               this.$outer = ParMapLike.this;
               IterableOnce.$init$(this);
               IterableOnceOps.$init$(this);
               Iterator.$init$(this);
               RemainsIterator.$init$(this);
               AugmentedIterableIterator.$init$(this);
               DelegatedSignalling.$init$(this);
               IterableSplitter.$init$(this);
               this.iter = s$2;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   default IterableSplitter valuesIterator() {
      return this.scala$collection$parallel$ParMapLike$$valuesIterator(this.splitter());
   }

   default ParSet keySet() {
      return new DefaultKeySet();
   }

   default ParIterable keys() {
      return this.keySet();
   }

   default ParIterable values() {
      return new DefaultValuesIterable();
   }

   default ParMap filterKeys(final Function1 p) {
      return new ParMap(p) {
         private ParMap filtered;
         private transient volatile TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
         private volatile ParIterableLike.ScanNode$ ScanNode$module;
         private volatile ParIterableLike.ScanLeaf$ ScanLeaf$module;
         private volatile boolean bitmap$0;
         // $FF: synthetic field
         private final ParMapLike $outer;
         private final Function1 p$1;

         public GenericParMapCompanion mapCompanion() {
            return ParMap.mapCompanion$(this);
         }

         public ParMap empty() {
            return ParMap.empty$(this);
         }

         public String stringPrefix() {
            return ParMap.stringPrefix$(this);
         }

         public boolean canEqual(final Object that) {
            return ParMapLike.super.canEqual(that);
         }

         public boolean equals(final Object that) {
            return ParMapLike.super.equals(that);
         }

         public int hashCode() {
            return ParMapLike.super.hashCode();
         }

         public ParMap updated(final Object key, final Object value) {
            return ParMapLike.super.updated(key, value);
         }

         public Object default(final Object key) {
            return ParMapLike.super.default(key);
         }

         public Object apply(final Object key) {
            return ParMapLike.super.apply(key);
         }

         public Object getOrElse(final Object key, final Function0 default) {
            return ParMapLike.super.getOrElse(key, default);
         }

         public boolean isDefinedAt(final Object key) {
            return ParMapLike.super.isDefinedAt(key);
         }

         public IterableSplitter keysIterator() {
            return ParMapLike.super.keysIterator();
         }

         public IterableSplitter valuesIterator() {
            return ParMapLike.super.valuesIterator();
         }

         public ParSet keySet() {
            return ParMapLike.super.keySet();
         }

         public ParIterable keys() {
            return ParMapLike.super.keys();
         }

         public ParIterable values() {
            return ParMapLike.super.values();
         }

         public ParMap filterKeys(final Function1 p) {
            return ParMapLike.super.filterKeys(p);
         }

         public ParMap mapValues(final Function1 f) {
            return ParMapLike.super.mapValues(f);
         }

         public ParMap map(final Function1 f) {
            return ParMapLike.super.map(f);
         }

         public ParMap collect(final PartialFunction pf) {
            return ParMapLike.super.collect(pf);
         }

         public ParMap flatMap(final Function1 f) {
            return ParMapLike.super.flatMap(f);
         }

         public ParMap concat(final IterableOnce that) {
            return ParMapLike.super.concat(that);
         }

         public final ParMap $plus$plus(final IterableOnce xs) {
            return ParMapLike.super.$plus$plus(xs);
         }

         public GenericParCompanion companion() {
            return ParIterable.companion$(this);
         }

         public void initTaskSupport() {
            ParIterableLike.initTaskSupport$(this);
         }

         public TaskSupport tasksupport() {
            return ParIterableLike.tasksupport$(this);
         }

         public void tasksupport_$eq(final TaskSupport ts) {
            ParIterableLike.tasksupport_$eq$(this, ts);
         }

         public ParIterable repr() {
            return ParIterableLike.repr$(this);
         }

         public final boolean isTraversableAgain() {
            return ParIterableLike.isTraversableAgain$(this);
         }

         public boolean hasDefiniteSize() {
            return ParIterableLike.hasDefiniteSize$(this);
         }

         public boolean isEmpty() {
            return ParIterableLike.isEmpty$(this);
         }

         public boolean nonEmpty() {
            return ParIterableLike.nonEmpty$(this);
         }

         public Object head() {
            return ParIterableLike.head$(this);
         }

         public Option headOption() {
            return ParIterableLike.headOption$(this);
         }

         public ParIterable tail() {
            return ParIterableLike.tail$(this);
         }

         public Object last() {
            return ParIterableLike.last$(this);
         }

         public Option lastOption() {
            return ParIterableLike.lastOption$(this);
         }

         public ParIterable init() {
            return ParIterableLike.init$(this);
         }

         public Splitter iterator() {
            return ParIterableLike.iterator$(this);
         }

         public ParIterable par() {
            return ParIterableLike.par$(this);
         }

         public boolean isStrictSplitterCollection() {
            return ParIterableLike.isStrictSplitterCollection$(this);
         }

         public Combiner reuse(final Option oldc, final Combiner newc) {
            return ParIterableLike.reuse$(this, oldc, newc);
         }

         public ParIterableLike.TaskOps task2ops(final ParIterableLike.StrictSplitterCheckTask tsk) {
            return ParIterableLike.task2ops$(this, tsk);
         }

         public ParIterableLike.NonDivisible wrap(final Function0 body) {
            return ParIterableLike.wrap$(this, body);
         }

         public ParIterableLike.SignallingOps delegatedSignalling2ops(final DelegatedSignalling it) {
            return ParIterableLike.delegatedSignalling2ops$(this, it);
         }

         public ParIterableLike.BuilderOps builder2ops(final Builder cb) {
            return ParIterableLike.builder2ops$(this, cb);
         }

         public ParIterable sequentially(final Function1 b) {
            return ParIterableLike.sequentially$(this, b);
         }

         public String mkString(final String start, final String sep, final String end) {
            return ParIterableLike.mkString$(this, start, sep, end);
         }

         public String mkString(final String sep) {
            return ParIterableLike.mkString$(this, sep);
         }

         public String mkString() {
            return ParIterableLike.mkString$(this);
         }

         public String toString() {
            return ParIterableLike.toString$(this);
         }

         public Object reduce(final Function2 op) {
            return ParIterableLike.reduce$(this, op);
         }

         public Option reduceOption(final Function2 op) {
            return ParIterableLike.reduceOption$(this, op);
         }

         public Object fold(final Object z, final Function2 op) {
            return ParIterableLike.fold$(this, z, op);
         }

         public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
            return ParIterableLike.aggregate$(this, z, seqop, combop);
         }

         public Object foldLeft(final Object z, final Function2 op) {
            return ParIterableLike.foldLeft$(this, z, op);
         }

         public Object foldRight(final Object z, final Function2 op) {
            return ParIterableLike.foldRight$(this, z, op);
         }

         public Object reduceLeft(final Function2 op) {
            return ParIterableLike.reduceLeft$(this, op);
         }

         public Object reduceRight(final Function2 op) {
            return ParIterableLike.reduceRight$(this, op);
         }

         public Option reduceLeftOption(final Function2 op) {
            return ParIterableLike.reduceLeftOption$(this, op);
         }

         public Option reduceRightOption(final Function2 op) {
            return ParIterableLike.reduceRightOption$(this, op);
         }

         public int count(final Function1 p) {
            return ParIterableLike.count$(this, p);
         }

         public Object sum(final Numeric num) {
            return ParIterableLike.sum$(this, num);
         }

         public Object product(final Numeric num) {
            return ParIterableLike.product$(this, num);
         }

         public Object min(final Ordering ord) {
            return ParIterableLike.min$(this, ord);
         }

         public Object max(final Ordering ord) {
            return ParIterableLike.max$(this, ord);
         }

         public Object maxBy(final Function1 f, final Ordering cmp) {
            return ParIterableLike.maxBy$(this, f, cmp);
         }

         public Object minBy(final Function1 f, final Ordering cmp) {
            return ParIterableLike.minBy$(this, f, cmp);
         }

         public ParIterable map(final Function1 f) {
            return ParIterableLike.map$(this, f);
         }

         public ParIterable collect(final PartialFunction pf) {
            return ParIterableLike.collect$(this, pf);
         }

         public ParIterable flatMap(final Function1 f) {
            return ParIterableLike.flatMap$(this, f);
         }

         public boolean forall(final Function1 p) {
            return ParIterableLike.forall$(this, p);
         }

         public boolean exists(final Function1 p) {
            return ParIterableLike.exists$(this, p);
         }

         public Option find(final Function1 p) {
            return ParIterableLike.find$(this, p);
         }

         public CombinerFactory combinerFactory() {
            return ParIterableLike.combinerFactory$(this);
         }

         public CombinerFactory combinerFactory(final Function0 cbf) {
            return ParIterableLike.combinerFactory$(this, cbf);
         }

         public ParIterable withFilter(final Function1 pred) {
            return ParIterableLike.withFilter$(this, pred);
         }

         public ParIterable filter(final Function1 pred) {
            return ParIterableLike.filter$(this, pred);
         }

         public ParIterable filterNot(final Function1 pred) {
            return ParIterableLike.filterNot$(this, pred);
         }

         public ParIterable $plus$plus(final IterableOnce that) {
            return ParIterableLike.$plus$plus$(this, that);
         }

         public Tuple2 partition(final Function1 pred) {
            return ParIterableLike.partition$(this, pred);
         }

         public scala.collection.parallel.immutable.ParMap groupBy(final Function1 f) {
            return ParIterableLike.groupBy$(this, f);
         }

         public ParIterable take(final int n) {
            return ParIterableLike.take$(this, n);
         }

         public ParIterable drop(final int n) {
            return ParIterableLike.drop$(this, n);
         }

         public ParIterable slice(final int unc_from, final int unc_until) {
            return ParIterableLike.slice$(this, unc_from, unc_until);
         }

         public Tuple2 splitAt(final int n) {
            return ParIterableLike.splitAt$(this, n);
         }

         public ParIterable scan(final Object z, final Function2 op) {
            return ParIterableLike.scan$(this, z, op);
         }

         public Iterable scanLeft(final Object z, final Function2 op) {
            return ParIterableLike.scanLeft$(this, z, op);
         }

         public Iterable scanRight(final Object z, final Function2 op) {
            return ParIterableLike.scanRight$(this, z, op);
         }

         public ParIterable takeWhile(final Function1 pred) {
            return ParIterableLike.takeWhile$(this, pred);
         }

         public Tuple2 span(final Function1 pred) {
            return ParIterableLike.span$(this, pred);
         }

         public ParIterable dropWhile(final Function1 pred) {
            return ParIterableLike.dropWhile$(this, pred);
         }

         public void copyToArray(final Object xs) {
            ParIterableLike.copyToArray$(this, xs);
         }

         public void copyToArray(final Object xs, final int start) {
            ParIterableLike.copyToArray$(this, xs, start);
         }

         public void copyToArray(final Object xs, final int start, final int len) {
            ParIterableLike.copyToArray$(this, xs, start, len);
         }

         public boolean sameElements(final IterableOnce that) {
            return ParIterableLike.sameElements$(this, that);
         }

         public ParIterable zip(final ParIterable that) {
            return ParIterableLike.zip$(this, (ParIterable)that);
         }

         public ParIterable zip(final Iterable that) {
            return ParIterableLike.zip$(this, (Iterable)that);
         }

         public ParIterable zipWithIndex() {
            return ParIterableLike.zipWithIndex$(this);
         }

         public ParIterable zipAll(final ParIterable that, final Object thisElem, final Object thatElem) {
            return ParIterableLike.zipAll$(this, that, thisElem, thatElem);
         }

         public Object toParCollection(final Function0 cbf) {
            return ParIterableLike.toParCollection$(this, cbf);
         }

         public Object toParMap(final Function0 cbf, final scala..less.colon.less ev) {
            return ParIterableLike.toParMap$(this, cbf, ev);
         }

         public Object toArray(final ClassTag evidence$1) {
            return ParIterableLike.toArray$(this, evidence$1);
         }

         public List toList() {
            return ParIterableLike.toList$(this);
         }

         public IndexedSeq toIndexedSeq() {
            return ParIterableLike.toIndexedSeq$(this);
         }

         /** @deprecated */
         public Stream toStream() {
            return ParIterableLike.toStream$(this);
         }

         public Iterator toIterator() {
            return ParIterableLike.toIterator$(this);
         }

         public Buffer toBuffer() {
            return ParIterableLike.toBuffer$(this);
         }

         /** @deprecated */
         public ParIterable toTraversable() {
            return ParIterableLike.toTraversable$(this);
         }

         public ParIterable toIterable() {
            return ParIterableLike.toIterable$(this);
         }

         public ParSeq toSeq() {
            return ParIterableLike.toSeq$(this);
         }

         public scala.collection.parallel.immutable.ParSet toSet() {
            return ParIterableLike.toSet$(this);
         }

         public scala.collection.parallel.immutable.ParMap toMap(final scala..less.colon.less ev) {
            return ParIterableLike.toMap$(this, ev);
         }

         public Vector toVector() {
            return ParIterableLike.toVector$(this);
         }

         public Object to(final Factory factory) {
            return ParIterableLike.to$(this, factory);
         }

         public int scanBlockSize() {
            return ParIterableLike.scanBlockSize$(this);
         }

         public Object $div$colon(final Object z, final Function2 op) {
            return ParIterableLike.$div$colon$(this, z, op);
         }

         public Object $colon$bslash(final Object z, final Function2 op) {
            return ParIterableLike.$colon$bslash$(this, z, op);
         }

         public String debugInformation() {
            return ParIterableLike.debugInformation$(this);
         }

         public Seq brokenInvariants() {
            return ParIterableLike.brokenInvariants$(this);
         }

         public ArrayBuffer debugBuffer() {
            return ParIterableLike.debugBuffer$(this);
         }

         public void debugclear() {
            ParIterableLike.debugclear$(this);
         }

         public ArrayBuffer debuglog(final String s) {
            return ParIterableLike.debuglog$(this, s);
         }

         public void printDebugBuffer() {
            ParIterableLike.printDebugBuffer$(this);
         }

         public Nothing parCombiner() {
            return CustomParallelizable.parCombiner$(this);
         }

         public Stepper stepper(final StepperShape shape) {
            return IterableOnce.stepper$(this, shape);
         }

         public Combiner newCombiner() {
            return GenericParMapTemplate.newCombiner$(this);
         }

         public Combiner genericMapCombiner() {
            return GenericParMapTemplate.genericMapCombiner$(this);
         }

         public Combiner newBuilder() {
            return GenericParTemplate.newBuilder$(this);
         }

         public Combiner genericBuilder() {
            return GenericParTemplate.genericBuilder$(this);
         }

         public Combiner genericCombiner() {
            return GenericParTemplate.genericCombiner$(this);
         }

         public Tuple2 unzip(final Function1 asPair) {
            return GenericTraversableTemplate.unzip$(this, asPair);
         }

         public Tuple3 unzip3(final Function1 asTriple) {
            return GenericTraversableTemplate.unzip3$(this, asTriple);
         }

         public ParIterable flatten(final Function1 asTraversable) {
            return GenericTraversableTemplate.flatten$(this, asTraversable);
         }

         public ParIterable transpose(final Function1 asTraversable) {
            return GenericTraversableTemplate.transpose$(this, asTraversable);
         }

         public TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport() {
            return this.scala$collection$parallel$ParIterableLike$$_tasksupport;
         }

         public void scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(final TaskSupport x$1) {
            this.scala$collection$parallel$ParIterableLike$$_tasksupport = x$1;
         }

         public ParIterableLike.ScanNode$ ScanNode() {
            if (this.ScanNode$module == null) {
               this.ScanNode$lzycompute$3();
            }

            return this.ScanNode$module;
         }

         public ParIterableLike.ScanLeaf$ ScanLeaf() {
            if (this.ScanLeaf$module == null) {
               this.ScanLeaf$lzycompute$3();
            }

            return this.ScanLeaf$module;
         }

         private ParMap filtered$lzycompute() {
            synchronized(this){}

            try {
               if (!this.bitmap$0) {
                  this.filtered = (ParMap)this.$outer.filter((kv) -> BoxesRunTime.boxToBoolean($anonfun$filtered$1(this, kv)));
                  this.bitmap$0 = true;
               }
            } catch (Throwable var3) {
               throw var3;
            }

            return this.filtered;
         }

         private ParMap filtered() {
            return !this.bitmap$0 ? this.filtered$lzycompute() : this.filtered;
         }

         public void foreach(final Function1 f) {
            this.$outer.foreach((kv) -> BoxesRunTime.unboxToBoolean(this.p$1.apply(kv._1())) ? f.apply(kv) : BoxedUnit.UNIT);
         }

         public IterableSplitter splitter() {
            return this.filtered().splitter();
         }

         public boolean contains(final Object key) {
            return this.$outer.contains(key) && BoxesRunTime.unboxToBoolean(this.p$1.apply(key));
         }

         public Option get(final Object key) {
            return (Option)(!BoxesRunTime.unboxToBoolean(this.p$1.apply(key)) ? scala.None..MODULE$ : this.$outer.get(key));
         }

         public scala.collection.Map seq() {
            return (scala.collection.Map)((MapOps)this.$outer.seq()).view().filterKeys(this.p$1).to(scala.collection.MapFactory..MODULE$.toFactory(scala.collection.Map..MODULE$));
         }

         public int size() {
            return this.filtered().size();
         }

         public int knownSize() {
            return this.filtered().knownSize();
         }

         public ParMap $plus(final Tuple2 kv) {
            return ParMap$.MODULE$.apply(scala.collection.immutable.Nil..MODULE$).$plus$plus(this).$plus(kv);
         }

         public ParMap $minus(final Object key) {
            return ParMap$.MODULE$.apply(scala.collection.immutable.Nil..MODULE$).$plus$plus(this).$minus(key);
         }

         private final void ScanNode$lzycompute$3() {
            synchronized(this){}

            try {
               if (this.ScanNode$module == null) {
                  this.ScanNode$module = new ParIterableLike.ScanNode$();
               }
            } catch (Throwable var3) {
               throw var3;
            }

         }

         private final void ScanLeaf$lzycompute$3() {
            synchronized(this){}

            try {
               if (this.ScanLeaf$module == null) {
                  this.ScanLeaf$module = new ParIterableLike.ScanLeaf$();
               }
            } catch (Throwable var3) {
               throw var3;
            }

         }

         // $FF: synthetic method
         public static final boolean $anonfun$filtered$1(final Object $this, final Tuple2 kv) {
            return BoxesRunTime.unboxToBoolean($this.p$1.apply(kv._1()));
         }

         public {
            if (ParMapLike.this == null) {
               throw null;
            } else {
               this.$outer = ParMapLike.this;
               this.p$1 = p$1;
               GenericTraversableTemplate.$init$(this);
               GenericParTemplate.$init$(this);
               GenericParMapTemplate.$init$(this);
               IterableOnce.$init$(this);
               Parallelizable.$init$(this);
               CustomParallelizable.$init$(this);
               ParIterableLike.$init$(this);
               ParIterable.$init$(this);
               ParMapLike.$init$(this);
               ParMap.$init$(this);
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   default ParMap mapValues(final Function1 f) {
      return new ParMap(f) {
         private transient volatile TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
         private volatile ParIterableLike.ScanNode$ ScanNode$module;
         private volatile ParIterableLike.ScanLeaf$ ScanLeaf$module;
         // $FF: synthetic field
         private final ParMapLike $outer;
         private final Function1 f$4;

         public GenericParMapCompanion mapCompanion() {
            return ParMap.mapCompanion$(this);
         }

         public ParMap empty() {
            return ParMap.empty$(this);
         }

         public String stringPrefix() {
            return ParMap.stringPrefix$(this);
         }

         public boolean canEqual(final Object that) {
            return ParMapLike.super.canEqual(that);
         }

         public boolean equals(final Object that) {
            return ParMapLike.super.equals(that);
         }

         public int hashCode() {
            return ParMapLike.super.hashCode();
         }

         public ParMap updated(final Object key, final Object value) {
            return ParMapLike.super.updated(key, value);
         }

         public Object default(final Object key) {
            return ParMapLike.super.default(key);
         }

         public Object apply(final Object key) {
            return ParMapLike.super.apply(key);
         }

         public Object getOrElse(final Object key, final Function0 default) {
            return ParMapLike.super.getOrElse(key, default);
         }

         public boolean isDefinedAt(final Object key) {
            return ParMapLike.super.isDefinedAt(key);
         }

         public IterableSplitter keysIterator() {
            return ParMapLike.super.keysIterator();
         }

         public IterableSplitter valuesIterator() {
            return ParMapLike.super.valuesIterator();
         }

         public ParSet keySet() {
            return ParMapLike.super.keySet();
         }

         public ParIterable keys() {
            return ParMapLike.super.keys();
         }

         public ParIterable values() {
            return ParMapLike.super.values();
         }

         public ParMap filterKeys(final Function1 p) {
            return ParMapLike.super.filterKeys(p);
         }

         public ParMap mapValues(final Function1 f) {
            return ParMapLike.super.mapValues(f);
         }

         public ParMap map(final Function1 f) {
            return ParMapLike.super.map(f);
         }

         public ParMap collect(final PartialFunction pf) {
            return ParMapLike.super.collect(pf);
         }

         public ParMap flatMap(final Function1 f) {
            return ParMapLike.super.flatMap(f);
         }

         public ParMap concat(final IterableOnce that) {
            return ParMapLike.super.concat(that);
         }

         public final ParMap $plus$plus(final IterableOnce xs) {
            return ParMapLike.super.$plus$plus(xs);
         }

         public GenericParCompanion companion() {
            return ParIterable.companion$(this);
         }

         public void initTaskSupport() {
            ParIterableLike.initTaskSupport$(this);
         }

         public TaskSupport tasksupport() {
            return ParIterableLike.tasksupport$(this);
         }

         public void tasksupport_$eq(final TaskSupport ts) {
            ParIterableLike.tasksupport_$eq$(this, ts);
         }

         public ParIterable repr() {
            return ParIterableLike.repr$(this);
         }

         public final boolean isTraversableAgain() {
            return ParIterableLike.isTraversableAgain$(this);
         }

         public boolean hasDefiniteSize() {
            return ParIterableLike.hasDefiniteSize$(this);
         }

         public boolean isEmpty() {
            return ParIterableLike.isEmpty$(this);
         }

         public boolean nonEmpty() {
            return ParIterableLike.nonEmpty$(this);
         }

         public Object head() {
            return ParIterableLike.head$(this);
         }

         public Option headOption() {
            return ParIterableLike.headOption$(this);
         }

         public ParIterable tail() {
            return ParIterableLike.tail$(this);
         }

         public Object last() {
            return ParIterableLike.last$(this);
         }

         public Option lastOption() {
            return ParIterableLike.lastOption$(this);
         }

         public ParIterable init() {
            return ParIterableLike.init$(this);
         }

         public Splitter iterator() {
            return ParIterableLike.iterator$(this);
         }

         public ParIterable par() {
            return ParIterableLike.par$(this);
         }

         public boolean isStrictSplitterCollection() {
            return ParIterableLike.isStrictSplitterCollection$(this);
         }

         public Combiner reuse(final Option oldc, final Combiner newc) {
            return ParIterableLike.reuse$(this, oldc, newc);
         }

         public ParIterableLike.TaskOps task2ops(final ParIterableLike.StrictSplitterCheckTask tsk) {
            return ParIterableLike.task2ops$(this, tsk);
         }

         public ParIterableLike.NonDivisible wrap(final Function0 body) {
            return ParIterableLike.wrap$(this, body);
         }

         public ParIterableLike.SignallingOps delegatedSignalling2ops(final DelegatedSignalling it) {
            return ParIterableLike.delegatedSignalling2ops$(this, it);
         }

         public ParIterableLike.BuilderOps builder2ops(final Builder cb) {
            return ParIterableLike.builder2ops$(this, cb);
         }

         public ParIterable sequentially(final Function1 b) {
            return ParIterableLike.sequentially$(this, b);
         }

         public String mkString(final String start, final String sep, final String end) {
            return ParIterableLike.mkString$(this, start, sep, end);
         }

         public String mkString(final String sep) {
            return ParIterableLike.mkString$(this, sep);
         }

         public String mkString() {
            return ParIterableLike.mkString$(this);
         }

         public String toString() {
            return ParIterableLike.toString$(this);
         }

         public Object reduce(final Function2 op) {
            return ParIterableLike.reduce$(this, op);
         }

         public Option reduceOption(final Function2 op) {
            return ParIterableLike.reduceOption$(this, op);
         }

         public Object fold(final Object z, final Function2 op) {
            return ParIterableLike.fold$(this, z, op);
         }

         public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
            return ParIterableLike.aggregate$(this, z, seqop, combop);
         }

         public Object foldLeft(final Object z, final Function2 op) {
            return ParIterableLike.foldLeft$(this, z, op);
         }

         public Object foldRight(final Object z, final Function2 op) {
            return ParIterableLike.foldRight$(this, z, op);
         }

         public Object reduceLeft(final Function2 op) {
            return ParIterableLike.reduceLeft$(this, op);
         }

         public Object reduceRight(final Function2 op) {
            return ParIterableLike.reduceRight$(this, op);
         }

         public Option reduceLeftOption(final Function2 op) {
            return ParIterableLike.reduceLeftOption$(this, op);
         }

         public Option reduceRightOption(final Function2 op) {
            return ParIterableLike.reduceRightOption$(this, op);
         }

         public int count(final Function1 p) {
            return ParIterableLike.count$(this, p);
         }

         public Object sum(final Numeric num) {
            return ParIterableLike.sum$(this, num);
         }

         public Object product(final Numeric num) {
            return ParIterableLike.product$(this, num);
         }

         public Object min(final Ordering ord) {
            return ParIterableLike.min$(this, ord);
         }

         public Object max(final Ordering ord) {
            return ParIterableLike.max$(this, ord);
         }

         public Object maxBy(final Function1 f, final Ordering cmp) {
            return ParIterableLike.maxBy$(this, f, cmp);
         }

         public Object minBy(final Function1 f, final Ordering cmp) {
            return ParIterableLike.minBy$(this, f, cmp);
         }

         public ParIterable map(final Function1 f) {
            return ParIterableLike.map$(this, f);
         }

         public ParIterable collect(final PartialFunction pf) {
            return ParIterableLike.collect$(this, pf);
         }

         public ParIterable flatMap(final Function1 f) {
            return ParIterableLike.flatMap$(this, f);
         }

         public boolean forall(final Function1 p) {
            return ParIterableLike.forall$(this, p);
         }

         public boolean exists(final Function1 p) {
            return ParIterableLike.exists$(this, p);
         }

         public Option find(final Function1 p) {
            return ParIterableLike.find$(this, p);
         }

         public CombinerFactory combinerFactory() {
            return ParIterableLike.combinerFactory$(this);
         }

         public CombinerFactory combinerFactory(final Function0 cbf) {
            return ParIterableLike.combinerFactory$(this, cbf);
         }

         public ParIterable withFilter(final Function1 pred) {
            return ParIterableLike.withFilter$(this, pred);
         }

         public ParIterable filter(final Function1 pred) {
            return ParIterableLike.filter$(this, pred);
         }

         public ParIterable filterNot(final Function1 pred) {
            return ParIterableLike.filterNot$(this, pred);
         }

         public ParIterable $plus$plus(final IterableOnce that) {
            return ParIterableLike.$plus$plus$(this, that);
         }

         public Tuple2 partition(final Function1 pred) {
            return ParIterableLike.partition$(this, pred);
         }

         public scala.collection.parallel.immutable.ParMap groupBy(final Function1 f) {
            return ParIterableLike.groupBy$(this, f);
         }

         public ParIterable take(final int n) {
            return ParIterableLike.take$(this, n);
         }

         public ParIterable drop(final int n) {
            return ParIterableLike.drop$(this, n);
         }

         public ParIterable slice(final int unc_from, final int unc_until) {
            return ParIterableLike.slice$(this, unc_from, unc_until);
         }

         public Tuple2 splitAt(final int n) {
            return ParIterableLike.splitAt$(this, n);
         }

         public ParIterable scan(final Object z, final Function2 op) {
            return ParIterableLike.scan$(this, z, op);
         }

         public Iterable scanLeft(final Object z, final Function2 op) {
            return ParIterableLike.scanLeft$(this, z, op);
         }

         public Iterable scanRight(final Object z, final Function2 op) {
            return ParIterableLike.scanRight$(this, z, op);
         }

         public ParIterable takeWhile(final Function1 pred) {
            return ParIterableLike.takeWhile$(this, pred);
         }

         public Tuple2 span(final Function1 pred) {
            return ParIterableLike.span$(this, pred);
         }

         public ParIterable dropWhile(final Function1 pred) {
            return ParIterableLike.dropWhile$(this, pred);
         }

         public void copyToArray(final Object xs) {
            ParIterableLike.copyToArray$(this, xs);
         }

         public void copyToArray(final Object xs, final int start) {
            ParIterableLike.copyToArray$(this, xs, start);
         }

         public void copyToArray(final Object xs, final int start, final int len) {
            ParIterableLike.copyToArray$(this, xs, start, len);
         }

         public boolean sameElements(final IterableOnce that) {
            return ParIterableLike.sameElements$(this, that);
         }

         public ParIterable zip(final ParIterable that) {
            return ParIterableLike.zip$(this, (ParIterable)that);
         }

         public ParIterable zip(final Iterable that) {
            return ParIterableLike.zip$(this, (Iterable)that);
         }

         public ParIterable zipWithIndex() {
            return ParIterableLike.zipWithIndex$(this);
         }

         public ParIterable zipAll(final ParIterable that, final Object thisElem, final Object thatElem) {
            return ParIterableLike.zipAll$(this, that, thisElem, thatElem);
         }

         public Object toParCollection(final Function0 cbf) {
            return ParIterableLike.toParCollection$(this, cbf);
         }

         public Object toParMap(final Function0 cbf, final scala..less.colon.less ev) {
            return ParIterableLike.toParMap$(this, cbf, ev);
         }

         public Object toArray(final ClassTag evidence$1) {
            return ParIterableLike.toArray$(this, evidence$1);
         }

         public List toList() {
            return ParIterableLike.toList$(this);
         }

         public IndexedSeq toIndexedSeq() {
            return ParIterableLike.toIndexedSeq$(this);
         }

         /** @deprecated */
         public Stream toStream() {
            return ParIterableLike.toStream$(this);
         }

         public Iterator toIterator() {
            return ParIterableLike.toIterator$(this);
         }

         public Buffer toBuffer() {
            return ParIterableLike.toBuffer$(this);
         }

         /** @deprecated */
         public ParIterable toTraversable() {
            return ParIterableLike.toTraversable$(this);
         }

         public ParIterable toIterable() {
            return ParIterableLike.toIterable$(this);
         }

         public ParSeq toSeq() {
            return ParIterableLike.toSeq$(this);
         }

         public scala.collection.parallel.immutable.ParSet toSet() {
            return ParIterableLike.toSet$(this);
         }

         public scala.collection.parallel.immutable.ParMap toMap(final scala..less.colon.less ev) {
            return ParIterableLike.toMap$(this, ev);
         }

         public Vector toVector() {
            return ParIterableLike.toVector$(this);
         }

         public Object to(final Factory factory) {
            return ParIterableLike.to$(this, factory);
         }

         public int scanBlockSize() {
            return ParIterableLike.scanBlockSize$(this);
         }

         public Object $div$colon(final Object z, final Function2 op) {
            return ParIterableLike.$div$colon$(this, z, op);
         }

         public Object $colon$bslash(final Object z, final Function2 op) {
            return ParIterableLike.$colon$bslash$(this, z, op);
         }

         public String debugInformation() {
            return ParIterableLike.debugInformation$(this);
         }

         public Seq brokenInvariants() {
            return ParIterableLike.brokenInvariants$(this);
         }

         public ArrayBuffer debugBuffer() {
            return ParIterableLike.debugBuffer$(this);
         }

         public void debugclear() {
            ParIterableLike.debugclear$(this);
         }

         public ArrayBuffer debuglog(final String s) {
            return ParIterableLike.debuglog$(this, s);
         }

         public void printDebugBuffer() {
            ParIterableLike.printDebugBuffer$(this);
         }

         public Nothing parCombiner() {
            return CustomParallelizable.parCombiner$(this);
         }

         public Stepper stepper(final StepperShape shape) {
            return IterableOnce.stepper$(this, shape);
         }

         public Combiner newCombiner() {
            return GenericParMapTemplate.newCombiner$(this);
         }

         public Combiner genericMapCombiner() {
            return GenericParMapTemplate.genericMapCombiner$(this);
         }

         public Combiner newBuilder() {
            return GenericParTemplate.newBuilder$(this);
         }

         public Combiner genericBuilder() {
            return GenericParTemplate.genericBuilder$(this);
         }

         public Combiner genericCombiner() {
            return GenericParTemplate.genericCombiner$(this);
         }

         public Tuple2 unzip(final Function1 asPair) {
            return GenericTraversableTemplate.unzip$(this, asPair);
         }

         public Tuple3 unzip3(final Function1 asTriple) {
            return GenericTraversableTemplate.unzip3$(this, asTriple);
         }

         public ParIterable flatten(final Function1 asTraversable) {
            return GenericTraversableTemplate.flatten$(this, asTraversable);
         }

         public ParIterable transpose(final Function1 asTraversable) {
            return GenericTraversableTemplate.transpose$(this, asTraversable);
         }

         public TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport() {
            return this.scala$collection$parallel$ParIterableLike$$_tasksupport;
         }

         public void scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(final TaskSupport x$1) {
            this.scala$collection$parallel$ParIterableLike$$_tasksupport = x$1;
         }

         public ParIterableLike.ScanNode$ ScanNode() {
            if (this.ScanNode$module == null) {
               this.ScanNode$lzycompute$4();
            }

            return this.ScanNode$module;
         }

         public ParIterableLike.ScanLeaf$ ScanLeaf() {
            if (this.ScanLeaf$module == null) {
               this.ScanLeaf$lzycompute$4();
            }

            return this.ScanLeaf$module;
         }

         public void foreach(final Function1 g) {
            this.$outer.withFilter((check$ifrefutable$3) -> BoxesRunTime.boxToBoolean($anonfun$foreach$6(check$ifrefutable$3))).foreach((x$7) -> {
               if (x$7 != null) {
                  Object k = x$7._1();
                  Object v = x$7._2();
                  return g.apply(new Tuple2(k, this.f$4.apply(v)));
               } else {
                  throw new MatchError(x$7);
               }
            });
         }

         public IterableSplitter splitter() {
            return this.$outer.splitter().map((kv) -> new Tuple2(kv._1(), this.f$4.apply(kv._2())));
         }

         public int size() {
            return this.$outer.size();
         }

         public int knownSize() {
            return this.$outer.knownSize();
         }

         public boolean contains(final Object key) {
            return this.$outer.contains(key);
         }

         public Option get(final Object key) {
            return this.$outer.get(key).map(this.f$4);
         }

         public scala.collection.Map seq() {
            return (scala.collection.Map)((MapOps)this.$outer.seq()).view().mapValues(this.f$4).to(scala.collection.MapFactory..MODULE$.toFactory(scala.collection.Map..MODULE$));
         }

         public ParMap $plus(final Tuple2 kv) {
            return ParMap$.MODULE$.apply(scala.collection.immutable.Nil..MODULE$).$plus$plus(this).$plus(kv);
         }

         public ParMap $minus(final Object key) {
            return ParMap$.MODULE$.apply(scala.collection.immutable.Nil..MODULE$).$plus$plus(this).$minus(key);
         }

         private final void ScanNode$lzycompute$4() {
            synchronized(this){}

            try {
               if (this.ScanNode$module == null) {
                  this.ScanNode$module = new ParIterableLike.ScanNode$();
               }
            } catch (Throwable var3) {
               throw var3;
            }

         }

         private final void ScanLeaf$lzycompute$4() {
            synchronized(this){}

            try {
               if (this.ScanLeaf$module == null) {
                  this.ScanLeaf$module = new ParIterableLike.ScanLeaf$();
               }
            } catch (Throwable var3) {
               throw var3;
            }

         }

         // $FF: synthetic method
         public static final boolean $anonfun$foreach$6(final Tuple2 check$ifrefutable$3) {
            return check$ifrefutable$3 != null;
         }

         public {
            if (ParMapLike.this == null) {
               throw null;
            } else {
               this.$outer = ParMapLike.this;
               this.f$4 = f$4;
               GenericTraversableTemplate.$init$(this);
               GenericParTemplate.$init$(this);
               GenericParMapTemplate.$init$(this);
               IterableOnce.$init$(this);
               Parallelizable.$init$(this);
               CustomParallelizable.$init$(this);
               ParIterableLike.$init$(this);
               ParIterable.$init$(this);
               ParMapLike.$init$(this);
               ParMap.$init$(this);
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   default ParMap map(final Function1 f) {
      return (ParMap)this.tasksupport().executeAndWaitResult(this.task2ops(new ParIterableLike.Map(f, this.combinerFactory(() -> this.mapCompanion().newCombiner()), this.splitter())).mapResult((x$8) -> (ParMap)x$8.resultWithTaskSupport()));
   }

   default ParMap collect(final PartialFunction pf) {
      return (ParMap)this.tasksupport().executeAndWaitResult(this.task2ops(new ParIterableLike.Collect(pf, this.combinerFactory(() -> this.mapCompanion().newCombiner()), this.splitter())).mapResult((x$9) -> (ParMap)x$9.resultWithTaskSupport()));
   }

   default ParMap flatMap(final Function1 f) {
      return (ParMap)this.tasksupport().executeAndWaitResult(this.task2ops(new ParIterableLike.FlatMap(f, this.combinerFactory(() -> this.mapCompanion().newCombiner()), this.splitter())).mapResult((x$10) -> (ParMap)x$10.resultWithTaskSupport()));
   }

   default ParMap concat(final IterableOnce that) {
      if (that instanceof ParIterable) {
         ParIterable var4 = (ParIterable)that;
         CombinerFactory cfactory = this.combinerFactory(() -> this.mapCompanion().newCombiner());
         ParIterableLike.Copy copythis = new ParIterableLike.Copy(cfactory, this.splitter());
         ParIterableLike.NonDivisible copythat = this.wrap(() -> {
            ParIterableLike.Copy othtask = var4.new Copy(cfactory, var4.splitter());
            return (Combiner)this.tasksupport().executeAndWaitResult(othtask);
         });
         ParIterableLike.ResultMapping task = this.task2ops(this.task2ops(copythis).parallel(copythat, (x$11, x$12) -> x$11.combine(x$12))).mapResult((x$13) -> (ParMap)x$13.resultWithTaskSupport());
         return (ParMap)this.tasksupport().executeAndWaitResult(task);
      } else {
         ParIterableLike.Copy copythis = new ParIterableLike.Copy(this.combinerFactory(() -> this.mapCompanion().newCombiner()), this.splitter());
         ParIterableLike.NonDivisible copythat = this.wrap(() -> {
            Combiner cb = this.mapCompanion().newCombiner();
            cb.$plus$plus$eq(that);
            return cb;
         });
         return (ParMap)this.tasksupport().executeAndWaitResult(this.task2ops(this.task2ops(copythis).parallel(copythat, (x$14, x$15) -> x$14.combine(x$15))).mapResult((x$16) -> (ParMap)x$16.resultWithTaskSupport()));
      }
   }

   default ParMap $plus$plus(final IterableOnce xs) {
      return this.concat(xs);
   }

   // $FF: synthetic method
   static boolean $anonfun$equals$1(final ParMap x2$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Object k = x0$1._1();
         Object v = x0$1._2();
         Option var7 = x2$1.get(k);
         if (var7 instanceof Some) {
            Some var8 = (Some)var7;
            Object var9 = var8.value();
            if (BoxesRunTime.equals(v, var9)) {
               return true;
            }
         }

         return false;
      } else {
         throw new MatchError(x0$1);
      }
   }

   static void $init$(final ParMapLike $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class DefaultKeySet implements ParSet {
      private transient volatile TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
      private volatile ParIterableLike.ScanNode$ ScanNode$module;
      private volatile ParIterableLike.ScanLeaf$ ScanLeaf$module;
      // $FF: synthetic field
      public final ParMapLike $outer;

      public ParSet empty() {
         return ParSet.empty$(this);
      }

      public GenericParCompanion companion() {
         return ParSet.companion$(this);
      }

      public String stringPrefix() {
         return ParSet.stringPrefix$(this);
      }

      public final boolean apply(final Object elem) {
         return ParSetLike.apply$(this, elem);
      }

      public ParSet intersect(final ParSet that) {
         return ParSetLike.intersect$(this, (ParSet)that);
      }

      public ParSet intersect(final scala.collection.Set that) {
         return ParSetLike.intersect$(this, (scala.collection.Set)that);
      }

      public ParSet $amp(final ParSet that) {
         return ParSetLike.$amp$(this, (ParSet)that);
      }

      public ParSet $amp(final scala.collection.Set that) {
         return ParSetLike.$amp$(this, (scala.collection.Set)that);
      }

      public ParSet $bar(final ParSet that) {
         return ParSetLike.$bar$(this, (ParSet)that);
      }

      public ParSet $bar(final scala.collection.Set that) {
         return ParSetLike.$bar$(this, (scala.collection.Set)that);
      }

      public ParSet $amp$tilde(final ParSet that) {
         return ParSetLike.$amp$tilde$(this, (ParSet)that);
      }

      public ParSet $amp$tilde(final scala.collection.Set that) {
         return ParSetLike.$amp$tilde$(this, (scala.collection.Set)that);
      }

      public boolean subsetOf(final ParSet that) {
         return ParSetLike.subsetOf$(this, that);
      }

      public boolean equals(final Object that) {
         return ParSetLike.equals$(this, that);
      }

      public int hashCode() {
         return ParSetLike.hashCode$(this);
      }

      public boolean canEqual(final Object other) {
         return ParSetLike.canEqual$(this, other);
      }

      public ParSet union(final scala.collection.Set that) {
         return ParSetLike.union$(this, (scala.collection.Set)that);
      }

      public ParSet union(final ParSet that) {
         return ParSetLike.union$(this, (ParSet)that);
      }

      public ParSet diff(final scala.collection.Set that) {
         return ParSetLike.diff$(this, (scala.collection.Set)that);
      }

      public ParSet diff(final ParSet that) {
         return ParSetLike.diff$(this, (ParSet)that);
      }

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

      public void initTaskSupport() {
         ParIterableLike.initTaskSupport$(this);
      }

      public TaskSupport tasksupport() {
         return ParIterableLike.tasksupport$(this);
      }

      public void tasksupport_$eq(final TaskSupport ts) {
         ParIterableLike.tasksupport_$eq$(this, ts);
      }

      public ParIterable repr() {
         return ParIterableLike.repr$(this);
      }

      public final boolean isTraversableAgain() {
         return ParIterableLike.isTraversableAgain$(this);
      }

      public boolean hasDefiniteSize() {
         return ParIterableLike.hasDefiniteSize$(this);
      }

      public boolean isEmpty() {
         return ParIterableLike.isEmpty$(this);
      }

      public boolean nonEmpty() {
         return ParIterableLike.nonEmpty$(this);
      }

      public Object head() {
         return ParIterableLike.head$(this);
      }

      public Option headOption() {
         return ParIterableLike.headOption$(this);
      }

      public ParIterable tail() {
         return ParIterableLike.tail$(this);
      }

      public Object last() {
         return ParIterableLike.last$(this);
      }

      public Option lastOption() {
         return ParIterableLike.lastOption$(this);
      }

      public ParIterable init() {
         return ParIterableLike.init$(this);
      }

      public Splitter iterator() {
         return ParIterableLike.iterator$(this);
      }

      public ParIterable par() {
         return ParIterableLike.par$(this);
      }

      public boolean isStrictSplitterCollection() {
         return ParIterableLike.isStrictSplitterCollection$(this);
      }

      public Combiner reuse(final Option oldc, final Combiner newc) {
         return ParIterableLike.reuse$(this, oldc, newc);
      }

      public ParIterableLike.TaskOps task2ops(final ParIterableLike.StrictSplitterCheckTask tsk) {
         return ParIterableLike.task2ops$(this, tsk);
      }

      public ParIterableLike.NonDivisible wrap(final Function0 body) {
         return ParIterableLike.wrap$(this, body);
      }

      public ParIterableLike.SignallingOps delegatedSignalling2ops(final DelegatedSignalling it) {
         return ParIterableLike.delegatedSignalling2ops$(this, it);
      }

      public ParIterableLike.BuilderOps builder2ops(final Builder cb) {
         return ParIterableLike.builder2ops$(this, cb);
      }

      public ParIterable sequentially(final Function1 b) {
         return ParIterableLike.sequentially$(this, b);
      }

      public String mkString(final String start, final String sep, final String end) {
         return ParIterableLike.mkString$(this, start, sep, end);
      }

      public String mkString(final String sep) {
         return ParIterableLike.mkString$(this, sep);
      }

      public String mkString() {
         return ParIterableLike.mkString$(this);
      }

      public Object reduce(final Function2 op) {
         return ParIterableLike.reduce$(this, op);
      }

      public Option reduceOption(final Function2 op) {
         return ParIterableLike.reduceOption$(this, op);
      }

      public Object fold(final Object z, final Function2 op) {
         return ParIterableLike.fold$(this, z, op);
      }

      public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
         return ParIterableLike.aggregate$(this, z, seqop, combop);
      }

      public Object foldLeft(final Object z, final Function2 op) {
         return ParIterableLike.foldLeft$(this, z, op);
      }

      public Object foldRight(final Object z, final Function2 op) {
         return ParIterableLike.foldRight$(this, z, op);
      }

      public Object reduceLeft(final Function2 op) {
         return ParIterableLike.reduceLeft$(this, op);
      }

      public Object reduceRight(final Function2 op) {
         return ParIterableLike.reduceRight$(this, op);
      }

      public Option reduceLeftOption(final Function2 op) {
         return ParIterableLike.reduceLeftOption$(this, op);
      }

      public Option reduceRightOption(final Function2 op) {
         return ParIterableLike.reduceRightOption$(this, op);
      }

      public int count(final Function1 p) {
         return ParIterableLike.count$(this, p);
      }

      public Object sum(final Numeric num) {
         return ParIterableLike.sum$(this, num);
      }

      public Object product(final Numeric num) {
         return ParIterableLike.product$(this, num);
      }

      public Object min(final Ordering ord) {
         return ParIterableLike.min$(this, ord);
      }

      public Object max(final Ordering ord) {
         return ParIterableLike.max$(this, ord);
      }

      public Object maxBy(final Function1 f, final Ordering cmp) {
         return ParIterableLike.maxBy$(this, f, cmp);
      }

      public Object minBy(final Function1 f, final Ordering cmp) {
         return ParIterableLike.minBy$(this, f, cmp);
      }

      public ParIterable map(final Function1 f) {
         return ParIterableLike.map$(this, f);
      }

      public ParIterable collect(final PartialFunction pf) {
         return ParIterableLike.collect$(this, pf);
      }

      public ParIterable flatMap(final Function1 f) {
         return ParIterableLike.flatMap$(this, f);
      }

      public boolean forall(final Function1 p) {
         return ParIterableLike.forall$(this, p);
      }

      public boolean exists(final Function1 p) {
         return ParIterableLike.exists$(this, p);
      }

      public Option find(final Function1 p) {
         return ParIterableLike.find$(this, p);
      }

      public CombinerFactory combinerFactory() {
         return ParIterableLike.combinerFactory$(this);
      }

      public CombinerFactory combinerFactory(final Function0 cbf) {
         return ParIterableLike.combinerFactory$(this, cbf);
      }

      public ParIterable withFilter(final Function1 pred) {
         return ParIterableLike.withFilter$(this, pred);
      }

      public ParIterable filter(final Function1 pred) {
         return ParIterableLike.filter$(this, pred);
      }

      public ParIterable filterNot(final Function1 pred) {
         return ParIterableLike.filterNot$(this, pred);
      }

      public ParIterable $plus$plus(final IterableOnce that) {
         return ParIterableLike.$plus$plus$(this, that);
      }

      public Tuple2 partition(final Function1 pred) {
         return ParIterableLike.partition$(this, pred);
      }

      public scala.collection.parallel.immutable.ParMap groupBy(final Function1 f) {
         return ParIterableLike.groupBy$(this, f);
      }

      public ParIterable take(final int n) {
         return ParIterableLike.take$(this, n);
      }

      public ParIterable drop(final int n) {
         return ParIterableLike.drop$(this, n);
      }

      public ParIterable slice(final int unc_from, final int unc_until) {
         return ParIterableLike.slice$(this, unc_from, unc_until);
      }

      public Tuple2 splitAt(final int n) {
         return ParIterableLike.splitAt$(this, n);
      }

      public ParIterable scan(final Object z, final Function2 op) {
         return ParIterableLike.scan$(this, z, op);
      }

      public Iterable scanLeft(final Object z, final Function2 op) {
         return ParIterableLike.scanLeft$(this, z, op);
      }

      public Iterable scanRight(final Object z, final Function2 op) {
         return ParIterableLike.scanRight$(this, z, op);
      }

      public ParIterable takeWhile(final Function1 pred) {
         return ParIterableLike.takeWhile$(this, pred);
      }

      public Tuple2 span(final Function1 pred) {
         return ParIterableLike.span$(this, pred);
      }

      public ParIterable dropWhile(final Function1 pred) {
         return ParIterableLike.dropWhile$(this, pred);
      }

      public void copyToArray(final Object xs) {
         ParIterableLike.copyToArray$(this, xs);
      }

      public void copyToArray(final Object xs, final int start) {
         ParIterableLike.copyToArray$(this, xs, start);
      }

      public void copyToArray(final Object xs, final int start, final int len) {
         ParIterableLike.copyToArray$(this, xs, start, len);
      }

      public boolean sameElements(final IterableOnce that) {
         return ParIterableLike.sameElements$(this, that);
      }

      public ParIterable zip(final ParIterable that) {
         return ParIterableLike.zip$(this, (ParIterable)that);
      }

      public ParIterable zip(final Iterable that) {
         return ParIterableLike.zip$(this, (Iterable)that);
      }

      public ParIterable zipWithIndex() {
         return ParIterableLike.zipWithIndex$(this);
      }

      public ParIterable zipAll(final ParIterable that, final Object thisElem, final Object thatElem) {
         return ParIterableLike.zipAll$(this, that, thisElem, thatElem);
      }

      public Object toParCollection(final Function0 cbf) {
         return ParIterableLike.toParCollection$(this, cbf);
      }

      public Object toParMap(final Function0 cbf, final scala..less.colon.less ev) {
         return ParIterableLike.toParMap$(this, cbf, ev);
      }

      public Object toArray(final ClassTag evidence$1) {
         return ParIterableLike.toArray$(this, evidence$1);
      }

      public List toList() {
         return ParIterableLike.toList$(this);
      }

      public IndexedSeq toIndexedSeq() {
         return ParIterableLike.toIndexedSeq$(this);
      }

      /** @deprecated */
      public Stream toStream() {
         return ParIterableLike.toStream$(this);
      }

      public Iterator toIterator() {
         return ParIterableLike.toIterator$(this);
      }

      public Buffer toBuffer() {
         return ParIterableLike.toBuffer$(this);
      }

      /** @deprecated */
      public ParIterable toTraversable() {
         return ParIterableLike.toTraversable$(this);
      }

      public ParIterable toIterable() {
         return ParIterableLike.toIterable$(this);
      }

      public ParSeq toSeq() {
         return ParIterableLike.toSeq$(this);
      }

      public scala.collection.parallel.immutable.ParSet toSet() {
         return ParIterableLike.toSet$(this);
      }

      public scala.collection.parallel.immutable.ParMap toMap(final scala..less.colon.less ev) {
         return ParIterableLike.toMap$(this, ev);
      }

      public Vector toVector() {
         return ParIterableLike.toVector$(this);
      }

      public Object to(final Factory factory) {
         return ParIterableLike.to$(this, factory);
      }

      public int scanBlockSize() {
         return ParIterableLike.scanBlockSize$(this);
      }

      public Object $div$colon(final Object z, final Function2 op) {
         return ParIterableLike.$div$colon$(this, z, op);
      }

      public Object $colon$bslash(final Object z, final Function2 op) {
         return ParIterableLike.$colon$bslash$(this, z, op);
      }

      public String debugInformation() {
         return ParIterableLike.debugInformation$(this);
      }

      public Seq brokenInvariants() {
         return ParIterableLike.brokenInvariants$(this);
      }

      public ArrayBuffer debugBuffer() {
         return ParIterableLike.debugBuffer$(this);
      }

      public void debugclear() {
         ParIterableLike.debugclear$(this);
      }

      public ArrayBuffer debuglog(final String s) {
         return ParIterableLike.debuglog$(this, s);
      }

      public void printDebugBuffer() {
         ParIterableLike.printDebugBuffer$(this);
      }

      public Nothing parCombiner() {
         return CustomParallelizable.parCombiner$(this);
      }

      public Stepper stepper(final StepperShape shape) {
         return IterableOnce.stepper$(this, shape);
      }

      public Combiner newBuilder() {
         return GenericParTemplate.newBuilder$(this);
      }

      public Combiner newCombiner() {
         return GenericParTemplate.newCombiner$(this);
      }

      public Combiner genericBuilder() {
         return GenericParTemplate.genericBuilder$(this);
      }

      public Combiner genericCombiner() {
         return GenericParTemplate.genericCombiner$(this);
      }

      public Tuple2 unzip(final Function1 asPair) {
         return GenericTraversableTemplate.unzip$(this, asPair);
      }

      public Tuple3 unzip3(final Function1 asTriple) {
         return GenericTraversableTemplate.unzip3$(this, asTriple);
      }

      public ParIterable flatten(final Function1 asTraversable) {
         return GenericTraversableTemplate.flatten$(this, asTraversable);
      }

      public ParIterable transpose(final Function1 asTraversable) {
         return GenericTraversableTemplate.transpose$(this, asTraversable);
      }

      public TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport() {
         return this.scala$collection$parallel$ParIterableLike$$_tasksupport;
      }

      public void scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(final TaskSupport x$1) {
         this.scala$collection$parallel$ParIterableLike$$_tasksupport = x$1;
      }

      public ParIterableLike.ScanNode$ ScanNode() {
         if (this.ScanNode$module == null) {
            this.ScanNode$lzycompute$1();
         }

         return this.ScanNode$module;
      }

      public ParIterableLike.ScanLeaf$ ScanLeaf() {
         if (this.ScanLeaf$module == null) {
            this.ScanLeaf$lzycompute$1();
         }

         return this.ScanLeaf$module;
      }

      public boolean contains(final Object key) {
         return this.scala$collection$parallel$ParMapLike$DefaultKeySet$$$outer().contains(key);
      }

      public IterableSplitter splitter() {
         return this.scala$collection$parallel$ParMapLike$DefaultKeySet$$$outer().scala$collection$parallel$ParMapLike$$keysIterator(this.scala$collection$parallel$ParMapLike$DefaultKeySet$$$outer().splitter());
      }

      public ParSet $plus(final Object elem) {
         return ((ParSetLike)ParSet$.MODULE$.apply(scala.collection.immutable.Nil..MODULE$).$plus$plus(this)).$plus(elem);
      }

      public ParSet $minus(final Object elem) {
         return ((ParSetLike)ParSet$.MODULE$.apply(scala.collection.immutable.Nil..MODULE$).$plus$plus(this)).$minus(elem);
      }

      public int size() {
         return this.scala$collection$parallel$ParMapLike$DefaultKeySet$$$outer().size();
      }

      public int knownSize() {
         return this.scala$collection$parallel$ParMapLike$DefaultKeySet$$$outer().knownSize();
      }

      public void foreach(final Function1 f) {
         this.scala$collection$parallel$ParMapLike$DefaultKeySet$$$outer().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$foreach$1(check$ifrefutable$1))).foreach((x$5) -> {
            if (x$5 != null) {
               Object k = x$5._1();
               return f.apply(k);
            } else {
               throw new MatchError(x$5);
            }
         });
      }

      public scala.collection.Set seq() {
         return ((MapOps)this.scala$collection$parallel$ParMapLike$DefaultKeySet$$$outer().seq()).keySet();
      }

      // $FF: synthetic method
      public ParMapLike scala$collection$parallel$ParMapLike$DefaultKeySet$$$outer() {
         return this.$outer;
      }

      private final void ScanNode$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.ScanNode$module == null) {
               this.ScanNode$module = new ParIterableLike.ScanNode$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      private final void ScanLeaf$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.ScanLeaf$module == null) {
               this.ScanLeaf$module = new ParIterableLike.ScanLeaf$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      // $FF: synthetic method
      public static final boolean $anonfun$foreach$1(final Tuple2 check$ifrefutable$1) {
         return check$ifrefutable$1 != null;
      }

      public DefaultKeySet() {
         if (ParMapLike.this == null) {
            throw null;
         } else {
            this.$outer = ParMapLike.this;
            super();
            GenericTraversableTemplate.$init$(this);
            GenericParTemplate.$init$(this);
            IterableOnce.$init$(this);
            Parallelizable.$init$(this);
            CustomParallelizable.$init$(this);
            ParIterableLike.$init$(this);
            ParIterable.$init$(this);
            Function1.$init$(this);
            ParSetLike.$init$(this);
            ParSet.$init$(this);
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class DefaultValuesIterable implements ParIterable {
      private transient volatile TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
      private volatile ParIterableLike.ScanNode$ ScanNode$module;
      private volatile ParIterableLike.ScanLeaf$ ScanLeaf$module;
      // $FF: synthetic field
      public final ParMapLike $outer;

      public GenericParCompanion companion() {
         return ParIterable.companion$(this);
      }

      public String stringPrefix() {
         return ParIterable.stringPrefix$(this);
      }

      public void initTaskSupport() {
         ParIterableLike.initTaskSupport$(this);
      }

      public TaskSupport tasksupport() {
         return ParIterableLike.tasksupport$(this);
      }

      public void tasksupport_$eq(final TaskSupport ts) {
         ParIterableLike.tasksupport_$eq$(this, ts);
      }

      public ParIterable repr() {
         return ParIterableLike.repr$(this);
      }

      public final boolean isTraversableAgain() {
         return ParIterableLike.isTraversableAgain$(this);
      }

      public boolean hasDefiniteSize() {
         return ParIterableLike.hasDefiniteSize$(this);
      }

      public boolean isEmpty() {
         return ParIterableLike.isEmpty$(this);
      }

      public boolean nonEmpty() {
         return ParIterableLike.nonEmpty$(this);
      }

      public Object head() {
         return ParIterableLike.head$(this);
      }

      public Option headOption() {
         return ParIterableLike.headOption$(this);
      }

      public ParIterable tail() {
         return ParIterableLike.tail$(this);
      }

      public Object last() {
         return ParIterableLike.last$(this);
      }

      public Option lastOption() {
         return ParIterableLike.lastOption$(this);
      }

      public ParIterable init() {
         return ParIterableLike.init$(this);
      }

      public Splitter iterator() {
         return ParIterableLike.iterator$(this);
      }

      public ParIterable par() {
         return ParIterableLike.par$(this);
      }

      public boolean isStrictSplitterCollection() {
         return ParIterableLike.isStrictSplitterCollection$(this);
      }

      public Combiner reuse(final Option oldc, final Combiner newc) {
         return ParIterableLike.reuse$(this, oldc, newc);
      }

      public ParIterableLike.TaskOps task2ops(final ParIterableLike.StrictSplitterCheckTask tsk) {
         return ParIterableLike.task2ops$(this, tsk);
      }

      public ParIterableLike.NonDivisible wrap(final Function0 body) {
         return ParIterableLike.wrap$(this, body);
      }

      public ParIterableLike.SignallingOps delegatedSignalling2ops(final DelegatedSignalling it) {
         return ParIterableLike.delegatedSignalling2ops$(this, it);
      }

      public ParIterableLike.BuilderOps builder2ops(final Builder cb) {
         return ParIterableLike.builder2ops$(this, cb);
      }

      public ParIterable sequentially(final Function1 b) {
         return ParIterableLike.sequentially$(this, b);
      }

      public String mkString(final String start, final String sep, final String end) {
         return ParIterableLike.mkString$(this, start, sep, end);
      }

      public String mkString(final String sep) {
         return ParIterableLike.mkString$(this, sep);
      }

      public String mkString() {
         return ParIterableLike.mkString$(this);
      }

      public String toString() {
         return ParIterableLike.toString$(this);
      }

      public Object reduce(final Function2 op) {
         return ParIterableLike.reduce$(this, op);
      }

      public Option reduceOption(final Function2 op) {
         return ParIterableLike.reduceOption$(this, op);
      }

      public Object fold(final Object z, final Function2 op) {
         return ParIterableLike.fold$(this, z, op);
      }

      public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
         return ParIterableLike.aggregate$(this, z, seqop, combop);
      }

      public Object foldLeft(final Object z, final Function2 op) {
         return ParIterableLike.foldLeft$(this, z, op);
      }

      public Object foldRight(final Object z, final Function2 op) {
         return ParIterableLike.foldRight$(this, z, op);
      }

      public Object reduceLeft(final Function2 op) {
         return ParIterableLike.reduceLeft$(this, op);
      }

      public Object reduceRight(final Function2 op) {
         return ParIterableLike.reduceRight$(this, op);
      }

      public Option reduceLeftOption(final Function2 op) {
         return ParIterableLike.reduceLeftOption$(this, op);
      }

      public Option reduceRightOption(final Function2 op) {
         return ParIterableLike.reduceRightOption$(this, op);
      }

      public int count(final Function1 p) {
         return ParIterableLike.count$(this, p);
      }

      public Object sum(final Numeric num) {
         return ParIterableLike.sum$(this, num);
      }

      public Object product(final Numeric num) {
         return ParIterableLike.product$(this, num);
      }

      public Object min(final Ordering ord) {
         return ParIterableLike.min$(this, ord);
      }

      public Object max(final Ordering ord) {
         return ParIterableLike.max$(this, ord);
      }

      public Object maxBy(final Function1 f, final Ordering cmp) {
         return ParIterableLike.maxBy$(this, f, cmp);
      }

      public Object minBy(final Function1 f, final Ordering cmp) {
         return ParIterableLike.minBy$(this, f, cmp);
      }

      public ParIterable map(final Function1 f) {
         return ParIterableLike.map$(this, f);
      }

      public ParIterable collect(final PartialFunction pf) {
         return ParIterableLike.collect$(this, pf);
      }

      public ParIterable flatMap(final Function1 f) {
         return ParIterableLike.flatMap$(this, f);
      }

      public boolean forall(final Function1 p) {
         return ParIterableLike.forall$(this, p);
      }

      public boolean exists(final Function1 p) {
         return ParIterableLike.exists$(this, p);
      }

      public Option find(final Function1 p) {
         return ParIterableLike.find$(this, p);
      }

      public CombinerFactory combinerFactory() {
         return ParIterableLike.combinerFactory$(this);
      }

      public CombinerFactory combinerFactory(final Function0 cbf) {
         return ParIterableLike.combinerFactory$(this, cbf);
      }

      public ParIterable withFilter(final Function1 pred) {
         return ParIterableLike.withFilter$(this, pred);
      }

      public ParIterable filter(final Function1 pred) {
         return ParIterableLike.filter$(this, pred);
      }

      public ParIterable filterNot(final Function1 pred) {
         return ParIterableLike.filterNot$(this, pred);
      }

      public ParIterable $plus$plus(final IterableOnce that) {
         return ParIterableLike.$plus$plus$(this, that);
      }

      public Tuple2 partition(final Function1 pred) {
         return ParIterableLike.partition$(this, pred);
      }

      public scala.collection.parallel.immutable.ParMap groupBy(final Function1 f) {
         return ParIterableLike.groupBy$(this, f);
      }

      public ParIterable take(final int n) {
         return ParIterableLike.take$(this, n);
      }

      public ParIterable drop(final int n) {
         return ParIterableLike.drop$(this, n);
      }

      public ParIterable slice(final int unc_from, final int unc_until) {
         return ParIterableLike.slice$(this, unc_from, unc_until);
      }

      public Tuple2 splitAt(final int n) {
         return ParIterableLike.splitAt$(this, n);
      }

      public ParIterable scan(final Object z, final Function2 op) {
         return ParIterableLike.scan$(this, z, op);
      }

      public Iterable scanLeft(final Object z, final Function2 op) {
         return ParIterableLike.scanLeft$(this, z, op);
      }

      public Iterable scanRight(final Object z, final Function2 op) {
         return ParIterableLike.scanRight$(this, z, op);
      }

      public ParIterable takeWhile(final Function1 pred) {
         return ParIterableLike.takeWhile$(this, pred);
      }

      public Tuple2 span(final Function1 pred) {
         return ParIterableLike.span$(this, pred);
      }

      public ParIterable dropWhile(final Function1 pred) {
         return ParIterableLike.dropWhile$(this, pred);
      }

      public void copyToArray(final Object xs) {
         ParIterableLike.copyToArray$(this, xs);
      }

      public void copyToArray(final Object xs, final int start) {
         ParIterableLike.copyToArray$(this, xs, start);
      }

      public void copyToArray(final Object xs, final int start, final int len) {
         ParIterableLike.copyToArray$(this, xs, start, len);
      }

      public boolean sameElements(final IterableOnce that) {
         return ParIterableLike.sameElements$(this, that);
      }

      public ParIterable zip(final ParIterable that) {
         return ParIterableLike.zip$(this, (ParIterable)that);
      }

      public ParIterable zip(final Iterable that) {
         return ParIterableLike.zip$(this, (Iterable)that);
      }

      public ParIterable zipWithIndex() {
         return ParIterableLike.zipWithIndex$(this);
      }

      public ParIterable zipAll(final ParIterable that, final Object thisElem, final Object thatElem) {
         return ParIterableLike.zipAll$(this, that, thisElem, thatElem);
      }

      public Object toParCollection(final Function0 cbf) {
         return ParIterableLike.toParCollection$(this, cbf);
      }

      public Object toParMap(final Function0 cbf, final scala..less.colon.less ev) {
         return ParIterableLike.toParMap$(this, cbf, ev);
      }

      public Object toArray(final ClassTag evidence$1) {
         return ParIterableLike.toArray$(this, evidence$1);
      }

      public List toList() {
         return ParIterableLike.toList$(this);
      }

      public IndexedSeq toIndexedSeq() {
         return ParIterableLike.toIndexedSeq$(this);
      }

      /** @deprecated */
      public Stream toStream() {
         return ParIterableLike.toStream$(this);
      }

      public Iterator toIterator() {
         return ParIterableLike.toIterator$(this);
      }

      public Buffer toBuffer() {
         return ParIterableLike.toBuffer$(this);
      }

      /** @deprecated */
      public ParIterable toTraversable() {
         return ParIterableLike.toTraversable$(this);
      }

      public ParIterable toIterable() {
         return ParIterableLike.toIterable$(this);
      }

      public ParSeq toSeq() {
         return ParIterableLike.toSeq$(this);
      }

      public scala.collection.parallel.immutable.ParSet toSet() {
         return ParIterableLike.toSet$(this);
      }

      public scala.collection.parallel.immutable.ParMap toMap(final scala..less.colon.less ev) {
         return ParIterableLike.toMap$(this, ev);
      }

      public Vector toVector() {
         return ParIterableLike.toVector$(this);
      }

      public Object to(final Factory factory) {
         return ParIterableLike.to$(this, factory);
      }

      public int scanBlockSize() {
         return ParIterableLike.scanBlockSize$(this);
      }

      public Object $div$colon(final Object z, final Function2 op) {
         return ParIterableLike.$div$colon$(this, z, op);
      }

      public Object $colon$bslash(final Object z, final Function2 op) {
         return ParIterableLike.$colon$bslash$(this, z, op);
      }

      public String debugInformation() {
         return ParIterableLike.debugInformation$(this);
      }

      public Seq brokenInvariants() {
         return ParIterableLike.brokenInvariants$(this);
      }

      public ArrayBuffer debugBuffer() {
         return ParIterableLike.debugBuffer$(this);
      }

      public void debugclear() {
         ParIterableLike.debugclear$(this);
      }

      public ArrayBuffer debuglog(final String s) {
         return ParIterableLike.debuglog$(this, s);
      }

      public void printDebugBuffer() {
         ParIterableLike.printDebugBuffer$(this);
      }

      public Nothing parCombiner() {
         return CustomParallelizable.parCombiner$(this);
      }

      public Stepper stepper(final StepperShape shape) {
         return IterableOnce.stepper$(this, shape);
      }

      public Combiner newBuilder() {
         return GenericParTemplate.newBuilder$(this);
      }

      public Combiner newCombiner() {
         return GenericParTemplate.newCombiner$(this);
      }

      public Combiner genericBuilder() {
         return GenericParTemplate.genericBuilder$(this);
      }

      public Combiner genericCombiner() {
         return GenericParTemplate.genericCombiner$(this);
      }

      public Tuple2 unzip(final Function1 asPair) {
         return GenericTraversableTemplate.unzip$(this, asPair);
      }

      public Tuple3 unzip3(final Function1 asTriple) {
         return GenericTraversableTemplate.unzip3$(this, asTriple);
      }

      public ParIterable flatten(final Function1 asTraversable) {
         return GenericTraversableTemplate.flatten$(this, asTraversable);
      }

      public ParIterable transpose(final Function1 asTraversable) {
         return GenericTraversableTemplate.transpose$(this, asTraversable);
      }

      public TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport() {
         return this.scala$collection$parallel$ParIterableLike$$_tasksupport;
      }

      public void scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(final TaskSupport x$1) {
         this.scala$collection$parallel$ParIterableLike$$_tasksupport = x$1;
      }

      public ParIterableLike.ScanNode$ ScanNode() {
         if (this.ScanNode$module == null) {
            this.ScanNode$lzycompute$2();
         }

         return this.ScanNode$module;
      }

      public ParIterableLike.ScanLeaf$ ScanLeaf() {
         if (this.ScanLeaf$module == null) {
            this.ScanLeaf$lzycompute$2();
         }

         return this.ScanLeaf$module;
      }

      public IterableSplitter splitter() {
         return this.scala$collection$parallel$ParMapLike$DefaultValuesIterable$$$outer().scala$collection$parallel$ParMapLike$$valuesIterator(this.scala$collection$parallel$ParMapLike$DefaultValuesIterable$$$outer().splitter());
      }

      public int size() {
         return this.scala$collection$parallel$ParMapLike$DefaultValuesIterable$$$outer().size();
      }

      public int knownSize() {
         return this.scala$collection$parallel$ParMapLike$DefaultValuesIterable$$$outer().knownSize();
      }

      public void foreach(final Function1 f) {
         this.scala$collection$parallel$ParMapLike$DefaultValuesIterable$$$outer().withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$foreach$3(check$ifrefutable$2))).foreach((x$6) -> {
            if (x$6 != null) {
               Object v = x$6._2();
               return f.apply(v);
            } else {
               throw new MatchError(x$6);
            }
         });
      }

      public Iterable seq() {
         return ((MapOps)this.scala$collection$parallel$ParMapLike$DefaultValuesIterable$$$outer().seq()).values();
      }

      // $FF: synthetic method
      public ParMapLike scala$collection$parallel$ParMapLike$DefaultValuesIterable$$$outer() {
         return this.$outer;
      }

      private final void ScanNode$lzycompute$2() {
         synchronized(this){}

         try {
            if (this.ScanNode$module == null) {
               this.ScanNode$module = new ParIterableLike.ScanNode$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      private final void ScanLeaf$lzycompute$2() {
         synchronized(this){}

         try {
            if (this.ScanLeaf$module == null) {
               this.ScanLeaf$module = new ParIterableLike.ScanLeaf$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      // $FF: synthetic method
      public static final boolean $anonfun$foreach$3(final Tuple2 check$ifrefutable$2) {
         return check$ifrefutable$2 != null;
      }

      public DefaultValuesIterable() {
         if (ParMapLike.this == null) {
            throw null;
         } else {
            this.$outer = ParMapLike.this;
            super();
            GenericTraversableTemplate.$init$(this);
            GenericParTemplate.$init$(this);
            IterableOnce.$init$(this);
            Parallelizable.$init$(this);
            CustomParallelizable.$init$(this);
            ParIterableLike.$init$(this);
            ParIterable.$init$(this);
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
