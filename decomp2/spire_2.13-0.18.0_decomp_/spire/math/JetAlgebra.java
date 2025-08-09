package spire.math;

import algebra.ring.AdditiveCommutativeGroup;
import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.DivisionRing;
import algebra.ring.EuclideanRing;
import algebra.ring.Field;
import algebra.ring.MultiplicativeCommutativeGroup;
import algebra.ring.MultiplicativeCommutativeMonoid;
import algebra.ring.MultiplicativeCommutativeSemigroup;
import algebra.ring.MultiplicativeGroup;
import algebra.ring.MultiplicativeMonoid;
import algebra.ring.MultiplicativeSemigroup;
import algebra.ring.Ring;
import algebra.ring.Signed;
import cats.kernel.CommutativeGroup;
import cats.kernel.Eq;
import cats.kernel.Order;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.ArrayOps.;
import scala.math.BigInt;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import spire.algebra.CModule;
import spire.algebra.FieldAssociativeAlgebra;
import spire.algebra.LeftModule;
import spire.algebra.NRoot;
import spire.algebra.Trig;
import spire.algebra.VectorSpace;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001de!\u0002\r\u001a\u0001ei\u0002\u0002C1\u0001\u0005\u000b\u0007I1\u00012\t\u0011%\u0004!\u0011!Q\u0001\n\rD\u0001B\u001b\u0001\u0003\u0006\u0004%\u0019a\u001b\u0005\t_\u0002\u0011\t\u0011)A\u0005Y\"A\u0001\u000f\u0001BC\u0002\u0013\r\u0011\u000f\u0003\u0005}\u0001\t\u0005\t\u0015!\u0003s\u0011!i\bA!b\u0001\n\u0007q\b\"CA\u0003\u0001\t\u0005\t\u0015!\u0003\u0000\u0011)\t9\u0001\u0001BC\u0002\u0013\r\u0011\u0011\u0002\u0005\u000b\u0003#\u0001!\u0011!Q\u0001\n\u0005-\u0001BCA\n\u0001\t\u0015\r\u0011b\u0001\u0002\u0016!Q\u0011Q\u0004\u0001\u0003\u0002\u0003\u0006I!a\u0006\t\u0015\u0005}\u0001A!b\u0001\n\u0007\t\t\u0003\u0003\u0006\u0002*\u0001\u0011\t\u0011)A\u0005\u0003GA!\"a\u000b\u0001\u0005\u000b\u0007I1AA\u0017\u0011)\t)\u0004\u0001B\u0001B\u0003%\u0011q\u0006\u0005\u000b\u0003o\u0001!Q1A\u0005\u0004\u0005e\u0002BCA\"\u0001\t\u0005\t\u0015!\u0003\u0002<!9\u0011Q\t\u0001\u0005\u0002\u0005\u001d\u0003BBA0\u0001\u0011\u0005a\u0010C\u0004\u0002b\u0001!\t!!\u0003\t\u000f\u0005\r\u0004\u0001\"\u0001\u0002f!9\u0011q\u000e\u0001\u0005\u0002\u0005E$A\u0003&fi\u0006cw-\u001a2sC*\u0011!dG\u0001\u0005[\u0006$\bNC\u0001\u001d\u0003\u0015\u0019\b/\u001b:f+\tq2f\u0005\u0005\u0001?\u0015B5JT,[!\t\u00013%D\u0001\"\u0015\u0005\u0011\u0013!B:dC2\f\u0017B\u0001\u0013\"\u0005\u0019\te.\u001f*fMB\u0019aeJ\u0015\u000e\u0003eI!\u0001K\r\u0003\u0015)+G/S:GS\u0016dG\r\u0005\u0002+W1\u0001A!\u0003\u0017\u0001A\u0003\u0005\tQ1\u0001/\u0005\u0005!6\u0001A\t\u0003_I\u0002\"\u0001\t\u0019\n\u0005E\n#a\u0002(pi\"Lgn\u001a\t\u0003AMJ!\u0001N\u0011\u0003\u0007\u0005s\u0017\u0010\u000b\u0003,me\u001a\u0005C\u0001\u00118\u0013\tA\u0014EA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017'B\u0012;wubdB\u0001\u0011<\u0013\ta\u0014%A\u0003GY>\fG/\r\u0003%}\t\u0013cBA C\u001b\u0005\u0001%BA!.\u0003\u0019a$o\\8u}%\t!%M\u0003$\t\u0016;eI\u0004\u0002!\u000b&\u0011a)I\u0001\u0007\t>,(\r\\32\t\u0011r$I\t\t\u0004M%K\u0013B\u0001&\u001a\u0005%QU\r^%t)JLw\rE\u0002'\u0019&J!!T\r\u0003\u0015)+G/S:O%>|G\u000f\u0005\u0003P%RKS\"\u0001)\u000b\u0005E[\u0012aB1mO\u0016\u0014'/Y\u0005\u0003'B\u00131BV3di>\u00148\u000b]1dKB\u0019a%V\u0015\n\u0005YK\"a\u0001&fiB!q\n\u0017+*\u0013\tI\u0006KA\fGS\u0016dG-Q:t_\u000eL\u0017\r^5wK\u0006cw-\u001a2sCB\u00111L\u0018\b\u0003}qK!!X\u0011\u0002\u000fA\f7m[1hK&\u0011q\f\u0019\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003;\u0006\n\u0011aY\u000b\u0002GB\u0019AmZ\u0015\u000e\u0003\u0015T!AZ\u0011\u0002\u000fI,g\r\\3di&\u0011\u0001.\u001a\u0002\t\u00072\f7o\u001d+bO\u0006\u00111\rI\u0001\u0002IV\tA\u000e\u0005\u0002'[&\u0011a.\u0007\u0002\u0007\u0015\u0016$H)[7\u0002\u0005\u0011\u0004\u0013AA3r+\u0005\u0011\bcA:zS9\u0011A\u000f\u001f\b\u0003k^t!a\u0010<\n\u0003qI!!U\u000e\n\u0005u\u0003\u0016B\u0001>|\u0005\t)\u0015O\u0003\u0002^!\u0006\u0019Q-\u001d\u0011\u0002\u0003\u0019,\u0012a \t\u0005g\u0006\u0005\u0011&C\u0002\u0002\u0004m\u0014QAR5fY\u0012\f!A\u001a\u0011\u0002\u00039,\"!a\u0003\u0011\t=\u000bi!K\u0005\u0004\u0003\u001f\u0001&!\u0002(S_>$\u0018A\u00018!\u0003\u0005yWCAA\f!\u0011\u0019\u0018\u0011D\u0015\n\u0007\u0005m1PA\u0003Pe\u0012,'/\u0001\u0002pA\u0005\tA/\u0006\u0002\u0002$A!q*!\n*\u0013\r\t9\u0003\u0015\u0002\u0005)JLw-\u0001\u0002uA\u0005\t1/\u0006\u0002\u00020A!1/!\r*\u0013\r\t\u0019d\u001f\u0002\u0007'&<g.\u001a3\u0002\u0005M\u0004\u0013!\u0001<\u0016\u0005\u0005m\u0002#B(S\u0003{I\u0003\u0003\u0002\u0011\u0002@%J1!!\u0011\"\u0005\u0015\t%O]1z\u0003\t1\b%\u0001\u0004=S:LGO\u0010\u000b\u0003\u0003\u0013\"B#a\u0013\u0002N\u0005=\u0013\u0011KA*\u0003+\n9&!\u0017\u0002\\\u0005u\u0003c\u0001\u0014\u0001S!)\u0011m\u0005a\u0002G\")!n\u0005a\u0002Y\")\u0001o\u0005a\u0002e\")Qp\u0005a\u0002\u007f\"9\u0011qA\nA\u0004\u0005-\u0001bBA\n'\u0001\u000f\u0011q\u0003\u0005\b\u0003?\u0019\u00029AA\u0012\u0011\u001d\tYc\u0005a\u0002\u0003_Aq!a\u000e\u0014\u0001\b\tY$\u0001\u0004tG\u0006d\u0017M]\u0001\u0006]J|w\u000e^\u0001\u0007i&lWm\u001d7\u0015\u000bQ\u000b9'a\u001b\t\r\u0005%d\u00031\u0001*\u0003\u0005\t\u0007BBA7-\u0001\u0007A+A\u0001x\u0003\r!w\u000e\u001e\u000b\u0006S\u0005M\u0014q\u000f\u0005\u0007\u0003k:\u0002\u0019\u0001+\u0002\u0003aDa!!\u001f\u0018\u0001\u0004!\u0016!A=)\u000f\u0001\ti(a!\u0002\u0006B\u0019\u0001%a \n\u0007\u0005\u0005\u0015E\u0001\tTKJL\u0017\r\u001c,feNLwN\\+J\t\u0006)a/\u00197vKz\t\u0001\u0001"
)
public class JetAlgebra implements JetIsField, JetIsTrig, JetIsNRoot, FieldAssociativeAlgebra {
   private static final long serialVersionUID = 0L;
   private final ClassTag c;
   private final JetDim d;
   public final Eq eq;
   public final Field f;
   public final NRoot n;
   public final Order o;
   public final Trig t;
   public final Signed s;
   public final VectorSpace v;

   public Field scalar$mcI$sp() {
      return VectorSpace.scalar$mcI$sp$(this);
   }

   public Field scalar$mcJ$sp() {
      return VectorSpace.scalar$mcJ$sp$(this);
   }

   public Object divr(final Object v, final Object f) {
      return VectorSpace.divr$(this, v, f);
   }

   public Object divr$mcD$sp(final Object v, final double f) {
      return VectorSpace.divr$mcD$sp$(this, v, f);
   }

   public Object divr$mcF$sp(final Object v, final float f) {
      return VectorSpace.divr$mcF$sp$(this, v, f);
   }

   public Object divr$mcI$sp(final Object v, final int f) {
      return VectorSpace.divr$mcI$sp$(this, v, f);
   }

   public Object divr$mcJ$sp(final Object v, final long f) {
      return VectorSpace.divr$mcJ$sp$(this, v, f);
   }

   public Object timesr(final Object v, final Object r) {
      return CModule.timesr$(this, v, r);
   }

   public Object timesr$mcD$sp(final Object v, final double r) {
      return CModule.timesr$mcD$sp$(this, v, r);
   }

   public Object timesr$mcF$sp(final Object v, final float r) {
      return CModule.timesr$mcF$sp$(this, v, r);
   }

   public Object timesr$mcI$sp(final Object v, final int r) {
      return CModule.timesr$mcI$sp$(this, v, r);
   }

   public Object timesr$mcJ$sp(final Object v, final long r) {
      return CModule.timesr$mcJ$sp$(this, v, r);
   }

   public Object timesl$mcD$sp(final double r, final Object v) {
      return LeftModule.timesl$mcD$sp$(this, r, v);
   }

   public Object timesl$mcF$sp(final float r, final Object v) {
      return LeftModule.timesl$mcF$sp$(this, r, v);
   }

   public Object timesl$mcI$sp(final int r, final Object v) {
      return LeftModule.timesl$mcI$sp$(this, r, v);
   }

   public Object timesl$mcJ$sp(final long r, final Object v) {
      return LeftModule.timesl$mcJ$sp$(this, r, v);
   }

   public Jet nroot(final Jet a, final int k) {
      return JetIsNRoot.nroot$(this, a, k);
   }

   public Jet sqrt(final Jet a) {
      return JetIsNRoot.sqrt$(this, a);
   }

   public Jet fpow(final Jet a, final Jet b) {
      return JetIsNRoot.fpow$(this, (Jet)a, b);
   }

   public Jet fpow(final Object a, final Jet b) {
      return JetIsNRoot.fpow$(this, (Object)a, b);
   }

   public double nroot$mcD$sp(final double a, final int n) {
      return NRoot.nroot$mcD$sp$(this, a, n);
   }

   public float nroot$mcF$sp(final float a, final int n) {
      return NRoot.nroot$mcF$sp$(this, a, n);
   }

   public int nroot$mcI$sp(final int a, final int n) {
      return NRoot.nroot$mcI$sp$(this, a, n);
   }

   public long nroot$mcJ$sp(final long a, final int n) {
      return NRoot.nroot$mcJ$sp$(this, a, n);
   }

   public double sqrt$mcD$sp(final double a) {
      return NRoot.sqrt$mcD$sp$(this, a);
   }

   public float sqrt$mcF$sp(final float a) {
      return NRoot.sqrt$mcF$sp$(this, a);
   }

   public int sqrt$mcI$sp(final int a) {
      return NRoot.sqrt$mcI$sp$(this, a);
   }

   public long sqrt$mcJ$sp(final long a) {
      return NRoot.sqrt$mcJ$sp$(this, a);
   }

   public double fpow$mcD$sp(final double a, final double b) {
      return NRoot.fpow$mcD$sp$(this, a, b);
   }

   public float fpow$mcF$sp(final float a, final float b) {
      return NRoot.fpow$mcF$sp$(this, a, b);
   }

   public int fpow$mcI$sp(final int a, final int b) {
      return NRoot.fpow$mcI$sp$(this, a, b);
   }

   public long fpow$mcJ$sp(final long a, final long b) {
      return NRoot.fpow$mcJ$sp$(this, a, b);
   }

   public Jet e() {
      return JetIsTrig.e$(this);
   }

   public Jet e$mcD$sp() {
      return JetIsTrig.e$mcD$sp$(this);
   }

   public Jet e$mcF$sp() {
      return JetIsTrig.e$mcF$sp$(this);
   }

   public Jet pi() {
      return JetIsTrig.pi$(this);
   }

   public Jet pi$mcD$sp() {
      return JetIsTrig.pi$mcD$sp$(this);
   }

   public Jet pi$mcF$sp() {
      return JetIsTrig.pi$mcF$sp$(this);
   }

   public Jet exp(final Jet a) {
      return JetIsTrig.exp$(this, a);
   }

   public Jet exp$mcD$sp(final Jet a) {
      return JetIsTrig.exp$mcD$sp$(this, a);
   }

   public Jet exp$mcF$sp(final Jet a) {
      return JetIsTrig.exp$mcF$sp$(this, a);
   }

   public Jet expm1(final Jet a) {
      return JetIsTrig.expm1$(this, a);
   }

   public Jet expm1$mcD$sp(final Jet a) {
      return JetIsTrig.expm1$mcD$sp$(this, a);
   }

   public Jet expm1$mcF$sp(final Jet a) {
      return JetIsTrig.expm1$mcF$sp$(this, a);
   }

   public Jet log(final Jet a) {
      return JetIsTrig.log$(this, a);
   }

   public Jet log$mcD$sp(final Jet a) {
      return JetIsTrig.log$mcD$sp$(this, a);
   }

   public Jet log$mcF$sp(final Jet a) {
      return JetIsTrig.log$mcF$sp$(this, a);
   }

   public Jet log1p(final Jet a) {
      return JetIsTrig.log1p$(this, a);
   }

   public Jet log1p$mcD$sp(final Jet a) {
      return JetIsTrig.log1p$mcD$sp$(this, a);
   }

   public Jet log1p$mcF$sp(final Jet a) {
      return JetIsTrig.log1p$mcF$sp$(this, a);
   }

   public Jet sin(final Jet a) {
      return JetIsTrig.sin$(this, a);
   }

   public Jet sin$mcD$sp(final Jet a) {
      return JetIsTrig.sin$mcD$sp$(this, a);
   }

   public Jet sin$mcF$sp(final Jet a) {
      return JetIsTrig.sin$mcF$sp$(this, a);
   }

   public Jet cos(final Jet a) {
      return JetIsTrig.cos$(this, a);
   }

   public Jet cos$mcD$sp(final Jet a) {
      return JetIsTrig.cos$mcD$sp$(this, a);
   }

   public Jet cos$mcF$sp(final Jet a) {
      return JetIsTrig.cos$mcF$sp$(this, a);
   }

   public Jet tan(final Jet a) {
      return JetIsTrig.tan$(this, a);
   }

   public Jet tan$mcD$sp(final Jet a) {
      return JetIsTrig.tan$mcD$sp$(this, a);
   }

   public Jet tan$mcF$sp(final Jet a) {
      return JetIsTrig.tan$mcF$sp$(this, a);
   }

   public Jet asin(final Jet a) {
      return JetIsTrig.asin$(this, a);
   }

   public Jet asin$mcD$sp(final Jet a) {
      return JetIsTrig.asin$mcD$sp$(this, a);
   }

   public Jet asin$mcF$sp(final Jet a) {
      return JetIsTrig.asin$mcF$sp$(this, a);
   }

   public Jet acos(final Jet a) {
      return JetIsTrig.acos$(this, a);
   }

   public Jet acos$mcD$sp(final Jet a) {
      return JetIsTrig.acos$mcD$sp$(this, a);
   }

   public Jet acos$mcF$sp(final Jet a) {
      return JetIsTrig.acos$mcF$sp$(this, a);
   }

   public Jet atan(final Jet a) {
      return JetIsTrig.atan$(this, a);
   }

   public Jet atan$mcD$sp(final Jet a) {
      return JetIsTrig.atan$mcD$sp$(this, a);
   }

   public Jet atan$mcF$sp(final Jet a) {
      return JetIsTrig.atan$mcF$sp$(this, a);
   }

   public Jet atan2(final Jet y, final Jet x) {
      return JetIsTrig.atan2$(this, y, x);
   }

   public Jet atan2$mcD$sp(final Jet y, final Jet x) {
      return JetIsTrig.atan2$mcD$sp$(this, y, x);
   }

   public Jet atan2$mcF$sp(final Jet y, final Jet x) {
      return JetIsTrig.atan2$mcF$sp$(this, y, x);
   }

   public Jet sinh(final Jet x) {
      return JetIsTrig.sinh$(this, x);
   }

   public Jet sinh$mcD$sp(final Jet x) {
      return JetIsTrig.sinh$mcD$sp$(this, x);
   }

   public Jet sinh$mcF$sp(final Jet x) {
      return JetIsTrig.sinh$mcF$sp$(this, x);
   }

   public Jet cosh(final Jet x) {
      return JetIsTrig.cosh$(this, x);
   }

   public Jet cosh$mcD$sp(final Jet x) {
      return JetIsTrig.cosh$mcD$sp$(this, x);
   }

   public Jet cosh$mcF$sp(final Jet x) {
      return JetIsTrig.cosh$mcF$sp$(this, x);
   }

   public Jet tanh(final Jet x) {
      return JetIsTrig.tanh$(this, x);
   }

   public Jet tanh$mcD$sp(final Jet x) {
      return JetIsTrig.tanh$mcD$sp$(this, x);
   }

   public Jet tanh$mcF$sp(final Jet x) {
      return JetIsTrig.tanh$mcF$sp$(this, x);
   }

   public Jet toRadians(final Jet a) {
      return JetIsTrig.toRadians$(this, a);
   }

   public Jet toRadians$mcD$sp(final Jet a) {
      return JetIsTrig.toRadians$mcD$sp$(this, a);
   }

   public Jet toRadians$mcF$sp(final Jet a) {
      return JetIsTrig.toRadians$mcF$sp$(this, a);
   }

   public Jet toDegrees(final Jet a) {
      return JetIsTrig.toDegrees$(this, a);
   }

   public Jet toDegrees$mcD$sp(final Jet a) {
      return JetIsTrig.toDegrees$mcD$sp$(this, a);
   }

   public Jet toDegrees$mcF$sp(final Jet a) {
      return JetIsTrig.toDegrees$mcF$sp$(this, a);
   }

   public double exp$mcD$sp(final double a) {
      return Trig.exp$mcD$sp$(this, a);
   }

   public float exp$mcF$sp(final float a) {
      return Trig.exp$mcF$sp$(this, a);
   }

   public double expm1$mcD$sp(final double a) {
      return Trig.expm1$mcD$sp$(this, a);
   }

   public float expm1$mcF$sp(final float a) {
      return Trig.expm1$mcF$sp$(this, a);
   }

   public double log$mcD$sp(final double a) {
      return Trig.log$mcD$sp$(this, a);
   }

   public float log$mcF$sp(final float a) {
      return Trig.log$mcF$sp$(this, a);
   }

   public double log1p$mcD$sp(final double a) {
      return Trig.log1p$mcD$sp$(this, a);
   }

   public float log1p$mcF$sp(final float a) {
      return Trig.log1p$mcF$sp$(this, a);
   }

   public double sin$mcD$sp(final double a) {
      return Trig.sin$mcD$sp$(this, a);
   }

   public float sin$mcF$sp(final float a) {
      return Trig.sin$mcF$sp$(this, a);
   }

   public double cos$mcD$sp(final double a) {
      return Trig.cos$mcD$sp$(this, a);
   }

   public float cos$mcF$sp(final float a) {
      return Trig.cos$mcF$sp$(this, a);
   }

   public double tan$mcD$sp(final double a) {
      return Trig.tan$mcD$sp$(this, a);
   }

   public float tan$mcF$sp(final float a) {
      return Trig.tan$mcF$sp$(this, a);
   }

   public double asin$mcD$sp(final double a) {
      return Trig.asin$mcD$sp$(this, a);
   }

   public float asin$mcF$sp(final float a) {
      return Trig.asin$mcF$sp$(this, a);
   }

   public double acos$mcD$sp(final double a) {
      return Trig.acos$mcD$sp$(this, a);
   }

   public float acos$mcF$sp(final float a) {
      return Trig.acos$mcF$sp$(this, a);
   }

   public double atan$mcD$sp(final double a) {
      return Trig.atan$mcD$sp$(this, a);
   }

   public float atan$mcF$sp(final float a) {
      return Trig.atan$mcF$sp$(this, a);
   }

   public double atan2$mcD$sp(final double y, final double x) {
      return Trig.atan2$mcD$sp$(this, y, x);
   }

   public float atan2$mcF$sp(final float y, final float x) {
      return Trig.atan2$mcF$sp$(this, y, x);
   }

   public double sinh$mcD$sp(final double x) {
      return Trig.sinh$mcD$sp$(this, x);
   }

   public float sinh$mcF$sp(final float x) {
      return Trig.sinh$mcF$sp$(this, x);
   }

   public double cosh$mcD$sp(final double x) {
      return Trig.cosh$mcD$sp$(this, x);
   }

   public float cosh$mcF$sp(final float x) {
      return Trig.cosh$mcF$sp$(this, x);
   }

   public double tanh$mcD$sp(final double x) {
      return Trig.tanh$mcD$sp$(this, x);
   }

   public float tanh$mcF$sp(final float x) {
      return Trig.tanh$mcF$sp$(this, x);
   }

   public double toRadians$mcD$sp(final double a) {
      return Trig.toRadians$mcD$sp$(this, a);
   }

   public float toRadians$mcF$sp(final float a) {
      return Trig.toRadians$mcF$sp$(this, a);
   }

   public double toDegrees$mcD$sp(final double a) {
      return Trig.toDegrees$mcD$sp$(this, a);
   }

   public float toDegrees$mcF$sp(final float a) {
      return Trig.toDegrees$mcF$sp$(this, a);
   }

   public Jet fromDouble(final double n) {
      return JetIsField.fromDouble$(this, n);
   }

   public Jet fromDouble$mcD$sp(final double n) {
      return JetIsField.fromDouble$mcD$sp$(this, n);
   }

   public Jet fromDouble$mcF$sp(final double n) {
      return JetIsField.fromDouble$mcF$sp$(this, n);
   }

   public Jet div(final Jet a, final Jet b) {
      return JetIsField.div$(this, a, b);
   }

   public Jet div$mcD$sp(final Jet a, final Jet b) {
      return JetIsField.div$mcD$sp$(this, a, b);
   }

   public Jet div$mcF$sp(final Jet a, final Jet b) {
      return JetIsField.div$mcF$sp$(this, a, b);
   }

   public Object gcd(final Object a, final Object b, final Eq eqA) {
      return Field.gcd$(this, a, b, eqA);
   }

   public double gcd$mcD$sp(final double a, final double b, final Eq eqA) {
      return Field.gcd$mcD$sp$(this, a, b, eqA);
   }

   public float gcd$mcF$sp(final float a, final float b, final Eq eqA) {
      return Field.gcd$mcF$sp$(this, a, b, eqA);
   }

   public int gcd$mcI$sp(final int a, final int b, final Eq eqA) {
      return Field.gcd$mcI$sp$(this, a, b, eqA);
   }

   public long gcd$mcJ$sp(final long a, final long b, final Eq eqA) {
      return Field.gcd$mcJ$sp$(this, a, b, eqA);
   }

   public Object lcm(final Object a, final Object b, final Eq eqA) {
      return Field.lcm$(this, a, b, eqA);
   }

   public double lcm$mcD$sp(final double a, final double b, final Eq eqA) {
      return Field.lcm$mcD$sp$(this, a, b, eqA);
   }

   public float lcm$mcF$sp(final float a, final float b, final Eq eqA) {
      return Field.lcm$mcF$sp$(this, a, b, eqA);
   }

   public int lcm$mcI$sp(final int a, final int b, final Eq eqA) {
      return Field.lcm$mcI$sp$(this, a, b, eqA);
   }

   public long lcm$mcJ$sp(final long a, final long b, final Eq eqA) {
      return Field.lcm$mcJ$sp$(this, a, b, eqA);
   }

   public BigInt euclideanFunction(final Object a) {
      return Field.euclideanFunction$(this, a);
   }

   public BigInt euclideanFunction$mcD$sp(final double a) {
      return Field.euclideanFunction$mcD$sp$(this, a);
   }

   public BigInt euclideanFunction$mcF$sp(final float a) {
      return Field.euclideanFunction$mcF$sp$(this, a);
   }

   public BigInt euclideanFunction$mcI$sp(final int a) {
      return Field.euclideanFunction$mcI$sp$(this, a);
   }

   public BigInt euclideanFunction$mcJ$sp(final long a) {
      return Field.euclideanFunction$mcJ$sp$(this, a);
   }

   public Object equot(final Object a, final Object b) {
      return Field.equot$(this, a, b);
   }

   public double equot$mcD$sp(final double a, final double b) {
      return Field.equot$mcD$sp$(this, a, b);
   }

   public float equot$mcF$sp(final float a, final float b) {
      return Field.equot$mcF$sp$(this, a, b);
   }

   public int equot$mcI$sp(final int a, final int b) {
      return Field.equot$mcI$sp$(this, a, b);
   }

   public long equot$mcJ$sp(final long a, final long b) {
      return Field.equot$mcJ$sp$(this, a, b);
   }

   public Object emod(final Object a, final Object b) {
      return Field.emod$(this, a, b);
   }

   public double emod$mcD$sp(final double a, final double b) {
      return Field.emod$mcD$sp$(this, a, b);
   }

   public float emod$mcF$sp(final float a, final float b) {
      return Field.emod$mcF$sp$(this, a, b);
   }

   public int emod$mcI$sp(final int a, final int b) {
      return Field.emod$mcI$sp$(this, a, b);
   }

   public long emod$mcJ$sp(final long a, final long b) {
      return Field.emod$mcJ$sp$(this, a, b);
   }

   public Tuple2 equotmod(final Object a, final Object b) {
      return Field.equotmod$(this, a, b);
   }

   public Tuple2 equotmod$mcD$sp(final double a, final double b) {
      return Field.equotmod$mcD$sp$(this, a, b);
   }

   public Tuple2 equotmod$mcF$sp(final float a, final float b) {
      return Field.equotmod$mcF$sp$(this, a, b);
   }

   public Tuple2 equotmod$mcI$sp(final int a, final int b) {
      return Field.equotmod$mcI$sp$(this, a, b);
   }

   public Tuple2 equotmod$mcJ$sp(final long a, final long b) {
      return Field.equotmod$mcJ$sp$(this, a, b);
   }

   public int fromDouble$mcI$sp(final double a) {
      return Field.fromDouble$mcI$sp$(this, a);
   }

   public long fromDouble$mcJ$sp(final double a) {
      return Field.fromDouble$mcJ$sp$(this, a);
   }

   public CommutativeGroup multiplicative() {
      return MultiplicativeCommutativeGroup.multiplicative$(this);
   }

   public CommutativeGroup multiplicative$mcD$sp() {
      return MultiplicativeCommutativeGroup.multiplicative$mcD$sp$(this);
   }

   public CommutativeGroup multiplicative$mcF$sp() {
      return MultiplicativeCommutativeGroup.multiplicative$mcF$sp$(this);
   }

   public CommutativeGroup multiplicative$mcI$sp() {
      return MultiplicativeCommutativeGroup.multiplicative$mcI$sp$(this);
   }

   public CommutativeGroup multiplicative$mcJ$sp() {
      return MultiplicativeCommutativeGroup.multiplicative$mcJ$sp$(this);
   }

   public byte fromDouble$mcB$sp(final double a) {
      return DivisionRing.fromDouble$mcB$sp$(this, a);
   }

   public short fromDouble$mcS$sp(final double a) {
      return DivisionRing.fromDouble$mcS$sp$(this, a);
   }

   public Object reciprocal(final Object x) {
      return MultiplicativeGroup.reciprocal$(this, x);
   }

   public double reciprocal$mcD$sp(final double x) {
      return MultiplicativeGroup.reciprocal$mcD$sp$(this, x);
   }

   public float reciprocal$mcF$sp(final float x) {
      return MultiplicativeGroup.reciprocal$mcF$sp$(this, x);
   }

   public int reciprocal$mcI$sp(final int x) {
      return MultiplicativeGroup.reciprocal$mcI$sp$(this, x);
   }

   public long reciprocal$mcJ$sp(final long x) {
      return MultiplicativeGroup.reciprocal$mcJ$sp$(this, x);
   }

   public double div$mcD$sp(final double x, final double y) {
      return MultiplicativeGroup.div$mcD$sp$(this, x, y);
   }

   public float div$mcF$sp(final float x, final float y) {
      return MultiplicativeGroup.div$mcF$sp$(this, x, y);
   }

   public int div$mcI$sp(final int x, final int y) {
      return MultiplicativeGroup.div$mcI$sp$(this, x, y);
   }

   public long div$mcJ$sp(final long x, final long y) {
      return MultiplicativeGroup.div$mcJ$sp$(this, x, y);
   }

   public Object pow(final Object a, final int n) {
      return MultiplicativeGroup.pow$(this, a, n);
   }

   public double pow$mcD$sp(final double a, final int n) {
      return MultiplicativeGroup.pow$mcD$sp$(this, a, n);
   }

   public float pow$mcF$sp(final float a, final int n) {
      return MultiplicativeGroup.pow$mcF$sp$(this, a, n);
   }

   public int pow$mcI$sp(final int a, final int n) {
      return MultiplicativeGroup.pow$mcI$sp$(this, a, n);
   }

   public long pow$mcJ$sp(final long a, final int n) {
      return MultiplicativeGroup.pow$mcJ$sp$(this, a, n);
   }

   public Jet minus(final Jet a, final Jet b) {
      return JetIsRing.minus$(this, a, b);
   }

   public Jet minus$mcD$sp(final Jet a, final Jet b) {
      return JetIsRing.minus$mcD$sp$(this, a, b);
   }

   public Jet minus$mcF$sp(final Jet a, final Jet b) {
      return JetIsRing.minus$mcF$sp$(this, a, b);
   }

   public Jet negate(final Jet a) {
      return JetIsRing.negate$(this, a);
   }

   public Jet negate$mcD$sp(final Jet a) {
      return JetIsRing.negate$mcD$sp$(this, a);
   }

   public Jet negate$mcF$sp(final Jet a) {
      return JetIsRing.negate$mcF$sp$(this, a);
   }

   public Jet one() {
      return JetIsRing.one$(this);
   }

   public Jet one$mcD$sp() {
      return JetIsRing.one$mcD$sp$(this);
   }

   public Jet one$mcF$sp() {
      return JetIsRing.one$mcF$sp$(this);
   }

   public Jet plus(final Jet a, final Jet b) {
      return JetIsRing.plus$(this, a, b);
   }

   public Jet plus$mcD$sp(final Jet a, final Jet b) {
      return JetIsRing.plus$mcD$sp$(this, a, b);
   }

   public Jet plus$mcF$sp(final Jet a, final Jet b) {
      return JetIsRing.plus$mcF$sp$(this, a, b);
   }

   public Jet pow$mcD$sp(final Jet a, final int b) {
      return JetIsRing.pow$mcD$sp$(this, a, b);
   }

   public Jet pow$mcF$sp(final Jet a, final int b) {
      return JetIsRing.pow$mcF$sp$(this, a, b);
   }

   public Jet times(final Jet a, final Jet b) {
      return JetIsRing.times$(this, a, b);
   }

   public Jet times$mcD$sp(final Jet a, final Jet b) {
      return JetIsRing.times$mcD$sp$(this, a, b);
   }

   public Jet times$mcF$sp(final Jet a, final Jet b) {
      return JetIsRing.times$mcF$sp$(this, a, b);
   }

   public Jet zero() {
      return JetIsRing.zero$(this);
   }

   public Jet zero$mcD$sp() {
      return JetIsRing.zero$mcD$sp$(this);
   }

   public Jet zero$mcF$sp() {
      return JetIsRing.zero$mcF$sp$(this);
   }

   public Jet fromInt(final int n) {
      return JetIsRing.fromInt$(this, n);
   }

   public Jet fromInt$mcD$sp(final int n) {
      return JetIsRing.fromInt$mcD$sp$(this, n);
   }

   public Jet fromInt$mcF$sp(final int n) {
      return JetIsRing.fromInt$mcF$sp$(this, n);
   }

   public int fromInt$mcI$sp(final int n) {
      return Ring.fromInt$mcI$sp$(this, n);
   }

   public long fromInt$mcJ$sp(final int n) {
      return Ring.fromInt$mcJ$sp$(this, n);
   }

   public Object fromBigInt(final BigInt n) {
      return Ring.fromBigInt$(this, n);
   }

   public double fromBigInt$mcD$sp(final BigInt n) {
      return Ring.fromBigInt$mcD$sp$(this, n);
   }

   public float fromBigInt$mcF$sp(final BigInt n) {
      return Ring.fromBigInt$mcF$sp$(this, n);
   }

   public int fromBigInt$mcI$sp(final BigInt n) {
      return Ring.fromBigInt$mcI$sp$(this, n);
   }

   public long fromBigInt$mcJ$sp(final BigInt n) {
      return Ring.fromBigInt$mcJ$sp$(this, n);
   }

   public CommutativeGroup additive() {
      return AdditiveCommutativeGroup.additive$(this);
   }

   public CommutativeGroup additive$mcD$sp() {
      return AdditiveCommutativeGroup.additive$mcD$sp$(this);
   }

   public CommutativeGroup additive$mcF$sp() {
      return AdditiveCommutativeGroup.additive$mcF$sp$(this);
   }

   public CommutativeGroup additive$mcI$sp() {
      return AdditiveCommutativeGroup.additive$mcI$sp$(this);
   }

   public CommutativeGroup additive$mcJ$sp() {
      return AdditiveCommutativeGroup.additive$mcJ$sp$(this);
   }

   public double negate$mcD$sp(final double x) {
      return AdditiveGroup.negate$mcD$sp$(this, x);
   }

   public float negate$mcF$sp(final float x) {
      return AdditiveGroup.negate$mcF$sp$(this, x);
   }

   public int negate$mcI$sp(final int x) {
      return AdditiveGroup.negate$mcI$sp$(this, x);
   }

   public long negate$mcJ$sp(final long x) {
      return AdditiveGroup.negate$mcJ$sp$(this, x);
   }

   public double minus$mcD$sp(final double x, final double y) {
      return AdditiveGroup.minus$mcD$sp$(this, x, y);
   }

   public float minus$mcF$sp(final float x, final float y) {
      return AdditiveGroup.minus$mcF$sp$(this, x, y);
   }

   public int minus$mcI$sp(final int x, final int y) {
      return AdditiveGroup.minus$mcI$sp$(this, x, y);
   }

   public long minus$mcJ$sp(final long x, final long y) {
      return AdditiveGroup.minus$mcJ$sp$(this, x, y);
   }

   public Object sumN(final Object a, final int n) {
      return AdditiveGroup.sumN$(this, a, n);
   }

   public double sumN$mcD$sp(final double a, final int n) {
      return AdditiveGroup.sumN$mcD$sp$(this, a, n);
   }

   public float sumN$mcF$sp(final float a, final int n) {
      return AdditiveGroup.sumN$mcF$sp$(this, a, n);
   }

   public int sumN$mcI$sp(final int a, final int n) {
      return AdditiveGroup.sumN$mcI$sp$(this, a, n);
   }

   public long sumN$mcJ$sp(final long a, final int n) {
      return AdditiveGroup.sumN$mcJ$sp$(this, a, n);
   }

   public int one$mcI$sp() {
      return MultiplicativeMonoid.one$mcI$sp$(this);
   }

   public long one$mcJ$sp() {
      return MultiplicativeMonoid.one$mcJ$sp$(this);
   }

   public boolean isOne(final Object a, final Eq ev) {
      return MultiplicativeMonoid.isOne$(this, a, ev);
   }

   public boolean isOne$mcD$sp(final double a, final Eq ev) {
      return MultiplicativeMonoid.isOne$mcD$sp$(this, a, ev);
   }

   public boolean isOne$mcF$sp(final float a, final Eq ev) {
      return MultiplicativeMonoid.isOne$mcF$sp$(this, a, ev);
   }

   public boolean isOne$mcI$sp(final int a, final Eq ev) {
      return MultiplicativeMonoid.isOne$mcI$sp$(this, a, ev);
   }

   public boolean isOne$mcJ$sp(final long a, final Eq ev) {
      return MultiplicativeMonoid.isOne$mcJ$sp$(this, a, ev);
   }

   public Object product(final IterableOnce as) {
      return MultiplicativeMonoid.product$(this, as);
   }

   public double product$mcD$sp(final IterableOnce as) {
      return MultiplicativeMonoid.product$mcD$sp$(this, as);
   }

   public float product$mcF$sp(final IterableOnce as) {
      return MultiplicativeMonoid.product$mcF$sp$(this, as);
   }

   public int product$mcI$sp(final IterableOnce as) {
      return MultiplicativeMonoid.product$mcI$sp$(this, as);
   }

   public long product$mcJ$sp(final IterableOnce as) {
      return MultiplicativeMonoid.product$mcJ$sp$(this, as);
   }

   public Option tryProduct(final IterableOnce as) {
      return MultiplicativeMonoid.tryProduct$(this, as);
   }

   public double times$mcD$sp(final double x, final double y) {
      return MultiplicativeSemigroup.times$mcD$sp$(this, x, y);
   }

   public float times$mcF$sp(final float x, final float y) {
      return MultiplicativeSemigroup.times$mcF$sp$(this, x, y);
   }

   public int times$mcI$sp(final int x, final int y) {
      return MultiplicativeSemigroup.times$mcI$sp$(this, x, y);
   }

   public long times$mcJ$sp(final long x, final long y) {
      return MultiplicativeSemigroup.times$mcJ$sp$(this, x, y);
   }

   public Object positivePow(final Object a, final int n) {
      return MultiplicativeSemigroup.positivePow$(this, a, n);
   }

   public double positivePow$mcD$sp(final double a, final int n) {
      return MultiplicativeSemigroup.positivePow$mcD$sp$(this, a, n);
   }

   public float positivePow$mcF$sp(final float a, final int n) {
      return MultiplicativeSemigroup.positivePow$mcF$sp$(this, a, n);
   }

   public int positivePow$mcI$sp(final int a, final int n) {
      return MultiplicativeSemigroup.positivePow$mcI$sp$(this, a, n);
   }

   public long positivePow$mcJ$sp(final long a, final int n) {
      return MultiplicativeSemigroup.positivePow$mcJ$sp$(this, a, n);
   }

   public int zero$mcI$sp() {
      return AdditiveMonoid.zero$mcI$sp$(this);
   }

   public long zero$mcJ$sp() {
      return AdditiveMonoid.zero$mcJ$sp$(this);
   }

   public boolean isZero(final Object a, final Eq ev) {
      return AdditiveMonoid.isZero$(this, a, ev);
   }

   public boolean isZero$mcD$sp(final double a, final Eq ev) {
      return AdditiveMonoid.isZero$mcD$sp$(this, a, ev);
   }

   public boolean isZero$mcF$sp(final float a, final Eq ev) {
      return AdditiveMonoid.isZero$mcF$sp$(this, a, ev);
   }

   public boolean isZero$mcI$sp(final int a, final Eq ev) {
      return AdditiveMonoid.isZero$mcI$sp$(this, a, ev);
   }

   public boolean isZero$mcJ$sp(final long a, final Eq ev) {
      return AdditiveMonoid.isZero$mcJ$sp$(this, a, ev);
   }

   public Object sum(final IterableOnce as) {
      return AdditiveMonoid.sum$(this, as);
   }

   public double sum$mcD$sp(final IterableOnce as) {
      return AdditiveMonoid.sum$mcD$sp$(this, as);
   }

   public float sum$mcF$sp(final IterableOnce as) {
      return AdditiveMonoid.sum$mcF$sp$(this, as);
   }

   public int sum$mcI$sp(final IterableOnce as) {
      return AdditiveMonoid.sum$mcI$sp$(this, as);
   }

   public long sum$mcJ$sp(final IterableOnce as) {
      return AdditiveMonoid.sum$mcJ$sp$(this, as);
   }

   public Option trySum(final IterableOnce as) {
      return AdditiveMonoid.trySum$(this, as);
   }

   public double plus$mcD$sp(final double x, final double y) {
      return AdditiveSemigroup.plus$mcD$sp$(this, x, y);
   }

   public float plus$mcF$sp(final float x, final float y) {
      return AdditiveSemigroup.plus$mcF$sp$(this, x, y);
   }

   public int plus$mcI$sp(final int x, final int y) {
      return AdditiveSemigroup.plus$mcI$sp$(this, x, y);
   }

   public long plus$mcJ$sp(final long x, final long y) {
      return AdditiveSemigroup.plus$mcJ$sp$(this, x, y);
   }

   public Object positiveSumN(final Object a, final int n) {
      return AdditiveSemigroup.positiveSumN$(this, a, n);
   }

   public double positiveSumN$mcD$sp(final double a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcD$sp$(this, a, n);
   }

   public float positiveSumN$mcF$sp(final float a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcF$sp$(this, a, n);
   }

   public int positiveSumN$mcI$sp(final int a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcI$sp$(this, a, n);
   }

   public long positiveSumN$mcJ$sp(final long a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcJ$sp$(this, a, n);
   }

   public ClassTag c() {
      return this.c;
   }

   public JetDim d() {
      return this.d;
   }

   public Eq eq() {
      return this.eq;
   }

   public Field f() {
      return this.f;
   }

   public NRoot n() {
      return this.n;
   }

   public Order o() {
      return this.o;
   }

   public Trig t() {
      return this.t;
   }

   public Signed s() {
      return this.s;
   }

   public VectorSpace v() {
      return this.v;
   }

   public Field scalar() {
      return this.f();
   }

   public NRoot nroot() {
      return this.n();
   }

   public Jet timesl(final Object a, final Jet w) {
      return Jet$.MODULE$.apply(a, this.c(), this.d(), this.f()).$times(w, this.f(), this.v());
   }

   public Object dot(final Jet x, final Jet y) {
      return .MODULE$.foldLeft$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.zip$extension(scala.Predef..MODULE$.genericArrayOps(x.infinitesimal()), scala.Predef..MODULE$.genericWrapArray(y.infinitesimal()))), this.scalar().times(x.real(), y.real()), (xx, yy) -> this.scalar().plus(xx, this.scalar().times(yy._1(), yy._2())));
   }

   public Eq eq$mcD$sp() {
      return this.eq();
   }

   public Eq eq$mcF$sp() {
      return this.eq();
   }

   public Field f$mcD$sp() {
      return this.f();
   }

   public Field f$mcF$sp() {
      return this.f();
   }

   public NRoot n$mcD$sp() {
      return this.n();
   }

   public NRoot n$mcF$sp() {
      return this.n();
   }

   public Order o$mcD$sp() {
      return this.o();
   }

   public Order o$mcF$sp() {
      return this.o();
   }

   public Trig t$mcD$sp() {
      return this.t();
   }

   public Trig t$mcF$sp() {
      return this.t();
   }

   public Signed s$mcD$sp() {
      return this.s();
   }

   public Signed s$mcF$sp() {
      return this.s();
   }

   public VectorSpace v$mcD$sp() {
      return this.v();
   }

   public VectorSpace v$mcF$sp() {
      return this.v();
   }

   public Field scalar$mcD$sp() {
      return this.scalar();
   }

   public Field scalar$mcF$sp() {
      return this.scalar();
   }

   public NRoot nroot$mcD$sp() {
      return this.nroot();
   }

   public NRoot nroot$mcF$sp() {
      return this.nroot();
   }

   public Jet timesl$mcD$sp(final double a, final Jet w) {
      return this.timesl(BoxesRunTime.boxToDouble(a), (Jet)w);
   }

   public Jet timesl$mcF$sp(final float a, final Jet w) {
      return this.timesl(BoxesRunTime.boxToFloat(a), (Jet)w);
   }

   public double dot$mcD$sp(final Jet x, final Jet y) {
      return BoxesRunTime.unboxToDouble(this.dot(x, y));
   }

   public float dot$mcF$sp(final Jet x, final Jet y) {
      return BoxesRunTime.unboxToFloat(this.dot(x, y));
   }

   public boolean specInstance$() {
      return false;
   }

   public JetAlgebra(final ClassTag c, final JetDim d, final Eq eq, final Field f, final NRoot n, final Order o, final Trig t, final Signed s, final VectorSpace v) {
      this.c = c;
      this.d = d;
      this.eq = eq;
      this.f = f;
      this.n = n;
      this.o = o;
      this.t = t;
      this.s = s;
      this.v = v;
      AdditiveSemigroup.$init$(this);
      AdditiveMonoid.$init$(this);
      AdditiveCommutativeSemigroup.$init$(this);
      AdditiveCommutativeMonoid.$init$(this);
      MultiplicativeSemigroup.$init$(this);
      MultiplicativeMonoid.$init$(this);
      AdditiveGroup.$init$(this);
      AdditiveCommutativeGroup.$init$(this);
      Ring.$init$(this);
      JetIsRing.$init$(this);
      MultiplicativeCommutativeSemigroup.$init$(this);
      MultiplicativeCommutativeMonoid.$init$(this);
      EuclideanRing.$init$(this);
      MultiplicativeGroup.$init$(this);
      DivisionRing.$init$(this);
      MultiplicativeCommutativeGroup.$init$(this);
      Field.$init$(this);
      JetIsField.$init$(this);
      JetIsTrig.$init$(this);
      NRoot.$init$(this);
      JetIsNRoot.$init$(this);
      CModule.$init$(this);
      VectorSpace.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
