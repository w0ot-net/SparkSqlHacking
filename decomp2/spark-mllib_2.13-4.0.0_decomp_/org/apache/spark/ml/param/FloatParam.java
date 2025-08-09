package org.apache.spark.ml.param;

import org.apache.spark.ml.util.Identifiable;
import org.json4s.JValue;
import org.json4s.jackson.JsonMethods.;
import scala.Function1;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ua\u0001\u0002\n\u0014\u0001yA\u0011\"\u000b\u0001\u0003\u0002\u0003\u0006IAK\u001b\t\u0013Y\u0002!\u0011!Q\u0001\n):\u0004\"\u0003\u001d\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0016:\u0011%Q\u0004A!A!\u0002\u0013Y\u0014\tC\u0003C\u0001\u0011\u00051\tC\u0003C\u0001\u0011\u0005\u0011\nC\u0003C\u0001\u0011\u0005Q\nC\u0003C\u0001\u0011\u0005\u0001\fC\u0003]\u0001\u0011\u0005S\fC\u0003d\u0001\u0011\u0005C\rC\u0003g\u0001\u0011\u0005smB\u0003k'!%1NB\u0003\u0013'!%A\u000eC\u0003C\u001b\u0011\u0005\u0001\u0010C\u0003z\u001b\u0011\u0005!\u0010C\u0004\u0002\u00065!\t!a\u0002\t\u0013\u00055Q\"!A\u0005\n\u0005=!A\u0003$m_\u0006$\b+\u0019:b[*\u0011A#F\u0001\u0006a\u0006\u0014\u0018-\u001c\u0006\u0003-]\t!!\u001c7\u000b\u0005aI\u0012!B:qCJ\\'B\u0001\u000e\u001c\u0003\u0019\t\u0007/Y2iK*\tA$A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001?A\u0019\u0001%I\u0012\u000e\u0003MI!AI\n\u0003\u000bA\u000b'/Y7\u0011\u0005\u0011:S\"A\u0013\u000b\u0003\u0019\nQa]2bY\u0006L!\u0001K\u0013\u0003\u000b\u0019cw.\u0019;\u0002\rA\f'/\u001a8u!\tY#G\u0004\u0002-aA\u0011Q&J\u0007\u0002])\u0011q&H\u0001\u0007yI|w\u000e\u001e \n\u0005E*\u0013A\u0002)sK\u0012,g-\u0003\u00024i\t11\u000b\u001e:j]\u001eT!!M\u0013\n\u0005%\n\u0013\u0001\u00028b[\u0016L!AN\u0011\u0002\u0007\u0011|7-\u0003\u00029C\u00059\u0011n\u001d,bY&$\u0007\u0003\u0002\u0013=GyJ!!P\u0013\u0003\u0013\u0019+hn\u0019;j_:\f\u0004C\u0001\u0013@\u0013\t\u0001UEA\u0004C_>dW-\u00198\n\u0005i\n\u0013A\u0002\u001fj]&$h\bF\u0003E\u000b\u001a;\u0005\n\u0005\u0002!\u0001!)\u0011&\u0002a\u0001U!)a'\u0002a\u0001U!)\u0001(\u0002a\u0001U!)!(\u0002a\u0001wQ!AIS&M\u0011\u0015Ic\u00011\u0001+\u0011\u00151d\u00011\u0001+\u0011\u0015Ad\u00011\u0001+)\u0015!e*\u0016,X\u0011\u0015Is\u00011\u0001P!\t\u00016+D\u0001R\u0015\t\u0011V#\u0001\u0003vi&d\u0017B\u0001+R\u00051IE-\u001a8uS\u001aL\u0017M\u00197f\u0011\u00151t\u00011\u0001+\u0011\u0015At\u00011\u0001+\u0011\u0015Qt\u00011\u0001<)\u0011!\u0015LW.\t\u000b%B\u0001\u0019A(\t\u000bYB\u0001\u0019\u0001\u0016\t\u000baB\u0001\u0019\u0001\u0016\u0002\u0003]$\"AX1\u0011\u0007\u0001z6%\u0003\u0002a'\tI\u0001+\u0019:b[B\u000b\u0017N\u001d\u0005\u0006E&\u0001\raI\u0001\u0006m\u0006dW/Z\u0001\u000bUN|g.\u00128d_\u0012,GC\u0001\u0016f\u0011\u0015\u0011'\u00021\u0001$\u0003)Q7o\u001c8EK\u000e|G-\u001a\u000b\u0003G!DQ![\u0006A\u0002)\nAA[:p]\u0006Qa\t\\8biB\u000b'/Y7\u0011\u0005\u0001j1cA\u0007naB\u0011AE\\\u0005\u0003_\u0016\u0012a!\u00118z%\u00164\u0007CA9w\u001b\u0005\u0011(BA:u\u0003\tIwNC\u0001v\u0003\u0011Q\u0017M^1\n\u0005]\u0014(\u0001D*fe&\fG.\u001b>bE2,G#A6\u0002\u0019)4\u0016\r\\;f\u000b:\u001cw\u000eZ3\u0015\u0007m\f\u0019\u0001\u0005\u0002}\u007f6\tQP\u0003\u0002\u007f7\u00051!n]8oiML1!!\u0001~\u0005\u0019Qe+\u00197vK\")!m\u0004a\u0001G\u0005a!NV1mk\u0016$UmY8eKR\u00191%!\u0003\t\r\u0005-\u0001\u00031\u0001|\u0003\u0019Qg+\u00197vK\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011\u0011\u0003\t\u0005\u0003'\tI\"\u0004\u0002\u0002\u0016)\u0019\u0011q\u0003;\u0002\t1\fgnZ\u0005\u0005\u00037\t)B\u0001\u0004PE*,7\r\u001e"
)
public class FloatParam extends Param {
   public static float jValueDecode(final JValue jValue) {
      return FloatParam$.MODULE$.jValueDecode(jValue);
   }

   public static JValue jValueEncode(final float value) {
      return FloatParam$.MODULE$.jValueEncode(value);
   }

   public ParamPair w(final float value) {
      return super.w(BoxesRunTime.boxToFloat(value));
   }

   public String jsonEncode(final float value) {
      return .MODULE$.compact(.MODULE$.render(FloatParam$.MODULE$.jValueEncode(value), .MODULE$.render$default$2(), .MODULE$.render$default$3()));
   }

   public float jsonDecode(final String json) {
      return FloatParam$.MODULE$.jValueDecode(.MODULE$.parse(json, .MODULE$.parse$default$2(), .MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput()));
   }

   public FloatParam(final String parent, final String name, final String doc, final Function1 isValid) {
      super((String)parent, name, doc, isValid, scala.reflect.ClassTag..MODULE$.Float());
   }

   public FloatParam(final String parent, final String name, final String doc) {
      this(parent, name, doc, ParamValidators$.MODULE$.alwaysTrue());
   }

   public FloatParam(final Identifiable parent, final String name, final String doc, final Function1 isValid) {
      this(parent.uid(), name, doc, isValid);
   }

   public FloatParam(final Identifiable parent, final String name, final String doc) {
      this(parent.uid(), name, doc);
   }
}
