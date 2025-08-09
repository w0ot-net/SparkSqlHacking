package com.fasterxml.jackson.module.scala.util;

import scala.Option.;
import scala.runtime.LazyRef;

public final class TastyUtil$ {
   public static final TastyUtil$ MODULE$ = new TastyUtil$();
   private static final Class thisClass;

   static {
      thisClass = MODULE$.getClass();
   }

   private Class thisClass() {
      return thisClass;
   }

   public boolean hasTastyFile(final Class clz) {
      for(; clz != null; clz = clz.getEnclosingClass()) {
         LazyRef className$lzy = new LazyRef();
         if (this.className$1(className$lzy, clz) != null) {
            String baseName = this.className$1(className$lzy, clz).replace(".", "/");
            String classFileBase = baseName.endsWith("$") ? baseName.substring(0, baseName.length() - 1) : baseName;
            String tastyFile = (new StringBuilder(7)).append("/").append(classFileBase).append(".tasty").toString();
            if (.MODULE$.apply(this.thisClass().getResource(tastyFile)).isDefined()) {
               return true;
            }
         }
      }

      return false;
   }

   private String getClassName(final Class clz) {
      String var10000;
      try {
         var10000 = clz.getCanonicalName();
      } catch (InternalError var2) {
         var10000 = null;
      }

      return var10000;
   }

   // $FF: synthetic method
   private final String className$lzycompute$1(final LazyRef className$lzy$1, final Class clz$1) {
      synchronized(className$lzy$1){}

      String var4;
      try {
         var4 = className$lzy$1.initialized() ? (String)className$lzy$1.value() : (String)className$lzy$1.initialize(this.getClassName(clz$1));
      } catch (Throwable var6) {
         throw var6;
      }

      return var4;
   }

   private final String className$1(final LazyRef className$lzy$1, final Class clz$1) {
      return className$lzy$1.initialized() ? (String)className$lzy$1.value() : this.className$lzycompute$1(className$lzy$1, clz$1);
   }

   private TastyUtil$() {
   }
}
