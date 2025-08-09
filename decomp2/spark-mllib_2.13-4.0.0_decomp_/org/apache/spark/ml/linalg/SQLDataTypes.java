package org.apache.spark.ml.linalg;

import org.apache.spark.sql.types.DataType;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y:Qa\u0002\u0005\t\u0002M1Q!\u0006\u0005\t\u0002YAQ!H\u0001\u0005\u0002yAqaH\u0001C\u0002\u0013\u0005\u0001\u0005\u0003\u0004*\u0003\u0001\u0006I!\t\u0005\bU\u0005\u0011\r\u0011\"\u0001!\u0011\u0019Y\u0013\u0001)A\u0005C\u0005a1+\u0015'ECR\fG+\u001f9fg*\u0011\u0011BC\u0001\u0007Y&t\u0017\r\\4\u000b\u0005-a\u0011AA7m\u0015\tia\"A\u0003ta\u0006\u00148N\u0003\u0002\u0010!\u00051\u0011\r]1dQ\u0016T\u0011!E\u0001\u0004_J<7\u0001\u0001\t\u0003)\u0005i\u0011\u0001\u0003\u0002\r'FcE)\u0019;b)f\u0004Xm]\n\u0003\u0003]\u0001\"\u0001G\u000e\u000e\u0003eQ\u0011AG\u0001\u0006g\u000e\fG.Y\u0005\u00039e\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001\u0014\u0003)1Vm\u0019;peRK\b/Z\u000b\u0002CA\u0011!eJ\u0007\u0002G)\u0011A%J\u0001\u0006if\u0004Xm\u001d\u0006\u0003M1\t1a]9m\u0013\tA3E\u0001\u0005ECR\fG+\u001f9f\u0003-1Vm\u0019;peRK\b/\u001a\u0011\u0002\u00155\u000bGO]5y)f\u0004X-A\u0006NCR\u0014\u0018\u000e\u001f+za\u0016\u0004\u0003fA\u0001.gA\u0011a&M\u0007\u0002_)\u0011\u0001\u0007D\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001\u001a0\u0005\u0015\u0019\u0016N\\2fC\u0005!\u0014!\u0002\u001a/a9\u0002\u0004f\u0001\u0001.g\u0001"
)
public final class SQLDataTypes {
   public static DataType MatrixType() {
      return SQLDataTypes$.MODULE$.MatrixType();
   }

   public static DataType VectorType() {
      return SQLDataTypes$.MODULE$.VectorType();
   }
}
