package org.apache.spark.util;

import org.apache.spark.SparkException;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U1QAA\u0002\u0001\u000b-AQ\u0001\u0005\u0001\u0005\u0002I\u0011\u0011EU3ukJt7\u000b^1uK6,g\u000e^%o\u00072|7/\u001e:f\u000bb\u001cW\r\u001d;j_:T!\u0001B\u0003\u0002\tU$\u0018\u000e\u001c\u0006\u0003\r\u001d\tQa\u001d9be.T!\u0001C\u0005\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005Q\u0011aA8sON\u0011\u0001\u0001\u0004\t\u0003\u001b9i\u0011!B\u0005\u0003\u001f\u0015\u0011ab\u00159be.,\u0005pY3qi&|g.\u0001\u0004=S:LGOP\u0002\u0001)\u0005\u0019\u0002C\u0001\u000b\u0001\u001b\u0005\u0019\u0001"
)
public class ReturnStatementInClosureException extends SparkException {
   public ReturnStatementInClosureException() {
      super("Return statements aren't allowed in Spark closures");
   }
}
