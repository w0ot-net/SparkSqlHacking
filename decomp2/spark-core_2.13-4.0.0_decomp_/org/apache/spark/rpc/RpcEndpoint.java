package org.apache.spark.rpc;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkException;
import scala.Function1;
import scala.PartialFunction;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005y3\u0001\"\u0004\b\u0011\u0002\u0007\u0005\u0001C\u0006\u0005\u0006;\u0001!\ta\b\u0005\bG\u0001\u0011\rQ\"\u0001%\u0011\u0015I\u0003\u0001\"\u0002+\u0011\u0015q\u0003\u0001\"\u00010\u0011\u00151\u0004\u0001\"\u00018\u0011\u0015i\u0004\u0001\"\u0001?\u0011\u0015i\u0005\u0001\"\u0001O\u0011\u0015!\u0006\u0001\"\u0001V\u0011\u00159\u0006\u0001\"\u0001Y\u0011\u0015Y\u0006\u0001\"\u0001 \u0011\u0015a\u0006\u0001\"\u0001 \u0011\u0015i\u0006\u0001\"\u0002 \u0005-\u0011\u0006oY#oIB|\u0017N\u001c;\u000b\u0005=\u0001\u0012a\u0001:qG*\u0011\u0011CE\u0001\u0006gB\f'o\u001b\u0006\u0003'Q\ta!\u00199bG\",'\"A\u000b\u0002\u0007=\u0014xm\u0005\u0002\u0001/A\u0011\u0001dG\u0007\u00023)\t!$A\u0003tG\u0006d\u0017-\u0003\u0002\u001d3\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$3\u0001\u0001\u000b\u0002AA\u0011\u0001$I\u0005\u0003Ee\u0011A!\u00168ji\u00061!\u000f]2F]Z,\u0012!\n\t\u0003M\u001dj\u0011AD\u0005\u0003Q9\u0011aA\u00159d\u000b:4\u0018\u0001B:fY\u001a,\u0012a\u000b\t\u0003M1J!!\f\b\u0003\u001dI\u00038-\u00128ea>Lg\u000e\u001e*fM\u00069!/Z2fSZ,W#\u0001\u0019\u0011\ta\t4\u0007I\u0005\u0003ee\u0011q\u0002U1si&\fGNR;oGRLwN\u001c\t\u00031QJ!!N\r\u0003\u0007\u0005s\u00170A\bsK\u000e,\u0017N^3B]\u0012\u0014V\r\u001d7z)\t\u0001\u0004\bC\u0003:\u000b\u0001\u0007!(A\u0004d_:$X\r\u001f;\u0011\u0005\u0019Z\u0014B\u0001\u001f\u000f\u00059\u0011\u0006oY\"bY2\u001cuN\u001c;fqR\fqa\u001c8FeJ|'\u000f\u0006\u0002!\u007f!)\u0001I\u0002a\u0001\u0003\u0006)1-Y;tKB\u0011!I\u0013\b\u0003\u0007\"s!\u0001R$\u000e\u0003\u0015S!A\u0012\u0010\u0002\rq\u0012xn\u001c;?\u0013\u0005Q\u0012BA%\u001a\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u0013'\u0003\u0013QC'o\\<bE2,'BA%\u001a\u0003-ygnQ8o]\u0016\u001cG/\u001a3\u0015\u0005\u0001z\u0005\"\u0002)\b\u0001\u0004\t\u0016!\u0004:f[>$X-\u00113ee\u0016\u001c8\u000f\u0005\u0002'%&\u00111K\u0004\u0002\u000b%B\u001c\u0017\t\u001a3sKN\u001c\u0018AD8o\t&\u001c8m\u001c8oK\u000e$X\r\u001a\u000b\u0003AYCQ\u0001\u0015\u0005A\u0002E\u000bab\u001c8OKR<xN]6FeJ|'\u000fF\u0002!3jCQ\u0001Q\u0005A\u0002\u0005CQ\u0001U\u0005A\u0002E\u000bqa\u001c8Ti\u0006\u0014H/\u0001\u0004p]N#x\u000e]\u0001\u0005gR|\u0007\u000f"
)
public interface RpcEndpoint {
   RpcEnv rpcEnv();

   // $FF: synthetic method
   static RpcEndpointRef self$(final RpcEndpoint $this) {
      return $this.self();
   }

   default RpcEndpointRef self() {
      .MODULE$.require(this.rpcEnv() != null, () -> "rpcEnv has not been initialized");
      return this.rpcEnv().endpointRef(this);
   }

   // $FF: synthetic method
   static PartialFunction receive$(final RpcEndpoint $this) {
      return $this.receive();
   }

   default PartialFunction receive() {
      return new Serializable() {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final RpcEndpoint $outer;

         public final Object applyOrElse(final Object x1, final Function1 default) {
            throw new SparkException(this.$outer.self() + " does not implement 'receive'");
         }

         public final boolean isDefinedAt(final Object x1) {
            return true;
         }

         public {
            if (RpcEndpoint.this == null) {
               throw null;
            } else {
               this.$outer = RpcEndpoint.this;
            }
         }
      };
   }

   // $FF: synthetic method
   static PartialFunction receiveAndReply$(final RpcEndpoint $this, final RpcCallContext context) {
      return $this.receiveAndReply(context);
   }

   default PartialFunction receiveAndReply(final RpcCallContext context) {
      return new Serializable(context) {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final RpcEndpoint $outer;
         private final RpcCallContext context$1;

         public final Object applyOrElse(final Object x1, final Function1 default) {
            this.context$1.sendFailure(new SparkException(this.$outer.self() + " won't reply anything"));
            return BoxedUnit.UNIT;
         }

         public final boolean isDefinedAt(final Object x1) {
            return true;
         }

         public {
            if (RpcEndpoint.this == null) {
               throw null;
            } else {
               this.$outer = RpcEndpoint.this;
               this.context$1 = context$1;
            }
         }
      };
   }

   // $FF: synthetic method
   static void onError$(final RpcEndpoint $this, final Throwable cause) {
      $this.onError(cause);
   }

   default void onError(final Throwable cause) {
      throw cause;
   }

   // $FF: synthetic method
   static void onConnected$(final RpcEndpoint $this, final RpcAddress remoteAddress) {
      $this.onConnected(remoteAddress);
   }

   default void onConnected(final RpcAddress remoteAddress) {
   }

   // $FF: synthetic method
   static void onDisconnected$(final RpcEndpoint $this, final RpcAddress remoteAddress) {
      $this.onDisconnected(remoteAddress);
   }

   default void onDisconnected(final RpcAddress remoteAddress) {
   }

   // $FF: synthetic method
   static void onNetworkError$(final RpcEndpoint $this, final Throwable cause, final RpcAddress remoteAddress) {
      $this.onNetworkError(cause, remoteAddress);
   }

   default void onNetworkError(final Throwable cause, final RpcAddress remoteAddress) {
   }

   // $FF: synthetic method
   static void onStart$(final RpcEndpoint $this) {
      $this.onStart();
   }

   default void onStart() {
   }

   // $FF: synthetic method
   static void onStop$(final RpcEndpoint $this) {
      $this.onStop();
   }

   default void onStop() {
   }

   // $FF: synthetic method
   static void stop$(final RpcEndpoint $this) {
      $this.stop();
   }

   default void stop() {
      RpcEndpointRef _self = this.self();
      if (_self != null) {
         this.rpcEnv().stop(_self);
      }
   }

   static void $init$(final RpcEndpoint $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
