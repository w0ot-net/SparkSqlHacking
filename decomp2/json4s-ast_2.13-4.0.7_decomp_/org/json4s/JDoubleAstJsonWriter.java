package org.json4s;

import scala.math.BigDecimal;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000513Qa\u0002\u0005\u0002*5AQA\u0005\u0001\u0005\u0002MAQ!\u0006\u0001\u0005\u0002YAQ\u0001\u000b\u0001\u0005\u0002YAQ!\u000b\u0001\u0005\u0002)BQa\r\u0001\u0005\u0002QBQ!\u000f\u0001\u0005\u0002i\u0012AC\u0013#pk\ndW-Q:u\u0015N|gn\u0016:ji\u0016\u0014(BA\u0005\u000b\u0003\u0019Q7o\u001c85g*\t1\"A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001\u001dA\u0011q\u0002E\u0007\u0002\u0011%\u0011\u0011\u0003\u0003\u0002\u0011\u0015Z\u000bG.^3Kg>twK]5uKJ\fa\u0001P5oSRtD#\u0001\u000b\u0011\u0005=\u0001\u0011AC:uCJ$\u0018I\u001d:bsR\tq\u0003E\u0002\u00101iI!!\u0007\u0005\u0003\u0015)\u001bxN\\,sSR,'\u000f\u0005\u0002\u001cK9\u0011Ad\t\b\u0003;\tr!AH\u0011\u000e\u0003}Q!\u0001\t\u0007\u0002\rq\u0012xn\u001c;?\u0013\u0005Y\u0011BA\u0005\u000b\u0013\t!\u0003\"A\u0004Kg>t\u0017i\u0015+\n\u0005\u0019:#A\u0002&WC2,XM\u0003\u0002%\u0011\u0005Y1\u000f^1si>\u0013'.Z2u\u0003\u00151Gn\\1u)\t92\u0006C\u0003-\t\u0001\u0007Q&A\u0003wC2,X\r\u0005\u0002/c5\tqFC\u00011\u0003\u0015\u00198-\u00197b\u0013\t\u0011tFA\u0003GY>\fG/\u0001\u0004e_V\u0014G.\u001a\u000b\u0003/UBQ\u0001L\u0003A\u0002Y\u0002\"AL\u001c\n\u0005az#A\u0002#pk\ndW-\u0001\u0006cS\u001e$UmY5nC2$\"aF\u001e\t\u000b12\u0001\u0019\u0001\u001f\u0011\u0005u\u0012eB\u0001 A\u001d\tqr(C\u00011\u0013\t\tu&A\u0004qC\u000e\\\u0017mZ3\n\u0005\r#%A\u0003\"jO\u0012+7-[7bY*\u0011\u0011iL\u0015\u0005\u0001\u0019C%*\u0003\u0002H\u0011\tA\"\nR8vE2,\u0017i\u001d;S_>$(j]8o/JLG/\u001a:\n\u0005%C!a\u0006&E_V\u0014G.\u001a&BeJ\f\u0017PS:p]^\u0013\u0018\u000e^3s\u0013\tY\u0005BA\fK\t>,(\r\\3K\r&,G\u000e\u001a&t_:<&/\u001b;fe\u0002"
)
public abstract class JDoubleAstJsonWriter extends JValueJsonWriter {
   public JsonWriter startArray() {
      return new JDoubleJArrayJsonWriter(this);
   }

   public JsonWriter startObject() {
      return new JDoubleJObjectJsonWriter(this);
   }

   public JsonWriter float(final float value) {
      return this.addNode(JsonAST$.MODULE$.JDouble().apply((double)value));
   }

   public JsonWriter double(final double value) {
      return this.addNode(JsonAST$.MODULE$.JDouble().apply(value));
   }

   public JsonWriter bigDecimal(final BigDecimal value) {
      return this.addNode(JsonAST$.MODULE$.JDouble().apply(value.doubleValue()));
   }
}
