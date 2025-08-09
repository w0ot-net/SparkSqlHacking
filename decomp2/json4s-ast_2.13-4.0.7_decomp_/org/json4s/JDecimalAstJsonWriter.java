package org.json4s;

import scala.math.BigDecimal;
import scala.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000513Qa\u0002\u0005\u0002*5AQA\u0005\u0001\u0005\u0002MAQ!\u0006\u0001\u0005\u0002YAQ\u0001\u000b\u0001\u0005\u0002YAQ!\u000b\u0001\u0005\u0002)BQa\r\u0001\u0005\u0002QBQ!\u000f\u0001\u0005\u0002i\u0012QC\u0013#fG&l\u0017\r\\!ti*\u001bxN\\,sSR,'O\u0003\u0002\n\u0015\u00051!n]8oiMT\u0011aC\u0001\u0004_J<7\u0001A\n\u0003\u00019\u0001\"a\u0004\t\u000e\u0003!I!!\u0005\u0005\u0003!)3\u0016\r\\;f\u0015N|gn\u0016:ji\u0016\u0014\u0018A\u0002\u001fj]&$h\bF\u0001\u0015!\ty\u0001!\u0001\u0006ti\u0006\u0014H/\u0011:sCf$\u0012a\u0006\t\u0004\u001faQ\u0012BA\r\t\u0005)Q5o\u001c8Xe&$XM\u001d\t\u00037\u0015r!\u0001H\u0012\u000f\u0005u\u0011cB\u0001\u0010\"\u001b\u0005y\"B\u0001\u0011\r\u0003\u0019a$o\\8u}%\t1\"\u0003\u0002\n\u0015%\u0011A\u0005C\u0001\b\u0015N|g.Q*U\u0013\t1sE\u0001\u0004K-\u0006dW/\u001a\u0006\u0003I!\t1b\u001d;beR|%M[3di\u0006)a\r\\8biR\u0011qc\u000b\u0005\u0006Y\u0011\u0001\r!L\u0001\u0006m\u0006dW/\u001a\t\u0003]Ej\u0011a\f\u0006\u0002a\u0005)1oY1mC&\u0011!g\f\u0002\u0006\r2|\u0017\r^\u0001\u0007I>,(\r\\3\u0015\u0005])\u0004\"\u0002\u0017\u0006\u0001\u00041\u0004C\u0001\u00188\u0013\tAtF\u0001\u0004E_V\u0014G.Z\u0001\u000bE&<G)Z2j[\u0006dGCA\f<\u0011\u0015ac\u00011\u0001=!\ti$I\u0004\u0002?\u0001:\u0011adP\u0005\u0002a%\u0011\u0011iL\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0019EI\u0001\u0006CS\u001e$UmY5nC2T!!Q\u0018*\t\u00011\u0005JS\u0005\u0003\u000f\"\u0011\u0011D\u0013#fG&l\u0017\r\\!tiJ{w\u000e\u001e&t_:<&/\u001b;fe&\u0011\u0011\n\u0003\u0002\u0019\u0015\u0012+7-[7bY*\u000b%O]1z\u0015N|gn\u0016:ji\u0016\u0014\u0018BA&\t\u0005aQE)Z2j[\u0006d'JR5fY\u0012T5o\u001c8Xe&$XM\u001d"
)
public abstract class JDecimalAstJsonWriter extends JValueJsonWriter {
   public JsonWriter startArray() {
      return new JDecimalJArrayJsonWriter(this);
   }

   public JsonWriter startObject() {
      return new JDecimalJObjectJsonWriter(this);
   }

   public JsonWriter float(final float value) {
      return this.double((double)value);
   }

   public JsonWriter double(final double value) {
      return this.addNode(JsonAST$.MODULE$.JDecimal().apply(.MODULE$.BigDecimal().apply(value)));
   }

   public JsonWriter bigDecimal(final BigDecimal value) {
      return this.addNode(JsonAST$.MODULE$.JDecimal().apply(value));
   }
}
