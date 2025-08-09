package org.apache.spark.sql.hive.thriftserver;

import java.io.IOException;
import java.util.concurrent.RejectedExecutionException;
import org.apache.hive.service.cli.OperationType;
import scala.Enumeration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m<QAC\u0006\t\u0002a1QAG\u0006\t\u0002mAQAI\u0001\u0005\u0002\rBQ\u0001J\u0001\u0005\u0002\u0015BQAP\u0001\u0005\u0002}BQ!T\u0001\u0005\u00029CQaW\u0001\u0005\u0002qCQAX\u0001\u0005\u0002}CQ!Y\u0001\u0005\u0002\tDQA^\u0001\u0005\u0002]\fa\u0003S5wKRC'/\u001b4u'\u0016\u0014h/\u001a:FeJ|'o\u001d\u0006\u0003\u00195\tA\u0002\u001e5sS\u001a$8/\u001a:wKJT!AD\b\u0002\t!Lg/\u001a\u0006\u0003!E\t1a]9m\u0015\t\u00112#A\u0003ta\u0006\u00148N\u0003\u0002\u0015+\u00051\u0011\r]1dQ\u0016T\u0011AF\u0001\u0004_J<7\u0001\u0001\t\u00033\u0005i\u0011a\u0003\u0002\u0017\u0011&4X\r\u00165sS\u001a$8+\u001a:wKJ,%O]8sgN\u0011\u0011\u0001\b\t\u0003;\u0001j\u0011A\b\u0006\u0002?\u0005)1oY1mC&\u0011\u0011E\b\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005A\u0012A\u0007;bg.,\u00050Z2vi&|gNU3kK\u000e$X\rZ#se>\u0014HC\u0001\u00143!\t9sF\u0004\u0002)[9\u0011\u0011\u0006L\u0007\u0002U)\u00111fF\u0001\u0007yI|w\u000e\u001e \n\u0003}I!A\f\u0010\u0002\u000fA\f7m[1hK&\u0011\u0001'\r\u0002\n)\"\u0014xn^1cY\u0016T!A\f\u0010\t\u000bM\u001a\u0001\u0019\u0001\u001b\u0002\u0011I,'.Z2uK\u0012\u0004\"!\u000e\u001f\u000e\u0003YR!a\u000e\u001d\u0002\u0015\r|gnY;se\u0016tGO\u0003\u0002:u\u0005!Q\u000f^5m\u0015\u0005Y\u0014\u0001\u00026bm\u0006L!!\u0010\u001c\u00035I+'.Z2uK\u0012,\u00050Z2vi&|g.\u0012=dKB$\u0018n\u001c8\u0002#I,hN\\5oOF+XM]=FeJ|'\u000fF\u0002'\u0001\nCQ!\u0011\u0003A\u0002\u0019\n\u0011!\u001a\u0005\u0006\u0007\u0012\u0001\r\u0001R\u0001\u0007M>\u0014X.\u0019;\u0011\u0005\u0015KeB\u0001$H\u001b\u0005\t\u0012B\u0001%\u0012\u0003I)%O]8s\u001b\u0016\u001c8/Y4f\r>\u0014X.\u0019;\n\u0005)[%!\u0002,bYV,\u0017B\u0001'\u001f\u0005-)e.^7fe\u0006$\u0018n\u001c8\u0002%!Lg/Z(qKJ\fG/\u001b8h\u000bJ\u0014xN\u001d\u000b\u0004M=S\u0006\"\u0002)\u0006\u0001\u0004\t\u0016!D8qKJ\fG/[8o)f\u0004X\r\u0005\u0002S16\t1K\u0003\u0002U+\u0006\u00191\r\\5\u000b\u0005Y;\u0016aB:feZL7-\u001a\u0006\u0003\u001dMI!!W*\u0003\u001b=\u0003XM]1uS>tG+\u001f9f\u0011\u0015\tU\u00011\u0001'\u0003m1\u0017-\u001b7fIR{w\n]3o\u001d\u0016<8+Z:tS>tWI\u001d:peR\u0011a%\u0018\u0005\u0006\u0003\u001a\u0001\rAJ\u0001\u001bG\u0006tgn\u001c;M_\u001eLg\u000eV8LKJ\u0014WM]8t\u000bJ\u0014xN\u001d\u000b\u0003M\u0001DQ!Q\u0004A\u0002\u0019\n\u0001dY1o]>$Hj\\4j]R{7\u000b\u001d8fO>,%O]8s)\u001113-\\8\t\u000b\u0011D\u0001\u0019A3\u0002\u0013A\u0014\u0018N\\2ja\u0006d\u0007C\u00014k\u001d\t9\u0007\u000e\u0005\u0002*=%\u0011\u0011NH\u0001\u0007!J,G-\u001a4\n\u0005-d'AB*ue&twM\u0003\u0002j=!)a\u000e\u0003a\u0001K\u0006Q1.Z=UC\n4\u0015\u000e\\3\t\u000b\u0005C\u0001\u0019\u00019\u0011\u0005E$X\"\u0001:\u000b\u0005MT\u0014AA5p\u0013\t)(OA\u0006J\u001f\u0016C8-\u001a9uS>t\u0017!\u00074bS2,G\rV8Ti\u0006\u0014HoU3sm&\u001cW-\u0012:s_J$2A\n={\u0011\u0015I\u0018\u00021\u0001f\u0003-\u0019XM\u001d<jG\u0016t\u0015-\\3\t\u000b\u0005K\u0001\u0019\u0001\u0014"
)
public final class HiveThriftServerErrors {
   public static Throwable failedToStartServiceError(final String serviceName, final Throwable e) {
      return HiveThriftServerErrors$.MODULE$.failedToStartServiceError(serviceName, e);
   }

   public static Throwable cannotLoginToSpnegoError(final String principal, final String keyTabFile, final IOException e) {
      return HiveThriftServerErrors$.MODULE$.cannotLoginToSpnegoError(principal, keyTabFile, e);
   }

   public static Throwable cannotLoginToKerberosError(final Throwable e) {
      return HiveThriftServerErrors$.MODULE$.cannotLoginToKerberosError(e);
   }

   public static Throwable failedToOpenNewSessionError(final Throwable e) {
      return HiveThriftServerErrors$.MODULE$.failedToOpenNewSessionError(e);
   }

   public static Throwable hiveOperatingError(final OperationType operationType, final Throwable e) {
      return HiveThriftServerErrors$.MODULE$.hiveOperatingError(operationType, e);
   }

   public static Throwable runningQueryError(final Throwable e, final Enumeration.Value format) {
      return HiveThriftServerErrors$.MODULE$.runningQueryError(e, format);
   }

   public static Throwable taskExecutionRejectedError(final RejectedExecutionException rejected) {
      return HiveThriftServerErrors$.MODULE$.taskExecutionRejectedError(rejected);
   }
}
