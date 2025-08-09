package org.apache.spark.ml.feature;

import org.apache.spark.ml.attribute.AttributeGroup;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructField;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uqAB\u0005\u000b\u0011\u0003QAC\u0002\u0004\u0017\u0015!\u0005!b\u0006\u0005\u0006=\u0005!\t\u0001\t\u0005\u0006C\u0005!IA\t\u0005\u0006}\u0005!Ia\u0010\u0005\u0006\u0015\u0006!\ta\u0013\u0005\b+\u0006\t\n\u0011\"\u0001W\u0011\u0015\t\u0017\u0001\"\u0001c\u0011\u001d\tI!\u0001C\u0001\u0003\u0017\t1c\u00148f\u0011>$XI\\2pI\u0016\u00148i\\7n_:T!a\u0003\u0007\u0002\u000f\u0019,\u0017\r^;sK*\u0011QBD\u0001\u0003[2T!a\u0004\t\u0002\u000bM\u0004\u0018M]6\u000b\u0005E\u0011\u0012AB1qC\u000eDWMC\u0001\u0014\u0003\ry'o\u001a\t\u0003+\u0005i\u0011A\u0003\u0002\u0014\u001f:,\u0007j\u001c;F]\u000e|G-\u001a:D_6lwN\\\n\u0003\u0003a\u0001\"!\u0007\u000f\u000e\u0003iQ\u0011aG\u0001\u0006g\u000e\fG.Y\u0005\u0003;i\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003Q\t!cZ3o\u001fV$\b/\u001e;BiR\u0014h*Y7fgR\u00111\u0005\u000e\t\u00043\u00112\u0013BA\u0013\u001b\u0005\u0019y\u0005\u000f^5p]B\u0019\u0011dJ\u0015\n\u0005!R\"!B!se\u0006L\bC\u0001\u00162\u001d\tYs\u0006\u0005\u0002-55\tQF\u0003\u0002/?\u00051AH]8pizJ!\u0001\r\u000e\u0002\rA\u0013X\rZ3g\u0013\t\u00114G\u0001\u0004TiJLgn\u001a\u0006\u0003aiAQ!N\u0002A\u0002Y\n\u0001\"\u001b8qkR\u001cu\u000e\u001c\t\u0003oqj\u0011\u0001\u000f\u0006\u0003si\nQ\u0001^=qKNT!a\u000f\b\u0002\u0007M\fH.\u0003\u0002>q\tY1\u000b\u001e:vGR4\u0015.\u001a7e\u0003I9WM\\(viB,H/\u0011;ue\u001e\u0013x.\u001e9\u0015\u0007\u00013\u0005\n\u0005\u0002B\t6\t!I\u0003\u0002D\u0019\u0005I\u0011\r\u001e;sS\n,H/Z\u0005\u0003\u000b\n\u0013a\"\u0011;ue&\u0014W\u000f^3He>,\b\u000fC\u0003H\t\u0001\u00071%A\bpkR\u0004X\u000f^!uiJt\u0015-\\3t\u0011\u0015IE\u00011\u0001*\u00035yW\u000f\u001e9vi\u000e{GNT1nK\u0006YBO]1og\u001a|'/\\(viB,HoQ8mk6t7k\u00195f[\u0006$RA\u000e'N\u001dNCQ!N\u0003A\u0002YBQ!S\u0003A\u0002%BQaT\u0003A\u0002A\u000b\u0001\u0002\u001a:pa2\u000b7\u000f\u001e\t\u00033EK!A\u0015\u000e\u0003\u000f\t{w\u000e\\3b]\"9A+\u0002I\u0001\u0002\u0004\u0001\u0016aC6fKBLeN^1mS\u0012\fQ\u0005\u001e:b]N4wN]7PkR\u0004X\u000f^\"pYVlgnU2iK6\fG\u0005Z3gCVdG\u000f\n\u001b\u0016\u0003]S#\u0001\u0015-,\u0003e\u0003\"AW0\u000e\u0003mS!\u0001X/\u0002\u0013Ut7\r[3dW\u0016$'B\u00010\u001b\u0003)\tgN\\8uCRLwN\\\u0005\u0003An\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003i9W\r^(viB,H/\u0011;ue\u001e\u0013x.\u001e9Ge>lG)\u0019;b)\u001d\u0019GN`A\u0002\u0003\u000f\u00012\u0001Z5A\u001d\t)wM\u0004\u0002-M&\t1$\u0003\u0002i5\u00059\u0001/Y2lC\u001e,\u0017B\u00016l\u0005\r\u0019V-\u001d\u0006\u0003QjAQ!\\\u0004A\u00029\fq\u0001Z1uCN,G\u000f\r\u0002pkB\u0019\u0001/]:\u000e\u0003iJ!A\u001d\u001e\u0003\u000f\u0011\u000bG/Y:fiB\u0011A/\u001e\u0007\u0001\t%1H.!A\u0001\u0002\u000b\u0005qOA\u0002`IM\n\"\u0001_>\u0011\u0005eI\u0018B\u0001>\u001b\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\u0007?\n\u0005uT\"aA!os\"1qp\u0002a\u0001\u0003\u0003\tQ\"\u001b8qkR\u001cu\u000e\u001c(b[\u0016\u001c\bc\u00013jS!9\u0011QA\u0004A\u0002\u0005\u0005\u0011AD8viB,HoQ8m\u001d\u0006lWm\u001d\u0005\u0006\u001f\u001e\u0001\r\u0001U\u0001\u001cGJ,\u0017\r^3BiR\u0014xI]8va\u001a{'/\u0011;ue:\u000bW.Z:\u0015\u0013\u0001\u000bi!a\u0004\u0002\u001a\u0005m\u0001\"B%\t\u0001\u0004I\u0003bBA\t\u0011\u0001\u0007\u00111C\u0001\t]Vl\u0017\t\u001e;sgB\u0019\u0011$!\u0006\n\u0007\u0005]!DA\u0002J]RDQa\u0014\u0005A\u0002ACQ\u0001\u0016\u0005A\u0002A\u0003"
)
public final class OneHotEncoderCommon {
   public static AttributeGroup createAttrGroupForAttrNames(final String outputColName, final int numAttrs, final boolean dropLast, final boolean keepInvalid) {
      return OneHotEncoderCommon$.MODULE$.createAttrGroupForAttrNames(outputColName, numAttrs, dropLast, keepInvalid);
   }

   public static Seq getOutputAttrGroupFromData(final Dataset dataset, final Seq inputColNames, final Seq outputColNames, final boolean dropLast) {
      return OneHotEncoderCommon$.MODULE$.getOutputAttrGroupFromData(dataset, inputColNames, outputColNames, dropLast);
   }

   public static boolean transformOutputColumnSchema$default$4() {
      return OneHotEncoderCommon$.MODULE$.transformOutputColumnSchema$default$4();
   }

   public static StructField transformOutputColumnSchema(final StructField inputCol, final String outputColName, final boolean dropLast, final boolean keepInvalid) {
      return OneHotEncoderCommon$.MODULE$.transformOutputColumnSchema(inputCol, outputColName, dropLast, keepInvalid);
   }
}
