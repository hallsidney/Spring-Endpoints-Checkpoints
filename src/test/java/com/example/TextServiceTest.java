package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

    @WebMvcTest(TextService.class)
    public class TextServiceTest {

        @Autowired
        private MockMvc mvc;

        @Test
        public void testCamelCase1() throws Exception {
            this.mvc.perform(get("/camelize?original=this_is_a_thing").accept(MediaType.TEXT_PLAIN))
                    .andExpect(status().isOk())
                    .andExpect(content().string("thisIsAThing"));
        }

        @Test
        public void testCamelCase2() throws Exception {
            this.mvc.perform(get("/camelize?original=this_is_a_thing&initialCap=true").accept(MediaType.TEXT_PLAIN))
                    .andExpect(status().isOk())
                    .andExpect(content().string("ThisIsAThing"));
        }

        @Test
        public void testBadWord1() throws Exception {
            this.mvc.perform(get("/redact?original=A little of this and a little of that&badWord=little&badWord=this").accept(MediaType.TEXT_PLAIN))
                    .andExpect(status().isOk())
                    .andExpect(content().string("A ****** of **** and a ****** of that"));
        }

        @Test
        public void testBadWord2() throws Exception {
            this.mvc.perform(get("/redact?original=A little of this and a little of that&badWord=little").accept(MediaType.TEXT_PLAIN))
                    .andExpect(status().isOk())
                    .andExpect(content().string("A ****** of this and a ****** of that"));
        }

        @Test
        public void testEncode1() throws Exception {
            MockHttpServletRequestBuilder request = post("/encode")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("message", "a little of this and a little of that")
                    .param("key", "mcxrstunopqabyzdefghijklvw");

            this.mvc.perform(request)
                    .andExpect(status().isOk())
                    .andExpect(content().string("m aohhas zt hnog myr m aohhas zt hnmh"));
        }

        @Test
        public void testSed() throws Exception {
            String find = "little";
            String replacement = "lot";
            MockHttpServletRequestBuilder request = post(String.format("/s/%s/%s", find, replacement))
                    .contentType(MediaType.TEXT_PLAIN)
                    .content("a little of this and a little of that");

            this.mvc.perform(request)
                    .andExpect(status().isOk())
                    .andExpect(content().string("a lot of this and a lot of that"));
        }


}
