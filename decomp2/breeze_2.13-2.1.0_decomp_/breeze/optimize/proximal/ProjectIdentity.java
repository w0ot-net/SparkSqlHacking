package breeze.optimize.proximal;

import breeze.linalg.DenseVector;
import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005c\u0001B\u000b\u0017\u0001vAQa\u000e\u0001\u0005\u0002aBQA\u000f\u0001\u0005\u0002mBq\u0001\u0014\u0001\u0012\u0002\u0013\u0005Q\nC\u0004Y\u0001\u0005\u0005I\u0011\u0001\u001d\t\u000fe\u0003\u0011\u0011!C!5\"91\rAA\u0001\n\u0003!\u0007b\u00025\u0001\u0003\u0003%\t!\u001b\u0005\b_\u0002\t\t\u0011\"\u0011q\u0011\u001d9\b!!A\u0005\u0002aDq! \u0001\u0002\u0002\u0013\u0005c\u0010C\u0005\u0002\u0002\u0001\t\t\u0011\"\u0011\u0002\u0004!I\u0011Q\u0001\u0001\u0002\u0002\u0013\u0005\u0013q\u0001\u0005\n\u0003\u0013\u0001\u0011\u0011!C!\u0003\u00179\u0011\"a\u0004\u0017\u0003\u0003E\t!!\u0005\u0007\u0011U1\u0012\u0011!E\u0001\u0003'AaaN\b\u0005\u0002\u0005-\u0002\"CA\u0003\u001f\u0005\u0005IQIA\u0004\u0011!\ticDA\u0001\n\u0003C\u0004\"CA\u0018\u001f\u0005\u0005I\u0011QA\u0019\u0011%\t9dDA\u0001\n\u0013\tIDA\bQe>TWm\u0019;JI\u0016tG/\u001b;z\u0015\t9\u0002$\u0001\u0005qe>D\u0018.\\1m\u0015\tI\"$\u0001\u0005paRLW.\u001b>f\u0015\u0005Y\u0012A\u00022sK\u0016TXm\u0001\u0001\u0014\u000b\u0001qB\u0005K\u0016\u0011\u0005}\u0011S\"\u0001\u0011\u000b\u0003\u0005\nQa]2bY\u0006L!a\t\u0011\u0003\r\u0005s\u0017PU3g!\t)c%D\u0001\u0017\u0013\t9cC\u0001\u0005Qe>D\u0018.\\1m!\ty\u0012&\u0003\u0002+A\t9\u0001K]8ek\u000e$\bC\u0001\u00175\u001d\ti#G\u0004\u0002/c5\tqF\u0003\u000219\u00051AH]8pizJ\u0011!I\u0005\u0003g\u0001\nq\u0001]1dW\u0006<W-\u0003\u00026m\ta1+\u001a:jC2L'0\u00192mK*\u00111\u0007I\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003e\u0002\"!\n\u0001\u0002\tA\u0014x\u000e\u001f\u000b\u0004y}R\u0005CA\u0010>\u0013\tq\u0004E\u0001\u0003V]&$\b\"\u0002!\u0003\u0001\u0004\t\u0015!\u0001=\u0011\u0007\t+u)D\u0001D\u0015\t!%$\u0001\u0004mS:\fGnZ\u0005\u0003\r\u000e\u00131\u0002R3og\u00164Vm\u0019;peB\u0011q\u0004S\u0005\u0003\u0013\u0002\u0012a\u0001R8vE2,\u0007bB&\u0003!\u0003\u0005\raR\u0001\u0004e\"|\u0017A\u00049s_b$C-\u001a4bk2$HEM\u000b\u0002\u001d*\u0012qiT\u0016\u0002!B\u0011\u0011KV\u0007\u0002%*\u00111\u000bV\u0001\nk:\u001c\u0007.Z2lK\u0012T!!\u0016\u0011\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002X%\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\t\r|\u0007/_\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003m\u0003\"\u0001X1\u000e\u0003uS!AX0\u0002\t1\fgn\u001a\u0006\u0002A\u0006!!.\u0019<b\u0013\t\u0011WL\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002KB\u0011qDZ\u0005\u0003O\u0002\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"A[7\u0011\u0005}Y\u0017B\u00017!\u0005\r\te.\u001f\u0005\b]\u001e\t\t\u00111\u0001f\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\t\u0011\u000fE\u0002sk*l\u0011a\u001d\u0006\u0003i\u0002\n!bY8mY\u0016\u001cG/[8o\u0013\t18O\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGCA=}!\ty\"0\u0003\u0002|A\t9!i\\8mK\u0006t\u0007b\u00028\n\u0003\u0003\u0005\rA[\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0002\\\u007f\"9aNCA\u0001\u0002\u0004)\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003\u0015\f\u0001\u0002^8TiJLgn\u001a\u000b\u00027\u00061Q-];bYN$2!_A\u0007\u0011\u001dqW\"!AA\u0002)\fq\u0002\u0015:pU\u0016\u001cG/\u00133f]RLG/\u001f\t\u0003K=\u0019RaDA\u000b\u0003C\u0001R!a\u0006\u0002\u001eej!!!\u0007\u000b\u0007\u0005m\u0001%A\u0004sk:$\u0018.\\3\n\t\u0005}\u0011\u0011\u0004\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0004\u0004\u0003BA\u0012\u0003Si!!!\n\u000b\u0007\u0005\u001dr,\u0001\u0002j_&\u0019Q'!\n\u0015\u0005\u0005E\u0011!B1qa2L\u0018aB;oCB\u0004H.\u001f\u000b\u0004s\u0006M\u0002\u0002CA\u001b'\u0005\u0005\t\u0019A\u001d\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002<A\u0019A,!\u0010\n\u0007\u0005}RL\u0001\u0004PE*,7\r\u001e"
)
public class ProjectIdentity implements Proximal, Product, Serializable {
   public static boolean unapply(final ProjectIdentity x$0) {
      return ProjectIdentity$.MODULE$.unapply(x$0);
   }

   public static ProjectIdentity apply() {
      return ProjectIdentity$.MODULE$.apply();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double valueAt(final DenseVector x) {
      return Proximal.valueAt$(this, x);
   }

   public void prox(final DenseVector x, final double rho) {
   }

   public double prox$default$2() {
      return (double)1.0F;
   }

   public ProjectIdentity copy() {
      return new ProjectIdentity();
   }

   public String productPrefix() {
      return "ProjectIdentity";
   }

   public int productArity() {
      return 0;
   }

   public Object productElement(final int x$1) {
      Object var2 = Statics.ioobe(x$1);
      return var2;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ProjectIdentity;
   }

   public String productElementName(final int x$1) {
      String var2 = (String)Statics.ioobe(x$1);
      return var2;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var2;
      if (x$1 instanceof ProjectIdentity) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2 && ((ProjectIdentity)x$1).canEqual(this);
   }

   public ProjectIdentity() {
      Proximal.$init$(this);
      Product.$init$(this);
   }
}
