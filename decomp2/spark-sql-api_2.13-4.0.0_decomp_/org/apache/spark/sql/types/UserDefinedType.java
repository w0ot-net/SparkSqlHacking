package org.apache.spark.sql.types;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.DeveloperApi;
import org.json4s.JValue;
import org.json4s.JsonListAssoc.;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ec!B\u000b\u0017\u0003\u0003\t\u0003\"B\u001b\u0001\t\u00031\u0004\"\u0002#\u0001\r\u0003)\u0005\"\u0002$\u0001\t\u00039\u0005\"\u0002)\u0001\t\u00039\u0005\"B)\u0001\r\u0003\u0011\u0006\"B+\u0001\r\u00031\u0006BB-\u0001\t\u0003B\"\fC\u0003i\u0001\u0019\u0005\u0011\u000eC\u0003s\u0001\u0011\u00053\u000f\u0003\u0004x\u0001\u0011\u0005#\u0004\u001f\u0005\u0007s\u0002!\t\u0005\u0007>\t\u000be\u0001A\u0011I$\t\u000f\u0005\u0005\u0001\u0001\"\u0011\u0002\u0004!9\u0011Q\u0001\u0001\u0005B\u0005\u001d\u0001BBA\u0007\u0001\u0011\u0005si\u0002\u0005\u0002*YA\tAGA\u0016\r\u001d)b\u0003#\u0001\u001b\u0003[Aa!N\t\u0005\u0002\u0005}\u0002B\u0002#\u0012\t\u0003\t\t\u0005C\u0005\u0002HE\t\t\u0011\"\u0003\u0002J\tyQk]3s\t\u00164\u0017N\\3e)f\u0004XM\u0003\u0002\u00181\u0005)A/\u001f9fg*\u0011\u0011DG\u0001\u0004gFd'BA\u000e\u001d\u0003\u0015\u0019\b/\u0019:l\u0015\tib$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002?\u0005\u0019qN]4\u0004\u0001U\u0011!EO\n\u0004\u0001\r:\u0003C\u0001\u0013&\u001b\u00051\u0012B\u0001\u0014\u0017\u0005!!\u0015\r^1UsB,\u0007C\u0001\u00153\u001d\tIsF\u0004\u0002+[5\t1F\u0003\u0002-A\u00051AH]8pizJ\u0011AL\u0001\u0006g\u000e\fG.Y\u0005\u0003aE\nq\u0001]1dW\u0006<WMC\u0001/\u0013\t\u0019DG\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00021c\u00051A(\u001b8jiz\"\u0012a\u000e\t\u0004I\u0001A\u0004CA\u001d;\u0019\u0001!Qa\u000f\u0001C\u0002q\u0012\u0001\"V:feRK\b/Z\t\u0003{\u0005\u0003\"AP \u000e\u0003EJ!\u0001Q\u0019\u0003\t9+H\u000e\u001c\t\u0003}\tK!aQ\u0019\u0003\u0007\u0005s\u00170A\u0004tc2$\u0016\u0010]3\u0016\u0003\r\nQ\u0001]=V\tR+\u0012\u0001\u0013\t\u0003\u00136s!AS&\u0011\u0005)\n\u0014B\u0001'2\u0003\u0019\u0001&/\u001a3fM&\u0011aj\u0014\u0002\u0007'R\u0014\u0018N\\4\u000b\u00051\u000b\u0014!E:fe&\fG.\u001b>fIBK8\t\\1tg\u0006I1/\u001a:jC2L'0\u001a\u000b\u0003\u0003NCQ\u0001V\u0003A\u0002a\n1a\u001c2k\u0003-!Wm]3sS\u0006d\u0017N_3\u0015\u0005a:\u0006\"\u0002-\u0007\u0001\u0004\t\u0015!\u00023biVl\u0017!\u00036t_:4\u0016\r\\;f+\u0005Y\u0006C\u0001/f\u001d\ti&M\u0004\u0002_A:\u0011!fX\u0005\u0002?%\u0011\u0011MH\u0001\u0007UN|g\u000eN:\n\u0005\r$\u0017a\u0002&t_:\f5\u000b\u0016\u0006\u0003CzI!AZ4\u0003\r)3\u0016\r\\;f\u0015\t\u0019G-A\u0005vg\u0016\u00148\t\\1tgV\t!\u000eE\u0002labj\u0011\u0001\u001c\u0006\u0003[:\fA\u0001\\1oO*\tq.\u0001\u0003kCZ\f\u0017BA9m\u0005\u0015\u0019E.Y:t\u0003-!WMZ1vYR\u001c\u0016N_3\u0016\u0003Q\u0004\"AP;\n\u0005Y\f$aA%oi\u0006Q\u0011m\u001d(vY2\f'\r\\3\u0016\u0003]\n1\"Y2dKB$8\u000fV=qKR\u00111P \t\u0003}qL!!`\u0019\u0003\u000f\t{w\u000e\\3b]\")qp\u0003a\u0001G\u0005AA-\u0019;b)f\u0004X-\u0001\u0005iCND7i\u001c3f)\u0005!\u0018AB3rk\u0006d7\u000fF\u0002|\u0003\u0013Aa!a\u0003\u000f\u0001\u0004\t\u0015!B8uQ\u0016\u0014\u0018!D2bi\u0006dwnZ*ue&tw\rK\u0002\u0001\u0003#\u0001B!a\u0005\u0002\u001a5\u0011\u0011Q\u0003\u0006\u0004\u0003/Q\u0012AC1o]>$\u0018\r^5p]&!\u00111DA\u000b\u00051!UM^3m_B,'/\u00119jQ\u0015\u0001\u0011qDA\u0013!\u0011\t\u0019\"!\t\n\t\u0005\r\u0012Q\u0003\u0002\u0006'&t7-Z\u0011\u0003\u0003O\tQa\r\u00183]A\nq\"V:fe\u0012+g-\u001b8fIRK\b/\u001a\t\u0003IE\u0019R!EA\u0018\u0003k\u00012APA\u0019\u0013\r\t\u0019$\r\u0002\u0007\u0003:L(+\u001a4\u0011\t\u0005]\u0012QH\u0007\u0003\u0003sQ1!a\u000fo\u0003\tIw.C\u00024\u0003s!\"!a\u000b\u0015\u0007\r\n\u0019\u0005\u0003\u0004\u0002FM\u0001\raI\u0001\u0003IR\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\u0013\u0011\u0007-\fi%C\u0002\u0002P1\u0014aa\u00142kK\u000e$\b"
)
public abstract class UserDefinedType extends DataType implements Serializable {
   public abstract DataType sqlType();

   public String pyUDT() {
      return null;
   }

   public String serializedPyClass() {
      return null;
   }

   public abstract Object serialize(final Object obj);

   public abstract Object deserialize(final Object datum);

   public JValue jsonValue() {
      return .MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(.MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("type"), "udt"), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.getClass().getName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("pyClass"), this.pyUDT()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("sqlType"), this.sqlType().jsonValue()));
   }

   public abstract Class userClass();

   public int defaultSize() {
      return this.sqlType().defaultSize();
   }

   public UserDefinedType asNullable() {
      return this;
   }

   public boolean acceptsType(final DataType dataType) {
      if (dataType instanceof UserDefinedType var4) {
         if (this.userClass() != null && var4.userClass() != null) {
            boolean var6;
            label24: {
               Class var10000 = this.getClass();
               Class var5 = var4.getClass();
               if (var10000 == null) {
                  if (var5 == null) {
                     break label24;
                  }
               } else if (var10000.equals(var5)) {
                  break label24;
               }

               if (!this.userClass().isAssignableFrom(var4.userClass())) {
                  var6 = false;
                  return var6;
               }
            }

            var6 = true;
            return var6;
         }
      }

      return false;
   }

   public String sql() {
      return this.sqlType().sql();
   }

   public int hashCode() {
      return this.getClass().hashCode();
   }

   public boolean equals(final Object other) {
      if (!(other instanceof UserDefinedType var4)) {
         return false;
      } else {
         boolean var6;
         label30: {
            Class var10000 = this.getClass();
            Class var5 = var4.getClass();
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

   public String catalogString() {
      return this.sqlType().simpleString();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
