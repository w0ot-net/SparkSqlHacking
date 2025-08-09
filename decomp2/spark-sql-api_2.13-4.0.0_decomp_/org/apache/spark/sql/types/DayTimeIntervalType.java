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
   bytes = "\u0006\u0005\t\u001da\u0001B\u001c9\u0001\u000eC\u0001B\u0017\u0001\u0003\u0016\u0004%\ta\u0017\u0005\t?\u0002\u0011\t\u0012)A\u00059\"A\u0001\r\u0001BK\u0002\u0013\u00051\f\u0003\u0005b\u0001\tE\t\u0015!\u0003]\u0011\u0015\u0011\u0007\u0001\"\u0001d\u0011\u00159\u0007\u0001\"\u0011i\u0011\u0019a\u0007\u0001\"\u0011=[\"9a\u000e\u0001b\u0001\n\u0003z\u0007B\u0002=\u0001A\u0003%\u0001\u000fC\u0004z\u0001\u0005\u0005I\u0011\u0001>\t\u000fu\u0004\u0011\u0013!C\u0001}\"A\u00111\u0003\u0001\u0012\u0002\u0013\u0005a\u0010C\u0005\u0002\u0016\u0001\t\t\u0011\"\u0011\u0002\u0018!A\u0011q\u0005\u0001\u0002\u0002\u0013\u0005\u0001\u000eC\u0005\u0002*\u0001\t\t\u0011\"\u0001\u0002,!I\u0011q\u0007\u0001\u0002\u0002\u0013\u0005\u0013\u0011\b\u0005\n\u0003\u000f\u0002\u0011\u0011!C\u0001\u0003\u0013B\u0011\"a\u0015\u0001\u0003\u0003%\t%!\u0016\t\u0013\u0005e\u0003!!A\u0005B\u0005m\u0003\"CA/\u0001\u0005\u0005I\u0011IA0\u0011%\t\t\u0007AA\u0001\n\u0003\n\u0019gB\u0004\u0002taB\t)!\u001e\u0007\r]B\u0004\u0012QA<\u0011\u0019\u0011w\u0003\"\u0001\u0002\u0000!A\u0011\u0011Q\fC\u0002\u0013\u00051\fC\u0004\u0002\u0004^\u0001\u000b\u0011\u0002/\t\u0011\u0005\u0015uC1A\u0005\u0002mCq!a\"\u0018A\u0003%A\f\u0003\u0005\u0002\n^\u0011\r\u0011\"\u0001\\\u0011\u001d\tYi\u0006Q\u0001\nqC\u0001\"!$\u0018\u0005\u0004%\ta\u0017\u0005\b\u0003\u001f;\u0002\u0015!\u0003]\u0011%\t\tj\u0006b\u0001\n\u0003\t\u0019\n\u0003\u0005\u0002\"^\u0001\u000b\u0011BAK\u0011\u001d\t\u0019k\u0006C\u0001\u0003KC\u0011\"a+\u0018\u0005\u0004%\t!!,\t\u0011\u0005Uv\u0003)A\u0005\u0003_C\u0001\"a.\u0018\u0005\u0004%\t!\u001c\u0005\b\u0003s;\u0002\u0015!\u0003e\u0011\u001d\tYl\u0006C\u0001\u0003{Cq!a/\u0018\t\u0003\ty\f\u0003\u0005\u0002D^!\tEOAc\u0011!\tim\u0006C!u\u0005=\u0007bBAk/\u0011\u0005#h\u001c\u0005\n\u0003w;\u0012\u0011!CA\u0003/D\u0011\"!8\u0018\u0003\u0003%\t)a8\t\u0013\u0005Uq#!A\u0005B\u0005]\u0001\u0002CA\u0014/\u0005\u0005I\u0011\u00015\t\u0013\u0005%r#!A\u0005\u0002\u0005E\b\"CA\u001c/\u0005\u0005I\u0011IA\u001d\u0011%\t9eFA\u0001\n\u0003\t)\u0010C\u0005\u0002Z]\t\t\u0011\"\u0011\u0002\\!I\u0011QL\f\u0002\u0002\u0013\u0005\u0013q\f\u0005\n\u0003s<\u0012\u0011!C\u0005\u0003w\u00141\u0003R1z)&lW-\u00138uKJ4\u0018\r\u001c+za\u0016T!!\u000f\u001e\u0002\u000bQL\b/Z:\u000b\u0005mb\u0014aA:rY*\u0011QHP\u0001\u0006gB\f'o\u001b\u0006\u0003\u007f\u0001\u000ba!\u00199bG\",'\"A!\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001!\u0005J\u0014\t\u0003\u000b\u001ak\u0011\u0001O\u0005\u0003\u000fb\u0012\u0001#\u00118tS&sG/\u001a:wC2$\u0016\u0010]3\u0011\u0005%cU\"\u0001&\u000b\u0003-\u000bQa]2bY\u0006L!!\u0014&\u0003\u000fA\u0013x\u000eZ;diB\u0011qj\u0016\b\u0003!Vs!!\u0015+\u000e\u0003IS!a\u0015\"\u0002\rq\u0012xn\u001c;?\u0013\u0005Y\u0015B\u0001,K\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001W-\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005YS\u0015AC:uCJ$h)[3mIV\tA\f\u0005\u0002J;&\u0011aL\u0013\u0002\u0005\u0005f$X-A\u0006ti\u0006\u0014HOR5fY\u0012\u0004\u0013\u0001C3oI\u001aKW\r\u001c3\u0002\u0013\u0015tGMR5fY\u0012\u0004\u0013A\u0002\u001fj]&$h\bF\u0002eK\u001a\u0004\"!\u0012\u0001\t\u000bi+\u0001\u0019\u0001/\t\u000b\u0001,\u0001\u0019\u0001/\u0002\u0017\u0011,g-Y;miNK'0Z\u000b\u0002SB\u0011\u0011J[\u0005\u0003W*\u00131!\u00138u\u0003)\t7OT;mY\u0006\u0014G.Z\u000b\u0002I\u0006AA/\u001f9f\u001d\u0006lW-F\u0001q!\t\tXO\u0004\u0002sgB\u0011\u0011KS\u0005\u0003i*\u000ba\u0001\u0015:fI\u00164\u0017B\u0001<x\u0005\u0019\u0019FO]5oO*\u0011AOS\u0001\nif\u0004XMT1nK\u0002\nAaY8qsR\u0019Am\u001f?\t\u000fiS\u0001\u0013!a\u00019\"9\u0001M\u0003I\u0001\u0002\u0004a\u0016AD2paf$C-\u001a4bk2$H%M\u000b\u0002\u007f*\u001aA,!\u0001,\u0005\u0005\r\u0001\u0003BA\u0003\u0003\u001fi!!a\u0002\u000b\t\u0005%\u00111B\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!\u0004K\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003#\t9AA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u00033\u0001B!a\u0007\u0002&5\u0011\u0011Q\u0004\u0006\u0005\u0003?\t\t#\u0001\u0003mC:<'BAA\u0012\u0003\u0011Q\u0017M^1\n\u0007Y\fi\"\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u00055\u00121\u0007\t\u0004\u0013\u0006=\u0012bAA\u0019\u0015\n\u0019\u0011I\\=\t\u0011\u0005Ur\"!AA\u0002%\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\u001e!\u0019\ti$a\u0011\u0002.5\u0011\u0011q\b\u0006\u0004\u0003\u0003R\u0015AC2pY2,7\r^5p]&!\u0011QIA \u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005-\u0013\u0011\u000b\t\u0004\u0013\u00065\u0013bAA(\u0015\n9!i\\8mK\u0006t\u0007\"CA\u001b#\u0005\u0005\t\u0019AA\u0017\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005e\u0011q\u000b\u0005\t\u0003k\u0011\u0012\u0011!a\u0001S\u0006A\u0001.Y:i\u0007>$W\rF\u0001j\u0003!!xn\u0015;sS:<GCAA\r\u0003\u0019)\u0017/^1mgR!\u00111JA3\u0011%\t)$FA\u0001\u0002\u0004\ti\u0003K\u0002\u0001\u0003S\u0002B!a\u001b\u0002p5\u0011\u0011Q\u000e\u0006\u0004\u0003\u001ba\u0014\u0002BA9\u0003[\u0012\u0001\"\u00168ti\u0006\u0014G.Z\u0001\u0014\t\u0006LH+[7f\u0013:$XM\u001d<bYRK\b/\u001a\t\u0003\u000b^\u0019RaFA=\u0011:\u00032!RA>\u0013\r\ti\b\u000f\u0002\u0011\u0003\n\u001cHO]1di\u0012\u000bG/\u0019+za\u0016$\"!!\u001e\u0002\u0007\u0011\u000b\u0015,\u0001\u0003E\u0003f\u0003\u0013\u0001\u0002%P+J\u000bQ\u0001S(V%\u0002\na!T%O+R+\u0015aB'J\u001dV#V\tI\u0001\u0007'\u0016\u001buJ\u0014#\u0002\u000fM+5i\u0014(EA\u0005iA-Y=US6,g)[3mIN,\"!!&\u0011\u000b\u0005]\u0015Q\u0014/\u000e\u0005\u0005e%\u0002BAN\u0003\u007f\t\u0011\"[7nkR\f'\r\\3\n\t\u0005}\u0015\u0011\u0014\u0002\u0004'\u0016\f\u0018A\u00043bsRKW.\u001a$jK2$7\u000fI\u0001\u000eM&,G\u000e\u001a+p'R\u0014\u0018N\\4\u0015\u0007A\f9\u000b\u0003\u0004\u0002*\u000e\u0002\r\u0001X\u0001\u0006M&,G\u000eZ\u0001\u000egR\u0014\u0018N\\4U_\u001aKW\r\u001c3\u0016\u0005\u0005=\u0006#B9\u00022Bd\u0016bAAZo\n\u0019Q*\u00199\u0002\u001dM$(/\u001b8h)>4\u0015.\u001a7eA\u00059A)\u0012$B+2#\u0016\u0001\u0003#F\r\u0006+F\n\u0016\u0011\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0003\u0011$2\u0001ZAa\u0011\u0019\tI+\u000ba\u00019\u0006\u0019B-\u001a4bk2$8i\u001c8de\u0016$X\rV=qKV\u0011\u0011q\u0019\t\u0004\u000b\u0006%\u0017bAAfq\tAA)\u0019;b)f\u0004X-A\u0006bG\u000e,\u0007\u000f^:UsB,G\u0003BA&\u0003#Dq!a5,\u0001\u0004\t9-A\u0003pi\",'/\u0001\u0007tS6\u0004H.Z*ue&tw\rF\u0003e\u00033\fY\u000eC\u0003[[\u0001\u0007A\fC\u0003a[\u0001\u0007A,A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005\u0005\u0018Q\u001e\t\u0006\u0013\u0006\r\u0018q]\u0005\u0004\u0003KT%AB(qi&|g\u000eE\u0003J\u0003SdF,C\u0002\u0002l*\u0013a\u0001V;qY\u0016\u0014\u0004\u0002CAx]\u0005\u0005\t\u0019\u00013\u0002\u0007a$\u0003\u0007\u0006\u0003\u0002.\u0005M\b\u0002CA\u001bc\u0005\u0005\t\u0019A5\u0015\t\u0005-\u0013q\u001f\u0005\n\u0003k\u0019\u0014\u0011!a\u0001\u0003[\tAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!@\u0011\t\u0005m\u0011q`\u0005\u0005\u0005\u0003\tiB\u0001\u0004PE*,7\r\u001e\u0015\u0004/\u0005%\u0004f\u0001\f\u0002j\u0001"
)
public class DayTimeIntervalType extends AnsiIntervalType implements Product, Serializable {
   private final byte startField;
   private final byte endField;
   private final String typeName;

   public static Option unapply(final DayTimeIntervalType x$0) {
      return DayTimeIntervalType$.MODULE$.unapply(x$0);
   }

   public static DayTimeIntervalType apply(final byte startField, final byte endField) {
      return DayTimeIntervalType$.MODULE$.apply(startField, endField);
   }

   public static DayTimeIntervalType apply(final byte field) {
      return DayTimeIntervalType$.MODULE$.apply(field);
   }

   public static DayTimeIntervalType apply() {
      return DayTimeIntervalType$.MODULE$.apply();
   }

   public static DayTimeIntervalType DEFAULT() {
      return DayTimeIntervalType$.MODULE$.DEFAULT();
   }

   public static Map stringToField() {
      return DayTimeIntervalType$.MODULE$.stringToField();
   }

   public static String fieldToString(final byte field) {
      return DayTimeIntervalType$.MODULE$.fieldToString(field);
   }

   public static Seq dayTimeFields() {
      return DayTimeIntervalType$.MODULE$.dayTimeFields();
   }

   public static byte SECOND() {
      return DayTimeIntervalType$.MODULE$.SECOND();
   }

   public static byte MINUTE() {
      return DayTimeIntervalType$.MODULE$.MINUTE();
   }

   public static byte HOUR() {
      return DayTimeIntervalType$.MODULE$.HOUR();
   }

   public static byte DAY() {
      return DayTimeIntervalType$.MODULE$.DAY();
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
      return 8;
   }

   public DayTimeIntervalType asNullable() {
      return this;
   }

   public String typeName() {
      return this.typeName;
   }

   public DayTimeIntervalType copy(final byte startField, final byte endField) {
      return new DayTimeIntervalType(startField, endField);
   }

   public byte copy$default$1() {
      return this.startField();
   }

   public byte copy$default$2() {
      return this.endField();
   }

   public String productPrefix() {
      return "DayTimeIntervalType";
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
      return x$1 instanceof DayTimeIntervalType;
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
            if (x$1 instanceof DayTimeIntervalType) {
               DayTimeIntervalType var4 = (DayTimeIntervalType)x$1;
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

   public DayTimeIntervalType(final byte startField, final byte endField) {
      String var10001;
      label21: {
         String startFieldName;
         label23: {
            this.startField = startField;
            this.endField = endField;
            super();
            Product.$init$(this);
            startFieldName = DayTimeIntervalType$.MODULE$.fieldToString(startField);
            String endFieldName = DayTimeIntervalType$.MODULE$.fieldToString(endField);
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
