package scala.xml;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.AbstractSeq;
import scala.collection.BuildFrom;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.StringOps.;
import scala.collection.immutable.Seq;
import scala.collection.immutable.StrictOptimizedSeqOps;
import scala.collection.mutable.Builder;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyRef;
import scala.runtime.Nothing;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eu!B\u000e\u001d\u0011\u0003\tc!B\u0012\u001d\u0011\u0003!\u0003\"B\u0019\u0002\t\u0003\u0011\u0004bB\u001a\u0002\u0005\u0004%)\u0001\u000e\u0005\b\u0003\u0013\n\u0001\u0015!\u00046\u0011\u001d\tY%\u0001C\u0001\u0003\u001b*Q!a\u0015\u0002\u0001UBq!!\u0016\u0002\t\u0007\t9\u0006C\u0004\u0002l\u0005!\t!!\u001c\t\u000f\u0005m\u0014\u0001b\u0001\u0002~!I\u0011\u0011Q\u0001\u0002\u0002\u0013%\u00111\u0011\u0004\u0006Gq\t\tA\u000e\u0005\u0006c-!\tA\u0015\u0005\u0006'.1\t\u0001\u0016\u0005\u0006/.!\t\u0005\u0017\u0005\u00069.!\t%\u0018\u0005\u0006C.!\tE\u0019\u0005\u0006C.!\t!\u001a\u0005\u0006].!\ta\u001c\u0005\b\u0003\u0007YA\u0011KA\u0003\u0011\u001d\tIa\u0003C!\u0003\u0017Aq!!\u0005\f\t\u0003\n\u0019\u0002C\u0004\u0002\u0018-!\t!!\u0007\t\u000f\u0005M2\u0002\"\u0001\u00026!9\u0011\u0011H\u0006\u0005\u0002\u0005m\u0002bBA!\u0017\u0011\u0005\u00131\t\u0005\b\u0003\u000bZA\u0011AA$\u0003\u001dqu\u000eZ3TKFT!!\b\u0010\u0002\u0007alGNC\u0001 \u0003\u0015\u00198-\u00197b\u0007\u0001\u0001\"AI\u0001\u000e\u0003q\u0011qAT8eKN+\u0017oE\u0002\u0002K%\u0002\"AJ\u0014\u000e\u0003yI!\u0001\u000b\u0010\u0003\r\u0005s\u0017PU3g!\tQs&D\u0001,\u0015\taS&\u0001\u0002j_*\ta&\u0001\u0003kCZ\f\u0017B\u0001\u0019,\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003\u0019a\u0014N\\5u}Q\t\u0011%A\u0003F[B$\u00180F\u00016!\t\u00113b\u0005\u0004\fo\u00013\u0015\n\u0014\t\u0004qmjT\"A\u001d\u000b\u0005ir\u0012AC2pY2,7\r^5p]&\u0011A(\u000f\u0002\f\u0003\n\u001cHO]1diN+\u0017\u000f\u0005\u0002#}%\u0011q\b\b\u0002\u0005\u001d>$W\rE\u0002B\tvj\u0011A\u0011\u0006\u0003\u0007f\n\u0011\"[7nkR\f'\r\\3\n\u0005\u0015\u0013%aA*fcB\u0011!eR\u0005\u0003\u0011r\u00111dU2bY\u00064VM]:j_:\u001c\u0006/Z2jM&\u001cgj\u001c3f'\u0016\f\bC\u0001\u0012K\u0013\tYED\u0001\u0005FcV\fG.\u001b;z!\ti\u0005K\u0004\u0002'\u001d&\u0011qJH\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0001\u0014K\u0003\u0002P=Q\tQ'\u0001\u0004uQ\u0016\u001cV-]\u000b\u0002+B\u0019\u0001HV\u001f\n\u0005\u0015K\u0014A\u00027f]\u001e$\b.F\u0001Z!\t1#,\u0003\u0002\\=\t\u0019\u0011J\u001c;\u0002\u0011%$XM]1u_J,\u0012A\u0018\t\u0004\u001b~k\u0014B\u00011R\u0005!IE/\u001a:bi>\u0014\u0018!B1qa2LHCA\u001fd\u0011\u0015!\u0007\u00031\u0001Z\u0003\u0005IGCA\u001bg\u0011\u00159\u0017\u00031\u0001i\u0003\u00051\u0007\u0003\u0002\u0014j{-L!A\u001b\u0010\u0003\u0013\u0019+hn\u0019;j_:\f\u0004C\u0001\u0014m\u0013\tigDA\u0004C_>dW-\u00198\u0002!alGnX:b[\u0016,E.Z7f]R\u001cXC\u00019y)\tY\u0017\u000fC\u0003s%\u0001\u00071/\u0001\u0003uQ\u0006$\bcA'um&\u0011Q/\u0015\u0002\t\u0013R,'/\u00192mKB\u0011q\u000f\u001f\u0007\u0001\t\u0015I(C1\u0001{\u0005\u0005\t\u0015CA>\u007f!\t1C0\u0003\u0002~=\t9aj\u001c;iS:<\u0007C\u0001\u0014\u0000\u0013\r\t\tA\b\u0002\u0004\u0003:L\u0018\u0001\u00052bg&\u001chi\u001c:ICND7i\u001c3f+\t\t9\u0001E\u00029-z\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0004W\u00065\u0001BBA\b)\u0001\u0007a0A\u0003pi\",'/A\u0007tiJL7\r^0%KF$S-\u001d\u000b\u0004W\u0006U\u0001BBA\b+\u0001\u0007\u0011*A\u0004%ENd\u0017m\u001d5\u0015\u0007U\nY\u0002\u0003\u0004s-\u0001\u0007\u0011Q\u0004\t\u0005\u0003?\tiC\u0004\u0003\u0002\"\u0005%\u0002cAA\u0012=5\u0011\u0011Q\u0005\u0006\u0004\u0003O\u0001\u0013A\u0002\u001fs_>$h(C\u0002\u0002,y\ta\u0001\u0015:fI\u00164\u0017\u0002BA\u0018\u0003c\u0011aa\u0015;sS:<'bAA\u0016=\u0005qAEY:mCNDGEY:mCNDGcA\u001b\u00028!1!o\u0006a\u0001\u0003;\t!\u0002\n2tY\u0006\u001c\b\u000eJ1u)\u0011\ti\"!\u0010\t\u000f\u0005}\u0002\u00041\u0001\u0002\u001e\u0005i\u0011\r\u001e;sS\n,H/\u001a(b[\u0016\f\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003;\tA\u0001^3yiV\u0011\u0011QD\u0001\u0007\u000b6\u0004H/\u001f\u0011\u0002\u000f\u0019\u0014x.\\*fcR\u0019Q'a\u0014\t\r\u0005ES\u00011\u0001V\u0003\u0005\u0019(\u0001B\"pY2\fAbY1o\u0005VLG\u000e\u001a$s_6,\"!!\u0017\u0011\u0011\u0005m\u0013\u0011MA4{Ur1AIA/\u0013\r\ty\u0006H\u0001\u0015'\u000e\fG.\u0019,feNLwN\\*qK\u000eLg-[2\n\t\u0005\r\u0014Q\r\u0002\u0004\u0007\n3%bAA09A\u0019\u0011\u0011\u000e\u0004\u000e\u0003\u0005\t!B\\3x\u0005VLG\u000eZ3s+\t\ty\u0007\u0005\u0004\u0002r\u0005]T(N\u0007\u0003\u0003gR1!!\u001e:\u0003\u001diW\u000f^1cY\u0016LA!!\u001f\u0002t\t9!)^5mI\u0016\u0014\u0018\u0001D:fcR{gj\u001c3f'\u0016\fHcA\u001b\u0002\u0000!1\u0011\u0011K\u0005A\u0002U\u000bAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\"\u0011\t\u0005\u001d\u0015QR\u0007\u0003\u0003\u0013S1!a#.\u0003\u0011a\u0017M\\4\n\t\u0005=\u0015\u0011\u0012\u0002\u0007\u001f\nTWm\u0019;"
)
public abstract class NodeSeq extends AbstractSeq implements Seq, ScalaVersionSpecificNodeSeq, Equality, Serializable {
   public static NodeSeq seqToNodeSeq(final scala.collection.Seq s) {
      return NodeSeq$.MODULE$.seqToNodeSeq(s);
   }

   public static Builder newBuilder() {
      return NodeSeq$.MODULE$.newBuilder();
   }

   public static BuildFrom canBuildFrom() {
      return NodeSeq$.MODULE$.canBuildFrom();
   }

   public static NodeSeq fromSeq(final scala.collection.Seq s) {
      return NodeSeq$.MODULE$.fromSeq(s);
   }

   public static NodeSeq Empty() {
      return NodeSeq$.MODULE$.Empty();
   }

   public boolean strict_$bang$eq(final Equality other) {
      return Equality.strict_$bang$eq$(this, other);
   }

   public int hashCode() {
      return Equality.hashCode$(this);
   }

   public boolean equals(final Object other) {
      return Equality.equals$(this, other);
   }

   public final boolean xml_$eq$eq(final Object other) {
      return Equality.xml_$eq$eq$(this, other);
   }

   public final boolean xml_$bang$eq(final Object other) {
      return Equality.xml_$bang$eq$(this, other);
   }

   public NodeSeq fromSpecific(final IterableOnce coll) {
      return ScalaVersionSpecificNodeSeq.fromSpecific$(this, coll);
   }

   public Builder newSpecificBuilder() {
      return ScalaVersionSpecificNodeSeq.newSpecificBuilder$(this);
   }

   public NodeSeq empty() {
      return ScalaVersionSpecificNodeSeq.empty$(this);
   }

   public NodeSeq concat(final IterableOnce suffix) {
      return ScalaVersionSpecificNodeSeq.concat$(this, suffix);
   }

   public final NodeSeq $plus$plus(final Seq suffix) {
      return ScalaVersionSpecificNodeSeq.$plus$plus$(this, suffix);
   }

   public NodeSeq appended(final Node base) {
      return ScalaVersionSpecificNodeSeq.appended$(this, base);
   }

   public NodeSeq appendedAll(final IterableOnce suffix) {
      return ScalaVersionSpecificNodeSeq.appendedAll$(this, suffix);
   }

   public NodeSeq prepended(final Node base) {
      return ScalaVersionSpecificNodeSeq.prepended$(this, base);
   }

   public NodeSeq prependedAll(final IterableOnce prefix) {
      return ScalaVersionSpecificNodeSeq.prependedAll$(this, prefix);
   }

   public NodeSeq map(final Function1 f) {
      return ScalaVersionSpecificNodeSeq.map$(this, f);
   }

   public NodeSeq flatMap(final Function1 f) {
      return ScalaVersionSpecificNodeSeq.flatMap$(this, f);
   }

   // $FF: synthetic method
   public Object scala$collection$immutable$StrictOptimizedSeqOps$$super$sorted(final Ordering ord) {
      return SeqOps.sorted$(this, ord);
   }

   public Object distinctBy(final Function1 f) {
      return StrictOptimizedSeqOps.distinctBy$(this, f);
   }

   public Object updated(final int index, final Object elem) {
      return StrictOptimizedSeqOps.updated$(this, index, elem);
   }

   public Object patch(final int from, final IterableOnce other, final int replaced) {
      return StrictOptimizedSeqOps.patch$(this, from, other, replaced);
   }

   public Object sorted(final Ordering ord) {
      return StrictOptimizedSeqOps.sorted$(this, ord);
   }

   public Object prepended(final Object elem) {
      return scala.collection.StrictOptimizedSeqOps.prepended$(this, elem);
   }

   public Object appended(final Object elem) {
      return scala.collection.StrictOptimizedSeqOps.appended$(this, elem);
   }

   public Object appendedAll(final IterableOnce suffix) {
      return scala.collection.StrictOptimizedSeqOps.appendedAll$(this, suffix);
   }

   public Object prependedAll(final IterableOnce prefix) {
      return scala.collection.StrictOptimizedSeqOps.prependedAll$(this, prefix);
   }

   public Object padTo(final int len, final Object elem) {
      return scala.collection.StrictOptimizedSeqOps.padTo$(this, len, elem);
   }

   public Object diff(final scala.collection.Seq that) {
      return scala.collection.StrictOptimizedSeqOps.diff$(this, that);
   }

   public Object intersect(final scala.collection.Seq that) {
      return scala.collection.StrictOptimizedSeqOps.intersect$(this, that);
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

   public final Seq toSeq() {
      return Seq.toSeq$(this);
   }

   public SeqFactory iterableFactory() {
      return Seq.iterableFactory$(this);
   }

   public abstract scala.collection.Seq theSeq();

   public int length() {
      return this.theSeq().length();
   }

   public Iterator iterator() {
      return this.theSeq().iterator();
   }

   public Node apply(final int i) {
      return (Node)this.theSeq().apply(i);
   }

   public NodeSeq apply(final Function1 f) {
      return (NodeSeq)this.filter(f);
   }

   public boolean xml_sameElements(final Iterable that) {
      Iterator these = this.iterator();
      Iterator those = that.iterator();

      while(these.hasNext() && those.hasNext()) {
         if (((Equality)these.next()).xml_$bang$eq(those.next())) {
            return false;
         }
      }

      return !these.hasNext() && !those.hasNext();
   }

   public scala.collection.Seq basisForHashCode() {
      return this.theSeq();
   }

   public boolean canEqual(final Object other) {
      return other instanceof NodeSeq;
   }

   public boolean strict_$eq$eq(final Equality other) {
      if (!(other instanceof NodeSeq)) {
         return false;
      } else {
         NodeSeq var4 = (NodeSeq)other;
         return this.length() == var4.length() && this.theSeq().sameElements(var4.theSeq());
      }
   }

   public NodeSeq $bslash(final String that) {
      switch (that == null ? 0 : that.hashCode()) {
         case 0:
            if ("".equals(that)) {
               throw fail$1(that);
            }
            break;
         case 64:
            if ("@".equals(that)) {
               throw fail$1(that);
            }
            break;
         case 95:
            if ("_".equals(that)) {
               return this.makeSeq$1((x$3) -> BoxesRunTime.boxToBoolean($anonfun$$bslash$2(x$3)));
            }
      }

      return .MODULE$.apply$extension(scala.Predef..MODULE$.augmentString(that), 0) == '@' && this.length() == 1 ? this.atResult$1(that) : this.makeSeq$1((x$4) -> BoxesRunTime.boxToBoolean($anonfun$$bslash$3(that, x$4)));
   }

   public NodeSeq $bslash$bslash(final String that) {
      switch (that == null ? 0 : that.hashCode()) {
         case 0:
            if ("".equals(that)) {
               throw fail$2(that);
            }
            break;
         case 95:
            if ("_".equals(that)) {
               return this.filt$1((x$6) -> BoxesRunTime.boxToBoolean($anonfun$$bslash$bslash$2(x$6)));
            }
      }

      return .MODULE$.apply$extension(scala.Predef..MODULE$.augmentString(that), 0) == '@' ? this.filt$1((x$7) -> BoxesRunTime.boxToBoolean($anonfun$$bslash$bslash$3(x$7))).flatMap((x$8) -> x$8.$bslash(that)) : this.filt$1((x) -> BoxesRunTime.boxToBoolean($anonfun$$bslash$bslash$5(that, x)));
   }

   public String $bslash$at(final String attributeName) {
      return this.$bslash((new StringBuilder(1)).append("@").append(attributeName).toString()).text();
   }

   public String toString() {
      return this.theSeq().mkString();
   }

   public String text() {
      return ((IterableOnceOps)this.map((x$9) -> x$9.text())).mkString();
   }

   private static final Nothing fail$1(final String that$1) {
      throw new IllegalArgumentException(that$1);
   }

   // $FF: synthetic method
   private final Node y$lzycompute$1(final LazyRef y$lzy$1) {
      synchronized(y$lzy$1){}

      Node var3;
      try {
         var3 = y$lzy$1.initialized() ? (Node)y$lzy$1.value() : (Node)y$lzy$1.initialize(this.apply(0));
      } catch (Throwable var5) {
         throw var5;
      }

      return var3;
   }

   private final Node y$1(final LazyRef y$lzy$1) {
      return y$lzy$1.initialized() ? (Node)y$lzy$1.value() : this.y$lzycompute$1(y$lzy$1);
   }

   private final NodeSeq atResult$1(final String that$1) {
      LazyRef y$lzy = new LazyRef();
      if (that$1.length() == 1) {
         throw fail$1(that$1);
      } else {
         Option var10000;
         if (.MODULE$.apply$extension(scala.Predef..MODULE$.augmentString(that$1), 1) == '{') {
            int i = that$1.indexOf(125);
            if (i == -1) {
               throw fail$1(that$1);
            }

            Tuple2 var8 = new Tuple2(that$1.substring(2, i), that$1.substring(i + 1, that$1.length()));
            if (var8 == null) {
               throw new MatchError(var8);
            }

            String uri = (String)var8._1();
            String key = (String)var8._2();
            if (uri == null || key == null) {
               throw new MatchError(var8);
            }

            Tuple2 var7 = new Tuple2(uri, key);
            String uri = (String)var7._1();
            String key = (String)var7._2();
            if (uri.isEmpty() || key.isEmpty()) {
               throw fail$1(that$1);
            }

            var10000 = this.y$1(y$lzy).attribute(uri, key);
         } else {
            var10000 = this.y$1(y$lzy).attribute(.MODULE$.drop$extension(scala.Predef..MODULE$.augmentString(that$1), 1));
         }

         Option attr = var10000;
         if (attr instanceof Some) {
            Some var16 = (Some)attr;
            scala.collection.Seq x = (scala.collection.Seq)var16.value();
            return new Group(x);
         } else {
            return NodeSeq$.MODULE$.Empty();
         }
      }
   }

   private final NodeSeq makeSeq$1(final Function1 cond) {
      return NodeSeq$.MODULE$.fromSeq((scala.collection.Seq)this.flatMap((x$2) -> x$2.child()).filter(cond));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$$bslash$2(final Node x$3) {
      return !x$3.isAtom();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$$bslash$3(final String that$1, final Node x$4) {
      boolean var3;
      label23: {
         String var10000 = x$4.label();
         if (var10000 == null) {
            if (that$1 == null) {
               break label23;
            }
         } else if (var10000.equals(that$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   private static final Nothing fail$2(final String that$2) {
      throw new IllegalArgumentException(that$2);
   }

   private final NodeSeq filt$1(final Function1 cond) {
      return (NodeSeq)this.flatMap((x$5) -> x$5.descendant_or_self()).filter(cond);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$$bslash$bslash$2(final Node x$6) {
      return !x$6.isAtom();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$$bslash$bslash$3(final Node x$7) {
      return !x$7.isAtom();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$$bslash$bslash$5(final String that$2, final Node x) {
      boolean var3;
      label25: {
         if (!x.isAtom()) {
            String var10000 = x.label();
            if (var10000 == null) {
               if (that$2 == null) {
                  break label25;
               }
            } else if (var10000.equals(that$2)) {
               break label25;
            }
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   public NodeSeq() {
      scala.collection.immutable.Iterable.$init$(this);
      Seq.$init$(this);
      StrictOptimizedIterableOps.$init$(this);
      scala.collection.StrictOptimizedSeqOps.$init$(this);
      StrictOptimizedSeqOps.$init$(this);
      ScalaVersionSpecificNodeSeq.$init$(this);
      Equality.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
