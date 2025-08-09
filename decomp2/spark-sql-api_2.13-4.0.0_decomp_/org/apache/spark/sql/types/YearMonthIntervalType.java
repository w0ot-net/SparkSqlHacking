package org.apache.spark.sql.types;

import java.io.Serializable;
import org.apache.spark.annotation.Unstable;
import org.apache.spark.sql.errors.DataTypeErrors$;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@Unstable
@ScalaSignature(
   bytes = "\u0006\u0005\u0005]h\u0001B\u001a5\u0001~B\u0001B\u0016\u0001\u0003\u0016\u0004%\ta\u0016\u0005\t7\u0002\u0011\t\u0012)A\u00051\"AA\f\u0001BK\u0002\u0013\u0005q\u000b\u0003\u0005^\u0001\tE\t\u0015!\u0003Y\u0011\u0015q\u0006\u0001\"\u0001`\u0011\u0015\u0019\u0007\u0001\"\u0011e\u0011\u0019A\u0007\u0001\"\u00119S\"9!\u000e\u0001b\u0001\n\u0003Z\u0007B\u0002;\u0001A\u0003%A\u000eC\u0004v\u0001\u0005\u0005I\u0011\u0001<\t\u000fe\u0004\u0011\u0013!C\u0001u\"A\u00111\u0002\u0001\u0012\u0002\u0013\u0005!\u0010C\u0005\u0002\u000e\u0001\t\t\u0011\"\u0011\u0002\u0010!A\u0011q\u0004\u0001\u0002\u0002\u0013\u0005A\rC\u0005\u0002\"\u0001\t\t\u0011\"\u0001\u0002$!I\u0011q\u0006\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0007\u0005\n\u0003\u007f\u0001\u0011\u0011!C\u0001\u0003\u0003B\u0011\"a\u0013\u0001\u0003\u0003%\t%!\u0014\t\u0013\u0005E\u0003!!A\u0005B\u0005M\u0003\"CA+\u0001\u0005\u0005I\u0011IA,\u0011%\tI\u0006AA\u0001\n\u0003\nYfB\u0004\u0002lQB\t)!\u001c\u0007\rM\"\u0004\u0012QA8\u0011\u0019qv\u0003\"\u0001\u0002x!A\u0011\u0011P\fC\u0002\u0013\u0005q\u000bC\u0004\u0002|]\u0001\u000b\u0011\u0002-\t\u0011\u0005utC1A\u0005\u0002]Cq!a \u0018A\u0003%\u0001\fC\u0005\u0002\u0002^\u0011\r\u0011\"\u0001\u0002\u0004\"A\u0011\u0011S\f!\u0002\u0013\t)\tC\u0004\u0002\u0014^!\t!!&\t\u0013\u0005muC1A\u0005\u0002\u0005u\u0005\u0002CAS/\u0001\u0006I!a(\t\u0011\u0005\u001dvC1A\u0005\u0002%Dq!!+\u0018A\u0003%\u0001\rC\u0004\u0002,^!\t!!,\t\u000f\u0005-v\u0003\"\u0001\u00020\"A\u00111W\f\u0005BY\n)\f\u0003\u0005\u0002>^!\tENA`\u0011\u001d\t)m\u0006C!m-D\u0011\"a+\u0018\u0003\u0003%\t)a2\t\u0013\u00055w#!A\u0005\u0002\u0006=\u0007\"CA\u0007/\u0005\u0005I\u0011IA\b\u0011!\tybFA\u0001\n\u0003!\u0007\"CA\u0011/\u0005\u0005I\u0011AAq\u0011%\tycFA\u0001\n\u0003\n\t\u0004C\u0005\u0002@]\t\t\u0011\"\u0001\u0002f\"I\u0011\u0011K\f\u0002\u0002\u0013\u0005\u00131\u000b\u0005\n\u0003+:\u0012\u0011!C!\u0003/B\u0011\"!;\u0018\u0003\u0003%I!a;\u0003+e+\u0017M]'p]RD\u0017J\u001c;feZ\fG\u000eV=qK*\u0011QGN\u0001\u0006if\u0004Xm\u001d\u0006\u0003oa\n1a]9m\u0015\tI$(A\u0003ta\u0006\u00148N\u0003\u0002<y\u00051\u0011\r]1dQ\u0016T\u0011!P\u0001\u0004_J<7\u0001A\n\u0005\u0001\u0001#%\n\u0005\u0002B\u00056\tA'\u0003\u0002Di\t\u0001\u0012I\\:j\u0013:$XM\u001d<bYRK\b/\u001a\t\u0003\u000b\"k\u0011A\u0012\u0006\u0002\u000f\u0006)1oY1mC&\u0011\u0011J\u0012\u0002\b!J|G-^2u!\tY5K\u0004\u0002M#:\u0011Q\nU\u0007\u0002\u001d*\u0011qJP\u0001\u0007yI|w\u000e\u001e \n\u0003\u001dK!A\u0015$\u0002\u000fA\f7m[1hK&\u0011A+\u0016\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003%\u001a\u000b!b\u001d;beR4\u0015.\u001a7e+\u0005A\u0006CA#Z\u0013\tQfI\u0001\u0003CsR,\u0017aC:uCJ$h)[3mI\u0002\n\u0001\"\u001a8e\r&,G\u000eZ\u0001\nK:$g)[3mI\u0002\na\u0001P5oSRtDc\u00011bEB\u0011\u0011\t\u0001\u0005\u0006-\u0016\u0001\r\u0001\u0017\u0005\u00069\u0016\u0001\r\u0001W\u0001\fI\u00164\u0017-\u001e7u'&TX-F\u0001f!\t)e-\u0003\u0002h\r\n\u0019\u0011J\u001c;\u0002\u0015\u0005\u001ch*\u001e7mC\ndW-F\u0001a\u0003!!\u0018\u0010]3OC6,W#\u00017\u0011\u00055\fhB\u00018p!\tie)\u0003\u0002q\r\u00061\u0001K]3eK\u001aL!A]:\u0003\rM#(/\u001b8h\u0015\t\u0001h)A\u0005usB,g*Y7fA\u0005!1m\u001c9z)\r\u0001w\u000f\u001f\u0005\b-*\u0001\n\u00111\u0001Y\u0011\u001da&\u0002%AA\u0002a\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001|U\tAFpK\u0001~!\rq\u0018qA\u0007\u0002\u007f*!\u0011\u0011AA\u0002\u0003%)hn\u00195fG.,GMC\u0002\u0002\u0006\u0019\u000b!\"\u00198o_R\fG/[8o\u0013\r\tIa \u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005E\u0001\u0003BA\n\u0003;i!!!\u0006\u000b\t\u0005]\u0011\u0011D\u0001\u0005Y\u0006twM\u0003\u0002\u0002\u001c\u0005!!.\u0019<b\u0013\r\u0011\u0018QC\u0001\raJ|G-^2u\u0003JLG/_\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\t)#a\u000b\u0011\u0007\u0015\u000b9#C\u0002\u0002*\u0019\u00131!\u00118z\u0011!\ticDA\u0001\u0002\u0004)\u0017a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u00024A1\u0011QGA\u001e\u0003Ki!!a\u000e\u000b\u0007\u0005eb)\u0001\u0006d_2dWm\u0019;j_:LA!!\u0010\u00028\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t\u0019%!\u0013\u0011\u0007\u0015\u000b)%C\u0002\u0002H\u0019\u0013qAQ8pY\u0016\fg\u000eC\u0005\u0002.E\t\t\u00111\u0001\u0002&\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\t\t\"a\u0014\t\u0011\u00055\"#!AA\u0002\u0015\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002K\u0006AAo\\*ue&tw\r\u0006\u0002\u0002\u0012\u00051Q-];bYN$B!a\u0011\u0002^!I\u0011QF\u000b\u0002\u0002\u0003\u0007\u0011Q\u0005\u0015\u0004\u0001\u0005\u0005\u0004\u0003BA2\u0003Oj!!!\u001a\u000b\u0007\u0005\u0015\u0001(\u0003\u0003\u0002j\u0005\u0015$\u0001C+ogR\f'\r\\3\u0002+e+\u0017M]'p]RD\u0017J\u001c;feZ\fG\u000eV=qKB\u0011\u0011iF\n\u0006/\u0005EDI\u0013\t\u0004\u0003\u0006M\u0014bAA;i\t\u0001\u0012IY:ue\u0006\u001cG\u000fR1uCRK\b/\u001a\u000b\u0003\u0003[\nA!W#B%\u0006)\u0011,R!SA\u0005)Qj\u0014(U\u0011\u00061Qj\u0014(U\u0011\u0002\nq\"_3be6{g\u000e\u001e5GS\u0016dGm]\u000b\u0003\u0003\u000b\u0003R!a\"\u0002\u000ebk!!!#\u000b\t\u0005-\u0015qG\u0001\nS6lW\u000f^1cY\u0016LA!a$\u0002\n\n\u00191+Z9\u0002!e,\u0017M]'p]RDg)[3mIN\u0004\u0013!\u00044jK2$Gk\\*ue&tw\rF\u0002m\u0003/Ca!!' \u0001\u0004A\u0016!\u00024jK2$\u0017!D:ue&tw\rV8GS\u0016dG-\u0006\u0002\u0002 B)Q.!)m1&\u0019\u00111U:\u0003\u00075\u000b\u0007/\u0001\btiJLgn\u001a+p\r&,G\u000e\u001a\u0011\u0002\u000f\u0011+e)Q+M)\u0006AA)\u0012$B+2#\u0006%A\u0003baBd\u0017\u0010F\u0001a)\r\u0001\u0017\u0011\u0017\u0005\u0007\u00033+\u0003\u0019\u0001-\u0002'\u0011,g-Y;mi\u000e{gn\u0019:fi\u0016$\u0016\u0010]3\u0016\u0005\u0005]\u0006cA!\u0002:&\u0019\u00111\u0018\u001b\u0003\u0011\u0011\u000bG/\u0019+za\u0016\f1\"Y2dKB$8\u000fV=qKR!\u00111IAa\u0011\u001d\t\u0019m\na\u0001\u0003o\u000bQa\u001c;iKJ\fAb]5na2,7\u000b\u001e:j]\u001e$R\u0001YAe\u0003\u0017DQAV\u0015A\u0002aCQ\u0001X\u0015A\u0002a\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002R\u0006u\u0007#B#\u0002T\u0006]\u0017bAAk\r\n1q\n\u001d;j_:\u0004R!RAm1bK1!a7G\u0005\u0019!V\u000f\u001d7fe!A\u0011q\u001c\u0016\u0002\u0002\u0003\u0007\u0001-A\u0002yIA\"B!!\n\u0002d\"A\u0011QF\u0017\u0002\u0002\u0003\u0007Q\r\u0006\u0003\u0002D\u0005\u001d\b\"CA\u0017_\u0005\u0005\t\u0019AA\u0013\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\ti\u000f\u0005\u0003\u0002\u0014\u0005=\u0018\u0002BAy\u0003+\u0011aa\u00142kK\u000e$\bfA\f\u0002b!\u001aa#!\u0019"
)
public class YearMonthIntervalType extends AnsiIntervalType implements Product, Serializable {
   private final byte startField;
   private final byte endField;
   private final String typeName;

   public static Option unapply(final YearMonthIntervalType x$0) {
      return YearMonthIntervalType$.MODULE$.unapply(x$0);
   }

   public static YearMonthIntervalType apply(final byte startField, final byte endField) {
      return YearMonthIntervalType$.MODULE$.apply(startField, endField);
   }

   public static YearMonthIntervalType apply(final byte field) {
      return YearMonthIntervalType$.MODULE$.apply(field);
   }

   public static YearMonthIntervalType apply() {
      return YearMonthIntervalType$.MODULE$.apply();
   }

   public static YearMonthIntervalType DEFAULT() {
      return YearMonthIntervalType$.MODULE$.DEFAULT();
   }

   public static Map stringToField() {
      return YearMonthIntervalType$.MODULE$.stringToField();
   }

   public static String fieldToString(final byte field) {
      return YearMonthIntervalType$.MODULE$.fieldToString(field);
   }

   public static Seq yearMonthFields() {
      return YearMonthIntervalType$.MODULE$.yearMonthFields();
   }

   public static byte MONTH() {
      return YearMonthIntervalType$.MODULE$.MONTH();
   }

   public static byte YEAR() {
      return YearMonthIntervalType$.MODULE$.YEAR();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public byte startField() {
      return this.startField;
   }

   public byte endField() {
      return this.endField;
   }

   public int defaultSize() {
      return 4;
   }

   public YearMonthIntervalType asNullable() {
      return this;
   }

   public String typeName() {
      return this.typeName;
   }

   public YearMonthIntervalType copy(final byte startField, final byte endField) {
      return new YearMonthIntervalType(startField, endField);
   }

   public byte copy$default$1() {
      return this.startField();
   }

   public byte copy$default$2() {
      return this.endField();
   }

   public String productPrefix() {
      return "YearMonthIntervalType";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToByte(this.startField());
         }
         case 1 -> {
            return BoxesRunTime.boxToByte(this.endField());
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof YearMonthIntervalType;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "startField";
         }
         case 1 -> {
            return "endField";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.startField());
      var1 = Statics.mix(var1, this.endField());
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label38: {
            if (x$1 instanceof YearMonthIntervalType) {
               YearMonthIntervalType var4 = (YearMonthIntervalType)x$1;
               if (this.startField() == var4.startField() && this.endField() == var4.endField() && var4.canEqual(this)) {
                  break label38;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public YearMonthIntervalType(final byte startField, final byte endField) {
      String var10001;
      label21: {
         String startFieldName;
         label23: {
            this.startField = startField;
            this.endField = endField;
            super();
            Product.$init$(this);
            startFieldName = YearMonthIntervalType$.MODULE$.fieldToString(startField);
            String endFieldName = YearMonthIntervalType$.MODULE$.fieldToString(endField);
            if (startFieldName == null) {
               if (endFieldName == null) {
                  break label23;
               }
            } else if (startFieldName.equals(endFieldName)) {
               break label23;
            }

            if (startField >= endField) {
               throw DataTypeErrors$.MODULE$.invalidDayTimeIntervalType(startFieldName, endFieldName);
            }

            var10001 = "interval " + startFieldName + " to " + endFieldName;
            break label21;
         }

         var10001 = "interval " + startFieldName;
      }

      this.typeName = var10001;
   }
}
