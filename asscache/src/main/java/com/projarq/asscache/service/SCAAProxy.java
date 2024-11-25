package com.projarq.asscache.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

@FeignClient(name = "scaa")
public interface SCAAProxy {
    @GetMapping("/assinaturas/validade/{signatureId}")
    public Date getSignatureExpiryDate(@PathVariable String signatureId);
}
