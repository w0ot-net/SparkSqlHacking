package org.apache.http.conn.routing;

import java.net.InetAddress;
import org.apache.http.HttpHost;

public interface RouteInfo {
   HttpHost getTargetHost();

   InetAddress getLocalAddress();

   int getHopCount();

   HttpHost getHopTarget(int var1);

   HttpHost getProxyHost();

   TunnelType getTunnelType();

   boolean isTunnelled();

   LayerType getLayerType();

   boolean isLayered();

   boolean isSecure();

   public static enum TunnelType {
      PLAIN,
      TUNNELLED;
   }

   public static enum LayerType {
      PLAIN,
      LAYERED;
   }
}
