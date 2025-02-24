/*
 * Tencent is pleased to support the open source community by making Polaris available.
 *
 * Copyright (C) 2019 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the BSD 3-Clause License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.tencent.polaris.discovery.client.flow;

import com.tencent.polaris.api.pojo.ServiceEventKey;
import com.tencent.polaris.api.pojo.ServiceEventKeysProvider;
import com.tencent.polaris.api.pojo.ServiceKey;
import com.tencent.polaris.api.rpc.WatchServiceRequest;

import java.util.Collections;
import java.util.Set;

/**
 * @author <a href="mailto:liaochuntao@live.com">liaochuntao</a>
 */
public class CommonWatchServiceRequest  implements ServiceEventKeysProvider {

    private final boolean watch;

    private final WatchServiceRequest watchServiceRequest;

    private final ServiceEventKey eventKey;

    private CommonInstancesRequest allRequest;

    public CommonWatchServiceRequest(WatchServiceRequest watchServiceRequest, boolean watch) {
        this.watchServiceRequest = watchServiceRequest;
        this.watch = watch;
        this.eventKey = ServiceEventKey
                .builder()
                .serviceKey(new ServiceKey(watchServiceRequest.getNamespace(), watchServiceRequest.getService()))
                .eventType(ServiceEventKey.EventType.INSTANCE).
                build();
    }

    public CommonWatchServiceRequest(WatchServiceRequest watchServiceRequest, CommonInstancesRequest allRequest, boolean watch) {
        this(watchServiceRequest, watch);
        this.allRequest = allRequest;
    }

    @Override
    public boolean isUseCache() {
        return false;
    }

    @Override
    public Set<ServiceEventKey> getSvcEventKeys() {
        return Collections.emptySet();
    }

    @Override
    public ServiceEventKey getSvcEventKey() {
        return eventKey;
    }

    public boolean isWatch() {
        return watch;
    }

    public WatchServiceRequest getWatchServiceRequest() {
        return watchServiceRequest;
    }

    public CommonInstancesRequest getAllRequest() {
        return allRequest;
    }
}
