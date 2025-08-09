package scala.collection.mutable;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.EvidenceIterableFactory;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.SortedIterableFactory;
import scala.collection.SortedOps;
import scala.collection.SortedSetFactoryDefaults;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.StepperShape$;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.StrictOptimizedSetOps;
import scala.collection.StrictOptimizedSortedSetOps;
import scala.collection.View;
import scala.collection.convert.impl.AnyBinaryTreeStepper;
import scala.collection.convert.impl.AnyBinaryTreeStepper$;
import scala.collection.convert.impl.BinaryTreeStepper$;
import scala.collection.convert.impl.DoubleBinaryTreeStepper;
import scala.collection.convert.impl.DoubleBinaryTreeStepper$;
import scala.collection.convert.impl.IntBinaryTreeStepper;
import scala.collection.convert.impl.IntBinaryTreeStepper$;
import scala.collection.convert.impl.LongBinaryTreeStepper;
import scala.collection.convert.impl.LongBinaryTreeStepper$;
import scala.collection.generic.DefaultSerializable;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t\rg\u0001B\u001b7!uB\u0001B\u001b\u0001\u0003\u0006\u0004%Ia\u001b\u0005\tm\u0002\u0011\t\u0011)A\u0005Y\"Aq\u000f\u0001BC\u0002\u0013\r\u0001\u0010C\u0005\u0002\u0002\u0001\u0011\t\u0011)A\u0005s\"9\u00111\u0001\u0001\u0005\n\u0005\u0015\u0001bBA\u0002\u0001\u0011\u0005\u0011Q\u0002\u0005\b\u0003+\u0001A\u0011IA\f\u0011\u001d\ty\u0002\u0001C\u0001\u0003CAq!!\u000b\u0001\t\u0003\tY\u0003C\u0004\u00022\u0001!\t%a\r\t\u000f\u0005e\u0004\u0001\"\u0001\u0002|!9\u00111\u0011\u0001\u0005\u0002\u0005\u0015\u0005bBAE\u0001\u0011\u0005\u00111\u0012\u0005\b\u0003'\u0003A\u0011AAK\u0011\u001d\ty\n\u0001C\u0001\u0003CCq!a*\u0001\t\u0003\tI\u000b\u0003\u0005\u0002:\u0002\u0001K\u0011KA^\u0011\u001d\ti\r\u0001C!\u0003\u001fDq!a6\u0001\t\u0003\ny\rC\u0004\u0002Z\u0002!\t%a7\t\u000f\u0005u\u0007\u0001\"\u0011\u0002`\"9\u0011\u0011\u001d\u0001\u0005B\u0005}\u0007bBAr\u0001\u0011\u0005\u0013Q\u001d\u0005\b\u0003W\u0004A\u0011IAw\u0011\u001d\t\t\u0010\u0001C!\u0003g4\u0001Ba\u0002\u0001A\u00035!\u0011\u0002\u0005\u000b\u0003[S\"\u0011!Q\u0001\n\u0005=\u0006BCA\\5\t\u0005\t\u0015!\u0003\u00020\"9\u00111\u0001\u000e\u0005\u0002\t-\u0001\u0002\u0003B\n5\u0001&IA!\u0006\t\u0011\tm!\u0004)C\u0005\u0005;A\u0001Ba\t\u001bA\u0013%!Q\u0005\u0005\b\u0003OSB\u0011\tB\u0015\u0011\u001d\t\u0019J\u0007C!\u0005_Aq!a\b\u001b\t\u0003\n\t\u0003C\u0004\u0002*i!\tEa\r\t\u000f\u00055'\u0004\"\u0011\u0002P\"9\u0011q\u001b\u000e\u0005B\u0005=\u0007bBAm5\u0011\u0005\u00131\u001c\u0005\b\u0003;TB\u0011IAp\u0011\u001d\u00119D\u0007C!\u0005sAq!!9\u001b\t\u0003\ny\u000eC\u0004\u0003<i!\tE!\u000f\t\u000f\u0005E(\u0004\"\u0011\u0003>!9!\u0011\n\u000e\u0005B\t-sa\u0002B(m!\u0005!\u0011\u000b\u0004\u0007kYB\tAa\u0015\t\u000f\u0005\rq\u0006\"\u0001\u0003\\!9!QL\u0018\u0005\u0002\t}\u0003bBAW_\u0011\u0005!q\u000e\u0005\b\u0005\u0017{C\u0011\u0001BG\u0011%\u0011\tkLA\u0001\n\u0013\u0011\u0019KA\u0004Ue\u0016,7+\u001a;\u000b\u0005]B\u0014aB7vi\u0006\u0014G.\u001a\u0006\u0003si\n!bY8mY\u0016\u001cG/[8o\u0015\u0005Y\u0014!B:dC2\f7\u0001A\u000b\u0003}\u0015\u001b\u0002\u0002A P%^s\u0016\r\u001a\t\u0004\u0001\u0006\u001bU\"\u0001\u001c\n\u0005\t3$aC!cgR\u0014\u0018m\u0019;TKR\u0004\"\u0001R#\r\u0001\u0011)a\t\u0001b\u0001\u000f\n\t\u0011)\u0005\u0002I\u0019B\u0011\u0011JS\u0007\u0002u%\u00111J\u000f\u0002\b\u001d>$\b.\u001b8h!\tIU*\u0003\u0002Ou\t\u0019\u0011I\\=\u0011\u0007\u0001\u00036)\u0003\u0002Rm\tI1k\u001c:uK\u0012\u001cV\r\u001e\t\u0006\u0001N\u001bUKV\u0005\u0003)Z\u0012AbU8si\u0016$7+\u001a;PaN\u0004\"\u0001\u0011\u0001\u0011\u0007\u0001\u00031\tE\u0003Y3\u000e[f+D\u00019\u0013\tQ\u0006H\u0001\u000eTiJL7\r^(qi&l\u0017N_3e\u0013R,'/\u00192mK>\u00038\u000f\u0005\u0002A9&\u0011QL\u000e\u0002\u0004'\u0016$\b#\u0002-`\u0007V3\u0016B\u000119\u0005m\u0019FO]5di>\u0003H/[7ju\u0016$7k\u001c:uK\u0012\u001cV\r^(qgB)\u0001LY\"V7&\u00111\r\u000f\u0002\u0019'>\u0014H/\u001a3TKR4\u0015m\u0019;pef$UMZ1vYR\u001c\bCA3i\u001b\u00051'BA49\u0003\u001d9WM\\3sS\u000eL!!\u001b4\u0003'\u0011+g-Y;miN+'/[1mSj\f'\r\\3\u0002\tQ\u0014X-Z\u000b\u0002YB!Q\u000e]\"t\u001d\t\u0001e.\u0003\u0002pm\u0005a!+\u001a3CY\u0006\u001c7\u000e\u0016:fK&\u0011\u0011O\u001d\u0002\u0005)J,WM\u0003\u0002pmA\u0011\u0011\n^\u0005\u0003kj\u0012AAT;mY\u0006)AO]3fA\u0005AqN\u001d3fe&tw-F\u0001z!\rQXp\u0011\b\u0003\u0013nL!\u0001 \u001e\u0002\u000fA\f7m[1hK&\u0011ap \u0002\t\u001fJ$WM]5oO*\u0011APO\u0001\n_J$WM]5oO\u0002\na\u0001P5oSRtD\u0003BA\u0004\u0003\u0017!2AVA\u0005\u0011\u00159X\u0001q\u0001z\u0011\u0015QW\u00011\u0001m)\t\ty\u0001F\u0002W\u0003#Aa!a\u0005\u0007\u0001\bI\u0018aA8sI\u0006)2o\u001c:uK\u0012LE/\u001a:bE2,g)Y2u_JLXCAA\r!\u0011A\u00161D+\n\u0007\u0005u\u0001HA\u000bT_J$X\rZ%uKJ\f'\r\\3GC\u000e$xN]=\u0002\u0011%$XM]1u_J,\"!a\t\u0011\ta\u000b)cQ\u0005\u0004\u0003OA$\u0001C%uKJ\fGo\u001c:\u0002\u0019%$XM]1u_J4%o\\7\u0015\t\u0005\r\u0012Q\u0006\u0005\u0007\u0003_I\u0001\u0019A\"\u0002\u000bM$\u0018M\u001d;\u0002\u000fM$X\r\u001d9feV!\u0011QGA )\u0011\t9$a\u001c\u0013\r\u0005e\u0012QHA*\r\u0019\tY\u0004\u0001\u0001\u00028\taAH]3gS:,W.\u001a8u}A\u0019A)a\u0010\u0005\u000f\u0005\u0005#B1\u0001\u0002D\t\t1+E\u0002I\u0003\u000b\u0002D!a\u0012\u0002PA)\u0001,!\u0013\u0002N%\u0019\u00111\n\u001d\u0003\u000fM#X\r\u001d9feB\u0019A)a\u0014\u0005\u0017\u0005E\u0013qHA\u0001\u0002\u0003\u0015\ta\u0012\u0002\u0004?\u0012\n\u0004\u0003BA+\u0003SrA!a\u0016\u0002f9!\u0011\u0011LA2\u001d\u0011\tY&!\u0019\u000e\u0005\u0005u#bAA0y\u00051AH]8pizJ\u0011aO\u0005\u0003siJ1!a\u001a9\u0003\u001d\u0019F/\u001a9qKJLA!a\u001b\u0002n\tqQI\u001a4jG&,g\u000e^*qY&$(bAA4q!9\u0011\u0011\u000f\u0006A\u0004\u0005M\u0014!B:iCB,\u0007C\u0002-\u0002v\r\u000bi$C\u0002\u0002xa\u0012Ab\u0015;faB,'o\u00155ba\u0016\fa!\u00193e\u001f:,G\u0003BA?\u0003\u007fj\u0011\u0001\u0001\u0005\u0007\u0003\u0003[\u0001\u0019A\"\u0002\t\u0015dW-\\\u0001\fgV\u0014GO]1di>sW\r\u0006\u0003\u0002~\u0005\u001d\u0005BBAA\u0019\u0001\u00071)A\u0003dY\u0016\f'\u000f\u0006\u0002\u0002\u000eB\u0019\u0011*a$\n\u0007\u0005E%H\u0001\u0003V]&$\u0018\u0001C2p]R\f\u0017N\\:\u0015\t\u0005]\u0015Q\u0014\t\u0004\u0013\u0006e\u0015bAANu\t9!i\\8mK\u0006t\u0007BBAA\u001d\u0001\u00071)A\u0007v]\u000e|gn\u001d;sC&tW\rZ\u000b\u0003\u0003G\u0003B\u0001WAS\u0007&\u0011Q\fO\u0001\ne\u0006tw-Z%na2$RAVAV\u0003kCq!!,\u0011\u0001\u0004\ty+\u0001\u0003ge>l\u0007\u0003B%\u00022\u000eK1!a-;\u0005\u0019y\u0005\u000f^5p]\"9\u0011q\u0017\tA\u0002\u0005=\u0016!B;oi&d\u0017!C2mCN\u001ch*Y7f+\t\ti\f\u0005\u0003\u0002@\u0006\u001dg\u0002BAa\u0003\u0007\u00042!a\u0017;\u0013\r\t)MO\u0001\u0007!J,G-\u001a4\n\t\u0005%\u00171\u001a\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0005\u0015'(\u0001\u0003tSj,WCAAi!\rI\u00151[\u0005\u0004\u0003+T$aA%oi\u0006I1N\\8x]NK'0Z\u0001\bSN,U\u000e\u001d;z+\t\t9*\u0001\u0003iK\u0006$W#A\"\u0002\t1\f7\u000f^\u0001\t[&t\u0017I\u001a;feR!\u0011qVAt\u0011\u0019\tIo\u0006a\u0001\u0007\u0006\u00191.Z=\u0002\u00135\f\u0007PQ3g_J,G\u0003BAX\u0003_Da!!;\u0019\u0001\u0004\u0019\u0015a\u00024pe\u0016\f7\r[\u000b\u0005\u0003k\u0014\u0019\u0001\u0006\u0003\u0002\u000e\u0006]\bbBA}3\u0001\u0007\u00111`\u0001\u0002MB1\u0011*!@D\u0005\u0003I1!a@;\u0005%1UO\\2uS>t\u0017\u0007E\u0002E\u0005\u0007!aA!\u0002\u001a\u0005\u00049%!A+\u0003#Q\u0013X-Z*fiB\u0013xN[3di&|gn\u0005\u0002\u001b-R1!Q\u0002B\b\u0005#\u00012!! \u001b\u0011\u001d\ti+\ba\u0001\u0003_Cq!a.\u001e\u0001\u0004\ty+\u0001\bqS\u000e\\Gj\\<fe\n{WO\u001c3\u0015\t\u0005=&q\u0003\u0005\b\u00053q\u0002\u0019AAX\u0003\u001dqWm\u001e$s_6\fa\u0002]5dWV\u0003\b/\u001a:C_VtG\r\u0006\u0003\u00020\n}\u0001b\u0002B\u0011?\u0001\u0007\u0011qV\u0001\t]\u0016<XK\u001c;jY\u0006\u0011\u0012n]%og&$WMV5fo\n{WO\u001c3t)\u0011\t9Ja\n\t\r\u0005%\b\u00051\u0001D)\u00151&1\u0006B\u0017\u0011\u001d\ti+\ta\u0001\u0003_Cq!a.\"\u0001\u0004\ty\u000b\u0006\u0003\u0002\u0018\nE\u0002BBAuE\u0001\u00071\t\u0006\u0003\u0002$\tU\u0002BBA\u0018I\u0001\u00071)\u0001\u0006iK\u0006$w\n\u001d;j_:,\"!a,\u0002\u00151\f7\u000f^(qi&|g.\u0006\u0003\u0003@\t\u001dC\u0003BAG\u0005\u0003Bq!!?-\u0001\u0004\u0011\u0019\u0005\u0005\u0004J\u0003{\u001c%Q\t\t\u0004\t\n\u001dCA\u0002B\u0003Y\t\u0007q)A\u0003dY>tW\rF\u0001WS\t\u0001!$A\u0004Ue\u0016,7+\u001a;\u0011\u0005\u0001{3#B\u0018\u0003V\u0005e\u0001cA%\u0003X%\u0019!\u0011\f\u001e\u0003\r\u0005s\u0017PU3g)\t\u0011\t&A\u0003f[B$\u00180\u0006\u0003\u0003b\t\u001dD\u0003\u0002B2\u0005S\u0002B\u0001\u0011\u0001\u0003fA\u0019AIa\u001a\u0005\u000b\u0019\u000b$\u0019A$\t\u0013\t-\u0014'!AA\u0004\t5\u0014AC3wS\u0012,gnY3%cA!!0 B3+\u0011\u0011\tH!\u001f\u0015\t\tM$\u0011\u0011\u000b\u0005\u0005k\u0012i\b\u0005\u0003A\u0001\t]\u0004c\u0001#\u0003z\u00111!1\u0010\u001aC\u0002\u001d\u0013\u0011!\u0012\u0005\u0007oJ\u0002\u001dAa \u0011\til(q\u000f\u0005\b\u0005\u0007\u0013\u0004\u0019\u0001BC\u0003\tIG\u000fE\u0003{\u0005\u000f\u00139(C\u0002\u0003\n~\u0014A\"\u0013;fe\u0006\u0014G.Z(oG\u0016\f!B\\3x\u0005VLG\u000eZ3s+\u0011\u0011yI!'\u0015\t\tE%Q\u0014\t\b\u0001\nM%q\u0013BN\u0013\r\u0011)J\u000e\u0002\b\u0005VLG\u000eZ3s!\r!%\u0011\u0014\u0003\u0006\rN\u0012\ra\u0012\t\u0005\u0001\u0002\u00119\n\u0003\u0004xg\u0001\u000f!q\u0014\t\u0005uv\u00149*\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0003&B!!q\u0015BY\u001b\t\u0011IK\u0003\u0003\u0003,\n5\u0016\u0001\u00027b]\u001eT!Aa,\u0002\t)\fg/Y\u0005\u0005\u0005g\u0013IK\u0001\u0004PE*,7\r\u001e\u0015\b_\t]&Q\u0018B`!\rI%\u0011X\u0005\u0004\u0005wS$\u0001E*fe&\fGNV3sg&|g.V%E\u0003\u00151\u0018\r\\;f=\u0005\u0019\u0001f\u0002\u0018\u00038\nu&q\u0018"
)
public class TreeSet extends AbstractSet implements SortedSet, StrictOptimizedSortedSetOps, DefaultSerializable {
   private final RedBlackTree.Tree scala$collection$mutable$TreeSet$$tree;
   private final Ordering ordering;

   public static Builder newBuilder(final Ordering ordering) {
      TreeSet$ var10000 = TreeSet$.MODULE$;
      return new ReusableBuilder(ordering) {
         private RedBlackTree.Tree tree;
         private final Ordering ordering$1;

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

         public <undefinedtype> addOne(final Object elem) {
            RedBlackTree$.MODULE$.insert(this.tree, elem, (Object)null, this.ordering$1);
            return this;
         }

         public TreeSet result() {
            return new TreeSet(this.tree, this.ordering$1);
         }

         public void clear() {
            this.tree = RedBlackTree.Tree$.MODULE$.empty();
         }

         public {
            this.ordering$1 = ordering$1;
            this.tree = RedBlackTree.Tree$.MODULE$.empty();
         }
      };
   }

   public static Factory evidenceIterableFactory(final Object evidence$13) {
      return EvidenceIterableFactory.evidenceIterableFactory$(TreeSet$.MODULE$, evidence$13);
   }

   public static Object unfold(final Object init, final Function1 f, final Object evidence$11) {
      TreeSet$ unfold_this = TreeSet$.MODULE$;
      IterableOnce from_it = new View.Unfold(init, f);
      return unfold_this.from(from_it, (Ordering)evidence$11);
   }

   public static Object iterate(final Object start, final int len, final Function1 f, final Object evidence$10) {
      TreeSet$ iterate_this = TreeSet$.MODULE$;
      IterableOnce from_it = new View.Iterate(start, len, f);
      return iterate_this.from(from_it, (Ordering)evidence$10);
   }

   public static Object tabulate(final int n, final Function1 f, final Object evidence$9) {
      TreeSet$ tabulate_this = TreeSet$.MODULE$;
      IterableOnce from_it = new View.Tabulate(n, f);
      return tabulate_this.from(from_it, (Ordering)evidence$9);
   }

   public static Object fill(final int n, final Function0 elem, final Object evidence$8) {
      TreeSet$ fill_this = TreeSet$.MODULE$;
      IterableOnce from_it = new View.Fill(n, elem);
      return fill_this.from(from_it, (Ordering)evidence$8);
   }

   public Object writeReplace() {
      return DefaultSerializable.writeReplace$(this);
   }

   public scala.collection.SortedSet map(final Function1 f, final Ordering ev) {
      return StrictOptimizedSortedSetOps.map$(this, f, ev);
   }

   public scala.collection.SortedSet flatMap(final Function1 f, final Ordering ev) {
      return StrictOptimizedSortedSetOps.flatMap$(this, f, ev);
   }

   public scala.collection.SortedSet zip(final IterableOnce that, final Ordering ev) {
      return StrictOptimizedSortedSetOps.zip$(this, that, ev);
   }

   public scala.collection.SortedSet collect(final PartialFunction pf, final Ordering ev) {
      return StrictOptimizedSortedSetOps.collect$(this, pf, ev);
   }

   public scala.collection.SetOps concat(final IterableOnce that) {
      return StrictOptimizedSetOps.concat$(this, that);
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

   public Set unsorted() {
      return SortedSet.unsorted$(this);
   }

   // $FF: synthetic method
   public boolean scala$collection$SortedSet$$super$equals(final Object that) {
      return scala.collection.Set.equals$(this, that);
   }

   public String stringPrefix() {
      return scala.collection.SortedSet.stringPrefix$(this);
   }

   public boolean equals(final Object that) {
      return scala.collection.SortedSet.equals$(this, that);
   }

   public scala.collection.SortedSet fromSpecific(final IterableOnce coll) {
      return SortedSetFactoryDefaults.fromSpecific$(this, coll);
   }

   public Builder newSpecificBuilder() {
      return SortedSetFactoryDefaults.newSpecificBuilder$(this);
   }

   public scala.collection.SortedSet empty() {
      return SortedSetFactoryDefaults.empty$(this);
   }

   public scala.collection.SortedSetOps.WithFilter withFilter(final Function1 p) {
      return SortedSetFactoryDefaults.withFilter$(this, p);
   }

   // $FF: synthetic method
   public Object scala$collection$SortedSetOps$$super$min(final Ordering ord) {
      return IterableOnceOps.min$(this, ord);
   }

   // $FF: synthetic method
   public Object scala$collection$SortedSetOps$$super$max(final Ordering ord) {
      return IterableOnceOps.max$(this, ord);
   }

   /** @deprecated */
   public Iterator keysIteratorFrom(final Object start) {
      return scala.collection.SortedSetOps.keysIteratorFrom$(this, start);
   }

   public Object firstKey() {
      return scala.collection.SortedSetOps.firstKey$(this);
   }

   public Object lastKey() {
      return scala.collection.SortedSetOps.lastKey$(this);
   }

   public Object min(final Ordering ord) {
      return scala.collection.SortedSetOps.min$(this, ord);
   }

   public Object max(final Ordering ord) {
      return scala.collection.SortedSetOps.max$(this, ord);
   }

   public scala.collection.SortedSetOps rangeTo(final Object to) {
      return scala.collection.SortedSetOps.rangeTo$(this, to);
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

   public RedBlackTree.Tree scala$collection$mutable$TreeSet$$tree() {
      return this.scala$collection$mutable$TreeSet$$tree;
   }

   public Ordering ordering() {
      return this.ordering;
   }

   public SortedIterableFactory sortedIterableFactory() {
      return TreeSet$.MODULE$;
   }

   public Iterator iterator() {
      RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
      RedBlackTree.Tree var5 = this.scala$collection$mutable$TreeSet$$tree();
      RedBlackTree$ var10001 = RedBlackTree$.MODULE$;
      None$ var6 = None$.MODULE$;
      RedBlackTree$ var10002 = RedBlackTree$.MODULE$;
      None$ var7 = None$.MODULE$;
      Ordering keysIterator_evidence$4 = this.ordering();
      None$ keysIterator_end = var7;
      None$ keysIterator_start = var6;
      RedBlackTree.Tree keysIterator_tree = var5;
      return new RedBlackTree.KeysIterator(keysIterator_tree, keysIterator_start, keysIterator_end, keysIterator_evidence$4);
   }

   public Iterator iteratorFrom(final Object start) {
      RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
      RedBlackTree.Tree var6 = this.scala$collection$mutable$TreeSet$$tree();
      Some var10001 = new Some(start);
      RedBlackTree$ var10002 = RedBlackTree$.MODULE$;
      None$ var7 = None$.MODULE$;
      Ordering keysIterator_evidence$4 = this.ordering();
      None$ keysIterator_end = var7;
      Some keysIterator_start = var10001;
      RedBlackTree.Tree keysIterator_tree = var6;
      return new RedBlackTree.KeysIterator(keysIterator_tree, keysIterator_start, keysIterator_end, keysIterator_evidence$4);
   }

   public Stepper stepper(final StepperShape shape) {
      int var2 = shape.shape();
      if (StepperShape$.MODULE$.IntShape() == var2) {
         IntBinaryTreeStepper$ var35 = IntBinaryTreeStepper$.MODULE$;
         int var36 = this.size();
         RedBlackTree.Node var41 = this.scala$collection$mutable$TreeSet$$tree().root();
         Function1 var44 = (x$1) -> x$1.left();
         Function1 var47 = (x$2) -> x$2.right();
         Function1 from_extract = (x$3) -> BoxesRunTime.boxToInteger($anonfun$stepper$3(x$3));
         Function1 from_right = var47;
         Function1 from_left = var44;
         RedBlackTree.Node from_root = var41;
         int from_maxLength = var36;
         IntBinaryTreeStepper from_ans = new IntBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, from_left, from_right, from_extract);
         from_ans.initialize(from_root, from_maxLength);
         return from_ans;
      } else if (StepperShape$.MODULE$.LongShape() == var2) {
         LongBinaryTreeStepper$ var33 = LongBinaryTreeStepper$.MODULE$;
         int var34 = this.size();
         RedBlackTree.Node var40 = this.scala$collection$mutable$TreeSet$$tree().root();
         Function1 var43 = (x$4) -> x$4.left();
         Function1 var46 = (x$5) -> x$5.right();
         Function1 from_extract = (x$6) -> BoxesRunTime.boxToLong($anonfun$stepper$6(x$6));
         Function1 from_right = var46;
         Function1 from_left = var43;
         RedBlackTree.Node from_root = var40;
         int from_maxLength = var34;
         LongBinaryTreeStepper from_ans = new LongBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, from_left, from_right, from_extract);
         from_ans.initialize(from_root, from_maxLength);
         return from_ans;
      } else if (StepperShape$.MODULE$.DoubleShape() == var2) {
         DoubleBinaryTreeStepper$ var10000 = DoubleBinaryTreeStepper$.MODULE$;
         int var32 = this.size();
         RedBlackTree.Node var39 = this.scala$collection$mutable$TreeSet$$tree().root();
         Function1 var42 = (x$7) -> x$7.left();
         Function1 var45 = (x$8) -> x$8.right();
         Function1 from_extract = (x$9) -> BoxesRunTime.boxToDouble($anonfun$stepper$9(x$9));
         Function1 from_right = var45;
         Function1 from_left = var42;
         RedBlackTree.Node from_root = var39;
         int from_maxLength = var32;
         DoubleBinaryTreeStepper from_ans = new DoubleBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, from_left, from_right, from_extract);
         from_ans.initialize(from_root, from_maxLength);
         return from_ans;
      } else {
         AnyBinaryTreeStepper$ var10001 = AnyBinaryTreeStepper$.MODULE$;
         int var37 = this.size();
         RedBlackTree.Node var10002 = this.scala$collection$mutable$TreeSet$$tree().root();
         Function1 var10003 = (x$10) -> x$10.left();
         Function1 var10004 = (x$11) -> x$11.right();
         Function1 from_extract = (x$12) -> x$12.key();
         Function1 from_right = var10004;
         Function1 from_left = var10003;
         RedBlackTree.Node from_root = var10002;
         int from_maxLength = var37;
         AnyBinaryTreeStepper from_ans = new AnyBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, from_left, from_right, from_extract);
         from_ans.initialize(from_root, from_maxLength);
         AnyBinaryTreeStepper var38 = from_ans;
         from_root = null;
         from_left = null;
         from_right = null;
         from_extract = null;
         Object var31 = null;
         return shape.parUnbox(var38);
      }
   }

   public TreeSet addOne(final Object elem) {
      RedBlackTree$.MODULE$.insert(this.scala$collection$mutable$TreeSet$$tree(), elem, (Object)null, this.ordering());
      return this;
   }

   public TreeSet subtractOne(final Object elem) {
      RedBlackTree$.MODULE$.delete(this.scala$collection$mutable$TreeSet$$tree(), elem, this.ordering());
      return this;
   }

   public void clear() {
      RedBlackTree$.MODULE$.clear(this.scala$collection$mutable$TreeSet$$tree());
   }

   public boolean contains(final Object elem) {
      return RedBlackTree$.MODULE$.contains(this.scala$collection$mutable$TreeSet$$tree(), elem, this.ordering());
   }

   public scala.collection.Set unconstrained() {
      return this;
   }

   public TreeSet rangeImpl(final Option from, final Option until) {
      return new TreeSetProjection(from, until);
   }

   public String className() {
      return "TreeSet";
   }

   public int size() {
      RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
      return this.scala$collection$mutable$TreeSet$$tree().size();
   }

   public int knownSize() {
      return this.size();
   }

   public boolean isEmpty() {
      return RedBlackTree$.MODULE$.isEmpty(this.scala$collection$mutable$TreeSet$$tree());
   }

   public Object head() {
      return RedBlackTree$.MODULE$.minKey(this.scala$collection$mutable$TreeSet$$tree()).get();
   }

   public Object last() {
      return RedBlackTree$.MODULE$.maxKey(this.scala$collection$mutable$TreeSet$$tree()).get();
   }

   public Option minAfter(final Object key) {
      return RedBlackTree$.MODULE$.minKeyAfter(this.scala$collection$mutable$TreeSet$$tree(), key, this.ordering());
   }

   public Option maxBefore(final Object key) {
      return RedBlackTree$.MODULE$.maxKeyBefore(this.scala$collection$mutable$TreeSet$$tree(), key, this.ordering());
   }

   public void foreach(final Function1 f) {
      RedBlackTree$.MODULE$.foreachKey(this.scala$collection$mutable$TreeSet$$tree(), f);
   }

   // $FF: synthetic method
   public static final int $anonfun$stepper$3(final RedBlackTree.Node x$3) {
      return BoxesRunTime.unboxToInt(x$3.key());
   }

   // $FF: synthetic method
   public static final long $anonfun$stepper$6(final RedBlackTree.Node x$6) {
      return BoxesRunTime.unboxToLong(x$6.key());
   }

   // $FF: synthetic method
   public static final double $anonfun$stepper$9(final RedBlackTree.Node x$9) {
      return BoxesRunTime.unboxToDouble(x$9.key());
   }

   public TreeSet(final RedBlackTree.Tree tree, final Ordering ordering) {
      this.scala$collection$mutable$TreeSet$$tree = tree;
      this.ordering = ordering;
      if (ordering == null) {
         throw new NullPointerException("ordering must not be null");
      }
   }

   public TreeSet(final Ordering ord) {
      this(RedBlackTree.Tree$.MODULE$.empty(), ord);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private final class TreeSetProjection extends TreeSet {
      private final Option from;
      private final Option until;
      // $FF: synthetic field
      private final TreeSet $outer;

      private Option pickLowerBound(final Option newFrom) {
         Option var4 = this.from;
         if (var4 instanceof Some) {
            Object fr = ((Some)var4).value();
            if (newFrom instanceof Some) {
               Object newFr = ((Some)newFrom).value();
               return new Some(this.ordering().max(fr, newFr));
            }
         }

         return None$.MODULE$.equals(var4) ? newFrom : this.from;
      }

      private Option pickUpperBound(final Option newUntil) {
         Option var4 = this.until;
         if (var4 instanceof Some) {
            Object unt = ((Some)var4).value();
            if (newUntil instanceof Some) {
               Object newUnt = ((Some)newUntil).value();
               return new Some(this.ordering().min(unt, newUnt));
            }
         }

         return None$.MODULE$.equals(var4) ? newUntil : this.until;
      }

      private boolean isInsideViewBounds(final Object key) {
         boolean afterFrom = this.from.isEmpty() || this.ordering().compare(this.from.get(), key) <= 0;
         boolean beforeUntil = this.until.isEmpty() || this.ordering().compare(key, this.until.get()) < 0;
         return afterFrom && beforeUntil;
      }

      public TreeSet rangeImpl(final Option from, final Option until) {
         return this.$outer.new TreeSetProjection(this.pickLowerBound(from), this.pickUpperBound(until));
      }

      public boolean contains(final Object key) {
         return this.isInsideViewBounds(key) && RedBlackTree$.MODULE$.contains(this.$outer.scala$collection$mutable$TreeSet$$tree(), key, this.ordering());
      }

      public Iterator iterator() {
         RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
         RedBlackTree.Tree var5 = this.$outer.scala$collection$mutable$TreeSet$$tree();
         Option var10001 = this.from;
         Option var10002 = this.until;
         Ordering keysIterator_evidence$4 = this.ordering();
         Option keysIterator_end = var10002;
         Option keysIterator_start = var10001;
         RedBlackTree.Tree keysIterator_tree = var5;
         return new RedBlackTree.KeysIterator(keysIterator_tree, keysIterator_start, keysIterator_end, keysIterator_evidence$4);
      }

      public Iterator iteratorFrom(final Object start) {
         RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
         RedBlackTree.Tree var6 = this.$outer.scala$collection$mutable$TreeSet$$tree();
         Option var10001 = this.pickLowerBound(new Some(start));
         Option var10002 = this.until;
         Ordering keysIterator_evidence$4 = this.ordering();
         Option keysIterator_end = var10002;
         Option keysIterator_start = var10001;
         RedBlackTree.Tree keysIterator_tree = var6;
         return new RedBlackTree.KeysIterator(keysIterator_tree, keysIterator_start, keysIterator_end, keysIterator_evidence$4);
      }

      public int size() {
         RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
         if (this.$outer.scala$collection$mutable$TreeSet$$tree().size() == 0) {
            return 0;
         } else {
            Iterator var1 = this.iterator();
            if (var1 == null) {
               throw null;
            } else {
               return var1.size();
            }
         }
      }

      public int knownSize() {
         RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
         return this.$outer.scala$collection$mutable$TreeSet$$tree().size() == 0 ? 0 : -1;
      }

      public boolean isEmpty() {
         RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
         return this.$outer.scala$collection$mutable$TreeSet$$tree().size() == 0 || !this.iterator().hasNext();
      }

      public Object head() {
         return this.headOption().get();
      }

      public Option headOption() {
         Option elem = this.from.isDefined() ? RedBlackTree$.MODULE$.minKeyAfter(this.$outer.scala$collection$mutable$TreeSet$$tree(), this.from.get(), this.ordering()) : RedBlackTree$.MODULE$.minKey(this.$outer.scala$collection$mutable$TreeSet$$tree());
         Option var4 = this.until;
         if (elem instanceof Some) {
            Object e = ((Some)elem).value();
            if (var4 instanceof Some) {
               Object unt = ((Some)var4).value();
               if (this.ordering().compare(e, unt) >= 0) {
                  return None$.MODULE$;
               }
            }
         }

         return elem;
      }

      public Object last() {
         return this.lastOption().get();
      }

      public Option lastOption() {
         Option elem = this.until.isDefined() ? RedBlackTree$.MODULE$.maxKeyBefore(this.$outer.scala$collection$mutable$TreeSet$$tree(), this.until.get(), this.ordering()) : RedBlackTree$.MODULE$.maxKey(this.$outer.scala$collection$mutable$TreeSet$$tree());
         Option var4 = this.from;
         if (elem instanceof Some) {
            Object e = ((Some)elem).value();
            if (var4 instanceof Some) {
               Object fr = ((Some)var4).value();
               if (this.ordering().compare(e, fr) < 0) {
                  return None$.MODULE$;
               }
            }
         }

         return elem;
      }

      public void foreach(final Function1 f) {
         this.iterator().foreach(f);
      }

      public TreeSet clone() {
         return ((TreeSet)SetOps.clone$(this)).rangeImpl(this.from, this.until);
      }

      public TreeSetProjection(final Option from, final Option until) {
         this.from = from;
         this.until = until;
         if (TreeSet.this == null) {
            throw null;
         } else {
            this.$outer = TreeSet.this;
            super(TreeSet.this.scala$collection$mutable$TreeSet$$tree(), TreeSet.this.ordering());
         }
      }
   }
}
