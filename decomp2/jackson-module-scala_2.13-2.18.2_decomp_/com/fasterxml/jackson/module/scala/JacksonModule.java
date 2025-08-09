package com.fasterxml.jackson.module.scala;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.type.TypeModifier;
import java.io.Closeable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.StringOps.;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ms!\u0002\u000b\u0016\u0011\u0003\u0001c!\u0002\u0012\u0016\u0011\u0003\u0019\u0003\"B\u0015\u0002\t\u0003Q\u0003bB\u0016\u0002\u0005\u0004%I\u0001\f\u0005\b\u0003\u0007\n\u0001\u0015!\u0003.\u0011%\t)%\u0001b\u0001\n\u0013\t9\u0005\u0003\u0005\u0002N\u0005\u0001\u000b\u0011BA%\u0011)\ty%\u0001EC\u0002\u0013\u0005\u0011\u0011\u000b\u0005\nc\u0006A)\u0019!C\u0001\u000332qAI\u000b\u0011\u0002\u0007\u0005a\u0007C\u0003>\u0013\u0011\u0005a\bC\u0004C\u0013\t\u0007I\u0011B\"\t\u000b\u001dLA\u0011\t5\t\u000bELA\u0011\t:\t\u000beLA\u0011\u0001>\t\u000buLA\u0011\u0003@\t\ruLA\u0011CA\u0003\u0011\u0019i\u0018\u0002\"\u0005\u0002\u0016!1Q0\u0003C\t\u0003KAa!`\u0005\u0005\u0012\u0005]\u0012!\u0004&bG.\u001cxN\\'pIVdWM\u0003\u0002\u0017/\u0005)1oY1mC*\u0011\u0001$G\u0001\u0007[>$W\u000f\\3\u000b\u0005iY\u0012a\u00026bG.\u001cxN\u001c\u0006\u00039u\t\u0011BZ1ti\u0016\u0014\b0\u001c7\u000b\u0003y\t1aY8n\u0007\u0001\u0001\"!I\u0001\u000e\u0003U\u0011QBS1dWN|g.T8ek2,7CA\u0001%!\t)s%D\u0001'\u0015\u00051\u0012B\u0001\u0015'\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012\u0001I\u0001\u0004G2\u001cX#A\u0017\u0011\u00079\u001aT'D\u00010\u0015\t\u0001\u0014'\u0001\u0003mC:<'\"\u0001\u001a\u0002\t)\fg/Y\u0005\u0003i=\u0012Qa\u00117bgN\u0004\"!I\u0005\u0014\u0005%9\u0004C\u0001\u001d<\u001b\u0005I$B\u0001\u001e\u001a\u0003!!\u0017\r^1cS:$\u0017B\u0001\u001f:\u0005\u0019iu\u000eZ;mK\u00061A%\u001b8ji\u0012\"\u0012a\u0010\t\u0003K\u0001K!!\u0011\u0014\u0003\tUs\u0017\u000e^\u0001\rS:LG/[1mSj,'o]\u000b\u0002\tB!QI\u0013'b\u001b\u00051%BA$I\u0003\u001diW\u000f^1cY\u0016T!!\u0013\u0014\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002L\r\n9!)^5mI\u0016\u0014\b\u0003B\u0013N\u001f~J!A\u0014\u0014\u0003\u0013\u0019+hn\u0019;j_:\f\u0004C\u0001)_\u001d\t\tFL\u0004\u0002S7:\u00111K\u0017\b\u0003)fs!!\u0016-\u000e\u0003YS!aV\u0010\u0002\rq\u0012xn\u001c;?\u0013\u0005q\u0012B\u0001\u000f\u001e\u0013\tQ2$\u0003\u0002;3%\u0011Q,O\u0001\u0007\u001b>$W\u000f\\3\n\u0005}\u0003'\u0001D*fiV\u00048i\u001c8uKb$(BA/:!\r\u0011W\rT\u0007\u0002G*\u0011A\rS\u0001\nS6lW\u000f^1cY\u0016L!AZ2\u0003\u0007M+\u0017/A\u0007hKRlu\u000eZ;mK:\u000bW.\u001a\u000b\u0002SB\u0011!N\u001c\b\u0003W2\u0004\"!\u0016\u0014\n\u000554\u0013A\u0002)sK\u0012,g-\u0003\u0002pa\n11\u000b\u001e:j]\u001eT!!\u001c\u0014\u0002\u000fY,'o]5p]R\t1\u000f\u0005\u0002uo6\tQO\u0003\u0002w3\u0005!1m\u001c:f\u0013\tAXOA\u0004WKJ\u001c\u0018n\u001c8\u0002\u0017M,G/\u001e9N_\u0012,H.\u001a\u000b\u0003\u007fmDQ\u0001 \bA\u0002=\u000bqaY8oi\u0016DH/\u0001\u0005%a2,8\u000fJ3r)\ry\u0018\u0011A\u0007\u0002\u0013!1\u00111A\bA\u00021\u000bA!\u001b8jiR\u0019q0a\u0002\t\u000f\u0005%\u0001\u00031\u0001\u0002\f\u0005\u00191/\u001a:\u0011\t\u00055\u0011\u0011C\u0007\u0003\u0003\u001fQ1!!\u0003:\u0013\u0011\t\u0019\"a\u0004\u0003\u0017M+'/[1mSj,'o\u001d\u000b\u0004\u007f\u0006]\u0001bBA\r#\u0001\u0007\u00111D\u0001\u0006I\u0016\u001cXM\u001d\t\u0005\u0003;\t\t#\u0004\u0002\u0002 )\u0019\u0011\u0011D\u001d\n\t\u0005\r\u0012q\u0004\u0002\u000e\t\u0016\u001cXM]5bY&TXM]:\u0015\u0007}\f9\u0003C\u0004\u0002*I\u0001\r!a\u000b\u0002\u000fQL\b/Z'pIB!\u0011QFA\u001a\u001b\t\tyCC\u0002\u00022e\nA\u0001^=qK&!\u0011QGA\u0018\u00051!\u0016\u0010]3N_\u0012Lg-[3s)\ry\u0018\u0011\b\u0005\b\u0003w\u0019\u0002\u0019AA\u001f\u0003)\u0011W-\u00198TKJlu\u000e\u001a\t\u0005\u0003\u001b\ty$\u0003\u0003\u0002B\u0005=!A\u0006\"fC:\u001cVM]5bY&TXM]'pI&4\u0017.\u001a:\u0002\t\rd7\u000fI\u0001\u0013EVLG\u000e\u001a)s_B\u001ch)\u001b7f]\u0006lW-\u0006\u0002\u0002JA\u0019a&a\u0013\n\u0005=|\u0013a\u00052vS2$\u0007K]8qg\u001aKG.\u001a8b[\u0016\u0004\u0013A\u00032vS2$\u0007K]8qgV\u0011\u00111\u000b\t\u0006\u000b\u0006U\u0013.[\u0005\u0004\u0003/2%aA'baV\t1\u000f"
)
public interface JacksonModule {
   static Map buildProps() {
      return JacksonModule$.MODULE$.buildProps();
   }

   void com$fasterxml$jackson$module$scala$JacksonModule$_setter_$com$fasterxml$jackson$module$scala$JacksonModule$$initializers_$eq(final Builder x$1);

   Builder com$fasterxml$jackson$module$scala$JacksonModule$$initializers();

   // $FF: synthetic method
   static String getModuleName$(final JacksonModule $this) {
      return $this.getModuleName();
   }

   default String getModuleName() {
      return "JacksonModule";
   }

   // $FF: synthetic method
   static Version version$(final JacksonModule $this) {
      return $this.version();
   }

   default Version version() {
      return JacksonModule$.MODULE$.version();
   }

   // $FF: synthetic method
   static void setupModule$(final JacksonModule $this, final Module.SetupContext context) {
      $this.setupModule(context);
   }

   default void setupModule(final Module.SetupContext context) {
      int MajorVersion = this.version().getMajorVersion();
      int MinorVersion = this.version().getMinorVersion();
      Version requiredVersion = new Version(MajorVersion, MinorVersion, 0, (String)null, "com.fasterxml.jackson.core", "jackson-databind");
      Version incompatibleVersion = new Version(MajorVersion, MinorVersion + 1, 0, (String)null, "com.fasterxml.jackson.core", "jackson-databind");
      Version var7 = context.getMapperVersion();
      if (var7 != null) {
         Some var8 = VersionExtractor$.MODULE$.unapply(var7);
         if (!var8.isEmpty()) {
            int var9 = ((Tuple2)var8.get())._1$mcI$sp();
            int var10 = ((Tuple2)var8.get())._2$mcI$sp();
            if (MajorVersion == var9 && MinorVersion == var10) {
               BoxedUnit var10000 = BoxedUnit.UNIT;
               ((IterableOnceOps)this.com$fasterxml$jackson$module$scala$JacksonModule$$initializers().result()).foreach((x$1) -> {
                  $anonfun$setupModule$1(context, x$1);
                  return BoxedUnit.UNIT;
               });
               return;
            }
         }
      }

      String databindVersionError = .MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Scala module %s requires Jackson Databind version >= %s and < %s - Found jackson-databind version %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.version(), requiredVersion, incompatibleVersion, var7}));
      throw new JsonMappingException((Closeable)null, databindVersionError);
   }

   // $FF: synthetic method
   static JacksonModule $plus$eq$(final JacksonModule $this, final Function1 init) {
      return $this.$plus$eq(init);
   }

   default JacksonModule $plus$eq(final Function1 init) {
      this.com$fasterxml$jackson$module$scala$JacksonModule$$initializers().$plus$eq(init);
      return this;
   }

   // $FF: synthetic method
   static JacksonModule $plus$eq$(final JacksonModule $this, final Serializers ser) {
      return $this.$plus$eq(ser);
   }

   default JacksonModule $plus$eq(final Serializers ser) {
      return this.$plus$eq((Function1)((x$2) -> {
         $anonfun$$plus$eq$1(ser, x$2);
         return BoxedUnit.UNIT;
      }));
   }

   // $FF: synthetic method
   static JacksonModule $plus$eq$(final JacksonModule $this, final Deserializers deser) {
      return $this.$plus$eq(deser);
   }

   default JacksonModule $plus$eq(final Deserializers deser) {
      return this.$plus$eq((Function1)((x$3) -> {
         $anonfun$$plus$eq$2(deser, x$3);
         return BoxedUnit.UNIT;
      }));
   }

   // $FF: synthetic method
   static JacksonModule $plus$eq$(final JacksonModule $this, final TypeModifier typeMod) {
      return $this.$plus$eq(typeMod);
   }

   default JacksonModule $plus$eq(final TypeModifier typeMod) {
      return this.$plus$eq((Function1)((x$4) -> {
         $anonfun$$plus$eq$3(typeMod, x$4);
         return BoxedUnit.UNIT;
      }));
   }

   // $FF: synthetic method
   static JacksonModule $plus$eq$(final JacksonModule $this, final BeanSerializerModifier beanSerMod) {
      return $this.$plus$eq(beanSerMod);
   }

   default JacksonModule $plus$eq(final BeanSerializerModifier beanSerMod) {
      return this.$plus$eq((Function1)((x$5) -> {
         $anonfun$$plus$eq$4(beanSerMod, x$5);
         return BoxedUnit.UNIT;
      }));
   }

   // $FF: synthetic method
   static void $anonfun$setupModule$1(final Module.SetupContext context$1, final Function1 x$1) {
      x$1.apply(context$1);
   }

   // $FF: synthetic method
   static void $anonfun$$plus$eq$1(final Serializers ser$1, final Module.SetupContext x$2) {
      x$2.addSerializers(ser$1);
   }

   // $FF: synthetic method
   static void $anonfun$$plus$eq$2(final Deserializers deser$1, final Module.SetupContext x$3) {
      x$3.addDeserializers(deser$1);
   }

   // $FF: synthetic method
   static void $anonfun$$plus$eq$3(final TypeModifier typeMod$1, final Module.SetupContext x$4) {
      x$4.addTypeModifier(typeMod$1);
   }

   // $FF: synthetic method
   static void $anonfun$$plus$eq$4(final BeanSerializerModifier beanSerMod$1, final Module.SetupContext x$5) {
      x$5.addBeanSerializerModifier(beanSerMod$1);
   }

   static void $init$(final JacksonModule $this) {
      $this.com$fasterxml$jackson$module$scala$JacksonModule$_setter_$com$fasterxml$jackson$module$scala$JacksonModule$$initializers_$eq(scala.package..MODULE$.Seq().newBuilder());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
