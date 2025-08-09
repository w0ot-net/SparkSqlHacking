package breeze.optimize;

import breeze.generic.UFunc;
import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.support.CanCopy;
import breeze.math.VectorSpace;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ma\u0001B\b\u0011\u0001UA\u0001\"\b\u0001\u0003\u0002\u0003\u0006IA\b\u0005\t[\u0001\u0011\t\u0011)A\u0005E!Aa\u0006\u0001B\u0001B\u0003%!\u0005\u0003\u00050\u0001\t\u0005\t\u0015!\u00031\u0011!\u0019\u0004A!A!\u0002\u0017!\u0004\"\u0002\u001e\u0001\t\u0003Y\u0004\"B\"\u0001\t\u0003!u!B$\u0011\u0011\u0003Ae!B\b\u0011\u0011\u0003I\u0005\"\u0002\u001e\n\t\u0003Q\u0005\"B&\n\t\u0007a\u0005\"\u00022\n\t\u0003\u0019\u0007b\u0002>\n#\u0003%\ta\u001f\u0005\n\u0003\u001bI\u0011\u0013!C\u0001\u0003\u001f\u0011\u0001#R7qSJL7-\u00197IKN\u001c\u0018.\u00198\u000b\u0005E\u0011\u0012\u0001C8qi&l\u0017N_3\u000b\u0003M\taA\u0019:fKj,7\u0001A\u000b\u0003-\u0011\u001a\"\u0001A\f\u0011\u0005aYR\"A\r\u000b\u0003i\tQa]2bY\u0006L!\u0001H\r\u0003\r\u0005s\u0017PU3g\u0003\t!g\rE\u0002 A\tj\u0011\u0001E\u0005\u0003CA\u0011A\u0002R5gM\u001a+hn\u0019;j_:\u0004\"a\t\u0013\r\u0001\u0011)Q\u0005\u0001b\u0001M\t\tA+\u0005\u0002(UA\u0011\u0001\u0004K\u0005\u0003Se\u0011qAT8uQ&tw\r\u0005\u0002\u0019W%\u0011A&\u0007\u0002\u0004\u0003:L\u0018!\u0001=\u0002\t\u001d\u0014\u0018\rZ\u0001\u0004KB\u001c\bC\u0001\r2\u0013\t\u0011\u0014D\u0001\u0004E_V\u0014G.Z\u0001\u0003mN\u0004B!\u000e\u001d#a5\taG\u0003\u00028%\u0005!Q.\u0019;i\u0013\tIdGA\u0006WK\u000e$xN]*qC\u000e,\u0017A\u0002\u001fj]&$h\bF\u0003=\u007f\u0001\u000b%\t\u0006\u0002>}A\u0019q\u0004\u0001\u0012\t\u000bM2\u00019\u0001\u001b\t\u000bu1\u0001\u0019\u0001\u0010\t\u000b52\u0001\u0019\u0001\u0012\t\u000b92\u0001\u0019\u0001\u0012\t\u000f=2\u0001\u0013!a\u0001a\u00051A\u0005^5nKN$\"AI#\t\u000b\u0019;\u0001\u0019\u0001\u0012\u0002\u0003Q\f\u0001#R7qSJL7-\u00197IKN\u001c\u0018.\u00198\u0011\u0005}I1CA\u0005\u0018)\u0005A\u0015a\u00029s_\u0012,8\r^\u000b\u0004\u001b~\u0003W#\u0001(\u0011\u000b=;VL\u00180\u000f\u0005A+V\"A)\u000b\u0005I\u001b\u0016!C8qKJ\fGo\u001c:t\u0015\t!&#\u0001\u0004mS:\fGnZ\u0005\u0003-F\u000b1b\u00149Nk2l\u0015\r\u001e:jq&\u0011\u0001,\u0017\u0002\u0006\u00136\u0004HNM\u0005\u00035n\u0013Q!\u0016$v]\u000eT!\u0001\u0018\n\u0002\u000f\u001d,g.\u001a:jGB\u0019q\u0004\u00010\u0011\u0005\rzF!B\u0013\f\u0005\u00041C!B1\f\u0005\u00041#!A%\u0002\u000f!,7o]5b]R!AM\u001e=z)\r)\u0017N\u001c\t\u0004M\u001e\u0004T\"A*\n\u0005!\u001c&a\u0003#f]N,W*\u0019;sSbDQa\r\u0007A\u0004)\u0004B!\u000e\u001dlaA\u0019a\r\u001c\u0019\n\u00055\u001c&a\u0003#f]N,g+Z2u_JDQa\u001c\u0007A\u0004A\fAaY8qsB\u0019\u0011\u000f^6\u000e\u0003IT!a]*\u0002\u000fM,\b\u000f]8si&\u0011QO\u001d\u0002\b\u0007\u0006t7i\u001c9z\u0011\u0015iB\u00021\u0001x!\ry\u0002e\u001b\u0005\u0006[1\u0001\ra\u001b\u0005\b_1\u0001\n\u00111\u00011\u0003EAWm]:jC:$C-\u001a4bk2$HeM\u000b\u0002y*\u0012\u0001'`\u0016\u0002}B\u0019q0!\u0003\u000e\u0005\u0005\u0005!\u0002BA\u0002\u0003\u000b\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005\u001d\u0011$\u0001\u0006b]:|G/\u0019;j_:LA!a\u0003\u0002\u0002\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00135+\rY\u0018\u0011\u0003\u0003\u0006K9\u0011\rA\n"
)
public class EmpiricalHessian {
   private final DiffFunction df;
   private final Object x;
   private final Object grad;
   private final double eps;
   private final VectorSpace vs;

   public static double $lessinit$greater$default$4() {
      return EmpiricalHessian$.MODULE$.$lessinit$greater$default$4();
   }

   public static double hessian$default$3() {
      return EmpiricalHessian$.MODULE$.hessian$default$3();
   }

   public static DenseMatrix hessian(final DiffFunction df, final DenseVector x, final double eps, final VectorSpace vs, final CanCopy copy) {
      return EmpiricalHessian$.MODULE$.hessian(df, x, eps, vs, copy);
   }

   public static UFunc.UImpl2 product() {
      return EmpiricalHessian$.MODULE$.product();
   }

   public Object $times(final Object t) {
      return ((ImmutableNumericOps)this.vs.hasOps().apply(((ImmutableNumericOps)this.vs.hasOps().apply(this.df.gradientAt(((NumericOps)this.vs.hasOps().apply(this.x)).$plus(((ImmutableNumericOps)this.vs.hasOps().apply(t)).$times(BoxesRunTime.boxToDouble(this.eps), this.vs.mulVS_M()), this.vs.addVV())))).$minus(this.grad, this.vs.subVV()))).$div(BoxesRunTime.boxToDouble(this.eps), this.vs.divVS());
   }

   public EmpiricalHessian(final DiffFunction df, final Object x, final Object grad, final double eps, final VectorSpace vs) {
      this.df = df;
      this.x = x;
      this.grad = grad;
      this.eps = eps;
      this.vs = vs;
   }
}
