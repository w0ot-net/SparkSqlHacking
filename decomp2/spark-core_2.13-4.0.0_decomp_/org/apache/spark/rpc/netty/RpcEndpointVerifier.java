package org.apache.spark.rpc.netty;

import java.io.Serializable;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcCallContext;
import org.apache.spark.rpc.RpcEndpoint;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import scala.Function1;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.Some;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-f!B\u0011#\u0001\tb\u0003\u0002C\u001c\u0001\u0005\u000b\u0007I\u0011I\u001d\t\u0011u\u0002!\u0011!Q\u0001\niB\u0001B\u0010\u0001\u0003\u0002\u0003\u0006Ia\u0010\u0005\u0006\u0007\u0002!\t\u0001\u0012\u0005\u0006\u0011\u0002!\t%S\u0004\u00071\nB\tAI-\u0007\r\u0005\u0012\u0003\u0012\u0001\u0012[\u0011\u0015\u0019u\u0001\"\u0001\\\u0011\u001davA1A\u0005\u0002uCaAZ\u0004!\u0002\u0013qf\u0001B4\b\u0001\"D\u0001\u0002_\u0006\u0003\u0016\u0004%\t!\u001f\u0005\n\u0003\u0007Y!\u0011#Q\u0001\niDaaQ\u0006\u0005\u0002\u0005\u0015\u0001\"CA\u0007\u0017\u0005\u0005I\u0011AA\b\u0011%\t\u0019bCI\u0001\n\u0003\t)\u0002\u0003\u0005\u0002,-\t\t\u0011\"\u0011^\u0011%\ticCA\u0001\n\u0003\ty\u0003C\u0005\u00028-\t\t\u0011\"\u0001\u0002:!I\u0011qH\u0006\u0002\u0002\u0013\u0005\u0013\u0011\t\u0005\n\u0003\u001fZ\u0011\u0011!C\u0001\u0003#B\u0011\"a\u0017\f\u0003\u0003%\t%!\u0018\t\u0013\u0005\u00054\"!A\u0005B\u0005\r\u0004\"CA3\u0017\u0005\u0005I\u0011IA4\u0011%\tIgCA\u0001\n\u0003\nYgB\u0005\u0002p\u001d\t\t\u0011#\u0001\u0002r\u0019AqmBA\u0001\u0012\u0003\t\u0019\b\u0003\u0004D7\u0011\u0005\u00111\u0012\u0005\n\u0003KZ\u0012\u0011!C#\u0003OB\u0011\"!$\u001c\u0003\u0003%\t)a$\t\u0013\u0005M5$!A\u0005\u0002\u0006U\u0005\"CAQ7\u0005\u0005I\u0011BAR\u0005M\u0011\u0006oY#oIB|\u0017N\u001c;WKJLg-[3s\u0015\t\u0019C%A\u0003oKR$\u0018P\u0003\u0002&M\u0005\u0019!\u000f]2\u000b\u0005\u001dB\u0013!B:qCJ\\'BA\u0015+\u0003\u0019\t\u0007/Y2iK*\t1&A\u0002pe\u001e\u001c2\u0001A\u00174!\tq\u0013'D\u00010\u0015\u0005\u0001\u0014!B:dC2\f\u0017B\u0001\u001a0\u0005\u0019\te.\u001f*fMB\u0011A'N\u0007\u0002I%\u0011a\u0007\n\u0002\f%B\u001cWI\u001c3q_&tG/\u0001\u0004sa\u000e,eN^\u0002\u0001+\u0005Q\u0004C\u0001\u001b<\u0013\taDE\u0001\u0004Sa\u000e,eN^\u0001\beB\u001cWI\u001c<!\u0003)!\u0017n\u001d9bi\u000eDWM\u001d\t\u0003\u0001\u0006k\u0011AI\u0005\u0003\u0005\n\u0012!\u0002R5ta\u0006$8\r[3s\u0003\u0019a\u0014N\\5u}Q\u0019QIR$\u0011\u0005\u0001\u0003\u0001\"B\u001c\u0005\u0001\u0004Q\u0004\"\u0002 \u0005\u0001\u0004y\u0014a\u0004:fG\u0016Lg/Z!oIJ+\u0007\u000f\\=\u0015\u0005)\u001b\u0006\u0003\u0002\u0018L\u001bBK!\u0001T\u0018\u0003\u001fA\u000b'\u000f^5bY\u001a+hn\u0019;j_:\u0004\"A\f(\n\u0005={#aA!osB\u0011a&U\u0005\u0003%>\u0012A!\u00168ji\")A+\u0002a\u0001+\u000691m\u001c8uKb$\bC\u0001\u001bW\u0013\t9FE\u0001\bSa\u000e\u001c\u0015\r\u001c7D_:$X\r\u001f;\u0002'I\u00038-\u00128ea>Lg\u000e\u001e,fe&4\u0017.\u001a:\u0011\u0005\u0001;1CA\u0004.)\u0005I\u0016\u0001\u0002(B\u001b\u0016+\u0012A\u0018\t\u0003?\u0012l\u0011\u0001\u0019\u0006\u0003C\n\fA\u0001\\1oO*\t1-\u0001\u0003kCZ\f\u0017BA3a\u0005\u0019\u0019FO]5oO\u0006)a*Q'FA\tq1\t[3dW\u0016C\u0018n\u001d;f]\u000e,7\u0003B\u0006.S2\u0004\"A\f6\n\u0005-|#a\u0002)s_\u0012,8\r\u001e\t\u0003[Vt!A\\:\u000f\u0005=\u0014X\"\u00019\u000b\u0005ED\u0014A\u0002\u001fs_>$h(C\u00011\u0013\t!x&A\u0004qC\u000e\\\u0017mZ3\n\u0005Y<(\u0001D*fe&\fG.\u001b>bE2,'B\u0001;0\u0003\u0011q\u0017-\\3\u0016\u0003i\u0004\"a_@\u000f\u0005ql\bCA80\u0013\tqx&\u0001\u0004Qe\u0016$WMZ\u0005\u0004K\u0006\u0005!B\u0001@0\u0003\u0015q\u0017-\\3!)\u0011\t9!a\u0003\u0011\u0007\u0005%1\"D\u0001\b\u0011\u0015Ah\u00021\u0001{\u0003\u0011\u0019w\u000e]=\u0015\t\u0005\u001d\u0011\u0011\u0003\u0005\bq>\u0001\n\u00111\u0001{\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"!a\u0006+\u0007i\fIb\u000b\u0002\u0002\u001cA!\u0011QDA\u0014\u001b\t\tyB\u0003\u0003\u0002\"\u0005\r\u0012!C;oG\",7m[3e\u0015\r\t)cL\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\u0015\u0003?\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jq\u0006a\u0001O]8ek\u000e$\u0018I]5usV\u0011\u0011\u0011\u0007\t\u0004]\u0005M\u0012bAA\u001b_\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0019Q*a\u000f\t\u0013\u0005u2#!AA\u0002\u0005E\u0012a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002DA)\u0011QIA&\u001b6\u0011\u0011q\t\u0006\u0004\u0003\u0013z\u0013AC2pY2,7\r^5p]&!\u0011QJA$\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005M\u0013\u0011\f\t\u0004]\u0005U\u0013bAA,_\t9!i\\8mK\u0006t\u0007\u0002CA\u001f+\u0005\u0005\t\u0019A'\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004=\u0006}\u0003\"CA\u001f-\u0005\u0005\t\u0019AA\u0019\u0003!A\u0017m\u001d5D_\u0012,GCAA\u0019\u0003!!xn\u0015;sS:<G#\u00010\u0002\r\u0015\fX/\u00197t)\u0011\t\u0019&!\u001c\t\u0011\u0005u\u0012$!AA\u00025\u000bab\u00115fG.,\u00050[:uK:\u001cW\rE\u0002\u0002\nm\u0019RaGA;\u0003\u0003\u0003r!a\u001e\u0002~i\f9!\u0004\u0002\u0002z)\u0019\u00111P\u0018\u0002\u000fI,h\u000e^5nK&!\u0011qPA=\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g.\r\t\u0005\u0003\u0007\u000bI)\u0004\u0002\u0002\u0006*\u0019\u0011q\u00112\u0002\u0005%|\u0017b\u0001<\u0002\u0006R\u0011\u0011\u0011O\u0001\u0006CB\u0004H.\u001f\u000b\u0005\u0003\u000f\t\t\nC\u0003y=\u0001\u0007!0A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005]\u0015Q\u0014\t\u0005]\u0005e%0C\u0002\u0002\u001c>\u0012aa\u00149uS>t\u0007\"CAP?\u0005\u0005\t\u0019AA\u0004\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003K\u00032aXAT\u0013\r\tI\u000b\u0019\u0002\u0007\u001f\nTWm\u0019;"
)
public class RpcEndpointVerifier implements RpcEndpoint {
   private final RpcEnv rpcEnv;
   public final Dispatcher org$apache$spark$rpc$netty$RpcEndpointVerifier$$dispatcher;

   public static String NAME() {
      return RpcEndpointVerifier$.MODULE$.NAME();
   }

   public final RpcEndpointRef self() {
      return RpcEndpoint.self$(this);
   }

   public PartialFunction receive() {
      return RpcEndpoint.receive$(this);
   }

   public void onError(final Throwable cause) {
      RpcEndpoint.onError$(this, cause);
   }

   public void onConnected(final RpcAddress remoteAddress) {
      RpcEndpoint.onConnected$(this, remoteAddress);
   }

   public void onDisconnected(final RpcAddress remoteAddress) {
      RpcEndpoint.onDisconnected$(this, remoteAddress);
   }

   public void onNetworkError(final Throwable cause, final RpcAddress remoteAddress) {
      RpcEndpoint.onNetworkError$(this, cause, remoteAddress);
   }

   public void onStart() {
      RpcEndpoint.onStart$(this);
   }

   public void onStop() {
      RpcEndpoint.onStop$(this);
   }

   public final void stop() {
      RpcEndpoint.stop$(this);
   }

   public RpcEnv rpcEnv() {
      return this.rpcEnv;
   }

   public PartialFunction receiveAndReply(final RpcCallContext context) {
      return new Serializable(context) {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final RpcEndpointVerifier $outer;
         private final RpcCallContext context$1;

         public final Object applyOrElse(final Object x1, final Function1 default) {
            if (x1 instanceof CheckExistence var5) {
               String name = var5.name();
               this.context$1.reply(BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$rpc$netty$RpcEndpointVerifier$$dispatcher.verify(name)));
               return BoxedUnit.UNIT;
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final Object x1) {
            return x1 instanceof CheckExistence;
         }

         public {
            if (RpcEndpointVerifier.this == null) {
               throw null;
            } else {
               this.$outer = RpcEndpointVerifier.this;
               this.context$1 = context$1;
            }
         }
      };
   }

   public RpcEndpointVerifier(final RpcEnv rpcEnv, final Dispatcher dispatcher) {
      this.rpcEnv = rpcEnv;
      this.org$apache$spark$rpc$netty$RpcEndpointVerifier$$dispatcher = dispatcher;
      RpcEndpoint.$init$(this);
   }

   public static class CheckExistence implements Product, Serializable {
      private final String name;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String name() {
         return this.name;
      }

      public CheckExistence copy(final String name) {
         return new CheckExistence(name);
      }

      public String copy$default$1() {
         return this.name();
      }

      public String productPrefix() {
         return "CheckExistence";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
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
         return x$1 instanceof CheckExistence;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
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

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var6;
         if (this != x$1) {
            label47: {
               if (x$1 instanceof CheckExistence) {
                  label40: {
                     CheckExistence var4 = (CheckExistence)x$1;
                     String var10000 = this.name();
                     String var5 = var4.name();
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

      public CheckExistence(final String name) {
         this.name = name;
         Product.$init$(this);
      }
   }

   public static class CheckExistence$ extends AbstractFunction1 implements Serializable {
      public static final CheckExistence$ MODULE$ = new CheckExistence$();

      public final String toString() {
         return "CheckExistence";
      }

      public CheckExistence apply(final String name) {
         return new CheckExistence(name);
      }

      public Option unapply(final CheckExistence x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.name()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(CheckExistence$.class);
      }
   }
}
