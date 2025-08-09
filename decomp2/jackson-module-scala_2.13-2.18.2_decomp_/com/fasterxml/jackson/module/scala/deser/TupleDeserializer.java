package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Constructor;
import scala.MatchError;
import scala.Product;
import scala.collection.IterableOps;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001de\u0001\u0002\u000b\u0016\t\tB\u0001\"\u000e\u0001\u0003\u0002\u0003\u0006IA\u000e\u0005\tu\u0001\u0011\t\u0011)A\u0005w!Aa\b\u0001B\u0001B\u0003%q\b\u0003\u0005W\u0001\t\u0005\t\u0015!\u0003X\u0011\u0015q\u0006\u0001\"\u0001`\u0011\u001d1\u0007A1A\u0005\u0002\u001dDa\u0001\u001d\u0001!\u0002\u0013A\u0007b\u0002<\u0001\u0005\u0004%\ta\u001e\u0005\b\u0003\u001b\u0001\u0001\u0015!\u0003y\u0011%\ty\u0001\u0001b\u0001\n\u0003\t\t\u0002\u0003\u0005\u0002\u001c\u0001\u0001\u000b\u0011BA\n\u0011\u001d\ti\u0002\u0001C\u0001\u0003?Aq!!\u000e\u0001\t\u0003\t9dB\u0005\u0002LU\t\t\u0011#\u0003\u0002N\u0019AA#FA\u0001\u0012\u0013\ty\u0005\u0003\u0004_\u001f\u0011\u0005\u00111\r\u0005\n\u0003Kz\u0011\u0013!C\u0001\u0003OB\u0011\"! \u0010#\u0003%\t!a \t\u0013\u0005\ru\"!A\u0005\n\u0005\u0015%!\u0005+va2,G)Z:fe&\fG.\u001b>fe*\u0011acF\u0001\u0006I\u0016\u001cXM\u001d\u0006\u00031e\tQa]2bY\u0006T!AG\u000e\u0002\r5|G-\u001e7f\u0015\taR$A\u0004kC\u000e\\7o\u001c8\u000b\u0005yy\u0012!\u00034bgR,'\u000f_7m\u0015\u0005\u0001\u0013aA2p[\u000e\u00011c\u0001\u0001$cA\u0019AE\u000b\u0017\u000e\u0003\u0015R!AJ\u0014\u0002\u0007M$HM\u0003\u0002\u0017Q)\u0011\u0011fG\u0001\tI\u0006$\u0018MY5oI&\u00111&\n\u0002\u0010'R$G)Z:fe&\fG.\u001b>feB\u0011QfL\u0007\u0002])\t\u0001$\u0003\u00021]\t9\u0001K]8ek\u000e$\bC\u0001\u001a4\u001b\u00059\u0013B\u0001\u001b(\u0005Y\u0019uN\u001c;fqR,\u0018\r\u001c#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014\u0018\u0001\u00036bm\u0006$\u0016\u0010]3\u0011\u0005]BT\"\u0001\u0015\n\u0005eB#\u0001\u0003&bm\u0006$\u0016\u0010]3\u0002\r\r|gNZ5h!\t9D(\u0003\u0002>Q\t)B)Z:fe&\fG.\u001b>bi&|gnQ8oM&<\u0017A\u0005<bYV,G)Z:fe&\fG.\u001b>feN\u00042\u0001\u0011%L\u001d\t\teI\u0004\u0002C\u000b6\t1I\u0003\u0002EC\u00051AH]8pizJ\u0011\u0001G\u0005\u0003\u000f:\nq\u0001]1dW\u0006<W-\u0003\u0002J\u0015\n\u00191+Z9\u000b\u0005\u001ds\u0003cA\u001cM\u001d&\u0011Q\n\u000b\u0002\u0011\u0015N|g\u000eR3tKJL\u0017\r\\5{KJ\u0004\"a\u0014+\u000e\u0003AS!!\u0015*\u0002\t1\fgn\u001a\u0006\u0002'\u0006!!.\u0019<b\u0013\t)\u0006K\u0001\u0004PE*,7\r^\u0001\u0012if\u0004X\rR3tKJL\u0017\r\\5{KJ\u001c\bc\u0001!I1B\u0011\u0011\fX\u0007\u00025*\u00111\fK\u0001\tUN|g\u000e^=qK&\u0011QL\u0017\u0002\u0011)f\u0004X\rR3tKJL\u0017\r\\5{KJ\fa\u0001P5oSRtD#\u00021cG\u0012,\u0007CA1\u0001\u001b\u0005)\u0002\"B\u001b\u0006\u0001\u00041\u0004\"\u0002\u001e\u0006\u0001\u0004Y\u0004b\u0002 \u0006!\u0003\u0005\ra\u0010\u0005\b-\u0016\u0001\n\u00111\u0001X\u0003\r\u0019Gn]\u000b\u0002QB\u0012\u0011N\u001c\t\u0004\u001f*d\u0017BA6Q\u0005\u0015\u0019E.Y:t!\tig\u000e\u0004\u0001\u0005\u0013=<\u0011\u0011!A\u0001\u0006\u0003\t(AA 1\u0003\u0011\u0019Gn\u001d\u0011\u0012\u0005I,\bCA\u0017t\u0013\t!hFA\u0004O_RD\u0017N\\4\u0011\u0005=#\u0016!B2u_J\u001cX#\u0001=\u0011\u00075J80\u0003\u0002{]\t)\u0011I\u001d:bsB\u001aA0a\u0002\u0011\u000bu\f\t!!\u0002\u000e\u0003yT!a )\u0002\u000fI,g\r\\3di&\u0019\u00111\u0001@\u0003\u0017\r{gn\u001d;sk\u000e$xN\u001d\t\u0004[\u0006\u001dA!C8\u0001\u0003\u0003\u0005\tQ!\u0001r\u0013\r\tYA[\u0001\u0010O\u0016$8i\u001c8tiJ,8\r^8sg\u000611\r^8sg\u0002\nAa\u0019;peV\u0011\u00111\u0003\u0019\u0005\u0003+\tI\u0002E\u0003~\u0003\u0003\t9\u0002E\u0002n\u00033!\u0011b\\\u0006\u0002\u0002\u0003\u0005)\u0011A9\u0002\u000b\r$xN\u001d\u0011\u0002!\r\u0014X-\u0019;f\u0007>tG/\u001a=uk\u0006dG#\u00021\u0002\"\u0005-\u0002bBA\u0012\u0019\u0001\u0007\u0011QE\u0001\u0005GRDH\u000fE\u00028\u0003OI1!!\u000b)\u0005Y!Um]3sS\u0006d\u0017N_1uS>t7i\u001c8uKb$\bbBA\u0017\u0019\u0001\u0007\u0011qF\u0001\taJ|\u0007/\u001a:usB\u0019q'!\r\n\u0007\u0005M\u0002F\u0001\u0007CK\u0006t\u0007K]8qKJ$\u00180A\u0006eKN,'/[1mSj,G#\u0002\u0017\u0002:\u0005%\u0003bBA\u001e\u001b\u0001\u0007\u0011QH\u0001\u0003UB\u0004B!a\u0010\u0002F5\u0011\u0011\u0011\t\u0006\u0004\u0003\u0007Z\u0012\u0001B2pe\u0016LA!a\u0012\u0002B\tQ!j]8o!\u0006\u00148/\u001a:\t\u000f\u0005\rR\u00021\u0001\u0002&\u0005\tB+\u001e9mK\u0012+7/\u001a:jC2L'0\u001a:\u0011\u0005\u0005|1#B\b\u0002R\u0005]\u0003cA\u0017\u0002T%\u0019\u0011Q\u000b\u0018\u0003\r\u0005s\u0017PU3g!\u0011\tI&a\u0018\u000e\u0005\u0005m#bAA/%\u0006\u0011\u0011n\\\u0005\u0005\u0003C\nYF\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0006\u0002\u0002N\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIM*\"!!\u001b+\u0007}\nYg\u000b\u0002\u0002nA!\u0011qNA=\u001b\t\t\tH\u0003\u0003\u0002t\u0005U\u0014!C;oG\",7m[3e\u0015\r\t9HL\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA>\u0003c\u0012\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%iU\u0011\u0011\u0011\u0011\u0016\u0004/\u0006-\u0014\u0001D<sSR,'+\u001a9mC\u000e,G#\u0001("
)
public class TupleDeserializer extends StdDeserializer implements ContextualDeserializer {
   private final JavaType javaType;
   private final DeserializationConfig config;
   private final Seq valueDeserializers;
   private final Seq typeDeserializers;
   private final Class cls;
   private final Constructor[] ctors;
   private final Constructor ctor;

   public static Seq $lessinit$greater$default$4() {
      return TupleDeserializer$.MODULE$.$lessinit$greater$default$4();
   }

   public static Seq $lessinit$greater$default$3() {
      return TupleDeserializer$.MODULE$.$lessinit$greater$default$3();
   }

   public Class cls() {
      return this.cls;
   }

   public Constructor[] ctors() {
      return this.ctors;
   }

   public Constructor ctor() {
      return this.ctor;
   }

   public TupleDeserializer createContextual(final DeserializationContext ctxt, final BeanProperty property) {
      IndexedSeq paramTypes = .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.javaType.containedTypeCount()).map((i) -> $anonfun$createContextual$1(this, BoxesRunTime.unboxToInt(i)));
      IndexedSeq paramDesers = (IndexedSeq)paramTypes.map((x$1) -> ctxt.findContextualValueDeserializer(x$1, property));
      BeanDeserializerFactory factory = BeanDeserializerFactory.instance;
      Seq typeDesers = property != null ? (Seq)paramTypes.map((x$2) -> factory.findPropertyTypeDeserializer(ctxt.getConfig(), x$2, property.getMember())) : (Seq)paramTypes.map((x$3) -> factory.findTypeDeserializer(this.config, x$3));
      return new TupleDeserializer(this.javaType, this.config, paramDesers, typeDesers);
   }

   public Product deserialize(final JsonParser jp, final DeserializationContext ctxt) {
      if (!jp.isExpectedStartArrayToken()) {
         return (Product)ctxt.handleUnexpectedToken(this.javaType.getRawClass(), jp);
      } else {
         label18: {
            Seq params = (Seq)((IterableOps)this.valueDeserializers.zip(this.typeDeserializers)).map((x0$1) -> {
               if (x0$1 != null) {
                  JsonDeserializer deser = (JsonDeserializer)x0$1._1();
                  TypeDeserializer typeDeser = (TypeDeserializer)x0$1._2();
                  jp.nextToken();
                  return typeDeser == null ? deser.deserialize(jp, ctxt) : deser.deserializeWithType(jp, ctxt, typeDeser);
               } else {
                  throw new MatchError(x0$1);
               }
            });
            JsonToken t = jp.nextToken();
            JsonToken var5 = JsonToken.END_ARRAY;
            if (t == null) {
               if (var5 != null) {
                  break label18;
               }
            } else if (!t.equals(var5)) {
               break label18;
            }

            return (Product)this.ctor().newInstance(params.toArray(scala.reflect.ClassTag..MODULE$.Object()));
         }

         ctxt.wrongTokenException(jp, ctxt.getContextualType(), JsonToken.END_ARRAY, "expected closing END_ARRAY after deserialized value");
         return null;
      }
   }

   // $FF: synthetic method
   public static final JavaType $anonfun$createContextual$1(final TupleDeserializer $this, final int i) {
      return $this.javaType.containedType(i);
   }

   public TupleDeserializer(final JavaType javaType, final DeserializationConfig config, final Seq valueDeserializers, final Seq typeDeserializers) {
      super(Product.class);
      this.javaType = javaType;
      this.config = config;
      this.valueDeserializers = valueDeserializers;
      this.typeDeserializers = typeDeserializers;
      this.cls = javaType.getRawClass();
      this.ctors = this.cls().getConstructors();
      if (this.ctors().length != 1) {
         throw new IllegalStateException("Tuple should have exactly one constructor");
      } else {
         this.ctor = (Constructor)scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.ctors()));
      }
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
