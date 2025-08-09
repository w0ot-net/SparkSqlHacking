package org.apache.zookeeper.client;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.apache.yetus.audience.InterfaceAudience.Public;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Public
public final class StaticHostProvider implements HostProvider {
   private static final Logger LOG = LoggerFactory.getLogger(StaticHostProvider.class);
   private List serverAddresses = new ArrayList(5);
   private Random sourceOfRandomness;
   private int lastIndex = -1;
   private int currentIndex = -1;
   private boolean reconfigMode = false;
   private final List oldServers = new ArrayList(5);
   private final List newServers = new ArrayList(5);
   private int currentIndexOld = -1;
   private int currentIndexNew = -1;
   private float pOld;
   private float pNew;
   private Resolver resolver;

   public StaticHostProvider(Collection serverAddresses) {
      this.init(serverAddresses, System.currentTimeMillis() ^ (long)this.hashCode(), new Resolver() {
         public InetAddress[] getAllByName(String name) throws UnknownHostException {
            return InetAddress.getAllByName(name);
         }
      });
   }

   public StaticHostProvider(Collection serverAddresses, Resolver resolver) {
      this.init(serverAddresses, System.currentTimeMillis() ^ (long)this.hashCode(), resolver);
   }

   public StaticHostProvider(Collection serverAddresses, long randomnessSeed) {
      this.init(serverAddresses, randomnessSeed, new Resolver() {
         public InetAddress[] getAllByName(String name) throws UnknownHostException {
            return InetAddress.getAllByName(name);
         }
      });
   }

   private void init(Collection serverAddresses, long randomnessSeed, Resolver resolver) {
      this.sourceOfRandomness = new Random(randomnessSeed);
      this.resolver = resolver;
      if (serverAddresses.isEmpty()) {
         throw new IllegalArgumentException("A HostProvider may not be empty!");
      } else {
         this.serverAddresses = this.shuffle(serverAddresses);
         this.currentIndex = -1;
         this.lastIndex = -1;
      }
   }

   private InetSocketAddress resolve(InetSocketAddress address) {
      try {
         String curHostString = address.getHostString();
         List<InetAddress> resolvedAddresses = new ArrayList(Arrays.asList(this.resolver.getAllByName(curHostString)));
         if (resolvedAddresses.isEmpty()) {
            return address;
         } else {
            Collections.shuffle(resolvedAddresses);
            return new InetSocketAddress((InetAddress)resolvedAddresses.get(0), address.getPort());
         }
      } catch (UnknownHostException e) {
         LOG.error("Unable to resolve address: {}", address.toString(), e);
         return address;
      }
   }

   private List shuffle(Collection serverAddresses) {
      List<InetSocketAddress> tmpList = new ArrayList(serverAddresses.size());
      tmpList.addAll(serverAddresses);
      Collections.shuffle(tmpList, this.sourceOfRandomness);
      return tmpList;
   }

   public synchronized boolean updateServerList(Collection serverAddresses, InetSocketAddress currentHost) {
      List<InetSocketAddress> shuffledList = this.shuffle(serverAddresses);
      if (shuffledList.isEmpty()) {
         throw new IllegalArgumentException("A HostProvider may not be empty!");
      } else {
         boolean myServerInNewConfig = false;
         InetSocketAddress myServer = currentHost;
         if (this.reconfigMode) {
            myServer = this.next(0L);
         }

         if (myServer == null) {
            if (this.lastIndex >= 0) {
               myServer = (InetSocketAddress)this.serverAddresses.get(this.lastIndex);
            } else {
               myServer = (InetSocketAddress)this.serverAddresses.get(0);
            }
         }

         for(InetSocketAddress addr : shuffledList) {
            if (addr.getPort() == myServer.getPort() && (addr.getAddress() != null && myServer.getAddress() != null && addr.getAddress().equals(myServer.getAddress()) || addr.getHostString().equals(myServer.getHostString()))) {
               myServerInNewConfig = true;
               break;
            }
         }

         this.reconfigMode = true;
         this.newServers.clear();
         this.oldServers.clear();

         for(InetSocketAddress address : shuffledList) {
            if (this.serverAddresses.contains(address)) {
               this.oldServers.add(address);
            } else {
               this.newServers.add(address);
            }
         }

         int numOld = this.oldServers.size();
         int numNew = this.newServers.size();
         if (numOld + numNew > this.serverAddresses.size()) {
            if (myServerInNewConfig) {
               if (this.sourceOfRandomness.nextFloat() <= 1.0F - (float)this.serverAddresses.size() / (float)(numOld + numNew)) {
                  this.pNew = 1.0F;
                  this.pOld = 0.0F;
               } else {
                  this.reconfigMode = false;
               }
            } else {
               this.pNew = 1.0F;
               this.pOld = 0.0F;
            }
         } else if (myServerInNewConfig) {
            this.reconfigMode = false;
         } else {
            this.pOld = (float)(numOld * (this.serverAddresses.size() - (numOld + numNew))) / (float)((numOld + numNew) * (this.serverAddresses.size() - numOld));
            this.pNew = 1.0F - this.pOld;
         }

         if (!this.reconfigMode) {
            this.currentIndex = shuffledList.indexOf(this.getServerAtCurrentIndex());
         } else {
            this.currentIndex = -1;
         }

         this.serverAddresses = shuffledList;
         this.currentIndexOld = -1;
         this.currentIndexNew = -1;
         this.lastIndex = this.currentIndex;
         return this.reconfigMode;
      }
   }

   public synchronized InetSocketAddress getServerAtIndex(int i) {
      return i >= 0 && i < this.serverAddresses.size() ? (InetSocketAddress)this.serverAddresses.get(i) : null;
   }

   public synchronized InetSocketAddress getServerAtCurrentIndex() {
      return this.getServerAtIndex(this.currentIndex);
   }

   public synchronized int size() {
      return this.serverAddresses.size();
   }

   private InetSocketAddress nextHostInReconfigMode() {
      boolean takeNew = this.sourceOfRandomness.nextFloat() <= this.pNew;
      if (this.currentIndexNew + 1 >= this.newServers.size() || !takeNew && this.currentIndexOld + 1 < this.oldServers.size()) {
         if (this.currentIndexOld + 1 < this.oldServers.size()) {
            ++this.currentIndexOld;
            return (InetSocketAddress)this.oldServers.get(this.currentIndexOld);
         } else {
            return null;
         }
      } else {
         ++this.currentIndexNew;
         return (InetSocketAddress)this.newServers.get(this.currentIndexNew);
      }
   }

   public InetSocketAddress next(long spinDelay) {
      boolean needToSleep = false;
      InetSocketAddress addr;
      synchronized(this) {
         if (this.reconfigMode) {
            addr = this.nextHostInReconfigMode();
            if (addr != null) {
               this.currentIndex = this.serverAddresses.indexOf(addr);
               return this.resolve(addr);
            }

            this.reconfigMode = false;
            needToSleep = spinDelay > 0L;
         }

         ++this.currentIndex;
         if (this.currentIndex == this.serverAddresses.size()) {
            this.currentIndex = 0;
         }

         addr = (InetSocketAddress)this.serverAddresses.get(this.currentIndex);
         needToSleep = needToSleep || this.currentIndex == this.lastIndex && spinDelay > 0L;
         if (this.lastIndex == -1) {
            this.lastIndex = 0;
         }
      }

      if (needToSleep) {
         try {
            Thread.sleep(spinDelay);
         } catch (InterruptedException e) {
            LOG.warn("Unexpected exception", e);
         }
      }

      return this.resolve(addr);
   }

   public synchronized void onConnected() {
      this.lastIndex = this.currentIndex;
      this.reconfigMode = false;
   }

   public interface Resolver {
      InetAddress[] getAllByName(String var1) throws UnknownHostException;
   }
}
