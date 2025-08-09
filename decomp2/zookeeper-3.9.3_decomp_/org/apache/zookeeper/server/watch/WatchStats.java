package org.apache.zookeeper.server.watch;

public final class WatchStats {
   private static final WatchStats[] WATCH_STATS = new WatchStats[]{new WatchStats(0), new WatchStats(1), new WatchStats(2), new WatchStats(3), new WatchStats(4), new WatchStats(5), new WatchStats(6), new WatchStats(7)};
   public static final WatchStats NONE;
   private final int flags;

   private WatchStats(int flags) {
      this.flags = flags;
   }

   private static int modeToFlag(WatcherMode mode) {
      return 1 << mode.ordinal();
   }

   public WatchStats addMode(WatcherMode mode) {
      int flags = this.flags | modeToFlag(mode);
      return WATCH_STATS[flags];
   }

   public WatchStats removeMode(WatcherMode mode) {
      int mask = ~modeToFlag(mode);
      int flags = this.flags & mask;
      return flags == 0 ? NONE : WATCH_STATS[flags];
   }

   public boolean hasMode(WatcherMode mode) {
      int flags = modeToFlag(mode);
      return (this.flags & flags) != 0;
   }

   static {
      NONE = WATCH_STATS[0];
   }
}
