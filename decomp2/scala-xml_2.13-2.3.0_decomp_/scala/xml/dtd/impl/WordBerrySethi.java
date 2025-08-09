package scala.xml.dtd.impl;

import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.Seq;
import scala.collection.immutable.BitSet;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

/** @deprecated */
@ScalaSignature(
   bytes = "\u0006\u0005\u00055eAB\u000f\u001f\u0003\u0003\u0001c\u0005C\u0003,\u0001\u0011\u0005Q\u0006C\u00040\u0001\t\u0007i\u0011\t\u0019\t\u0013Q\u0002\u0001\u0019!a\u0001\n#)\u0004\"C\"\u0001\u0001\u0004\u0005\r\u0011\"\u0005E\u0011%Y\u0005\u00011A\u0001B\u0003&a\u0007C\u0005M\u0001\u0001\u0007\t\u0019!C\t\u001b\"IA\f\u0001a\u0001\u0002\u0004%\t\"\u0018\u0005\n?\u0002\u0001\r\u0011!Q!\n9C\u0011\u0002\u0019\u0001A\u0002\u0003\u0007I\u0011C1\t\u0013=\u0004\u0001\u0019!a\u0001\n#\u0001\b\"\u0003:\u0001\u0001\u0004\u0005\t\u0015)\u0003c\u0011%\u0019\b\u00011AA\u0002\u0013EA\u000fC\u0005w\u0001\u0001\u0007\t\u0019!C\to\"I\u0011\u0010\u0001a\u0001\u0002\u0003\u0006K!\u001e\u0005\nu\u0002\u0001\r\u00111A\u0005\u0012mD!b \u0001A\u0002\u0003\u0007I\u0011CA\u0001\u0011)\t)\u0001\u0001a\u0001\u0002\u0003\u0006K\u0001 \u0005\b\u0003\u000f\u0001A\u0011KA\u0005\u0011\u001d\tI\u0002\u0001C)\u00037Aq!a\b\u0001\t#\n\t\u0003C\u0004\u0002*\u0001!\t\"a\u000b\t\u000f\u0005%\u0002\u0001\"\u0005\u00028!9\u0011Q\b\u0001\u0005B\u0005}\u0002bBA\"\u0001\u0011E\u0011Q\t\u0005\b\u0003#\u0002A\u0011CA*\u0011\u001d\t\t\u0007\u0001C\t\u0003GBq!!\u001a\u0001\t#\t\u0019\u0007C\u0004\u0002h\u0001!\t!!\u001b\u0003\u001d]{'\u000f\u001a\"feJL8+\u001a;iS*\u0011q\u0004I\u0001\u0005S6\u0004HN\u0003\u0002\"E\u0005\u0019A\r\u001e3\u000b\u0005\r\"\u0013a\u0001=nY*\tQ%A\u0003tG\u0006d\u0017m\u0005\u0002\u0001OA\u0011\u0001&K\u0007\u0002=%\u0011!F\b\u0002\u000f\u0005\u0006\u001cXMQ3sef\u001cV\r\u001e5j\u0003\u0019a\u0014N\\5u}\r\u0001A#\u0001\u0018\u0011\u0005!\u0002\u0011\u0001\u00027b]\u001e,\u0012!\r\t\u0003QIJ!a\r\u0010\u0003\u000f]{'\u000fZ#ya\u00061A.\u00192fYN,\u0012A\u000e\t\u0004oqrT\"\u0001\u001d\u000b\u0005eR\u0014aB7vi\u0006\u0014G.\u001a\u0006\u0003w\u0011\n!bY8mY\u0016\u001cG/[8o\u0013\ti\u0004HA\u0004ICND7+\u001a;\u0011\u0005}\neB\u0001!\u0003\u001b\u0005\u0001\u0011B\u0001\"3\u0005\u001dyF.\u00192fYR\u000b!\u0002\\1cK2\u001cx\fJ3r)\t)\u0015\n\u0005\u0002G\u000f6\tA%\u0003\u0002II\t!QK\\5u\u0011\u001dQE!!AA\u0002Y\n1\u0001\u001f\u00132\u0003\u001da\u0017MY3mg\u0002\nq\u0001\\1cK2\fE/F\u0001O!\u0011ye+\u0017 \u000f\u0005A#\u0006CA)%\u001b\u0005\u0011&BA*-\u0003\u0019a$o\\8u}%\u0011Q\u000bJ\u0001\u0007!J,G-\u001a4\n\u0005]C&aA'ba*\u0011Q\u000b\n\t\u0003\rjK!a\u0017\u0013\u0003\u0007%sG/A\u0006mC\n,G.\u0011;`I\u0015\fHCA#_\u0011\u001dQu!!AA\u00029\u000b\u0001\u0002\\1cK2\fE\u000fI\u0001\u0007I\u0016dG/Y9\u0016\u0003\t\u00042AR2f\u0013\t!GEA\u0003BeJ\f\u0017\u0010\u0005\u00038MzB\u0017BA49\u0005\u001dA\u0015m\u001d5NCB\u00042!\u001b7Z\u001d\t1%.\u0003\u0002lI\u00059\u0001/Y2lC\u001e,\u0017BA7o\u0005\u0011a\u0015n\u001d;\u000b\u0005-$\u0013A\u00033fYR\f\u0017o\u0018\u0013fcR\u0011Q)\u001d\u0005\b\u0015*\t\t\u00111\u0001c\u0003\u001d!W\r\u001c;bc\u0002\n\u0001\u0002Z3gCVdG/]\u000b\u0002kB\u0019ai\u00195\u0002\u0019\u0011,g-Y;miF|F%Z9\u0015\u0005\u0015C\bb\u0002&\u000e\u0003\u0003\u0005\r!^\u0001\nI\u00164\u0017-\u001e7uc\u0002\n\u0001\"\u001b8ji&\fGn]\u000b\u0002yB\u0019q*`-\n\u0005yD&aA*fi\u0006a\u0011N\\5uS\u0006d7o\u0018\u0013fcR\u0019Q)a\u0001\t\u000f)\u0003\u0012\u0011!a\u0001y\u0006I\u0011N\\5uS\u0006d7\u000fI\u0001\nG>l\u0007OR5sgR$2\u0001`A\u0006\u0011\u001d\tiA\u0005a\u0001\u0003\u001f\t\u0011A\u001d\t\u0004\u007f\u0005E\u0011\u0002BA\n\u0003+\u0011aAU3h\u000bb\u0004\u0018bAA\f=\t!!)Y:f\u0003!\u0019w.\u001c9MCN$Hc\u0001?\u0002\u001e!9\u0011QB\nA\u0002\u0005=\u0011aC2p[B4u\u000e\u001c7poF\"R\u0001`A\u0012\u0003OAa!!\n\u0015\u0001\u0004a\u0018\u0001\u00024pYFBq!!\u0004\u0015\u0001\u0004\ty!A\u0005tK\u0016tG*\u00192fYR9Q)!\f\u00020\u0005M\u0002bBA\u0007+\u0001\u0007\u0011q\u0002\u0005\u0007\u0003c)\u0002\u0019A-\u0002\u0003%Da!!\u000e\u0016\u0001\u0004q\u0014!\u00027bE\u0016dG#B-\u0002:\u0005m\u0002bBA\u0007-\u0001\u0007\u0011q\u0002\u0005\u0007\u0003k1\u0002\u0019\u0001 \u0002\u0011Q\u0014\u0018M^3sg\u0016$2!RA!\u0011\u001d\tia\u0006a\u0001\u0003\u001f\ta\"\\1lKR\u0013\u0018M\\:ji&|g\u000eF\u0004F\u0003\u000f\nY%a\u0014\t\r\u0005%\u0003\u00041\u0001Z\u0003\r\u0019(o\u0019\u0005\u0007\u0003\u001bB\u0002\u0019A-\u0002\t\u0011,7\u000f\u001e\u0005\u0007\u0003kA\u0002\u0019\u0001 \u0002\u0015%t\u0017\u000e^5bY&TX\rF\u0002F\u0003+Bq!a\u0016\u001a\u0001\u0004\tI&A\u0004tk\n,\u0007\u0010\u001d:\u0011\r\u0005m\u0013QLA\b\u001b\u0005Q\u0014bAA0u\t\u00191+Z9\u0002\u001f%t\u0017\u000e^5bY&TX-Q;u_6$\u0012!R\u0001\u0013G>dG.Z2u)J\fgn]5uS>t7/A\u0007bkR|W.\u0019;p]\u001a\u0013x.\u001c\u000b\u0007\u0003W\n\t(!\u001e\u0011\t!\niGP\u0005\u0004\u0003_r\"a\u0004(p]\u0012,GoV8sI\u0006+Ho\\7\t\u000f\u0005MD\u00041\u0001\u0002\u0010\u0005\u0019\u0001/\u0019;\t\r\u0005]D\u00041\u0001Z\u0003!1\u0017N\\1m)\u0006<\u0007f\u0003\u0001\u0002|\u0005\u0005\u00151QAD\u0003\u0013\u00032ARA?\u0013\r\ty\b\n\u0002\u000bI\u0016\u0004(/Z2bi\u0016$\u0017aB7fgN\fw-Z\u0011\u0003\u0003\u000b\u000b!\u0004\u00165jg\u0002\u001aG.Y:tA]LG\u000e\u001c\u0011cK\u0002\u0012X-\\8wK\u0012\fQa]5oG\u0016\f#!a#\u0002\rIr\u0013\u0007\r\u00181\u0001"
)
public abstract class WordBerrySethi extends BaseBerrySethi {
   private HashSet labels;
   private Map labelAt;
   private HashMap[] deltaq;
   private List[] defaultq;
   private Set initials;

   public abstract WordExp lang();

   public HashSet labels() {
      return this.labels;
   }

   public void labels_$eq(final HashSet x$1) {
      this.labels = x$1;
   }

   public Map labelAt() {
      return this.labelAt;
   }

   public void labelAt_$eq(final Map x$1) {
      this.labelAt = x$1;
   }

   public HashMap[] deltaq() {
      return this.deltaq;
   }

   public void deltaq_$eq(final HashMap[] x$1) {
      this.deltaq = x$1;
   }

   public List[] defaultq() {
      return this.defaultq;
   }

   public void defaultq_$eq(final List[] x$1) {
      this.defaultq = x$1;
   }

   public Set initials() {
      return this.initials;
   }

   public void initials_$eq(final Set x$1) {
      this.initials = x$1;
   }

   public Set compFirst(final Base.RegExp r) {
      if (r instanceof WordExp.Letter) {
         WordExp.Letter var4 = (WordExp.Letter)r;
         return (Set).MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{var4.pos()}));
      } else {
         return super.compFirst(r);
      }
   }

   public Set compLast(final Base.RegExp r) {
      if (r instanceof WordExp.Letter) {
         WordExp.Letter var4 = (WordExp.Letter)r;
         return (Set).MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{var4.pos()}));
      } else {
         return super.compLast(r);
      }
   }

   public Set compFollow1(final Set fol1, final Base.RegExp r) {
      if (r instanceof WordExp.Letter) {
         WordExp.Letter var5 = (WordExp.Letter)r;
         this.follow().update(BoxesRunTime.boxToInteger(var5.pos()), fol1);
         return (Set).MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{var5.pos()}));
      } else {
         return this.lang().Eps().equals(r) ? this.emptySet() : super.compFollow1(fol1, r);
      }
   }

   public void seenLabel(final Base.RegExp r, final int i, final WordExp.Label label) {
      this.labelAt_$eq((Map)this.labelAt().updated(BoxesRunTime.boxToInteger(i), label));
      this.labels().$plus$eq(label);
   }

   public int seenLabel(final Base.RegExp r, final WordExp.Label label) {
      this.pos_$eq(this.pos() + 1);
      this.seenLabel(r, this.pos(), label);
      return this.pos();
   }

   public void traverse(final Base.RegExp r) {
      if (r instanceof WordExp.Letter) {
         WordExp.Letter var4 = (WordExp.Letter)r;
         WordExp.Label label = var4.a();
         var4.pos_$eq(this.seenLabel(r, label));
         BoxedUnit var7 = BoxedUnit.UNIT;
      } else if (this.lang().Eps().equals(r)) {
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else {
         super.traverse(r);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   public void makeTransition(final int src, final int dest, final WordExp.Label label) {
      scala.collection.mutable.Map q = this.deltaq()[src];
      q.update(label, ((List)q.getOrElse(label, () -> scala.collection.immutable.Nil..MODULE$)).$colon$colon(BoxesRunTime.boxToInteger(dest)));
   }

   public void initialize(final Seq subexpr) {
      this.labelAt_$eq((Map)scala.collection.immutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
      this.follow_$eq((HashMap)scala.collection.mutable.HashMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
      this.labels_$eq((HashSet)scala.collection.mutable.HashSet..MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
      this.pos_$eq(0);
      subexpr.foreach((r) -> {
         $anonfun$initialize$1(this, r);
         return BoxedUnit.UNIT;
      });
      this.initials_$eq((Set).MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{0})));
   }

   public void initializeAutom() {
      this.finals_$eq(scala.collection.immutable.Map..MODULE$.empty());
      this.deltaq_$eq(new HashMap[this.pos()]);
      this.defaultq_$eq(new List[this.pos()]);
      scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), this.pos()).foreach$mVc$sp((JFunction1.mcVI.sp)(j) -> {
         this.deltaq()[j] = (HashMap)scala.collection.mutable.HashMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
         this.defaultq()[j] = scala.collection.immutable.Nil..MODULE$;
      });
   }

   public void collectTransitions() {
      scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), this.pos()).map((j) -> $anonfun$collectTransitions$1(this, BoxesRunTime.unboxToInt(j))).foreach((x$6) -> {
         $anonfun$collectTransitions$2(this, x$6);
         return BoxedUnit.UNIT;
      });
   }

   public NondetWordAutom automatonFrom(final Base.RegExp pat, final int finalTag) {
      this.finalTag_$eq(finalTag);
      if (pat instanceof Base.Sequ) {
         Base.Sequ var5 = (Base.Sequ)pat;
         this.initialize(var5.rs());
         this.pos_$eq(this.pos() + 1);
         this.compFollow(var5.rs());
         this.initializeAutom();
         this.collectTransitions();
         if (var5.isNullable()) {
            this.finals_$eq((Map)this.finals().updated(BoxesRunTime.boxToInteger(0), BoxesRunTime.boxToInteger(finalTag)));
         }

         Map delta1 = .MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(.MODULE$.refArrayOps((Object[])this.deltaq()))), (x$7) -> x$7.swap(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toMap(scala..less.colon.less..MODULE$.refl());
         int[] finalsArr = (int[])scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), this.pos()).map((JFunction1.mcII.sp)(k) -> BoxesRunTime.unboxToInt(this.finals().getOrElse(BoxesRunTime.boxToInteger(k), (JFunction0.mcI.sp)() -> 0))).toArray(scala.reflect.ClassTag..MODULE$.Int());
         scala.collection.mutable.Map[] deltaArr = (scala.collection.mutable.Map[])scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), this.pos()).map((x) -> $anonfun$automatonFrom$4(delta1, BoxesRunTime.unboxToInt(x))).toArray(scala.reflect.ClassTag..MODULE$.apply(scala.collection.mutable.Map.class));
         BitSet[] defaultArr = (BitSet[])scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), this.pos()).map((k) -> $anonfun$automatonFrom$6(this, BoxesRunTime.unboxToInt(k))).toArray(scala.reflect.ClassTag..MODULE$.apply(BitSet.class));
         return new NondetWordAutom(finalsArr, deltaArr, defaultArr) {
            private final int nstates = WordBerrySethi.this.pos();
            private final Seq labels = WordBerrySethi.this.labels().toList();
            private final int[] finals;
            private final scala.collection.mutable.Map[] delta;
            private final BitSet[] default;

            public int nstates() {
               return this.nstates;
            }

            public Seq labels() {
               return this.labels;
            }

            public int[] finals() {
               return this.finals;
            }

            public scala.collection.mutable.Map[] delta() {
               return this.delta;
            }

            public BitSet[] default() {
               return this.default;
            }

            public {
               this.finals = finalsArr$1;
               this.delta = deltaArr$1;
               this.default = defaultArr$1;
            }
         };
      } else {
         return this.automatonFrom(this.lang().Sequ().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Base.RegExp[]{pat})), finalTag);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$initialize$1(final WordBerrySethi $this, final Base.RegExp r) {
      $this.traverse(r);
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$collectTransitions$1(final WordBerrySethi $this, final int j) {
      Set fol = (Set)$this.follow().apply(BoxesRunTime.boxToInteger(j));
      return new Tuple2(BoxesRunTime.boxToInteger(j), fol);
   }

   // $FF: synthetic method
   public static final void $anonfun$collectTransitions$2(final WordBerrySethi $this, final Tuple2 x$6) {
      if (x$6 != null) {
         int j = x$6._1$mcI$sp();
         Set fol = (Set)x$6._2();
         fol.foreach((JFunction1.mcVI.sp)(k) -> {
            if ($this.pos() == k) {
               $this.finals_$eq((Map)$this.finals().updated(BoxesRunTime.boxToInteger(j), BoxesRunTime.boxToInteger($this.finalTag())));
            } else {
               $this.makeTransition(j, k, (WordExp.Label)$this.labelAt().apply(BoxesRunTime.boxToInteger(k)));
            }
         });
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$6);
      }
   }

   // $FF: synthetic method
   public static final HashMap $anonfun$automatonFrom$4(final Map delta1$1, final int x) {
      return (HashMap)scala.collection.mutable.HashMap..MODULE$.apply((scala.collection.immutable.Seq)((IterableOnceOps)delta1$1.apply(BoxesRunTime.boxToInteger(x))).toSeq().map((x0$1) -> {
         if (x0$1 != null) {
            WordExp.Label k = (WordExp.Label)x0$1._1();
            List v = (List)x0$1._2();
            return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(k), scala.collection.immutable.BitSet..MODULE$.apply(v));
         } else {
            throw new MatchError(x0$1);
         }
      }));
   }

   // $FF: synthetic method
   public static final BitSet $anonfun$automatonFrom$6(final WordBerrySethi $this, final int k) {
      return (BitSet)scala.collection.immutable.BitSet..MODULE$.apply($this.defaultq()[k]);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
