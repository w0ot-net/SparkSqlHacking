package io.fabric8.kubernetes.client;

import java.net.InetAddress;

public interface LocalPortForward extends PortForward {
   InetAddress getLocalAddress();

   int getLocalPort();
}
