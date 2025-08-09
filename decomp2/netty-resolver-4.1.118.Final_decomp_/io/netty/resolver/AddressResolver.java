package io.netty.resolver;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.Promise;
import java.io.Closeable;
import java.net.SocketAddress;

public interface AddressResolver extends Closeable {
   boolean isSupported(SocketAddress var1);

   boolean isResolved(SocketAddress var1);

   Future resolve(SocketAddress var1);

   Future resolve(SocketAddress var1, Promise var2);

   Future resolveAll(SocketAddress var1);

   Future resolveAll(SocketAddress var1, Promise var2);

   void close();
}
