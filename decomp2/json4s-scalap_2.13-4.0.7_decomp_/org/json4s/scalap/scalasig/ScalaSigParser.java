package org.json4s.scalap.scalasig;

import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005);Qa\u0002\u0005\t\u0002E1Qa\u0005\u0005\t\u0002QAQaG\u0001\u0005\u0002qAQ!H\u0001\u0005\u0002yAQAK\u0001\u0005\u0002-BQ!L\u0001\u0005\u00029BQ!L\u0001\u0005\u0002A\nabU2bY\u0006\u001c\u0016n\u001a)beN,'O\u0003\u0002\n\u0015\u0005A1oY1mCNLwM\u0003\u0002\f\u0019\u000511oY1mCBT!!\u0004\b\u0002\r)\u001cxN\u001c\u001bt\u0015\u0005y\u0011aA8sO\u000e\u0001\u0001C\u0001\n\u0002\u001b\u0005A!AD*dC2\f7+[4QCJ\u001cXM]\n\u0003\u0003U\u0001\"AF\r\u000e\u0003]Q\u0011\u0001G\u0001\u0006g\u000e\fG.Y\u0005\u00035]\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001\u0012\u0003Y\u00198-\u00197b'&<gI]8n\u0003:tw\u000e^1uS>tGCA\u0010&!\r1\u0002EI\u0005\u0003C]\u0011aa\u00149uS>t\u0007C\u0001\n$\u0013\t!\u0003B\u0001\u0005TG\u0006d\u0017mU5h\u0011\u001513\u00011\u0001(\u0003%\u0019G.Y:t\r&dW\r\u0005\u0002\u0013Q%\u0011\u0011\u0006\u0003\u0002\n\u00072\f7o\u001d$jY\u0016\fQc]2bY\u0006\u001c\u0016n\u001a$s_6\fE\u000f\u001e:jEV$X\r\u0006\u0002 Y!)a\u0005\u0002a\u0001O\u0005)\u0001/\u0019:tKR\u0011qd\f\u0005\u0006M\u0015\u0001\ra\n\u000b\u0003?EBQA\r\u0004A\u0002M\nQa\u00197buj\u0004$\u0001N!\u0011\u0007UbtH\u0004\u00027uA\u0011qgF\u0007\u0002q)\u0011\u0011\bE\u0001\u0007yI|w\u000e\u001e \n\u0005m:\u0012A\u0002)sK\u0012,g-\u0003\u0002>}\t)1\t\\1tg*\u00111h\u0006\t\u0003\u0001\u0006c\u0001\u0001B\u0005Cc\u0005\u0005\t\u0011!B\u0001\u0007\n\u0019q\fJ\u0019\u0012\u0005\u0011;\u0005C\u0001\fF\u0013\t1uCA\u0004O_RD\u0017N\\4\u0011\u0005YA\u0015BA%\u0018\u0005\r\te.\u001f"
)
public final class ScalaSigParser {
   public static Option parse(final Class clazz) {
      return ScalaSigParser$.MODULE$.parse(clazz);
   }

   public static Option parse(final ClassFile classFile) {
      return ScalaSigParser$.MODULE$.parse(classFile);
   }

   public static Option scalaSigFromAttribute(final ClassFile classFile) {
      return ScalaSigParser$.MODULE$.scalaSigFromAttribute(classFile);
   }

   public static Option scalaSigFromAnnotation(final ClassFile classFile) {
      return ScalaSigParser$.MODULE$.scalaSigFromAnnotation(classFile);
   }
}
