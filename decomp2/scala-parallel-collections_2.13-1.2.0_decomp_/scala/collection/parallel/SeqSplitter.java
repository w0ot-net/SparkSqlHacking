package scala.collection.parallel;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.BufferedIterator;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.generic.DelegatedSignalling;
import scala.collection.generic.Signalling;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.collection.parallel.mutable.ParArray;
import scala.collection.parallel.mutable.ParArray$;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\t%ga\u0002 @!\u0003\r\tA\u0012\u0005\u0006C\u0002!\tA\u0019\u0005\u0006M\u00021\ta\u001a\u0005\u0006S\u00021\tA\u001b\u0005\u0006e\u00021\ta\u001d\u0005\u0006y\u0002!\tE\u001b\u0005\u0006{\u0002!\tA \u0005\b\u0003\u0003\u0001a\u0011AA\u0002\r\u0019\t)\u0001\u0001\u0001\u0002\b!I\u0011\u0011\u0003\u0005\u0003\u0002\u0003\u0006I!\u001f\u0005\b\u0003'AA\u0011AA\u000b\u0011\u00151\u0007\u0002\"\u0011h\u0011\u0015I\u0007\u0002\"\u0011k\u0011\u0019\u0011\b\u0002\"\u0001\u0002\u001c!A\u0011q\u0004\u0001\u0005B\u0005\u000b\t\u0003C\u0004\u0002(\u0001!\t%!\u000b\t\u000f\u0005=\u0002\u0001\"\u0011\u00022\u00191\u00111\b\u0001\u0001\u0003{A!\"a\u0014\u0012\u0005\u0003\u0005\u000b\u0011BA)\u0011\u001d\t\u0019\"\u0005C\u0001\u0003/BaAZ\t\u0005B\u0005u\u0003BB5\u0012\t\u0003\ny\u0006\u0003\u0004s#\u0011\u0005\u00111\r\u0005\b\u0003O\u0002A\u0011IA5\r\u0019\t9\b\u0001\u0001\u0002z!a\u0011Q\u0013\r\u0003\u0002\u0003\u0006I!a#\u0002\u0018\"9\u00111\u0003\r\u0005\u0002\u0005m\u0005B\u00024\u0019\t\u0003\n\t\u000b\u0003\u0004j1\u0011\u0005\u00131\u0015\u0005\u0007eb!\t!a*\t\u000f\u0005-\u0006\u0001\"\u0001\u0002.\u001a1\u0011\u0011\u0019\u0001\u0001\u0003\u0007DA\"!7 \u0005\u0003\u0005\u000b\u0011BAn\u0003;Dq!a\u0005 \t\u0003\ty\u000e\u0003\u0004g?\u0011\u0005\u0013Q\u001d\u0005\u0007S~!\t%a:\t\rI|B\u0011AAv\u0011\u001d\tY\u0010\u0001C!\u0003{4aA!\u0004\u0001\u0001\t=\u0001\u0002DAmM\t\u0005\t\u0015!\u0003\u0003&\t\u001d\u0002\u0002\u0004B\u0015M\t\u0005\t\u0015!\u0003\u0003\u001a\t-\u0002\u0002\u0004B\u0018M\t\u0005\t\u0015!\u0003\u0003\u001e\tE\u0002bBA\nM\u0011\u0005!Q\u0007\u0005\u0007M\u001a\"\tEa\u0010\t\u000f\t\u0005c\u0005\"\u0003\u0003D!1\u0011N\nC!\u0005\u0013BaA\u001d\u0014\u0005\u0002\t5\u0003b\u0002B)\u0001\u0011\u0005#1\u000b\u0005\u0007\u0005k\u0002A\u0011A4\u0007\r\t]\u0004\u0001\u0001B=\u0011%\u0011\u0019)\rB\u0001B\u0003%\u0011\u0010\u0003\u0006\u0003\u0006F\u0012\t\u0011)A\u0005\u0005{B\u0011Ba\"2\u0005\u0003\u0005\u000b\u0011B=\t\u000f\u0005M\u0011\u0007\"\u0001\u0003\n\"A!1S\u0019!\u0002\u0013\u0011)\nC\u0004\u0003\u001aF\"\tAa'\t\u000f\t\r\u0016\u0007\"\u0001\u0003&\"9\u0011\u0011A\u0019\u0005\u0002\u0005\r\u0001B\u000242\t\u0003\u00119\u000b\u0003\u0004jc\u0011\u0005!1\u0016\u0005\u0007eF\"\tAa,\t\u000f\tM\u0006\u0001\"\u0001\u00036\nY1+Z9Ta2LG\u000f^3s\u0015\t\u0001\u0015)\u0001\u0005qCJ\fG\u000e\\3m\u0015\t\u00115)\u0001\u0006d_2dWm\u0019;j_:T\u0011\u0001R\u0001\u0006g\u000e\fG.Y\u0002\u0001+\t9%kE\u0003\u0001\u00112[f\f\u0005\u0002J\u00156\t1)\u0003\u0002L\u0007\n1\u0011I\\=SK\u001a\u00042!\u0014(Q\u001b\u0005y\u0014BA(@\u0005AIE/\u001a:bE2,7\u000b\u001d7jiR,'\u000f\u0005\u0002R%2\u0001AAB*\u0001\t\u000b\u0007AKA\u0001U#\t)\u0006\f\u0005\u0002J-&\u0011qk\u0011\u0002\b\u001d>$\b.\u001b8h!\tI\u0015,\u0003\u0002[\u0007\n\u0019\u0011I\\=\u0011\u00075c\u0006+\u0003\u0002^\u007f\t!\u0012)^4nK:$X\rZ*fc&#XM]1u_J\u00042!T0Q\u0013\t\u0001wHA\bQe\u0016\u001c\u0017n]3Ta2LG\u000f^3s\u0003\u0019!\u0013N\\5uIQ\t1\r\u0005\u0002JI&\u0011Qm\u0011\u0002\u0005+:LG/A\u0002ekB,\u0012\u0001\u001b\t\u0004\u001b\u0002\u0001\u0016!B:qY&$X#A6\u0011\u00071|\u0007N\u0004\u0002J[&\u0011anQ\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0001\u0018OA\u0002TKFT!A\\\"\u0002\rA\u001c\b\u000f\\5u)\tYG\u000fC\u0003v\t\u0001\u0007a/A\u0003tSj,7\u000fE\u0002JofL!\u0001_\"\u0003\u0015q\u0012X\r]3bi\u0016$g\b\u0005\u0002Ju&\u00111p\u0011\u0002\u0004\u0013:$\u0018aE:qY&$x+\u001b;i'&<g.\u00197mS:<\u0017\u0001\u00069ta2LGoV5uQNKwM\\1mY&tw\r\u0006\u0002l\u007f\")QO\u0002a\u0001m\u0006I!/Z7bS:LgnZ\u000b\u0002s\n!\"+Z7bS:\u001c\u0018\n^3sCR|'\u000fV1lK:\u001cB\u0001CA\u0005QB!\u00111BA\u0007\u001b\u0005\u0001\u0011bAA\b\u001d\n)A+Y6f]\u0006\u0011Ao[\u0001\u0007y%t\u0017\u000e\u001e \u0015\t\u0005]\u0011\u0011\u0004\t\u0004\u0003\u0017A\u0001BBA\t\u0015\u0001\u0007\u0011\u0010F\u0002l\u0003;AQ!^\u0007A\u0002Y\f\u0001B\\3x)\u0006\\WM\u001c\u000b\u0005\u0003/\t\u0019\u0003\u0003\u0004\u0002&9\u0001\r!_\u0001\u0006k:$\u0018\u000e\\\u0001\u0005i\u0006\\W\rF\u0002i\u0003WAa!!\f\u0010\u0001\u0004I\u0018!\u00018\u0002\u000bMd\u0017nY3\u0015\u000b!\f\u0019$a\u000e\t\r\u0005U\u0002\u00031\u0001z\u0003\u00151'o\\72\u0011\u0019\tI\u0004\u0005a\u0001s\u00061QO\u001c;jYF\u0012QCU3nC&t7/\u0013;fe\u0006$xN]'baB,G-\u0006\u0003\u0002@\u0005%3#B\t\u0002B\u00055\u0003CBA\u0006\u0003\u0007\n9%C\u0002\u0002F9\u0013a!T1qa\u0016$\u0007cA)\u0002J\u00111\u00111J\tC\u0002Q\u0013\u0011a\u0015\t\u0005\u001b\u0002\t9%A\u0001g!\u0019I\u00151\u000b)\u0002H%\u0019\u0011QK\"\u0003\u0013\u0019+hn\u0019;j_:\fD\u0003BA-\u00037\u0002R!a\u0003\u0012\u0003\u000fBq!a\u0014\u0014\u0001\u0004\t\t&\u0006\u0002\u0002NU\u0011\u0011\u0011\r\t\u0005Y>\fi\u0005\u0006\u0003\u0002b\u0005\u0015\u0004\"B;\u0017\u0001\u00041\u0018aA7baV!\u00111NA9)\u0011\ti'a\u001d\u0011\t5\u0003\u0011q\u000e\t\u0004#\u0006EDABA&/\t\u0007A\u000bC\u0004\u0002P]\u0001\r!!\u001e\u0011\r%\u000b\u0019\u0006UA8\u0005]\u0011V-\\1j]NLE/\u001a:bi>\u0014\u0018\t\u001d9f]\u0012,G-\u0006\u0004\u0002|\u0005\u0015\u0015QR\n\u00061\u0005u\u00141\u0013\t\t\u0003\u0017\ty(a!\u0002\f&\u0019\u0011\u0011\u0011(\u0003\u0011\u0005\u0003\b/\u001a8eK\u0012\u00042!UAC\t\u001d\t9\t\u0007b\u0001\u0003\u0013\u0013\u0011!V\t\u0003!b\u00032!UAG\t\u001d\ty\t\u0007b\u0001\u0003#\u0013!\u0001U%\u0012\u0007U\u000b\u0019\n\u0005\u0003N\u0001\u0005\r\u0015AA5u\u0013\u0011\tI*a \u0002\tQD\u0017\r\u001e\u000b\u0005\u0003;\u000by\nE\u0004\u0002\fa\t\u0019)a#\t\u000f\u0005U%\u00041\u0001\u0002\fV\u0011\u00111S\u000b\u0003\u0003K\u0003B\u0001\\8\u0002\u0014R!\u0011QUAU\u0011\u0015)X\u00041\u0001w\u00031\t\u0007\u000f]3oIB\u000b'oU3r+\u0019\ty+!.\u0002:R!\u0011\u0011WA`!\u001d\tY\u0001GAZ\u0003o\u00032!UA[\t\u001d\t9I\bb\u0001\u0003\u0013\u00032!UA]\t\u001d\tyI\bb\u0001\u0003w\u000b2!VA_!\u0011i\u0005!a-\t\u000f\u0005ee\u00041\u0001\u00028\n)\"+Z7bS:\u001c\u0018\n^3sCR|'OW5qa\u0016$W\u0003BAc\u0003\u001f\u001cRaHAd\u0003#\u0004b!a\u0003\u0002J\u00065\u0017bAAf\u001d\n1!,\u001b9qK\u0012\u00042!UAh\t\u0019\tYe\bb\u0001)B!Q\nAAj!\u0019I\u0015Q\u001b)\u0002N&\u0019\u0011q[\"\u0003\rQ+\b\u000f\\33\u0003\t!\u0018\u000e\u0005\u0003N\u0001\u00055\u0017\u0002BAM\u0003\u0013$B!!9\u0002dB)\u00111B\u0010\u0002N\"9\u0011\u0011\\\u0011A\u0002\u0005mWCAAi+\t\tI\u000f\u0005\u0003m_\u0006EG\u0003BAw\u0003o\u0004b!a<\u0002v\u0006EWBAAy\u0015\r\t\u00190Q\u0001\nS6lW\u000f^1cY\u0016L1\u0001]Ay\u0011\u0019\tI\u0010\na\u0001m\u0006\u00191O_:\u0002\u0013iL\u0007\u000fU1s'\u0016\fX\u0003BA\u0000\u0005\u000f!BA!\u0001\u0003\nA!Q\n\u0001B\u0002!\u0019I\u0015Q\u001b)\u0003\u0006A\u0019\u0011Ka\u0002\u0005\r\u0005-SE1\u0001U\u0011\u001d\tI*\na\u0001\u0005\u0017\u0001B!\u0014\u0001\u0003\u0006\tA\"+Z7bS:\u001c\u0018\n^3sCR|'OW5qa\u0016$\u0017\t\u001c7\u0016\r\tE!1\u0004B\u0010'\u00151#1\u0003B\u0011!!\tYA!\u0006\u0003\u001a\tu\u0011b\u0001B\f\u001d\nI!,\u001b9qK\u0012\fE\u000e\u001c\t\u0004#\nmAaBADM\t\u0007\u0011\u0011\u0012\t\u0004#\n}AABA&M\t\u0007A\u000b\u0005\u0003N\u0001\t\r\u0002cB%\u0002V\ne!Q\u0004\t\u0005\u001b\u0002\u0011i\"\u0003\u0003\u0002\u001a\nU\u0011!\u0002;iSN,\u0017\u0002\u0002B\u0017\u0005+\t\u0001\u0002\u001e5jg\u0016dW-\\\u0001\u0006i\"\fG/Z\u0005\u0005\u0005g\u0011)\"\u0001\u0005uQ\u0006$X\r\\3n)!\u00119D!\u000f\u0003<\tu\u0002cBA\u0006M\te!Q\u0004\u0005\b\u00033T\u0003\u0019\u0001B\u0013\u0011\u001d\u0011IC\u000ba\u0001\u00053AqAa\f+\u0001\u0004\u0011i\"\u0006\u0002\u0003\"\u00059\u0001/\u0019;dQ\u0016lWC\u0001B#!\u001dI\u0015Q\u001bB$\u0005K\u0001B!\u0014\u0001\u0003\u001aU\u0011!1\n\t\u0005Y>\u0014\t\u0003\u0006\u0003\u0003L\t=\u0003\"B;/\u0001\u00041\u0018\u0001\u0004>ja\u0006cG\u000eU1s'\u0016\fX\u0003\u0003B+\u0005O\u0012YFa\u0018\u0015\u0011\t]#\u0011\u000eB7\u0005c\u0002r!a\u0003'\u00053\u0012i\u0006E\u0002R\u00057\"q!a\"0\u0005\u0004\tI\tE\u0002R\u0005?\"qA!\u00190\u0005\u0004\u0011\u0019GA\u0001S#\r\u0011)\u0007\u0017\t\u0004#\n\u001dDABA&_\t\u0007A\u000bC\u0004\u0002\u001a>\u0002\rAa\u001b\u0011\t5\u0003!Q\r\u0005\b\u0005_z\u0003\u0019\u0001B-\u0003!!\b.[:FY\u0016l\u0007b\u0002B:_\u0001\u0007!QL\u0001\ti\"\fG/\u00127f[\u00069!/\u001a<feN,'a\u0002)bi\u000eDW\rZ\u000b\u0005\u0005w\u0012\ti\u0005\u00032\u0011\nu\u0004\u0003B'\u0001\u0005\u007f\u00022!\u0015BA\t\u001d\t9)\rb\u0001\u0003\u0013\u000bAA\u001a:p[\u0006)\u0001/\u0019;dQ\u0006A!/\u001a9mC\u000e,G\r\u0006\u0005\u0003\f\n5%q\u0012BI!\u0015\tY!\rB@\u0011\u0019\u0011\u0019)\u000ea\u0001s\"9!QQ\u001bA\u0002\tu\u0004B\u0002BDk\u0001\u0007\u00110\u0001\u0003ue&|\u0007C\u0002BL1\t}\u0004\u000e\u0005\u0004i1\t}$QP\u0001\bQ\u0006\u001ch*\u001a=u+\t\u0011i\nE\u0002J\u0005?K1A!)D\u0005\u001d\u0011un\u001c7fC:\fAA\\3yiR\u0011!qP\u000b\u0003\u0005S\u0003B\u0001[\u0019\u0003\u0000U\u0011!Q\u0016\t\u0005Y>\u0014i\b\u0006\u0003\u0003.\nE\u0006\"B;=\u0001\u00041\u0018a\u00039bi\u000eD\u0007+\u0019:TKF,BAa.\u0003>RA!\u0011\u0018B`\u0005\u0003\u00149\rE\u0003\u0002\fE\u0012Y\fE\u0002R\u0005{#q!a\">\u0005\u0004\tI\t\u0003\u0004\u0003\u0004v\u0002\r!\u001f\u0005\b\u0005\u0007l\u0004\u0019\u0001Bc\u0003)\u0001\u0018\r^2i\u000b2,Wn\u001d\t\u0005\u001b\u0002\u0011Y\f\u0003\u0004\u0003\bv\u0002\r!\u001f"
)
public interface SeqSplitter extends IterableSplitter, AugmentedSeqIterator, PreciseSplitter {
   SeqSplitter dup();

   Seq split();

   Seq psplit(final Seq sizes);

   default Seq splitWithSignalling() {
      Seq pits = this.split();
      pits.foreach((x$9) -> {
         $anonfun$splitWithSignalling$2(this, x$9);
         return BoxedUnit.UNIT;
      });
      return pits;
   }

   default Seq psplitWithSignalling(final Seq sizes) {
      Seq pits = this.psplit(sizes);
      pits.foreach((x$10) -> {
         $anonfun$psplitWithSignalling$1(this, x$10);
         return BoxedUnit.UNIT;
      });
      return pits;
   }

   int remaining();

   default RemainsIteratorTaken newTaken(final int until) {
      return new RemainsIteratorTaken(until);
   }

   default SeqSplitter take(final int n) {
      return this.newTaken(n);
   }

   default SeqSplitter slice(final int from1, final int until1) {
      return (SeqSplitter)this.newSliceInternal(this.newTaken(until1), from1);
   }

   default SeqSplitter map(final Function1 f) {
      return new RemainsIteratorMapped(f);
   }

   default RemainsIteratorAppended appendParSeq(final SeqSplitter that) {
      return new RemainsIteratorAppended(that);
   }

   default SeqSplitter zipParSeq(final SeqSplitter that) {
      return new RemainsIteratorZipped(that);
   }

   default RemainsIteratorZippedAll zipAllParSeq(final SeqSplitter that, final Object thisElem, final Object thatElem) {
      return new RemainsIteratorZippedAll(that, thisElem, thatElem);
   }

   default SeqSplitter reverse() {
      ParArray pa = (ParArray)ParArray$.MODULE$.fromIterables(.MODULE$.genericWrapArray(new IterableOnce[]{this})).reverse();
      return new ParArray.ParArrayIterator(pa) {
         // $FF: synthetic field
         private final SeqSplitter $outer;

         public SeqSplitter reverse() {
            return this.$outer;
         }

         public {
            if (SeqSplitter.this == null) {
               throw null;
            } else {
               this.$outer = SeqSplitter.this;
            }
         }
      };
   }

   default Patched patchParSeq(final int from, final SeqSplitter patchElems, final int replaced) {
      return new Patched(from, patchElems, replaced);
   }

   // $FF: synthetic method
   static void $anonfun$splitWithSignalling$2(final SeqSplitter $this, final SeqSplitter x$9) {
      x$9.signalDelegate_$eq($this.signalDelegate());
   }

   // $FF: synthetic method
   static void $anonfun$psplitWithSignalling$1(final SeqSplitter $this, final SeqSplitter x$10) {
      x$10.signalDelegate_$eq($this.signalDelegate());
   }

   static void $init$(final SeqSplitter $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class RemainsIteratorTaken extends IterableSplitter.Taken implements SeqSplitter {
      public Seq splitWithSignalling() {
         return SeqSplitter.super.splitWithSignalling();
      }

      public Seq psplitWithSignalling(final Seq sizes) {
         return SeqSplitter.super.psplitWithSignalling(sizes);
      }

      public RemainsIteratorTaken newTaken(final int until) {
         return SeqSplitter.super.newTaken(until);
      }

      public SeqSplitter take(final int n) {
         return SeqSplitter.super.take(n);
      }

      public SeqSplitter slice(final int from1, final int until1) {
         return SeqSplitter.super.slice(from1, until1);
      }

      public SeqSplitter map(final Function1 f) {
         return SeqSplitter.super.map(f);
      }

      public RemainsIteratorAppended appendParSeq(final SeqSplitter that) {
         return SeqSplitter.super.appendParSeq(that);
      }

      public SeqSplitter zipParSeq(final SeqSplitter that) {
         return SeqSplitter.super.zipParSeq(that);
      }

      public RemainsIteratorZippedAll zipAllParSeq(final SeqSplitter that, final Object thisElem, final Object thatElem) {
         return SeqSplitter.super.zipAllParSeq(that, thisElem, thatElem);
      }

      public SeqSplitter reverse() {
         return SeqSplitter.super.reverse();
      }

      public Patched patchParSeq(final int from, final SeqSplitter patchElems, final int replaced) {
         return SeqSplitter.super.patchParSeq(from, patchElems, replaced);
      }

      public int prefixLength(final Function1 pred) {
         return AugmentedSeqIterator.prefixLength$(this, pred);
      }

      public int indexWhere(final Function1 pred) {
         return AugmentedSeqIterator.indexWhere$(this, pred);
      }

      public int lastIndexWhere(final Function1 pred) {
         return AugmentedSeqIterator.lastIndexWhere$(this, pred);
      }

      public boolean corresponds(final Function2 corr, final Iterator that) {
         return AugmentedSeqIterator.corresponds$(this, corr, that);
      }

      public Combiner reverse2combiner(final Combiner cb) {
         return AugmentedSeqIterator.reverse2combiner$(this, cb);
      }

      public Combiner reverseMap2combiner(final Function1 f, final Combiner cb) {
         return AugmentedSeqIterator.reverseMap2combiner$(this, f, cb);
      }

      public Combiner updated2combiner(final int index, final Object elem, final Combiner cb) {
         return AugmentedSeqIterator.updated2combiner$(this, index, elem, cb);
      }

      public SeqSplitter dup() {
         return (SeqSplitter)super.dup();
      }

      public Seq split() {
         return super.split();
      }

      public Seq psplit(final Seq sizes) {
         return this.takeSeq(this.scala$collection$parallel$SeqSplitter$RemainsIteratorTaken$$$outer().psplit(sizes), (p, n) -> $anonfun$psplit$1(p, BoxesRunTime.unboxToInt(n)));
      }

      // $FF: synthetic method
      public SeqSplitter scala$collection$parallel$SeqSplitter$RemainsIteratorTaken$$$outer() {
         return (SeqSplitter)this.$outer;
      }

      // $FF: synthetic method
      public static final SeqSplitter $anonfun$psplit$1(final SeqSplitter p, final int n) {
         return p.take(n);
      }

      public RemainsIteratorTaken(final int tk) {
         super(tk);
         AugmentedSeqIterator.$init$(this);
         SeqSplitter.$init$(this);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class RemainsIteratorMapped extends IterableSplitter.Mapped implements SeqSplitter {
      private final Function1 f;

      public Seq splitWithSignalling() {
         return SeqSplitter.super.splitWithSignalling();
      }

      public Seq psplitWithSignalling(final Seq sizes) {
         return SeqSplitter.super.psplitWithSignalling(sizes);
      }

      public RemainsIteratorTaken newTaken(final int until) {
         return SeqSplitter.super.newTaken(until);
      }

      public SeqSplitter take(final int n) {
         return SeqSplitter.super.take(n);
      }

      public SeqSplitter slice(final int from1, final int until1) {
         return SeqSplitter.super.slice(from1, until1);
      }

      public SeqSplitter map(final Function1 f) {
         return SeqSplitter.super.map(f);
      }

      public RemainsIteratorAppended appendParSeq(final SeqSplitter that) {
         return SeqSplitter.super.appendParSeq(that);
      }

      public SeqSplitter zipParSeq(final SeqSplitter that) {
         return SeqSplitter.super.zipParSeq(that);
      }

      public RemainsIteratorZippedAll zipAllParSeq(final SeqSplitter that, final Object thisElem, final Object thatElem) {
         return SeqSplitter.super.zipAllParSeq(that, thisElem, thatElem);
      }

      public SeqSplitter reverse() {
         return SeqSplitter.super.reverse();
      }

      public Patched patchParSeq(final int from, final SeqSplitter patchElems, final int replaced) {
         return SeqSplitter.super.patchParSeq(from, patchElems, replaced);
      }

      public int prefixLength(final Function1 pred) {
         return AugmentedSeqIterator.prefixLength$(this, pred);
      }

      public int indexWhere(final Function1 pred) {
         return AugmentedSeqIterator.indexWhere$(this, pred);
      }

      public int lastIndexWhere(final Function1 pred) {
         return AugmentedSeqIterator.lastIndexWhere$(this, pred);
      }

      public boolean corresponds(final Function2 corr, final Iterator that) {
         return AugmentedSeqIterator.corresponds$(this, corr, that);
      }

      public Combiner reverse2combiner(final Combiner cb) {
         return AugmentedSeqIterator.reverse2combiner$(this, cb);
      }

      public Combiner reverseMap2combiner(final Function1 f, final Combiner cb) {
         return AugmentedSeqIterator.reverseMap2combiner$(this, f, cb);
      }

      public Combiner updated2combiner(final int index, final Object elem, final Combiner cb) {
         return AugmentedSeqIterator.updated2combiner$(this, index, elem, cb);
      }

      public SeqSplitter dup() {
         return (SeqSplitter)super.dup();
      }

      public Seq split() {
         return super.split();
      }

      public Seq psplit(final Seq sizes) {
         return (Seq)this.scala$collection$parallel$SeqSplitter$RemainsIteratorMapped$$$outer().psplit(sizes).map((x$11) -> x$11.map(this.f));
      }

      // $FF: synthetic method
      public SeqSplitter scala$collection$parallel$SeqSplitter$RemainsIteratorMapped$$$outer() {
         return (SeqSplitter)this.$outer;
      }

      public RemainsIteratorMapped(final Function1 f) {
         super(f);
         this.f = f;
         AugmentedSeqIterator.$init$(this);
         SeqSplitter.$init$(this);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class RemainsIteratorAppended extends IterableSplitter.Appended implements SeqSplitter {
      public Seq splitWithSignalling() {
         return SeqSplitter.super.splitWithSignalling();
      }

      public Seq psplitWithSignalling(final Seq sizes) {
         return SeqSplitter.super.psplitWithSignalling(sizes);
      }

      public RemainsIteratorTaken newTaken(final int until) {
         return SeqSplitter.super.newTaken(until);
      }

      public SeqSplitter take(final int n) {
         return SeqSplitter.super.take(n);
      }

      public SeqSplitter slice(final int from1, final int until1) {
         return SeqSplitter.super.slice(from1, until1);
      }

      public SeqSplitter map(final Function1 f) {
         return SeqSplitter.super.map(f);
      }

      public RemainsIteratorAppended appendParSeq(final SeqSplitter that) {
         return SeqSplitter.super.appendParSeq(that);
      }

      public SeqSplitter zipParSeq(final SeqSplitter that) {
         return SeqSplitter.super.zipParSeq(that);
      }

      public RemainsIteratorZippedAll zipAllParSeq(final SeqSplitter that, final Object thisElem, final Object thatElem) {
         return SeqSplitter.super.zipAllParSeq(that, thisElem, thatElem);
      }

      public SeqSplitter reverse() {
         return SeqSplitter.super.reverse();
      }

      public Patched patchParSeq(final int from, final SeqSplitter patchElems, final int replaced) {
         return SeqSplitter.super.patchParSeq(from, patchElems, replaced);
      }

      public int prefixLength(final Function1 pred) {
         return AugmentedSeqIterator.prefixLength$(this, pred);
      }

      public int indexWhere(final Function1 pred) {
         return AugmentedSeqIterator.indexWhere$(this, pred);
      }

      public int lastIndexWhere(final Function1 pred) {
         return AugmentedSeqIterator.lastIndexWhere$(this, pred);
      }

      public boolean corresponds(final Function2 corr, final Iterator that) {
         return AugmentedSeqIterator.corresponds$(this, corr, that);
      }

      public Combiner reverse2combiner(final Combiner cb) {
         return AugmentedSeqIterator.reverse2combiner$(this, cb);
      }

      public Combiner reverseMap2combiner(final Function1 f, final Combiner cb) {
         return AugmentedSeqIterator.reverseMap2combiner$(this, f, cb);
      }

      public Combiner updated2combiner(final int index, final Object elem, final Combiner cb) {
         return AugmentedSeqIterator.updated2combiner$(this, index, elem, cb);
      }

      public SeqSplitter dup() {
         return (SeqSplitter)super.dup();
      }

      public Seq split() {
         return super.split();
      }

      public Seq psplit(final Seq sizes) {
         if (this.firstNonEmpty()) {
            int selfrem = this.scala$collection$parallel$SeqSplitter$RemainsIteratorAppended$$$outer().remaining();
            BooleanRef appendMiddle = BooleanRef.create(false);
            Seq szcum = (Seq)sizes.scanLeft(BoxesRunTime.boxToInteger(0), (JFunction2.mcIII.sp)(x$12, x$13) -> x$12 + x$13);
            Seq splitsizes = (Seq)((IterableOps)sizes.zip((IterableOnce)((IterableOps)szcum.init()).zip((IterableOnce)szcum.tail()))).flatMap((t) -> {
               if (t != null) {
                  int sz = t._1$mcI$sp();
                  Tuple2 var7 = (Tuple2)t._2();
                  if (var7 != null) {
                     int from = var7._1$mcI$sp();
                     int until = var7._2$mcI$sp();
                     Tuple3 var4 = new Tuple3(BoxesRunTime.boxToInteger(sz), BoxesRunTime.boxToInteger(from), BoxesRunTime.boxToInteger(until));
                     int szx = BoxesRunTime.unboxToInt(var4._1());
                     int from = BoxesRunTime.unboxToInt(var4._2());
                     int until = BoxesRunTime.unboxToInt(var4._3());
                     if (from < selfrem && until > selfrem) {
                        appendMiddle.elem = true;
                        return (Seq)scala.package..MODULE$.Seq().apply(.MODULE$.wrapIntArray(new int[]{selfrem - from, until - selfrem}));
                     }

                     return (Seq)scala.package..MODULE$.Seq().apply(.MODULE$.wrapIntArray(new int[]{szx}));
                  }
               }

               throw new MatchError(t);
            });
            Tuple2 var9 = ((IterableOps)splitsizes.zip((IterableOnce)szcum.init())).span((x$15) -> BoxesRunTime.boxToBoolean($anonfun$psplit$5(selfrem, x$15)));
            if (var9 != null) {
               Seq selfszfrom = (Seq)var9._1();
               Seq thatszfrom = (Seq)var9._2();
               Tuple2 var8 = new Tuple2(selfszfrom, thatszfrom);
               Seq selfszfrom = (Seq)var8._1();
               Seq thatszfromx = (Seq)var8._2();
               Tuple2 var15 = new Tuple2(selfszfrom.map((x$17) -> BoxesRunTime.boxToInteger($anonfun$psplit$6(x$17))), thatszfromx.map((x$18) -> BoxesRunTime.boxToInteger($anonfun$psplit$7(x$18))));
               if (var15 != null) {
                  Seq selfsizes = (Seq)var15._1();
                  Seq thatsizes = (Seq)var15._2();
                  Tuple2 var14 = new Tuple2(selfsizes, thatsizes);
                  Seq selfsizesx = (Seq)var14._1();
                  Seq thatsizesx = (Seq)var14._2();
                  Seq selfs = this.scala$collection$parallel$SeqSplitter$RemainsIteratorAppended$$$outer().psplit(selfsizesx);
                  Seq thats = ((SeqSplitter)this.that()).psplit(thatsizesx);
                  return appendMiddle.elem ? (Seq)((IterableOps)((IterableOps)selfs.init()).$plus$plus(new scala.collection.immutable..colon.colon(((SeqSplitter)selfs.last()).appendParSeq((SeqSplitter)thats.head()), scala.collection.immutable.Nil..MODULE$))).$plus$plus((IterableOnce)thats.tail()) : (Seq)selfs.$plus$plus(thats);
               } else {
                  throw new MatchError(var15);
               }
            } else {
               throw new MatchError(var9);
            }
         } else {
            return ((SeqSplitter)this.curr()).psplit(sizes);
         }
      }

      // $FF: synthetic method
      public SeqSplitter scala$collection$parallel$SeqSplitter$RemainsIteratorAppended$$$outer() {
         return (SeqSplitter)this.$outer;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$psplit$5(final int selfrem$1, final Tuple2 x$15) {
         return x$15._2$mcI$sp() < selfrem$1;
      }

      // $FF: synthetic method
      public static final int $anonfun$psplit$6(final Tuple2 x$17) {
         return x$17._1$mcI$sp();
      }

      // $FF: synthetic method
      public static final int $anonfun$psplit$7(final Tuple2 x$18) {
         return x$18._1$mcI$sp();
      }

      public RemainsIteratorAppended(final SeqSplitter it) {
         super(it);
         AugmentedSeqIterator.$init$(this);
         SeqSplitter.$init$(this);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class RemainsIteratorZipped extends IterableSplitter.Zipped implements SeqSplitter {
      public Seq splitWithSignalling() {
         return SeqSplitter.super.splitWithSignalling();
      }

      public Seq psplitWithSignalling(final Seq sizes) {
         return SeqSplitter.super.psplitWithSignalling(sizes);
      }

      public RemainsIteratorTaken newTaken(final int until) {
         return SeqSplitter.super.newTaken(until);
      }

      public SeqSplitter take(final int n) {
         return SeqSplitter.super.take(n);
      }

      public SeqSplitter slice(final int from1, final int until1) {
         return SeqSplitter.super.slice(from1, until1);
      }

      public SeqSplitter map(final Function1 f) {
         return SeqSplitter.super.map(f);
      }

      public RemainsIteratorAppended appendParSeq(final SeqSplitter that) {
         return SeqSplitter.super.appendParSeq(that);
      }

      public SeqSplitter zipParSeq(final SeqSplitter that) {
         return SeqSplitter.super.zipParSeq(that);
      }

      public RemainsIteratorZippedAll zipAllParSeq(final SeqSplitter that, final Object thisElem, final Object thatElem) {
         return SeqSplitter.super.zipAllParSeq(that, thisElem, thatElem);
      }

      public SeqSplitter reverse() {
         return SeqSplitter.super.reverse();
      }

      public Patched patchParSeq(final int from, final SeqSplitter patchElems, final int replaced) {
         return SeqSplitter.super.patchParSeq(from, patchElems, replaced);
      }

      public int prefixLength(final Function1 pred) {
         return AugmentedSeqIterator.prefixLength$(this, pred);
      }

      public int indexWhere(final Function1 pred) {
         return AugmentedSeqIterator.indexWhere$(this, pred);
      }

      public int lastIndexWhere(final Function1 pred) {
         return AugmentedSeqIterator.lastIndexWhere$(this, pred);
      }

      public boolean corresponds(final Function2 corr, final Iterator that) {
         return AugmentedSeqIterator.corresponds$(this, corr, that);
      }

      public Combiner reverse2combiner(final Combiner cb) {
         return AugmentedSeqIterator.reverse2combiner$(this, cb);
      }

      public Combiner reverseMap2combiner(final Function1 f, final Combiner cb) {
         return AugmentedSeqIterator.reverseMap2combiner$(this, f, cb);
      }

      public Combiner updated2combiner(final int index, final Object elem, final Combiner cb) {
         return AugmentedSeqIterator.updated2combiner$(this, index, elem, cb);
      }

      public SeqSplitter dup() {
         return (SeqSplitter)super.dup();
      }

      public Seq split() {
         return super.split();
      }

      public Seq psplit(final Seq szs) {
         return (Seq)((IterableOps)this.scala$collection$parallel$SeqSplitter$RemainsIteratorZipped$$$outer().psplit(szs).zip(this.that().psplit(szs))).map((p) -> ((SeqSplitter)p._1()).zipParSeq((SeqSplitter)p._2()));
      }

      // $FF: synthetic method
      public SeqSplitter scala$collection$parallel$SeqSplitter$RemainsIteratorZipped$$$outer() {
         return (SeqSplitter)this.$outer;
      }

      public RemainsIteratorZipped(final SeqSplitter ti) {
         super(ti);
         AugmentedSeqIterator.$init$(this);
         SeqSplitter.$init$(this);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class RemainsIteratorZippedAll extends IterableSplitter.ZippedAll implements SeqSplitter {
      public Seq splitWithSignalling() {
         return SeqSplitter.super.splitWithSignalling();
      }

      public Seq psplitWithSignalling(final Seq sizes) {
         return SeqSplitter.super.psplitWithSignalling(sizes);
      }

      public RemainsIteratorTaken newTaken(final int until) {
         return SeqSplitter.super.newTaken(until);
      }

      public SeqSplitter take(final int n) {
         return SeqSplitter.super.take(n);
      }

      public SeqSplitter slice(final int from1, final int until1) {
         return SeqSplitter.super.slice(from1, until1);
      }

      public SeqSplitter map(final Function1 f) {
         return SeqSplitter.super.map(f);
      }

      public RemainsIteratorAppended appendParSeq(final SeqSplitter that) {
         return SeqSplitter.super.appendParSeq(that);
      }

      public SeqSplitter zipParSeq(final SeqSplitter that) {
         return SeqSplitter.super.zipParSeq(that);
      }

      public RemainsIteratorZippedAll zipAllParSeq(final SeqSplitter that, final Object thisElem, final Object thatElem) {
         return SeqSplitter.super.zipAllParSeq(that, thisElem, thatElem);
      }

      public SeqSplitter reverse() {
         return SeqSplitter.super.reverse();
      }

      public Patched patchParSeq(final int from, final SeqSplitter patchElems, final int replaced) {
         return SeqSplitter.super.patchParSeq(from, patchElems, replaced);
      }

      public int prefixLength(final Function1 pred) {
         return AugmentedSeqIterator.prefixLength$(this, pred);
      }

      public int indexWhere(final Function1 pred) {
         return AugmentedSeqIterator.indexWhere$(this, pred);
      }

      public int lastIndexWhere(final Function1 pred) {
         return AugmentedSeqIterator.lastIndexWhere$(this, pred);
      }

      public boolean corresponds(final Function2 corr, final Iterator that) {
         return AugmentedSeqIterator.corresponds$(this, corr, that);
      }

      public Combiner reverse2combiner(final Combiner cb) {
         return AugmentedSeqIterator.reverse2combiner$(this, cb);
      }

      public Combiner reverseMap2combiner(final Function1 f, final Combiner cb) {
         return AugmentedSeqIterator.reverseMap2combiner$(this, f, cb);
      }

      public Combiner updated2combiner(final int index, final Object elem, final Combiner cb) {
         return AugmentedSeqIterator.updated2combiner$(this, index, elem, cb);
      }

      public SeqSplitter dup() {
         return (SeqSplitter)super.dup();
      }

      private Tuple2 patchem() {
         int selfrem = this.scala$collection$parallel$SeqSplitter$RemainsIteratorZippedAll$$$outer().remaining();
         int thatrem = this.that().remaining();
         SeqSplitter thisit = (SeqSplitter)(selfrem < thatrem ? this.scala$collection$parallel$SeqSplitter$RemainsIteratorZippedAll$$$outer().appendParSeq(scala.collection.parallel.immutable.package$.MODULE$.repetition(this.thiselem(), thatrem - selfrem).splitter()) : this.scala$collection$parallel$SeqSplitter$RemainsIteratorZippedAll$$$outer());
         SeqSplitter thatit = (SeqSplitter)(selfrem > thatrem ? this.that().appendParSeq(scala.collection.parallel.immutable.package$.MODULE$.repetition(this.thatelem(), selfrem - thatrem).splitter()) : this.that());
         return new Tuple2(thisit, thatit);
      }

      public Seq split() {
         Tuple2 var3 = this.patchem();
         if (var3 != null) {
            SeqSplitter thisit = (SeqSplitter)var3._1();
            SeqSplitter thatit = (SeqSplitter)var3._2();
            Tuple2 var2 = new Tuple2(thisit, thatit);
            SeqSplitter thisit = (SeqSplitter)var2._1();
            SeqSplitter thatit = (SeqSplitter)var2._2();
            SeqSplitter zipped = thisit.zipParSeq(thatit);
            return zipped.split();
         } else {
            throw new MatchError(var3);
         }
      }

      public Seq psplit(final Seq sizes) {
         Tuple2 var4 = this.patchem();
         if (var4 != null) {
            SeqSplitter thisit = (SeqSplitter)var4._1();
            SeqSplitter thatit = (SeqSplitter)var4._2();
            Tuple2 var3 = new Tuple2(thisit, thatit);
            SeqSplitter thisit = (SeqSplitter)var3._1();
            SeqSplitter thatitx = (SeqSplitter)var3._2();
            SeqSplitter zipped = thisit.zipParSeq(thatitx);
            return zipped.psplit(sizes);
         } else {
            throw new MatchError(var4);
         }
      }

      // $FF: synthetic method
      public SeqSplitter scala$collection$parallel$SeqSplitter$RemainsIteratorZippedAll$$$outer() {
         return (SeqSplitter)this.$outer;
      }

      public RemainsIteratorZippedAll(final SeqSplitter ti, final Object thise, final Object thate) {
         super(ti, thise, thate);
         AugmentedSeqIterator.$init$(this);
         SeqSplitter.$init$(this);
      }
   }

   public class Patched implements SeqSplitter {
      private final int from;
      private final SeqSplitter patch;
      private final int replaced;
      private final RemainsIteratorAppended trio;
      private Signalling signalDelegate;
      // $FF: synthetic field
      public final SeqSplitter $outer;

      public Seq splitWithSignalling() {
         return SeqSplitter.super.splitWithSignalling();
      }

      public Seq psplitWithSignalling(final Seq sizes) {
         return SeqSplitter.super.psplitWithSignalling(sizes);
      }

      public RemainsIteratorTaken newTaken(final int until) {
         return SeqSplitter.super.newTaken(until);
      }

      public SeqSplitter take(final int n) {
         return SeqSplitter.super.take(n);
      }

      public SeqSplitter slice(final int from1, final int until1) {
         return SeqSplitter.super.slice(from1, until1);
      }

      public SeqSplitter map(final Function1 f) {
         return SeqSplitter.super.map(f);
      }

      public RemainsIteratorAppended appendParSeq(final SeqSplitter that) {
         return SeqSplitter.super.appendParSeq(that);
      }

      public SeqSplitter zipParSeq(final SeqSplitter that) {
         return SeqSplitter.super.zipParSeq(that);
      }

      public RemainsIteratorZippedAll zipAllParSeq(final SeqSplitter that, final Object thisElem, final Object thatElem) {
         return SeqSplitter.super.zipAllParSeq(that, thisElem, thatElem);
      }

      public SeqSplitter reverse() {
         return SeqSplitter.super.reverse();
      }

      public Patched patchParSeq(final int from, final SeqSplitter patchElems, final int replaced) {
         return SeqSplitter.super.patchParSeq(from, patchElems, replaced);
      }

      public int prefixLength(final Function1 pred) {
         return AugmentedSeqIterator.prefixLength$(this, pred);
      }

      public int indexWhere(final Function1 pred) {
         return AugmentedSeqIterator.indexWhere$(this, pred);
      }

      public int lastIndexWhere(final Function1 pred) {
         return AugmentedSeqIterator.lastIndexWhere$(this, pred);
      }

      public boolean corresponds(final Function2 corr, final Iterator that) {
         return AugmentedSeqIterator.corresponds$(this, corr, that);
      }

      public Combiner reverse2combiner(final Combiner cb) {
         return AugmentedSeqIterator.reverse2combiner$(this, cb);
      }

      public Combiner reverseMap2combiner(final Function1 f, final Combiner cb) {
         return AugmentedSeqIterator.reverseMap2combiner$(this, f, cb);
      }

      public Combiner updated2combiner(final int index, final Object elem, final Combiner cb) {
         return AugmentedSeqIterator.updated2combiner$(this, index, elem, cb);
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

      public IterableSplitter.Taken newSliceInternal(final IterableSplitter.Taken it, final int from1) {
         return IterableSplitter.newSliceInternal$(this, it, from1);
      }

      public IterableSplitter drop(final int n) {
         return IterableSplitter.drop$(this, n);
      }

      public IterableSplitter.Appended appendParIterable(final IterableSplitter that) {
         return IterableSplitter.appendParIterable$(this, that);
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

      public StringBuilder addString(final StringBuilder b, final String start, final String sep, final String end) {
         return IterableOnceOps.addString$(this, b, start, sep, end);
      }

      public final StringBuilder addString(final StringBuilder b, final String sep) {
         return IterableOnceOps.addString$(this, b, sep);
      }

      public final StringBuilder addString(final StringBuilder b) {
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

      public Map toMap(final scala..less.colon.less ev) {
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

      public boolean hasNext() {
         return this.trio.hasNext();
      }

      public Object next() {
         return this.trio.next();
      }

      public int remaining() {
         return this.trio.remaining();
      }

      public Patched dup() {
         return this.scala$collection$parallel$SeqSplitter$Patched$$$outer().dup().patchParSeq(this.from, this.patch, this.replaced);
      }

      public Seq split() {
         return this.trio.split();
      }

      public Seq psplit(final Seq sizes) {
         return this.trio.psplit(sizes);
      }

      // $FF: synthetic method
      public SeqSplitter scala$collection$parallel$SeqSplitter$Patched$$$outer() {
         return this.$outer;
      }

      public Patched(final int from, final SeqSplitter patch, final int replaced) {
         this.from = from;
         this.patch = patch;
         this.replaced = replaced;
         if (SeqSplitter.this == null) {
            throw null;
         } else {
            this.$outer = SeqSplitter.this;
            super();
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
            RemainsIterator.$init$(this);
            AugmentedIterableIterator.$init$(this);
            DelegatedSignalling.$init$(this);
            IterableSplitter.$init$(this);
            AugmentedSeqIterator.$init$(this);
            SeqSplitter.$init$(this);
            this.signalDelegate_$eq(SeqSplitter.this.signalDelegate());
            Seq pits = SeqSplitter.this.psplit(.MODULE$.wrapIntArray(new int[]{from, replaced, SeqSplitter.this.remaining() - from - replaced}));
            this.trio = ((SeqSplitter)pits.apply(0)).appendParSeq(patch).appendParSeq((SeqSplitter)pits.apply(2));
         }
      }
   }
}
