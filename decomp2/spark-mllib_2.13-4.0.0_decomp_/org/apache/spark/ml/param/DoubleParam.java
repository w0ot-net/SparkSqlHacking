package org.apache.spark.ml.param;

import org.apache.spark.ml.util.Identifiable;
import org.json4s.JValue;
import org.json4s.jackson.JsonMethods.;
import scala.Function1;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ua\u0001\u0002\n\u0014\u0001yA\u0011\"\u000b\u0001\u0003\u0002\u0003\u0006IAK\u001b\t\u0013Y\u0002!\u0011!Q\u0001\n):\u0004\"\u0003\u001d\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0016:\u0011%Q\u0004A!A!\u0002\u0013Y\u0014\tC\u0003C\u0001\u0011\u00051\tC\u0003C\u0001\u0011\u0005\u0011\nC\u0003C\u0001\u0011\u0005Q\nC\u0003C\u0001\u0011\u0005\u0001\fC\u0003]\u0001\u0011\u0005S\fC\u0003d\u0001\u0011\u0005C\rC\u0003g\u0001\u0011\u0005sm\u0002\u0004k'!\u00051c\u001b\u0004\u0007%MA\ta\u00057\t\u000b\tkA\u0011\u0001=\t\u000belA\u0011\u0001>\t\u000f\u0005\u0015Q\u0002\"\u0001\u0002\b!I\u0011QB\u0007\u0002\u0002\u0013%\u0011q\u0002\u0002\f\t>,(\r\\3QCJ\fWN\u0003\u0002\u0015+\u0005)\u0001/\u0019:b[*\u0011acF\u0001\u0003[2T!\u0001G\r\u0002\u000bM\u0004\u0018M]6\u000b\u0005iY\u0012AB1qC\u000eDWMC\u0001\u001d\u0003\ry'oZ\u0002\u0001'\t\u0001q\u0004E\u0002!C\rj\u0011aE\u0005\u0003EM\u0011Q\u0001U1sC6\u0004\"\u0001J\u0014\u000e\u0003\u0015R\u0011AJ\u0001\u0006g\u000e\fG.Y\u0005\u0003Q\u0015\u0012a\u0001R8vE2,\u0017A\u00029be\u0016tG\u000f\u0005\u0002,e9\u0011A\u0006\r\t\u0003[\u0015j\u0011A\f\u0006\u0003_u\ta\u0001\u0010:p_Rt\u0014BA\u0019&\u0003\u0019\u0001&/\u001a3fM&\u00111\u0007\u000e\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005E*\u0013BA\u0015\"\u0003\u0011q\u0017-\\3\n\u0005Y\n\u0013a\u00013pG&\u0011\u0001(I\u0001\bSN4\u0016\r\\5e!\u0011!Ch\t \n\u0005u*#!\u0003$v]\u000e$\u0018n\u001c82!\t!s(\u0003\u0002AK\t9!i\\8mK\u0006t\u0017B\u0001\u001e\"\u0003\u0019a\u0014N\\5u}Q)A)\u0012$H\u0011B\u0011\u0001\u0005\u0001\u0005\u0006S\u0015\u0001\rA\u000b\u0005\u0006m\u0015\u0001\rA\u000b\u0005\u0006q\u0015\u0001\rA\u000b\u0005\u0006u\u0015\u0001\ra\u000f\u000b\u0005\t*[E\nC\u0003*\r\u0001\u0007!\u0006C\u00037\r\u0001\u0007!\u0006C\u00039\r\u0001\u0007!\u0006F\u0003E\u001dV3v\u000bC\u0003*\u000f\u0001\u0007q\n\u0005\u0002Q'6\t\u0011K\u0003\u0002S+\u0005!Q\u000f^5m\u0013\t!\u0016K\u0001\u0007JI\u0016tG/\u001b4jC\ndW\rC\u00037\u000f\u0001\u0007!\u0006C\u00039\u000f\u0001\u0007!\u0006C\u0003;\u000f\u0001\u00071\b\u0006\u0003E3j[\u0006\"B\u0015\t\u0001\u0004y\u0005\"\u0002\u001c\t\u0001\u0004Q\u0003\"\u0002\u001d\t\u0001\u0004Q\u0013!A<\u0015\u0005y\u000b\u0007c\u0001\u0011`G%\u0011\u0001m\u0005\u0002\n!\u0006\u0014\u0018-\u001c)bSJDQAY\u0005A\u0002\r\nQA^1mk\u0016\f!B[:p]\u0016s7m\u001c3f)\tQS\rC\u0003c\u0015\u0001\u00071%\u0001\u0006kg>tG)Z2pI\u0016$\"a\t5\t\u000b%\\\u0001\u0019\u0001\u0016\u0002\t)\u001cxN\\\u0001\f\t>,(\r\\3QCJ\fW\u000e\u0005\u0002!\u001bM\u0019Q\"\u001c9\u0011\u0005\u0011r\u0017BA8&\u0005\u0019\te.\u001f*fMB\u0011\u0011O^\u0007\u0002e*\u00111\u000f^\u0001\u0003S>T\u0011!^\u0001\u0005U\u00064\u0018-\u0003\u0002xe\na1+\u001a:jC2L'0\u00192mKR\t1.\u0001\u0007k-\u0006dW/Z#oG>$W\rF\u0002|\u0003\u0007\u0001\"\u0001`@\u000e\u0003uT!A`\u000e\u0002\r)\u001cxN\u001c\u001bt\u0013\r\t\t! \u0002\u0007\u0015Z\u000bG.^3\t\u000b\t|\u0001\u0019A\u0012\u0002\u0019)4\u0016\r\\;f\t\u0016\u001cw\u000eZ3\u0015\u0007\r\nI\u0001\u0003\u0004\u0002\fA\u0001\ra_\u0001\u0007UZ\u000bG.^3\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005E\u0001\u0003BA\n\u00033i!!!\u0006\u000b\u0007\u0005]A/\u0001\u0003mC:<\u0017\u0002BA\u000e\u0003+\u0011aa\u00142kK\u000e$\b"
)
public class DoubleParam extends Param {
   public static double jValueDecode(final JValue jValue) {
      return DoubleParam$.MODULE$.jValueDecode(jValue);
   }

   public static JValue jValueEncode(final double value) {
      return DoubleParam$.MODULE$.jValueEncode(value);
   }

   public ParamPair w(final double value) {
      return super.w(BoxesRunTime.boxToDouble(value));
   }

   public String jsonEncode(final double value) {
      return .MODULE$.compact(.MODULE$.render(DoubleParam$.MODULE$.jValueEncode(value), .MODULE$.render$default$2(), .MODULE$.render$default$3()));
   }

   public double jsonDecode(final String json) {
      return DoubleParam$.MODULE$.jValueDecode(.MODULE$.parse(json, .MODULE$.parse$default$2(), .MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput()));
   }

   public DoubleParam(final String parent, final String name, final String doc, final Function1 isValid) {
      super((String)parent, name, doc, isValid, scala.reflect.ClassTag..MODULE$.Double());
   }

   public DoubleParam(final String parent, final String name, final String doc) {
      this(parent, name, doc, ParamValidators$.MODULE$.alwaysTrue());
   }

   public DoubleParam(final Identifiable parent, final String name, final String doc, final Function1 isValid) {
      this(parent.uid(), name, doc, isValid);
   }

   public DoubleParam(final Identifiable parent, final String name, final String doc) {
      this(parent.uid(), name, doc);
   }
}
