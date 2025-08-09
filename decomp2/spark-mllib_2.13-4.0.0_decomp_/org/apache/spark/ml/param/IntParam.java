package org.apache.spark.ml.param;

import org.apache.spark.ml.util.Identifiable;
import org.json4s.Formats;
import org.json4s.JInt;
import org.json4s.jackson.JsonMethods.;
import scala.Function1;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00114A\u0001D\u0007\u00011!I1\u0005\u0001B\u0001B\u0003%Ae\f\u0005\na\u0001\u0011\t\u0011)A\u0005IEB\u0011B\r\u0001\u0003\u0002\u0003\u0006I\u0001J\u001a\t\u0013Q\u0002!\u0011!Q\u0001\nUZ\u0004\"\u0002\u001f\u0001\t\u0003i\u0004\"\u0002\u001f\u0001\t\u0003\u0019\u0005\"\u0002\u001f\u0001\t\u00039\u0005\"\u0002\u001f\u0001\t\u0003\u0011\u0006\"\u0002,\u0001\t\u0003:\u0006\"B/\u0001\t\u0003r\u0006\"\u00021\u0001\t\u0003\n'\u0001C%oiB\u000b'/Y7\u000b\u00059y\u0011!\u00029be\u0006l'B\u0001\t\u0012\u0003\tiGN\u0003\u0002\u0013'\u0005)1\u000f]1sW*\u0011A#F\u0001\u0007CB\f7\r[3\u000b\u0003Y\t1a\u001c:h\u0007\u0001\u0019\"\u0001A\r\u0011\u0007iYR$D\u0001\u000e\u0013\taRBA\u0003QCJ\fW\u000e\u0005\u0002\u001fC5\tqDC\u0001!\u0003\u0015\u00198-\u00197b\u0013\t\u0011sDA\u0002J]R\fa\u0001]1sK:$\bCA\u0013-\u001d\t1#\u0006\u0005\u0002(?5\t\u0001F\u0003\u0002*/\u00051AH]8pizJ!aK\u0010\u0002\rA\u0013X\rZ3g\u0013\ticF\u0001\u0004TiJLgn\u001a\u0006\u0003W}I!aI\u000e\u0002\t9\fW.Z\u0005\u0003am\t1\u0001Z8d\u0013\t\u00114$A\u0004jgZ\u000bG.\u001b3\u0011\ty1T\u0004O\u0005\u0003o}\u0011\u0011BR;oGRLwN\\\u0019\u0011\u0005yI\u0014B\u0001\u001e \u0005\u001d\u0011un\u001c7fC:L!\u0001N\u000e\u0002\rqJg.\u001b;?)\u0015qt\bQ!C!\tQ\u0002\u0001C\u0003$\u000b\u0001\u0007A\u0005C\u00031\u000b\u0001\u0007A\u0005C\u00033\u000b\u0001\u0007A\u0005C\u00035\u000b\u0001\u0007Q\u0007\u0006\u0003?\t\u00163\u0005\"B\u0012\u0007\u0001\u0004!\u0003\"\u0002\u0019\u0007\u0001\u0004!\u0003\"\u0002\u001a\u0007\u0001\u0004!C#\u0002 I\u001fB\u000b\u0006\"B\u0012\b\u0001\u0004I\u0005C\u0001&N\u001b\u0005Y%B\u0001'\u0010\u0003\u0011)H/\u001b7\n\u00059[%\u0001D%eK:$\u0018NZ5bE2,\u0007\"\u0002\u0019\b\u0001\u0004!\u0003\"\u0002\u001a\b\u0001\u0004!\u0003\"\u0002\u001b\b\u0001\u0004)D\u0003\u0002 T)VCQa\t\u0005A\u0002%CQ\u0001\r\u0005A\u0002\u0011BQA\r\u0005A\u0002\u0011\n\u0011a\u001e\u000b\u00031n\u00032AG-\u001e\u0013\tQVBA\u0005QCJ\fW\u000eU1je\")A,\u0003a\u0001;\u0005)a/\u00197vK\u0006Q!n]8o\u000b:\u001cw\u000eZ3\u0015\u0005\u0011z\u0006\"\u0002/\u000b\u0001\u0004i\u0012A\u00036t_:$UmY8eKR\u0011QD\u0019\u0005\u0006G.\u0001\r\u0001J\u0001\u0005UN|g\u000e"
)
public class IntParam extends Param {
   public ParamPair w(final int value) {
      return super.w(BoxesRunTime.boxToInteger(value));
   }

   public String jsonEncode(final int value) {
      return .MODULE$.compact(.MODULE$.render(new JInt(scala.math.BigInt..MODULE$.int2bigInt(value)), .MODULE$.render$default$2(), .MODULE$.render$default$3()));
   }

   public int jsonDecode(final String json) {
      Formats formats = org.json4s.DefaultFormats..MODULE$;
      return BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(.MODULE$.parse(json, .MODULE$.parse$default$2(), .MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput())), formats, scala.reflect.ManifestFactory..MODULE$.Int()));
   }

   public IntParam(final String parent, final String name, final String doc, final Function1 isValid) {
      super((String)parent, name, doc, isValid, scala.reflect.ClassTag..MODULE$.Int());
   }

   public IntParam(final String parent, final String name, final String doc) {
      this(parent, name, doc, ParamValidators$.MODULE$.alwaysTrue());
   }

   public IntParam(final Identifiable parent, final String name, final String doc, final Function1 isValid) {
      this(parent.uid(), name, doc, isValid);
   }

   public IntParam(final Identifiable parent, final String name, final String doc) {
      this(parent.uid(), name, doc);
   }
}
