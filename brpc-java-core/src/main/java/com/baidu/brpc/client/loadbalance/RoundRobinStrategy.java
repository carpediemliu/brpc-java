/*
 * Copyright (c) 2018 Baidu, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.baidu.brpc.client.loadbalance;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

import com.baidu.brpc.client.RpcClient;
import com.baidu.brpc.client.channel.BrpcChannelGroup;

/**
 * Round-robin load balance strategy implementation
 */
public class RoundRobinStrategy implements LoadBalanceStrategy {

    private AtomicLong counter = new AtomicLong(0);

    @Override
    public void init(RpcClient rpcClient) {

    }

    @Override
    public BrpcChannelGroup selectInstance(CopyOnWriteArrayList<BrpcChannelGroup> instances) {
        long instanceNum = instances.size();
        if (instanceNum == 0) {
            return null;
        }
        int index = (int) (counter.getAndIncrement() % instanceNum);
        BrpcChannelGroup channelGroup = instances.get(index);
        return channelGroup;
    }

    @Override
    public void destroy() {
    }
}
