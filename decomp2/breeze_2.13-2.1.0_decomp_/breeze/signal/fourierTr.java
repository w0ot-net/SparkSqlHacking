package breeze.signal;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]<Q\u0001E\t\t\u0002Y1Q\u0001G\t\t\u0002eAQAJ\u0001\u0005\u0002\u001dBq\u0001K\u0001C\u0002\u0013\r\u0011\u0006\u0003\u0004?\u0003\u0001\u0006IA\u000b\u0005\u0006\u007f\u0005!\u0019\u0001\u0011\u0005\u0006\u000f\u0006!\u0019\u0001\u0013\u0005\u0006\u001d\u0006!\u0019a\u0014\u0005\b+\u0006\u0011\r\u0011b\u0001W\u0011\u0019A\u0016\u0001)A\u0005/\"9\u0011,\u0001b\u0001\n\u0007Q\u0006BB0\u0002A\u0003%1\fC\u0004a\u0003\t\u0007I1A1\t\r\u0011\f\u0001\u0015!\u0003c\u0011\u001d)\u0017A1A\u0005\u0004\u0019DaA^\u0001!\u0002\u00139\u0017!\u00034pkJLWM\u001d+s\u0015\t\u00112#\u0001\u0004tS\u001et\u0017\r\u001c\u0006\u0002)\u00051!M]3fu\u0016\u001c\u0001\u0001\u0005\u0002\u0018\u00035\t\u0011CA\u0005g_V\u0014\u0018.\u001a:UeN\u0019\u0011A\u0007\u0011\u0011\u0005mqR\"\u0001\u000f\u000b\u0003u\tQa]2bY\u0006L!a\b\u000f\u0003\r\u0005s\u0017PU3g!\t\tC%D\u0001#\u0015\t\u00193#A\u0004hK:,'/[2\n\u0005\u0015\u0012#!B+Gk:\u001c\u0017A\u0002\u001fj]&$h\bF\u0001\u0017\u00035!g\u000fR8vE2,\u0017\u0007\u0012$G)V\t!\u0006\u0005\u0003,Y9:dBA\f\u0001\u0013\tiCE\u0001\u0003J[Bd\u0007cA\u00183i5\t\u0001G\u0003\u00022'\u00051A.\u001b8bY\u001eL!a\r\u0019\u0003\u0017\u0011+gn]3WK\u000e$xN\u001d\t\u00037UJ!A\u000e\u000f\u0003\r\u0011{WO\u00197f!\ry#\u0007\u000f\t\u0003sqj\u0011A\u000f\u0006\u0003wM\tA!\\1uQ&\u0011QH\u000f\u0002\b\u0007>l\u0007\u000f\\3y\u00039!g\u000fR8vE2,\u0017\u0007\u0012$G)\u0002\nq\u0002\u001a<E)F\"eI\u0012+`\r2|\u0017\r^\u000b\u0002\u0003B!!\tL\"8\u001b\u0005\t\u0001cA\u00183\tB\u00111$R\u0005\u0003\rr\u0011QA\u00127pCR\fQ\u0002\u001a<E)F\"eI\u0012+`\u0013:$X#A%\u0011\t\tc#j\u000e\t\u0004_IZ\u0005CA\u000eM\u0013\tiEDA\u0002J]R\fa\u0002\u001a<E)F\"eI\u0012+`\u0019>tw-F\u0001Q!\u0011\u0011E&U\u001c\u0011\u0007=\u0012$\u000b\u0005\u0002\u001c'&\u0011A\u000b\b\u0002\u0005\u0019>tw-\u0001\bem\u000e{W\u000e\u001d7fqF\"eI\u0012+\u0016\u0003]\u0003Ba\u000b\u00178o\u0005yAM^\"p[BdW\r_\u0019E\r\u001a#\u0006%\u0001\be[\u000e{W\u000e\u001d7fqJ\"eI\u0012+\u0016\u0003m\u0003Ba\u000b\u0017]9B\u0019q&\u0018\u001d\n\u0005y\u0003$a\u0003#f]N,W*\u0019;sSb\fq\u0002Z7D_6\u0004H.\u001a=3\t\u001a3E\u000bI\u0001\u000eI6$u.\u001e2mKJ\"eI\u0012+\u0016\u0003\t\u0004Ba\u000b\u0017d9B\u0019q&\u0018\u001b\u0002\u001d\u0011lGi\\;cY\u0016\u0014DI\u0012$UA\u00051BM\u001e#pk\ndW-\r#G_V\u0014\u0018.\u001a:SC:<W-F\u0001h!\u0015\u0011\u0005N\f68\u0013\tIGEA\u0003J[Bd'\u0007\u0005\u0002lg:\u0011A.\u001d\b\u0003[Bl\u0011A\u001c\u0006\u0003_V\ta\u0001\u0010:p_Rt\u0014\"A\u000f\n\u0005Id\u0012a\u00029bG.\fw-Z\u0005\u0003iV\u0014QAU1oO\u0016T!A\u001d\u000f\u0002/\u00114Hi\\;cY\u0016\fDIR8ve&,'OU1oO\u0016\u0004\u0003"
)
public final class fourierTr {
   public static UFunc.UImpl2 dvDouble1DFourierRange() {
      return fourierTr$.MODULE$.dvDouble1DFourierRange();
   }

   public static UFunc.UImpl dmDouble2DFFT() {
      return fourierTr$.MODULE$.dmDouble2DFFT();
   }

   public static UFunc.UImpl dmComplex2DFFT() {
      return fourierTr$.MODULE$.dmComplex2DFFT();
   }

   public static UFunc.UImpl dvComplex1DFFT() {
      return fourierTr$.MODULE$.dvComplex1DFFT();
   }

   public static UFunc.UImpl dvDT1DFFT_Long() {
      return fourierTr$.MODULE$.dvDT1DFFT_Long();
   }

   public static UFunc.UImpl dvDT1DFFT_Int() {
      return fourierTr$.MODULE$.dvDT1DFFT_Int();
   }

   public static UFunc.UImpl dvDT1DFFT_Float() {
      return fourierTr$.MODULE$.dvDT1DFFT_Float();
   }

   public static UFunc.UImpl dvDouble1DFFT() {
      return fourierTr$.MODULE$.dvDouble1DFFT();
   }

   public static Object withSink(final Object s) {
      return fourierTr$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return fourierTr$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return fourierTr$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return fourierTr$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return fourierTr$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return fourierTr$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return fourierTr$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return fourierTr$.MODULE$.apply(v, impl);
   }
}
