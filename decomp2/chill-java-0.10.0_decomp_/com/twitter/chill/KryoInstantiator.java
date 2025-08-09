package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import java.io.Serializable;
import org.objenesis.strategy.InstantiatorStrategy;

public class KryoInstantiator implements Serializable {
   public Kryo newKryo() {
      return new Kryo();
   }

   public KryoInstantiator setClassLoader(final ClassLoader var1) {
      return new KryoInstantiator() {
         public Kryo newKryo() {
            Kryo var1x = KryoInstantiator.this.newKryo();
            var1x.setClassLoader(var1);
            return var1x;
         }
      };
   }

   public KryoInstantiator setInstantiatorStrategy(final InstantiatorStrategy var1) {
      return new KryoInstantiator() {
         public Kryo newKryo() {
            Kryo var1x = KryoInstantiator.this.newKryo();
            var1x.setInstantiatorStrategy(var1);
            return var1x;
         }
      };
   }

   public KryoInstantiator setReferences(final boolean var1) {
      return new KryoInstantiator() {
         public Kryo newKryo() {
            Kryo var1x = KryoInstantiator.this.newKryo();
            if (var1x.getReferences() != var1) {
               var1x.setReferences(var1);
            }

            return var1x;
         }
      };
   }

   public KryoInstantiator setRegistrationRequired(final boolean var1) {
      return new KryoInstantiator() {
         public Kryo newKryo() {
            Kryo var1x = KryoInstantiator.this.newKryo();
            if (var1x.isRegistrationRequired() != var1) {
               var1x.setRegistrationRequired(var1);
            }

            return var1x;
         }
      };
   }

   public KryoInstantiator setThreadContextClassLoader() {
      return new KryoInstantiator() {
         public Kryo newKryo() {
            Kryo var1 = KryoInstantiator.this.newKryo();
            var1.setClassLoader(Thread.currentThread().getContextClassLoader());
            return var1;
         }
      };
   }

   public KryoInstantiator withRegistrar(final IKryoRegistrar var1) {
      return new KryoInstantiator() {
         public Kryo newKryo() {
            Kryo var1x = KryoInstantiator.this.newKryo();
            var1.apply(var1x);
            return var1x;
         }
      };
   }
}
