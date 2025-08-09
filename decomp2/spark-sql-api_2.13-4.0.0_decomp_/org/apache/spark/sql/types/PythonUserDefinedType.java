package org.apache.spark.sql.types;

import java.lang.invoke.SerializedLambda;
import java.util.Objects;
import org.json4s.JValue;
import org.json4s.JsonListAssoc.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M4Qa\u0004\t\u0001%iA\u0001\"\n\u0001\u0003\u0006\u0004%\ta\n\u0005\tW\u0001\u0011\t\u0011)A\u0005Q!AA\u0006\u0001BC\u0002\u0013\u0005S\u0006\u0003\u0005:\u0001\t\u0005\t\u0015!\u0003/\u0011!Q\u0004A!b\u0001\n\u0003j\u0003\u0002C\u001e\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0018\t\u000bq\u0002A\u0011A\u001f\t\u000b\t\u0003A\u0011I\"\t\u000b\u0019\u0003A\u0011I$\t\u000b)\u0003A\u0011I&\t\rQ\u0003A\u0011\t\nV\u0011\u0019\u0019\u0007\u0001\"\u0011\u0013I\")!\u000e\u0001C!W\")a\u000e\u0001C!_\n)\u0002+\u001f;i_:,6/\u001a:EK\u001aLg.\u001a3UsB,'BA\t\u0013\u0003\u0015!\u0018\u0010]3t\u0015\t\u0019B#A\u0002tc2T!!\u0006\f\u0002\u000bM\u0004\u0018M]6\u000b\u0005]A\u0012AB1qC\u000eDWMC\u0001\u001a\u0003\ry'oZ\n\u0003\u0001m\u00012\u0001H\u000f \u001b\u0005\u0001\u0012B\u0001\u0010\u0011\u0005=)6/\u001a:EK\u001aLg.\u001a3UsB,\u0007C\u0001\u0011$\u001b\u0005\t#\"\u0001\u0012\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0011\n#aA!os\u000691/\u001d7UsB,7\u0001A\u000b\u0002QA\u0011A$K\u0005\u0003UA\u0011\u0001\u0002R1uCRK\b/Z\u0001\tgFdG+\u001f9fA\u0005)\u0001/_+E)V\ta\u0006\u0005\u00020m9\u0011\u0001\u0007\u000e\t\u0003c\u0005j\u0011A\r\u0006\u0003g\u0019\na\u0001\u0010:p_Rt\u0014BA\u001b\"\u0003\u0019\u0001&/\u001a3fM&\u0011q\u0007\u000f\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005U\n\u0013A\u00029z+\u0012#\u0006%A\ttKJL\u0017\r\\5{K\u0012\u0004\u0016p\u00117bgN\f!c]3sS\u0006d\u0017N_3e!f\u001cE.Y:tA\u00051A(\u001b8jiz\"BAP A\u0003B\u0011A\u0004\u0001\u0005\u0006K\u001d\u0001\r\u0001\u000b\u0005\u0006Y\u001d\u0001\rA\f\u0005\u0006u\u001d\u0001\rAL\u0001\ng\u0016\u0014\u0018.\u00197ju\u0016$\"a\b#\t\u000b\u0015C\u0001\u0019A\u0010\u0002\u0007=\u0014'.A\u0006eKN,'/[1mSj,GCA\u0010I\u0011\u0015I\u0015\u00021\u0001 \u0003\u0015!\u0017\r^1n\u0003%)8/\u001a:DY\u0006\u001c8/F\u0001M!\ri%kH\u0007\u0002\u001d*\u0011q\nU\u0001\u0005Y\u0006twMC\u0001R\u0003\u0011Q\u0017M^1\n\u0005Ms%!B\"mCN\u001c\u0018!\u00036t_:4\u0016\r\\;f+\u00051\u0006CA,a\u001d\tAVL\u0004\u0002Z7:\u0011\u0011GW\u0005\u00023%\u0011A\fG\u0001\u0007UN|g\u000eN:\n\u0005y{\u0016a\u0002&t_:\f5\u000b\u0016\u0006\u00039bI!!\u00192\u0003\r)3\u0016\r\\;f\u0015\tqv,A\u0006bG\u000e,\u0007\u000f^:UsB,GCA3i!\t\u0001c-\u0003\u0002hC\t9!i\\8mK\u0006t\u0007\"B5\r\u0001\u0004A\u0013\u0001\u00033bi\u0006$\u0016\u0010]3\u0002\r\u0015\fX/\u00197t)\t)G\u000eC\u0003n\u001b\u0001\u0007q$A\u0003pi\",'/\u0001\u0005iCND7i\u001c3f)\u0005\u0001\bC\u0001\u0011r\u0013\t\u0011\u0018EA\u0002J]R\u0004"
)
public class PythonUserDefinedType extends UserDefinedType {
   private final DataType sqlType;
   private final String pyUDT;
   private final String serializedPyClass;

   public DataType sqlType() {
      return this.sqlType;
   }

   public String pyUDT() {
      return this.pyUDT;
   }

   public String serializedPyClass() {
      return this.serializedPyClass;
   }

   public Object serialize(final Object obj) {
      return obj;
   }

   public Object deserialize(final Object datam) {
      return datam;
   }

   public Class userClass() {
      return null;
   }

   public JValue jsonValue() {
      return .MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(.MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("type"), "udt"), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("pyClass"), this.pyUDT()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("serializedClass"), this.serializedPyClass()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("sqlType"), this.sqlType().jsonValue()));
   }

   public boolean acceptsType(final DataType dataType) {
      if (!(dataType instanceof PythonUserDefinedType var4)) {
         return false;
      } else {
         boolean var6;
         label30: {
            String var10000 = this.pyUDT();
            String var5 = var4.pyUDT();
            if (var10000 == null) {
               if (var5 == null) {
                  break label30;
               }
            } else if (var10000.equals(var5)) {
               break label30;
            }

            var6 = false;
            return var6;
         }

         var6 = true;
         return var6;
      }
   }

   public boolean equals(final Object other) {
      if (!(other instanceof PythonUserDefinedType var4)) {
         return false;
      } else {
         boolean var6;
         label30: {
            String var10000 = this.pyUDT();
            String var5 = var4.pyUDT();
            if (var10000 == null) {
               if (var5 == null) {
                  break label30;
               }
            } else if (var10000.equals(var5)) {
               break label30;
            }

            var6 = false;
            return var6;
         }

         var6 = true;
         return var6;
      }
   }

   public int hashCode() {
      return Objects.hashCode(this.pyUDT());
   }

   public PythonUserDefinedType(final DataType sqlType, final String pyUDT, final String serializedPyClass) {
      this.sqlType = sqlType;
      this.pyUDT = pyUDT;
      this.serializedPyClass = serializedPyClass;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
