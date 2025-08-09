package com.twitter.chill.config;

import com.esotericsoftware.kryo.Kryo;
import com.twitter.chill.ClassRegistrar;
import com.twitter.chill.IKryoRegistrar;
import com.twitter.chill.KryoInstantiator;
import com.twitter.chill.ReflectingDefaultRegistrar;
import com.twitter.chill.ReflectingRegistrar;
import java.util.ArrayList;
import java.util.List;
import org.objenesis.strategy.InstantiatorStrategy;
import org.objenesis.strategy.StdInstantiatorStrategy;

public class ReflectingInstantiator extends KryoInstantiator {
   final boolean regRequired;
   final boolean skipMissing;
   final Class kryoClass;
   final Class instStratClass;
   final List registrations;
   final List defaultRegistrations;
   public static final String prefix = "com.twitter.chill.config.reflectinginstantiator";
   public static final String KRYO_CLASS = "com.twitter.chill.config.reflectinginstantiator.kryoclass";
   public static final String KRYO_CLASS_DEFAULT = Kryo.class.getName();
   public static final String INSTANTIATOR_STRATEGY_CLASS = "com.twitter.chill.config.reflectinginstantiator.instantiatorstrategyclass";
   public static final String INSTANTIATOR_STRATEGY_CLASS_DEFAULT = StdInstantiatorStrategy.class.getName();
   public static final String REGISTRATIONS = "com.twitter.chill.config.reflectinginstantiator.registrations";
   public static final String DEFAULT_REGISTRATIONS = "com.twitter.chill.config.reflectinginstantiator.defaultregistrations";
   public static final String SKIP_MISSING = "com.twitter.chill.config.reflectinginstantiator.skipmissing";
   public static final boolean SKIP_MISSING_DEFAULT = false;
   public static final String REGISTRATION_REQUIRED = "com.twitter.chill.config.reflectinginstantiator.registrationrequired";
   public static final boolean REGISTRATION_REQUIRED_DEFAULT = false;

   public ReflectingInstantiator(Config var1) throws ConfigurationException {
      this.regRequired = var1.getBoolean("com.twitter.chill.config.reflectinginstantiator.registrationrequired", false);
      this.skipMissing = var1.getBoolean("com.twitter.chill.config.reflectinginstantiator.skipmissing", false);

      try {
         this.kryoClass = Class.forName(var1.getOrElse("com.twitter.chill.config.reflectinginstantiator.kryoclass", KRYO_CLASS_DEFAULT), true, Thread.currentThread().getContextClassLoader());
         this.instStratClass = Class.forName(var1.getOrElse("com.twitter.chill.config.reflectinginstantiator.instantiatorstrategyclass", INSTANTIATOR_STRATEGY_CLASS_DEFAULT), true, Thread.currentThread().getContextClassLoader());
         this.registrations = this.buildRegistrars(var1.get("com.twitter.chill.config.reflectinginstantiator.registrations"), false);
         this.defaultRegistrations = this.buildRegistrars(var1.get("com.twitter.chill.config.reflectinginstantiator.defaultregistrations"), true);
         this.newKryoWithEx();
      } catch (ClassNotFoundException var3) {
         throw new ConfigurationException(var3);
      } catch (InstantiationException var4) {
         throw new ConfigurationException(var4);
      } catch (IllegalAccessException var5) {
         throw new ConfigurationException(var5);
      }
   }

   public ReflectingInstantiator(Iterable var1, Iterable var2, Iterable var3) {
      this(Kryo.class, StdInstantiatorStrategy.class, var1, var2, var3, false, false);
   }

   public ReflectingInstantiator(Class var1, Class var2, Iterable var3, Iterable var4, Iterable var5, boolean var6, boolean var7) {
      this.kryoClass = var1;
      this.instStratClass = var2;
      this.regRequired = var6;
      this.skipMissing = var7;
      this.registrations = new ArrayList();

      for(IKryoRegistrar var9 : var3) {
         this.registrations.add(var9);
      }

      for(IKryoRegistrar var12 : var4) {
         this.registrations.add(var12);
      }

      this.defaultRegistrations = new ArrayList();

      for(ReflectingDefaultRegistrar var13 : var5) {
         this.defaultRegistrations.add(var13);
      }

   }

   public void set(Config var1) throws ConfigurationException {
      var1.setBoolean("com.twitter.chill.config.reflectinginstantiator.registrationrequired", this.regRequired);
      var1.setBoolean("com.twitter.chill.config.reflectinginstantiator.skipmissing", this.skipMissing);
      var1.set("com.twitter.chill.config.reflectinginstantiator.kryoclass", this.kryoClass.getName());
      var1.set("com.twitter.chill.config.reflectinginstantiator.instantiatorstrategyclass", this.instStratClass.getName());
      var1.set("com.twitter.chill.config.reflectinginstantiator.registrations", this.registrarsToString(this.registrations));
      var1.set("com.twitter.chill.config.reflectinginstantiator.defaultregistrations", this.registrarsToString(this.defaultRegistrations));
   }

   protected Kryo newKryoWithEx() throws InstantiationException, IllegalAccessException {
      Kryo var1 = (Kryo)this.kryoClass.newInstance();
      var1.setInstantiatorStrategy((InstantiatorStrategy)this.instStratClass.newInstance());
      var1.setRegistrationRequired(this.regRequired);

      for(IKryoRegistrar var3 : this.registrations) {
         var3.apply(var1);
      }

      for(IKryoRegistrar var5 : this.defaultRegistrations) {
         var5.apply(var1);
      }

      return var1;
   }

   public Kryo newKryo() {
      try {
         return this.newKryoWithEx();
      } catch (InstantiationException var2) {
         throw new RuntimeException(var2);
      } catch (IllegalAccessException var3) {
         throw new RuntimeException(var3);
      }
   }

   protected List buildRegistrars(String var1, boolean var2) throws ConfigurationException {
      ArrayList var3 = new ArrayList();
      if (var1 == null) {
         return var3;
      } else {
         for(String var7 : var1.split(":")) {
            String[] var8 = var7.split(",");

            try {
               switch (var8.length) {
                  case 1:
                     if (var2) {
                        throw new ConfigurationException("default serializers require class and serializer: " + var1);
                     }

                     var3.add(new ClassRegistrar(Class.forName(var8[0], true, Thread.currentThread().getContextClassLoader())));
                     break;
                  case 2:
                     Class var9 = Class.forName(var8[0], true, Thread.currentThread().getContextClassLoader());
                     Class var10 = Class.forName(var8[1], true, Thread.currentThread().getContextClassLoader());
                     if (var2) {
                        var3.add(new ReflectingDefaultRegistrar(var9, var10));
                     } else {
                        var3.add(new ReflectingRegistrar(var9, var10));
                     }
                     break;
                  default:
                     throw new ConfigurationException(var1 + " is not well-formed.");
               }
            } catch (ClassNotFoundException var11) {
               if (!this.skipMissing) {
                  throw new ConfigurationException(var11);
               }

               System.err.println("Could not find serialization or class for " + var8[1] + ". Skipping registration.");
            }
         }

         return var3;
      }
   }

   protected String registrarsToString(Iterable var1) throws ConfigurationException {
      StringBuilder var2 = new StringBuilder();
      boolean var3 = true;

      for(IKryoRegistrar var5 : var1) {
         if (!var3) {
            var2.append(":");
         }

         var3 = false;
         Object var6 = null;
         String var8;
         if (var5 instanceof ClassRegistrar) {
            ClassRegistrar var7 = (ClassRegistrar)var5;
            var8 = var7.getRegisteredClass().getName();
         } else if (var5 instanceof ReflectingRegistrar) {
            ReflectingRegistrar var9 = (ReflectingRegistrar)var5;
            var8 = var9.getRegisteredClass().getName() + "," + var9.getSerializerClass().getName();
         } else {
            if (!(var5 instanceof ReflectingDefaultRegistrar)) {
               throw new ConfigurationException("Unknown type of reflecting registrar: " + var5.getClass().getName());
            }

            ReflectingDefaultRegistrar var10 = (ReflectingDefaultRegistrar)var5;
            var8 = var10.getRegisteredClass().getName() + "," + var10.getSerializerClass().getName();
         }

         var2.append(var8);
      }

      return var2.toString();
   }

   public int hashCode() {
      return this.kryoClass.hashCode() ^ this.registrations.hashCode() ^ this.defaultRegistrations.hashCode();
   }

   public boolean equals(Object var1) {
      if (null == var1) {
         return false;
      } else if (!(var1 instanceof ReflectingInstantiator)) {
         return false;
      } else {
         ReflectingInstantiator var2 = (ReflectingInstantiator)var1;
         return this.regRequired == var2.regRequired && this.skipMissing == var2.skipMissing && this.kryoClass.equals(var2.kryoClass) && this.instStratClass.equals(var2.instStratClass) && this.registrations.equals(var2.registrations) && this.defaultRegistrations.equals(var2.defaultRegistrations);
      }
   }
}
