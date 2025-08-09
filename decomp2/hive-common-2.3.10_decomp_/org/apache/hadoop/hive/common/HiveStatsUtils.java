package org.apache.hadoop.hive.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.conf.HiveConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HiveStatsUtils {
   private static final Logger LOG = LoggerFactory.getLogger(HiveStatsUtils.class);

   public static FileStatus[] getFileStatusRecurse(Path path, int level, FileSystem fs) throws IOException {
      if (level < 0) {
         List<FileStatus> result = new ArrayList();

         try {
            FileStatus fileStatus = fs.getFileStatus(path);
            FileUtils.listStatusRecursively(fs, fileStatus, result);
         } catch (IOException var5) {
            return new FileStatus[0];
         }

         return (FileStatus[])result.toArray(new FileStatus[result.size()]);
      } else {
         StringBuilder sb = new StringBuilder(path.toUri().getPath());

         for(int i = 0; i < level; ++i) {
            sb.append("/").append("*");
         }

         Path pathPattern = new Path(path, sb.toString());
         return fs.globStatus(pathPattern, FileUtils.HIDDEN_FILES_PATH_FILTER);
      }
   }

   public static int getNumBitVectorsForNDVEstimation(Configuration conf) throws Exception {
      float percentageError = HiveConf.getFloatVar(conf, HiveConf.ConfVars.HIVE_STATS_NDV_ERROR);
      if ((double)percentageError < (double)0.0F) {
         throw new Exception("hive.stats.ndv.error can't be negative");
      } else {
         int numBitVectors;
         if ((double)percentageError <= 2.4) {
            numBitVectors = 1024;
            LOG.info("Lowest error achievable is 2.4% but error requested is " + percentageError + "%");
            LOG.info("Choosing 1024 bit vectors..");
         } else if ((double)percentageError <= 3.4) {
            numBitVectors = 1024;
            LOG.info("Error requested is " + percentageError + "%");
            LOG.info("Choosing 1024 bit vectors..");
         } else if ((double)percentageError <= 4.8) {
            numBitVectors = 512;
            LOG.info("Error requested is " + percentageError + "%");
            LOG.info("Choosing 512 bit vectors..");
         } else if ((double)percentageError <= 6.8) {
            numBitVectors = 256;
            LOG.info("Error requested is " + percentageError + "%");
            LOG.info("Choosing 256 bit vectors..");
         } else if ((double)percentageError <= 9.7) {
            numBitVectors = 128;
            LOG.info("Error requested is " + percentageError + "%");
            LOG.info("Choosing 128 bit vectors..");
         } else if ((double)percentageError <= 13.8) {
            numBitVectors = 64;
            LOG.info("Error requested is " + percentageError + "%");
            LOG.info("Choosing 64 bit vectors..");
         } else if ((double)percentageError <= 19.6) {
            numBitVectors = 32;
            LOG.info("Error requested is " + percentageError + "%");
            LOG.info("Choosing 32 bit vectors..");
         } else if ((double)percentageError <= 28.2) {
            numBitVectors = 16;
            LOG.info("Error requested is " + percentageError + "%");
            LOG.info("Choosing 16 bit vectors..");
         } else if ((double)percentageError <= 40.9) {
            numBitVectors = 8;
            LOG.info("Error requested is " + percentageError + "%");
            LOG.info("Choosing 8 bit vectors..");
         } else if ((double)percentageError <= (double)61.0F) {
            numBitVectors = 4;
            LOG.info("Error requested is " + percentageError + "%");
            LOG.info("Choosing 4 bit vectors..");
         } else {
            numBitVectors = 2;
            LOG.info("Error requested is " + percentageError + "%");
            LOG.info("Choosing 2 bit vectors..");
         }

         return numBitVectors;
      }
   }
}
