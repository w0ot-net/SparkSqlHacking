package org.json4s.prefs;

import org.json4s.JValue;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q:Qa\u0003\u0007\t\u0002M1Q!\u0006\u0007\t\u0002YAQ!H\u0001\u0005\u0002yAQaH\u0001\u0005\u0002\u0001Bq\u0001M\u0001C\u0002\u0013\u0005\u0001\u0005\u0003\u00042\u0003\u0001\u0006I!\t\u0005\be\u0005\u0011\r\u0011\"\u0001!\u0011\u0019\u0019\u0014\u0001)A\u0005C\u00199Q\u0003\u0004I\u0001$\u0003\u0011\u0003\"B\u0012\t\r\u0003!\u0003\"\u0002\u0015\t\r\u0003I\u0013AE#naRLh+\u00197vKN#(/\u0019;fOfT!!\u0004\b\u0002\u000bA\u0014XMZ:\u000b\u0005=\u0001\u0012A\u00026t_:$4OC\u0001\u0012\u0003\ry'oZ\u0002\u0001!\t!\u0012!D\u0001\r\u0005I)U\u000e\u001d;z-\u0006dW/Z*ue\u0006$XmZ=\u0014\u0005\u00059\u0002C\u0001\r\u001c\u001b\u0005I\"\"\u0001\u000e\u0002\u000bM\u001c\u0017\r\\1\n\u0005qI\"AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002'\u00059A-\u001a4bk2$X#A\u0011\u0011\u0005QA1C\u0001\u0005\u0018\u0003IqwN\\3WC2\u0014V\r\u001d7bG\u0016lWM\u001c;\u0016\u0003\u0015\u00022\u0001\u0007\u0014\u0018\u0013\t9\u0013D\u0001\u0004PaRLwN\\\u0001\re\u0016\u0004H.Y2f\u000b6\u0004H/\u001f\u000b\u0003U9\u0002\"a\u000b\u0017\u000e\u00039I!!\f\b\u0003\r)3\u0016\r\\;f\u0011\u0015y#\u00021\u0001+\u0003\u0011)G.Z7\u0002\tM\\\u0017\u000e]\u0001\u0006g.L\u0007\u000fI\u0001\taJ,7/\u001a:wK\u0006I\u0001O]3tKJ4X\r\t"
)
public interface EmptyValueStrategy {
   static EmptyValueStrategy preserve() {
      return EmptyValueStrategy$.MODULE$.preserve();
   }

   static EmptyValueStrategy skip() {
      return EmptyValueStrategy$.MODULE$.skip();
   }

   static EmptyValueStrategy default() {
      return EmptyValueStrategy$.MODULE$.default();
   }

   Option noneValReplacement();

   JValue replaceEmpty(final JValue elem);
}
