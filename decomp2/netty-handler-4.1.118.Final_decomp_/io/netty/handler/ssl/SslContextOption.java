package io.netty.handler.ssl;

import io.netty.util.AbstractConstant;
import io.netty.util.ConstantPool;
import io.netty.util.internal.ObjectUtil;

public class SslContextOption extends AbstractConstant {
   private static final ConstantPool pool = new ConstantPool() {
      protected SslContextOption newConstant(int id, String name) {
         return new SslContextOption(id, name);
      }
   };

   public static SslContextOption valueOf(String name) {
      return (SslContextOption)pool.valueOf(name);
   }

   public static SslContextOption valueOf(Class firstNameComponent, String secondNameComponent) {
      return (SslContextOption)pool.valueOf(firstNameComponent, secondNameComponent);
   }

   public static boolean exists(String name) {
      return pool.exists(name);
   }

   private SslContextOption(int id, String name) {
      super(id, name);
   }

   protected SslContextOption(String name) {
      this(pool.nextId(), name);
   }

   public void validate(Object value) {
      ObjectUtil.checkNotNull(value, "value");
   }
}
