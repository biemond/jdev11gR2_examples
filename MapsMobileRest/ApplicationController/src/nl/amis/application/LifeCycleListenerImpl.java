package nl.amis.application;

import oracle.adfmf.application.LifeCycleListener;

/**
 * This auto-generated application life cycle listener provides the basic structure for
 * developers needing to include their own functionality during the different stages of
 * the application life cycle.
 *
 * Common examples of functionality that might be added:
 *
 * start:
 *   1. determine if the application has updates
 *   2. determine if there already exists a local application database image
 *   3. setup any one time state for the application
 *
 * activate:
 *   1. read any application cache stores and re-populate state (if needed)
 *   2. establish/re-establish any database connections and cursors
 *   3. process any pending web-service requests
 *   4. obtain any required resources
 *
 * deactivate:
 *   1. write any restorable state to an application cache store
 *   2. close any database cursors and connections
 *   3. defer any pending web-service requests
 *   4. release held resources
 *
 * stop:
 *   1. logoff any remote services
 *
 * @see oracle.adfmf.application.LifeCycleListener
 *
 * NOTE:
 * 1. Even though this is auto generated, it is not auto registered (see #2) since doing
 *    so could incur startup of the JVM in cases where it may not otherwise be necessary.
 * 2. In order for the system to recognize an application life cycle listener
 *    it must be registered in the adfmf-application.xml file.
 * 3. Application assemblers must implement this interface if they would like to
 *    receive notification of application start, hibernation, and application resume.
 */
public class LifeCycleListenerImpl implements LifeCycleListener
{
  public LifeCycleListenerImpl()
  {
  }

  /**
   * The start method will be called at the start of the application.
   * 
   * NOTE:
   * 1. This is a <b>blocking</b> call and will freeze the user interface
   *    while the method is being executed.  If you have any longer running
   *    items you should create a background thread and do the work there.
   * 2. Only the application controller's classes will be available in this
   *    method.
   */
  public void start()
  {
    // Add code here...
  }

  /**
   * The stop method will be called at the termination of the application.
   * 
   * NOTE:
   * 1. Depending on how the application is being shutdown, this method may
   *    or may not be called. For example, if a user kills the Application from
   *    the iOS multitask UI then stop will not be called.  Because of this, each
   *    feature should save off their state in the deactivate handler.
   * 2. Only the application controller's classes will be available in this
   *    method.
   */
  public void stop()
  {
    // Add code here...
  }

  /**
   * The activate method will be called when the application is started (post
   * the start method) and when an application is resumed by the operating
   * system.
   * 
   * NOTE:
   * 1. This is a <b>blocking</b> call and will freeze the user interface
   *    while the method is being executed.  If you have any longer running
   *    items you should create a background thread and do the work there.
   * 2. Only the application controller's classes will be available in this
   *    method.
   */
  public void activate()
  {
    // Add code here...
  }

  /**
   * The deactivate method will be called as part of the application shutdown
   * process or when the application is being deactivated/hibernated by the
   * operating system.
   * 
   * NOTE:
   * 1. This is a <b>blocking</b> call and will freeze the user interface
   *    while the method is being executed.  If you have any longer running
   *    items you should create a background thread and do the work there.
   * 2. Only the application controller's classes will be available in this
   *    method.
   */
  public void deactivate()
  {
    // Add code here...
  }
}
