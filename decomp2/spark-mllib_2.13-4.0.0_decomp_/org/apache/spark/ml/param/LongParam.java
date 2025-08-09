package org.apache.spark.ml.param;

import org.apache.spark.ml.util.Identifiable;
import org.json4s.Formats;
import org.json4s.JInt;
import org.json4s.jackson.JsonMethods.;
import scala.Function1;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00114A\u0001D\u0007\u00011!I1\u0005\u0001B\u0001B\u0003%Ae\f\u0005\na\u0001\u0011\t\u0011)A\u0005IEB\u0011B\r\u0001\u0003\u0002\u0003\u0006I\u0001J\u001a\t\u0013Q\u0002!\u0011!Q\u0001\nUZ\u0004\"\u0002\u001f\u0001\t\u0003i\u0004\"\u0002\u001f\u0001\t\u0003\u0019\u0005\"\u0002\u001f\u0001\t\u00039\u0005\"\u0002\u001f\u0001\t\u0003\u0011\u0006\"\u0002,\u0001\t\u0003:\u0006\"B/\u0001\t\u0003r\u0006\"\u00021\u0001\t\u0003\n'!\u0003'p]\u001e\u0004\u0016M]1n\u0015\tqq\"A\u0003qCJ\fWN\u0003\u0002\u0011#\u0005\u0011Q\u000e\u001c\u0006\u0003%M\tQa\u001d9be.T!\u0001F\u000b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00051\u0012aA8sO\u000e\u00011C\u0001\u0001\u001a!\rQ2$H\u0007\u0002\u001b%\u0011A$\u0004\u0002\u0006!\u0006\u0014\u0018-\u001c\t\u0003=\u0005j\u0011a\b\u0006\u0002A\u0005)1oY1mC&\u0011!e\b\u0002\u0005\u0019>tw-\u0001\u0004qCJ,g\u000e\u001e\t\u0003K1r!A\n\u0016\u0011\u0005\u001dzR\"\u0001\u0015\u000b\u0005%:\u0012A\u0002\u001fs_>$h(\u0003\u0002,?\u00051\u0001K]3eK\u001aL!!\f\u0018\u0003\rM#(/\u001b8h\u0015\tYs$\u0003\u0002$7\u0005!a.Y7f\u0013\t\u00014$A\u0002e_\u000eL!AM\u000e\u0002\u000f%\u001ch+\u00197jIB!aDN\u000f9\u0013\t9tDA\u0005Gk:\u001cG/[8ocA\u0011a$O\u0005\u0003u}\u0011qAQ8pY\u0016\fg.\u0003\u000257\u00051A(\u001b8jiz\"RAP A\u0003\n\u0003\"A\u0007\u0001\t\u000b\r*\u0001\u0019\u0001\u0013\t\u000bA*\u0001\u0019\u0001\u0013\t\u000bI*\u0001\u0019\u0001\u0013\t\u000bQ*\u0001\u0019A\u001b\u0015\ty\"UI\u0012\u0005\u0006G\u0019\u0001\r\u0001\n\u0005\u0006a\u0019\u0001\r\u0001\n\u0005\u0006e\u0019\u0001\r\u0001\n\u000b\u0006}!{\u0005+\u0015\u0005\u0006G\u001d\u0001\r!\u0013\t\u0003\u00156k\u0011a\u0013\u0006\u0003\u0019>\tA!\u001e;jY&\u0011aj\u0013\u0002\r\u0013\u0012,g\u000e^5gS\u0006\u0014G.\u001a\u0005\u0006a\u001d\u0001\r\u0001\n\u0005\u0006e\u001d\u0001\r\u0001\n\u0005\u0006i\u001d\u0001\r!\u000e\u000b\u0005}M#V\u000bC\u0003$\u0011\u0001\u0007\u0011\nC\u00031\u0011\u0001\u0007A\u0005C\u00033\u0011\u0001\u0007A%A\u0001x)\tA6\fE\u0002\u001b3vI!AW\u0007\u0003\u0013A\u000b'/Y7QC&\u0014\b\"\u0002/\n\u0001\u0004i\u0012!\u0002<bYV,\u0017A\u00036t_:,enY8eKR\u0011Ae\u0018\u0005\u00069*\u0001\r!H\u0001\u000bUN|g\u000eR3d_\u0012,GCA\u000fc\u0011\u0015\u00197\u00021\u0001%\u0003\u0011Q7o\u001c8"
)
public class LongParam extends Param {
   public ParamPair w(final long value) {
      return super.w(BoxesRunTime.boxToLong(value));
   }

   public String jsonEncode(final long value) {
      return .MODULE$.compact(.MODULE$.render(new JInt(scala.math.BigInt..MODULE$.long2bigInt(value)), .MODULE$.render$default$2(), .MODULE$.render$default$3()));
   }

   public long jsonDecode(final String json) {
      Formats formats = org.json4s.DefaultFormats..MODULE$;
      return BoxesRunTime.unboxToLong(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(.MODULE$.parse(json, .MODULE$.parse$default$2(), .MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput())), formats, scala.reflect.ManifestFactory..MODULE$.Long()));
   }

   public LongParam(final String parent, final String name, final String doc, final Function1 isValid) {
      super((String)parent, name, doc, isValid, scala.reflect.ClassTag..MODULE$.Long());
   }

   public LongParam(final String parent, final String name, final String doc) {
      this(parent, name, doc, ParamValidators$.MODULE$.alwaysTrue());
   }

   public LongParam(final Identifiable parent, final String name, final String doc, final Function1 isValid) {
      this(parent.uid(), name, doc, isValid);
   }

   public LongParam(final Identifiable parent, final String name, final String doc) {
      this(parent.uid(), name, doc);
   }
}
