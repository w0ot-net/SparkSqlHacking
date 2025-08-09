package org.apache.spark.deploy.history;

import java.io.Serializable;
import org.apache.spark.util.kvstore.KVIndex;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=g\u0001\u0002\u0012$\t:B\u0001\u0002\u0012\u0001\u0003\u0016\u0004%\t!\u0012\u0005\tC\u0002\u0011\t\u0012)A\u0005\r\"A!\r\u0001BK\u0002\u0013\u00051\r\u0003\u0005k\u0001\tE\t\u0015!\u0003e\u0011!Y\u0007A!f\u0001\n\u0003)\u0005\u0002\u00037\u0001\u0005#\u0005\u000b\u0011\u0002$\t\u00115\u0004!Q3A\u0005\u00029D\u0001B\u001d\u0001\u0003\u0012\u0003\u0006Ia\u001c\u0005\tg\u0002\u0011)\u001a!C\u0001G\"AA\u000f\u0001B\tB\u0003%A\rC\u0003v\u0001\u0011\u0005a\u000fC\u0004\u007f\u0001\u0005\u0005I\u0011A@\t\u0013\u0005-\u0001!%A\u0005\u0002\u00055\u0001\"CA\u0010\u0001E\u0005I\u0011AA\u0011\u0011%\t)\u0003AI\u0001\n\u0003\ti\u0001C\u0005\u0002(\u0001\t\n\u0011\"\u0001\u0002*!I\u0011Q\u0006\u0001\u0012\u0002\u0013\u0005\u0011\u0011\u0005\u0005\n\u0003_\u0001\u0011\u0011!C!\u0003cA\u0011\"!\u0011\u0001\u0003\u0003%\t!a\u0011\t\u0013\u0005-\u0003!!A\u0005\u0002\u00055\u0003\"CA-\u0001\u0005\u0005I\u0011IA.\u0011%\tI\u0007AA\u0001\n\u0003\tY\u0007C\u0005\u0002v\u0001\t\t\u0011\"\u0011\u0002x!I\u00111\u0010\u0001\u0002\u0002\u0013\u0005\u0013Q\u0010\u0005\n\u0003\u007f\u0002\u0011\u0011!C!\u0003\u0003C\u0011\"a!\u0001\u0003\u0003%\t%!\"\b\u0013\u0005%5%!A\t\n\u0005-e\u0001\u0003\u0012$\u0003\u0003EI!!$\t\rUdB\u0011AAS\u0011%\ty\bHA\u0001\n\u000b\n\t\tC\u0005\u0002(r\t\t\u0011\"!\u0002*\"I\u0011Q\u0017\u000f\u0002\u0002\u0013\u0005\u0015q\u0017\u0005\n\u0003\u000bd\u0012\u0011!C\u0005\u0003\u000f\u0014A#\u00119qY&\u001c\u0017\r^5p]N#xN]3J]\u001a|'B\u0001\u0013&\u0003\u001dA\u0017n\u001d;pefT!AJ\u0014\u0002\r\u0011,\u0007\u000f\\8z\u0015\tA\u0013&A\u0003ta\u0006\u00148N\u0003\u0002+W\u00051\u0011\r]1dQ\u0016T\u0011\u0001L\u0001\u0004_J<7\u0001A\n\u0005\u0001=*\u0004\b\u0005\u00021g5\t\u0011GC\u00013\u0003\u0015\u00198-\u00197b\u0013\t!\u0014G\u0001\u0004B]f\u0014VM\u001a\t\u0003aYJ!aN\u0019\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0011(\u0011\b\u0003u}r!a\u000f \u000e\u0003qR!!P\u0017\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0011\u0014B\u0001!2\u0003\u001d\u0001\u0018mY6bO\u0016L!AQ\"\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005\u0001\u000b\u0014\u0001\u00029bi\",\u0012A\u0012\t\u0003\u000f.s!\u0001S%\u0011\u0005m\n\u0014B\u0001&2\u0003\u0019\u0001&/\u001a3fM&\u0011A*\u0014\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005)\u000b\u0004FA\u0001PU\t\u0001\u0006\f\u0005\u0002R-6\t!K\u0003\u0002T)\u000691N^:u_J,'BA+(\u0003\u0011)H/\u001b7\n\u0005]\u0013&aB&W\u0013:$W\r_\u0016\u00023B\u0011!lX\u0007\u00027*\u0011A,X\u0001\u0005[\u0016$\u0018M\u0003\u0002_c\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\u0001\\&AB4fiR,'/A\u0003qCRD\u0007%\u0001\u0006mCN$\u0018iY2fgN,\u0012\u0001\u001a\t\u0003a\u0015L!AZ\u0019\u0003\t1{gn\u001a\u0015\u0005\u0007=C\u0017.A\u0003wC2,X-I\u0001c\u0003-a\u0017m\u001d;BG\u000e,7o\u001d\u0011\u0002\u000b\u0005\u0004\b/\u00133\u0002\r\u0005\u0004\b/\u00133!\u0003%\tG\u000f^3naRLE-F\u0001p!\r\u0001\u0004OR\u0005\u0003cF\u0012aa\u00149uS>t\u0017AC1ui\u0016l\u0007\u000f^%eA\u0005!1/\u001b>f\u0003\u0015\u0019\u0018N_3!\u0003\u0019a\u0014N\\5u}Q1q/\u001f>|yv\u0004\"\u0001\u001f\u0001\u000e\u0003\rBQ\u0001R\u0006A\u0002\u0019CQAY\u0006A\u0002\u0011DQa[\u0006A\u0002\u0019CQ!\\\u0006A\u0002=DQa]\u0006A\u0002\u0011\fAaY8qsRYq/!\u0001\u0002\u0004\u0005\u0015\u0011qAA\u0005\u0011\u001d!E\u0002%AA\u0002\u0019CqA\u0019\u0007\u0011\u0002\u0003\u0007A\rC\u0004l\u0019A\u0005\t\u0019\u0001$\t\u000f5d\u0001\u0013!a\u0001_\"91\u000f\u0004I\u0001\u0002\u0004!\u0017AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0003\u001fQ3ARA\tW\t\t\u0019\u0002\u0005\u0003\u0002\u0016\u0005mQBAA\f\u0015\r\tI\"X\u0001\nk:\u001c\u0007.Z2lK\u0012LA!!\b\u0002\u0018\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u00111\u0005\u0016\u0004I\u0006E\u0011AD2paf$C-\u001a4bk2$HeM\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00135+\t\tYCK\u0002p\u0003#\tabY8qs\u0012\"WMZ1vYR$S'A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003g\u0001B!!\u000e\u0002@5\u0011\u0011q\u0007\u0006\u0005\u0003s\tY$\u0001\u0003mC:<'BAA\u001f\u0003\u0011Q\u0017M^1\n\u00071\u000b9$\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002FA\u0019\u0001'a\u0012\n\u0007\u0005%\u0013GA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002P\u0005U\u0003c\u0001\u0019\u0002R%\u0019\u00111K\u0019\u0003\u0007\u0005s\u0017\u0010C\u0005\u0002XQ\t\t\u00111\u0001\u0002F\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!\u0018\u0011\r\u0005}\u0013QMA(\u001b\t\t\tGC\u0002\u0002dE\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\t9'!\u0019\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003[\n\u0019\bE\u00021\u0003_J1!!\u001d2\u0005\u001d\u0011un\u001c7fC:D\u0011\"a\u0016\u0017\u0003\u0003\u0005\r!a\u0014\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003g\tI\bC\u0005\u0002X]\t\t\u00111\u0001\u0002F\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002F\u0005AAo\\*ue&tw\r\u0006\u0002\u00024\u00051Q-];bYN$B!!\u001c\u0002\b\"I\u0011q\u000b\u000e\u0002\u0002\u0003\u0007\u0011qJ\u0001\u0015\u0003B\u0004H.[2bi&|gn\u0015;pe\u0016LeNZ8\u0011\u0005ad2#\u0002\u000f\u0002\u0010\u0006m\u0005CCAI\u0003/3EMR8eo6\u0011\u00111\u0013\u0006\u0004\u0003+\u000b\u0014a\u0002:v]RLW.Z\u0005\u0005\u00033\u000b\u0019JA\tBEN$(/Y2u\rVt7\r^5p]V\u0002B!!(\u0002$6\u0011\u0011q\u0014\u0006\u0005\u0003C\u000bY$\u0001\u0002j_&\u0019!)a(\u0015\u0005\u0005-\u0015!B1qa2LHcC<\u0002,\u00065\u0016qVAY\u0003gCQ\u0001R\u0010A\u0002\u0019CQAY\u0010A\u0002\u0011DQa[\u0010A\u0002\u0019CQ!\\\u0010A\u0002=DQa]\u0010A\u0002\u0011\fq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002:\u0006\u0005\u0007\u0003\u0002\u0019q\u0003w\u0003\u0002\u0002MA_\r\u00124u\u000eZ\u0005\u0004\u0003\u007f\u000b$A\u0002+va2,W\u0007\u0003\u0005\u0002D\u0002\n\t\u00111\u0001x\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u0013\u0004B!!\u000e\u0002L&!\u0011QZA\u001c\u0005\u0019y%M[3di\u0002"
)
public class ApplicationStoreInfo implements Product, Serializable {
   private final String path;
   private final long lastAccess;
   private final String appId;
   private final Option attemptId;
   private final long size;

   public static Option unapply(final ApplicationStoreInfo x$0) {
      return ApplicationStoreInfo$.MODULE$.unapply(x$0);
   }

   public static ApplicationStoreInfo apply(final String path, final long lastAccess, final String appId, final Option attemptId, final long size) {
      return ApplicationStoreInfo$.MODULE$.apply(path, lastAccess, appId, attemptId, size);
   }

   public static Function1 tupled() {
      return ApplicationStoreInfo$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ApplicationStoreInfo$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   @KVIndex
   public String path() {
      return this.path;
   }

   @KVIndex("lastAccess")
   public long lastAccess() {
      return this.lastAccess;
   }

   public String appId() {
      return this.appId;
   }

   public Option attemptId() {
      return this.attemptId;
   }

   public long size() {
      return this.size;
   }

   public ApplicationStoreInfo copy(final String path, final long lastAccess, final String appId, final Option attemptId, final long size) {
      return new ApplicationStoreInfo(path, lastAccess, appId, attemptId, size);
   }

   public String copy$default$1() {
      return this.path();
   }

   public long copy$default$2() {
      return this.lastAccess();
   }

   public String copy$default$3() {
      return this.appId();
   }

   public Option copy$default$4() {
      return this.attemptId();
   }

   public long copy$default$5() {
      return this.size();
   }

   public String productPrefix() {
      return "ApplicationStoreInfo";
   }

   public int productArity() {
      return 5;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.path();
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.lastAccess());
         }
         case 2 -> {
            return this.appId();
         }
         case 3 -> {
            return this.attemptId();
         }
         case 4 -> {
            return BoxesRunTime.boxToLong(this.size());
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
      return x$1 instanceof ApplicationStoreInfo;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "path";
         }
         case 1 -> {
            return "lastAccess";
         }
         case 2 -> {
            return "appId";
         }
         case 3 -> {
            return "attemptId";
         }
         case 4 -> {
            return "size";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.path()));
      var1 = Statics.mix(var1, Statics.longHash(this.lastAccess()));
      var1 = Statics.mix(var1, Statics.anyHash(this.appId()));
      var1 = Statics.mix(var1, Statics.anyHash(this.attemptId()));
      var1 = Statics.mix(var1, Statics.longHash(this.size()));
      return Statics.finalizeHash(var1, 5);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10;
      if (this != x$1) {
         label71: {
            if (x$1 instanceof ApplicationStoreInfo) {
               ApplicationStoreInfo var4 = (ApplicationStoreInfo)x$1;
               if (this.lastAccess() == var4.lastAccess() && this.size() == var4.size()) {
                  label64: {
                     String var10000 = this.path();
                     String var5 = var4.path();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label64;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label64;
                     }

                     var10000 = this.appId();
                     String var6 = var4.appId();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label64;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label64;
                     }

                     Option var9 = this.attemptId();
                     Option var7 = var4.attemptId();
                     if (var9 == null) {
                        if (var7 != null) {
                           break label64;
                        }
                     } else if (!var9.equals(var7)) {
                        break label64;
                     }

                     if (var4.canEqual(this)) {
                        break label71;
                     }
                  }
               }
            }

            var10 = false;
            return var10;
         }
      }

      var10 = true;
      return var10;
   }

   public ApplicationStoreInfo(final String path, final long lastAccess, final String appId, final Option attemptId, final long size) {
      this.path = path;
      this.lastAccess = lastAccess;
      this.appId = appId;
      this.attemptId = attemptId;
      this.size = size;
      Product.$init$(this);
   }
}
