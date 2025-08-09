package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005d!B\r\u001b\u0001r\u0011\u0003\u0002C\u001f\u0001\u0005+\u0007I\u0011\t \t\u0011\u001d\u0003!\u0011#Q\u0001\n}B\u0001\u0002\u0013\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u0013\u0002\u0011\t\u0012)A\u0005\u007f!)!\n\u0001C\u0001\u0017\")q\n\u0001C!!\"9\u0011\u000bAA\u0001\n\u0003\u0011\u0006bB+\u0001#\u0003%\tA\u0016\u0005\bC\u0002\t\n\u0011\"\u0001W\u0011\u001d\u0011\u0007!!A\u0005B\rDqa\u001b\u0001\u0002\u0002\u0013\u0005A\u000eC\u0004q\u0001\u0005\u0005I\u0011A9\t\u000f]\u0004\u0011\u0011!C!q\"Aq\u0010AA\u0001\n\u0003\t\t\u0001C\u0005\u0002\f\u0001\t\t\u0011\"\u0011\u0002\u000e!I\u0011\u0011\u0003\u0001\u0002\u0002\u0013\u0005\u00131\u0003\u0005\n\u0003+\u0001\u0011\u0011!C!\u0003/9!\"a\u0007\u001b\u0003\u0003E\t\u0001HA\u000f\r%I\"$!A\t\u0002q\ty\u0002\u0003\u0004K'\u0011\u0005\u0011q\u0007\u0005\t\u001fN\t\t\u0011\"\u0012\u0002:!I\u00111H\n\u0002\u0002\u0013\u0005\u0015Q\b\u0005\n\u0003\u0007\u001a\u0012\u0011!CA\u0003\u000bB\u0011\"a\u0016\u0014\u0003\u0003%I!!\u0017\u00033\u0015CXmY;u_J\u001c\u0015m\u00195f)\u0006\u001c8\u000eT8dCRLwN\u001c\u0006\u00037q\t\u0011b]2iK\u0012,H.\u001a:\u000b\u0005uq\u0012!B:qCJ\\'BA\u0010!\u0003\u0019\t\u0007/Y2iK*\t\u0011%A\u0002pe\u001e\u001cR\u0001A\u0012*[A\u0002\"\u0001J\u0014\u000e\u0003\u0015R\u0011AJ\u0001\u0006g\u000e\fG.Y\u0005\u0003Q\u0015\u0012a!\u00118z%\u00164\u0007C\u0001\u0016,\u001b\u0005Q\u0012B\u0001\u0017\u001b\u00051!\u0016m]6M_\u000e\fG/[8o!\t!c&\u0003\u00020K\t9\u0001K]8ek\u000e$\bCA\u0019;\u001d\t\u0011\u0004H\u0004\u00024o5\tAG\u0003\u00026m\u00051AH]8piz\u001a\u0001!C\u0001'\u0013\tIT%A\u0004qC\u000e\\\u0017mZ3\n\u0005mb$\u0001D*fe&\fG.\u001b>bE2,'BA\u001d&\u0003\u0011Awn\u001d;\u0016\u0003}\u0002\"\u0001\u0011#\u000f\u0005\u0005\u0013\u0005CA\u001a&\u0013\t\u0019U%\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u000b\u001a\u0013aa\u0015;sS:<'BA\"&\u0003\u0015Awn\u001d;!\u0003))\u00070Z2vi>\u0014\u0018\nZ\u0001\fKb,7-\u001e;pe&#\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0004\u00196s\u0005C\u0001\u0016\u0001\u0011\u0015iT\u00011\u0001@\u0011\u0015AU\u00011\u0001@\u0003!!xn\u0015;sS:<G#A \u0002\t\r|\u0007/\u001f\u000b\u0004\u0019N#\u0006bB\u001f\b!\u0003\u0005\ra\u0010\u0005\b\u0011\u001e\u0001\n\u00111\u0001@\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012a\u0016\u0016\u0003\u007fa[\u0013!\u0017\t\u00035~k\u0011a\u0017\u0006\u00039v\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005y+\u0013AC1o]>$\u0018\r^5p]&\u0011\u0001m\u0017\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003\u0011\u0004\"!\u001a6\u000e\u0003\u0019T!a\u001a5\u0002\t1\fgn\u001a\u0006\u0002S\u0006!!.\u0019<b\u0013\t)e-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001n!\t!c.\u0003\u0002pK\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011!/\u001e\t\u0003IML!\u0001^\u0013\u0003\u0007\u0005s\u0017\u0010C\u0004w\u0019\u0005\u0005\t\u0019A7\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u0005I\bc\u0001>~e6\t1P\u0003\u0002}K\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005y\\(\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\u0001\u0002\nA\u0019A%!\u0002\n\u0007\u0005\u001dQEA\u0004C_>dW-\u00198\t\u000fYt\u0011\u0011!a\u0001e\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\r!\u0017q\u0002\u0005\bm>\t\t\u00111\u0001n\u0003!A\u0017m\u001d5D_\u0012,G#A7\u0002\r\u0015\fX/\u00197t)\u0011\t\u0019!!\u0007\t\u000fY\f\u0012\u0011!a\u0001e\u0006IR\t_3dkR|'oQ1dQ\u0016$\u0016m]6M_\u000e\fG/[8o!\tQ3cE\u0003\u0014\u0003C\ti\u0003E\u0004\u0002$\u0005%rh\u0010'\u000e\u0005\u0005\u0015\"bAA\u0014K\u00059!/\u001e8uS6,\u0017\u0002BA\u0016\u0003K\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c83!\u0011\ty#!\u000e\u000e\u0005\u0005E\"bAA\u001aQ\u0006\u0011\u0011n\\\u0005\u0004w\u0005EBCAA\u000f)\u0005!\u0017!B1qa2LH#\u0002'\u0002@\u0005\u0005\u0003\"B\u001f\u0017\u0001\u0004y\u0004\"\u0002%\u0017\u0001\u0004y\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003\u000f\n\u0019\u0006E\u0003%\u0003\u0013\ni%C\u0002\u0002L\u0015\u0012aa\u00149uS>t\u0007#\u0002\u0013\u0002P}z\u0014bAA)K\t1A+\u001e9mKJB\u0001\"!\u0016\u0018\u0003\u0003\u0005\r\u0001T\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA.!\r)\u0017QL\u0005\u0004\u0003?2'AB(cU\u0016\u001cG\u000f"
)
public class ExecutorCacheTaskLocation implements TaskLocation, Product, Serializable {
   private final String host;
   private final String executorId;

   public static Option unapply(final ExecutorCacheTaskLocation x$0) {
      return ExecutorCacheTaskLocation$.MODULE$.unapply(x$0);
   }

   public static ExecutorCacheTaskLocation apply(final String host, final String executorId) {
      return ExecutorCacheTaskLocation$.MODULE$.apply(host, executorId);
   }

   public static Function1 tupled() {
      return ExecutorCacheTaskLocation$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ExecutorCacheTaskLocation$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String host() {
      return this.host;
   }

   public String executorId() {
      return this.executorId;
   }

   public String toString() {
      String var10000 = TaskLocation$.MODULE$.executorLocationTag();
      return var10000 + this.host() + "_" + this.executorId();
   }

   public ExecutorCacheTaskLocation copy(final String host, final String executorId) {
      return new ExecutorCacheTaskLocation(host, executorId);
   }

   public String copy$default$1() {
      return this.host();
   }

   public String copy$default$2() {
      return this.executorId();
   }

   public String productPrefix() {
      return "ExecutorCacheTaskLocation";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.host();
         }
         case 1 -> {
            return this.executorId();
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
      return x$1 instanceof ExecutorCacheTaskLocation;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "host";
         }
         case 1 -> {
            return "executorId";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof ExecutorCacheTaskLocation) {
               label48: {
                  ExecutorCacheTaskLocation var4 = (ExecutorCacheTaskLocation)x$1;
                  String var10000 = this.host();
                  String var5 = var4.host();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  var10000 = this.executorId();
                  String var6 = var4.executorId();
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var6)) {
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

   public ExecutorCacheTaskLocation(final String host, final String executorId) {
      this.host = host;
      this.executorId = executorId;
      Product.$init$(this);
   }
}
