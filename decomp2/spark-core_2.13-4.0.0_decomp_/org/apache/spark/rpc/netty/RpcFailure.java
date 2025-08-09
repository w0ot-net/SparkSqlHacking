package org.apache.spark.rpc.netty;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ub!\u0002\f\u0018\u0001^\t\u0003\u0002\u0003\u001d\u0001\u0005+\u0007I\u0011A\u001d\t\u0011u\u0002!\u0011#Q\u0001\niBQA\u0010\u0001\u0005\u0002}Bqa\u0011\u0001\u0002\u0002\u0013\u0005A\tC\u0004G\u0001E\u0005I\u0011A$\t\u000fI\u0003\u0011\u0011!C!'\"9A\fAA\u0001\n\u0003i\u0006bB1\u0001\u0003\u0003%\tA\u0019\u0005\bQ\u0002\t\t\u0011\"\u0011j\u0011\u001d\u0001\b!!A\u0005\u0002EDqA\u001e\u0001\u0002\u0002\u0013\u0005s\u000fC\u0004z\u0001\u0005\u0005I\u0011\t>\t\u000fm\u0004\u0011\u0011!C!y\"9Q\u0010AA\u0001\n\u0003rxACA\u0001/\u0005\u0005\t\u0012A\f\u0002\u0004\u0019IacFA\u0001\u0012\u00039\u0012Q\u0001\u0005\u0007}A!\t!!\b\t\u000fm\u0004\u0012\u0011!C#y\"I\u0011q\u0004\t\u0002\u0002\u0013\u0005\u0015\u0011\u0005\u0005\n\u0003K\u0001\u0012\u0011!CA\u0003OA\u0011\"a\r\u0011\u0003\u0003%I!!\u000e\u0003\u0015I\u00038MR1jYV\u0014XM\u0003\u0002\u00193\u0005)a.\u001a;us*\u0011!dG\u0001\u0004eB\u001c'B\u0001\u000f\u001e\u0003\u0015\u0019\b/\u0019:l\u0015\tqr$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002A\u0005\u0019qN]4\u0014\t\u0001\u0011\u0003f\u000b\t\u0003G\u0019j\u0011\u0001\n\u0006\u0002K\u0005)1oY1mC&\u0011q\u0005\n\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\rJ\u0013B\u0001\u0016%\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001L\u001b\u000f\u00055\u001adB\u0001\u00183\u001b\u0005y#B\u0001\u00192\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\u0013\n\u0005Q\"\u0013a\u00029bG.\fw-Z\u0005\u0003m]\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001\u000e\u0013\u0002\u0003\u0015,\u0012A\u000f\t\u0003YmJ!\u0001P\u001c\u0003\u0013QC'o\\<bE2,\u0017AA3!\u0003\u0019a\u0014N\\5u}Q\u0011\u0001I\u0011\t\u0003\u0003\u0002i\u0011a\u0006\u0005\u0006q\r\u0001\rAO\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002A\u000b\"9\u0001\b\u0002I\u0001\u0002\u0004Q\u0014AD2paf$C-\u001a4bk2$H%M\u000b\u0002\u0011*\u0012!(S\u0016\u0002\u0015B\u00111\nU\u0007\u0002\u0019*\u0011QJT\u0001\nk:\u001c\u0007.Z2lK\u0012T!a\u0014\u0013\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002R\u0019\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005!\u0006CA+[\u001b\u00051&BA,Y\u0003\u0011a\u0017M\\4\u000b\u0003e\u000bAA[1wC&\u00111L\u0016\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003y\u0003\"aI0\n\u0005\u0001$#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HCA2g!\t\u0019C-\u0003\u0002fI\t\u0019\u0011I\\=\t\u000f\u001dD\u0011\u0011!a\u0001=\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012A\u001b\t\u0004W:\u001cW\"\u00017\u000b\u00055$\u0013AC2pY2,7\r^5p]&\u0011q\u000e\u001c\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0002skB\u00111e]\u0005\u0003i\u0012\u0012qAQ8pY\u0016\fg\u000eC\u0004h\u0015\u0005\u0005\t\u0019A2\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0003)bDqaZ\u0006\u0002\u0002\u0003\u0007a,\u0001\u0005iCND7i\u001c3f)\u0005q\u0016\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003Q\u000ba!Z9vC2\u001cHC\u0001:\u0000\u0011\u001d9g\"!AA\u0002\r\f!B\u00159d\r\u0006LG.\u001e:f!\t\t\u0005cE\u0003\u0011\u0003\u000f\t\u0019\u0002\u0005\u0004\u0002\n\u0005=!\bQ\u0007\u0003\u0003\u0017Q1!!\u0004%\u0003\u001d\u0011XO\u001c;j[\u0016LA!!\u0005\u0002\f\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\u0011\t\u0005U\u00111D\u0007\u0003\u0003/Q1!!\u0007Y\u0003\tIw.C\u00027\u0003/!\"!a\u0001\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007\u0001\u000b\u0019\u0003C\u00039'\u0001\u0007!(A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005%\u0012q\u0006\t\u0005G\u0005-\"(C\u0002\u0002.\u0011\u0012aa\u00149uS>t\u0007\u0002CA\u0019)\u0005\u0005\t\u0019\u0001!\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u00028A\u0019Q+!\u000f\n\u0007\u0005mbK\u0001\u0004PE*,7\r\u001e"
)
public class RpcFailure implements Product, Serializable {
   private final Throwable e;

   public static Option unapply(final RpcFailure x$0) {
      return RpcFailure$.MODULE$.unapply(x$0);
   }

   public static RpcFailure apply(final Throwable e) {
      return RpcFailure$.MODULE$.apply(e);
   }

   public static Function1 andThen(final Function1 g) {
      return RpcFailure$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return RpcFailure$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Throwable e() {
      return this.e;
   }

   public RpcFailure copy(final Throwable e) {
      return new RpcFailure(e);
   }

   public Throwable copy$default$1() {
      return this.e();
   }

   public String productPrefix() {
      return "RpcFailure";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.e();
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
      return x$1 instanceof RpcFailure;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "e";
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
            if (x$1 instanceof RpcFailure) {
               label40: {
                  RpcFailure var4 = (RpcFailure)x$1;
                  Throwable var10000 = this.e();
                  Throwable var5 = var4.e();
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

   public RpcFailure(final Throwable e) {
      this.e = e;
      Product.$init$(this);
   }
}
