package org.apache.spark.sql.types;

import java.io.Serializable;
import java.util.Locale;
import org.apache.spark.annotation.Stable;
import org.apache.spark.sql.errors.DataTypeErrors$;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005\tMf\u0001B(Q\u0001nC\u0001B\u001d\u0001\u0003\u0016\u0004%\ta\u001d\u0005\to\u0002\u0011\t\u0012)A\u0005i\"A\u0001\u0010\u0001BK\u0002\u0013\u00051\u000f\u0003\u0005z\u0001\tE\t\u0015!\u0003u\u0011\u0015Q\b\u0001\"\u0001|\u0011\u0015Q\b\u0001\"\u0001\u0000\u0011\u0019Q\b\u0001\"\u0001\u0002\u0004!9\u0011Q\u0001\u0001\u0005B\u0005\u001d\u0001bBA\r\u0001\u0011\u0005\u00131\u0004\u0005\u0007'\u0002!\t%a\u0002\t\u0011\u0005u\u0001\u0001\"\u0001S\u0003?Aq!!\r\u0001\t\u0013\t\u0019\u0004\u0003\u0005\u0002F\u0001!\tAUA$\u0011\u0019\tY\u0005\u0001C!g\"9\u0011Q\n\u0001\u0005B\u0005\u001d\u0001\u0002CA(\u0001\u0011\u0005C+!\u0015\t\u0013\u0005M\u0003!!A\u0005\u0002\u0005U\u0003\"CA.\u0001E\u0005I\u0011AA/\u0011%\ty\u0007AI\u0001\n\u0003\ti\u0006C\u0005\u0002r\u0001\t\t\u0011\"\u0011\u0002t!A\u00111\u0011\u0001\u0002\u0002\u0013\u00051\u000fC\u0005\u0002\u0006\u0002\t\t\u0011\"\u0001\u0002\b\"I\u00111\u0013\u0001\u0002\u0002\u0013\u0005\u0013Q\u0013\u0005\n\u0003G\u0003\u0011\u0011!C\u0001\u0003KC\u0011\"!+\u0001\u0003\u0003%\t%a+\t\u0013\u0005=\u0006!!A\u0005B\u0005E\u0006\"CAZ\u0001\u0005\u0005I\u0011IA[\u000f\u001d\t)\r\u0015E\u0001\u0003\u000f4aa\u0014)\t\u0002\u0005%\u0007B\u0002>\u001e\t\u0003\tY\u000e\u0003\u0005\u0002^v\u0011\r\u0011\"\u0001t\u0011\u001d\ty.\bQ\u0001\nQD\u0001\"!9\u001e\u0005\u0004%\ta\u001d\u0005\b\u0003Gl\u0002\u0015!\u0003u\u0011!\t)/\bb\u0001\n\u0003\u0019\bbBAt;\u0001\u0006I\u0001\u001e\u0005\n\u0003Sl\"\u0019!C\u0001\u0003#Bq!a;\u001eA\u0003%A\u0010C\u0005\u0002nv\u0011\r\u0011\"\u0001\u0002R!9\u0011q^\u000f!\u0002\u0013a\b\u0002CAy;\t\u0007I\u0011A:\t\u000f\u0005MX\u0004)A\u0005i\"Q\u0011Q_\u000fC\u0002\u0013\u0005!+!\u0015\t\u000f\u0005]X\u0004)A\u0005y\"Q\u0011\u0011`\u000fC\u0002\u0013\u0005!+!\u0015\t\u000f\u0005mX\u0004)A\u0005y\"Q\u0011Q`\u000fC\u0002\u0013\u0005!+!\u0015\t\u000f\u0005}X\u0004)A\u0005y\"Q!\u0011A\u000fC\u0002\u0013\u0005!+!\u0015\t\u000f\t\rQ\u0004)A\u0005y\"Q!QA\u000fC\u0002\u0013\u0005!+!\u0015\t\u000f\t\u001dQ\u0004)A\u0005y\"Q!\u0011B\u000fC\u0002\u0013\u0005!+!\u0015\t\u000f\t-Q\u0004)A\u0005y\"Q!QB\u000fC\u0002\u0013\u0005!+!\u0015\t\u000f\t=Q\u0004)A\u0005y\"Q!\u0011C\u000fC\u0002\u0013\u0005!+!\u0015\t\u000f\tMQ\u0004)A\u0005y\"A!QC\u000f\u0005\u0002I\u00139\u0002\u0003\u0005\u0003\u001eu!\tA\u0015B\u0010\u0011!\u0011Y#\bC\u0001%\n5\u0002\u0002\u0003B\u001a;\u0011\u0005!K!\u000e\t\u0011\tmR\u0004\"\u0001S\u0005{A\u0001Ba\u0012\u001e\t\u0003\u0011&\u0011\n\u0005\t\u0005\u001fjB\u0011\t*\u0003R!A!1K\u000f\u0005BI\u0013)\u0006\u0003\u0005\u0002Nu!\tEUA\u0004\u000f!\u0011I&\bE\u0001%\nmc\u0001\u0003B0;!\u0005!K!\u0019\t\ri,E\u0011\u0001B5\u0011\u001d\u0011Y'\u0012C\u0001\u0005[BqAa \u001e\t\u0003\u0011\t\tC\u0004\u0003\bv!\tA!#\t\u000f\t5U\u0004\"\u0001\u0003\u0010\"9!1N\u000f\u0005\u0002\tM\u0005\"\u0003BL;\u0005\u0005I\u0011\u0011BM\u0011%\u0011Y'HA\u0001\n\u0003\u0013y\nC\u0005\u0003&v\t\t\u0011\"\u0003\u0003(\nYA)Z2j[\u0006dG+\u001f9f\u0015\t\t&+A\u0003usB,7O\u0003\u0002T)\u0006\u00191/\u001d7\u000b\u0005U3\u0016!B:qCJ\\'BA,Y\u0003\u0019\t\u0007/Y2iK*\t\u0011,A\u0002pe\u001e\u001c\u0001a\u0005\u0003\u00019\u00024\u0007CA/_\u001b\u0005\u0001\u0016BA0Q\u000591%/Y2uS>t\u0017\r\u001c+za\u0016\u0004\"!\u00193\u000e\u0003\tT\u0011aY\u0001\u0006g\u000e\fG.Y\u0005\u0003K\n\u0014q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002h_:\u0011\u0001.\u001c\b\u0003S2l\u0011A\u001b\u0006\u0003Wj\u000ba\u0001\u0010:p_Rt\u0014\"A2\n\u00059\u0014\u0017a\u00029bG.\fw-Z\u0005\u0003aF\u0014AbU3sS\u0006d\u0017N_1cY\u0016T!A\u001c2\u0002\u0013A\u0014XmY5tS>tW#\u0001;\u0011\u0005\u0005,\u0018B\u0001<c\u0005\rIe\u000e^\u0001\u000baJ,7-[:j_:\u0004\u0013!B:dC2,\u0017AB:dC2,\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0004yvt\bCA/\u0001\u0011\u0015\u0011X\u00011\u0001u\u0011\u0015AX\u00011\u0001u)\ra\u0018\u0011\u0001\u0005\u0006e\u001a\u0001\r\u0001\u001e\u000b\u0002y\u0006AA/\u001f9f\u001d\u0006lW-\u0006\u0002\u0002\nA!\u00111BA\n\u001d\u0011\ti!a\u0004\u0011\u0005%\u0014\u0017bAA\tE\u00061\u0001K]3eK\u001aLA!!\u0006\u0002\u0018\t11\u000b\u001e:j]\u001eT1!!\u0005c\u0003!!xn\u0015;sS:<GCAA\u0005\u0003-I7oV5eKJ$\u0006.\u00198\u0015\t\u0005\u0005\u0012q\u0005\t\u0004C\u0006\r\u0012bAA\u0013E\n9!i\\8mK\u0006t\u0007bBA\u0015\u0017\u0001\u0007\u00111F\u0001\u0006_RDWM\u001d\t\u0004;\u00065\u0012bAA\u0018!\nAA)\u0019;b)f\u0004X-A\njg^KG-\u001a:UQ\u0006t\u0017J\u001c;fe:\fG\u000e\u0006\u0003\u0002\"\u0005U\u0002bBA\u0015\u0019\u0001\u0007\u00111\u0006\u0015\u0004\u0019\u0005e\u0002\u0003BA\u001e\u0003\u0003j!!!\u0010\u000b\u0007\u0005}\"-\u0001\u0006b]:|G/\u0019;j_:LA!a\u0011\u0002>\t9A/Y5me\u0016\u001c\u0017!D5t)&<\u0007\u000e^3s)\"\fg\u000e\u0006\u0003\u0002\"\u0005%\u0003bBA\u0015\u001b\u0001\u0007\u00111F\u0001\fI\u00164\u0017-\u001e7u'&TX-\u0001\u0007tS6\u0004H.Z*ue&tw-\u0001\u0006bg:+H\u000e\\1cY\u0016,\u0012\u0001`\u0001\u0005G>\u0004\u0018\u0010F\u0003}\u0003/\nI\u0006C\u0004s#A\u0005\t\u0019\u0001;\t\u000fa\f\u0002\u0013!a\u0001i\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nTCAA0U\r!\u0018\u0011M\u0016\u0003\u0003G\u0002B!!\u001a\u0002l5\u0011\u0011q\r\u0006\u0005\u0003S\ni$A\u0005v]\u000eDWmY6fI&!\u0011QNA4\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u0011Q\u000f\t\u0005\u0003o\n\t)\u0004\u0002\u0002z)!\u00111PA?\u0003\u0011a\u0017M\\4\u000b\u0005\u0005}\u0014\u0001\u00026bm\u0006LA!!\u0006\u0002z\u0005a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BAE\u0003\u001f\u00032!YAF\u0013\r\tiI\u0019\u0002\u0004\u0003:L\b\u0002CAI-\u0005\u0005\t\u0019\u0001;\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\t9\n\u0005\u0004\u0002\u001a\u0006}\u0015\u0011R\u0007\u0003\u00037S1!!(c\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003C\u000bYJ\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\u0011\u0003OC\u0011\"!%\u0019\u0003\u0003\u0005\r!!#\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003k\ni\u000b\u0003\u0005\u0002\u0012f\t\t\u00111\u0001u\u0003!A\u0017m\u001d5D_\u0012,G#\u0001;\u0002\r\u0015\fX/\u00197t)\u0011\t\t#a.\t\u0013\u0005E5$!AA\u0002\u0005%\u0005f\u0001\u0001\u0002<B!\u0011QXAa\u001b\t\tyLC\u0002\u0002@QKA!a1\u0002@\n11\u000b^1cY\u0016\f1\u0002R3dS6\fG\u000eV=qKB\u0011Q,H\n\u0006;\u0005-\u0017\u0011\u001b\t\u0004;\u00065\u0017bAAh!\n\u0001\u0012IY:ue\u0006\u001cG\u000fR1uCRK\b/\u001a\t\u0005\u0003'\fI.\u0004\u0002\u0002V*!\u0011q[A?\u0003\tIw.C\u0002q\u0003+$\"!a2\u0002\u001b5\u000b\u0005l\u0018)S\u000b\u000eK5+S(O\u00039i\u0015\tW0Q%\u0016\u001b\u0015jU%P\u001d\u0002\n\u0011\"T!Y?N\u001b\u0015\tT#\u0002\u00155\u000b\u0005lX*D\u00032+\u0005%A\u0007E\u000b\u001a\u000bU\u000b\u0014+`'\u000e\u000bE*R\u0001\u000f\t\u00163\u0015)\u0016'U?N\u001b\u0015\tT#!\u00039\u0019\u0016l\u0015+F\u001b~#UIR!V\u0019R\u000bqbU-T)\u0016ku\fR#G\u0003VcE\u000bI\u0001\r+N+%k\u0018#F\r\u0006+F\nV\u0001\u000e+N+%k\u0018#F\r\u0006+F\n\u0016\u0011\u0002-5Ke*S'V\u001b~\u000bEIS+T)\u0016#ulU\"B\u0019\u0016\u000bq#T%O\u00136+VjX!E\u0015V\u001bF+\u0012#`'\u000e\u000bE*\u0012\u0011\u0002\u001d\t{w\u000e\\3b]\u0012+7-[7bY\u0006y!i\\8mK\u0006tG)Z2j[\u0006d\u0007%A\u0006CsR,G)Z2j[\u0006d\u0017\u0001\u0004\"zi\u0016$UmY5nC2\u0004\u0013\u0001D*i_J$H)Z2j[\u0006d\u0017!D*i_J$H)Z2j[\u0006d\u0007%\u0001\u0006J]R$UmY5nC2\f1\"\u00138u\t\u0016\u001c\u0017.\\1mA\u0005YAj\u001c8h\t\u0016\u001c\u0017.\\1m\u00031auN\\4EK\u000eLW.\u00197!\u000311En\\1u\t\u0016\u001c\u0017.\\1m\u000351En\\1u\t\u0016\u001c\u0017.\\1mA\u0005iAi\\;cY\u0016$UmY5nC2\fa\u0002R8vE2,G)Z2j[\u0006d\u0007%A\u0007CS\u001eLe\u000e\u001e#fG&l\u0017\r\\\u0001\u000f\u0005&<\u0017J\u001c;EK\u000eLW.\u00197!\u0003\u001d1wN\u001d+za\u0016$2\u0001 B\r\u0011\u001d\u0011Yb\u000fa\u0001\u0003W\t\u0001\u0002Z1uCRK\b/Z\u0001\fMJ|W\u000eR3dS6\fG\u000eF\u0002}\u0005CAqAa\t=\u0001\u0004\u0011)#A\u0001e!\ri&qE\u0005\u0004\u0005S\u0001&a\u0002#fG&l\u0017\r\\\u0001\bE>,h\u000eZ3e)\u0015a(q\u0006B\u0019\u0011\u0015\u0011X\b1\u0001u\u0011\u0015AX\b1\u0001u\u0003m\u0011w.\u001e8eK\u0012\u0004&/\u001a4fe&sG/Z4sC2$\u0015nZ5ugR)APa\u000e\u0003:!)!O\u0010a\u0001i\")\u0001P\u0010a\u0001i\u0006\u00112\r[3dW:+w-\u0019;jm\u0016\u001c6-\u00197f)\u0011\u0011yD!\u0012\u0011\u0007\u0005\u0014\t%C\u0002\u0003D\t\u0014A!\u00168ji\")\u0001p\u0010a\u0001i\u0006!\u0012\r\u001a6vgR\u0004&/Z2jg&|gnU2bY\u0016$R\u0001 B&\u0005\u001bBQA\u001d!A\u0002QDQ\u0001\u001f!A\u0002Q\f1\u0003Z3gCVdGoQ8oGJ,G/\u001a+za\u0016,\"!a\u000b\u0002\u0017\u0005\u001c7-\u001a9ugRK\b/\u001a\u000b\u0005\u0003C\u00119\u0006C\u0004\u0002*\t\u0003\r!a\u000b\u0002\u000b\u0019K\u00070\u001a3\u0011\u0007\tuS)D\u0001\u001e\u0005\u00151\u0015\u000e_3e'\r)%1\r\t\u0004C\n\u0015\u0014b\u0001B4E\n1\u0011I\\=SK\u001a$\"Aa\u0017\u0002\u000fUt\u0017\r\u001d9msR!!q\u000eB>!\u0015\t'\u0011\u000fB;\u0013\r\u0011\u0019H\u0019\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000b\u0005\u00149\b\u001e;\n\u0007\te$M\u0001\u0004UkBdWM\r\u0005\u0007\u0005{:\u0005\u0019\u0001?\u0002\u0003Q\f!#[:4e\tKG\u000fR3dS6\fG\u000eV=qKR!\u0011\u0011\u0005BB\u0011\u001d\u0011)\t\u0013a\u0001\u0003W\t!\u0001\u001a;\u0002%%\u001ch\u0007\u000e\"ji\u0012+7-[7bYRK\b/\u001a\u000b\u0005\u0003C\u0011Y\tC\u0004\u0003\u0006&\u0003\r!a\u000b\u0002-%\u001c()\u001f;f\u0003J\u0014\u0018-\u001f#fG&l\u0017\r\u001c+za\u0016$B!!\t\u0003\u0012\"9!Q\u0011&A\u0002\u0005-B\u0003BA\u0011\u0005+CqA! L\u0001\u0004\tY#A\u0003baBd\u0017\u0010F\u0003}\u00057\u0013i\nC\u0003s\u0019\u0002\u0007A\u000fC\u0003y\u0019\u0002\u0007A\u000f\u0006\u0003\u0003p\t\u0005\u0006\u0002\u0003BR\u001b\u0006\u0005\t\u0019\u0001?\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0003*B!\u0011q\u000fBV\u0013\u0011\u0011i+!\u001f\u0003\r=\u0013'.Z2uQ\ri\u00121\u0018\u0015\u00049\u0005m\u0006"
)
public class DecimalType extends FractionalType implements Product, Serializable {
   private final int precision;
   private final int scale;

   public static Option unapply(final DecimalType x$0) {
      return DecimalType$.MODULE$.unapply(x$0);
   }

   public static DecimalType apply(final int precision, final int scale) {
      return DecimalType$.MODULE$.apply(precision, scale);
   }

   public static boolean unapply(final DataType t) {
      return DecimalType$.MODULE$.unapply(t);
   }

   public static boolean isByteArrayDecimalType(final DataType dt) {
      return DecimalType$.MODULE$.isByteArrayDecimalType(dt);
   }

   public static boolean is64BitDecimalType(final DataType dt) {
      return DecimalType$.MODULE$.is64BitDecimalType(dt);
   }

   public static boolean is32BitDecimalType(final DataType dt) {
      return DecimalType$.MODULE$.is32BitDecimalType(dt);
   }

   public static int MINIMUM_ADJUSTED_SCALE() {
      return DecimalType$.MODULE$.MINIMUM_ADJUSTED_SCALE();
   }

   public static DecimalType USER_DEFAULT() {
      return DecimalType$.MODULE$.USER_DEFAULT();
   }

   public static DecimalType SYSTEM_DEFAULT() {
      return DecimalType$.MODULE$.SYSTEM_DEFAULT();
   }

   public static int DEFAULT_SCALE() {
      return DecimalType$.MODULE$.DEFAULT_SCALE();
   }

   public static int MAX_SCALE() {
      return DecimalType$.MODULE$.MAX_SCALE();
   }

   public static int MAX_PRECISION() {
      return DecimalType$.MODULE$.MAX_PRECISION();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int precision() {
      return this.precision;
   }

   public int scale() {
      return this.scale;
   }

   public String typeName() {
      int var10000 = this.precision();
      return "decimal(" + var10000 + "," + this.scale() + ")";
   }

   public String toString() {
      int var10000 = this.precision();
      return "DecimalType(" + var10000 + "," + this.scale() + ")";
   }

   public String sql() {
      return this.typeName().toUpperCase(Locale.ROOT);
   }

   public boolean isWiderThan(final DataType other) {
      return this.isWiderThanInternal(other);
   }

   private boolean isWiderThanInternal(final DataType other) {
      while(true) {
         if (!(other instanceof DecimalType)) {
            if (other instanceof IntegralType) {
               IntegralType var6 = (IntegralType)other;
               other = DecimalType$.MODULE$.forType(var6);
               continue;
            }

            return false;
         }

         DecimalType var5 = (DecimalType)other;
         return this.precision() - this.scale() >= var5.precision() - var5.scale() && this.scale() >= var5.scale();
      }
   }

   public boolean isTighterThan(final DataType other) {
      if (other instanceof DecimalType var4) {
         return this.precision() - this.scale() <= var4.precision() - var4.scale() && this.scale() <= var4.scale();
      } else if (other instanceof IntegralType var5) {
         DecimalType integerAsDecimal = DecimalType$.MODULE$.forType(var5);
         .MODULE$.assert(integerAsDecimal.scale() == 0);
         return this.precision() < integerAsDecimal.precision() && this.scale() == 0;
      } else {
         return false;
      }
   }

   public int defaultSize() {
      return this.precision() <= Decimal$.MODULE$.MAX_LONG_DIGITS() ? 8 : 16;
   }

   public String simpleString() {
      int var10000 = this.precision();
      return "decimal(" + var10000 + "," + this.scale() + ")";
   }

   public DecimalType asNullable() {
      return this;
   }

   public DecimalType copy(final int precision, final int scale) {
      return new DecimalType(precision, scale);
   }

   public int copy$default$1() {
      return this.precision();
   }

   public int copy$default$2() {
      return this.scale();
   }

   public String productPrefix() {
      return "DecimalType";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.precision());
         }
         case 1 -> {
            return BoxesRunTime.boxToInteger(this.scale());
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof DecimalType;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "precision";
         }
         case 1 -> {
            return "scale";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.precision());
      var1 = Statics.mix(var1, this.scale());
      return Statics.finalizeHash(var1, 2);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label38: {
            if (x$1 instanceof DecimalType) {
               DecimalType var4 = (DecimalType)x$1;
               if (this.precision() == var4.precision() && this.scale() == var4.scale() && var4.canEqual(this)) {
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

   public DecimalType(final int precision, final int scale) {
      this.precision = precision;
      this.scale = scale;
      Product.$init$(this);
      DecimalType$.MODULE$.checkNegativeScale(scale);
      if (scale > precision) {
         throw DataTypeErrors$.MODULE$.decimalCannotGreaterThanPrecisionError(scale, precision);
      } else if (precision > DecimalType$.MODULE$.MAX_PRECISION()) {
         throw DataTypeErrors$.MODULE$.decimalPrecisionExceedsMaxPrecisionError(precision, DecimalType$.MODULE$.MAX_PRECISION());
      }
   }

   public DecimalType(final int precision) {
      this(precision, 0);
   }

   public DecimalType() {
      this(10);
   }

   public static class Fixed$ {
      public static final Fixed$ MODULE$ = new Fixed$();

      public Option unapply(final DecimalType t) {
         return new Some(new Tuple2.mcII.sp(t.precision(), t.scale()));
      }
   }
}
