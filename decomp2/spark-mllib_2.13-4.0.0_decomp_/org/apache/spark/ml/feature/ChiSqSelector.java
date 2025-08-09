package org.apache.spark.ml.feature;

import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.stat.ChiSquareTest$;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructType;
import scala.reflect.ScalaSignature;

/** @deprecated */
@ScalaSignature(
   bytes = "\u0006\u0005\u00055h\u0001\u0002\r\u001a\u0005\u0011B\u0001\u0002\f\u0001\u0003\u0006\u0004%\t%\f\u0005\t\t\u0002\u0011\t\u0011)A\u0005]!)a\t\u0001C\u0001\u000f\")a\t\u0001C\u0001\u0019\")a\n\u0001C!\u001f\")\u0001\f\u0001C!3\")\u0011\r\u0001C!E\")Q\r\u0001C!M\")1\u000e\u0001C!Y\")q\u000e\u0001C!a\")1\u000f\u0001C!i\")q\u000f\u0001C!q\")1\u0010\u0001C!y\"9q\u0010\u0001Q\u0005R\u0005\u0005\u0001\u0002CA\u0015\u0001\u0001&\t\"a\u000b\t\u000f\u0005e\u0002\u0001\"\u0011\u0002<!9\u00111\r\u0001\u0005B\u0005\u0015\u0004bBA=\u0001\u0011\u0005\u00131P\u0004\b\u0003KK\u0002\u0012AAT\r\u0019A\u0012\u0004#\u0001\u0002*\"1a\t\u0006C\u0001\u0003\u001bDq!a4\u0015\t\u0003\n\t\u000eC\u0005\u0002ZR\t\t\u0011\"\u0003\u0002\\\ni1\t[5TcN+G.Z2u_JT!AG\u000e\u0002\u000f\u0019,\u0017\r^;sK*\u0011A$H\u0001\u0003[2T!AH\u0010\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u0001\n\u0013AB1qC\u000eDWMC\u0001#\u0003\ry'oZ\u0002\u0001'\t\u0001Q\u0005E\u0002'O%j\u0011!G\u0005\u0003Qe\u0011\u0001bU3mK\u000e$xN\u001d\t\u0003M)J!aK\r\u0003%\rC\u0017nU9TK2,7\r^8s\u001b>$W\r\\\u0001\u0004k&$W#\u0001\u0018\u0011\u0005=BdB\u0001\u00197!\t\tD'D\u00013\u0015\t\u00194%\u0001\u0004=e>|GO\u0010\u0006\u0002k\u0005)1oY1mC&\u0011q\u0007N\u0001\u0007!J,G-\u001a4\n\u0005eR$AB*ue&twM\u0003\u00028i!\u001a\u0011\u0001\u0010\"\u0011\u0005u\u0002U\"\u0001 \u000b\u0005}j\u0012AC1o]>$\u0018\r^5p]&\u0011\u0011I\u0010\u0002\u0006'&t7-Z\u0011\u0002\u0007\u0006)\u0011G\f\u001c/a\u0005!Q/\u001b3!Q\r\u0011AHQ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005!K\u0005C\u0001\u0014\u0001\u0011\u0015a3\u00011\u0001/Q\rIEH\u0011\u0015\u0004\u0007q\u0012E#\u0001%)\u0007\u0011a$)A\ttKRtU/\u001c+pa\u001a+\u0017\r^;sKN$\"\u0001U)\u000e\u0003\u0001AQAU\u0003A\u0002M\u000bQA^1mk\u0016\u0004\"\u0001V+\u000e\u0003QJ!A\u0016\u001b\u0003\u0007%sG\u000fK\u0002\u0006y\t\u000bQb]3u!\u0016\u00148-\u001a8uS2,GC\u0001)[\u0011\u0015\u0011f\u00011\u0001\\!\t!F,\u0003\u0002^i\t1Ai\\;cY\u0016D3A\u0002\u001f`C\u0005\u0001\u0017!\u0002\u001a/c9\u0002\u0014AB:fi\u001a\u0003(\u000f\u0006\u0002QG\")!k\u0002a\u00017\"\u001aq\u0001P0\u0002\rM,GO\u00123s)\t\u0001v\rC\u0003S\u0011\u0001\u00071\fK\u0002\ty%\f\u0013A[\u0001\u0006e9\u0012d\u0006M\u0001\u0007g\u0016$hi^3\u0015\u0005Ak\u0007\"\u0002*\n\u0001\u0004Y\u0006fA\u0005=S\u0006y1/\u001a;TK2,7\r^8s)f\u0004X\r\u0006\u0002Qc\")!K\u0003a\u0001]!\u001a!\u0002P0\u0002\u001dM,GOR3biV\u0014Xm]\"pYR\u0011\u0001+\u001e\u0005\u0006%.\u0001\rA\f\u0015\u0004\u0017q\u0012\u0015\u0001D:fi>+H\u000f];u\u0007>dGC\u0001)z\u0011\u0015\u0011F\u00021\u0001/Q\raAHQ\u0001\fg\u0016$H*\u00192fY\u000e{G\u000e\u0006\u0002Q{\")!+\u0004a\u0001]!\u001aQ\u0002\u0010\"\u0002-\u001d,GoU3mK\u000e$\u0018n\u001c8UKN$(+Z:vYR$B!a\u0001\u0002&A!\u0011QAA\u0010\u001d\u0011\t9!!\u0007\u000f\t\u0005%\u0011Q\u0003\b\u0005\u0003\u0017\t\u0019B\u0004\u0003\u0002\u000e\u0005EabA\u0019\u0002\u0010%\t!%\u0003\u0002!C%\u0011adH\u0005\u0004\u0003/i\u0012aA:rY&!\u00111DA\u000f\u0003\u001d\u0001\u0018mY6bO\u0016T1!a\u0006\u001e\u0013\u0011\t\t#a\t\u0003\u0013\u0011\u000bG/\u0019$sC6,'\u0002BA\u000e\u0003;Aq!a\n\u000f\u0001\u0004\t\u0019!\u0001\u0002eM\u0006\u00192M]3bi\u0016\u001cV\r\\3di>\u0014Xj\u001c3fYR)\u0011&!\f\u00020!)Af\u0004a\u0001]!9\u0011\u0011G\bA\u0002\u0005M\u0012aB5oI&\u001cWm\u001d\t\u0005)\u0006U2+C\u0002\u00028Q\u0012Q!\u0011:sCf\f1AZ5u)\rI\u0013Q\b\u0005\b\u0003\u007f\u0001\u0002\u0019AA!\u0003\u001d!\u0017\r^1tKR\u0004D!a\u0011\u0002PA1\u0011QIA$\u0003\u0017j!!!\b\n\t\u0005%\u0013Q\u0004\u0002\b\t\u0006$\u0018m]3u!\u0011\ti%a\u0014\r\u0001\u0011a\u0011\u0011KA\u001f\u0003\u0003\u0005\tQ!\u0001\u0002T\t\u0019q\fJ\u0019\u0012\t\u0005U\u00131\f\t\u0004)\u0006]\u0013bAA-i\t9aj\u001c;iS:<\u0007c\u0001+\u0002^%\u0019\u0011q\f\u001b\u0003\u0007\u0005s\u0017\u0010K\u0002\u0011y\t\u000bq\u0002\u001e:b]N4wN]7TG\",W.\u0019\u000b\u0005\u0003O\n\u0019\b\u0005\u0003\u0002j\u0005=TBAA6\u0015\u0011\ti'!\b\u0002\u000bQL\b/Z:\n\t\u0005E\u00141\u000e\u0002\u000b'R\u0014Xo\u0019;UsB,\u0007bBA;#\u0001\u0007\u0011qM\u0001\u0007g\u000eDW-\\1)\u0007Ea$)\u0001\u0003d_BLHc\u0001%\u0002~!9\u0011q\u0010\nA\u0002\u0005\u0005\u0015!B3yiJ\f\u0007\u0003BAB\u0003\u0013k!!!\"\u000b\u0007\u0005\u001d5$A\u0003qCJ\fW.\u0003\u0003\u0002\f\u0006\u0015%\u0001\u0003)be\u0006lW*\u00199)\u0007Ia$\tK\u0006\u0001\u0003#\u000b9*!'\u0002\u001e\u0006}\u0005c\u0001+\u0002\u0014&\u0019\u0011Q\u0013\u001b\u0003\u0015\u0011,\u0007O]3dCR,G-A\u0004nKN\u001c\u0018mZ3\"\u0005\u0005m\u0015!J;tK\u0002*f.\u001b<be&\fG/\u001a$fCR,(/Z*fY\u0016\u001cGo\u001c:!S:\u001cH/Z1e\u0003\u0015\u0019\u0018N\\2fC\t\t\t+A\u00034]Er\u0013\u0007K\u0002\u0001y\t\u000bQb\u00115j'F\u001cV\r\\3di>\u0014\bC\u0001\u0014\u0015'\u001d!\u00121VAY\u0003{\u00032\u0001VAW\u0013\r\ty\u000b\u000e\u0002\u0007\u0003:L(+\u001a4\u0011\u000b\u0005M\u0016\u0011\u0018%\u000e\u0005\u0005U&bAA\\7\u0005!Q\u000f^5m\u0013\u0011\tY,!.\u0003+\u0011+g-Y;miB\u000b'/Y7t%\u0016\fG-\u00192mKB!\u0011qXAe\u001b\t\t\tM\u0003\u0003\u0002D\u0006\u0015\u0017AA5p\u0015\t\t9-\u0001\u0003kCZ\f\u0017\u0002BAf\u0003\u0003\u0014AbU3sS\u0006d\u0017N_1cY\u0016$\"!a*\u0002\t1|\u0017\r\u001a\u000b\u0004\u0011\u0006M\u0007BBAk-\u0001\u0007a&\u0001\u0003qCRD\u0007f\u0001\f=\u0005\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011Q\u001c\t\u0005\u0003?\f)/\u0004\u0002\u0002b*!\u00111]Ac\u0003\u0011a\u0017M\\4\n\t\u0005\u001d\u0018\u0011\u001d\u0002\u0007\u001f\nTWm\u0019;)\u0007Qa$\tK\u0002\u0014y\t\u0003"
)
public final class ChiSqSelector extends Selector {
   private final String uid;

   public static ChiSqSelector load(final String path) {
      return ChiSqSelector$.MODULE$.load(path);
   }

   public static MLReader read() {
      return ChiSqSelector$.MODULE$.read();
   }

   public String uid() {
      return this.uid;
   }

   public ChiSqSelector setNumTopFeatures(final int value) {
      return (ChiSqSelector)super.setNumTopFeatures(value);
   }

   public ChiSqSelector setPercentile(final double value) {
      return (ChiSqSelector)super.setPercentile(value);
   }

   public ChiSqSelector setFpr(final double value) {
      return (ChiSqSelector)super.setFpr(value);
   }

   public ChiSqSelector setFdr(final double value) {
      return (ChiSqSelector)super.setFdr(value);
   }

   public ChiSqSelector setFwe(final double value) {
      return (ChiSqSelector)super.setFwe(value);
   }

   public ChiSqSelector setSelectorType(final String value) {
      return (ChiSqSelector)super.setSelectorType(value);
   }

   public ChiSqSelector setFeaturesCol(final String value) {
      return (ChiSqSelector)super.setFeaturesCol(value);
   }

   public ChiSqSelector setOutputCol(final String value) {
      return (ChiSqSelector)super.setOutputCol(value);
   }

   public ChiSqSelector setLabelCol(final String value) {
      return (ChiSqSelector)super.setLabelCol(value);
   }

   public Dataset getSelectionTestResult(final Dataset df) {
      return ChiSquareTest$.MODULE$.test(df, this.getFeaturesCol(), this.getLabelCol(), true);
   }

   public ChiSqSelectorModel createSelectorModel(final String uid, final int[] indices) {
      return new ChiSqSelectorModel(uid, indices);
   }

   public ChiSqSelectorModel fit(final Dataset dataset) {
      return (ChiSqSelectorModel)super.fit(dataset);
   }

   public StructType transformSchema(final StructType schema) {
      return super.transformSchema(schema);
   }

   public ChiSqSelector copy(final ParamMap extra) {
      return (ChiSqSelector)this.defaultCopy(extra);
   }

   public ChiSqSelector(final String uid) {
      this.uid = uid;
   }

   public ChiSqSelector() {
      this(Identifiable$.MODULE$.randomUID("chiSqSelector"));
   }
}
