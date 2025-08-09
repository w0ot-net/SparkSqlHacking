package org.apache.curator.utils;

public class DebugUtils {
   public static final String PROPERTY_LOG_EVENTS = "curator-log-events";
   public static final String PROPERTY_DONT_LOG_CONNECTION_ISSUES = "curator-dont-log-connection-problems";
   public static final String PROPERTY_LOG_ONLY_FIRST_CONNECTION_ISSUE_AS_ERROR_LEVEL = "curator-log-only-first-connection-issue-as-error-level";
   public static final String PROPERTY_REMOVE_WATCHERS_IN_FOREGROUND = "curator-remove-watchers-in-foreground";
   public static final String PROPERTY_VALIDATE_NAMESPACE_WATCHER_MAP_EMPTY = "curator-validate-namespace-watcher-map-empty";
   public static final String PROPERTY_VALIDATE_NO_REMAINING_WATCHERS = "curator-validate-no-remaining-watchers";

   private DebugUtils() {
   }
}
