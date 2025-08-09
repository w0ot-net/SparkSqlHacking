package org.apache.spark.ml.feature;

import java.io.IOException;
import java.io.Serializable;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasLabelCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.AbstractFunction1;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\rMa\u0001\u0002\u001c8\u0001\tC\u0001B\u0015\u0001\u0003\u0006\u0004%\te\u0015\u0005\tU\u0002\u0011\t\u0011)A\u0005)\"AA\u000e\u0001BC\u0002\u0013\u0005Q\u000e\u0003\u0005w\u0001\t\u0005\t\u0015!\u0003o\u0011\u0019A\b\u0001\"\u0001:s\"1\u0001\u0010\u0001C\u0001syDaa \u0001\u0005\u0002\u0005\u0005\u0001bBA\u0006\u0001\u0011\u0005\u0011Q\u0002\u0005\b\u0003'\u0001A\u0011CA\u000b\u0011\u001d\ti\u0002\u0001C!\u0003?Aq!!\u001b\u0001\t\u0003\nY\u0007C\u0004\u0002\u0000\u0001!\t%!!\t\u000f\u0005U\u0005\u0001\"\u0011\u0002\u0018\"9\u0011\u0011\u0015\u0001\u0005B\u0005\rvaBAUo!\u0005\u00111\u0016\u0004\u0007m]B\t!!,\t\ra\u0004B\u0011AAf\u0011\u001d\ti\r\u0005C!\u0003\u001fDq!!7\u0011\t\u0003\nYNB\u0004\u0002dB\u0001\u0001#!:\t\u0013\u0005\u001dHC!A!\u0002\u00139\u0005B\u0002=\u0015\t\u0003\tIO\u0002\u0004\u0002rR!\u00151\u001f\u0005\nY^\u0011)\u001a!C\u0001\u0005\u0013A\u0011B^\f\u0003\u0012\u0003\u0006IAa\u0003\t\ra<B\u0011\u0001B\t\u0011%\tyhFA\u0001\n\u0003\u0011I\u0002C\u0005\u0003\u001e]\t\n\u0011\"\u0001\u0003 !I!1G\f\u0002\u0002\u0013\u0005#Q\u0007\u0005\n\u0005\u0003:\u0012\u0011!C\u0001\u0005\u0007B\u0011B!\u0012\u0018\u0003\u0003%\tAa\u0012\t\u0013\t5s#!A\u0005B\t=\u0003\"\u0003B//\u0005\u0005I\u0011\u0001B0\u0011%\u0011\u0019gFA\u0001\n\u0003\u0012)\u0007C\u0005\u0003j]\t\t\u0011\"\u0011\u0003l!I\u0011\u0011U\f\u0002\u0002\u0013\u0005#Q\u000e\u0005\n\u0005_:\u0012\u0011!C!\u0005c:\u0011B!\u001e\u0015\u0003\u0003EIAa\u001e\u0007\u0013\u0005EH#!A\t\n\te\u0004B\u0002=(\t\u0003\u00119\tC\u0005\u0002\"\u001e\n\t\u0011\"\u0012\u0003n!I!\u0011R\u0014\u0002\u0002\u0013\u0005%1\u0012\u0005\n\u0005\u001f;\u0013\u0011!CA\u0005#CqA!(\u0015\t#\u0012yJ\u0002\u0004\u0003*B!!1\u0016\u0005\u0007q6\"\tA!,\t\u0013\tEVF1A\u0005\n\tU\u0002\u0002\u0003BZ[\u0001\u0006IAa\u000e\t\u000f\u0005eW\u0006\"\u0011\u00036\"9\u0011Q\u0004\t\u0005\n\te\u0006b\u0002Bk!\u0011%!q\u001b\u0005\b\u0005S\u0004B\u0011\u0002Bv\u0011%\u0019)\u0001EA\u0001\n\u0013\u00199A\u0001\u0010V]&4\u0018M]5bi\u00164U-\u0019;ve\u0016\u001cV\r\\3di>\u0014Xj\u001c3fY*\u0011\u0001(O\u0001\bM\u0016\fG/\u001e:f\u0015\tQ4(\u0001\u0002nY*\u0011A(P\u0001\u0006gB\f'o\u001b\u0006\u0003}}\na!\u00199bG\",'\"\u0001!\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001\u0019\u0015\n\u0014\t\u0004\t\u0016;U\"A\u001d\n\u0005\u0019K$!B'pI\u0016d\u0007C\u0001%\u0001\u001b\u00059\u0004C\u0001%K\u0013\tYuGA\u0010V]&4\u0018M]5bi\u00164U-\u0019;ve\u0016\u001cV\r\\3di>\u0014\b+\u0019:b[N\u0004\"!\u0014)\u000e\u00039S!aT\u001d\u0002\tU$\u0018\u000e\\\u0005\u0003#:\u0013!\"\u0014'Xe&$\u0018M\u00197f\u0003\r)\u0018\u000eZ\u000b\u0002)B\u0011QK\u0018\b\u0003-r\u0003\"a\u0016.\u000e\u0003aS!!W!\u0002\rq\u0012xn\u001c;?\u0015\u0005Y\u0016!B:dC2\f\u0017BA/[\u0003\u0019\u0001&/\u001a3fM&\u0011q\f\u0019\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005uS\u0006fA\u0001cQB\u00111MZ\u0007\u0002I*\u0011QmO\u0001\u000bC:tw\u000e^1uS>t\u0017BA4e\u0005\u0015\u0019\u0016N\\2fC\u0005I\u0017!B\u001a/c9\n\u0014\u0001B;jI\u0002B3A\u00012i\u0003A\u0019X\r\\3di\u0016$g)Z1ukJ,7/F\u0001o!\ry\u0007O]\u0007\u00025&\u0011\u0011O\u0017\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003_NL!\u0001\u001e.\u0003\u0007%sG\u000fK\u0002\u0004E\"\f\u0011c]3mK\u000e$X\r\u001a$fCR,(/Z:!Q\r!!\r[\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007\u001dSH\u0010C\u0003S\u000b\u0001\u0007A\u000bK\u0002{E\"DQ\u0001\\\u0003A\u00029D3\u0001 2i)\u00059\u0015AD:fi\u001a+\u0017\r^;sKN\u001cu\u000e\u001c\u000b\u0005\u0003\u0007\t)!D\u0001\u0001\u0011\u0019\t9a\u0002a\u0001)\u0006)a/\u00197vK\"\u001aqA\u00195\u0002\u0019M,GoT;uaV$8i\u001c7\u0015\t\u0005\r\u0011q\u0002\u0005\u0007\u0003\u000fA\u0001\u0019\u0001+)\u0007!\u0011\u0007.\u0001\njg:+X.\u001a:jG\u0006#HO]5ckR,WCAA\f!\ry\u0017\u0011D\u0005\u0004\u00037Q&a\u0002\"p_2,\u0017M\\\u0001\niJ\fgn\u001d4pe6$B!!\t\u0002DA!\u00111EA\u001f\u001d\u0011\t)#a\u000e\u000f\t\u0005\u001d\u00121\u0007\b\u0005\u0003S\t\tD\u0004\u0003\u0002,\u0005=bbA,\u0002.%\t\u0001)\u0003\u0002?\u007f%\u0011A(P\u0005\u0004\u0003kY\u0014aA:rY&!\u0011\u0011HA\u001e\u0003\u001d\u0001\u0018mY6bO\u0016T1!!\u000e<\u0013\u0011\ty$!\u0011\u0003\u0013\u0011\u000bG/\u0019$sC6,'\u0002BA\u001d\u0003wAq!!\u0012\u000b\u0001\u0004\t9%A\u0004eCR\f7/\u001a;1\t\u0005%\u0013Q\u000b\t\u0007\u0003\u0017\ni%!\u0015\u000e\u0005\u0005m\u0012\u0002BA(\u0003w\u0011q\u0001R1uCN,G\u000f\u0005\u0003\u0002T\u0005UC\u0002\u0001\u0003\r\u0003/\n\u0019%!A\u0001\u0002\u000b\u0005\u0011\u0011\f\u0002\u0004?\u0012\u0012\u0014\u0003BA.\u0003C\u00022a\\A/\u0013\r\tyF\u0017\u0002\b\u001d>$\b.\u001b8h!\ry\u00171M\u0005\u0004\u0003KR&aA!os\"\u001a!B\u00195\u0002\u001fQ\u0014\u0018M\\:g_Jl7k\u00195f[\u0006$B!!\u001c\u0002zA!\u0011qNA;\u001b\t\t\tH\u0003\u0003\u0002t\u0005m\u0012!\u0002;za\u0016\u001c\u0018\u0002BA<\u0003c\u0012!b\u0015;sk\u000e$H+\u001f9f\u0011\u001d\tYh\u0003a\u0001\u0003[\naa]2iK6\f\u0007fA\u0006cQ\u0006!1m\u001c9z)\r9\u00151\u0011\u0005\b\u0003\u000bc\u0001\u0019AAD\u0003\u0015)\u0007\u0010\u001e:b!\u0011\tI)a$\u000e\u0005\u0005-%bAAGs\u0005)\u0001/\u0019:b[&!\u0011\u0011SAF\u0005!\u0001\u0016M]1n\u001b\u0006\u0004\bf\u0001\u0007cQ\u0006)qO]5uKV\u0011\u0011\u0011\u0014\t\u0004\u001b\u0006m\u0015bAAO\u001d\nAQ\nT,sSR,'\u000fK\u0002\u000eE\"\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002)\"\u001aaB\u00195)\u0007\u0001\u0011\u0007.\u0001\u0010V]&4\u0018M]5bi\u00164U-\u0019;ve\u0016\u001cV\r\\3di>\u0014Xj\u001c3fYB\u0011\u0001\nE\n\b!\u0005=\u0016QWA^!\ry\u0017\u0011W\u0005\u0004\u0003gS&AB!osJ+g\r\u0005\u0003N\u0003o;\u0015bAA]\u001d\nQQ\n\u0014*fC\u0012\f'\r\\3\u0011\t\u0005u\u0016qY\u0007\u0003\u0003\u007fSA!!1\u0002D\u0006\u0011\u0011n\u001c\u0006\u0003\u0003\u000b\fAA[1wC&!\u0011\u0011ZA`\u00051\u0019VM]5bY&T\u0018M\u00197f)\t\tY+\u0001\u0003sK\u0006$WCAAi!\u0011i\u00151[$\n\u0007\u0005UgJ\u0001\u0005N\u0019J+\u0017\rZ3sQ\r\u0011\"\r[\u0001\u0005Y>\fG\rF\u0002H\u0003;Da!a8\u0014\u0001\u0004!\u0016\u0001\u00029bi\"D3a\u00052i\u0005\u0011*f.\u001b<be&\fG/\u001a$fCR,(/Z*fY\u0016\u001cGo\u001c:N_\u0012,Gn\u0016:ji\u0016\u00148c\u0001\u000b\u0002\u001a\u0006A\u0011N\\:uC:\u001cW\r\u0006\u0003\u0002l\u0006=\bcAAw)5\t\u0001\u0003\u0003\u0004\u0002hZ\u0001\ra\u0012\u0002\u0005\t\u0006$\u0018mE\u0004\u0018\u0003_\u000b)0a?\u0011\u0007=\f90C\u0002\u0002zj\u0013q\u0001\u0015:pIV\u001cG\u000f\u0005\u0003\u0002~\n\u0015a\u0002BA\u0000\u0005\u0007q1a\u0016B\u0001\u0013\u0005Y\u0016bAA\u001d5&!\u0011\u0011\u001aB\u0004\u0015\r\tIDW\u000b\u0003\u0005\u0017\u0001R!!@\u0003\u000eILAAa\u0004\u0003\b\t\u00191+Z9\u0015\t\tM!q\u0003\t\u0004\u0005+9R\"\u0001\u000b\t\r1T\u0002\u0019\u0001B\u0006)\u0011\u0011\u0019Ba\u0007\t\u00111\\\u0002\u0013!a\u0001\u0005\u0017\tabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0003\")\"!1\u0002B\u0012W\t\u0011)\u0003\u0005\u0003\u0003(\t=RB\u0001B\u0015\u0015\u0011\u0011YC!\f\u0002\u0013Ut7\r[3dW\u0016$'BA3[\u0013\u0011\u0011\tD!\u000b\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0005o\u0001BA!\u000f\u0003@5\u0011!1\b\u0006\u0005\u0005{\t\u0019-\u0001\u0003mC:<\u0017bA0\u0003<\u0005a\u0001O]8ek\u000e$\u0018I]5usV\t!/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005\u0005$\u0011\n\u0005\t\u0005\u0017z\u0012\u0011!a\u0001e\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"A!\u0015\u0011\r\tM#\u0011LA1\u001b\t\u0011)FC\u0002\u0003Xi\u000b!bY8mY\u0016\u001cG/[8o\u0013\u0011\u0011YF!\u0016\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003/\u0011\t\u0007C\u0005\u0003L\u0005\n\t\u00111\u0001\u0002b\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\u00119Da\u001a\t\u0011\t-#%!AA\u0002I\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002eR\u0011!qG\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005]!1\u000f\u0005\n\u0005\u0017*\u0013\u0011!a\u0001\u0003C\nA\u0001R1uCB\u0019!QC\u0014\u0014\u000b\u001d\u0012Y(a/\u0011\u0011\tu$1\u0011B\u0006\u0005'i!Aa \u000b\u0007\t\u0005%,A\u0004sk:$\u0018.\\3\n\t\t\u0015%q\u0010\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\fDC\u0001B<\u0003\u0015\t\u0007\u000f\u001d7z)\u0011\u0011\u0019B!$\t\r1T\u0003\u0019\u0001B\u0006\u0003\u001d)h.\u00199qYf$BAa%\u0003\u001aB)qN!&\u0003\f%\u0019!q\u0013.\u0003\r=\u0003H/[8o\u0011%\u0011YjKA\u0001\u0002\u0004\u0011\u0019\"A\u0002yIA\n\u0001b]1wK&k\u0007\u000f\u001c\u000b\u0005\u0005C\u00139\u000bE\u0002p\u0005GK1A!*[\u0005\u0011)f.\u001b;\t\r\u0005}G\u00061\u0001U\u0005\u0011*f.\u001b<be&\fG/\u001a$fCR,(/Z*fY\u0016\u001cGo\u001c:N_\u0012,GNU3bI\u0016\u00148cA\u0017\u0002RR\u0011!q\u0016\t\u0004\u0003[l\u0013!C2mCN\u001ch*Y7f\u0003)\u0019G.Y:t\u001d\u0006lW\r\t\u000b\u0004\u000f\n]\u0006BBApc\u0001\u0007A\u000b\u0006\u0007\u0002\"\tm&q\u0019Be\u0005\u001b\u0014\t\u000eC\u0004\u0002FI\u0002\rA!01\t\t}&1\u0019\t\u0007\u0003\u0017\niE!1\u0011\t\u0005M#1\u0019\u0003\r\u0005\u000b\u0014Y,!A\u0001\u0002\u000b\u0005\u0011\u0011\f\u0002\u0004?\u0012\u001a\u0004\"\u000273\u0001\u0004q\u0007b\u0002Bfe\u0001\u0007\u0011QN\u0001\r_V$\b/\u001e;TG\",W.\u0019\u0005\u0007\u0005\u001f\u0014\u0004\u0019\u0001+\u0002\u0013=,H\u000f];u\u0007>d\u0007B\u0002Bje\u0001\u0007A+A\u0006gK\u0006$XO]3t\u0007>d\u0017a\u00049sKB|U\u000f\u001e9vi\u001aKW\r\u001c3\u0015\u0019\te'q\u001cBq\u0005G\u0014)Oa:\u0011\t\u0005=$1\\\u0005\u0005\u0005;\f\tHA\u0006TiJ,8\r\u001e$jK2$\u0007bBA>g\u0001\u0007\u0011Q\u000e\u0005\u0006YN\u0002\rA\u001c\u0005\u0007\u0005\u001f\u001c\u0004\u0019\u0001+\t\r\tM7\u00071\u0001U\u0011\u001d\t\u0019b\ra\u0001\u0003/\tabY8naJ,7o]*qCJ\u001cX\r\u0006\u0005\u0003n\nm(q`B\u0002!\u0019y'q\u001e8\u0003t&\u0019!\u0011\u001f.\u0003\rQ+\b\u000f\\33!\u0011y\u0007O!>\u0011\u0007=\u001490C\u0002\u0003zj\u0013a\u0001R8vE2,\u0007B\u0002B\u007fi\u0001\u0007a.A\u0004j]\u0012L7-Z:\t\u000f\r\u0005A\u00071\u0001\u0003t\u00061a/\u00197vKNDQ\u0001\u001c\u001bA\u00029\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"a!\u0003\u0011\t\te21B\u0005\u0005\u0007\u001b\u0011YD\u0001\u0004PE*,7\r\u001e\u0015\u0004!\tD\u0007fA\bcQ\u0002"
)
public class UnivariateFeatureSelectorModel extends Model implements UnivariateFeatureSelectorParams, MLWritable {
   private final String uid;
   private final int[] selectedFeatures;
   private Param featureType;
   private Param labelType;
   private Param selectionMode;
   private DoubleParam selectionThreshold;
   private Param outputCol;
   private Param labelCol;
   private Param featuresCol;

   public static UnivariateFeatureSelectorModel load(final String path) {
      return UnivariateFeatureSelectorModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return UnivariateFeatureSelectorModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public String getFeatureType() {
      return UnivariateFeatureSelectorParams.getFeatureType$(this);
   }

   public String getLabelType() {
      return UnivariateFeatureSelectorParams.getLabelType$(this);
   }

   public String getSelectionMode() {
      return UnivariateFeatureSelectorParams.getSelectionMode$(this);
   }

   public double getSelectionThreshold() {
      return UnivariateFeatureSelectorParams.getSelectionThreshold$(this);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getLabelCol() {
      return HasLabelCol.getLabelCol$(this);
   }

   public final String getFeaturesCol() {
      return HasFeaturesCol.getFeaturesCol$(this);
   }

   public final Param featureType() {
      return this.featureType;
   }

   public final Param labelType() {
      return this.labelType;
   }

   public final Param selectionMode() {
      return this.selectionMode;
   }

   public final DoubleParam selectionThreshold() {
      return this.selectionThreshold;
   }

   public final void org$apache$spark$ml$feature$UnivariateFeatureSelectorParams$_setter_$featureType_$eq(final Param x$1) {
      this.featureType = x$1;
   }

   public final void org$apache$spark$ml$feature$UnivariateFeatureSelectorParams$_setter_$labelType_$eq(final Param x$1) {
      this.labelType = x$1;
   }

   public final void org$apache$spark$ml$feature$UnivariateFeatureSelectorParams$_setter_$selectionMode_$eq(final Param x$1) {
      this.selectionMode = x$1;
   }

   public final void org$apache$spark$ml$feature$UnivariateFeatureSelectorParams$_setter_$selectionThreshold_$eq(final DoubleParam x$1) {
      this.selectionThreshold = x$1;
   }

   public final Param outputCol() {
      return this.outputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCol$_setter_$outputCol_$eq(final Param x$1) {
      this.outputCol = x$1;
   }

   public final Param labelCol() {
      return this.labelCol;
   }

   public final void org$apache$spark$ml$param$shared$HasLabelCol$_setter_$labelCol_$eq(final Param x$1) {
      this.labelCol = x$1;
   }

   public final Param featuresCol() {
      return this.featuresCol;
   }

   public final void org$apache$spark$ml$param$shared$HasFeaturesCol$_setter_$featuresCol_$eq(final Param x$1) {
      this.featuresCol = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public int[] selectedFeatures() {
      return this.selectedFeatures;
   }

   public UnivariateFeatureSelectorModel setFeaturesCol(final String value) {
      return (UnivariateFeatureSelectorModel)this.set(this.featuresCol(), value);
   }

   public UnivariateFeatureSelectorModel setOutputCol(final String value) {
      return (UnivariateFeatureSelectorModel)this.set(this.outputCol(), value);
   }

   public boolean isNumericAttribute() {
      return true;
   }

   public Dataset transform(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema(), true);
      return UnivariateFeatureSelectorModel$.MODULE$.org$apache$spark$ml$feature$UnivariateFeatureSelectorModel$$transform(dataset, (int[]).MODULE$.sorted$extension(scala.Predef..MODULE$.intArrayOps(this.selectedFeatures()), scala.math.Ordering.Int..MODULE$), outputSchema, (String)this.$(this.outputCol()), (String)this.$(this.featuresCol()));
   }

   public StructType transformSchema(final StructType schema) {
      SchemaUtils$.MODULE$.checkColumnType(schema, (String)this.$(this.featuresCol()), new VectorUDT(), SchemaUtils$.MODULE$.checkColumnType$default$4());
      StructField newField = UnivariateFeatureSelectorModel$.MODULE$.org$apache$spark$ml$feature$UnivariateFeatureSelectorModel$$prepOutputField(schema, this.selectedFeatures(), (String)this.$(this.outputCol()), (String)this.$(this.featuresCol()), this.isNumericAttribute());
      return SchemaUtils$.MODULE$.appendColumn(schema, newField);
   }

   public UnivariateFeatureSelectorModel copy(final ParamMap extra) {
      UnivariateFeatureSelectorModel copied = (UnivariateFeatureSelectorModel)(new UnivariateFeatureSelectorModel(this.uid(), this.selectedFeatures())).setParent(this.parent());
      return (UnivariateFeatureSelectorModel)this.copyValues(copied, extra);
   }

   public MLWriter write() {
      return new UnivariateFeatureSelectorModelWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "UnivariateFeatureSelectorModel: uid=" + var10000 + ", numSelectedFeatures=" + this.selectedFeatures().length;
   }

   public UnivariateFeatureSelectorModel(final String uid, final int[] selectedFeatures) {
      this.uid = uid;
      this.selectedFeatures = selectedFeatures;
      HasFeaturesCol.$init$(this);
      HasLabelCol.$init$(this);
      HasOutputCol.$init$(this);
      UnivariateFeatureSelectorParams.$init$(this);
      MLWritable.$init$(this);
      Statics.releaseFence();
   }

   public UnivariateFeatureSelectorModel() {
      this("", scala.Array..MODULE$.emptyIntArray());
   }

   public static class UnivariateFeatureSelectorModelWriter extends MLWriter {
      private volatile Data$ Data$module;
      private final UnivariateFeatureSelectorModel instance;

      private Data$ Data() {
         if (this.Data$module == null) {
            this.Data$lzycompute$1();
         }

         return this.Data$module;
      }

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         Data data = new Data(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.selectedFeatures()).toImmutableArraySeq());
         String dataPath = (new Path(path, "data")).toString();
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(UnivariateFeatureSelectorModelWriter.class.getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.feature.UnivariateFeatureSelectorModel.UnivariateFeatureSelectorModelWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.feature.UnivariateFeatureSelectorModel.UnivariateFeatureSelectorModelWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$1() {
            }
         }

         var10000.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())).write().parquet(dataPath);
      }

      private final void Data$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.Data$module == null) {
               this.Data$module = new Data$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      public UnivariateFeatureSelectorModelWriter(final UnivariateFeatureSelectorModel instance) {
         this.instance = instance;
      }

      private class Data implements Product, Serializable {
         private final Seq selectedFeatures;
         // $FF: synthetic field
         public final UnivariateFeatureSelectorModelWriter $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public Seq selectedFeatures() {
            return this.selectedFeatures;
         }

         public Data copy(final Seq selectedFeatures) {
            return this.org$apache$spark$ml$feature$UnivariateFeatureSelectorModel$UnivariateFeatureSelectorModelWriter$Data$$$outer().new Data(selectedFeatures);
         }

         public Seq copy$default$1() {
            return this.selectedFeatures();
         }

         public String productPrefix() {
            return "Data";
         }

         public int productArity() {
            return 1;
         }

         public Object productElement(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return this.selectedFeatures();
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
            return x$1 instanceof Data;
         }

         public String productElementName(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return "selectedFeatures";
               }
               default -> {
                  return (String)Statics.ioobe(x$1);
               }
            }
         }

         public int hashCode() {
            return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
         }

         public String toString() {
            return scala.runtime.ScalaRunTime..MODULE$._toString(this);
         }

         public boolean equals(final Object x$1) {
            boolean var6;
            if (this != x$1) {
               label52: {
                  if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$feature$UnivariateFeatureSelectorModel$UnivariateFeatureSelectorModelWriter$Data$$$outer() == this.org$apache$spark$ml$feature$UnivariateFeatureSelectorModel$UnivariateFeatureSelectorModelWriter$Data$$$outer()) {
                     label42: {
                        Data var4 = (Data)x$1;
                        Seq var10000 = this.selectedFeatures();
                        Seq var5 = var4.selectedFeatures();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label42;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label42;
                        }

                        if (var4.canEqual(this)) {
                           break label52;
                        }
                     }
                  }

                  var6 = false;
                  return var6;
               }
            }

            var6 = true;
            return var6;
         }

         // $FF: synthetic method
         public UnivariateFeatureSelectorModelWriter org$apache$spark$ml$feature$UnivariateFeatureSelectorModel$UnivariateFeatureSelectorModelWriter$Data$$$outer() {
            return this.$outer;
         }

         public Data(final Seq selectedFeatures) {
            this.selectedFeatures = selectedFeatures;
            if (UnivariateFeatureSelectorModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = UnivariateFeatureSelectorModelWriter.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class Data$ extends AbstractFunction1 implements Serializable {
         // $FF: synthetic field
         private final UnivariateFeatureSelectorModelWriter $outer;

         public final String toString() {
            return "Data";
         }

         public Data apply(final Seq selectedFeatures) {
            return this.$outer.new Data(selectedFeatures);
         }

         public Option unapply(final Data x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.selectedFeatures()));
         }

         public Data$() {
            if (UnivariateFeatureSelectorModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = UnivariateFeatureSelectorModelWriter.this;
               super();
            }
         }
      }
   }

   private static class UnivariateFeatureSelectorModelReader extends MLReader {
      private final String className = UnivariateFeatureSelectorModel.class.getName();

      private String className() {
         return this.className;
      }

      public UnivariateFeatureSelectorModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         Row data = (Row)this.sparkSession().read().parquet(dataPath).select("selectedFeatures", scala.collection.immutable.Nil..MODULE$).head();
         int[] selectedFeatures = (int[])((IterableOnceOps)data.getAs(0)).toArray(scala.reflect.ClassTag..MODULE$.Int());
         UnivariateFeatureSelectorModel model = new UnivariateFeatureSelectorModel(metadata.uid(), selectedFeatures);
         metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
         return model;
      }

      public UnivariateFeatureSelectorModelReader() {
      }
   }
}
