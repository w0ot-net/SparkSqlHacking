package scala.collection.immutable;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.SortedMapFactory;
import scala.collection.SortedMapFactoryDefaults;
import scala.collection.SortedOps;
import scala.collection.mutable.Builder;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\tEaaB\u000e\u001d!\u0003\r\ta\t\u0005\u0006\u0015\u0002!\ta\u0013\u0005\u0006\u001f\u0002!\t\u0005\u0015\u0005\u0006#\u0002!\tE\u0015\u0005\u0006-\u0002!\te\u0016\u0005\u0006G\u0002!\t\u0005Z\u0004\u0006UrA\ta\u001b\u0004\u00067qA\t\u0001\u001c\u0005\u0006i\u001e!\t!\u001e\u0005\u0006m\u001e!\te\u001e\u0004\u0007\u0003C9!!a\t\t\u0019\u0005%#B!A!\u0002\u0013\tY$a\u0013\t\u0019\u00055#B!A!\u0002\u0013\ty%!\u0015\t\rQTA\u0011AA*\u0011\u001d\tIF\u0003C\u0002\u00037BQ!\u0015\u0006\u0005BICq!a\u0018\u000b\t\u0003\t\t\u0007C\u0004\u0002p)!\t!!\u001d\t\u000f\u0005]$\u0002\"\u0001\u0002z!9\u0011q\u0011\u0006\u0005B\u0005%\u0005bBAO\u0015\u0011\u0005\u0013q\u0014\u0005\b\u0003gSA\u0011IA[\u0011\u001d\tIL\u0003C!\u0003wCq!!0\u000b\t#\ny\fC\u0004\u0002\\*!\t&!8\t\u001d\u00055(\u0002%A\u0002\u0002\u0003%I!a<\u0002R!I\u0011\u0011_\u0004\u0002\u0002\u0013%\u00111\u001f\u0002\n'>\u0014H/\u001a3NCBT!!\b\u0010\u0002\u0013%lW.\u001e;bE2,'BA\u0010!\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002C\u0005)1oY1mC\u000e\u0001Qc\u0001\u00130sM1\u0001!J\u0015<}\r\u0003\"AJ\u0014\u000e\u0003\u0001J!\u0001\u000b\u0011\u0003\r\u0005s\u0017PU3g!\u0011Q3&\f\u001d\u000e\u0003qI!\u0001\f\u000f\u0003\u00075\u000b\u0007\u000f\u0005\u0002/_1\u0001A!\u0002\u0019\u0001\u0005\u0004\t$!A&\u0012\u0005I*\u0004C\u0001\u00144\u0013\t!\u0004EA\u0004O_RD\u0017N\\4\u0011\u0005\u00192\u0014BA\u001c!\u0005\r\te.\u001f\t\u0003]e\"aA\u000f\u0001\u0005\u0006\u0004\t$!\u0001,\u0011\tqjT\u0006O\u0007\u0002=%\u00111D\b\t\u0007U}j\u0003(\u0011\"\n\u0005\u0001c\"\u0001D*peR,G-T1q\u001fB\u001c\bC\u0001\u0016\u0001!\u0011Q\u0003!\f\u001d\u0011\u000fq\"U\u0006O!G\u0013&\u0011QI\b\u0002\u0019'>\u0014H/\u001a3NCB4\u0015m\u0019;pef$UMZ1vYR\u001c\bC\u0001\u0016H\u0013\tAED\u0001\u0005Ji\u0016\u0014\u0018M\u00197f!\tQ3&\u0001\u0004%S:LG\u000f\n\u000b\u0002\u0019B\u0011a%T\u0005\u0003\u001d\u0002\u0012A!\u00168ji\u0006AQO\\:peR,G-F\u0001*\u0003A\u0019xN\u001d;fI6\u000b\u0007OR1di>\u0014\u00180F\u0001T!\raD+Q\u0005\u0003+z\u0011\u0001cU8si\u0016$W*\u00199GC\u000e$xN]=\u0002\u0017]LG\u000f\u001b#fM\u0006,H\u000e^\u000b\u00031n#\"!\u00170\u0011\t)\u0002QF\u0017\t\u0003]m#Q\u0001\u0018\u0003C\u0002u\u0013!AV\u0019\u0012\u0005a*\u0004\"B0\u0005\u0001\u0004\u0001\u0017!\u00013\u0011\t\u0019\nWFW\u0005\u0003E\u0002\u0012\u0011BR;oGRLwN\\\u0019\u0002!]LG\u000f\u001b#fM\u0006,H\u000e\u001e,bYV,WCA3i)\t1\u0017\u000e\u0005\u0003+\u00015:\u0007C\u0001\u0018i\t\u0015aVA1\u0001^\u0011\u0015yV\u00011\u0001h\u0003%\u0019vN\u001d;fI6\u000b\u0007\u000f\u0005\u0002+\u000fM\u0011q!\u001c\t\u0004]F\feB\u0001\u001fp\u0013\t\u0001h$\u0001\tT_J$X\rZ'ba\u001a\u000b7\r^8ss&\u0011!o\u001d\u0002\t\t\u0016dWmZ1uK*\u0011\u0001OH\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003-\fAA\u001a:p[V\u0019\u0001\u0010 @\u0015\u0007e\f\t\u0002\u0006\u0002{\u007fB!!\u0006A>~!\tqC\u0010B\u00031\u0013\t\u0007\u0011\u0007\u0005\u0002/}\u0012)!(\u0003b\u0001c!I\u0011\u0011A\u0005\u0002\u0002\u0003\u000f\u00111A\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004#BA\u0003\u0003\u0017Yhb\u0001\u0014\u0002\b%\u0019\u0011\u0011\u0002\u0011\u0002\u000fA\f7m[1hK&!\u0011QBA\b\u0005!y%\u000fZ3sS:<'bAA\u0005A!9\u00111C\u0005A\u0002\u0005U\u0011AA5u!\u0015a\u0014qCA\u000e\u0013\r\tIB\b\u0002\r\u0013R,'/\u00192mK>s7-\u001a\t\u0006M\u0005u10`\u0005\u0004\u0003?\u0001#A\u0002+va2,'GA\u0006XSRDG)\u001a4bk2$XCBA\u0013\u0003k\tIdE\u0005\u000b\u0003O\tY$!\u0010\u0002DAA\u0011\u0011FA\u0018\u0003g\t9DD\u0002+\u0003WI1!!\f\u001d\u0003\ri\u0015\r]\u0005\u0005\u0003C\t\tDC\u0002\u0002.q\u00012ALA\u001b\t\u0015\u0001$B1\u00012!\rq\u0013\u0011\b\u0003\u0007u)!)\u0019A\u0019\u0011\r)\u0002\u00111GA\u001c!%Qs(a\r\u00028\u0005\u000by\u0004E\u0004\u0002B)\t\u0019$a\u000e\u000e\u0003\u001d\u0001B!!\u0002\u0002F%!\u0011qIA\b\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003))h\u000eZ3sYfLgnZ\u0005\u0005\u0003\u0013\ny#\u0001\u0007eK\u001a\fW\u000f\u001c;WC2,X\r\u0005\u0004'C\u0006M\u0012qG\u0005\u0005\u0003\u001b\ny\u0003\u0006\u0004\u0002@\u0005U\u0013q\u000b\u0005\b\u0003\u0013j\u0001\u0019AA\u001e\u0011\u001d\ti%\u0004a\u0001\u0003\u001f\n\u0001b\u001c:eKJLgnZ\u000b\u0003\u0003;\u0002b!!\u0002\u0002\f\u0005M\u0012\u0001D5uKJ\fGo\u001c:Ge>lG\u0003BA2\u0003W\u0002R\u0001PA3\u0003SJ1!a\u001a\u001f\u0005!IE/\u001a:bi>\u0014\bc\u0002\u0014\u0002\u001e\u0005M\u0012q\u0007\u0005\b\u0003[\u0002\u0002\u0019AA\u001a\u0003\u0015\u0019H/\u0019:u\u0003AYW-_:Ji\u0016\u0014\u0018\r^8s\rJ|W\u000e\u0006\u0003\u0002t\u0005U\u0004#\u0002\u001f\u0002f\u0005M\u0002bBA7#\u0001\u0007\u00111G\u0001\ne\u0006tw-Z%na2$b!a\u0010\u0002|\u0005\r\u0005B\u0002<\u0013\u0001\u0004\ti\bE\u0003'\u0003\u007f\n\u0019$C\u0002\u0002\u0002\u0002\u0012aa\u00149uS>t\u0007bBAC%\u0001\u0007\u0011QP\u0001\u0006k:$\u0018\u000e\\\u0001\bkB$\u0017\r^3e+\u0011\tY)!%\u0015\r\u00055\u0015QSAM!\u001d\t\tECA\u001a\u0003\u001f\u00032ALAI\t\u0019a6C1\u0001\u0002\u0014F\u0019\u0011qG\u001b\t\u000f\u0005]5\u00031\u0001\u00024\u0005\u00191.Z=\t\u000f\u0005m5\u00031\u0001\u0002\u0010\u0006)a/\u00197vK\u000611m\u001c8dCR,B!!)\u0002(R!\u00111UAV!\u001d\t\tECA\u001a\u0003K\u00032ALAT\t\u001d\tI\u000b\u0006b\u0001\u0003'\u0013!A\u0016\u001a\t\u000f\u00055F\u00031\u0001\u00020\u0006\u0011\u0001p\u001d\t\u0006y\u0005]\u0011\u0011\u0017\t\bM\u0005u\u00111GAS\u0003\u001d\u0011X-\\8wK\u0012$B!a\u0010\u00028\"9\u0011qS\u000bA\u0002\u0005M\u0012!B3naRLXCAA \u000311'o\\7Ta\u0016\u001c\u0017NZ5d)\u0011\ty$!1\t\u000f\u0005\rw\u00031\u0001\u0002F\u0006!1m\u001c7mU\u0011\t9-!3\u0011\u000bq\n9\"!\u001b,\u0005\u0005-\u0007\u0003BAg\u0003/l!!a4\u000b\t\u0005E\u00171[\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!6!\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u00033\fyMA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\f!C\\3x'B,7-\u001b4jG\n+\u0018\u000e\u001c3feV\u0011\u0011q\u001c\u0016\u0005\u0003C\fI\r\u0005\u0005\u0002d\u0006%\u0018\u0011NA \u001b\t\t)OC\u0002\u0002hz\tq!\\;uC\ndW-\u0003\u0003\u0002l\u0006\u0015(a\u0002\"vS2$WM]\u0001\u0013gV\u0004XM\u001d\u0013eK\u001a\fW\u000f\u001c;WC2,X-\u0006\u0002\u0002P\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011Q\u001f\t\u0005\u0003o\u0014\t!\u0004\u0002\u0002z*!\u00111`A\u007f\u0003\u0011a\u0017M\\4\u000b\u0005\u0005}\u0018\u0001\u00026bm\u0006LAAa\u0001\u0002z\n1qJ\u00196fGRDsa\u0002B\u0004\u00037\u0013i\u0001E\u0002'\u0005\u0013I1Aa\u0003!\u0005A\u0019VM]5bYZ+'o]5p]VKEIH\u0001\u0004Q\u001d1!qAAN\u0005\u001b\u0001"
)
public interface SortedMap extends Map, scala.collection.SortedMap, SortedMapOps {
   static Builder newBuilder(final Ordering evidence$48) {
      return SortedMap$.MODULE$.newBuilder(evidence$48);
   }

   default Map unsorted() {
      return this;
   }

   // $FF: synthetic method
   static SortedMapFactory sortedMapFactory$(final SortedMap $this) {
      return $this.sortedMapFactory();
   }

   default SortedMapFactory sortedMapFactory() {
      return SortedMap$.MODULE$;
   }

   default SortedMap withDefault(final Function1 d) {
      return new WithDefault(this, d);
   }

   default SortedMap withDefaultValue(final Object d) {
      return new WithDefault(this, (x$1) -> d);
   }

   static void $init$(final SortedMap $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public static final class WithDefault extends Map.WithDefault implements SortedMap {
      public Map unsorted() {
         return SortedMap.super.unsorted();
      }

      public SortedMap withDefault(final Function1 d) {
         return SortedMap.super.withDefault(d);
      }

      public SortedMap withDefaultValue(final Object d) {
         return SortedMap.super.withDefaultValue(d);
      }

      public SortedSet keySet() {
         return SortedMapOps.keySet$(this);
      }

      public final Map $plus(final Tuple2 kv) {
         return SortedMapOps.$plus$(this, kv);
      }

      public Map updatedWith(final Object key, final Function1 remappingFunction) {
         return SortedMapOps.updatedWith$(this, key, remappingFunction);
      }

      public Map transform(final Function2 f) {
         return SortedMapOps.transform$(this, f);
      }

      // $FF: synthetic method
      public boolean scala$collection$SortedMap$$super$equals(final Object o) {
         return scala.collection.Map.equals$(this, o);
      }

      public String stringPrefix() {
         return scala.collection.SortedMap.stringPrefix$(this);
      }

      public boolean equals(final Object that) {
         return scala.collection.SortedMap.equals$(this, that);
      }

      public scala.collection.SortedMapOps.WithFilter withFilter(final Function1 p) {
         return SortedMapFactoryDefaults.withFilter$(this, p);
      }

      public final scala.collection.Map sortedMapFromIterable(final scala.collection.Iterable it, final Ordering ordering) {
         return scala.collection.SortedMapOps.sortedMapFromIterable$(this, it, ordering);
      }

      public Iterator valuesIteratorFrom(final Object start) {
         return scala.collection.SortedMapOps.valuesIteratorFrom$(this, start);
      }

      public Object firstKey() {
         return scala.collection.SortedMapOps.firstKey$(this);
      }

      public Object lastKey() {
         return scala.collection.SortedMapOps.lastKey$(this);
      }

      public Option minAfter(final Object key) {
         return scala.collection.SortedMapOps.minAfter$(this, key);
      }

      public Option maxBefore(final Object key) {
         return scala.collection.SortedMapOps.maxBefore$(this, key);
      }

      public scala.collection.SortedMapOps rangeTo(final Object to) {
         return scala.collection.SortedMapOps.rangeTo$(this, to);
      }

      public scala.collection.Map map(final Function1 f, final Ordering ordering) {
         return scala.collection.SortedMapOps.map$(this, f, ordering);
      }

      public scala.collection.Map flatMap(final Function1 f, final Ordering ordering) {
         return scala.collection.SortedMapOps.flatMap$(this, f, ordering);
      }

      public scala.collection.Map collect(final PartialFunction pf, final Ordering ordering) {
         return scala.collection.SortedMapOps.collect$(this, pf, ordering);
      }

      public final scala.collection.Map $plus$plus(final IterableOnce xs) {
         return scala.collection.SortedMapOps.$plus$plus$(this, xs);
      }

      /** @deprecated */
      public scala.collection.Map $plus(final Tuple2 elem1, final Tuple2 elem2, final Seq elems) {
         return scala.collection.SortedMapOps.$plus$(this, elem1, elem2, elems);
      }

      /** @deprecated */
      public int compare(final Object k0, final Object k1) {
         return SortedOps.compare$(this, k0, k1);
      }

      public Object range(final Object from, final Object until) {
         return SortedOps.range$(this, from, until);
      }

      /** @deprecated */
      public final Object from(final Object from) {
         return SortedOps.from$(this, from);
      }

      public Object rangeFrom(final Object from) {
         return SortedOps.rangeFrom$(this, from);
      }

      /** @deprecated */
      public final Object until(final Object until) {
         return SortedOps.until$(this, until);
      }

      public Object rangeUntil(final Object until) {
         return SortedOps.rangeUntil$(this, until);
      }

      /** @deprecated */
      public final Object to(final Object to) {
         return SortedOps.to$(this, to);
      }

      // $FF: synthetic method
      private Function1 super$defaultValue() {
         return super.defaultValue();
      }

      public Ordering ordering() {
         return ((SortedMap)super.underlying()).ordering();
      }

      public SortedMapFactory sortedMapFactory() {
         return ((SortedMap)super.underlying()).sortedMapFactory();
      }

      public Iterator iteratorFrom(final Object start) {
         return ((SortedMap)super.underlying()).iteratorFrom(start);
      }

      public Iterator keysIteratorFrom(final Object start) {
         return ((SortedMap)super.underlying()).keysIteratorFrom(start);
      }

      public WithDefault rangeImpl(final Option from, final Option until) {
         return new WithDefault((SortedMap)((SortedMap)super.underlying()).rangeImpl(from, until), super.defaultValue());
      }

      public WithDefault updated(final Object key, final Object value) {
         return new WithDefault((SortedMap)((SortedMap)super.underlying()).updated(key, value), super.defaultValue());
      }

      public WithDefault concat(final IterableOnce xs) {
         return new WithDefault((SortedMap)((SortedMap)super.underlying()).concat(xs), super.defaultValue());
      }

      public WithDefault removed(final Object key) {
         return new WithDefault((SortedMap)((SortedMap)super.underlying()).removed(key), super.defaultValue());
      }

      public WithDefault empty() {
         return new WithDefault((SortedMap)((SortedMap)super.underlying()).empty(), super.defaultValue());
      }

      public WithDefault fromSpecific(final IterableOnce coll) {
         return new WithDefault((SortedMap)this.sortedMapFactory().from(coll, this.ordering()), super.defaultValue());
      }

      public Builder newSpecificBuilder() {
         return SortedMap$.MODULE$.newBuilder(this.ordering()).mapResult((p) -> new WithDefault(p, this.super$defaultValue()));
      }

      public WithDefault(final SortedMap underlying, final Function1 defaultValue) {
         super(underlying, defaultValue);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
