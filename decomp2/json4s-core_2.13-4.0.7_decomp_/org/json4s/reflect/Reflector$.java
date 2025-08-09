package org.json4s.reflect;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import org.json4s.DefaultFormats$;
import org.json4s.Formats;
import scala.Option;
import scala.Symbol;
import scala.Predef.;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.reflect.Manifest;
import scala.runtime.BoxesRunTime;

public final class Reflector$ {
   public static final Reflector$ MODULE$ = new Reflector$();
   private static final Memo rawClasses = new Memo();
   private static final Memo unmangledNames = new Memo();
   private static final Memo descriptors = new Memo();
   private static final Set primitives;
   private static final Memo stringTypes;

   static {
      primitives = (Set).MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Type[]{String.class, Integer.TYPE, Long.TYPE, Double.TYPE, Float.TYPE, Byte.TYPE, BigInt.class, Boolean.TYPE, Short.TYPE, Integer.class, Long.class, Double.class, Float.class, BigDecimal.class, Byte.class, Boolean.class, Number.class, Short.class, Date.class, Timestamp.class, Symbol.class, java.math.BigDecimal.class, BigInteger.class})));
      stringTypes = new Memo();
   }

   public void clearCaches() {
      rawClasses.clear();
      unmangledNames.clear();
      descriptors.clear();
      stringTypes.clear();
   }

   public boolean isPrimitive(final Type t, final Set extra) {
      return primitives.apply(t) || extra.apply(t);
   }

   public Set isPrimitive$default$2() {
      return .MODULE$.Set().empty();
   }

   public ScalaType scalaTypeOf(final Manifest mf) {
      return ScalaType$.MODULE$.apply(mf);
   }

   public ScalaType scalaTypeOf(final Class clazz) {
      return ScalaType$.MODULE$.apply(ManifestFactory$.MODULE$.manifestOf((Type)clazz));
   }

   public ScalaType scalaTypeOf(final Type t) {
      return ScalaType$.MODULE$.apply(ManifestFactory$.MODULE$.manifestOf(t));
   }

   public Option scalaTypeOf(final String name) {
      return (Option)stringTypes.apply(name, (x$1) -> ScalaSigReader$.MODULE$.resolveClass(x$1, package$.MODULE$.ClassLoaders()).map((c) -> MODULE$.scalaTypeOf(c)));
   }

   public ObjectDescriptor describe(final Manifest mf, final Formats formats) {
      return this.describeWithFormats(package$.MODULE$.scalaTypeDescribable(this.scalaTypeOf(mf), formats), formats);
   }

   /** @deprecated */
   public ObjectDescriptor describe(final ReflectorDescribable st) {
      return (ObjectDescriptor)descriptors.apply(st.scalaType(), (x$2) -> MODULE$.createDescriptorWithFormats(x$2, st.paranamer(), st.companionClasses(), DefaultFormats$.MODULE$));
   }

   public Formats describe$default$2() {
      return DefaultFormats$.MODULE$;
   }

   public ObjectDescriptor describeWithFormats(final ReflectorDescribable st, final Formats formats) {
      return (ObjectDescriptor)descriptors.apply(st.scalaType(), (x$3) -> MODULE$.createDescriptorWithFormats(x$3, st.paranamer(), st.companionClasses(), formats));
   }

   /** @deprecated */
   public ObjectDescriptor createDescriptor(final ScalaType tpe, final ParameterNameReader paramNameReader, final List companionMappings) {
      return this.createDescriptorWithFormats(tpe, paramNameReader, companionMappings, DefaultFormats$.MODULE$);
   }

   public ParameterNameReader createDescriptor$default$2() {
      return ParanamerReader$.MODULE$;
   }

   public List createDescriptor$default$3() {
      return scala.package..MODULE$.Nil();
   }

   public ObjectDescriptor createDescriptorWithFormats(final ScalaType tpe, final ParameterNameReader paramNameReader, final List companionMappings, final Formats formats) {
      return (ObjectDescriptor)(tpe.isPrimitive() ? new PrimitiveDescriptor(tpe, PrimitiveDescriptor$.MODULE$.apply$default$2()) : (new Reflector.ClassDescriptorBuilder(tpe, paramNameReader, companionMappings, formats)).result());
   }

   public ParameterNameReader createDescriptorWithFormats$default$2() {
      return ParanamerReader$.MODULE$;
   }

   public List createDescriptorWithFormats$default$3() {
      return scala.package..MODULE$.Nil();
   }

   public Option defaultValue(final Class compClass, final Object compObj, final int argIndex, final String pattern) {
      return (Option)scala.util.control.Exception..MODULE$.allCatch().withApply((x$16) -> scala.None..MODULE$).apply(() -> (Option)(compObj == null ? scala.None..MODULE$ : scala.Option..MODULE$.apply(compClass.getMethod(scala.collection.StringOps..MODULE$.format$extension(.MODULE$.augmentString(pattern), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(argIndex + 1)})))).map((meth) -> () -> meth.invoke(compObj))));
   }

   public Class rawClassOf(final Type t) {
      return (Class)rawClasses.apply(t, (x0$1) -> {
         Class var1;
         if (x0$1 instanceof Class) {
            Class var3 = (Class)x0$1;
            var1 = var3;
         } else {
            if (!(x0$1 instanceof ParameterizedType)) {
               throw scala.sys.package..MODULE$.error((new StringBuilder(22)).append("Raw type of ").append(x0$1).append(" not known").toString());
            }

            ParameterizedType var4 = (ParameterizedType)x0$1;
            var1 = MODULE$.rawClassOf(var4.getRawType());
         }

         return var1;
      });
   }

   public String unmangleName(final String name) {
      return (String)unmangledNames.apply(name, (name0) -> scala.reflect.NameTransformer..MODULE$.decode(name0));
   }

   public ParameterizedType mkParameterizedType(final Type owner, final Seq typeArgs) {
      return new ParameterizedType(typeArgs, owner) {
         private final Seq typeArgs$1;
         private final Type owner$2;

         public Type[] getActualTypeArguments() {
            return (Type[])this.typeArgs$1.toArray(scala.reflect.ClassTag..MODULE$.apply(Type.class));
         }

         public Type getOwnerType() {
            return this.owner$2;
         }

         public Class getRawType() {
            return Reflector$.MODULE$.rawClassOf(this.owner$2);
         }

         public String toString() {
            return (new StringBuilder(2)).append(this.getOwnerType().toString()).append("[").append(.MODULE$.wrapRefArray((Object[])this.getActualTypeArguments()).mkString(",")).append("]").toString();
         }

         public {
            this.typeArgs$1 = typeArgs$1;
            this.owner$2 = owner$2;
         }
      };
   }

   private Reflector$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
