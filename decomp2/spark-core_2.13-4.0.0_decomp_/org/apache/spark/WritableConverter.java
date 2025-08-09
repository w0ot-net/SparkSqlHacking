package org.apache.spark;

import java.io.Serializable;
import scala.Function0;
import scala.Function1;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mg!\u0002\u0012$\u0001\rJ\u0003\u0002C\u001d\u0001\u0005\u000b\u0007I\u0011A\u001e\t\u0011}\u0003!\u0011!Q\u0001\nqB\u0001\u0002\u001b\u0001\u0003\u0006\u0004%\t!\u001b\u0005\tW\u0002\u0011\t\u0011)A\u0005U\")A\u000e\u0001C\u0001[\u001e)qo\tE\u0001q\u001a)!e\tE\u0001s\")An\u0002C\u0001u\"11p\u0002C\u0001GqD\u0011\"!\u0006\b\u0005\u0004%\u0019!a\u0006\t\u0011\u0005\u001dr\u0001)A\u0005\u00033A\u0011\"!\u000b\b\u0005\u0004%\u0019!a\u000b\t\u0011\u0005]r\u0001)A\u0005\u0003[A\u0011\"!\u000f\b\u0005\u0004%\u0019!a\u000f\t\u0011\u0005\u001ds\u0001)A\u0005\u0003{A\u0011\"!\u0013\b\u0005\u0004%\u0019!a\u0013\t\u0011\u0005]s\u0001)A\u0005\u0003\u001bB\u0011\"!\u0017\b\u0005\u0004%\u0019!a\u0017\t\u0011\u0005\u001dt\u0001)A\u0005\u0003;B\u0011\"!\u001b\b\u0005\u0004%\u0019!a\u001b\t\u0011\u0005ut\u0001)A\u0005\u0003[B\u0011\"a \b\u0005\u0004%\u0019!!!\t\u0011\u00055u\u0001)A\u0005\u0003\u0007Cq!a$\b\t\u0007\t\t\nC\u0004\u0002$\u001e!\u0019!!*\t\u000f\u0005\u001dv\u0001b\u0001\u0002*\"9\u00111V\u0004\u0005\u0004\u00055\u0006bBAX\u000f\u0011\r\u0011\u0011\u0017\u0005\b\u0003g;A1AA[\u0011\u001d\t9l\u0002C\u0002\u0003sCq!a/\b\t\u0007\ti\fC\u0004\u0002@\u001e!\u0019!!1\t\u0013\u0005-w!!A\u0005\n\u00055'!E,sSR\f'\r\\3D_:4XM\u001d;fe*\u0011A%J\u0001\u0006gB\f'o\u001b\u0006\u0003M\u001d\na!\u00199bG\",'\"\u0001\u0015\u0002\u0007=\u0014x-\u0006\u0002+\u000fN\u0019\u0001aK\u0019\u0011\u00051zS\"A\u0017\u000b\u00039\nQa]2bY\u0006L!\u0001M\u0017\u0003\r\u0005s\u0017PU3g!\t\u0011t'D\u00014\u0015\t!T'\u0001\u0002j_*\ta'\u0001\u0003kCZ\f\u0017B\u0001\u001d4\u00051\u0019VM]5bY&T\u0018M\u00197f\u000359(/\u001b;bE2,7\t\\1tg\u000e\u0001Q#\u0001\u001f\u0011\t1jt\bU\u0005\u0003}5\u0012\u0011BR;oGRLwN\\\u0019\u0011\u0007\u0001\u001bU)D\u0001B\u0015\t\u0011U&A\u0004sK\u001adWm\u0019;\n\u0005\u0011\u000b%\u0001C\"mCN\u001cH+Y4\u0011\u0005\u0019;E\u0002\u0001\u0003\u0006\u0011\u0002\u0011\r!\u0013\u0002\u0002)F\u0011!*\u0014\t\u0003Y-K!\u0001T\u0017\u0003\u000f9{G\u000f[5oOB\u0011AFT\u0005\u0003\u001f6\u00121!\u00118za\t\tV\fE\u0002S3rs!aU,\u0011\u0005QkS\"A+\u000b\u0005YS\u0014A\u0002\u001fs_>$h(\u0003\u0002Y[\u00051\u0001K]3eK\u001aL!AW.\u0003\u000b\rc\u0017m]:\u000b\u0005ak\u0003C\u0001$^\t%q&!!A\u0001\u0002\u000b\u0005\u0001M\u0001\u0003`IE*\u0014AD<sSR\f'\r\\3DY\u0006\u001c8\u000fI\t\u0003\u0015\u0006\u0004\"A\u00194\u000e\u0003\rT!\u0001\u000e3\u000b\u0005\u0015,\u0013A\u00025bI>|\u0007/\u0003\u0002hG\nAqK]5uC\ndW-A\u0004d_:4XM\u001d;\u0016\u0003)\u0004B\u0001L\u001fb\u000b\u0006A1m\u001c8wKJ$\b%\u0001\u0004=S:LGO\u0010\u000b\u0004]B4\bcA8\u0001\u000b6\t1\u0005C\u0003:\u000b\u0001\u0007\u0011\u000f\u0005\u0003-{}\u0012\bGA:v!\r\u0011\u0016\f\u001e\t\u0003\rV$\u0011B\u00189\u0002\u0002\u0003\u0005)\u0011\u00011\t\u000b!,\u0001\u0019\u00016\u0002#]\u0013\u0018\u000e^1cY\u0016\u001cuN\u001c<feR,'\u000f\u0005\u0002p\u000fM\u0019qaK\u0019\u0015\u0003a\fqc]5na2,wK]5uC\ndWmQ8om\u0016\u0014H/\u001a:\u0016\u000bu\f\u0019!!\u0004\u0015\u0007y\f\t\u0002F\u0002\u0000\u0003\u000b\u0001Ba\u001c\u0001\u0002\u0002A\u0019a)a\u0001\u0005\u000b!K!\u0019A%\t\u0013\u0005\u001d\u0011\"!AA\u0004\u0005%\u0011aC3wS\u0012,gnY3%ca\u0002B\u0001Q\"\u0002\fA\u0019a)!\u0004\u0005\r\u0005=\u0011B1\u0001a\u0005\u00059\u0006B\u00025\n\u0001\u0004\t\u0019\u0002\u0005\u0004-{\u0005-\u0011\u0011A\u0001\u0017S:$xK]5uC\ndWmQ8om\u0016\u0014H/\u001a:G]V\u0011\u0011\u0011\u0004\t\u0006Y\u0005m\u0011qD\u0005\u0004\u0003;i#!\u0003$v]\u000e$\u0018n\u001c81!\u0011y\u0007!!\t\u0011\u00071\n\u0019#C\u0002\u0002&5\u00121!\u00138u\u0003]Ig\u000e^,sSR\f'\r\\3D_:4XM\u001d;fe\u001as\u0007%A\fm_:<wK]5uC\ndWmQ8om\u0016\u0014H/\u001a:G]V\u0011\u0011Q\u0006\t\u0006Y\u0005m\u0011q\u0006\t\u0005_\u0002\t\t\u0004E\u0002-\u0003gI1!!\u000e.\u0005\u0011auN\\4\u000211|gnZ,sSR\f'\r\\3D_:4XM\u001d;fe\u001as\u0007%A\re_V\u0014G.Z,sSR\f'\r\\3D_:4XM\u001d;fe\u001asWCAA\u001f!\u0015a\u00131DA !\u0011y\u0007!!\u0011\u0011\u00071\n\u0019%C\u0002\u0002F5\u0012a\u0001R8vE2,\u0017A\u00073pk\ndWm\u0016:ji\u0006\u0014G.Z\"p]Z,'\u000f^3s\r:\u0004\u0013\u0001\u00074m_\u0006$xK]5uC\ndWmQ8om\u0016\u0014H/\u001a:G]V\u0011\u0011Q\n\t\u0006Y\u0005m\u0011q\n\t\u0005_\u0002\t\t\u0006E\u0002-\u0003'J1!!\u0016.\u0005\u00151En\\1u\u0003e1Gn\\1u/JLG/\u00192mK\u000e{gN^3si\u0016\u0014hI\u001c\u0011\u00025\t|w\u000e\\3b]^\u0013\u0018\u000e^1cY\u0016\u001cuN\u001c<feR,'O\u00128\u0016\u0005\u0005u\u0003#\u0002\u0017\u0002\u001c\u0005}\u0003\u0003B8\u0001\u0003C\u00022\u0001LA2\u0013\r\t)'\f\u0002\b\u0005>|G.Z1o\u0003m\u0011wn\u001c7fC:<&/\u001b;bE2,7i\u001c8wKJ$XM\u001d$oA\u0005A\"-\u001f;fg^\u0013\u0018\u000e^1cY\u0016\u001cuN\u001c<feR,'O\u00128\u0016\u0005\u00055\u0004#\u0002\u0017\u0002\u001c\u0005=\u0004\u0003B8\u0001\u0003c\u0002R\u0001LA:\u0003oJ1!!\u001e.\u0005\u0015\t%O]1z!\ra\u0013\u0011P\u0005\u0004\u0003wj#\u0001\u0002\"zi\u0016\f\u0011DY=uKN<&/\u001b;bE2,7i\u001c8wKJ$XM\u001d$oA\u0005I2\u000f\u001e:j]\u001e<&/\u001b;bE2,7i\u001c8wKJ$XM\u001d$o+\t\t\u0019\tE\u0003-\u00037\t)\t\u0005\u0003p\u0001\u0005\u001d\u0005c\u0001*\u0002\n&\u0019\u00111R.\u0003\rM#(/\u001b8h\u0003i\u0019HO]5oO^\u0013\u0018\u000e^1cY\u0016\u001cuN\u001c<feR,'O\u00128!\u0003m9(/\u001b;bE2,wK]5uC\ndWmQ8om\u0016\u0014H/\u001a:G]V!\u00111SAN)\u0011\t)*!(\u0011\u000b1\nY\"a&\u0011\t=\u0004\u0011\u0011\u0014\t\u0004\r\u0006mE!\u0002%\u0019\u0005\u0004\u0001\u0007\"CAP1\u0005\u0005\t9AAQ\u0003-)g/\u001b3f]\u000e,G%M\u001d\u0011\t\u0001\u001b\u0015\u0011T\u0001\u0015S:$xK]5uC\ndWmQ8om\u0016\u0014H/\u001a:\u0015\u0005\u0005}\u0011!\u00067p]\u001e<&/\u001b;bE2,7i\u001c8wKJ$XM\u001d\u000b\u0003\u0003_\tq\u0003Z8vE2,wK]5uC\ndWmQ8om\u0016\u0014H/\u001a:\u0015\u0005\u0005}\u0012A\u00064m_\u0006$xK]5uC\ndWmQ8om\u0016\u0014H/\u001a:\u0015\u0005\u0005=\u0013\u0001\u00072p_2,\u0017M\\,sSR\f'\r\\3D_:4XM\u001d;feR\u0011\u0011qL\u0001\u0017Ef$Xm],sSR\f'\r\\3D_:4XM\u001d;feR\u0011\u0011qN\u0001\u0018gR\u0014\u0018N\\4Xe&$\u0018M\u00197f\u0007>tg/\u001a:uKJ$\"!!\"\u00023]\u0014\u0018\u000e^1cY\u0016<&/\u001b;bE2,7i\u001c8wKJ$XM]\u000b\u0005\u0003\u0007\fI\r\u0006\u0002\u0002FB!q\u000eAAd!\r1\u0015\u0011\u001a\u0003\u0006\u0011\u0002\u0012\r\u0001Y\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u001f\u0004B!!5\u0002X6\u0011\u00111\u001b\u0006\u0004\u0003+,\u0014\u0001\u00027b]\u001eLA!!7\u0002T\n1qJ\u00196fGR\u0004"
)
public class WritableConverter implements Serializable {
   private final Function1 writableClass;
   private final Function1 convert;

   public static WritableConverter writableWritableConverter() {
      return WritableConverter$.MODULE$.writableWritableConverter();
   }

   public static WritableConverter stringWritableConverter() {
      return WritableConverter$.MODULE$.stringWritableConverter();
   }

   public static WritableConverter bytesWritableConverter() {
      return WritableConverter$.MODULE$.bytesWritableConverter();
   }

   public static WritableConverter booleanWritableConverter() {
      return WritableConverter$.MODULE$.booleanWritableConverter();
   }

   public static WritableConverter floatWritableConverter() {
      return WritableConverter$.MODULE$.floatWritableConverter();
   }

   public static WritableConverter doubleWritableConverter() {
      return WritableConverter$.MODULE$.doubleWritableConverter();
   }

   public static WritableConverter longWritableConverter() {
      return WritableConverter$.MODULE$.longWritableConverter();
   }

   public static WritableConverter intWritableConverter() {
      return WritableConverter$.MODULE$.intWritableConverter();
   }

   public static Function0 writableWritableConverterFn(final ClassTag evidence$19) {
      return WritableConverter$.MODULE$.writableWritableConverterFn(evidence$19);
   }

   public static Function0 stringWritableConverterFn() {
      return WritableConverter$.MODULE$.stringWritableConverterFn();
   }

   public static Function0 bytesWritableConverterFn() {
      return WritableConverter$.MODULE$.bytesWritableConverterFn();
   }

   public static Function0 booleanWritableConverterFn() {
      return WritableConverter$.MODULE$.booleanWritableConverterFn();
   }

   public static Function0 floatWritableConverterFn() {
      return WritableConverter$.MODULE$.floatWritableConverterFn();
   }

   public static Function0 doubleWritableConverterFn() {
      return WritableConverter$.MODULE$.doubleWritableConverterFn();
   }

   public static Function0 longWritableConverterFn() {
      return WritableConverter$.MODULE$.longWritableConverterFn();
   }

   public static Function0 intWritableConverterFn() {
      return WritableConverter$.MODULE$.intWritableConverterFn();
   }

   public Function1 writableClass() {
      return this.writableClass;
   }

   public Function1 convert() {
      return this.convert;
   }

   public WritableConverter(final Function1 writableClass, final Function1 convert) {
      this.writableClass = writableClass;
      this.convert = convert;
   }
}
