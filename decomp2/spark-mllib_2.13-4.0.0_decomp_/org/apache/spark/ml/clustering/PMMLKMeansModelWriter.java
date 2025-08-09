package org.apache.spark.ml.clustering;

import org.apache.spark.SparkContext;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.util.MLFormatRegister;
import org.apache.spark.sql.SparkSession;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I3A!\u0002\u0004\u0005#!)\u0011\u0005\u0001C\u0001E!)Q\u0005\u0001C!M!)!\u0007\u0001C!M!)1\u0007\u0001C!i\t)\u0002+T'M\u00176+\u0017M\\:N_\u0012,Gn\u0016:ji\u0016\u0014(BA\u0004\t\u0003)\u0019G.^:uKJLgn\u001a\u0006\u0003\u0013)\t!!\u001c7\u000b\u0005-a\u0011!B:qCJ\\'BA\u0007\u000f\u0003\u0019\t\u0007/Y2iK*\tq\"A\u0002pe\u001e\u001c\u0001a\u0005\u0003\u0001%aq\u0002CA\n\u0017\u001b\u0005!\"\"A\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005]!\"AB!osJ+g\r\u0005\u0002\u001a95\t!D\u0003\u0002\u001c\u0011\u0005!Q\u000f^5m\u0013\ti\"D\u0001\bN\u0019^\u0013\u0018\u000e^3s\r>\u0014X.\u0019;\u0011\u0005ey\u0012B\u0001\u0011\u001b\u0005AiEJR8s[\u0006$(+Z4jgR,'/\u0001\u0004=S:LGO\u0010\u000b\u0002GA\u0011A\u0005A\u0007\u0002\r\u00051am\u001c:nCR$\u0012a\n\t\u0003Q=r!!K\u0017\u0011\u0005)\"R\"A\u0016\u000b\u00051\u0002\u0012A\u0002\u001fs_>$h(\u0003\u0002/)\u00051\u0001K]3eK\u001aL!\u0001M\u0019\u0003\rM#(/\u001b8h\u0015\tqC#A\u0005ti\u0006<WMT1nK\u0006)qO]5uKR)Q\u0007\u000f\u001eC\u0019B\u00111CN\u0005\u0003oQ\u0011A!\u00168ji\")\u0011\b\u0002a\u0001O\u0005!\u0001/\u0019;i\u0011\u0015YD\u00011\u0001=\u00031\u0019\b/\u0019:l'\u0016\u001c8/[8o!\ti\u0004)D\u0001?\u0015\ty$\"A\u0002tc2L!!\u0011 \u0003\u0019M\u0003\u0018M]6TKN\u001c\u0018n\u001c8\t\u000b\r#\u0001\u0019\u0001#\u0002\u0013=\u0004H/[8o\u001b\u0006\u0004\b\u0003B#KO\u001dj\u0011A\u0012\u0006\u0003\u000f\"\u000bq!\\;uC\ndWM\u0003\u0002J)\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005-3%aA'ba\")Q\n\u0002a\u0001\u001d\u0006)1\u000f^1hKB\u0011q\nU\u0007\u0002\u0011%\u0011\u0011\u000b\u0003\u0002\u000e!&\u0004X\r\\5oKN#\u0018mZ3"
)
public class PMMLKMeansModelWriter implements MLFormatRegister {
   public String shortName() {
      return MLFormatRegister.shortName$(this);
   }

   public String format() {
      return "pmml";
   }

   public String stageName() {
      return "org.apache.spark.ml.clustering.KMeansModel";
   }

   public void write(final String path, final SparkSession sparkSession, final Map optionMap, final PipelineStage stage) {
      KMeansModel instance = (KMeansModel)stage;
      SparkContext sc = sparkSession.sparkContext();
      instance.parentModel().toPMML(sc, path);
   }

   public PMMLKMeansModelWriter() {
      MLFormatRegister.$init$(this);
   }
}
