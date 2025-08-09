package io.netty.util.internal;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Collections;
import java.util.Enumeration;

public final class SocketUtils {
   private static final Enumeration EMPTY = Collections.enumeration(Collections.emptyList());

   private SocketUtils() {
   }

   private static Enumeration empty() {
      return EMPTY;
   }

   public static void connect(final Socket socket, final SocketAddress remoteAddress, final int timeout) throws IOException {
      try {
         AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Void run() throws IOException {
               socket.connect(remoteAddress, timeout);
               return null;
            }
         });
      } catch (PrivilegedActionException e) {
         throw (IOException)e.getCause();
      }
   }

   public static void bind(final Socket socket, final SocketAddress bindpoint) throws IOException {
      try {
         AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Void run() throws IOException {
               socket.bind(bindpoint);
               return null;
            }
         });
      } catch (PrivilegedActionException e) {
         throw (IOException)e.getCause();
      }
   }

   public static boolean connect(final SocketChannel socketChannel, final SocketAddress remoteAddress) throws IOException {
      try {
         return (Boolean)AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Boolean run() throws IOException {
               return socketChannel.connect(remoteAddress);
            }
         });
      } catch (PrivilegedActionException e) {
         throw (IOException)e.getCause();
      }
   }

   @SuppressJava6Requirement(
      reason = "Usage guarded by java version check"
   )
   public static void bind(final SocketChannel socketChannel, final SocketAddress address) throws IOException {
      try {
         AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Void run() throws IOException {
               socketChannel.bind(address);
               return null;
            }
         });
      } catch (PrivilegedActionException e) {
         throw (IOException)e.getCause();
      }
   }

   public static SocketChannel accept(final ServerSocketChannel serverSocketChannel) throws IOException {
      try {
         return (SocketChannel)AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public SocketChannel run() throws IOException {
               return serverSocketChannel.accept();
            }
         });
      } catch (PrivilegedActionException e) {
         throw (IOException)e.getCause();
      }
   }

   @SuppressJava6Requirement(
      reason = "Usage guarded by java version check"
   )
   public static void bind(final DatagramChannel networkChannel, final SocketAddress address) throws IOException {
      try {
         AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public Void run() throws IOException {
               networkChannel.bind(address);
               return null;
            }
         });
      } catch (PrivilegedActionException e) {
         throw (IOException)e.getCause();
      }
   }

   public static SocketAddress localSocketAddress(final ServerSocket socket) {
      return (SocketAddress)AccessController.doPrivileged(new PrivilegedAction() {
         public SocketAddress run() {
            return socket.getLocalSocketAddress();
         }
      });
   }

   public static InetAddress addressByName(final String hostname) throws UnknownHostException {
      try {
         return (InetAddress)AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public InetAddress run() throws UnknownHostException {
               return InetAddress.getByName(hostname);
            }
         });
      } catch (PrivilegedActionException e) {
         throw (UnknownHostException)e.getCause();
      }
   }

   public static InetAddress[] allAddressesByName(final String hostname) throws UnknownHostException {
      try {
         return (InetAddress[])AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public InetAddress[] run() throws UnknownHostException {
               return InetAddress.getAllByName(hostname);
            }
         });
      } catch (PrivilegedActionException e) {
         throw (UnknownHostException)e.getCause();
      }
   }

   public static InetSocketAddress socketAddress(final String hostname, final int port) {
      return (InetSocketAddress)AccessController.doPrivileged(new PrivilegedAction() {
         public InetSocketAddress run() {
            return new InetSocketAddress(hostname, port);
         }
      });
   }

   public static Enumeration addressesFromNetworkInterface(final NetworkInterface intf) {
      Enumeration<InetAddress> addresses = (Enumeration)AccessController.doPrivileged(new PrivilegedAction() {
         public Enumeration run() {
            return intf.getInetAddresses();
         }
      });
      return addresses == null ? empty() : addresses;
   }

   @SuppressJava6Requirement(
      reason = "Usage guarded by java version check"
   )
   public static InetAddress loopbackAddress() {
      return (InetAddress)AccessController.doPrivileged(new PrivilegedAction() {
         public InetAddress run() {
            if (PlatformDependent.javaVersion() >= 7) {
               return InetAddress.getLoopbackAddress();
            } else {
               try {
                  return InetAddress.getByName((String)null);
               } catch (UnknownHostException e) {
                  throw new IllegalStateException(e);
               }
            }
         }
      });
   }

   public static byte[] hardwareAddressFromNetworkInterface(final NetworkInterface intf) throws SocketException {
      try {
         return (byte[])AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public byte[] run() throws SocketException {
               return intf.getHardwareAddress();
            }
         });
      } catch (PrivilegedActionException e) {
         throw (SocketException)e.getCause();
      }
   }
}
