package org.apache.spark.sql.catalyst.analysis;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!2Aa\u0001\u0003\u0001#!Aa\u0003\u0001B\u0001B\u0003%q\u0003C\u0003%\u0001\u0011\u0005QE\u0001\u0010ECR\f'-Y:f\u00032\u0014X-\u00193z\u000bbL7\u000f^:Fq\u000e,\u0007\u000f^5p]*\u0011QAB\u0001\tC:\fG._:jg*\u0011q\u0001C\u0001\tG\u0006$\u0018\r\\=ti*\u0011\u0011BC\u0001\u0004gFd'BA\u0006\r\u0003\u0015\u0019\b/\u0019:l\u0015\tia\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001f\u0005\u0019qN]4\u0004\u0001M\u0011\u0001A\u0005\t\u0003'Qi\u0011\u0001B\u0005\u0003+\u0011\u0011qDT1nKN\u0004\u0018mY3BYJ,\u0017\rZ=Fq&\u001cHo]#yG\u0016\u0004H/[8o\u0003\t!'\r\u0005\u0002\u0019C9\u0011\u0011d\b\t\u00035ui\u0011a\u0007\u0006\u00039A\ta\u0001\u0010:p_Rt$\"\u0001\u0010\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0001j\u0012A\u0002)sK\u0012,g-\u0003\u0002#G\t11\u000b\u001e:j]\u001eT!\u0001I\u000f\u0002\rqJg.\u001b;?)\t1s\u0005\u0005\u0002\u0014\u0001!)aC\u0001a\u0001/\u0001"
)
public class DatabaseAlreadyExistsException extends NamespaceAlreadyExistsException {
   public DatabaseAlreadyExistsException(final String db) {
      super((String[])((Object[])(new String[]{db})));
   }
}
