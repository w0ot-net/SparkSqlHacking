package io.netty.channel.pool;

import io.netty.channel.Channel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.Promise;
import java.io.Closeable;

public interface ChannelPool extends Closeable {
   Future acquire();

   Future acquire(Promise var1);

   Future release(Channel var1);

   Future release(Channel var1, Promise var2);

   void close();
}
