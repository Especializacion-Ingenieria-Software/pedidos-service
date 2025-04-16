package co.edu.itm.restaurant.orders.application.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HealthControllerTest {

    private MockMvc mockMvc;
    private HealthController healthController;

    @BeforeEach
    public void setUp() {
        healthController = new HealthController();
        mockMvc = MockMvcBuilders.standaloneSetup(healthController).build();
    }

    @Test
    public void health_ShouldReturnServiceOk() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/health")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("service's ok"));
    }
}