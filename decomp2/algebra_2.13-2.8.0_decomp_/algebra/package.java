package algebra;

import cats.kernel.Band;
import cats.kernel.BoundedSemilattice;
import cats.kernel.CommutativeGroup;
import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.Group;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.Semigroup;
import cats.kernel.Semilattice;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005v!B\u0014)\u0011\u0003Yc!B\u0017)\u0011\u0003q\u0003\"B\u001b\u0002\t\u00031T\u0001B\u001c\u0002\u0001aBqaS\u0001C\u0002\u0013\u0005A\n\u0003\u0004W\u0003\u0001\u0006I!T\u0003\u0005/\u0006\u0001\u0001\fC\u0004^\u0003\t\u0007I\u0011\u00010\t\r\u0005\f\u0001\u0015!\u0003`\u000b\u0011\u0011\u0017\u0001A2\t\u000f!\f!\u0019!C\u0001S\"1A.\u0001Q\u0001\n),A!\\\u0001\u0001]\"91/\u0001b\u0001\n\u0003!\bBB<\u0002A\u0003%Q/\u0002\u0003y\u0003\u0001I\bb\u0002@\u0002\u0005\u0004%\ta \u0005\t\u0003\u000b\t\u0001\u0015!\u0003\u0002\u0002\u00151\u0011qA\u0001\u0001\u0003\u0013A\u0011\"a\u0005\u0002\u0005\u0004%\t!!\u0006\t\u0011\u0005m\u0011\u0001)A\u0005\u0003/)a!!\b\u0002\u0001\u0005}\u0001\"CA\u0015\u0003\t\u0007I\u0011AA\u0016\u0011!\t\t$\u0001Q\u0001\n\u00055RABA\u001a\u0003\u0001\t)\u0004C\u0005\u0002@\u0005\u0011\r\u0011\"\u0001\u0002B!A\u0011qI\u0001!\u0002\u0013\t\u0019%\u0002\u0004\u0002J\u0005\u0001\u00111\n\u0005\n\u0003+\n!\u0019!C\u0001\u0003/B\u0001\"!\u0018\u0002A\u0003%\u0011\u0011L\u0003\u0007\u0003?\n\u0001!!\u0019\t\u0013\u0005-\u0014A1A\u0005\u0002\u00055\u0004\u0002CA:\u0003\u0001\u0006I!a\u001c\u0006\r\u0005U\u0014\u0001AA<\u0011%\t\t)\u0001b\u0001\n\u0003\t\u0019\t\u0003\u0005\u0002\n\u0006\u0001\u000b\u0011BAC\u000b\u0019\tY)\u0001\u0001\u0002\u000e\"I\u0011qS\u0001C\u0002\u0013\u0005\u0011\u0011\u0014\u0005\t\u0003?\u000b\u0001\u0015!\u0003\u0002\u001c\u00069\u0001/Y2lC\u001e,'\"A\u0015\u0002\u000f\u0005dw-\u001a2sC\u000e\u0001\u0001C\u0001\u0017\u0002\u001b\u0005A#a\u00029bG.\fw-Z\n\u0003\u0003=\u0002\"\u0001M\u001a\u000e\u0003ER\u0011AM\u0001\u0006g\u000e\fG.Y\u0005\u0003iE\u0012a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001,\u0005\u0011\u0011\u0015M\u001c3\u0016\u0005e\u0012\u0005c\u0001\u001e@\u00016\t1H\u0003\u0002={\u000511.\u001a:oK2T\u0011AP\u0001\u0005G\u0006$8/\u0003\u00028wA\u0011\u0011I\u0011\u0007\u0001\t\u0015\u00195A1\u0001E\u0005\u0005\t\u0015CA#I!\t\u0001d)\u0003\u0002Hc\t9aj\u001c;iS:<\u0007C\u0001\u0019J\u0013\tQ\u0015GA\u0002B]f\fAAQ1oIV\tQJ\u0004\u0002O+:\u0011q\n\u0016\b\u0003!Nk\u0011!\u0015\u0006\u0003%*\na\u0001\u0010:p_Rt\u0014\"\u0001 \n\u0005qj\u0014BA&<\u0003\u0015\u0011\u0015M\u001c3!\u0005I\u0011u.\u001e8eK\u0012\u001cV-\\5mCR$\u0018nY3\u0016\u0005ec\u0006c\u0001\u001e[7&\u0011qk\u000f\t\u0003\u0003r#Qa\u0011\u0004C\u0002\u0011\u000b!CQ8v]\u0012,GmU3nS2\fG\u000f^5dKV\tqL\u0004\u0002OA&\u0011QlO\u0001\u0014\u0005>,h\u000eZ3e'\u0016l\u0017\u000e\\1ui&\u001cW\r\t\u0002\u0011\u0007>lW.\u001e;bi&4Xm\u0012:pkB,\"\u0001Z4\u0011\u0007i*g-\u0003\u0002cwA\u0011\u0011i\u001a\u0003\u0006\u0007&\u0011\r\u0001R\u0001\u0011\u0007>lW.\u001e;bi&4Xm\u0012:pkB,\u0012A\u001b\b\u0003\u001d.L!\u0001[\u001e\u0002#\r{W.\\;uCRLg/Z$s_V\u0004\bEA\tD_6lW\u000f^1uSZ,Wj\u001c8pS\u0012,\"a\u001c:\u0011\u0007i\u0002\u0018/\u0003\u0002nwA\u0011\u0011I\u001d\u0003\u0006\u00072\u0011\r\u0001R\u0001\u0012\u0007>lW.\u001e;bi&4X-T8o_&$W#A;\u000f\u000593\u0018BA:<\u0003I\u0019u.\\7vi\u0006$\u0018N^3N_:|\u0017\u000e\u001a\u0011\u0003)\r{W.\\;uCRLg/Z*f[&<'o\\;q+\tQX\u0010E\u0002;wrL!\u0001_\u001e\u0011\u0005\u0005kH!B\"\u0010\u0005\u0004!\u0015\u0001F\"p[6,H/\u0019;jm\u0016\u001cV-\\5he>,\b/\u0006\u0002\u0002\u00029\u0019a*a\u0001\n\u0005y\\\u0014!F\"p[6,H/\u0019;jm\u0016\u001cV-\\5he>,\b\u000f\t\u0002\u0003\u000bF,B!a\u0003\u0002\u0012A)!(!\u0004\u0002\u0010%\u0019\u0011qA\u001e\u0011\u0007\u0005\u000b\t\u0002B\u0003D%\t\u0007A)\u0001\u0002FcV\u0011\u0011q\u0003\b\u0004\u001d\u0006e\u0011bAA\nw\u0005\u0019Q)\u001d\u0011\u0003\u000b\u001d\u0013x.\u001e9\u0016\t\u0005\u0005\u0012q\u0005\t\u0006u\u0005\r\u0012QE\u0005\u0004\u0003;Y\u0004cA!\u0002(\u0011)1)\u0006b\u0001\t\u0006)qI]8vaV\u0011\u0011Q\u0006\b\u0004\u001d\u0006=\u0012bAA\u0015w\u00051qI]8va\u0002\u0012a!T8o_&$W\u0003BA\u001c\u0003{\u0001RAOA\u001d\u0003wI1!a\r<!\r\t\u0015Q\b\u0003\u0006\u0007b\u0011\r\u0001R\u0001\u0007\u001b>tw.\u001b3\u0016\u0005\u0005\rcb\u0001(\u0002F%\u0019\u0011qH\u001e\u0002\u000f5{gn\\5eA\t)qJ\u001d3feV!\u0011QJA*!\u0015Q\u0014qJA)\u0013\r\tIe\u000f\t\u0004\u0003\u0006MC!B\"\u001c\u0005\u0004!\u0015!B(sI\u0016\u0014XCAA-\u001d\rq\u00151L\u0005\u0004\u0003+Z\u0014AB(sI\u0016\u0014\bE\u0001\u0007QCJ$\u0018.\u00197Pe\u0012,'/\u0006\u0003\u0002d\u0005%\u0004#\u0002\u001e\u0002f\u0005\u001d\u0014bAA0wA\u0019\u0011)!\u001b\u0005\u000b\rs\"\u0019\u0001#\u0002\u0019A\u000b'\u000f^5bY>\u0013H-\u001a:\u0016\u0005\u0005=db\u0001(\u0002r%\u0019\u00111N\u001e\u0002\u001bA\u000b'\u000f^5bY>\u0013H-\u001a:!\u0005%\u0019V-\\5he>,\b/\u0006\u0003\u0002z\u0005}\u0004#\u0002\u001e\u0002|\u0005u\u0014bAA;wA\u0019\u0011)a \u0005\u000b\r\u000b#\u0019\u0001#\u0002\u0013M+W.[4s_V\u0004XCAAC\u001d\rq\u0015qQ\u0005\u0004\u0003\u0003[\u0014AC*f[&<'o\\;qA\tY1+Z7jY\u0006$H/[2f+\u0011\ty)!&\u0011\u000bi\n\t*a%\n\u0007\u0005-5\bE\u0002B\u0003+#Qa\u0011\u0013C\u0002\u0011\u000b1bU3nS2\fG\u000f^5dKV\u0011\u00111\u0014\b\u0004\u001d\u0006u\u0015bAALw\u0005a1+Z7jY\u0006$H/[2fA\u0001"
)
public final class package {
   public static Semilattice Semilattice() {
      return package$.MODULE$.Semilattice();
   }

   public static Semigroup Semigroup() {
      return package$.MODULE$.Semigroup();
   }

   public static PartialOrder PartialOrder() {
      return package$.MODULE$.PartialOrder();
   }

   public static Order Order() {
      return package$.MODULE$.Order();
   }

   public static Monoid Monoid() {
      return package$.MODULE$.Monoid();
   }

   public static Group Group() {
      return package$.MODULE$.Group();
   }

   public static Eq Eq() {
      return package$.MODULE$.Eq();
   }

   public static CommutativeSemigroup CommutativeSemigroup() {
      return package$.MODULE$.CommutativeSemigroup();
   }

   public static CommutativeMonoid CommutativeMonoid() {
      return package$.MODULE$.CommutativeMonoid();
   }

   public static CommutativeGroup CommutativeGroup() {
      return package$.MODULE$.CommutativeGroup();
   }

   public static BoundedSemilattice BoundedSemilattice() {
      return package$.MODULE$.BoundedSemilattice();
   }

   public static Band Band() {
      return package$.MODULE$.Band();
   }
}
