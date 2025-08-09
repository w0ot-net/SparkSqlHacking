package org.apache.spark.sql.types;

import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019;a\u0001C\u0005\t\u0002-\u0019bAB\u000b\n\u0011\u0003Ya\u0003C\u0003\u001e\u0003\u0011\u0005q\u0004C\u0004!\u0003\t\u0007I\u0011A\u0011\t\rE\n\u0001\u0015!\u0003#\u0011\u0015\u0011\u0014\u0001\"\u00014\u0011\u0015q\u0014\u0001\"\u0001@\u0011\u0015\u0011\u0015\u0001\"\u0003D\u0003))\u0006oQ1tiJ+H.\u001a\u0006\u0003\u0015-\tQ\u0001^=qKNT!\u0001D\u0007\u0002\u0007M\fHN\u0003\u0002\u000f\u001f\u0005)1\u000f]1sW*\u0011\u0001#E\u0001\u0007CB\f7\r[3\u000b\u0003I\t1a\u001c:h!\t!\u0012!D\u0001\n\u0005))\u0006oQ1tiJ+H.Z\n\u0003\u0003]\u0001\"\u0001G\u000e\u000e\u0003eQ\u0011AG\u0001\u0006g\u000e\fG.Y\u0005\u00039e\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003M\t\u0011C\\;nKJL7\r\u0015:fG\u0016$WM\\2f+\u0005\u0011\u0003cA\u0012,]9\u0011A%\u000b\b\u0003K!j\u0011A\n\u0006\u0003Oy\ta\u0001\u0010:p_Rt\u0014\"\u0001\u000e\n\u0005)J\u0012a\u00029bG.\fw-Z\u0005\u0003Y5\u0012!\"\u00138eKb,GmU3r\u0015\tQ\u0013\u0004\u0005\u0002\u0015_%\u0011\u0001'\u0003\u0002\f\u001dVlWM]5d)f\u0004X-\u0001\nok6,'/[2Qe\u0016\u001cW\rZ3oG\u0016\u0004\u0013!C2b]V\u00038)Y:u)\r!t\u0007\u0010\t\u00031UJ!AN\r\u0003\u000f\t{w\u000e\\3b]\")\u0001(\u0002a\u0001s\u0005!aM]8n!\t!\"(\u0003\u0002<\u0013\tAA)\u0019;b)f\u0004X\rC\u0003>\u000b\u0001\u0007\u0011(\u0001\u0002u_\u00061B.Z4bY:+X.\u001a:jGB\u0013XmY3eK:\u001cW\rF\u00025\u0001\u0006CQ\u0001\u000f\u0004A\u0002eBQ!\u0010\u0004A\u0002e\nQC]3t_24\u0018M\u00197f\u001dVdG.\u00192jY&$\u0018\u0010F\u00025\t\u0016CQ\u0001O\u0004A\u0002QBQ!P\u0004A\u0002Q\u0002"
)
public final class UpCastRule {
   public static boolean legalNumericPrecedence(final DataType from, final DataType to) {
      return UpCastRule$.MODULE$.legalNumericPrecedence(from, to);
   }

   public static boolean canUpCast(final DataType from, final DataType to) {
      return UpCastRule$.MODULE$.canUpCast(from, to);
   }

   public static IndexedSeq numericPrecedence() {
      return UpCastRule$.MODULE$.numericPrecedence();
   }
}
