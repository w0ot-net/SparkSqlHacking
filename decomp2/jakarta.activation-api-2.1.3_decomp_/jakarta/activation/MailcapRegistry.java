package jakarta.activation;

import java.util.Map;

public interface MailcapRegistry {
   Map getMailcapList(String var1);

   Map getMailcapFallbackList(String var1);

   String[] getMimeTypes();

   String[] getNativeCommands(String var1);

   void appendToMailcap(String var1);
}
