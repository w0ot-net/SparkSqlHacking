package org.apache.spark.executor;

import org.apache.spark.annotation.DeveloperApi;
import scala.Enumeration;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u00059;Q!\u0004\b\t\u0002]1Q!\u0007\b\t\u0002iAQ!L\u0001\u0005\u00029*A!G\u0001\u0001_!91'\u0001b\u0001\n\u0003!\u0004BB\u001b\u0002A\u0003%q\u0006C\u00047\u0003\t\u0007I\u0011\u0001\u001b\t\r]\n\u0001\u0015!\u00030\u0011\u001dA\u0014A1A\u0005\u0002QBa!O\u0001!\u0002\u0013y\u0003b\u0002\u001e\u0002\u0005\u0004%\t\u0001\u000e\u0005\u0007w\u0005\u0001\u000b\u0011B\u0018\t\u000fq\n\u0011\u0011!C\u0005{\u0005qA)\u0019;b%\u0016\fG-T3uQ>$'BA\b\u0011\u0003!)\u00070Z2vi>\u0014(BA\t\u0013\u0003\u0015\u0019\b/\u0019:l\u0015\t\u0019B#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002+\u0005\u0019qN]4\u0004\u0001A\u0011\u0001$A\u0007\u0002\u001d\tqA)\u0019;b%\u0016\fG-T3uQ>$7cA\u0001\u001cCA\u0011AdH\u0007\u0002;)\ta$A\u0003tG\u0006d\u0017-\u0003\u0002!;\tYQI\\;nKJ\fG/[8o!\t\u0011#F\u0004\u0002$Q9\u0011AeJ\u0007\u0002K)\u0011aEF\u0001\u0007yI|w\u000e\u001e \n\u0003yI!!K\u000f\u0002\u000fA\f7m[1hK&\u00111\u0006\f\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003Su\ta\u0001P5oSRtD#A\f\u0011\u0005A\nT\"A\u0001\n\u0005Iz\"!\u0002,bYV,\u0017AB'f[>\u0014\u00180F\u00010\u0003\u001diU-\\8ss\u0002\nA\u0001R5tW\u0006)A)[:lA\u00051\u0001*\u00193p_B\fq\u0001S1e_>\u0004\b%A\u0004OKR<xN]6\u0002\u00119+Go^8sW\u0002\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\u0012A\u0010\t\u0003\u007f\u0011k\u0011\u0001\u0011\u0006\u0003\u0003\n\u000bA\u0001\\1oO*\t1)\u0001\u0003kCZ\f\u0017BA#A\u0005\u0019y%M[3di\"\u0012\u0011a\u0012\t\u0003\u0011.k\u0011!\u0013\u0006\u0003\u0015B\t!\"\u00198o_R\fG/[8o\u0013\ta\u0015J\u0001\u0007EKZ,Gn\u001c9fe\u0006\u0003\u0018\u000e\u000b\u0002\u0001\u000f\u0002"
)
public final class DataReadMethod {
   public static Enumeration.Value Network() {
      return DataReadMethod$.MODULE$.Network();
   }

   public static Enumeration.Value Hadoop() {
      return DataReadMethod$.MODULE$.Hadoop();
   }

   public static Enumeration.Value Disk() {
      return DataReadMethod$.MODULE$.Disk();
   }

   public static Enumeration.Value Memory() {
      return DataReadMethod$.MODULE$.Memory();
   }

   public static Enumeration.ValueSet ValueSet() {
      return DataReadMethod$.MODULE$.ValueSet();
   }

   public static Enumeration.ValueOrdering ValueOrdering() {
      return DataReadMethod$.MODULE$.ValueOrdering();
   }

   public static Enumeration.Value withName(final String s) {
      return DataReadMethod$.MODULE$.withName(s);
   }

   public static Enumeration.Value apply(final int x) {
      return DataReadMethod$.MODULE$.apply(x);
   }

   public static int maxId() {
      return DataReadMethod$.MODULE$.maxId();
   }

   public static Enumeration.ValueSet values() {
      return DataReadMethod$.MODULE$.values();
   }

   public static String toString() {
      return DataReadMethod$.MODULE$.toString();
   }
}
