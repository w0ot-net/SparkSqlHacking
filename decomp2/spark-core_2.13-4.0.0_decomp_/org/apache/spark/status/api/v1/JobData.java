package org.apache.spark.status.api.v1;

import org.apache.spark.JobExecutionStatus;
import scala.Option;
import scala.collection.Seq;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dd\u0001\u0002\u0017.\u0001iB\u0001\"\u0011\u0001\u0003\u0006\u0004%\tA\u0011\u0005\t\r\u0002\u0011\t\u0011)A\u0005\u0007\"Aq\t\u0001BC\u0002\u0013\u0005\u0001\n\u0003\u0005U\u0001\t\u0005\t\u0015!\u0003J\u0011!)\u0006A!b\u0001\n\u00031\u0006\u0002\u0003.\u0001\u0005\u0003\u0005\u000b\u0011B,\t\u0011m\u0003!Q1A\u0005\u0002qC\u0001B\u001a\u0001\u0003\u0002\u0003\u0006I!\u0018\u0005\tO\u0002\u0011)\u0019!C\u00019\"A\u0001\u000e\u0001B\u0001B\u0003%Q\f\u0003\u0005j\u0001\t\u0015\r\u0011\"\u0001k\u0011!\t\bA!A!\u0002\u0013Y\u0007\u0002\u0003:\u0001\u0005\u000b\u0007I\u0011\u0001,\t\u0011M\u0004!\u0011!Q\u0001\n]C\u0001\u0002\u001e\u0001\u0003\u0006\u0004%\t!\u001e\u0005\to\u0002\u0011\t\u0011)A\u0005m\"A!\u0007\u0001BC\u0002\u0013\u0005\u0001\u0010\u0003\u0005~\u0001\t\u0005\t\u0015!\u0003z\u0011!q\bA!b\u0001\n\u0003\u0011\u0005\u0002C@\u0001\u0005\u0003\u0005\u000b\u0011B\"\t\u0013\u0005\u0005\u0001A!b\u0001\n\u0003\u0011\u0005\"CA\u0002\u0001\t\u0005\t\u0015!\u0003D\u0011%\t)\u0001\u0001BC\u0002\u0013\u0005!\tC\u0005\u0002\b\u0001\u0011\t\u0011)A\u0005\u0007\"I\u0011\u0011\u0002\u0001\u0003\u0006\u0004%\tA\u0011\u0005\n\u0003\u0017\u0001!\u0011!Q\u0001\n\rC\u0011\"!\u0004\u0001\u0005\u000b\u0007I\u0011\u0001\"\t\u0013\u0005=\u0001A!A!\u0002\u0013\u0019\u0005\"CA\t\u0001\t\u0015\r\u0011\"\u0001C\u0011%\t\u0019\u0002\u0001B\u0001B\u0003%1\tC\u0005\u0002\u0016\u0001\u0011)\u0019!C\u0001\u0005\"I\u0011q\u0003\u0001\u0003\u0002\u0003\u0006Ia\u0011\u0005\n\u00033\u0001!Q1A\u0005\u0002\tC\u0011\"a\u0007\u0001\u0005\u0003\u0005\u000b\u0011B\"\t\u0013\u0005u\u0001A!b\u0001\n\u0003\u0011\u0005\"CA\u0010\u0001\t\u0005\t\u0015!\u0003D\u0011%\t\t\u0003\u0001BC\u0002\u0013\u0005!\tC\u0005\u0002$\u0001\u0011\t\u0011)A\u0005\u0007\"I\u0011Q\u0005\u0001\u0003\u0006\u0004%\tA\u0011\u0005\n\u0003O\u0001!\u0011!Q\u0001\n\rC!\"!\u000b\u0001\u0005\u000b\u0007I\u0011AA\u0016\u0011)\t\u0019\u0004\u0001B\u0001B\u0003%\u0011Q\u0006\u0005\t\u0003k\u0001A\u0011A\u001a\u00028\t9!j\u001c2ECR\f'B\u0001\u00180\u0003\t1\u0018G\u0003\u00021c\u0005\u0019\u0011\r]5\u000b\u0005I\u001a\u0014AB:uCR,8O\u0003\u00025k\u0005)1\u000f]1sW*\u0011agN\u0001\u0007CB\f7\r[3\u000b\u0003a\n1a\u001c:h\u0007\u0001\u0019\"\u0001A\u001e\u0011\u0005qzT\"A\u001f\u000b\u0003y\nQa]2bY\u0006L!\u0001Q\u001f\u0003\r\u0005s\u0017PU3g\u0003\u0015QwNY%e+\u0005\u0019\u0005C\u0001\u001fE\u0013\t)UHA\u0002J]R\faA[8c\u0013\u0012\u0004\u0013\u0001\u00028b[\u0016,\u0012!\u0013\t\u0003\u0015Fs!aS(\u0011\u00051kT\"A'\u000b\u00059K\u0014A\u0002\u001fs_>$h(\u0003\u0002Q{\u00051\u0001K]3eK\u001aL!AU*\u0003\rM#(/\u001b8h\u0015\t\u0001V(A\u0003oC6,\u0007%A\u0006eKN\u001c'/\u001b9uS>tW#A,\u0011\u0007qB\u0016*\u0003\u0002Z{\t1q\n\u001d;j_:\fA\u0002Z3tGJL\u0007\u000f^5p]\u0002\nab];c[&\u001c8/[8o)&lW-F\u0001^!\ra\u0004L\u0018\t\u0003?\u0012l\u0011\u0001\u0019\u0006\u0003C\n\fA!\u001e;jY*\t1-\u0001\u0003kCZ\f\u0017BA3a\u0005\u0011!\u0015\r^3\u0002\u001fM,(-\\5tg&|g\u000eV5nK\u0002\nabY8na2,G/[8o)&lW-A\bd_6\u0004H.\u001a;j_:$\u0016.\\3!\u0003!\u0019H/Y4f\u0013\u0012\u001cX#A6\u0011\u00071|7)D\u0001n\u0015\tqW(\u0001\u0006d_2dWm\u0019;j_:L!\u0001]7\u0003\u0007M+\u0017/A\u0005ti\u0006<W-\u00133tA\u0005A!n\u001c2He>,\b/A\u0005k_\n<%o\\;qA\u00059!n\u001c2UC\u001e\u001cX#\u0001<\u0011\u00071|\u0017*\u0001\u0005k_\n$\u0016mZ:!+\u0005I\bC\u0001>|\u001b\u0005\u0019\u0014B\u0001?4\u0005IQuNY#yK\u000e,H/[8o'R\fG/^:\u0002\u000fM$\u0018\r^;tA\u0005Aa.^7UCN\\7/A\u0005ok6$\u0016m]6tA\u0005qa.^7BGRLg/\u001a+bg.\u001c\u0018a\u00048v[\u0006\u001bG/\u001b<f)\u0006\u001c8n\u001d\u0011\u0002#9,XnQ8na2,G/\u001a3UCN\\7/\u0001\nok6\u001cu.\u001c9mKR,G\rV1tWN\u0004\u0013a\u00048v[N[\u0017\u000e\u001d9fIR\u000b7o[:\u0002!9,XnU6jaB,G\rV1tWN\u0004\u0013A\u00048v[\u001a\u000b\u0017\u000e\\3e)\u0006\u001c8n]\u0001\u0010]Vlg)Y5mK\u0012$\u0016m]6tA\u0005qa.^7LS2dW\r\u001a+bg.\u001c\u0018a\u00048v[.KG\u000e\\3e)\u0006\u001c8n\u001d\u0011\u0002'9,XnQ8na2,G/\u001a3J]\u0012L7-Z:\u0002)9,XnQ8na2,G/\u001a3J]\u0012L7-Z:!\u0003=qW/\\!di&4Xm\u0015;bO\u0016\u001c\u0018\u0001\u00058v[\u0006\u001bG/\u001b<f'R\fw-Z:!\u0003IqW/\\\"p[BdW\r^3e'R\fw-Z:\u0002'9,XnQ8na2,G/\u001a3Ti\u0006<Wm\u001d\u0011\u0002!9,XnU6jaB,Gm\u0015;bO\u0016\u001c\u0018!\u00058v[N[\u0017\u000e\u001d9fIN#\u0018mZ3tA\u0005ya.^7GC&dW\rZ*uC\u001e,7/\u0001\tok64\u0015-\u001b7fIN#\u0018mZ3tA\u0005\u00112.\u001b7mK\u0012$\u0016m]6t'VlW.\u0019:z+\t\ti\u0003E\u0003K\u0003_I5)C\u0002\u00022M\u00131!T1q\u0003MY\u0017\u000e\u001c7fIR\u000b7o[:Tk6l\u0017M]=!\u0003\u0019a\u0014N\\5u}Qa\u0013\u0011HA\u001f\u0003\u007f\t\t%a\u0011\u0002F\u0005\u001d\u0013\u0011JA&\u0003\u001b\ny%!\u0015\u0002T\u0005U\u0013qKA-\u00037\ni&a\u0018\u0002b\u0005\r\u0014Q\r\t\u0004\u0003w\u0001Q\"A\u0017\t\u000b\u0005[\u0003\u0019A\"\t\u000b\u001d[\u0003\u0019A%\t\u000bU[\u0003\u0019A,\t\u000bm[\u0003\u0019A/\t\u000b\u001d\\\u0003\u0019A/\t\u000b%\\\u0003\u0019A6\t\u000bI\\\u0003\u0019A,\t\u000bQ\\\u0003\u0019\u0001<\t\u000bIZ\u0003\u0019A=\t\u000by\\\u0003\u0019A\"\t\r\u0005\u00051\u00061\u0001D\u0011\u0019\t)a\u000ba\u0001\u0007\"1\u0011\u0011B\u0016A\u0002\rCa!!\u0004,\u0001\u0004\u0019\u0005BBA\tW\u0001\u00071\t\u0003\u0004\u0002\u0016-\u0002\ra\u0011\u0005\u0007\u00033Y\u0003\u0019A\"\t\r\u0005u1\u00061\u0001D\u0011\u0019\t\tc\u000ba\u0001\u0007\"1\u0011QE\u0016A\u0002\rCq!!\u000b,\u0001\u0004\ti\u0003"
)
public class JobData {
   private final int jobId;
   private final String name;
   private final Option description;
   private final Option submissionTime;
   private final Option completionTime;
   private final Seq stageIds;
   private final Option jobGroup;
   private final Seq jobTags;
   private final JobExecutionStatus status;
   private final int numTasks;
   private final int numActiveTasks;
   private final int numCompletedTasks;
   private final int numSkippedTasks;
   private final int numFailedTasks;
   private final int numKilledTasks;
   private final int numCompletedIndices;
   private final int numActiveStages;
   private final int numCompletedStages;
   private final int numSkippedStages;
   private final int numFailedStages;
   private final Map killedTasksSummary;

   public int jobId() {
      return this.jobId;
   }

   public String name() {
      return this.name;
   }

   public Option description() {
      return this.description;
   }

   public Option submissionTime() {
      return this.submissionTime;
   }

   public Option completionTime() {
      return this.completionTime;
   }

   public Seq stageIds() {
      return this.stageIds;
   }

   public Option jobGroup() {
      return this.jobGroup;
   }

   public Seq jobTags() {
      return this.jobTags;
   }

   public JobExecutionStatus status() {
      return this.status;
   }

   public int numTasks() {
      return this.numTasks;
   }

   public int numActiveTasks() {
      return this.numActiveTasks;
   }

   public int numCompletedTasks() {
      return this.numCompletedTasks;
   }

   public int numSkippedTasks() {
      return this.numSkippedTasks;
   }

   public int numFailedTasks() {
      return this.numFailedTasks;
   }

   public int numKilledTasks() {
      return this.numKilledTasks;
   }

   public int numCompletedIndices() {
      return this.numCompletedIndices;
   }

   public int numActiveStages() {
      return this.numActiveStages;
   }

   public int numCompletedStages() {
      return this.numCompletedStages;
   }

   public int numSkippedStages() {
      return this.numSkippedStages;
   }

   public int numFailedStages() {
      return this.numFailedStages;
   }

   public Map killedTasksSummary() {
      return this.killedTasksSummary;
   }

   public JobData(final int jobId, final String name, final Option description, final Option submissionTime, final Option completionTime, final Seq stageIds, final Option jobGroup, final Seq jobTags, final JobExecutionStatus status, final int numTasks, final int numActiveTasks, final int numCompletedTasks, final int numSkippedTasks, final int numFailedTasks, final int numKilledTasks, final int numCompletedIndices, final int numActiveStages, final int numCompletedStages, final int numSkippedStages, final int numFailedStages, final Map killedTasksSummary) {
      this.jobId = jobId;
      this.name = name;
      this.description = description;
      this.submissionTime = submissionTime;
      this.completionTime = completionTime;
      this.stageIds = stageIds;
      this.jobGroup = jobGroup;
      this.jobTags = jobTags;
      this.status = status;
      this.numTasks = numTasks;
      this.numActiveTasks = numActiveTasks;
      this.numCompletedTasks = numCompletedTasks;
      this.numSkippedTasks = numSkippedTasks;
      this.numFailedTasks = numFailedTasks;
      this.numKilledTasks = numKilledTasks;
      this.numCompletedIndices = numCompletedIndices;
      this.numActiveStages = numActiveStages;
      this.numCompletedStages = numCompletedStages;
      this.numSkippedStages = numSkippedStages;
      this.numFailedStages = numFailedStages;
      this.killedTasksSummary = killedTasksSummary;
   }
}
