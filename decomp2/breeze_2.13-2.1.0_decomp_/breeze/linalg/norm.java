package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.CanTraverseValues;
import breeze.math.Field;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055s!B\t\u0013\u0011\u00039b!B\r\u0013\u0011\u0003Q\u0002\"B\u0014\u0002\t\u0003A\u0003bB\u0015\u0002\u0005\u0004%\u0019A\u000b\u0005\u0007k\u0005\u0001\u000b\u0011B\u0016\t\u000fY\n!\u0019!C\u0002o!1A(\u0001Q\u0001\naBq!P\u0001C\u0002\u0013\ra\b\u0003\u0004D\u0003\u0001\u0006Ia\u0010\u0005\b\t\u0006\u0011\r\u0011b\u0001F\u0011\u00199\u0015\u0001)A\u0005\r\")\u0001*\u0001C\u0002\u0013\")q,\u0001C\u0002A\")q-\u0001C\u0002Q\")\u0001/\u0001C\u0002c\"1q0\u0001C\u0002\u0003\u0003Aq!a\f\u0002\t\u0003\t\t$\u0001\u0003o_Jl'BA\n\u0015\u0003\u0019a\u0017N\\1mO*\tQ#\u0001\u0004ce\u0016,'0Z\u0002\u0001!\tA\u0012!D\u0001\u0013\u0005\u0011qwN]7\u0014\u0007\u0005Y\u0012\u0005\u0005\u0002\u001d?5\tQDC\u0001\u001f\u0003\u0015\u00198-\u00197b\u0013\t\u0001SD\u0001\u0004B]f\u0014VM\u001a\t\u0003E\u0015j\u0011a\t\u0006\u0003IQ\tqaZ3oKJL7-\u0003\u0002'G\t)QKR;oG\u00061A(\u001b8jiz\"\u0012aF\u0001\u000fg\u000e\fG.\u0019:O_Jlw,\u00138u+\u0005Y\u0003\u0003\u0002\u0017._Ij\u0011!A\u0005\u0003]\u0015\u0012A!S7qYB\u0011A\u0004M\u0005\u0003cu\u00111!\u00138u!\ta2'\u0003\u00025;\t1Ai\\;cY\u0016\fqb]2bY\u0006\u0014hj\u001c:n?&sG\u000fI\u0001\u0010g\u000e\fG.\u0019:O_Jlw\fT8oOV\t\u0001\b\u0005\u0003-[e\u0012\u0004C\u0001\u000f;\u0013\tYTD\u0001\u0003M_:<\u0017\u0001E:dC2\f'OT8s[~cuN\\4!\u0003A\u00198-\u00197be:{'/\\0GY>\fG/F\u0001@!\u0011aS\u0006\u0011\u001a\u0011\u0005q\t\u0015B\u0001\"\u001e\u0005\u00151En\\1u\u0003E\u00198-\u00197be:{'/\\0GY>\fG\u000fI\u0001\u0012g\u000e\fG.\u0019:O_Jlw\fR8vE2,W#\u0001$\u0011\t1j#GM\u0001\u0013g\u000e\fG.\u0019:O_Jlw\fR8vE2,\u0007%\u0001\u000bo_Jl\u0017\r\u001c(pe6$vNT8s[Vs\u0017\u000e^\u000b\u0003\u0015B#\"a\u0013/\u0011\u000b1be*\u0017\u001a\n\u00055+#!B%na2\u0014\u0004CA(Q\u0019\u0001!Q!U\u0006C\u0002I\u0013\u0011\u0001V\t\u0003'Z\u0003\"\u0001\b+\n\u0005Uk\"a\u0002(pi\"Lgn\u001a\t\u00039]K!\u0001W\u000f\u0003\u0007\u0005s\u0017\u0010\u0005\u0002\u001d5&\u00111,\b\u0002\u0005+:LG\u000fC\u0003^\u0017\u0001\u000fa,\u0001\u0005o_Jl\u0017*\u001c9m!\u0011aSF\u0014\u001a\u0002-9|'/\u001c#pk\ndW\rV8O_Jl\u0017\r\u001c(pe6,\"!\u00193\u0015\u0005\t,\u0007\u0003\u0002\u0017.GJ\u0002\"a\u00143\u0005\u000bEc!\u0019\u0001*\t\u000buc\u00019\u00014\u0011\u000b1b5M\r\u001a\u0002\u001d\u0019\u0014x.\\\"b]:{'/\\%oiV\u0011\u0011\u000e\u001c\u000b\u0003U6\u0004R\u0001\f'l_I\u0002\"a\u00147\u0005\u000bEk!\u0019\u0001*\t\u000b9l\u00019A8\u0002\t%l\u0007\u000f\u001c\t\u0006Y1['GM\u0001\u000bg\u000e\fG.\u0019:O_JlWC\u0001:w)\t\u0019x\u000f\u0005\u0003u[U\u0014dB\u0001\r\u0001!\tye\u000fB\u0003R\u001d\t\u0007!\u000bC\u0003y\u001d\u0001\u000f\u00110A\u0003gS\u0016dG\rE\u0002{{Vl\u0011a\u001f\u0006\u0003yR\tA!\\1uQ&\u0011ap\u001f\u0002\u0006\r&,G\u000eZ\u0001\bG\u0006tgj\u001c:n+\u0019\t\u0019!!\u0003\u0002\u0018Q1\u0011QAA\r\u0003S\u0001b\u0001\u001e'\u0002\bI\u0012\u0004cA(\u0002\n\u00119\u00111B\bC\u0002\u00055!a\u0001,fGF\u00191+a\u0004\u0011\u000ba\t\t\"!\u0006\n\u0007\u0005M!C\u0001\u0004WK\u000e$xN\u001d\t\u0004\u001f\u0006]A!B)\u0010\u0005\u0004\u0011\u0006bBA\u000e\u001f\u0001\u000f\u0011QD\u0001\u0012G\u0006tGK]1wKJ\u001cXMV1mk\u0016\u001c\b\u0003CA\u0010\u0003K\t9!!\u0006\u000e\u0005\u0005\u0005\"bAA\u0012%\u000591/\u001e9q_J$\u0018\u0002BA\u0014\u0003C\u0011\u0011cQ1o)J\fg/\u001a:tKZ\u000bG.^3t\u0011\u001d\tYc\u0004a\u0002\u0003[\t\u0001bY1o\u001d>\u0014Xn\u0015\t\u0006i6\n)BM\u0001\u0013MJ|W\u000e\u0016:bm\u0016\u00148/\u001a,bYV,7/\u0006\u0004\u00024\u0005}\u0012q\t\u000b\u0007\u0003k\t\t%!\u0013\u0013\u000b\u0005]2$a\u000f\u0007\r\u0005e\u0002\u0003AA\u001b\u00051a$/\u001a4j]\u0016lWM\u001c;?!\u0019!H*!\u00103eA\u0019q*a\u0010\u0005\r\u0005-\u0001C1\u0001S\u0011\u001d\tY\u0002\u0005a\u0002\u0003\u0007\u0002\u0002\"a\b\u0002&\u0005u\u0012Q\t\t\u0004\u001f\u0006\u001dC!B)\u0011\u0005\u0004\u0011\u0006bBA\u0016!\u0001\u000f\u00111\n\t\u0006i6\n)E\r"
)
public final class norm {
   public static UFunc.UImpl2 fromTraverseValues(final CanTraverseValues canTraverseValues, final UFunc.UImpl canNormS) {
      return norm$.MODULE$.fromTraverseValues(canTraverseValues, canNormS);
   }

   public static UFunc.UImpl2 canNorm(final CanTraverseValues canTraverseValues, final UFunc.UImpl canNormS) {
      return norm$.MODULE$.canNorm(canTraverseValues, canNormS);
   }

   public static UFunc.UImpl scalarNorm(final Field field) {
      return norm$.MODULE$.scalarNorm(field);
   }

   public static UFunc.UImpl2 fromCanNormInt(final UFunc.UImpl2 impl) {
      return norm$.MODULE$.fromCanNormInt(impl);
   }

   public static UFunc.UImpl normDoubleToNormalNorm(final UFunc.UImpl2 normImpl) {
      return norm$.MODULE$.normDoubleToNormalNorm(normImpl);
   }

   public static UFunc.UImpl2 normalNormToNormUnit(final UFunc.UImpl normImpl) {
      return norm$.MODULE$.normalNormToNormUnit(normImpl);
   }

   public static UFunc.UImpl scalarNorm_Double() {
      return norm$.MODULE$.scalarNorm_Double();
   }

   public static UFunc.UImpl scalarNorm_Float() {
      return norm$.MODULE$.scalarNorm_Float();
   }

   public static UFunc.UImpl scalarNorm_Long() {
      return norm$.MODULE$.scalarNorm_Long();
   }

   public static UFunc.UImpl scalarNorm_Int() {
      return norm$.MODULE$.scalarNorm_Int();
   }

   public static Object withSink(final Object s) {
      return norm$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return norm$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return norm$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return norm$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return norm$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return norm$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return norm$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return norm$.MODULE$.apply(v, impl);
   }
}
