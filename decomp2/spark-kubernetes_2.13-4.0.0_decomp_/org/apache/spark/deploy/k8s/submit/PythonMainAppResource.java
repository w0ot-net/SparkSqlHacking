package org.apache.spark.deploy.k8s.submit;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.annotation.Stable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@Stable
@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ud\u0001\u0002\f\u0018\u0001\u0012B\u0001\"\u0011\u0001\u0003\u0016\u0004%\tA\u0011\u0005\t\u0017\u0002\u0011\t\u0012)A\u0005\u0007\")A\n\u0001C\u0001\u001b\"9\u0001\u000bAA\u0001\n\u0003\t\u0006bB*\u0001#\u0003%\t\u0001\u0016\u0005\b?\u0002\t\t\u0011\"\u0011a\u0011\u001dA\u0007!!A\u0005\u0002%Dq!\u001c\u0001\u0002\u0002\u0013\u0005a\u000eC\u0004u\u0001\u0005\u0005I\u0011I;\t\u000fq\u0004\u0011\u0011!C\u0001{\"I\u0011Q\u0001\u0001\u0002\u0002\u0013\u0005\u0013q\u0001\u0005\n\u0003\u0017\u0001\u0011\u0011!C!\u0003\u001bA\u0011\"a\u0004\u0001\u0003\u0003%\t%!\u0005\t\u0013\u0005M\u0001!!A\u0005B\u0005Uq!CA\u001d/\u0005\u0005\t\u0012AA\u001e\r!1r#!A\t\u0002\u0005u\u0002B\u0002'\u0011\t\u0003\t)\u0006C\u0005\u0002\u0010A\t\t\u0011\"\u0012\u0002\u0012!I\u0011q\u000b\t\u0002\u0002\u0013\u0005\u0015\u0011\f\u0005\n\u0003;\u0002\u0012\u0011!CA\u0003?B\u0011\"a\u001b\u0011\u0003\u0003%I!!\u001c\u0003+AKH\u000f[8o\u001b\u0006Lg.\u00119q%\u0016\u001cx.\u001e:dK*\u0011\u0001$G\u0001\u0007gV\u0014W.\u001b;\u000b\u0005iY\u0012aA69g*\u0011A$H\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005yy\u0012!B:qCJ\\'B\u0001\u0011\"\u0003\u0019\t\u0007/Y2iK*\t!%A\u0002pe\u001e\u001c\u0001a\u0005\u0004\u0001K-z#'\u000e\t\u0003M%j\u0011a\n\u0006\u0002Q\u0005)1oY1mC&\u0011!f\n\u0002\u0007\u0003:L(+\u001a4\u0011\u00051jS\"A\f\n\u00059:\"aD'bS:\f\u0005\u000f\u001d*fg>,(oY3\u0011\u00051\u0002\u0014BA\u0019\u0018\u00059quN\u001c&W\u001bJ+7o\\;sG\u0016\u0004\"AJ\u001a\n\u0005Q:#a\u0002)s_\u0012,8\r\u001e\t\u0003myr!a\u000e\u001f\u000f\u0005aZT\"A\u001d\u000b\u0005i\u001a\u0013A\u0002\u001fs_>$h(C\u0001)\u0013\tit%A\u0004qC\u000e\\\u0017mZ3\n\u0005}\u0002%\u0001D*fe&\fG.\u001b>bE2,'BA\u001f(\u0003=\u0001(/[7bef\u0014Vm]8ve\u000e,W#A\"\u0011\u0005\u0011CeBA#G!\tAt%\u0003\u0002HO\u00051\u0001K]3eK\u001aL!!\u0013&\u0003\rM#(/\u001b8h\u0015\t9u%\u0001\tqe&l\u0017M]=SKN|WO]2fA\u00051A(\u001b8jiz\"\"AT(\u0011\u00051\u0002\u0001\"B!\u0004\u0001\u0004\u0019\u0015\u0001B2paf$\"A\u0014*\t\u000f\u0005#\u0001\u0013!a\u0001\u0007\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#A++\u0005\r36&A,\u0011\u0005akV\"A-\u000b\u0005i[\u0016!C;oG\",7m[3e\u0015\tav%\u0001\u0006b]:|G/\u0019;j_:L!AX-\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002CB\u0011!mZ\u0007\u0002G*\u0011A-Z\u0001\u0005Y\u0006twMC\u0001g\u0003\u0011Q\u0017M^1\n\u0005%\u001b\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#\u00016\u0011\u0005\u0019Z\u0017B\u00017(\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\ty'\u000f\u0005\u0002'a&\u0011\u0011o\n\u0002\u0004\u0003:L\bbB:\t\u0003\u0003\u0005\rA[\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0003Y\u00042a\u001e>p\u001b\u0005A(BA=(\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003wb\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u0019a0a\u0001\u0011\u0005\u0019z\u0018bAA\u0001O\t9!i\\8mK\u0006t\u0007bB:\u000b\u0003\u0003\u0005\ra\\\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002b\u0003\u0013Aqa]\u0006\u0002\u0002\u0003\u0007!.\u0001\u0005iCND7i\u001c3f)\u0005Q\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003\u0005\fa!Z9vC2\u001cHc\u0001@\u0002\u0018!91ODA\u0001\u0002\u0004y\u0007f\u0001\u0001\u0002\u001cA!\u0011QDA\u0011\u001b\t\tyB\u0003\u0002];%!\u00111EA\u0010\u0005\u0019\u0019F/\u00192mK\"\u001a\u0001!a\n\u0011\t\u0005u\u0011\u0011F\u0005\u0005\u0003W\tyB\u0001\u0007EKZ,Gn\u001c9fe\u0006\u0003\u0018\u000eK\u0003\u0001\u0003_\t)\u0004\u0005\u0003\u0002\u001e\u0005E\u0012\u0002BA\u001a\u0003?\u0011QaU5oG\u0016\f#!a\u000e\u0002\u000bIrCG\f\u0019\u0002+AKH\u000f[8o\u001b\u0006Lg.\u00119q%\u0016\u001cx.\u001e:dKB\u0011A\u0006E\n\u0006!\u0005}\u00121\n\t\u0007\u0003\u0003\n9e\u0011(\u000e\u0005\u0005\r#bAA#O\u00059!/\u001e8uS6,\u0017\u0002BA%\u0003\u0007\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c82!\u0011\ti%a\u0015\u000e\u0005\u0005=#bAA)K\u0006\u0011\u0011n\\\u0005\u0004\u007f\u0005=CCAA\u001e\u0003\u0015\t\u0007\u000f\u001d7z)\rq\u00151\f\u0005\u0006\u0003N\u0001\raQ\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t\t'a\u001a\u0011\t\u0019\n\u0019gQ\u0005\u0004\u0003K:#AB(qi&|g\u000e\u0003\u0005\u0002jQ\t\t\u00111\u0001O\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003_\u00022AYA9\u0013\r\t\u0019h\u0019\u0002\u0007\u001f\nTWm\u0019;"
)
public class PythonMainAppResource implements MainAppResource, NonJVMResource, Product, Serializable {
   private final String primaryResource;

   public static Option unapply(final PythonMainAppResource x$0) {
      return PythonMainAppResource$.MODULE$.unapply(x$0);
   }

   public static PythonMainAppResource apply(final String primaryResource) {
      return PythonMainAppResource$.MODULE$.apply(primaryResource);
   }

   public static Function1 andThen(final Function1 g) {
      return PythonMainAppResource$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return PythonMainAppResource$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String primaryResource() {
      return this.primaryResource;
   }

   public PythonMainAppResource copy(final String primaryResource) {
      return new PythonMainAppResource(primaryResource);
   }

   public String copy$default$1() {
      return this.primaryResource();
   }

   public String productPrefix() {
      return "PythonMainAppResource";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.primaryResource();
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
      return x$1 instanceof PythonMainAppResource;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "primaryResource";
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
            if (x$1 instanceof PythonMainAppResource) {
               label40: {
                  PythonMainAppResource var4 = (PythonMainAppResource)x$1;
                  String var10000 = this.primaryResource();
                  String var5 = var4.primaryResource();
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

   public PythonMainAppResource(final String primaryResource) {
      this.primaryResource = primaryResource;
      Product.$init$(this);
   }
}
