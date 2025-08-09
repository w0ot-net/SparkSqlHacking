package breeze.linalg;

import breeze.generic.UFunc;
import breeze.gymnastics.NotGiven;
import breeze.linalg.operators.CastOps;
import breeze.linalg.operators.GenericOps;
import breeze.linalg.operators.GenericOpsLowPrio;
import breeze.linalg.operators.GenericOpsLowPrio3;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanZipMapValues;
import breeze.linalg.support.ScalarOf;
import breeze.math.Ring;
import breeze.math.Semiring;
import scala.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I4Aa\u0003\u0007\u0005#!)a\u0004\u0001C\u0001?\u001d)!\u0005\u0004E\u0001G\u0019)1\u0002\u0004E\u0001I!)ad\u0001C\u0001W!9Af\u0001b\u0001\n\u0003i\u0003BB\u0019\u0004A\u0003%a\u0006C\u00033\u0007\u0011\r1\u0007C\u0003P\u0007\u0011\r\u0001\u000bC\u0003S\u0007\u0011\r1\u000bC\u0003Y\u0007\u0011\r\u0011LA\u0004jg\u000ecwn]3\u000b\u00055q\u0011A\u00027j]\u0006dwMC\u0001\u0010\u0003\u0019\u0011'/Z3{K\u000e\u00011c\u0001\u0001\u00131A\u00111CF\u0007\u0002))\tQ#A\u0003tG\u0006d\u0017-\u0003\u0002\u0018)\t1\u0011I\\=SK\u001a\u0004\"!\u0007\u000f\u000e\u0003iQ!a\u0007\u0007\u0002\u0013=\u0004XM]1u_J\u001c\u0018BA\u000f\u001b\u0005)9UM\\3sS\u000e|\u0005o]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u0001\u0002\"!\t\u0001\u000e\u00031\tq![:DY>\u001cX\r\u0005\u0002\"\u0007M\u00191AE\u0013\u0011\u0005\u0019JS\"A\u0014\u000b\u0005!r\u0011aB4f]\u0016\u0014\u0018nY\u0005\u0003U\u001d\u0012Q!\u0016$v]\u000e$\u0012aI\u0001\u0012\t\u00163\u0015)\u0016'U?R{E*\u0012*B\u001d\u000e+U#\u0001\u0018\u0011\u0005My\u0013B\u0001\u0019\u0015\u0005\u0019!u.\u001e2mK\u0006\u0011B)\u0012$B+2#v\fV(M\u000bJ\u000bejQ#!\u00039!WMZ1vYR$v\u000e\\%na2,2\u0001N\u001eF)\t)$\nE\u00037oe\"u)D\u0001\u0004\u0013\tA\u0014FA\u0003J[Bd'\u0007\u0005\u0002;w1\u0001A!\u0002\u001f\b\u0005\u0004i$!A!\u0012\u0005y\n\u0005CA\n@\u0013\t\u0001ECA\u0004O_RD\u0017N\\4\u0011\u0005M\u0011\u0015BA\"\u0015\u0005\r\te.\u001f\t\u0003u\u0015#QAR\u0004C\u0002u\u0012\u0011A\u0011\t\u0003'!K!!\u0013\u000b\u0003\u000f\t{w\u000e\\3b]\")1j\u0002a\u0002\u0019\u0006)\u0011.\u001c9mgA1a'T\u001dE]\u001dK!AT\u0015\u0003\u000b%k\u0007\u000f\\\u001a\u0002\u0017%l\u0007\u000f\\0E_V\u0014G.Z\u000b\u0002#B1a'\u0014\u0018/]\u001d\u000b!\"[7qY~3En\\1u+\u0005!\u0006C\u0002\u001cN+Vss\t\u0005\u0002\u0014-&\u0011q\u000b\u0006\u0002\u0006\r2|\u0017\r^\u0001\u000eMJ|WNW5q-\u0006dW/Z:\u0016\u000bikvL[7\u0015\u0007m\u0003w\u000e\u0005\u00047\u001brsff\u0012\t\u0003uu#Q\u0001\u0010\u0006C\u0002u\u0002\"AO0\u0005\u000b\u0019S!\u0019A\u001f\t\u000b\u0005T\u00019\u00012\u0002\u0007\rTh\u000fE\u0003doqsfM\u0004\u0002\"I&\u0011Q\rD\u0001\nu&\u0004h+\u00197vKN\u0004B!I4jY&\u0011\u0001\u000e\u0004\u0002\r5&\u0004\b/\u001a3WC2,Xm\u001d\t\u0003u)$Qa\u001b\u0006C\u0002u\u0012!AV\u0019\u0011\u0005ijG!\u00028\u000b\u0005\u0004i$A\u0001,3\u0011\u0015\u0001(\u0002q\u0001r\u0003\u0011\u0011\u0017m]3\u0011\rYj\u0015\u000e\u001c\u0018H\u0001"
)
public class isClose implements GenericOps {
   public static UFunc.UImpl3 fromZipValues(final UFunc.UImpl2 czv, final UFunc.UImpl3 base) {
      return isClose$.MODULE$.fromZipValues(czv, base);
   }

   public static UFunc.UImpl3 impl_Float() {
      return isClose$.MODULE$.impl_Float();
   }

   public static UFunc.UImpl3 impl_Double() {
      return isClose$.MODULE$.impl_Double();
   }

   public static UFunc.UImpl2 defaultTolImpl(final UFunc.UImpl3 impl3) {
      return isClose$.MODULE$.defaultTolImpl(impl3);
   }

   public static double DEFAULT_TOLERANCE() {
      return isClose$.MODULE$.DEFAULT_TOLERANCE();
   }

   public static Object withSink(final Object s) {
      return isClose$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return isClose$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return isClose$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return isClose$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return isClose$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return isClose$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return isClose$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return isClose$.MODULE$.apply(v, impl);
   }

   public UFunc.InPlaceImpl2 impl_OpAdd_InPlace_T_U_Generic_from_scaleAdd_InPlace(final UFunc.InPlaceImpl3 sa, final Semiring semi) {
      return GenericOps.impl_OpAdd_InPlace_T_U_Generic_from_scaleAdd_InPlace$(this, sa, semi);
   }

   public UFunc.InPlaceImpl2 impl_OpSub_InPlace_T_U_Generic_from_scaleAdd_InPlace(final UFunc.InPlaceImpl3 sa, final Ring ring) {
      return GenericOps.impl_OpSub_InPlace_T_U_Generic_from_scaleAdd_InPlace$(this, sa, ring);
   }

   public UFunc.UImpl impl_OpNeg_T_Generic_from_OpMulScalar(final ScalarOf scalarOf, final Ring ring, final UFunc.UImpl2 scale) {
      return GenericOps.impl_OpNeg_T_Generic_from_OpMulScalar$(this, scalarOf, ring, scale);
   }

   public UFunc.UImpl2 pureFromUpdate(final UFunc.InPlaceImpl2 op, final CanCopy copy) {
      return GenericOpsLowPrio.pureFromUpdate$(this, op, copy);
   }

   public UFunc.UImpl2 castOps_V_V(final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.UImpl2 op) {
      return CastOps.castOps_V_V$(this, v1lt, v2lt, v1ne, op);
   }

   public UFunc.InPlaceImpl2 castUpdateOps_V_V(final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.InPlaceImpl2 op) {
      return CastOps.castUpdateOps_V_V$(this, v1lt, v2lt, v1ne, op);
   }

   public UFunc.UImpl2 castOps_V_S(final ScalarOf v2, final .less.colon.less v1lt, final NotGiven v1ne, final UFunc.UImpl2 op) {
      return CastOps.castOps_V_S$(this, v2, v1lt, v1ne, op);
   }

   public UFunc.InPlaceImpl2 castUpdateOps_V_S(final ScalarOf v2, final .less.colon.less v1lt, final NotGiven v1ne, final UFunc.InPlaceImpl2 op) {
      return CastOps.castUpdateOps_V_S$(this, v2, v1lt, v1ne, op);
   }

   public UFunc.UImpl2 castOps_M_M(final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.UImpl2 op) {
      return CastOps.castOps_M_M$(this, v1lt, v2lt, v1ne, op);
   }

   public UFunc.InPlaceImpl2 castUpdateOps_M_M(final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.InPlaceImpl2 op) {
      return CastOps.castUpdateOps_M_M$(this, v1lt, v2lt, v1ne, op);
   }

   public UFunc.UImpl2 castOps_M_V(final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.UImpl2 op) {
      return CastOps.castOps_M_V$(this, v1lt, v2lt, v1ne, op);
   }

   public UFunc.InPlaceImpl2 castUpdateOps_M_V(final .less.colon.less v1lt, final .less.colon.less v2lt, final NotGiven v1ne, final UFunc.InPlaceImpl2 op) {
      return CastOps.castUpdateOps_M_V$(this, v1lt, v2lt, v1ne, op);
   }

   public UFunc.UImpl2 impl_T_S_eq_U_from_ZipMap(final ScalarOf handhold, final UFunc.UImpl2 impl, final CanZipMapValues canZipMapValues) {
      return GenericOpsLowPrio3.impl_T_S_eq_U_from_ZipMap$(this, handhold, impl, canZipMapValues);
   }

   public isClose() {
      GenericOpsLowPrio3.$init$(this);
      CastOps.$init$(this);
      GenericOpsLowPrio.$init$(this);
      GenericOps.$init$(this);
   }
}
