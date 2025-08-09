package org.json4s;

import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import scala.sys.package.;

@ScalaSignature(
   bytes = "\u0006\u0005i4QAD\b\u0002*QAQ!\f\u0001\u0005\u00029BQ\u0001\r\u0001\u0007\u0002EBQ\u0001\u000e\u0001\u0005\u0002UBQA\u000e\u0001\u0005\u0002]BQA\u0011\u0001\u0005\u0002\rCQA\u0012\u0001\u0005\u0002\u001dCQ\u0001\u0014\u0001\u0005\u00025CQA\u0015\u0001\u0005\u0002MCQ\u0001\u0017\u0001\u0005\u0002eCQ\u0001\u001a\u0001\u0005\u0002\u0015DQA\u001b\u0001\u0005\u0002-DQ\u0001\u001d\u0001\u0005\u0002UBQ!\u001d\u0001\u0005\u0002I\u0014\u0001C\u0013,bYV,'j]8o/JLG/\u001a:\u000b\u0005A\t\u0012A\u00026t_:$4OC\u0001\u0013\u0003\ry'oZ\u0002\u0001'\r\u0001Qc\u0007\t\u0003-ei\u0011a\u0006\u0006\u00021\u0005)1oY1mC&\u0011!d\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0007qir$D\u0001\u0010\u0013\tqrB\u0001\u0006Kg>twK]5uKJ\u0004\"\u0001\t\u0016\u000f\u0005\u0005BcB\u0001\u0012(\u001d\t\u0019c%D\u0001%\u0015\t)3#\u0001\u0004=e>|GOP\u0005\u0002%%\u0011\u0001#E\u0005\u0003S=\tqAS:p]\u0006\u001bF+\u0003\u0002,Y\t1!JV1mk\u0016T!!K\b\u0002\rqJg.\u001b;?)\u0005y\u0003C\u0001\u000f\u0001\u0003\u001d\tG\r\u001a(pI\u0016$\"a\u0007\u001a\t\u000bM\u0012\u0001\u0019A\u0010\u0002\t9|G-Z\u0001\nK:$wJ\u00196fGR$\u0012aG\u0001\u000bgR\f'\u000f\u001e$jK2$GCA\u000e9\u0011\u0015ID\u00011\u0001;\u0003\u0011q\u0017-\\3\u0011\u0005mzdB\u0001\u001f>!\t\u0019s#\u0003\u0002?/\u00051\u0001K]3eK\u001aL!\u0001Q!\u0003\rM#(/\u001b8h\u0015\tqt#\u0001\u0004tiJLgn\u001a\u000b\u00037\u0011CQ!R\u0003A\u0002i\nQA^1mk\u0016\fAAY=uKR\u00111\u0004\u0013\u0005\u0006\u000b\u001a\u0001\r!\u0013\t\u0003-)K!aS\f\u0003\t\tKH/Z\u0001\u0004S:$HCA\u000eO\u0011\u0015)u\u00011\u0001P!\t1\u0002+\u0003\u0002R/\t\u0019\u0011J\u001c;\u0002\t1|gn\u001a\u000b\u00037QCQ!\u0012\u0005A\u0002U\u0003\"A\u0006,\n\u0005];\"\u0001\u0002'p]\u001e\faAY5h\u0013:$HCA\u000e[\u0011\u0015)\u0015\u00021\u0001\\!\ta\u0016M\u0004\u0002^?:\u00111EX\u0005\u00021%\u0011\u0001mF\u0001\ba\u0006\u001c7.Y4f\u0013\t\u00117M\u0001\u0004CS\u001eLe\u000e\u001e\u0006\u0003A^\tqAY8pY\u0016\fg\u000e\u0006\u0002\u001cM\")QI\u0003a\u0001OB\u0011a\u0003[\u0005\u0003S^\u0011qAQ8pY\u0016\fg.A\u0003tQ>\u0014H\u000f\u0006\u0002\u001cY\")Qi\u0003a\u0001[B\u0011aC\\\u0005\u0003_^\u0011Qa\u00155peR\f\u0001\"\u001a8e\u0003J\u0014\u0018-_\u0001\nC\u0012$'JV1mk\u0016$\"aG:\t\u000bQl\u0001\u0019A\u0010\u0002\u0005)4\u0018f\u0001\u0001wq&\u0011qo\u0004\u0002\u0016\u0015\u0012+7-[7bY\u0006\u001bHOS:p]^\u0013\u0018\u000e^3s\u0013\tIxB\u0001\u000bK\t>,(\r\\3BgRT5o\u001c8Xe&$XM\u001d"
)
public abstract class JValueJsonWriter implements JsonWriter {
   public abstract JsonWriter addNode(final JValue node);

   public JsonWriter endObject() {
      throw .MODULE$.error("You have to start an object to be able to end it (endObject called before startObject)");
   }

   public JsonWriter startField(final String name) {
      throw .MODULE$.error("You have to start an object before starting a field.");
   }

   public JsonWriter string(final String value) {
      return this.addNode(JsonAST$.MODULE$.JString().apply(value));
   }

   public JsonWriter byte(final byte value) {
      return this.addNode(JsonAST$.MODULE$.JInt().apply(scala.math.BigInt..MODULE$.long2bigInt((long)value)));
   }

   public JsonWriter int(final int value) {
      return this.addNode(JsonAST$.MODULE$.JInt().apply(scala.math.BigInt..MODULE$.int2bigInt(value)));
   }

   public JsonWriter long(final long value) {
      return this.addNode(JsonAST$.MODULE$.JInt().apply(scala.math.BigInt..MODULE$.long2bigInt(value)));
   }

   public JsonWriter bigInt(final BigInt value) {
      return this.addNode(JsonAST$.MODULE$.JInt().apply(value));
   }

   public JsonWriter boolean(final boolean value) {
      return this.addNode(JsonAST$.MODULE$.JBool().apply(value));
   }

   public JsonWriter short(final short value) {
      return this.addNode(JsonAST$.MODULE$.JInt().apply(scala.math.BigInt..MODULE$.long2bigInt((long)value)));
   }

   public JsonWriter endArray() {
      throw .MODULE$.error("You have to start an object to be able to end it (endArray called before startArray)");
   }

   public JsonWriter addJValue(final JValue jv) {
      return this.addNode(jv);
   }
}
