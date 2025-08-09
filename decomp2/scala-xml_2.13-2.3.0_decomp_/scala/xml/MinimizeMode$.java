package scala.xml;

import scala.Enumeration;
import scala.runtime.ModuleSerializationProxy;

public final class MinimizeMode$ extends Enumeration {
   public static final MinimizeMode$ MODULE$ = new MinimizeMode$();
   private static final Enumeration.Value Default;
   private static final Enumeration.Value Always;
   private static final Enumeration.Value Never;

   static {
      Default = MODULE$.Value();
      Always = MODULE$.Value();
      Never = MODULE$.Value();
   }

   public Enumeration.Value Default() {
      return Default;
   }

   public Enumeration.Value Always() {
      return Always;
   }

   public Enumeration.Value Never() {
      return Never;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MinimizeMode$.class);
   }

   private MinimizeMode$() {
   }
}
