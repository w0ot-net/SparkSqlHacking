package org.apache.spark.deploy.rest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.scala.DefaultScalaModule.;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.util.Utils$;
import org.json4s.JObject;
import org.json4s.JString;
import org.json4s.JValue;
import scala.Function1;
import scala.Option;
import scala.Tuple2;
import scala.collection.immutable.List;

public final class SubmitRestProtocolMessage$ {
   public static final SubmitRestProtocolMessage$ MODULE$ = new SubmitRestProtocolMessage$();
   private static final String packagePrefix;
   private static final ObjectMapper org$apache$spark$deploy$rest$SubmitRestProtocolMessage$$mapper;

   static {
      packagePrefix = MODULE$.getClass().getPackage().getName();
      org$apache$spark$deploy$rest$SubmitRestProtocolMessage$$mapper = (new ObjectMapper()).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).enable(SerializationFeature.INDENT_OUTPUT).registerModule(.MODULE$);
   }

   private String packagePrefix() {
      return packagePrefix;
   }

   public ObjectMapper org$apache$spark$deploy$rest$SubmitRestProtocolMessage$$mapper() {
      return org$apache$spark$deploy$rest$SubmitRestProtocolMessage$$mapper;
   }

   public String parseAction(final String json) {
      JValue var4 = org.json4s.jackson.JsonMethods..MODULE$.parse(json, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
      Object var10000;
      if (var4 instanceof JObject var5) {
         List fields = var5.obj();
         var10000 = fields.collectFirst(new Serializable() {
            private static final long serialVersionUID = 0L;

            public final Object applyOrElse(final Tuple2 x1, final Function1 default) {
               if (x1 != null) {
                  String var5 = (String)x1._1();
                  JValue v = (JValue)x1._2();
                  if ("action".equals(var5)) {
                     return v;
                  }
               }

               return default.apply(x1);
            }

            public final boolean isDefinedAt(final Tuple2 x1) {
               if (x1 != null) {
                  String var4 = (String)x1._1();
                  if ("action".equals(var4)) {
                     return true;
                  }
               }

               return false;
            }
         }).collect(new Serializable() {
            private static final long serialVersionUID = 0L;

            public final Object applyOrElse(final JValue x2, final Function1 default) {
               if (x2 instanceof JString var5) {
                  String s = var5.s();
                  return s;
               } else {
                  return default.apply(x2);
               }
            }

            public final boolean isDefinedAt(final JValue x2) {
               return x2 instanceof JString;
            }
         });
      } else {
         var10000 = scala.None..MODULE$;
      }

      Option value = (Option)var10000;
      return (String)value.getOrElse(() -> {
         throw new SubmitRestMissingFieldException("Action field not found in JSON:\n" + json);
      });
   }

   public SubmitRestProtocolMessage fromJson(final String json) {
      String className = this.parseAction(json);
      Class clazz = Utils$.MODULE$.classForName(this.packagePrefix() + "." + className, Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3()).asSubclass(SubmitRestProtocolMessage.class);
      return this.fromJson(json, clazz);
   }

   public SubmitRestProtocolMessage fromJson(final String json, final Class clazz) {
      return (SubmitRestProtocolMessage)this.org$apache$spark$deploy$rest$SubmitRestProtocolMessage$$mapper().readValue(json, clazz);
   }

   private SubmitRestProtocolMessage$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
