package org.apache.spark.sql.types;

import org.apache.spark.annotation.Stable;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005I4Aa\u0004\t\u00017!)\u0001\u0005\u0001C\u0005C!)1\u0005\u0001C!I!11\u0006\u0001C!)1:Q\u0001\u000e\t\t\u0002V2Qa\u0004\t\t\u0002ZBQ\u0001I\u0003\u0005\u0002\u0019CqaR\u0003\u0002\u0002\u0013\u0005\u0003\nC\u0004R\u000b\u0005\u0005I\u0011\u0001\u0013\t\u000fI+\u0011\u0011!C\u0001'\"9\u0011,BA\u0001\n\u0003R\u0006bB1\u0006\u0003\u0003%\tA\u0019\u0005\bO\u0016\t\t\u0011\"\u0011i\u0011\u001dIW!!A\u0005B)Dqa[\u0003\u0002\u0002\u0013%ANA\u0006C_>dW-\u00198UsB,'BA\t\u0013\u0003\u0015!\u0018\u0010]3t\u0015\t\u0019B#A\u0002tc2T!!\u0006\f\u0002\u000bM\u0004\u0018M]6\u000b\u0005]A\u0012AB1qC\u000eDWMC\u0001\u001a\u0003\ry'oZ\u0002\u0001'\t\u0001A\u0004\u0005\u0002\u001e=5\t\u0001#\u0003\u0002 !\tQ\u0011\t^8nS\u000e$\u0016\u0010]3\u0002\rqJg.\u001b;?)\u0005\u0011\u0003CA\u000f\u0001\u0003-!WMZ1vYR\u001c\u0016N_3\u0016\u0003\u0015\u0002\"AJ\u0015\u000e\u0003\u001dR\u0011\u0001K\u0001\u0006g\u000e\fG.Y\u0005\u0003U\u001d\u00121!\u00138u\u0003)\t7OT;mY\u0006\u0014G.Z\u000b\u0002E!\u0012\u0001A\f\t\u0003_Ij\u0011\u0001\r\u0006\u0003cQ\t!\"\u00198o_R\fG/[8o\u0013\t\u0019\u0004G\u0001\u0004Ti\u0006\u0014G.Z\u0001\f\u0005>|G.Z1o)f\u0004X\r\u0005\u0002\u001e\u000bM!QAI\u001c;!\t1\u0003(\u0003\u0002:O\t9\u0001K]8ek\u000e$\bCA\u001eD\u001d\ta\u0014I\u0004\u0002>\u00016\taH\u0003\u0002@5\u00051AH]8pizJ\u0011\u0001K\u0005\u0003\u0005\u001e\nq\u0001]1dW\u0006<W-\u0003\u0002E\u000b\na1+\u001a:jC2L'0\u00192mK*\u0011!i\n\u000b\u0002k\u0005i\u0001O]8ek\u000e$\bK]3gSb,\u0012!\u0013\t\u0003\u0015>k\u0011a\u0013\u0006\u0003\u00196\u000bA\u0001\\1oO*\ta*\u0001\u0003kCZ\f\u0017B\u0001)L\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HC\u0001+X!\t1S+\u0003\u0002WO\t\u0019\u0011I\\=\t\u000faK\u0011\u0011!a\u0001K\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012a\u0017\t\u00049~#V\"A/\u000b\u0005y;\u0013AC2pY2,7\r^5p]&\u0011\u0001-\u0018\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0002dMB\u0011a\u0005Z\u0005\u0003K\u001e\u0012qAQ8pY\u0016\fg\u000eC\u0004Y\u0017\u0005\u0005\t\u0019\u0001+\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012!J\u0001\ti>\u001cFO]5oOR\t\u0011*\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001n!\tQe.\u0003\u0002p\u0017\n1qJ\u00196fGRD#!\u0002\u0018)\u0005\u0011q\u0003"
)
public class BooleanType extends AtomicType {
   public static boolean canEqual(final Object x$1) {
      return BooleanType$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return BooleanType$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return BooleanType$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return BooleanType$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return BooleanType$.MODULE$.productPrefix();
   }

   public static Iterator productElementNames() {
      return BooleanType$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return BooleanType$.MODULE$.productElementName(n);
   }

   public int defaultSize() {
      return 1;
   }

   public BooleanType asNullable() {
      return this;
   }
}
