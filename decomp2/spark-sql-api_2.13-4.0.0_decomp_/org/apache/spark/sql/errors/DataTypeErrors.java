package org.apache.spark.sql.errors;

import org.apache.spark.QueryContext;
import org.apache.spark.SparkArithmeticException;
import org.apache.spark.SparkException;
import org.apache.spark.SparkNumberFormatException;
import org.apache.spark.SparkRuntimeException;
import org.apache.spark.SparkUnsupportedOperationException;
import org.apache.spark.sql.catalyst.trees.Origin;
import org.apache.spark.sql.types.AbstractDataType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.Decimal;
import org.apache.spark.unsafe.types.UTF8String;
import scala.Enumeration;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t=vA\u0002\u0013&\u0011\u00039sF\u0002\u00042K!\u0005qE\r\u0005\u0006y\u0005!\tA\u0010\u0005\u0006\u007f\u0005!\t\u0001\u0011\u0005\u0006\u000b\u0006!\tA\u0012\u0005\u0006#\u0006!\tA\u0015\u0005\u0006_\u0006!\t\u0001\u001d\u0005\u0006w\u0006!\t\u0001 \u0005\b\u0003[\tA\u0011AA\u0018\u0011\u001d\tY$\u0001C\u0001\u0003{Aq!a\u0013\u0002\t\u0003\ti\u0005C\u0004\u0002T\u0005!\t!!\u0016\t\u000f\u0005\r\u0014\u0001\"\u0001\u0002f!9\u0011qN\u0001\u0005\u0002\u0005E\u0004bBA>\u0003\u0011\u0005\u0011Q\u0010\u0005\b\u0003'\u000bA\u0011AAK\u0011\u001d\tY*\u0001C\u0001\u0003;Cq!!*\u0002\t\u0003\t9\u000bC\u0004\u0002,\u0006!\t!!,\t\u000f\u0005E\u0016\u0001\"\u0001\u00024\"9\u0011qY\u0001\u0005\u0002\u0005%\u0007bBAj\u0003\u0011\u0005\u0011Q\u001b\u0005\b\u0003?\fA\u0011AAq\u0011\u001d\ty0\u0001C\u0001\u0005\u0003A\u0011B!\f\u0002#\u0003%\tAa\f\t\u000f\t\u0015\u0013\u0001\"\u0001\u0003H!I!\u0011K\u0001\u0012\u0002\u0013\u0005!q\u0006\u0005\b\u0005'\nA\u0011\u0002B+\u0011\u001d\u0011y&\u0001C\u0001\u0005CBqAa\u001d\u0002\t\u0003\u0011)\bC\u0004\u0003\u0000\u0005!\tA!!\t\u000f\t5\u0015\u0001\"\u0001\u0003\u0010\"9!QS\u0001\u0005\u0002\t]\u0005b\u0002BN\u0003\u0011\u0005!Q\u0014\u0005\b\u0005G\u000bA\u0011\u0001BS\u0011\u001d\u00119+\u0001C\u0001\u0005S\u000ba\u0002R1uCRK\b/Z#se>\u00148O\u0003\u0002'O\u00051QM\u001d:peNT!\u0001K\u0015\u0002\u0007M\fHN\u0003\u0002+W\u0005)1\u000f]1sW*\u0011A&L\u0001\u0007CB\f7\r[3\u000b\u00039\n1a\u001c:h!\t\u0001\u0014!D\u0001&\u00059!\u0015\r^1UsB,WI\u001d:peN\u001c2!A\u001a:!\t!t'D\u00016\u0015\u00051\u0014!B:dC2\f\u0017B\u0001\u001d6\u0005\u0019\te.\u001f*fMB\u0011\u0001GO\u0005\u0003w\u0015\u0012!\u0003R1uCRK\b/Z#se>\u00148OQ1tK\u00061A(\u001b8jiz\u001a\u0001\u0001F\u00010\u0003\t*hn];qa>\u0014H/\u001a3Pa\u0016\u0014\u0018\r^5p]\u0016C8-\u001a9uS>tWI\u001d:peR\t\u0011\t\u0005\u0002C\u00076\t\u0011&\u0003\u0002ES\t\u00113\u000b]1sWVs7/\u001e9q_J$X\rZ(qKJ\fG/[8o\u000bb\u001cW\r\u001d;j_:\f\u0001\u0006Z3dS6\fG\u000e\u0015:fG&\u001c\u0018n\u001c8Fq\u000e,W\rZ:NCb\u0004&/Z2jg&|g.\u0012:s_J$2a\u0012&P!\t\u0011\u0005*\u0003\u0002JS\tA2\u000b]1sW\u0006\u0013\u0018\u000e\u001e5nKRL7-\u0012=dKB$\u0018n\u001c8\t\u000b-#\u0001\u0019\u0001'\u0002\u0013A\u0014XmY5tS>t\u0007C\u0001\u001bN\u0013\tqUGA\u0002J]RDQ\u0001\u0015\u0003A\u00021\u000bA\"\\1y!J,7-[:j_:\fq#\u001e8tkB\u0004xN\u001d;fIJ{WO\u001c3j]\u001elu\u000eZ3\u0015\u0005M3\u0006C\u0001\"U\u0013\t)\u0016F\u0001\bTa\u0006\u00148.\u0012=dKB$\u0018n\u001c8\t\u000b]+\u0001\u0019\u0001-\u0002\u0013I|WO\u001c3N_\u0012,\u0007CA-l\u001d\tQfM\u0004\u0002\\G:\u0011A,\u0019\b\u0003;\u0002l\u0011A\u0018\u0006\u0003?v\na\u0001\u0010:p_Rt\u0014\"\u0001\u001c\n\u0005\t,\u0014a\u00029bG.\fw-Z\u0005\u0003I\u0016\f!BQ5h\t\u0016\u001c\u0017.\\1m\u0015\t\u0011W'\u0003\u0002hQ\u0006a!k\\;oI&tw-T8eK*\u0011A-\u001b\u0006\u0003UV\nA!\\1uQ&\u0011A.\u001c\u0002\u0006-\u0006dW/Z\u0005\u0003]V\u00121\"\u00128v[\u0016\u0014\u0018\r^5p]\u0006Qr.\u001e;PM\u0012+7-[7bYRK\b/\u001a*b]\u001e,WI\u001d:peR\u0011q)\u001d\u0005\u0006e\u001a\u0001\ra]\u0001\u0004gR\u0014\bC\u0001;z\u001b\u0005)(B\u0001<x\u0003\u0015!\u0018\u0010]3t\u0015\tA\u0018&\u0001\u0004v]N\fg-Z\u0005\u0003uV\u0014!\"\u0016+GqM#(/\u001b8h\u0003a)hn];qa>\u0014H/\u001a3KCZ\fG+\u001f9f\u000bJ\u0014xN\u001d\u000b\u0004{\u0006\u0005\u0001C\u0001\"\u007f\u0013\ty\u0018FA\u000bTa\u0006\u00148NU;oi&lW-\u0012=dKB$\u0018n\u001c8\t\u000f\u0005\rq\u00011\u0001\u0002\u0006\u0005)1\r\\1{uB\"\u0011qAA\u000e!\u0019\tI!!\u0005\u0002\u00189!\u00111BA\u0007!\tiV'C\u0002\u0002\u0010U\na\u0001\u0015:fI\u00164\u0017\u0002BA\n\u0003+\u0011Qa\u00117bgNT1!a\u00046!\u0011\tI\"a\u0007\r\u0001\u0011a\u0011QDA\u0001\u0003\u0003\u0005\tQ!\u0001\u0002 \t\u0019q\fJ\u0019\u0012\t\u0005\u0005\u0012q\u0005\t\u0004i\u0005\r\u0012bAA\u0013k\t9aj\u001c;iS:<\u0007c\u0001\u001b\u0002*%\u0019\u00111F\u001b\u0003\u0007\u0005s\u00170A\u0010ok2dG*\u001b;fe\u0006d7oQ1o]>$()Z\"bgR,G-\u0012:s_J$2!QA\u0019\u0011\u001d\t\u0019\u0004\u0003a\u0001\u0003k\tAA\\1nKB!\u0011\u0011BA\u001c\u0013\u0011\tI$!\u0006\u0003\rM#(/\u001b8h\u0003]qw\u000e^+tKJ$UMZ5oK\u0012$\u0016\u0010]3FeJ|'\u000f\u0006\u0004\u0002@\u0005\u0015\u0013q\t\t\u00047\u0006\u0005\u0013bAA\"K\nIA\u000b\u001b:po\u0006\u0014G.\u001a\u0005\b\u0003gI\u0001\u0019AA\u001b\u0011\u001d\tI%\u0003a\u0001\u0003k\t\u0011\"^:fe\u000ec\u0017m]:\u0002=\r\fgN\\8u\u0019>\fG-V:fe\u0012+g-\u001b8fIRK\b/Z#se>\u0014HCBA \u0003\u001f\n\t\u0006C\u0004\u00024)\u0001\r!!\u000e\t\u000f\u0005%#\u00021\u0001\u00026\u0005IRO\\:vaB|'\u000f^3e\u0003J\u0014\u0018-\u001f+za\u0016,%O]8s)\ri\u0018q\u000b\u0005\b\u0003\u0007Y\u0001\u0019AA-a\u0011\tY&a\u0018\u0011\r\u0005%\u0011\u0011CA/!\u0011\tI\"a\u0018\u0005\u0019\u0005\u0005\u0014qKA\u0001\u0002\u0003\u0015\t!a\b\u0003\u0007}##'\u0001\ftG\",W.\u0019$bS2$v\u000eU1sg\u0016,%O]8s)\u0019\ty$a\u001a\u0002l!9\u0011\u0011\u000e\u0007A\u0002\u0005U\u0012AB:dQ\u0016l\u0017\rC\u0004\u0002n1\u0001\r!a\u0010\u0002\u0003\u0015\f!$\u001b8wC2LG\rR1z)&lW-\u00138uKJ4\u0018\r\u001c+za\u0016$b!a\u0010\u0002t\u0005]\u0004bBA;\u001b\u0001\u0007\u0011QG\u0001\u000fgR\f'\u000f\u001e$jK2$g*Y7f\u0011\u001d\tI(\u0004a\u0001\u0003k\tA\"\u001a8e\r&,G\u000e\u001a(b[\u0016\f1#\u001b8wC2LG\rR1z)&lWMR5fY\u0012$b!a\u0010\u0002\u0000\u0005%\u0005bBAA\u001d\u0001\u0007\u00111Q\u0001\u0006M&,G\u000e\u001a\t\u0004i\u0005\u0015\u0015bAADk\t!!)\u001f;f\u0011\u001d\tYI\u0004a\u0001\u0003\u001b\u000bAb];qa>\u0014H/\u001a3JIN\u0004RaWAH\u0003kI1!!%f\u0005\r\u0019V-]\u0001\u0016S:4\u0018\r\\5e3\u0016\f'/T8oi\"4\u0015.\u001a7e)\u0019\ty$a&\u0002\u001a\"9\u0011\u0011Q\bA\u0002\u0005\r\u0005bBAF\u001f\u0001\u0007\u0011QR\u0001'I\u0016\u001c\u0017.\\1m\u0007\u0006tgn\u001c;He\u0016\fG/\u001a:UQ\u0006t\u0007K]3dSNLwN\\#se>\u0014HCBA \u0003?\u000b\u0019\u000b\u0003\u0004\u0002\"B\u0001\r\u0001T\u0001\u0006g\u000e\fG.\u001a\u0005\u0006\u0017B\u0001\r\u0001T\u0001\u001d]\u0016<\u0017\r^5wKN\u001b\u0017\r\\3O_R\fE\u000e\\8xK\u0012,%O]8s)\u0011\ty$!+\t\r\u0005\u0005\u0016\u00031\u0001M\u0003a\tG\u000f\u001e:jEV$XMT1nKNKh\u000e^1y\u000bJ\u0014xN\u001d\u000b\u0005\u0003\u007f\ty\u000bC\u0004\u00024I\u0001\r!!\u000e\u0002K\r\fgN\\8u\u001b\u0016\u0014x-Z%oG>l\u0007/\u0019;jE2,G)\u0019;b)f\u0004Xm]#se>\u0014HCBA \u0003k\u000b\u0019\rC\u0004\u00028N\u0001\r!!/\u0002\t1,g\r\u001e\t\u0005\u0003w\u000by,\u0004\u0002\u0002>*\u0011aoJ\u0005\u0005\u0003\u0003\fiL\u0001\u0005ECR\fG+\u001f9f\u0011\u001d\t)m\u0005a\u0001\u0003s\u000bQA]5hQR\f\u0011gY1o]>$X*\u001a:hK\u0012+7-[7bYRK\b/Z:XSRD\u0017J\\2p[B\fG/\u001b2mKN\u001b\u0017\r\\3FeJ|'\u000f\u0006\u0004\u0002@\u0005-\u0017q\u001a\u0005\u0007\u0003\u001b$\u0002\u0019\u0001'\u0002\u00131,g\r^*dC2,\u0007BBAi)\u0001\u0007A*\u0001\u0006sS\u001eDGoU2bY\u0016\f\u0001\u0004Z1uCRK\b/Z+ogV\u0004\bo\u001c:uK\u0012,%O]8s)\u0019\ty$a6\u0002\\\"9\u0011\u0011\\\u000bA\u0002\u0005U\u0012\u0001\u00033bi\u0006$\u0016\u0010]3\t\u000f\u0005uW\u00031\u0001\u00026\u00059a-Y5mkJ,\u0017\u0001E5om\u0006d\u0017\u000e\u001a$jK2$g*Y7f)!\ty$a9\u0002h\u0006-\bbBAs-\u0001\u0007\u0011QR\u0001\nM&,G\u000e\u001a(b[\u0016Dq!!;\u0017\u0001\u0004\ti)\u0001\u0003qCRD\u0007bBAw-\u0001\u0007\u0011q^\u0001\bG>tG/\u001a=u!\u0011\t\t0a?\u000e\u0005\u0005M(\u0002BA{\u0003o\fQ\u0001\u001e:fKNT1!!?(\u0003!\u0019\u0017\r^1msN$\u0018\u0002BA\u007f\u0003g\u0014aa\u0014:jO&t\u0017AJ;og\u000e\fG.\u001a3WC2,X\rV8p\u0019\u0006\u0014x-\u001a$peB\u0013XmY5tS>tWI\u001d:peRQ!1\u0001B\n\u0005;\u0011\tC!\n\u0011\t\t\u0015!qB\u0007\u0003\u0005\u000fQAA!\u0003\u0003\f\u0005!A.\u00198h\u0015\t\u0011i!\u0001\u0003kCZ\f\u0017\u0002\u0002B\t\u0005\u000f\u00111#\u0011:ji\"lW\r^5d\u000bb\u001cW\r\u001d;j_:DqA!\u0006\u0018\u0001\u0004\u00119\"A\u0003wC2,X\r\u0005\u0003\u0002<\ne\u0011\u0002\u0002B\u000e\u0003{\u0013q\u0001R3dS6\fG\u000e\u0003\u0004\u0003 ]\u0001\r\u0001T\u0001\u0011I\u0016\u001c\u0017.\\1m!J,7-[:j_:DaAa\t\u0018\u0001\u0004a\u0015\u0001\u00043fG&l\u0017\r\\*dC2,\u0007\"CAw/A\u0005\t\u0019\u0001B\u0014!\r\u0011%\u0011F\u0005\u0004\u0005WI#\u0001D)vKJL8i\u001c8uKb$\u0018\u0001M;og\u000e\fG.\u001a3WC2,X\rV8p\u0019\u0006\u0014x-\u001a$peB\u0013XmY5tS>tWI\u001d:pe\u0012\"WMZ1vYR$C'\u0006\u0002\u00032)\"!q\u0005B\u001aW\t\u0011)\u0004\u0005\u0003\u00038\t\u0005SB\u0001B\u001d\u0015\u0011\u0011YD!\u0010\u0002\u0013Ut7\r[3dW\u0016$'b\u0001B k\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\t\r#\u0011\b\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!I2b]:|Go\u00115b]\u001e,G)Z2j[\u0006d\u0007K]3dSNLwN\\#se>\u0014HC\u0003B\u0002\u0005\u0013\u0012YE!\u0014\u0003P!9!QC\rA\u0002\t]\u0001B\u0002B\u00103\u0001\u0007A\n\u0003\u0004\u0003$e\u0001\r\u0001\u0014\u0005\n\u0003[L\u0002\u0013!a\u0001\u0005O\t1fY1o]>$8\t[1oO\u0016$UmY5nC2\u0004&/Z2jg&|g.\u0012:s_J$C-\u001a4bk2$H\u0005N\u0001\u0017]VlWM]5d-\u0006dW/Z(vi>3'+\u00198hKRQ!1\u0001B,\u00053\u0012YF!\u0018\t\u000f\tU1\u00041\u0001\u0003\u0018!1!qD\u000eA\u00021CaAa\t\u001c\u0001\u0004a\u0005bBAw7\u0001\u0007!qE\u0001 S:4\u0018\r\\5e\u0013:\u0004X\u000f^%o\u0007\u0006\u001cH\u000fV8Ok6\u0014WM]#se>\u0014H\u0003\u0003B2\u0005S\u0012iG!\u001d\u0011\u0007\t\u0013)'C\u0002\u0003h%\u0012!d\u00159be.tU/\u001c2fe\u001a{'/\\1u\u000bb\u001cW\r\u001d;j_:DqAa\u001b\u001d\u0001\u0004\tI,\u0001\u0002u_\"1!q\u000e\u000fA\u0002M\f\u0011a\u001d\u0005\b\u0003[d\u0002\u0019\u0001B\u0014\u0003m\tWNY5hk>,8oQ8mk6twJ\u001d$jK2$WI\u001d:peRA\u0011q\bB<\u0005s\u0012i\bC\u0004\u00024u\u0001\r!!$\t\r\tmT\u00041\u0001M\u0003)qW/\\'bi\u000eDWm\u001d\u0005\b\u0003[l\u0002\u0019AAx\u0003e\u0019\u0017m\u001d;j]\u001e\u001c\u0015-^:f\u001fZ,'O\u001a7po\u0016\u0013(o\u001c:\u0015\u0011\t\r!1\u0011BD\u0005\u0017CqA!\"\u001f\u0001\u0004\t)$A\u0001u\u0011\u001d\u0011II\ba\u0001\u0003s\u000bAA\u001a:p[\"9!1\u000e\u0010A\u0002\u0005e\u0016\u0001\b4bS2,G\rU1sg&twm\u0015;sk\u000e$H+\u001f9f\u000bJ\u0014xN\u001d\u000b\u0004{\nE\u0005b\u0002BJ?\u0001\u0007\u0011QG\u0001\u0004e\u0006<\u0018!\t4jK2$\u0017J\u001c3fq>s'k\\<XSRDw.\u001e;TG\",W.Y#se>\u0014HcA!\u0003\u001a\"9\u0011Q\u001d\u0011A\u0002\u0005U\u0012\u0001\u0005<bYV,\u0017j\u001d(vY2,%O]8s)\u0011\tyDa(\t\r\t\u0005\u0016\u00051\u0001M\u0003\u0015Ig\u000eZ3y\u0003%\u001a\u0007.\u0019:PeZ\u000b'o\u00195beRK\b/Z!t'R\u0014\u0018N\\4V]N,\b\u000f]8si\u0016$WI\u001d:peR\u0011\u0011qH\u0001$kN,'o\u00159fG&4\u0017.\u001a3TG\",W.Y+ogV\u0004\bo\u001c:uK\u0012,%O]8s)\u0011\tyDa+\t\u000f\t56\u00051\u0001\u00026\u0005Iq\u000e]3sCRLwN\u001c"
)
public final class DataTypeErrors {
   public static Throwable userSpecifiedSchemaUnsupportedError(final String operation) {
      return DataTypeErrors$.MODULE$.userSpecifiedSchemaUnsupportedError(operation);
   }

   public static Throwable charOrVarcharTypeAsStringUnsupportedError() {
      return DataTypeErrors$.MODULE$.charOrVarcharTypeAsStringUnsupportedError();
   }

   public static Throwable valueIsNullError(final int index) {
      return DataTypeErrors$.MODULE$.valueIsNullError(index);
   }

   public static SparkUnsupportedOperationException fieldIndexOnRowWithoutSchemaError(final String fieldName) {
      return DataTypeErrors$.MODULE$.fieldIndexOnRowWithoutSchemaError(fieldName);
   }

   public static SparkRuntimeException failedParsingStructTypeError(final String raw) {
      return DataTypeErrors$.MODULE$.failedParsingStructTypeError(raw);
   }

   public static ArithmeticException castingCauseOverflowError(final String t, final DataType from, final DataType to) {
      return DataTypeErrors$.MODULE$.castingCauseOverflowError(t, from, to);
   }

   public static Throwable ambiguousColumnOrFieldError(final Seq name, final int numMatches, final Origin context) {
      return DataTypeErrors$.MODULE$.ambiguousColumnOrFieldError(name, numMatches, context);
   }

   public static SparkNumberFormatException invalidInputInCastToNumberError(final DataType to, final UTF8String s, final QueryContext context) {
      return DataTypeErrors$.MODULE$.invalidInputInCastToNumberError(to, s, context);
   }

   public static QueryContext cannotChangeDecimalPrecisionError$default$4() {
      return DataTypeErrors$.MODULE$.cannotChangeDecimalPrecisionError$default$4();
   }

   public static ArithmeticException cannotChangeDecimalPrecisionError(final Decimal value, final int decimalPrecision, final int decimalScale, final QueryContext context) {
      return DataTypeErrors$.MODULE$.cannotChangeDecimalPrecisionError(value, decimalPrecision, decimalScale, context);
   }

   public static QueryContext unscaledValueTooLargeForPrecisionError$default$4() {
      return DataTypeErrors$.MODULE$.unscaledValueTooLargeForPrecisionError$default$4();
   }

   public static ArithmeticException unscaledValueTooLargeForPrecisionError(final Decimal value, final int decimalPrecision, final int decimalScale, final QueryContext context) {
      return DataTypeErrors$.MODULE$.unscaledValueTooLargeForPrecisionError(value, decimalPrecision, decimalScale, context);
   }

   public static Throwable invalidFieldName(final Seq fieldName, final Seq path, final Origin context) {
      return DataTypeErrors$.MODULE$.invalidFieldName(fieldName, path, context);
   }

   public static Throwable dataTypeUnsupportedError(final String dataType, final String failure) {
      return DataTypeErrors$.MODULE$.dataTypeUnsupportedError(dataType, failure);
   }

   public static Throwable cannotMergeDecimalTypesWithIncompatibleScaleError(final int leftScale, final int rightScale) {
      return DataTypeErrors$.MODULE$.cannotMergeDecimalTypesWithIncompatibleScaleError(leftScale, rightScale);
   }

   public static Throwable cannotMergeIncompatibleDataTypesError(final DataType left, final DataType right) {
      return DataTypeErrors$.MODULE$.cannotMergeIncompatibleDataTypesError(left, right);
   }

   public static Throwable attributeNameSyntaxError(final String name) {
      return DataTypeErrors$.MODULE$.attributeNameSyntaxError(name);
   }

   public static Throwable negativeScaleNotAllowedError(final int scale) {
      return DataTypeErrors$.MODULE$.negativeScaleNotAllowedError(scale);
   }

   public static Throwable decimalCannotGreaterThanPrecisionError(final int scale, final int precision) {
      return DataTypeErrors$.MODULE$.decimalCannotGreaterThanPrecisionError(scale, precision);
   }

   public static Throwable invalidYearMonthField(final byte field, final Seq supportedIds) {
      return DataTypeErrors$.MODULE$.invalidYearMonthField(field, supportedIds);
   }

   public static Throwable invalidDayTimeField(final byte field, final Seq supportedIds) {
      return DataTypeErrors$.MODULE$.invalidDayTimeField(field, supportedIds);
   }

   public static Throwable invalidDayTimeIntervalType(final String startFieldName, final String endFieldName) {
      return DataTypeErrors$.MODULE$.invalidDayTimeIntervalType(startFieldName, endFieldName);
   }

   public static Throwable schemaFailToParseError(final String schema, final Throwable e) {
      return DataTypeErrors$.MODULE$.schemaFailToParseError(schema, e);
   }

   public static SparkRuntimeException unsupportedArrayTypeError(final Class clazz) {
      return DataTypeErrors$.MODULE$.unsupportedArrayTypeError(clazz);
   }

   public static Throwable cannotLoadUserDefinedTypeError(final String name, final String userClass) {
      return DataTypeErrors$.MODULE$.cannotLoadUserDefinedTypeError(name, userClass);
   }

   public static Throwable notUserDefinedTypeError(final String name, final String userClass) {
      return DataTypeErrors$.MODULE$.notUserDefinedTypeError(name, userClass);
   }

   public static SparkUnsupportedOperationException nullLiteralsCannotBeCastedError(final String name) {
      return DataTypeErrors$.MODULE$.nullLiteralsCannotBeCastedError(name);
   }

   public static SparkRuntimeException unsupportedJavaTypeError(final Class clazz) {
      return DataTypeErrors$.MODULE$.unsupportedJavaTypeError(clazz);
   }

   public static SparkArithmeticException outOfDecimalTypeRangeError(final UTF8String str) {
      return DataTypeErrors$.MODULE$.outOfDecimalTypeRangeError(str);
   }

   public static SparkException unsupportedRoundingMode(final Enumeration.Value roundMode) {
      return DataTypeErrors$.MODULE$.unsupportedRoundingMode(roundMode);
   }

   public static SparkArithmeticException decimalPrecisionExceedsMaxPrecisionError(final int precision, final int maxPrecision) {
      return DataTypeErrors$.MODULE$.decimalPrecisionExceedsMaxPrecisionError(precision, maxPrecision);
   }

   public static SparkUnsupportedOperationException unsupportedOperationExceptionError() {
      return DataTypeErrors$.MODULE$.unsupportedOperationExceptionError();
   }

   public static String toDSOption(final String option) {
      return DataTypeErrors$.MODULE$.toDSOption(option);
   }

   public static QueryContext[] getQueryContext(final QueryContext context) {
      return DataTypeErrors$.MODULE$.getQueryContext(context);
   }

   public static String getSummary(final QueryContext sqlContext) {
      return DataTypeErrors$.MODULE$.getSummary(sqlContext);
   }

   public static String toSQLValue(final double value) {
      return DataTypeErrors$.MODULE$.toSQLValue(value);
   }

   public static String toSQLValue(final float value) {
      return DataTypeErrors$.MODULE$.toSQLValue(value);
   }

   public static String toSQLValue(final long value) {
      return DataTypeErrors$.MODULE$.toSQLValue(value);
   }

   public static String toSQLValue(final int value) {
      return DataTypeErrors$.MODULE$.toSQLValue(value);
   }

   public static String toSQLValue(final short value) {
      return DataTypeErrors$.MODULE$.toSQLValue(value);
   }

   public static String toSQLValue(final UTF8String value) {
      return DataTypeErrors$.MODULE$.toSQLValue(value);
   }

   public static String toSQLValue(final String value) {
      return DataTypeErrors$.MODULE$.toSQLValue(value);
   }

   public static String toSQLType(final AbstractDataType t) {
      return DataTypeErrors$.MODULE$.toSQLType(t);
   }

   public static String toSQLType(final String text) {
      return DataTypeErrors$.MODULE$.toSQLType(text);
   }

   public static String toSQLConf(final String conf) {
      return DataTypeErrors$.MODULE$.toSQLConf(conf);
   }

   public static String toSQLStmt(final String text) {
      return DataTypeErrors$.MODULE$.toSQLStmt(text);
   }

   public static String toSQLId(final Seq parts) {
      return DataTypeErrors$.MODULE$.toSQLId(parts);
   }

   public static String toSQLId(final String parts) {
      return DataTypeErrors$.MODULE$.toSQLId(parts);
   }
}
