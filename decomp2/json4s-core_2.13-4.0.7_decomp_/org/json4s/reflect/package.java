package org.json4s.reflect;

import org.json4s.Formats;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=t!B\n\u0015\u0011\u0003Yb!B\u000f\u0015\u0011\u0003q\u0002\"B\u0013\u0002\t\u00031\u0003\"B\u0014\u0002\t\u0003A\u0003\"\u0002&\u0002\t\u0003Y\u0005\u0002C,\u0002\u0005\u0004%\t\u0001\u0006-\t\re\u000b\u0001\u0015!\u0003*\u0011!Q\u0016A1A\u0005\u0002QA\u0006BB.\u0002A\u0003%\u0011\u0006\u0003\u0005]\u0003\t\u0007I\u0011\u0001\u000b^\u0011\u0019I\u0017\u0001)A\u0005=\")!.\u0001C\u0002W\"910AI\u0001\n\u0003a\bbBA\u0007\u0003\u0011\r\u0011q\u0002\u0005\n\u0003[\t\u0011\u0013!C\u0001\u0003_Aq!a\u000f\u0002\t\u0007\ti\u0004C\u0005\u0002H\u0005\t\n\u0011\"\u0001\u0002J!9\u0011QJ\u0001\u0005\u0002\u0005=\u0003\"CA5\u0003E\u0005I\u0011AA6\u0003\u001d\u0001\u0018mY6bO\u0016T!!\u0006\f\u0002\u000fI,g\r\\3di*\u0011q\u0003G\u0001\u0007UN|g\u000eN:\u000b\u0003e\t1a\u001c:h\u0007\u0001\u0001\"\u0001H\u0001\u000e\u0003Q\u0011q\u0001]1dW\u0006<Wm\u0005\u0002\u0002?A\u0011\u0001eI\u0007\u0002C)\t!%A\u0003tG\u0006d\u0017-\u0003\u0002%C\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#A\u000e\u0002\u001dM\fg-Z*j[BdWMT1nKR\u0011\u0011&\r\t\u0003U=j\u0011a\u000b\u0006\u0003Y5\nA\u0001\\1oO*\ta&\u0001\u0003kCZ\f\u0017B\u0001\u0019,\u0005\u0019\u0019FO]5oO\")!g\u0001a\u0001g\u0005)1\r\\1{uB\u0012A'\u0011\t\u0004kqzdB\u0001\u001c;!\t9\u0014%D\u00019\u0015\tI$$\u0001\u0004=e>|GOP\u0005\u0003w\u0005\na\u0001\u0015:fI\u00164\u0017BA\u001f?\u0005\u0015\u0019E.Y:t\u0015\tY\u0014\u0005\u0005\u0002A\u00032\u0001A!\u0003\"2\u0003\u0003\u0005\tQ!\u0001D\u0005\ryF%M\t\u0003\t\u001e\u0003\"\u0001I#\n\u0005\u0019\u000b#a\u0002(pi\"Lgn\u001a\t\u0003A!K!!S\u0011\u0003\u0007\u0005s\u00170A\u0006tiJL\u0007\u000fR8mY\u0006\u0014HC\u0001'O!\t)T*\u0003\u00021}!)q\n\u0002a\u0001\u0019\u0006!a.Y7fQ\t!\u0011\u000b\u0005\u0002S+6\t1K\u0003\u0002UC\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005Y\u001b&a\u0002;bS2\u0014XmY\u0001\u001f\u0007>t7\u000f\u001e:vGR|'\u000fR3gCVdGOV1mk\u0016\u0004\u0016\r\u001e;fe:,\u0012!K\u0001 \u0007>t7\u000f\u001e:vGR|'\u000fR3gCVdGOV1mk\u0016\u0004\u0016\r\u001e;fe:\u0004\u0013aD'pIVdWMR5fY\u0012t\u0015-\\3\u0002!5{G-\u001e7f\r&,G\u000e\u001a(b[\u0016\u0004\u0013\u0001D\"mCN\u001cHj\\1eKJ\u001cX#\u00010\u0011\u0007}#g-D\u0001a\u0015\t\t'-A\u0005j[6,H/\u00192mK*\u00111-I\u0001\u000bG>dG.Z2uS>t\u0017BA3a\u0005\u00191Vm\u0019;peB\u0011!fZ\u0005\u0003Q.\u00121b\u00117bgNdu.\u00193fe\u0006i1\t\\1tg2{\u0017\rZ3sg\u0002\nAc]2bY\u0006$\u0016\u0010]3EKN\u001c'/\u001b2bE2,GC\u00017z)\ti7\u000fE\u0002\u001d]BL!a\u001c\u000b\u0003)I+g\r\\3di>\u0014H)Z:de&\u0014\u0017M\u00197f!\ta\u0012/\u0003\u0002s)\tI1kY1mCRK\b/\u001a\u0005\bi.\u0001\n\u0011q\u0001v\u0003\u001d1wN]7biN\u0004\"A^<\u000e\u0003YI!\u0001\u001f\f\u0003\u000f\u0019{'/\\1ug\")!p\u0003a\u0001a\u0006\tA/\u0001\u0010tG\u0006d\u0017\rV=qK\u0012+7o\u0019:jE\u0006\u0014G.\u001a\u0013eK\u001a\fW\u000f\u001c;%eQ\u0019Q0a\u0003+\u0005Ut8&A@\u0011\t\u0005\u0005\u0011qA\u0007\u0003\u0003\u0007Q1!!\u0002T\u0003%)hn\u00195fG.,G-\u0003\u0003\u0002\n\u0005\r!!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\")!\u0010\u0004a\u0001a\u0006\u00012\r\\1tg\u0012+7o\u0019:jE\u0006\u0014G.\u001a\u000b\u0005\u0003#\t\t\u0003\u0006\u0003\u0002\u0014\u0005}\u0001\u0003\u0002\u000fo\u0003+\u0001D!a\u0006\u0002\u001cA!Q\u0007PA\r!\r\u0001\u00151\u0004\u0003\u000b\u0003;i\u0011\u0011!A\u0001\u0006\u0003\u0019%aA0%i!9A/\u0004I\u0001\u0002\b)\bB\u0002>\u000e\u0001\u0004\t\u0019\u0003\r\u0003\u0002&\u0005%\u0002\u0003B\u001b=\u0003O\u00012\u0001QA\u0015\t-\tY#!\t\u0002\u0002\u0003\u0005)\u0011A\"\u0003\u0007}#3'\u0001\u000edY\u0006\u001c8\u000fR3tGJL'-\u00192mK\u0012\"WMZ1vYR$#\u0007F\u0002~\u0003cAaA\u001f\bA\u0002\u0005M\u0002\u0007BA\u001b\u0003s\u0001B!\u000e\u001f\u00028A\u0019\u0001)!\u000f\u0005\u0017\u0005-\u0012\u0011GA\u0001\u0002\u0003\u0015\taQ\u0001\u0012gR\u0014\u0018N\\4EKN\u001c'/\u001b2bE2,G\u0003BA \u0003\u000b\"B!!\u0011\u0002DA\u0019AD\u001c'\t\u000fQ|\u0001\u0013!a\u0002k\")!p\u0004a\u0001\u0019\u0006Y2\u000f\u001e:j]\u001e$Um]2sS\n\f'\r\\3%I\u00164\u0017-\u001e7uII\"2!`A&\u0011\u0015Q\b\u00031\u0001M\u0003\u00111\u0017-\u001b7\u0015\u000b\u0011\u000b\t&!\u0016\t\r\u0005M\u0013\u00031\u0001M\u0003\ri7o\u001a\u0005\n\u0003/\n\u0002\u0013!a\u0001\u00033\nQaY1vg\u0016\u0004B!a\u0017\u0002d9!\u0011QLA1\u001d\r9\u0014qL\u0005\u0002E%\u00111#I\u0005\u0005\u0003K\n9GA\u0005Fq\u000e,\u0007\u000f^5p]*\u00111#I\u0001\u000fM\u0006LG\u000e\n3fM\u0006,H\u000e\u001e\u00133+\t\tiGK\u0002\u0002Zy\u0004"
)
public final class package {
   public static Exception fail$default$2() {
      return package$.MODULE$.fail$default$2();
   }

   public static Nothing fail(final String msg, final Exception cause) {
      return package$.MODULE$.fail(msg, cause);
   }

   public static Formats stringDescribable$default$2(final String t) {
      return package$.MODULE$.stringDescribable$default$2(t);
   }

   public static ReflectorDescribable stringDescribable(final String t, final Formats formats) {
      return package$.MODULE$.stringDescribable(t, formats);
   }

   public static Formats classDescribable$default$2(final Class t) {
      return package$.MODULE$.classDescribable$default$2(t);
   }

   public static ReflectorDescribable classDescribable(final Class t, final Formats formats) {
      return package$.MODULE$.classDescribable(t, formats);
   }

   public static Formats scalaTypeDescribable$default$2(final ScalaType t) {
      return package$.MODULE$.scalaTypeDescribable$default$2(t);
   }

   public static ReflectorDescribable scalaTypeDescribable(final ScalaType t, final Formats formats) {
      return package$.MODULE$.scalaTypeDescribable(t, formats);
   }

   public static String stripDollar(final String name) {
      return package$.MODULE$.stripDollar(name);
   }

   public static String safeSimpleName(final Class clazz) {
      return package$.MODULE$.safeSimpleName(clazz);
   }
}
