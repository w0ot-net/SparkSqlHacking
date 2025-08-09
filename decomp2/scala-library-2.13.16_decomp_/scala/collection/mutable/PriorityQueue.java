package scala.collection.mutable;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Array;
import java.util.NoSuchElementException;
import scala.Array$;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.AbstractIterator;
import scala.collection.EvidenceIterableFactory;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.IterableOnce$;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.View;
import scala.collection.generic.DefaultSerializationProxy;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Range;
import scala.collection.immutable.Range$;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.RichInt$;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(
   bytes = "\u0006\u0005\tmh\u0001B\u001c9!}B\u0001B\u001d\u0001\u0003\u0006\u0004%\u0019a\u001d\u0005\tu\u0002\u0011\t\u0011)A\u0005i\")1\u0010\u0001C\u0001y\u001a)q\u0010\u0001\u0003\u0002\u0002!11\u0010\u0002C\u0001\u0003#Aq!a\u0006\u0005\t\u0003\nI\u0002C\u0004\u0002(\u0011!\t!!\u000b\t\u000f\u0005EB\u0001\"\u0001\u00024!9\u0011q\b\u0003\u0005\u0002\u0005\u0005\u0003bBA(\t\u0011\u0005\u0011\u0011\u000b\u0005\b\u0003/\"A\u0011AA-\u0011\u001d\ti\u0006\u0002C\u0001\u0003?B\u0011\"!\u001b\u0001\u0005\u0004%I!a\u001b\t\u0011\u0005=\u0004\u0001)A\u0005\u0003[Bq!!\u001d\u0001\t\u0003\tI\u0003C\u0004\u0002t\u0001!\t%!\u000b\t\u000f\u0005U\u0004\u0001\"\u0011\u0002*!9\u0011q\u000f\u0001\u0005B\u0005e\u0004bBAA\u0001\u0011E\u00131\u0011\u0005\b\u0003\u001f\u0003A\u0011KAI\u0011\u001d\t\u0019\n\u0001C!\u0003+Cq!a\u0006\u0001\t\u0003\t9\nC\u0004\u0002\u001e\u0002!\t!a(\t\u000f\u0005\u0005\u0006\u0001\"\u0003\u0002$\"9\u0011\u0011\u0016\u0001\u0005\u0012\u0005-\u0006bBA[\u0001\u0011E\u0011q\u0017\u0005\b\u0003\u007f\u0003A\u0011AAa\u0011\u001d\t9\r\u0001C!\u0003\u0013Dq!a4\u0001\t\u0013\t\t\u000eC\u0004\u0002V\u0002!I!a6\t\u000f\u0005u\u0007\u0001\"\u0001\u0002`\"9\u00111\u001d\u0001\u0005\u0002\u0005\u0015\bbBAy\u0001\u0011\u0005\u00111\u001f\u0005\b\u0003k\u0004A\u0011AA|\u0011\u001d\u0011y\u0001\u0001C!\u0005#AqAa\u0005\u0001\t\u0003\u0011)\u0002C\u0004\u0003\u0018\u0001!\tE!\u0007\t\u000f\t\u0005\u0002\u0001\"\u0001\u0002\u0016\"9!1\u0005\u0001\u0005\u0002\te\u0001b\u0002B\u0013\u0001\u0011\u0005!q\u0005\u0005\b\u0005_\u0001A\u0011\tB\u0019\u0011\u001d\u0011\u0019\u0005\u0001C!\u0005\u000bBqA!\u0014\u0001\t\u0003\ny\nC\u0004\u0003P\u0001!\tE!\u0015\t\u000f\t\u001d\u0004\u0001\"\u0001\u0003j!A!1\u0018\u0001!\n#\u0011\t\u0010\u0003\u0005\u0003t\u0002\u0001K\u0011\u000bB{\u000f\u001d\u0011i\u0007\u000fE\u0001\u0005_2aa\u000e\u001d\t\u0002\tE\u0004BB>2\t\u0003\u0011Y\bC\u0004\u0003~E\"\tAa \t\u000f\u0005M\u0015\u0007\"\u0001\u0003\u0012\"9\u00111\\\u0019\u0005\u0002\t\u0005\u0006\"\u0003B^c\u0005\u0005I\u0011\u0002B_\u00055\u0001&/[8sSRL\u0018+^3vK*\u0011\u0011HO\u0001\b[V$\u0018M\u00197f\u0015\tYD(\u0001\u0006d_2dWm\u0019;j_:T\u0011!P\u0001\u0006g\u000e\fG.Y\u0002\u0001+\t\u0001uiE\u0005\u0001\u0003F#&,\u00181dMB\u0019!iQ#\u000e\u0003aJ!\u0001\u0012\u001d\u0003!\u0005\u00137\u000f\u001e:bGRLE/\u001a:bE2,\u0007C\u0001$H\u0019\u0001!Q\u0001\u0013\u0001C\u0002%\u0013\u0011!Q\t\u0003\u0015:\u0003\"a\u0013'\u000e\u0003qJ!!\u0014\u001f\u0003\u000f9{G\u000f[5oOB\u00111jT\u0005\u0003!r\u00121!\u00118z!\r\u0011%+R\u0005\u0003'b\u0012\u0001\"\u0013;fe\u0006\u0014G.\u001a\t\u0006+Z+\u0005,W\u0007\u0002u%\u0011qK\u000f\u0002\f\u0013R,'/\u00192mK>\u00038\u000f\u0005\u0002C%B\u0019!\tA#\u0011\u000bU[V\tW-\n\u0005qS$AG*ue&\u001cGo\u00149uS6L'0\u001a3Ji\u0016\u0014\u0018M\u00197f\u001fB\u001c\b\u0003\u0002\"_\u000bfK!a\u0018\u001d\u0003\u000f\t+\u0018\u000e\u001c3feB\u0019!)Y-\n\u0005\tD$!C\"m_:,\u0017M\u00197f!\r\u0011E-R\u0005\u0003Kb\u0012\u0001b\u0012:po\u0006\u0014G.\u001a\t\u0003O>t!\u0001[7\u000f\u0005%dW\"\u00016\u000b\u0005-t\u0014A\u0002\u001fs_>$h(C\u0001>\u0013\tqG(A\u0004qC\u000e\\\u0017mZ3\n\u0005A\f(\u0001D*fe&\fG.\u001b>bE2,'B\u00018=\u0003\ry'\u000fZ\u000b\u0002iB\u0019Q\u000f_#\u000e\u0003YT!a\u001e\u001f\u0002\t5\fG\u000f[\u0005\u0003sZ\u0014\u0001b\u0014:eKJLgnZ\u0001\u0005_J$\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0002{R\u0011\u0011L \u0005\u0006e\u000e\u0001\u001d\u0001\u001e\u0002\u0015%\u0016\u001c\u0018N_1cY\u0016\f%O]1z\u0003\u000e\u001cWm]:\u0016\t\u0005\r\u0011QB\n\u0004\t\u0005\u0015\u0001#\u0002\"\u0002\b\u0005-\u0011bAA\u0005q\tY\u0011I\u001d:bs\n+hMZ3s!\r1\u0015Q\u0002\u0003\u0007\u0003\u001f!!\u0019A%\u0003\u0005\u0005\u0003DCAA\n!\u0015\t)\u0002BA\u0006\u001b\u0005\u0001\u0011AC7ba&s\u0007\u000b\\1dKR!\u00111DA\u000f\u001b\u0005!\u0001bBA\u0010\r\u0001\u0007\u0011\u0011E\u0001\u0002MB91*a\t\u0002\f\u0005-\u0011bAA\u0013y\tIa)\u001e8di&|g.M\u0001\ba~\u001b\u0018N_31+\t\tY\u0003E\u0002L\u0003[I1!a\f=\u0005\rIe\u000e^\u0001\fa~\u001b\u0018N_31?\u0012*\u0017\u000f\u0006\u0003\u00026\u0005m\u0002cA&\u00028%\u0019\u0011\u0011\b\u001f\u0003\tUs\u0017\u000e\u001e\u0005\b\u0003{A\u0001\u0019AA\u0016\u0003\u0005\u0019\u0018a\u00029`CJ\u0014\u0018-_\u000b\u0003\u0003\u0007\u0002RaSA#\u0003\u0013J1!a\u0012=\u0005\u0015\t%O]1z!\rY\u00151J\u0005\u0004\u0003\u001bb$AB!osJ+g-\u0001\u0007q?\u0016t7/\u001e:f'&TX\r\u0006\u0003\u00026\u0005M\u0003bBA+\u0015\u0001\u0007\u00111F\u0001\u0002]\u00061\u0002oX3ogV\u0014X-\u00113eSRLwN\\1m'&TX\r\u0006\u0003\u00026\u0005m\u0003bBA+\u0017\u0001\u0007\u00111F\u0001\u0007a~\u001bx/\u00199\u0015\r\u0005U\u0012\u0011MA3\u0011\u001d\t\u0019\u0007\u0004a\u0001\u0003W\t\u0011!\u0019\u0005\b\u0003Ob\u0001\u0019AA\u0016\u0003\u0005\u0011\u0017A\u0002:fg\u0006\u0014(/\u0006\u0002\u0002nA!\u0011Q\u0003\u0003F\u0003\u001d\u0011Xm]1se\u0002\na\u0001\\3oORD\u0017\u0001B:ju\u0016\f\u0011b\u001b8po:\u001c\u0016N_3\u0002\u000f%\u001cX)\u001c9usV\u0011\u00111\u0010\t\u0004\u0017\u0006u\u0014bAA@y\t9!i\\8mK\u0006t\u0017\u0001\u00044s_6\u001c\u0006/Z2jM&\u001cGcA-\u0002\u0006\"9\u0011qQ\nA\u0002\u0005%\u0015\u0001B2pY2\u0004B!VAF\u000b&\u0019\u0011Q\u0012\u001e\u0003\u0019%#XM]1cY\u0016|enY3\u0002%9,wo\u00159fG&4\u0017n\u0019\"vS2$WM]\u000b\u0002;\u0006)Q-\u001c9usV\t\u0011\f\u0006\u0003\u0002\u0016\u0005e\u0005bBA\u0010-\u0001\u0007\u00111\u0014\t\u0006\u0017\u0006\rR)R\u0001\u0007e\u0016\u001cX\u000f\u001c;\u0015\u0003e\u000b1\u0001^8B)\r)\u0015Q\u0015\u0005\b\u0003OC\u0002\u0019AA%\u0003\u0005A\u0018!\u00024jqV\u0003HCBA\u001b\u0003[\u000b\t\fC\u0004\u00020f\u0001\r!a\u0011\u0002\u0005\u0005\u001c\bbBAZ3\u0001\u0007\u00111F\u0001\u0002[\u00069a-\u001b=E_^tG\u0003CA>\u0003s\u000bY,!0\t\u000f\u0005=&\u00041\u0001\u0002D!9\u00111\u0017\u000eA\u0002\u0005-\u0002bBA+5\u0001\u0007\u00111F\u0001\u0007C\u0012$wJ\\3\u0015\t\u0005U\u00111\u0019\u0005\u0007\u0003\u000b\\\u0002\u0019A#\u0002\t\u0015dW-\\\u0001\u0007C\u0012$\u0017\t\u001c7\u0015\t\u0005U\u00111\u001a\u0005\b\u0003\u001bd\u0002\u0019AAE\u0003\tA8/A\u0005v]N\fg-Z!eIR!\u0011QGAj\u0011\u0019\t)-\ba\u0001\u000b\u00069\u0001.Z1qS\u001aLH\u0003BA\u001b\u00033Dq!a7\u001f\u0001\u0004\tY#\u0001\u0003ge>l\u0017A\u0003\u0013qYV\u001cH\u0005\u001d7vgR\u0019\u0011,!9\t\u000f\u00055w\u00041\u0001\u0002\n\u00069QM\\9vKV,G\u0003BA\u001b\u0003ODq!!;!\u0001\u0004\tY/A\u0003fY\u0016l7\u000f\u0005\u0003L\u0003[,\u0015bAAxy\tQAH]3qK\u0006$X\r\u001a \u0002\u000f\u0011,\u0017/^3vKR\tQ)\u0001\u0006eKF,X-^3BY2,B!!?\u0003\nU\u0011\u00111 \t\u0007\u0003{\u0014\u0019Aa\u0002\u000e\u0005\u0005}(b\u0001B\u0001u\u0005I\u0011.\\7vi\u0006\u0014G.Z\u0005\u0005\u0005\u000b\tyPA\u0002TKF\u00042A\u0012B\u0005\t\u001d\u0011YA\tb\u0001\u0005\u001b\u0011!!Q\u0019\u0012\u0005\u0015s\u0015\u0001\u00025fC\u0012,\u0012!R\u0001\u0006G2,\u0017M\u001d\u000b\u0003\u0003k\t\u0001\"\u001b;fe\u0006$xN]\u000b\u0003\u00057\u0001B!\u0016B\u000f\u000b&\u0019!q\u0004\u001e\u0003\u0011%#XM]1u_J\fqA]3wKJ\u001cX-A\bsKZ,'o]3Ji\u0016\u0014\u0018\r^8s\u0003\u001d!x.U;fk\u0016,\"A!\u000b\u0011\t\t\u0013Y#R\u0005\u0004\u0005[A$!B)vKV,\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\tM\u0002\u0003\u0002B\u001b\u0005{qAAa\u000e\u0003:A\u0011\u0011\u000eP\u0005\u0004\u0005wa\u0014A\u0002)sK\u0012,g-\u0003\u0003\u0003@\t\u0005#AB*ue&twMC\u0002\u0003<q\na\u0001^8MSN$XC\u0001B$!\u0015\tiP!\u0013F\u0013\u0011\u0011Y%a@\u0003\t1K7\u000f^\u0001\u0006G2|g.Z\u0001\fG>\u0004\u0018\u0010V8BeJ\f\u00170\u0006\u0003\u0003T\tmC\u0003CA\u0016\u0005+\u0012yFa\u0019\t\u000f\u00055G\u00061\u0001\u0003XA)1*!\u0012\u0003ZA\u0019aIa\u0017\u0005\u000f\tuCF1\u0001\u0003\u000e\t\t!\tC\u0004\u0003b1\u0002\r!a\u000b\u0002\u000bM$\u0018M\u001d;\t\u000f\t\u0015D\u00061\u0001\u0002,\u0005\u0019A.\u001a8\u0002!=\u0014H-\u001a:fI\u000e{W\u000e]1oS>tWC\u0001B6\u001d\t\u0011\u0005'A\u0007Qe&|'/\u001b;z#V,W/\u001a\t\u0003\u0005F\u001aR!MA%\u0005g\u0002R!\u0016B;\u0005sJ1Aa\u001e;\u0005U\u0019vN\u001d;fI&#XM]1cY\u00164\u0015m\u0019;pef\u0004\"A\u0011\u0001\u0015\u0005\t=\u0014A\u00038fo\n+\u0018\u000e\u001c3feV!!\u0011\u0011BD)\u0011\u0011\u0019Ia#\u0011\r\ts&Q\u0011BE!\r1%q\u0011\u0003\u0006\u0011N\u0012\r!\u0013\t\u0005\u0005\u0002\u0011)\tC\u0005\u0003\u000eN\n\t\u0011q\u0001\u0003\u0010\u0006QQM^5eK:\u001cW\rJ\u0019\u0011\tUD(QQ\u000b\u0005\u0005'\u0013I\n\u0006\u0003\u0003\u0016\nm\u0005\u0003\u0002\"\u0001\u0005/\u00032A\u0012BM\t\u0015AEG1\u0001J\u0011%\u0011i\nNA\u0001\u0002\b\u0011y*\u0001\u0006fm&$WM\\2fII\u0002B!\u001e=\u0003\u0018V!!1\u0015BV)\u0011\u0011)K!.\u0015\t\t\u001d&q\u0016\t\u0005\u0005\u0002\u0011I\u000bE\u0002G\u0005W#aA!,6\u0005\u0004I%!A#\t\u0013\tEV'!AA\u0004\tM\u0016AC3wS\u0012,gnY3%gA!Q\u000f\u001fBU\u0011\u001d\u00119,\u000ea\u0001\u0005s\u000b!!\u001b;\u0011\u000bU\u000bYI!+\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\t}\u0006\u0003\u0002Ba\u0005\u0017l!Aa1\u000b\t\t\u0015'qY\u0001\u0005Y\u0006twM\u0003\u0002\u0003J\u0006!!.\u0019<b\u0013\u0011\u0011iMa1\u0003\r=\u0013'.Z2uQ\u001d\t$\u0011\u001bBl\u00053\u00042a\u0013Bj\u0013\r\u0011)\u000e\u0010\u0002\u0011'\u0016\u0014\u0018.\u00197WKJ\u001c\u0018n\u001c8V\u0013\u0012\u000bQA^1mk\u0016t\u0012a\u0001\u0015\ba\tE'q\u001bBmQ-i#q\u001cBs\u0005O\u0014YO!<\u0011\u0007-\u0013\t/C\u0002\u0003dr\u0012!\u0002Z3qe\u0016\u001c\u0017\r^3e\u0003\u001diWm]:bO\u0016\f#A!;\u00027U\u001bX\r\t1Qe&|'/\u001b;z#V,W/\u001a1!S:\u001cH/Z1e\u0003\u0015\u0019\u0018N\\2fC\t\u0011y/\u0001\u00043]E\u001ad\u0006\r\u000b\u0003\u0003\u0013\n\u0011b\u00197bgNt\u0015-\\3\u0016\u0005\t]\b\u0003\u0002Ba\u0005sLAAa\u0010\u0003D\u0002"
)
public class PriorityQueue extends AbstractIterable implements StrictOptimizedIterableOps, Builder, Cloneable, Serializable {
   private final Ordering ord;
   private final ResizableArrayAccess scala$collection$mutable$PriorityQueue$$resarr;

   public static PriorityQueue from(final IterableOnce it, final Ordering evidence$3) {
      return PriorityQueue$.MODULE$.from(it, evidence$3);
   }

   public static Builder newBuilder(final Ordering evidence$1) {
      PriorityQueue$ var10000 = PriorityQueue$.MODULE$;
      return new Builder(evidence$1) {
         private final PriorityQueue pq;

         public void sizeHint(final int size) {
            Builder.sizeHint$(this, size);
         }

         public final void sizeHint(final IterableOnce coll, final int delta) {
            Builder.sizeHint$(this, coll, delta);
         }

         public final int sizeHint$default$2() {
            return Builder.sizeHint$default$2$(this);
         }

         public final void sizeHintBounded(final int size, final scala.collection.Iterable boundingColl) {
            Builder.sizeHintBounded$(this, size, boundingColl);
         }

         public Builder mapResult(final Function1 f) {
            return Builder.mapResult$(this, f);
         }

         public final Growable $plus$eq(final Object elem) {
            return Growable.$plus$eq$(this, elem);
         }

         /** @deprecated */
         public final Growable $plus$eq(final Object elem1, final Object elem2, final scala.collection.immutable.Seq elems) {
            return Growable.$plus$eq$(this, elem1, elem2, elems);
         }

         public Growable addAll(final IterableOnce elems) {
            return Growable.addAll$(this, elems);
         }

         public final Growable $plus$plus$eq(final IterableOnce elems) {
            return Growable.$plus$plus$eq$(this, elems);
         }

         public int knownSize() {
            return Growable.knownSize$(this);
         }

         private PriorityQueue pq() {
            return this.pq;
         }

         public <undefinedtype> addOne(final Object elem) {
            this.pq().scala$collection$mutable$PriorityQueue$$unsafeAdd(elem);
            return this;
         }

         public PriorityQueue result() {
            this.pq().scala$collection$mutable$PriorityQueue$$heapify(1);
            return this.pq();
         }

         public void clear() {
            this.pq().clear();
         }

         public {
            this.pq = new PriorityQueue(evidence$1$1);
         }
      };
   }

   public static Factory evidenceIterableFactory(final Object evidence$13) {
      return EvidenceIterableFactory.evidenceIterableFactory$(PriorityQueue$.MODULE$, evidence$13);
   }

   public static Object unfold(final Object init, final Function1 f, final Object evidence$11) {
      PriorityQueue$ unfold_this = PriorityQueue$.MODULE$;
      IterableOnce from_it = new View.Unfold(init, f);
      return unfold_this.from(from_it, (Ordering)evidence$11);
   }

   public static Object iterate(final Object start, final int len, final Function1 f, final Object evidence$10) {
      PriorityQueue$ iterate_this = PriorityQueue$.MODULE$;
      IterableOnce from_it = new View.Iterate(start, len, f);
      return iterate_this.from(from_it, (Ordering)evidence$10);
   }

   public static Object tabulate(final int n, final Function1 f, final Object evidence$9) {
      PriorityQueue$ tabulate_this = PriorityQueue$.MODULE$;
      IterableOnce from_it = new View.Tabulate(n, f);
      return tabulate_this.from(from_it, (Ordering)evidence$9);
   }

   public static Object fill(final int n, final Function0 elem, final Object evidence$8) {
      PriorityQueue$ fill_this = PriorityQueue$.MODULE$;
      IterableOnce from_it = new View.Fill(n, elem);
      return fill_this.from(from_it, (Ordering)evidence$8);
   }

   public static Object apply(final scala.collection.immutable.Seq xs, final Object evidence$7) {
      return PriorityQueue$.MODULE$.from(xs, (Ordering)((Ordering)evidence$7));
   }

   // $FF: synthetic method
   public Object scala$collection$mutable$Cloneable$$super$clone() {
      return super.clone();
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

   public final void sizeHintBounded(final int size, final scala.collection.Iterable boundingColl) {
      Builder.sizeHintBounded$(this, size, boundingColl);
   }

   public Builder mapResult(final Function1 f) {
      return Builder.mapResult$(this, f);
   }

   public final Growable $plus$eq(final Object elem) {
      return Growable.$plus$eq$(this, elem);
   }

   /** @deprecated */
   public final Growable $plus$eq(final Object elem1, final Object elem2, final scala.collection.immutable.Seq elems) {
      return Growable.$plus$eq$(this, elem1, elem2, elems);
   }

   public final Growable $plus$plus$eq(final IterableOnce elems) {
      return Growable.$plus$plus$eq$(this, elems);
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

   public Ordering ord() {
      return this.ord;
   }

   public ResizableArrayAccess scala$collection$mutable$PriorityQueue$$resarr() {
      return this.scala$collection$mutable$PriorityQueue$$resarr;
   }

   public int length() {
      return this.scala$collection$mutable$PriorityQueue$$resarr().length() - 1;
   }

   public int size() {
      return this.length();
   }

   public int knownSize() {
      return this.length();
   }

   public boolean isEmpty() {
      return this.scala$collection$mutable$PriorityQueue$$resarr().p_size0() < 2;
   }

   public PriorityQueue fromSpecific(final IterableOnce coll) {
      return PriorityQueue$.MODULE$.from(coll, this.ord());
   }

   public Builder newSpecificBuilder() {
      PriorityQueue$ var10000 = PriorityQueue$.MODULE$;
      Ordering newBuilder_evidence$1 = this.ord();
      return new Builder(newBuilder_evidence$1) {
         private final PriorityQueue pq;

         public void sizeHint(final int size) {
            Builder.sizeHint$(this, size);
         }

         public final void sizeHint(final IterableOnce coll, final int delta) {
            Builder.sizeHint$(this, coll, delta);
         }

         public final int sizeHint$default$2() {
            return Builder.sizeHint$default$2$(this);
         }

         public final void sizeHintBounded(final int size, final scala.collection.Iterable boundingColl) {
            Builder.sizeHintBounded$(this, size, boundingColl);
         }

         public Builder mapResult(final Function1 f) {
            return Builder.mapResult$(this, f);
         }

         public final Growable $plus$eq(final Object elem) {
            return Growable.$plus$eq$(this, elem);
         }

         /** @deprecated */
         public final Growable $plus$eq(final Object elem1, final Object elem2, final scala.collection.immutable.Seq elems) {
            return Growable.$plus$eq$(this, elem1, elem2, elems);
         }

         public Growable addAll(final IterableOnce elems) {
            return Growable.addAll$(this, elems);
         }

         public final Growable $plus$plus$eq(final IterableOnce elems) {
            return Growable.$plus$plus$eq$(this, elems);
         }

         public int knownSize() {
            return Growable.knownSize$(this);
         }

         private PriorityQueue pq() {
            return this.pq;
         }

         public <undefinedtype> addOne(final Object elem) {
            this.pq().scala$collection$mutable$PriorityQueue$$unsafeAdd(elem);
            return this;
         }

         public PriorityQueue result() {
            this.pq().scala$collection$mutable$PriorityQueue$$heapify(1);
            return this.pq();
         }

         public void clear() {
            this.pq().clear();
         }

         public {
            this.pq = new PriorityQueue(evidence$1$1);
         }
      };
   }

   public PriorityQueue empty() {
      PriorityQueue$ var10000 = PriorityQueue$.MODULE$;
      Ordering empty_evidence$2 = this.ord();
      return new PriorityQueue(empty_evidence$2);
   }

   public PriorityQueue mapInPlace(final Function1 f) {
      this.scala$collection$mutable$PriorityQueue$$resarr().mapInPlace(f);
      this.scala$collection$mutable$PriorityQueue$$heapify(1);
      return this;
   }

   public PriorityQueue result() {
      return this;
   }

   public Object scala$collection$mutable$PriorityQueue$$toA(final Object x) {
      return x;
   }

   public void fixUp(final Object[] as, final int m) {
      for(int k = m; k > 1 && this.ord().lt(as[k / 2], as[k]); k /= 2) {
         this.scala$collection$mutable$PriorityQueue$$resarr().p_swap(k, k / 2);
      }

   }

   public boolean fixDown(final Object[] as, final int m, final int n) {
      int k;
      int j;
      for(k = m; n >= 2 * k; k = j) {
         j = 2 * k;
         if (j < n && this.ord().lt(as[j], as[j + 1])) {
            ++j;
         }

         if (this.ord().gteq(as[k], as[j])) {
            if (k != m) {
               return true;
            }

            return false;
         }

         Object h = as[k];
         as[k] = as[j];
         as[j] = h;
      }

      if (k != m) {
         return true;
      } else {
         return false;
      }
   }

   public PriorityQueue addOne(final Object elem) {
      this.scala$collection$mutable$PriorityQueue$$resarr().p_ensureAdditionalSize(1);
      this.scala$collection$mutable$PriorityQueue$$resarr().p_array()[this.scala$collection$mutable$PriorityQueue$$resarr().p_size0()] = elem;
      this.fixUp(this.scala$collection$mutable$PriorityQueue$$resarr().p_array(), this.scala$collection$mutable$PriorityQueue$$resarr().p_size0());
      this.scala$collection$mutable$PriorityQueue$$resarr().p_size0_$eq(this.scala$collection$mutable$PriorityQueue$$resarr().p_size0() + 1);
      return this;
   }

   public PriorityQueue addAll(final IterableOnce xs) {
      int from = this.scala$collection$mutable$PriorityQueue$$resarr().p_size0();
      xs.iterator().foreach((x) -> {
         $anonfun$addAll$1(this, x);
         return BoxedUnit.UNIT;
      });
      this.scala$collection$mutable$PriorityQueue$$heapify(from);
      return this;
   }

   public void scala$collection$mutable$PriorityQueue$$unsafeAdd(final Object elem) {
      this.scala$collection$mutable$PriorityQueue$$resarr().p_ensureAdditionalSize(1);
      this.scala$collection$mutable$PriorityQueue$$resarr().p_array()[this.scala$collection$mutable$PriorityQueue$$resarr().p_size0()] = elem;
      this.scala$collection$mutable$PriorityQueue$$resarr().p_size0_$eq(this.scala$collection$mutable$PriorityQueue$$resarr().p_size0() + 1);
   }

   public void scala$collection$mutable$PriorityQueue$$heapify(final int from) {
      int n = this.length();
      if (from <= 2) {
         RichInt$ var34 = RichInt$.MODULE$;
         int var6 = n / 2;
         int to$extension_end = 1;
         Range$ var35 = Range$.MODULE$;
         Range var36 = (new Range.Inclusive(var6, to$extension_end, 1)).by(-1);
         if (var36 == null) {
            throw null;
         } else {
            Range foreach_this = var36;
            if (!foreach_this.isEmpty()) {
               int foreach_i = foreach_this.start();

               while(true) {
                  $anonfun$heapify$1(this, n, foreach_i);
                  if (foreach_i == foreach_this.scala$collection$immutable$Range$$lastElement) {
                     break;
                  }

                  foreach_i += foreach_this.step();
               }
            }

         }
      } else if (n - from < 4) {
         RichInt$ var32 = RichInt$.MODULE$;
         Range$ var33 = Range$.MODULE$;
         Range foreach$mVc$sp_this = new Range.Inclusive(from, n, 1);
         if (!foreach$mVc$sp_this.isEmpty()) {
            int foreach$mVc$sp_i = foreach$mVc$sp_this.start();

            while(true) {
               $anonfun$heapify$2(this, foreach$mVc$sp_i);
               if (foreach$mVc$sp_i == foreach$mVc$sp_this.scala$collection$immutable$Range$$lastElement) {
                  break;
               }

               foreach$mVc$sp_i += foreach$mVc$sp_this.step();
            }
         }

      } else {
         int create_e = from / 2;
         int var21 = create_e;
         Queue$ var10000 = Queue$.MODULE$;
         scala.collection.immutable.ArraySeq apply_elems = ScalaRunTime$.MODULE$.wrapIntArray(new int[]{create_e});
         Queue$ apply_this = var10000;
         Queue var28 = apply_this.from(apply_elems);
         Object var23 = null;
         apply_elems = null;
         Queue queue = var28;
         RichInt$ var29 = RichInt$.MODULE$;
         int var7 = n / 2;
         Range$ var30 = Range$.MODULE$;
         Range var31 = (new Range.Exclusive(var7, create_e, 1)).by(-1);
         if (var31 == null) {
            throw null;
         } else {
            Range foreach_this = var31;
            if (!foreach_this.isEmpty()) {
               int foreach_i = foreach_this.start();

               while(true) {
                  if (this.fixDown(this.scala$collection$mutable$PriorityQueue$$resarr().p_array(), foreach_i, n)) {
                     int $anonfun$heapify$3_parent = foreach_i / 2;
                     if ($anonfun$heapify$3_parent < var21) {
                        var21 = $anonfun$heapify$3_parent;
                        Integer $anonfun$heapify$3_$plus$eq_elem = $anonfun$heapify$3_parent;
                        if (queue == null) {
                           throw null;
                        }

                        queue.addOne($anonfun$heapify$3_$plus$eq_elem);
                        Object var26 = null;
                     }
                  }

                  Object var27 = null;
                  if (foreach_i == foreach_this.scala$collection$immutable$Range$$lastElement) {
                     break;
                  }

                  foreach_i += foreach_this.step();
               }
            }

            Object var25 = null;

            while(queue.nonEmpty()) {
               int i = BoxesRunTime.unboxToInt(queue.dequeue());
               if (this.fixDown(this.scala$collection$mutable$PriorityQueue$$resarr().p_array(), i, n)) {
                  int parent = i / 2;
                  if (parent < var21 && parent > 0) {
                     var21 = parent;
                     Object $plus$eq_elem = parent;
                     queue.addOne($plus$eq_elem);
                     $plus$eq_elem = null;
                  }
               }
            }

         }
      }
   }

   public PriorityQueue $plus$plus(final IterableOnce xs) {
      PriorityQueue var10000 = this.clone();
      if (var10000 == null) {
         throw null;
      } else {
         return var10000.addAll(xs);
      }
   }

   public void enqueue(final scala.collection.immutable.Seq elems) {
      this.addAll(elems);
   }

   public Object dequeue() {
      if (this.scala$collection$mutable$PriorityQueue$$resarr().p_size0() > 1) {
         this.scala$collection$mutable$PriorityQueue$$resarr().p_size0_$eq(this.scala$collection$mutable$PriorityQueue$$resarr().p_size0() - 1);
         Object result = this.scala$collection$mutable$PriorityQueue$$resarr().p_array()[1];
         this.scala$collection$mutable$PriorityQueue$$resarr().p_array()[1] = this.scala$collection$mutable$PriorityQueue$$resarr().p_array()[this.scala$collection$mutable$PriorityQueue$$resarr().p_size0()];
         this.scala$collection$mutable$PriorityQueue$$resarr().p_array()[this.scala$collection$mutable$PriorityQueue$$resarr().p_size0()] = null;
         this.fixDown(this.scala$collection$mutable$PriorityQueue$$resarr().p_array(), 1, this.scala$collection$mutable$PriorityQueue$$resarr().p_size0() - 1);
         return result;
      } else {
         throw new NoSuchElementException("no element to remove from heap");
      }
   }

   public scala.collection.immutable.Seq dequeueAll() {
      Object var17;
      label122: {
         label125: {
            ArrayBuilder$ var10000 = ArrayBuilder$.MODULE$;
            ClassTag make_evidence$1 = ClassTag$.MODULE$.Any();
            Class var3 = make_evidence$1.runtimeClass();
            Class var8 = Byte.TYPE;
            if (var8 == null) {
               if (var3 == null) {
                  break label125;
               }
            } else if (var8.equals(var3)) {
               break label125;
            }

            label126: {
               var8 = Short.TYPE;
               if (var8 == null) {
                  if (var3 == null) {
                     break label126;
                  }
               } else if (var8.equals(var3)) {
                  break label126;
               }

               label127: {
                  var8 = Character.TYPE;
                  if (var8 == null) {
                     if (var3 == null) {
                        break label127;
                     }
                  } else if (var8.equals(var3)) {
                     break label127;
                  }

                  label128: {
                     var8 = Integer.TYPE;
                     if (var8 == null) {
                        if (var3 == null) {
                           break label128;
                        }
                     } else if (var8.equals(var3)) {
                        break label128;
                     }

                     label129: {
                        var8 = Long.TYPE;
                        if (var8 == null) {
                           if (var3 == null) {
                              break label129;
                           }
                        } else if (var8.equals(var3)) {
                           break label129;
                        }

                        label130: {
                           var8 = Float.TYPE;
                           if (var8 == null) {
                              if (var3 == null) {
                                 break label130;
                              }
                           } else if (var8.equals(var3)) {
                              break label130;
                           }

                           label131: {
                              var8 = Double.TYPE;
                              if (var8 == null) {
                                 if (var3 == null) {
                                    break label131;
                                 }
                              } else if (var8.equals(var3)) {
                                 break label131;
                              }

                              label132: {
                                 var8 = Boolean.TYPE;
                                 if (var8 == null) {
                                    if (var3 == null) {
                                       break label132;
                                    }
                                 } else if (var8.equals(var3)) {
                                    break label132;
                                 }

                                 label65: {
                                    var8 = Void.TYPE;
                                    if (var8 == null) {
                                       if (var3 == null) {
                                          break label65;
                                       }
                                    } else if (var8.equals(var3)) {
                                       break label65;
                                    }

                                    var17 = new ArrayBuilder.ofRef(make_evidence$1);
                                    break label122;
                                 }

                                 var17 = new ArrayBuilder.ofUnit();
                                 break label122;
                              }

                              var17 = new ArrayBuilder.ofBoolean();
                              break label122;
                           }

                           var17 = new ArrayBuilder.ofDouble();
                           break label122;
                        }

                        var17 = new ArrayBuilder.ofFloat();
                        break label122;
                     }

                     var17 = new ArrayBuilder.ofLong();
                     break label122;
                  }

                  var17 = new ArrayBuilder.ofInt();
                  break label122;
               }

               var17 = new ArrayBuilder.ofChar();
               break label122;
            }

            var17 = new ArrayBuilder.ofShort();
            break label122;
         }

         var17 = new ArrayBuilder.ofByte();
      }

      Object var5 = null;
      Object var6 = null;
      ArrayBuilder b = (ArrayBuilder)var17;
      b.sizeHint(this.length());

      while(this.nonEmpty()) {
         Object $plus$eq_elem = this.dequeue();
         b.addOne($plus$eq_elem);
         $plus$eq_elem = null;
      }

      return scala.collection.immutable.ArraySeq$.MODULE$.unsafeWrapArray(b.result());
   }

   public Object head() {
      if (this.scala$collection$mutable$PriorityQueue$$resarr().p_size0() > 1) {
         return this.scala$collection$mutable$PriorityQueue$$resarr().p_array()[1];
      } else {
         throw new NoSuchElementException("queue is empty");
      }
   }

   public void clear() {
      this.scala$collection$mutable$PriorityQueue$$resarr().clear();
      this.scala$collection$mutable$PriorityQueue$$resarr().p_size0_$eq(1);
   }

   public Iterator iterator() {
      return this.scala$collection$mutable$PriorityQueue$$resarr().iterator().drop(1);
   }

   public PriorityQueue reverse() {
      PriorityQueue revq = new PriorityQueue(this.ord().reverse());
      int n = this.scala$collection$mutable$PriorityQueue$$resarr().p_size0();
      revq.scala$collection$mutable$PriorityQueue$$resarr().p_ensureSize(n);
      revq.scala$collection$mutable$PriorityQueue$$resarr().p_size0_$eq(n);
      Object[] from = this.scala$collection$mutable$PriorityQueue$$resarr().p_array();
      Object[] to = revq.scala$collection$mutable$PriorityQueue$$resarr().p_array();
      RichInt$ var10000 = RichInt$.MODULE$;
      byte var5 = 1;
      Range$ var9 = Range$.MODULE$;
      Range foreach$mVc$sp_this = new Range.Exclusive(var5, n, 1);
      if (!foreach$mVc$sp_this.isEmpty()) {
         int foreach$mVc$sp_i = foreach$mVc$sp_this.start();

         while(true) {
            to[foreach$mVc$sp_i] = from[n - foreach$mVc$sp_i];
            if (foreach$mVc$sp_i == foreach$mVc$sp_this.scala$collection$immutable$Range$$lastElement) {
               break;
            }

            foreach$mVc$sp_i += foreach$mVc$sp_this.step();
         }
      }

      foreach$mVc$sp_this = null;
      revq.scala$collection$mutable$PriorityQueue$$heapify(1);
      return revq;
   }

   public Iterator reverseIterator() {
      return new AbstractIterator() {
         private int i;
         // $FF: synthetic field
         private final PriorityQueue $outer;

         public boolean hasNext() {
            return this.i >= 1;
         }

         public Object next() {
            Object n = this.$outer.scala$collection$mutable$PriorityQueue$$resarr().p_array()[this.i];
            --this.i;
            if (this.$outer == null) {
               throw null;
            } else {
               return n;
            }
         }

         public {
            if (PriorityQueue.this == null) {
               throw null;
            } else {
               this.$outer = PriorityQueue.this;
               this.i = PriorityQueue.this.scala$collection$mutable$PriorityQueue$$resarr().p_size0() - 1;
            }
         }
      };
   }

   public Queue toQueue() {
      Queue$ var10002 = Queue$.MODULE$;
      return (Queue)(new Queue(16)).addAll(this.iterator());
   }

   public String toString() {
      List var10000 = this.toList();
      String mkString_end = ")";
      String mkString_sep = ", ";
      String mkString_start = "PriorityQueue(";
      if (var10000 == null) {
         throw null;
      } else {
         return IterableOnceOps.mkString$(var10000, mkString_start, mkString_sep, mkString_end);
      }
   }

   public List toList() {
      List$ var10000 = List$.MODULE$;
      IterableOnce from_coll = this.iterator();
      return Nil$.MODULE$.prependedAll(from_coll);
   }

   public PriorityQueue clone() {
      PriorityQueue pq = new PriorityQueue(this.ord());
      int n = this.scala$collection$mutable$PriorityQueue$$resarr().p_size0();
      pq.scala$collection$mutable$PriorityQueue$$resarr().p_ensureSize(n);
      System.arraycopy(this.scala$collection$mutable$PriorityQueue$$resarr().p_array(), 1, pq.scala$collection$mutable$PriorityQueue$$resarr().p_array(), 1, n - 1);
      pq.scala$collection$mutable$PriorityQueue$$resarr().p_size0_$eq(n);
      return pq;
   }

   public int copyToArray(final Object xs, final int start, final int len) {
      IterableOnce$ var10000 = IterableOnce$.MODULE$;
      int var7 = this.length();
      int elemsToCopyToArray_destLen = Array.getLength(xs);
      int elemsToCopyToArray_srcLen = var7;
      scala.math.package$ var8 = scala.math.package$.MODULE$;
      var8 = scala.math.package$.MODULE$;
      var8 = scala.math.package$.MODULE$;
      int copied = Math.max(Math.min(Math.min(len, elemsToCopyToArray_srcLen), elemsToCopyToArray_destLen - start), 0);
      if (copied > 0) {
         Array$.MODULE$.copy(this.scala$collection$mutable$PriorityQueue$$resarr().p_array(), 1, xs, start, copied);
      }

      return copied;
   }

   /** @deprecated */
   public PriorityQueue$ orderedCompanion() {
      return PriorityQueue$.MODULE$;
   }

   public Object writeReplace() {
      return new DefaultSerializationProxy(EvidenceIterableFactory.evidenceIterableFactory$(PriorityQueue$.MODULE$, this.ord()), this);
   }

   public String className() {
      return "PriorityQueue";
   }

   // $FF: synthetic method
   public static final void $anonfun$addAll$1(final PriorityQueue $this, final Object x) {
      $this.scala$collection$mutable$PriorityQueue$$unsafeAdd(x);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$heapify$1(final PriorityQueue $this, final int n$1, final int i) {
      return $this.fixDown($this.scala$collection$mutable$PriorityQueue$$resarr().p_array(), i, n$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$heapify$2(final PriorityQueue $this, final int i) {
      $this.fixUp($this.scala$collection$mutable$PriorityQueue$$resarr().p_array(), i);
   }

   // $FF: synthetic method
   public static final Object $anonfun$heapify$3(final PriorityQueue $this, final int n$1, final IntRef min$1, final Queue queue$1, final int i) {
      if ($this.fixDown($this.scala$collection$mutable$PriorityQueue$$resarr().p_array(), i, n$1)) {
         int parent = i / 2;
         if (parent < min$1.elem) {
            min$1.elem = parent;
            Integer $plus$eq_elem = parent;
            if (queue$1 == null) {
               throw null;
            } else {
               return queue$1.addOne($plus$eq_elem);
            }
         } else {
            return BoxedUnit.UNIT;
         }
      } else {
         return BoxedUnit.UNIT;
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$reverse$1(final Object[] to$1, final Object[] from$1, final int n$2, final int i) {
      to$1[i] = from$1[n$2 - i];
   }

   public PriorityQueue(final Ordering ord) {
      this.ord = ord;
      this.scala$collection$mutable$PriorityQueue$$resarr = new ResizableArrayAccess();
      this.scala$collection$mutable$PriorityQueue$$resarr().p_size0_$eq(this.scala$collection$mutable$PriorityQueue$$resarr().p_size0() + 1);
   }

   // $FF: synthetic method
   public static final Object $anonfun$heapify$3$adapted(final PriorityQueue $this, final int n$1, final IntRef min$1, final Queue queue$1, final Object i) {
      return $anonfun$heapify$3($this, n$1, min$1, queue$1, BoxesRunTime.unboxToInt(i));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   private class ResizableArrayAccess extends ArrayBuffer {
      // $FF: synthetic field
      public final PriorityQueue $outer;

      public ResizableArrayAccess mapInPlace(final Function1 f) {
         int i = 1;

         for(int siz = this.length(); i < siz; ++i) {
            this.update(i, f.apply(this.apply(i)));
         }

         return this;
      }

      public int p_size0() {
         return this.size0();
      }

      public void p_size0_$eq(final int s) {
         this.size0_$eq(s);
      }

      public Object[] p_array() {
         return this.array();
      }

      public void p_ensureSize(final int n) {
         super.ensureSize(n);
      }

      public void p_ensureAdditionalSize(final int n) {
         super.ensureSize(this.size0() + n);
      }

      public void p_swap(final int a, final int b) {
         Object h = this.array()[a];
         this.array()[a] = this.array()[b];
         this.array()[b] = h;
      }

      // $FF: synthetic method
      public PriorityQueue scala$collection$mutable$PriorityQueue$ResizableArrayAccess$$$outer() {
         return this.$outer;
      }

      public ResizableArrayAccess() {
         if (PriorityQueue.this == null) {
            throw null;
         } else {
            this.$outer = PriorityQueue.this;
            super();
         }
      }
   }
}
