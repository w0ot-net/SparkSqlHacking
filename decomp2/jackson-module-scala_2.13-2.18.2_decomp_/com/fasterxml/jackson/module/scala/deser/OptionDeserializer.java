package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.std.ReferenceTypeDeserializer;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.runtime.ObjectRef;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-e\u0001\u0002\f\u0018\t\u0011B\u0001B\u000f\u0001\u0003\u0002\u0003\u0006Ia\u000f\u0005\t\u007f\u0001\u0011\t\u0011)A\u0005\u0001\"Aq\t\u0001B\u0001B\u0003%\u0001\n\u0003\u0005M\u0001\t\u0005\t\u0015!\u0003N\u0011\u0015\t\u0006\u0001\"\u0001S\u0011\u0015I\u0006\u0001\"\u0011[\u0011\u0015Y\u0006\u0001\"\u0011]\u0011\u0015Y\u0006\u0001\"\u0011^\u0011\u0019\u0019\u0007\u0001)C\u0005I\")\u0011\u0010\u0001C!u\"1q\u0010\u0001C!\u0003\u0003Aq!!\u0006\u0001\t\u0003\n9\u0002C\u0004\u0002$\u0001!\t%!\n\t\r\r\u0004A\u0011IA\u0016\u0011\u001d\tY\u0004\u0001C!\u0003{Aq!!\u0012\u0001\t\u0003\n9eB\u0005\u0002L]\t\t\u0011#\u0003\u0002N\u0019AacFA\u0001\u0012\u0013\ty\u0005\u0003\u0004R%\u0011\u0005\u0011\u0011\r\u0005\n\u0003G\u0012\u0012\u0013!C\u0001\u0003KB\u0011\"a\u001f\u0013\u0003\u0003%I!! \u0003%=\u0003H/[8o\t\u0016\u001cXM]5bY&TXM\u001d\u0006\u00031e\tQ\u0001Z3tKJT!AG\u000e\u0002\u000bM\u001c\u0017\r\\1\u000b\u0005qi\u0012AB7pIVdWM\u0003\u0002\u001f?\u00059!.Y2lg>t'B\u0001\u0011\"\u0003%1\u0017m\u001d;feblGNC\u0001#\u0003\r\u0019w.\\\u0002\u0001'\r\u0001QE\u000e\t\u0004M1rS\"A\u0014\u000b\u0005!J\u0013aA:uI*\u0011\u0001D\u000b\u0006\u0003Wu\t\u0001\u0002Z1uC\nLg\u000eZ\u0005\u0003[\u001d\u0012\u0011DU3gKJ,gnY3UsB,G)Z:fe&\fG.\u001b>feB\u0019q&M\u001a\u000e\u0003AR\u0011AG\u0005\u0003eA\u0012aa\u00149uS>t\u0007CA\u00185\u0013\t)\u0004G\u0001\u0004B]f\u0014VM\u001a\t\u0003oaj\u0011!K\u0005\u0003s%\u0012acQ8oi\u0016DH/^1m\t\u0016\u001cXM]5bY&TXM]\u0001\tMVdG\u000eV=qKB\u0011A(P\u0007\u0002U%\u0011aH\u000b\u0002\t\u0015\u00064\u0018\rV=qK\u0006)b/\u00197vKRK\b/\u001a#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014\bcA\u00182\u0003B\u0011!)R\u0007\u0002\u0007*\u0011AIK\u0001\tUN|g\u000e^=qK&\u0011ai\u0011\u0002\u0011)f\u0004X\rR3tKJL\u0017\r\\5{KJ\f\u0011C^1mk\u0016$Um]3sS\u0006d\u0017N_3s!\ry\u0013'\u0013\t\u0004y)\u001b\u0014BA&+\u0005AQ5o\u001c8EKN,'/[1mSj,'/\u0001\u0007cK\u0006t\u0007K]8qKJ$\u0018\u0010E\u00020c9\u0003\"\u0001P(\n\u0005AS#\u0001\u0004\"fC:\u0004&o\u001c9feRL\u0018A\u0002\u001fj]&$h\bF\u0003T+Z;\u0006\f\u0005\u0002U\u00015\tq\u0003C\u0003;\u000b\u0001\u00071\bC\u0003@\u000b\u0001\u0007\u0001\tC\u0003H\u000b\u0001\u0007\u0001\nC\u0004M\u000bA\u0005\t\u0019A'\u0002\u0019\u001d,GOV1mk\u0016$\u0016\u0010]3\u0015\u0003m\nAbZ3u\u001dVdGNV1mk\u0016$\u0012A\f\u000b\u0003]yCQa\u0018\u0005A\u0002\u0001\fAa\u0019;yiB\u0011A(Y\u0005\u0003E*\u0012a\u0003R3tKJL\u0017\r\\5{CRLwN\\\"p]R,\u0007\u0010^\u0001\ro&$\bNU3t_24X\r\u001a\u000b\u0006'\u00164\u0007\u000e\u001f\u0005\u0006u%\u0001\ra\u000f\u0005\u0006O&\u0001\r\u0001Q\u0001\nif\u0004X\rR3tKJDQ![\u0005A\u0002)\f!B^1mk\u0016$Um]3s!\ry\u0013g\u001b\u0019\u0003Y>\u00042\u0001\u0010&n!\tqw\u000e\u0004\u0001\u0005\u0013AD\u0017\u0011!A\u0001\u0006\u0003\t(aA0%cE\u0011!/\u001e\t\u0003_ML!\u0001\u001e\u0019\u0003\u000f9{G\u000f[5oOB\u0011qF^\u0005\u0003oB\u00121!\u00118z\u0011\u0015a\u0015\u00021\u0001N\u0003A\u0019'/Z1uK\u000e{g\u000e^3yiV\fG\u000eF\u0002|yv\u00042\u0001\u0010&/\u0011\u0015y&\u00021\u0001a\u0011\u0015q(\u00021\u0001O\u0003!\u0001(o\u001c9feRL\u0018a\u00033fg\u0016\u0014\u0018.\u00197ju\u0016$RALA\u0002\u0003'Aq!!\u0002\f\u0001\u0004\t9!A\u0001q!\u0011\tI!a\u0004\u000e\u0005\u0005-!bAA\u0007;\u0005!1m\u001c:f\u0013\u0011\t\t\"a\u0003\u0003\u0015)\u001bxN\u001c)beN,'\u000fC\u0003`\u0017\u0001\u0007\u0001-A\neKN,'/[1mSj,w+\u001b;i)f\u0004X\rF\u0004/\u00033\ti\"a\b\t\u000f\u0005mA\u00021\u0001\u0002\b\u0005\u0011!\u000e\u001d\u0005\u0006?2\u0001\r\u0001\u0019\u0005\u0007\u0003Ca\u0001\u0019A!\u0002!QL\b/\u001a#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014\u0018A\u0004:fM\u0016\u0014XM\\2f-\u0006dW/\u001a\u000b\u0004]\u0005\u001d\u0002BBA\u0015\u001b\u0001\u0007Q/\u0001\u0005d_:$XM\u001c;t)\u0015)\u0013QFA\u0018\u0011\u00159g\u00021\u0001B\u0011\u0019Ig\u00021\u0001\u00022A\"\u00111GA\u001c!\u0011a$*!\u000e\u0011\u00079\f9\u0004B\u0006\u0002:\u0005=\u0012\u0011!A\u0001\u0006\u0003\t(aA0%e\u0005yQ\u000f\u001d3bi\u0016\u0014VMZ3sK:\u001cW\rF\u0003/\u0003\u007f\t\u0019\u0005\u0003\u0004\u0002B=\u0001\rAL\u0001\ne\u00164WM]3oG\u0016Da!!\u000b\u0010\u0001\u0004)\u0018!D4fiJ+g-\u001a:f]\u000e,G\rF\u00024\u0003\u0013Ba!!\u0011\u0011\u0001\u0004q\u0013AE(qi&|g\u000eR3tKJL\u0017\r\\5{KJ\u0004\"\u0001\u0016\n\u0014\tI\u0019\u0014\u0011\u000b\t\u0005\u0003'\ni&\u0004\u0002\u0002V)!\u0011qKA-\u0003\tIwN\u0003\u0002\u0002\\\u0005!!.\u0019<b\u0013\u0011\ty&!\u0016\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\u00055\u0013a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$C'\u0006\u0002\u0002h)\u001aQ*!\u001b,\u0005\u0005-\u0004\u0003BA7\u0003oj!!a\u001c\u000b\t\u0005E\u00141O\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!\u001e1\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003s\nyGA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a \u0011\t\u0005\u0005\u0015qQ\u0007\u0003\u0003\u0007SA!!\"\u0002Z\u0005!A.\u00198h\u0013\u0011\tI)a!\u0003\r=\u0013'.Z2u\u0001"
)
public class OptionDeserializer extends ReferenceTypeDeserializer {
   private final JavaType fullType;
   private final Option valueTypeDeserializer;
   private final Option valueDeserializer;
   private final Option beanProperty;

   public static Option $lessinit$greater$default$4() {
      return OptionDeserializer$.MODULE$.$lessinit$greater$default$4();
   }

   public JavaType getValueType() {
      return this.fullType;
   }

   public Option getNullValue() {
      return .MODULE$;
   }

   public Option getNullValue(final DeserializationContext ctxt) {
      return .MODULE$;
   }

   private OptionDeserializer withResolved(final JavaType fullType, final Option typeDeser, final Option valueDeser, final Option beanProperty) {
      JavaType var5 = this.fullType;
      if (fullType == null) {
         if (var5 != null) {
            return new OptionDeserializer(fullType, typeDeser, valueDeser, beanProperty);
         }
      } else if (!fullType.equals(var5)) {
         return new OptionDeserializer(fullType, typeDeser, valueDeser, beanProperty);
      }

      Option var6 = this.valueTypeDeserializer;
      if (typeDeser == null) {
         if (var6 != null) {
            return new OptionDeserializer(fullType, typeDeser, valueDeser, beanProperty);
         }
      } else if (!typeDeser.equals(var6)) {
         return new OptionDeserializer(fullType, typeDeser, valueDeser, beanProperty);
      }

      Option var7 = this.valueDeserializer;
      if (valueDeser == null) {
         if (var7 != null) {
            return new OptionDeserializer(fullType, typeDeser, valueDeser, beanProperty);
         }
      } else if (!valueDeser.equals(var7)) {
         return new OptionDeserializer(fullType, typeDeser, valueDeser, beanProperty);
      }

      Option var8 = this.beanProperty;
      if (beanProperty == null) {
         if (var8 == null) {
            return this;
         }
      } else if (beanProperty.equals(var8)) {
         return this;
      }

      return new OptionDeserializer(fullType, typeDeser, valueDeser, beanProperty);
   }

   public JsonDeserializer createContextual(final DeserializationContext ctxt, final BeanProperty property) {
      Option typeDeser = this.valueTypeDeserializer.map((x$1) -> x$1.forProperty(property));
      Option deser = this.valueDeserializer;
      ObjectRef typ = ObjectRef.create(this.fullType);
      if (deser.isEmpty()) {
         if (property != null) {
            AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
            AnnotatedMember member = property.getMember();
            if (intr != null && member != null) {
               typ.elem = intr.refineDeserializationType(ctxt.getConfig(), member, (JavaType)typ.elem);
            }

            deser = scala.Option..MODULE$.apply(ctxt.findContextualValueDeserializer(refdType$1(typ), property));
         }
      } else {
         deser = scala.Option..MODULE$.apply(ctxt.handleSecondaryContextualization((JsonDeserializer)deser.get(), property, refdType$1(typ)));
      }

      return this.withResolved((JavaType)typ.elem, typeDeser, deser, scala.Option..MODULE$.apply(property));
   }

   public Option deserialize(final JsonParser p, final DeserializationContext ctxt) {
      JsonDeserializer deser = (JsonDeserializer)this.valueDeserializer.getOrElse(() -> ctxt.findContextualValueDeserializer(this.fullType.getContentType(), (BeanProperty)this.beanProperty.orNull(scala..less.colon.less..MODULE$.refl())));
      Option var6 = this.valueTypeDeserializer;
      Object var10000;
      if (var6 instanceof Some) {
         Some var7 = (Some)var6;
         TypeDeserializer vtd = (TypeDeserializer)var7.value();
         var10000 = deser.deserializeWithType(p, ctxt, vtd);
      } else {
         label23: {
            label22: {
               if (.MODULE$.equals(var6)) {
                  JsonToken var10 = p.currentToken();
                  JsonToken var9 = JsonToken.VALUE_NULL;
                  if (var10 == null) {
                     if (var9 == null) {
                        break label22;
                     }
                  } else if (var10.equals(var9)) {
                     break label22;
                  }
               }

               var10000 = deser.deserialize(p, ctxt);
               break label23;
            }

            var10000 = null;
         }
      }

      Object refd = var10000;
      return scala.Option..MODULE$.apply(refd);
   }

   public Option deserializeWithType(final JsonParser jp, final DeserializationContext ctxt, final TypeDeserializer typeDeserializer) {
      JsonToken t = jp.getCurrentToken();
      JsonToken var7 = JsonToken.VALUE_NULL;
      if (t == null) {
         if (var7 == null) {
            return this.getNullValue(ctxt);
         }
      } else if (t.equals(var7)) {
         return this.getNullValue(ctxt);
      }

      Option var8 = this.valueTypeDeserializer;
      if (var8 instanceof Some) {
         Some var9 = (Some)var8;
         TypeDeserializer vtd = (TypeDeserializer)var9.value();
         return scala.Option..MODULE$.apply(vtd.deserializeTypedFromAny(jp, ctxt));
      } else {
         Object var11 = typeDeserializer.deserializeTypedFromAny(jp, ctxt);
         if (var11 instanceof Some) {
            Some var12 = (Some)var11;
            Object any = var12.value();
            return this.referenceValue(any);
         } else {
            return this.referenceValue(var11);
         }
      }
   }

   public Option referenceValue(final Object contents) {
      Option var3 = scala.Option..MODULE$.apply(contents);
      if (var3 instanceof Some) {
         Some var4 = (Some)var3;
         Object anyRef = var4.value();
         if (anyRef instanceof Object) {
            return new Some(anyRef);
         }
      }

      return .MODULE$;
   }

   public ReferenceTypeDeserializer withResolved(final TypeDeserializer typeDeser, final JsonDeserializer valueDeser) {
      return new OptionDeserializer(this.fullType, scala.Option..MODULE$.apply(typeDeser), scala.Option..MODULE$.apply(valueDeser).map((x$2) -> x$2), this.beanProperty);
   }

   public Option updateReference(final Option reference, final Object contents) {
      return this.referenceValue(contents);
   }

   public Object getReferenced(final Option reference) {
      return reference.orNull(scala..less.colon.less..MODULE$.refl());
   }

   private static final JavaType refdType$1(final ObjectRef typ$1) {
      return (JavaType)scala.Option..MODULE$.apply(((JavaType)typ$1.elem).getContentType()).getOrElse(() -> TypeFactory.unknownType());
   }

   public OptionDeserializer(final JavaType fullType, final Option valueTypeDeserializer, final Option valueDeserializer, final Option beanProperty) {
      super(fullType, (ValueInstantiator).MODULE$.orNull(scala..less.colon.less..MODULE$.refl()), (TypeDeserializer)valueTypeDeserializer.orNull(scala..less.colon.less..MODULE$.refl()), (JsonDeserializer)valueDeserializer.orNull(scala..less.colon.less..MODULE$.refl()));
      this.fullType = fullType;
      this.valueTypeDeserializer = valueTypeDeserializer;
      this.valueDeserializer = valueDeserializer;
      this.beanProperty = beanProperty;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
