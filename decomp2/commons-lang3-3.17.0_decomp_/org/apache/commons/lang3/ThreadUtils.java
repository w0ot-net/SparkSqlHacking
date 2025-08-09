package org.apache.commons.lang3;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.time.DurationUtils;

public class ThreadUtils {
   /** @deprecated */
   @Deprecated
   public static final AlwaysTruePredicate ALWAYS_TRUE_PREDICATE = new AlwaysTruePredicate();
   private static final Predicate ALWAYS_TRUE = (t) -> true;

   private static Predicate alwaysTruePredicate() {
      return ALWAYS_TRUE;
   }

   public static Thread findThreadById(long threadId) {
      if (threadId <= 0L) {
         throw new IllegalArgumentException("The thread id must be greater than zero");
      } else {
         Collection<Thread> result = findThreads((Predicate)((t) -> t != null && t.getId() == threadId));
         return result.isEmpty() ? null : (Thread)result.iterator().next();
      }
   }

   public static Thread findThreadById(long threadId, String threadGroupName) {
      Objects.requireNonNull(threadGroupName, "threadGroupName");
      Thread thread = findThreadById(threadId);
      return thread != null && thread.getThreadGroup() != null && thread.getThreadGroup().getName().equals(threadGroupName) ? thread : null;
   }

   public static Thread findThreadById(long threadId, ThreadGroup threadGroup) {
      Objects.requireNonNull(threadGroup, "threadGroup");
      Thread thread = findThreadById(threadId);
      return thread != null && threadGroup.equals(thread.getThreadGroup()) ? thread : null;
   }

   public static Collection findThreadGroups(Predicate predicate) {
      return findThreadGroups(getSystemThreadGroup(), true, predicate);
   }

   public static Collection findThreadGroups(ThreadGroup threadGroup, boolean recurse, Predicate predicate) {
      Objects.requireNonNull(threadGroup, "threadGroup");
      Objects.requireNonNull(predicate, "predicate");
      int count = threadGroup.activeGroupCount();

      ThreadGroup[] threadGroups;
      do {
         threadGroups = new ThreadGroup[count + count / 2 + 1];
         count = threadGroup.enumerate(threadGroups, recurse);
      } while(count >= threadGroups.length);

      return Collections.unmodifiableCollection((Collection)Stream.of(threadGroups).limit((long)count).filter(predicate).collect(Collectors.toList()));
   }

   /** @deprecated */
   @Deprecated
   public static Collection findThreadGroups(ThreadGroup threadGroup, boolean recurse, ThreadGroupPredicate predicate) {
      Objects.requireNonNull(predicate);
      return findThreadGroups(threadGroup, recurse, predicate::test);
   }

   /** @deprecated */
   @Deprecated
   public static Collection findThreadGroups(ThreadGroupPredicate predicate) {
      return findThreadGroups(getSystemThreadGroup(), true, predicate);
   }

   public static Collection findThreadGroupsByName(String threadGroupName) {
      return findThreadGroups(predicateThreadGroup(threadGroupName));
   }

   public static Collection findThreads(Predicate predicate) {
      return findThreads(getSystemThreadGroup(), true, predicate);
   }

   public static Collection findThreads(ThreadGroup threadGroup, boolean recurse, Predicate predicate) {
      Objects.requireNonNull(threadGroup, "The group must not be null");
      Objects.requireNonNull(predicate, "The predicate must not be null");
      int count = threadGroup.activeCount();

      Thread[] threads;
      do {
         threads = new Thread[count + count / 2 + 1];
         count = threadGroup.enumerate(threads, recurse);
      } while(count >= threads.length);

      return Collections.unmodifiableCollection((Collection)Stream.of(threads).limit((long)count).filter(predicate).collect(Collectors.toList()));
   }

   /** @deprecated */
   @Deprecated
   public static Collection findThreads(ThreadGroup threadGroup, boolean recurse, ThreadPredicate predicate) {
      Objects.requireNonNull(predicate);
      return findThreads(threadGroup, recurse, predicate::test);
   }

   /** @deprecated */
   @Deprecated
   public static Collection findThreads(ThreadPredicate predicate) {
      return findThreads(getSystemThreadGroup(), true, predicate);
   }

   public static Collection findThreadsByName(String threadName) {
      return findThreads(predicateThread(threadName));
   }

   public static Collection findThreadsByName(String threadName, String threadGroupName) {
      Objects.requireNonNull(threadName, "threadName");
      Objects.requireNonNull(threadGroupName, "threadGroupName");
      return Collections.unmodifiableCollection((Collection)findThreadGroups(predicateThreadGroup(threadGroupName)).stream().flatMap((group) -> findThreads(group, false, predicateThread(threadName)).stream()).collect(Collectors.toList()));
   }

   public static Collection findThreadsByName(String threadName, ThreadGroup threadGroup) {
      return findThreads(threadGroup, false, predicateThread(threadName));
   }

   public static Collection getAllThreadGroups() {
      return findThreadGroups(alwaysTruePredicate());
   }

   public static Collection getAllThreads() {
      return findThreads(alwaysTruePredicate());
   }

   public static ThreadGroup getSystemThreadGroup() {
      ThreadGroup threadGroup;
      for(threadGroup = Thread.currentThread().getThreadGroup(); threadGroup != null && threadGroup.getParent() != null; threadGroup = threadGroup.getParent()) {
      }

      return threadGroup;
   }

   public static void join(Thread thread, Duration duration) throws InterruptedException {
      Objects.requireNonNull(thread);
      DurationUtils.accept(thread::join, duration);
   }

   private static Predicate namePredicate(String name, Function nameGetter) {
      return (t) -> t != null && Objects.equals(nameGetter.apply(t), Objects.requireNonNull(name));
   }

   private static Predicate predicateThread(String threadName) {
      return namePredicate(threadName, Thread::getName);
   }

   private static Predicate predicateThreadGroup(String threadGroupName) {
      return namePredicate(threadGroupName, ThreadGroup::getName);
   }

   public static void sleep(Duration duration) throws InterruptedException {
      DurationUtils.accept(Thread::sleep, duration);
   }

   public static void sleepQuietly(Duration duration) {
      try {
         sleep(duration);
      } catch (InterruptedException var2) {
      }

   }

   /** @deprecated */
   @Deprecated
   private static final class AlwaysTruePredicate implements ThreadPredicate, ThreadGroupPredicate {
      private AlwaysTruePredicate() {
      }

      public boolean test(Thread thread) {
         return true;
      }

      public boolean test(ThreadGroup threadGroup) {
         return true;
      }
   }

   /** @deprecated */
   @Deprecated
   public static class NamePredicate implements ThreadPredicate, ThreadGroupPredicate {
      private final String name;

      public NamePredicate(String name) {
         Objects.requireNonNull(name, "name");
         this.name = name;
      }

      public boolean test(Thread thread) {
         return thread != null && thread.getName().equals(this.name);
      }

      public boolean test(ThreadGroup threadGroup) {
         return threadGroup != null && threadGroup.getName().equals(this.name);
      }
   }

   /** @deprecated */
   @Deprecated
   public static class ThreadIdPredicate implements ThreadPredicate {
      private final long threadId;

      public ThreadIdPredicate(long threadId) {
         if (threadId <= 0L) {
            throw new IllegalArgumentException("The thread id must be greater than zero");
         } else {
            this.threadId = threadId;
         }
      }

      public boolean test(Thread thread) {
         return thread != null && thread.getId() == this.threadId;
      }
   }

   /** @deprecated */
   @Deprecated
   @FunctionalInterface
   public interface ThreadGroupPredicate {
      boolean test(ThreadGroup var1);
   }

   /** @deprecated */
   @Deprecated
   @FunctionalInterface
   public interface ThreadPredicate {
      boolean test(Thread var1);
   }
}
