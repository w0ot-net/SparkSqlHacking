package org.apache.spark.graphx.lib;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.graphx.EdgeContext;
import org.apache.spark.graphx.Graph;
import org.apache.spark.graphx.Graph$;
import org.apache.spark.graphx.TripletFields;
import org.apache.spark.graphx.VertexRDD;
import org.apache.spark.rdd.RDD;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple4;
import scala.Predef.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

public final class SVDPlusPlus$ {
   public static final SVDPlusPlus$ MODULE$ = new SVDPlusPlus$();

   public Tuple2 run(final RDD edges, final SVDPlusPlus.Conf conf) {
      .MODULE$.require(conf.maxIters() > 0, () -> "Maximum of iterations must be greater than 0, but got " + conf.maxIters());
      .MODULE$.require(conf.maxVal() > conf.minVal(), () -> {
         double var10000 = conf.maxVal();
         return "MaxVal must be greater than MinVal, but got {maxVal: " + var10000 + ", minVal: " + conf.minVal() + "}";
      });
      edges.cache();
      Tuple2 var5 = (Tuple2)edges.map((e) -> new Tuple2.mcDJ.sp(e.attr$mcD$sp(), 1L), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)).fold(new Tuple2.mcDJ.sp((double)0.0F, 0L), (a, b) -> new Tuple2.mcDJ.sp(a._1$mcD$sp() + b._1$mcD$sp(), a._2$mcJ$sp() + b._2$mcJ$sp()));
      if (var5 != null) {
         double rs = var5._1$mcD$sp();
         long rc = var5._2$mcJ$sp();
         Tuple2.mcDJ.sp var4 = new Tuple2.mcDJ.sp(rs, rc);
         double rs = ((Tuple2)var4)._1$mcD$sp();
         long rc = ((Tuple2)var4)._2$mcJ$sp();
         double u = rs / (double)rc;
         ObjectRef g = ObjectRef.create(Graph$.MODULE$.fromEdges(edges, defaultF$1(conf.rank()), Graph$.MODULE$.fromEdges$default$3(), Graph$.MODULE$.fromEdges$default$4(), scala.reflect.ClassTag..MODULE$.apply(Tuple4.class), scala.reflect.ClassTag..MODULE$.Double()).cache());
         this.materialize((Graph)g.elem);
         edges.unpersist(edges.unpersist$default$1());
         Graph qual$1 = (Graph)g.elem;
         Function1 x$1 = (ctx) -> {
            $anonfun$run$7(ctx);
            return BoxedUnit.UNIT;
         };
         Function2 x$2 = (g1, g2) -> new Tuple2.mcJD.sp(g1._1$mcJ$sp() + g2._1$mcJ$sp(), g1._2$mcD$sp() + g2._2$mcD$sp());
         TripletFields x$3 = qual$1.aggregateMessages$default$3();
         VertexRDD t0 = qual$1.aggregateMessages(x$1, x$2, x$3, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
         Graph gJoinT0 = ((Graph)g.elem).outerJoinVertices(t0, (vid, vd, msg) -> $anonfun$run$9(u, BoxesRunTime.unboxToLong(vid), vd, msg), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.reflect.ClassTag..MODULE$.apply(Tuple4.class), scala..less.colon.less..MODULE$.refl()).cache();
         this.materialize(gJoinT0);
         Graph qual$2 = (Graph)g.elem;
         boolean x$4 = qual$2.unpersist$default$1();
         qual$2.unpersist(x$4);
         g.elem = gJoinT0;
         scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), conf.maxIters()).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
            ((Graph)g.elem).cache();
            Graph qual$3 = (Graph)g.elem;
            Function1 x$5 = (ctx) -> {
               $anonfun$run$11(ctx);
               return BoxedUnit.UNIT;
            };
            Function2 x$6 = (g1, g2) -> {
               double[] out = (double[])(([D)g1).clone();
               org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().daxpy(out.length, (double)1.0F, g2, 1, out, 1);
               return out;
            };
            TripletFields x$7 = qual$3.aggregateMessages$default$3();
            VertexRDD t1 = qual$3.aggregateMessages(x$5, x$6, x$7, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)));
            Graph gJoinT1 = ((Graph)g.elem).outerJoinVertices(t1, (vid, vd, msg) -> $anonfun$run$13(BoxesRunTime.unboxToLong(vid), vd, msg), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)), scala.reflect.ClassTag..MODULE$.apply(Tuple4.class), scala..less.colon.less..MODULE$.refl()).cache();
            MODULE$.materialize(gJoinT1);
            Graph qual$4 = (Graph)g.elem;
            boolean x$8 = qual$4.unpersist$default$1();
            qual$4.unpersist(x$8);
            g.elem = gJoinT1;
            ((Graph)g.elem).cache();
            Graph qual$5 = (Graph)g.elem;
            Function1 x$9 = (ctx) -> {
               $anonfun$run$14(conf, u, ctx);
               return BoxedUnit.UNIT;
            };
            Function2 x$10 = (g1, g2) -> {
               double[] out1 = (double[])((double[])g1._1()).clone();
               org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().daxpy(out1.length, (double)1.0F, (double[])g2._1(), 1, out1, 1);
               double[] out2 = (double[])((double[])g2._2()).clone();
               org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().daxpy(out2.length, (double)1.0F, (double[])g2._2(), 1, out2, 1);
               return new Tuple3(out1, out2, BoxesRunTime.boxToDouble(BoxesRunTime.unboxToDouble(g1._3()) + BoxesRunTime.unboxToDouble(g2._3())));
            };
            TripletFields x$11 = qual$5.aggregateMessages$default$3();
            VertexRDD t2 = qual$5.aggregateMessages(x$9, x$10, x$11, scala.reflect.ClassTag..MODULE$.apply(Tuple3.class));
            Graph gJoinT2 = ((Graph)g.elem).outerJoinVertices(t2, (vid, vd, msg) -> $anonfun$run$16(BoxesRunTime.unboxToLong(vid), vd, msg), scala.reflect.ClassTag..MODULE$.apply(Tuple3.class), scala.reflect.ClassTag..MODULE$.apply(Tuple4.class), scala..less.colon.less..MODULE$.refl()).cache();
            MODULE$.materialize(gJoinT2);
            Graph qual$6 = (Graph)g.elem;
            boolean x$12 = qual$6.unpersist$default$1();
            qual$6.unpersist(x$12);
            g.elem = gJoinT2;
         });
         ((Graph)g.elem).cache();
         Graph qual$7 = (Graph)g.elem;
         Function1 x$13 = (ctx) -> {
            $anonfun$run$17(conf, u, ctx);
            return BoxedUnit.UNIT;
         };
         JFunction2.mcDDD.sp x$14 = (x$6, x$7) -> x$6 + x$7;
         TripletFields x$15 = qual$7.aggregateMessages$default$3();
         VertexRDD t3 = qual$7.aggregateMessages(x$13, x$14, x$15, scala.reflect.ClassTag..MODULE$.Double());
         Graph gJoinT3 = ((Graph)g.elem).outerJoinVertices(t3, (vid, vd, msg) -> $anonfun$run$19(BoxesRunTime.unboxToLong(vid), vd, msg), scala.reflect.ClassTag..MODULE$.Double(), scala.reflect.ClassTag..MODULE$.apply(Tuple4.class), scala..less.colon.less..MODULE$.refl()).cache();
         this.materialize(gJoinT3);
         Graph qual$8 = (Graph)g.elem;
         boolean x$16 = qual$8.unpersist$default$1();
         qual$8.unpersist(x$16);
         g.elem = gJoinT3;
         VertexRDD newVertices = ((Graph)g.elem).vertices().mapValues((Function1)((v) -> new Tuple4(v._1(), v._2(), v._3(), v._4())), scala.reflect.ClassTag..MODULE$.apply(Tuple4.class));
         return new Tuple2(Graph$.MODULE$.apply(newVertices, ((Graph)g.elem).edges(), Graph$.MODULE$.apply$default$3(), Graph$.MODULE$.apply$default$4(), Graph$.MODULE$.apply$default$5(), scala.reflect.ClassTag..MODULE$.apply(Tuple4.class), scala.reflect.ClassTag..MODULE$.Double()), BoxesRunTime.boxToDouble(u));
      } else {
         throw new MatchError(var5);
      }
   }

   private void materialize(final Graph g) {
      g.vertices().count();
      g.edges().count();
   }

   private static final Tuple4 defaultF$1(final int rank) {
      double[] v1 = (double[])scala.Array..MODULE$.fill(rank, (JFunction0.mcD.sp)() -> scala.util.Random..MODULE$.nextDouble(), scala.reflect.ClassTag..MODULE$.Double());
      double[] v2 = (double[])scala.Array..MODULE$.fill(rank, (JFunction0.mcD.sp)() -> scala.util.Random..MODULE$.nextDouble(), scala.reflect.ClassTag..MODULE$.Double());
      return new Tuple4(v1, v2, BoxesRunTime.boxToDouble((double)0.0F), BoxesRunTime.boxToDouble((double)0.0F));
   }

   // $FF: synthetic method
   public static final void $anonfun$run$7(final EdgeContext ctx) {
      ctx.sendToSrc(new Tuple2.mcJD.sp(1L, BoxesRunTime.unboxToDouble(ctx.attr())));
      ctx.sendToDst(new Tuple2.mcJD.sp(1L, BoxesRunTime.unboxToDouble(ctx.attr())));
   }

   // $FF: synthetic method
   public static final Tuple4 $anonfun$run$9(final double u$1, final long vid, final Tuple4 vd, final Option msg) {
      return new Tuple4(vd._1(), vd._2(), BoxesRunTime.boxToDouble(((Tuple2)msg.get())._2$mcD$sp() / (double)((Tuple2)msg.get())._1$mcJ$sp() - u$1), BoxesRunTime.boxToDouble((double)1.0F / scala.math.package..MODULE$.sqrt((double)((Tuple2)msg.get())._1$mcJ$sp())));
   }

   private static final void sendMsgTrainF$1(final SVDPlusPlus.Conf conf, final double u, final EdgeContext ctx) {
      Tuple2 var7 = new Tuple2(ctx.srcAttr(), ctx.dstAttr());
      if (var7 != null) {
         Tuple4 usr = (Tuple4)var7._1();
         Tuple4 itm = (Tuple4)var7._2();
         Tuple2 var6 = new Tuple2(usr, itm);
         Tuple4 usr = (Tuple4)var6._1();
         Tuple4 itm = (Tuple4)var6._2();
         Tuple2 var13 = new Tuple2(usr._1(), itm._1());
         if (var13 != null) {
            double[] p = (double[])var13._1();
            double[] q = (double[])var13._2();
            Tuple2 var12 = new Tuple2(p, q);
            double[] p = (double[])var12._1();
            double[] q = (double[])var12._2();
            int rank = p.length;
            double pred = u + BoxesRunTime.unboxToDouble(usr._3()) + BoxesRunTime.unboxToDouble(itm._3()) + org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().ddot(rank, q, 1, (double[])usr._2(), 1);
            pred = scala.math.package..MODULE$.max(pred, conf.minVal());
            pred = scala.math.package..MODULE$.min(pred, conf.maxVal());
            double err = BoxesRunTime.unboxToDouble(ctx.attr()) - pred;
            double[] updateP = (double[])(([D)q).clone();
            org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().dscal(rank, err * conf.gamma2(), updateP, 1);
            org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().daxpy(rank, -conf.gamma7() * conf.gamma2(), p, 1, updateP, 1);
            double[] updateQ = (double[])((double[])usr._2()).clone();
            org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().dscal(rank, err * conf.gamma2(), updateQ, 1);
            org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().daxpy(rank, -conf.gamma7() * conf.gamma2(), q, 1, updateQ, 1);
            double[] updateY = (double[])(([D)q).clone();
            org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().dscal(rank, err * BoxesRunTime.unboxToDouble(usr._4()) * conf.gamma2(), updateY, 1);
            org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().daxpy(rank, -conf.gamma7() * conf.gamma2(), (double[])itm._2(), 1, updateY, 1);
            ctx.sendToSrc(new Tuple3(updateP, updateY, BoxesRunTime.boxToDouble((err - conf.gamma6() * BoxesRunTime.unboxToDouble(usr._3())) * conf.gamma1())));
            ctx.sendToDst(new Tuple3(updateQ, updateY, BoxesRunTime.boxToDouble((err - conf.gamma6() * BoxesRunTime.unboxToDouble(itm._3())) * conf.gamma1())));
         } else {
            throw new MatchError(var13);
         }
      } else {
         throw new MatchError(var7);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$run$11(final EdgeContext ctx) {
      ctx.sendToSrc(((Tuple4)ctx.dstAttr())._2());
   }

   // $FF: synthetic method
   public static final Tuple4 $anonfun$run$13(final long vid, final Tuple4 vd, final Option msg) {
      if (msg.isDefined()) {
         double[] out = (double[])((double[])vd._1()).clone();
         org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().daxpy(out.length, BoxesRunTime.unboxToDouble(vd._4()), (double[])msg.get(), 1, out, 1);
         return new Tuple4(vd._1(), out, vd._3(), vd._4());
      } else {
         return vd;
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$run$14(final SVDPlusPlus.Conf conf$1, final double u$1, final EdgeContext ctx) {
      sendMsgTrainF$1(conf$1, u$1, ctx);
   }

   // $FF: synthetic method
   public static final Tuple4 $anonfun$run$16(final long vid, final Tuple4 vd, final Option msg) {
      double[] out1 = (double[])((double[])vd._1()).clone();
      org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().daxpy(out1.length, (double)1.0F, (double[])((Tuple3)msg.get())._1(), 1, out1, 1);
      double[] out2 = (double[])((double[])vd._2()).clone();
      org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().daxpy(out2.length, (double)1.0F, (double[])((Tuple3)msg.get())._2(), 1, out2, 1);
      return new Tuple4(out1, out2, BoxesRunTime.boxToDouble(BoxesRunTime.unboxToDouble(vd._3()) + BoxesRunTime.unboxToDouble(((Tuple3)msg.get())._3())), vd._4());
   }

   private static final void sendMsgTestF$1(final SVDPlusPlus.Conf conf, final double u, final EdgeContext ctx) {
      Tuple2 var7 = new Tuple2(ctx.srcAttr(), ctx.dstAttr());
      if (var7 != null) {
         Tuple4 usr = (Tuple4)var7._1();
         Tuple4 itm = (Tuple4)var7._2();
         Tuple2 var6 = new Tuple2(usr, itm);
         Tuple4 usr = (Tuple4)var6._1();
         Tuple4 itm = (Tuple4)var6._2();
         Tuple2 var13 = new Tuple2(usr._1(), itm._1());
         if (var13 != null) {
            double[] p = (double[])var13._1();
            double[] q = (double[])var13._2();
            Tuple2 var12 = new Tuple2(p, q);
            double[] var16 = (double[])var12._1();
            double[] q = (double[])var12._2();
            double pred = u + BoxesRunTime.unboxToDouble(usr._3()) + BoxesRunTime.unboxToDouble(itm._3()) + org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().ddot(q.length, q, 1, (double[])usr._2(), 1);
            pred = scala.math.package..MODULE$.max(pred, conf.minVal());
            pred = scala.math.package..MODULE$.min(pred, conf.maxVal());
            double err = (BoxesRunTime.unboxToDouble(ctx.attr()) - pred) * (BoxesRunTime.unboxToDouble(ctx.attr()) - pred);
            ctx.sendToDst(BoxesRunTime.boxToDouble(err));
         } else {
            throw new MatchError(var13);
         }
      } else {
         throw new MatchError(var7);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$run$17(final SVDPlusPlus.Conf conf$1, final double u$1, final EdgeContext ctx) {
      sendMsgTestF$1(conf$1, u$1, ctx);
   }

   // $FF: synthetic method
   public static final Tuple4 $anonfun$run$19(final long vid, final Tuple4 vd, final Option msg) {
      return msg.isDefined() ? new Tuple4(vd._1(), vd._2(), vd._3(), msg.get()) : vd;
   }

   private SVDPlusPlus$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
