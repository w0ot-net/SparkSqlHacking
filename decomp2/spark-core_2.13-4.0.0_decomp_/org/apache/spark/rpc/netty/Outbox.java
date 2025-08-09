package org.apache.spark.rpc.netty;

import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import javax.annotation.concurrent.GuardedBy;
import org.apache.spark.SparkException;
import org.apache.spark.network.client.TransportClient;
import org.apache.spark.rpc.RpcAddress;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005c!B\r\u001b\u0001i!\u0003\u0002C\u0016\u0001\u0005\u0003\u0005\u000b\u0011B\u0017\t\u0011E\u0002!Q1A\u0005\u0002IB\u0001b\u000e\u0001\u0003\u0002\u0003\u0006Ia\r\u0005\u0006q\u0001!\t!\u000f\u0005\b{\u0001\u0011\r\u0011\"\u0003?\u0011\u0019Q\u0005\u0001)A\u0005\u007f!9\u0011\f\u0001a\u0001\n\u0013Q\u0006b\u00022\u0001\u0001\u0004%Ia\u0019\u0005\u0007S\u0002\u0001\u000b\u0015B.\t\u000f-\u0004\u0001\u0019!C\u0005Y\"9!\u000f\u0001a\u0001\n\u0013\u0019\bBB;\u0001A\u0003&Q\u000eC\u0004x\u0001\u0001\u0007I\u0011\u0002=\t\u000fq\u0004\u0001\u0019!C\u0005{\"1q\u0010\u0001Q!\neD\u0001\"a\u0001\u0001\u0001\u0004%I\u0001\u001f\u0005\n\u0003\u000b\u0001\u0001\u0019!C\u0005\u0003\u000fAq!a\u0003\u0001A\u0003&\u0011\u0010C\u0004\u0002\u0010\u0001!\t!!\u0005\t\u000f\u0005]\u0001\u0001\"\u0003\u0002\u001a!9\u00111\u0004\u0001\u0005\n\u0005e\u0001bBA\u000f\u0001\u0011%\u0011q\u0004\u0005\b\u0003{\u0001A\u0011BA\r\u0011\u001d\ty\u0004\u0001C\u0001\u00033\u0011aaT;uE>D(BA\u000e\u001d\u0003\u0015qW\r\u001e;z\u0015\tib$A\u0002sa\u000eT!a\b\u0011\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u0005\u0012\u0013AB1qC\u000eDWMC\u0001$\u0003\ry'oZ\n\u0003\u0001\u0015\u0002\"AJ\u0015\u000e\u0003\u001dR\u0011\u0001K\u0001\u0006g\u000e\fG.Y\u0005\u0003U\u001d\u0012a!\u00118z%\u00164\u0017\u0001\u00038fiRLXI\u001c<\u0004\u0001A\u0011afL\u0007\u00025%\u0011\u0001G\u0007\u0002\f\u001d\u0016$H/\u001f*qG\u0016sg/A\u0004bI\u0012\u0014Xm]:\u0016\u0003M\u0002\"\u0001N\u001b\u000e\u0003qI!A\u000e\u000f\u0003\u0015I\u00038-\u00113ee\u0016\u001c8/\u0001\u0005bI\u0012\u0014Xm]:!\u0003\u0019a\u0014N\\5u}Q\u0019!h\u000f\u001f\u0011\u00059\u0002\u0001\"B\u0016\u0005\u0001\u0004i\u0003\"B\u0019\u0005\u0001\u0004\u0019\u0014\u0001C7fgN\fw-Z:\u0016\u0003}\u00022\u0001Q#H\u001b\u0005\t%B\u0001\"D\u0003\u0011)H/\u001b7\u000b\u0003\u0011\u000bAA[1wC&\u0011a)\u0011\u0002\u000b\u0019&t7.\u001a3MSN$\bC\u0001\u0018I\u0013\tI%DA\u0007PkR\u0014w\u000e_'fgN\fw-Z\u0001\n[\u0016\u001c8/Y4fg\u0002BCA\u0002'W/B\u0011Q\nV\u0007\u0002\u001d*\u0011q\nU\u0001\u000bG>t7-\u001e:sK:$(BA)S\u0003)\tgN\\8uCRLwN\u001c\u0006\u0002'\u0006)!.\u0019<bq&\u0011QK\u0014\u0002\n\u000fV\f'\u000fZ3e\u0005f\fQA^1mk\u0016\f\u0013\u0001W\u0001\u0005i\"L7/\u0001\u0004dY&,g\u000e^\u000b\u00027B\u0011A\fY\u0007\u0002;*\u0011\u0011L\u0018\u0006\u0003?z\tqA\\3uo>\u00148.\u0003\u0002b;\nyAK]1ogB|'\u000f^\"mS\u0016tG/\u0001\u0006dY&,g\u000e^0%KF$\"\u0001Z4\u0011\u0005\u0019*\u0017B\u00014(\u0005\u0011)f.\u001b;\t\u000f!D\u0011\u0011!a\u00017\u0006\u0019\u0001\u0010J\u0019\u0002\u000f\rd\u0017.\u001a8uA!\"\u0011\u0002\u0014,X\u00035\u0019wN\u001c8fGR4U\u000f^;sKV\tQ\u000eE\u0002oa\u0012l\u0011a\u001c\u0006\u0003\u001f\u0006K!!]8\u0003\r\u0019+H/\u001e:f\u0003E\u0019wN\u001c8fGR4U\u000f^;sK~#S-\u001d\u000b\u0003IRDq\u0001[\u0006\u0002\u0002\u0003\u0007Q.\u0001\bd_:tWm\u0019;GkR,(/\u001a\u0011)\t1aekV\u0001\bgR|\u0007\u000f]3e+\u0005I\bC\u0001\u0014{\u0013\tYxEA\u0004C_>dW-\u00198\u0002\u0017M$x\u000e\u001d9fI~#S-\u001d\u000b\u0003IzDq\u0001\u001b\b\u0002\u0002\u0003\u0007\u00110\u0001\u0005ti>\u0004\b/\u001a3!Q\u0011yAJV,\u0002\u0011\u0011\u0014\u0018-\u001b8j]\u001e\fA\u0002\u001a:bS:LgnZ0%KF$2\u0001ZA\u0005\u0011\u001dA\u0017#!AA\u0002e\f\u0011\u0002\u001a:bS:Lgn\u001a\u0011)\tIaekV\u0001\u0005g\u0016tG\rF\u0002e\u0003'Aa!!\u0006\u0014\u0001\u00049\u0015aB7fgN\fw-Z\u0001\fIJ\f\u0017N\\(vi\n|\u0007\u0010F\u0001e\u0003Ea\u0017-\u001e8dQ\u000e{gN\\3diR\u000b7o[\u0001\u0015Q\u0006tG\r\\3OKR<xN]6GC&dWO]3\u0015\u0007\u0011\f\t\u0003C\u0004\u0002$Y\u0001\r!!\n\u0002\u0003\u0015\u0004B!a\n\u000289!\u0011\u0011FA\u001a\u001d\u0011\tY#!\r\u000e\u0005\u00055\"bAA\u0018Y\u00051AH]8pizJ\u0011\u0001K\u0005\u0004\u0003k9\u0013a\u00029bG.\fw-Z\u0005\u0005\u0003s\tYDA\u0005UQJ|w/\u00192mK*\u0019\u0011QG\u0014\u0002\u0017\rdwn]3DY&,g\u000e^\u0001\u0005gR|\u0007\u000f"
)
public class Outbox {
   public final NettyRpcEnv org$apache$spark$rpc$netty$Outbox$$nettyEnv;
   private final RpcAddress address;
   @GuardedBy("this")
   private final LinkedList messages;
   @GuardedBy("this")
   private TransportClient org$apache$spark$rpc$netty$Outbox$$client;
   @GuardedBy("this")
   private Future org$apache$spark$rpc$netty$Outbox$$connectFuture;
   @GuardedBy("this")
   private boolean org$apache$spark$rpc$netty$Outbox$$stopped;
   @GuardedBy("this")
   private boolean draining;

   public RpcAddress address() {
      return this.address;
   }

   private LinkedList messages() {
      return this.messages;
   }

   private TransportClient client() {
      return this.org$apache$spark$rpc$netty$Outbox$$client;
   }

   public void org$apache$spark$rpc$netty$Outbox$$client_$eq(final TransportClient x$1) {
      this.org$apache$spark$rpc$netty$Outbox$$client = x$1;
   }

   private Future connectFuture() {
      return this.org$apache$spark$rpc$netty$Outbox$$connectFuture;
   }

   public void org$apache$spark$rpc$netty$Outbox$$connectFuture_$eq(final Future x$1) {
      this.org$apache$spark$rpc$netty$Outbox$$connectFuture = x$1;
   }

   public boolean org$apache$spark$rpc$netty$Outbox$$stopped() {
      return this.org$apache$spark$rpc$netty$Outbox$$stopped;
   }

   private void stopped_$eq(final boolean x$1) {
      this.org$apache$spark$rpc$netty$Outbox$$stopped = x$1;
   }

   private boolean draining() {
      return this.draining;
   }

   private void draining_$eq(final boolean x$1) {
      this.draining = x$1;
   }

   public void send(final OutboxMessage message) {
      synchronized(this){}

      boolean var4;
      try {
         boolean var10000;
         if (this.org$apache$spark$rpc$netty$Outbox$$stopped()) {
            var10000 = true;
         } else {
            this.messages().add(message);
            var10000 = false;
         }

         var4 = var10000;
      } catch (Throwable var6) {
         throw var6;
      }

      if (var4) {
         message.onFailure(new SparkException("Message is dropped because Outbox is stopped"));
      } else {
         this.org$apache$spark$rpc$netty$Outbox$$drainOutbox();
      }
   }

   public void org$apache$spark$rpc$netty$Outbox$$drainOutbox() {
      OutboxMessage message = null;
      synchronized(this){}

      try {
         if (this.org$apache$spark$rpc$netty$Outbox$$stopped() || this.connectFuture() != null) {
            return;
         }

         if (this.client() == null) {
            this.launchConnectTask();
            return;
         }

         if (this.draining()) {
            return;
         }

         message = (OutboxMessage)this.messages().poll();
         if (message == null) {
            return;
         }

         this.draining_$eq(true);
      } catch (Throwable var26) {
         throw var26;
      }

      while(true) {
         try {
            synchronized(this){}

            TransportClient var6;
            try {
               var6 = this.client();
            } catch (Throwable var23) {
               throw var23;
            }

            if (var6 != null) {
               message.sendWith(var6);
            } else {
               .MODULE$.assert(this.org$apache$spark$rpc$netty$Outbox$$stopped());
            }
         } catch (Throwable var24) {
            if (var24 != null && scala.util.control.NonFatal..MODULE$.apply(var24)) {
               this.org$apache$spark$rpc$netty$Outbox$$handleNetworkFailure(var24);
               return;
            }

            throw var24;
         }

         synchronized(this){}

         try {
            if (this.org$apache$spark$rpc$netty$Outbox$$stopped()) {
               break;
            }

            message = (OutboxMessage)this.messages().poll();
            if (message == null) {
               this.draining_$eq(false);
               break;
            }
         } catch (Throwable var25) {
            throw var25;
         }
      }

   }

   private void launchConnectTask() {
      this.org$apache$spark$rpc$netty$Outbox$$connectFuture_$eq(this.org$apache$spark$rpc$netty$Outbox$$nettyEnv.clientConnectionExecutor().submit(new Callable() {
         // $FF: synthetic field
         private final Outbox $outer;

         public void call() {
            try {
               TransportClient _client = this.$outer.org$apache$spark$rpc$netty$Outbox$$nettyEnv.createClient(this.$outer.address());
               synchronized(this.$outer){}

               try {
                  this.$outer.org$apache$spark$rpc$netty$Outbox$$client_$eq(_client);
                  if (this.$outer.org$apache$spark$rpc$netty$Outbox$$stopped()) {
                     this.$outer.org$apache$spark$rpc$netty$Outbox$$closeClient();
                  }
               } catch (Throwable var23) {
                  throw var23;
               }
            } catch (Throwable var24) {
               if (var24 instanceof InterruptedException) {
                  return;
               }

               if (var24 != null && scala.util.control.NonFatal..MODULE$.apply(var24)) {
                  synchronized(this.$outer){}

                  try {
                     this.$outer.org$apache$spark$rpc$netty$Outbox$$connectFuture_$eq((Future)null);
                  } catch (Throwable var21) {
                     throw var21;
                  }

                  this.$outer.org$apache$spark$rpc$netty$Outbox$$handleNetworkFailure(var24);
                  return;
               }

               throw var24;
            }

            synchronized(this.$outer){}

            try {
               this.$outer.org$apache$spark$rpc$netty$Outbox$$connectFuture_$eq((Future)null);
            } catch (Throwable var22) {
               throw var22;
            }

            this.$outer.org$apache$spark$rpc$netty$Outbox$$drainOutbox();
         }

         public {
            if (Outbox.this == null) {
               throw null;
            } else {
               this.$outer = Outbox.this;
            }
         }
      }));
   }

   public void org$apache$spark$rpc$netty$Outbox$$handleNetworkFailure(final Throwable e) {
      synchronized(this){}

      try {
         .MODULE$.assert(this.connectFuture() == null);
         if (this.org$apache$spark$rpc$netty$Outbox$$stopped()) {
            return;
         }

         this.stopped_$eq(true);
         this.org$apache$spark$rpc$netty$Outbox$$closeClient();
      } catch (Throwable var5) {
         throw var5;
      }

      this.org$apache$spark$rpc$netty$Outbox$$nettyEnv.removeOutbox(this.address());

      for(OutboxMessage message = (OutboxMessage)this.messages().poll(); message != null; message = (OutboxMessage)this.messages().poll()) {
         message.onFailure(e);
      }

      .MODULE$.assert(this.messages().isEmpty());
   }

   public synchronized void org$apache$spark$rpc$netty$Outbox$$closeClient() {
      this.org$apache$spark$rpc$netty$Outbox$$client_$eq((TransportClient)null);
   }

   public void stop() {
      synchronized(this){}

      try {
         if (this.org$apache$spark$rpc$netty$Outbox$$stopped()) {
            return;
         }

         this.stopped_$eq(true);
         if (this.connectFuture() != null) {
            BoxesRunTime.boxToBoolean(this.connectFuture().cancel(true));
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         this.org$apache$spark$rpc$netty$Outbox$$closeClient();
      } catch (Throwable var4) {
         throw var4;
      }

      for(OutboxMessage message = (OutboxMessage)this.messages().poll(); message != null; message = (OutboxMessage)this.messages().poll()) {
         message.onFailure(new SparkException("Message is dropped because Outbox is stopped"));
      }

   }

   public Outbox(final NettyRpcEnv nettyEnv, final RpcAddress address) {
      this.org$apache$spark$rpc$netty$Outbox$$nettyEnv = nettyEnv;
      this.address = address;
      this.messages = new LinkedList();
      this.org$apache$spark$rpc$netty$Outbox$$client = null;
      this.org$apache$spark$rpc$netty$Outbox$$connectFuture = null;
      this.org$apache$spark$rpc$netty$Outbox$$stopped = false;
      this.draining = false;
   }
}
