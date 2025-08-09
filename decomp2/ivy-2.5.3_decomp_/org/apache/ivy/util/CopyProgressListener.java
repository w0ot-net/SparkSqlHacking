package org.apache.ivy.util;

public interface CopyProgressListener {
   void start(CopyProgressEvent var1);

   void progress(CopyProgressEvent var1);

   void end(CopyProgressEvent var1);
}
