package org.apache.spark.rpc.netty;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.network.client.RpcResponseCallback;
import org.apache.spark.network.client.TransportClient;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Product;
import scala.StringContext;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\t-a!\u0002\u0015*\u0001&\u001a\u0004\u0002\u0003/\u0001\u0005+\u0007I\u0011A/\t\u0011\u0019\u0004!\u0011#Q\u0001\nyC\u0001b\u001a\u0001\u0003\u0016\u0004%\t\u0001\u001b\u0005\te\u0002\u0011\t\u0012)A\u0005S\"A1\u000f\u0001BK\u0002\u0013\u0005A\u000f\u0003\u0005|\u0001\tE\t\u0015!\u0003v\u0011\u0015a\b\u0001\"\u0001~\u0011)\t\u0005\u00011AA\u0002\u0013%\u0011Q\u0001\u0005\f\u0003\u000f\u0001\u0001\u0019!a\u0001\n\u0013\tI\u0001\u0003\u0006\u0002\u0010\u0001\u0001\r\u0011!Q!\naD1\"!\u0005\u0001\u0001\u0004\u0005\r\u0011\"\u0003\u0002\u0014!Y\u00111\u0004\u0001A\u0002\u0003\u0007I\u0011BA\u000f\u0011-\t\t\u0003\u0001a\u0001\u0002\u0003\u0006K!!\u0006\t\u000f\u0005\r\u0002\u0001\"\u0011\u0002&!A\u0011\u0011\u0006\u0001\u0005\u0002%\nY\u0003C\u0004\u0002.\u0001!\t!a\u000b\t\u000f\u0005=\u0002\u0001\"\u0001\u0002,!9\u0011\u0011\u0007\u0001\u0005B\u0005M\u0002bBA\u001d\u0001\u0011\u0005\u00131\b\u0005\n\u0003\u0003\u0002\u0011\u0011!C\u0001\u0003\u0007B\u0011\"a\u0013\u0001#\u0003%\t!!\u0014\t\u0013\u0005\r\u0004!%A\u0005\u0002\u0005\u0015\u0004\"CA5\u0001E\u0005I\u0011AA6\u0011%\ty\u0007AA\u0001\n\u0003\n\t\bC\u0005\u0002\u0000\u0001\t\t\u0011\"\u0001\u0002\u0002\"I\u0011\u0011\u0012\u0001\u0002\u0002\u0013\u0005\u00111\u0012\u0005\n\u0003+\u0003\u0011\u0011!C!\u0003/C\u0011\"!*\u0001\u0003\u0003%\t!a*\t\u0013\u0005E\u0006!!A\u0005B\u0005M\u0006\"CA\\\u0001\u0005\u0005I\u0011IA]\u0011%\tY\fAA\u0001\n\u0003\ni\fC\u0005\u0002@\u0002\t\t\u0011\"\u0011\u0002B\u001eQ\u0011QY\u0015\u0002\u0002#\u0005\u0011&a2\u0007\u0013!J\u0013\u0011!E\u0001S\u0005%\u0007B\u0002?#\t\u0003\t\t\u000fC\u0005\u0002<\n\n\t\u0011\"\u0012\u0002>\"I\u00111\u001d\u0012\u0002\u0002\u0013\u0005\u0015Q\u001d\u0005\n\u0003[\u0014\u0013\u0011!CA\u0003_D\u0011B!\u0001#\u0003\u0003%IAa\u0001\u0003!I\u00038mT;uE>DX*Z:tC\u001e,'B\u0001\u0016,\u0003\u0015qW\r\u001e;z\u0015\taS&A\u0002sa\u000eT!AL\u0018\u0002\u000bM\u0004\u0018M]6\u000b\u0005A\n\u0014AB1qC\u000eDWMC\u00013\u0003\ry'oZ\n\b\u0001QRdH\u0012'P!\t)\u0004(D\u00017\u0015\u00059\u0014!B:dC2\f\u0017BA\u001d7\u0005\u0019\te.\u001f*fMB\u00111\bP\u0007\u0002S%\u0011Q(\u000b\u0002\u000e\u001fV$(m\u001c=NKN\u001c\u0018mZ3\u0011\u0005}\"U\"\u0001!\u000b\u0005\u0005\u0013\u0015AB2mS\u0016tGO\u0003\u0002D[\u00059a.\u001a;x_J\\\u0017BA#A\u0005M\u0011\u0006o\u0019*fgB|gn]3DC2d'-Y2l!\t9%*D\u0001I\u0015\tIU&\u0001\u0005j]R,'O\\1m\u0013\tY\u0005JA\u0004M_\u001e<\u0017N\\4\u0011\u0005Uj\u0015B\u0001(7\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001U-\u000f\u0005E;fB\u0001*W\u001b\u0005\u0019&B\u0001+V\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\u001c\n\u0005a3\u0014a\u00029bG.\fw-Z\u0005\u00035n\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001\u0017\u001c\u0002\u000f\r|g\u000e^3oiV\ta\f\u0005\u0002`I6\t\u0001M\u0003\u0002bE\u0006\u0019a.[8\u000b\u0003\r\fAA[1wC&\u0011Q\r\u0019\u0002\u000b\u0005f$XMQ;gM\u0016\u0014\u0018\u0001C2p]R,g\u000e\u001e\u0011\u0002\u0015}{gNR1jYV\u0014X-F\u0001j!\u0011)$\u000e\\8\n\u0005-4$!\u0003$v]\u000e$\u0018n\u001c82!\t\u0001V.\u0003\u0002o7\nIA\u000b\u001b:po\u0006\u0014G.\u001a\t\u0003kAL!!\u001d\u001c\u0003\tUs\u0017\u000e^\u0001\f?>tg)Y5mkJ,\u0007%\u0001\u0006`_:\u001cVoY2fgN,\u0012!\u001e\t\u0006kYDhl\\\u0005\u0003oZ\u0012\u0011BR;oGRLwN\u001c\u001a\u0011\u0005}J\u0018B\u0001>A\u0005=!&/\u00198ta>\u0014Ho\u00117jK:$\u0018aC0p]N+8mY3tg\u0002\na\u0001P5oSRtDC\u0002@\u0000\u0003\u0003\t\u0019\u0001\u0005\u0002<\u0001!)Al\u0002a\u0001=\")qm\u0002a\u0001S\")1o\u0002a\u0001kV\t\u00010\u0001\u0006dY&,g\u000e^0%KF$2a\\A\u0006\u0011!\ti!CA\u0001\u0002\u0004A\u0018a\u0001=%c\u000591\r\\5f]R\u0004\u0013!\u0003:fcV,7\u000f^%e+\t\t)\u0002E\u00026\u0003/I1!!\u00077\u0005\u0011auN\\4\u0002\u001bI,\u0017/^3ti&#w\fJ3r)\ry\u0017q\u0004\u0005\n\u0003\u001ba\u0011\u0011!a\u0001\u0003+\t!B]3rk\u0016\u001cH/\u00133!\u0003!\u0019XM\u001c3XSRDGcA8\u0002(!)\u0011I\u0004a\u0001q\u0006\u0001\"/Z7pm\u0016\u0014\u0006o\u0019*fcV,7\u000f\u001e\u000b\u0002_\u0006IqN\u001c+j[\u0016|W\u000f^\u0001\b_:\f%m\u001c:u\u0003%ygNR1jYV\u0014X\rF\u0002p\u0003kAa!a\u000e\u0013\u0001\u0004a\u0017!A3\u0002\u0013=t7+^2dKN\u001cHcA8\u0002>!1\u0011qH\nA\u0002y\u000b\u0001B]3ta>t7/Z\u0001\u0005G>\u0004\u0018\u0010F\u0004\u007f\u0003\u000b\n9%!\u0013\t\u000fq#\u0002\u0013!a\u0001=\"9q\r\u0006I\u0001\u0002\u0004I\u0007bB:\u0015!\u0003\u0005\r!^\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\tyEK\u0002_\u0003#Z#!a\u0015\u0011\t\u0005U\u0013qL\u0007\u0003\u0003/RA!!\u0017\u0002\\\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003;2\u0014AC1o]>$\u0018\r^5p]&!\u0011\u0011MA,\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\t9GK\u0002j\u0003#\nabY8qs\u0012\"WMZ1vYR$3'\u0006\u0002\u0002n)\u001aQ/!\u0015\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\t\u0019\b\u0005\u0003\u0002v\u0005mTBAA<\u0015\r\tIHY\u0001\u0005Y\u0006tw-\u0003\u0003\u0002~\u0005]$AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002\u0004B\u0019Q'!\"\n\u0007\u0005\u001deGA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002\u000e\u0006M\u0005cA\u001b\u0002\u0010&\u0019\u0011\u0011\u0013\u001c\u0003\u0007\u0005s\u0017\u0010C\u0005\u0002\u000ei\t\t\u00111\u0001\u0002\u0004\u0006y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002\u001aB1\u00111TAQ\u0003\u001bk!!!(\u000b\u0007\u0005}e'\u0001\u0006d_2dWm\u0019;j_:LA!a)\u0002\u001e\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\tI+a,\u0011\u0007U\nY+C\u0002\u0002.Z\u0012qAQ8pY\u0016\fg\u000eC\u0005\u0002\u000eq\t\t\u00111\u0001\u0002\u000e\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\t\u0019(!.\t\u0013\u00055Q$!AA\u0002\u0005\r\u0015\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005\r\u0015\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005M\u0014AB3rk\u0006d7\u000f\u0006\u0003\u0002*\u0006\r\u0007\"CA\u0007A\u0005\u0005\t\u0019AAG\u0003A\u0011\u0006oY(vi\n|\u00070T3tg\u0006<W\r\u0005\u0002<EM)!%a3\u0002XBA\u0011QZAj=&,h0\u0004\u0002\u0002P*\u0019\u0011\u0011\u001b\u001c\u0002\u000fI,h\u000e^5nK&!\u0011Q[Ah\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gn\r\t\u0005\u00033\fy.\u0004\u0002\u0002\\*\u0019\u0011Q\u001c2\u0002\u0005%|\u0017b\u0001.\u0002\\R\u0011\u0011qY\u0001\u0006CB\u0004H.\u001f\u000b\b}\u0006\u001d\u0018\u0011^Av\u0011\u0015aV\u00051\u0001_\u0011\u00159W\u00051\u0001j\u0011\u0015\u0019X\u00051\u0001v\u0003\u001d)h.\u00199qYf$B!!=\u0002~B)Q'a=\u0002x&\u0019\u0011Q\u001f\u001c\u0003\r=\u0003H/[8o!\u0019)\u0014\u0011 0jk&\u0019\u00111 \u001c\u0003\rQ+\b\u000f\\34\u0011!\tyPJA\u0001\u0002\u0004q\u0018a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011!Q\u0001\t\u0005\u0003k\u00129!\u0003\u0003\u0003\n\u0005]$AB(cU\u0016\u001cG\u000f"
)
public class RpcOutboxMessage implements OutboxMessage, RpcResponseCallback, Logging, Product, Serializable {
   private final ByteBuffer content;
   private final Function1 _onFailure;
   private final Function2 _onSuccess;
   private TransportClient client;
   private long requestId;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Option unapply(final RpcOutboxMessage x$0) {
      return RpcOutboxMessage$.MODULE$.unapply(x$0);
   }

   public static RpcOutboxMessage apply(final ByteBuffer content, final Function1 _onFailure, final Function2 _onSuccess) {
      return RpcOutboxMessage$.MODULE$.apply(content, _onFailure, _onSuccess);
   }

   public static Function1 tupled() {
      return RpcOutboxMessage$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return RpcOutboxMessage$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public ByteBuffer content() {
      return this.content;
   }

   public Function1 _onFailure() {
      return this._onFailure;
   }

   public Function2 _onSuccess() {
      return this._onSuccess;
   }

   private TransportClient client() {
      return this.client;
   }

   private void client_$eq(final TransportClient x$1) {
      this.client = x$1;
   }

   private long requestId() {
      return this.requestId;
   }

   private void requestId_$eq(final long x$1) {
      this.requestId = x$1;
   }

   public void sendWith(final TransportClient client) {
      this.client_$eq(client);
      this.requestId_$eq(client.sendRpc(this.content(), this));
   }

   public void removeRpcRequest() {
      if (this.client() != null) {
         this.client().removeRpcRequest(this.requestId());
      } else {
         this.logError((Function0)(() -> "Ask terminated before connecting successfully"));
      }
   }

   public void onTimeout() {
      this.removeRpcRequest();
   }

   public void onAbort() {
      this.removeRpcRequest();
   }

   public void onFailure(final Throwable e) {
      this._onFailure().apply(e);
   }

   public void onSuccess(final ByteBuffer response) {
      this._onSuccess().apply(this.client(), response);
   }

   public RpcOutboxMessage copy(final ByteBuffer content, final Function1 _onFailure, final Function2 _onSuccess) {
      return new RpcOutboxMessage(content, _onFailure, _onSuccess);
   }

   public ByteBuffer copy$default$1() {
      return this.content();
   }

   public Function1 copy$default$2() {
      return this._onFailure();
   }

   public Function2 copy$default$3() {
      return this._onSuccess();
   }

   public String productPrefix() {
      return "RpcOutboxMessage";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.content();
         }
         case 1 -> {
            return this._onFailure();
         }
         case 2 -> {
            return this._onSuccess();
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
      return x$1 instanceof RpcOutboxMessage;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "content";
         }
         case 1 -> {
            return "_onFailure";
         }
         case 2 -> {
            return "_onSuccess";
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
         label63: {
            if (x$1 instanceof RpcOutboxMessage) {
               label56: {
                  RpcOutboxMessage var4 = (RpcOutboxMessage)x$1;
                  ByteBuffer var10000 = this.content();
                  ByteBuffer var5 = var4.content();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label56;
                  }

                  Function1 var8 = this._onFailure();
                  Function1 var6 = var4._onFailure();
                  if (var8 == null) {
                     if (var6 != null) {
                        break label56;
                     }
                  } else if (!var8.equals(var6)) {
                     break label56;
                  }

                  Function2 var9 = this._onSuccess();
                  Function2 var7 = var4._onSuccess();
                  if (var9 == null) {
                     if (var7 != null) {
                        break label56;
                     }
                  } else if (!var9.equals(var7)) {
                     break label56;
                  }

                  if (var4.canEqual(this)) {
                     break label63;
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

   public RpcOutboxMessage(final ByteBuffer content, final Function1 _onFailure, final Function2 _onSuccess) {
      this.content = content;
      this._onFailure = _onFailure;
      this._onSuccess = _onSuccess;
      Logging.$init$(this);
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
