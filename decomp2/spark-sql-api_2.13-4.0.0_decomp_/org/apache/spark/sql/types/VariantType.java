package org.apache.spark.sql.types;

import org.apache.spark.annotation.Unstable;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@Unstable
@ScalaSignature(
   bytes = "\u0006\u0005I4Aa\u0004\t\u00017!)\u0001\u0005\u0001C\u0005C!)1\u0005\u0001C!I!11\u0006\u0001C!)1:Q\u0001\u000e\t\t\u0002V2Qa\u0004\t\t\u0002ZBQ\u0001I\u0003\u0005\u0002\u0019CqaR\u0003\u0002\u0002\u0013\u0005\u0003\nC\u0004R\u000b\u0005\u0005I\u0011\u0001\u0013\t\u000fI+\u0011\u0011!C\u0001'\"9\u0011,BA\u0001\n\u0003R\u0006bB1\u0006\u0003\u0003%\tA\u0019\u0005\bO\u0016\t\t\u0011\"\u0011i\u0011\u001dIW!!A\u0005B)Dqa[\u0003\u0002\u0002\u0013%ANA\u0006WCJL\u0017M\u001c;UsB,'BA\t\u0013\u0003\u0015!\u0018\u0010]3t\u0015\t\u0019B#A\u0002tc2T!!\u0006\f\u0002\u000bM\u0004\u0018M]6\u000b\u0005]A\u0012AB1qC\u000eDWMC\u0001\u001a\u0003\ry'oZ\u0002\u0001'\t\u0001A\u0004\u0005\u0002\u001e=5\t\u0001#\u0003\u0002 !\tQ\u0011\t^8nS\u000e$\u0016\u0010]3\u0002\rqJg.\u001b;?)\u0005\u0011\u0003CA\u000f\u0001\u0003-!WMZ1vYR\u001c\u0016N_3\u0016\u0003\u0015\u0002\"AJ\u0015\u000e\u0003\u001dR\u0011\u0001K\u0001\u0006g\u000e\fG.Y\u0005\u0003U\u001d\u00121!\u00138u\u0003)\t7OT;mY\u0006\u0014G.Z\u000b\u0002E!\u0012\u0001A\f\t\u0003_Ij\u0011\u0001\r\u0006\u0003cQ\t!\"\u00198o_R\fG/[8o\u0013\t\u0019\u0004G\u0001\u0005V]N$\u0018M\u00197f\u0003-1\u0016M]5b]R$\u0016\u0010]3\u0011\u0005u)1\u0003B\u0003#oi\u0002\"A\n\u001d\n\u0005e:#a\u0002)s_\u0012,8\r\u001e\t\u0003w\rs!\u0001P!\u000f\u0005u\u0002U\"\u0001 \u000b\u0005}R\u0012A\u0002\u001fs_>$h(C\u0001)\u0013\t\u0011u%A\u0004qC\u000e\\\u0017mZ3\n\u0005\u0011+%\u0001D*fe&\fG.\u001b>bE2,'B\u0001\"()\u0005)\u0014!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001J!\tQu*D\u0001L\u0015\taU*\u0001\u0003mC:<'\"\u0001(\u0002\t)\fg/Y\u0005\u0003!.\u0013aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003)^\u0003\"AJ+\n\u0005Y;#aA!os\"9\u0001,CA\u0001\u0002\u0004)\u0013a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001\\!\rav\fV\u0007\u0002;*\u0011alJ\u0001\u000bG>dG.Z2uS>t\u0017B\u00011^\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0005\r4\u0007C\u0001\u0014e\u0013\t)wEA\u0004C_>dW-\u00198\t\u000fa[\u0011\u0011!a\u0001)\u0006A\u0001.Y:i\u0007>$W\rF\u0001&\u0003!!xn\u0015;sS:<G#A%\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u00035\u0004\"A\u00138\n\u0005=\\%AB(cU\u0016\u001cG\u000f\u000b\u0002\u0006]!\u0012AA\f"
)
public class VariantType extends AtomicType {
   public static boolean canEqual(final Object x$1) {
      return VariantType$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return VariantType$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return VariantType$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return VariantType$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return VariantType$.MODULE$.productPrefix();
   }

   public static Iterator productElementNames() {
      return VariantType$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return VariantType$.MODULE$.productElementName(n);
   }

   public int defaultSize() {
      return 2048;
   }

   public VariantType asNullable() {
      return this;
   }
}
