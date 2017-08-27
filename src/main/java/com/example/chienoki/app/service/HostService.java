package com.example.chienoki.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.example.chienoki.domain.Host;
import com.example.chienoki.domain.HostRepository;

@Component
public class HostService {

	@Autowired
	HostRepository hostRepository;
	
	public Iterable<Host> getPage(int pageNumber) {
		PageRequest request = new PageRequest(pageNumber - 1, 3, Sort.Direction.ASC, "id");
		return hostRepository.findAll(request).getContent();
	}
	
	public void saveHost(String name, String url) {
		Host host = new Host();
		host.setName(name);
		host.setUrl(url);
		hostRepository.save(host);
	}
	
	public void deleteHost(Long id) {
		hostRepository.delete(id);
	}
}
