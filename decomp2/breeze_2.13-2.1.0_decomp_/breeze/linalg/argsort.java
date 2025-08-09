package breeze.linalg;

import breeze.generic.UFunc;
import scala.;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=;Qa\u0002\u0005\t\u000251Qa\u0004\u0005\t\u0002AAQ\u0001I\u0001\u0005\u0002\u0005BQAI\u0001\u0005\u0004\rBQAO\u0001\u0005\u0004mBQ!Q\u0001\u0005\u0004\tCQ\u0001S\u0001\u0005\u0004%\u000bq!\u0019:hg>\u0014HO\u0003\u0002\n\u0015\u00051A.\u001b8bY\u001eT\u0011aC\u0001\u0007EJ,WM_3\u0004\u0001A\u0011a\"A\u0007\u0002\u0011\t9\u0011M]4t_J$8\u0003B\u0001\u0012/u\u0001\"AE\u000b\u000e\u0003MQ\u0011\u0001F\u0001\u0006g\u000e\fG.Y\u0005\u0003-M\u0011a!\u00118z%\u00164\u0007C\u0001\r\u001c\u001b\u0005I\"B\u0001\u000e\u000b\u0003\u001d9WM\\3sS\u000eL!\u0001H\r\u0003\u000bU3UO\\2\u0011\u00059q\u0012BA\u0010\t\u0005Iaun\u001e)sS>\u0014\u0018\u000e^=Be\u001e\u001cvN\u001d;\u0002\rqJg.\u001b;?)\u0005i\u0011AF1sON|'\u000f\u001e#f]N,g+Z2u_J|\u0016J\u001c;\u0016\u0003\u0011\u0002B!\n\u0014)]5\t\u0011!\u0003\u0002(7\t!\u0011*\u001c9m!\rq\u0011fK\u0005\u0003U!\u00111\u0002R3og\u00164Vm\u0019;peB\u0011!\u0003L\u0005\u0003[M\u00111!\u00138u!\rysg\u000b\b\u0003aUr!!\r\u001b\u000e\u0003IR!a\r\u0007\u0002\rq\u0012xn\u001c;?\u0013\u0005!\u0012B\u0001\u001c\u0014\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001O\u001d\u0003\u0015%sG-\u001a=fIN+\u0017O\u0003\u00027'\u0005I\u0012M]4t_J$H)\u001a8tKZ+7\r^8s?\u0012{WO\u00197f+\u0005a\u0004\u0003B\u0013'{9\u00022AD\u0015?!\t\u0011r(\u0003\u0002A'\t1Ai\\;cY\u0016\f\u0001$\u0019:hg>\u0014H\u000fR3og\u00164Vm\u0019;pe~3En\\1u+\u0005\u0019\u0005\u0003B\u0013'\t:\u00022AD\u0015F!\t\u0011b)\u0003\u0002H'\t)a\t\\8bi\u00069\u0012M]4t_J$H)\u001a8tKZ+7\r^8s?2{gnZ\u000b\u0002\u0015B!QEJ&/!\rq\u0011\u0006\u0014\t\u0003%5K!AT\n\u0003\t1{gn\u001a"
)
public final class argsort {
   public static UFunc.UImpl argsortDenseVector_Long() {
      return argsort$.MODULE$.argsortDenseVector_Long();
   }

   public static UFunc.UImpl argsortDenseVector_Float() {
      return argsort$.MODULE$.argsortDenseVector_Float();
   }

   public static UFunc.UImpl argsortDenseVector_Double() {
      return argsort$.MODULE$.argsortDenseVector_Double();
   }

   public static UFunc.UImpl argsortDenseVector_Int() {
      return argsort$.MODULE$.argsortDenseVector_Int();
   }

   public static UFunc.UImpl argsortQuasiTensorWithOrdering(final .less.colon.less qt, final Ordering ord) {
      return argsort$.MODULE$.argsortQuasiTensorWithOrdering(qt, ord);
   }

   public static Object withSink(final Object s) {
      return argsort$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return argsort$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return argsort$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return argsort$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return argsort$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return argsort$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return argsort$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return argsort$.MODULE$.apply(v, impl);
   }
}
