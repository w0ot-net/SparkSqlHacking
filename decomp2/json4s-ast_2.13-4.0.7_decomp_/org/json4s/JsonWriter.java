package org.json4s;

import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mt!B\r\u001b\u0011\u0003yb!B\u0011\u001b\u0011\u0003\u0011\u0003\"B\u0015\u0002\t\u0003Q\u0003\"B\u0016\u0002\t\u0003a\u0003BBA\"\u0003\u0011\u0005A\u0006C\u0004\u0002F\u0005!\t!a\u0012\t\u000f\u0005-\u0014\u0001\"\u0001\u0002n\u00199\u0011E\u0007I\u0001$\u0003q\u0003\"\u0002\u0019\b\r\u0003\t\u0004\"\u0002 \b\r\u0003\t\u0004\"B \b\r\u0003\t\u0004\"\u0002!\b\r\u0003\t\u0004\"B!\b\r\u0003\u0011\u0005\"\u0002)\b\r\u0003\t\u0006\"\u0002,\b\r\u00039\u0006\"\u0002/\b\r\u0003i\u0006\"\u00022\b\r\u0003\u0019\u0007\"\u00028\b\r\u0003y\u0007\"\u0002;\b\r\u0003)\b\"\u0002>\b\r\u0003Y\bbBA\u0001\u000f\u0019\u0005\u00111\u0001\u0005\b\u0003\u001b9a\u0011AA\b\u0011\u001d\tIb\u0002D\u0001\u00037Aq!!\t\b\r\u0003\t\u0019\u0003C\u0004\u0002&\u001d1\t!a\n\u0002\u0015)\u001bxN\\,sSR,'O\u0003\u0002\u001c9\u00051!n]8oiMT\u0011!H\u0001\u0004_J<7\u0001\u0001\t\u0003A\u0005i\u0011A\u0007\u0002\u000b\u0015N|gn\u0016:ji\u0016\u00148CA\u0001$!\t!s%D\u0001&\u0015\u00051\u0013!B:dC2\f\u0017B\u0001\u0015&\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012aH\u0001\u0004CN$X#A\u0017\u0011\t\u0001:\u0011QF\u000b\u0003_U\u001a\"aB\u0012\u0002\u0015M$\u0018M\u001d;BeJ\f\u0017\u0010F\u00013!\r\u0001sa\r\t\u0003iUb\u0001\u0001B\u00037\u000f\t\u0007qGA\u0001U#\tA4\b\u0005\u0002%s%\u0011!(\n\u0002\b\u001d>$\b.\u001b8h!\t!C(\u0003\u0002>K\t\u0019\u0011I\\=\u0002\u0011\u0015tG-\u0011:sCf\f1b\u001d;beR|%M[3di\u0006IQM\u001c3PE*,7\r^\u0001\u0007gR\u0014\u0018N\\4\u0015\u0005I\u001a\u0005\"\u0002#\r\u0001\u0004)\u0015!\u0002<bYV,\u0007C\u0001$N\u001d\t95\n\u0005\u0002IK5\t\u0011J\u0003\u0002K=\u00051AH]8pizJ!\u0001T\u0013\u0002\rA\u0013X\rZ3g\u0013\tquJ\u0001\u0004TiJLgn\u001a\u0006\u0003\u0019\u0016\nAAY=uKR\u0011!G\u0015\u0005\u0006\t6\u0001\ra\u0015\t\u0003IQK!!V\u0013\u0003\t\tKH/Z\u0001\u0004S:$HC\u0001\u001aY\u0011\u0015!e\u00021\u0001Z!\t!#,\u0003\u0002\\K\t\u0019\u0011J\u001c;\u0002\t1|gn\u001a\u000b\u0003eyCQ\u0001R\bA\u0002}\u0003\"\u0001\n1\n\u0005\u0005,#\u0001\u0002'p]\u001e\faAY5h\u0013:$HC\u0001\u001ae\u0011\u0015!\u0005\u00031\u0001f!\t17N\u0004\u0002hS:\u0011\u0001\n[\u0005\u0002M%\u0011!.J\u0001\ba\u0006\u001c7.Y4f\u0013\taWN\u0001\u0004CS\u001eLe\u000e\u001e\u0006\u0003U\u0016\nqAY8pY\u0016\fg\u000e\u0006\u00023a\")A)\u0005a\u0001cB\u0011AE]\u0005\u0003g\u0016\u0012qAQ8pY\u0016\fg.A\u0003tQ>\u0014H\u000f\u0006\u00023m\")AI\u0005a\u0001oB\u0011A\u0005_\u0005\u0003s\u0016\u0012Qa\u00155peR\fQA\u001a7pCR$\"A\r?\t\u000b\u0011\u001b\u0002\u0019A?\u0011\u0005\u0011r\u0018BA@&\u0005\u00151En\\1u\u0003\u0019!w.\u001e2mKR\u0019!'!\u0002\t\r\u0011#\u0002\u0019AA\u0004!\r!\u0013\u0011B\u0005\u0004\u0003\u0017)#A\u0002#pk\ndW-\u0001\u0006cS\u001e$UmY5nC2$2AMA\t\u0011\u0019!U\u00031\u0001\u0002\u0014A\u0019a-!\u0006\n\u0007\u0005]QN\u0001\u0006CS\u001e$UmY5nC2\f!b\u001d;beR4\u0015.\u001a7e)\r\u0011\u0014Q\u0004\u0005\u0007\u0003?1\u0002\u0019A#\u0002\t9\fW.Z\u0001\u0007e\u0016\u001cX\u000f\u001c;\u0016\u0003M\n\u0011\"\u00193e\u0015Z\u000bG.^3\u0015\u0007I\nI\u0003C\u0004\u0002,a\u0001\r!!\f\u0002\u0005)4\b\u0003BA\u0018\u0003{qA!!\r\u0002:9!\u00111GA\u001c\u001d\rA\u0015QG\u0005\u0002;%\u00111\u0004H\u0005\u0004\u0003wQ\u0012a\u0002&t_:\f5\u000bV\u0005\u0005\u0003\u007f\t\tE\u0001\u0004K-\u0006dW/\u001a\u0006\u0004\u0003wQ\u0012!\u00042jO\u0012+7-[7bY\u0006\u001bH/A\u0005tiJ,\u0017-\\5oOV!\u0011\u0011JA()\u0019\tY%a\u0019\u0002hA!\u0001eBA'!\r!\u0014q\n\u0003\u0007m\u0015\u0011\r!!\u0015\u0012\u0007a\n\u0019\u0006\u0005\u0003\u0002V\u0005}SBAA,\u0015\u0011\tI&a\u0017\u0002\u0005%|'BAA/\u0003\u0011Q\u0017M^1\n\t\u0005\u0005\u0014q\u000b\u0002\u0007/JLG/\u001a:\t\u000f\u0005\u0015T\u00011\u0001\u0002N\u00051qO]5uKJDa!!\u001b\u0006\u0001\u0004\t\u0018aE1mo\u0006L8/R:dCB,WK\\5d_\u0012,\u0017aD:ue\u0016\fW.\u001b8h!J,G\u000f^=\u0016\t\u0005=\u0014Q\u000f\u000b\u0007\u0003c\n9(!\u001f\u0011\t\u0001:\u00111\u000f\t\u0004i\u0005UDA\u0002\u001c\u0007\u0005\u0004\t\t\u0006C\u0004\u0002f\u0019\u0001\r!a\u001d\t\r\u0005%d\u00011\u0001r\u0001"
)
public interface JsonWriter {
   static JsonWriter streamingPretty(final java.io.Writer writer, final boolean alwaysEscapeUnicode) {
      return JsonWriter$.MODULE$.streamingPretty(writer, alwaysEscapeUnicode);
   }

   static JsonWriter streaming(final java.io.Writer writer, final boolean alwaysEscapeUnicode) {
      return JsonWriter$.MODULE$.streaming(writer, alwaysEscapeUnicode);
   }

   static JsonWriter bigDecimalAst() {
      return JsonWriter$.MODULE$.bigDecimalAst();
   }

   static JsonWriter ast() {
      return JsonWriter$.MODULE$.ast();
   }

   JsonWriter startArray();

   JsonWriter endArray();

   JsonWriter startObject();

   JsonWriter endObject();

   JsonWriter string(final String value);

   JsonWriter byte(final byte value);

   JsonWriter int(final int value);

   JsonWriter long(final long value);

   JsonWriter bigInt(final BigInt value);

   JsonWriter boolean(final boolean value);

   JsonWriter short(final short value);

   JsonWriter float(final float value);

   JsonWriter double(final double value);

   JsonWriter bigDecimal(final BigDecimal value);

   JsonWriter startField(final String name);

   Object result();

   JsonWriter addJValue(final JValue jv);
}
