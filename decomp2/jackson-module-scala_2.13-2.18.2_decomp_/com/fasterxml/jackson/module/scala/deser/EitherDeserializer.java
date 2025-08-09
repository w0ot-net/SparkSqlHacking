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
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

@ScalaSignature(
   bytes = "\u0006\u0005\t-c\u0001B\u0015+\t]B\u0001B\u0016\u0001\u0003\u0002\u0003\u0006Ia\u0016\u0005\t7\u0002\u0011\t\u0011)A\u00059\"Aq\f\u0001B\u0001B\u0003%\u0001\rC\u0005\u0002f\u0002\u0011\t\u0011)A\u0005A\"1!\u0010\u0001C\u0001\u0003ODq!a=\u0001\t\u0003\n)\u0010C\u0004\u0003\u000e\u0001!IAa\u0004\t\u000f\tE\u0002\u0001\"\u0003\u00034!9!\u0011\b\u0001\u0005B\tm\u0002b\u0002B!\u0001\u0011\u0005#1I\u0004\u0006]*BIa\u001c\u0004\u0006S)BI!\u001d\u0005\u0006u2!\ta\u001f\u0004\u0005y2\u0001U\u0010\u0003\u0006\u0002\b9\u0011)\u001a!C\u0001\u0003\u0013A!\"a\u0006\u000f\u0005#\u0005\u000b\u0011BA\u0006\u0011)\tIB\u0004BK\u0002\u0013\u0005\u00111\u0004\u0005\u000b\u0003Wq!\u0011#Q\u0001\n\u0005u\u0001B\u0002>\u000f\t\u0003\ti\u0003C\u0005\u000289\t\t\u0011\"\u0001\u0002:!I\u0011q\b\b\u0012\u0002\u0013\u0005\u0011\u0011\t\u0005\n\u0003/r\u0011\u0013!C\u0001\u00033B\u0011\"!\u0018\u000f\u0003\u0003%\t%a\u0018\t\u0013\u00055d\"!A\u0005\u0002\u0005=\u0004\"CA<\u001d\u0005\u0005I\u0011AA=\u0011%\t)IDA\u0001\n\u0003\n9\tC\u0005\u0002\u0016:\t\t\u0011\"\u0001\u0002\u0018\"I\u0011\u0011\u0015\b\u0002\u0002\u0013\u0005\u00131\u0015\u0005\n\u0003Os\u0011\u0011!C!\u0003SC\u0011\"a+\u000f\u0003\u0003%\t%!,\t\u0013\u0005=f\"!A\u0005B\u0005EvaBA[\u0019!\u0005\u0011q\u0017\u0004\u0007y2A\t!!/\t\ri\fC\u0011AA^\u0011%\ti,\tb\u0001\n\u0003\ty\f\u0003\u0005\u0002B\u0006\u0002\u000b\u0011BA\u0018\u0011%\t\u0019-IA\u0001\n\u0003\u000b)\rC\u0005\u0002L\u0006\n\t\u0011\"!\u0002N\"I\u00111\\\u0011\u0002\u0002\u0013%\u0011Q\u001c\u0005\n\u00037d\u0011\u0011!C\u0005\u0003;\u0014!#R5uQ\u0016\u0014H)Z:fe&\fG.\u001b>fe*\u00111\u0006L\u0001\u0006I\u0016\u001cXM\u001d\u0006\u0003[9\nQa]2bY\u0006T!a\f\u0019\u0002\r5|G-\u001e7f\u0015\t\t$'A\u0004kC\u000e\\7o\u001c8\u000b\u0005M\"\u0014!\u00034bgR,'\u000f_7m\u0015\u0005)\u0014aA2p[\u000e\u00011c\u0001\u00019%B\u0019\u0011hP!\u000e\u0003iR!a\u000f\u001f\u0002\u0007M$HM\u0003\u0002,{)\u0011a\bM\u0001\tI\u0006$\u0018MY5oI&\u0011\u0001I\u000f\u0002\u0010'R$G)Z:fe&\fG.\u001b>feB!!i\u0013(O\u001d\t\u0019\u0005J\u0004\u0002E\u000f6\tQI\u0003\u0002Gm\u00051AH]8pizJ\u0011!L\u0005\u0003\u0013*\u000bq\u0001]1dW\u0006<WMC\u0001.\u0013\taUJ\u0001\u0004FSRDWM\u001d\u0006\u0003\u0013*\u0003\"a\u0014)\u000e\u0003)K!!\u0015&\u0003\r\u0005s\u0017PU3g!\t\u0019F+D\u0001=\u0013\t)FH\u0001\fD_:$X\r\u001f;vC2$Um]3sS\u0006d\u0017N_3s\u0003!Q\u0017M^1UsB,\u0007C\u0001-Z\u001b\u0005i\u0014B\u0001.>\u0005!Q\u0015M^1UsB,\u0017AB2p]\u001aLw\r\u0005\u0002Y;&\u0011a,\u0010\u0002\u0016\t\u0016\u001cXM]5bY&T\u0018\r^5p]\u000e{gNZ5h\u0003YaWM\u001a;EKN,'/[1mSj,'oQ8oM&<\u0007CA1\u000f\u001d\t\u00117B\u0004\u0002d[:\u0011A\r\u001c\b\u0003K.t!A\u001a6\u000f\u0005\u001dLgB\u0001#i\u0013\u0005)\u0014BA\u001a5\u0013\t\t$'\u0003\u00020a%\u0011QFL\u0005\u0003W1\n!#R5uQ\u0016\u0014H)Z:fe&\fG.\u001b>feB\u0011\u0001\u000fD\u0007\u0002UM\u0019AB\u0014:\u0011\u0005MDX\"\u0001;\u000b\u0005U4\u0018AA5p\u0015\u00059\u0018\u0001\u00026bm\u0006L!!\u001f;\u0003\u0019M+'/[1mSj\f'\r\\3\u0002\rqJg.\u001b;?)\u0005y'!G#mK6,g\u000e\u001e#fg\u0016\u0014\u0018.\u00197ju\u0016\u00148i\u001c8gS\u001e\u001cRA\u0004(\u007f\u0003\u0007\u0001\"aT@\n\u0007\u0005\u0005!JA\u0004Qe>$Wo\u0019;\u0011\u0007\t\u000b)!\u0003\u0002z\u001b\u0006aA-Z:fe&\fG.\u001b>feV\u0011\u00111\u0002\t\u0006\u001f\u00065\u0011\u0011C\u0005\u0004\u0003\u001fQ%AB(qi&|g\u000e\u0005\u0003Y\u0003'q\u0015bAA\u000b{\t\u0001\"j]8o\t\u0016\u001cXM]5bY&TXM]\u0001\u000eI\u0016\u001cXM]5bY&TXM\u001d\u0011\u0002!QL\b/\u001a#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014XCAA\u000f!\u0015y\u0015QBA\u0010!\u0011\t\t#a\n\u000e\u0005\u0005\r\"bAA\u0013{\u0005A!n]8oif\u0004X-\u0003\u0003\u0002*\u0005\r\"\u0001\u0005+za\u0016$Um]3sS\u0006d\u0017N_3s\u0003E!\u0018\u0010]3EKN,'/[1mSj,'\u000f\t\u000b\u0007\u0003_\t\u0019$!\u000e\u0011\u0007\u0005Eb\"D\u0001\r\u0011\u001d\t9a\u0005a\u0001\u0003\u0017Aq!!\u0007\u0014\u0001\u0004\ti\"\u0001\u0003d_BLHCBA\u0018\u0003w\ti\u0004C\u0005\u0002\bQ\u0001\n\u00111\u0001\u0002\f!I\u0011\u0011\u0004\u000b\u0011\u0002\u0003\u0007\u0011QD\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\t\u0019E\u000b\u0003\u0002\f\u0005\u00153FAA$!\u0011\tI%a\u0015\u000e\u0005\u0005-#\u0002BA'\u0003\u001f\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005E#*\u0001\u0006b]:|G/\u0019;j_:LA!!\u0016\u0002L\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u00111\f\u0016\u0005\u0003;\t)%A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003C\u0002B!a\u0019\u0002j5\u0011\u0011Q\r\u0006\u0004\u0003O2\u0018\u0001\u00027b]\u001eLA!a\u001b\u0002f\t11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!!\u001d\u0011\u0007=\u000b\u0019(C\u0002\u0002v)\u00131!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a\u001f\u0002\u0002B\u0019q*! \n\u0007\u0005}$JA\u0002B]fD\u0011\"a!\u001a\u0003\u0003\u0005\r!!\u001d\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\tI\t\u0005\u0004\u0002\f\u0006E\u00151P\u0007\u0003\u0003\u001bS1!a$K\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003'\u000biI\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BAM\u0003?\u00032aTAN\u0013\r\tiJ\u0013\u0002\b\u0005>|G.Z1o\u0011%\t\u0019iGA\u0001\u0002\u0004\tY(\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA1\u0003KC\u0011\"a!\u001d\u0003\u0003\u0005\r!!\u001d\u0002\u0011!\f7\u000f[\"pI\u0016$\"!!\u001d\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!\u0019\u0002\r\u0015\fX/\u00197t)\u0011\tI*a-\t\u0013\u0005\ru$!AA\u0002\u0005m\u0014!G#mK6,g\u000e\u001e#fg\u0016\u0014\u0018.\u00197ju\u0016\u00148i\u001c8gS\u001e\u00042!!\r\"'\r\tcJ\u001d\u000b\u0003\u0003o\u000bQ!Z7qif,\"!a\f\u0002\r\u0015l\u0007\u000f^=!\u0003\u0015\t\u0007\u000f\u001d7z)\u0019\ty#a2\u0002J\"9\u0011qA\u0013A\u0002\u0005-\u0001bBA\rK\u0001\u0007\u0011QD\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\ty-a6\u0011\u000b=\u000bi!!5\u0011\u000f=\u000b\u0019.a\u0003\u0002\u001e%\u0019\u0011Q\u001b&\u0003\rQ+\b\u000f\\33\u0011%\tINJA\u0001\u0002\u0004\ty#A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a8\u0011\t\u0005\r\u0014\u0011]\u0005\u0005\u0003G\f)G\u0001\u0004PE*,7\r^\u0001\u0018e&<\u0007\u000e\u001e#fg\u0016\u0014\u0018.\u00197ju\u0016\u00148i\u001c8gS\u001e$\"\"!;\u0002l\u00065\u0018q^Ay!\t\u0001\b\u0001C\u0003W\u000b\u0001\u0007q\u000bC\u0003\\\u000b\u0001\u0007A\fC\u0003`\u000b\u0001\u0007\u0001\r\u0003\u0004\u0002f\u0016\u0001\r\u0001Y\u0001\u0011GJ,\u0017\r^3D_:$X\r\u001f;vC2$b!a>\u0002z\n\r\u0001\u0003\u0002-\u0002\u0014\u0005Cq!a?\u0007\u0001\u0004\ti0\u0001\u0003dib$\bc\u0001-\u0002\u0000&\u0019!\u0011A\u001f\u0003-\u0011+7/\u001a:jC2L'0\u0019;j_:\u001cuN\u001c;fqRDqA!\u0002\u0007\u0001\u0004\u00119!\u0001\u0005qe>\u0004XM\u001d;z!\rA&\u0011B\u0005\u0004\u0005\u0017i$\u0001\u0004\"fC:\u0004&o\u001c9feRL\u0018\u0001\u00053fg\u0016\u0014\u0018.\u00197ju\u00164\u0016\r\\;f))\u0011\tBa\u0005\u0003$\t\u0015\"q\u0006\t\u0005\u0003G\n\t\u000fC\u0004\u0003\u0016\u001d\u0001\rAa\u0006\u0002\tQL\b/\u001a\t\u0005\u00053\u0011y\"\u0004\u0002\u0003\u001c)\u0019!Q\u0004\u0019\u0002\t\r|'/Z\u0005\u0005\u0005C\u0011YBA\u0005Kg>tGk\\6f]\")1l\u0002a\u0001A\"9!qE\u0004A\u0002\t%\u0012A\u00016q!\u0011\u0011IBa\u000b\n\t\t5\"1\u0004\u0002\u000b\u0015N|g\u000eU1sg\u0016\u0014\bbBA~\u000f\u0001\u0007\u0011Q`\u0001\u0012I\u0016\u001cXM]5bY&TX-R5uQ\u0016\u0014H#B!\u00036\t]\u0002b\u0002B\u0014\u0011\u0001\u0007!\u0011\u0006\u0005\b\u0003wD\u0001\u0019AA\u007f\u0003-!Wm]3sS\u0006d\u0017N_3\u0015\u000b\u0005\u0013iDa\u0010\t\u000f\t\u001d\u0012\u00021\u0001\u0003*!9\u00111`\u0005A\u0002\u0005u\u0018a\u00053fg\u0016\u0014\u0018.\u00197ju\u0016<\u0016\u000e\u001e5UsB,GcB!\u0003F\t\u001d#\u0011\n\u0005\b\u0005OQ\u0001\u0019\u0001B\u0015\u0011\u001d\tYP\u0003a\u0001\u0003{Dq!!\u0007\u000b\u0001\u0004\ty\u0002"
)
public class EitherDeserializer extends StdDeserializer implements ContextualDeserializer {
   private final JavaType javaType;
   private final DeserializationConfig config;
   private final ElementDeserializerConfig leftDeserializerConfig;
   private final ElementDeserializerConfig rightDeserializerConfig;

   public JsonDeserializer createContextual(final DeserializationContext ctxt, final BeanProperty property) {
      int var3 = this.javaType.containedTypeCount();
      switch (var3) {
         case 1:
            label21: {
               String var10000 = this.javaType.getBindings().getBoundName(0);
               String var6 = "A";
               if (var10000 == null) {
                  if (var6 == null) {
                     break label21;
                  }
               } else if (var10000.equals(var6)) {
                  break label21;
               }

               ElementDeserializerConfig rightDeserializerConfig = this.deserializerConfigFor$1(0, property, ctxt);
               return new EitherDeserializer(this.javaType, this.config, EitherDeserializer.ElementDeserializerConfig$.MODULE$.empty(), rightDeserializerConfig);
            }

            ElementDeserializerConfig leftDeserializerConfig = this.deserializerConfigFor$1(0, property, ctxt);
            return new EitherDeserializer(this.javaType, this.config, leftDeserializerConfig, EitherDeserializer.ElementDeserializerConfig$.MODULE$.empty());
         case 2:
            ElementDeserializerConfig leftDeserializerConfig = this.deserializerConfigFor$1(0, property, ctxt);
            ElementDeserializerConfig rightDeserializerConfig = this.deserializerConfigFor$1(1, property, ctxt);
            return new EitherDeserializer(this.javaType, this.config, leftDeserializerConfig, rightDeserializerConfig);
         default:
            return this;
      }
   }

   private Object deserializeValue(final JsonToken type, final ElementDeserializerConfig config, final JsonParser jp, final DeserializationContext ctxt) {
      Tuple2 var6 = new Tuple2(config, type);
      if (var6 != null) {
         ElementDeserializerConfig var7 = (ElementDeserializerConfig)var6._1();
         JsonToken var8 = (JsonToken)var6._2();
         if (var7 != null) {
            Option var9 = var7.deserializer();
            if (var9 instanceof Some) {
               Some var10 = (Some)var9;
               JsonDeserializer ed = (JsonDeserializer)var10.value();
               if (JsonToken.VALUE_NULL.equals(var8) && ed instanceof OptionDeserializer) {
                  return .MODULE$;
               }
            }
         }
      }

      if (var6 != null) {
         JsonToken var12 = (JsonToken)var6._2();
         if (JsonToken.VALUE_NULL.equals(var12)) {
            return .MODULE$.orNull(scala..less.colon.less..MODULE$.refl());
         }
      }

      if (var6 != null) {
         ElementDeserializerConfig var13 = (ElementDeserializerConfig)var6._1();
         if (var13 != null) {
            Option var14 = var13.deserializer();
            Option var15 = var13.typeDeserializer();
            if (var14 instanceof Some) {
               Some var16 = (Some)var14;
               JsonDeserializer ed = (JsonDeserializer)var16.value();
               if (var15 instanceof Some) {
                  Some var18 = (Some)var15;
                  TypeDeserializer td = (TypeDeserializer)var18.value();
                  return ed.deserializeWithType(jp, ctxt, td);
               }
            }
         }
      }

      if (var6 != null) {
         ElementDeserializerConfig var20 = (ElementDeserializerConfig)var6._1();
         if (var20 != null) {
            Option var21 = var20.deserializer();
            if (var21 instanceof Some) {
               Some var22 = (Some)var21;
               JsonDeserializer ed = (JsonDeserializer)var22.value();
               return ed.deserialize(jp, ctxt);
            }
         }
      }

      if (var6 != null) {
         return ctxt.handleUnexpectedToken(this.javaType.getRawClass(), jp);
      } else {
         throw new MatchError(var6);
      }
   }

   private Either deserializeEither(final JsonParser jp, final DeserializationContext ctxt) {
      JsonToken var6 = jp.currentToken();
      if (JsonToken.START_OBJECT.equals(var6)) {
         Object var15;
         label57: {
            String key = jp.nextFieldName();
            JsonToken type = jp.nextToken();
            switch (key == null ? 0 : key.hashCode()) {
               case 108:
                  if ("l".equals(key)) {
                     var15 = new Left(this.deserializeValue(type, this.leftDeserializerConfig, jp, ctxt));
                     break label57;
                  }
                  break;
               case 114:
                  if ("r".equals(key)) {
                     var15 = new Right(this.deserializeValue(type, this.rightDeserializerConfig, jp, ctxt));
                     break label57;
                  }
                  break;
               case 3317767:
                  if ("left".equals(key)) {
                     var15 = new Left(this.deserializeValue(type, this.leftDeserializerConfig, jp, ctxt));
                     break label57;
                  }
                  break;
               case 108511772:
                  if ("right".equals(key)) {
                     var15 = new Right(this.deserializeValue(type, this.rightDeserializerConfig, jp, ctxt));
                     break label57;
                  }
            }

            var15 = (Either)ctxt.handleUnexpectedToken(this.javaType, jp);
         }

         Either result = (Either)var15;
         jp.nextToken();
         return result;
      } else if (JsonToken.START_ARRAY.equals(var6)) {
         Object var10000;
         label69: {
            String key = jp.nextTextValue();
            JsonToken type = jp.nextToken();
            switch (key == null ? 0 : key.hashCode()) {
               case 108:
                  if ("l".equals(key)) {
                     var10000 = new Left(this.deserializeValue(type, this.leftDeserializerConfig, jp, ctxt));
                     break label69;
                  }
                  break;
               case 114:
                  if ("r".equals(key)) {
                     var10000 = new Right(this.deserializeValue(type, this.rightDeserializerConfig, jp, ctxt));
                     break label69;
                  }
                  break;
               case 3317767:
                  if ("left".equals(key)) {
                     var10000 = new Left(this.deserializeValue(type, this.leftDeserializerConfig, jp, ctxt));
                     break label69;
                  }
                  break;
               case 108511772:
                  if ("right".equals(key)) {
                     var10000 = new Right(this.deserializeValue(type, this.rightDeserializerConfig, jp, ctxt));
                     break label69;
                  }
            }

            var10000 = (Either)ctxt.handleUnexpectedToken(this.javaType, jp);
         }

         Either result = (Either)var10000;
         jp.nextToken();
         return result;
      } else {
         return (Either)ctxt.handleUnexpectedToken(this.javaType, jp);
      }
   }

   public Either deserialize(final JsonParser jp, final DeserializationContext ctxt) {
      return this.deserializeEither(jp, ctxt);
   }

   public Either deserializeWithType(final JsonParser jp, final DeserializationContext ctxt, final TypeDeserializer typeDeserializer) {
      return this.deserializeEither(jp, ctxt);
   }

   private final ElementDeserializerConfig deserializerConfigFor$1(final int param, final BeanProperty property, final DeserializationContext ctxt$1) {
      JavaType containedType = this.javaType.containedType(param);
      Option paramDeserializer = scala.Option..MODULE$.apply(ctxt$1.findContextualValueDeserializer(containedType, property));
      Option typeDeserializer = scala.Option..MODULE$.apply(property).flatMap((p) -> scala.Option..MODULE$.apply(BeanDeserializerFactory.instance.findPropertyTypeDeserializer(ctxt$1.getConfig(), containedType, p.getMember())));
      return new ElementDeserializerConfig(paramDeserializer, typeDeserializer);
   }

   public EitherDeserializer(final JavaType javaType, final DeserializationConfig config, final ElementDeserializerConfig leftDeserializerConfig, final ElementDeserializerConfig rightDeserializerConfig) {
      super(Either.class);
      this.javaType = javaType;
      this.config = config;
      this.leftDeserializerConfig = leftDeserializerConfig;
      this.rightDeserializerConfig = rightDeserializerConfig;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class ElementDeserializerConfig implements Product, Serializable {
      private final Option deserializer;
      private final Option typeDeserializer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Option deserializer() {
         return this.deserializer;
      }

      public Option typeDeserializer() {
         return this.typeDeserializer;
      }

      public ElementDeserializerConfig copy(final Option deserializer, final Option typeDeserializer) {
         return new ElementDeserializerConfig(deserializer, typeDeserializer);
      }

      public Option copy$default$1() {
         return this.deserializer();
      }

      public Option copy$default$2() {
         return this.typeDeserializer();
      }

      public String productPrefix() {
         return "ElementDeserializerConfig";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.deserializer();
            case 1:
               return this.typeDeserializer();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof ElementDeserializerConfig;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "deserializer";
            case 1:
               return "typeDeserializer";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var8;
         if (this != x$1) {
            label55: {
               if (x$1 instanceof ElementDeserializerConfig) {
                  label48: {
                     ElementDeserializerConfig var4 = (ElementDeserializerConfig)x$1;
                     Option var10000 = this.deserializer();
                     Option var5 = var4.deserializer();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label48;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label48;
                     }

                     var10000 = this.typeDeserializer();
                     Option var6 = var4.typeDeserializer();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label48;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label48;
                     }

                     if (var4.canEqual(this)) {
                        break label55;
                     }
                  }
               }

               var8 = false;
               return var8;
            }
         }

         var8 = true;
         return var8;
      }

      public ElementDeserializerConfig(final Option deserializer, final Option typeDeserializer) {
         this.deserializer = deserializer;
         this.typeDeserializer = typeDeserializer;
         Product.$init$(this);
      }
   }

   public static class ElementDeserializerConfig$ implements Serializable {
      public static final ElementDeserializerConfig$ MODULE$ = new ElementDeserializerConfig$();
      private static final ElementDeserializerConfig empty;

      static {
         empty = new ElementDeserializerConfig(.MODULE$, .MODULE$);
      }

      public ElementDeserializerConfig empty() {
         return empty;
      }

      public ElementDeserializerConfig apply(final Option deserializer, final Option typeDeserializer) {
         return new ElementDeserializerConfig(deserializer, typeDeserializer);
      }

      public Option unapply(final ElementDeserializerConfig x$0) {
         return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.deserializer(), x$0.typeDeserializer())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ElementDeserializerConfig$.class);
      }
   }
}
