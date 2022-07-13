package com.example.ecommerce_rookies.TestService;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestApi {

    @Autowired
    private MockMvc mvc;

    @WithMockUser(value = "ADMINADMIN" , roles = "ADMIN")
    @DisplayName("Admin Board.")
    @Test
    public void Test_RoleAdmin() throws Exception{
            MvcResult result = mvc.perform( MockMvcRequestBuilders
                            .get("http://localhost:8080/api/test/admin")
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
            assertEquals(result.getResponse().getContentAsString(),"Admin Board.");
    }
    @WithMockUser(value = "TrongKhanh2" , roles = "USER")
    @DisplayName("User Content.")
    @Test
    public void Test_RoleUser() throws Exception{
        MvcResult result = mvc.perform( MockMvcRequestBuilders
                        .get("http://localhost:8080/api/test/user")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(),"User Content.");
    }
}
