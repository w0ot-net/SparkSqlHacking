package org.fusesource.jansi;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class HtmlAnsiOutputStream extends AnsiOutputStream {
   private boolean concealOn = false;
   private static final String[] ANSI_COLOR_MAP = new String[]{"black", "red", "green", "yellow", "blue", "magenta", "cyan", "white"};
   private static final byte[] BYTES_QUOT = "&quot;".getBytes();
   private static final byte[] BYTES_AMP = "&amp;".getBytes();
   private static final byte[] BYTES_LT = "&lt;".getBytes();
   private static final byte[] BYTES_GT = "&gt;".getBytes();
   private List closingAttributes = new ArrayList();

   public void close() throws IOException {
      this.closeAttributes();
      super.close();
   }

   public HtmlAnsiOutputStream(OutputStream os) {
      super(os);
   }

   private void write(String s) throws IOException {
      super.out.write(s.getBytes());
   }

   private void writeAttribute(String s) throws IOException {
      this.write("<" + s + ">");
      this.closingAttributes.add(0, s.split(" ", 2)[0]);
   }

   private void closeAttributes() throws IOException {
      for(String attr : this.closingAttributes) {
         this.write("</" + attr + ">");
      }

      this.closingAttributes.clear();
   }

   public void write(int data) throws IOException {
      switch (data) {
         case 34:
            this.out.write(BYTES_QUOT);
            break;
         case 38:
            this.out.write(BYTES_AMP);
            break;
         case 60:
            this.out.write(BYTES_LT);
            break;
         case 62:
            this.out.write(BYTES_GT);
            break;
         default:
            super.write(data);
      }

   }

   public void writeLine(byte[] buf, int offset, int len) throws IOException {
      this.write(buf, offset, len);
      this.closeAttributes();
   }

   protected void processSetAttribute(int attribute) throws IOException {
      switch (attribute) {
         case 1:
            this.writeAttribute("b");
            break;
         case 4:
            this.writeAttribute("u");
         case 7:
         case 27:
         default:
            break;
         case 8:
            this.write("\u001b[8m");
            this.concealOn = true;
            break;
         case 22:
            this.closeAttributes();
            break;
         case 24:
            this.closeAttributes();
      }

   }

   protected void processAttributeRest() throws IOException {
      if (this.concealOn) {
         this.write("\u001b[0m");
         this.concealOn = false;
      }

      this.closeAttributes();
   }

   protected void processSetForegroundColor(int color, boolean bright) throws IOException {
      this.writeAttribute("span style=\"color: " + ANSI_COLOR_MAP[color] + ";\"");
   }

   protected void processSetBackgroundColor(int color, boolean bright) throws IOException {
      this.writeAttribute("span style=\"background-color: " + ANSI_COLOR_MAP[color] + ";\"");
   }
}
