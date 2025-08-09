package spire.optional;

import cats.kernel.Eq;
import cats.kernel.Group;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.Factory;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.SeqOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.Builder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction2;
import spire.algebra.Action;
import spire.algebra.partial.PartialAction;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mf\u0001B\u000e\u001d\u0001\u0005B\u0001B\f\u0001\u0003\u0006\u0004%Ia\f\u0005\tw\u0001\u0011\t\u0011)A\u0005a!)A\b\u0001C\u0005{!)\u0011\t\u0001C!\u0005\")\u0011\t\u0001C\u0001\u000b\")q\n\u0001C!!\"AA\u000b\u0001EC\u0002\u0013%Q\u000bC\u0003^\u0001\u0011\u0005a\fC\u0003a\u0001\u0011\u0005\u0011\rC\u0003c\u0001\u0011\u00051\rC\u0003h\u0001\u0011\u0005\u0001\u000eC\u0004\u0002\u001c\u0001!\t!!\b\t\u000f\u0005\r\u0002\u0001\"\u0001\u0002&\u001d9\u0011\u0011\u0006\u000f\t\u0002\u0005-bAB\u000e\u001d\u0011\u0003\ti\u0003\u0003\u0004=\u001f\u0011\u0005\u0011q\u0006\u0005\u0007\u0003>!\t!!\r\t\r\u0005{A\u0011AA\u001b\u0011\u0019\tu\u0002\"\u0001\u0002D!I\u00111J\bC\u0002\u0013\r\u0011Q\n\u0005\t\u0003?z\u0001\u0015!\u0003\u0002P!I\u0011\u0011M\bC\u0002\u0013\r\u00111\r\u0005\t\u0003cz\u0001\u0015!\u0003\u0002f!I\u00111O\bC\u0002\u0013\r\u0011Q\u000f\u0005\t\u0003\u001b{\u0001\u0015!\u0003\u0002x!9\u0011qR\b\u0005\u0004\u0005E%\u0001\u0002)fe6T!!\b\u0010\u0002\u0011=\u0004H/[8oC2T\u0011aH\u0001\u0006gBL'/Z\u0002\u0001'\r\u0001!\u0005\u000b\t\u0003G\u0019j\u0011\u0001\n\u0006\u0002K\u0005)1oY1mC&\u0011q\u0005\n\u0002\u0007\u0003:L(+\u001a4\u0011\t\rJ3fK\u0005\u0003U\u0011\u0012\u0011BR;oGRLwN\\\u0019\u0011\u0005\rb\u0013BA\u0017%\u0005\rIe\u000e^\u0001\b[\u0006\u0004\b/\u001b8h+\u0005\u0001\u0004\u0003B\u00199W-r!A\r\u001c\u0011\u0005M\"S\"\u0001\u001b\u000b\u0005U\u0002\u0013A\u0002\u001fs_>$h(\u0003\u00028I\u00051\u0001K]3eK\u001aL!!\u000f\u001e\u0003\u00075\u000b\u0007O\u0003\u00028I\u0005AQ.\u00199qS:<\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003}\u0001\u0003\"a\u0010\u0001\u000e\u0003qAQAL\u0002A\u0002A\nQ!\u00199qYf$\"aK\"\t\u000b\u0011#\u0001\u0019A\u0016\u0002\u0003-$BA\u0010$I\u0015\")q)\u0002a\u0001W\u0005\u0011a\u000e\r\u0005\u0006\u0013\u0016\u0001\raK\u0001\u0003]FBQaS\u0003A\u00021\u000b!A\\:\u0011\u0007\rj5&\u0003\u0002OI\tQAH]3qK\u0006$X\r\u001a \u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012!\u0015\t\u0003cIK!a\u0015\u001e\u0003\rM#(/\u001b8h\u00039IgN^3sg\u0016l\u0015\r\u001d9j]\u001e,\u0012A\u0016\t\u0005/r[3&D\u0001Y\u0015\tI&,A\u0005j[6,H/\u00192mK*\u00111\fJ\u0001\u000bG>dG.Z2uS>t\u0017BA\u001dY\u0003\u0019IgN^3siR\u00111f\u0018\u0005\u0006\t\"\u0001\raK\u0001\bS:4XM]:f+\u0005q\u0014!B5nC\u001e,W#\u00013\u0011\u0007E*7&\u0003\u0002gu\t\u00191+\u001a;\u0002\u000fA,'/\\;uKV!\u0011.a\u0002t)\rQ\u00171\u0002\u000b\u0003Wr\u00042\u0001\\8r\u001b\u0005i'B\u00018\u001f\u0003\u0011)H/\u001b7\n\u0005Al'aA(qiB\u0011!o\u001d\u0007\u0001\t\u0015!8B1\u0001v\u0005\t\u0019\u0016)\u0005\u0002wsB\u00111e^\u0005\u0003q\u0012\u0012qAT8uQ&tw\r\u0005\u0002$u&\u00111\u0010\n\u0002\u0004\u0003:L\b\"B?\f\u0001\bq\u0018aA2cMB1q0!\u0001\u0002\u0006El\u0011AW\u0005\u0004\u0003\u0007Q&a\u0002$bGR|'/\u001f\t\u0004e\u0006\u001dAABA\u0005\u0017\t\u0007QOA\u0001B\u0011\u001d\tia\u0003a\u0001\u0003\u001f\t1a]3r!!y\u0018\u0011CA\u0003\u0003+\t\u0018bAA\n5\n11+Z9PaN\u00042a`A\f\u0013\r\tIB\u0017\u0002\u0004'\u0016\f\u0018aB2p[B|7/\u001a\u000b\u0004}\u0005}\u0001BBA\u0011\u0019\u0001\u0007a(\u0001\u0003uQ\u0006$\u0018aB1oIRCWM\u001c\u000b\u0004}\u0005\u001d\u0002BBA\u0011\u001b\u0001\u0007a(\u0001\u0003QKJl\u0007CA \u0010'\ty!\u0005\u0006\u0002\u0002,Q\u0019a(a\r\t\u000b9\n\u0002\u0019\u0001\u0019\u0015\u0007y\n9\u0004C\u0004\u0002:I\u0001\r!a\u000f\u0002\u000bA\f\u0017N]:\u0011\t\rj\u0015Q\b\t\u0006G\u0005}2fK\u0005\u0004\u0003\u0003\"#A\u0002+va2,'\u0007F\u0004?\u0003\u000b\n9%!\u0013\t\u000b\u001d\u001b\u0002\u0019A\u0016\t\u000b%\u001b\u0002\u0019A\u0016\t\u000b-\u001b\u0002\u0019\u0001'\u0002\rA+'/\\#r+\t\ty\u0005E\u0003\u0002R\u0005mc(\u0004\u0002\u0002T)!\u0011QKA,\u0003\u0019YWM\u001d8fY*\u0011\u0011\u0011L\u0001\u0005G\u0006$8/\u0003\u0003\u0002^\u0005M#AA#r\u0003\u001d\u0001VM]7Fc\u0002\nQ\u0002U3s[&sG/Q2uS>tWCAA3!\u0019\t9'!\u001c,}5\u0011\u0011\u0011\u000e\u0006\u0004\u0003Wr\u0012aB1mO\u0016\u0014'/Y\u0005\u0005\u0003_\nIG\u0001\u0004BGRLwN\\\u0001\u000f!\u0016\u0014X.\u00138u\u0003\u000e$\u0018n\u001c8!\u0003%\u0001VM]7He>,\b/\u0006\u0002\u0002xA)\u0011\u0011PAD}9!\u00111PAB\u001d\u0011\ti(!!\u000f\u0007M\ny(C\u0001 \u0013\r\tYGH\u0005\u0005\u0003\u000b\u000bI'A\u0004qC\u000e\\\u0017mZ3\n\t\u0005%\u00151\u0012\u0002\u0006\u000fJ|W\u000f\u001d\u0006\u0005\u0003\u000b\u000bI'\u0001\u0006QKJlwI]8va\u0002\nA\u0003U3s[N+\u0017\u000fU1si&\fG.Q2uS>tWCBAJ\u0003k\u000b\u0019\u000b\u0006\u0003\u0002\u0016\u0006]\u0006cBAL\u0003;\u000b\tKP\u0007\u0003\u00033SA!a'\u0002j\u00059\u0001/\u0019:uS\u0006d\u0017\u0002BAP\u00033\u0013Q\u0002U1si&\fG.Q2uS>t\u0007#\u0002:\u0002$\u0006MFaBAS5\t\u0007\u0011q\u0015\u0002\u0003\u0007\u000e+B!!+\u00020F\u0019a/a+\u0011\u0013}\f\t\"!,\u0002\u0016\u0005E\u0006c\u0001:\u00020\u00129\u0011\u0011BAR\u0005\u0004)\b#\u0002:\u0002$\u00065\u0006c\u0001:\u00026\u00121\u0011\u0011\u0002\u000eC\u0002UDa! \u000eA\u0004\u0005e\u0006cB@\u0002\u0002\u0005M\u0016\u0011\u0015"
)
public class Perm implements Function1.mcII.sp {
   private Map inverseMapping;
   private final Map spire$optional$Perm$$mapping;
   private volatile boolean bitmap$0;

   public static PartialAction PermSeqPartialAction(final Factory cbf) {
      return Perm$.MODULE$.PermSeqPartialAction(cbf);
   }

   public static Group PermGroup() {
      return Perm$.MODULE$.PermGroup();
   }

   public static Action PermIntAction() {
      return Perm$.MODULE$.PermIntAction();
   }

   public static Eq PermEq() {
      return Perm$.MODULE$.PermEq();
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

   public Map spire$optional$Perm$$mapping() {
      return this.spire$optional$Perm$$mapping;
   }

   public int apply(final int k) {
      return this.apply$mcII$sp(k);
   }

   public Perm apply(final int n0, final int n1, final Seq ns) {
      Seq cycle = (Seq)((SeqOps)ns.$plus$colon(BoxesRunTime.boxToInteger(n1))).$plus$colon(BoxesRunTime.boxToInteger(n0));
      .MODULE$.require(!cycle.exists(this.image()), () -> "Cycle must be disjoint.");
      return this.compose(Perm$.MODULE$.apply(n0, n1, ns));
   }

   public String toString() {
      return ((IterableOnceOps)((IterableOps)this.spire$optional$Perm$$mapping().toSeq().sorted(scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.Int..MODULE$, scala.math.Ordering.Int..MODULE$))).map((x0$1) -> {
         if (x0$1 != null) {
            int k = x0$1._1$mcI$sp();
            int v = x0$1._2$mcI$sp();
            String var1 = (new StringBuilder(4)).append(k).append(" -> ").append(v).toString();
            return var1;
         } else {
            throw new MatchError(x0$1);
         }
      })).mkString("Perm(", ", ", ")");
   }

   private Map inverseMapping$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.inverseMapping = (Map)this.spire$optional$Perm$$mapping().map((x$1) -> x$1.swap$mcII$sp());
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.inverseMapping;
   }

   private Map inverseMapping() {
      return !this.bitmap$0 ? this.inverseMapping$lzycompute() : this.inverseMapping;
   }

   public int invert(final int k) {
      return BoxesRunTime.unboxToInt(this.inverseMapping().getOrElse(BoxesRunTime.boxToInteger(k), (JFunction0.mcI.sp)() -> k));
   }

   public Perm inverse() {
      return new Perm(this.inverseMapping());
   }

   public Set image() {
      return this.spire$optional$Perm$$mapping().keySet();
   }

   public Object permute(final SeqOps seq, final Factory cbf) {
      if (this.image().isEmpty()) {
         return spire.util.Opt..MODULE$.apply(cbf.fromSpecific(seq));
      } else if (BoxesRunTime.unboxToInt(this.image().max(scala.math.Ordering.Int..MODULE$)) >= seq.size()) {
         return spire.util.Opt..MODULE$.empty();
      } else {
         Builder builder = cbf.newBuilder();
         int index$macro$2 = 0;

         for(int limit$macro$4 = seq.size(); index$macro$2 < limit$macro$4; ++index$macro$2) {
            builder.$plus$eq(seq.apply(this.invert(index$macro$2)));
         }

         return spire.util.Opt..MODULE$.apply(builder.result());
      }
   }

   public Perm compose(final Perm that) {
      return new Perm(((IterableOnceOps)((IterableOps)this.image().$bar(that.image()).map((k) -> $anonfun$compose$1(this, that, BoxesRunTime.unboxToInt(k)))).filter(scala.Function..MODULE$.tupled((JFunction2.mcZII.sp)(x$2, x$3) -> x$2 != x$3))).toMap(scala..less.colon.less..MODULE$.refl()));
   }

   public Perm andThen(final Perm that) {
      return that.compose(this);
   }

   public int apply$mcII$sp(final int k) {
      return BoxesRunTime.unboxToInt(this.spire$optional$Perm$$mapping().getOrElse(BoxesRunTime.boxToInteger(k), (JFunction0.mcI.sp)() -> k));
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$compose$1(final Perm $this, final Perm that$1, final int k) {
      return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(k)), BoxesRunTime.boxToInteger($this.apply$mcII$sp(that$1.apply$mcII$sp(k))));
   }

   public Perm(final Map mapping) {
      this.spire$optional$Perm$$mapping = mapping;
      Function1.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
