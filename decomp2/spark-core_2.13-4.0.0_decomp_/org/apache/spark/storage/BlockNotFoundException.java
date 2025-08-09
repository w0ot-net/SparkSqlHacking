package org.apache.spark.storage;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)2Aa\u0001\u0003\u0001\u001b!AA\u0004\u0001B\u0001B\u0003%Q\u0004C\u0003&\u0001\u0011\u0005aE\u0001\fCY>\u001c7NT8u\r>,h\u000eZ#yG\u0016\u0004H/[8o\u0015\t)a!A\u0004ti>\u0014\u0018mZ3\u000b\u0005\u001dA\u0011!B:qCJ\\'BA\u0005\u000b\u0003\u0019\t\u0007/Y2iK*\t1\"A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001\u001dA\u0011q\"\u0007\b\u0003!Yq!!\u0005\u000b\u000e\u0003IQ!a\u0005\u0007\u0002\rq\u0012xn\u001c;?\u0013\u0005)\u0012!B:dC2\f\u0017BA\f\u0019\u0003\u001d\u0001\u0018mY6bO\u0016T\u0011!F\u0005\u00035m\u0011\u0011\"\u0012=dKB$\u0018n\u001c8\u000b\u0005]A\u0012a\u00022m_\u000e\\\u0017\n\u001a\t\u0003=\tr!a\b\u0011\u0011\u0005EA\u0012BA\u0011\u0019\u0003\u0019\u0001&/\u001a3fM&\u00111\u0005\n\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0005B\u0012A\u0002\u001fj]&$h\b\u0006\u0002(SA\u0011\u0001\u0006A\u0007\u0002\t!)AD\u0001a\u0001;\u0001"
)
public class BlockNotFoundException extends Exception {
   public BlockNotFoundException(final String blockId) {
      super("Block " + blockId + " not found");
   }
}
