package org.snakeyaml.engine.v2.comments;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import org.snakeyaml.engine.v2.events.CommentEvent;
import org.snakeyaml.engine.v2.events.Event;
import org.snakeyaml.engine.v2.parser.Parser;

public class CommentEventsCollector {
   private final Queue eventSource;
   private final CommentType[] expectedCommentTypes;
   private List commentLineList;

   public CommentEventsCollector(final Parser parser, CommentType... expectedCommentTypes) {
      this.eventSource = new AbstractQueue() {
         public boolean offer(Event e) {
            throw new UnsupportedOperationException();
         }

         public Event poll() {
            return parser.next();
         }

         public Event peek() {
            return parser.peekEvent();
         }

         public Iterator iterator() {
            throw new UnsupportedOperationException();
         }

         public int size() {
            throw new UnsupportedOperationException();
         }
      };
      this.expectedCommentTypes = expectedCommentTypes;
      this.commentLineList = new ArrayList();
   }

   public CommentEventsCollector(Queue eventSource, CommentType... expectedCommentTypes) {
      this.eventSource = eventSource;
      this.expectedCommentTypes = expectedCommentTypes;
      this.commentLineList = new ArrayList();
   }

   private boolean isEventExpected(Event event) {
      if (event != null && event.getEventId() == Event.ID.Comment) {
         CommentEvent commentEvent = (CommentEvent)event;

         for(CommentType type : this.expectedCommentTypes) {
            if (commentEvent.getCommentType() == type) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public CommentEventsCollector collectEvents() {
      this.collectEvents((Event)null);
      return this;
   }

   public Event collectEvents(Event event) {
      if (event != null) {
         if (!this.isEventExpected(event)) {
            return event;
         }

         this.commentLineList.add(new CommentLine((CommentEvent)event));
      }

      while(this.isEventExpected((Event)this.eventSource.peek())) {
         this.commentLineList.add(new CommentLine((CommentEvent)this.eventSource.poll()));
      }

      return null;
   }

   public Event collectEventsAndPoll(Event event) {
      Event nextEvent = this.collectEvents(event);
      return nextEvent != null ? nextEvent : (Event)this.eventSource.poll();
   }

   public List consume() {
      List var1;
      try {
         var1 = this.commentLineList;
      } finally {
         this.commentLineList = new ArrayList();
      }

      return var1;
   }

   public boolean isEmpty() {
      return this.commentLineList.isEmpty();
   }
}
