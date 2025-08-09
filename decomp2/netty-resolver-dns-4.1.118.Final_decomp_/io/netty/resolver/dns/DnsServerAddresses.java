package io.netty.resolver.dns;

import io.netty.util.internal.ObjectUtil;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class DnsServerAddresses {
   /** @deprecated */
   @Deprecated
   public static List defaultAddressList() {
      return DefaultDnsServerAddressStreamProvider.defaultAddressList();
   }

   /** @deprecated */
   @Deprecated
   public static DnsServerAddresses defaultAddresses() {
      return DefaultDnsServerAddressStreamProvider.defaultAddresses();
   }

   public static DnsServerAddresses sequential(Iterable addresses) {
      return sequential0(sanitize(addresses));
   }

   public static DnsServerAddresses sequential(InetSocketAddress... addresses) {
      return sequential0(sanitize(addresses));
   }

   private static DnsServerAddresses sequential0(List addresses) {
      return (DnsServerAddresses)(addresses.size() == 1 ? singleton((InetSocketAddress)addresses.get(0)) : new DefaultDnsServerAddresses("sequential", addresses) {
         public DnsServerAddressStream stream() {
            return new SequentialDnsServerAddressStream(this.addresses, 0);
         }
      });
   }

   public static DnsServerAddresses shuffled(Iterable addresses) {
      return shuffled0(sanitize(addresses));
   }

   public static DnsServerAddresses shuffled(InetSocketAddress... addresses) {
      return shuffled0(sanitize(addresses));
   }

   private static DnsServerAddresses shuffled0(List addresses) {
      return (DnsServerAddresses)(addresses.size() == 1 ? singleton((InetSocketAddress)addresses.get(0)) : new DefaultDnsServerAddresses("shuffled", addresses) {
         public DnsServerAddressStream stream() {
            return new ShuffledDnsServerAddressStream(this.addresses);
         }
      });
   }

   public static DnsServerAddresses rotational(Iterable addresses) {
      return rotational0(sanitize(addresses));
   }

   public static DnsServerAddresses rotational(InetSocketAddress... addresses) {
      return rotational0(sanitize(addresses));
   }

   private static DnsServerAddresses rotational0(List addresses) {
      return (DnsServerAddresses)(addresses.size() == 1 ? singleton((InetSocketAddress)addresses.get(0)) : new RotationalDnsServerAddresses(addresses));
   }

   public static DnsServerAddresses singleton(InetSocketAddress address) {
      ObjectUtil.checkNotNull(address, "address");
      if (address.isUnresolved()) {
         throw new IllegalArgumentException("cannot use an unresolved DNS server address: " + address);
      } else {
         return new SingletonDnsServerAddresses(address);
      }
   }

   private static List sanitize(Iterable addresses) {
      ObjectUtil.checkNotNull(addresses, "addresses");
      List<InetSocketAddress> list;
      if (addresses instanceof Collection) {
         list = new ArrayList(((Collection)addresses).size());
      } else {
         list = new ArrayList(4);
      }

      for(InetSocketAddress a : addresses) {
         if (a == null) {
            break;
         }

         if (a.isUnresolved()) {
            throw new IllegalArgumentException("cannot use an unresolved DNS server address: " + a);
         }

         list.add(a);
      }

      return (List)ObjectUtil.checkNonEmpty(list, "list");
   }

   private static List sanitize(InetSocketAddress[] addresses) {
      ObjectUtil.checkNotNull(addresses, "addresses");
      List<InetSocketAddress> list = new ArrayList(addresses.length);

      for(InetSocketAddress a : addresses) {
         if (a == null) {
            break;
         }

         if (a.isUnresolved()) {
            throw new IllegalArgumentException("cannot use an unresolved DNS server address: " + a);
         }

         list.add(a);
      }

      return list.isEmpty() ? DefaultDnsServerAddressStreamProvider.defaultAddressList() : list;
   }

   public abstract DnsServerAddressStream stream();
}
