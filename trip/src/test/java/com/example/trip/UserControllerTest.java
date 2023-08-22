package com.example.trip;


import com.example.trip.controller.UserController;
import com.example.trip.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void getUserTest() throws Exception {

        // UserController uc = new UserController();


        String result = mvc.perform(MockMvcRequestBuilders.get("/user/userByName")
                                .param("username", "admin")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andReturn().getResponse().getContentAsString();
                // System.out.println("====/" + result );

    }

    @Test
    public void registerUserTest() throws Exception {
        String userInfo = "{\"username\":\"tester\",\"firstname\":\"testF\",\"lastname\":\"testL\"," +
                "\"email\":\"tester@gmail.com\"}"+ ",\"password\":\"555\"}";

        // UserController uc = new UserController();

        mvc.perform(MockMvcRequestBuilders.post("/user/register")
                        .content(userInfo)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        String result = mvc.perform(MockMvcRequestBuilders.get("/user/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();
        System.out.println("====/" + result );
    }

}