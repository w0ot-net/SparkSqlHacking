package org.apache.spark.deploy.k8s.submit;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005f!B\u0010!\u0001\u001ab\u0003\u0002C\"\u0001\u0005+\u0007I\u0011\u0001#\t\u0011%\u0003!\u0011#Q\u0001\n\u0015C\u0001B\u0013\u0001\u0003\u0016\u0004%\ta\u0013\u0005\t)\u0002\u0011\t\u0012)A\u0005\u0019\"AQ\u000b\u0001BK\u0002\u0013\u0005a\u000b\u0003\u0005[\u0001\tE\t\u0015!\u0003X\u0011!Y\u0006A!f\u0001\n\u0003a\u0006\u0002\u00031\u0001\u0005#\u0005\u000b\u0011B/\t\u000b\u0005\u0004A\u0011\u00012\t\u000f!\u0004\u0011\u0011!C\u0001S\"9a\u000eAI\u0001\n\u0003y\u0007b\u0002>\u0001#\u0003%\ta\u001f\u0005\b{\u0002\t\n\u0011\"\u0001\u007f\u0011%\t\t\u0001AI\u0001\n\u0003\t\u0019\u0001C\u0005\u0002\b\u0001\t\t\u0011\"\u0011\u0002\n!I\u0011\u0011\u0004\u0001\u0002\u0002\u0013\u0005\u00111\u0004\u0005\n\u0003G\u0001\u0011\u0011!C\u0001\u0003KA\u0011\"!\r\u0001\u0003\u0003%\t%a\r\t\u0013\u0005\u0005\u0003!!A\u0005\u0002\u0005\r\u0003\"CA'\u0001\u0005\u0005I\u0011IA(\u0011%\t\u0019\u0006AA\u0001\n\u0003\n)\u0006C\u0005\u0002X\u0001\t\t\u0011\"\u0011\u0002Z!I\u00111\f\u0001\u0002\u0002\u0013\u0005\u0013QL\u0004\t\u0003C\u0002\u0003\u0012\u0001\u0014\u0002d\u00199q\u0004\tE\u0001M\u0005\u0015\u0004BB1\u001a\t\u0003\t\t\bC\u0004\u0002te!\t!!\u001e\t\u0013\u0005m\u0014$!A\u0005\u0002\u0006u\u0004\"CAD3\u0005\u0005I\u0011QAE\u0011%\t9*GA\u0001\n\u0013\tIJA\bDY&,g\u000e^!sOVlWM\u001c;t\u0015\t\t#%\u0001\u0004tk\nl\u0017\u000e\u001e\u0006\u0003G\u0011\n1a\u001b\u001dt\u0015\t)c%\u0001\u0004eKBdw.\u001f\u0006\u0003O!\nQa\u001d9be.T!!\u000b\u0016\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005Y\u0013aA8sON!\u0001!L\u001a7!\tq\u0013'D\u00010\u0015\u0005\u0001\u0014!B:dC2\f\u0017B\u0001\u001a0\u0005\u0019\te.\u001f*fMB\u0011a\u0006N\u0005\u0003k=\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00028\u0001:\u0011\u0001H\u0010\b\u0003suj\u0011A\u000f\u0006\u0003wq\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002a%\u0011qhL\u0001\ba\u0006\u001c7.Y4f\u0013\t\t%I\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002@_\u0005yQ.Y5o\u0003B\u0004(+Z:pkJ\u001cW-F\u0001F!\t1u)D\u0001!\u0013\tA\u0005EA\bNC&t\u0017\t\u001d9SKN|WO]2f\u0003Ai\u0017-\u001b8BaB\u0014Vm]8ve\u000e,\u0007%A\u0005nC&t7\t\\1tgV\tA\n\u0005\u0002N#:\u0011aj\u0014\t\u0003s=J!\u0001U\u0018\u0002\rA\u0013X\rZ3g\u0013\t\u00116K\u0001\u0004TiJLgn\u001a\u0006\u0003!>\n!\"\\1j]\u000ec\u0017m]:!\u0003)!'/\u001b<fe\u0006\u0013xm]\u000b\u0002/B\u0019a\u0006\u0017'\n\u0005e{#!B!se\u0006L\u0018a\u00033sSZ,'/\u0011:hg\u0002\n\u0011\u0002\u001d:pqf,6/\u001a:\u0016\u0003u\u00032A\f0M\u0013\tyvF\u0001\u0004PaRLwN\\\u0001\u000baJ|\u00070_+tKJ\u0004\u0013A\u0002\u001fj]&$h\bF\u0003dI\u00164w\r\u0005\u0002G\u0001!)1)\u0003a\u0001\u000b\")!*\u0003a\u0001\u0019\")Q+\u0003a\u0001/\")1,\u0003a\u0001;\u0006!1m\u001c9z)\u0015\u0019'n\u001b7n\u0011\u001d\u0019%\u0002%AA\u0002\u0015CqA\u0013\u0006\u0011\u0002\u0003\u0007A\nC\u0004V\u0015A\u0005\t\u0019A,\t\u000fmS\u0001\u0013!a\u0001;\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u00019+\u0005\u0015\u000b8&\u0001:\u0011\u0005MDX\"\u0001;\u000b\u0005U4\u0018!C;oG\",7m[3e\u0015\t9x&\u0001\u0006b]:|G/\u0019;j_:L!!\u001f;\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0003qT#\u0001T9\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU\tqP\u000b\u0002Xc\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\"TCAA\u0003U\ti\u0016/A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003\u0017\u0001B!!\u0004\u0002\u00185\u0011\u0011q\u0002\u0006\u0005\u0003#\t\u0019\"\u0001\u0003mC:<'BAA\u000b\u0003\u0011Q\u0017M^1\n\u0007I\u000by!\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002\u001eA\u0019a&a\b\n\u0007\u0005\u0005rFA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002(\u00055\u0002c\u0001\u0018\u0002*%\u0019\u00111F\u0018\u0003\u0007\u0005s\u0017\u0010C\u0005\u00020E\t\t\u00111\u0001\u0002\u001e\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!\u000e\u0011\r\u0005]\u0012QHA\u0014\u001b\t\tIDC\u0002\u0002<=\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\ty$!\u000f\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003\u000b\nY\u0005E\u0002/\u0003\u000fJ1!!\u00130\u0005\u001d\u0011un\u001c7fC:D\u0011\"a\f\u0014\u0003\u0003\u0005\r!a\n\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003\u0017\t\t\u0006C\u0005\u00020Q\t\t\u00111\u0001\u0002\u001e\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002\u001e\u0005AAo\\*ue&tw\r\u0006\u0002\u0002\f\u00051Q-];bYN$B!!\u0012\u0002`!I\u0011qF\f\u0002\u0002\u0003\u0007\u0011qE\u0001\u0010\u00072LWM\u001c;Be\u001e,X.\u001a8ugB\u0011a)G\n\u000535\n9\u0007\u0005\u0003\u0002j\u0005=TBAA6\u0015\u0011\ti'a\u0005\u0002\u0005%|\u0017bA!\u0002lQ\u0011\u00111M\u0001\u0014MJ|WnQ8n[\u0006tG\rT5oK\u0006\u0013xm\u001d\u000b\u0004G\u0006]\u0004BBA=7\u0001\u0007q+\u0001\u0003be\u001e\u001c\u0018!B1qa2LH#C2\u0002\u0000\u0005\u0005\u00151QAC\u0011\u0015\u0019E\u00041\u0001F\u0011\u0015QE\u00041\u0001M\u0011\u0015)F\u00041\u0001X\u0011\u0015YF\u00041\u0001^\u0003\u001d)h.\u00199qYf$B!a#\u0002\u0014B!aFXAG!\u001dq\u0013qR#M/vK1!!%0\u0005\u0019!V\u000f\u001d7fi!A\u0011QS\u000f\u0002\u0002\u0003\u00071-A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a'\u0011\t\u00055\u0011QT\u0005\u0005\u0003?\u000byA\u0001\u0004PE*,7\r\u001e"
)
public class ClientArguments implements Product, Serializable {
   private final MainAppResource mainAppResource;
   private final String mainClass;
   private final String[] driverArgs;
   private final Option proxyUser;

   public static Option unapply(final ClientArguments x$0) {
      return ClientArguments$.MODULE$.unapply(x$0);
   }

   public static ClientArguments apply(final MainAppResource mainAppResource, final String mainClass, final String[] driverArgs, final Option proxyUser) {
      return ClientArguments$.MODULE$.apply(mainAppResource, mainClass, driverArgs, proxyUser);
   }

   public static ClientArguments fromCommandLineArgs(final String[] args) {
      return ClientArguments$.MODULE$.fromCommandLineArgs(args);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public MainAppResource mainAppResource() {
      return this.mainAppResource;
   }

   public String mainClass() {
      return this.mainClass;
   }

   public String[] driverArgs() {
      return this.driverArgs;
   }

   public Option proxyUser() {
      return this.proxyUser;
   }

   public ClientArguments copy(final MainAppResource mainAppResource, final String mainClass, final String[] driverArgs, final Option proxyUser) {
      return new ClientArguments(mainAppResource, mainClass, driverArgs, proxyUser);
   }

   public MainAppResource copy$default$1() {
      return this.mainAppResource();
   }

   public String copy$default$2() {
      return this.mainClass();
   }

   public String[] copy$default$3() {
      return this.driverArgs();
   }

   public Option copy$default$4() {
      return this.proxyUser();
   }

   public String productPrefix() {
      return "ClientArguments";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.mainAppResource();
         }
         case 1 -> {
            return this.mainClass();
         }
         case 2 -> {
            return this.driverArgs();
         }
         case 3 -> {
            return this.proxyUser();
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
      return x$1 instanceof ClientArguments;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "mainAppResource";
         }
         case 1 -> {
            return "mainClass";
         }
         case 2 -> {
            return "driverArgs";
         }
         case 3 -> {
            return "proxyUser";
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
      boolean var10;
      if (this != x$1) {
         label67: {
            if (x$1 instanceof ClientArguments) {
               label59: {
                  ClientArguments var4 = (ClientArguments)x$1;
                  MainAppResource var10000 = this.mainAppResource();
                  MainAppResource var5 = var4.mainAppResource();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label59;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label59;
                  }

                  String var8 = this.mainClass();
                  String var6 = var4.mainClass();
                  if (var8 == null) {
                     if (var6 != null) {
                        break label59;
                     }
                  } else if (!var8.equals(var6)) {
                     break label59;
                  }

                  if (this.driverArgs() == var4.driverArgs()) {
                     label60: {
                        Option var9 = this.proxyUser();
                        Option var7 = var4.proxyUser();
                        if (var9 == null) {
                           if (var7 != null) {
                              break label60;
                           }
                        } else if (!var9.equals(var7)) {
                           break label60;
                        }

                        if (var4.canEqual(this)) {
                           break label67;
                        }
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

   public ClientArguments(final MainAppResource mainAppResource, final String mainClass, final String[] driverArgs, final Option proxyUser) {
      this.mainAppResource = mainAppResource;
      this.mainClass = mainClass;
      this.driverArgs = driverArgs;
      this.proxyUser = proxyUser;
      Product.$init$(this);
   }
}
