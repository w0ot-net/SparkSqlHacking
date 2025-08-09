package com.fasterxml.jackson.module.scala.util;

import scala.Function0;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.util.Try;

public final class ClassW$ {
   public static final ClassW$ MODULE$ = new ClassW$();
   private static final Class productClass = Product.class;

   public Class productClass() {
      return productClass;
   }

   public ClassW apply(final Function0 c) {
      return new ClassW(c) {
         private Class value;
         private Try com$fasterxml$jackson$module$scala$util$ClassW$$moduleField;
         private volatile byte bitmap$0;
         private Function0 c$1;

         /** @deprecated */
         public boolean extendsScalaClass() {
            return ClassW.extendsScalaClass$(this);
         }

         public boolean extendsScalaClass(final boolean supportScala3Classes) {
            return ClassW.extendsScalaClass$(this, supportScala3Classes);
         }

         public boolean hasSignature() {
            return ClassW.hasSignature$(this);
         }

         public boolean isScalaObject() {
            return ClassW.isScalaObject$(this);
         }

         public Option getModuleField() {
            return ClassW.getModuleField$(this);
         }

         private Try com$fasterxml$jackson$module$scala$util$ClassW$$moduleField$lzycompute() {
            synchronized(this){}

            try {
               if ((byte)(this.bitmap$0 & 2) == 0) {
                  this.com$fasterxml$jackson$module$scala$util$ClassW$$moduleField = ClassW.com$fasterxml$jackson$module$scala$util$ClassW$$moduleField$(this);
                  this.bitmap$0 = (byte)(this.bitmap$0 | 2);
               }
            } catch (Throwable var3) {
               throw var3;
            }

            return this.com$fasterxml$jackson$module$scala$util$ClassW$$moduleField;
         }

         public Try com$fasterxml$jackson$module$scala$util$ClassW$$moduleField() {
            return (byte)(this.bitmap$0 & 2) == 0 ? this.com$fasterxml$jackson$module$scala$util$ClassW$$moduleField$lzycompute() : this.com$fasterxml$jackson$module$scala$util$ClassW$$moduleField;
         }

         private Class value$lzycompute() {
            synchronized(this){}

            try {
               if ((byte)(this.bitmap$0 & 1) == 0) {
                  this.value = (Class)this.c$1.apply();
                  this.bitmap$0 = (byte)(this.bitmap$0 | 1);
               }
            } catch (Throwable var3) {
               throw var3;
            }

            this.c$1 = null;
            return this.value;
         }

         public Class value() {
            return (byte)(this.bitmap$0 & 1) == 0 ? this.value$lzycompute() : this.value;
         }

         public {
            this.c$1 = c$1;
            ClassW.$init$(this);
         }
      };
   }

   public Option unapply(final ClassW c) {
      return new Some(c.value());
   }

   private ClassW$() {
   }
}
