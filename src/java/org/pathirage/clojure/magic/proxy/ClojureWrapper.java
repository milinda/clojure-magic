/**
 * Licensed to Milinda Pathirage under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.pathirage.clojure.magic.proxy;

import backtype.storm.utils.Utils;
import clojure.lang.IFn;
import clojure.lang.RT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

public class ClojureWrapper {
    private static Logger LOG = LoggerFactory.getLogger(ClojureWrapper.class);
    private List<String> prepFnSpec;
    private List<Object> params;

    ClojureProxy proxy;

    public ClojureWrapper(List<String> prepFnSpec, List<Object> params){
        this.prepFnSpec = prepFnSpec;
        this.params = params;
    }

    public void prepare(){
        LOG.info("Prepare function namespace: " + prepFnSpec.get(0));
        LOG.info("Prepare function name: " + prepFnSpec.get(1));
        // Prepare boltFilter from clojure
        IFn hof = Utils.loadClojureFn(prepFnSpec.get(0), prepFnSpec.get(1));
        try {
            IFn preparer = (IFn) hof.applyTo(RT.seq(params));

            proxy = (ClojureProxy) preparer.applyTo(RT.seq(Collections.emptyList()));
        } catch (Exception e) {
            LOG.error("Something went wrong during initialization of SpoutFilter.", e);
            throw new RuntimeException(e);
        }
    }

    public void execute(){
        proxy.execute();
    }
}
