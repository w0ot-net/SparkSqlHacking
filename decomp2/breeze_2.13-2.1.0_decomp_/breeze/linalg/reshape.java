package breeze.linalg;

import breeze.generic.UFunc;
import breeze.math.Semiring;
import breeze.storage.Zero;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=q!B\u0004\t\u0011\u0003ia!B\b\t\u0011\u0003\u0001\u0002\"B\u000f\u0002\t\u0003q\u0002\"B\u0010\u0002\t\u0007\u0001\u0003\"\u0002*\u0002\t\u0007\u0019\u0006\"B4\u0002\t\u0007A\u0007\"B<\u0002\t\u0007A\u0018a\u0002:fg\"\f\u0007/\u001a\u0006\u0003\u0013)\ta\u0001\\5oC2<'\"A\u0006\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0001\"AD\u0001\u000e\u0003!\u0011qA]3tQ\u0006\u0004XmE\u0002\u0002#]\u0001\"AE\u000b\u000e\u0003MQ\u0011\u0001F\u0001\u0006g\u000e\fG.Y\u0005\u0003-M\u0011a!\u00118z%\u00164\u0007C\u0001\r\u001c\u001b\u0005I\"B\u0001\u000e\u000b\u0003\u001d9WM\\3sS\u000eL!\u0001H\r\u0003\u000bU3UO\\2\u0002\rqJg.\u001b;?)\u0005i\u0011!C:w%\u0016\u001c\b.\u00199f+\t\t3\u0006\u0006\u0003#u\tS\u0005CB\u0012%MQ\"t'D\u0001\u0002\u0013\t)3DA\u0003J[Bd7\u0007E\u0002\u000fO%J!\u0001\u000b\u0005\u0003\u0019M\u0003\u0018M]:f-\u0016\u001cGo\u001c:\u0011\u0005)ZC\u0002\u0001\u0003\u0006Y\r\u0011\r!\f\u0002\u0002)F\u0011a&\r\t\u0003%=J!\u0001M\n\u0003\u000f9{G\u000f[5oOB\u0011!CM\u0005\u0003gM\u00111!\u00118z!\t\u0011R'\u0003\u00027'\t\u0019\u0011J\u001c;\u0011\u00079A\u0014&\u0003\u0002:\u0011\tI1iU\"NCR\u0014\u0018\u000e\u001f\u0005\bw\r\t\t\u0011q\u0001=\u0003))g/\u001b3f]\u000e,G%\r\t\u0004{\u0001KS\"\u0001 \u000b\u0005}\u001a\u0012a\u0002:fM2,7\r^\u0005\u0003\u0003z\u0012\u0001b\u00117bgN$\u0016m\u001a\u0005\b\u0007\u000e\t\t\u0011q\u0001E\u0003))g/\u001b3f]\u000e,GE\r\t\u0004\u000b\"KS\"\u0001$\u000b\u0005\u001dS\u0011\u0001B7bi\"L!!\u0013$\u0003\u0011M+W.\u001b:j]\u001eDqaS\u0002\u0002\u0002\u0003\u000fA*\u0001\u0006fm&$WM\\2fIM\u00022!\u0014)*\u001b\u0005q%BA(\u000b\u0003\u001d\u0019Ho\u001c:bO\u0016L!!\u0015(\u0003\ti+'o\\\u0001\nIZ\u0014Vm\u001d5ba\u0016,\"\u0001\u0016.\u0015\tUs\u0016\r\u001a\t\u0007G\u00112F\u0007N.\u0011\u000799\u0016,\u0003\u0002Y\u0011\tYA)\u001a8tKZ+7\r^8s!\tQ#\fB\u0003-\t\t\u0007Q\u0006E\u0002\u000f9fK!!\u0018\u0005\u0003\u0017\u0011+gn]3NCR\u0014\u0018\u000e\u001f\u0005\b?\u0012\t\t\u0011q\u0001a\u0003))g/\u001b3f]\u000e,G\u0005\u000e\t\u0004{\u0001K\u0006b\u00022\u0005\u0003\u0003\u0005\u001daY\u0001\u000bKZLG-\u001a8dK\u0012*\u0004cA#I3\"9Q\rBA\u0001\u0002\b1\u0017AC3wS\u0012,gnY3%mA\u0019Q\nU-\u0002\u0013\u0011l'+Z:iCB,WCA5n)\u0011Qg.\u001d;\u0011\r\r\"3\u000e\u000e\u001bl!\rqA\f\u001c\t\u0003U5$Q\u0001L\u0003C\u00025Bqa\\\u0003\u0002\u0002\u0003\u000f\u0001/\u0001\u0006fm&$WM\\2fI]\u00022!\u0010!m\u0011\u001d\u0011X!!AA\u0004M\f!\"\u001a<jI\u0016t7-\u001a\u00139!\r)\u0005\n\u001c\u0005\bk\u0016\t\t\u0011q\u0001w\u0003))g/\u001b3f]\u000e,G%\u000f\t\u0004\u001bBc\u0017AC2tGJ+7\u000f[1qKV\u0011\u00110 \u000b\u0007uz\f\u0019!!\u0003\u0011\r\r\"3\u0010\u000e\u001b|!\rq\u0001\b \t\u0003Uu$Q\u0001\f\u0004C\u00025B\u0001b \u0004\u0002\u0002\u0003\u000f\u0011\u0011A\u0001\fKZLG-\u001a8dK\u0012\n\u0004\u0007E\u0002>\u0001rD\u0011\"!\u0002\u0007\u0003\u0003\u0005\u001d!a\u0002\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013'\r\t\u0004\u000b\"c\b\"CA\u0006\r\u0005\u0005\t9AA\u0007\u0003-)g/\u001b3f]\u000e,G%\r\u001a\u0011\u00075\u0003F\u0010"
)
public final class reshape {
   public static UFunc.UImpl3 cscReshape(final ClassTag evidence$10, final Semiring evidence$11, final Zero evidence$12) {
      return reshape$.MODULE$.cscReshape(evidence$10, evidence$11, evidence$12);
   }

   public static UFunc.UImpl3 dmReshape(final ClassTag evidence$7, final Semiring evidence$8, final Zero evidence$9) {
      return reshape$.MODULE$.dmReshape(evidence$7, evidence$8, evidence$9);
   }

   public static UFunc.UImpl3 dvReshape(final ClassTag evidence$4, final Semiring evidence$5, final Zero evidence$6) {
      return reshape$.MODULE$.dvReshape(evidence$4, evidence$5, evidence$6);
   }

   public static UFunc.UImpl3 svReshape(final ClassTag evidence$1, final Semiring evidence$2, final Zero evidence$3) {
      return reshape$.MODULE$.svReshape(evidence$1, evidence$2, evidence$3);
   }

   public static Object withSink(final Object s) {
      return reshape$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return reshape$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return reshape$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return reshape$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return reshape$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return reshape$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return reshape$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return reshape$.MODULE$.apply(v, impl);
   }
}
