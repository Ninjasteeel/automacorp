package com.emse.spring.automacorp.Controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.emse.spring.automacorp.dto.Sensor;
import com.emse.spring.automacorp.dto.SensorCommand;
import com.emse.spring.automacorp.model.SensorEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.emse.spring.automacorp.model.SensorType.TEMPERATURE;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.emse.spring.automacorp.dto.SensorCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SensorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenSensorDoesNotExist_whenSensorInfoIsRetrieved_then404IsReceived() throws Exception {
        // Given
        Long sensorId = 999L;

        // When/Then
        mockMvc.perform(get("/api/sensors/{id}", sensorId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenRequestWithNoAcceptHeader_whenRequestIsExecuted_thenDefaultResponseContentTypeIsJson() throws Exception {
        // Given/When/Then
        mockMvc.perform(get("/api/sensors/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void givenSensorExists_whenSensorInformationIsRetrieved_thenRetrievedResourceIsCorrect() throws Exception {
        // Given/When/Then
        mockMvc.perform(get("/api/sensors/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Sample Sensor"))
                .andExpect(jsonPath("$.value").value(25.0));
    }

    @Test
    public void givenExistingSensor_whenSensorIsUpdated_thenUpdatedSensorIsReturned() throws Exception {
        // Given
        Long sensorId = -10L;
        SensorCommand updatedSensor = new SensorCommand("Updated Sensor", 30.0, TEMPERATURE, sensorId);

        // When/Then
        mockMvc.perform(put("/api/sensors/{id}", sensorId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedSensor)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Updated Sensor"))
                .andExpect(jsonPath("$.value").value(30.0));
    }

    @Test
    public void givenSensorExists_whenSensorIsDeleted_thenNoContentStatusIsReturned() throws Exception {
        // Given
        Long sensorId = -10L;

        // When/Then
        mockMvc.perform(delete("/api/sensors/{id}", sensorId)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) // Set Content-Type header
                        .with(csrf())) // Include CSRF token
                .andExpect(status().isNoContent());

    }

    // Utility method to convert object to JSON
    private String asJsonString(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
