package jodd.util;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class ClipboardUtil {
   public static void copyToClipboard(String str) {
      StringSelection copyItem = new StringSelection(str);
      Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
      clipboard.setContents(copyItem, (ClipboardOwner)null);
   }

   public static String getStringFromClipboard() {
      Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
      Transferable paste = clipboard.getContents((Object)null);
      if (paste == null) {
         return null;
      } else {
         try {
            return (String)paste.getTransferData(DataFlavor.stringFlavor);
         } catch (Exception var3) {
            return null;
         }
      }
   }
}
