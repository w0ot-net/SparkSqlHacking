package org.apache.orc;

import java.io.IOException;
import org.apache.hadoop.fs.Path;
import org.apache.orc.protobuf.MessageOrBuilder;
import org.apache.orc.protobuf.TextFormat;

/** @deprecated */
@Deprecated
public class UnknownFormatException extends IOException {
   private final Path path;
   private final String versionString;
   private final OrcProto.PostScript postscript;

   public UnknownFormatException(Path path, String versionString, OrcProto.PostScript postscript) {
      super(String.valueOf(path) + " was written by a future ORC version " + versionString + ". This file is not readable by this version of ORC.\nPostscript: " + TextFormat.shortDebugString((MessageOrBuilder)postscript));
      this.path = path;
      this.versionString = versionString;
      this.postscript = postscript;
   }

   public Path getPath() {
      return this.path;
   }

   public String getVersionString() {
      return this.versionString;
   }

   public OrcProto.PostScript getPostscript() {
      return this.postscript;
   }
}
