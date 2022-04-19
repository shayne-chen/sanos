package com.shaw.sanos.api.controller;

import com.shaw.sanos.api.controller.dto.ProviderNodeDTO;
import com.shaw.sanos.api.utils.ResponseUtil;
import com.shaw.sanos.manager.instance.InstanceNodeManager;
import com.shaw.sanos.transport.entity.Response;
import com.shaw.sanos.transport.instance.InstanceNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author shaw
 * @date 2022/3/22
 */
@RestController
@RequestMapping("/sanos/api")
public class SanosApiController {

    private static final Logger logger = LoggerFactory.getLogger(SanosApiController.class);

    @Autowired
    private InstanceNodeManager instanceNodeManager;

    @PostMapping("/register")
    public Response registerInstance(@RequestBody ProviderNodeDTO nodeDTO) {
        InstanceNode node = new InstanceNode(nodeDTO.getApplication(), nodeDTO.getIp(),
                Integer.parseInt(nodeDTO.getPort()), nodeDTO.getLastActiveTime());
        logger.info("provider注册, info: {}", node.toString());
        instanceNodeManager.addNode(node);
        return ResponseUtil.returnSuccess();
    }

    @PostMapping("/heart/beat")
    public Response heartBeatInstance(@RequestBody ProviderNodeDTO nodeDTO) {
        InstanceNode node = new InstanceNode(nodeDTO.getApplication(), nodeDTO.getIp(),
                Integer.parseInt(nodeDTO.getPort()), nodeDTO.getLastActiveTime());
        logger.info("provider heartbeat, info: {}", node.toString());
        instanceNodeManager.updateNode(node);
        return ResponseUtil.returnSuccess();
    }

    @PostMapping("/provider/shutdown")
    public Response providerShutdown(@RequestBody ProviderNodeDTO nodeDTO) {
        logger.info("provider shutdown, {}", nodeDTO.toString());
        InstanceNode node = new InstanceNode(nodeDTO.getApplication(), nodeDTO.getIp(),
                Integer.parseInt(nodeDTO.getPort()), nodeDTO.getLastActiveTime());
        instanceNodeManager.expireNode(node);
        return ResponseUtil.returnSuccess();
    }

    @GetMapping("/provider/get")
    public Response getProviderNodes(@RequestParam String application) {
        return ResponseUtil.returnSuccess(instanceNodeManager.getNodeList(application));
    }

    @GetMapping("/provider/get/batch")
    public Response batchGetProviderNodes(@RequestParam String applications) {
        String[] appNames = applications.split(",");
        return ResponseUtil.returnSuccess(instanceNodeManager.getBatchNodeList(appNames));
    }

}
