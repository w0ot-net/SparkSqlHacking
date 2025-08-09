package breeze.collection.mutable;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.BuildFrom;
import scala.collection.Factory;
import scala.collection.IndexedSeq;
import scala.collection.IndexedSeqView;
import scala.collection.Iterable;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.Searching;
import scala.collection.Seq;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.StrictOptimizedSeqOps;
import scala.collection.Iterator.;
import scala.collection.generic.DefaultSerializable;
import scala.collection.mutable.AbstractBuffer;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.GrowableBuilder;
import scala.collection.mutable.IndexedBuffer;
import scala.collection.mutable.IndexedSeqOps;
import scala.math.Integral;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\tec\u0001\u0002\u00180\u0001YB\u0001B\u001c\u0001\u0003\u0006\u0004%\ta\u001c\u0005\tg\u0002\u0011\t\u0011)A\u0005a\")A\u000f\u0001C\u0001k\"9q\u000f\u0001b\u0001\n\u0013A\bB\u0002?\u0001A\u0003%\u0011\u0010C\u0003~\u0001\u0011\u0005a\u0010\u0003\u0004\u0002\u0006\u0001!\te\u001c\u0005\t\u0003\u000f\u0001\u0001\u0019!C\u0005_\"I\u0011\u0011\u0002\u0001A\u0002\u0013%\u00111\u0002\u0005\b\u0003/\u0001\u0001\u0015)\u0003q\u0011!\tI\u0002\u0001a\u0001\n\u0013y\u0007\"CA\u000e\u0001\u0001\u0007I\u0011BA\u000f\u0011\u001d\t\t\u0003\u0001Q!\nADa!a\t\u0001\t\u0013y\u0007bBA\u0013\u0001\u0011\u0005\u0013q\u0005\u0005\u0007\u0003[\u0001A\u0011A8\t\u000f\u0005=\u0002\u0001\"\u0003\u00022!9\u0011q\u0007\u0001\u0005\n\u0005e\u0002\"CA!\u0001E\u0005I\u0011BA\"\u0011\u001d\tI\u0006\u0001C\u0001\u00037Bq!a\u0019\u0001\t\u0013\t)\u0007C\u0004\u0002l\u0001!I!!\u001c\t\u000f\u0005E\u0004\u0001\"\u0001\u0002t!9\u0011\u0011\u0010\u0001\u0005\u0002\u0005m\u0004bBA?\u0001\u0011\u0005\u0013q\u0010\u0005\b\u0003\u000b\u0003A\u0011IAD\u0011\u001d\t)\n\u0001C\u0001\u0003/Cq!a(\u0001\t\u0003\n\t\u000bC\u0004\u0002 \u0002!\t!!+\t\u000f\u00055\u0006\u0001\"\u0011\u00020\"9\u00111\u0017\u0001\u0005B\u0005U\u0006bBA\\\u0001\u0011\u0005\u0013\u0011\u0018\u0005\b\u0003\u0017\u0004A\u0011IAg\u0011\u001d\t)\u000e\u0001C\u0001\u0003sCq!a6\u0001\t\u0003\nI\u000eC\u0004\u0002\\\u0002!\t&!8\t\u000f\u0005\u001d\b\u0001\"\u0015\u0002j\"9\u0011Q\u001e\u0001\u0005B\u0005=\bbBAy\u0001\u0011\u0005\u00131_\u0004\b\u0003w|\u0003\u0012AA\u007f\r\u0019qs\u0006#\u0001\u0002\u0000\"1A/\u000bC\u0001\u0005/Aq!!\n*\t\u0003\u0011I\u0002C\u0004\u00030%\"\u0019A!\r\t\u0013\t%\u0013&!A\u0005\n\t-#A\u0003*j]\u001e\u0014UO\u001a4fe*\u0011\u0001'M\u0001\b[V$\u0018M\u00197f\u0015\t\u00114'\u0001\u0006d_2dWm\u0019;j_:T\u0011\u0001N\u0001\u0007EJ,WM_3\u0004\u0001U\u0011qGQ\n\b\u0001abu*V-`!\rId\bQ\u0007\u0002u)\u0011\u0001g\u000f\u0006\u0003eqR\u0011!P\u0001\u0006g\u000e\fG.Y\u0005\u0003\u007fi\u0012a\"\u00112tiJ\f7\r\u001e\"vM\u001a,'\u000f\u0005\u0002B\u00052\u0001A!B\"\u0001\u0005\u0004!%!A!\u0012\u0005\u0015K\u0005C\u0001$H\u001b\u0005a\u0014B\u0001%=\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\u0012&\n\u0005-c$aA!osB\u0019\u0011(\u0014!\n\u00059S$!D%oI\u0016DX\r\u001a\"vM\u001a,'\u000fE\u0003:!\u0002\u0013F+\u0003\u0002Ru\ti\u0011J\u001c3fq\u0016$7+Z9PaN\u0004\"a\u0015\u0001\u000e\u0003=\u00022a\u0015\u0001A!\u00151v\u000b\u0011*U\u001b\u0005Y\u0014B\u0001-<\u0005U\u0019FO]5di>\u0003H/[7ju\u0016$7+Z9PaN\u0004\"AW/\u000e\u0003mS!\u0001X\u001e\u0002\u000f\u001d,g.\u001a:jG&\u0011al\u0017\u0002\u0014\t\u00164\u0017-\u001e7u'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\t\u0005s\u0001\u0004%-\u0003\u0002bu\t9!)^5mI\u0016\u0014\bcA2l\u0001:\u0011A-\u001b\b\u0003K\"l\u0011A\u001a\u0006\u0003OV\na\u0001\u0010:p_Rt\u0014\"A\u001f\n\u0005)d\u0014a\u00029bG.\fw-Z\u0005\u0003Y6\u00141aU3r\u0015\tQG(\u0001\u0005dCB\f7-\u001b;z+\u0005\u0001\bC\u0001$r\u0013\t\u0011HHA\u0002J]R\f\u0011bY1qC\u000eLG/\u001f\u0011\u0002\rqJg.\u001b;?)\t!f\u000fC\u0003o\u0007\u0001\u0007\u0001/A\u0002ck\u001a,\u0012!\u001f\t\u0004si\u0004\u0015BA>;\u0005-\t%O]1z\u0005V4g-\u001a:\u0002\t\t,h\rI\u0001\u0007SN4U\u000f\u001c7\u0016\u0003}\u00042ARA\u0001\u0013\r\t\u0019\u0001\u0010\u0002\b\u0005>|G.Z1o\u0003%Ygn\\<o'&TX-\u0001\u0005ti\u0006\u0014H\u000fU8t\u00031\u0019H/\u0019:u!>\u001cx\fJ3r)\u0011\ti!a\u0005\u0011\u0007\u0019\u000by!C\u0002\u0002\u0012q\u0012A!\u00168ji\"A\u0011QC\u0005\u0002\u0002\u0003\u0007\u0001/A\u0002yIE\n\u0011b\u001d;beR\u0004vn\u001d\u0011\u0002\r\u0015tG\rU8t\u0003))g\u000e\u001a)pg~#S-\u001d\u000b\u0005\u0003\u001b\ty\u0002\u0003\u0005\u0002\u00161\t\t\u00111\u0001q\u0003\u001d)g\u000e\u001a)pg\u0002\nq\u0001\u001e:vK\u0016sG-A\u0003baBd\u0017\u0010F\u0002A\u0003SAa!a\u000b\u0010\u0001\u0004\u0001\u0018!\u00018\u0002\r1,gn\u001a;i\u0003\u0015Ig\u000eZ3y)\r\u0001\u00181\u0007\u0005\u0007\u0003k\t\u0002\u0019\u00019\u0002\u0003%\f1BY8v]\u0012\u001c8\t[3dWR1\u0011QBA\u001e\u0003{Aa!!\u000e\u0013\u0001\u0004\u0001\b\u0002CA %A\u0005\t\u0019A@\u0002\u0019%t7\r\\;tSZ,WI\u001c3\u0002+\t|WO\u001c3t\u0007\",7m\u001b\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u0011Q\t\u0016\u0004\u007f\u0006\u001d3FAA%!\u0011\tY%!\u0016\u000e\u0005\u00055#\u0002BA(\u0003#\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005MC(\u0001\u0006b]:|G/\u0019;j_:LA!a\u0016\u0002N\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\rU\u0004H-\u0019;f)\u0019\ti!!\u0018\u0002`!1\u00111\u0006\u000bA\u0002ADa!!\u0019\u0015\u0001\u0004\u0001\u0015!\u0001=\u0002\u000f\u0005$g/\u00198dKR\u0019\u0001/a\u001a\t\r\u0005%T\u00031\u0001q\u0003\r\u0001xn]\u0001\u0007e\u0016\u001cW\rZ3\u0015\u0007A\fy\u0007\u0003\u0004\u0002jY\u0001\r\u0001]\u0001\u0007C\u0012$wJ\\3\u0015\t\u0005U\u0014qO\u0007\u0002\u0001!1\u0011\u0011M\fA\u0002\u0001\u000bQa\u00197fCJ$\"!!\u0004\u0002\u000fA\u0014X\r]3oIR!\u0011QOAA\u0011\u0019\t\u0019)\u0007a\u0001\u0001\u0006!Q\r\\3n\u0003%Ign]3si\u0006cG\u000e\u0006\u0004\u0002\u000e\u0005%\u00151\u0012\u0005\u0007\u0003WQ\u0002\u0019\u00019\t\u000f\u00055%\u00041\u0001\u0002\u0010\u0006)Q\r\\3ngB!1-!%A\u0013\r\t\u0019*\u001c\u0002\r\u0013R,'/\u00192mK>s7-Z\u0001\u0007S:\u001cXM\u001d;\u0015\r\u00055\u0011\u0011TAO\u0011\u0019\tYj\u0007a\u0001a\u0006\u0019\u0011\u000e\u001a=\t\r\u0005\r5\u00041\u0001A\u0003\u0019\u0011X-\\8wKR1\u0011QBAR\u0003KCa!a\u000b\u001d\u0001\u0004\u0001\bBBAT9\u0001\u0007\u0001/A\u0003d_VtG\u000fF\u0002A\u0003WCa!a\u000b\u001e\u0001\u0004\u0001\u0018aC:vER\u0014\u0018m\u0019;P]\u0016$B!!\u001e\u00022\"1\u00111\u0011\u0010A\u0002\u0001\u000bQa\u00197p]\u0016$\u0012\u0001V\u0001\nG2\f7o\u001d(b[\u0016,\"!a/\u0011\t\u0005u\u0016Q\u0019\b\u0005\u0003\u007f\u000b\t\r\u0005\u0002fy%\u0019\u00111\u0019\u001f\u0002\rA\u0013X\rZ3g\u0013\u0011\t9-!3\u0003\rM#(/\u001b8h\u0015\r\t\u0019\rP\u0001\tSR,'/\u0019;peV\u0011\u0011q\u001a\t\u0005-\u0006E\u0007)C\u0002\u0002Tn\u0012\u0001\"\u0013;fe\u0006$xN]\u0001\fgR\fG/Z*ue&tw-\u0001\u0004sKN,H\u000e\u001e\u000b\u0002E\u0006aaM]8n'B,7-\u001b4jGR\u0019A+a8\t\u000f\u0005\u0005H\u00051\u0001\u0002d\u0006!1m\u001c7m!\u00111\u0016Q\u001d!\n\u0007\u0005M5(\u0001\noK^\u001c\u0006/Z2jM&\u001c')^5mI\u0016\u0014XCAAv!\u0011I\u0004\r\u0011+\u0002\u000b\u0015l\u0007\u000f^=\u0016\u0003Q\u000bq\"\u001b;fe\u0006\u0014G.\u001a$bGR|'/_\u000b\u0003\u0003k\u0004BAVA|%&\u0019\u0011\u0011`\u001e\u0003\u0015M+\u0017OR1di>\u0014\u00180\u0001\u0006SS:<')\u001e4gKJ\u0004\"aU\u0015\u0014\u000b%\u0012\tAa\u0002\u0011\u0007\u0019\u0013\u0019!C\u0002\u0003\u0006q\u0012a!\u00118z%\u00164\u0007\u0003\u0002B\u0005\u0005'i!Aa\u0003\u000b\t\t5!qB\u0001\u0003S>T!A!\u0005\u0002\t)\fg/Y\u0005\u0005\u0005+\u0011YA\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0006\u0002\u0002~V!!1\u0004B\u0012)\u0011\u0011iB!\f\u0015\t\t}!Q\u0005\t\u0005'\u0002\u0011\t\u0003E\u0002B\u0005G!QaQ\u0016C\u0002\u0011Cq!!$,\u0001\u0004\u00119\u0003E\u0003G\u0005S\u0011\t#C\u0002\u0003,q\u0012!\u0002\u0010:fa\u0016\fG/\u001a3?\u0011\u0015q7\u00061\u0001q\u00031\u0019\u0017M\u001c\"vS2$gI]8n+\u0019\u0011\u0019Da\u0010\u0003DU\u0011!Q\u0007\t\n-\n]\"1\bB!\u0005\u000fJ1A!\u000f<\u0005%\u0011U/\u001b7e\rJ|W\u000e\u0005\u0003T\u0001\tu\u0002cA!\u0003@\u0011)1\t\fb\u0001\tB\u0019\u0011Ia\u0011\u0005\r\t\u0015CF1\u0001E\u0005\u0005\u0011\u0005\u0003B*\u0001\u0005\u0003\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"A!\u0014\u0011\t\t=#QK\u0007\u0003\u0005#RAAa\u0015\u0003\u0010\u0005!A.\u00198h\u0013\u0011\u00119F!\u0015\u0003\r=\u0013'.Z2u\u0001"
)
public class RingBuffer extends AbstractBuffer implements IndexedBuffer, StrictOptimizedSeqOps, DefaultSerializable, Builder {
   private final int capacity;
   private final ArrayBuffer buf;
   private int startPos;
   private int endPos;

   public static BuildFrom canBuildFrom() {
      return RingBuffer$.MODULE$.canBuildFrom();
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

   public Object writeReplace() {
      return DefaultSerializable.writeReplace$(this);
   }

   public Object distinctBy(final Function1 f) {
      return StrictOptimizedSeqOps.distinctBy$(this, f);
   }

   public Object prepended(final Object elem) {
      return StrictOptimizedSeqOps.prepended$(this, elem);
   }

   public Object appended(final Object elem) {
      return StrictOptimizedSeqOps.appended$(this, elem);
   }

   public Object appendedAll(final IterableOnce suffix) {
      return StrictOptimizedSeqOps.appendedAll$(this, suffix);
   }

   public Object prependedAll(final IterableOnce prefix) {
      return StrictOptimizedSeqOps.prependedAll$(this, prefix);
   }

   public Object padTo(final int len, final Object elem) {
      return StrictOptimizedSeqOps.padTo$(this, len, elem);
   }

   public Object diff(final Seq that) {
      return StrictOptimizedSeqOps.diff$(this, that);
   }

   public Object intersect(final Seq that) {
      return StrictOptimizedSeqOps.intersect$(this, that);
   }

   public Tuple2 partition(final Function1 p) {
      return StrictOptimizedIterableOps.partition$(this, p);
   }

   public Tuple2 span(final Function1 p) {
      return StrictOptimizedIterableOps.span$(this, p);
   }

   public Tuple2 unzip(final Function1 asPair) {
      return StrictOptimizedIterableOps.unzip$(this, asPair);
   }

   public Tuple3 unzip3(final Function1 asTriple) {
      return StrictOptimizedIterableOps.unzip3$(this, asTriple);
   }

   public Object map(final Function1 f) {
      return StrictOptimizedIterableOps.map$(this, f);
   }

   public final Object strictOptimizedMap(final Builder b, final Function1 f) {
      return StrictOptimizedIterableOps.strictOptimizedMap$(this, b, f);
   }

   public Object flatMap(final Function1 f) {
      return StrictOptimizedIterableOps.flatMap$(this, f);
   }

   public final Object strictOptimizedFlatMap(final Builder b, final Function1 f) {
      return StrictOptimizedIterableOps.strictOptimizedFlatMap$(this, b, f);
   }

   public final Object strictOptimizedConcat(final IterableOnce that, final Builder b) {
      return StrictOptimizedIterableOps.strictOptimizedConcat$(this, that, b);
   }

   public Object collect(final PartialFunction pf) {
      return StrictOptimizedIterableOps.collect$(this, pf);
   }

   public final Object strictOptimizedCollect(final Builder b, final PartialFunction pf) {
      return StrictOptimizedIterableOps.strictOptimizedCollect$(this, b, pf);
   }

   public Object flatten(final Function1 toIterableOnce) {
      return StrictOptimizedIterableOps.flatten$(this, toIterableOnce);
   }

   public final Object strictOptimizedFlatten(final Builder b, final Function1 toIterableOnce) {
      return StrictOptimizedIterableOps.strictOptimizedFlatten$(this, b, toIterableOnce);
   }

   public Object zip(final IterableOnce that) {
      return StrictOptimizedIterableOps.zip$(this, that);
   }

   public final Object strictOptimizedZip(final IterableOnce that, final Builder b) {
      return StrictOptimizedIterableOps.strictOptimizedZip$(this, that, b);
   }

   public Object zipWithIndex() {
      return StrictOptimizedIterableOps.zipWithIndex$(this);
   }

   public Object scanLeft(final Object z, final Function2 op) {
      return StrictOptimizedIterableOps.scanLeft$(this, z, op);
   }

   public Object filter(final Function1 pred) {
      return StrictOptimizedIterableOps.filter$(this, pred);
   }

   public Object filterNot(final Function1 pred) {
      return StrictOptimizedIterableOps.filterNot$(this, pred);
   }

   public Object filterImpl(final Function1 pred, final boolean isFlipped) {
      return StrictOptimizedIterableOps.filterImpl$(this, pred, isFlipped);
   }

   public Tuple2 partitionMap(final Function1 f) {
      return StrictOptimizedIterableOps.partitionMap$(this, f);
   }

   public Object tapEach(final Function1 f) {
      return StrictOptimizedIterableOps.tapEach$(this, f);
   }

   public Object takeRight(final int n) {
      return StrictOptimizedIterableOps.takeRight$(this, n);
   }

   public Object dropRight(final int n) {
      return StrictOptimizedIterableOps.dropRight$(this, n);
   }

   public IndexedBuffer flatMapInPlace(final Function1 f) {
      return IndexedBuffer.flatMapInPlace$(this, f);
   }

   public IndexedBuffer filterInPlace(final Function1 p) {
      return IndexedBuffer.filterInPlace$(this, p);
   }

   public IndexedBuffer patchInPlace(final int from, final IterableOnce patch, final int replaced) {
      return IndexedBuffer.patchInPlace$(this, from, patch, replaced);
   }

   public IndexedSeqOps mapInPlace(final Function1 f) {
      return IndexedSeqOps.mapInPlace$(this, f);
   }

   public IndexedSeqOps sortInPlace(final Ordering ord) {
      return IndexedSeqOps.sortInPlace$(this, ord);
   }

   public IndexedSeqOps sortInPlaceWith(final Function2 lt) {
      return IndexedSeqOps.sortInPlaceWith$(this, lt);
   }

   public IndexedSeqOps sortInPlaceBy(final Function1 f, final Ordering ord) {
      return IndexedSeqOps.sortInPlaceBy$(this, f, ord);
   }

   public String stringPrefix() {
      return IndexedSeq.stringPrefix$(this);
   }

   public Stepper stepper(final StepperShape shape) {
      return scala.collection.IndexedSeqOps.stepper$(this, shape);
   }

   public Iterator reverseIterator() {
      return scala.collection.IndexedSeqOps.reverseIterator$(this);
   }

   public Object foldRight(final Object z, final Function2 op) {
      return scala.collection.IndexedSeqOps.foldRight$(this, z, op);
   }

   public IndexedSeqView view() {
      return scala.collection.IndexedSeqOps.view$(this);
   }

   /** @deprecated */
   public IndexedSeqView view(final int from, final int until) {
      return scala.collection.IndexedSeqOps.view$(this, from, until);
   }

   public Iterable reversed() {
      return scala.collection.IndexedSeqOps.reversed$(this);
   }

   public Object take(final int n) {
      return scala.collection.IndexedSeqOps.take$(this, n);
   }

   public Object drop(final int n) {
      return scala.collection.IndexedSeqOps.drop$(this, n);
   }

   public Object reverse() {
      return scala.collection.IndexedSeqOps.reverse$(this);
   }

   public Object slice(final int from, final int until) {
      return scala.collection.IndexedSeqOps.slice$(this, from, until);
   }

   public Object head() {
      return scala.collection.IndexedSeqOps.head$(this);
   }

   public Option headOption() {
      return scala.collection.IndexedSeqOps.headOption$(this);
   }

   public Object last() {
      return scala.collection.IndexedSeqOps.last$(this);
   }

   public final int lengthCompare(final int len) {
      return scala.collection.IndexedSeqOps.lengthCompare$(this, len);
   }

   public final int lengthCompare(final Iterable that) {
      return scala.collection.IndexedSeqOps.lengthCompare$(this, that);
   }

   public Searching.SearchResult search(final Object elem, final Ordering ord) {
      return scala.collection.IndexedSeqOps.search$(this, elem, ord);
   }

   public Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
      return scala.collection.IndexedSeqOps.search$(this, elem, from, to, ord);
   }

   public int capacity() {
      return this.capacity;
   }

   private ArrayBuffer buf() {
      return this.buf;
   }

   public boolean isFull() {
      return this.endPos() < 0;
   }

   public int knownSize() {
      return this.length();
   }

   private int startPos() {
      return this.startPos;
   }

   private void startPos_$eq(final int x$1) {
      this.startPos = x$1;
   }

   private int endPos() {
      return this.endPos;
   }

   private void endPos_$eq(final int x$1) {
      this.endPos = x$1;
   }

   private int trueEnd() {
      return this.isFull() ? this.startPos() : this.endPos();
   }

   public Object apply(final int n) {
      return this.buf().apply(this.index(n));
   }

   public int length() {
      return this.isFull() ? this.capacity() : (this.endPos() < this.startPos() ? this.capacity() + this.endPos() - this.startPos() : this.endPos() - this.startPos());
   }

   private int index(final int i) {
      this.boundsCheck(i, this.boundsCheck$default$2());
      return (i + this.startPos()) % this.capacity();
   }

   private void boundsCheck(final int i, final boolean inclusiveEnd) {
      if (i < 0 || i > this.length() || !inclusiveEnd && i == this.length()) {
         throw new IndexOutOfBoundsException((new StringBuilder(56)).append(i).append(" out of bounds for RingBuffer with length ").append(this.length()).append(" and capacity ").append(this.capacity()).toString());
      }
   }

   private boolean boundsCheck$default$2() {
      return false;
   }

   public void update(final int n, final Object x) {
      this.buf().update(this.index(n), x);
   }

   private int advance(final int pos) {
      return (pos + 1) % this.capacity();
   }

   private int recede(final int pos) {
      return pos - 1 < 0 ? this.capacity() - 1 : pos - 1;
   }

   public RingBuffer addOne(final Object x) {
      if (this.isFull()) {
         this.buf().update(this.startPos(), x);
         this.startPos_$eq(this.advance(this.startPos()));
      } else {
         this.buf().update(this.endPos(), x);
         this.endPos_$eq(this.advance(this.endPos()));
         if (this.endPos() == this.startPos()) {
            this.endPos_$eq(-1);
         }
      }

      return this;
   }

   public void clear() {
      this.startPos_$eq(0);
      this.endPos_$eq(0);
   }

   public RingBuffer prepend(final Object elem) {
      this.startPos_$eq(this.recede(this.startPos()));
      this.buf().update(this.startPos(), elem);
      if (this.endPos() == this.startPos()) {
         this.endPos_$eq(-1);
      }

      return this;
   }

   public void insertAll(final int n, final IterableOnce elems) {
      if (n == this.length()) {
         this.$plus$plus$eq(elems);
      } else {
         this.boundsCheck(n, this.boundsCheck$default$2());
         RingBuffer toInsertAfter = (RingBuffer)this.slice(n, this.length());
         this.dropRightInPlace(this.length() - n);
         this.$plus$plus$eq(elems);
         this.$plus$plus$eq(toInsertAfter);
      }

   }

   public void insert(final int idx, final Object elem) {
      this.insertAll(idx, .MODULE$.single(elem));
   }

   public void remove(final int n, final int count) {
      this.boundsCheck(n, true);
      this.boundsCheck(n + count, true);
      if (count != 0) {
         if (n + count == this.length()) {
            this.endPos_$eq((this.trueEnd() + this.capacity() - count) % this.capacity());
            scala.Predef..MODULE$.assert(this.length() == n);
         } else if (n == 0) {
            if (this.isFull()) {
               this.endPos_$eq(this.startPos());
            }

            this.startPos_$eq(this.startPos() + count);
            this.startPos_$eq(this.startPos() % this.capacity());
         } else {
            ArrayBuffer elements = (ArrayBuffer)this.to(scala.collection.IterableFactory..MODULE$.toFactory(scala.collection.mutable.ArrayBuffer..MODULE$));
            elements.remove(n, count);
            this.clear();
            this.$plus$plus$eq(elements);
         }
      }

   }

   public Object remove(final int n) {
      Object v = this.apply(n);
      this.remove(n, 1);
      return v;
   }

   public RingBuffer subtractOne(final Object elem) {
      int pos = this.indexOf(elem);
      if (pos >= 0) {
         this.remove(pos);
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return this;
   }

   public RingBuffer clone() {
      return (RingBuffer)(new RingBuffer(this.capacity())).$plus$plus$eq(this);
   }

   public String className() {
      return "RingBuffer";
   }

   public Iterator iterator() {
      return .MODULE$.range(0, this.length()).map((n) -> $anonfun$iterator$1(this, BoxesRunTime.unboxToInt(n)));
   }

   public String stateString() {
      scala.collection.mutable.StringBuilder out = new scala.collection.mutable.StringBuilder("RingBuffer(");
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.capacity()).foreach((i) -> $anonfun$stateString$1(this, out, BoxesRunTime.unboxToInt(i)));
      out.$plus$plus$eq(")");
      return out.toString();
   }

   public scala.collection.immutable.Seq result() {
      return this.iterator().toIndexedSeq();
   }

   public RingBuffer fromSpecific(final IterableOnce coll) {
      return (RingBuffer)(new RingBuffer(this.capacity())).$plus$plus$eq((IterableOnce)coll.iterator().to(scala.collection.IterableFactory..MODULE$.toFactory(scala.collection.mutable.ArrayBuffer..MODULE$)));
   }

   public Builder newSpecificBuilder() {
      return new GrowableBuilder(new RingBuffer(this.capacity()));
   }

   public RingBuffer empty() {
      return new RingBuffer(this.capacity());
   }

   public SeqFactory iterableFactory() {
      return new SeqFactory() {
         // $FF: synthetic field
         private final RingBuffer $outer;

         public final SeqOps unapplySeq(final SeqOps x) {
            return SeqFactory.unapplySeq$(this, x);
         }

         public Object apply(final scala.collection.immutable.Seq elems) {
            return IterableFactory.apply$(this, elems);
         }

         public Object iterate(final Object start, final int len, final Function1 f) {
            return IterableFactory.iterate$(this, start, len, f);
         }

         public Object unfold(final Object init, final Function1 f) {
            return IterableFactory.unfold$(this, init, f);
         }

         public Object range(final Object start, final Object end, final Integral evidence$3) {
            return IterableFactory.range$(this, start, end, evidence$3);
         }

         public Object range(final Object start, final Object end, final Object step, final Integral evidence$4) {
            return IterableFactory.range$(this, start, end, step, evidence$4);
         }

         public Object fill(final int n, final Function0 elem) {
            return IterableFactory.fill$(this, n, elem);
         }

         public Object fill(final int n1, final int n2, final Function0 elem) {
            return IterableFactory.fill$(this, n1, n2, elem);
         }

         public Object fill(final int n1, final int n2, final int n3, final Function0 elem) {
            return IterableFactory.fill$(this, n1, n2, n3, elem);
         }

         public Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
            return IterableFactory.fill$(this, n1, n2, n3, n4, elem);
         }

         public Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
            return IterableFactory.fill$(this, n1, n2, n3, n4, n5, elem);
         }

         public Object tabulate(final int n, final Function1 f) {
            return IterableFactory.tabulate$(this, n, f);
         }

         public Object tabulate(final int n1, final int n2, final Function2 f) {
            return IterableFactory.tabulate$(this, n1, n2, f);
         }

         public Object tabulate(final int n1, final int n2, final int n3, final Function3 f) {
            return IterableFactory.tabulate$(this, n1, n2, n3, f);
         }

         public Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
            return IterableFactory.tabulate$(this, n1, n2, n3, n4, f);
         }

         public Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
            return IterableFactory.tabulate$(this, n1, n2, n3, n4, n5, f);
         }

         public Object concat(final scala.collection.immutable.Seq xss) {
            return IterableFactory.concat$(this, xss);
         }

         public Factory iterableFactory() {
            return IterableFactory.iterableFactory$(this);
         }

         public Builder newBuilder() {
            return RingBuffer$.MODULE$.canBuildFrom().newBuilder(this.$outer);
         }

         public RingBuffer empty() {
            return (RingBuffer)this.newBuilder().result();
         }

         public RingBuffer from(final IterableOnce f) {
            return (RingBuffer)((Builder)this.newBuilder().$plus$plus$eq(f)).result();
         }

         public {
            if (RingBuffer.this == null) {
               throw null;
            } else {
               this.$outer = RingBuffer.this;
               IterableFactory.$init$(this);
               SeqFactory.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   public static final Object $anonfun$iterator$1(final RingBuffer $this, final int n) {
      return $this.apply(n);
   }

   // $FF: synthetic method
   public static final scala.collection.mutable.StringBuilder $anonfun$stateString$1(final RingBuffer $this, final scala.collection.mutable.StringBuilder out$1, final int i) {
      if (i != 0) {
         out$1.$plus$plus$eq(", ");
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      scala.collection.mutable.StringBuilder var5;
      if (i == $this.startPos() && $this.startPos() == $this.endPos()) {
         var5 = out$1.$plus$plus$eq("`'");
      } else {
         if (i == $this.trueEnd()) {
            out$1.$plus$plus$eq("'");
         } else {
            BoxedUnit var3 = BoxedUnit.UNIT;
         }

         if (i == $this.startPos()) {
            out$1.$plus$plus$eq("`");
         } else {
            BoxedUnit var4 = BoxedUnit.UNIT;
         }

         var5 = out$1.$plus$plus$eq($this.buf().apply(i).toString());
      }

      return var5;
   }

   public RingBuffer(final int capacity) {
      this.capacity = capacity;
      scala.collection.IndexedSeqOps.$init$(this);
      IndexedSeq.$init$(this);
      IndexedSeqOps.$init$(this);
      scala.collection.mutable.IndexedSeq.$init$(this);
      IndexedBuffer.$init$(this);
      StrictOptimizedIterableOps.$init$(this);
      StrictOptimizedSeqOps.$init$(this);
      DefaultSerializable.$init$(this);
      Builder.$init$(this);
      this.buf = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.fill(capacity, () -> null);
      this.startPos = 0;
      this.endPos = 0;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
