package breeze.collection.mutable;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableFactory;
import scala.collection.IterableFactoryDefaults;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.LazyZip2;
import scala.collection.Searching;
import scala.collection.SeqFactory;
import scala.collection.SeqView;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.View;
import scala.collection.WithFilter;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Range;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Cloneable;
import scala.collection.mutable.Map;
import scala.collection.mutable.Seq;
import scala.collection.mutable.SeqOps;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mg\u0001B\r\u001b\u0005\u0005B\u0001\"\u000e\u0001\u0003\u0006\u0004%\tA\u000e\u0005\tu\u0001\u0011\t\u0011)A\u0005o!A1\b\u0001B\u0002B\u0003-A\bC\u0003N\u0001\u0011\u0005a\nC\u0003U\u0001\u0011%a\u0007C\u0004V\u0001\t\u0007I\u0011\u0001,\t\ri\u0003\u0001\u0015!\u0003X\u0011\u0015Y\u0006\u0001\"\u0001]\u0011\u00151\u0007\u0001\"\u0001h\u0011\u00151\u0007\u0001\"\u0001o\u001119\b\u0001\"A\u0001\u0006\u0003\u0005\t\u0011\"\u0003y\u0011\u0015Q\b\u0001\"\u0001|\u0011\u001d\t\t\u0001\u0001C\u0001\u0003\u0007Aq!a\u0004\u0001\t\u0003\t\t\u0002C\u0004\u0002*\u0001!\t%a\u000b\b\u000f\u0005%#\u0004#\u0001\u0002L\u00191\u0011D\u0007E\u0001\u0003\u001bBa!T\t\u0005\u0002\u0005u\u0003bBA0#\u0011\u0005\u0011\u0011\r\u0005\b\u0003o\nB\u0011AAB\u0011\u001d\t\t+\u0005C\u0001\u0003GCq!a+\u0012\t\u0003\ti\u000bC\u0004\u0002FF!\t!a2\t\u0013\u0005-\u0017#!A\u0005\n\u00055'a\u0004+sS\u0006tw-\u001e7be\u0006\u0013(/Y=\u000b\u0005ma\u0012aB7vi\u0006\u0014G.\u001a\u0006\u0003;y\t!bY8mY\u0016\u001cG/[8o\u0015\u0005y\u0012A\u00022sK\u0016TXm\u0001\u0001\u0016\u0005\t\"5c\u0001\u0001$SA\u0011AeJ\u0007\u0002K)\ta%A\u0003tG\u0006d\u0017-\u0003\u0002)K\t1\u0011I\\=SK\u001a\u0004\"A\u000b\u001a\u000f\u0005-\u0002dB\u0001\u00170\u001b\u0005i#B\u0001\u0018!\u0003\u0019a$o\\8u}%\ta%\u0003\u00022K\u00059\u0001/Y2lC\u001e,\u0017BA\u001a5\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\tT%A\u0005eS6,gn]5p]V\tq\u0007\u0005\u0002%q%\u0011\u0011(\n\u0002\u0004\u0013:$\u0018A\u00033j[\u0016t7/[8oA\u0005QQM^5eK:\u001cW\rJ\u0019\u0011\u0007u\u0002%)D\u0001?\u0015\tyT%A\u0004sK\u001adWm\u0019;\n\u0005\u0005s$\u0001C\"mCN\u001cH+Y4\u0011\u0005\r#E\u0002\u0001\u0003\u0006\u000b\u0002\u0011\rA\u0012\u0002\u0002)F\u0011qI\u0013\t\u0003I!K!!S\u0013\u0003\u000f9{G\u000f[5oOB\u0011AeS\u0005\u0003\u0019\u0016\u00121!\u00118z\u0003\u0019a\u0014N\\5u}Q\u0011qj\u0015\u000b\u0003!J\u00032!\u0015\u0001C\u001b\u0005Q\u0002\"B\u001e\u0005\u0001\ba\u0004\"B\u001b\u0005\u0001\u00049\u0014\u0001\u00038v[\u0016cW-\\:\u0002\t\u0011\fG/Y\u000b\u0002/B\u0019A\u0005\u0017\"\n\u0005e+#!B!se\u0006L\u0018!\u00023bi\u0006\u0004\u0013AB;qI\u0006$X\r\u0006\u0003^A\n$\u0007C\u0001\u0013_\u0013\tyVE\u0001\u0003V]&$\b\"B1\t\u0001\u00049\u0014!\u0001:\t\u000b\rD\u0001\u0019A\u001c\u0002\u0003\rDQ!\u001a\u0005A\u0002\t\u000b\u0011\u0001^\u0001\u0006CB\u0004H.\u001f\u000b\u0004\u0005\"L\u0007\"B1\n\u0001\u00049\u0004\"B2\n\u0001\u00049\u0004FA\u0005l!\t!C.\u0003\u0002nK\t1\u0011N\u001c7j]\u0016$\"a\\;\u0011\u0007A\u001c()D\u0001r\u0015\tY\"O\u0003\u0002\u001eK%\u0011A/\u001d\u0002\u0004'\u0016\f\b\"B1\u000b\u0001\u00049\u0004F\u0001\u0006l\u0003A\u0012'/Z3{K\u0012\u001aw\u000e\u001c7fGRLwN\u001c\u0013nkR\f'\r\\3%)JL\u0017M\\4vY\u0006\u0014\u0018I\u001d:bs\u0012\"3\u000f\\5dKR\u0011q.\u001f\u0005\u0006C.\u0001\raN\u0001\tSR,'/\u0019;peV\tA\u0010E\u0002~}>l\u0011A]\u0005\u0003\u007fJ\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\bM>\u0014X-Y2i)\ri\u0016Q\u0001\u0005\b\u0003\u000fi\u0001\u0019AA\u0005\u0003\u00051\u0007#\u0002\u0013\u0002\f\tk\u0016bAA\u0007K\tIa)\u001e8di&|g.M\u0001\u0004[\u0006\u0004X\u0003BA\n\u00037!B!!\u0006\u0002&Q!\u0011qCA\u0010!\u0011\t\u0006!!\u0007\u0011\u0007\r\u000bY\u0002\u0002\u0004\u0002\u001e9\u0011\rA\u0012\u0002\u0002+\"I\u0011\u0011\u0005\b\u0002\u0002\u0003\u000f\u00111E\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004\u0003B\u001fA\u00033Aq!a\u0002\u000f\u0001\u0004\t9\u0003\u0005\u0004%\u0003\u0017\u0011\u0015\u0011D\u0001\ti>\u001cFO]5oOR\u0011\u0011Q\u0006\t\u0005\u0003_\t9D\u0004\u0003\u00022\u0005M\u0002C\u0001\u0017&\u0013\r\t)$J\u0001\u0007!J,G-\u001a4\n\t\u0005e\u00121\b\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0005UR\u0005K\u0004\u0001\u0003\u007f\t)%a\u0012\u0011\u0007\u0011\n\t%C\u0002\u0002D\u0015\u0012\u0001cU3sS\u0006dg+\u001a:tS>tW+\u0013#\u0002\u000bY\fG.^3\u001f\u0003\u0005\tq\u0002\u0016:jC:<W\u000f\\1s\u0003J\u0014\u0018-\u001f\t\u0003#F\u0019B!E\u0012\u0002PA!\u0011\u0011KA.\u001b\t\t\u0019F\u0003\u0003\u0002V\u0005]\u0013AA5p\u0015\t\tI&\u0001\u0003kCZ\f\u0017bA\u001a\u0002TQ\u0011\u00111J\u0001\ti\u0006\u0014W\u000f\\1uKV!\u00111MA7)\u0011\t)'a \u0015\t\u0005\u001d\u0014Q\u000f\u000b\u0005\u0003S\ny\u0007\u0005\u0003R\u0001\u0005-\u0004cA\"\u0002n\u0011)Qi\u0005b\u0001\r\"I\u0011\u0011O\n\u0002\u0002\u0003\u000f\u00111O\u0001\u000bKZLG-\u001a8dK\u0012\u001a\u0004\u0003B\u001fA\u0003WBq!a\u001e\u0014\u0001\u0004\tI(\u0001\u0003gS2d\u0007c\u0002\u0013\u0002|]:\u00141N\u0005\u0004\u0003{*#!\u0003$v]\u000e$\u0018n\u001c83\u0011\u0019\t\ti\u0005a\u0001o\u0005\u0019A-[7\u0016\t\u0005\u0015\u0015q\u0012\u000b\u0005\u0003\u000f\u000by\n\u0006\u0003\u0002\n\u0006]E\u0003BAF\u0003#\u0003B!\u0015\u0001\u0002\u000eB\u00191)a$\u0005\u000b\u0015#\"\u0019\u0001$\t\u0013\u0005ME#!AA\u0004\u0005U\u0015AC3wS\u0012,gnY3%iA!Q\bQAG\u0011!\t9\b\u0006CA\u0002\u0005e\u0005#\u0002\u0013\u0002\u001c\u00065\u0015bAAOK\tAAHY=oC6,g\b\u0003\u0004\u0002\u0002R\u0001\raN\u0001\u0006S:$W\r\u001f\u000b\u0006o\u0005\u0015\u0016q\u0015\u0005\u0006CV\u0001\ra\u000e\u0005\u0006GV\u0001\ra\u000e\u0015\u0003+-\f1A]1x+\u0011\ty+a.\u0015\r\u0005E\u0016qXAa)\u0011\t\u0019,!/\u0011\t\u0011B\u0016Q\u0017\t\u0004\u0007\u0006]F!B#\u0017\u0005\u00041\u0005\"CA^-\u0005\u0005\t9AA_\u0003))g/\u001b3f]\u000e,G%\u000e\t\u0005{\u0001\u000b)\f\u0003\u0004\u0002\u0002Z\u0001\ra\u000e\u0005\t\u0003o2B\u00111\u0001\u0002DB)A%a'\u00026\u0006I\u0011M\u001d:bsNK'0\u001a\u000b\u0004o\u0005%\u0007BBAA/\u0001\u0007q'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002PB!\u0011\u0011[Al\u001b\t\t\u0019N\u0003\u0003\u0002V\u0006]\u0013\u0001\u00027b]\u001eLA!!7\u0002T\n1qJ\u00196fGR\u0004"
)
public final class TriangularArray implements Serializable {
   private static final long serialVersionUID = 1L;
   private final int dimension;
   private final Object data;

   public static int arraySize(final int dim) {
      return TriangularArray$.MODULE$.arraySize(dim);
   }

   public static Object raw(final int dim, final Function0 fill, final ClassTag evidence$5) {
      return TriangularArray$.MODULE$.raw(dim, fill, evidence$5);
   }

   public static int index(final int r, final int c) {
      return TriangularArray$.MODULE$.index(r, c);
   }

   public static TriangularArray fill(final int dim, final Function0 fill, final ClassTag evidence$4) {
      return TriangularArray$.MODULE$.fill(dim, fill, evidence$4);
   }

   public static TriangularArray tabulate(final int dim, final Function2 fill, final ClassTag evidence$3) {
      return TriangularArray$.MODULE$.tabulate(dim, fill, evidence$3);
   }

   public int dimension() {
      return this.dimension;
   }

   private int numElems() {
      return this.dimension() * (this.dimension() + 1) / 2;
   }

   public Object data() {
      return this.data;
   }

   public void update(final int r, final int c, final Object t) {
      .MODULE$.array_update(this.data(), TriangularArray$.MODULE$.index(r, c), t);
   }

   public Object apply(final int r, final int c) {
      return .MODULE$.array_apply(this.data(), TriangularArray$.MODULE$.index(r, c));
   }

   public Seq apply(final int r) {
      return this.breeze$collection$mutable$TriangularArray$$slice(r);
   }

   public Seq breeze$collection$mutable$TriangularArray$$slice(final int r) {
      return new Seq(r) {
         // $FF: synthetic field
         private final TriangularArray $outer;
         private final int r$1;

         public SeqFactory iterableFactory() {
            return Seq.iterableFactory$(this);
         }

         public Object clone() {
            return SeqOps.clone$(this);
         }

         /** @deprecated */
         public final SeqOps transform(final Function1 f) {
            return SeqOps.transform$(this, f);
         }

         // $FF: synthetic method
         public Object scala$collection$mutable$Cloneable$$super$clone() {
            return super.clone();
         }

         public boolean canEqual(final Object that) {
            return scala.collection.Seq.canEqual$(this, that);
         }

         public boolean equals(final Object o) {
            return scala.collection.Seq.equals$(this, o);
         }

         public int hashCode() {
            return scala.collection.Seq.hashCode$(this);
         }

         public String toString() {
            return scala.collection.Seq.toString$(this);
         }

         public String stringPrefix() {
            return scala.collection.Seq.stringPrefix$(this);
         }

         // $FF: synthetic method
         public Object scala$collection$SeqOps$$super$concat(final IterableOnce suffix) {
            return IterableOps.concat$(this, suffix);
         }

         // $FF: synthetic method
         public int scala$collection$SeqOps$$super$sizeCompare(final int otherSize) {
            return IterableOps.sizeCompare$(this, otherSize);
         }

         // $FF: synthetic method
         public int scala$collection$SeqOps$$super$sizeCompare(final Iterable that) {
            return IterableOps.sizeCompare$(this, that);
         }

         public SeqView view() {
            return scala.collection.SeqOps.view$(this);
         }

         public Object prepended(final Object elem) {
            return scala.collection.SeqOps.prepended$(this, elem);
         }

         public final Object $plus$colon(final Object elem) {
            return scala.collection.SeqOps.$plus$colon$(this, elem);
         }

         public Object appended(final Object elem) {
            return scala.collection.SeqOps.appended$(this, elem);
         }

         public final Object $colon$plus(final Object elem) {
            return scala.collection.SeqOps.$colon$plus$(this, elem);
         }

         public Object prependedAll(final IterableOnce prefix) {
            return scala.collection.SeqOps.prependedAll$(this, prefix);
         }

         public final Object $plus$plus$colon(final IterableOnce prefix) {
            return scala.collection.SeqOps.$plus$plus$colon$(this, prefix);
         }

         public Object appendedAll(final IterableOnce suffix) {
            return scala.collection.SeqOps.appendedAll$(this, suffix);
         }

         public final Object $colon$plus$plus(final IterableOnce suffix) {
            return scala.collection.SeqOps.$colon$plus$plus$(this, suffix);
         }

         public final Object concat(final IterableOnce suffix) {
            return scala.collection.SeqOps.concat$(this, suffix);
         }

         /** @deprecated */
         public final Object union(final scala.collection.Seq that) {
            return scala.collection.SeqOps.union$(this, that);
         }

         public final int size() {
            return scala.collection.SeqOps.size$(this);
         }

         public Object distinct() {
            return scala.collection.SeqOps.distinct$(this);
         }

         public Object distinctBy(final Function1 f) {
            return scala.collection.SeqOps.distinctBy$(this, f);
         }

         public Object reverse() {
            return scala.collection.SeqOps.reverse$(this);
         }

         public Iterator reverseIterator() {
            return scala.collection.SeqOps.reverseIterator$(this);
         }

         public boolean startsWith(final IterableOnce that, final int offset) {
            return scala.collection.SeqOps.startsWith$(this, that, offset);
         }

         public int startsWith$default$2() {
            return scala.collection.SeqOps.startsWith$default$2$(this);
         }

         public boolean endsWith(final Iterable that) {
            return scala.collection.SeqOps.endsWith$(this, that);
         }

         public boolean isDefinedAt(final int idx) {
            return scala.collection.SeqOps.isDefinedAt$(this, idx);
         }

         public Object padTo(final int len, final Object elem) {
            return scala.collection.SeqOps.padTo$(this, len, elem);
         }

         public final int segmentLength(final Function1 p) {
            return scala.collection.SeqOps.segmentLength$(this, p);
         }

         public int segmentLength(final Function1 p, final int from) {
            return scala.collection.SeqOps.segmentLength$(this, p, from);
         }

         /** @deprecated */
         public final int prefixLength(final Function1 p) {
            return scala.collection.SeqOps.prefixLength$(this, p);
         }

         public int indexWhere(final Function1 p, final int from) {
            return scala.collection.SeqOps.indexWhere$(this, p, from);
         }

         public int indexWhere(final Function1 p) {
            return scala.collection.SeqOps.indexWhere$(this, p);
         }

         public int indexOf(final Object elem, final int from) {
            return scala.collection.SeqOps.indexOf$(this, elem, from);
         }

         public int indexOf(final Object elem) {
            return scala.collection.SeqOps.indexOf$(this, elem);
         }

         public int lastIndexOf(final Object elem, final int end) {
            return scala.collection.SeqOps.lastIndexOf$(this, elem, end);
         }

         public int lastIndexOf$default$2() {
            return scala.collection.SeqOps.lastIndexOf$default$2$(this);
         }

         public int lastIndexWhere(final Function1 p, final int end) {
            return scala.collection.SeqOps.lastIndexWhere$(this, p, end);
         }

         public int lastIndexWhere(final Function1 p) {
            return scala.collection.SeqOps.lastIndexWhere$(this, p);
         }

         public int indexOfSlice(final scala.collection.Seq that, final int from) {
            return scala.collection.SeqOps.indexOfSlice$(this, that, from);
         }

         public int indexOfSlice(final scala.collection.Seq that) {
            return scala.collection.SeqOps.indexOfSlice$(this, that);
         }

         public int lastIndexOfSlice(final scala.collection.Seq that, final int end) {
            return scala.collection.SeqOps.lastIndexOfSlice$(this, that, end);
         }

         public int lastIndexOfSlice(final scala.collection.Seq that) {
            return scala.collection.SeqOps.lastIndexOfSlice$(this, that);
         }

         public Option findLast(final Function1 p) {
            return scala.collection.SeqOps.findLast$(this, p);
         }

         public boolean containsSlice(final scala.collection.Seq that) {
            return scala.collection.SeqOps.containsSlice$(this, that);
         }

         public boolean contains(final Object elem) {
            return scala.collection.SeqOps.contains$(this, elem);
         }

         /** @deprecated */
         public Object reverseMap(final Function1 f) {
            return scala.collection.SeqOps.reverseMap$(this, f);
         }

         public Iterator permutations() {
            return scala.collection.SeqOps.permutations$(this);
         }

         public Iterator combinations(final int n) {
            return scala.collection.SeqOps.combinations$(this, n);
         }

         public Object sorted(final Ordering ord) {
            return scala.collection.SeqOps.sorted$(this, ord);
         }

         public Object sortWith(final Function2 lt) {
            return scala.collection.SeqOps.sortWith$(this, lt);
         }

         public Object sortBy(final Function1 f, final Ordering ord) {
            return scala.collection.SeqOps.sortBy$(this, f, ord);
         }

         public Range indices() {
            return scala.collection.SeqOps.indices$(this);
         }

         public final int sizeCompare(final int otherSize) {
            return scala.collection.SeqOps.sizeCompare$(this, otherSize);
         }

         public int lengthCompare(final int len) {
            return scala.collection.SeqOps.lengthCompare$(this, len);
         }

         public final int sizeCompare(final Iterable that) {
            return scala.collection.SeqOps.sizeCompare$(this, that);
         }

         public int lengthCompare(final Iterable that) {
            return scala.collection.SeqOps.lengthCompare$(this, that);
         }

         public final IterableOps lengthIs() {
            return scala.collection.SeqOps.lengthIs$(this);
         }

         public boolean isEmpty() {
            return scala.collection.SeqOps.isEmpty$(this);
         }

         public boolean sameElements(final IterableOnce that) {
            return scala.collection.SeqOps.sameElements$(this, that);
         }

         public boolean corresponds(final scala.collection.Seq that, final Function2 p) {
            return scala.collection.SeqOps.corresponds$(this, that, p);
         }

         public Object diff(final scala.collection.Seq that) {
            return scala.collection.SeqOps.diff$(this, that);
         }

         public Object intersect(final scala.collection.Seq that) {
            return scala.collection.SeqOps.intersect$(this, that);
         }

         public Object patch(final int from, final IterableOnce other, final int replaced) {
            return scala.collection.SeqOps.patch$(this, from, other, replaced);
         }

         public Object updated(final int index, final Object elem) {
            return scala.collection.SeqOps.updated$(this, index, elem);
         }

         public Map occCounts(final scala.collection.Seq sq) {
            return scala.collection.SeqOps.occCounts$(this, sq);
         }

         public Searching.SearchResult search(final Object elem, final Ordering ord) {
            return scala.collection.SeqOps.search$(this, elem, ord);
         }

         public Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
            return scala.collection.SeqOps.search$(this, elem, from, to, ord);
         }

         public Option unapply(final Object a) {
            return PartialFunction.unapply$(this, a);
         }

         public PartialFunction elementWise() {
            return PartialFunction.elementWise$(this);
         }

         public PartialFunction orElse(final PartialFunction that) {
            return PartialFunction.orElse$(this, that);
         }

         public PartialFunction andThen(final Function1 k) {
            return PartialFunction.andThen$(this, k);
         }

         public PartialFunction andThen(final PartialFunction k) {
            return PartialFunction.andThen$(this, k);
         }

         public PartialFunction compose(final PartialFunction k) {
            return PartialFunction.compose$(this, k);
         }

         public Function1 lift() {
            return PartialFunction.lift$(this);
         }

         public Object applyOrElse(final Object x, final Function1 default) {
            return PartialFunction.applyOrElse$(this, x, default);
         }

         public Function1 runWith(final Function1 action) {
            return PartialFunction.runWith$(this, action);
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

         /** @deprecated */
         public final Iterable toIterable() {
            return Iterable.toIterable$(this);
         }

         public final Iterable coll() {
            return Iterable.coll$(this);
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

         public LazyZip2 lazyZip(final Iterable that) {
            return Iterable.lazyZip$(this, that);
         }

         public IterableOps fromSpecific(final IterableOnce coll) {
            return IterableFactoryDefaults.fromSpecific$(this, coll);
         }

         public Builder newSpecificBuilder() {
            return IterableFactoryDefaults.newSpecificBuilder$(this);
         }

         public IterableOps empty() {
            return IterableFactoryDefaults.empty$(this);
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

         public final IterableOps sizeIs() {
            return IterableOps.sizeIs$(this);
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

         public scala.collection.immutable.Map groupBy(final Function1 f) {
            return IterableOps.groupBy$(this, f);
         }

         public scala.collection.immutable.Map groupMap(final Function1 key, final Function1 f) {
            return IterableOps.groupMap$(this, key, f);
         }

         public scala.collection.immutable.Map groupMapReduce(final Function1 key, final Function1 f, final Function2 reduce) {
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

         public scala.collection.immutable.Map toMap(final scala..less.colon.less ev) {
            return IterableOnceOps.toMap$(this, ev);
         }

         public Set toSet() {
            return IterableOnceOps.toSet$(this);
         }

         public scala.collection.immutable.Seq toSeq() {
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

         public Object apply(final int c) {
            return this.$outer.apply(this.r$1, c);
         }

         public void update(final int c, final Object t) {
            this.$outer.update(this.r$1, c, t);
         }

         public int length() {
            return this.$outer.dimension() - this.r$1;
         }

         public Iterator iterator() {
            return scala.package..MODULE$.Iterator().range(this.r$1, this.$outer.dimension()).map((c) -> $anonfun$iterator$1(this, BoxesRunTime.unboxToInt(c)));
         }

         // $FF: synthetic method
         public static final Object $anonfun$iterator$1(final Object $this, final int c) {
            return $this.apply(c);
         }

         public {
            if (TriangularArray.this == null) {
               throw null;
            } else {
               this.$outer = TriangularArray.this;
               this.r$1 = r$1;
               IterableOnce.$init$(this);
               IterableOnceOps.$init$(this);
               IterableOps.$init$(this);
               IterableFactoryDefaults.$init$(this);
               Iterable.$init$(this);
               scala.collection.mutable.Iterable.$init$(this);
               Function1.$init$(this);
               PartialFunction.$init$(this);
               scala.collection.SeqOps.$init$(this);
               scala.collection.Seq.$init$(this);
               Cloneable.$init$(this);
               SeqOps.$init$(this);
               Seq.$init$(this);
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public Iterator iterator() {
      return scala.package..MODULE$.Iterator().range(0, this.numElems()).map((r) -> $anonfun$iterator$2(this, BoxesRunTime.unboxToInt(r)));
   }

   public void foreach(final Function1 f) {
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.genericArrayOps(this.data()), f);
   }

   public TriangularArray map(final Function1 f, final ClassTag evidence$2) {
      return TriangularArray$.MODULE$.tabulate(this.dimension(), (i, j) -> $anonfun$map$1(this, f, BoxesRunTime.unboxToInt(i), BoxesRunTime.unboxToInt(j)), evidence$2);
   }

   public String toString() {
      StringBuilder buffer = new StringBuilder();
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.dimension()).foreach((r) -> $anonfun$toString$1(this, buffer, BoxesRunTime.unboxToInt(r)));
      return buffer.toString();
   }

   // $FF: synthetic method
   public static final Seq $anonfun$iterator$2(final TriangularArray $this, final int r) {
      return $this.breeze$collection$mutable$TriangularArray$$slice(r);
   }

   // $FF: synthetic method
   public static final Object $anonfun$map$1(final TriangularArray $this, final Function1 f$1, final int i, final int j) {
      return f$1.apply($this.apply(i, j));
   }

   // $FF: synthetic method
   public static final String $anonfun$toString$2(final TriangularArray $this, final int r$2, final int c) {
      return c <= r$2 ? "----" : (String)scala.Option..MODULE$.apply($this.apply(r$2, c)).map((x$1) -> x$1.toString()).getOrElse(() -> "null");
   }

   // $FF: synthetic method
   public static final StringBuilder $anonfun$toString$1(final TriangularArray $this, final StringBuilder buffer$1, final int r) {
      IndexedSeq columns = scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), $this.dimension()).map((c) -> $anonfun$toString$2($this, r, BoxesRunTime.unboxToInt(c)));
      return buffer$1.$plus$plus$eq(columns.mkString("[", ", ", "]\n"));
   }

   public TriangularArray(final int dimension, final ClassTag evidence$1) {
      this.dimension = dimension;
      this.data = evidence$1.newArray(this.numElems());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
