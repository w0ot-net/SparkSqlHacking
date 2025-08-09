package scala.xml.dtd.impl;

import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.BitSetOps;
import scala.collection.immutable.BitSet;
import scala.collection.immutable.List;
import scala.collection.immutable.Set;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t3QAB\u0004\u0001\u0013=A\u0001\"\u0006\u0001\u0003\u0006\u0004%\ta\u0006\u0005\tI\u0001\u0011\t\u0011)A\u00051!)Q\u0005\u0001C\u0001M!)\u0011\u0006\u0001C\u0001U!)Q\b\u0001C\u0001}\t\u00112+\u001e2tKR\u001cuN\\:ueV\u001cG/[8o\u0015\tA\u0011\"\u0001\u0003j[Bd'B\u0001\u0006\f\u0003\r!G\u000f\u001a\u0006\u0003\u00195\t1\u0001_7m\u0015\u0005q\u0011!B:dC2\fWC\u0001\t\u001f'\t\u0001\u0011\u0003\u0005\u0002\u0013'5\tQ\"\u0003\u0002\u0015\u001b\t1\u0011I\\=SK\u001a\f1A\u001c4b\u0007\u0001)\u0012\u0001\u0007\t\u00043iaR\"A\u0004\n\u0005m9!a\u0004(p]\u0012,GoV8sI\u0006+Ho\\7\u0011\u0005uqB\u0002\u0001\u0003\u0006?\u0001\u0011\r\u0001\t\u0002\u0002)F\u0011\u0011%\u0005\t\u0003%\tJ!aI\u0007\u0003\u000f9{G\u000f[5oO\u0006!aNZ1!\u0003\u0019a\u0014N\\5u}Q\u0011q\u0005\u000b\t\u00043\u0001a\u0002\"B\u000b\u0004\u0001\u0004A\u0012!C:fY\u0016\u001cG\u000fV1h)\rYc\u0006\u000f\t\u0003%1J!!L\u0007\u0003\u0007%sG\u000fC\u00030\t\u0001\u0007\u0001'A\u0001R!\t\td'D\u00013\u0015\t\u0019D'A\u0005j[6,H/\u00192mK*\u0011Q'D\u0001\u000bG>dG.Z2uS>t\u0017BA\u001c3\u0005\u0019\u0011\u0015\u000e^*fi\")\u0011\b\u0002a\u0001u\u00051a-\u001b8bYN\u00042AE\u001e,\u0013\taTBA\u0003BeJ\f\u00170A\u0006eKR,'/\\5oSj,W#A \u0011\u0007e\u0001E$\u0003\u0002B\u000f\taA)\u001a;X_J$\u0017)\u001e;p[\u0002"
)
public class SubsetConstruction {
   private final NondetWordAutom nfa;

   public NondetWordAutom nfa() {
      return this.nfa;
   }

   public int selectTag(final BitSet Q, final int[] finals) {
      return ((BitSetOps)Q.map(.MODULE$.wrapIntArray(finals)).filter((JFunction1.mcZI.sp)(x$1) -> x$1 > 0)).min(scala.math.Ordering.Int..MODULE$);
   }

   public DetWordAutom determinize() {
      Map indexMap = (Map)scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      Map invIndexMap = (Map)scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      int ix = 0;
      BitSet q0 = (BitSet)scala.collection.immutable.BitSet..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{0}));
      BitSet sink = scala.collection.immutable.BitSet..MODULE$.empty();
      ObjectRef states = ObjectRef.create((Set).MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new BitSet[]{q0, sink}))));
      HashMap delta = new HashMap();
      Map deftrans = (Map)scala.collection.mutable.Map..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(q0), sink), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(sink), sink)})));
      Map finals = (Map)scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      ObjectRef rest = ObjectRef.create(scala.collection.immutable.List..MODULE$.empty());
      rest.elem = ((List)rest.elem).$colon$colon(sink).$colon$colon(q0);
      this.addFinal$1(q0, finals);

      while(((List)rest.elem).nonEmpty()) {
         BitSet P = (BitSet)((List)rest.elem).head();
         rest.elem = (List)((List)rest.elem).tail();
         indexMap.update(P, BoxesRunTime.boxToInteger(ix));
         invIndexMap.update(BoxesRunTime.boxToInteger(ix), P);
         ++ix;
         HashMap Pdelta = new HashMap();
         delta.update(P, Pdelta);
         this.nfa().labels().foreach((label) -> {
            $anonfun$determinize$1(this, P, Pdelta, states, rest, finals, label);
            return BoxedUnit.UNIT;
         });
         BitSet Pdef = this.nfa().nextDefault(P);
         deftrans.update(P, Pdef);
         this.add$1(Pdef, states, rest, finals);
      }

      int nstatesR = ((Set)states.elem).size();
      Map[] deltaR = new Map[nstatesR];
      int[] defaultR = new int[nstatesR];
      int[] finalsR = new int[nstatesR];
      ((Set)states.elem).foreach((Q) -> {
         $anonfun$determinize$2(indexMap, delta, deftrans, deltaR, defaultR, Q);
         return BoxedUnit.UNIT;
      });
      finals.foreach((x0$1) -> {
         $anonfun$determinize$5(finalsR, indexMap, x0$1);
         return BoxedUnit.UNIT;
      });
      return new DetWordAutom(nstatesR, deltaR, defaultR, finalsR) {
         private final int nstates;
         private final Map[] delta;
         private final int[] default;
         private final int[] finals;

         public int nstates() {
            return this.nstates;
         }

         public Map[] delta() {
            return this.delta;
         }

         public int[] default() {
            return this.default;
         }

         public int[] finals() {
            return this.finals;
         }

         public {
            this.nstates = nstatesR$1;
            this.delta = deltaR$1;
            this.default = defaultR$1;
            this.finals = finalsR$1;
         }
      };
   }

   private final void addFinal$1(final BitSet q, final Map finals$1) {
      if (this.nfa().containsFinal(q)) {
         finals$1.update(q, BoxesRunTime.boxToInteger(this.selectTag(q, this.nfa().finals())));
      }
   }

   private final void add$1(final BitSet Q, final ObjectRef states$1, final ObjectRef rest$1, final Map finals$1) {
      if (!((Set)states$1.elem).apply(Q)) {
         states$1.elem = (Set)((Set)states$1.elem).$plus(Q);
         rest$1.elem = ((List)rest$1.elem).$colon$colon(Q);
         this.addFinal$1(Q, finals$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$determinize$1(final SubsetConstruction $this, final BitSet P$1, final HashMap Pdelta$1, final ObjectRef states$1, final ObjectRef rest$1, final Map finals$1, final Object label) {
      BitSet Q = $this.nfa().next(P$1, label);
      Pdelta$1.update(label, Q);
      $this.add$1(Q, states$1, rest$1, finals$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$determinize$3(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final void $anonfun$determinize$4(final Map indexMap$1, final int qDef$1, final Map ntrans$1, final Tuple2 x$2) {
      if (x$2 != null) {
         Object label = x$2._1();
         BitSet value = (BitSet)x$2._2();
         int p = BoxesRunTime.unboxToInt(indexMap$1.apply(value));
         if (p != qDef$1) {
            ntrans$1.update(label, BoxesRunTime.boxToInteger(p));
            BoxedUnit var9 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x$2);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$determinize$2(final Map indexMap$1, final HashMap delta$1, final Map deftrans$1, final Map[] deltaR$1, final int[] defaultR$1, final BitSet Q) {
      int q = BoxesRunTime.unboxToInt(indexMap$1.apply(Q));
      Map trans = (Map)delta$1.apply(Q);
      BitSet transDef = (BitSet)deftrans$1.apply(Q);
      int qDef = BoxesRunTime.unboxToInt(indexMap$1.apply(transDef));
      Map ntrans = new HashMap();
      trans.withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$determinize$3(check$ifrefutable$1))).foreach((x$2) -> {
         $anonfun$determinize$4(indexMap$1, qDef, ntrans, x$2);
         return BoxedUnit.UNIT;
      });
      deltaR$1[q] = ntrans;
      defaultR$1[q] = qDef;
   }

   // $FF: synthetic method
   public static final void $anonfun$determinize$5(final int[] finalsR$1, final Map indexMap$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         BitSet k = (BitSet)x0$1._1();
         int v = x0$1._2$mcI$sp();
         finalsR$1[BoxesRunTime.unboxToInt(indexMap$1.apply(k))] = v;
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   public SubsetConstruction(final NondetWordAutom nfa) {
      this.nfa = nfa;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
