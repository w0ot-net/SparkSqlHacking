package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.client.LocalPortForward;
import io.fabric8.kubernetes.client.PortForward;
import java.net.InetAddress;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public interface PortForwardable {
   PortForward portForward(int var1, ReadableByteChannel var2, WritableByteChannel var3);

   LocalPortForward portForward(int var1, int var2);

   LocalPortForward portForward(int var1, InetAddress var2, int var3);

   LocalPortForward portForward(int var1);
}
