/*
 * Copyright Chris2018998
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.influxdb.client;

import cn.beeop.BeeObjectException;
import cn.beeop.BeeObjectHandle;
import cn.beeop.BeeObjectSource;
import cn.beeop.BeeObjectSourceConfig;
/**
 * Influxdb Client pooled in BeeOP
 *
 * @author Chris.Liao
 * @version 1.0
 */

public class InfluxdbBeeClientTest {
    public static void main(String[]agrgs){
        BeeObjectSourceConfig config = new BeeObjectSourceConfig();
        config.setUsername("root");
        config.setPassword("root");
        config.setServerUrl("http://localhost:8086");
        config.setObjectFactory(new InfluxdbBeeClientFactory());
        config.setObjectInterfaces(new Class[]{InfluxDBClient.class});
        BeeObjectSource obs = null;
        BeeObjectHandle handle=null;

        try {
            obs = new BeeObjectSource(config);
            handle=obs.getObject();

            testInfluxDB((InfluxDBClient)handle.getProxyObject());
        } catch (BeeObjectException e) {
            e.printStackTrace();
        } finally {
            if(handle!=null)
                try{handle.close();} catch (BeeObjectException e) {}
            if(obs!=null)obs.close();
        }
    }

    private static void testInfluxDB(InfluxDBClient influxDBClient){
        //test......
    }
}
