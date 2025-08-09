package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import org.apache.spark.rpc.RpcEndpointRef;
import scala.Option;
import scala.Some;
import scala.Tuple5;
import scala.None.;
import scala.runtime.AbstractFunction5;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class RegisterReceiver$ extends AbstractFunction5 implements Serializable {
   public static final RegisterReceiver$ MODULE$ = new RegisterReceiver$();

   public final String toString() {
      return "RegisterReceiver";
   }

   public RegisterReceiver apply(final int streamId, final String typ, final String host, final String executorId, final RpcEndpointRef receiverEndpoint) {
      return new RegisterReceiver(streamId, typ, host, executorId, receiverEndpoint);
   }

   public Option unapply(final RegisterReceiver x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(BoxesRunTime.boxToInteger(x$0.streamId()), x$0.typ(), x$0.host(), x$0.executorId(), x$0.receiverEndpoint())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RegisterReceiver$.class);
   }

   private RegisterReceiver$() {
   }
}
