package org.apache.commons.text.lookup;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;
import org.apache.commons.lang3.function.FailableSupplier;

final class InetAddressStringLookup extends AbstractStringLookup {
   static final InetAddressStringLookup LOCAL_HOST = new InetAddressStringLookup(InetAddress::getLocalHost);
   static final InetAddressStringLookup LOOPACK_ADDRESS = new InetAddressStringLookup(InetAddress::getLoopbackAddress);
   private final FailableSupplier inetAddressSupplier;

   private InetAddressStringLookup(FailableSupplier inetAddressSupplier) {
      this.inetAddressSupplier = (FailableSupplier)Objects.requireNonNull(inetAddressSupplier, "inetAddressSupplier");
   }

   private InetAddress getInetAddress() throws UnknownHostException {
      return (InetAddress)this.inetAddressSupplier.get();
   }

   public String lookup(String key) {
      if (key == null) {
         return null;
      } else {
         try {
            switch (key) {
               case "name":
                  return this.getInetAddress().getHostName();
               case "canonical-name":
                  return this.getInetAddress().getCanonicalHostName();
               case "address":
                  return this.getInetAddress().getHostAddress();
               default:
                  throw new IllegalArgumentException(key);
            }
         } catch (UnknownHostException var4) {
            return null;
         }
      }
   }
}
