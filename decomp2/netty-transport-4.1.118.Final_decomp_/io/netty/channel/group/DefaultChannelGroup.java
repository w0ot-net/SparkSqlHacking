package io.netty.channel.group;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelId;
import io.netty.channel.ServerChannel;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class DefaultChannelGroup extends AbstractSet implements ChannelGroup {
   private static final AtomicInteger nextId = new AtomicInteger();
   private final String name;
   private final EventExecutor executor;
   private final ConcurrentMap serverChannels;
   private final ConcurrentMap nonServerChannels;
   private final ChannelFutureListener remover;
   private final VoidChannelGroupFuture voidFuture;
   private final boolean stayClosed;
   private volatile boolean closed;

   public DefaultChannelGroup(EventExecutor executor) {
      this(executor, false);
   }

   public DefaultChannelGroup(String name, EventExecutor executor) {
      this(name, executor, false);
   }

   public DefaultChannelGroup(EventExecutor executor, boolean stayClosed) {
      this("group-0x" + Integer.toHexString(nextId.incrementAndGet()), executor, stayClosed);
   }

   public DefaultChannelGroup(String name, EventExecutor executor, boolean stayClosed) {
      this.serverChannels = PlatformDependent.newConcurrentHashMap();
      this.nonServerChannels = PlatformDependent.newConcurrentHashMap();
      this.remover = new ChannelFutureListener() {
         public void operationComplete(ChannelFuture future) throws Exception {
            DefaultChannelGroup.this.remove(future.channel());
         }
      };
      this.voidFuture = new VoidChannelGroupFuture(this);
      ObjectUtil.checkNotNull(name, "name");
      this.name = name;
      this.executor = executor;
      this.stayClosed = stayClosed;
   }

   public String name() {
      return this.name;
   }

   public Channel find(ChannelId id) {
      Channel c = (Channel)this.nonServerChannels.get(id);
      return c != null ? c : (Channel)this.serverChannels.get(id);
   }

   public boolean isEmpty() {
      return this.nonServerChannels.isEmpty() && this.serverChannels.isEmpty();
   }

   public int size() {
      return this.nonServerChannels.size() + this.serverChannels.size();
   }

   public boolean contains(Object o) {
      if (o instanceof ServerChannel) {
         return this.serverChannels.containsValue(o);
      } else {
         return o instanceof Channel ? this.nonServerChannels.containsValue(o) : false;
      }
   }

   public boolean add(Channel channel) {
      ConcurrentMap<ChannelId, Channel> map = channel instanceof ServerChannel ? this.serverChannels : this.nonServerChannels;
      boolean added = map.putIfAbsent(channel.id(), channel) == null;
      if (added) {
         channel.closeFuture().addListener(this.remover);
      }

      if (this.stayClosed && this.closed) {
         channel.close();
      }

      return added;
   }

   public boolean remove(Object o) {
      Channel c = null;
      if (o instanceof ChannelId) {
         c = (Channel)this.nonServerChannels.remove(o);
         if (c == null) {
            c = (Channel)this.serverChannels.remove(o);
         }
      } else if (o instanceof Channel) {
         c = (Channel)o;
         if (c instanceof ServerChannel) {
            c = (Channel)this.serverChannels.remove(c.id());
         } else {
            c = (Channel)this.nonServerChannels.remove(c.id());
         }
      }

      if (c == null) {
         return false;
      } else {
         c.closeFuture().removeListener(this.remover);
         return true;
      }
   }

   public void clear() {
      this.nonServerChannels.clear();
      this.serverChannels.clear();
   }

   public Iterator iterator() {
      return new CombinedIterator(this.serverChannels.values().iterator(), this.nonServerChannels.values().iterator());
   }

   public Object[] toArray() {
      Collection<Channel> channels = new ArrayList(this.size());
      channels.addAll(this.serverChannels.values());
      channels.addAll(this.nonServerChannels.values());
      return channels.toArray();
   }

   public Object[] toArray(Object[] a) {
      Collection<Channel> channels = new ArrayList(this.size());
      channels.addAll(this.serverChannels.values());
      channels.addAll(this.nonServerChannels.values());
      return channels.toArray(a);
   }

   public ChannelGroupFuture close() {
      return this.close(ChannelMatchers.all());
   }

   public ChannelGroupFuture disconnect() {
      return this.disconnect(ChannelMatchers.all());
   }

   public ChannelGroupFuture deregister() {
      return this.deregister(ChannelMatchers.all());
   }

   public ChannelGroupFuture write(Object message) {
      return this.write(message, ChannelMatchers.all());
   }

   private static Object safeDuplicate(Object message) {
      if (message instanceof ByteBuf) {
         return ((ByteBuf)message).retainedDuplicate();
      } else {
         return message instanceof ByteBufHolder ? ((ByteBufHolder)message).retainedDuplicate() : ReferenceCountUtil.retain(message);
      }
   }

   public ChannelGroupFuture write(Object message, ChannelMatcher matcher) {
      return this.write(message, matcher, false);
   }

   public ChannelGroupFuture write(Object message, ChannelMatcher matcher, boolean voidPromise) {
      ObjectUtil.checkNotNull(message, "message");
      ObjectUtil.checkNotNull(matcher, "matcher");
      ChannelGroupFuture future;
      if (voidPromise) {
         for(Channel c : this.nonServerChannels.values()) {
            if (matcher.matches(c)) {
               c.write(safeDuplicate(message), c.voidPromise());
            }
         }

         future = this.voidFuture;
      } else {
         Map<Channel, ChannelFuture> futures = new LinkedHashMap(this.nonServerChannels.size());

         for(Channel c : this.nonServerChannels.values()) {
            if (matcher.matches(c)) {
               futures.put(c, c.write(safeDuplicate(message)));
            }
         }

         future = new DefaultChannelGroupFuture(this, futures, this.executor);
      }

      ReferenceCountUtil.release(message);
      return future;
   }

   public ChannelGroup flush() {
      return this.flush(ChannelMatchers.all());
   }

   public ChannelGroupFuture flushAndWrite(Object message) {
      return this.writeAndFlush(message);
   }

   public ChannelGroupFuture writeAndFlush(Object message) {
      return this.writeAndFlush(message, ChannelMatchers.all());
   }

   public ChannelGroupFuture disconnect(ChannelMatcher matcher) {
      ObjectUtil.checkNotNull(matcher, "matcher");
      Map<Channel, ChannelFuture> futures = new LinkedHashMap(this.size());

      for(Channel c : this.serverChannels.values()) {
         if (matcher.matches(c)) {
            futures.put(c, c.disconnect());
         }
      }

      for(Channel c : this.nonServerChannels.values()) {
         if (matcher.matches(c)) {
            futures.put(c, c.disconnect());
         }
      }

      return new DefaultChannelGroupFuture(this, futures, this.executor);
   }

   public ChannelGroupFuture close(ChannelMatcher matcher) {
      ObjectUtil.checkNotNull(matcher, "matcher");
      Map<Channel, ChannelFuture> futures = new LinkedHashMap(this.size());
      if (this.stayClosed) {
         this.closed = true;
      }

      for(Channel c : this.serverChannels.values()) {
         if (matcher.matches(c)) {
            futures.put(c, c.close());
         }
      }

      for(Channel c : this.nonServerChannels.values()) {
         if (matcher.matches(c)) {
            futures.put(c, c.close());
         }
      }

      return new DefaultChannelGroupFuture(this, futures, this.executor);
   }

   public ChannelGroupFuture deregister(ChannelMatcher matcher) {
      ObjectUtil.checkNotNull(matcher, "matcher");
      Map<Channel, ChannelFuture> futures = new LinkedHashMap(this.size());

      for(Channel c : this.serverChannels.values()) {
         if (matcher.matches(c)) {
            futures.put(c, c.deregister());
         }
      }

      for(Channel c : this.nonServerChannels.values()) {
         if (matcher.matches(c)) {
            futures.put(c, c.deregister());
         }
      }

      return new DefaultChannelGroupFuture(this, futures, this.executor);
   }

   public ChannelGroup flush(ChannelMatcher matcher) {
      for(Channel c : this.nonServerChannels.values()) {
         if (matcher.matches(c)) {
            c.flush();
         }
      }

      return this;
   }

   public ChannelGroupFuture flushAndWrite(Object message, ChannelMatcher matcher) {
      return this.writeAndFlush(message, matcher);
   }

   public ChannelGroupFuture writeAndFlush(Object message, ChannelMatcher matcher) {
      return this.writeAndFlush(message, matcher, false);
   }

   public ChannelGroupFuture writeAndFlush(Object message, ChannelMatcher matcher, boolean voidPromise) {
      ObjectUtil.checkNotNull(message, "message");
      ChannelGroupFuture future;
      if (voidPromise) {
         for(Channel c : this.nonServerChannels.values()) {
            if (matcher.matches(c)) {
               c.writeAndFlush(safeDuplicate(message), c.voidPromise());
            }
         }

         future = this.voidFuture;
      } else {
         Map<Channel, ChannelFuture> futures = new LinkedHashMap(this.nonServerChannels.size());

         for(Channel c : this.nonServerChannels.values()) {
            if (matcher.matches(c)) {
               futures.put(c, c.writeAndFlush(safeDuplicate(message)));
            }
         }

         future = new DefaultChannelGroupFuture(this, futures, this.executor);
      }

      ReferenceCountUtil.release(message);
      return future;
   }

   public ChannelGroupFuture newCloseFuture() {
      return this.newCloseFuture(ChannelMatchers.all());
   }

   public ChannelGroupFuture newCloseFuture(ChannelMatcher matcher) {
      Map<Channel, ChannelFuture> futures = new LinkedHashMap(this.size());

      for(Channel c : this.serverChannels.values()) {
         if (matcher.matches(c)) {
            futures.put(c, c.closeFuture());
         }
      }

      for(Channel c : this.nonServerChannels.values()) {
         if (matcher.matches(c)) {
            futures.put(c, c.closeFuture());
         }
      }

      return new DefaultChannelGroupFuture(this, futures, this.executor);
   }

   public int hashCode() {
      return System.identityHashCode(this);
   }

   public boolean equals(Object o) {
      return this == o;
   }

   public int compareTo(ChannelGroup o) {
      int v = this.name().compareTo(o.name());
      return v != 0 ? v : System.identityHashCode(this) - System.identityHashCode(o);
   }

   public String toString() {
      return StringUtil.simpleClassName(this) + "(name: " + this.name() + ", size: " + this.size() + ')';
   }
}
