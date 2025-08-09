package org.apache.zookeeper;

import java.util.List;
import org.apache.yetus.audience.InterfaceAudience.Public;
import org.apache.zookeeper.data.Stat;

@Public
public interface AsyncCallback {
   @Public
   public interface ACLCallback extends AsyncCallback {
      void processResult(int var1, String var2, Object var3, List var4, Stat var5);
   }

   @Public
   public interface AllChildrenNumberCallback extends AsyncCallback {
      void processResult(int var1, String var2, Object var3, int var4);
   }

   @Public
   public interface Children2Callback extends AsyncCallback {
      void processResult(int var1, String var2, Object var3, List var4, Stat var5);
   }

   @Public
   public interface ChildrenCallback extends AsyncCallback {
      void processResult(int var1, String var2, Object var3, List var4);
   }

   @Public
   public interface Create2Callback extends AsyncCallback {
      void processResult(int var1, String var2, Object var3, String var4, Stat var5);
   }

   @Public
   public interface DataCallback extends AsyncCallback {
      void processResult(int var1, String var2, Object var3, byte[] var4, Stat var5);
   }

   public interface EphemeralsCallback extends AsyncCallback {
      void processResult(int var1, Object var2, List var3);
   }

   @Public
   public interface MultiCallback extends AsyncCallback {
      void processResult(int var1, String var2, Object var3, List var4);
   }

   @Public
   public interface StatCallback extends AsyncCallback {
      void processResult(int var1, String var2, Object var3, Stat var4);
   }

   @Public
   public interface StringCallback extends AsyncCallback {
      void processResult(int var1, String var2, Object var3, String var4);
   }

   @Public
   public interface VoidCallback extends AsyncCallback {
      void processResult(int var1, String var2, Object var3);
   }
}
