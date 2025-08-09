package org.apache.spark.sql.types;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U2a!\u0002\u0004\u0002\u0002!\u0001\u0002\"B\f\u0001\t\u0003I\u0002B\u0002\u000f\u0001\r\u0003AQ\u0004\u0003\u0004\"\u0001\u0019\u0005\u0001B\t\u0005\u0007Q\u00011\t\u0001C\u0015\u0003!\u0005\u00137\u000f\u001e:bGR$\u0015\r^1UsB,'BA\u0004\t\u0003\u0015!\u0018\u0010]3t\u0015\tI!\"A\u0002tc2T!a\u0003\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00055q\u0011AB1qC\u000eDWMC\u0001\u0010\u0003\ry'oZ\n\u0003\u0001E\u0001\"AE\u000b\u000e\u0003MQ\u0011\u0001F\u0001\u0006g\u000e\fG.Y\u0005\u0003-M\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003i\u0001\"a\u0007\u0001\u000e\u0003\u0019\t1\u0003Z3gCVdGoQ8oGJ,G/\u001a+za\u0016,\u0012A\b\t\u00037}I!\u0001\t\u0004\u0003\u0011\u0011\u000bG/\u0019+za\u0016\f1\"Y2dKB$8\u000fV=qKR\u00111E\n\t\u0003%\u0011J!!J\n\u0003\u000f\t{w\u000e\\3b]\")qe\u0001a\u0001=\u0005)q\u000e\u001e5fe\u0006a1/[7qY\u0016\u001cFO]5oOV\t!\u0006\u0005\u0002,e9\u0011A\u0006\r\t\u0003[Mi\u0011A\f\u0006\u0003_a\ta\u0001\u0010:p_Rt\u0014BA\u0019\u0014\u0003\u0019\u0001&/\u001a3fM&\u00111\u0007\u000e\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005E\u001a\u0002"
)
public abstract class AbstractDataType {
   public abstract DataType defaultConcreteType();

   public abstract boolean acceptsType(final DataType other);

   public abstract String simpleString();
}
