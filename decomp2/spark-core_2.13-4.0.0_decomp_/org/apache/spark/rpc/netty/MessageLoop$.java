package org.apache.spark.rpc.netty;

import org.apache.spark.rpc.RpcEndpoint;

public final class MessageLoop$ {
   public static final MessageLoop$ MODULE$ = new MessageLoop$();
   private static final Inbox PoisonPill = new Inbox((String)null, (RpcEndpoint)null);

   public Inbox PoisonPill() {
      return PoisonPill;
   }

   private MessageLoop$() {
   }
}
