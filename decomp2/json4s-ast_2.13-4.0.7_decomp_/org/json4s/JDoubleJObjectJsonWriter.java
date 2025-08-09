package org.json4s;

import scala.Tuple2;
import scala.collection.mutable.ListBuffer;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import scala.sys.package.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rc\u0001\u0002\f\u0018\rqA\u0001\"\u000e\u0001\u0003\u0002\u0003\u0006Ia\t\u0005\u0006m\u0001!\ta\u000e\u0005\u0007u\u0001\u0001\u000b\u0011B\u001e\t\u000b\u0019\u0003A\u0011A$\t\u000b)\u0003A\u0011A&\t\u000b1\u0003A\u0011A&\t\u000b5\u0003A\u0011A&\t\u000b9\u0003A\u0011A&\t\u000b=\u0003A\u0011\u0001)\t\u000bm\u0003A\u0011\u0001/\t\u000b\u0005\u0004A\u0011\u00012\t\u000b\u001d\u0004A\u0011\u00015\t\u000b5\u0004A\u0011\u00018\t\u000be\u0004A\u0011\u0001>\t\r}\u0004A\u0011AA\u0001\u0011\u001d\tY\u0001\u0001C\u0001\u0003\u001bAq!a\u0006\u0001\t\u0003\tI\u0002C\u0004\u0002$\u0001!\t!!\n\t\u000f\u0005=\u0002\u0001\"\u0001\u00022!9\u0011q\u0007\u0001\u0005\u0002\u0005e\u0002bBA \u0001\u0011\u0005\u0011\u0011\t\u0002\u0019\u0015\u0012{WO\u00197f\u0015>\u0013'.Z2u\u0015N|gn\u0016:ji\u0016\u0014(B\u0001\r\u001a\u0003\u0019Q7o\u001c85g*\t!$A\u0002pe\u001e\u001c\u0001aE\u0002\u0001;\r\u0002\"AH\u0011\u000e\u0003}Q\u0011\u0001I\u0001\u0006g\u000e\fG.Y\u0005\u0003E}\u0011a!\u00118z%\u00164\u0007c\u0001\u0013&O5\tq#\u0003\u0002'/\tQ!j]8o/JLG/\u001a:\u0011\u0005!\u0012dBA\u00151\u001d\tQsF\u0004\u0002,]5\tAF\u0003\u0002.7\u00051AH]8pizJ\u0011AG\u0005\u00031eI!!M\f\u0002\u000f)\u001bxN\\!T)&\u00111\u0007\u000e\u0002\u0007\u0015Z\u000bG.^3\u000b\u0005E:\u0012A\u00029be\u0016tG/\u0001\u0004=S:LGO\u0010\u000b\u0003qe\u0002\"\u0001\n\u0001\t\u000bU\u0012\u0001\u0019A\u0012\u0002\u000b9|G-Z:\u0011\u0007q\n5)D\u0001>\u0015\tqt(A\u0004nkR\f'\r\\3\u000b\u0005\u0001{\u0012AC2pY2,7\r^5p]&\u0011!)\u0010\u0002\u000b\u0019&\u001cHOQ;gM\u0016\u0014\bC\u0001\u0015E\u0013\t)EG\u0001\u0004K\r&,G\u000eZ\u0001\bC\u0012$gj\u001c3f)\tA\u0004\nC\u0003J\t\u0001\u00071)\u0001\u0003o_\u0012,\u0017AC:uCJ$\u0018I\u001d:bsR\t1%\u0001\u0005f]\u0012\f%O]1z\u0003-\u0019H/\u0019:u\u001f\nTWm\u0019;\u0002\u0013\u0015tGm\u00142kK\u000e$\u0018AB:ue&tw\r\u0006\u0002$#\")!+\u0003a\u0001'\u0006)a/\u00197vKB\u0011A\u000b\u0017\b\u0003+Z\u0003\"aK\u0010\n\u0005]{\u0012A\u0002)sK\u0012,g-\u0003\u0002Z5\n11\u000b\u001e:j]\u001eT!aV\u0010\u0002\t\tLH/\u001a\u000b\u0003GuCQA\u0015\u0006A\u0002y\u0003\"AH0\n\u0005\u0001|\"\u0001\u0002\"zi\u0016\f1!\u001b8u)\t\u00193\rC\u0003S\u0017\u0001\u0007A\r\u0005\u0002\u001fK&\u0011am\b\u0002\u0004\u0013:$\u0018\u0001\u00027p]\u001e$\"aI5\t\u000bIc\u0001\u0019\u00016\u0011\u0005yY\u0017B\u00017 \u0005\u0011auN\\4\u0002\r\tLw-\u00138u)\t\u0019s\u000eC\u0003S\u001b\u0001\u0007\u0001\u000f\u0005\u0002rm:\u0011!\u000f\u001e\b\u0003WML\u0011\u0001I\u0005\u0003k~\tq\u0001]1dW\u0006<W-\u0003\u0002xq\n1!)[4J]RT!!^\u0010\u0002\u000f\t|w\u000e\\3b]R\u00111e\u001f\u0005\u0006%:\u0001\r\u0001 \t\u0003=uL!A`\u0010\u0003\u000f\t{w\u000e\\3b]\u0006)1\u000f[8siR\u00191%a\u0001\t\rI{\u0001\u0019AA\u0003!\rq\u0012qA\u0005\u0004\u0003\u0013y\"!B*i_J$\u0018!\u00024m_\u0006$HcA\u0012\u0002\u0010!1!\u000b\u0005a\u0001\u0003#\u00012AHA\n\u0013\r\t)b\b\u0002\u0006\r2|\u0017\r^\u0001\u0007I>,(\r\\3\u0015\u0007\r\nY\u0002\u0003\u0004S#\u0001\u0007\u0011Q\u0004\t\u0004=\u0005}\u0011bAA\u0011?\t1Ai\\;cY\u0016\f!BY5h\t\u0016\u001c\u0017.\\1m)\r\u0019\u0013q\u0005\u0005\u0007%J\u0001\r!!\u000b\u0011\u0007E\fY#C\u0002\u0002.a\u0014!BQ5h\t\u0016\u001c\u0017.\\1m\u0003)\u0019H/\u0019:u\r&,G\u000e\u001a\u000b\u0004G\u0005M\u0002BBA\u001b'\u0001\u00071+\u0001\u0003oC6,\u0017!C1eI*3\u0016\r\\;f)\r\u0019\u00131\b\u0005\u0007\u0003{!\u0002\u0019A\u0014\u0002\u0005)4\u0018A\u0002:fgVdG/F\u0001(\u0001"
)
public final class JDoubleJObjectJsonWriter implements JsonWriter {
   private final JsonWriter parent;
   private final ListBuffer nodes;

   public JDoubleJObjectJsonWriter addNode(final Tuple2 node) {
      this.nodes.$plus$eq(node);
      return this;
   }

   public JsonWriter startArray() {
      throw .MODULE$.error("You have to start a field to be able to end it (startArray called before startField in a JObject builder)");
   }

   public JsonWriter endArray() {
      throw .MODULE$.error("You have to start an array to be able to end it (endArray called before startArray)");
   }

   public JsonWriter startObject() {
      throw .MODULE$.error("You have to start a field to be able to end it (startObject called before startField in a JObject builder)");
   }

   public JsonWriter endObject() {
      JsonWriter var2 = this.parent;
      JsonWriter var1;
      if (var2 instanceof JDoubleAstJsonWriter) {
         JDoubleAstJsonWriter var3 = (JDoubleAstJsonWriter)var2;
         var1 = var3.addNode(this.result());
      } else {
         var1 = this.parent;
      }

      return var1;
   }

   public JsonWriter string(final String value) {
      throw .MODULE$.error("You have to start a field to be able to end it (string called before startField in a JObject builder)");
   }

   public JsonWriter byte(final byte value) {
      throw .MODULE$.error("You have to start a field to be able to end it (byte called before startField in a JObject builder)");
   }

   public JsonWriter int(final int value) {
      throw .MODULE$.error("You have to start a field to be able to end it (int called before startField in a JObject builder)");
   }

   public JsonWriter long(final long value) {
      throw .MODULE$.error("You have to start a field to be able to end it (long called before startField in a JObject builder)");
   }

   public JsonWriter bigInt(final BigInt value) {
      throw .MODULE$.error("You have to start a field to be able to end it (bigInt called before startField in a JObject builder)");
   }

   public JsonWriter boolean(final boolean value) {
      throw .MODULE$.error("You have to start a field to be able to end it (boolean called before startField in a JObject builder)");
   }

   public JsonWriter short(final short value) {
      throw .MODULE$.error("You have to start a field to be able to end it (short called before startField in a JObject builder)");
   }

   public JsonWriter float(final float value) {
      throw .MODULE$.error("You have to start a field to be able to end it (float called before startField in a JObject builder)");
   }

   public JsonWriter double(final double value) {
      throw .MODULE$.error("You have to start a field to be able to end it (double called before startField in a JObject builder)");
   }

   public JsonWriter bigDecimal(final BigDecimal value) {
      throw .MODULE$.error("You have to start a field to be able to end it (bigDecimal called before startField in a JObject builder)");
   }

   public JsonWriter startField(final String name) {
      return new JDoubleJFieldJsonWriter(name, this);
   }

   public JsonWriter addJValue(final JValue jv) {
      throw .MODULE$.error("You have to start a field to be able to end it (addJValue called before startField in a JObject builder)");
   }

   public JValue result() {
      return JsonAST$.MODULE$.JObject().apply(this.nodes.toList());
   }

   public JDoubleJObjectJsonWriter(final JsonWriter parent) {
      this.parent = parent;
      this.nodes = (ListBuffer)scala.collection.mutable.ListBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
   }
}
