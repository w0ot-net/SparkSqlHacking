package org.apache.spark.sql.hive.execution;

import java.util.UUID;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.internal.io.FileCommitProtocol;
import org.apache.spark.internal.io.FileCommitProtocol.;
import org.apache.spark.sql.classic.SparkSession;
import org.apache.spark.sql.execution.SparkPlan;
import org.apache.spark.sql.execution.command.DataWritingCommand;
import org.apache.spark.sql.execution.datasources.FileFormat;
import org.apache.spark.sql.execution.datasources.FileFormatWriter;
import scala.Option;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mc\u0001C\u0004\t!\u0003\r\tA\u0003\u000b\t\u000b\u0019\u0002A\u0011\u0001\u0015\t\u000b=\u0002A\u0011\u0003\u0019\t\u0013\u0005E\u0002!%A\u0005\u0012\u0005M\u0002\"CA%\u0001E\u0005I\u0011CA&\u0011%\ty\u0005AI\u0001\n#\t\t\u0006C\u0005\u0002V\u0001\t\n\u0011\"\u0005\u0002X\tq1+\u0019<f\u0003ND\u0015N^3GS2,'BA\u0005\u000b\u0003%)\u00070Z2vi&|gN\u0003\u0002\f\u0019\u0005!\u0001.\u001b<f\u0015\tia\"A\u0002tc2T!a\u0004\t\u0002\u000bM\u0004\u0018M]6\u000b\u0005E\u0011\u0012AB1qC\u000eDWMC\u0001\u0014\u0003\ry'oZ\n\u0004\u0001Uy\u0002C\u0001\f\u001e\u001b\u00059\"B\u0001\r\u001a\u0003\u001dawnZ5dC2T!AG\u000e\u0002\u000bAd\u0017M\\:\u000b\u0005qa\u0011\u0001C2bi\u0006d\u0017p\u001d;\n\u0005y9\"a\u0003'pO&\u001c\u0017\r\u001c)mC:\u0004\"\u0001\t\u0013\u000e\u0003\u0005R!AI\u0012\u0002\u000f\r|W.\\1oI*\u0011\u0011\u0002D\u0005\u0003K\u0005\u0012!\u0003R1uC^\u0013\u0018\u000e^5oO\u000e{W.\\1oI\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001*!\tQS&D\u0001,\u0015\u0005a\u0013!B:dC2\f\u0017B\u0001\u0018,\u0005\u0011)f.\u001b;\u0002\u001dM\fg/Z!t\u0011&4XMR5mKRa\u0011gP$N/~\u000b70!\u0007\u0002,A\u0019!'\u000f\u001f\u000f\u0005M:\u0004C\u0001\u001b,\u001b\u0005)$B\u0001\u001c(\u0003\u0019a$o\\8u}%\u0011\u0001hK\u0001\u0007!J,G-\u001a4\n\u0005iZ$aA*fi*\u0011\u0001h\u000b\t\u0003euJ!AP\u001e\u0003\rM#(/\u001b8h\u0011\u0015\u0001%\u00011\u0001B\u00031\u0019\b/\u0019:l'\u0016\u001c8/[8o!\t\u0011U)D\u0001D\u0015\t!E\"A\u0004dY\u0006\u001c8/[2\n\u0005\u0019\u001b%\u0001D*qCJ\\7+Z:tS>t\u0007\"\u0002%\u0003\u0001\u0004I\u0015\u0001\u00029mC:\u0004\"AS&\u000e\u0003\rJ!\u0001T\u0012\u0003\u0013M\u0003\u0018M]6QY\u0006t\u0007\"\u0002(\u0003\u0001\u0004y\u0015A\u00035bI>|\u0007oQ8oMB\u0011\u0001+V\u0007\u0002#*\u0011!kU\u0001\u0005G>tgM\u0003\u0002U!\u00051\u0001.\u00193p_BL!AV)\u0003\u001b\r{gNZ5hkJ\fG/[8o\u0011\u0015A&\u00011\u0001Z\u0003)1\u0017\u000e\\3G_Jl\u0017\r\u001e\t\u00035vk\u0011a\u0017\u0006\u00039\u000e\n1\u0002Z1uCN|WO]2fg&\u0011al\u0017\u0002\u000b\r&dWMR8s[\u0006$\b\"\u00021\u0003\u0001\u0004a\u0014AD8viB,H\u000fT8dCRLwN\u001c\u0005\bE\n\u0001\n\u00111\u0001d\u0003a\u0019Wo\u001d;p[B\u000b'\u000f^5uS>tGj\\2bi&|gn\u001d\t\u0005e\u00114G(\u0003\u0002fw\t\u0019Q*\u00199\u0011\u0005\u001dDhB\u00015v\u001d\tI7O\u0004\u0002ke:\u00111.\u001d\b\u0003YBt!!\\8\u000f\u0005Qr\u0017\"A\n\n\u0005E\u0011\u0012BA\b\u0011\u0013\tia\"\u0003\u0002\u001d\u0019%\u0011AoG\u0001\bG\u0006$\u0018\r\\8h\u0013\t1x/\u0001\u0007DCR\fGn\\4UsB,7O\u0003\u0002u7%\u0011\u0011P\u001f\u0002\u0013)\u0006\u0014G.\u001a)beRLG/[8o'B,7M\u0003\u0002wo\"9AP\u0001I\u0001\u0002\u0004i\u0018a\u00059beRLG/[8o\u0003R$(/\u001b2vi\u0016\u001c\b#\u0002@\u0002\b\u00055abA@\u0002\u00049\u0019A'!\u0001\n\u00031J1!!\u0002,\u0003\u001d\u0001\u0018mY6bO\u0016LA!!\u0003\u0002\f\t\u00191+Z9\u000b\u0007\u0005\u00151\u0006\u0005\u0003\u0002\u0010\u0005UQBAA\t\u0015\r\t\u0019bG\u0001\fKb\u0004(/Z:tS>t7/\u0003\u0003\u0002\u0018\u0005E!!C!uiJL'-\u001e;f\u0011%\tYB\u0001I\u0001\u0002\u0004\ti\"\u0001\u0006ck\u000e\\W\r^*qK\u000e\u0004RAKA\u0010\u0003GI1!!\t,\u0005\u0019y\u0005\u000f^5p]B!\u0011QEA\u0014\u001b\u00059\u0018bAA\u0015o\nQ!)^2lKR\u001c\u0006/Z2\t\u0013\u00055\"\u0001%AA\u0002\u0005=\u0012aB8qi&|gn\u001d\t\u0005e\u0011dD(\u0001\rtCZ,\u0017i\u001d%jm\u00164\u0015\u000e\\3%I\u00164\u0017-\u001e7uIY*\"!!\u000e+\u0007\r\f9d\u000b\u0002\u0002:A!\u00111HA#\u001b\t\tiD\u0003\u0003\u0002@\u0005\u0005\u0013!C;oG\",7m[3e\u0015\r\t\u0019eK\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA$\u0003{\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003a\u0019\u0018M^3Bg\"Kg/\u001a$jY\u0016$C-\u001a4bk2$HeN\u000b\u0003\u0003\u001bR3!`A\u001c\u0003a\u0019\u0018M^3Bg\"Kg/\u001a$jY\u0016$C-\u001a4bk2$H\u0005O\u000b\u0003\u0003'RC!!\b\u00028\u0005A2/\u0019<f\u0003ND\u0015N^3GS2,G\u0005Z3gCVdG\u000fJ\u001d\u0016\u0005\u0005e#\u0006BA\u0018\u0003o\u0001"
)
public interface SaveAsHiveFile extends DataWritingCommand {
   // $FF: synthetic method
   static Set saveAsHiveFile$(final SaveAsHiveFile $this, final SparkSession sparkSession, final SparkPlan plan, final Configuration hadoopConf, final FileFormat fileFormat, final String outputLocation, final Map customPartitionLocations, final Seq partitionAttributes, final Option bucketSpec, final Map options) {
      return $this.saveAsHiveFile(sparkSession, plan, hadoopConf, fileFormat, outputLocation, customPartitionLocations, partitionAttributes, bucketSpec, options);
   }

   default Set saveAsHiveFile(final SparkSession sparkSession, final SparkPlan plan, final Configuration hadoopConf, final FileFormat fileFormat, final String outputLocation, final Map customPartitionLocations, final Seq partitionAttributes, final Option bucketSpec, final Map options) {
      FileCommitProtocol committer = .MODULE$.instantiate(sparkSession.sessionState().conf().fileCommitProtocolClass(), UUID.randomUUID().toString(), outputLocation, .MODULE$.instantiate$default$4());
      return org.apache.spark.sql.execution.datasources.FileFormatWriter..MODULE$.write(sparkSession, plan, fileFormat, committer, new FileFormatWriter.OutputSpec(outputLocation, customPartitionLocations, this.outputColumns()), hadoopConf, partitionAttributes, bucketSpec, new scala.collection.immutable..colon.colon(this.basicWriteJobStatsTracker(hadoopConf), scala.collection.immutable.Nil..MODULE$), options, org.apache.spark.sql.execution.datasources.FileFormatWriter..MODULE$.write$default$11());
   }

   // $FF: synthetic method
   static Map saveAsHiveFile$default$6$(final SaveAsHiveFile $this) {
      return $this.saveAsHiveFile$default$6();
   }

   default Map saveAsHiveFile$default$6() {
      return scala.Predef..MODULE$.Map().empty();
   }

   // $FF: synthetic method
   static Seq saveAsHiveFile$default$7$(final SaveAsHiveFile $this) {
      return $this.saveAsHiveFile$default$7();
   }

   default Seq saveAsHiveFile$default$7() {
      return scala.collection.immutable.Nil..MODULE$;
   }

   // $FF: synthetic method
   static Option saveAsHiveFile$default$8$(final SaveAsHiveFile $this) {
      return $this.saveAsHiveFile$default$8();
   }

   default Option saveAsHiveFile$default$8() {
      return scala.None..MODULE$;
   }

   // $FF: synthetic method
   static Map saveAsHiveFile$default$9$(final SaveAsHiveFile $this) {
      return $this.saveAsHiveFile$default$9();
   }

   default Map saveAsHiveFile$default$9() {
      return scala.Predef..MODULE$.Map().empty();
   }

   static void $init$(final SaveAsHiveFile $this) {
   }
}
