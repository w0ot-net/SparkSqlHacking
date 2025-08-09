package org.apache.spark.rpc.netty;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.network.client.TransportClient;
import org.apache.spark.rpc.RpcEnvStoppedException;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.StringContext;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\re!\u0002\r\u001a\u0001f\u0019\u0003\u0002\u0003#\u0001\u0005+\u0007I\u0011A#\t\u00119\u0003!\u0011#Q\u0001\n\u0019CQa\u0014\u0001\u0005\u0002ACQa\u0015\u0001\u0005BQCQ!\u0019\u0001\u0005B\tDq\u0001\u001b\u0001\u0002\u0002\u0013\u0005\u0011\u000eC\u0004l\u0001E\u0005I\u0011\u00017\t\u000f]\u0004\u0011\u0011!C!q\"Aq\u0010AA\u0001\n\u0003\t\t\u0001C\u0005\u0002\n\u0001\t\t\u0011\"\u0001\u0002\f!I\u0011q\u0003\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0004\u0005\n\u0003O\u0001\u0011\u0011!C\u0001\u0003SA\u0011\"a\r\u0001\u0003\u0003%\t%!\u000e\t\u0013\u0005e\u0002!!A\u0005B\u0005m\u0002\"CA\u001f\u0001\u0005\u0005I\u0011IA \u0011%\t\t\u0005AA\u0001\n\u0003\n\u0019e\u0002\u0006\u0002He\t\t\u0011#\u0001\u001a\u0003\u00132\u0011\u0002G\r\u0002\u0002#\u0005\u0011$a\u0013\t\r=\u0013B\u0011AA2\u0011%\tiDEA\u0001\n\u000b\ny\u0004C\u0005\u0002fI\t\t\u0011\"!\u0002h!I\u00111\u000e\n\u0002\u0002\u0013\u0005\u0015Q\u000e\u0005\n\u0003s\u0012\u0012\u0011!C\u0005\u0003w\u00121c\u00148f/\u0006Lx*\u001e;c_blUm]:bO\u0016T!AG\u000e\u0002\u000b9,G\u000f^=\u000b\u0005qi\u0012a\u0001:qG*\u0011adH\u0001\u0006gB\f'o\u001b\u0006\u0003A\u0005\na!\u00199bG\",'\"\u0001\u0012\u0002\u0007=\u0014xm\u0005\u0004\u0001I)rCg\u000e\t\u0003K!j\u0011A\n\u0006\u0002O\u0005)1oY1mC&\u0011\u0011F\n\u0002\u0007\u0003:L(+\u001a4\u0011\u0005-bS\"A\r\n\u00055J\"!D(vi\n|\u00070T3tg\u0006<W\r\u0005\u00020e5\t\u0001G\u0003\u00022;\u0005A\u0011N\u001c;fe:\fG.\u0003\u00024a\t9Aj\\4hS:<\u0007CA\u00136\u0013\t1dEA\u0004Qe>$Wo\u0019;\u0011\u0005a\neBA\u001d@\u001d\tQd(D\u0001<\u0015\taT(\u0001\u0004=e>|GOP\u0002\u0001\u0013\u00059\u0013B\u0001!'\u0003\u001d\u0001\u0018mY6bO\u0016L!AQ\"\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005\u00013\u0013aB2p]R,g\u000e^\u000b\u0002\rB\u0011q\tT\u0007\u0002\u0011*\u0011\u0011JS\u0001\u0004]&|'\"A&\u0002\t)\fg/Y\u0005\u0003\u001b\"\u0013!BQ=uK\n+hMZ3s\u0003!\u0019wN\u001c;f]R\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002R%B\u00111\u0006\u0001\u0005\u0006\t\u000e\u0001\rAR\u0001\tg\u0016tGmV5uQR\u0011Q\u000b\u0017\t\u0003KYK!a\u0016\u0014\u0003\tUs\u0017\u000e\u001e\u0005\u00063\u0012\u0001\rAW\u0001\u0007G2LWM\u001c;\u0011\u0005m{V\"\u0001/\u000b\u0005ek&B\u00010\u001e\u0003\u001dqW\r^<pe.L!\u0001\u0019/\u0003\u001fQ\u0013\u0018M\\:q_J$8\t\\5f]R\f\u0011b\u001c8GC&dWO]3\u0015\u0005U\u001b\u0007\"\u00023\u0006\u0001\u0004)\u0017!A3\u0011\u0005a2\u0017BA4D\u0005%!\u0006N]8xC\ndW-\u0001\u0003d_BLHCA)k\u0011\u001d!e\u0001%AA\u0002\u0019\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001nU\t1enK\u0001p!\t\u0001X/D\u0001r\u0015\t\u00118/A\u0005v]\u000eDWmY6fI*\u0011AOJ\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001<r\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003e\u0004\"A_?\u000e\u0003mT!\u0001 &\u0002\t1\fgnZ\u0005\u0003}n\u0014aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLXCAA\u0002!\r)\u0013QA\u0005\u0004\u0003\u000f1#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA\u0007\u0003'\u00012!JA\b\u0013\r\t\tB\n\u0002\u0004\u0003:L\b\"CA\u000b\u0015\u0005\u0005\t\u0019AA\u0002\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u00111\u0004\t\u0007\u0003;\t\u0019#!\u0004\u000e\u0005\u0005}!bAA\u0011M\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005\u0015\u0012q\u0004\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002,\u0005E\u0002cA\u0013\u0002.%\u0019\u0011q\u0006\u0014\u0003\u000f\t{w\u000e\\3b]\"I\u0011Q\u0003\u0007\u0002\u0002\u0003\u0007\u0011QB\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002z\u0003oA\u0011\"!\u0006\u000e\u0003\u0003\u0005\r!a\u0001\u0002\u0011!\f7\u000f[\"pI\u0016$\"!a\u0001\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012!_\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005-\u0012Q\t\u0005\n\u0003+\u0001\u0012\u0011!a\u0001\u0003\u001b\t1c\u00148f/\u0006Lx*\u001e;c_blUm]:bO\u0016\u0004\"a\u000b\n\u0014\u000bI\ti%!\u0017\u0011\r\u0005=\u0013Q\u000b$R\u001b\t\t\tFC\u0002\u0002T\u0019\nqA];oi&lW-\u0003\u0003\u0002X\u0005E#!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ocA!\u00111LA1\u001b\t\tiFC\u0002\u0002`)\u000b!![8\n\u0007\t\u000bi\u0006\u0006\u0002\u0002J\u0005)\u0011\r\u001d9msR\u0019\u0011+!\u001b\t\u000b\u0011+\u0002\u0019\u0001$\u0002\u000fUt\u0017\r\u001d9msR!\u0011qNA;!\u0011)\u0013\u0011\u000f$\n\u0007\u0005MdE\u0001\u0004PaRLwN\u001c\u0005\t\u0003o2\u0012\u0011!a\u0001#\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005u\u0004c\u0001>\u0002\u0000%\u0019\u0011\u0011Q>\u0003\r=\u0013'.Z2u\u0001"
)
public class OneWayOutboxMessage implements OutboxMessage, Logging, Product, Serializable {
   private final ByteBuffer content;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Option unapply(final OneWayOutboxMessage x$0) {
      return OneWayOutboxMessage$.MODULE$.unapply(x$0);
   }

   public static OneWayOutboxMessage apply(final ByteBuffer content) {
      return OneWayOutboxMessage$.MODULE$.apply(content);
   }

   public static Function1 andThen(final Function1 g) {
      return OneWayOutboxMessage$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return OneWayOutboxMessage$.MODULE$.compose(g);
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

   public void sendWith(final TransportClient client) {
      client.send(this.content());
   }

   public void onFailure(final Throwable e) {
      if (e instanceof RpcEnvStoppedException var4) {
         this.logDebug((Function0)(() -> var4.getMessage()));
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else if (e != null) {
         this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to send one-way RPC."})))).log(scala.collection.immutable.Nil..MODULE$)), e);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(e);
      }
   }

   public OneWayOutboxMessage copy(final ByteBuffer content) {
      return new OneWayOutboxMessage(content);
   }

   public ByteBuffer copy$default$1() {
      return this.content();
   }

   public String productPrefix() {
      return "OneWayOutboxMessage";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.content();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof OneWayOutboxMessage;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "content";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof OneWayOutboxMessage) {
               label40: {
                  OneWayOutboxMessage var4 = (OneWayOutboxMessage)x$1;
                  ByteBuffer var10000 = this.content();
                  ByteBuffer var5 = var4.content();
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

   public OneWayOutboxMessage(final ByteBuffer content) {
      this.content = content;
      Logging.$init$(this);
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
