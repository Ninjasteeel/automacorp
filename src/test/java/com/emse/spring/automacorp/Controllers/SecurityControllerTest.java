package com.emse.spring.automacorp.Controllers;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.core.userdetails.UserDetails;


import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(SecurityController.class)
class SecurityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "testUser", roles = "USER")
    void testFindUserName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/users/me"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testUser"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testAdminOnlyEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/users/admin-only"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Accessible only to Admin"));
    }

    @Test
    @WithMockUser(username = "regularUser", roles = "USER")
    void testAdminOnlyEndpointAccessDeniedForUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/users/admin-only"))
                .andExpect(status().isForbidden());
    }
}