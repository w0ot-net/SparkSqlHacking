package org.apache.spark.ml.util;

import org.apache.spark.SparkContext;
import org.apache.spark.ml.param.Params;
import org.apache.spark.sql.SparkSession;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]c!B\n\u0015\u0001Yq\u0002\u0002C\u0012\u0001\u0005\u0003\u0005\u000b\u0011B\u0013\t\u000b-\u0002A\u0011\u0001\u0017\t\u000b=\u0002A\u0011\u000b\u0019\b\r\u0011#\u0002\u0012\u0001\fF\r\u0019\u0019B\u0003#\u0001\u0017\r\")1&\u0002C\u0001\u0015\")1*\u0002C\u0001\u0019\"9\u0001/BI\u0001\n\u0003\t\bb\u0002?\u0006#\u0003%\t! \u0005\u0006\u0017\u0016!\ta \u0005\u0007\u0017\u0016!\t!a\u0006\t\r-+A\u0011AA\u0011\u0011\u001d\tI#\u0002C\u0001\u0003WA\u0001\"a\u000f\u0006#\u0003%\t!\u001d\u0005\t\u0003{)\u0011\u0013!C\u0001{\"9\u0011\u0011F\u0003\u0005\u0002\u0005}\u0002bBA\u0015\u000b\u0011\u0005\u0011\u0011\n\u0005\b\u0003S)A\u0011AA)\u0005M!UMZ1vYR\u0004\u0016M]1ng^\u0013\u0018\u000e^3s\u0015\t)b#\u0001\u0003vi&d'BA\f\u0019\u0003\tiGN\u0003\u0002\u001a5\u0005)1\u000f]1sW*\u00111\u0004H\u0001\u0007CB\f7\r[3\u000b\u0003u\t1a\u001c:h'\t\u0001q\u0004\u0005\u0002!C5\tA#\u0003\u0002#)\tAQ\nT,sSR,'/\u0001\u0005j]N$\u0018M\\2f\u0007\u0001\u0001\"AJ\u0015\u000e\u0003\u001dR!\u0001\u000b\f\u0002\u000bA\f'/Y7\n\u0005):#A\u0002)be\u0006l7/\u0001\u0004=S:LGO\u0010\u000b\u0003[9\u0002\"\u0001\t\u0001\t\u000b\r\u0012\u0001\u0019A\u0013\u0002\u0011M\fg/Z%na2$\"!M\u001c\u0011\u0005I*T\"A\u001a\u000b\u0003Q\nQa]2bY\u0006L!AN\u001a\u0003\tUs\u0017\u000e\u001e\u0005\u0006q\r\u0001\r!O\u0001\u0005a\u0006$\b\u000e\u0005\u0002;\u0003:\u00111h\u0010\t\u0003yMj\u0011!\u0010\u0006\u0003}\u0011\na\u0001\u0010:p_Rt\u0014B\u0001!4\u0003\u0019\u0001&/\u001a3fM&\u0011!i\u0011\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0001\u001b\u0014a\u0005#fM\u0006,H\u000e\u001e)be\u0006l7o\u0016:ji\u0016\u0014\bC\u0001\u0011\u0006'\t)q\t\u0005\u00023\u0011&\u0011\u0011j\r\u0002\u0007\u0003:L(+\u001a4\u0015\u0003\u0015\u000bAb]1wK6+G/\u00193bi\u0006$b!M'O\u001fV\u0003\u0007\"B\u0012\b\u0001\u0004)\u0003\"\u0002\u001d\b\u0001\u0004I\u0004\"\u0002)\b\u0001\u0004\t\u0016AA:d!\t\u00116+D\u0001\u0019\u0013\t!\u0006D\u0001\u0007Ta\u0006\u00148nQ8oi\u0016DH\u000fC\u0004W\u000fA\u0005\t\u0019A,\u0002\u001b\u0015DHO]1NKR\fG-\u0019;b!\r\u0011\u0004LW\u0005\u00033N\u0012aa\u00149uS>t\u0007CA._\u001b\u0005a&BA/\u001d\u0003\u0019Q7o\u001c85g&\u0011q\f\u0018\u0002\b\u0015>\u0013'.Z2u\u0011\u001d\tw\u0001%AA\u0002\t\f\u0001\u0002]1sC6l\u0015\r\u001d\t\u0004ea\u001b\u0007CA.e\u0013\t)GL\u0001\u0004K-\u0006dW/\u001a\u0015\u0007\u000f\u001dT7.\u001c8\u0011\u0005IB\u0017BA54\u0005)!W\r\u001d:fG\u0006$X\rZ\u0001\b[\u0016\u001c8/Y4fC\u0005a\u0017AI;tK\u0002\u001a\u0018M^3NKR\fG-\u0019;bA]LG\u000f\u001b\u0011Ta\u0006\u00148nU3tg&|g.A\u0003tS:\u001cW-I\u0001p\u0003\u0015!d\u0006\r\u00181\u0003Y\u0019\u0018M^3NKR\fG-\u0019;bI\u0011,g-Y;mi\u0012\"T#\u0001:+\u0005]\u001b8&\u0001;\u0011\u0005UTX\"\u0001<\u000b\u0005]D\u0018!C;oG\",7m[3e\u0015\tI8'\u0001\u0006b]:|G/\u0019;j_:L!a\u001f<\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\ftCZ,W*\u001a;bI\u0006$\u0018\r\n3fM\u0006,H\u000e\u001e\u00136+\u0005q(F\u00012t)-\t\u0014\u0011AA\u0002\u0003\u000b\t\u0019\"!\u0006\t\u000b\rR\u0001\u0019A\u0013\t\u000baR\u0001\u0019A\u001d\t\reQ\u0001\u0019AA\u0004!\u0011\tI!a\u0004\u000e\u0005\u0005-!bAA\u00071\u0005\u00191/\u001d7\n\t\u0005E\u00111\u0002\u0002\r'B\f'o[*fgNLwN\u001c\u0005\u0006-*\u0001\ra\u0016\u0005\u0006C*\u0001\rA\u0019\u000b\nc\u0005e\u00111DA\u000f\u0003?AQaI\u0006A\u0002\u0015BQ\u0001O\u0006A\u0002eBa!G\u0006A\u0002\u0005\u001d\u0001\"\u0002,\f\u0001\u00049FcB\u0019\u0002$\u0005\u0015\u0012q\u0005\u0005\u0006G1\u0001\r!\n\u0005\u0006q1\u0001\r!\u000f\u0005\u000731\u0001\r!a\u0002\u0002#\u001d,G/T3uC\u0012\fG/\u0019+p'\u00064X\rF\u0005:\u0003[\ty#!\r\u00024!)1%\u0004a\u0001K!)\u0001+\u0004a\u0001#\"9a+\u0004I\u0001\u0002\u00049\u0006bB1\u000e!\u0003\u0005\rA\u0019\u0015\b\u001b\u001dT\u0017qG7oC\t\tI$A\u0014vg\u0016\u0004s-\u001a;NKR\fG-\u0019;b)>\u001c\u0016M^3!o&$\b\u000eI*qCJ\\7+Z:tS>t\u0017aG4fi6+G/\u00193bi\u0006$vnU1wK\u0012\"WMZ1vYR$3'A\u000ehKRlU\r^1eCR\fGk\\*bm\u0016$C-\u001a4bk2$H\u0005\u000e\u000b\ns\u0005\u0005\u00131IA#\u0003\u000fBQa\t\tA\u0002\u0015Ba!\u0007\tA\u0002\u0005\u001d\u0001\"\u0002,\u0011\u0001\u00049\u0006\"B1\u0011\u0001\u0004\u0011GcB\u001d\u0002L\u00055\u0013q\n\u0005\u0006GE\u0001\r!\n\u0005\u00073E\u0001\r!a\u0002\t\u000bY\u000b\u0002\u0019A,\u0015\u000be\n\u0019&!\u0016\t\u000b\r\u0012\u0002\u0019A\u0013\t\re\u0011\u0002\u0019AA\u0004\u0001"
)
public class DefaultParamsWriter extends MLWriter {
   private final Params instance;

   public static String getMetadataToSave(final Params instance, final SparkSession spark) {
      return DefaultParamsWriter$.MODULE$.getMetadataToSave(instance, spark);
   }

   public static String getMetadataToSave(final Params instance, final SparkSession spark, final Option extraMetadata) {
      return DefaultParamsWriter$.MODULE$.getMetadataToSave(instance, spark, extraMetadata);
   }

   public static String getMetadataToSave(final Params instance, final SparkSession spark, final Option extraMetadata, final Option paramMap) {
      return DefaultParamsWriter$.MODULE$.getMetadataToSave(instance, spark, extraMetadata, paramMap);
   }

   public static Option getMetadataToSave$default$4() {
      return DefaultParamsWriter$.MODULE$.getMetadataToSave$default$4();
   }

   public static Option getMetadataToSave$default$3() {
      return DefaultParamsWriter$.MODULE$.getMetadataToSave$default$3();
   }

   /** @deprecated */
   public static String getMetadataToSave(final Params instance, final SparkContext sc, final Option extraMetadata, final Option paramMap) {
      return DefaultParamsWriter$.MODULE$.getMetadataToSave(instance, sc, extraMetadata, paramMap);
   }

   public static void saveMetadata(final Params instance, final String path, final SparkSession spark) {
      DefaultParamsWriter$.MODULE$.saveMetadata(instance, path, spark);
   }

   public static void saveMetadata(final Params instance, final String path, final SparkSession spark, final Option extraMetadata) {
      DefaultParamsWriter$.MODULE$.saveMetadata(instance, path, spark, extraMetadata);
   }

   public static void saveMetadata(final Params instance, final String path, final SparkSession spark, final Option extraMetadata, final Option paramMap) {
      DefaultParamsWriter$.MODULE$.saveMetadata(instance, path, spark, extraMetadata, paramMap);
   }

   public static Option saveMetadata$default$5() {
      return DefaultParamsWriter$.MODULE$.saveMetadata$default$5();
   }

   public static Option saveMetadata$default$4() {
      return DefaultParamsWriter$.MODULE$.saveMetadata$default$4();
   }

   /** @deprecated */
   public static void saveMetadata(final Params instance, final String path, final SparkContext sc, final Option extraMetadata, final Option paramMap) {
      DefaultParamsWriter$.MODULE$.saveMetadata(instance, path, sc, extraMetadata, paramMap);
   }

   public void saveImpl(final String path) {
      DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
   }

   public DefaultParamsWriter(final Params instance) {
      this.instance = instance;
   }
}
