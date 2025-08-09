package org.apache.spark.rpc.netty;

import java.io.Serializable;
import org.apache.spark.rpc.RpcAddress;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%d!B\r\u001b\u0001j!\u0003\u0002C \u0001\u0005+\u0007I\u0011\u0001!\t\u0011\u0011\u0003!\u0011#Q\u0001\n\u0005C\u0001\"\u0012\u0001\u0003\u0016\u0004%\tA\u0012\u0005\t\u0017\u0002\u0011\t\u0012)A\u0005\u000f\")A\n\u0001C\u0001\u001b\"9\u0011\u000bAA\u0001\n\u0003\u0011\u0006bB+\u0001#\u0003%\tA\u0016\u0005\bC\u0002\t\n\u0011\"\u0001c\u0011\u001d!\u0007!!A\u0005B\u0015DqA\u001c\u0001\u0002\u0002\u0013\u0005q\u000eC\u0004t\u0001\u0005\u0005I\u0011\u0001;\t\u000fi\u0004\u0011\u0011!C!w\"I\u0011Q\u0001\u0001\u0002\u0002\u0013\u0005\u0011q\u0001\u0005\n\u0003#\u0001\u0011\u0011!C!\u0003'A\u0011\"a\u0006\u0001\u0003\u0003%\t%!\u0007\t\u0013\u0005m\u0001!!A\u0005B\u0005u\u0001\"CA\u0010\u0001\u0005\u0005I\u0011IA\u0011\u000f)\t)CGA\u0001\u0012\u0003Q\u0012q\u0005\u0004\n3i\t\t\u0011#\u0001\u001b\u0003SAa\u0001T\n\u0005\u0002\u0005\u0005\u0003\"CA\u000e'\u0005\u0005IQIA\u000f\u0011%\t\u0019eEA\u0001\n\u0003\u000b)\u0005C\u0005\u0002LM\t\t\u0011\"!\u0002N!I\u0011qL\n\u0002\u0002\u0013%\u0011\u0011\r\u0002\u001d%\u0016lw\u000e^3Qe>\u001cWm]:D_:tWm\u0019;j_:,%O]8s\u0015\tYB$A\u0003oKR$\u0018P\u0003\u0002\u001e=\u0005\u0019!\u000f]2\u000b\u0005}\u0001\u0013!B:qCJ\\'BA\u0011#\u0003\u0019\t\u0007/Y2iK*\t1%A\u0002pe\u001e\u001cR\u0001A\u0013,_I\u0002\"AJ\u0015\u000e\u0003\u001dR\u0011\u0001K\u0001\u0006g\u000e\fG.Y\u0005\u0003U\u001d\u0012a!\u00118z%\u00164\u0007C\u0001\u0017.\u001b\u0005Q\u0012B\u0001\u0018\u001b\u00051IeNY8y\u001b\u0016\u001c8/Y4f!\t1\u0003'\u0003\u00022O\t9\u0001K]8ek\u000e$\bCA\u001a=\u001d\t!$H\u0004\u00026s5\taG\u0003\u00028q\u00051AH]8piz\u001a\u0001!C\u0001)\u0013\tYt%A\u0004qC\u000e\\\u0017mZ3\n\u0005ur$\u0001D*fe&\fG.\u001b>bE2,'BA\u001e(\u0003\u0015\u0019\u0017-^:f+\u0005\t\u0005CA\u001aC\u0013\t\u0019eHA\u0005UQJ|w/\u00192mK\u000611-Y;tK\u0002\nQB]3n_R,\u0017\t\u001a3sKN\u001cX#A$\u0011\u0005!KU\"\u0001\u000f\n\u0005)c\"A\u0003*qG\u0006#GM]3tg\u0006q!/Z7pi\u0016\fE\r\u001a:fgN\u0004\u0013A\u0002\u001fj]&$h\bF\u0002O\u001fB\u0003\"\u0001\f\u0001\t\u000b}*\u0001\u0019A!\t\u000b\u0015+\u0001\u0019A$\u0002\t\r|\u0007/\u001f\u000b\u0004\u001dN#\u0006bB \u0007!\u0003\u0005\r!\u0011\u0005\b\u000b\u001a\u0001\n\u00111\u0001H\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012a\u0016\u0016\u0003\u0003b[\u0013!\u0017\t\u00035~k\u0011a\u0017\u0006\u00039v\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005y;\u0013AC1o]>$\u0018\r^5p]&\u0011\u0001m\u0017\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0002G*\u0012q\tW\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003\u0019\u0004\"a\u001a7\u000e\u0003!T!!\u001b6\u0002\t1\fgn\u001a\u0006\u0002W\u0006!!.\u0019<b\u0013\ti\u0007N\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002aB\u0011a%]\u0005\u0003e\u001e\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"!\u001e=\u0011\u0005\u00192\u0018BA<(\u0005\r\te.\u001f\u0005\bs.\t\t\u00111\u0001q\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\tA\u0010\u0005\u0003~\u0003\u0003)X\"\u0001@\u000b\u0005}<\u0013AC2pY2,7\r^5p]&\u0019\u00111\u0001@\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003\u0013\ty\u0001E\u0002'\u0003\u0017I1!!\u0004(\u0005\u001d\u0011un\u001c7fC:Dq!_\u0007\u0002\u0002\u0003\u0007Q/\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,Gc\u00014\u0002\u0016!9\u0011PDA\u0001\u0002\u0004\u0001\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003A\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002M\u00061Q-];bYN$B!!\u0003\u0002$!9\u00110EA\u0001\u0002\u0004)\u0018\u0001\b*f[>$X\r\u0015:pG\u0016\u001c8oQ8o]\u0016\u001cG/[8o\u000bJ\u0014xN\u001d\t\u0003YM\u0019RaEA\u0016\u0003o\u0001r!!\f\u00024\u0005;e*\u0004\u0002\u00020)\u0019\u0011\u0011G\u0014\u0002\u000fI,h\u000e^5nK&!\u0011QGA\u0018\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\r\t\u0005\u0003s\ty$\u0004\u0002\u0002<)\u0019\u0011Q\b6\u0002\u0005%|\u0017bA\u001f\u0002<Q\u0011\u0011qE\u0001\u0006CB\u0004H.\u001f\u000b\u0006\u001d\u0006\u001d\u0013\u0011\n\u0005\u0006\u007fY\u0001\r!\u0011\u0005\u0006\u000bZ\u0001\raR\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\ty%a\u0017\u0011\u000b\u0019\n\t&!\u0016\n\u0007\u0005MsE\u0001\u0004PaRLwN\u001c\t\u0006M\u0005]\u0013iR\u0005\u0004\u00033:#A\u0002+va2,'\u0007\u0003\u0005\u0002^]\t\t\u00111\u0001O\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003G\u00022aZA3\u0013\r\t9\u0007\u001b\u0002\u0007\u001f\nTWm\u0019;"
)
public class RemoteProcessConnectionError implements InboxMessage, Product, Serializable {
   private final Throwable cause;
   private final RpcAddress remoteAddress;

   public static Option unapply(final RemoteProcessConnectionError x$0) {
      return RemoteProcessConnectionError$.MODULE$.unapply(x$0);
   }

   public static RemoteProcessConnectionError apply(final Throwable cause, final RpcAddress remoteAddress) {
      return RemoteProcessConnectionError$.MODULE$.apply(cause, remoteAddress);
   }

   public static Function1 tupled() {
      return RemoteProcessConnectionError$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return RemoteProcessConnectionError$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Throwable cause() {
      return this.cause;
   }

   public RpcAddress remoteAddress() {
      return this.remoteAddress;
   }

   public RemoteProcessConnectionError copy(final Throwable cause, final RpcAddress remoteAddress) {
      return new RemoteProcessConnectionError(cause, remoteAddress);
   }

   public Throwable copy$default$1() {
      return this.cause();
   }

   public RpcAddress copy$default$2() {
      return this.remoteAddress();
   }

   public String productPrefix() {
      return "RemoteProcessConnectionError";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.cause();
         }
         case 1 -> {
            return this.remoteAddress();
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
      return x$1 instanceof RemoteProcessConnectionError;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "cause";
         }
         case 1 -> {
            return "remoteAddress";
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
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof RemoteProcessConnectionError) {
               label48: {
                  RemoteProcessConnectionError var4 = (RemoteProcessConnectionError)x$1;
                  Throwable var10000 = this.cause();
                  Throwable var5 = var4.cause();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  RpcAddress var7 = this.remoteAddress();
                  RpcAddress var6 = var4.remoteAddress();
                  if (var7 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var7.equals(var6)) {
                     break label48;
                  }

                  if (var4.canEqual(this)) {
                     break label55;
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public RemoteProcessConnectionError(final Throwable cause, final RpcAddress remoteAddress) {
      this.cause = cause;
      this.remoteAddress = remoteAddress;
      Product.$init$(this);
   }
}
