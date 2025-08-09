package io.netty.channel.group;

import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.util.internal.ObjectUtil;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

public class ChannelGroupException extends ChannelException implements Iterable {
   private static final long serialVersionUID = -4093064295562629453L;
   private final Collection failed;

   public ChannelGroupException(Collection causes) {
      ObjectUtil.checkNonEmpty(causes, "causes");
      this.failed = Collections.unmodifiableCollection(causes);
   }

   public Iterator iterator() {
      return this.failed.iterator();
   }
}
