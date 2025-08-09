package org.json4s;

import scala.Function1;
import scala.Option;
import scala.Symbol;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.math.BigDecimal;
import scala.math.BigInt;

public final class JsonDSL$ implements JsonDSL, DoubleMode {
   public static final JsonDSL$ MODULE$ = new JsonDSL$();

   static {
      Implicits.$init$(MODULE$);
      JsonDSL.$init$(MODULE$);
      DoubleMode.$init$(MODULE$);
   }

   public JValue double2jvalue(final double x) {
      return DoubleMode.double2jvalue$(this, x);
   }

   public JValue float2jvalue(final float x) {
      return DoubleMode.float2jvalue$(this, x);
   }

   public JValue bigdecimal2jvalue(final BigDecimal x) {
      return DoubleMode.bigdecimal2jvalue$(this, x);
   }

   public JArray seq2jvalue(final Iterable s, final Function1 ev) {
      return JsonDSL.seq2jvalue$(this, s, ev);
   }

   public JObject map2jvalue(final Map m, final Function1 ev) {
      return JsonDSL.map2jvalue$(this, m, ev);
   }

   public JValue option2jvalue(final Option opt, final Function1 ev) {
      return JsonDSL.option2jvalue$(this, opt, ev);
   }

   public JString symbol2jvalue(final Symbol x) {
      return JsonDSL.symbol2jvalue$(this, x);
   }

   public JObject pair2jvalue(final Tuple2 t, final Function1 ev) {
      return JsonDSL.pair2jvalue$(this, t, ev);
   }

   public JObject list2jvalue(final List l) {
      return JsonDSL.list2jvalue$(this, l);
   }

   public List jobject2assoc(final JObject o) {
      return JsonDSL.jobject2assoc$(this, o);
   }

   public Tuple2 pair2Assoc(final Tuple2 t, final Function1 ev) {
      return JsonDSL.pair2Assoc$(this, t, ev);
   }

   public JValue short2jvalue(final short x) {
      return Implicits.short2jvalue$(this, x);
   }

   public JValue byte2jvalue(final byte x) {
      return Implicits.byte2jvalue$(this, x);
   }

   public JValue char2jvalue(final char x) {
      return Implicits.char2jvalue$(this, x);
   }

   public JValue int2jvalue(final int x) {
      return Implicits.int2jvalue$(this, x);
   }

   public JValue long2jvalue(final long x) {
      return Implicits.long2jvalue$(this, x);
   }

   public JValue bigint2jvalue(final BigInt x) {
      return Implicits.bigint2jvalue$(this, x);
   }

   public JValue boolean2jvalue(final boolean x) {
      return Implicits.boolean2jvalue$(this, x);
   }

   public JValue string2jvalue(final String x) {
      return Implicits.string2jvalue$(this, x);
   }

   private JsonDSL$() {
   }
}
