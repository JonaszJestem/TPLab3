package eu.jpereira.trainings.designpatterns.structural.facade;

import eu.jpereira.trainings.designpatterns.structural.facade.service.*;

public class DefaultBookStoreFacade implements BookstoreFacade {
    private CustomerDBService customerDBService;
    private BookDBService bookDBService;
    private OrderingService orderingService;
    private WarehouseService warehouseService;
    private CustomerNotificationService customerNotificationService;

    @Override
    public void placeOrder(String customerId, String isbn) {
        this.customerNotificationService.notifyClient(
            this.warehouseService.dispatch(
                    this.orderingService.createOrder(
                        this.customerDBService.findCustomerById(customerId),
                        this.bookDBService.findBookByISBN(isbn)
                    )
            )
        );
    }

    public void setCustomerDBService(CustomerDBService customerDBService) {
        this.customerDBService = customerDBService;
    }

    public void setBookDBService(BookDBService bookDBService) {
        this.bookDBService = bookDBService;
    }

    public void setOrderingService(OrderingService orderingService) {
        this.orderingService = orderingService;
    }

    public void setWarehouseService(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    public void setCustomerNotificationService(CustomerNotificationService customerNotificationService) {
        this.customerNotificationService = customerNotificationService;
    }

}
