package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanTransformValues;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]q!B\u0005\u000b\u0011\u0003ya!B\t\u000b\u0011\u0003\u0011\u0002\"B\u0010\u0002\t\u0003\u0001\u0003\"B\u0011\u0002\t\u0007\u0011\u0003\"\u0002(\u0002\t\u0007y\u0005\"\u00020\u0002\t\u0007y\u0006\"\u00026\u0002\t\u0007Y\u0007\"B;\u0002\t\u00071\bbBA\u0001\u0003\u0011\r\u00111A\u0001\u0005G2L\u0007O\u0003\u0002\f\u0019\u00051A.\u001b8bY\u001eT\u0011!D\u0001\u0007EJ,WM_3\u0004\u0001A\u0011\u0001#A\u0007\u0002\u0015\t!1\r\\5q'\r\t1#\u0007\t\u0003)]i\u0011!\u0006\u0006\u0002-\u0005)1oY1mC&\u0011\u0001$\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0005iiR\"A\u000e\u000b\u0005qa\u0011aB4f]\u0016\u0014\u0018nY\u0005\u0003=m\u0011\u0001#\u00127f[\u0016tGo^5tKV3UO\\2\u0002\rqJg.\u001b;?)\u0005y\u0011\u0001D2mSB|%\u000fZ3sS:<WcA\u0012-mQ\u0019A\u0005\u000f$\u0011\r\u00152#&N\u001b+\u001b\u0005\t\u0011BA\u0014)\u0005\u0015IU\u000e\u001d74\u0013\tI3DA\u0003V\rVt7\r\u0005\u0002,Y1\u0001A!B\u0017\u0004\u0005\u0004q#!\u0001+\u0012\u0005=\u0012\u0004C\u0001\u000b1\u0013\t\tTCA\u0004O_RD\u0017N\\4\u0011\u0005Q\u0019\u0014B\u0001\u001b\u0016\u0005\r\te.\u001f\t\u0003WY\"QaN\u0002C\u00029\u0012\u0011A\u0016\u0005\u0006s\r\u0001\u001dAO\u0001\t_J$WM]5oOB\u00191hQ\u001b\u000f\u0005q\neBA\u001fA\u001b\u0005q$BA \u000f\u0003\u0019a$o\\8u}%\ta#\u0003\u0002C+\u00059\u0001/Y2lC\u001e,\u0017B\u0001#F\u0005!y%\u000fZ3sS:<'B\u0001\"\u0016\u0011\u001595\u0001q\u0001I\u0003\r\u0019WN\u001e\t\u0007\u00132SS'\u000e\u0016\u000e\u0003)S!a\u0013\u0006\u0002\u000fM,\b\u000f]8si&\u0011QJ\u0013\u0002\r\u0007\u0006tW*\u00199WC2,Xm]\u0001\u0014G2L\u0007/\u00138QY\u0006\u001cWm\u0014:eKJLgnZ\u000b\u0004!V;FcA)Y5B)QE\u0015+W-&\u00111\u000b\u000b\u0002\r\u0013:\u0004F.Y2f\u00136\u0004Hn\r\t\u0003WU#Q!\f\u0003C\u00029\u0002\"aK,\u0005\u000b]\"!\u0019\u0001\u0018\t\u000be\"\u00019A-\u0011\u0007m\u001ae\u000bC\u0003H\t\u0001\u000f1\f\u0005\u0003J9R3\u0016BA/K\u0005I\u0019\u0015M\u001c+sC:\u001chm\u001c:n-\u0006dW/Z:\u0002%\rd\u0017\u000e]%o!2\f7-Z0E_V\u0014G.Z\u000b\u0003A\u000e$\"!\u00195\u0011\u000b\u0015\u0012&-Z3\u0011\u0005-\u001aG!\u00023\u0006\u0005\u0004q#a\u0001,fGB\u0011ACZ\u0005\u0003OV\u0011a\u0001R8vE2,\u0007\"B$\u0006\u0001\bI\u0007\u0003B%]E\u0016\f\u0011c\u00197ja&s\u0007\u000b\\1dK~3En\\1u+\taw\u000e\u0006\u0002ngB)QE\u00158qaB\u00111f\u001c\u0003\u0006I\u001a\u0011\rA\f\t\u0003)EL!A]\u000b\u0003\u000b\u0019cw.\u0019;\t\u000b\u001d3\u00019\u0001;\u0011\t%cf\u000e]\u0001\u0010G2L\u0007/\u00138QY\u0006\u001cWmX%oiV\u0011qO\u001f\u000b\u0003qz\u0004R!\n*zwn\u0004\"a\u000b>\u0005\u000b\u0011<!\u0019\u0001\u0018\u0011\u0005Qa\u0018BA?\u0016\u0005\rIe\u000e\u001e\u0005\u0006\u000f\u001e\u0001\u001da \t\u0005\u0013rK80\u0001\tdY&\u0004\u0018J\u001c)mC\u000e,w\fT8oOV!\u0011QAA\u0006)\u0011\t9!a\u0005\u0011\u0011\u0015\u0012\u0016\u0011BA\u0007\u0003\u001b\u00012aKA\u0006\t\u0015!\u0007B1\u0001/!\r!\u0012qB\u0005\u0004\u0003#)\"\u0001\u0002'p]\u001eDaa\u0012\u0005A\u0004\u0005U\u0001CB%]\u0003\u0013\ti\u0001"
)
public final class clip {
   public static UFunc.InPlaceImpl3 clipInPlace_Long(final CanTransformValues cmv) {
      return clip$.MODULE$.clipInPlace_Long(cmv);
   }

   public static UFunc.InPlaceImpl3 clipInPlace_Int(final CanTransformValues cmv) {
      return clip$.MODULE$.clipInPlace_Int(cmv);
   }

   public static UFunc.InPlaceImpl3 clipInPlace_Float(final CanTransformValues cmv) {
      return clip$.MODULE$.clipInPlace_Float(cmv);
   }

   public static UFunc.InPlaceImpl3 clipInPlace_Double(final CanTransformValues cmv) {
      return clip$.MODULE$.clipInPlace_Double(cmv);
   }

   public static UFunc.InPlaceImpl3 clipInPlaceOrdering(final Ordering ordering, final CanTransformValues cmv) {
      return clip$.MODULE$.clipInPlaceOrdering(ordering, cmv);
   }

   public static UFunc.UImpl3 clipOrdering(final Ordering ordering, final CanMapValues cmv) {
      return clip$.MODULE$.clipOrdering(ordering, cmv);
   }

   public static Object withSink(final Object s) {
      return clip$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return clip$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return clip$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return clip$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return clip$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return clip$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return clip$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return clip$.MODULE$.apply(v, impl);
   }
}
