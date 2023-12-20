package com.nbb.feign.framework.springcloudloadbalancer;

import com.alibaba.cloud.nacos.NacosServiceInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.NoopServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
public class SameClusterPriorityLoadBalancer implements ReactorServiceInstanceLoadBalancer {

    private static Random random = new Random();

    final String clusterName;

    final String serviceId;

    ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;

    public SameClusterPriorityLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider, String serviceId, String clusterName) {
        this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
        this.serviceId = serviceId;
        this.clusterName = clusterName;
    }

    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {
        ServiceInstanceListSupplier supplier = serviceInstanceListSupplierProvider
                .getIfAvailable(NoopServiceInstanceListSupplier::new);
        return supplier.get(request).next().map(this::getInstanceResponse);
    }

    private Response<ServiceInstance> getInstanceResponse(List<ServiceInstance> instances) {
        if (instances.isEmpty()) {
            if (log.isWarnEnabled()) {
                log.warn("No servers available for service: " + serviceId);
            }
            return new EmptyResponse();
        }

        // 集群名称相同的服务实例
        List<NacosServiceInstance> sameClusterServers = instances.stream()
                .map(instance -> (NacosServiceInstance)instance)
                .filter(instance -> clusterName.equals(instance.getMetadata().get("nacos.cluster")))
                .collect(Collectors.toList());


        if (!CollectionUtils.isEmpty(sameClusterServers)) {
            return new DefaultResponse(sameClusterServers.get(random.nextInt(sameClusterServers.size())));
        }

        return new DefaultResponse(instances.get(random.nextInt(instances.size())));
    }
}
