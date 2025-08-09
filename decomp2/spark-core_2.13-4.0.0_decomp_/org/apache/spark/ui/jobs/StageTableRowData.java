package org.apache.spark.ui.jobs;

import java.util.Date;
import org.apache.spark.status.api.v1.StageData;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rc!\u0002\u0014(\u0001%\n\u0004\u0002\u0003\u001d\u0001\u0005\u000b\u0007I\u0011\u0001\u001e\t\u0011\u0015\u0003!\u0011!Q\u0001\nmB\u0001B\u0012\u0001\u0003\u0006\u0004%\ta\u0012\u0005\t\u0017\u0002\u0011\t\u0011)A\u0005\u0011\"AA\n\u0001BC\u0002\u0013\u0005Q\n\u0003\u0005R\u0001\t\u0005\t\u0015!\u0003O\u0011!\u0011\u0006A!b\u0001\n\u0003i\u0005\u0002C*\u0001\u0005\u0003\u0005\u000b\u0011\u0002(\t\u0011Q\u0003!Q1A\u0005\u0002UC\u0001\"\u0019\u0001\u0003\u0002\u0003\u0006IA\u0016\u0005\tE\u0002\u0011)\u0019!C\u0001G\"AQ\r\u0001B\u0001B\u0003%A\r\u0003\u0005g\u0001\t\u0015\r\u0011\"\u0001h\u0011!\u0001\bA!A!\u0002\u0013A\u0007\u0002C9\u0001\u0005\u000b\u0007I\u0011A+\t\u0011I\u0004!\u0011!Q\u0001\nYC\u0001b\u001d\u0001\u0003\u0006\u0004%\t\u0001\u001e\u0005\tq\u0002\u0011\t\u0011)A\u0005k\"A\u0011\u0010\u0001BC\u0002\u0013\u0005Q\u000b\u0003\u0005{\u0001\t\u0005\t\u0015!\u0003W\u0011!Y\bA!b\u0001\n\u0003!\b\u0002\u0003?\u0001\u0005\u0003\u0005\u000b\u0011B;\t\u0011u\u0004!Q1A\u0005\u0002UC\u0001B \u0001\u0003\u0002\u0003\u0006IA\u0016\u0005\t\u007f\u0002\u0011)\u0019!C\u0001i\"I\u0011\u0011\u0001\u0001\u0003\u0002\u0003\u0006I!\u001e\u0005\n\u0003\u0007\u0001!Q1A\u0005\u0002UC\u0011\"!\u0002\u0001\u0005\u0003\u0005\u000b\u0011\u0002,\t\u0013\u0005\u001d\u0001A!b\u0001\n\u0003!\b\"CA\u0005\u0001\t\u0005\t\u0015!\u0003v\u0011%\tY\u0001\u0001BC\u0002\u0013\u0005Q\u000bC\u0005\u0002\u000e\u0001\u0011\t\u0011)A\u0005-\"I\u0011q\u0002\u0001\u0003\u0006\u0004%\t\u0001\u001e\u0005\n\u0003#\u0001!\u0011!Q\u0001\nUD\u0011\"a\u0005\u0001\u0005\u000b\u0007I\u0011A+\t\u0013\u0005U\u0001A!A!\u0002\u00131\u0006bBA\f\u0001\u0011\u0005\u0011\u0011\u0004\u0002\u0012'R\fw-\u001a+bE2,'k\\<ECR\f'B\u0001\u0015*\u0003\u0011QwNY:\u000b\u0005)Z\u0013AA;j\u0015\taS&A\u0003ta\u0006\u00148N\u0003\u0002/_\u00051\u0011\r]1dQ\u0016T\u0011\u0001M\u0001\u0004_J<7C\u0001\u00013!\t\u0019d'D\u00015\u0015\u0005)\u0014!B:dC2\f\u0017BA\u001c5\u0005\u0019\te.\u001f*fM\u0006)1\u000f^1hK\u000e\u0001Q#A\u001e\u0011\u0005q\u001aU\"A\u001f\u000b\u0005yz\u0014A\u0001<2\u0015\t\u0001\u0015)A\u0002ba&T!AQ\u0016\u0002\rM$\u0018\r^;t\u0013\t!UHA\u0005Ti\u0006<W\rR1uC\u000611\u000f^1hK\u0002\naa\u001c9uS>tW#\u0001%\u0011\u0007MJ5(\u0003\u0002Ki\t1q\n\u001d;j_:\fqa\u001c9uS>t\u0007%A\u0004ti\u0006<W-\u00133\u0016\u00039\u0003\"aM(\n\u0005A#$aA%oi\u0006A1\u000f^1hK&#\u0007%A\u0005biR,W\u000e\u001d;JI\u0006Q\u0011\r\u001e;f[B$\u0018\n\u001a\u0011\u0002\u001dM\u001c\u0007.\u001a3vY&tw\rU8pYV\ta\u000b\u0005\u0002X=:\u0011\u0001\f\u0018\t\u00033Rj\u0011A\u0017\u0006\u00037f\na\u0001\u0010:p_Rt\u0014BA/5\u0003\u0019\u0001&/\u001a3fM&\u0011q\f\u0019\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005u#\u0014aD:dQ\u0016$W\u000f\\5oOB{w\u000e\u001c\u0011\u0002#\u0011,7o\u0019:jaRLwN\\(qi&|g.F\u0001e!\r\u0019\u0014JV\u0001\u0013I\u0016\u001c8M]5qi&|gn\u00149uS>t\u0007%\u0001\btk\nl\u0017n]:j_:$\u0016.\\3\u0016\u0003!\u0004\"!\u001b8\u000e\u0003)T!a\u001b7\u0002\tU$\u0018\u000e\u001c\u0006\u0002[\u0006!!.\u0019<b\u0013\ty'N\u0001\u0003ECR,\u0017aD:vE6L7o]5p]RKW.\u001a\u0011\u0002/\u0019|'/\\1ui\u0016$7+\u001e2nSN\u001c\u0018n\u001c8US6,\u0017\u0001\u00074pe6\fG\u000f^3e'V\u0014W.[:tS>tG+[7fA\u0005AA-\u001e:bi&|g.F\u0001v!\t\u0019d/\u0003\u0002xi\t!Aj\u001c8h\u0003%!WO]1uS>t\u0007%A\tg_Jl\u0017\r\u001e;fI\u0012+(/\u0019;j_:\f!CZ8s[\u0006$H/\u001a3EkJ\fG/[8oA\u0005I\u0011N\u001c9viJ+\u0017\rZ\u0001\u000bS:\u0004X\u000f\u001e*fC\u0012\u0004\u0013!E5oaV$(+Z1e/&$\b.\u00168ji\u0006\u0011\u0012N\u001c9viJ+\u0017\rZ,ji\",f.\u001b;!\u0003-yW\u000f\u001e9vi^\u0013\u0018\u000e^3\u0002\u0019=,H\u000f];u/JLG/\u001a\u0011\u0002'=,H\u000f];u/JLG/Z,ji\",f.\u001b;\u0002)=,H\u000f];u/JLG/Z,ji\",f.\u001b;!\u0003-\u0019\b.\u001e4gY\u0016\u0014V-\u00193\u0002\u0019MDWO\u001a4mKJ+\u0017\r\u001a\u0011\u0002'MDWO\u001a4mKJ+\u0017\rZ,ji\",f.\u001b;\u0002)MDWO\u001a4mKJ+\u0017\rZ,ji\",f.\u001b;!\u00031\u0019\b.\u001e4gY\u0016<&/\u001b;f\u00035\u0019\b.\u001e4gY\u0016<&/\u001b;fA\u0005!2\u000f[;gM2,wK]5uK^KG\u000f[+oSR\fQc\u001d5vM\u001adWm\u0016:ji\u0016<\u0016\u000e\u001e5V]&$\b%\u0001\u0004=S:LGO\u0010\u000b'\u00037\ty\"!\t\u0002$\u0005\u0015\u0012qEA\u0015\u0003W\ti#a\f\u00022\u0005M\u0012QGA\u001c\u0003s\tY$!\u0010\u0002@\u0005\u0005\u0003cAA\u000f\u00015\tq\u0005C\u00039K\u0001\u00071\bC\u0003GK\u0001\u0007\u0001\nC\u0003MK\u0001\u0007a\nC\u0003SK\u0001\u0007a\nC\u0003UK\u0001\u0007a\u000bC\u0003cK\u0001\u0007A\rC\u0003gK\u0001\u0007\u0001\u000eC\u0003rK\u0001\u0007a\u000bC\u0003tK\u0001\u0007Q\u000fC\u0003zK\u0001\u0007a\u000bC\u0003|K\u0001\u0007Q\u000fC\u0003~K\u0001\u0007a\u000bC\u0003\u0000K\u0001\u0007Q\u000f\u0003\u0004\u0002\u0004\u0015\u0002\rA\u0016\u0005\u0007\u0003\u000f)\u0003\u0019A;\t\r\u0005-Q\u00051\u0001W\u0011\u0019\ty!\na\u0001k\"1\u00111C\u0013A\u0002Y\u0003"
)
public class StageTableRowData {
   private final StageData stage;
   private final Option option;
   private final int stageId;
   private final int attemptId;
   private final String schedulingPool;
   private final Option descriptionOption;
   private final Date submissionTime;
   private final String formattedSubmissionTime;
   private final long duration;
   private final String formattedDuration;
   private final long inputRead;
   private final String inputReadWithUnit;
   private final long outputWrite;
   private final String outputWriteWithUnit;
   private final long shuffleRead;
   private final String shuffleReadWithUnit;
   private final long shuffleWrite;
   private final String shuffleWriteWithUnit;

   public StageData stage() {
      return this.stage;
   }

   public Option option() {
      return this.option;
   }

   public int stageId() {
      return this.stageId;
   }

   public int attemptId() {
      return this.attemptId;
   }

   public String schedulingPool() {
      return this.schedulingPool;
   }

   public Option descriptionOption() {
      return this.descriptionOption;
   }

   public Date submissionTime() {
      return this.submissionTime;
   }

   public String formattedSubmissionTime() {
      return this.formattedSubmissionTime;
   }

   public long duration() {
      return this.duration;
   }

   public String formattedDuration() {
      return this.formattedDuration;
   }

   public long inputRead() {
      return this.inputRead;
   }

   public String inputReadWithUnit() {
      return this.inputReadWithUnit;
   }

   public long outputWrite() {
      return this.outputWrite;
   }

   public String outputWriteWithUnit() {
      return this.outputWriteWithUnit;
   }

   public long shuffleRead() {
      return this.shuffleRead;
   }

   public String shuffleReadWithUnit() {
      return this.shuffleReadWithUnit;
   }

   public long shuffleWrite() {
      return this.shuffleWrite;
   }

   public String shuffleWriteWithUnit() {
      return this.shuffleWriteWithUnit;
   }

   public StageTableRowData(final StageData stage, final Option option, final int stageId, final int attemptId, final String schedulingPool, final Option descriptionOption, final Date submissionTime, final String formattedSubmissionTime, final long duration, final String formattedDuration, final long inputRead, final String inputReadWithUnit, final long outputWrite, final String outputWriteWithUnit, final long shuffleRead, final String shuffleReadWithUnit, final long shuffleWrite, final String shuffleWriteWithUnit) {
      this.stage = stage;
      this.option = option;
      this.stageId = stageId;
      this.attemptId = attemptId;
      this.schedulingPool = schedulingPool;
      this.descriptionOption = descriptionOption;
      this.submissionTime = submissionTime;
      this.formattedSubmissionTime = formattedSubmissionTime;
      this.duration = duration;
      this.formattedDuration = formattedDuration;
      this.inputRead = inputRead;
      this.inputReadWithUnit = inputReadWithUnit;
      this.outputWrite = outputWrite;
      this.outputWriteWithUnit = outputWriteWithUnit;
      this.shuffleRead = shuffleRead;
      this.shuffleReadWithUnit = shuffleReadWithUnit;
      this.shuffleWrite = shuffleWrite;
      this.shuffleWriteWithUnit = shuffleWriteWithUnit;
   }
}
