package breeze.optimize.proximal;

import breeze.linalg.DenseVector;
import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005c\u0001B\u000b\u0017\u0001vAQa\u000e\u0001\u0005\u0002aBQA\u000f\u0001\u0005\u0002mBq\u0001\u0014\u0001\u0012\u0002\u0013\u0005Q\nC\u0004Y\u0001\u0005\u0005I\u0011\u0001\u001d\t\u000fe\u0003\u0011\u0011!C!5\"91\rAA\u0001\n\u0003!\u0007b\u00025\u0001\u0003\u0003%\t!\u001b\u0005\b_\u0002\t\t\u0011\"\u0011q\u0011\u001d9\b!!A\u0005\u0002aDq! \u0001\u0002\u0002\u0013\u0005c\u0010C\u0005\u0002\u0002\u0001\t\t\u0011\"\u0011\u0002\u0004!I\u0011Q\u0001\u0001\u0002\u0002\u0013\u0005\u0013q\u0001\u0005\n\u0003\u0013\u0001\u0011\u0011!C!\u0003\u00179\u0011\"a\u0004\u0017\u0003\u0003E\t!!\u0005\u0007\u0011U1\u0012\u0011!E\u0001\u0003'AaaN\b\u0005\u0002\u0005-\u0002\"CA\u0003\u001f\u0005\u0005IQIA\u0004\u0011!\ticDA\u0001\n\u0003C\u0004\"CA\u0018\u001f\u0005\u0005I\u0011QA\u0019\u0011%\t9dDA\u0001\n\u0013\tID\u0001\u0006Qe>TWm\u0019;Q_NT!a\u0006\r\u0002\u0011A\u0014x\u000e_5nC2T!!\u0007\u000e\u0002\u0011=\u0004H/[7ju\u0016T\u0011aG\u0001\u0007EJ,WM_3\u0004\u0001M)\u0001A\b\u0013)WA\u0011qDI\u0007\u0002A)\t\u0011%A\u0003tG\u0006d\u0017-\u0003\u0002$A\t1\u0011I\\=SK\u001a\u0004\"!\n\u0014\u000e\u0003YI!a\n\f\u0003\u0011A\u0013x\u000e_5nC2\u0004\"aH\u0015\n\u0005)\u0002#a\u0002)s_\u0012,8\r\u001e\t\u0003YQr!!\f\u001a\u000f\u00059\nT\"A\u0018\u000b\u0005Ab\u0012A\u0002\u001fs_>$h(C\u0001\"\u0013\t\u0019\u0004%A\u0004qC\u000e\\\u0017mZ3\n\u0005U2$\u0001D*fe&\fG.\u001b>bE2,'BA\u001a!\u0003\u0019a\u0014N\\5u}Q\t\u0011\b\u0005\u0002&\u0001\u0005!\u0001O]8y)\ratH\u0013\t\u0003?uJ!A\u0010\u0011\u0003\tUs\u0017\u000e\u001e\u0005\u0006\u0001\n\u0001\r!Q\u0001\u0002qB\u0019!)R$\u000e\u0003\rS!\u0001\u0012\u000e\u0002\r1Lg.\u00197h\u0013\t15IA\u0006EK:\u001cXMV3di>\u0014\bCA\u0010I\u0013\tI\u0005E\u0001\u0004E_V\u0014G.\u001a\u0005\b\u0017\n\u0001\n\u00111\u0001H\u0003\r\u0011\bn\\\u0001\u000faJ|\u0007\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0005q%FA$PW\u0005\u0001\u0006CA)W\u001b\u0005\u0011&BA*U\u0003%)hn\u00195fG.,GM\u0003\u0002VA\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005]\u0013&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006!1m\u001c9z\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\t1\f\u0005\u0002]C6\tQL\u0003\u0002_?\u0006!A.\u00198h\u0015\u0005\u0001\u0017\u0001\u00026bm\u0006L!AY/\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005)\u0007CA\u0010g\u0013\t9\u0007EA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002k[B\u0011qd[\u0005\u0003Y\u0002\u00121!\u00118z\u0011\u001dqw!!AA\u0002\u0015\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#A9\u0011\u0007I,(.D\u0001t\u0015\t!\b%\u0001\u0006d_2dWm\u0019;j_:L!A^:\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0003sr\u0004\"a\b>\n\u0005m\u0004#a\u0002\"p_2,\u0017M\u001c\u0005\b]&\t\t\u00111\u0001k\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0005m{\bb\u00028\u000b\u0003\u0003\u0005\r!Z\u0001\tQ\u0006\u001c\bnQ8eKR\tQ-\u0001\u0005u_N#(/\u001b8h)\u0005Y\u0016AB3rk\u0006d7\u000fF\u0002z\u0003\u001bAqA\\\u0007\u0002\u0002\u0003\u0007!.\u0001\u0006Qe>TWm\u0019;Q_N\u0004\"!J\b\u0014\u000b=\t)\"!\t\u0011\u000b\u0005]\u0011QD\u001d\u000e\u0005\u0005e!bAA\u000eA\u00059!/\u001e8uS6,\u0017\u0002BA\u0010\u00033\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c81!\u0011\t\u0019#!\u000b\u000e\u0005\u0005\u0015\"bAA\u0014?\u0006\u0011\u0011n\\\u0005\u0004k\u0005\u0015BCAA\t\u0003\u0015\t\u0007\u000f\u001d7z\u0003\u001d)h.\u00199qYf$2!_A\u001a\u0011!\t)dEA\u0001\u0002\u0004I\u0014a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\b\t\u00049\u0006u\u0012bAA ;\n1qJ\u00196fGR\u0004"
)
public class ProjectPos implements Proximal, Product, Serializable {
   public static boolean unapply(final ProjectPos x$0) {
      return ProjectPos$.MODULE$.unapply(x$0);
   }

   public static ProjectPos apply() {
      return ProjectPos$.MODULE$.apply();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double valueAt(final DenseVector x) {
      return Proximal.valueAt$(this, x);
   }

   public void prox(final DenseVector x, final double rho) {
      int index$macro$2 = 0;

      for(int limit$macro$4 = x.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
         x.update$mcD$sp(index$macro$2, .MODULE$.max((double)0.0F, x.apply$mcD$sp(index$macro$2)));
      }

   }

   public double prox$default$2() {
      return (double)0.0F;
   }

   public ProjectPos copy() {
      return new ProjectPos();
   }

   public String productPrefix() {
      return "ProjectPos";
   }

   public int productArity() {
      return 0;
   }

   public Object productElement(final int x$1) {
      Object var2 = Statics.ioobe(x$1);
      return var2;
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ProjectPos;
   }

   public String productElementName(final int x$1) {
      String var2 = (String)Statics.ioobe(x$1);
      return var2;
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var2;
      if (x$1 instanceof ProjectPos) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2 && ((ProjectPos)x$1).canEqual(this);
   }

   public ProjectPos() {
      Proximal.$init$(this);
      Product.$init$(this);
   }
}
