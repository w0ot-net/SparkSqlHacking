package org.apache.spark.api.r;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Map;
import scala.Function2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\tetA\u0002\u0017.\u0011\u0003\ttG\u0002\u0004:[!\u0005\u0011G\u000f\u0005\u0006\u0003\u0006!\taQ\u0003\u0005\t\u0006\u0001Q)\u0002\u0003Z\u0003\u0001Q\u0006\"C1\u0002\u0001\u0004\u0005\t\u0015)\u0003c\u0011%!\u0017\u00011A\u0001B\u0003&Q\rC\u0003g\u0003\u0011\u0005q\rC\u0003k\u0003\u0011\u00051\u000eC\u0003n\u0003\u0011\u0005a\u000eC\u0003r\u0003\u0011\u0005!\u000fC\u0003z\u0003\u0011\u0005!\u0010\u0003\u0004\u0000\u0003\u0011\u0005\u0011\u0011\u0001\u0005\b\u0003'\tA\u0011AA\u000b\u0011\u001d\ty\"\u0001C\u0001\u0003CAq!a\u000b\u0002\t\u0003\ti\u0003C\u0004\u0002L\u0005!\t!!\u0014\t\u000f\u0005E\u0013\u0001\"\u0001\u0002T!9\u0011qK\u0001\u0005\u0002\u0005e\u0003bBA5\u0003\u0011\u0005\u00111\u000e\u0005\b\u0003k\nA\u0011AA<\u0011\u001d\ti(\u0001C\u0001\u0003\u007fBq!!\"\u0002\t\u0003\t9\tC\u0004\u0002\u000e\u0006!\t!a$\t\u000f\u0005U\u0015\u0001\"\u0001\u0002\u0018\"9\u0011QT\u0001\u0005\u0002\u0005}\u0005bBA`\u0003\u0011\u0005\u0011\u0011\u0019\u0005\b\u0003\u0013\fA\u0011AAf\u0011\u001d\ti.\u0001C\u0001\u0003?Dq!a<\u0002\t\u0013\t\t\u0010C\u0004\u0002~\u0006!\t!a@\t\u000f\t%\u0011\u0001\"\u0001\u0003\f!9!1C\u0001\u0005\u0002\tU\u0001b\u0002B\u000e\u0003\u0011\u0005!Q\u0004\u0005\b\u0005G\tA\u0011\u0001B\u0013\u0011\u001d\u0011Y#\u0001C\u0001\u0005[AqAa\u000b\u0002\t\u0003\u0011I\u0004C\u0004\u0003@\u0005!\tA!\u0011\t\u000f\t\u001d\u0013\u0001\"\u0001\u0003J!9!qJ\u0001\u0005\u0002\tE\u0003b\u0002B-\u0003\u0011\u0005!1\f\u0005\b\u0005C\nA\u0011\u0001B2\u0011\u001d\u0011I'\u0001C\u0001\u0005WBqA!\u001d\u0002\t\u0003\u0011\u0019(A\u0003TKJ$UM\u0003\u0002/_\u0005\t!O\u0003\u00021c\u0005\u0019\u0011\r]5\u000b\u0005I\u001a\u0014!B:qCJ\\'B\u0001\u001b6\u0003\u0019\t\u0007/Y2iK*\ta'A\u0002pe\u001e\u0004\"\u0001O\u0001\u000e\u00035\u0012QaU3s\t\u0016\u001c\"!A\u001e\u0011\u0005qzT\"A\u001f\u000b\u0003y\nQa]2bY\u0006L!\u0001Q\u001f\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}\r\u0001A#A\u001c\u0003\u001bM\u000bFJU3bI>\u0013'.Z2u!\u0015ad\t\u0013)T\u0013\t9UHA\u0005Gk:\u001cG/[8oeA\u0011\u0011JT\u0007\u0002\u0015*\u00111\nT\u0001\u0003S>T\u0011!T\u0001\u0005U\u00064\u0018-\u0003\u0002P\u0015\nyA)\u0019;b\u0013:\u0004X\u000f^*ue\u0016\fW\u000e\u0005\u0002=#&\u0011!+\u0010\u0002\u0005\u0007\"\f'\u000f\u0005\u0002U/6\tQK\u0003\u0002W\u0019\u0006!A.\u00198h\u0013\tAVK\u0001\u0004PE*,7\r\u001e\u0002\u000f'FcuK]5uK>\u0013'.Z2u!\u0015adiW*_!\tIE,\u0003\u0002^\u0015\n\u0001B)\u0019;b\u001fV$\b/\u001e;TiJ,\u0017-\u001c\t\u0003y}K!\u0001Y\u001f\u0003\u000f\t{w\u000e\\3b]\u0006i1/\u001d7SK\u0006$wJ\u00196fGR\u0004\"aY\u0002\u000e\u0003\u0005\tab]9m/JLG/Z(cU\u0016\u001cG\u000f\u0005\u0002d\t\u0005\u00012/\u001a;T#2\u0013V-\u00193PE*,7\r\u001e\u000b\u0003G\"DQ![\u0004A\u0002\t\fQA^1mk\u0016\f\u0011c]3u'FcuK]5uK>\u0013'.Z2u)\t\u0019G\u000eC\u0003j\u0011\u0001\u0007Q-\u0001\bsK\u0006$wJ\u00196fGR$\u0016\u0010]3\u0015\u0005A{\u0007\"\u00029\n\u0001\u0004A\u0015a\u00013jg\u0006Q!/Z1e\u001f\nTWm\u0019;\u0015\u0007M\u001bH\u000fC\u0003q\u0015\u0001\u0007\u0001\nC\u0003v\u0015\u0001\u0007a/\u0001\tkm6|%M[3diR\u0013\u0018mY6feB\u0011\u0001h^\u0005\u0003q6\u0012\u0001C\u0013,N\u001f\nTWm\u0019;Ue\u0006\u001c7.\u001a:\u0002\u001fI,\u0017\r\u001a+za\u0016$wJ\u00196fGR$BaU>}}\")\u0001o\u0003a\u0001\u0011\")Qp\u0003a\u0001!\u0006AA-\u0019;b)f\u0004X\rC\u0003v\u0017\u0001\u0007a/A\u0005sK\u0006$')\u001f;fgR!\u00111AA\b!\u0015a\u0014QAA\u0005\u0013\r\t9!\u0010\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0004y\u0005-\u0011bAA\u0007{\t!!)\u001f;f\u0011\u0019\t\t\u0002\u0004a\u0001\u0011\u0006\u0011\u0011N\\\u0001\be\u0016\fG-\u00138u)\u0011\t9\"!\b\u0011\u0007q\nI\"C\u0002\u0002\u001cu\u00121!\u00138u\u0011\u0019\t\t\"\u0004a\u0001\u0011\u0006Q!/Z1e\t>,(\r\\3\u0015\t\u0005\r\u0012\u0011\u0006\t\u0004y\u0005\u0015\u0012bAA\u0014{\t1Ai\\;cY\u0016Da!!\u0005\u000f\u0001\u0004A\u0015a\u0004:fC\u0012\u001cFO]5oO\nKH/Z:\u0015\r\u0005=\u0012QIA$!\u0011\t\t$a\u0010\u000f\t\u0005M\u00121\b\t\u0004\u0003kiTBAA\u001c\u0015\r\tIDQ\u0001\u0007yI|w\u000e\u001e \n\u0007\u0005uR(\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003\u0003\n\u0019E\u0001\u0004TiJLgn\u001a\u0006\u0004\u0003{i\u0004BBA\t\u001f\u0001\u0007\u0001\nC\u0004\u0002J=\u0001\r!a\u0006\u0002\u00071,g.\u0001\u0006sK\u0006$7\u000b\u001e:j]\u001e$B!a\f\u0002P!1\u0011\u0011\u0003\tA\u0002!\u000b1B]3bI\n{w\u000e\\3b]R\u0019a,!\u0016\t\r\u0005E\u0011\u00031\u0001I\u0003!\u0011X-\u00193ECR,G\u0003BA.\u0003O\u0002B!!\u0018\u0002d5\u0011\u0011q\f\u0006\u0004\u0003Cb\u0015aA:rY&!\u0011QMA0\u0005\u0011!\u0015\r^3\t\r\u0005E!\u00031\u0001I\u0003!\u0011X-\u00193US6,G\u0003BA7\u0003g\u0002B!!\u0018\u0002p%!\u0011\u0011OA0\u0005%!\u0016.\\3ti\u0006l\u0007\u000f\u0003\u0004\u0002\u0012M\u0001\r\u0001S\u0001\re\u0016\fGMQ=uKN\f%O\u001d\u000b\u0005\u0003s\nY\bE\u0003=\u0003\u000b\t\u0019\u0001\u0003\u0004\u0002\u0012Q\u0001\r\u0001S\u0001\u000be\u0016\fG-\u00138u\u0003J\u0014H\u0003BAA\u0003\u0007\u0003R\u0001PA\u0003\u0003/Aa!!\u0005\u0016\u0001\u0004A\u0015!\u0004:fC\u0012$u.\u001e2mK\u0006\u0013(\u000f\u0006\u0003\u0002\n\u0006-\u0005#\u0002\u001f\u0002\u0006\u0005\r\u0002BBA\t-\u0001\u0007\u0001*\u0001\bsK\u0006$'i\\8mK\u0006t\u0017I\u001d:\u0015\t\u0005E\u00151\u0013\t\u0005y\u0005\u0015a\f\u0003\u0004\u0002\u0012]\u0001\r\u0001S\u0001\u000ee\u0016\fGm\u0015;sS:<\u0017I\u001d:\u0015\t\u0005e\u00151\u0014\t\u0006y\u0005\u0015\u0011q\u0006\u0005\u0007\u0003#A\u0002\u0019\u0001%\u0002\u0013I,\u0017\rZ!se\u0006LHCBAQ\u0003w\u000bi\f\r\u0003\u0002$\u0006%\u0006#\u0002\u001f\u0002\u0006\u0005\u0015\u0006\u0003BAT\u0003Sc\u0001\u0001B\u0006\u0002,f\t\t\u0011!A\u0003\u0002\u00055&aA0%cE!\u0011qVA[!\ra\u0014\u0011W\u0005\u0004\u0003gk$a\u0002(pi\"Lgn\u001a\t\u0004y\u0005]\u0016bAA]{\t\u0019\u0011I\\=\t\u000bAL\u0002\u0019\u0001%\t\u000bUL\u0002\u0019\u0001<\u0002\u0011I,\u0017\r\u001a'jgR$b!a1\u0002F\u0006\u001d\u0007\u0003\u0002\u001f\u0002\u0006MCQ\u0001\u001d\u000eA\u0002!CQ!\u001e\u000eA\u0002Y\fqA]3bI6\u000b\u0007\u000f\u0006\u0004\u0002N\u0006e\u00171\u001c\t\u0007\u0003\u001f\f)nU*\u000e\u0005\u0005E'bAAj\u0019\u0006!Q\u000f^5m\u0013\u0011\t9.!5\u0003\u00075\u000b\u0007\u000f\u0003\u0004\u0002\u0012m\u0001\r\u0001\u0013\u0005\u0006kn\u0001\rA^\u0001\noJLG/\u001a+za\u0016$b!!9\u0002h\u0006-\bc\u0001\u001f\u0002d&\u0019\u0011Q]\u001f\u0003\tUs\u0017\u000e\u001e\u0005\u0007\u0003Sd\u0002\u0019A.\u0002\u0007\u0011|7\u000fC\u0004\u0002nr\u0001\r!a\f\u0002\u000fQL\b/Z*ue\u0006iqO]5uK.+\u0017PV1mk\u0016$\"\"!9\u0002t\u0006U\u0018\u0011`A~\u0011\u0019\tI/\ba\u00017\"1\u0011q_\u000fA\u0002M\u000b1a[3z\u0011\u0015IW\u00041\u0001T\u0011\u0015)X\u00041\u0001w\u0003-9(/\u001b;f\u001f\nTWm\u0019;\u0015\u0011\u0005\u0005(\u0011\u0001B\u0002\u0005\u000fAa!!;\u001f\u0001\u0004Y\u0006B\u0002B\u0003=\u0001\u00071+A\u0002pE*DQ!\u001e\u0010A\u0002Y\f\u0001b\u001e:ji\u0016Le\u000e\u001e\u000b\u0007\u0003C\u0014iA!\u0005\t\r\t=q\u00041\u0001\\\u0003\ryW\u000f\u001e\u0005\u0007S~\u0001\r!a\u0006\u0002\u0017]\u0014\u0018\u000e^3E_V\u0014G.\u001a\u000b\u0007\u0003C\u00149B!\u0007\t\r\t=\u0001\u00051\u0001\\\u0011\u0019I\u0007\u00051\u0001\u0002$\u0005aqO]5uK\n{w\u000e\\3b]R1\u0011\u0011\u001dB\u0010\u0005CAaAa\u0004\"\u0001\u0004Y\u0006\"B5\"\u0001\u0004q\u0016!C<sSR,G)\u0019;f)\u0019\t\tOa\n\u0003*!1!q\u0002\u0012A\u0002mCa!\u001b\u0012A\u0002\u0005m\u0013!C<sSR,G+[7f)\u0019\t\tOa\f\u00032!1!qB\u0012A\u0002mCa![\u0012A\u0002\tM\u0002\u0003BA/\u0005kIAAa\u000e\u0002`\t!A+[7f)\u0019\t\tOa\u000f\u0003>!1!q\u0002\u0013A\u0002mCa!\u001b\u0013A\u0002\u00055\u0014aC<sSR,7\u000b\u001e:j]\u001e$b!!9\u0003D\t\u0015\u0003B\u0002B\bK\u0001\u00071\f\u0003\u0004jK\u0001\u0007\u0011qF\u0001\u000boJLG/\u001a\"zi\u0016\u001cHCBAq\u0005\u0017\u0012i\u0005\u0003\u0004\u0003\u0010\u0019\u0002\ra\u0017\u0005\u0007S\u001a\u0002\r!a\u0001\u0002\u0013]\u0014\u0018\u000e^3K\u001f\nTG\u0003CAq\u0005'\u0012)Fa\u0016\t\r\t=q\u00051\u0001\\\u0011\u0015Iw\u00051\u0001T\u0011\u0015)x\u00051\u0001w\u0003-9(/\u001b;f\u0013:$\u0018I\u001d:\u0015\r\u0005\u0005(Q\fB0\u0011\u0019\u0011y\u0001\u000ba\u00017\"1\u0011\u000e\u000ba\u0001\u0003\u0003\u000bab\u001e:ji\u0016$u.\u001e2mK\u0006\u0013(\u000f\u0006\u0004\u0002b\n\u0015$q\r\u0005\u0007\u0005\u001fI\u0003\u0019A.\t\r%L\u0003\u0019AAE\u0003=9(/\u001b;f\u0005>|G.Z1o\u0003J\u0014HCBAq\u0005[\u0012y\u0007\u0003\u0004\u0003\u0010)\u0002\ra\u0017\u0005\u0007S*\u0002\r!!%\u0002\u001d]\u0014\u0018\u000e^3TiJLgnZ!seR1\u0011\u0011\u001dB;\u0005oBaAa\u0004,\u0001\u0004Y\u0006BB5,\u0001\u0004\tI\n"
)
public final class SerDe {
   public static void writeStringArr(final DataOutputStream out, final String[] value) {
      SerDe$.MODULE$.writeStringArr(out, value);
   }

   public static void writeBooleanArr(final DataOutputStream out, final boolean[] value) {
      SerDe$.MODULE$.writeBooleanArr(out, value);
   }

   public static void writeDoubleArr(final DataOutputStream out, final double[] value) {
      SerDe$.MODULE$.writeDoubleArr(out, value);
   }

   public static void writeIntArr(final DataOutputStream out, final int[] value) {
      SerDe$.MODULE$.writeIntArr(out, value);
   }

   public static void writeJObj(final DataOutputStream out, final Object value, final JVMObjectTracker jvmObjectTracker) {
      SerDe$.MODULE$.writeJObj(out, value, jvmObjectTracker);
   }

   public static void writeBytes(final DataOutputStream out, final byte[] value) {
      SerDe$.MODULE$.writeBytes(out, value);
   }

   public static void writeString(final DataOutputStream out, final String value) {
      SerDe$.MODULE$.writeString(out, value);
   }

   public static void writeTime(final DataOutputStream out, final Timestamp value) {
      SerDe$.MODULE$.writeTime(out, value);
   }

   public static void writeTime(final DataOutputStream out, final Time value) {
      SerDe$.MODULE$.writeTime(out, value);
   }

   public static void writeDate(final DataOutputStream out, final Date value) {
      SerDe$.MODULE$.writeDate(out, value);
   }

   public static void writeBoolean(final DataOutputStream out, final boolean value) {
      SerDe$.MODULE$.writeBoolean(out, value);
   }

   public static void writeDouble(final DataOutputStream out, final double value) {
      SerDe$.MODULE$.writeDouble(out, value);
   }

   public static void writeInt(final DataOutputStream out, final int value) {
      SerDe$.MODULE$.writeInt(out, value);
   }

   public static void writeObject(final DataOutputStream dos, final Object obj, final JVMObjectTracker jvmObjectTracker) {
      SerDe$.MODULE$.writeObject(dos, obj, jvmObjectTracker);
   }

   public static void writeType(final DataOutputStream dos, final String typeStr) {
      SerDe$.MODULE$.writeType(dos, typeStr);
   }

   public static Map readMap(final DataInputStream in, final JVMObjectTracker jvmObjectTracker) {
      return SerDe$.MODULE$.readMap(in, jvmObjectTracker);
   }

   public static Object[] readList(final DataInputStream dis, final JVMObjectTracker jvmObjectTracker) {
      return SerDe$.MODULE$.readList(dis, jvmObjectTracker);
   }

   public static Object readArray(final DataInputStream dis, final JVMObjectTracker jvmObjectTracker) {
      return SerDe$.MODULE$.readArray(dis, jvmObjectTracker);
   }

   public static String[] readStringArr(final DataInputStream in) {
      return SerDe$.MODULE$.readStringArr(in);
   }

   public static boolean[] readBooleanArr(final DataInputStream in) {
      return SerDe$.MODULE$.readBooleanArr(in);
   }

   public static double[] readDoubleArr(final DataInputStream in) {
      return SerDe$.MODULE$.readDoubleArr(in);
   }

   public static int[] readIntArr(final DataInputStream in) {
      return SerDe$.MODULE$.readIntArr(in);
   }

   public static byte[][] readBytesArr(final DataInputStream in) {
      return SerDe$.MODULE$.readBytesArr(in);
   }

   public static Timestamp readTime(final DataInputStream in) {
      return SerDe$.MODULE$.readTime(in);
   }

   public static Date readDate(final DataInputStream in) {
      return SerDe$.MODULE$.readDate(in);
   }

   public static boolean readBoolean(final DataInputStream in) {
      return SerDe$.MODULE$.readBoolean(in);
   }

   public static String readString(final DataInputStream in) {
      return SerDe$.MODULE$.readString(in);
   }

   public static String readStringBytes(final DataInputStream in, final int len) {
      return SerDe$.MODULE$.readStringBytes(in, len);
   }

   public static double readDouble(final DataInputStream in) {
      return SerDe$.MODULE$.readDouble(in);
   }

   public static int readInt(final DataInputStream in) {
      return SerDe$.MODULE$.readInt(in);
   }

   public static byte[] readBytes(final DataInputStream in) {
      return SerDe$.MODULE$.readBytes(in);
   }

   public static Object readTypedObject(final DataInputStream dis, final char dataType, final JVMObjectTracker jvmObjectTracker) {
      return SerDe$.MODULE$.readTypedObject(dis, dataType, jvmObjectTracker);
   }

   public static Object readObject(final DataInputStream dis, final JVMObjectTracker jvmObjectTracker) {
      return SerDe$.MODULE$.readObject(dis, jvmObjectTracker);
   }

   public static char readObjectType(final DataInputStream dis) {
      return SerDe$.MODULE$.readObjectType(dis);
   }

   public static SerDe$ setSQLWriteObject(final Function2 value) {
      return SerDe$.MODULE$.setSQLWriteObject(value);
   }

   public static SerDe$ setSQLReadObject(final Function2 value) {
      return SerDe$.MODULE$.setSQLReadObject(value);
   }
}
