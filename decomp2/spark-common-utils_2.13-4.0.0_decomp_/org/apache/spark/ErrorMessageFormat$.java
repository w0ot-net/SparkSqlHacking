package org.apache.spark;

import scala.Enumeration;
import scala.runtime.ModuleSerializationProxy;

public final class ErrorMessageFormat$ extends Enumeration {
   public static final ErrorMessageFormat$ MODULE$ = new ErrorMessageFormat$();
   private static final Enumeration.Value PRETTY;
   private static final Enumeration.Value MINIMAL;
   private static final Enumeration.Value STANDARD;

   static {
      PRETTY = MODULE$.Value();
      MINIMAL = MODULE$.Value();
      STANDARD = MODULE$.Value();
   }

   public Enumeration.Value PRETTY() {
      return PRETTY;
   }

   public Enumeration.Value MINIMAL() {
      return MINIMAL;
   }

   public Enumeration.Value STANDARD() {
      return STANDARD;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ErrorMessageFormat$.class);
   }

   private ErrorMessageFormat$() {
   }
}
