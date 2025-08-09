package io.netty.resolver.dns;

import io.netty.util.internal.BoundedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class ResolvConf {
   private final List nameservers;

   static ResolvConf fromReader(BufferedReader reader) throws IOException {
      return new ResolvConf(reader);
   }

   static ResolvConf fromFile(String file) throws IOException {
      BufferedReader reader = new BufferedReader(new InputStreamReader(new BoundedInputStream(new FileInputStream(file), 1048576)));

      ResolvConf var2;
      try {
         var2 = fromReader(reader);
      } finally {
         reader.close();
      }

      return var2;
   }

   static ResolvConf system() {
      ResolvConf resolvConv = ResolvConf.ResolvConfLazy.machineResolvConf;
      if (resolvConv != null) {
         return resolvConv;
      } else {
         throw new IllegalStateException("/etc/resolv.conf could not be read");
      }
   }

   private ResolvConf(BufferedReader reader) throws IOException {
      List<InetSocketAddress> nameservers = new ArrayList();

      String ln;
      while((ln = reader.readLine()) != null) {
         ln = ln.trim();
         if (!ln.isEmpty() && ln.startsWith("nameserver")) {
            ln = ln.substring("nameserver".length());
            int cIndex = ln.indexOf(35);
            if (cIndex != -1) {
               ln = ln.substring(0, cIndex);
            }

            ln = ln.trim();
            if (!ln.isEmpty()) {
               nameservers.add(new InetSocketAddress(ln, 53));
            }
         }
      }

      this.nameservers = Collections.unmodifiableList(nameservers);
   }

   List getNameservers() {
      return this.nameservers;
   }

   private static final class ResolvConfLazy {
      static final ResolvConf machineResolvConf;

      static {
         ResolvConf resolvConf;
         try {
            resolvConf = ResolvConf.fromFile("/etc/resolv.conf");
         } catch (IOException var2) {
            resolvConf = null;
         } catch (SecurityException var3) {
            resolvConf = null;
         }

         machineResolvConf = resolvConf;
      }
   }
}
