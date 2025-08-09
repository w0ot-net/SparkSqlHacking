package spire.std;

import algebra.ring.AdditiveCommutativeGroup;
import algebra.ring.AdditiveGroup;
import algebra.ring.CommutativeRing;
import algebra.ring.CommutativeRng;
import cats.kernel.CommutativeGroup;
import java.lang.invoke.SerializedLambda;
import scala..less.colon.less.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import spire.algebra.CModule;
import spire.algebra.LeftModule;

@ScalaSignature(
   bytes = "\u0006\u0005\r4AAB\u0004\u0001\u0019!Aq\t\u0001BC\u0002\u0013\r\u0003\nC\u0005M\u0001\t\u0005\t\u0015!\u0003J\u001b\")a\n\u0001C\u0001\u001f\")1\u000b\u0001C\u0001)\")q\u000b\u0001C\u00011\n9Q*\u00199D%:<'B\u0001\u0005\n\u0003\r\u0019H\u000f\u001a\u0006\u0002\u0015\u0005)1\u000f]5sK\u000e\u0001QcA\u0007\u0015CM)\u0001AD\u0012<\u007fA!q\u0002\u0005\n!\u001b\u00059\u0011BA\t\b\u00051i\u0015\r]\"TK6L'/\u001b8h!\t\u0019B\u0003\u0004\u0001\u0005\u000bU\u0001!\u0019\u0001\f\u0003\u0003-\u000b\"aF\u000f\u0011\u0005aYR\"A\r\u000b\u0003i\tQa]2bY\u0006L!\u0001H\r\u0003\u000f9{G\u000f[5oOB\u0011\u0001DH\u0005\u0003?e\u00111!\u00118z!\t\u0019\u0012\u0005B\u0003#\u0001\t\u0007aCA\u0001W!\r!\u0003g\r\b\u0003K5r!AJ\u0016\u000f\u0005\u001dRS\"\u0001\u0015\u000b\u0005%Z\u0011A\u0002\u001fs_>$h(C\u0001\u000b\u0013\ta\u0013\"A\u0004bY\u001e,'M]1\n\u00059z\u0013a\u00029bG.\fw-\u001a\u0006\u0003Y%I!!\r\u001a\u0003\t\r\u0013fn\u001a\u0006\u0003]=\u0002B\u0001\u000e\u001d\u0013A9\u0011QG\u000e\t\u0003OeI!aN\r\u0002\rA\u0013X\rZ3g\u0013\tI$HA\u0002NCBT!aN\r\u0011\tqj4\u0007I\u0007\u0002_%\u0011ah\f\u0002\b\u00076{G-\u001e7f!\t\u0001EI\u0004\u0002B\u0007:\u0011qEQ\u0005\u00025%\u0011a&G\u0005\u0003\u000b\u001a\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!AL\r\u0002\rM\u001c\u0017\r\\1s+\u0005I\u0005c\u0001\u0013KA%\u00111J\r\u0002\u0006\u0007JKgnZ\u0001\bg\u000e\fG.\u0019:!\u0013\t9\u0005#\u0001\u0004=S:LGO\u0010\u000b\u0002!R\u0011\u0011K\u0015\t\u0005\u001f\u0001\u0011\u0002\u0005C\u0003H\u0007\u0001\u000f\u0011*\u0001\u0004oK\u001e\fG/\u001a\u000b\u0003gUCQA\u0016\u0003A\u0002M\n\u0011\u0001_\u0001\u0007i&lWm\u001d7\u0015\u0007MJ6\fC\u0003[\u000b\u0001\u0007\u0001%A\u0001s\u0011\u0015aV\u00011\u00014\u0003\u00051\b\u0006\u0002\u0001_C\n\u0004\"\u0001G0\n\u0005\u0001L\"\u0001E*fe&\fGNV3sg&|g.V%E\u0003\u00151\u0018\r\\;f=\u0005\u0001\u0001"
)
public class MapCRng extends MapCSemiring implements CommutativeRng, CModule {
   private static final long serialVersionUID = 0L;

   public CommutativeRing scalar$mcD$sp() {
      return CModule.scalar$mcD$sp$(this);
   }

   public CommutativeRing scalar$mcF$sp() {
      return CModule.scalar$mcF$sp$(this);
   }

   public CommutativeRing scalar$mcI$sp() {
      return CModule.scalar$mcI$sp$(this);
   }

   public CommutativeRing scalar$mcJ$sp() {
      return CModule.scalar$mcJ$sp$(this);
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

   public Object minus(final Object x, final Object y) {
      return AdditiveGroup.minus$(this, x, y);
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

   public CommutativeRing scalar() {
      return (CommutativeRing)super.scalar();
   }

   public Map negate(final Map x) {
      return x.view().mapValues((x$1) -> this.scalar().negate(x$1)).toMap(.MODULE$.refl());
   }

   public Map timesl(final Object r, final Map v) {
      return v.view().mapValues((x$2) -> this.scalar().times(r, x$2)).toMap(.MODULE$.refl());
   }

   public MapCRng(final CommutativeRing scalar) {
      super(scalar);
      AdditiveGroup.$init$(this);
      AdditiveCommutativeGroup.$init$(this);
      CModule.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
