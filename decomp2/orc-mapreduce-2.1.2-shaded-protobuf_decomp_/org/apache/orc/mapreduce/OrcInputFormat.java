package org.apache.orc.mapreduce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.hive.ql.io.sarg.SearchArgument;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.orc.OrcConf;
import org.apache.orc.OrcFile;
import org.apache.orc.Reader;

public class OrcInputFormat extends FileInputFormat {
   public static void setSearchArgument(Configuration conf, SearchArgument sarg, String[] columnNames) {
      org.apache.orc.mapred.OrcInputFormat.setSearchArgument(conf, sarg, columnNames);
   }

   public RecordReader createRecordReader(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
      FileSplit split = (FileSplit)inputSplit;
      Configuration conf = taskAttemptContext.getConfiguration();
      Reader file = OrcFile.createReader(split.getPath(), OrcFile.readerOptions(conf).maxLength(OrcConf.MAX_FILE_LENGTH.getLong(conf)));
      Reader.Options options = org.apache.orc.mapred.OrcInputFormat.buildOptions(conf, file, split.getStart(), split.getLength()).useSelected(true);
      return new OrcMapreduceRecordReader(file, options);
   }

   protected List listStatus(JobContext job) throws IOException {
      List<FileStatus> complete = super.listStatus(job);
      List<FileStatus> result = new ArrayList(complete.size());

      for(FileStatus stat : complete) {
         if (stat.getLen() != 0L) {
            result.add(stat);
         }
      }

      return result;
   }
}
