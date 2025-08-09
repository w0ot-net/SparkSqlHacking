package org.apache.spark.api.r;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.timeout.ReadTimeoutHandler;
import java.lang.invoke.SerializedLambda;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkEnv$;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.config.R$;
import org.apache.spark.network.util.IOMode;
import org.apache.spark.network.util.NettyUtils;
import scala.StringContext;
import scala.Tuple2;
import scala.Option.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000554QAD\b\u0001'eAQ\u0001\t\u0001\u0005\u0002\tBa!\n\u0001!B\u00131\u0003B\u0002\u0019\u0001A\u0003&\u0011\u0007\u0003\u00047\u0001\u0001\u0006Ka\u000e\u0005\tu\u0001\u0011\r\u0011\"\u0001\u0010w!1q\b\u0001Q\u0001\nqBQ\u0001\u0011\u0001\u0005\u0002\u0005CQa\u0013\u0001\u0005\u00021CQ\u0001\u0015\u0001\u0005\u00021;a!U\b\t\u0002M\u0011fA\u0002\b\u0010\u0011\u0003\u00192\u000bC\u0003!\u0017\u0011\u0005!\fC\u0003\\\u0017\u0011\u0005AL\u0001\u0005S\u0005\u0006\u001c7.\u001a8e\u0015\t\u0001\u0012#A\u0001s\u0015\t\u00112#A\u0002ba&T!\u0001F\u000b\u0002\u000bM\u0004\u0018M]6\u000b\u0005Y9\u0012AB1qC\u000eDWMC\u0001\u0019\u0003\ry'oZ\n\u0003\u0001i\u0001\"a\u0007\u0010\u000e\u0003qQ\u0011!H\u0001\u0006g\u000e\fG.Y\u0005\u0003?q\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003\r\u0002\"\u0001\n\u0001\u000e\u0003=\tQb\u00195b]:,GNR;ukJ,\u0007CA\u0014/\u001b\u0005A#BA\u0015+\u0003\u001d\u0019\u0007.\u00198oK2T!a\u000b\u0017\u0002\u000b9,G\u000f^=\u000b\u00035\n!![8\n\u0005=B#!D\"iC:tW\r\u001c$viV\u0014X-A\u0005c_>$8\u000f\u001e:baB\u0011!\u0007N\u0007\u0002g)\u0011\u0001GK\u0005\u0003kM\u0012qbU3sm\u0016\u0014(i\\8ugR\u0014\u0018\r]\u0001\nE>\u001c8o\u0012:pkB\u0004\"a\n\u001d\n\u0005eB#AD#wK:$Hj\\8q\u000fJ|W\u000f]\u0001\u0011UZlwJ\u00196fGR$&/Y2lKJ,\u0012\u0001\u0010\t\u0003IuJ!AP\b\u0003!)3Vj\u00142kK\u000e$HK]1dW\u0016\u0014\u0018!\u00056w[>\u0013'.Z2u)J\f7m[3sA\u0005!\u0011N\\5u)\u0005\u0011\u0005\u0003B\u000eD\u000b\"K!\u0001\u0012\u000f\u0003\rQ+\b\u000f\\33!\tYb)\u0003\u0002H9\t\u0019\u0011J\u001c;\u0011\u0005\u0011J\u0015B\u0001&\u0010\u0005-\u0011\u0016)\u001e;i\u0011\u0016d\u0007/\u001a:\u0002\u0007I,h\u000eF\u0001N!\tYb*\u0003\u0002P9\t!QK\\5u\u0003\u0015\u0019Gn\\:f\u0003!\u0011&)Y2lK:$\u0007C\u0001\u0013\f'\rY!\u0004\u0016\t\u0003+bk\u0011A\u0016\u0006\u0003/N\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u00033Z\u0013q\u0001T8hO&tw\rF\u0001S\u0003\u0011i\u0017-\u001b8\u0015\u00055k\u0006\"\u00020\u000e\u0001\u0004y\u0016\u0001B1sON\u00042a\u00071c\u0013\t\tGDA\u0003BeJ\f\u0017\u0010\u0005\u0002dU:\u0011A\r\u001b\t\u0003Kri\u0011A\u001a\u0006\u0003O\u0006\na\u0001\u0010:p_Rt\u0014BA5\u001d\u0003\u0019\u0001&/\u001a3fM&\u00111\u000e\u001c\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005%d\u0002"
)
public class RBackend {
   private ChannelFuture channelFuture = null;
   private ServerBootstrap bootstrap = null;
   private EventLoopGroup bossGroup = null;
   private final JVMObjectTracker jvmObjectTracker = new JVMObjectTracker();

   public static void main(final String[] args) {
      RBackend$.MODULE$.main(args);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return RBackend$.MODULE$.LogStringContext(sc);
   }

   public JVMObjectTracker jvmObjectTracker() {
      return this.jvmObjectTracker;
   }

   public Tuple2 init() {
      SparkConf conf = (SparkConf).MODULE$.apply(SparkEnv$.MODULE$.get()).map((x$1) -> x$1.conf()).getOrElse(() -> new SparkConf());
      int backendConnectionTimeout = BoxesRunTime.unboxToInt(conf.get(R$.MODULE$.R_BACKEND_CONNECTION_TIMEOUT()));
      this.bossGroup = NettyUtils.createEventLoop(IOMode.NIO, BoxesRunTime.unboxToInt(conf.get(R$.MODULE$.R_NUM_BACKEND_THREADS())), "RBackend");
      EventLoopGroup workerGroup = this.bossGroup;
      RBackendHandler handler = new RBackendHandler(this);
      RAuthHelper authHelper = new RAuthHelper(conf);
      Class channelClass = NettyUtils.getServerChannelClass(IOMode.NIO);
      this.bootstrap = (ServerBootstrap)(new ServerBootstrap()).group(this.bossGroup, workerGroup).channel(channelClass);
      this.bootstrap.childHandler(new ChannelInitializer(backendConnectionTimeout, authHelper, handler) {
         private final int backendConnectionTimeout$1;
         private final RAuthHelper authHelper$1;
         private final RBackendHandler handler$1;

         public void initChannel(final SocketChannel ch) {
            ch.pipeline().addLast("encoder", new ByteArrayEncoder()).addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4)).addLast("decoder", new ByteArrayDecoder()).addLast("readTimeoutHandler", new ReadTimeoutHandler(this.backendConnectionTimeout$1)).addLast(new ChannelHandler[]{new RBackendAuthHandler(this.authHelper$1.secret())}).addLast("handler", this.handler$1);
         }

         public {
            this.backendConnectionTimeout$1 = backendConnectionTimeout$1;
            this.authHelper$1 = authHelper$1;
            this.handler$1 = handler$1;
         }
      });
      this.channelFuture = this.bootstrap.bind(new InetSocketAddress("localhost", 0));
      this.channelFuture.syncUninterruptibly();
      int port = ((InetSocketAddress)this.channelFuture.channel().localAddress()).getPort();
      return new Tuple2(BoxesRunTime.boxToInteger(port), authHelper);
   }

   public void run() {
      this.channelFuture.channel().closeFuture().syncUninterruptibly();
   }

   public void close() {
      if (this.channelFuture != null) {
         this.channelFuture.channel().close().awaitUninterruptibly(10L, TimeUnit.SECONDS);
         this.channelFuture = null;
      }

      if (this.bootstrap != null && this.bootstrap.config().group() != null) {
         this.bootstrap.config().group().shutdownGracefully();
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      if (this.bootstrap != null && this.bootstrap.config().childGroup() != null) {
         this.bootstrap.config().childGroup().shutdownGracefully();
      } else {
         BoxedUnit var1 = BoxedUnit.UNIT;
      }

      this.bootstrap = null;
      this.jvmObjectTracker().clear();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
