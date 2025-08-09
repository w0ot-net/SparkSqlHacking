package org.apache.hadoop.hive.ql.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.fs.ContentSummary;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextInputFormat;

public class DelegateSymlinkTextInputFormat extends SymlinkTextInputFormat {
   public RecordReader getRecordReader(InputSplit split, JobConf job, Reporter reporter) throws IOException {
      DelegateSymlinkTextInputSplit delegateSplit = (DelegateSymlinkTextInputSplit)split;
      InputSplit targetSplit = delegateSplit.getSplit().getTargetSplit();
      TextInputFormat inputFormat = new TextInputFormat();
      inputFormat.configure(job);
      return inputFormat.getRecordReader(targetSplit, job, reporter);
   }

   public InputSplit[] getSplits(JobConf job, int numSplits) throws IOException {
      InputSplit[] splits = super.getSplits(job, numSplits);

      for(int i = 0; i < splits.length; ++i) {
         SymlinkTextInputFormat.SymlinkTextInputSplit split = (SymlinkTextInputFormat.SymlinkTextInputSplit)splits[i];
         splits[i] = new DelegateSymlinkTextInputSplit(split);
      }

      return splits;
   }

   public void configure(JobConf job) {
      super.configure(job);
   }

   public ContentSummary getContentSummary(Path p, JobConf job) throws IOException {
      return super.getContentSummary(p, job);
   }

   public static class DelegateSymlinkTextInputSplit extends FileSplit {
      private Path targetPath;

      public DelegateSymlinkTextInputSplit() {
         super((Path)null, 0L, 0L, (String[])null);
         this.targetPath = null;
      }

      public DelegateSymlinkTextInputSplit(SymlinkTextInputFormat.SymlinkTextInputSplit split) throws IOException {
         super(split.getPath(), split.getTargetSplit().getStart(), split.getTargetSplit().getLength(), split.getTargetSplit().getLocations());
         this.targetPath = split.getTargetSplit().getPath();
      }

      public Path getTargetPath() {
         return this.targetPath;
      }

      private SymlinkTextInputFormat.SymlinkTextInputSplit getSplit() throws IOException {
         return new SymlinkTextInputFormat.SymlinkTextInputSplit(this.getPath(), new FileSplit(this.targetPath, this.getStart(), this.getLength(), this.getLocations()));
      }

      public void write(DataOutput out) throws IOException {
         super.write(out);
         Text.writeString(out, this.targetPath != null ? this.targetPath.toString() : "");
      }

      public void readFields(DataInput in) throws IOException {
         super.readFields(in);
         String target = Text.readString(in);
         this.targetPath = !target.isEmpty() ? new Path(target) : null;
      }
   }
}
