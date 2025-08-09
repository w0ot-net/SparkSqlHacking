package scala.reflect;

import scala.None$;
import scala.Some;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Seq;
import scala.runtime.Nothing$;
import scala.runtime.Null$;
import scala.runtime.ScalaRunTime$;

public final class ManifestFactory$ {
   public static final ManifestFactory$ MODULE$ = new ManifestFactory$();
   private static final ManifestFactory.ByteManifest Byte = new ManifestFactory.ByteManifest();
   private static final ManifestFactory.ShortManifest Short = new ManifestFactory.ShortManifest();
   private static final ManifestFactory.CharManifest Char = new ManifestFactory.CharManifest();
   private static final ManifestFactory.IntManifest Int = new ManifestFactory.IntManifest();
   private static final ManifestFactory.LongManifest Long = new ManifestFactory.LongManifest();
   private static final ManifestFactory.FloatManifest Float = new ManifestFactory.FloatManifest();
   private static final ManifestFactory.DoubleManifest Double = new ManifestFactory.DoubleManifest();
   private static final ManifestFactory.BooleanManifest Boolean = new ManifestFactory.BooleanManifest();
   private static final ManifestFactory.UnitManifest Unit = new ManifestFactory.UnitManifest();
   public static final Class scala$reflect$ManifestFactory$$ObjectTYPE = Object.class;
   public static final Class scala$reflect$ManifestFactory$$NothingTYPE = Nothing$.class;
   public static final Class scala$reflect$ManifestFactory$$NullTYPE = Null$.class;
   private static final Manifest Any = new ManifestFactory.AnyManifest();
   private static final Manifest Object = new ManifestFactory.ObjectManifest();
   private static final Manifest AnyRef;
   private static final Manifest AnyVal;
   private static final Manifest Null;
   private static final Manifest Nothing;

   static {
      AnyRef = MODULE$.Object();
      AnyVal = new ManifestFactory.AnyValPhantomManifest();
      Null = new ManifestFactory.NullManifest();
      Nothing = new ManifestFactory.NothingManifest();
   }

   public List valueManifests() {
      List$ var10000 = scala.package$.MODULE$.List();
      ArraySeq apply_elems = ScalaRunTime$.MODULE$.wrapRefArray(new AnyValManifest[]{this.Byte(), this.Short(), this.Char(), this.Int(), this.Long(), this.Float(), this.Double(), this.Boolean(), this.Unit()});
      if (var10000 == null) {
         throw null;
      } else {
         return Nil$.MODULE$.prependedAll(apply_elems);
      }
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
      ManifestFactory.ClassTypeManifest var10000 = new ManifestFactory.ClassTypeManifest;
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

   public Manifest classType(final Manifest prefix, final Class clazz, final Seq args) {
      return new ManifestFactory.ClassTypeManifest(new Some(prefix), clazz, args.toList());
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
      return new ManifestFactory.IntersectionTypeManifest((Manifest[])parents.toArray(ClassTag$.MODULE$.apply(Manifest.class)));
   }

   private ManifestFactory$() {
   }
}
