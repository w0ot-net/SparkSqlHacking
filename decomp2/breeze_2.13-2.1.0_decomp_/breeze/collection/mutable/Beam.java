package breeze.collection.mutable;

import breeze.linalg.clip$;
import breeze.linalg.support.CanMapValues$;
import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.BuildFrom;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableFactory;
import scala.collection.IterableFactoryDefaults;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.LazyZip2;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.View;
import scala.collection.WithFilter;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Cloneable;
import scala.collection.mutable.Growable;
import scala.collection.mutable.Shrinkable;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\r\u0005a\u0001\u0002\"D\u0001)C\u0001b\u001e\u0001\u0003\u0006\u0004%\t\u0001\u001f\u0005\ty\u0002\u0011\t\u0011)A\u0005s\"AQ\u0010\u0001BC\u0002\u0013Mc\u0010C\u0005\u0002\u0006\u0001\u0011\t\u0011)A\u0005\u007f\"9\u0011q\u0001\u0001\u0005\u0002\u0005%\u0001\"CA\t\u0001\t\u0007I\u0011CA\n\u0011!\t)\u0003\u0001Q\u0001\n\u0005U\u0001BBA\u0014\u0001\u0011\u0005\u0003\u0010C\u0004\u0002*\u0001!\t!a\u000b\t\u000f\u00055\u0002\u0001\"\u0011\u00020!9\u0011q\u0007\u0001\u0005B\u0005e\u0002bBA \u0001\u0011\u0005\u0013\u0011\t\u0005\b\u0005C\u0003A\u0011\u0001BR\u0011\u001d\ti\u0010\u0001C!\u0005OCqAa.\u0001\t\u0003\u0012I\fC\u0004\u0003B\u0002!\tEa1\t\u000f\t\u0005\u0001\u0001\"\u0011\u0003L\"9!\u0011\u001b\u0001\u0005B\tM\u0007b\u0002Bk\u0001\u0011\u0005#q\u001b\u0005\b\u0005G\u0004A\u0011\u000bBs\u0011\u001d\u0011\t\u0010\u0001C!\u0005g<q!a\u0015D\u0011\u0003\t)F\u0002\u0004C\u0007\"\u0005\u0011q\u000b\u0005\b\u0003\u000f9B\u0011AA2\r%\t)g\u0006I\u0001$C\t9\u0007C\u0004\u0002le1\t!!\u001c\b\u000f\t\u001dr\u0003#!\u0003\u0010\u00199!qA\f\t\u0002\n%\u0001bBA\u00049\u0011\u0005!Q\u0002\u0005\b\u0003WbB\u0011\u0001B\t\u0011%\t\u0019\rHA\u0001\n\u0003\n)\r\u0003\u0005\u0002Tr\t\t\u0011\"\u0001y\u0011%\t)\u000eHA\u0001\n\u0003\u0011)\u0002C\u0005\u0002^r\t\t\u0011\"\u0011\u0002`\"I\u0011q\u001d\u000f\u0002\u0002\u0013\u0005!\u0011\u0004\u0005\n\u0003sd\u0012\u0011!C!\u0003wD\u0011\"!@\u001d\u0003\u0003%\t%a@\t\u0013\tuA$!A\u0005\n\t}aABA</\u0001\u000bI\b\u0003\u0006\u0002l\u001d\u0012)\u001a!C\u0001\u0003\u0017C!\"a$(\u0005#\u0005\u000b\u0011BAG\u0011\u001d\t9a\nC\u0001\u0003#C\u0011\"a&(\u0003\u0003%\t!!'\t\u0013\u0005\u001dv%%A\u0005\u0002\u0005%\u0006\"CAbO\u0005\u0005I\u0011IAc\u0011!\t\u0019nJA\u0001\n\u0003A\b\"CAkO\u0005\u0005I\u0011AAl\u0011%\tinJA\u0001\n\u0003\ny\u000eC\u0005\u0002h\u001e\n\t\u0011\"\u0001\u0002j\"I\u00111_\u0014\u0002\u0002\u0013\u0005\u0013Q\u001f\u0005\n\u0003s<\u0013\u0011!C!\u0003wD\u0011\"!@(\u0003\u0003%\t%a@\t\u0013\t\u0005q%!A\u0005B\t\rq!\u0003B\u0015/\u0005\u0005\t\u0012\u0001B\u0016\r%\t9hFA\u0001\u0012\u0003\u0011i\u0003C\u0004\u0002\b]\"\tAa\f\t\u0013\u0005ux'!A\u0005F\u0005}\b\"\u0003B\u0019o\u0005\u0005I\u0011\u0011B\u001a\u0011%\u0011\teNA\u0001\n\u0003\u0013\u0019\u0005C\u0005\u0003\u001e]\n\t\u0011\"\u0003\u0003 !I!\u0011L\fC\u0002\u0013\u0005!1\f\u0005\t\u0005;:\u0002\u0015!\u0003\u0003\f!9!qL\f\u0005\u0004\t\u0005\u0004b\u0002B\u0019/\u0011\u0005!q\u0010\u0005\n\u0005;9\u0012\u0011!C\u0005\u0005?\u0011AAQ3b[*\u0011A)R\u0001\b[V$\u0018M\u00197f\u0015\t1u)\u0001\u0006d_2dWm\u0019;j_:T\u0011\u0001S\u0001\u0007EJ,WM_3\u0004\u0001U\u00111*W\n\u0007\u00011\u0013&MZ6\u0011\u00055\u0003V\"\u0001(\u000b\u0003=\u000bQa]2bY\u0006L!!\u0015(\u0003\r\u0005s\u0017PU3g!\r\u0019VkV\u0007\u0002)*\u0011aIT\u0005\u0003-R\u0013\u0001\"\u0013;fe\u0006\u0014G.\u001a\t\u00031fc\u0001\u0001B\u0003[\u0001\t\u00071LA\u0001U#\tav\f\u0005\u0002N;&\u0011aL\u0014\u0002\b\u001d>$\b.\u001b8h!\ti\u0005-\u0003\u0002b\u001d\n\u0019\u0011I\\=\u0011\u0007\r$w+D\u0001D\u0013\t)7IA\u0003J\u0005\u0016\fW\u000eE\u0003TO^K'.\u0003\u0002i)\nY\u0011\n^3sC\ndWm\u00149t!\t\u0019V\u000bE\u0002d\u0001]\u0003\"\u0001\u001c;\u000f\u00055\u0014hB\u00018r\u001b\u0005y'B\u00019J\u0003\u0019a$o\\8u}%\tq*\u0003\u0002t\u001d\u00069\u0001/Y2lC\u001e,\u0017BA;w\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\u0019h*A\u0004nCb\u001c\u0016N_3\u0016\u0003e\u0004\"!\u0014>\n\u0005mt%aA%oi\u0006AQ.\u0019=TSj,\u0007%\u0001\u0005pe\u0012,'/\u001b8h+\u0005y\b\u0003\u00027\u0002\u0002]K1!a\u0001w\u0005!y%\u000fZ3sS:<\u0017!C8sI\u0016\u0014\u0018N\\4!\u0003\u0019a\u0014N\\5u}Q!\u00111BA\b)\rQ\u0017Q\u0002\u0005\u0006{\u0016\u0001\u001da \u0005\u0006o\u0016\u0001\r!_\u0001\u0006cV,W/Z\u000b\u0003\u0003+\u0001R!a\u0006\u0002\"]k!!!\u0007\u000b\t\u0005m\u0011QD\u0001\u0005kRLGN\u0003\u0002\u0002 \u0005!!.\u0019<b\u0013\u0011\t\u0019#!\u0007\u0003\u001bA\u0013\u0018n\u001c:jif\fV/Z;f\u0003\u0019\tX/Z;fA\u0005!1/\u001b>f\u0003\ri\u0017N\\\u000b\u0002/\u00061\u0011\r\u001a3P]\u0016$B!!\r\u000245\t\u0001\u0001\u0003\u0004\u00026)\u0001\raV\u0001\u0002q\u0006Y1/\u001e2ue\u0006\u001cGo\u00148f)\u0011\t\t$a\u000f\t\r\u0005u2\u00021\u0001X\u0003\u0011)G.Z7\u0002\u0015\rDWmY6fI\u0006#G\r\u0006\u0003\u0002D\t}\u0005\u0003BA#3]s1!a\u0012\u0017\u001d\u0011\tI%!\u0015\u000f\t\u0005-\u0013q\n\b\u0004]\u00065\u0013\"\u0001%\n\u0005\u0019;\u0015B\u0001#F\u0003\u0011\u0011U-Y7\u0011\u0005\r<2\u0003B\fM\u00033\u0002B!a\u0017\u0002b5\u0011\u0011Q\f\u0006\u0005\u0003?\ni\"\u0001\u0002j_&\u0019Q/!\u0018\u0015\u0005\u0005U#A\u0003\"fC6\u0014Vm];miV!\u0011\u0011NA:'\tIB*A\u0004eK2,G/\u001a3\u0016\u0005\u0005=\u0004\u0003B*V\u0003c\u00022\u0001WA:\t\u0019Q\u0016\u0004\"b\u00017&\u001a\u0011d\n\u000f\u0003\u000b\u0005#G-\u001a3\u0016\t\u0005m\u00141Q\n\bO1\u000bi(!\"l!\u0015\ty(GAA\u001b\u00059\u0002c\u0001-\u0002\u0004\u00121!l\nCC\u0002m\u00032!TAD\u0013\r\tII\u0014\u0002\b!J|G-^2u+\t\ti\t\u0005\u0003T+\u0006\u0005\u0015\u0001\u00033fY\u0016$X\r\u001a\u0011\u0015\t\u0005M\u0015Q\u0013\t\u0006\u0003\u007f:\u0013\u0011\u0011\u0005\b\u0003WR\u0003\u0019AAG\u0003\u0011\u0019w\u000e]=\u0016\t\u0005m\u0015\u0011\u0015\u000b\u0005\u0003;\u000b\u0019\u000bE\u0003\u0002\u0000\u001d\ny\nE\u0002Y\u0003C#QAW\u0016C\u0002mC\u0011\"a\u001b,!\u0003\u0005\r!!*\u0011\tM+\u0016qT\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0011\tY+!1\u0016\u0005\u00055&\u0006BAG\u0003_[#!!-\u0011\t\u0005M\u0016QX\u0007\u0003\u0003kSA!a.\u0002:\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003ws\u0015AC1o]>$\u0018\r^5p]&!\u0011qXA[\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0003\u000652\u0012\raW\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005\u001d\u0007\u0003BAe\u0003\u001fl!!a3\u000b\t\u00055\u0017QD\u0001\u0005Y\u0006tw-\u0003\u0003\u0002R\u0006-'AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0007}\u000bI\u000e\u0003\u0005\u0002\\>\n\t\u00111\u0001z\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011\u0011\u001d\t\u0005'\u0006\rx,C\u0002\u0002fR\u0013\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u00111^Ay!\ri\u0015Q^\u0005\u0004\u0003_t%a\u0002\"p_2,\u0017M\u001c\u0005\t\u00037\f\u0014\u0011!a\u0001?\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\t9-a>\t\u0011\u0005m''!AA\u0002e\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002s\u0006AAo\\*ue&tw\r\u0006\u0002\u0002H\u00061Q-];bYN$B!a;\u0003\u0006!A\u00111\\\u001b\u0002\u0002\u0003\u0007qL\u0001\u0005O_R\fE\rZ3e'\u001daBJa\u0003\u0002\u0006.\u0004B!a \u001a9R\u0011!q\u0002\t\u0004\u0003\u007fbRC\u0001B\n!\r\u0019V\u000b\u0018\u000b\u0004?\n]\u0001\u0002CAnC\u0005\u0005\t\u0019A=\u0015\t\u0005-(1\u0004\u0005\t\u00037\u001c\u0013\u0011!a\u0001?\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011!\u0011\u0005\t\u0005\u0003\u0013\u0014\u0019#\u0003\u0003\u0003&\u0005-'AB(cU\u0016\u001cG/\u0001\u0005O_R\fE\rZ3e\u0003\u0015\tE\rZ3e!\r\tyhN\n\u0005o1\u000bI\u0006\u0006\u0002\u0003,\u0005)\u0011\r\u001d9msV!!Q\u0007B\u001e)\u0011\u00119D!\u0010\u0011\u000b\u0005}tE!\u000f\u0011\u0007a\u0013Y\u0004B\u0003[u\t\u00071\fC\u0004\u0002li\u0002\rAa\u0010\u0011\tM+&\u0011H\u0001\bk:\f\u0007\u000f\u001d7z+\u0011\u0011)E!\u0015\u0015\t\t\u001d#1\u000b\t\u0006\u001b\n%#QJ\u0005\u0004\u0005\u0017r%AB(qi&|g\u000e\u0005\u0003T+\n=\u0003c\u0001-\u0003R\u0011)!l\u000fb\u00017\"I!QK\u001e\u0002\u0002\u0003\u0007!qK\u0001\u0004q\u0012\u0002\u0004#BA@O\t=\u0013A\u0004(pi\"LgnZ#wS\u000e$X\rZ\u000b\u0003\u0005\u0017\tqBT8uQ&tw-\u0012<jGR,G\rI\u0001\rG\u0006t')^5mI\u001a\u0013x.\\\u000b\u0007\u0005G\u0012yGa\u001d\u0015\t\t\u0015$\u0011\u0010\t\n'\n\u001d$1\u000eB9\u0005oJ1A!\u001bU\u0005%\u0011U/\u001b7e\rJ|W\u000e\u0005\u0003d\u0001\t5\u0004c\u0001-\u0003p\u0011)!l\u0010b\u00017B\u0019\u0001La\u001d\u0005\r\tUtH1\u0001\\\u0005\u0005)\u0006\u0003B2\u0001\u0005cB\u0011Ba\u001f@\u0003\u0003\u0005\u001dA! \u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0003m\u0003\u0003\u0011\t(\u0006\u0003\u0003\u0002\n-E\u0003\u0002BB\u0005;#BA!\"\u0003\u0014R!!q\u0011BG!\u0011\u0019\u0007A!#\u0011\u0007a\u0013Y\tB\u0003[\u0001\n\u00071\fC\u0005\u0003\u0010\u0002\u000b\t\u0011q\u0001\u0003\u0012\u0006QQM^5eK:\u001cW\r\n\u001a\u0011\u000b1\f\tA!#\t\u000f\tU\u0005\t1\u0001\u0003\u0018\u0006\u0011\u0001p\u001d\t\u0006\u001b\ne%\u0011R\u0005\u0004\u00057s%A\u0003\u001fsKB,\u0017\r^3e}!)q\u000f\u0011a\u0001s\"1\u0011Q\u0007\u0007A\u0002]\u000b\u0001\"\u001b;fe\u0006$xN]\u000b\u0003\u0005K\u0003BaUAr/R\u0011!\u0011\u0016\t\u0005\u0005W\u0013\u0019L\u0004\u0003\u0003.\n=\u0006C\u00018O\u0013\r\u0011\tLT\u0001\u0007!J,G-\u001a4\n\t\u0005E'Q\u0017\u0006\u0004\u0005cs\u0015!B2mK\u0006\u0014HC\u0001B^!\ri%QX\u0005\u0004\u0005\u007fs%\u0001B+oSR\faA]3tk2$HC\u0001Bc!\u0011\u0019&qY,\n\u0007\t%GK\u0001\u0006J]\u0012,\u00070\u001a3TKF$B!a;\u0003N\"1!qZ\tA\u0002}\u000b1a\u001c2k\u0003\u0015\u0019Gn\u001c8f)\u0005Q\u0017A\u00058foN\u0003XmY5gS\u000e\u0014U/\u001b7eKJ,\"A!7\u0011\r\tm'q\\,k\u001b\t\u0011iN\u0003\u0002E)&!!\u0011\u001dBo\u0005\u001d\u0011U/\u001b7eKJ\fAB\u001a:p[N\u0003XmY5gS\u000e$2A\u001bBt\u0011\u001d\u0011I\u000f\u0006a\u0001\u0005W\fAaY8mYB!1K!<X\u0013\r\u0011y\u000f\u0016\u0002\r\u0013R,'/\u00192mK>s7-Z\u0001\u0006K6\u0004H/_\u000b\u0002U\":\u0001Aa>\u0003~\n}\bcA'\u0003z&\u0019!1 (\u0003!M+'/[1m-\u0016\u00148/[8o+&#\u0015!\u0002<bYV,g$A\u0001"
)
public class Beam implements IBeam, Serializable {
   private static final long serialVersionUID = 1L;
   private final int maxSize;
   private final Ordering ordering;
   private final PriorityQueue queue;

   public static Beam apply(final int maxSize, final Seq xs, final Ordering evidence$2) {
      return Beam$.MODULE$.apply(maxSize, xs, evidence$2);
   }

   public static BuildFrom canBuildFrom(final Ordering evidence$1) {
      return Beam$.MODULE$.canBuildFrom(evidence$1);
   }

   public static BeamResult NothingEvicted() {
      return Beam$.MODULE$.NothingEvicted();
   }

   public int knownSize() {
      return IBeam.knownSize$(this);
   }

   // $FF: synthetic method
   public Object scala$collection$mutable$Cloneable$$super$clone() {
      return super.clone();
   }

   public final Shrinkable $minus$eq(final Object elem) {
      return Shrinkable.$minus$eq$(this, elem);
   }

   /** @deprecated */
   public Shrinkable $minus$eq(final Object elem1, final Object elem2, final Seq elems) {
      return Shrinkable.$minus$eq$(this, elem1, elem2, elems);
   }

   public Shrinkable subtractAll(final IterableOnce xs) {
      return Shrinkable.subtractAll$(this, xs);
   }

   public final Shrinkable $minus$minus$eq(final IterableOnce xs) {
      return Shrinkable.$minus$minus$eq$(this, xs);
   }

   public void sizeHint(final int size) {
      Builder.sizeHint$(this, size);
   }

   public final void sizeHint(final IterableOnce coll, final int delta) {
      Builder.sizeHint$(this, coll, delta);
   }

   public final int sizeHint$default$2() {
      return Builder.sizeHint$default$2$(this);
   }

   public final void sizeHintBounded(final int size, final Iterable boundingColl) {
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

   public Growable addAll(final IterableOnce xs) {
      return Growable.addAll$(this, xs);
   }

   public final Growable $plus$plus$eq(final IterableOnce xs) {
      return Growable.$plus$plus$eq$(this, xs);
   }

   /** @deprecated */
   public final Iterable toIterable() {
      return Iterable.toIterable$(this);
   }

   public final Iterable coll() {
      return Iterable.coll$(this);
   }

   public IterableFactory iterableFactory() {
      return Iterable.iterableFactory$(this);
   }

   /** @deprecated */
   public Iterable seq() {
      return Iterable.seq$(this);
   }

   public String className() {
      return Iterable.className$(this);
   }

   public final String collectionClassName() {
      return Iterable.collectionClassName$(this);
   }

   public String stringPrefix() {
      return Iterable.stringPrefix$(this);
   }

   public LazyZip2 lazyZip(final Iterable that) {
      return Iterable.lazyZip$(this, that);
   }

   /** @deprecated */
   public final Iterable toTraversable() {
      return IterableOps.toTraversable$(this);
   }

   public boolean isTraversableAgain() {
      return IterableOps.isTraversableAgain$(this);
   }

   /** @deprecated */
   public final Object repr() {
      return IterableOps.repr$(this);
   }

   /** @deprecated */
   public IterableFactory companion() {
      return IterableOps.companion$(this);
   }

   public Object head() {
      return IterableOps.head$(this);
   }

   public Option headOption() {
      return IterableOps.headOption$(this);
   }

   public Object last() {
      return IterableOps.last$(this);
   }

   public Option lastOption() {
      return IterableOps.lastOption$(this);
   }

   public View view() {
      return IterableOps.view$(this);
   }

   public int sizeCompare(final int otherSize) {
      return IterableOps.sizeCompare$(this, otherSize);
   }

   public final IterableOps sizeIs() {
      return IterableOps.sizeIs$(this);
   }

   public int sizeCompare(final Iterable that) {
      return IterableOps.sizeCompare$(this, that);
   }

   /** @deprecated */
   public View view(final int from, final int until) {
      return IterableOps.view$(this, from, until);
   }

   public Object transpose(final Function1 asIterable) {
      return IterableOps.transpose$(this, asIterable);
   }

   public Object filter(final Function1 pred) {
      return IterableOps.filter$(this, pred);
   }

   public Object filterNot(final Function1 pred) {
      return IterableOps.filterNot$(this, pred);
   }

   public WithFilter withFilter(final Function1 p) {
      return IterableOps.withFilter$(this, p);
   }

   public Tuple2 partition(final Function1 p) {
      return IterableOps.partition$(this, p);
   }

   public Tuple2 splitAt(final int n) {
      return IterableOps.splitAt$(this, n);
   }

   public Object take(final int n) {
      return IterableOps.take$(this, n);
   }

   public Object takeRight(final int n) {
      return IterableOps.takeRight$(this, n);
   }

   public Object takeWhile(final Function1 p) {
      return IterableOps.takeWhile$(this, p);
   }

   public Tuple2 span(final Function1 p) {
      return IterableOps.span$(this, p);
   }

   public Object drop(final int n) {
      return IterableOps.drop$(this, n);
   }

   public Object dropRight(final int n) {
      return IterableOps.dropRight$(this, n);
   }

   public Object dropWhile(final Function1 p) {
      return IterableOps.dropWhile$(this, p);
   }

   public Iterator grouped(final int size) {
      return IterableOps.grouped$(this, size);
   }

   public Iterator sliding(final int size) {
      return IterableOps.sliding$(this, size);
   }

   public Iterator sliding(final int size, final int step) {
      return IterableOps.sliding$(this, size, step);
   }

   public Object tail() {
      return IterableOps.tail$(this);
   }

   public Object init() {
      return IterableOps.init$(this);
   }

   public Object slice(final int from, final int until) {
      return IterableOps.slice$(this, from, until);
   }

   public Map groupBy(final Function1 f) {
      return IterableOps.groupBy$(this, f);
   }

   public Map groupMap(final Function1 key, final Function1 f) {
      return IterableOps.groupMap$(this, key, f);
   }

   public Map groupMapReduce(final Function1 key, final Function1 f, final Function2 reduce) {
      return IterableOps.groupMapReduce$(this, key, f, reduce);
   }

   public Object scan(final Object z, final Function2 op) {
      return IterableOps.scan$(this, z, op);
   }

   public Object scanLeft(final Object z, final Function2 op) {
      return IterableOps.scanLeft$(this, z, op);
   }

   public Object scanRight(final Object z, final Function2 op) {
      return IterableOps.scanRight$(this, z, op);
   }

   public Object map(final Function1 f) {
      return IterableOps.map$(this, f);
   }

   public Object flatMap(final Function1 f) {
      return IterableOps.flatMap$(this, f);
   }

   public Object flatten(final Function1 asIterable) {
      return IterableOps.flatten$(this, asIterable);
   }

   public Object collect(final PartialFunction pf) {
      return IterableOps.collect$(this, pf);
   }

   public Tuple2 partitionMap(final Function1 f) {
      return IterableOps.partitionMap$(this, f);
   }

   public Object concat(final IterableOnce suffix) {
      return IterableOps.concat$(this, suffix);
   }

   public final Object $plus$plus(final IterableOnce suffix) {
      return IterableOps.$plus$plus$(this, suffix);
   }

   public Object zip(final IterableOnce that) {
      return IterableOps.zip$(this, that);
   }

   public Object zipWithIndex() {
      return IterableOps.zipWithIndex$(this);
   }

   public Object zipAll(final Iterable that, final Object thisElem, final Object thatElem) {
      return IterableOps.zipAll$(this, that, thisElem, thatElem);
   }

   public Tuple2 unzip(final Function1 asPair) {
      return IterableOps.unzip$(this, asPair);
   }

   public Tuple3 unzip3(final Function1 asTriple) {
      return IterableOps.unzip3$(this, asTriple);
   }

   public Iterator tails() {
      return IterableOps.tails$(this);
   }

   public Iterator inits() {
      return IterableOps.inits$(this);
   }

   public Object tapEach(final Function1 f) {
      return IterableOps.tapEach$(this, f);
   }

   /** @deprecated */
   public Object $plus$plus$colon(final IterableOnce that) {
      return IterableOps.$plus$plus$colon$(this, that);
   }

   /** @deprecated */
   public boolean hasDefiniteSize() {
      return IterableOnceOps.hasDefiniteSize$(this);
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

   public int count(final Function1 p) {
      return IterableOnceOps.count$(this, p);
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

   public Object fold(final Object z, final Function2 op) {
      return IterableOnceOps.fold$(this, z, op);
   }

   public Object reduce(final Function2 op) {
      return IterableOnceOps.reduce$(this, op);
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

   public boolean isEmpty() {
      return IterableOnceOps.isEmpty$(this);
   }

   public boolean nonEmpty() {
      return IterableOnceOps.nonEmpty$(this);
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

   public Object sum(final Numeric num) {
      return IterableOnceOps.sum$(this, num);
   }

   public Object product(final Numeric num) {
      return IterableOnceOps.product$(this, num);
   }

   public Object min(final Ordering ord) {
      return IterableOnceOps.min$(this, ord);
   }

   public Option minOption(final Ordering ord) {
      return IterableOnceOps.minOption$(this, ord);
   }

   public Object max(final Ordering ord) {
      return IterableOnceOps.max$(this, ord);
   }

   public Option maxOption(final Ordering ord) {
      return IterableOnceOps.maxOption$(this, ord);
   }

   public Object maxBy(final Function1 f, final Ordering cmp) {
      return IterableOnceOps.maxBy$(this, f, cmp);
   }

   public Option maxByOption(final Function1 f, final Ordering cmp) {
      return IterableOnceOps.maxByOption$(this, f, cmp);
   }

   public Object minBy(final Function1 f, final Ordering cmp) {
      return IterableOnceOps.minBy$(this, f, cmp);
   }

   public Option minByOption(final Function1 f, final Ordering cmp) {
      return IterableOnceOps.minByOption$(this, f, cmp);
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

   public Map toMap(final .less.colon.less ev) {
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

   public int maxSize() {
      return this.maxSize;
   }

   public Ordering ordering() {
      return this.ordering;
   }

   public PriorityQueue queue() {
      return this.queue;
   }

   public int size() {
      return this.queue().size();
   }

   public Object min() {
      if (this.queue().isEmpty()) {
         throw new NoSuchElementException();
      } else {
         return this.queue().peek();
      }
   }

   public Beam addOne(final Object x) {
      if (this.queue().size() < this.maxSize()) {
         BoxesRunTime.boxToBoolean(this.queue().add(x));
      } else if (this.maxSize() > 0 && this.ordering().compare(this.min(), x) < 0) {
         this.queue().poll();
         BoxesRunTime.boxToBoolean(this.queue().add(x));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return this;
   }

   public Beam subtractOne(final Object elem) {
      this.queue().remove(elem);
      return this;
   }

   public BeamResult checkedAdd(final Object x) {
      Object var10000;
      if (this.queue().size() < this.maxSize()) {
         this.queue().add(x);
         var10000 = Beam$.MODULE$.NothingEvicted();
      } else if (this.maxSize() > 0 && this.ordering().compare(this.min(), x) < 0) {
         Object r = this.queue().poll();
         this.queue().add(x);
         var10000 = new Added((Iterable)scala.collection.Iterable..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{r})));
      } else {
         var10000 = Beam.NotAdded$.MODULE$;
      }

      return (BeamResult)var10000;
   }

   public Iterator iterator() {
      return scala.jdk.CollectionConverters..MODULE$.IteratorHasAsScala(this.queue().iterator()).asScala();
   }

   public String toString() {
      return this.iterator().mkString("Beam(", ",", ")");
   }

   public void clear() {
      this.queue().clear();
   }

   public scala.collection.IndexedSeq result() {
      ArrayBuffer r = new ArrayBuffer();

      while(!this.queue().isEmpty()) {
         r.$plus$eq(this.queue().poll());
      }

      return (scala.collection.IndexedSeq)r.reverse();
   }

   public boolean equals(final Object obj) {
      boolean var2;
      if (obj instanceof Beam) {
         Beam var4 = (Beam)obj;
         var2 = this.maxSize() == var4.maxSize() && this.iterator().sameElements(var4.iterator());
      } else {
         var2 = false;
      }

      return var2;
   }

   public Beam clone() {
      return (Beam)(new Beam(this.maxSize(), this.ordering())).$plus$plus$eq(this.iterator());
   }

   public Builder newSpecificBuilder() {
      return Beam$.MODULE$.canBuildFrom(this.ordering()).newBuilder(this);
   }

   public Beam fromSpecific(final IterableOnce coll) {
      return (Beam)Beam$.MODULE$.canBuildFrom(this.ordering()).fromSpecific(this, coll);
   }

   public Beam empty() {
      return Beam$.MODULE$.apply(this.maxSize(), scala.collection.immutable.Nil..MODULE$, this.ordering());
   }

   public Beam(final int maxSize, final Ordering ordering) {
      this.maxSize = maxSize;
      this.ordering = ordering;
      IterableOnce.$init$(this);
      IterableOnceOps.$init$(this);
      IterableOps.$init$(this);
      IterableFactoryDefaults.$init$(this);
      Iterable.$init$(this);
      Growable.$init$(this);
      Builder.$init$(this);
      Shrinkable.$init$(this);
      Cloneable.$init$(this);
      IBeam.$init$(this);
      scala.Predef..MODULE$.assert(maxSize >= 0);
      this.queue = new PriorityQueue(clip$.MODULE$.apply$mIIIc$sp(BoxesRunTime.boxToInteger(maxSize), 1, 16, clip$.MODULE$.clipOrdering(scala.math.Ordering.Int..MODULE$, CanMapValues$.MODULE$.canMapSelfInt())), ordering);
   }

   public static class NotAdded$ implements BeamResult, Product, Serializable {
      public static final NotAdded$ MODULE$ = new NotAdded$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Iterable deleted() {
         return (Iterable)scala.collection.Iterable..MODULE$.empty();
      }

      public String productPrefix() {
         return "NotAdded";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         Object var2 = Statics.ioobe(x$1);
         return var2;
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof NotAdded$;
      }

      public int hashCode() {
         return 1613002253;
      }

      public String toString() {
         return "NotAdded";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(NotAdded$.class);
      }
   }

   public static class Added implements BeamResult, Product, Serializable {
      private final Iterable deleted;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Iterable deleted() {
         return this.deleted;
      }

      public Added copy(final Iterable deleted) {
         return new Added(deleted);
      }

      public Iterable copy$default$1() {
         return this.deleted();
      }

      public String productPrefix() {
         return "Added";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.deleted();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Added;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "deleted";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var7;
         if (this != x$1) {
            label53: {
               boolean var2;
               if (x$1 instanceof Added) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label36: {
                     label35: {
                        Added var4 = (Added)x$1;
                        Iterable var10000 = this.deleted();
                        Iterable var5 = var4.deleted();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label35;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label35;
                        }

                        if (var4.canEqual(this)) {
                           var7 = true;
                           break label36;
                        }
                     }

                     var7 = false;
                  }

                  if (var7) {
                     break label53;
                  }
               }

               var7 = false;
               return var7;
            }
         }

         var7 = true;
         return var7;
      }

      public Added(final Iterable deleted) {
         this.deleted = deleted;
         Product.$init$(this);
      }
   }

   public static class Added$ implements Serializable {
      public static final Added$ MODULE$ = new Added$();

      public final String toString() {
         return "Added";
      }

      public Added apply(final Iterable deleted) {
         return new Added(deleted);
      }

      public Option unapply(final Added x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.deleted()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Added$.class);
      }
   }

   public interface BeamResult {
      Iterable deleted();
   }
}
