package io.netty.channel;

import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;
import java.lang.reflect.Constructor;

public class ReflectiveChannelFactory implements ChannelFactory {
   private final Constructor constructor;

   public ReflectiveChannelFactory(Class clazz) {
      ObjectUtil.checkNotNull(clazz, "clazz");

      try {
         this.constructor = clazz.getConstructor();
      } catch (NoSuchMethodException e) {
         throw new IllegalArgumentException("Class " + StringUtil.simpleClassName(clazz) + " does not have a public non-arg constructor", e);
      }
   }

   public Channel newChannel() {
      try {
         return (Channel)this.constructor.newInstance();
      } catch (Throwable t) {
         throw new ChannelException("Unable to create Channel from class " + this.constructor.getDeclaringClass(), t);
      }
   }

   public String toString() {
      return StringUtil.simpleClassName(ReflectiveChannelFactory.class) + '(' + StringUtil.simpleClassName(this.constructor.getDeclaringClass()) + ".class)";
   }
}
