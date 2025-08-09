package org.apache.orc.mapred;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.hive.ql.io.sarg.SearchArgument;
import org.apache.hadoop.hive.ql.io.sarg.SearchArgumentImpl;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;
import org.apache.orc.OrcConf;
import org.apache.orc.OrcFile;
import org.apache.orc.Reader;
import org.apache.orc.TypeDescription;
import org.apache.orc.TypeDescription.Category;

public class OrcInputFormat extends FileInputFormat {
   private static final int KRYO_SARG_MAX_BUFFER = 16777216;

   public static boolean[] parseInclude(TypeDescription schema, String columnsStr) {
      if (columnsStr != null && schema.getCategory() == Category.STRUCT) {
         boolean[] result = new boolean[schema.getMaximumId() + 1];
         result[0] = true;
         if (columnsStr.isBlank()) {
            return result;
         } else {
            List<TypeDescription> types = schema.getChildren();

            for(String idString : columnsStr.split(",")) {
               TypeDescription type = (TypeDescription)types.get(Integer.parseInt(idString));

               for(int c = type.getId(); c <= type.getMaximumId(); ++c) {
                  result[c] = true;
               }
            }

            return result;
         }
      } else {
         return null;
      }
   }

   public static void setSearchArgument(Configuration conf, SearchArgument sarg, String[] columnNames) {
      int bufferSize = (int)OrcConf.KRYO_SARG_BUFFER.getLong(conf);
      Output out = new Output(bufferSize, 16777216);
      (new Kryo()).writeObject(out, sarg);
      OrcConf.KRYO_SARG.setString(conf, Base64.getMimeEncoder().encodeToString(out.toBytes()));
      StringBuilder buffer = new StringBuilder();

      for(int i = 0; i < columnNames.length; ++i) {
         if (i != 0) {
            buffer.append(',');
         }

         buffer.append(columnNames[i]);
      }

      OrcConf.SARG_COLUMNS.setString(conf, buffer.toString());
   }

   public static Reader.Options buildOptions(Configuration conf, Reader reader, long start, long length) {
      TypeDescription schema = TypeDescription.fromString(OrcConf.MAPRED_INPUT_SCHEMA.getString(conf));
      Reader.Options options = reader.options().range(start, length).useZeroCopy(OrcConf.USE_ZEROCOPY.getBoolean(conf)).skipCorruptRecords(OrcConf.SKIP_CORRUPT_DATA.getBoolean(conf)).tolerateMissingSchema(OrcConf.TOLERATE_MISSING_SCHEMA.getBoolean(conf));
      if (schema != null) {
         options.schema(schema);
      } else {
         schema = reader.getSchema();
      }

      options.include(parseInclude(schema, OrcConf.INCLUDE_COLUMNS.getString(conf)));
      String kryoSarg = OrcConf.KRYO_SARG.getString(conf);
      String sargColumns = OrcConf.SARG_COLUMNS.getString(conf);
      if (kryoSarg != null && sargColumns != null) {
         byte[] sargBytes = Base64.getMimeDecoder().decode(kryoSarg);
         SearchArgument sarg = (SearchArgument)(new Kryo()).readObject(new Input(sargBytes), SearchArgumentImpl.class);
         options.searchArgument(sarg, sargColumns.split(","));
      }

      return options;
   }

   public RecordReader getRecordReader(InputSplit inputSplit, JobConf conf, Reporter reporter) throws IOException {
      FileSplit split = (FileSplit)inputSplit;
      Reader file = OrcFile.createReader(split.getPath(), OrcFile.readerOptions(conf).maxLength(OrcConf.MAX_FILE_LENGTH.getLong(conf)));
      Reader.Options options = buildOptions(conf, file, split.getStart(), split.getLength()).useSelected(true);
      return new OrcMapredRecordReader(file, options);
   }

   protected FileStatus[] listStatus(JobConf job) throws IOException {
      FileStatus[] result = super.listStatus(job);
      List<FileStatus> ok = new ArrayList(result.length);

      for(FileStatus stat : result) {
         if (stat.getLen() != 0L) {
            ok.add(stat);
         }
      }

      if (ok.size() == result.length) {
         return result;
      } else {
         return (FileStatus[])ok.toArray(new FileStatus[0]);
      }
   }
}
