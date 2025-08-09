package spire.algebra;

import algebra.ring.Field;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005=4\u0001\u0002D\u0007\u0011\u0002\u0007\u0005Q\"\u0005\u0005\u0006y\u0001!\t!\u0010\u0005\u0006\u0003\u00021\tA\u0011\u0005\u0006\r\u0002!\ta\u0012\u0005\u0006\u001f\u00021\t\u0001\u0015\u0005\u0006)\u0002!\t!\u0016\u0005\u0006-\u0002!\ta\u0016\u0005\u00069\u0002!\t!\u0018\u0005\u0006?\u0002!\t\u0005\u0019\u0005\u0006G\u0002!\t\u0001\u001a\u0005\u0006Q\u0002!\t%\u001b\u0005\u0006Y\u0002!\t!\u001c\u0002\u0018\u001d>\u0014X.\u001a3J]:,'\u000f\u0015:pIV\u001cGo\u00159bG\u0016T!AD\b\u0002\u000f\u0005dw-\u001a2sC*\t\u0001#A\u0003ta&\u0014X-F\u0002\u0013?\u001d\u001a2\u0001A\n\u001a!\t!r#D\u0001\u0016\u0015\u00051\u0012!B:dC2\f\u0017B\u0001\r\u0016\u0005\r\te.\u001f\t\u00055mib%D\u0001\u000e\u0013\taRBA\tO_JlW\r\u001a,fGR|'o\u00159bG\u0016\u0004\"AH\u0010\r\u0001\u0011)\u0001\u0005\u0001b\u0001E\t\tak\u0001\u0001\u0012\u0005\r\u001a\u0002C\u0001\u000b%\u0013\t)SCA\u0004O_RD\u0017N\\4\u0011\u0005y9C!\u0003\u0015\u0001A\u0003\u0005\tQ1\u0001#\u0005\u00051\u0005\u0006B\u0014+[]\u0002\"\u0001F\u0016\n\u00051*\"aC:qK\u000eL\u0017\r\\5{K\u0012\fTa\t\u00180cAr!\u0001F\u0018\n\u0005A*\u0012!\u0002$m_\u0006$\u0018\u0007\u0002\u00133mYq!a\r\u001c\u000e\u0003QR!!N\u0011\u0002\rq\u0012xn\u001c;?\u0013\u00051\u0012'B\u00129smRdB\u0001\u000b:\u0013\tQT#\u0001\u0004E_V\u0014G.Z\u0019\u0005II2d#\u0001\u0004%S:LG\u000f\n\u000b\u0002}A\u0011AcP\u0005\u0003\u0001V\u0011A!\u00168ji\u0006)1\u000f]1dKV\t1\t\u0005\u0003\u001b\tv1\u0013BA#\u000e\u0005EIeN\\3s!J|G-^2u'B\f7-Z\u0001\u0007g\u000e\fG.\u0019:\u0016\u0003!\u00032!\u0013''\u001d\tQ\"*\u0003\u0002L\u001b\u00059\u0001/Y2lC\u001e,\u0017BA'O\u0005\u00151\u0015.\u001a7e\u0015\tYU\"A\u0003oe>|G/F\u0001R!\rQ\"KJ\u0005\u0003'6\u0011QA\u0014*p_R\fAA_3s_V\tQ$\u0001\u0003qYV\u001cHcA\u000fY5\")\u0011L\u0002a\u0001;\u0005\ta\u000fC\u0003\\\r\u0001\u0007Q$A\u0001x\u0003\u0019qWmZ1uKR\u0011QD\u0018\u0005\u00063\u001e\u0001\r!H\u0001\u0006[&tWo\u001d\u000b\u0004;\u0005\u0014\u0007\"B-\t\u0001\u0004i\u0002\"B.\t\u0001\u0004i\u0012A\u0002;j[\u0016\u001cH\u000eF\u0002\u001eK\u001eDQAZ\u0005A\u0002\u0019\n\u0011A\u001a\u0005\u00063&\u0001\r!H\u0001\u0005I&4(\u000fF\u0002\u001eU.DQ!\u0017\u0006A\u0002uAQA\u001a\u0006A\u0002\u0019\nAA\\8s[R\u0011aE\u001c\u0005\u00063.\u0001\r!\b"
)
public interface NormedInnerProductSpace extends NormedVectorSpace {
   InnerProductSpace space();

   // $FF: synthetic method
   static Field scalar$(final NormedInnerProductSpace $this) {
      return $this.scalar();
   }

   default Field scalar() {
      return this.space().scalar();
   }

   NRoot nroot();

   // $FF: synthetic method
   static Object zero$(final NormedInnerProductSpace $this) {
      return $this.zero();
   }

   default Object zero() {
      return this.space().zero();
   }

   // $FF: synthetic method
   static Object plus$(final NormedInnerProductSpace $this, final Object v, final Object w) {
      return $this.plus(v, w);
   }

   default Object plus(final Object v, final Object w) {
      return this.space().plus(v, w);
   }

   // $FF: synthetic method
   static Object negate$(final NormedInnerProductSpace $this, final Object v) {
      return $this.negate(v);
   }

   default Object negate(final Object v) {
      return this.space().negate(v);
   }

   // $FF: synthetic method
   static Object minus$(final NormedInnerProductSpace $this, final Object v, final Object w) {
      return $this.minus(v, w);
   }

   default Object minus(final Object v, final Object w) {
      return this.space().minus(v, w);
   }

   // $FF: synthetic method
   static Object timesl$(final NormedInnerProductSpace $this, final Object f, final Object v) {
      return $this.timesl(f, v);
   }

   default Object timesl(final Object f, final Object v) {
      return this.space().timesl(f, v);
   }

   // $FF: synthetic method
   static Object divr$(final NormedInnerProductSpace $this, final Object v, final Object f) {
      return $this.divr(v, f);
   }

   default Object divr(final Object v, final Object f) {
      return this.space().divr(v, f);
   }

   // $FF: synthetic method
   static Object norm$(final NormedInnerProductSpace $this, final Object v) {
      return $this.norm(v);
   }

   default Object norm(final Object v) {
      return this.nroot().sqrt(this.space().dot(v, v));
   }

   // $FF: synthetic method
   static InnerProductSpace space$mcD$sp$(final NormedInnerProductSpace $this) {
      return $this.space$mcD$sp();
   }

   default InnerProductSpace space$mcD$sp() {
      return this.space();
   }

   // $FF: synthetic method
   static InnerProductSpace space$mcF$sp$(final NormedInnerProductSpace $this) {
      return $this.space$mcF$sp();
   }

   default InnerProductSpace space$mcF$sp() {
      return this.space();
   }

   // $FF: synthetic method
   static Field scalar$mcD$sp$(final NormedInnerProductSpace $this) {
      return $this.scalar$mcD$sp();
   }

   default Field scalar$mcD$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static Field scalar$mcF$sp$(final NormedInnerProductSpace $this) {
      return $this.scalar$mcF$sp();
   }

   default Field scalar$mcF$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static NRoot nroot$mcD$sp$(final NormedInnerProductSpace $this) {
      return $this.nroot$mcD$sp();
   }

   default NRoot nroot$mcD$sp() {
      return this.nroot();
   }

   // $FF: synthetic method
   static NRoot nroot$mcF$sp$(final NormedInnerProductSpace $this) {
      return $this.nroot$mcF$sp();
   }

   default NRoot nroot$mcF$sp() {
      return this.nroot();
   }

   // $FF: synthetic method
   static Object timesl$mcD$sp$(final NormedInnerProductSpace $this, final double f, final Object v) {
      return $this.timesl$mcD$sp(f, v);
   }

   default Object timesl$mcD$sp(final double f, final Object v) {
      return this.timesl(BoxesRunTime.boxToDouble(f), v);
   }

   // $FF: synthetic method
   static Object timesl$mcF$sp$(final NormedInnerProductSpace $this, final float f, final Object v) {
      return $this.timesl$mcF$sp(f, v);
   }

   default Object timesl$mcF$sp(final float f, final Object v) {
      return this.timesl(BoxesRunTime.boxToFloat(f), v);
   }

   // $FF: synthetic method
   static Object divr$mcD$sp$(final NormedInnerProductSpace $this, final Object v, final double f) {
      return $this.divr$mcD$sp(v, f);
   }

   default Object divr$mcD$sp(final Object v, final double f) {
      return this.divr(v, BoxesRunTime.boxToDouble(f));
   }

   // $FF: synthetic method
   static Object divr$mcF$sp$(final NormedInnerProductSpace $this, final Object v, final float f) {
      return $this.divr$mcF$sp(v, f);
   }

   default Object divr$mcF$sp(final Object v, final float f) {
      return this.divr(v, BoxesRunTime.boxToFloat(f));
   }

   // $FF: synthetic method
   static double norm$mcD$sp$(final NormedInnerProductSpace $this, final Object v) {
      return $this.norm$mcD$sp(v);
   }

   default double norm$mcD$sp(final Object v) {
      return BoxesRunTime.unboxToDouble(this.norm(v));
   }

   // $FF: synthetic method
   static float norm$mcF$sp$(final NormedInnerProductSpace $this, final Object v) {
      return $this.norm$mcF$sp(v);
   }

   default float norm$mcF$sp(final Object v) {
      return BoxesRunTime.unboxToFloat(this.norm(v));
   }

   static void $init$(final NormedInnerProductSpace $this) {
   }
}
