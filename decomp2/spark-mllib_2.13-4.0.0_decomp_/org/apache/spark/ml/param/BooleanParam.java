package org.apache.spark.ml.param;

import org.apache.spark.ml.util.Identifiable;
import org.json4s.Formats;
import org.json4s.jackson.JsonMethods.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000513A!\u0003\u0006\u0001+!A\u0001\u0005\u0001B\u0001B\u0003%\u0011\u0005\u0003\u0005-\u0001\t\u0005\t\u0015!\u0003\"\u0011!i\u0003A!A!\u0002\u0013\t\u0003\"\u0002\u0018\u0001\t\u0003y\u0003\"\u0002\u0018\u0001\t\u0003!\u0004\"\u0002 \u0001\t\u0003z\u0004\"B#\u0001\t\u00032\u0005\"\u0002%\u0001\t\u0003J%\u0001\u0004\"p_2,\u0017M\u001c)be\u0006l'BA\u0006\r\u0003\u0015\u0001\u0018M]1n\u0015\tia\"\u0001\u0002nY*\u0011q\u0002E\u0001\u0006gB\f'o\u001b\u0006\u0003#I\ta!\u00199bG\",'\"A\n\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u00011\u0002cA\f\u001955\t!\"\u0003\u0002\u001a\u0015\t)\u0001+\u0019:b[B\u00111DH\u0007\u00029)\tQ$A\u0003tG\u0006d\u0017-\u0003\u0002 9\t9!i\\8mK\u0006t\u0017A\u00029be\u0016tG\u000f\u0005\u0002#S9\u00111e\n\t\u0003Iqi\u0011!\n\u0006\u0003MQ\ta\u0001\u0010:p_Rt\u0014B\u0001\u0015\u001d\u0003\u0019\u0001&/\u001a3fM&\u0011!f\u000b\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005!b\u0012\u0001\u00028b[\u0016\f1\u0001Z8d\u0003\u0019a\u0014N\\5u}Q!\u0001'\r\u001a4!\t9\u0002\u0001C\u0003!\t\u0001\u0007\u0011\u0005C\u0003-\t\u0001\u0007\u0011\u0005C\u0003.\t\u0001\u0007\u0011\u0005\u0006\u00031kqj\u0004\"\u0002\u0011\u0006\u0001\u00041\u0004CA\u001c;\u001b\u0005A$BA\u001d\r\u0003\u0011)H/\u001b7\n\u0005mB$\u0001D%eK:$\u0018NZ5bE2,\u0007\"\u0002\u0017\u0006\u0001\u0004\t\u0003\"B\u0017\u0006\u0001\u0004\t\u0013!A<\u0015\u0005\u0001\u001b\u0005cA\fB5%\u0011!I\u0003\u0002\n!\u0006\u0014\u0018-\u001c)bSJDQ\u0001\u0012\u0004A\u0002i\tQA^1mk\u0016\f!B[:p]\u0016s7m\u001c3f)\t\ts\tC\u0003E\u000f\u0001\u0007!$\u0001\u0006kg>tG)Z2pI\u0016$\"A\u0007&\t\u000b-C\u0001\u0019A\u0011\u0002\t)\u001cxN\u001c"
)
public class BooleanParam extends Param {
   public ParamPair w(final boolean value) {
      return super.w(BoxesRunTime.boxToBoolean(value));
   }

   public String jsonEncode(final boolean value) {
      return .MODULE$.compact(.MODULE$.render(org.json4s.JBool..MODULE$.apply(value), .MODULE$.render$default$2(), .MODULE$.render$default$3()));
   }

   public boolean jsonDecode(final String json) {
      Formats formats = org.json4s.DefaultFormats..MODULE$;
      return BoxesRunTime.unboxToBoolean(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(.MODULE$.parse(json, .MODULE$.parse$default$2(), .MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput())), formats, scala.reflect.ManifestFactory..MODULE$.Boolean()));
   }

   public BooleanParam(final String parent, final String name, final String doc) {
      super((String)parent, name, doc, scala.reflect.ClassTag..MODULE$.Boolean());
   }

   public BooleanParam(final Identifiable parent, final String name, final String doc) {
      this(parent.uid(), name, doc);
   }
}
