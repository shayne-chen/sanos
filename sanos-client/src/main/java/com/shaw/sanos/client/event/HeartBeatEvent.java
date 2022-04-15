package com.shaw.sanos.client.event;

import com.shaw.sanos.client.dto.ProviderDTO;
import org.springframework.context.ApplicationEvent;

/**
 * @author shaw
 * @date 2022/4/7
 */
public class HeartBeatEvent extends ApplicationEvent {

    private static final long serialVersionUID = 4280075807837989946L;

    private ProviderDTO providerDTO;

    public HeartBeatEvent(Object source, ProviderDTO providerDTO) {
        super(source);
        this.providerDTO = providerDTO;
    }

    public void setProviderDTO(ProviderDTO providerDTO) {
        this.providerDTO = providerDTO;
    }

    public ProviderDTO getProviderDTO() {
        return providerDTO;
    }
}
