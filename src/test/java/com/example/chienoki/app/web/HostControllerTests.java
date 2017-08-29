package com.example.chienoki.app.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.chienoki.app.service.HostService;
import com.example.chienoki.domain.Host;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HostControllerTests {
	
	@Autowired
    private MockMvc mvc;

    @MockBean
    private HostService hostService;
    
	@Test
	public void testHello() throws Exception {
		List<Host> hosts = new ArrayList<Host>();
		when(hostService.getPage(1)).thenReturn(hosts);
		mvc.perform(get("/hosts"))
			.andExpect(status().isOk());
	}
}
