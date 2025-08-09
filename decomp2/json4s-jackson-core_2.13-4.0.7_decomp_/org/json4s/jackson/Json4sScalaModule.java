package org.json4s.jackson;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I3A\u0001C\u0005\u0001!!)A\u0004\u0001C\u0001;!)\u0001\u0005\u0001C\u0001C!)q\u0006\u0001C\u0001a!)q\u0007\u0001C\u0001q\u001d)a*\u0003E\u0001\u001f\u001a)\u0001\"\u0003E\u0001!\")AD\u0002C\u0001#\n\t\"j]8oiM\u001c6-\u00197b\u001b>$W\u000f\\3\u000b\u0005)Y\u0011a\u00026bG.\u001cxN\u001c\u0006\u0003\u00195\taA[:p]R\u001a(\"\u0001\b\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u0001\t\u0002C\u0001\n\u001b\u001b\u0005\u0019\"B\u0001\u000b\u0016\u0003!!\u0017\r^1cS:$'B\u0001\u0006\u0017\u0015\t9\u0002$A\u0005gCN$XM\u001d=nY*\t\u0011$A\u0002d_6L!aG\n\u0003\r5{G-\u001e7f\u0003\u0019a\u0014N\\5u}Q\ta\u0004\u0005\u0002 \u00015\t\u0011\"A\u0007hKRlu\u000eZ;mK:\u000bW.\u001a\u000b\u0002EA\u00111\u0005\f\b\u0003I)\u0002\"!\n\u0015\u000e\u0003\u0019R!aJ\b\u0002\rq\u0012xn\u001c;?\u0015\u0005I\u0013!B:dC2\f\u0017BA\u0016)\u0003\u0019\u0001&/\u001a3fM&\u0011QF\f\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005-B\u0013a\u0002<feNLwN\u001c\u000b\u0002cA\u0011!'N\u0007\u0002g)\u0011A'F\u0001\u0005G>\u0014X-\u0003\u00027g\t9a+\u001a:tS>t\u0017aC:fiV\u0004Xj\u001c3vY\u0016$\"!O\u001f\u0011\u0005iZT\"\u0001\u0015\n\u0005qB#\u0001B+oSRDQA\u0010\u0003A\u0002}\nAa\u0019;yiB\u0011\u0001i\u0013\b\u0003\u0003&s!A\u0011%\u000f\u0005\r;eB\u0001#G\u001d\t)S)C\u0001\u001a\u0013\t9\u0002$\u0003\u0002\u000b-%\u0011A#F\u0005\u0003\u0015N\ta!T8ek2,\u0017B\u0001'N\u00051\u0019V\r^;q\u0007>tG/\u001a=u\u0015\tQ5#A\tKg>tGg]*dC2\fWj\u001c3vY\u0016\u0004\"a\b\u0004\u0014\u0005\u0019qB#A("
)
public class Json4sScalaModule extends Module {
   public String getModuleName() {
      return "Json4sScalaModule";
   }

   public Version version() {
      return Json4sModule$.MODULE$.version();
   }

   public void setupModule(final Module.SetupContext ctxt) {
      ctxt.addSerializers(JValueSerializerResolver$.MODULE$);
      ctxt.addDeserializers(JValueDeserializerResolver$.MODULE$);
   }
}
