package org.apache.spark.util;

import org.apache.spark.SparkException;
import scala.concurrent.Awaitable;
import scala.concurrent.duration.Duration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005);a!\u0002\u0004\t\u0002!qaA\u0002\t\u0007\u0011\u0003A\u0011\u0003C\u0003\u0019\u0003\u0011\u0005!\u0004C\u0003\u001c\u0003\u0011\u0005A\u0004C\u0003C\u0003\u0011\u00051)\u0001\tTa\u0006\u00148\u000e\u00165sK\u0006$W\u000b^5mg*\u0011q\u0001C\u0001\u0005kRLGN\u0003\u0002\n\u0015\u0005)1\u000f]1sW*\u00111\u0002D\u0001\u0007CB\f7\r[3\u000b\u00035\t1a\u001c:h!\ty\u0011!D\u0001\u0007\u0005A\u0019\u0006/\u0019:l)\"\u0014X-\u00193Vi&d7o\u0005\u0002\u0002%A\u00111CF\u0007\u0002))\tQ#A\u0003tG\u0006d\u0017-\u0003\u0002\u0018)\t1\u0011I\\=SK\u001a\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002\u001d\u0005Y\u0011m^1jiJ+7/\u001e7u+\ti\u0002\u0005F\u0002\u001fSE\u0002\"a\b\u0011\r\u0001\u0011)\u0011e\u0001b\u0001E\t\tA+\u0005\u0002$MA\u00111\u0003J\u0005\u0003KQ\u0011qAT8uQ&tw\r\u0005\u0002\u0014O%\u0011\u0001\u0006\u0006\u0002\u0004\u0003:L\b\"\u0002\u0016\u0004\u0001\u0004Y\u0013!C1xC&$\u0018M\u00197f!\rasFH\u0007\u0002[)\u0011a\u0006F\u0001\u000bG>t7-\u001e:sK:$\u0018B\u0001\u0019.\u0005%\tu/Y5uC\ndW\rC\u00033\u0007\u0001\u00071'\u0001\u0004bi6{7\u000f\u001e\t\u0003i]j\u0011!\u000e\u0006\u0003m5\n\u0001\u0002Z;sCRLwN\\\u0005\u0003qU\u0012\u0001\u0002R;sCRLwN\u001c\u0015\u0004\u0007i\n\u0005cA\n<{%\u0011A\b\u0006\u0002\u0007i\"\u0014xn^:\u0011\u0005yzT\"\u0001\u0005\n\u0005\u0001C!AD*qCJ\\W\t_2faRLwN\\\u0012\u0002{\u0005)\u0013m^1jiJ+7/\u001e7u\u001d>\u001c\u0006/\u0019:l\u000bb\u001cW\r\u001d;j_:\u001cuN\u001c<feNLwN\\\u000b\u0003\t\u001a#2!R$J!\tyb\tB\u0003\"\t\t\u0007!\u0005C\u0003+\t\u0001\u0007\u0001\nE\u0002-_\u0015CQA\r\u0003A\u0002M\u0002"
)
public final class SparkThreadUtils {
   public static Object awaitResultNoSparkExceptionConversion(final Awaitable awaitable, final Duration atMost) {
      return SparkThreadUtils$.MODULE$.awaitResultNoSparkExceptionConversion(awaitable, atMost);
   }

   public static Object awaitResult(final Awaitable awaitable, final Duration atMost) throws SparkException {
      return SparkThreadUtils$.MODULE$.awaitResult(awaitable, atMost);
   }
}
