package org.apache.zookeeper.client;

import java.net.InetSocketAddress;
import java.util.Collection;
import org.apache.yetus.audience.InterfaceAudience.Public;

@Public
public interface HostProvider {
   int size();

   InetSocketAddress next(long var1);

   void onConnected();

   boolean updateServerList(Collection var1, InetSocketAddress var2);
}
