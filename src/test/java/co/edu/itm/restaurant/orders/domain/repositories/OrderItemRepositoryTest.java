package co.edu.itm.restaurant.orders.domain.repositories;

import co.edu.itm.restaurant.orders.domain.entities.OrderItem;
import co.edu.itm.restaurant.orders.domain.value_objects.Item;
import co.edu.itm.restaurant.orders.infraestructure.persistence.IOrderItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderItemRepositoryTest {

    @Mock
    private IOrderItemRepository iOrderItemRepository;

    @InjectMocks
    private OrderItemRepository orderItemRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave_ShouldSaveOrderItem() {
        // Arrange
        Item item1 = new Item(1, "Pizza", 2, 10.5);
        Item item2 = new Item(2, "Soda", 1, 2.0);
        List<Item> items = Arrays.asList(item1, item2);

        OrderItem orderItem = new OrderItem(1, items);

        when(iOrderItemRepository.save(any(OrderItem.class))).thenReturn(orderItem);

        // Act
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);

        // Assert
        assertNotNull(savedOrderItem);
        assertEquals(1, savedOrderItem.getId());
        assertEquals(2, savedOrderItem.getItem().size());
        assertEquals("Pizza", savedOrderItem.getItem().get(0).getName());
        assertEquals("Soda", savedOrderItem.getItem().get(1).getName());
        verify(iOrderItemRepository, times(1)).save(orderItem);
    }

    @Test
    public void testSave_WithEmptyItemList() {
        // Arrange
        OrderItem orderItem = new OrderItem();
        orderItem.setId(2);
        orderItem.setItem(Collections.emptyList()); // Usando Collections.emptyList() en lugar de List.of()

        when(iOrderItemRepository.save(any(OrderItem.class))).thenReturn(orderItem);

        // Act
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);

        // Assert
        assertNotNull(savedOrderItem);
        assertEquals(2, savedOrderItem.getId());
        assertTrue(savedOrderItem.getItem().isEmpty());
        verify(iOrderItemRepository, times(1)).save(orderItem);
    }

    @Test
    public void testSave_WhenRepositoryThrowsException() {
        // Arrange
        OrderItem orderItem = new OrderItem();
        when(iOrderItemRepository.save(any(OrderItem.class)))
                .thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            orderItemRepository.save(orderItem);
        });

        assertEquals("Database error", exception.getMessage());
        verify(iOrderItemRepository, times(1)).save(orderItem);
    }

    @Test
    public void testSave_ShouldPassThroughSameOrderItemInstance() {
        // Arrange
        OrderItem orderItem = new OrderItem();

        // Configurar el mock para que devuelva el mismo objeto que recibe
        when(iOrderItemRepository.save(any(OrderItem.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        OrderItem result = orderItemRepository.save(orderItem);

        // Assert
        assertSame(orderItem, result);
        verify(iOrderItemRepository, times(1)).save(orderItem);
    }
}