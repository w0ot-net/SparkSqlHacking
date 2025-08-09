package org.apache.spark.sql.types;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.Stable;
import org.apache.spark.sql.catalyst.util.CollationFactory;
import org.apache.spark.sql.catalyst.util.QuotingUtils$;
import org.apache.spark.sql.catalyst.util.ResolveDefaultColumnsUtils$;
import org.apache.spark.sql.catalyst.util.StringConcat;
import org.apache.spark.util.SparkSchemaUtils.;
import org.json4s.JObject;
import org.json4s.JString;
import org.json4s.JValue;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005\tEd\u0001B\u001c9\u0001\u000eC\u0001\"\u0017\u0001\u0003\u0016\u0004%\tA\u0017\u0005\tG\u0002\u0011\t\u0012)A\u00057\"AA\r\u0001BK\u0002\u0013\u0005Q\r\u0003\u0005k\u0001\tE\t\u0015!\u0003g\u0011!Y\u0007A!f\u0001\n\u0003a\u0007\u0002\u00039\u0001\u0005#\u0005\u000b\u0011B7\t\u0011E\u0004!Q3A\u0005\u0002ID\u0001B\u001e\u0001\u0003\u0012\u0003\u0006Ia\u001d\u0005\u0006o\u0002!\t\u0001\u001f\u0005\u0006o\u0002!\tB \u0005\b\u007f\u0002!\tAOA\u0001\u0011\u001d\tY\u0003\u0001C!\u0003[A\u0001\"a\f\u0001\t\u0003Q\u0014\u0011\u0007\u0005\b\u0003\u001b\u0002A\u0011BA\u0019\u0011)\ty\u0005\u0001EC\u0002\u0013%\u0011\u0011\u000b\u0005\b\u00033\u0002A\u0011BA.\u0011\u001d\t\t\u0007\u0001C\u0005\u0003GBq!a\u001a\u0001\t\u0003\tI\u0007C\u0004\u0002p\u0001!\t!!\u001d\t\u0011\u0005e\u0004\u0001\"\u0001;\u0003cBq!a\u001f\u0001\t\u0003\ti\b\u0003\u0004\u0002\u0004\u0002!\tA \u0005\b\u0003\u000b\u0003A\u0011AA9\u0011\u001d\t9\t\u0001C\u0001\u0003\u0013C\u0001\"!$\u0001\t\u0003Q\u0014\u0011\u000f\u0005\b\u0003\u001f\u0003A\u0011BAI\u0011\u001d\t\t\u000b\u0001C\u0005\u0003#C!\"a)\u0001\u0011\u000b\u0007I\u0011BAI\u0011\u001dY\u0004\u0001\"\u0001;\u0003#Ca!!*\u0001\t\u0003Q\u0006\"CAT\u0001\u0005\u0005I\u0011AAU\u0011%\t\u0019\fAI\u0001\n\u0003\t)\fC\u0005\u0002L\u0002\t\n\u0011\"\u0001\u0002N\"I\u0011\u0011\u001b\u0001\u0012\u0002\u0013\u0005\u00111\u001b\u0005\n\u0003/\u0004\u0011\u0013!C\u0001\u00033D\u0011\"!8\u0001\u0003\u0003%\t%!%\t\u0013\u0005}\u0007!!A\u0005\u0002\u0005\u0005\b\"CAr\u0001\u0005\u0005I\u0011AAs\u0011%\t\t\u0010AA\u0001\n\u0003\n\u0019\u0010C\u0005\u0003\u0002\u0001\t\t\u0011\"\u0001\u0003\u0004!I!q\u0001\u0001\u0002\u0002\u0013\u0005#\u0011\u0002\u0005\n\u0005\u001b\u0001\u0011\u0011!C!\u0005\u001fA\u0011B!\u0005\u0001\u0003\u0003%\tEa\u0005\b\u0013\t\r\u0002(!A\t\u0002\t\u0015b\u0001C\u001c9\u0003\u0003E\tAa\n\t\r]lC\u0011\u0001B \u0011%\tY#LA\u0001\n\u000b\u0012\t\u0005C\u0005\u0003D5\n\t\u0011\"!\u0003F!I!qJ\u0017\u0012\u0002\u0013\u0005\u00111\u001b\u0005\n\u0005#j\u0013\u0013!C\u0001\u00033D\u0011Ba\u0015.\u0003\u0003%\tI!\u0016\t\u0013\t\rT&%A\u0005\u0002\u0005M\u0007\"\u0003B3[E\u0005I\u0011AAm\u0011%\u00119'LA\u0001\n\u0013\u0011IGA\u0006TiJ,8\r\u001e$jK2$'BA\u001d;\u0003\u0015!\u0018\u0010]3t\u0015\tYD(A\u0002tc2T!!\u0010 \u0002\u000bM\u0004\u0018M]6\u000b\u0005}\u0002\u0015AB1qC\u000eDWMC\u0001B\u0003\ry'oZ\u0002\u0001'\u0011\u0001AIS'\u0011\u0005\u0015CU\"\u0001$\u000b\u0003\u001d\u000bQa]2bY\u0006L!!\u0013$\u0003\r\u0005s\u0017PU3g!\t)5*\u0003\u0002M\r\n9\u0001K]8ek\u000e$\bC\u0001(W\u001d\tyEK\u0004\u0002Q'6\t\u0011K\u0003\u0002S\u0005\u00061AH]8pizJ\u0011aR\u0005\u0003+\u001a\u000bq\u0001]1dW\u0006<W-\u0003\u0002X1\na1+\u001a:jC2L'0\u00192mK*\u0011QKR\u0001\u0005]\u0006lW-F\u0001\\!\ta\u0006M\u0004\u0002^=B\u0011\u0001KR\u0005\u0003?\u001a\u000ba\u0001\u0015:fI\u00164\u0017BA1c\u0005\u0019\u0019FO]5oO*\u0011qLR\u0001\u0006]\u0006lW\rI\u0001\tI\u0006$\u0018\rV=qKV\ta\r\u0005\u0002hQ6\t\u0001(\u0003\u0002jq\tAA)\u0019;b)f\u0004X-A\u0005eCR\fG+\u001f9fA\u0005Aa.\u001e7mC\ndW-F\u0001n!\t)e.\u0003\u0002p\r\n9!i\\8mK\u0006t\u0017!\u00038vY2\f'\r\\3!\u0003!iW\r^1eCR\fW#A:\u0011\u0005\u001d$\u0018BA;9\u0005!iU\r^1eCR\f\u0017!C7fi\u0006$\u0017\r^1!\u0003\u0019a\u0014N\\5u}Q)\u0011P_>}{B\u0011q\r\u0001\u0005\u00063&\u0001\ra\u0017\u0005\u0006I&\u0001\rA\u001a\u0005\bW&\u0001\n\u00111\u0001n\u0011\u001d\t\u0018\u0002%AA\u0002M$\u0012!_\u0001\u0015EVLG\u000e\u001a$pe6\fG\u000f^3e'R\u0014\u0018N\\4\u0015\u0011\u0005\r\u0011\u0011BA\u0007\u0003C\u00012!RA\u0003\u0013\r\t9A\u0012\u0002\u0005+:LG\u000f\u0003\u0004\u0002\f-\u0001\raW\u0001\u0007aJ,g-\u001b=\t\u000f\u0005=1\u00021\u0001\u0002\u0012\u0005a1\u000f\u001e:j]\u001e\u001cuN\\2biB!\u00111CA\u000f\u001b\t\t)B\u0003\u0003\u0002\u0018\u0005e\u0011\u0001B;uS2T1!a\u0007;\u0003!\u0019\u0017\r^1msN$\u0018\u0002BA\u0010\u0003+\u0011Ab\u0015;sS:<7i\u001c8dCRDq!a\t\f\u0001\u0004\t)#\u0001\u0005nCb$U\r\u001d;i!\r)\u0015qE\u0005\u0004\u0003S1%aA%oi\u0006AAo\\*ue&tw\rF\u0001\\\u0003%Q7o\u001c8WC2,X-\u0006\u0002\u00024A!\u0011QGA$\u001d\u0011\t9$!\u0011\u000f\t\u0005e\u0012Q\b\b\u0004!\u0006m\u0012\"A!\n\u0007\u0005}\u0002)\u0001\u0004kg>tGg]\u0005\u0005\u0003\u0007\n)%A\u0004Kg>t\u0017i\u0015+\u000b\u0007\u0005}\u0002)\u0003\u0003\u0002J\u0005-#A\u0002&WC2,XM\u0003\u0003\u0002D\u0005\u0015\u0013\u0001D7fi\u0006$\u0017\r^1Kg>t\u0017!E2pY2\fG/[8o\u001b\u0016$\u0018\rZ1uCV\u0011\u00111\u000b\t\u00069\u0006U3lW\u0005\u0004\u0003/\u0012'aA'ba\u0006\u0001\u0012n]\"pY2\fG/\u001a3TiJLgn\u001a\u000b\u0004[\u0006u\u0003BBA0!\u0001\u0007a-\u0001\u0002ei\u0006!2o\u00195f[\u0006\u001cu\u000e\u001c7bi&|gNV1mk\u0016$2aWA3\u0011\u0019\ty&\u0005a\u0001M\u0006Yq/\u001b;i\u0007>lW.\u001a8u)\rI\u00181\u000e\u0005\u0007\u0003[\u0012\u0002\u0019A.\u0002\u000f\r|W.\\3oi\u0006Qq-\u001a;D_6lWM\u001c;\u0015\u0005\u0005M\u0004\u0003B#\u0002vmK1!a\u001eG\u0005\u0019y\u0005\u000f^5p]\u0006Qq-\u001a;EK\u001a\fW\u000f\u001c;\u0002/]LG\u000f[\"veJ,g\u000e\u001e#fM\u0006,H\u000e\u001e,bYV,GcA=\u0002\u0000!1\u0011\u0011Q\u000bA\u0002m\u000bQA^1mk\u0016\f\u0001d\u00197fCJ\u001cUO\u001d:f]R$UMZ1vYR4\u0016\r\\;f\u0003Y9W\r^\"veJ,g\u000e\u001e#fM\u0006,H\u000e\u001e,bYV,\u0017!G<ji\",\u00050[:uK:\u001cW\rR3gCVdGOV1mk\u0016$2!_AF\u0011\u0019\t\t\t\u0007a\u00017\u0006Ar-\u001a;Fq&\u001cH/\u001a8dK\u0012+g-Y;miZ\u000bG.^3\u0002\u001b\u001d,G\u000f\u0012#M\t\u00164\u0017-\u001e7u+\t\t\u0019\n\u0005\u0003\u0002\u0016\u0006}UBAAL\u0015\u0011\tI*a'\u0002\t1\fgn\u001a\u0006\u0003\u0003;\u000bAA[1wC&\u0019\u0011-a&\u0002\u001b\u001d,G\u000f\u0012#M\u0007>lW.\u001a8u\u0003\u001dqW\u000f\u001c7E\t2\u000bQ\u0001^8E\t2\u000bAaY8qsRI\u00110a+\u0002.\u0006=\u0016\u0011\u0017\u0005\b3~\u0001\n\u00111\u0001\\\u0011\u001d!w\u0004%AA\u0002\u0019Dqa[\u0010\u0011\u0002\u0003\u0007Q\u000eC\u0004r?A\u0005\t\u0019A:\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011q\u0017\u0016\u00047\u0006e6FAA^!\u0011\ti,a2\u000e\u0005\u0005}&\u0002BAa\u0003\u0007\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005\u0015g)\u0001\u0006b]:|G/\u0019;j_:LA!!3\u0002@\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u0011q\u001a\u0016\u0004M\u0006e\u0016AD2paf$C-\u001a4bk2$HeM\u000b\u0003\u0003+T3!\\A]\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIQ*\"!a7+\u0007M\fI,A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003K\ta\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002h\u00065\bcA#\u0002j&\u0019\u00111\u001e$\u0003\u0007\u0005s\u0017\u0010C\u0005\u0002p\u001a\n\t\u00111\u0001\u0002&\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!>\u0011\r\u0005]\u0018Q`At\u001b\t\tIPC\u0002\u0002|\u001a\u000b!bY8mY\u0016\u001cG/[8o\u0013\u0011\ty0!?\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0004[\n\u0015\u0001\"CAxQ\u0005\u0005\t\u0019AAt\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005M%1\u0002\u0005\n\u0003_L\u0013\u0011!a\u0001\u0003K\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003K\ta!Z9vC2\u001cHcA7\u0003\u0016!I\u0011q^\u0016\u0002\u0002\u0003\u0007\u0011q\u001d\u0015\u0004\u0001\te\u0001\u0003\u0002B\u000e\u0005?i!A!\b\u000b\u0007\u0005\u0015G(\u0003\u0003\u0003\"\tu!AB*uC\ndW-A\u0006TiJ,8\r\u001e$jK2$\u0007CA4.'\u0015i#\u0011\u0006B\u001b!%\u0011YC!\r\\M6\u001c\u00180\u0004\u0002\u0003.)\u0019!q\u0006$\u0002\u000fI,h\u000e^5nK&!!1\u0007B\u0017\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g\u000e\u000e\t\u0005\u0005o\u0011i$\u0004\u0002\u0003:)!!1HAN\u0003\tIw.C\u0002X\u0005s!\"A!\n\u0015\u0005\u0005M\u0015!B1qa2LH#C=\u0003H\t%#1\nB'\u0011\u0015I\u0006\u00071\u0001\\\u0011\u0015!\u0007\u00071\u0001g\u0011\u001dY\u0007\u0007%AA\u00025Dq!\u001d\u0019\u0011\u0002\u0003\u00071/A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00134\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u0012\"\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0005/\u0012y\u0006E\u0003F\u0003k\u0012I\u0006E\u0004F\u00057Zf-\\:\n\u0007\tucI\u0001\u0004UkBdW\r\u000e\u0005\t\u0005C\u001a\u0014\u0011!a\u0001s\u0006\u0019\u0001\u0010\n\u0019\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00134\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%i\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011!1\u000e\t\u0005\u0003+\u0013i'\u0003\u0003\u0003p\u0005]%AB(cU\u0016\u001cG\u000f"
)
public class StructField implements Product, Serializable {
   private Map collationMetadata;
   private String nullDDL;
   private final String name;
   private final DataType dataType;
   private final boolean nullable;
   private final Metadata metadata;
   private volatile byte bitmap$0;

   public static Metadata $lessinit$greater$default$4() {
      return StructField$.MODULE$.$lessinit$greater$default$4();
   }

   public static boolean $lessinit$greater$default$3() {
      return StructField$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option unapply(final StructField x$0) {
      return StructField$.MODULE$.unapply(x$0);
   }

   public static Metadata apply$default$4() {
      return StructField$.MODULE$.apply$default$4();
   }

   public static boolean apply$default$3() {
      return StructField$.MODULE$.apply$default$3();
   }

   public static StructField apply(final String name, final DataType dataType, final boolean nullable, final Metadata metadata) {
      return StructField$.MODULE$.apply(name, dataType, nullable, metadata);
   }

   public static Function1 tupled() {
      return StructField$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return StructField$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String name() {
      return this.name;
   }

   public DataType dataType() {
      return this.dataType;
   }

   public boolean nullable() {
      return this.nullable;
   }

   public Metadata metadata() {
      return this.metadata;
   }

   public void buildFormattedString(final String prefix, final StringConcat stringConcat, final int maxDepth) {
      if (maxDepth > 0) {
         stringConcat.append(prefix + "-- " + .MODULE$.escapeMetaCharacters(this.name()) + ": " + this.dataType().typeName() + " (nullable = " + this.nullable() + ")\n");
         DataType$.MODULE$.buildFormattedString(this.dataType(), prefix + "    |", stringConcat, maxDepth);
      }
   }

   public String toString() {
      String var10000 = this.name();
      return "StructField(" + var10000 + "," + this.dataType() + "," + this.nullable() + ")";
   }

   public JValue jsonValue() {
      return org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("name"), this.name()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("type"), this.dataType().jsonValue()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), scala.Predef..MODULE$.$conforms())), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("nullable"), BoxesRunTime.boxToBoolean(this.nullable())), (x) -> $anonfun$jsonValue$3(BoxesRunTime.unboxToBoolean(x))))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("metadata"), this.metadataJson()));
   }

   private JValue metadataJson() {
      JValue metadataJsonValue = this.metadata().jsonValue();
      if (metadataJsonValue instanceof JObject var4) {
         List fields = var4.obj();
         if (this.collationMetadata().nonEmpty()) {
            List collationFields = this.collationMetadata().map((kv) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(kv._1()), new JString((String)kv._2()))).toList();
            return new JObject((List)fields.$colon$plus(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(DataType$.MODULE$.COLLATIONS_METADATA_KEY()), new JObject(collationFields))));
         }
      }

      return metadataJsonValue;
   }

   private Map collationMetadata$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            scala.collection.mutable.Map fieldToCollationMap = (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
            this.visitRecursively$1(this.dataType(), this.name(), fieldToCollationMap);
            this.collationMetadata = fieldToCollationMap.toMap(scala..less.colon.less..MODULE$.refl());
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.collationMetadata;
   }

   private Map collationMetadata() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.collationMetadata$lzycompute() : this.collationMetadata;
   }

   private boolean isCollatedString(final DataType dt) {
      if (dt instanceof StringType var4) {
         return !var4.isUTF8BinaryCollation();
      } else {
         return false;
      }
   }

   private String schemaCollationValue(final DataType dt) {
      label18: {
         if (dt instanceof StringType var4) {
            IndeterminateStringType$ var5 = IndeterminateStringType$.MODULE$;
            if (var4 == null) {
               if (var5 != null) {
                  break label18;
               }
            } else if (!var4.equals(var5)) {
               break label18;
            }
         }

         throw org.apache.spark.SparkException..MODULE$.internalError("Unexpected data type " + dt);
      }

      CollationFactory.Collation collation = CollationFactory.fetchCollation(var4.collationId());
      return collation.identifier().toStringWithoutVersion();
   }

   public StructField withComment(final String comment) {
      Metadata newMetadata = (new MetadataBuilder()).withMetadata(this.metadata()).putString("comment", comment).build();
      String x$2 = this.copy$default$1();
      DataType x$3 = this.copy$default$2();
      boolean x$4 = this.copy$default$3();
      return this.copy(x$2, x$3, x$4, newMetadata);
   }

   public Option getComment() {
      return (Option)(this.metadata().contains("comment") ? scala.Option..MODULE$.apply(this.metadata().getString("comment")) : scala.None..MODULE$);
   }

   public Option getDefault() {
      return (Option)(this.metadata().contains(StructType$.MODULE$.SQL_FUNCTION_DEFAULT_METADATA_KEY()) ? scala.Option..MODULE$.apply(this.metadata().getString(StructType$.MODULE$.SQL_FUNCTION_DEFAULT_METADATA_KEY())) : scala.None..MODULE$);
   }

   public StructField withCurrentDefaultValue(final String value) {
      Metadata newMetadata = (new MetadataBuilder()).withMetadata(this.metadata()).putString(ResolveDefaultColumnsUtils$.MODULE$.CURRENT_DEFAULT_COLUMN_METADATA_KEY(), value).build();
      String x$2 = this.copy$default$1();
      DataType x$3 = this.copy$default$2();
      boolean x$4 = this.copy$default$3();
      return this.copy(x$2, x$3, x$4, newMetadata);
   }

   public StructField clearCurrentDefaultValue() {
      Metadata newMetadata = (new MetadataBuilder()).withMetadata(this.metadata()).remove(ResolveDefaultColumnsUtils$.MODULE$.CURRENT_DEFAULT_COLUMN_METADATA_KEY()).build();
      String x$2 = this.copy$default$1();
      DataType x$3 = this.copy$default$2();
      boolean x$4 = this.copy$default$3();
      return this.copy(x$2, x$3, x$4, newMetadata);
   }

   public Option getCurrentDefaultValue() {
      return (Option)(this.metadata().contains(ResolveDefaultColumnsUtils$.MODULE$.CURRENT_DEFAULT_COLUMN_METADATA_KEY()) ? scala.Option..MODULE$.apply(this.metadata().getString(ResolveDefaultColumnsUtils$.MODULE$.CURRENT_DEFAULT_COLUMN_METADATA_KEY())) : scala.None..MODULE$);
   }

   public StructField withExistenceDefaultValue(final String value) {
      Metadata newMetadata = (new MetadataBuilder()).withMetadata(this.metadata()).putString(ResolveDefaultColumnsUtils$.MODULE$.EXISTS_DEFAULT_COLUMN_METADATA_KEY(), value).build();
      String x$2 = this.copy$default$1();
      DataType x$3 = this.copy$default$2();
      boolean x$4 = this.copy$default$3();
      return this.copy(x$2, x$3, x$4, newMetadata);
   }

   public Option getExistenceDefaultValue() {
      return (Option)(this.metadata().contains(ResolveDefaultColumnsUtils$.MODULE$.EXISTS_DEFAULT_COLUMN_METADATA_KEY()) ? scala.Option..MODULE$.apply(this.metadata().getString(ResolveDefaultColumnsUtils$.MODULE$.EXISTS_DEFAULT_COLUMN_METADATA_KEY())) : scala.None..MODULE$);
   }

   private String getDDLDefault() {
      return (String)this.getDefault().orElse(() -> this.getCurrentDefaultValue()).map((x$1) -> " DEFAULT " + x$1).getOrElse(() -> "");
   }

   private String getDDLComment() {
      return (String)this.getComment().map((str) -> QuotingUtils$.MODULE$.escapeSingleQuotedString(str)).map((x$2) -> " COMMENT '" + x$2 + "'").getOrElse(() -> "");
   }

   private String nullDDL$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.nullDDL = this.nullable() ? "" : " NOT NULL";
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.nullDDL;
   }

   private String nullDDL() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.nullDDL$lzycompute() : this.nullDDL;
   }

   public String sql() {
      String var10000 = QuotingUtils$.MODULE$.quoteIfNeeded(this.name());
      return var10000 + ": " + this.dataType().sql() + this.nullDDL() + this.getDDLComment();
   }

   public String toDDL() {
      String var10000 = QuotingUtils$.MODULE$.quoteIfNeeded(this.name());
      return var10000 + " " + this.dataType().sql() + this.nullDDL() + this.getDDLDefault() + this.getDDLComment();
   }

   public StructField copy(final String name, final DataType dataType, final boolean nullable, final Metadata metadata) {
      return new StructField(name, dataType, nullable, metadata);
   }

   public String copy$default$1() {
      return this.name();
   }

   public DataType copy$default$2() {
      return this.dataType();
   }

   public boolean copy$default$3() {
      return this.nullable();
   }

   public Metadata copy$default$4() {
      return this.metadata();
   }

   public String productPrefix() {
      return "StructField";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.name();
         }
         case 1 -> {
            return this.dataType();
         }
         case 2 -> {
            return BoxesRunTime.boxToBoolean(this.nullable());
         }
         case 3 -> {
            return this.metadata();
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
      return x$1 instanceof StructField;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "name";
         }
         case 1 -> {
            return "dataType";
         }
         case 2 -> {
            return "nullable";
         }
         case 3 -> {
            return "metadata";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.name()));
      var1 = Statics.mix(var1, Statics.anyHash(this.dataType()));
      var1 = Statics.mix(var1, this.nullable() ? 1231 : 1237);
      var1 = Statics.mix(var1, Statics.anyHash(this.metadata()));
      return Statics.finalizeHash(var1, 4);
   }

   public boolean equals(final Object x$1) {
      boolean var10;
      if (this != x$1) {
         label67: {
            if (x$1 instanceof StructField) {
               StructField var4 = (StructField)x$1;
               if (this.nullable() == var4.nullable()) {
                  label60: {
                     String var10000 = this.name();
                     String var5 = var4.name();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label60;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label60;
                     }

                     DataType var8 = this.dataType();
                     DataType var6 = var4.dataType();
                     if (var8 == null) {
                        if (var6 != null) {
                           break label60;
                        }
                     } else if (!var8.equals(var6)) {
                        break label60;
                     }

                     Metadata var9 = this.metadata();
                     Metadata var7 = var4.metadata();
                     if (var9 == null) {
                        if (var7 != null) {
                           break label60;
                        }
                     } else if (!var9.equals(var7)) {
                        break label60;
                     }

                     if (var4.canEqual(this)) {
                        break label67;
                     }
                  }
               }
            }

            var10 = false;
            return var10;
         }
      }

      var10 = true;
      return var10;
   }

   // $FF: synthetic method
   public static final JValue $anonfun$jsonValue$3(final boolean x) {
      return org.json4s.JsonDSL..MODULE$.boolean2jvalue(x);
   }

   private final void visitRecursively$1(final DataType dt, final String path, final scala.collection.mutable.Map fieldToCollationMap$1) {
      if (dt instanceof ArrayType var6) {
         this.processDataType$1(var6.elementType(), path + ".element", fieldToCollationMap$1);
         BoxedUnit var11 = BoxedUnit.UNIT;
      } else if (dt instanceof MapType var7) {
         this.processDataType$1(var7.keyType(), path + ".key", fieldToCollationMap$1);
         this.processDataType$1(var7.valueType(), path + ".value", fieldToCollationMap$1);
         BoxedUnit var10 = BoxedUnit.UNIT;
      } else {
         if (dt instanceof StringType var8) {
            if (this.isCollatedString(var8)) {
               fieldToCollationMap$1.update(path, this.schemaCollationValue(var8));
               BoxedUnit var9 = BoxedUnit.UNIT;
               return;
            }
         }

         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   private final void processDataType$1(final DataType dt, final String path, final scala.collection.mutable.Map fieldToCollationMap$1) {
      if (this.isCollatedString(dt)) {
         fieldToCollationMap$1.update(path, this.schemaCollationValue(dt));
      } else {
         this.visitRecursively$1(dt, path, fieldToCollationMap$1);
      }
   }

   public StructField(final String name, final DataType dataType, final boolean nullable, final Metadata metadata) {
      this.name = name;
      this.dataType = dataType;
      this.nullable = nullable;
      this.metadata = metadata;
      Product.$init$(this);
   }

   public StructField() {
      this((String)null, (DataType)null, StructField$.MODULE$.$lessinit$greater$default$3(), StructField$.MODULE$.$lessinit$greater$default$4());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
