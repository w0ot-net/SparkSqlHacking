package scala.reflect;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import scala.Option;
import scala.Some;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Nothing$;
import scala.runtime.Null$;

public final class ClassTag$ implements Serializable {
   public static final ClassTag$ MODULE$ = new ClassTag$();
   public static final Class scala$reflect$ClassTag$$ObjectTYPE = Object.class;
   public static final Class scala$reflect$ClassTag$$NothingTYPE = Nothing$.class;
   public static final Class scala$reflect$ClassTag$$NullTYPE = Null$.class;
   private static final ManifestFactory.ByteManifest Byte;
   private static final ManifestFactory.ShortManifest Short;
   private static final ManifestFactory.CharManifest Char;
   private static final ManifestFactory.IntManifest Int;
   private static final ManifestFactory.LongManifest Long;
   private static final ManifestFactory.FloatManifest Float;
   private static final ManifestFactory.DoubleManifest Double;
   private static final ManifestFactory.BooleanManifest Boolean;
   private static final ManifestFactory.UnitManifest Unit;
   private static final ClassTag Any;
   private static final ClassTag Object;
   private static final ClassTag AnyVal;
   private static final ClassTag AnyRef;
   private static final ClassTag Nothing;
   private static final ClassTag Null;
   private static final boolean cacheDisabled;

   static {
      Byte = Manifest$.MODULE$.Byte();
      Short = Manifest$.MODULE$.Short();
      Char = Manifest$.MODULE$.Char();
      Int = Manifest$.MODULE$.Int();
      Long = Manifest$.MODULE$.Long();
      Float = Manifest$.MODULE$.Float();
      Double = Manifest$.MODULE$.Double();
      Boolean = Manifest$.MODULE$.Boolean();
      Unit = Manifest$.MODULE$.Unit();
      Any = Manifest$.MODULE$.Any();
      Object = Manifest$.MODULE$.Object();
      AnyVal = Manifest$.MODULE$.AnyVal();
      AnyRef = Manifest$.MODULE$.AnyRef();
      Nothing = Manifest$.MODULE$.Nothing();
      Null = Manifest$.MODULE$.Null();
      cacheDisabled = java.lang.Boolean.getBoolean("scala.reflect.classtag.cache.disable");
   }

   public ManifestFactory.ByteManifest Byte() {
      return Byte;
   }

   public ManifestFactory.ShortManifest Short() {
      return Short;
   }

   public ManifestFactory.CharManifest Char() {
      return Char;
   }

   public ManifestFactory.IntManifest Int() {
      return Int;
   }

   public ManifestFactory.LongManifest Long() {
      return Long;
   }

   public ManifestFactory.FloatManifest Float() {
      return Float;
   }

   public ManifestFactory.DoubleManifest Double() {
      return Double;
   }

   public ManifestFactory.BooleanManifest Boolean() {
      return Boolean;
   }

   public ManifestFactory.UnitManifest Unit() {
      return Unit;
   }

   public ClassTag Any() {
      return Any;
   }

   public ClassTag Object() {
      return Object;
   }

   public ClassTag AnyVal() {
      return AnyVal;
   }

   public ClassTag AnyRef() {
      return AnyRef;
   }

   public ClassTag Nothing() {
      return Nothing;
   }

   public ClassTag Null() {
      return Null;
   }

   private boolean cacheDisabled() {
      return cacheDisabled;
   }

   public ClassTag apply(final Class runtimeClass1) {
      if (this.cacheDisabled()) {
         return ClassTag.cache$.MODULE$.computeTag(runtimeClass1);
      } else {
         ClassTag tag = (ClassTag)((WeakReference)ClassTag.cache$.MODULE$.get(runtimeClass1)).get();
         if (tag == null) {
            ClassTag.cache$.MODULE$.remove(runtimeClass1);
            tag = ClassTag.cache$.MODULE$.computeTag(runtimeClass1);
         }

         return tag;
      }
   }

   public Option unapply(final ClassTag ctag) {
      return new Some(ctag.runtimeClass());
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ClassTag$.class);
   }

   private ClassTag$() {
   }
}
