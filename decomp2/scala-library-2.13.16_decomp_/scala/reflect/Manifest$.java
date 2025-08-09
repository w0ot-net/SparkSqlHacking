package scala.reflect;

import java.io.Serializable;
import scala.None$;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Seq;
import scala.runtime.ModuleSerializationProxy;

public final class Manifest$ implements Serializable {
   public static final Manifest$ MODULE$ = new Manifest$();
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
   private static final Manifest AnyRef;
   private static final Manifest AnyVal;
   private static final Manifest Null;
   private static final Manifest Nothing;

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
      AnyRef = ManifestFactory$.MODULE$.AnyRef();
      AnyVal = ManifestFactory$.MODULE$.AnyVal();
      Null = ManifestFactory$.MODULE$.Null();
      Nothing = ManifestFactory$.MODULE$.Nothing();
   }

   public List valueManifests() {
      return ManifestFactory$.MODULE$.valueManifests();
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

   public Manifest AnyRef() {
      return AnyRef;
   }

   public Manifest AnyVal() {
      return AnyVal;
   }

   public Manifest Null() {
      return Null;
   }

   public Manifest Nothing() {
      return Nothing;
   }

   public Manifest singleType(final Object value) {
      return new ManifestFactory.SingletonTypeManifest(value);
   }

   public Manifest classType(final Class clazz) {
      return new ManifestFactory.ClassTypeManifest(None$.MODULE$, clazz, Nil$.MODULE$);
   }

   public Manifest classType(final Class clazz, final Manifest arg1, final Seq args) {
      return ManifestFactory$.MODULE$.classType(clazz, arg1, args);
   }

   public Manifest classType(final Manifest prefix, final Class clazz, final Seq args) {
      return ManifestFactory$.MODULE$.classType(prefix, clazz, args);
   }

   public Manifest arrayType(final Manifest arg) {
      return arg.arrayManifest();
   }

   public Manifest abstractType(final Manifest prefix, final String name, final Class upperBound, final Seq args) {
      return new ManifestFactory.AbstractTypeManifest(prefix, name, upperBound, args);
   }

   public Manifest wildcardType(final Manifest lowerBound, final Manifest upperBound) {
      return new ManifestFactory.WildcardManifest(lowerBound, upperBound);
   }

   public Manifest intersectionType(final Seq parents) {
      return ManifestFactory$.MODULE$.intersectionType(parents);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Manifest$.class);
   }

   private Manifest$() {
   }
}
