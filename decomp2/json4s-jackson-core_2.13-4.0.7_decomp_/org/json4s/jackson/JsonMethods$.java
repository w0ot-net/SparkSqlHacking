package org.json4s.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json4s.AsJsonInput;
import org.json4s.JValue;
import org.json4s.Reader;
import org.json4s.Writer;
import org.json4s.prefs.EmptyValueStrategy;
import scala.Option;

public final class JsonMethods$ implements JsonMethods {
   public static final JsonMethods$ MODULE$ = new JsonMethods$();
   private static ObjectMapper org$json4s$jackson$JsonMethods$$_defaultMapper;
   private static volatile boolean bitmap$0;

   static {
      JsonMethods.$init$(MODULE$);
   }

   public ObjectMapper mapper() {
      return JsonMethods.mapper$(this);
   }

   public JValue parse(final Object in, final boolean useBigDecimalForDouble, final boolean useBigIntForLong, final AsJsonInput evidence$1) {
      return JsonMethods.parse$(this, in, useBigDecimalForDouble, useBigIntForLong, evidence$1);
   }

   public boolean parse$default$2() {
      return JsonMethods.parse$default$2$(this);
   }

   public boolean parse$default$3() {
      return JsonMethods.parse$default$3$(this);
   }

   public Option parseOpt(final Object in, final boolean useBigDecimalForDouble, final boolean useBigIntForLong, final AsJsonInput evidence$2) {
      return JsonMethods.parseOpt$(this, in, useBigDecimalForDouble, useBigIntForLong, evidence$2);
   }

   public boolean parseOpt$default$2() {
      return JsonMethods.parseOpt$default$2$(this);
   }

   public boolean parseOpt$default$3() {
      return JsonMethods.parseOpt$default$3$(this);
   }

   public JValue render(final JValue value, final boolean alwaysEscapeUnicode, final EmptyValueStrategy emptyValueStrategy) {
      return JsonMethods.render$(this, value, alwaysEscapeUnicode, emptyValueStrategy);
   }

   public boolean render$default$2() {
      return JsonMethods.render$default$2$(this);
   }

   public EmptyValueStrategy render$default$3() {
      return JsonMethods.render$default$3$(this);
   }

   public String compact(final JValue d) {
      return JsonMethods.compact$(this, d);
   }

   public String pretty(final JValue d) {
      return JsonMethods.pretty$(this, d);
   }

   public JValue asJValue(final Object obj, final Writer writer) {
      return JsonMethods.asJValue$(this, obj, writer);
   }

   public Object fromJValue(final JValue json, final Reader reader) {
      return JsonMethods.fromJValue$(this, json, reader);
   }

   public JsonNode asJsonNode(final JValue jv) {
      return JsonMethods.asJsonNode$(this, jv);
   }

   public JValue fromJsonNode(final JsonNode jn) {
      return JsonMethods.fromJsonNode$(this, jn);
   }

   private ObjectMapper org$json4s$jackson$JsonMethods$$_defaultMapper$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            org$json4s$jackson$JsonMethods$$_defaultMapper = JsonMethods.org$json4s$jackson$JsonMethods$$_defaultMapper$(this);
            bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return org$json4s$jackson$JsonMethods$$_defaultMapper;
   }

   public ObjectMapper org$json4s$jackson$JsonMethods$$_defaultMapper() {
      return !bitmap$0 ? this.org$json4s$jackson$JsonMethods$$_defaultMapper$lzycompute() : org$json4s$jackson$JsonMethods$$_defaultMapper;
   }

   private JsonMethods$() {
   }
}
