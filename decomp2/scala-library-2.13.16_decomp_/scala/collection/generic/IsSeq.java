package scala.collection.generic;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Array;
import scala.$less$colon$less;
import scala.Array$;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.ArrayOps$;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.Searching;
import scala.collection.Seq;
import scala.collection.SeqOps;
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
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.ArrayBuilder$;
import scala.collection.mutable.ArraySeq$;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Map;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime$;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dgaB\t\u0013!\u0003\r\t!\u0007\u0005\u0006]\u0001!\ta\f\u0005\bg\u0001\u0011\r\u0011\"\u00115\u0011\u0015\u0011\u0006A\"\u0001T\u000f\u00151&\u0003#\u0001X\r\u0015\t\"\u0003#\u0001Y\u0011\u0015IV\u0001\"\u0001[\u0011\u001dYVA1A\u0005\nqCa!Y\u0003!\u0002\u0013i\u0006\"\u00022\u0006\t\u0007\u0019\u0007\"\u0002=\u0006\t\u0007I\b\"CA\u000f\u000b\t\u0007I1AA\u0010\u0011!\t9%\u0002Q\u0001\n\u0005\u0005\u0002\"CA%\u000b\t\u0007I1AA&\u0011!\ty&\u0002Q\u0001\n\u00055\u0003bBA1\u000b\u0011\r\u00111\r\u0005\b\u0003\u0017+A1AAG\u0005\u0015I5oU3r\u0015\t\u0019B#A\u0004hK:,'/[2\u000b\u0005U1\u0012AC2pY2,7\r^5p]*\tq#A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u0005i)3c\u0001\u0001\u001c?A\u0011A$H\u0007\u0002-%\u0011aD\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0007\u0001\n3%D\u0001\u0013\u0013\t\u0011#C\u0001\u0006Jg&#XM]1cY\u0016\u0004\"\u0001J\u0013\r\u0001\u0011)a\u0005\u0001b\u0001O\t!!+\u001a9s#\tA3\u0006\u0005\u0002\u001dS%\u0011!F\u0006\u0002\b\u001d>$\b.\u001b8h!\taB&\u0003\u0002.-\t\u0019\u0011I\\=\u0002\r\u0011Jg.\u001b;%)\u0005\u0001\u0004C\u0001\u000f2\u0013\t\u0011dC\u0001\u0003V]&$\u0018AC2p]Z,'o]5p]V\tQ\u0007\u0005\u0003\u001dm\rB\u0014BA\u001c\u0017\u0005%1UO\\2uS>t\u0017\u0007E\u0003:uq\u0012U)D\u0001\u0015\u0013\tYDC\u0001\u0004TKF|\u0005o\u001d\t\u0003{yj\u0011\u0001A\u0005\u0003\u007f\u0001\u0013\u0011!Q\u0005\u0003\u0003J\u0011a\"S:Ji\u0016\u0014\u0018M\u00197f\u001f:\u001cW\r\u0005\u0002:\u0007&\u0011A\t\u0006\u0002\t\u0013R,'/\u00192mKB\u0011QHR\u0005\u0003\u000f\u0006\u0012\u0011a\u0011\u0015\u0007\u0005%cUj\u0014)\u0011\u0005qQ\u0015BA&\u0017\u0005)!W\r\u001d:fG\u0006$X\rZ\u0001\b[\u0016\u001c8/Y4fC\u0005q\u0015AK\u0014d_:4XM]:j_:<\u0003%[:!]><\b%\u0019\u0011nKRDw\u000e\u001a\u0011oC6,G\rI\u0014baBd\u0017pJ\u0001\u0006g&t7-Z\u0011\u0002#\u00061!GL\u00194]A\nQ!\u00199qYf$\"\u0001\u000f+\t\u000bU\u001b\u0001\u0019A\u0012\u0002\t\r|G\u000e\\\u0001\u0006\u0013N\u001cV-\u001d\t\u0003A\u0015\u0019\"!B\u000e\u0002\rqJg.\u001b;?)\u00059\u0016AD:fc>\u00038/S:TKF4\u0016\r\\\u000b\u0002;B\u0019\u0001\u0005\u00010\u0011\u0007ez6&\u0003\u0002a)\t\u00191+Z9\u0002\u001fM,\u0017o\u00149t\u0013N\u001cV-\u001d,bY\u0002\n1b]3r\u001fB\u001c\u0018j]*fcV\u0019AM\u001b;\u0016\u0003\u0015\u0014\"A\u001a5\u0007\t\u001d,\u0001!\u001a\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u0004A\u0001I\u0007c\u0001\u0013kg\u0012)1.\u0003b\u0001Y\n\u00191i\u0011\u0019\u0016\u00055\u0004\u0018C\u0001\u0015o!\u0015I$h\u001c\"s!\t!\u0003\u000fB\u0003rU\n\u0007qEA\u0001Y!\r!#n\u001c\t\u0003IQ$Q!^\u0005C\u0002\u001d\u0012!!\u0011\u0019\u0006\t}2\u0007a]\u0003\u0005\u000f\u001a\u0004\u0011.\u0001\u0007tKF4\u0016.Z<JgN+\u0017/\u0006\u0003{\u007f\u0006EQ#A>\u0013\u0005qlh\u0001B4\u0006\u0001m\u00042\u0001\t\u0001\u007f!\u0011!s0a\u0004\u0005\r-T!\u0019AA\u0001+\u0011\t\u0019!!\u0004\u0012\u0007!\n)\u0001E\u0003:\u0003\u000f\tY!C\u0002\u0002\nQ\u0011qaU3r-&,w\u000fE\u0002%\u0003\u001b!Q!]@C\u0002\u001d\u00022\u0001JA\t\t\u0015)(B1\u0001(\u000b\u0015yD\u0010AA\b\u000b\u00159E\u0010AA\f!\u0015I\u0014\u0011DA\b\u0013\r\tY\u0002\u0006\u0002\u0005-&,w/A\u0006tiJLgnZ%t'\u0016\fXCAA\u0011%\u0011\t\u0019#!\n\u0007\u000b\u001d,\u0001!!\t\u0011\t\u0001\u0002\u0011q\u0005\t\u0005\u0003S\t9D\u0004\u0003\u0002,\u0005M\u0002cAA\u0017-5\u0011\u0011q\u0006\u0006\u0004\u0003cA\u0012A\u0002\u001fs_>$h(C\u0002\u00026Y\ta\u0001\u0015:fI\u00164\u0017\u0002BA\u001d\u0003w\u0011aa\u0015;sS:<'bAA\u001b-\u00151q(a\t!\u0003\u007f\u00012\u0001HA!\u0013\r\t\u0019E\u0006\u0002\u0005\u0007\"\f'/\u0002\u0004H\u0003G\u0001\u0013qE\u0001\rgR\u0014\u0018N\\4JgN+\u0017\u000fI\u0001\u0010gR\u0014\u0018N\\4WS\u0016<\u0018j]*fcV\u0011\u0011Q\n\n\u0005\u0003\u001f\n\tFB\u0003h\u000b\u0001\ti\u0005\u0005\u0003!\u0001\u0005M\u0003cA\u001d\u0002V%\u0019\u0011q\u000b\u000b\u0003\u0015M#(/\u001b8h-&,w/\u0002\u0004@\u0003\u001f\u0002\u0013qH\u0003\u0007\u000f\u0006=\u0003%!\u0018\u0011\u000be\nI\"a\u0010\u0002!M$(/\u001b8h-&,w/S:TKF\u0004\u0013AC1se\u0006L\u0018j]*fcV!\u0011QMA;)\u0011\t9'a\u001f\u0013\t\u0005%\u00141\u000e\u0004\u0006O\u0016\u0001\u0011q\r\t\u0005A\u0001\ti\u0007E\u0003\u001d\u0003_\n\u0019(C\u0002\u0002rY\u0011Q!\u0011:sCf\u00042\u0001JA;\t\u0015)xB1\u0001(\u000b\u0019y\u0014\u0011\u000e\u0001\u0002t\u00151q)!\u001b\u0001\u0003[B\u0011\"! \u0010\u0003\u0003\u0005\u001d!a \u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u0005\u0004\u0002\u0002\u0006\u001d\u00151O\u0007\u0003\u0003\u0007S1!!\"\u0017\u0003\u001d\u0011XM\u001a7fGRLA!!#\u0002\u0004\nA1\t\\1tgR\u000bw-\u0001\u0006sC:<W-S:TKF,B!a$\u0002\u001aV\u0011\u0011\u0011\u0013\n\u0005\u0003'\u000b)JB\u0003h\u000b\u0001\t\t\n\u0005\u0003!\u0001\u0005]\u0005c\u0001\u0013\u0002\u001a\u00129\u00111\u0014\tC\u0002\u0005u%AA\"1#\rA\u0013q\u0014\t\u0005\u0003C\u000bYK\u0004\u0003\u0002$\u0006\u001df\u0002BA\u0017\u0003KK\u0011aF\u0005\u0004\u0003S3\u0012a\u00029bG.\fw-Z\u0005\u0005\u0003[\u000byKA\u0003SC:<WMC\u0002\u0002*Z)aaPAJ\u0001\u0005M\u0006c\u0001\u000f\u00026&\u0019\u0011q\u0017\f\u0003\u0007%sG/\u0002\u0004H\u0003'\u0003\u00111\u0018\t\u0007\u0003{\u000b\u0019-a-\u000e\u0005\u0005}&bAAa)\u0005I\u0011.\\7vi\u0006\u0014G.Z\u0005\u0005\u0003\u000b\fyL\u0001\u0006J]\u0012,\u00070\u001a3TKF\u0004"
)
public interface IsSeq extends IsIterable {
   static IsSeq rangeIsSeq() {
      IsSeq$ var10000 = IsSeq$.MODULE$;
      return new IsSeq() {
         /** @deprecated */
         private Function1 conversion;

         /** @deprecated */
         public Function1 conversion() {
            return this.conversion;
         }

         public void scala$collection$generic$IsSeq$_setter_$conversion_$eq(final Function1 x$1) {
            this.conversion = x$1;
         }

         public void scala$collection$generic$IsIterable$_setter_$conversion_$eq(final Function1 x$1) {
         }

         public void scala$collection$generic$IsIterableOnce$_setter_$conversion_$eq(final Function1 x$1) {
         }

         public SeqOps apply(final Range coll) {
            return coll;
         }

         public {
            IsIterableOnce.$init$(this);
            IsIterable.$init$(this);
            IsSeq.$init$(this);
            Statics.releaseFence();
         }
      };
   }

   static IsSeq arrayIsSeq(final ClassTag evidence$1) {
      IsSeq$ var10000 = IsSeq$.MODULE$;
      return new IsSeq(evidence$1) {
         /** @deprecated */
         private Function1 conversion;
         public final ClassTag evidence$1$1;

         /** @deprecated */
         public Function1 conversion() {
            return this.conversion;
         }

         public void scala$collection$generic$IsSeq$_setter_$conversion_$eq(final Function1 x$1) {
            this.conversion = x$1;
         }

         public void scala$collection$generic$IsIterable$_setter_$conversion_$eq(final Function1 x$1) {
         }

         public void scala$collection$generic$IsIterableOnce$_setter_$conversion_$eq(final Function1 x$1) {
         }

         public SeqOps apply(final Object a) {
            return new SeqOps(a) {
               // $FF: synthetic field
               private final <undefinedtype> $outer;
               private final Object a$1;

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
                  return SeqOps.view$(this);
               }

               public Object prepended(final Object elem) {
                  return SeqOps.prepended$(this, elem);
               }

               public final Object $plus$colon(final Object elem) {
                  return SeqOps.$plus$colon$(this, elem);
               }

               public Object appended(final Object elem) {
                  return SeqOps.appended$(this, elem);
               }

               public final Object $colon$plus(final Object elem) {
                  return SeqOps.$colon$plus$(this, elem);
               }

               public Object prependedAll(final IterableOnce prefix) {
                  return SeqOps.prependedAll$(this, prefix);
               }

               public final Object $plus$plus$colon(final IterableOnce prefix) {
                  return SeqOps.$plus$plus$colon$(this, prefix);
               }

               public Object appendedAll(final IterableOnce suffix) {
                  return SeqOps.appendedAll$(this, suffix);
               }

               public final Object $colon$plus$plus(final IterableOnce suffix) {
                  return SeqOps.$colon$plus$plus$(this, suffix);
               }

               public final Object concat(final IterableOnce suffix) {
                  return SeqOps.concat$(this, suffix);
               }

               /** @deprecated */
               public final Object union(final Seq that) {
                  return SeqOps.union$(this, that);
               }

               public final int size() {
                  return SeqOps.size$(this);
               }

               public Object distinct() {
                  return SeqOps.distinct$(this);
               }

               public Object distinctBy(final Function1 f) {
                  return SeqOps.distinctBy$(this, f);
               }

               public Object reverse() {
                  return SeqOps.reverse$(this);
               }

               public Iterator reverseIterator() {
                  return SeqOps.reverseIterator$(this);
               }

               public boolean startsWith(final IterableOnce that, final int offset) {
                  return SeqOps.startsWith$(this, that, offset);
               }

               public int startsWith$default$2() {
                  return SeqOps.startsWith$default$2$(this);
               }

               public boolean endsWith(final Iterable that) {
                  return SeqOps.endsWith$(this, that);
               }

               public boolean isDefinedAt(final int idx) {
                  return SeqOps.isDefinedAt$(this, idx);
               }

               public Object padTo(final int len, final Object elem) {
                  return SeqOps.padTo$(this, len, elem);
               }

               public final int segmentLength(final Function1 p) {
                  return SeqOps.segmentLength$(this, p);
               }

               public int segmentLength(final Function1 p, final int from) {
                  return SeqOps.segmentLength$(this, p, from);
               }

               /** @deprecated */
               public final int prefixLength(final Function1 p) {
                  return SeqOps.prefixLength$(this, p);
               }

               public int indexWhere(final Function1 p, final int from) {
                  return SeqOps.indexWhere$(this, p, from);
               }

               public int indexWhere(final Function1 p) {
                  return SeqOps.indexWhere$(this, p);
               }

               public int indexOf(final Object elem, final int from) {
                  return SeqOps.indexOf$(this, elem, from);
               }

               public int indexOf(final Object elem) {
                  return SeqOps.indexOf$(this, elem);
               }

               public int lastIndexOf(final Object elem, final int end) {
                  return SeqOps.lastIndexOf$(this, elem, end);
               }

               public int lastIndexOf$default$2() {
                  return SeqOps.lastIndexOf$default$2$(this);
               }

               public int lastIndexWhere(final Function1 p, final int end) {
                  return SeqOps.lastIndexWhere$(this, p, end);
               }

               public int lastIndexWhere(final Function1 p) {
                  return SeqOps.lastIndexWhere$(this, p);
               }

               public int indexOfSlice(final Seq that, final int from) {
                  return SeqOps.indexOfSlice$(this, that, from);
               }

               public int indexOfSlice(final Seq that) {
                  return SeqOps.indexOfSlice$(this, that);
               }

               public int lastIndexOfSlice(final Seq that, final int end) {
                  return SeqOps.lastIndexOfSlice$(this, that, end);
               }

               public int lastIndexOfSlice(final Seq that) {
                  return SeqOps.lastIndexOfSlice$(this, that);
               }

               public Option findLast(final Function1 p) {
                  return SeqOps.findLast$(this, p);
               }

               public boolean containsSlice(final Seq that) {
                  return SeqOps.containsSlice$(this, that);
               }

               public boolean contains(final Object elem) {
                  return SeqOps.contains$(this, elem);
               }

               /** @deprecated */
               public Object reverseMap(final Function1 f) {
                  return SeqOps.reverseMap$(this, f);
               }

               public Iterator permutations() {
                  return SeqOps.permutations$(this);
               }

               public Iterator combinations(final int n) {
                  return SeqOps.combinations$(this, n);
               }

               public Object sorted(final Ordering ord) {
                  return SeqOps.sorted$(this, ord);
               }

               public Object sortWith(final Function2 lt) {
                  return SeqOps.sortWith$(this, lt);
               }

               public Object sortBy(final Function1 f, final Ordering ord) {
                  return SeqOps.sortBy$(this, f, ord);
               }

               public Range indices() {
                  return SeqOps.indices$(this);
               }

               public final int sizeCompare(final int otherSize) {
                  return SeqOps.sizeCompare$(this, otherSize);
               }

               public int lengthCompare(final int len) {
                  return SeqOps.lengthCompare$(this, len);
               }

               public final int sizeCompare(final Iterable that) {
                  return SeqOps.sizeCompare$(this, that);
               }

               public int lengthCompare(final Iterable that) {
                  return SeqOps.lengthCompare$(this, that);
               }

               public final IterableOps lengthIs() {
                  return SeqOps.lengthIs$(this);
               }

               public boolean isEmpty() {
                  return SeqOps.isEmpty$(this);
               }

               public boolean sameElements(final IterableOnce that) {
                  return SeqOps.sameElements$(this, that);
               }

               public boolean corresponds(final Seq that, final Function2 p) {
                  return SeqOps.corresponds$(this, that, p);
               }

               public Object diff(final Seq that) {
                  return SeqOps.diff$(this, that);
               }

               public Object intersect(final Seq that) {
                  return SeqOps.intersect$(this, that);
               }

               public Object patch(final int from, final IterableOnce other, final int replaced) {
                  return SeqOps.patch$(this, from, other, replaced);
               }

               public Object updated(final int index, final Object elem) {
                  return SeqOps.updated$(this, index, elem);
               }

               public Map occCounts(final Seq sq) {
                  return SeqOps.occCounts$(this, sq);
               }

               public Searching.SearchResult search(final Object elem, final Ordering ord) {
                  return SeqOps.search$(this, elem, ord);
               }

               public Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
                  return SeqOps.search$(this, elem, from, to, ord);
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

               public scala.collection.immutable.Map toMap(final $less$colon$less ev) {
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

               public Object apply(final int i) {
                  return ScalaRunTime$.MODULE$.array_apply(this.a$1, i);
               }

               public int length() {
                  return Array.getLength(this.a$1);
               }

               public Iterable toIterable() {
                  return ArraySeq$.MODULE$.make(this.a$1);
               }

               public Object coll() {
                  return this.a$1;
               }

               public Object fromSpecific(final IterableOnce coll) {
                  return Array$.MODULE$.from(coll, this.$outer.evidence$1$1);
               }

               public IterableFactory iterableFactory() {
                  return ArraySeq$.MODULE$.untagged();
               }

               public Object empty() {
                  return this.$outer.evidence$1$1.newArray(0);
               }

               public Builder newSpecificBuilder() {
                  ClassTag newBuilder_t = this.$outer.evidence$1$1;
                  ArrayBuilder$ var10000 = ArrayBuilder$.MODULE$;
                  Class var2 = newBuilder_t.runtimeClass();
                  Class var3 = Byte.TYPE;
                  if (var3 == null) {
                     if (var2 == null) {
                        return new ArrayBuilder.ofByte();
                     }
                  } else if (var3.equals(var2)) {
                     return new ArrayBuilder.ofByte();
                  }

                  var3 = Short.TYPE;
                  if (var3 == null) {
                     if (var2 == null) {
                        return new ArrayBuilder.ofShort();
                     }
                  } else if (var3.equals(var2)) {
                     return new ArrayBuilder.ofShort();
                  }

                  var3 = Character.TYPE;
                  if (var3 == null) {
                     if (var2 == null) {
                        return new ArrayBuilder.ofChar();
                     }
                  } else if (var3.equals(var2)) {
                     return new ArrayBuilder.ofChar();
                  }

                  var3 = Integer.TYPE;
                  if (var3 == null) {
                     if (var2 == null) {
                        return new ArrayBuilder.ofInt();
                     }
                  } else if (var3.equals(var2)) {
                     return new ArrayBuilder.ofInt();
                  }

                  var3 = Long.TYPE;
                  if (var3 == null) {
                     if (var2 == null) {
                        return new ArrayBuilder.ofLong();
                     }
                  } else if (var3.equals(var2)) {
                     return new ArrayBuilder.ofLong();
                  }

                  var3 = Float.TYPE;
                  if (var3 == null) {
                     if (var2 == null) {
                        return new ArrayBuilder.ofFloat();
                     }
                  } else if (var3.equals(var2)) {
                     return new ArrayBuilder.ofFloat();
                  }

                  var3 = Double.TYPE;
                  if (var3 == null) {
                     if (var2 == null) {
                        return new ArrayBuilder.ofDouble();
                     }
                  } else if (var3.equals(var2)) {
                     return new ArrayBuilder.ofDouble();
                  }

                  var3 = Boolean.TYPE;
                  if (var3 == null) {
                     if (var2 == null) {
                        return new ArrayBuilder.ofBoolean();
                     }
                  } else if (var3.equals(var2)) {
                     return new ArrayBuilder.ofBoolean();
                  }

                  var3 = Void.TYPE;
                  if (var3 == null) {
                     if (var2 == null) {
                        return new ArrayBuilder.ofUnit();
                     }
                  } else if (var3.equals(var2)) {
                     return new ArrayBuilder.ofUnit();
                  }

                  return new ArrayBuilder.ofRef(newBuilder_t);
               }

               public Iterator iterator() {
                  return ArrayOps$.MODULE$.iterator$extension(this.a$1);
               }

               public {
                  if (<VAR_NAMELESS_ENCLOSURE> == null) {
                     throw null;
                  } else {
                     this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     this.a$1 = a$1;
                  }
               }
            };
         }

         public {
            this.evidence$1$1 = evidence$1$1;
            IsIterableOnce.$init$(this);
            IsIterable.$init$(this);
            IsSeq.$init$(this);
            Statics.releaseFence();
         }
      };
   }

   static IsSeq stringViewIsSeq() {
      return IsSeq$.MODULE$.stringViewIsSeq();
   }

   static IsSeq stringIsSeq() {
      return IsSeq$.MODULE$.stringIsSeq();
   }

   static IsSeq seqViewIsSeq() {
      IsSeq$ var10000 = IsSeq$.MODULE$;
      return new IsSeq() {
         /** @deprecated */
         private Function1 conversion;

         /** @deprecated */
         public Function1 conversion() {
            return this.conversion;
         }

         public void scala$collection$generic$IsSeq$_setter_$conversion_$eq(final Function1 x$1) {
            this.conversion = x$1;
         }

         public void scala$collection$generic$IsIterable$_setter_$conversion_$eq(final Function1 x$1) {
         }

         public void scala$collection$generic$IsIterableOnce$_setter_$conversion_$eq(final Function1 x$1) {
         }

         public SeqOps apply(final SeqView coll) {
            return coll;
         }

         public {
            IsIterableOnce.$init$(this);
            IsIterable.$init$(this);
            IsSeq.$init$(this);
            Statics.releaseFence();
         }
      };
   }

   static IsSeq seqOpsIsSeq() {
      return IsSeq$.MODULE$.seqOpsIsSeq();
   }

   void scala$collection$generic$IsSeq$_setter_$conversion_$eq(final Function1 x$1);

   /** @deprecated */
   Function1 conversion();

   SeqOps apply(final Object coll);

   static void $init$(final IsSeq $this) {
      $this.scala$collection$generic$IsSeq$_setter_$conversion_$eq((x$1) -> $this.apply(x$1));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
