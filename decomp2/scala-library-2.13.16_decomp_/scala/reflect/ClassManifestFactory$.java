package scala.reflect;

import scala.MatchError;
import scala.None$;
import scala.Some;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Seq;

public final class ClassManifestFactory$ {
   public static final ClassManifestFactory$ MODULE$ = new ClassManifestFactory$();
   private static final ManifestFactory.ByteManifest Byte;
   private static final ManifestFactory.ShortManifest Short;
   private static final ManifestFactory.CharManifest Char;
   private static final ManifestFactory.IntManifest Int;
   private static final ManifestFactory.LongManifest Long;
   private static final ManifestFactory.FloatManifest Float;
   private static final ManifestFactory.DoubleManifest Double;
   private static final ManifestFactory.BooleanManifest Boolean;
   private static final ManifestFactory.UnitManifest Unit;
   private static final Manifest Any;
   private static final Manifest Object;
   private static final Manifest AnyVal;
   private static final Manifest Nothing;
   private static final Manifest Null;

   static {
      Byte = ManifestFactory$.MODULE$.Byte();
      Short = ManifestFactory$.MODULE$.Short();
      Char = ManifestFactory$.MODULE$.Char();
      Int = ManifestFactory$.MODULE$.Int();
      Long = ManifestFactory$.MODULE$.Long();
      Float = ManifestFactory$.MODULE$.Float();
      Double = ManifestFactory$.MODULE$.Double();
      Boolean = ManifestFactory$.MODULE$.Boolean();
      Unit = ManifestFactory$.MODULE$.Unit();
      Any = ManifestFactory$.MODULE$.Any();
      Object = ManifestFactory$.MODULE$.Object();
      AnyVal = ManifestFactory$.MODULE$.AnyVal();
      Nothing = ManifestFactory$.MODULE$.Nothing();
      Null = ManifestFactory$.MODULE$.Null();
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

   public Manifest Any() {
      return Any;
   }

   public Manifest Object() {
      return Object;
   }

   public Manifest AnyVal() {
      return AnyVal;
   }

   public Manifest Nothing() {
      return Nothing;
   }

   public Manifest Null() {
      return Null;
   }

   public ClassTag fromClass(final Class clazz) {
      Class var10000 = java.lang.Byte.TYPE;
      if (var10000 == null) {
         if (clazz == null) {
            return this.Byte();
         }
      } else if (var10000.equals(clazz)) {
         return this.Byte();
      }

      var10000 = java.lang.Short.TYPE;
      if (var10000 == null) {
         if (clazz == null) {
            return this.Short();
         }
      } else if (var10000.equals(clazz)) {
         return this.Short();
      }

      var10000 = Character.TYPE;
      if (var10000 == null) {
         if (clazz == null) {
            return this.Char();
         }
      } else if (var10000.equals(clazz)) {
         return this.Char();
      }

      var10000 = Integer.TYPE;
      if (var10000 == null) {
         if (clazz == null) {
            return this.Int();
         }
      } else if (var10000.equals(clazz)) {
         return this.Int();
      }

      var10000 = java.lang.Long.TYPE;
      if (var10000 == null) {
         if (clazz == null) {
            return this.Long();
         }
      } else if (var10000.equals(clazz)) {
         return this.Long();
      }

      var10000 = java.lang.Float.TYPE;
      if (var10000 == null) {
         if (clazz == null) {
            return this.Float();
         }
      } else if (var10000.equals(clazz)) {
         return this.Float();
      }

      var10000 = java.lang.Double.TYPE;
      if (var10000 == null) {
         if (clazz == null) {
            return this.Double();
         }
      } else if (var10000.equals(clazz)) {
         return this.Double();
      }

      var10000 = java.lang.Boolean.TYPE;
      if (var10000 == null) {
         if (clazz == null) {
            return this.Boolean();
         }
      } else if (var10000.equals(clazz)) {
         return this.Boolean();
      }

      var10000 = Void.TYPE;
      if (var10000 == null) {
         if (clazz == null) {
            return this.Unit();
         }
      } else if (var10000.equals(clazz)) {
         return this.Unit();
      }

      return new ClassTypeManifest(None$.MODULE$, clazz, Nil$.MODULE$);
   }

   public Manifest singleType(final Object value) {
      Manifest$ var10000 = Manifest$.MODULE$;
      return new ManifestFactory.SingletonTypeManifest(value);
   }

   public ClassTag classType(final Class clazz) {
      return new ClassTypeManifest(None$.MODULE$, clazz, Nil$.MODULE$);
   }

   public ClassTag classType(final Class clazz, final OptManifest arg1, final Seq args) {
      ClassTypeManifest var10000 = new ClassTypeManifest;
      None$ var10002 = None$.MODULE$;
      List var10004 = args.toList();
      if (var10004 == null) {
         throw null;
      } else {
         List $colon$colon_this = var10004;
         $colon$colon var6 = new $colon$colon(arg1, $colon$colon_this);
         $colon$colon_this = null;
         var10000.<init>(var10002, clazz, var6);
         return var10000;
      }
   }

   public ClassTag classType(final OptManifest prefix, final Class clazz, final Seq args) {
      return new ClassTypeManifest(new Some(prefix), clazz, args.toList());
   }

   public ClassTag arrayType(final OptManifest arg) {
      if (NoManifest$.MODULE$.equals(arg)) {
         return this.Object();
      } else if (arg instanceof ClassTag) {
         return ((ClassTag)arg).arrayManifest();
      } else {
         throw new MatchError(arg);
      }
   }

   public ClassTag abstractType(final OptManifest prefix, final String name, final Class clazz, final Seq args) {
      return new ClassManifestFactory.AbstractTypeClassManifest(prefix, name, clazz, Nil$.MODULE$);
   }

   public ClassTag abstractType(final OptManifest prefix, final String name, final ClassTag upperbound, final Seq args) {
      return new ClassManifestFactory.AbstractTypeClassManifest(prefix, name, upperbound.runtimeClass(), Nil$.MODULE$);
   }

   private ClassManifestFactory$() {
   }
}
