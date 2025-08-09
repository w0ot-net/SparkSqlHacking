package org.apache.ivy.plugins.trigger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.apache.ivy.core.IvyPatternHelper;
import org.apache.ivy.core.event.IvyEvent;
import org.apache.ivy.core.resolve.ResolveProcessException;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.StringUtils;

public class LogTrigger extends AbstractTrigger {
   private static final String LINE_SEPARATOR = System.lineSeparator();
   private String message = "";
   private File file = null;
   private boolean append = true;
   private String encoding;

   public void progress(IvyEvent event) {
      this.log(IvyPatternHelper.substituteVariables(this.message, event.getAttributes()));
   }

   protected void log(String message) {
      if (this.file == null) {
         Message.info(message);
      } else {
         Writer out = null;

         try {
            message = message + LINE_SEPARATOR;
            String filename = this.file.getAbsolutePath();
            if (StringUtils.isNullOrEmpty(this.encoding)) {
               out = new FileWriter(filename, this.append);
            } else {
               out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename, this.append), this.encoding));
            }

            out.write(message, 0, message.length());
         } catch (IOException e) {
            throw new ResolveProcessException(e);
         } finally {
            if (out != null) {
               try {
                  out.close();
               } catch (IOException e) {
                  throw new ResolveProcessException(e);
               }
            }

         }
      }

   }

   public void setMessage(String msg) {
      this.message = msg;
   }

   public void setFile(File file) {
      this.file = file;
   }

   public void setAppend(boolean append) {
      this.append = append;
   }

   public void setEncoding(String encoding) {
      this.encoding = encoding;
   }
}
