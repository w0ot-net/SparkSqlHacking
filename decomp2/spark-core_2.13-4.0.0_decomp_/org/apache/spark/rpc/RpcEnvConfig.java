package org.apache.spark.rpc;

import java.io.Serializable;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%h!B\u0016-\u0001:\"\u0004\u0002C&\u0001\u0005+\u0007I\u0011\u0001'\t\u0011E\u0003!\u0011#Q\u0001\n5C\u0001B\u0015\u0001\u0003\u0016\u0004%\ta\u0015\u0005\t9\u0002\u0011\t\u0012)A\u0005)\"AQ\f\u0001BK\u0002\u0013\u00051\u000b\u0003\u0005_\u0001\tE\t\u0015!\u0003U\u0011!y\u0006A!f\u0001\n\u0003\u0019\u0006\u0002\u00031\u0001\u0005#\u0005\u000b\u0011\u0002+\t\u0011\u0005\u0004!Q3A\u0005\u0002\tD\u0001B\u001a\u0001\u0003\u0012\u0003\u0006Ia\u0019\u0005\tO\u0002\u0011)\u001a!C\u0001Q\"AA\u000e\u0001B\tB\u0003%\u0011\u000e\u0003\u0005n\u0001\tU\r\u0011\"\u0001c\u0011!q\u0007A!E!\u0002\u0013\u0019\u0007\u0002C8\u0001\u0005+\u0007I\u0011\u00019\t\u0011Q\u0004!\u0011#Q\u0001\nEDQ!\u001e\u0001\u0005\u0002YD\u0011\"a\u0001\u0001\u0003\u0003%\t!!\u0002\t\u0013\u0005]\u0001!%A\u0005\u0002\u0005e\u0001\"CA\u0018\u0001E\u0005I\u0011AA\u0019\u0011%\t)\u0004AI\u0001\n\u0003\t\t\u0004C\u0005\u00028\u0001\t\n\u0011\"\u0001\u00022!I\u0011\u0011\b\u0001\u0012\u0002\u0013\u0005\u00111\b\u0005\n\u0003\u007f\u0001\u0011\u0013!C\u0001\u0003\u0003B\u0011\"!\u0012\u0001#\u0003%\t!a\u000f\t\u0013\u0005\u001d\u0003!%A\u0005\u0002\u0005%\u0003\"CA'\u0001\u0005\u0005I\u0011IA(\u0011!\ty\u0006AA\u0001\n\u0003\u0011\u0007\"CA1\u0001\u0005\u0005I\u0011AA2\u0011%\ty\u0007AA\u0001\n\u0003\n\t\bC\u0005\u0002\u0000\u0001\t\t\u0011\"\u0001\u0002\u0002\"I\u0011Q\u0011\u0001\u0002\u0002\u0013\u0005\u0013q\u0011\u0005\n\u0003\u0017\u0003\u0011\u0011!C!\u0003\u001bC\u0011\"a$\u0001\u0003\u0003%\t%!%\t\u0013\u0005M\u0005!!A\u0005B\u0005UuACAMY\u0005\u0005\t\u0012\u0001\u0018\u0002\u001c\u001aI1\u0006LA\u0001\u0012\u0003q\u0013Q\u0014\u0005\u0007k\u0016\"\t!!.\t\u0013\u0005=U%!A\u0005F\u0005E\u0005\"CA\\K\u0005\u0005I\u0011QA]\u0011%\tY-JA\u0001\n\u0003\u000bi\rC\u0005\u0002`\u0016\n\t\u0011\"\u0003\u0002b\na!\u000b]2F]Z\u001cuN\u001c4jO*\u0011QFL\u0001\u0004eB\u001c'BA\u00181\u0003\u0015\u0019\b/\u0019:l\u0015\t\t$'\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002g\u0005\u0019qN]4\u0014\t\u0001)4H\u0010\t\u0003mej\u0011a\u000e\u0006\u0002q\u0005)1oY1mC&\u0011!h\u000e\u0002\u0007\u0003:L(+\u001a4\u0011\u0005Yb\u0014BA\u001f8\u0005\u001d\u0001&o\u001c3vGR\u0004\"a\u0010%\u000f\u0005\u00013eBA!F\u001b\u0005\u0011%BA\"E\u0003\u0019a$o\\8u}\r\u0001\u0011\"\u0001\u001d\n\u0005\u001d;\u0014a\u00029bG.\fw-Z\u0005\u0003\u0013*\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!aR\u001c\u0002\t\r|gNZ\u000b\u0002\u001bB\u0011ajT\u0007\u0002]%\u0011\u0001K\f\u0002\n'B\f'o[\"p]\u001a\fQaY8oM\u0002\nAA\\1nKV\tA\u000b\u0005\u0002V3:\u0011ak\u0016\t\u0003\u0003^J!\u0001W\u001c\u0002\rA\u0013X\rZ3g\u0013\tQ6L\u0001\u0004TiJLgn\u001a\u0006\u00031^\nQA\\1nK\u0002\n1BY5oI\u0006#GM]3tg\u0006a!-\u001b8e\u0003\u0012$'/Z:tA\u0005\u0001\u0012\r\u001a<feRL7/Z!eIJ,7o]\u0001\u0012C\u00124XM\u001d;jg\u0016\fE\r\u001a:fgN\u0004\u0013\u0001\u00029peR,\u0012a\u0019\t\u0003m\u0011L!!Z\u001c\u0003\u0007%sG/A\u0003q_J$\b%A\btK\u000e,(/\u001b;z\u001b\u0006t\u0017mZ3s+\u0005I\u0007C\u0001(k\u0013\tYgFA\bTK\u000e,(/\u001b;z\u001b\u0006t\u0017mZ3s\u0003A\u0019XmY;sSRLX*\u00198bO\u0016\u0014\b%\u0001\bok6,6/\u00192mK\u000e{'/Z:\u0002\u001f9,X.V:bE2,7i\u001c:fg\u0002\n!b\u00197jK:$Xj\u001c3f+\u0005\t\bC\u0001\u001cs\u0013\t\u0019xGA\u0004C_>dW-\u00198\u0002\u0017\rd\u0017.\u001a8u\u001b>$W\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0015]L(p\u001f?~}~\f\t\u0001\u0005\u0002y\u00015\tA\u0006C\u0003L#\u0001\u0007Q\nC\u0003S#\u0001\u0007A\u000bC\u0003^#\u0001\u0007A\u000bC\u0003`#\u0001\u0007A\u000bC\u0003b#\u0001\u00071\rC\u0003h#\u0001\u0007\u0011\u000eC\u0003n#\u0001\u00071\rC\u0003p#\u0001\u0007\u0011/\u0001\u0003d_BLH#E<\u0002\b\u0005%\u00111BA\u0007\u0003\u001f\t\t\"a\u0005\u0002\u0016!91J\u0005I\u0001\u0002\u0004i\u0005b\u0002*\u0013!\u0003\u0005\r\u0001\u0016\u0005\b;J\u0001\n\u00111\u0001U\u0011\u001dy&\u0003%AA\u0002QCq!\u0019\n\u0011\u0002\u0003\u00071\rC\u0004h%A\u0005\t\u0019A5\t\u000f5\u0014\u0002\u0013!a\u0001G\"9qN\u0005I\u0001\u0002\u0004\t\u0018AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u00037Q3!TA\u000fW\t\ty\u0002\u0005\u0003\u0002\"\u0005-RBAA\u0012\u0015\u0011\t)#a\n\u0002\u0013Ut7\r[3dW\u0016$'bAA\u0015o\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u00055\u00121\u0005\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0003\u0003gQ3\u0001VA\u000f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM\nabY8qs\u0012\"WMZ1vYR$C'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001b\u0016\u0005\u0005u\"fA2\u0002\u001e\u0005q1m\u001c9zI\u0011,g-Y;mi\u00122TCAA\"U\rI\u0017QD\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00138\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIa*\"!a\u0013+\u0007E\fi\"A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003#\u0002B!a\u0015\u0002^5\u0011\u0011Q\u000b\u0006\u0005\u0003/\nI&\u0001\u0003mC:<'BAA.\u0003\u0011Q\u0017M^1\n\u0007i\u000b)&\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005\u0015\u00141\u000e\t\u0004m\u0005\u001d\u0014bAA5o\t\u0019\u0011I\\=\t\u0011\u00055T$!AA\u0002\r\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA:!\u0019\t)(a\u001f\u0002f5\u0011\u0011q\u000f\u0006\u0004\u0003s:\u0014AC2pY2,7\r^5p]&!\u0011QPA<\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0007E\f\u0019\tC\u0005\u0002n}\t\t\u00111\u0001\u0002f\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\t\t&!#\t\u0011\u00055\u0004%!AA\u0002\r\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002G\u0006AAo\\*ue&tw\r\u0006\u0002\u0002R\u00051Q-];bYN$2!]AL\u0011%\tigIA\u0001\u0002\u0004\t)'\u0001\u0007Sa\u000e,eN^\"p]\u001aLw\r\u0005\u0002yKM)Q%a(\u0002,Bi\u0011\u0011UAT\u001bR#FkY5dc^l!!a)\u000b\u0007\u0005\u0015v'A\u0004sk:$\u0018.\\3\n\t\u0005%\u00161\u0015\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:D\u0004\u0003BAW\u0003gk!!a,\u000b\t\u0005E\u0016\u0011L\u0001\u0003S>L1!SAX)\t\tY*A\u0003baBd\u0017\u0010F\tx\u0003w\u000bi,a0\u0002B\u0006\r\u0017QYAd\u0003\u0013DQa\u0013\u0015A\u00025CQA\u0015\u0015A\u0002QCQ!\u0018\u0015A\u0002QCQa\u0018\u0015A\u0002QCQ!\u0019\u0015A\u0002\rDQa\u001a\u0015A\u0002%DQ!\u001c\u0015A\u0002\rDQa\u001c\u0015A\u0002E\fq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002P\u0006m\u0007#\u0002\u001c\u0002R\u0006U\u0017bAAjo\t1q\n\u001d;j_:\u00042BNAl\u001bR#FkY5dc&\u0019\u0011\u0011\\\u001c\u0003\rQ+\b\u000f\\39\u0011!\ti.KA\u0001\u0002\u00049\u0018a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u001d\t\u0005\u0003'\n)/\u0003\u0003\u0002h\u0006U#AB(cU\u0016\u001cG\u000f"
)
public class RpcEnvConfig implements Product, Serializable {
   private final SparkConf conf;
   private final String name;
   private final String bindAddress;
   private final String advertiseAddress;
   private final int port;
   private final SecurityManager securityManager;
   private final int numUsableCores;
   private final boolean clientMode;

   public static Option unapply(final RpcEnvConfig x$0) {
      return RpcEnvConfig$.MODULE$.unapply(x$0);
   }

   public static RpcEnvConfig apply(final SparkConf conf, final String name, final String bindAddress, final String advertiseAddress, final int port, final SecurityManager securityManager, final int numUsableCores, final boolean clientMode) {
      return RpcEnvConfig$.MODULE$.apply(conf, name, bindAddress, advertiseAddress, port, securityManager, numUsableCores, clientMode);
   }

   public static Function1 tupled() {
      return RpcEnvConfig$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return RpcEnvConfig$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public SparkConf conf() {
      return this.conf;
   }

   public String name() {
      return this.name;
   }

   public String bindAddress() {
      return this.bindAddress;
   }

   public String advertiseAddress() {
      return this.advertiseAddress;
   }

   public int port() {
      return this.port;
   }

   public SecurityManager securityManager() {
      return this.securityManager;
   }

   public int numUsableCores() {
      return this.numUsableCores;
   }

   public boolean clientMode() {
      return this.clientMode;
   }

   public RpcEnvConfig copy(final SparkConf conf, final String name, final String bindAddress, final String advertiseAddress, final int port, final SecurityManager securityManager, final int numUsableCores, final boolean clientMode) {
      return new RpcEnvConfig(conf, name, bindAddress, advertiseAddress, port, securityManager, numUsableCores, clientMode);
   }

   public SparkConf copy$default$1() {
      return this.conf();
   }

   public String copy$default$2() {
      return this.name();
   }

   public String copy$default$3() {
      return this.bindAddress();
   }

   public String copy$default$4() {
      return this.advertiseAddress();
   }

   public int copy$default$5() {
      return this.port();
   }

   public SecurityManager copy$default$6() {
      return this.securityManager();
   }

   public int copy$default$7() {
      return this.numUsableCores();
   }

   public boolean copy$default$8() {
      return this.clientMode();
   }

   public String productPrefix() {
      return "RpcEnvConfig";
   }

   public int productArity() {
      return 8;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.conf();
         }
         case 1 -> {
            return this.name();
         }
         case 2 -> {
            return this.bindAddress();
         }
         case 3 -> {
            return this.advertiseAddress();
         }
         case 4 -> {
            return BoxesRunTime.boxToInteger(this.port());
         }
         case 5 -> {
            return this.securityManager();
         }
         case 6 -> {
            return BoxesRunTime.boxToInteger(this.numUsableCores());
         }
         case 7 -> {
            return BoxesRunTime.boxToBoolean(this.clientMode());
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
      return x$1 instanceof RpcEnvConfig;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "conf";
         }
         case 1 -> {
            return "name";
         }
         case 2 -> {
            return "bindAddress";
         }
         case 3 -> {
            return "advertiseAddress";
         }
         case 4 -> {
            return "port";
         }
         case 5 -> {
            return "securityManager";
         }
         case 6 -> {
            return "numUsableCores";
         }
         case 7 -> {
            return "clientMode";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.conf()));
      var1 = Statics.mix(var1, Statics.anyHash(this.name()));
      var1 = Statics.mix(var1, Statics.anyHash(this.bindAddress()));
      var1 = Statics.mix(var1, Statics.anyHash(this.advertiseAddress()));
      var1 = Statics.mix(var1, this.port());
      var1 = Statics.mix(var1, Statics.anyHash(this.securityManager()));
      var1 = Statics.mix(var1, this.numUsableCores());
      var1 = Statics.mix(var1, this.clientMode() ? 1231 : 1237);
      return Statics.finalizeHash(var1, 8);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var14;
      if (this != x$1) {
         label91: {
            if (x$1 instanceof RpcEnvConfig) {
               RpcEnvConfig var4 = (RpcEnvConfig)x$1;
               if (this.port() == var4.port() && this.numUsableCores() == var4.numUsableCores() && this.clientMode() == var4.clientMode()) {
                  label84: {
                     SparkConf var10000 = this.conf();
                     SparkConf var5 = var4.conf();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label84;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label84;
                     }

                     String var10 = this.name();
                     String var6 = var4.name();
                     if (var10 == null) {
                        if (var6 != null) {
                           break label84;
                        }
                     } else if (!var10.equals(var6)) {
                        break label84;
                     }

                     var10 = this.bindAddress();
                     String var7 = var4.bindAddress();
                     if (var10 == null) {
                        if (var7 != null) {
                           break label84;
                        }
                     } else if (!var10.equals(var7)) {
                        break label84;
                     }

                     var10 = this.advertiseAddress();
                     String var8 = var4.advertiseAddress();
                     if (var10 == null) {
                        if (var8 != null) {
                           break label84;
                        }
                     } else if (!var10.equals(var8)) {
                        break label84;
                     }

                     SecurityManager var13 = this.securityManager();
                     SecurityManager var9 = var4.securityManager();
                     if (var13 == null) {
                        if (var9 != null) {
                           break label84;
                        }
                     } else if (!var13.equals(var9)) {
                        break label84;
                     }

                     if (var4.canEqual(this)) {
                        break label91;
                     }
                  }
               }
            }

            var14 = false;
            return var14;
         }
      }

      var14 = true;
      return var14;
   }

   public RpcEnvConfig(final SparkConf conf, final String name, final String bindAddress, final String advertiseAddress, final int port, final SecurityManager securityManager, final int numUsableCores, final boolean clientMode) {
      this.conf = conf;
      this.name = name;
      this.bindAddress = bindAddress;
      this.advertiseAddress = advertiseAddress;
      this.port = port;
      this.securityManager = securityManager;
      this.numUsableCores = numUsableCores;
      this.clientMode = clientMode;
      Product.$init$(this);
   }
}
