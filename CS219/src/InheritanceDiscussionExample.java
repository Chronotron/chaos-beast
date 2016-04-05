import java.io.PrintStream;

/***************************************************
 * InheritanceDiscussionExample.java
 * Paul Parker
 * <p>
 * Demonstrates inheritance
 ****************************************************/
public class InheritanceDiscussionExample {
    public static void main(String[] args) {
        // initialize services
        System.out.println("Creating a new HttpService");
        HttpService httpService = new HttpService();
        System.out.println("Creating a new InventoryHttpService");
        InventoryHttpService inventoryHttpService = new InventoryHttpService();
        System.out.println("");

        // demonstrate get
        System.out.println("---");
        System.out.println("Demonstrate both objects can get");
        demonstrateGet(httpService, "someUrl");
        demonstrateGet(inventoryHttpService, "someUrl");
        System.out.println("");

        // demonstrate post
        System.out.println("---");
        System.out.println("Demonstrate both objects can post");
        demonstratePost(httpService, "someUrl");
        demonstratePost(inventoryHttpService, "someUrl");
        System.out.println("");

        // demonstrate unique behavior
        System.out.println("---");
        System.out.println("Demonstrate unique behavior of the InventoryHttpService");
        demonstrateInventoryHttpServiceBehavior(httpService);
        demonstrateInventoryHttpServiceBehavior(inventoryHttpService);
    }

    /**
     * demonstrates a mock get operation on and HttpService
     *
     * @param service service for demonstration
     * @param url     for demonstration
     */
    private static void demonstrateGet(HttpService service, String url) {
        service.get(url);
    }

    /**
     * demonstrate behavior that is only applicable to the {@link InventoryHttpService}
     *
     * @param service generic service for demonstration
     */
    private static void demonstrateInventoryHttpServiceBehavior(HttpService service) {
        InventoryHttpService inventoryHttpService; // service for demonstration
        if (service instanceof InventoryHttpService) {
            inventoryHttpService = (InventoryHttpService) service;
            inventoryHttpService.getInventory();
            inventoryHttpService.postOrders("new shoes");
        } else {
            System.out.println("This object was an HttpService but not an InventoryHttpService");
        }
    }

    /**
     * demonstrates a mock post operation on and HttpService
     *
     * @param service service for demonstration
     * @param url     for demonstration
     */
    private static void demonstratePost(HttpService service, String url) {
        service.post(url);
    }

    private static class HttpService {

        //***********************************************************

        public HttpService() {
            System.out.println("I can get and post");
        }

        //***********************************************************

        /**
         * @param url url for a mock http get operation
         */
        public void get(String url) {
            System.out.println("Get: " + url);
        }

        /**
         * @param url url for a mock http post operation
         */
        public void post(String url) {
            System.out.println("Post: " + url);
        }

    } // end HttpService class

    private static class InventoryHttpService extends HttpService {

        //***********************************************************

        public InventoryHttpService() {
            System.out.println("I can get inventory and post orders");
        }

        //***********************************************************

        /**
         * makes a mock http request to get inventory
         */
        public void getInventory() {
            get("Inventory");
        }

        /**
         * makes a mock http request for posting orders
         *
         * @param orders orders to be posted
         */
        public void postOrders(String orders) {
            post("Orders=" + orders);
        }

    } // end InventoryHttpService class

} // end InheritanceDiscussionExample class
