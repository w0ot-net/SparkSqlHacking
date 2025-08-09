package org.apache.spark.ml.util;

import org.apache.spark.annotation.Unstable;
import scala.reflect.ScalaSignature;

@Unstable
@ScalaSignature(
   bytes = "\u0006\u000512qAA\u0002\u0011\u0002G\u0005a\u0002C\u0003\u001a\u0001\u0019\u0005#DA\tHK:,'/\u00197N\u0019^\u0013\u0018\u000e^1cY\u0016T!\u0001B\u0003\u0002\tU$\u0018\u000e\u001c\u0006\u0003\r\u001d\t!!\u001c7\u000b\u0005!I\u0011!B:qCJ\\'B\u0001\u0006\f\u0003\u0019\t\u0007/Y2iK*\tA\"A\u0002pe\u001e\u001c\u0001aE\u0002\u0001\u001fU\u0001\"\u0001E\n\u000e\u0003EQ\u0011AE\u0001\u0006g\u000e\fG.Y\u0005\u0003)E\u0011a!\u00118z%\u00164\u0007C\u0001\f\u0018\u001b\u0005\u0019\u0011B\u0001\r\u0004\u0005)iEj\u0016:ji\u0006\u0014G.Z\u0001\u0006oJLG/Z\u000b\u00027A\u0011a\u0003H\u0005\u0003;\r\u0011qbR3oKJ\fG.\u0014'Xe&$XM\u001d\u0015\u0004\u0003})\u0003C\u0001\u0011$\u001b\u0005\t#B\u0001\u0012\b\u0003)\tgN\\8uCRLwN\\\u0005\u0003I\u0005\u0012QaU5oG\u0016\f\u0013AJ\u0001\u0006e9\"d\u0006\r\u0015\u0004\u0001})\u0003F\u0001\u0001*!\t\u0001#&\u0003\u0002,C\tAQK\\:uC\ndW\r"
)
public interface GeneralMLWritable extends MLWritable {
   GeneralMLWriter write();
}
