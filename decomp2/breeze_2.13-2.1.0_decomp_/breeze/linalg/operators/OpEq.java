package breeze.linalg.operators;

import breeze.generic.UFunc;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015r!\u0002\u000b\u0016\u0011\u0003ab!\u0002\u0010\u0016\u0011\u0003y\u0002\"B\u0018\u0002\t\u0003\u0001\u0004\"B\u0019\u0002\t\u0007\u0011\u0004\"\u0002,\u0002\t\u00079\u0006\"\u0002/\u0002\t\u0007i\u0006\"\u00022\u0002\t\u0007\u0019\u0007\"\u00025\u0002\t\u0007I\u0007\"\u00028\u0002\t\u0007y\u0007\"B9\u0002\t\u0007\u0011\b\"\u0002;\u0002\t\u0007)\b\"B<\u0002\t\u0007A\b\"\u0002>\u0002\t\u0007Y\b\"B?\u0002\t\u0007q\bbBA\u0001\u0003\u0011\r\u00111\u0001\u0005\b\u0003\u000f\tA1AA\u0005\u0011\u001d\ti!\u0001C\u0002\u0003\u001fAq!a\u0005\u0002\t\u0007\t)\u0002C\u0004\u0002\u001a\u0005!\u0019!a\u0007\t\u000f\u0005}\u0011\u0001b\u0001\u0002\"\u0005!q\n]#r\u0015\t1r#A\u0005pa\u0016\u0014\u0018\r^8sg*\u0011\u0001$G\u0001\u0007Y&t\u0017\r\\4\u000b\u0003i\taA\u0019:fKj,7\u0001\u0001\t\u0003;\u0005i\u0011!\u0006\u0002\u0005\u001fB,\u0015o\u0005\u0003\u0002A\u0019J\u0003CA\u0011%\u001b\u0005\u0011#\"A\u0012\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0015\u0012#AB!osJ+g\r\u0005\u0002\u001eO%\u0011\u0001&\u0006\u0002\u0007\u001fB$\u0016\u0010]3\u0011\u0005)jS\"A\u0016\u000b\u00051J\u0012aB4f]\u0016\u0014\u0018nY\u0005\u0003]-\u0012\u0001#\u00127f[\u0016tGo^5tKV3UO\\2\u0002\rqJg.\u001b;?)\u0005a\u0012!E5na2\u0014dI]8n\u001fJ$WM]5oOV\u00111\u0007\u0010\u000b\u0003i!\u0003R!\u000e\u001c;u\u0015k\u0011!A\u0005\u0003oa\u0012Q!S7qYJJ!!O\u0016\u0003\u000bU3UO\\2\u0011\u0005mbD\u0002\u0001\u0003\u0006{\r\u0011\rA\u0010\u0002\u0002)F\u0011qH\u0011\t\u0003C\u0001K!!\u0011\u0012\u0003\u000f9{G\u000f[5oOB\u0011\u0011eQ\u0005\u0003\t\n\u00121!\u00118z!\t\tc)\u0003\u0002HE\t9!i\\8mK\u0006t\u0007bB%\u0004\u0003\u0003\u0005\u001dAS\u0001\u000bKZLG-\u001a8dK\u0012J\u0004cA&Tu9\u0011A*\u0015\b\u0003\u001bBk\u0011A\u0014\u0006\u0003\u001fn\ta\u0001\u0010:p_Rt\u0014\"A\u0012\n\u0005I\u0013\u0013a\u00029bG.\fw-Z\u0005\u0003)V\u0013\u0001b\u0014:eKJLgn\u001a\u0006\u0003%\n\nQ\"[7qYJz\u0016J\u001c;`\u0013:$X#\u0001-\u0011\u000bU2\u0014,W#\u0011\u0005\u0005R\u0016BA.#\u0005\rIe\u000e^\u0001\u0010S6\u0004HNM0GY>\fGoX%oiV\ta\fE\u00036m}KV\t\u0005\u0002\"A&\u0011\u0011M\t\u0002\u0006\r2|\u0017\r^\u0001\u000fS6\u0004HNM0M_:<w,\u00138u+\u0005!\u0007#B\u001b7Kf+\u0005CA\u0011g\u0013\t9'E\u0001\u0003M_:<\u0017\u0001E5na2\u0014t\fR8vE2,w,\u00138u+\u0005Q\u0007#B\u001b7Wf+\u0005CA\u0011m\u0013\ti'E\u0001\u0004E_V\u0014G.Z\u0001\u0010S6\u0004HNM0J]R|f\t\\8biV\t\u0001\u000fE\u00036me{V)A\tj[Bd'g\u0018$m_\u0006$xL\u00127pCR,\u0012a\u001d\t\u0006kYzv,R\u0001\u0011S6\u0004HNM0M_:<wL\u00127pCR,\u0012A\u001e\t\u0006kY*w,R\u0001\u0013S6\u0004HNM0E_V\u0014G.Z0GY>\fG/F\u0001z!\u0015)dg[0F\u00039IW\u000e\u001d73?&sGo\u0018'p]\u001e,\u0012\u0001 \t\u0006kYJV-R\u0001\u0011S6\u0004HNM0GY>\fGo\u0018'p]\u001e,\u0012a \t\u0006kYzV-R\u0001\u0010S6\u0004HNM0M_:<w\fT8oOV\u0011\u0011Q\u0001\t\u0006kY*W-R\u0001\u0012S6\u0004HNM0E_V\u0014G.Z0M_:<WCAA\u0006!\u0015)dg[3F\u0003AIW\u000e\u001d73?&sGo\u0018#pk\ndW-\u0006\u0002\u0002\u0012A)QGN-l\u000b\u0006\u0011\u0012.\u001c9me}3En\\1u?\u0012{WO\u00197f+\t\t9\u0002E\u00036m}[W)A\tj[Bd'g\u0018'p]\u001e|Fi\\;cY\u0016,\"!!\b\u0011\u000bU2Tm[#\u0002'%l\u0007\u000f\u001c\u001a`\t>,(\r\\3`\t>,(\r\\3\u0016\u0005\u0005\r\u0002#B\u001b7W.,\u0005"
)
public final class OpEq {
   public static UFunc.UImpl2 impl2_Double_Double() {
      return OpEq$.MODULE$.impl2_Double_Double();
   }

   public static UFunc.UImpl2 impl2_Long_Double() {
      return OpEq$.MODULE$.impl2_Long_Double();
   }

   public static UFunc.UImpl2 impl2_Float_Double() {
      return OpEq$.MODULE$.impl2_Float_Double();
   }

   public static UFunc.UImpl2 impl2_Int_Double() {
      return OpEq$.MODULE$.impl2_Int_Double();
   }

   public static UFunc.UImpl2 impl2_Double_Long() {
      return OpEq$.MODULE$.impl2_Double_Long();
   }

   public static UFunc.UImpl2 impl2_Long_Long() {
      return OpEq$.MODULE$.impl2_Long_Long();
   }

   public static UFunc.UImpl2 impl2_Float_Long() {
      return OpEq$.MODULE$.impl2_Float_Long();
   }

   public static UFunc.UImpl2 impl2_Int_Long() {
      return OpEq$.MODULE$.impl2_Int_Long();
   }

   public static UFunc.UImpl2 impl2_Double_Float() {
      return OpEq$.MODULE$.impl2_Double_Float();
   }

   public static UFunc.UImpl2 impl2_Long_Float() {
      return OpEq$.MODULE$.impl2_Long_Float();
   }

   public static UFunc.UImpl2 impl2_Float_Float() {
      return OpEq$.MODULE$.impl2_Float_Float();
   }

   public static UFunc.UImpl2 impl2_Int_Float() {
      return OpEq$.MODULE$.impl2_Int_Float();
   }

   public static UFunc.UImpl2 impl2_Double_Int() {
      return OpEq$.MODULE$.impl2_Double_Int();
   }

   public static UFunc.UImpl2 impl2_Long_Int() {
      return OpEq$.MODULE$.impl2_Long_Int();
   }

   public static UFunc.UImpl2 impl2_Float_Int() {
      return OpEq$.MODULE$.impl2_Float_Int();
   }

   public static UFunc.UImpl2 impl2_Int_Int() {
      return OpEq$.MODULE$.impl2_Int_Int();
   }

   public static UFunc.UImpl2 impl2FromOrdering(final Ordering evidence$9) {
      return OpEq$.MODULE$.impl2FromOrdering(evidence$9);
   }

   public static Object withSink(final Object s) {
      return OpEq$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return OpEq$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return OpEq$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return OpEq$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return OpEq$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return OpEq$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return OpEq$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return OpEq$.MODULE$.apply(v, impl);
   }
}
