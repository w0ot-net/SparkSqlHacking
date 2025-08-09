package org.apache.spark.ml.param;

import java.io.Serializable;
import org.apache.spark.ml.linalg.JsonMatrixConverter$;
import org.apache.spark.ml.linalg.JsonVectorConverter$;
import org.apache.spark.ml.linalg.Matrix;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.util.Identifiable;
import org.json4s.JString;
import scala.Function1;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ue\u0001\u0002\u0010 \u0001)B\u0001B\u0010\u0001\u0003\u0006\u0004%\ta\u0010\u0005\t\u0011\u0002\u0011\t\u0011)A\u0005\u0001\"A\u0011\n\u0001BC\u0002\u0013\u0005q\b\u0003\u0005K\u0001\t\u0005\t\u0015!\u0003A\u0011!Y\u0005A!b\u0001\n\u0003y\u0004\u0002\u0003'\u0001\u0005\u0003\u0005\u000b\u0011\u0002!\t\u00115\u0003!Q1A\u0005\u00029C\u0001\u0002\u0019\u0001\u0003\u0002\u0003\u0006Ia\u0014\u0005\tC\u0002\u0011\u0019\u0011)A\u0006E\")\u0001\u000e\u0001C\u0001S\"9!\u000f\u0001b\u0001\n\u0003\u0019\bB\u0002;\u0001A\u0003%!\rC\u0003i\u0001\u0011\u0005Q\u000f\u0003\u0004i\u0001\u0011\u0005\u0011q\u0001\u0005\u0007Q\u0002!\t!!\u0006\t\u0011\u0005\r\u0002\u0001\"\u0001 \u0003KAq!!\r\u0001\t\u0003\t\u0019\u0004C\u0004\u0002>\u0001!\t!a\u0010\t\u000f\u0005\r\u0003\u0001\"\u0001\u0002F!9\u0011\u0011\n\u0001\u0005\u0002\u0005-\u0003\u0002CA)\u0001\u0001\u0006I!a\u0015\t\u000f\u0005\u0005\u0004\u0001\"\u0012\u0002d!9\u0011Q\r\u0001\u0005F\u0005\u001d\u0004bBA8\u0001\u0011\u0015\u0013\u0011O\u0004\t\u0003oz\u0002\u0012A\u0011\u0002z\u00199ad\bE\u0001C\u0005m\u0004B\u00025\u001b\t\u0003\t9\tC\u0004\u0002Ji!\t!!#\t\u0013\u0005M%$!A\u0005\n\u0005U%!\u0002)be\u0006l'B\u0001\u0011\"\u0003\u0015\u0001\u0018M]1n\u0015\t\u00113%\u0001\u0002nY*\u0011A%J\u0001\u0006gB\f'o\u001b\u0006\u0003M\u001d\na!\u00199bG\",'\"\u0001\u0015\u0002\u0007=\u0014xm\u0001\u0001\u0016\u0005-\"6c\u0001\u0001-eA\u0011Q\u0006M\u0007\u0002])\tq&A\u0003tG\u0006d\u0017-\u0003\u00022]\t1\u0011I\\=SK\u001a\u0004\"aM\u001e\u000f\u0005QJdBA\u001b9\u001b\u00051$BA\u001c*\u0003\u0019a$o\\8u}%\tq&\u0003\u0002;]\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u001f>\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tQd&\u0001\u0004qCJ,g\u000e^\u000b\u0002\u0001B\u0011\u0011)\u0012\b\u0003\u0005\u000e\u0003\"!\u000e\u0018\n\u0005\u0011s\u0013A\u0002)sK\u0012,g-\u0003\u0002G\u000f\n11\u000b\u001e:j]\u001eT!\u0001\u0012\u0018\u0002\u000fA\f'/\u001a8uA\u0005!a.Y7f\u0003\u0015q\u0017-\\3!\u0003\r!wnY\u0001\u0005I>\u001c\u0007%A\u0004jgZ\u000bG.\u001b3\u0016\u0003=\u0003B!\f)S;&\u0011\u0011K\f\u0002\n\rVt7\r^5p]F\u0002\"a\u0015+\r\u0001\u0011)Q\u000b\u0001b\u0001-\n\tA+\u0005\u0002X5B\u0011Q\u0006W\u0005\u00033:\u0012qAT8uQ&tw\r\u0005\u0002.7&\u0011AL\f\u0002\u0004\u0003:L\bCA\u0017_\u0013\tyfFA\u0004C_>dW-\u00198\u0002\u0011%\u001ch+\u00197jI\u0002\n!\"\u001a<jI\u0016t7-\u001a\u00132!\r\u0019gMU\u0007\u0002I*\u0011QML\u0001\be\u00164G.Z2u\u0013\t9GM\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003\u0019a\u0014N\\5u}Q)!N\\8qcR\u00111.\u001c\t\u0004Y\u0002\u0011V\"A\u0010\t\u000b\u0005T\u00019\u00012\t\u000byR\u0001\u0019\u0001!\t\u000b%S\u0001\u0019\u0001!\t\u000b-S\u0001\u0019\u0001!\t\u000b5S\u0001\u0019A(\u0002%A\f'/Y7WC2,Xm\u00117bgN$\u0016mZ\u000b\u0002E\u0006\u0019\u0002/\u0019:b[Z\u000bG.^3DY\u0006\u001c8\u000fV1hAQAa/_A\u0001\u0003\u0007\t)\u0001\u0006\u0002lo\"9\u00010DA\u0001\u0002\b\u0011\u0017AC3wS\u0012,gnY3%e!)a(\u0004a\u0001uB\u00111P`\u0007\u0002y*\u0011Q0I\u0001\u0005kRLG.\u0003\u0002\u0000y\na\u0011\nZ3oi&4\u0017.\u00192mK\")\u0011*\u0004a\u0001\u0001\")1*\u0004a\u0001\u0001\")Q*\u0004a\u0001\u001fRA\u0011\u0011BA\b\u0003#\t\u0019\u0002F\u0002l\u0003\u0017A\u0001\"!\u0004\u000f\u0003\u0003\u0005\u001dAY\u0001\u000bKZLG-\u001a8dK\u0012\u001a\u0004\"\u0002 \u000f\u0001\u0004\u0001\u0005\"B%\u000f\u0001\u0004\u0001\u0005\"B&\u000f\u0001\u0004\u0001E\u0003CA\f\u0003;\ty\"!\t\u0015\u0007-\fI\u0002\u0003\u0005\u0002\u001c=\t\t\u0011q\u0001c\u0003))g/\u001b3f]\u000e,G\u0005\u000e\u0005\u0006}=\u0001\rA\u001f\u0005\u0006\u0013>\u0001\r\u0001\u0011\u0005\u0006\u0017>\u0001\r\u0001Q\u0001\tm\u0006d\u0017\u000eZ1uKR!\u0011qEA\u0017!\ri\u0013\u0011F\u0005\u0004\u0003Wq#\u0001B+oSRDa!a\f\u0011\u0001\u0004\u0011\u0016!\u0002<bYV,\u0017!A<\u0015\t\u0005U\u00121\b\t\u0005Y\u0006]\"+C\u0002\u0002:}\u0011\u0011\u0002U1sC6\u0004\u0016-\u001b:\t\r\u0005=\u0012\u00031\u0001S\u00039!S.\u001b8vg\u0012:'/Z1uKJ$B!!\u000e\u0002B!1\u0011q\u0006\nA\u0002I\u000b!B[:p]\u0016s7m\u001c3f)\r\u0001\u0015q\t\u0005\u0007\u0003_\u0019\u0002\u0019\u0001*\u0002\u0015)\u001cxN\u001c#fG>$W\rF\u0002S\u0003\u001bBa!a\u0014\u0015\u0001\u0004\u0001\u0015\u0001\u00026t_:\fAc\u001d;sS:<'+\u001a9sKN,g\u000e^1uS>t\u0007\u0003BA+\u0003?j!!a\u0016\u000b\t\u0005e\u00131L\u0001\u0005Y\u0006twM\u0003\u0002\u0002^\u0005!!.\u0019<b\u0013\r1\u0015qK\u0001\ti>\u001cFO]5oOR\t\u0001)\u0001\u0005iCND7i\u001c3f)\t\tI\u0007E\u0002.\u0003WJ1!!\u001c/\u0005\rIe\u000e^\u0001\u0007KF,\u0018\r\\:\u0015\u0007u\u000b\u0019\b\u0003\u0004\u0002va\u0001\rAW\u0001\u0004_\nT\u0017!\u0002)be\u0006l\u0007C\u00017\u001b'\u0011QB&! \u0011\t\u0005}\u0014QQ\u0007\u0003\u0003\u0003SA!a!\u0002\\\u0005\u0011\u0011n\\\u0005\u0004y\u0005\u0005ECAA=+\u0011\tY)a$\u0015\t\u00055\u0015\u0011\u0013\t\u0004'\u0006=E!B+\u001d\u0005\u00041\u0006BBA(9\u0001\u0007\u0001)\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\u0018B!\u0011QKAM\u0013\u0011\tY*a\u0016\u0003\r=\u0013'.Z2u\u0001"
)
public class Param implements Serializable {
   private final String parent;
   private final String name;
   private final String doc;
   private final Function1 isValid;
   private final ClassTag paramValueClassTag;
   private final String stringRepresentation;

   public String parent() {
      return this.parent;
   }

   public String name() {
      return this.name;
   }

   public String doc() {
      return this.doc;
   }

   public Function1 isValid() {
      return this.isValid;
   }

   public ClassTag paramValueClassTag() {
      return this.paramValueClassTag;
   }

   public void validate(final Object value) {
      if (!BoxesRunTime.unboxToBoolean(this.isValid().apply(value))) {
         String valueToString = .MODULE$.isArray(value, 1) ? scala.Predef..MODULE$.genericWrapArray(value).mkString("[", ",", "]") : value.toString();
         String var10002 = this.parent();
         throw new IllegalArgumentException(var10002 + " parameter " + this.name() + " given invalid value " + valueToString + ".");
      }
   }

   public ParamPair w(final Object value) {
      return this.$minus$greater(value);
   }

   public ParamPair $minus$greater(final Object value) {
      return new ParamPair(this, value);
   }

   public String jsonEncode(final Object value) {
      if (value instanceof String var4) {
         return org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(new JString(var4), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
      } else if (value instanceof Vector var5) {
         return JsonVectorConverter$.MODULE$.toJson(var5);
      } else if (value instanceof Matrix var6) {
         return JsonMatrixConverter$.MODULE$.toJson(var6);
      } else {
         String var10002 = this.getClass().getName();
         throw new UnsupportedOperationException("The default jsonEncode only supports string, vector and matrix. " + var10002 + " must override jsonEncode for " + value.getClass().getName() + ".");
      }
   }

   public Object jsonDecode(final String json) {
      return Param$.MODULE$.jsonDecode(json);
   }

   public final String toString() {
      return this.stringRepresentation;
   }

   public final int hashCode() {
      return Statics.anyHash(this.toString());
   }

   public final boolean equals(final Object obj) {
      if (!(obj instanceof Param var4)) {
         return false;
      } else {
         boolean var8;
         label39: {
            label28: {
               String var10000 = var4.parent();
               String var5 = this.parent();
               if (var10000 == null) {
                  if (var5 != null) {
                     break label28;
                  }
               } else if (!var10000.equals(var5)) {
                  break label28;
               }

               var10000 = var4.name();
               String var6 = this.name();
               if (var10000 == null) {
                  if (var6 == null) {
                     break label39;
                  }
               } else if (var10000.equals(var6)) {
                  break label39;
               }
            }

            var8 = false;
            return var8;
         }

         var8 = true;
         return var8;
      }
   }

   public Param(final String parent, final String name, final String doc, final Function1 isValid, final ClassTag evidence$1) {
      this.parent = parent;
      this.name = name;
      this.doc = doc;
      this.isValid = isValid;
      this.paramValueClassTag = (ClassTag)scala.Predef..MODULE$.implicitly(evidence$1);
      this.stringRepresentation = parent + "__" + name;
   }

   public Param(final Identifiable parent, final String name, final String doc, final Function1 isValid, final ClassTag evidence$2) {
      this(parent.uid(), name, doc, isValid, evidence$2);
   }

   public Param(final String parent, final String name, final String doc, final ClassTag evidence$3) {
      this(parent, name, doc, ParamValidators$.MODULE$.alwaysTrue(), evidence$3);
   }

   public Param(final Identifiable parent, final String name, final String doc, final ClassTag evidence$4) {
      this(parent.uid(), name, doc, evidence$4);
   }
}
