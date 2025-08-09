package org.apache.spark.resource;

import java.io.Serializable;
import org.apache.spark.SparkException;
import org.json4s.Formats;
import org.json4s.JValue;
import org.json4s.jackson.JsonMethods.;
import scala.runtime.ModuleSerializationProxy;

public final class ResourceInformation$ implements Serializable {
   public static final ResourceInformation$ MODULE$ = new ResourceInformation$();
   private static String exampleJson;
   private static volatile boolean bitmap$0;

   private String exampleJson$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            exampleJson = .MODULE$.compact(.MODULE$.render((new ResourceInformationJson("gpu", new scala.collection.immutable..colon.colon("0", new scala.collection.immutable..colon.colon("1", scala.collection.immutable.Nil..MODULE$)))).toJValue(), .MODULE$.render$default$2(), .MODULE$.render$default$3()));
            bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return exampleJson;
   }

   private String exampleJson() {
      return !bitmap$0 ? this.exampleJson$lzycompute() : exampleJson;
   }

   public ResourceInformation parseJson(final String json) {
      Formats formats = org.json4s.DefaultFormats..MODULE$;

      try {
         return ((ResourceInformationJson)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(.MODULE$.parse(json, .MODULE$.parse$default$2(), .MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput())), formats, scala.reflect.ManifestFactory..MODULE$.classType(ResourceInformationJson.class))).toResourceInformation();
      } catch (Throwable var7) {
         if (var7 != null && scala.util.control.NonFatal..MODULE$.apply(var7)) {
            throw new SparkException("Error parsing JSON into ResourceInformation:\n" + json + "\nHere is a correct example: " + this.exampleJson() + ".", var7);
         } else {
            throw var7;
         }
      }
   }

   public ResourceInformation parseJson(final JValue json) {
      Formats formats = org.json4s.DefaultFormats..MODULE$;

      try {
         return ((ResourceInformationJson)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(json), formats, scala.reflect.ManifestFactory..MODULE$.classType(ResourceInformationJson.class))).toResourceInformation();
      } catch (Throwable var7) {
         if (var7 != null && scala.util.control.NonFatal..MODULE$.apply(var7)) {
            throw new SparkException("Error parsing JSON into ResourceInformation:\n" + json + "\n", var7);
         } else {
            throw var7;
         }
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ResourceInformation$.class);
   }

   private ResourceInformation$() {
   }
}
