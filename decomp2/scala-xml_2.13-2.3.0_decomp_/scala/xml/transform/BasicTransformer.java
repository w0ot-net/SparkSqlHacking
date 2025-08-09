package scala.xml.transform;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.xml.Elem$;
import scala.xml.Group;
import scala.xml.Node;
import scala.xml.NodeBuffer;
import scala.xml.NodeSeq$;

@ScalaSignature(
   bytes = "\u0006\u0005\u00113Qa\u0002\u0005\u0002\u0002=AQa\u0007\u0001\u0005\u0002qAQa\b\u0001\u0005\u0012\u0001BQ!\u0003\u0001\u0005\u00029BQ!\u0003\u0001\u0005\u0002uBQ!\u0003\u0001\u0005\u0002}BQ!\u0011\u0001\u0005B\t\u0013\u0001CQ1tS\u000e$&/\u00198tM>\u0014X.\u001a:\u000b\u0005%Q\u0011!\u0003;sC:\u001chm\u001c:n\u0015\tYA\"A\u0002y[2T\u0011!D\u0001\u0006g\u000e\fG.Y\u0002\u0001'\r\u0001\u0001\u0003\u0006\t\u0003#Ii\u0011\u0001D\u0005\u0003'1\u0011a!\u00118z%\u00164\u0007\u0003B\t\u0016/]I!A\u0006\u0007\u0003\u0013\u0019+hn\u0019;j_:\f\u0004C\u0001\r\u001a\u001b\u0005Q\u0011B\u0001\u000e\u000b\u0005\u0011qu\u000eZ3\u0002\rqJg.\u001b;?)\u0005i\u0002C\u0001\u0010\u0001\u001b\u0005A\u0011!C;oG\"\fgnZ3e)\r\tCE\n\t\u0003#\tJ!a\t\u0007\u0003\u000f\t{w\u000e\\3b]\")QE\u0001a\u0001/\u0005\ta\u000eC\u0003(\u0005\u0001\u0007\u0001&\u0001\u0002ogB\u0019\u0011\u0006L\f\u000e\u0003)R!a\u000b\u0007\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002.U\t\u00191+Z9\u0015\u0007!z\u0003\bC\u00031\u0007\u0001\u0007\u0011'\u0001\u0002jiB\u0019!'N\f\u000f\u0005E\u0019\u0014B\u0001\u001b\r\u0003\u001d\u0001\u0018mY6bO\u0016L!AN\u001c\u0003\u0011%#XM]1u_JT!\u0001\u000e\u0007\t\u000be\u001a\u0001\u0019\u0001\u001e\u0002\u00059\u0014\u0007C\u0001\r<\u0013\ta$B\u0001\u0006O_\u0012,')\u001e4gKJ$\"\u0001\u000b \t\u000b\u001d\"\u0001\u0019\u0001\u0015\u0015\u0005!\u0002\u0005\"B\u0013\u0006\u0001\u00049\u0012!B1qa2LHCA\fD\u0011\u0015)c\u00011\u0001\u0018\u0001"
)
public abstract class BasicTransformer implements Function1 {
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

   public Function1 andThen(final Function1 g) {
      return Function1.andThen$(this, g);
   }

   public String toString() {
      return Function1.toString$(this);
   }

   public boolean unchanged(final Node n, final Seq ns) {
      boolean var4;
      label25: {
         if (ns.length() == 1) {
            Object var10000 = ns.head();
            if (var10000 == null) {
               if (n == null) {
                  break label25;
               }
            } else if (var10000.equals(n)) {
               break label25;
            }
         }

         var4 = false;
         return var4;
      }

      var4 = true;
      return var4;
   }

   public Seq transform(final Iterator it, final NodeBuffer nb) {
      return (Seq)it.foldLeft(nb, (x$1, x$2) -> (NodeBuffer)x$1.$plus$plus$eq(this.transform(x$2)));
   }

   public Seq transform(final Seq ns) {
      Seq changed = (Seq)ns.flatMap((n) -> this.transform(n));
      return changed.length() == ns.length() && !((IterableOnceOps)changed.zip(ns)).exists((p) -> BoxesRunTime.boxToBoolean($anonfun$transform$3(p))) ? ns : changed;
   }

   public Seq transform(final Node n) {
      if (n.doTransform()) {
         if (n instanceof Group) {
            Group var4 = (Group)n;
            Seq xs = var4.nodes();
            return new Group(this.transform(xs));
         } else {
            Seq ch = n.child();
            Seq nch = this.transform(ch);
            return (Seq)(ch == nch ? n : Elem$.MODULE$.apply(n.prefix(), n.label(), n.attributes(), n.scope(), nch.isEmpty(), NodeSeq$.MODULE$.seqToNodeSeq(nch)));
         }
      } else {
         return n;
      }
   }

   public Node apply(final Node n) {
      Seq seq = this.transform(n);
      if (seq.length() > 1) {
         throw new UnsupportedOperationException("transform must return single node for root");
      } else {
         return (Node)seq.head();
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$transform$3(final Tuple2 p) {
      return !BoxesRunTime.equals(p._1(), p._2());
   }

   public BasicTransformer() {
      Function1.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
