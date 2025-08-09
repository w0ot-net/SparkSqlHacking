package com.fasterxml.jackson.module.scala.util;

import scala.Function0;
import scala.Option;
import scala.Some;

public final class StringW$ {
   public static final StringW$ MODULE$ = new StringW$();

   public StringW apply(final Function0 s) {
      return new StringW(s) {
         private String value;
         private volatile boolean bitmap$0;
         private Function0 s$1;

         public String orIfEmpty(final Function0 s2) {
            return StringW.orIfEmpty$(this, s2);
         }

         private String value$lzycompute() {
            synchronized(this){}

            try {
               if (!this.bitmap$0) {
                  this.value = (String)this.s$1.apply();
                  this.bitmap$0 = true;
               }
            } catch (Throwable var3) {
               throw var3;
            }

            this.s$1 = null;
            return this.value;
         }

         public String value() {
            return !this.bitmap$0 ? this.value$lzycompute() : this.value;
         }

         public {
            this.s$1 = s$1;
            StringW.$init$(this);
         }
      };
   }

   public Option unapply(final StringW s) {
      return new Some(s.value());
   }

   private StringW$() {
   }
}
