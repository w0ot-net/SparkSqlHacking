package breeze.linalg;

import breeze.generic.UFunc;
import breeze.math.Ring;
import scala.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015<Qa\u0005\u000b\t\u0002e1Qa\u0007\u000b\t\u0002qAQ\u0001L\u0001\u0005\u00025BqAL\u0001C\u0002\u0013\rq\u0006\u0003\u0004;\u0003\u0001\u0006I\u0001\r\u0005\bw\u0005\u0011\r\u0011b\u0001=\u0011\u0019\u0011\u0015\u0001)A\u0005{!91)\u0001b\u0001\n\u0007!\u0005B\u0002&\u0002A\u0003%Q\tC\u0004L\u0003\t\u0007I1\u0001'\t\rI\u000b\u0001\u0015!\u0003N\u0011\u001d\u0019\u0016A1A\u0005\u0004QCa\u0001W\u0001!\u0002\u0013)\u0006bB-\u0002\u0005\u0004%\u0019A\u0017\u0005\u00079\u0006\u0001\u000b\u0011B.\t\u000fu\u000b!\u0019!C\u0002=\"1\u0001-\u0001Q\u0001\n}Cq!Y\u0001C\u0002\u0013\r!\r\u0003\u0004e\u0003\u0001\u0006IaY\u0001\u0005I&4gM\u0003\u0002\u0016-\u00051A.\u001b8bY\u001eT\u0011aF\u0001\u0007EJ,WM_3\u0004\u0001A\u0011!$A\u0007\u0002)\t!A-\u001b4g'\u0011\tQdI\u0015\u0011\u0005y\tS\"A\u0010\u000b\u0003\u0001\nQa]2bY\u0006L!AI\u0010\u0003\r\u0005s\u0017PU3g!\t!s%D\u0001&\u0015\t1c#A\u0004hK:,'/[2\n\u0005!*#!B+Gk:\u001c\u0007C\u0001\u000e+\u0013\tYCCA\u0006eS\u001a4Gj\\<Qe&|\u0017A\u0002\u001fj]&$h\bF\u0001\u001a\u0003QIW\u000e\u001d7E-~#u.\u001e2mK~#ekX%oiV\t\u0001\u0007E\u00032eQ:D'D\u0001\u0002\u0013\t\u0019tEA\u0003J[Bd'\u0007E\u0002\u001bk]J!A\u000e\u000b\u0003\u0017\u0011+gn]3WK\u000e$xN\u001d\t\u0003=aJ!!O\u0010\u0003\u0007%sG/A\u000bj[BdGIV0E_V\u0014G.Z0E-~Ke\u000e\u001e\u0011\u0002+%l\u0007\u000f\u001c#W?\u0012{WO\u00197f?\u00123v\fT8oOV\tQ\bE\u00032ey:d\bE\u0002\u001bk}\u0002\"A\b!\n\u0005\u0005{\"\u0001\u0002'p]\u001e\fa#[7qY\u00123v\fR8vE2,w\f\u0012,`\u0019>tw\rI\u0001\u0017S6\u0004H\u000e\u0012,`\t>,(\r\\3`\tZ{f\t\\8biV\tQ\tE\u00032e\u0019;d\tE\u0002\u001bk\u001d\u0003\"A\b%\n\u0005%{\"!\u0002$m_\u0006$\u0018aF5na2$ek\u0018#pk\ndWm\u0018#W?\u001acw.\u0019;!\u0003]IW\u000e\u001d7E-~#u.\u001e2mK~#ek\u0018#pk\ndW-F\u0001N!\u0015\t$GT\u001cO!\rQRg\u0014\t\u0003=AK!!U\u0010\u0003\r\u0011{WO\u00197f\u0003aIW\u000e\u001d7E-~#u.\u001e2mK~#ek\u0018#pk\ndW\rI\u0001\u000eS6\u0004H\u000e\u0012,`\tZ{\u0016J\u001c;\u0016\u0003U\u0003B!\r,5i%\u0011qk\n\u0002\u0005\u00136\u0004H.\u0001\bj[BdGIV0E-~Ke\u000e\u001e\u0011\u0002\u001d%l\u0007\u000f\u001c#W?\u00123v\fT8oOV\t1\f\u0005\u00032-zr\u0014aD5na2$ek\u0018#W?2{gn\u001a\u0011\u0002\u001f%l\u0007\u000f\u001c#W?\u00123vL\u00127pCR,\u0012a\u0018\t\u0005cY3e)\u0001\tj[BdGIV0E-~3En\\1uA\u0005\u0001\u0012.\u001c9m\tZ{FIV0E_V\u0014G.Z\u000b\u0002GB!\u0011G\u0016(O\u0003EIW\u000e\u001d7E-~#ek\u0018#pk\ndW\r\t"
)
public final class diff {
   public static UFunc.UImpl implDV_DV_Double() {
      return diff$.MODULE$.implDV_DV_Double();
   }

   public static UFunc.UImpl implDV_DV_Float() {
      return diff$.MODULE$.implDV_DV_Float();
   }

   public static UFunc.UImpl implDV_DV_Long() {
      return diff$.MODULE$.implDV_DV_Long();
   }

   public static UFunc.UImpl implDV_DV_Int() {
      return diff$.MODULE$.implDV_DV_Int();
   }

   public static UFunc.UImpl2 implDV_Double_DV_Double() {
      return diff$.MODULE$.implDV_Double_DV_Double();
   }

   public static UFunc.UImpl2 implDV_Double_DV_Float() {
      return diff$.MODULE$.implDV_Double_DV_Float();
   }

   public static UFunc.UImpl2 implDV_Double_DV_Long() {
      return diff$.MODULE$.implDV_Double_DV_Long();
   }

   public static UFunc.UImpl2 implDV_Double_DV_Int() {
      return diff$.MODULE$.implDV_Double_DV_Int();
   }

   public static UFunc.UImpl implVec(final .less.colon.less vec, final ClassTag ct, final Ring ring) {
      return diff$.MODULE$.implVec(vec, ct, ring);
   }

   public static Object withSink(final Object s) {
      return diff$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return diff$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return diff$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return diff$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return diff$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return diff$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return diff$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return diff$.MODULE$.apply(v, impl);
   }
}
