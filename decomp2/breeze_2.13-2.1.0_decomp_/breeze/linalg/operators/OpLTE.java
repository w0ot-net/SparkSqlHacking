package breeze.linalg.operators;

import breeze.generic.UFunc;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015r!\u0002\u000b\u0016\u0011\u0003ab!\u0002\u0010\u0016\u0011\u0003y\u0002\"B\u0018\u0002\t\u0003\u0001\u0004\"B\u0019\u0002\t\u0007\u0011\u0004\"\u0002,\u0002\t\u00079\u0006\"\u0002/\u0002\t\u0007i\u0006\"\u00022\u0002\t\u0007\u0019\u0007\"\u00025\u0002\t\u0007I\u0007\"\u00028\u0002\t\u0007y\u0007\"B9\u0002\t\u0007\u0011\b\"\u0002;\u0002\t\u0007)\b\"B<\u0002\t\u0007A\b\"\u0002>\u0002\t\u0007Y\b\"B?\u0002\t\u0007q\bbBA\u0001\u0003\u0011\r\u00111\u0001\u0005\b\u0003\u000f\tA1AA\u0005\u0011\u001d\ti!\u0001C\u0002\u0003\u001fAq!a\u0005\u0002\t\u0007\t)\u0002C\u0004\u0002\u001a\u0005!\u0019!a\u0007\t\u000f\u0005}\u0011\u0001b\u0001\u0002\"\u0005)q\n\u001d'U\u000b*\u0011acF\u0001\n_B,'/\u0019;peNT!\u0001G\r\u0002\r1Lg.\u00197h\u0015\u0005Q\u0012A\u00022sK\u0016TXm\u0001\u0001\u0011\u0005u\tQ\"A\u000b\u0003\u000b=\u0003H\nV#\u0014\t\u0005\u0001c%\u000b\t\u0003C\u0011j\u0011A\t\u0006\u0002G\u0005)1oY1mC&\u0011QE\t\u0002\u0007\u0003:L(+\u001a4\u0011\u0005u9\u0013B\u0001\u0015\u0016\u0005\u0019y\u0005\u000fV=qKB\u0011!&L\u0007\u0002W)\u0011A&G\u0001\bO\u0016tWM]5d\u0013\tq3F\u0001\tFY\u0016lWM\u001c;xSN,WKR;oG\u00061A(\u001b8jiz\"\u0012\u0001H\u0001\u0012S6\u0004HN\r$s_6|%\u000fZ3sS:<WCA\u001a=)\t!\u0004\nE\u00036miRT)D\u0001\u0002\u0013\t9\u0004HA\u0003J[Bd''\u0003\u0002:W\t)QKR;oGB\u00111\b\u0010\u0007\u0001\t\u0015i4A1\u0001?\u0005\u0005!\u0016CA C!\t\t\u0003)\u0003\u0002BE\t9aj\u001c;iS:<\u0007CA\u0011D\u0013\t!%EA\u0002B]f\u0004\"!\t$\n\u0005\u001d\u0013#a\u0002\"p_2,\u0017M\u001c\u0005\b\u0013\u000e\t\t\u0011q\u0001K\u0003))g/\u001b3f]\u000e,GE\u000e\t\u0004\u0017NSdB\u0001'R\u001d\ti\u0005+D\u0001O\u0015\ty5$\u0001\u0004=e>|GOP\u0005\u0002G%\u0011!KI\u0001\ba\u0006\u001c7.Y4f\u0013\t!VK\u0001\u0005Pe\u0012,'/\u001b8h\u0015\t\u0011&%A\u0007j[Bd'gX%oi~Ke\u000e^\u000b\u00021B)QGN-Z\u000bB\u0011\u0011EW\u0005\u00037\n\u00121!\u00138u\u0003=IW\u000e\u001d73?\u001acw.\u0019;`\u0013:$X#\u00010\u0011\u000bU2t,W#\u0011\u0005\u0005\u0002\u0017BA1#\u0005\u00151En\\1u\u00039IW\u000e\u001d73?2{gnZ0J]R,\u0012\u0001\u001a\t\u0006kY*\u0017,\u0012\t\u0003C\u0019L!a\u001a\u0012\u0003\t1{gnZ\u0001\u0011S6\u0004HNM0E_V\u0014G.Z0J]R,\u0012A\u001b\t\u0006kYZ\u0017,\u0012\t\u0003C1L!!\u001c\u0012\u0003\r\u0011{WO\u00197f\u0003=IW\u000e\u001d73?&sGo\u0018$m_\u0006$X#\u00019\u0011\u000bU2\u0014lX#\u0002#%l\u0007\u000f\u001c\u001a`\r2|\u0017\r^0GY>\fG/F\u0001t!\u0015)dgX0F\u0003AIW\u000e\u001d73?2{gnZ0GY>\fG/F\u0001w!\u0015)d'Z0F\u0003IIW\u000e\u001d73?\u0012{WO\u00197f?\u001acw.\u0019;\u0016\u0003e\u0004R!\u000e\u001cl?\u0016\u000ba\"[7qYJz\u0016J\u001c;`\u0019>tw-F\u0001}!\u0015)d'W3F\u0003AIW\u000e\u001d73?\u001acw.\u0019;`\u0019>tw-F\u0001\u0000!\u0015)dgX3F\u0003=IW\u000e\u001d73?2{gnZ0M_:<WCAA\u0003!\u0015)d'Z3F\u0003EIW\u000e\u001d73?\u0012{WO\u00197f?2{gnZ\u000b\u0003\u0003\u0017\u0001R!\u000e\u001clK\u0016\u000b\u0001#[7qYJz\u0016J\u001c;`\t>,(\r\\3\u0016\u0005\u0005E\u0001#B\u001b73.,\u0015AE5na2\u0014tL\u00127pCR|Fi\\;cY\u0016,\"!a\u0006\u0011\u000bU2tl[#\u0002#%l\u0007\u000f\u001c\u001a`\u0019>twm\u0018#pk\ndW-\u0006\u0002\u0002\u001eA)QGN3l\u000b\u0006\u0019\u0012.\u001c9me}#u.\u001e2mK~#u.\u001e2mKV\u0011\u00111\u0005\t\u0006kYZ7.\u0012"
)
public final class OpLTE {
   public static UFunc.UImpl2 impl2_Double_Double() {
      return OpLTE$.MODULE$.impl2_Double_Double();
   }

   public static UFunc.UImpl2 impl2_Long_Double() {
      return OpLTE$.MODULE$.impl2_Long_Double();
   }

   public static UFunc.UImpl2 impl2_Float_Double() {
      return OpLTE$.MODULE$.impl2_Float_Double();
   }

   public static UFunc.UImpl2 impl2_Int_Double() {
      return OpLTE$.MODULE$.impl2_Int_Double();
   }

   public static UFunc.UImpl2 impl2_Double_Long() {
      return OpLTE$.MODULE$.impl2_Double_Long();
   }

   public static UFunc.UImpl2 impl2_Long_Long() {
      return OpLTE$.MODULE$.impl2_Long_Long();
   }

   public static UFunc.UImpl2 impl2_Float_Long() {
      return OpLTE$.MODULE$.impl2_Float_Long();
   }

   public static UFunc.UImpl2 impl2_Int_Long() {
      return OpLTE$.MODULE$.impl2_Int_Long();
   }

   public static UFunc.UImpl2 impl2_Double_Float() {
      return OpLTE$.MODULE$.impl2_Double_Float();
   }

   public static UFunc.UImpl2 impl2_Long_Float() {
      return OpLTE$.MODULE$.impl2_Long_Float();
   }

   public static UFunc.UImpl2 impl2_Float_Float() {
      return OpLTE$.MODULE$.impl2_Float_Float();
   }

   public static UFunc.UImpl2 impl2_Int_Float() {
      return OpLTE$.MODULE$.impl2_Int_Float();
   }

   public static UFunc.UImpl2 impl2_Double_Int() {
      return OpLTE$.MODULE$.impl2_Double_Int();
   }

   public static UFunc.UImpl2 impl2_Long_Int() {
      return OpLTE$.MODULE$.impl2_Long_Int();
   }

   public static UFunc.UImpl2 impl2_Float_Int() {
      return OpLTE$.MODULE$.impl2_Float_Int();
   }

   public static UFunc.UImpl2 impl2_Int_Int() {
      return OpLTE$.MODULE$.impl2_Int_Int();
   }

   public static UFunc.UImpl2 impl2FromOrdering(final Ordering evidence$6) {
      return OpLTE$.MODULE$.impl2FromOrdering(evidence$6);
   }

   public static Object withSink(final Object s) {
      return OpLTE$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return OpLTE$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return OpLTE$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return OpLTE$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return OpLTE$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return OpLTE$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return OpLTE$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return OpLTE$.MODULE$.apply(v, impl);
   }
}
