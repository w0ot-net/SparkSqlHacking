package com.fasterxml.jackson.module.scala.introspect;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.util.LookupCache;
import com.fasterxml.jackson.module.scala.DefaultLookupCacheFactory$;
import com.fasterxml.jackson.module.scala.JacksonModule;
import com.fasterxml.jackson.module.scala.LookupCacheFactory;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Some;
import scala.collection.mutable.Map;
import scala.collection.mutable.Map.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005MgaB\u0010!!\u0003\r\t!\f\u0005\u0006q\u0001!\t!\u000f\u0005\b\u007f\u0001\u0001\r\u0011\"\u0003A\u0011\u001d!\u0005\u00011A\u0005\n\u0015Cq\u0001\u0013\u0001A\u0002\u0013%\u0011\nC\u0004N\u0001\u0001\u0007I\u0011\u0002(\t\u000fA\u0003\u0001\u0019!C\u0005#\"9Q\u000b\u0001a\u0001\n\u00131\u0006b\u0002-\u0001\u0001\u0004%I!\u0015\u0005\b3\u0002\u0001\r\u0011\"\u0003[\u0011!a\u0006\u00011A\u0005\u0002\u0001j\u0006\u0002C:\u0001\u0001\u0004%\t\u0001\t;\t\u0011Y\u0004\u0001\u0019!C\u0001A]D\u0001\"\u001f\u0001A\u0002\u0013\u0005\u0001E\u001f\u0005\ty\u0002\u0011\r\u0011\"\u0001!{\"9\u00111\u0003\u0001\u0005\u0002\u0005U\u0001bBA\u000e\u0001\u0011\u0005\u0011Q\u0004\u0005\b\u0003G\u0001A\u0011AA\u0013\u0011\u0019\tI\u0003\u0001C\u0005s!1\u00111\u0006\u0001\u0005\neBq!!\f\u0001\t\u0003\ty\u0003C\u0004\u0002f\u0001!\t!a\u001a\t\u000f\u0005\u001d\u0005\u0001\"\u0001\u0002\n\"1\u0011q\u0011\u0001\u0005\u0002eBq!a&\u0001\t\u0003\tI\nC\u0004\u00024\u0002!\t!!.\t\u000f\u0005m\u0006\u0001\"\u0001\u0002>\"9\u00111\u0019\u0001\u0005\u0002\u0005\u0015waBAdA!\u0005\u0011\u0011\u001a\u0004\u0007?\u0001B\t!a3\t\u000f\u0005=W\u0004\"\u0001\u0002R\n\t3kY1mC\u0006sgn\u001c;bi&|g.\u00138ue>\u001c\b/Z2u_Jlu\u000eZ;mK*\u0011\u0011EI\u0001\u000bS:$(o\\:qK\u000e$(BA\u0012%\u0003\u0015\u00198-\u00197b\u0015\t)c%\u0001\u0004n_\u0012,H.\u001a\u0006\u0003O!\nqA[1dWN|gN\u0003\u0002*U\u0005Ia-Y:uKJDX\u000e\u001c\u0006\u0002W\u0005\u00191m\\7\u0004\u0001M\u0019\u0001A\f\u001b\u0011\u0005=\u0012T\"\u0001\u0019\u000b\u0005E2\u0013\u0001\u00033bi\u0006\u0014\u0017N\u001c3\n\u0005M\u0002$AB'pIVdW\r\u0005\u00026m5\t!%\u0003\u00028E\ti!*Y2lg>tWj\u001c3vY\u0016\fa\u0001J5oSR$C#\u0001\u001e\u0011\u0005mjT\"\u0001\u001f\u000b\u0003\rJ!A\u0010\u001f\u0003\tUs\u0017\u000e^\u0001\u0014?2|wn[;q\u0007\u0006\u001c\u0007.\u001a$bGR|'/_\u000b\u0002\u0003B\u0011QGQ\u0005\u0003\u0007\n\u0012!\u0003T8pWV\u00048)Y2iK\u001a\u000b7\r^8ss\u00069r\f\\8pWV\u00048)Y2iK\u001a\u000b7\r^8ss~#S-\u001d\u000b\u0003u\u0019CqaR\u0002\u0002\u0002\u0003\u0007\u0011)A\u0002yIE\n1dX:i_VdGmU;qa>\u0014HoU2bY\u0006\u001c4\t\\1tg\u0016\u001cX#\u0001&\u0011\u0005mZ\u0015B\u0001'=\u0005\u001d\u0011un\u001c7fC:\fqdX:i_VdGmU;qa>\u0014HoU2bY\u0006\u001c4\t\\1tg\u0016\u001cx\fJ3r)\tQt\nC\u0004H\u000b\u0005\u0005\t\u0019\u0001&\u0002)}#Wm]2sSB$xN]\"bG\",7+\u001b>f+\u0005\u0011\u0006CA\u001eT\u0013\t!FHA\u0002J]R\f\u0001d\u00183fg\u000e\u0014\u0018\u000e\u001d;pe\u000e\u000b7\r[3TSj,w\fJ3r)\tQt\u000bC\u0004H\u000f\u0005\u0005\t\u0019\u0001*\u0002'}\u001b8-\u00197b)f\u0004XmQ1dQ\u0016\u001c\u0016N_3\u0002/}\u001b8-\u00197b)f\u0004XmQ1dQ\u0016\u001c\u0016N_3`I\u0015\fHC\u0001\u001e\\\u0011\u001d9\u0015\"!AA\u0002I\u000b\u0001c\u00183fg\u000e\u0014\u0018\u000e\u001d;pe\u000e\u000b7\r[3\u0016\u0003y\u0003Ba\u00182e_6\t\u0001M\u0003\u0002ba\u0005!Q\u000f^5m\u0013\t\u0019\u0007MA\u0006M_>\\W\u000f]\"bG\",\u0007CA3m\u001d\t1'\u000e\u0005\u0002hy5\t\u0001N\u0003\u0002jY\u00051AH]8pizJ!a\u001b\u001f\u0002\rA\u0013X\rZ3g\u0013\tigN\u0001\u0004TiJLgn\u001a\u0006\u0003Wr\u0002\"\u0001]9\u000e\u0003\u0001J!A\u001d\u0011\u0003\u001d\t+\u0017M\u001c#fg\u000e\u0014\u0018\u000e\u001d;pe\u0006!r\fZ3tGJL\u0007\u000f^8s\u0007\u0006\u001c\u0007.Z0%KF$\"AO;\t\u000f\u001d[\u0011\u0011!a\u0001=\u0006yql]2bY\u0006$\u0016\u0010]3DC\u000eDW-F\u0001y!\u0011y&\r\u001a&\u0002'}\u001b8-\u00197b)f\u0004XmQ1dQ\u0016|F%Z9\u0015\u0005iZ\bbB$\u000e\u0003\u0003\u0005\r\u0001_\u0001\f_Z,'O]5eK6\u000b\u0007/F\u0001\u007f!\u0019y\u0018\u0011\u00023\u0002\u000e5\u0011\u0011\u0011\u0001\u0006\u0005\u0003\u0007\t)!A\u0004nkR\f'\r\\3\u000b\u0007\u0005\u001dA(\u0001\u0006d_2dWm\u0019;j_:LA!a\u0003\u0002\u0002\t\u0019Q*\u00199\u0011\u0007A\fy!C\u0002\u0002\u0012\u0001\u0012ab\u00117bgN|e/\u001a:sS\u0012,7/A\u000btKRdun\\6va\u000e\u000b7\r[3GC\u000e$xN]=\u0015\u0007i\n9\u0002\u0003\u0004\u0002\u001a=\u0001\r!Q\u0001\u0013Y>|7.\u001e9DC\u000eDWMR1di>\u0014\u00180\u0001\ftKR$Um]2sSB$xN]\"bG\",7+\u001b>f)\rQ\u0014q\u0004\u0005\u0007\u0003C\u0001\u0002\u0019\u0001*\u0002\tML'0Z\u0001\u0016g\u0016$8kY1mCRK\b/Z\"bG\",7+\u001b>f)\rQ\u0014q\u0005\u0005\u0007\u0003C\t\u0002\u0019\u0001*\u0002/I,7M]3bi\u0016$Um]2sSB$xN]\"bG\",\u0017A\u0006:fGJ,\u0017\r^3TG\u0006d\u0017\rV=qK\u000e\u000b7\r[3\u00027I,w-[:uKJ\u0014VMZ3sK:\u001cW\r\u001a,bYV,G+\u001f9f)\u001dQ\u0014\u0011GA*\u0003/Bq!a\r\u0015\u0001\u0004\t)$A\u0003dY\u0006T(\u0010\r\u0003\u00028\u0005\u0005\u0003#B3\u0002:\u0005u\u0012bAA\u001e]\n)1\t\\1tgB!\u0011qHA!\u0019\u0001!A\"a\u0011\u00022\u0005\u0005\t\u0011!B\u0001\u0003\u000b\u00121a\u0018\u00135#\u0011\t9%!\u0014\u0011\u0007m\nI%C\u0002\u0002Lq\u0012qAT8uQ&tw\rE\u0002<\u0003\u001fJ1!!\u0015=\u0005\r\te.\u001f\u0005\u0007\u0003+\"\u0002\u0019\u00013\u0002\u0013\u0019LW\r\u001c3OC6,\u0007bBA-)\u0001\u0007\u00111L\u0001\u000fe\u00164WM]3oG\u0016$G+\u001f9fa\u0011\ti&!\u0019\u0011\u000b\u0015\fI$a\u0018\u0011\t\u0005}\u0012\u0011\r\u0003\r\u0003G\n9&!A\u0001\u0002\u000b\u0005\u0011Q\t\u0002\u0004?\u0012*\u0014\u0001I4fiJ+w-[:uKJ,GMU3gKJ,gnY3e-\u0006dW/\u001a+za\u0016$b!!\u001b\u0002z\u0005\u0015\u0005#B\u001e\u0002l\u0005=\u0014bAA7y\t1q\n\u001d;j_:\u0004D!!\u001d\u0002vA)Q-!\u000f\u0002tA!\u0011qHA;\t-\t9(FA\u0001\u0002\u0003\u0015\t!!\u0012\u0003\u0007}#s\u0007C\u0004\u00024U\u0001\r!a\u001f1\t\u0005u\u0014\u0011\u0011\t\u0006K\u0006e\u0012q\u0010\t\u0005\u0003\u007f\t\t\t\u0002\u0007\u0002\u0004\u0006e\u0014\u0011!A\u0001\u0006\u0003\t)EA\u0002`IYBa!!\u0016\u0016\u0001\u0004!\u0017AH2mK\u0006\u0014(+Z4jgR,'/\u001a3SK\u001a,'/\u001a8dK\u0012$\u0016\u0010]3t)\rQ\u00141\u0012\u0005\b\u0003g1\u0002\u0019AAGa\u0011\ty)a%\u0011\u000b\u0015\fI$!%\u0011\t\u0005}\u00121\u0013\u0003\r\u0003+\u000bY)!A\u0001\u0002\u000b\u0005\u0011Q\t\u0002\u0004?\u0012B\u0014AE:fi\u0012+7o\u0019:jaR|'oQ1dQ\u0016$2AXAN\u0011\u0019\ti\n\u0007a\u0001=\u0006)1-Y2iK\"Z\u0001$!)\u0002(\u0006%\u0016QVAX!\rY\u00141U\u0005\u0004\u0003Kc$A\u00033faJ,7-\u0019;fI\u00069Q.Z:tC\u001e,\u0017EAAV\u0003y[W-\u001f\u0011usB,\u0007e^5mY\u0002\u001a\u0007.\u00198hK\u0002\"x\u000eI*ue&tw\rI5oAY\u0014d&M\u001b/a\u0001\ng\u000e\u001a\u0011uQ&\u001c\bEZ;oGRLwN\u001c\u0011xS2d\u0007EY3!e\u0016lwN^3eA%t\u0007%\u0019\u0011mCR,'\u000f\t:fY\u0016\f7/Z\u0001\u0006g&t7-Z\u0011\u0003\u0003c\u000baA\r\u00182i9\u001a\u0014!E:fiN\u001b\u0017\r\\1UsB,7)Y2iKR\u0019\u00010a.\t\r\u0005u\u0015\u00041\u0001yQ-I\u0012\u0011UAT\u0003S\u000bi+a,\u0002)M,\b\u000f]8siN\u001b\u0017\r\\14\u00072\f7o]3t)\rQ\u0014q\u0018\u0005\u0007\u0003\u0003T\u0002\u0019\u0001&\u0002\u000fM,\b\u000f]8si\u0006Q2\u000f[8vY\u0012\u001cV\u000f\u001d9peR\u001c6-\u00197bg\rc\u0017m]:fgR\t!*A\u0011TG\u0006d\u0017-\u00118o_R\fG/[8o\u0013:$(o\\:qK\u000e$xN]'pIVdW\r\u0005\u0002q;M!QDLAg!\t\u0001\b!\u0001\u0004=S:LGO\u0010\u000b\u0003\u0003\u0013\u0004"
)
public interface ScalaAnnotationIntrospectorModule extends JacksonModule {
   static Iterable getDependencies() {
      return ScalaAnnotationIntrospectorModule$.MODULE$.getDependencies();
   }

   static Object getTypeId() {
      return ScalaAnnotationIntrospectorModule$.MODULE$.getTypeId();
   }

   void com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$_setter_$overrideMap_$eq(final Map x$1);

   LookupCacheFactory com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_lookupCacheFactory();

   void com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_lookupCacheFactory_$eq(final LookupCacheFactory x$1);

   boolean com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_shouldSupportScala3Classes();

   void com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_shouldSupportScala3Classes_$eq(final boolean x$1);

   int com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_descriptorCacheSize();

   void com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_descriptorCacheSize_$eq(final int x$1);

   int com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_scalaTypeCacheSize();

   void com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_scalaTypeCacheSize_$eq(final int x$1);

   LookupCache _descriptorCache();

   void _descriptorCache_$eq(final LookupCache x$1);

   LookupCache _scalaTypeCache();

   void _scalaTypeCache_$eq(final LookupCache x$1);

   Map overrideMap();

   // $FF: synthetic method
   static void setLookupCacheFactory$(final ScalaAnnotationIntrospectorModule $this, final LookupCacheFactory lookupCacheFactory) {
      $this.setLookupCacheFactory(lookupCacheFactory);
   }

   default void setLookupCacheFactory(final LookupCacheFactory lookupCacheFactory) {
      this.com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_lookupCacheFactory_$eq(lookupCacheFactory);
      this.recreateDescriptorCache();
      this.recreateScalaTypeCache();
   }

   // $FF: synthetic method
   static void setDescriptorCacheSize$(final ScalaAnnotationIntrospectorModule $this, final int size) {
      $this.setDescriptorCacheSize(size);
   }

   default void setDescriptorCacheSize(final int size) {
      this.com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_descriptorCacheSize_$eq(size);
      this.recreateDescriptorCache();
   }

   // $FF: synthetic method
   static void setScalaTypeCacheSize$(final ScalaAnnotationIntrospectorModule $this, final int size) {
      $this.setScalaTypeCacheSize(size);
   }

   default void setScalaTypeCacheSize(final int size) {
      this.com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_scalaTypeCacheSize_$eq(size);
      this.recreateScalaTypeCache();
   }

   private void recreateDescriptorCache() {
      this._descriptorCache().clear();
      this._descriptorCache_$eq(this.com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_lookupCacheFactory().createLookupCache(16, this.com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_descriptorCacheSize()));
   }

   private void recreateScalaTypeCache() {
      this._scalaTypeCache().clear();
      this._scalaTypeCache_$eq(this.com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_lookupCacheFactory().createLookupCache(16, this.com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_scalaTypeCacheSize()));
   }

   // $FF: synthetic method
   static void registerReferencedValueType$(final ScalaAnnotationIntrospectorModule $this, final Class clazz, final String fieldName, final Class referencedType) {
      $this.registerReferencedValueType(clazz, fieldName, referencedType);
   }

   default void registerReferencedValueType(final Class clazz, final String fieldName, final Class referencedType) {
      Map overrides = ((ClassOverrides)this.overrideMap().getOrElseUpdate(clazz.getName(), () -> new ClassOverrides(ClassOverrides$.MODULE$.apply$default$1()))).overrides();
      Option var6 = overrides.get(fieldName);
      if (var6 instanceof Some) {
         Some var7 = (Some)var6;
         ClassHolder holder = (ClassHolder)var7.value();
         overrides.put(fieldName, holder.copy(new Some(referencedType)));
         BoxedUnit var9 = BoxedUnit.UNIT;
      } else {
         overrides.put(fieldName, new ClassHolder(new Some(referencedType)));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   // $FF: synthetic method
   static Option getRegisteredReferencedValueType$(final ScalaAnnotationIntrospectorModule $this, final Class clazz, final String fieldName) {
      return $this.getRegisteredReferencedValueType(clazz, fieldName);
   }

   default Option getRegisteredReferencedValueType(final Class clazz, final String fieldName) {
      return this.overrideMap().get(clazz.getName()).flatMap((overrides) -> overrides.overrides().get(fieldName).flatMap((x$21) -> x$21.valueClass()));
   }

   // $FF: synthetic method
   static void clearRegisteredReferencedTypes$(final ScalaAnnotationIntrospectorModule $this, final Class clazz) {
      $this.clearRegisteredReferencedTypes(clazz);
   }

   default void clearRegisteredReferencedTypes(final Class clazz) {
      this.overrideMap().remove(clazz.getName());
   }

   // $FF: synthetic method
   static void clearRegisteredReferencedTypes$(final ScalaAnnotationIntrospectorModule $this) {
      $this.clearRegisteredReferencedTypes();
   }

   default void clearRegisteredReferencedTypes() {
      this.overrideMap().clear();
   }

   // $FF: synthetic method
   static LookupCache setDescriptorCache$(final ScalaAnnotationIntrospectorModule $this, final LookupCache cache) {
      return $this.setDescriptorCache(cache);
   }

   /** @deprecated */
   default LookupCache setDescriptorCache(final LookupCache cache) {
      LookupCache existingCache = this._descriptorCache();
      this._descriptorCache_$eq(cache);
      return existingCache;
   }

   // $FF: synthetic method
   static LookupCache setScalaTypeCache$(final ScalaAnnotationIntrospectorModule $this, final LookupCache cache) {
      return $this.setScalaTypeCache(cache);
   }

   /** @deprecated */
   default LookupCache setScalaTypeCache(final LookupCache cache) {
      LookupCache existingCache = this._scalaTypeCache();
      this._scalaTypeCache_$eq(cache);
      return existingCache;
   }

   // $FF: synthetic method
   static void supportScala3Classes$(final ScalaAnnotationIntrospectorModule $this, final boolean support) {
      $this.supportScala3Classes(support);
   }

   default void supportScala3Classes(final boolean support) {
      this.com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_shouldSupportScala3Classes_$eq(support);
   }

   // $FF: synthetic method
   static boolean shouldSupportScala3Classes$(final ScalaAnnotationIntrospectorModule $this) {
      return $this.shouldSupportScala3Classes();
   }

   default boolean shouldSupportScala3Classes() {
      return this.com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_shouldSupportScala3Classes();
   }

   // $FF: synthetic method
   static void $anonfun$$init$$1(final Module.SetupContext x$18) {
      x$18.appendAnnotationIntrospector(JavaAnnotationIntrospector$.MODULE$);
   }

   // $FF: synthetic method
   static void $anonfun$$init$$2(final Module.SetupContext x$19) {
      x$19.appendAnnotationIntrospector(ScalaAnnotationIntrospector$.MODULE$);
   }

   // $FF: synthetic method
   static void $anonfun$$init$$3(final Module.SetupContext x$20) {
      x$20.addValueInstantiators(ScalaAnnotationIntrospector$.MODULE$);
   }

   static void $init$(final ScalaAnnotationIntrospectorModule $this) {
      $this.$plus$eq((x$18) -> {
         $anonfun$$init$$1(x$18);
         return BoxedUnit.UNIT;
      });
      $this.$plus$eq((x$19) -> {
         $anonfun$$init$$2(x$19);
         return BoxedUnit.UNIT;
      });
      $this.$plus$eq((x$20) -> {
         $anonfun$$init$$3(x$20);
         return BoxedUnit.UNIT;
      });
      $this.com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_lookupCacheFactory_$eq(DefaultLookupCacheFactory$.MODULE$);
      $this.com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_shouldSupportScala3Classes_$eq(true);
      $this.com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_descriptorCacheSize_$eq(100);
      $this.com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_scalaTypeCacheSize_$eq(1000);
      $this._descriptorCache_$eq($this.com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_lookupCacheFactory().createLookupCache(16, $this.com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_descriptorCacheSize()));
      $this._scalaTypeCache_$eq($this.com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_lookupCacheFactory().createLookupCache(16, $this.com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$$_scalaTypeCacheSize()));
      $this.com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospectorModule$_setter_$overrideMap_$eq((Map).MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
