package org.apache.spark.rpc;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ed!\u0002\u000f\u001e\u0001~)\u0003\u0002\u0003\u001f\u0001\u0005+\u0007I\u0011A\u001f\t\u0011\t\u0003!\u0011#Q\u0001\nyB\u0001b\u0011\u0001\u0003\u0016\u0004%\t\u0001\u0012\u0005\t\u001b\u0002\u0011\t\u0012)A\u0005\u000b\")a\n\u0001C\u0001\u001f\")a\n\u0001C\u0001'\"9A\f\u0001b\u0001\n\u0003j\u0006BB3\u0001A\u0003%a\fC\u0004g\u0001\u0005\u0005I\u0011A4\t\u000f)\u0004\u0011\u0013!C\u0001W\"9a\u000fAI\u0001\n\u00039\bbB=\u0001\u0003\u0003%\t%\u0018\u0005\bu\u0002\t\t\u0011\"\u0001|\u0011\u001da\b!!A\u0005\u0002uD\u0011\"a\u0002\u0001\u0003\u0003%\t%!\u0003\t\u0013\u0005]\u0001!!A\u0005\u0002\u0005e\u0001\"CA\u0012\u0001\u0005\u0005I\u0011IA\u0013\u0011%\tI\u0003AA\u0001\n\u0003\nY\u0003C\u0005\u0002.\u0001\t\t\u0011\"\u0011\u00020\u001dA\u00111G\u000f\t\u0002}\t)DB\u0004\u001d;!\u0005q$a\u000e\t\r9+B\u0011AA\"\u0011\u001d\t)%\u0006C\u0001\u0003\u000fBq!!\u0012\u0016\t\u0003\ty\u0005C\u0005\u0002FU\t\t\u0011\"!\u0002V!I\u00111L\u000b\u0002\u0002\u0013\u0005\u0015Q\f\u0005\n\u0003_*\u0012\u0011!C\u0005\u0003c\u0012!C\u00159d\u000b:$\u0007o\\5oi\u0006#GM]3tg*\u0011adH\u0001\u0004eB\u001c'B\u0001\u0011\"\u0003\u0015\u0019\b/\u0019:l\u0015\t\u00113%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002I\u0005\u0019qN]4\u0014\t\u00011Cf\f\t\u0003O)j\u0011\u0001\u000b\u0006\u0002S\u0005)1oY1mC&\u00111\u0006\u000b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u001dj\u0013B\u0001\u0018)\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001M\u001d\u000f\u0005E:dB\u0001\u001a7\u001b\u0005\u0019$B\u0001\u001b6\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\u0015\n\u0005aB\u0013a\u00029bG.\fw-Z\u0005\u0003um\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001\u000f\u0015\u0002\u0015I\u00048-\u00113ee\u0016\u001c8/F\u0001?!\ty\u0004)D\u0001\u001e\u0013\t\tUD\u0001\u0006Sa\u000e\fE\r\u001a:fgN\f1B\u001d9d\u0003\u0012$'/Z:tA\u0005!a.Y7f+\u0005)\u0005C\u0001$K\u001d\t9\u0005\n\u0005\u00023Q%\u0011\u0011\nK\u0001\u0007!J,G-\u001a4\n\u0005-c%AB*ue&twM\u0003\u0002JQ\u0005)a.Y7fA\u00051A(\u001b8jiz\"2\u0001U)S!\ty\u0004\u0001C\u0003=\u000b\u0001\u0007a\bC\u0003D\u000b\u0001\u0007Q\t\u0006\u0003Q)Z[\u0006\"B+\u0007\u0001\u0004)\u0015\u0001\u00025pgRDQa\u0016\u0004A\u0002a\u000bA\u0001]8siB\u0011q%W\u0005\u00035\"\u00121!\u00138u\u0011\u0015\u0019e\u00011\u0001F\u0003!!xn\u0015;sS:<W#\u00010\u0011\u0005}#W\"\u00011\u000b\u0005\u0005\u0014\u0017\u0001\u00027b]\u001eT\u0011aY\u0001\u0005U\u00064\u0018-\u0003\u0002LA\u0006IAo\\*ue&tw\rI\u0001\u0005G>\u0004\u0018\u0010F\u0002QQ&Dq\u0001P\u0005\u0011\u0002\u0003\u0007a\bC\u0004D\u0013A\u0005\t\u0019A#\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\tAN\u000b\u0002?[.\na\u000e\u0005\u0002pi6\t\u0001O\u0003\u0002re\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003g\"\n!\"\u00198o_R\fG/[8o\u0013\t)\bOA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'F\u0001yU\t)U.A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u0001\raJ|G-^2u\u0003JLG/_\u000b\u00021\u0006q\u0001O]8ek\u000e$X\t\\3nK:$Hc\u0001@\u0002\u0004A\u0011qe`\u0005\u0004\u0003\u0003A#aA!os\"A\u0011Q\u0001\b\u0002\u0002\u0003\u0007\u0001,A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003\u0017\u0001R!!\u0004\u0002\u0014yl!!a\u0004\u000b\u0007\u0005E\u0001&\u0001\u0006d_2dWm\u0019;j_:LA!!\u0006\u0002\u0010\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\tY\"!\t\u0011\u0007\u001d\ni\"C\u0002\u0002 !\u0012qAQ8pY\u0016\fg\u000e\u0003\u0005\u0002\u0006A\t\t\u00111\u0001\u007f\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007y\u000b9\u0003\u0003\u0005\u0002\u0006E\t\t\u00111\u0001Y\u0003!A\u0017m\u001d5D_\u0012,G#\u0001-\u0002\r\u0015\fX/\u00197t)\u0011\tY\"!\r\t\u0011\u0005\u00151#!AA\u0002y\f!C\u00159d\u000b:$\u0007o\\5oi\u0006#GM]3tgB\u0011q(F\n\u0005+\u0019\nI\u0004\u0005\u0003\u0002<\u0005\u0005SBAA\u001f\u0015\r\tyDY\u0001\u0003S>L1AOA\u001f)\t\t)$A\u0003baBd\u0017\u0010F\u0004Q\u0003\u0013\nY%!\u0014\t\u000bU;\u0002\u0019A#\t\u000b];\u0002\u0019\u0001-\t\u000b\r;\u0002\u0019A#\u0015\u0007A\u000b\t\u0006\u0003\u0004\u0002Ta\u0001\r!R\u0001\tgB\f'o[+sYR)\u0001+a\u0016\u0002Z!)A(\u0007a\u0001}!)1)\u0007a\u0001\u000b\u00069QO\\1qa2LH\u0003BA0\u0003W\u0002RaJA1\u0003KJ1!a\u0019)\u0005\u0019y\u0005\u000f^5p]B)q%a\u001a?\u000b&\u0019\u0011\u0011\u000e\u0015\u0003\rQ+\b\u000f\\33\u0011!\tiGGA\u0001\u0002\u0004\u0001\u0016a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u000f\t\u0004?\u0006U\u0014bAA<A\n1qJ\u00196fGR\u0004"
)
public class RpcEndpointAddress implements Product, Serializable {
   private final RpcAddress rpcAddress;
   private final String name;
   private final String toString;

   public static Option unapply(final RpcEndpointAddress x$0) {
      return RpcEndpointAddress$.MODULE$.unapply(x$0);
   }

   public static RpcEndpointAddress apply(final RpcAddress rpcAddress, final String name) {
      return RpcEndpointAddress$.MODULE$.apply(rpcAddress, name);
   }

   public static RpcEndpointAddress apply(final String sparkUrl) {
      return RpcEndpointAddress$.MODULE$.apply(sparkUrl);
   }

   public static RpcEndpointAddress apply(final String host, final int port, final String name) {
      return RpcEndpointAddress$.MODULE$.apply(host, port, name);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public RpcAddress rpcAddress() {
      return this.rpcAddress;
   }

   public String name() {
      return this.name;
   }

   public String toString() {
      return this.toString;
   }

   public RpcEndpointAddress copy(final RpcAddress rpcAddress, final String name) {
      return new RpcEndpointAddress(rpcAddress, name);
   }

   public RpcAddress copy$default$1() {
      return this.rpcAddress();
   }

   public String copy$default$2() {
      return this.name();
   }

   public String productPrefix() {
      return "RpcEndpointAddress";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.rpcAddress();
         }
         case 1 -> {
            return this.name();
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
      return x$1 instanceof RpcEndpointAddress;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "rpcAddress";
         }
         case 1 -> {
            return "name";
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
            if (x$1 instanceof RpcEndpointAddress) {
               label48: {
                  RpcEndpointAddress var4 = (RpcEndpointAddress)x$1;
                  RpcAddress var10000 = this.rpcAddress();
                  RpcAddress var5 = var4.rpcAddress();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  String var7 = this.name();
                  String var6 = var4.name();
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

   public RpcEndpointAddress(final RpcAddress rpcAddress, final String name) {
      this.rpcAddress = rpcAddress;
      this.name = name;
      Product.$init$(this);
      scala.Predef..MODULE$.require(name != null, () -> "RpcEndpoint name must be provided.");
      this.toString = rpcAddress != null ? "spark://" + name + "@" + rpcAddress.host() + ":" + rpcAddress.port() : "spark-client://" + name;
   }

   public RpcEndpointAddress(final String host, final int port, final String name) {
      this(RpcAddress$.MODULE$.apply(host, port), name);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
