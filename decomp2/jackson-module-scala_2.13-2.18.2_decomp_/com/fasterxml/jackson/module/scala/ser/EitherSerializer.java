package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.ser.impl.UnknownSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.module.scala.util.Implicits$;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.Option.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015h\u0001\u0002\f\u0018\t\u0011B\u0001b\u0011\u0001\u0003\u0002\u0003\u0006I\u0001\u0012\u0005\t\u0011\u0002\u0011\t\u0011)A\u0005\t\"A\u0011\n\u0001B\u0001B\u0003%!\n\u0003\u0005R\u0001\t\u0005\t\u0015!\u0003S\u0011!i\u0006A!a\u0001\n\u0003q\u0006\u0002C3\u0001\u0005\u0003\u0007I\u0011\u00014\t\u00111\u0004!\u0011!Q!\n}CQ!\u001c\u0001\u0005\u00029Da!\u001e\u0001!\n#1\bbB@\u0001A\u0013E\u0011\u0011\u0001\u0005\b\u0003'\u0001A\u0011IA\u000b\u0011\u001d\tI\u0004\u0001C!\u0003wAq!!\u000f\u0001\t\u0003\t)\u0006C\u0004\u0002p\u0001!\t%!\u001d\t\u0011\u0005u\u0004\u0001)C\t\u0003\u007f:\u0011\"!)\u0018\u0003\u0003EI!a)\u0007\u0011Y9\u0012\u0011!E\u0005\u0003KCa!\\\t\u0005\u0002\u0005]\u0006\"CA]#E\u0005I\u0011AA^\u0011%\ty-EI\u0001\n\u0003\t\t\u000eC\u0005\u0002VF\t\t\u0011\"\u0003\u0002X\n\u0001R)\u001b;iKJ\u001cVM]5bY&TXM\u001d\u0006\u00031e\t1a]3s\u0015\tQ2$A\u0003tG\u0006d\u0017M\u0003\u0002\u001d;\u00051Qn\u001c3vY\u0016T!AH\u0010\u0002\u000f)\f7m[:p]*\u0011\u0001%I\u0001\nM\u0006\u001cH/\u001a:y[2T\u0011AI\u0001\u0004G>l7\u0001A\n\u0004\u0001\u0015z\u0004c\u0001\u0014-]5\tqE\u0003\u0002)S\u0005\u00191\u000f\u001e3\u000b\u0005aQ#BA\u0016\u001e\u0003!!\u0017\r^1cS:$\u0017BA\u0017(\u00055\u0019F\u000fZ*fe&\fG.\u001b>feB!q\u0006O\u001e<\u001d\t\u0001TG\u0004\u00022i5\t!G\u0003\u00024G\u00051AH]8pizJ\u0011AG\u0005\u0003m]\nq\u0001]1dW\u0006<WMC\u0001\u001b\u0013\tI$H\u0001\u0004FSRDWM\u001d\u0006\u0003m]\u0002\"\u0001P\u001f\u000e\u0003]J!AP\u001c\u0003\r\u0005s\u0017PU3g!\t\u0001\u0015)D\u0001*\u0013\t\u0011\u0015F\u0001\u000bD_:$X\r\u001f;vC2\u001cVM]5bY&TXM]\u0001\u0005Y\u00164G\u000f\u0005\u0002F\r6\tq#\u0003\u0002H/\tiQ)\u001b;iKJ$U\r^1jYN\fQA]5hQR\f\u0001\u0002\u001d:pa\u0016\u0014H/\u001f\t\u0004y-k\u0015B\u0001'8\u0005\u0019y\u0005\u000f^5p]B\u0011ajT\u0007\u0002U%\u0011\u0001K\u000b\u0002\r\u0005\u0016\fg\u000e\u0015:pa\u0016\u0014H/_\u0001\u0011G>tG/\u001a8u\u0013:\u001cG.^:j_:\u00042\u0001P&T!\t!&L\u0004\u0002V16\taK\u0003\u0002X;\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005e3\u0016a\u0003&t_:Len\u00197vI\u0016L!a\u0017/\u0003\u000f%s7\r\\;eK*\u0011\u0011LV\u0001\u0013Ift\u0017-\\5d'\u0016\u0014\u0018.\u00197ju\u0016\u00148/F\u0001`!\t\u00017-D\u0001b\u0015\t\u0011\u0017&\u0001\u0003j[Bd\u0017B\u00013b\u0005U\u0001&o\u001c9feRL8+\u001a:jC2L'0\u001a:NCB\fa\u0003Z=oC6L7mU3sS\u0006d\u0017N_3sg~#S-\u001d\u000b\u0003O*\u0004\"\u0001\u00105\n\u0005%<$\u0001B+oSRDqa\u001b\u0004\u0002\u0002\u0003\u0007q,A\u0002yIE\n1\u0003Z=oC6L7mU3sS\u0006d\u0017N_3sg\u0002\na\u0001P5oSRtDCB8qcJ\u001cH\u000f\u0005\u0002F\u0001!)1\t\u0003a\u0001\t\")\u0001\n\u0003a\u0001\t\")\u0011\n\u0003a\u0001\u0015\"9\u0011\u000b\u0003I\u0001\u0002\u0004\u0011\u0006bB/\t!\u0003\u0005\raX\u0001\ro&$\bNU3t_24X\r\u001a\u000b\u0006_^L80 \u0005\u0006q&\u0001\rAS\u0001\u0005aJ|\u0007\u000fC\u0003{\u0013\u0001\u0007A)A\u0004oK^dUM\u001a;\t\u000bqL\u0001\u0019\u0001#\u0002\u00119,wOU5hQRDQA`\u0005A\u0002I\u000b1bY8oi\u0016tG/\u00138dY\u000692M]3bi\u0016\u001cuN\u001c;fqR,\u0018\r\u001c#fi\u0006LGn\u001d\u000b\b\t\u0006\r\u0011QBA\b\u0011\u001d\t)A\u0003a\u0001\u0003\u000f\tA\u0001\u001d:pmB\u0019a*!\u0003\n\u0007\u0005-!F\u0001\nTKJL\u0017\r\\5{KJ\u0004&o\u001c<jI\u0016\u0014\b\"\u0002=\u000b\u0001\u0004i\u0005BBA\t\u0015\u0001\u0007A)A\u0004eKR\f\u0017\u000e\\:\u0002!\r\u0014X-\u0019;f\u0007>tG/\u001a=uk\u0006dGCBA\f\u0003k\t9\u0004\r\u0003\u0002\u001a\u0005\r\u0002#\u0002(\u0002\u001c\u0005}\u0011bAA\u000fU\tq!j]8o'\u0016\u0014\u0018.\u00197ju\u0016\u0014\b\u0003BA\u0011\u0003Ga\u0001\u0001B\u0006\u0002&-\t\t\u0011!A\u0003\u0002\u0005\u001d\"aA0%cE!\u0011\u0011FA\u0018!\ra\u00141F\u0005\u0004\u0003[9$a\u0002(pi\"Lgn\u001a\t\u0004y\u0005E\u0012bAA\u001ao\t\u0019\u0011I\\=\t\u000f\u0005\u00151\u00021\u0001\u0002\b!)\u0001p\u0003a\u0001\u001b\u0006I1/\u001a:jC2L'0\u001a\u000b\bO\u0006u\u0012\u0011IA)\u0011\u0019\ty\u0004\u0004a\u0001]\u0005)a/\u00197vK\"9\u00111\t\u0007A\u0002\u0005\u0015\u0013\u0001\u00026hK:\u0004B!a\u0012\u0002N5\u0011\u0011\u0011\n\u0006\u0004\u0003\u0017j\u0012\u0001B2pe\u0016LA!a\u0014\u0002J\ti!j]8o\u000f\u0016tWM]1u_JDq!a\u0015\r\u0001\u0004\t9!\u0001\u0005qe>4\u0018\u000eZ3s)%9\u0017qKA-\u00037\ni\u0006\u0003\u0004\u0002@5\u0001\rA\f\u0005\b\u0003\u0007j\u0001\u0019AA#\u0011\u001d\t\u0019&\u0004a\u0001\u0003\u000fAq!a\u0018\u000e\u0001\u0004\t\t'A\u0002wiN\u0004B\u0001P&\u0002dA!\u0011QMA6\u001b\t\t9GC\u0002\u0002j)\n\u0001B[:p]RL\b/Z\u0005\u0005\u0003[\n9G\u0001\bUsB,7+\u001a:jC2L'0\u001a:\u0002#M,'/[1mSj,w+\u001b;i)f\u0004X\rF\u0005h\u0003g\n)(a\u001e\u0002z!1\u0011q\b\bA\u00029Bq!a\u0011\u000f\u0001\u0004\t)\u0005C\u0004\u0002T9\u0001\r!a\u0002\t\u000f\u0005md\u00021\u0001\u0002d\u00059A/\u001f9f'\u0016\u0014\u0018\u0001\u00064j]\u0012\u001c\u0015m\u00195fIN+'/[1mSj,'\u000f\u0006\u0004\u0002\u0002\u0006\r\u0015Q\u0011\t\u0005\u001d\u0006m1\bC\u0004\u0002\u0006=\u0001\r!a\u0002\t\u000f\u0005\u001du\u00021\u0001\u0002\n\u0006\u0019A/\u001f91\t\u0005-\u0015Q\u0014\t\u0007\u0003\u001b\u000b)*a'\u000f\t\u0005=\u0015\u0011\u0013\t\u0003c]J1!a%8\u0003\u0019\u0001&/\u001a3fM&!\u0011qSAM\u0005\u0015\u0019E.Y:t\u0015\r\t\u0019j\u000e\t\u0005\u0003C\ti\n\u0002\u0007\u0002 \u0006\u0015\u0015\u0011!A\u0001\u0006\u0003\t9CA\u0002`II\n\u0001#R5uQ\u0016\u00148+\u001a:jC2L'0\u001a:\u0011\u0005\u0015\u000b2\u0003B\t<\u0003O\u0003B!!+\u000246\u0011\u00111\u0016\u0006\u0005\u0003[\u000by+\u0001\u0002j_*\u0011\u0011\u0011W\u0001\u0005U\u00064\u0018-\u0003\u0003\u00026\u0006-&\u0001D*fe&\fG.\u001b>bE2,GCAAR\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%iU\u0011\u0011Q\u0018\u0016\u0004%\u0006}6FAAa!\u0011\t\u0019-a3\u000e\u0005\u0005\u0015'\u0002BAd\u0003\u0013\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005];\u0014\u0002BAg\u0003\u000b\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%kU\u0011\u00111\u001b\u0016\u0004?\u0006}\u0016\u0001D<sSR,'+\u001a9mC\u000e,GCAAm!\u0011\tY.!9\u000e\u0005\u0005u'\u0002BAp\u0003_\u000bA\u0001\\1oO&!\u00111]Ao\u0005\u0019y%M[3di\u0002"
)
public class EitherSerializer extends StdSerializer implements ContextualSerializer {
   private final EitherDetails left;
   private final EitherDetails right;
   private final Option property;
   private final Option contentInclusion;
   private PropertySerializerMap dynamicSerializers;

   public static PropertySerializerMap $lessinit$greater$default$5() {
      return EitherSerializer$.MODULE$.$lessinit$greater$default$5();
   }

   public static Option $lessinit$greater$default$4() {
      return EitherSerializer$.MODULE$.$lessinit$greater$default$4();
   }

   public PropertySerializerMap dynamicSerializers() {
      return this.dynamicSerializers;
   }

   public void dynamicSerializers_$eq(final PropertySerializerMap x$1) {
      this.dynamicSerializers = x$1;
   }

   public EitherSerializer withResolved(final Option prop, final EitherDetails newLeft, final EitherDetails newRight, final Option contentIncl) {
      Option var5 = this.property;
      if (prop == null) {
         if (var5 != null) {
            return new EitherSerializer(newLeft, newRight, prop, contentIncl, this.dynamicSerializers());
         }
      } else if (!prop.equals(var5)) {
         return new EitherSerializer(newLeft, newRight, prop, contentIncl, this.dynamicSerializers());
      }

      EitherDetails var10000 = this.left;
      if (var10000 == null) {
         if (newLeft != null) {
            return new EitherSerializer(newLeft, newRight, prop, contentIncl, this.dynamicSerializers());
         }
      } else if (!var10000.equals(newLeft)) {
         return new EitherSerializer(newLeft, newRight, prop, contentIncl, this.dynamicSerializers());
      }

      var10000 = this.right;
      if (var10000 == null) {
         if (newRight != null) {
            return new EitherSerializer(newLeft, newRight, prop, contentIncl, this.dynamicSerializers());
         }
      } else if (!var10000.equals(newRight)) {
         return new EitherSerializer(newLeft, newRight, prop, contentIncl, this.dynamicSerializers());
      }

      Option var8 = this.contentInclusion;
      if (contentIncl == null) {
         if (var8 == null) {
            return this;
         }
      } else if (contentIncl.equals(var8)) {
         return this;
      }

      return new EitherSerializer(newLeft, newRight, prop, contentIncl, this.dynamicSerializers());
   }

   public EitherDetails createContextualDetails(final SerializerProvider prov, final BeanProperty prop, final EitherDetails details) {
      Option vts = Implicits$.MODULE$.mkOptionW(details.valueTypeSerializer()).optMap((x$3x) -> x$3x.forProperty(prop));
      Option ser = .MODULE$.apply(prop).flatMap((propx) -> .MODULE$.apply(propx.getMember()).flatMap((member) -> .MODULE$.apply(prov.getAnnotationIntrospector().findContentSerializer(member)).map((serDef) -> prov.serializerInstance(member, serDef))));
      ser = ser.orElse(() -> details.valueSerializer()).map((x$4) -> prov.handlePrimaryContextualization(x$4, prop));
      ser = .MODULE$.apply(this.findContextualConvertingSerializer(prov, prop, (JsonSerializer)ser.orNull(scala..less.colon.less..MODULE$.refl())));
      Object var10000;
      if (scala.None..MODULE$.equals(ser)) {
         var10000 = details.typ().isDefined() && OptionSerializer$.MODULE$.hasContentTypeAnnotation(prov, prop) ? .MODULE$.apply(prov.findValueSerializer((JavaType)details.typ().get(), prop)).filterNot((x$5) -> BoxesRunTime.boxToBoolean($anonfun$createContextualDetails$7(x$5))) : scala.None..MODULE$;
      } else {
         if (!(ser instanceof Some)) {
            throw new MatchError(ser);
         }

         Some var8 = (Some)ser;
         JsonSerializer s = (JsonSerializer)var8.value();
         var10000 = .MODULE$.apply(prov.handlePrimaryContextualization(s, prop));
      }

      Option x$2 = var10000;
      if (((Option)x$2).isEmpty() && OptionSerializer$.MODULE$.useStatic(prov, .MODULE$.apply(prop), details.typ())) {
         x$2 = .MODULE$.apply(OptionSerializer$.MODULE$.findSerializer(prov, (JavaType)details.typ().orNull(scala..less.colon.less..MODULE$.refl()), .MODULE$.apply(prop)));
      }

      Option x$3 = details.copy$default$1();
      return details.copy(x$3, vts, (Option)x$2);
   }

   public JsonSerializer createContextual(final SerializerProvider prov, final BeanProperty prop) {
      Option propOpt = .MODULE$.apply(prop);
      EitherDetails newLeft = this.createContextualDetails(prov, prop, this.left);
      EitherDetails newRight = this.createContextualDetails(prov, prop, this.right);
      Object var10000;
      if (scala.None..MODULE$.equals(propOpt)) {
         var10000 = this.contentInclusion;
      } else {
         label28: {
            if (!(propOpt instanceof Some)) {
               throw new MatchError(propOpt);
            }

            JsonInclude.Include incl;
            label23: {
               Some var9 = (Some)propOpt;
               BeanProperty p = (BeanProperty)var9.value();
               JsonInclude.Value pinc = p.findPropertyInclusion(prov.getConfig(), Option.class);
               incl = pinc.getContentInclusion();
               JsonInclude.Include var13 = Include.USE_DEFAULTS;
               if (incl == null) {
                  if (var13 != null) {
                     break label23;
                  }
               } else if (!incl.equals(var13)) {
                  break label23;
               }

               var10000 = this.contentInclusion;
               break label28;
            }

            var10000 = new Some(incl);
         }
      }

      Option newIncl = (Option)var10000;
      return this.withResolved(propOpt, newLeft, newRight, newIncl);
   }

   public void serialize(final Either value, final JsonGenerator jgen, final SerializerProvider provider) {
      this.serialize(value, jgen, provider, scala.None..MODULE$);
   }

   public void serialize(final Either value, final JsonGenerator jgen, final SerializerProvider provider, final Option vts) {
      Tuple3 var10000;
      if (value instanceof Left) {
         Left var11 = (Left)value;
         Object c = var11.value();
         var10000 = new Tuple3("l", c, this.left);
      } else {
         if (!(value instanceof Right)) {
            throw new MatchError(value);
         }

         Right var13 = (Right)value;
         Object c = var13.value();
         var10000 = new Tuple3("r", c, this.right);
      }

      Tuple3 var9 = var10000;
      if (var9 != null) {
         String field = (String)var9._1();
         Object content = var9._2();
         EitherDetails details = (EitherDetails)var9._3();
         Tuple3 var8 = new Tuple3(field, content, details);
         String field = (String)var8._1();
         Object content = var8._2();
         EitherDetails details = (EitherDetails)var8._3();
         jgen.writeStartObject();
         jgen.writeFieldName(field);
         if (content == null) {
            provider.defaultSerializeNull(jgen);
         } else {
            JsonSerializer ser = (JsonSerializer)details.valueSerializer().getOrElse(() -> this.findCachedSerializer(provider, content.getClass()));
            Option var22 = vts.orElse(() -> details.valueTypeSerializer());
            if (var22 instanceof Some) {
               Some var23 = (Some)var22;
               TypeSerializer vts = (TypeSerializer)var23.value();
               ser.serializeWithType(content, jgen, provider, vts);
               BoxedUnit var25 = BoxedUnit.UNIT;
            } else {
               if (!scala.None..MODULE$.equals(var22)) {
                  throw new MatchError(var22);
               }

               ser.serialize(content, jgen, provider);
               BoxedUnit var26 = BoxedUnit.UNIT;
            }
         }

         jgen.writeEndObject();
      } else {
         throw new MatchError(var9);
      }
   }

   public void serializeWithType(final Either value, final JsonGenerator jgen, final SerializerProvider provider, final TypeSerializer typeSer) {
      if (value == null) {
         provider.defaultSerializeNull(jgen);
      } else {
         typeSer.writeTypePrefix(jgen, typeSer.typeId(value, JsonToken.START_OBJECT));
         this.serialize(value, jgen, provider, new Some(typeSer));
         typeSer.writeTypeSuffix(jgen, typeSer.typeId(value, JsonToken.END_OBJECT));
      }
   }

   public JsonSerializer findCachedSerializer(final SerializerProvider prov, final Class typ) {
      JsonSerializer ser = this.dynamicSerializers().serializerFor(typ);
      if (ser == null) {
         ser = OptionSerializer$.MODULE$.findSerializer(prov, typ, this.property);
         this.dynamicSerializers_$eq(this.dynamicSerializers().newWith(typ, ser));
      }

      return ser;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$createContextualDetails$7(final JsonSerializer x$5) {
      return x$5 instanceof UnknownSerializer;
   }

   public EitherSerializer(final EitherDetails left, final EitherDetails right, final Option property, final Option contentInclusion, final PropertySerializerMap dynamicSerializers) {
      this.left = left;
      this.right = right;
      this.property = property;
      this.contentInclusion = contentInclusion;
      this.dynamicSerializers = dynamicSerializers;
      super(Either.class);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
