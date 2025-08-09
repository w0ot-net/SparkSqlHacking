package org.apache.spark.sql.catalyst.parser;

import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00013qa\u0001\u0003\u0011\u0002G\u0005\u0011\u0003C\u0003\u0019\u0001\u0019\u0005\u0011\u0004C\u00038\u0001\u0019\u0005\u0001HA\fECR\fG+\u001f9f!\u0006\u00148/\u001a:J]R,'OZ1dK*\u0011QAB\u0001\u0007a\u0006\u00148/\u001a:\u000b\u0005\u001dA\u0011\u0001C2bi\u0006d\u0017p\u001d;\u000b\u0005%Q\u0011aA:rY*\u00111\u0002D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001b9\ta!\u00199bG\",'\"A\b\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u0001\u0011\u0002CA\n\u0017\u001b\u0005!\"\"A\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005]!\"AB!osJ+g-\u0001\tqCJ\u001cX\rV1cY\u0016\u001c6\r[3nCR\u0011!\u0004\t\t\u00037yi\u0011\u0001\b\u0006\u0003;!\tQ\u0001^=qKNL!a\b\u000f\u0003\u0015M#(/^2u)f\u0004X\rC\u0003\"\u0003\u0001\u0007!%A\u0004tc2$V\r\u001f;\u0011\u0005\rRcB\u0001\u0013)!\t)C#D\u0001'\u0015\t9\u0003#\u0001\u0004=e>|GOP\u0005\u0003SQ\ta\u0001\u0015:fI\u00164\u0017BA\u0016-\u0005\u0019\u0019FO]5oO*\u0011\u0011\u0006\u0006\u0015\u0004\u00039*\u0004cA\n0c%\u0011\u0001\u0007\u0006\u0002\u0007i\"\u0014xn^:\u0011\u0005I\u001aT\"\u0001\u0003\n\u0005Q\"!A\u0004)beN,W\t_2faRLwN\\\u0011\u0002m\u0005\tC+\u001a=uA\r\fgN\\8uA\t,\u0007\u0005]1sg\u0016$\u0007\u0005^8!C\u0002\u001a8\r[3nC\u0006i\u0001/\u0019:tK\u0012\u000bG/\u0019+za\u0016$\"!\u000f\u001f\u0011\u0005mQ\u0014BA\u001e\u001d\u0005!!\u0015\r^1UsB,\u0007\"B\u0011\u0003\u0001\u0004\u0011\u0003f\u0001\u0002/}\u0005\nq(A\u0012UKb$\beY1o]>$\bEY3!a\u0006\u00148/\u001a3!i>\u0004\u0013\r\t#bi\u0006$\u0016\u0010]3"
)
public interface DataTypeParserInterface {
   StructType parseTableSchema(final String sqlText) throws ParseException;

   DataType parseDataType(final String sqlText) throws ParseException;
}
