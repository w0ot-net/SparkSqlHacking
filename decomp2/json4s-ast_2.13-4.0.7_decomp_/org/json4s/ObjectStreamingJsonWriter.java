package org.json4s;

import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.sys.package.;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055d\u0001\u0002\u0011\"\r\u0019B\u0001b\u0010\u0001\u0003\u0006\u0004&\t\u0002\u0011\u0005\t\u0003\u0002\u0011\t\u0011)A\u0005Y!A!\t\u0001BCB\u0013E1\t\u0003\u0005H\u0001\t\u0005\t\u0015!\u0003E\u0011!A\u0005A!A!\u0002\u0013A\u0003\u0002C%\u0001\u0005\u000b\u0007K\u0011\u0003&\t\u00119\u0003!\u0011!Q\u0001\n-C\u0001b\u0014\u0001\u0003\u0006\u0004&\tb\u0011\u0005\t!\u0002\u0011\t\u0011)A\u0005\t\"A\u0011\u000b\u0001BCB\u0013E!\n\u0003\u0005S\u0001\t\u0005\t\u0015!\u0003L\u0011\u0015\u0019\u0006\u0001\"\u0001U\u0011\u0019a\u0006\u0001)Q\u0005\u0017\")Q\f\u0001C\u0001\u0001\")a\f\u0001C\u0001?\")\u0001\u000f\u0001C!c\")!\u000f\u0001C\u0001g\")Q\u000f\u0001C!c\")a\u000f\u0001C!c\")q\u000f\u0001C!c\")\u0001\u0010\u0001C!s\")A\u0010\u0001C!{\"9\u0011Q\u0001\u0001\u0005B\u0005\u001d\u0001bBA\u0006\u0001\u0011\u0005\u0013Q\u0002\u0005\b\u0003/\u0001A\u0011IA\r\u0011\u001d\ty\u0003\u0001C!\u0003cAq!!\u000e\u0001\t\u0003\n9\u0004C\u0004\u0002B\u0001!\t%a\u0011\t\u000f\u00055\u0003\u0001\"\u0011\u0002P!9\u0011\u0011\f\u0001\u0005B\u0005m\u0003bBA3\u0001\u0011\u0005\u0013q\r\u0002\u001a\u001f\nTWm\u0019;TiJ,\u0017-\\5oO*\u001bxN\\,sSR,'O\u0003\u0002#G\u00051!n]8oiMT\u0011\u0001J\u0001\u0004_J<7\u0001A\u000b\u0003O9\u001a\"\u0001\u0001\u0015\u0011\u0007%RC&D\u0001\"\u0013\tY\u0013EA\nTiJ,\u0017-\\5oO*\u001bxN\\,sSR,'\u000f\u0005\u0002.]1\u0001A!B\u0018\u0001\u0005\u0004\u0001$!\u0001+\u0012\u0005E:\u0004C\u0001\u001a6\u001b\u0005\u0019$\"\u0001\u001b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u001a$a\u0002(pi\"Lgn\u001a\t\u0003quj\u0011!\u000f\u0006\u0003um\n!![8\u000b\u0003q\nAA[1wC&\u0011a(\u000f\u0002\u0007/JLG/\u001a:\u0002\u000b9|G-Z:\u0016\u00031\naA\\8eKN\u0004\u0013!\u00027fm\u0016dW#\u0001#\u0011\u0005I*\u0015B\u0001$4\u0005\rIe\u000e^\u0001\u0007Y\u00164X\r\u001c\u0011\u0002\rA\f'/\u001a8u\u0003\u0019\u0001(/\u001a;usV\t1\n\u0005\u00023\u0019&\u0011Qj\r\u0002\b\u0005>|G.Z1o\u0003\u001d\u0001(/\u001a;us\u0002\naa\u001d9bG\u0016\u001c\u0018aB:qC\u000e,7\u000fI\u0001\u0014C2<\u0018-_:Fg\u000e\f\u0007/Z+oS\u000e|G-Z\u0001\u0015C2<\u0018-_:Fg\u000e\f\u0007/Z+oS\u000e|G-\u001a\u0011\u0002\rqJg.\u001b;?)\u001d)fk\u0016-Z5n\u00032!\u000b\u0001-\u0011\u0015yD\u00021\u0001-\u0011\u0015\u0011E\u00021\u0001E\u0011\u0015AE\u00021\u0001)\u0011\u0015IE\u00021\u0001L\u0011\u0015yE\u00021\u0001E\u0011\u0015\tF\u00021\u0001L\u0003\u001dI7OR5sgR\faA]3tk2$\u0018aB1eI:{G-\u001a\u000b\u0003A\u000e\u00042!K1-\u0013\t\u0011\u0017E\u0001\u0006Kg>twK]5uKJDQ\u0001Z\bA\u0002\u0015\fAA\\8eKB\u0011a-\u001c\b\u0003O.\u0004\"\u0001[\u001a\u000e\u0003%T!A[\u0013\u0002\rq\u0012xn\u001c;?\u0013\ta7'\u0001\u0004Qe\u0016$WMZ\u0005\u0003]>\u0014aa\u0015;sS:<'B\u000174\u0003%)g\u000eZ(cU\u0016\u001cG\u000fF\u0001a\u0003=\tG\rZ!oIF+x\u000e^3O_\u0012,GC\u00011u\u0011\u0015!\u0017\u00031\u0001f\u0003)\u0019H/\u0019:u\u0003J\u0014\u0018-_\u0001\tK:$\u0017I\u001d:bs\u0006Y1\u000f^1si>\u0013'.Z2u\u0003\u0019\u0019HO]5oOR\u0011\u0001M\u001f\u0005\u0006wV\u0001\r!Z\u0001\u0006m\u0006dW/Z\u0001\u0005Ef$X\r\u0006\u0002a}\")1P\u0006a\u0001\u007fB\u0019!'!\u0001\n\u0007\u0005\r1G\u0001\u0003CsR,\u0017aA5oiR\u0019\u0001-!\u0003\t\u000bm<\u0002\u0019\u0001#\u0002\t1|gn\u001a\u000b\u0004A\u0006=\u0001BB>\u0019\u0001\u0004\t\t\u0002E\u00023\u0003'I1!!\u00064\u0005\u0011auN\\4\u0002\r\tLw-\u00138u)\r\u0001\u00171\u0004\u0005\u0007wf\u0001\r!!\b\u0011\t\u0005}\u0011\u0011\u0006\b\u0005\u0003C\t)CD\u0002i\u0003GI\u0011\u0001N\u0005\u0004\u0003O\u0019\u0014a\u00029bG.\fw-Z\u0005\u0005\u0003W\tiC\u0001\u0004CS\u001eLe\u000e\u001e\u0006\u0004\u0003O\u0019\u0014a\u00022p_2,\u0017M\u001c\u000b\u0004A\u0006M\u0002\"B>\u001b\u0001\u0004Y\u0015!B:i_J$Hc\u00011\u0002:!11p\u0007a\u0001\u0003w\u00012AMA\u001f\u0013\r\tyd\r\u0002\u0006'\"|'\u000f^\u0001\u0006M2|\u0017\r\u001e\u000b\u0004A\u0006\u0015\u0003BB>\u001d\u0001\u0004\t9\u0005E\u00023\u0003\u0013J1!a\u00134\u0005\u00151En\\1u\u0003\u0019!w.\u001e2mKR\u0019\u0001-!\u0015\t\rml\u0002\u0019AA*!\r\u0011\u0014QK\u0005\u0004\u0003/\u001a$A\u0002#pk\ndW-\u0001\u0006cS\u001e$UmY5nC2$2\u0001YA/\u0011\u0019Yh\u00041\u0001\u0002`A!\u0011qDA1\u0013\u0011\t\u0019'!\f\u0003\u0015\tKw\rR3dS6\fG.\u0001\u0006ti\u0006\u0014HOR5fY\u0012$2\u0001YA5\u0011\u0019\tYg\ba\u0001K\u0006!a.Y7f\u0001"
)
public final class ObjectStreamingJsonWriter extends StreamingJsonWriter {
   private final java.io.Writer nodes;
   private final int level;
   private final StreamingJsonWriter parent;
   private final boolean pretty;
   private final int spaces;
   private final boolean alwaysEscapeUnicode;
   private boolean isFirst;

   public java.io.Writer nodes() {
      return this.nodes;
   }

   public int level() {
      return this.level;
   }

   public boolean pretty() {
      return this.pretty;
   }

   public int spaces() {
      return this.spaces;
   }

   public boolean alwaysEscapeUnicode() {
      return this.alwaysEscapeUnicode;
   }

   public java.io.Writer result() {
      return this.nodes();
   }

   public JsonWriter addNode(final String node) {
      if (this.isFirst) {
         this.isFirst = false;
      } else {
         this.nodes().write(",");
      }

      this.nodes().write(node);
      return this;
   }

   public JsonWriter endObject() {
      this.writePretty(2);
      this.nodes().write(125);
      return this.parent;
   }

   public JsonWriter addAndQuoteNode(final String node) {
      if (this.isFirst) {
         this.isFirst = false;
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         this.nodes().append(",");
      }

      this.nodes().append("\"");
      ParserUtil$.MODULE$.quote(node, this.nodes(), this.alwaysEscapeUnicode());
      this.nodes().append("\"");
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
      FieldStreamingJsonWriter r = new FieldStreamingJsonWriter(name, this.isFirst, this.nodes(), this.level(), this, this.pretty(), this.spaces(), this.alwaysEscapeUnicode());
      if (this.isFirst) {
         this.isFirst = false;
      }

      return r;
   }

   public ObjectStreamingJsonWriter(final java.io.Writer nodes, final int level, final StreamingJsonWriter parent, final boolean pretty, final int spaces, final boolean alwaysEscapeUnicode) {
      this.nodes = nodes;
      this.level = level;
      this.parent = parent;
      this.pretty = pretty;
      this.spaces = spaces;
      this.alwaysEscapeUnicode = alwaysEscapeUnicode;
      nodes.write(123);
      this.writePretty(this.writePretty$default$1());
      this.isFirst = true;
   }
}
