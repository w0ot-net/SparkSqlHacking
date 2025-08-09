package org.apache.spark.sql.catalyst.parser;

import org.apache.spark.internal.Logging;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0001:Q\u0001B\u0003\t\u0002I1Q\u0001F\u0003\t\u0002UAQ!G\u0001\u0005\u0002iAQaG\u0001\u0005Rq\ta\u0002R1uCRK\b/\u001a)beN,'O\u0003\u0002\u0007\u000f\u00051\u0001/\u0019:tKJT!\u0001C\u0005\u0002\u0011\r\fG/\u00197zgRT!AC\u0006\u0002\u0007M\fHN\u0003\u0002\r\u001b\u0005)1\u000f]1sW*\u0011abD\u0001\u0007CB\f7\r[3\u000b\u0003A\t1a\u001c:h\u0007\u0001\u0001\"aE\u0001\u000e\u0003\u0015\u0011a\u0002R1uCRK\b/\u001a)beN,'o\u0005\u0002\u0002-A\u00111cF\u0005\u00031\u0015\u0011a\"\u00112tiJ\f7\r\u001e)beN,'/\u0001\u0004=S:LGO\u0010\u000b\u0002%\u0005Q\u0011m\u001d;Ck&dG-\u001a:\u0016\u0003u\u0001\"a\u0005\u0010\n\u0005})!A\u0005#bi\u0006$\u0016\u0010]3BgR\u0014U/\u001b7eKJ\u0004"
)
public final class DataTypeParser {
   public static StructType parseTableSchema(final String sqlText) {
      return DataTypeParser$.MODULE$.parseTableSchema(sqlText);
   }

   public static DataType parseDataType(final String sqlText) {
      return DataTypeParser$.MODULE$.parseDataType(sqlText);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return DataTypeParser$.MODULE$.LogStringContext(sc);
   }
}
