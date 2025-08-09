package org.apache.spark;

import scala.Enumeration;
import scala.runtime.ModuleSerializationProxy;

public final class RequestMethod$ extends Enumeration {
   public static final RequestMethod$ MODULE$ = new RequestMethod$();
   private static final Enumeration.Value BARRIER;
   private static final Enumeration.Value ALL_GATHER;

   static {
      BARRIER = MODULE$.Value();
      ALL_GATHER = MODULE$.Value();
   }

   public Enumeration.Value BARRIER() {
      return BARRIER;
   }

   public Enumeration.Value ALL_GATHER() {
      return ALL_GATHER;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RequestMethod$.class);
   }

   private RequestMethod$() {
   }
}
