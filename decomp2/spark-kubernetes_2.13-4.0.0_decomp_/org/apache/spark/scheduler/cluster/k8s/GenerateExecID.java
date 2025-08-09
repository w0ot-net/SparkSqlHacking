package org.apache.spark.scheduler.cluster.k8s;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005eb\u0001\u0002\f\u0018\u0001\u0012B\u0001B\u000e\u0001\u0003\u0016\u0004%\ta\u000e\u0005\t\u0007\u0002\u0011\t\u0012)A\u0005q!)A\t\u0001C\u0001\u000b\"9\u0011\nAA\u0001\n\u0003Q\u0005b\u0002'\u0001#\u0003%\t!\u0014\u0005\b1\u0002\t\t\u0011\"\u0011Z\u0011\u001dy\u0006!!A\u0005\u0002\u0001Dq\u0001\u001a\u0001\u0002\u0002\u0013\u0005Q\rC\u0004l\u0001\u0005\u0005I\u0011\t7\t\u000fM\u0004\u0011\u0011!C\u0001i\"9\u0011\u0010AA\u0001\n\u0003R\bb\u0002?\u0001\u0003\u0003%\t% \u0005\b}\u0002\t\t\u0011\"\u0011\u0000\u0011%\t\t\u0001AA\u0001\n\u0003\n\u0019aB\u0005\u0002\b]\t\t\u0011#\u0001\u0002\n\u0019AacFA\u0001\u0012\u0003\tY\u0001\u0003\u0004E!\u0011\u0005\u0011\u0011\u0004\u0005\b}B\t\t\u0011\"\u0012\u0000\u0011%\tY\u0002EA\u0001\n\u0003\u000bi\u0002C\u0005\u0002\"A\t\t\u0011\"!\u0002$!I\u0011q\u0006\t\u0002\u0002\u0013%\u0011\u0011\u0007\u0002\u000f\u000f\u0016tWM]1uK\u0016CXmY%E\u0015\tA\u0012$A\u0002lqMT!AG\u000e\u0002\u000f\rdWo\u001d;fe*\u0011A$H\u0001\ng\u000eDW\rZ;mKJT!AH\u0010\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u0001\n\u0013AB1qC\u000eDWMC\u0001#\u0003\ry'oZ\u0002\u0001'\u0011\u0001QeK\u001a\u0011\u0005\u0019JS\"A\u0014\u000b\u0003!\nQa]2bY\u0006L!AK\u0014\u0003\r\u0005s\u0017PU3g!\ta\u0013'D\u0001.\u0015\tqs&\u0001\u0002j_*\t\u0001'\u0001\u0003kCZ\f\u0017B\u0001\u001a.\u00051\u0019VM]5bY&T\u0018M\u00197f!\t1C'\u0003\u00026O\t9\u0001K]8ek\u000e$\u0018a\u00029pI:\u000bW.Z\u000b\u0002qA\u0011\u0011\b\u0011\b\u0003uy\u0002\"aO\u0014\u000e\u0003qR!!P\u0012\u0002\rq\u0012xn\u001c;?\u0013\tyt%\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u0003\n\u0013aa\u0015;sS:<'BA (\u0003!\u0001x\u000e\u001a(b[\u0016\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002G\u0011B\u0011q\tA\u0007\u0002/!)ag\u0001a\u0001q\u0005!1m\u001c9z)\t15\nC\u00047\tA\u0005\t\u0019\u0001\u001d\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\taJ\u000b\u00029\u001f.\n\u0001\u000b\u0005\u0002R-6\t!K\u0003\u0002T)\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003+\u001e\n!\"\u00198o_R\fG/[8o\u0013\t9&KA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u0001.\u0011\u0005msV\"\u0001/\u000b\u0005u{\u0013\u0001\u00027b]\u001eL!!\u0011/\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003\u0005\u0004\"A\n2\n\u0005\r<#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HC\u00014j!\t1s-\u0003\u0002iO\t\u0019\u0011I\\=\t\u000f)D\u0011\u0011!a\u0001C\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012!\u001c\t\u0004]F4W\"A8\u000b\u0005A<\u0013AC2pY2,7\r^5p]&\u0011!o\u001c\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0002vqB\u0011aE^\u0005\u0003o\u001e\u0012qAQ8pY\u0016\fg\u000eC\u0004k\u0015\u0005\u0005\t\u0019\u00014\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u00035nDqA[\u0006\u0002\u0002\u0003\u0007\u0011-\u0001\u0005iCND7i\u001c3f)\u0005\t\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003i\u000ba!Z9vC2\u001cHcA;\u0002\u0006!9!NDA\u0001\u0002\u00041\u0017AD$f]\u0016\u0014\u0018\r^3Fq\u0016\u001c\u0017\n\u0012\t\u0003\u000fB\u0019B\u0001EA\u0007WA1\u0011qBA\u000bq\u0019k!!!\u0005\u000b\u0007\u0005Mq%A\u0004sk:$\u0018.\\3\n\t\u0005]\u0011\u0011\u0003\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\fDCAA\u0005\u0003\u0015\t\u0007\u000f\u001d7z)\r1\u0015q\u0004\u0005\u0006mM\u0001\r\u0001O\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t)#a\u000b\u0011\t\u0019\n9\u0003O\u0005\u0004\u0003S9#AB(qi&|g\u000e\u0003\u0005\u0002.Q\t\t\u00111\u0001G\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003g\u00012aWA\u001b\u0013\r\t9\u0004\u0018\u0002\u0007\u001f\nTWm\u0019;"
)
public class GenerateExecID implements Serializable, Product {
   private final String podName;

   public static Option unapply(final GenerateExecID x$0) {
      return GenerateExecID$.MODULE$.unapply(x$0);
   }

   public static GenerateExecID apply(final String podName) {
      return GenerateExecID$.MODULE$.apply(podName);
   }

   public static Function1 andThen(final Function1 g) {
      return GenerateExecID$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return GenerateExecID$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String podName() {
      return this.podName;
   }

   public GenerateExecID copy(final String podName) {
      return new GenerateExecID(podName);
   }

   public String copy$default$1() {
      return this.podName();
   }

   public String productPrefix() {
      return "GenerateExecID";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.podName();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof GenerateExecID;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "podName";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof GenerateExecID) {
               label40: {
                  GenerateExecID var4 = (GenerateExecID)x$1;
                  String var10000 = this.podName();
                  String var5 = var4.podName();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label40;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label40;
                  }

                  if (var4.canEqual(this)) {
                     break label47;
                  }
               }
            }

            var6 = false;
            return var6;
         }
      }

      var6 = true;
      return var6;
   }

   public GenerateExecID(final String podName) {
      this.podName = podName;
      Product.$init$(this);
   }
}
