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
import cn.beeop.BeeObjectFactory;
import com.influxdb.client.domain.HealthCheck;

import java.util.Properties;

import static cn.beeop.pool.StaticCenter.isBlank;

/**
 * Influxdb Client Factory on BeeOP
 *
 * @author Chris.Liao
 * @version 1.0
 */
public class InfluxdbBeeClientFactory implements BeeObjectFactory {
    public Object create(Properties prop) throws BeeObjectException {
        String user=prop.getProperty("user");
        String password=prop.getProperty("password");
        String serverUrl=prop.getProperty("serverUrl");
        if(isBlank(user)) throw new BeeObjectException("user cant't be null or empty");
        if(isBlank(password))throw new BeeObjectException("password cant't be null or empty");
        if(isBlank(serverUrl))throw new BeeObjectException("serverUrl cant't be null or empty");

        char[]passwordChars=password.toCharArray();
        InfluxDBClient client= InfluxDBClientFactory.create(serverUrl,user, passwordChars);
        return client;
    }

    public void setDefault(Object obj) throws BeeObjectException { }
    public void reset(Object obj) throws BeeObjectException { }
    public void destroy(Object obj) {
       ((InfluxDBClient)obj).close();
    }

    public boolean isAlive(Object obj, int timeout) {
        InfluxDBClient client= (InfluxDBClient)obj;
        try {
            HealthCheck check=client.health();
            if(HealthCheck.StatusEnum.PASS.equals(check.getStatus())){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}