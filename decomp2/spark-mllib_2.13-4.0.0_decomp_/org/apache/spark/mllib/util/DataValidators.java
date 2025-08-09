package org.apache.spark.mllib.util;

import org.apache.spark.internal.Logging;
import scala.Function1;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E;QAB\u0004\t\u0002I1Q\u0001F\u0004\t\u0002UAQAI\u0001\u0005\u0002\rBq\u0001J\u0001C\u0002\u0013\u0005Q\u0005\u0003\u0004B\u0003\u0001\u0006IA\n\u0005\u0006\u0007\u0006!\t\u0001R\u0001\u000f\t\u0006$\u0018MV1mS\u0012\fGo\u001c:t\u0015\tA\u0011\"\u0001\u0003vi&d'B\u0001\u0006\f\u0003\u0015iG\u000e\\5c\u0015\taQ\"A\u0003ta\u0006\u00148N\u0003\u0002\u000f\u001f\u00051\u0011\r]1dQ\u0016T\u0011\u0001E\u0001\u0004_J<7\u0001\u0001\t\u0003'\u0005i\u0011a\u0002\u0002\u000f\t\u0006$\u0018MV1mS\u0012\fGo\u001c:t'\r\ta\u0003\b\t\u0003/ii\u0011\u0001\u0007\u0006\u00023\u0005)1oY1mC&\u00111\u0004\u0007\u0002\u0007\u0003:L(+\u001a4\u0011\u0005u\u0001S\"\u0001\u0010\u000b\u0005}Y\u0011\u0001C5oi\u0016\u0014h.\u00197\n\u0005\u0005r\"a\u0002'pO\u001eLgnZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003I\tACY5oCJLH*\u00192fYZ\u000bG.\u001b3bi>\u0014X#\u0001\u0014\u0011\t]9\u0013&N\u0005\u0003Qa\u0011\u0011BR;oGRLwN\\\u0019\u0011\u0007)js&D\u0001,\u0015\ta3\"A\u0002sI\u0012L!AL\u0016\u0003\u0007I#E\t\u0005\u00021g5\t\u0011G\u0003\u00023\u0013\u0005Q!/Z4sKN\u001c\u0018n\u001c8\n\u0005Q\n$\u0001\u0004'bE\u0016dW\r\u001a)pS:$\bCA\f7\u0013\t9\u0004DA\u0004C_>dW-\u00198)\u0007\rIt\b\u0005\u0002;{5\t1H\u0003\u0002=\u0017\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005yZ$!B*j]\u000e,\u0017%\u0001!\u0002\u000bEr\u0003G\f\u0019\u0002+\tLg.\u0019:z\u0019\u0006\u0014W\r\u001c,bY&$\u0017\r^8sA!\u001aA!O \u0002'5,H\u000e^5MC\n,GNV1mS\u0012\fGo\u001c:\u0015\u0005\u0019*\u0005\"\u0002$\u0006\u0001\u00049\u0015!A6\u0011\u0005]A\u0015BA%\u0019\u0005\rIe\u000e\u001e\u0015\u0004\u000beZ\u0015%\u0001'\u0002\u000bEr3G\f\u0019)\u0007\u0005Id*I\u0001P\u0003\u0015\u0001d\u0006\u000f\u00181Q\r\u0001\u0011H\u0014"
)
public final class DataValidators {
   public static Function1 multiLabelValidator(final int k) {
      return DataValidators$.MODULE$.multiLabelValidator(k);
   }

   public static Function1 binaryLabelValidator() {
      return DataValidators$.MODULE$.binaryLabelValidator();
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return DataValidators$.MODULE$.LogStringContext(sc);
   }
}
