package org.apache.zookeeper.server.quorum;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NoRouteToHostException;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public final class MultipleAddresses {
   public static final Duration DEFAULT_TIMEOUT = Duration.ofMillis(1000L);
   private Set addresses;
   private final Duration timeout;

   private static Set newConcurrentHashSet() {
      return Collections.newSetFromMap(new ConcurrentHashMap());
   }

   public MultipleAddresses() {
      this((Collection)Collections.emptyList());
   }

   public MultipleAddresses(Collection addresses) {
      this(addresses, DEFAULT_TIMEOUT);
   }

   public MultipleAddresses(InetSocketAddress address) {
      this(Arrays.asList(address), DEFAULT_TIMEOUT);
   }

   public MultipleAddresses(Collection addresses, Duration timeout) {
      this.addresses = newConcurrentHashSet();
      this.addresses.addAll(addresses);
      this.timeout = timeout;
   }

   public boolean isEmpty() {
      return this.addresses.isEmpty();
   }

   public Set getAllAddresses() {
      return Collections.unmodifiableSet(this.addresses);
   }

   public Set getWildcardAddresses() {
      return (Set)this.addresses.stream().map((a) -> new InetSocketAddress(a.getPort())).collect(Collectors.toSet());
   }

   public List getAllPorts() {
      return (List)this.addresses.stream().map(InetSocketAddress::getPort).distinct().collect(Collectors.toList());
   }

   public List getAllHostStrings() {
      return (List)this.addresses.stream().map(InetSocketAddress::getHostString).distinct().collect(Collectors.toList());
   }

   public void addAddress(InetSocketAddress address) {
      this.addresses.add(address);
   }

   public InetSocketAddress getReachableAddress() throws NoRouteToHostException {
      return (InetSocketAddress)this.addresses.parallelStream().filter(this::checkIfAddressIsReachable).findAny().orElseThrow(() -> new NoRouteToHostException("No valid address among " + this.addresses));
   }

   public Set getAllReachableAddresses() {
      return (Set)this.addresses.parallelStream().filter(this::checkIfAddressIsReachable).collect(Collectors.toSet());
   }

   public Set getAllReachableAddressesOrAll() {
      if (this.addresses.size() == 1) {
         return this.getAllAddresses();
      } else {
         Set<InetSocketAddress> allReachable = this.getAllReachableAddresses();
         return allReachable.isEmpty() ? this.getAllAddresses() : allReachable;
      }
   }

   public InetSocketAddress getReachableOrOne() {
      if (this.addresses.size() == 1) {
         return this.getOne();
      } else {
         InetSocketAddress address;
         try {
            address = this.getReachableAddress();
         } catch (NoRouteToHostException var3) {
            address = this.getOne();
         }

         return address;
      }
   }

   public void recreateSocketAddresses() {
      this.addresses = (Set)this.addresses.parallelStream().map(this::recreateSocketAddress).collect(Collectors.toCollection(MultipleAddresses::newConcurrentHashSet));
   }

   public InetSocketAddress getOne() {
      return (InetSocketAddress)this.addresses.iterator().next();
   }

   public int size() {
      return this.addresses.size();
   }

   private boolean checkIfAddressIsReachable(InetSocketAddress address) {
      if (address.isUnresolved()) {
         return false;
      } else {
         try {
            if (address.getAddress().isReachable((int)this.timeout.toMillis())) {
               return true;
            }
         } catch (IOException var3) {
         }

         return false;
      }
   }

   private InetSocketAddress recreateSocketAddress(InetSocketAddress address) {
      try {
         return new InetSocketAddress(InetAddress.getByName(address.getHostString()), address.getPort());
      } catch (UnknownHostException var3) {
         return address;
      }
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         MultipleAddresses that = (MultipleAddresses)o;
         return Objects.equals(this.addresses, that.addresses);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.addresses});
   }

   public String toString() {
      return (String)this.addresses.stream().map(InetSocketAddress::toString).collect(Collectors.joining("|"));
   }
}
