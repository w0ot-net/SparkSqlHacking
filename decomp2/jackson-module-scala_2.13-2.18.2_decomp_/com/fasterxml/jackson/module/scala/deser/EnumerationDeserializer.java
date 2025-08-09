package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import scala.Enumeration;
import scala.MatchError;
import scala.Tuple2;
import scala.None.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I3AAB\u0004\u0005)!Aq\u0005\u0001B\u0001B\u0003%\u0001\u0006C\u0003,\u0001\u0011\u0005A\u0006C\u00030\u0001\u0011\u0005\u0003\u0007C\u0003?\u0001\u0011%q\bC\u0003P\u0001\u0011%\u0001KA\fF]VlWM]1uS>tG)Z:fe&\fG.\u001b>fe*\u0011\u0001\"C\u0001\u0006I\u0016\u001cXM\u001d\u0006\u0003\u0015-\tQa]2bY\u0006T!\u0001D\u0007\u0002\r5|G-\u001e7f\u0015\tqq\"A\u0004kC\u000e\\7o\u001c8\u000b\u0005A\t\u0012!\u00034bgR,'\u000f_7m\u0015\u0005\u0011\u0012aA2p[\u000e\u00011c\u0001\u0001\u0016GA\u0019a#G\u000e\u000e\u0003]Q!\u0001G\u0007\u0002\u0011\u0011\fG/\u00192j]\u0012L!AG\f\u0003!)\u001bxN\u001c#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014\bC\u0001\u000f\"!\tir$D\u0001\u001f\u0015\u0005Q\u0011B\u0001\u0011\u001f\u0005-)e.^7fe\u0006$\u0018n\u001c8\n\u0005\tz\"!\u0002,bYV,\u0007C\u0001\u0013&\u001b\u00059\u0011B\u0001\u0014\b\u0005\u0005\u001auN\u001c;fqR,\u0018\r\\#ok6,'/\u0019;j_:$Um]3sS\u0006d\u0017N_3s\u0003\u001d!\b.\u001a+za\u0016\u0004\"AF\u0015\n\u0005):\"\u0001\u0003&bm\u0006$\u0016\u0010]3\u0002\rqJg.\u001b;?)\tic\u0006\u0005\u0002%\u0001!)qE\u0001a\u0001Q\u0005YA-Z:fe&\fG.\u001b>f)\rY\u0012'\u000f\u0005\u0006e\r\u0001\raM\u0001\u0003UB\u0004\"\u0001N\u001c\u000e\u0003UR!AN\u0007\u0002\t\r|'/Z\u0005\u0003qU\u0012!BS:p]B\u000b'o]3s\u0011\u0015Q4\u00011\u0001<\u0003\u0011\u0019G\u000f\u001f;\u0011\u0005Ya\u0014BA\u001f\u0018\u0005Y!Um]3sS\u0006d\u0017N_1uS>t7i\u001c8uKb$\u0018!\u00039beN,\u0007+Y5s)\t\u0001e\n\u0005\u0003\u001e\u0003\u000e\u001b\u0015B\u0001\"\u001f\u0005\u0019!V\u000f\u001d7feA\u0011Ai\u0013\b\u0003\u000b&\u0003\"A\u0012\u0010\u000e\u0003\u001dS!\u0001S\n\u0002\rq\u0012xn\u001c;?\u0013\tQe$\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u00196\u0013aa\u0015;sS:<'B\u0001&\u001f\u0011\u0015\u0011D\u00011\u00014\u0003%qW\r\u001f;U_.,g\u000e\u0006\u0002D#\")!'\u0002a\u0001g\u0001"
)
public class EnumerationDeserializer extends JsonDeserializer implements ContextualEnumerationDeserializer {
   private final JavaType theType;

   public JsonDeserializer createContextual(final DeserializationContext ctxt, final BeanProperty property) {
      return ContextualEnumerationDeserializer.createContextual$(this, ctxt, property);
   }

   public Enumeration.Value deserialize(final JsonParser jp, final DeserializationContext ctxt) {
      JsonToken var10000 = jp.getCurrentToken();
      JsonToken var5 = JsonToken.START_OBJECT;
      if (var10000 == null) {
         if (var5 != null) {
            return (Enumeration.Value)ctxt.handleUnexpectedToken(this.theType.getRawClass(), jp);
         }
      } else if (!var10000.equals(var5)) {
         return (Enumeration.Value)ctxt.handleUnexpectedToken(this.theType.getRawClass(), jp);
      }

      Tuple2 var7 = this.parsePair(jp);
      if (var7 == null) {
         throw new MatchError(var7);
      } else {
         String eclass = (String)var7._1();
         String eclassName = (String)var7._2();
         Tuple2 var6 = new Tuple2(eclass, eclassName);
         String eclass = (String)var6._1();
         String eclassName = (String)var6._2();
         String var12 = "enumClass";
         if (eclass == null) {
            if (var12 != null) {
               return (Enumeration.Value)ctxt.handleUnexpectedToken(this.theType.getRawClass(), jp);
            }
         } else if (!eclass.equals(var12)) {
            return (Enumeration.Value)ctxt.handleUnexpectedToken(this.theType.getRawClass(), jp);
         }

         Tuple2 var14 = this.parsePair(jp);
         if (var14 == null) {
            throw new MatchError(var14);
         } else {
            String value = (String)var14._1();
            String valueValue = (String)var14._2();
            Tuple2 var13 = new Tuple2(value, valueValue);
            String value = (String)var13._1();
            String valueValue = (String)var13._2();
            String var19 = "value";
            if (value == null) {
               if (var19 != null) {
                  return (Enumeration.Value)ctxt.handleUnexpectedToken(this.theType.getRawClass(), jp);
               }
            } else if (!value.equals(var19)) {
               return (Enumeration.Value)ctxt.handleUnexpectedToken(this.theType.getRawClass(), jp);
            }

            jp.nextToken();
            return ((Enumeration)Class.forName((new StringBuilder(1)).append(eclassName).append("$").toString()).getField("MODULE$").get(.MODULE$.orNull(scala..less.colon.less..MODULE$.refl()))).withName(valueValue);
         }
      }
   }

   private Tuple2 parsePair(final JsonParser jp) {
      return new Tuple2(this.nextToken(jp), this.nextToken(jp));
   }

   private String nextToken(final JsonParser jp) {
      jp.nextToken();
      return jp.getText();
   }

   public EnumerationDeserializer(final JavaType theType) {
      this.theType = theType;
      ContextualEnumerationDeserializer.$init$(this);
   }
}
