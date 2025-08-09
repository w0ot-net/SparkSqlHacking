package org.apache.spark.sql.catalyst.util;

import org.apache.spark.internal.Logging;
import scala.None;
import scala.Option;
import scala.StringContext;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%s!\u0002\u0007\u000e\u0011\u0003Qb!\u0002\u000f\u000e\u0011\u0003i\u0002\"\u0002\u0016\u0002\t\u0003Y\u0003b\u0002\u0017\u0002\u0005\u0004%I!\f\u0005\u0007s\u0005\u0001\u000b\u0011\u0002\u0018\t\u000bi\nA\u0011A\u001e\t\u000fE\f\u0011\u0013!C\u0001e\"1!(\u0001C\u0001\u0003\u000bA!\"!\u0006\u0002\u0011\u000b\u0007IQBA\f\u0011\u001d\t\t#\u0001C\u0001\u0003GAq!!\u000e\u0002\t\u0003\t9\u0004C\u0004\u00026\u0005!\t!a\u0011\u0002!M\u0003\u0018M]6TiJLgnZ+uS2\u001c(B\u0001\b\u0010\u0003\u0011)H/\u001b7\u000b\u0005A\t\u0012\u0001C2bi\u0006d\u0017p\u001d;\u000b\u0005I\u0019\u0012aA:rY*\u0011A#F\u0001\u0006gB\f'o\u001b\u0006\u0003-]\ta!\u00199bG\",'\"\u0001\r\u0002\u0007=\u0014xm\u0001\u0001\u0011\u0005m\tQ\"A\u0007\u0003!M\u0003\u0018M]6TiJLgnZ+uS2\u001c8cA\u0001\u001fIA\u0011qDI\u0007\u0002A)\t\u0011%A\u0003tG\u0006d\u0017-\u0003\u0002$A\t1\u0011I\\=SK\u001a\u0004\"!\n\u0015\u000e\u0003\u0019R!aJ\n\u0002\u0011%tG/\u001a:oC2L!!\u000b\u0014\u0003\u000f1{wmZ5oO\u00061A(\u001b8jiz\"\u0012AG\u0001\u0019iJ,hnY1uS>tw+\u0019:oS:<\u0007K]5oi\u0016$W#\u0001\u0018\u0011\u0005=:T\"\u0001\u0019\u000b\u0005E\u0012\u0014AB1u_6L7M\u0003\u00024i\u0005Q1m\u001c8dkJ\u0014XM\u001c;\u000b\u00059)$\"\u0001\u001c\u0002\t)\fg/Y\u0005\u0003qA\u0012Q\"\u0011;p[&\u001c'i\\8mK\u0006t\u0017!\u0007;sk:\u001c\u0017\r^5p]^\u000b'O\\5oOB\u0013\u0018N\u001c;fI\u0002\nq\u0002\u001e:v]\u000e\fG/\u001a3TiJLgnZ\u000b\u0003yU#r!\u0010%_A\n$\u0017\u000e\u0005\u0002?\u000b:\u0011qh\u0011\t\u0003\u0001\u0002j\u0011!\u0011\u0006\u0003\u0005f\ta\u0001\u0010:p_Rt\u0014B\u0001#!\u0003\u0019\u0001&/\u001a3fM&\u0011ai\u0012\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0011\u0003\u0003\"B%\u0006\u0001\u0004Q\u0015aA:fcB\u00191\nU*\u000f\u00051seB\u0001!N\u0013\u0005\t\u0013BA(!\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0015*\u0003\u0007M+\u0017O\u0003\u0002PAA\u0011A+\u0016\u0007\u0001\t\u00151VA1\u0001X\u0005\u0005!\u0016C\u0001-\\!\ty\u0012,\u0003\u0002[A\t9aj\u001c;iS:<\u0007CA\u0010]\u0013\ti\u0006EA\u0002B]fDQaX\u0003A\u0002u\nQa\u001d;beRDQ!Y\u0003A\u0002u\n1a]3q\u0011\u0015\u0019W\u00011\u0001>\u0003\r)g\u000e\u001a\u0005\u0006K\u0016\u0001\rAZ\u0001\n[\u0006Dh)[3mIN\u0004\"aH4\n\u0005!\u0004#aA%oi\"9!.\u0002I\u0001\u0002\u0004Y\u0017AD2vgR|W\u000eV8TiJLgn\u001a\t\u0004?1t\u0017BA7!\u0005\u0019y\u0005\u000f^5p]B!qd\\*>\u0013\t\u0001\bEA\u0005Gk:\u001cG/[8oc\u0005IBO];oG\u0006$X\rZ*ue&tw\r\n3fM\u0006,H\u000e\u001e\u00137+\r\u0019\u00181A\u000b\u0002i*\u0012Q\u000f\u001f\b\u0003?YL!a\u001e\u0011\u0002\t9{g.Z\u0016\u0002sB\u0011!p`\u0007\u0002w*\u0011A0`\u0001\nk:\u001c\u0007.Z2lK\u0012T!A \u0011\u0002\u0015\u0005tgn\u001c;bi&|g.C\u0002\u0002\u0002m\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\t\u00151fA1\u0001X+\u0011\t9!a\u0004\u0015\u000fu\nI!!\u0005\u0002\u0014!1\u0011j\u0002a\u0001\u0003\u0017\u0001Ba\u0013)\u0002\u000eA\u0019A+a\u0004\u0005\u000bY;!\u0019A,\t\u000b\u0005<\u0001\u0019A\u001f\t\u000b\u0015<\u0001\u0019\u00014\u0002;M\u0003\u0016iQ#`\t\u0016c\u0015*T%U\u000b\u0012{V\u000b\u0015)F%\u000e\u000b5+R0I\u000bb+\"!!\u0007\u0011\t\u0005m\u0011QD\u0007\u0002i%\u0019\u0011q\u0004\u001b\u0003\u0013!+\u0007PR8s[\u0006$\u0018\u0001D4fi\"+\u0007p\u0015;sS:<GcA\u001f\u0002&!9\u0011qE\u0005A\u0002\u0005%\u0012!\u00022zi\u0016\u001c\b#B\u0010\u0002,\u0005=\u0012bAA\u0017A\t)\u0011I\u001d:bsB\u0019q$!\r\n\u0007\u0005M\u0002E\u0001\u0003CsR,\u0017AC:jI\u0016\u0014\u0015pU5eKR1\u0011\u0011HA\u001e\u0003\u007f\u00012a\u0013)>\u0011\u0019\tiD\u0003a\u0001{\u0005!A.\u001a4u\u0011\u0019\t\tE\u0003a\u0001{\u0005)!/[4iiR1\u0011\u0011HA#\u0003\u000fBq!!\u0010\f\u0001\u0004\tI\u0004C\u0004\u0002B-\u0001\r!!\u000f"
)
public final class SparkStringUtils {
   public static Seq sideBySide(final Seq left, final Seq right) {
      return SparkStringUtils$.MODULE$.sideBySide(left, right);
   }

   public static Seq sideBySide(final String left, final String right) {
      return SparkStringUtils$.MODULE$.sideBySide(left, right);
   }

   public static String getHexString(final byte[] bytes) {
      return SparkStringUtils$.MODULE$.getHexString(bytes);
   }

   public static String truncatedString(final Seq seq, final String sep, final int maxFields) {
      return SparkStringUtils$.MODULE$.truncatedString(seq, sep, maxFields);
   }

   public static None truncatedString$default$6() {
      return SparkStringUtils$.MODULE$.truncatedString$default$6();
   }

   public static String truncatedString(final Seq seq, final String start, final String sep, final String end, final int maxFields, final Option customToString) {
      return SparkStringUtils$.MODULE$.truncatedString(seq, start, sep, end, maxFields, customToString);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return SparkStringUtils$.MODULE$.LogStringContext(sc);
   }
}
