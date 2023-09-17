package queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class QueueTest {

private static String QUEUE_IS_EMPTY = "Queue is empty";
private static String SECOND_ADDED_OBJECT = "Second";
private static String FIRST_ADDED_OBJECT = "First";
private static String SOMETHING = "Something";

@Test public void test01QueueShouldBeEmptyWhenCreated() {
    assertTrue( new Queue().isEmpty() );
  }

  @Test public void test02AddElementsToTheQueue() {
    assertFalse( queueWithSomething().isEmpty() );
  }

  @Test public void test03AddedElementsIsAtHead() {
    assertEquals( SOMETHING, queueWithSomething().head() );
  }

  @Test public void test04TakeRemovesElementsFromTheQueue() {
    Queue queue = queueWithSomething();
    queue.take();
    
    assertTrue( queue.isEmpty() );
  }

  @Test public void test05TakeReturnsLastAddedObject() {    
    assertEquals( SOMETHING, queueWithSomething().take() );
  }

  @Test public void test06QueueBehavesFIFO() {
    Queue queue = queueWith2Objects();

    assertEquals( queue.take(), FIRST_ADDED_OBJECT );
    assertEquals( queue.take(), SECOND_ADDED_OBJECT );
    assertTrue( queue.isEmpty() );
  }

  @Test public void test07HeadReturnsFirstAddedObject() {
    assertEquals( queueWith2Objects().head(), FIRST_ADDED_OBJECT );
  }

  @Test public void test08HeadDoesNotRemoveObjectFromQueue() {
    Queue queue = queueWithSomething();
    assertEquals( 1, queue.size() );
    queue.head();
    assertEquals( 1, queue.size() );
  }

  @Test public void test09SizeRepresentsObjectInTheQueue() {
    assertEquals( 2, queueWith2Objects().size() );
  }

  @Test public void test10CanNotTakeWhenThereAreNoObjectsInTheQueue() {
    Error e = assertThrows(Error.class, () -> new Queue().take());
    assertTrue( e.getMessage().equals( QUEUE_IS_EMPTY ) );
  }

  @Test public void test09CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
    Queue queue = queueWithSomething();
    queue.take();
    Error e = assertThrows(Error.class,() -> queue.take());
    assertTrue( e.getMessage().equals( QUEUE_IS_EMPTY ) );
  }

  @Test public void test10CanNotHeadWhenThereAreNoObjectsInTheQueue() {
    Error e = assertThrows(Error.class,() -> new Queue().head());
    assertTrue( e.getMessage().equals( QUEUE_IS_EMPTY ) );
  }
  private Queue queueWithSomething() {
		return new Queue().add( SOMETHING );
	}
  private Queue queueWith2Objects() {
		return new Queue().add( FIRST_ADDED_OBJECT ).add( SECOND_ADDED_OBJECT );
	}
}