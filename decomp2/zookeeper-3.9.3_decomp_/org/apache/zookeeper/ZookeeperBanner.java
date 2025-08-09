package org.apache.zookeeper;

import org.slf4j.Logger;

public class ZookeeperBanner {
   private static final String[] BANNER = new String[]{"", "  ______                  _                                          ", " |___  /                 | |                                         ", "    / /    ___     ___   | | __   ___    ___   _ __     ___   _ __   ", "   / /    / _ \\   / _ \\  | |/ /  / _ \\  / _ \\ | '_ \\   / _ \\ | '__|", "  / /__  | (_) | | (_) | |   <  |  __/ |  __/ | |_) | |  __/ | |    ", " /_____|  \\___/   \\___/  |_|\\_\\  \\___|  \\___| | .__/   \\___| |_|", "                                              | |                     ", "                                              |_|                     ", ""};

   public static void printBanner(Logger log) {
      for(String line : BANNER) {
         log.info(line);
      }

   }
}
