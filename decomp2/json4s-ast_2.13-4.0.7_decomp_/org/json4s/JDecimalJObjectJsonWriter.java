package org.json4s;

import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ListBuffer;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import scala.sys.package.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rc\u0001\u0002\f\u0018\rqA\u0001\"\u000e\u0001\u0003\u0002\u0003\u0006Ia\t\u0005\u0006m\u0001!\ta\u000e\u0005\u0007u\u0001\u0001\u000b\u0011B\u001e\t\u000b\u0019\u0003A\u0011A$\t\u000b)\u0003A\u0011A&\t\u000b1\u0003A\u0011A&\t\u000b5\u0003A\u0011A&\t\u000b9\u0003A\u0011A&\t\u000b=\u0003A\u0011\u0001)\t\u000bm\u0003A\u0011\u0001/\t\u000b\u0005\u0004A\u0011\u00012\t\u000b\u001d\u0004A\u0011\u00015\t\u000b5\u0004A\u0011\u00018\t\u000be\u0004A\u0011\u0001>\t\r}\u0004A\u0011AA\u0001\u0011\u001d\tY\u0001\u0001C\u0001\u0003\u001bAq!a\u0006\u0001\t\u0003\tI\u0002C\u0004\u0002$\u0001!\t!!\n\t\u000f\u0005=\u0002\u0001\"\u0001\u00022!9\u0011q\u0007\u0001\u0005\u0002\u0005e\u0002bBA \u0001\u0011\u0005\u0011\u0011\t\u0002\u001a\u0015\u0012+7-[7bY*{%M[3di*\u001bxN\\,sSR,'O\u0003\u0002\u00193\u00051!n]8oiMT\u0011AG\u0001\u0004_J<7\u0001A\n\u0004\u0001u\u0019\u0003C\u0001\u0010\"\u001b\u0005y\"\"\u0001\u0011\u0002\u000bM\u001c\u0017\r\\1\n\u0005\tz\"AB!osJ+g\rE\u0002%K\u001dj\u0011aF\u0005\u0003M]\u0011!BS:p]^\u0013\u0018\u000e^3s!\tA#G\u0004\u0002*a9\u0011!f\f\b\u0003W9j\u0011\u0001\f\u0006\u0003[m\ta\u0001\u0010:p_Rt\u0014\"\u0001\u000e\n\u0005aI\u0012BA\u0019\u0018\u0003\u001dQ5o\u001c8B'RK!a\r\u001b\u0003\r)3\u0016\r\\;f\u0015\t\tt#\u0001\u0004qCJ,g\u000e^\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005aJ\u0004C\u0001\u0013\u0001\u0011\u0015)$\u00011\u0001$\u0003\u0015qw\u000eZ3t!\ra\u0014iQ\u0007\u0002{)\u0011ahP\u0001\b[V$\u0018M\u00197f\u0015\t\u0001u$\u0001\u0006d_2dWm\u0019;j_:L!AQ\u001f\u0003\u00151K7\u000f\u001e\"vM\u001a,'\u000f\u0005\u0002)\t&\u0011Q\t\u000e\u0002\u0007\u0015\u001aKW\r\u001c3\u0002\u000f\u0005$GMT8eKR\u0011\u0001\b\u0013\u0005\u0006\u0013\u0012\u0001\raQ\u0001\u0005]>$W-\u0001\u0006ti\u0006\u0014H/\u0011:sCf$\u0012aI\u0001\tK:$\u0017I\u001d:bs\u0006Y1\u000f^1si>\u0013'.Z2u\u0003%)g\u000eZ(cU\u0016\u001cG/\u0001\u0004tiJLgn\u001a\u000b\u0003GECQAU\u0005A\u0002M\u000bQA^1mk\u0016\u0004\"\u0001\u0016-\u000f\u0005U3\u0006CA\u0016 \u0013\t9v$\u0001\u0004Qe\u0016$WMZ\u0005\u00033j\u0013aa\u0015;sS:<'BA, \u0003\u0011\u0011\u0017\u0010^3\u0015\u0005\rj\u0006\"\u0002*\u000b\u0001\u0004q\u0006C\u0001\u0010`\u0013\t\u0001wD\u0001\u0003CsR,\u0017aA5oiR\u00111e\u0019\u0005\u0006%.\u0001\r\u0001\u001a\t\u0003=\u0015L!AZ\u0010\u0003\u0007%sG/\u0001\u0003m_:<GCA\u0012j\u0011\u0015\u0011F\u00021\u0001k!\tq2.\u0003\u0002m?\t!Aj\u001c8h\u0003\u0019\u0011\u0017nZ%oiR\u00111e\u001c\u0005\u0006%6\u0001\r\u0001\u001d\t\u0003cZt!A\u001d;\u000f\u0005-\u001a\u0018\"\u0001\u0011\n\u0005U|\u0012a\u00029bG.\fw-Z\u0005\u0003ob\u0014aAQ5h\u0013:$(BA; \u0003\u001d\u0011wn\u001c7fC:$\"aI>\t\u000bIs\u0001\u0019\u0001?\u0011\u0005yi\u0018B\u0001@ \u0005\u001d\u0011un\u001c7fC:\fQa\u001d5peR$2aIA\u0002\u0011\u0019\u0011v\u00021\u0001\u0002\u0006A\u0019a$a\u0002\n\u0007\u0005%qDA\u0003TQ>\u0014H/A\u0003gY>\fG\u000fF\u0002$\u0003\u001fAaA\u0015\tA\u0002\u0005E\u0001c\u0001\u0010\u0002\u0014%\u0019\u0011QC\u0010\u0003\u000b\u0019cw.\u0019;\u0002\r\u0011|WO\u00197f)\r\u0019\u00131\u0004\u0005\u0007%F\u0001\r!!\b\u0011\u0007y\ty\"C\u0002\u0002\"}\u0011a\u0001R8vE2,\u0017A\u00032jO\u0012+7-[7bYR\u00191%a\n\t\rI\u0013\u0002\u0019AA\u0015!\r\t\u00181F\u0005\u0004\u0003[A(A\u0003\"jO\u0012+7-[7bY\u0006Q1\u000f^1si\u001aKW\r\u001c3\u0015\u0007\r\n\u0019\u0004\u0003\u0004\u00026M\u0001\raU\u0001\u0005]\u0006lW-A\u0005bI\u0012Te+\u00197vKR\u00191%a\u000f\t\r\u0005uB\u00031\u0001(\u0003\tQg/\u0001\u0004sKN,H\u000e^\u000b\u0002O\u0001"
)
public final class JDecimalJObjectJsonWriter implements JsonWriter {
   private final JsonWriter parent;
   private final ListBuffer nodes;

   public JDecimalJObjectJsonWriter addNode(final Tuple2 node) {
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
      if (var2 instanceof JDecimalAstJsonWriter) {
         JDecimalAstJsonWriter var3 = (JDecimalAstJsonWriter)var2;
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
      return new JDecimalJFieldJsonWriter(name, this);
   }

   public JsonWriter addJValue(final JValue jv) {
      throw .MODULE$.error("You have to start a field to be able to end it (addJValue called before startField in a JObject builder)");
   }

   public JValue result() {
      return JsonAST$.MODULE$.JObject().apply((Seq)this.nodes.toList());
   }

   public JDecimalJObjectJsonWriter(final JsonWriter parent) {
      this.parent = parent;
      this.nodes = (ListBuffer)scala.collection.mutable.ListBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
   }
}
