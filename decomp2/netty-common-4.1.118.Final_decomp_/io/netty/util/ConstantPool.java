package io.netty.util;

import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class ConstantPool {
   private final ConcurrentMap constants = PlatformDependent.newConcurrentHashMap();
   private final AtomicInteger nextId = new AtomicInteger(1);

   public Constant valueOf(Class firstNameComponent, String secondNameComponent) {
      return this.valueOf(((Class)ObjectUtil.checkNotNull(firstNameComponent, "firstNameComponent")).getName() + '#' + (String)ObjectUtil.checkNotNull(secondNameComponent, "secondNameComponent"));
   }

   public Constant valueOf(String name) {
      return this.getOrCreate(ObjectUtil.checkNonEmpty(name, "name"));
   }

   private Constant getOrCreate(String name) {
      T constant = (T)((Constant)this.constants.get(name));
      if (constant == null) {
         T tempConstant = (T)this.newConstant(this.nextId(), name);
         constant = (T)((Constant)this.constants.putIfAbsent(name, tempConstant));
         if (constant == null) {
            return tempConstant;
         }
      }

      return constant;
   }

   public boolean exists(String name) {
      return this.constants.containsKey(ObjectUtil.checkNonEmpty(name, "name"));
   }

   public Constant newInstance(String name) {
      return this.createOrThrow(ObjectUtil.checkNonEmpty(name, "name"));
   }

   private Constant createOrThrow(String name) {
      T constant = (T)((Constant)this.constants.get(name));
      if (constant == null) {
         T tempConstant = (T)this.newConstant(this.nextId(), name);
         constant = (T)((Constant)this.constants.putIfAbsent(name, tempConstant));
         if (constant == null) {
            return tempConstant;
         }
      }

      throw new IllegalArgumentException(String.format("'%s' is already in use", name));
   }

   protected abstract Constant newConstant(int var1, String var2);

   /** @deprecated */
   @Deprecated
   public final int nextId() {
      return this.nextId.getAndIncrement();
   }
}
