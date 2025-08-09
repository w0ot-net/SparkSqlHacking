package org.apache.spark;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-;aAE\n\t\u0002MIbAB\u000e\u0014\u0011\u0003\u0019B\u0004C\u0003$\u0003\u0011\u0005Q\u0005\u0003\u0006'\u0003A\u0005\t1!Q\u0001\n\u001dBqAM\u0001C\u0002\u0013\u00051\u0007\u0003\u0004?\u0003\u0001\u0006I\u0001\u000e\u0005\b\u007f\u0005\u0011\r\u0011\"\u00014\u0011\u0019\u0001\u0015\u0001)A\u0005i!9\u0011)\u0001b\u0001\n\u0003\u0019\u0004B\u0002\"\u0002A\u0003%A\u0007C\u0004D\u0003\t\u0007I\u0011A\u001a\t\r\u0011\u000b\u0001\u0015!\u00035\u0011\u001d)\u0015A1A\u0005\u0002MBaAR\u0001!\u0002\u0013!\u0004bB$\u0002\u0005\u0004%\ta\r\u0005\u0007\u0011\u0006\u0001\u000b\u0011\u0002\u001b\t\u000f%\u000b!\u0019!C\u0001g!1!*\u0001Q\u0001\nQ\nab\u00159be.\u0014U/\u001b7e\u0013:4wN\u0003\u0002\u0015+\u0005)1\u000f]1sW*\u0011acF\u0001\u0007CB\f7\r[3\u000b\u0003a\t1a\u001c:h!\tQ\u0012!D\u0001\u0014\u00059\u0019\u0006/\u0019:l\u0005VLG\u000eZ%oM>\u001c\"!A\u000f\u0011\u0005y\tS\"A\u0010\u000b\u0003\u0001\nQa]2bY\u0006L!AI\u0010\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}\r\u0001A#A\r\u0002\u0007a$\u0013\u0007E\u0005\u001fQ)R#F\u000b\u0016+U%\u0011\u0011f\b\u0002\u0007)V\u0004H.Z\u001c\u0011\u0005-\u0002T\"\u0001\u0017\u000b\u00055r\u0013\u0001\u00027b]\u001eT\u0011aL\u0001\u0005U\u00064\u0018-\u0003\u00022Y\t11\u000b\u001e:j]\u001e\fQb\u001d9be.|f/\u001a:tS>tW#\u0001\u001b\u0011\u0005UbdB\u0001\u001c;!\t9t$D\u00019\u0015\tID%\u0001\u0004=e>|GOP\u0005\u0003w}\ta\u0001\u0015:fI\u00164\u0017BA\u0019>\u0015\tYt$\u0001\bta\u0006\u00148n\u0018<feNLwN\u001c\u0011\u0002\u0019M\u0004\u0018M]6`EJ\fgn\u00195\u0002\u001bM\u0004\u0018M]6`EJ\fgn\u00195!\u00039\u0019\b/\u0019:l?J,g/[:j_:\fqb\u001d9be.|&/\u001a<jg&|g\u000eI\u0001\u0011gB\f'o[0ck&dGmX;tKJ\f\u0011c\u001d9be.|&-^5mI~+8/\u001a:!\u00039\u0019\b/\u0019:l?J,\u0007o\\0ve2\fqb\u001d9be.|&/\u001a9p?V\u0014H\u000eI\u0001\u0011gB\f'o[0ck&dGm\u00183bi\u0016\f\u0011c\u001d9be.|&-^5mI~#\u0017\r^3!\u00039\u0019\b/\u0019:l?\u0012|7m\u0018:p_R\fqb\u001d9be.|Fm\\2`e>|G\u000f\t"
)
public final class SparkBuildInfo {
   public static String spark_doc_root() {
      return SparkBuildInfo$.MODULE$.spark_doc_root();
   }

   public static String spark_build_date() {
      return SparkBuildInfo$.MODULE$.spark_build_date();
   }

   public static String spark_repo_url() {
      return SparkBuildInfo$.MODULE$.spark_repo_url();
   }

   public static String spark_build_user() {
      return SparkBuildInfo$.MODULE$.spark_build_user();
   }

   public static String spark_revision() {
      return SparkBuildInfo$.MODULE$.spark_revision();
   }

   public static String spark_branch() {
      return SparkBuildInfo$.MODULE$.spark_branch();
   }

   public static String spark_version() {
      return SparkBuildInfo$.MODULE$.spark_version();
   }
}
