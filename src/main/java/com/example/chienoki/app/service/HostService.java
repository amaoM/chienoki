package com.example.chienoki.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.chienoki.domain.Host;
import com.example.chienoki.domain.HostRepository;

/**
 * 
 * @author amaomasashi
 *
 */
@Service
public class HostService {

    @Autowired
    HostRepository hostRepository;

    /**
     * @param pageNumber
     * @return
     */
    public Iterable<Host> getPage(int pageNumber) {
        PageRequest request = new PageRequest(pageNumber - 1, 3, Sort.Direction.ASC, "id");
        return hostRepository.findAll(request).getContent();
    }

    /**
     * @param name
     * @param url
     */
    public void saveHost(String name, String url) {
        Host host = new Host();
        host.setName(name);
        host.setUrl(url);
        hostRepository.save(host);
    }

    /**
     * @param id
     */
    public void deleteHost(Long id) {
        hostRepository.delete(id);
    }
}
