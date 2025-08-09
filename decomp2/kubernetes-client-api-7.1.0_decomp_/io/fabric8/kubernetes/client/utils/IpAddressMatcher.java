package io.fabric8.kubernetes.client.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class IpAddressMatcher {
   private static final Logger logger = LoggerFactory.getLogger(IpAddressMatcher.class);
   private final int nMaskBits;
   private final InetAddress requiredAddress;

   public IpAddressMatcher(String ipAddress) {
      if (ipAddress.indexOf(47) > 0) {
         String[] addressWithMask = ipAddress.split("\\/");
         ipAddress = addressWithMask[0];
         this.nMaskBits = Integer.parseInt(addressWithMask[1]);
      } else {
         this.nMaskBits = -1;
      }

      this.requiredAddress = this.parseAddress(ipAddress);
   }

   public boolean matches(String address) {
      InetAddress remoteAddress = this.parseAddress(address);
      if (remoteAddress != null && this.requiredAddress != null) {
         if (!this.requiredAddress.getClass().equals(remoteAddress.getClass())) {
            return false;
         } else if (this.nMaskBits < 0) {
            return remoteAddress.equals(this.requiredAddress);
         } else {
            byte[] remAddr = remoteAddress.getAddress();
            byte[] reqAddr = this.requiredAddress.getAddress();
            int nMaskFullBytes = this.nMaskBits / 8;
            byte finalByte = (byte)('\uff00' >> (this.nMaskBits & 7));

            for(int i = 0; i < nMaskFullBytes; ++i) {
               if (remAddr[i] != reqAddr[i]) {
                  return false;
               }
            }

            if (finalByte != 0) {
               return (remAddr[nMaskFullBytes] & finalByte) == (reqAddr[nMaskFullBytes] & finalByte);
            } else {
               return true;
            }
         }
      } else {
         return false;
      }
   }

   private InetAddress parseAddress(String address) {
      try {
         return InetAddress.getByName(address);
      } catch (UnknownHostException var3) {
         if (!"https://kubernetes.default.svc".contains(address)) {
            logger.error("Failed to resolve hostname: {}", address);
         }

         return null;
      }
   }
}
