package com.example.chienoki.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.chienoki.app.service.HostService;
import com.example.chienoki.domain.Host;

@Controller
public class HostController {

	@Autowired
	private HostService hostService;
	
	@RequestMapping(value = "/hosts", method = RequestMethod.GET)
	public @ResponseBody Iterable<Host> getHosts(@RequestParam(name = "p", defaultValue = "1") int pageNumber) {
		return hostService.getPage(pageNumber);
	}
	
	@RequestMapping(value = "/hosts/save", method = RequestMethod.POST)
	public void saveHost(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "url") String url
	) {
		System.out.println(name);
		System.out.println(url);
		hostService.saveHost(name, url);
	}
	
	@RequestMapping(value = "/hosts/delete", method = RequestMethod.GET)
	public void deleteHost(@RequestParam(name = "id") Long hostId) {
		hostService.deleteHost(hostId);
	}
}
