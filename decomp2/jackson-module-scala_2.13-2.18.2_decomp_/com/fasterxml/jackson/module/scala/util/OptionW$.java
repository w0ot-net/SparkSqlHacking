package com.fasterxml.jackson.module.scala.util;

import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Some;

public final class OptionW$ {
   public static final OptionW$ MODULE$ = new OptionW$();

   public OptionW apply(final Function0 a) {
      return new OptionW(a) {
         private Option value;
         private volatile boolean bitmap$0;
         private Function0 a$1;

         public Option optMap(final Function1 f) {
            return OptionW.optMap$(this, f);
         }

         private Option value$lzycompute() {
            synchronized(this){}

            try {
               if (!this.bitmap$0) {
                  this.value = (Option)this.a$1.apply();
                  this.bitmap$0 = true;
               }
            } catch (Throwable var3) {
               throw var3;
            }

            this.a$1 = null;
            return this.value;
         }

         public Option value() {
            return !this.bitmap$0 ? this.value$lzycompute() : this.value;
         }

         public {
            this.a$1 = a$1;
            OptionW.$init$(this);
         }
      };
   }

   public Option unapply(final OptionW v) {
      return new Some(v.value());
   }

   private OptionW$() {
   }
}
