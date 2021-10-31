/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.dubbo.samples.client;

import java.util.HashMap;
import java.util.Map;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.samples.api.GreetingsService;

public class SimpleConsumerApplication {

  public static void main(String[] args) {
    ApplicationConfig applicationConfig = new ApplicationConfig("simple-dubbo-consumer");
    applicationConfig.setQosEnable(false);
    applicationConfig.setCompiler("jdk");
    Map<String, String> parameterMap = new HashMap<>();
    parameterMap.put("proxy", "jdk");
    applicationConfig.setParameters(parameterMap);

    ReferenceConfig<GreetingsService> reference = new ReferenceConfig<>();
    reference.setInterface(GreetingsService.class);
//    reference.setApplication(applicationConfig);
//    reference.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));

    DubboBootstrap bootstrap = DubboBootstrap.getInstance();
    bootstrap.application(applicationConfig)
        .registry(new RegistryConfig("zookeeper://127.0.0.1:2181"))
        .reference(reference)
        .start();

    GreetingsService service = reference.get();
    String message = service.sayHi("dubbo");
    System.out.println("============");
    System.out.println(message);
    System.out.println("============");
  }
}
