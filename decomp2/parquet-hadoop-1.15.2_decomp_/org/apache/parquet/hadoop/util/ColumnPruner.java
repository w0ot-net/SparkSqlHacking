package org.apache.parquet.hadoop.util;

import java.io.IOException;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.hadoop.rewrite.ParquetRewriter;
import org.apache.parquet.hadoop.rewrite.RewriteOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** @deprecated */
@Deprecated
public class ColumnPruner {
   private static final Logger LOG = LoggerFactory.getLogger(ColumnPruner.class);

   public void pruneColumns(Configuration conf, Path inputFile, Path outputFile, List cols) throws IOException {
      RewriteOptions options = (new RewriteOptions.Builder(conf, inputFile, outputFile)).prune(cols).build();
      ParquetRewriter rewriter = new ParquetRewriter(options);
      rewriter.processBlocks();
      rewriter.close();
   }
}
