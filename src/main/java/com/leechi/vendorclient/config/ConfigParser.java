/*
 * Copyright (C) 2011 Toshiaki Maki <makingx@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.leechi.vendorclient.config;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Map;

public class ConfigParser {
    private static final String BUNDLE_NAME = "app-config";

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
            .getBundle(BUNDLE_NAME);

    private ConfigParser() {
    }

    public static String getValue(String key) {
        try {
            Map<String, String> variables= System.getenv();
            if (variables.containsKey(key)) {
                return variables.get(key);
            } else {
                return RESOURCE_BUNDLE.getString(key);
            }

        } catch (MissingResourceException e) {
            return null;
        }
    }
}
