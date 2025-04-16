package co.edu.itm.restaurant.orders.application.controllers;

import co.edu.itm.restaurant.orders.application.dto.OrderDTO;
import co.edu.itm.restaurant.orders.application.dto.OrderItemDTO;
import co.edu.itm.restaurant.orders.application.services.OrderService;
import co.edu.itm.restaurant.orders.domain.entities.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class OrderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Order testOrder;
    private OrderDTO testOrderDTO;
    private List<Order> testOrders;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();

        // Configurar una orden de prueba
        testOrder = new Order();
        testOrder.setId(1);
        testOrder.setCustomer(100);
        testOrder.setComments("Test order");
        testOrder.setOrderTotal(50.0);
        testOrder.setItems(201);

        // Configurar un DTO de orden de prueba
        testOrderDTO = new OrderDTO();
        testOrderDTO.setId(1);
        testOrderDTO.setCustomer(100);
        testOrderDTO.setComments("Test order");
        testOrderDTO.setOrderTotal(50.0);

        List<OrderItemDTO> items = new ArrayList<>();
        OrderItemDTO item1 = new OrderItemDTO();
        item1.setName("Pizza");
        item1.setAmount(2);
        item1.setUnitPrice(15.0);

        OrderItemDTO item2 = new OrderItemDTO();
        item2.setName("Soda");
        item2.setAmount(2);
        item2.setUnitPrice(10.0);

        items.add(item1);
        items.add(item2);
        testOrderDTO.setItems(items);

        // Configurar lista de órdenes
        Order order2 = new Order();
        order2.setId(2);
        order2.setCustomer(101);
        order2.setComments("Another test order");
        order2.setOrderTotal(75.0);
        order2.setItems(202);

        testOrders = Arrays.asList(testOrder, order2);
    }

    @Test
    public void getOrder_WhenOrderExists_ReturnsOrder() throws Exception {
        // Arrange
        when(orderService.findById(1)).thenReturn(testOrder);

        // Act & Assert
        mockMvc.perform(get("/order/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.customer").value(100))
                .andExpect(jsonPath("$.comments").value("Test order"))
                .andExpect(jsonPath("$.orderTotal").value(50.0))
                .andExpect(jsonPath("$.items").value(201));

        verify(orderService, times(1)).findById(1);
    }

    @Test
    public void getOrder_WhenOrderDoesNotExist_ReturnsNull() throws Exception {
        // Arrange
        when(orderService.findById(99)).thenReturn(null);

        // Act & Assert
        mockMvc.perform(get("/order/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(orderService, times(1)).findById(99);
    }

    @Test
    public void createOrder_ValidOrder_ReturnsCreatedOrder() throws Exception {
        // Arrange
        OrderDTO inputOrderDTO = new OrderDTO();
        inputOrderDTO.setCustomer(100);
        inputOrderDTO.setComments("New order");
        inputOrderDTO.setOrderTotal(45.0);
        inputOrderDTO.setItems(new ArrayList<>());

        when(orderService.saveOrder(any(OrderDTO.class))).thenReturn(testOrderDTO);

        // Act & Assert
        mockMvc.perform(post("/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputOrderDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.customer").value(100))
                .andExpect(jsonPath("$.comments").value("Test order"))
                .andExpect(jsonPath("$.orderTotal").value(50.0))
                .andExpect(jsonPath("$.items").isArray())
                .andExpect(jsonPath("$.items.length()").value(2));

        verify(orderService, times(1)).saveOrder(any(OrderDTO.class));
    }

    @Test
    public void getAllOrders_ReturnsAllOrders() throws Exception {
        // Arrange
        when(orderService.findAll()).thenReturn(testOrders);

        // Act & Assert
        mockMvc.perform(get("/order")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].customer").value(100))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].customer").value(101));

        verify(orderService, times(1)).findAll();
    }

    @Test
    public void getAllOrders_WhenNoOrders_ReturnsEmptyList() throws Exception {
        // Arrange
        when(orderService.findAll()).thenReturn(new ArrayList<>());

        // Act & Assert
        mockMvc.perform(get("/order")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        verify(orderService, times(1)).findAll();
    }
}